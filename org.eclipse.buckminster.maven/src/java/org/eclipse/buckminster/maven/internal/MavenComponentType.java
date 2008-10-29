/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.maven.internal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.cspec.WellKnownExports;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.builder.ComponentRequestBuilder;
import org.eclipse.buckminster.core.cspec.builder.GroupBuilder;
import org.eclipse.buckminster.core.cspec.model.ComponentName;
import org.eclipse.buckminster.core.cspec.model.DependencyAlreadyDefinedException;
import org.eclipse.buckminster.core.ctype.AbstractComponentType;
import org.eclipse.buckminster.core.ctype.IResolutionBuilder;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.IVersionDesignator;
import org.eclipse.buckminster.core.version.TripletVersion;
import org.eclipse.buckminster.core.version.VersionFactory;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.core.version.VersionSyntaxException;
import org.eclipse.buckminster.maven.MavenPlugin;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * This class is preliminary. A lot of things remain that concerns scope, plugins, exclusion in transitive dependencies
 * etc.
 * 
 * @author Thomas Hallgren
 */
public class MavenComponentType extends AbstractComponentType
{
	private static final MavenCSpecBuilder s_builder = new MavenCSpecBuilder();

	private static SimpleDateFormat s_dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.US);
	private static SimpleDateFormat s_timestampFormat = new SimpleDateFormat("yyyyMMdd'.'HHmmss", Locale.US);

	private static final Pattern s_timestampPattern = Pattern.compile(//
			"^((?:19|20)\\d{2}(?:0[1-9]|1[012])(?:0[1-9]|[12][0-9]|3[01]))" + //
			"(?:\\.((?:[01][0-9]|2[0-3])[0-5][0-9][0-5][0-9]))?$");

	public static IVersion createVersion(String versionStr) throws CoreException
	{
		versionStr = TextUtils.notEmptyTrimmedString(versionStr);
		if(versionStr == null)
			return null;

		Matcher m = s_timestampPattern.matcher(versionStr);
		if(m.matches())
			return VersionFactory.TimestampType.coerce(createTimestamp(m.group(1), m.group(2)));

		try
		{
			return VersionFactory.TripletType.fromString(versionStr);
		}
		catch(VersionSyntaxException e)
		{
			return VersionFactory.StringType.fromString(versionStr);
		}
	}

	static void addDependencies(IComponentReader reader, Document pomDoc, CSpecBuilder cspec,
			GroupBuilder archives, ExpandingProperties properties) throws CoreException
	{
		Element project = pomDoc.getDocumentElement();
		Node parentNode = null;
		Node propertiesNode = null;
		Node dependenciesNode = null;
		String groupId = null;
		String artifactId = null;
		String versionStr = null;

		for(Node child = project.getFirstChild(); child != null; child = child.getNextSibling())
		{
			if(child.getNodeType() != Node.ELEMENT_NODE)
				continue;

			String nodeName = child.getNodeName();
			if("parent".equals(nodeName))
				parentNode = child;
			else if("properties".equals(nodeName))
				propertiesNode = child;
			else if("dependencies".equals(nodeName))
				dependenciesNode = child;
			else if("groupId".equals(nodeName))
				groupId = child.getTextContent().trim();
			else if("artifactId".equals(nodeName))
				artifactId = child.getTextContent().trim();
			else if("version".equals(nodeName))
				versionStr = child.getTextContent().trim();
		}

		if(reader instanceof MavenReader && parentNode != null)
			processParentNode((MavenReader)reader, cspec, archives, properties, parentNode);

		if(groupId != null)
		{
			properties.put("project.groupId", groupId, true);
			properties.put("pom.groupId", groupId, true);
			properties.put("groupId", groupId, true);
		}
		if(artifactId != null)
		{
			properties.put("project.artifactId", artifactId, true);
			properties.put("pom.artifactId", artifactId, true);
			properties.put("artifactId", artifactId, true);
		}
		if(versionStr != null)
		{
			properties.put("project.version", versionStr, true);
			properties.put("pom.version", versionStr, true);
			properties.put("version", versionStr, true);
		}

		if(propertiesNode != null)
			processProperties(properties, propertiesNode);

		if(dependenciesNode != null)
		{
			Provider provider = reader.getProviderMatch().getProvider();
			ComponentQuery query = reader.getNodeQuery().getComponentQuery();
			for(Node dep = dependenciesNode.getFirstChild(); dep != null; dep = dep.getNextSibling())
			{
				if(dep.getNodeType() == Node.ELEMENT_NODE && "dependency".equals(dep.getNodeName()))
					addDependency(query, provider, cspec, archives, properties, dep);
			}
		}
	}

	static Date createTimestamp(String date, String time) throws CoreException
	{
		try
		{
			return (time != null)
				? s_timestampFormat.parse(date + '.' + time)
				: s_dateFormat.parse(date);
		}
		catch(ParseException e)
		{
			throw BuckminsterException.wrap(e);
		}
	}

	static IVersionDesignator createVersionDesignator(String versionStr) throws CoreException
	{
		IVersion version = createVersion(versionStr);
		if(version == null)
			return null;

		return VersionFactory.createExplicitDesignator(version);
	}

	static VersionMatch createVersionMatch(String versionStr, String typeInfo) throws CoreException
	{
		IVersion version = createVersion(versionStr);
		if(version == null)
			//
			// No version at all. Treat as if it was an unversioned SNAPSHOT
			//
			return VersionMatch.DEFAULT;
		return new VersionMatch(version, null, -1, null, typeInfo);
	}

	static boolean isSnapshotVersion(IVersion version)
	{
		return version != null && version.toString().endsWith("SNAPSHOT");
	}

	static IVersion stripFromSnapshot(IVersion version)
	{
		if(version == null)
			return null;

		String vstr = version.toString();
		if(vstr.endsWith("SNAPSHOT"))
		{
			int stripLen = 8;
			if(vstr.charAt(vstr.length() - (stripLen + 1)) == '-')
				stripLen++;
			vstr = vstr.substring(0, vstr.length() - stripLen);
		}
		try
		{
			return version.getType().fromString(vstr);
		}
		catch(VersionSyntaxException e)
		{
			return version;
		}
	}

	private static void addDependency(ComponentQuery query, Provider provider, CSpecBuilder cspec,
			GroupBuilder archives, ExpandingProperties properties, Node dep) throws CoreException
	{
		String id = null;
		String groupId = null;
		String artifactId = null;
		String versionStr = null;
		String type = null;
		boolean optional = false;
		for(Node depChild = dep.getFirstChild(); depChild != null; depChild = depChild.getNextSibling())
		{
			if(depChild.getNodeType() != Node.ELEMENT_NODE)
				continue;

			String localName = depChild.getNodeName();
			String nodeValue = depChild.getTextContent().trim();
			if("groupId".equals(localName))
				groupId = nodeValue;
			else if("artifactId".equals(localName))
				artifactId = nodeValue;
			else if("version".equals(localName))
				versionStr = nodeValue;
			else if("id".equals(localName))
				id = nodeValue;
			else if("type".equals(localName))
				type = nodeValue;
			else if("optional".equals(localName))
				optional = Boolean.parseBoolean(nodeValue);
		}

		if(optional)
			//
			// Docs etc. We skip this here since we don't generate an
			// actions that can make use of it
			//
			return;

		if(artifactId == null)
			artifactId = id;

		if(artifactId == null)
			return;

		if("plugin".equals(type))
			//
			// Maven plugin (required for Maven builds). We don't want it.
			//
			return;

		if(groupId == null)
			groupId = artifactId;

		artifactId = ExpandingProperties.expand(properties, artifactId, 0);
		groupId = ExpandingProperties.expand(properties, groupId, 0);
		if(versionStr != null)
			versionStr = ExpandingProperties.expand(properties, versionStr, 0);

		String componentName = (provider instanceof MavenProvider)
				? ((MavenProvider)provider).getComponentName(groupId, artifactId)
				: MavenProvider.getDefaultName(groupId, artifactId);

		ComponentName adviceKey = new ComponentName(componentName, null);
		if(query.skipComponent(adviceKey))
			return;

		ComponentRequestBuilder depBld = cspec.createDependencyBuilder();
		depBld.setName(componentName);

		IVersionDesignator vd = query.getVersionOverride(adviceKey);
		if(vd == null)
			vd = createVersionDesignator(versionStr);
		depBld.setVersionDesignator(vd);

		try
		{
			cspec.addDependency(depBld);
			archives.addExternalPrerequisite(componentName, WellKnownExports.JAVA_BINARIES);
		}
		catch(DependencyAlreadyDefinedException e)
		{
			ComponentRequestBuilder oldDep = cspec.getDependency(depBld.getName());
			if(!Trivial.equalsAllowNull(vd, oldDep.getVersionDesignator()))
				MavenPlugin.getLogger().warning(e.getMessage());
		}
	}

	private static void processParentNode(MavenReader reader, CSpecBuilder cspec, GroupBuilder archives,
			ExpandingProperties properties, Node parent) throws CoreException
	{
		String groupId = null;
		String artifactId = null;
		String versionStr = null;
		for(Node child = parent.getFirstChild(); child != null; child = child.getNextSibling())
		{
			if(child.getNodeType() != Node.ELEMENT_NODE)
				continue;

			String localName = child.getNodeName();
			String nodeValue = child.getTextContent().trim();
			if("groupId".equals(localName))
				groupId = nodeValue;
			else if("artifactId".equals(localName))
				artifactId = nodeValue;
			else if("version".equals(localName))
				versionStr = nodeValue;
		}

		Provider provider = reader.getProviderMatch().getProvider();
		String componentName = (provider instanceof MavenProvider)
				? ((MavenProvider)provider).getComponentName(groupId, artifactId)
				: MavenProvider.getDefaultName(groupId, artifactId);

		MapEntry entry = new MapEntry(componentName, groupId, artifactId, null);
		VersionMatch vm = reader.getVersionMatch();
		if(versionStr != null)
			vm = createVersionMatch(versionStr, vm.getArtifactInfo());

		IPath parentPath;
		MavenReaderType mrt = (MavenReaderType)reader.getReaderType();
		parentPath = mrt.getPomPath(entry, vm);

		MavenPlugin.getLogger().debug(
				"Getting POM information for parent: %s - %s at path %s", groupId, artifactId, parentPath);
		Document parentDoc = reader.getPOMDocument(entry, vm, parentPath, new NullProgressMonitor());
		if(parentDoc == null)
			return;

		addDependencies(reader, parentDoc, cspec, archives, properties);
	}

	private static void processProperties(ExpandingProperties properties, Node node)
	{
		for(Node child = node.getFirstChild(); child != null; child = child.getNextSibling())
		{
			if(child.getNodeType() != Node.ELEMENT_NODE)
				continue;
			String nodeName = child.getNodeName();
			String nodeValue = child.getTextContent().trim();
			properties.put(nodeName, nodeValue, true);
		}
	}

	public IResolutionBuilder getResolutionBuilder(IComponentReader reader, IProgressMonitor monitor)
			throws CoreException
	{
		MonitorUtils.complete(monitor);
		return s_builder;
	}

	@Override
	public IVersionDesignator getTypeSpecificDesignator(IVersionDesignator designator)
	{
		if(designator == null)
			return null;

		IVersion low = designator.getVersion();
		IVersion high = designator.getToVersion();
		boolean lowIsSnapshot = isSnapshotVersion(low);
		boolean highIsSnapshot = (high != null && isSnapshotVersion(high));
		if(!(lowIsSnapshot || highIsSnapshot))
			return designator;

		low = stripFromSnapshot(low);
		if(high != null)
			high = stripFromSnapshot(high);

		if(!(low instanceof TripletVersion))
			//
			// We cannot apply advanced semantics here so we just
			// strip the SNAPSHOT part and hope for the best.
			//
			return VersionFactory.createRangeDesignator(low, designator.includesLowerBound(), high, designator.includesUpperBound());

		StringBuilder bld = new StringBuilder();
		TripletVersion lowTriplet = (TripletVersion)low;
		if(lowIsSnapshot)
		{
			if(high == null || designator.includesLowerBound())
			{
				// [1.2.4-SNAPSHOT -> (1.2.3
				// >=1.2.4-SNAPSHOT -> (1.2.3
				//
				// Rationale:
				// In the triplet world the release 1.2.4 is higher then any 1.2.4.SNAPSHOT
				// so we need something that is lower then 1.2.4 but higher then 1.2.3. This
				// means "starting from 1.2.3 not including 1.2.3", i.e. (1.2.3. We then want
				// to include everything up to the release of 1.2.4
				//
				// The >= calls for a very high limit. We get to that later.
				//
				bld.append('(');
				int major = lowTriplet.getMajor();
				if(lowTriplet.hasMinor())
				{
					bld.append(major);
					bld.append('.');
					int minor = lowTriplet.getMinor();
					if(lowTriplet.hasMicro())
					{
						bld.append(minor);
						bld.append('.');
						bld.append(lowTriplet.getMicro() - 1);
					}
					else
						bld.append(minor - 1);
				}
				else
					bld.append(major - 1);
			}
			else
			{
				// (1.2.4.SNAPSHOT -> [1.2.4
				//
				// Rationale:
				// 1.2.4.SNAPSHOT is lower then the 1.2.4 release. We let (1.2.4.SNAPSHOT
				// mean "starting from 1.2.4.SNAPSHOT but not including the SNAPSHOT which
				// in essence, is the same as starting from, and including, the 1.2.4 release
				//
				bld.append('[');
				bld.append(lowTriplet);
			}
		}
		else
		{
			// The best we can do here is to always include the low version. We don't
			// have any semantics to apply
			//
			bld.append('[');
			bld.append(lowTriplet);
		}

		bld.append(',');
		if(designator.isExplicit())
		{
			low.toString(bld);
			bld.append(']');
		}
		else
		{
			if(high == null)
			{
				// Greater or equal. We need a ridiculously high version...
				//
				bld.append(Integer.MAX_VALUE);
				bld.append(']');
			}
			else
			{
				if(designator.includesUpperBound())
				{
					// 1.2.3.SNAPSHOT] -> 1.2.3]
					// 
					// The upper bound is included and a SNAPSHOT
					// can resolve to a release.
					//
					bld.append(high);
					bld.append(']');
				}
				else
				{
					if(highIsSnapshot)
					{
						// ,1.2.3.SNAPSHOT) -> 1.2.2]
						//
						// We cannot use 1.2.3) here since that would
						// allow 1.2.3.xxx to be included since it's
						// lower. Instead, we include all up to the
						// 1.2.2 release
						//
						TripletVersion highTriplet = (TripletVersion)high;
						int major = highTriplet.getMajor();
						if(highTriplet.hasMinor())
						{
							bld.append(major);
							bld.append('.');
							int minor = highTriplet.getMinor();
							if(highTriplet.hasMicro())
							{
								bld.append(minor);
								bld.append('.');
								bld.append(highTriplet.getMicro() - 1);
							}
							else
								bld.append(minor - 1);
						}
						else
							bld.append(major - 1);
						bld.append(']');
					}
					else
					{
						bld.append(high);
						bld.append(')');
					}
				}
			}
		}
		try
		{
			return VersionFactory.createDesignator(low.getType(), bld.toString());
		}
		catch(VersionSyntaxException e)
		{
			return designator;
		}
	}
}
