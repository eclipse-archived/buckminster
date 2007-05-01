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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.cspec.WellKnownExports;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.builder.DependencyBuilder;
import org.eclipse.buckminster.core.cspec.builder.GroupBuilder;
import org.eclipse.buckminster.core.cspec.model.ComponentName;
import org.eclipse.buckminster.core.cspec.model.DependencyAlreadyDefinedException;
import org.eclipse.buckminster.core.ctype.IResolutionBuilder;
import org.eclipse.buckminster.core.helpers.AbstractComponentType;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.IVersionDesignator;
import org.eclipse.buckminster.core.version.IVersionSelector;
import org.eclipse.buckminster.core.version.TripletVersion;
import org.eclipse.buckminster.core.version.VersionFactory;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.core.version.VersionSelectorFactory;
import org.eclipse.buckminster.core.version.VersionSyntaxException;
import org.eclipse.buckminster.maven.MavenPlugin;
import org.eclipse.buckminster.runtime.MonitorUtils;
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

	private static final Pattern s_snapshotDesignatorPattern = Pattern.compile("^(.+)-"
			+ MavenComponentType.s_snapshotExpr + '$');

	private static final String s_snapshotExpr = "SNAPSHOT";

	private static final Pattern s_timestampDesignatorPattern = Pattern.compile("^(.+)-"
			+ MavenComponentType.s_timestampExpr + '$');

	private static final String s_timestampExpr = "((?:19|20)\\d{2}(?:0[1-9]|1[012])(?:0[1-9]|[12][0-9]|3[01]))(?:\\.((?:[01][0-9]|2[0-3])[0-5][0-9][0-5][0-9]))?";

	private static final Pattern s_timestampPattern = Pattern.compile('^' + MavenComponentType.s_timestampExpr + '$');

	static void addDependencies(IComponentReader reader, Document pomDoc, IPath pomPath, CSpecBuilder cspec,
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
			processParentNode((MavenReader)reader, cspec, pomPath, archives, properties, parentNode);

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

	static IVersion createTimestamp(String date, String time) throws CoreException
	{
		StringBuilder timeBld = new StringBuilder();
		timeBld.append(date);
		if(time != null)
		{
			timeBld.append('.');
			timeBld.append(time);
		}
		return VersionFactory.TimestampType.fromString(timeBld.toString());
	}

	static IVersion createVersion(String versionStr) throws CoreException
	{
		if(versionStr == null)
			return null;

		if(MavenComponentType.s_snapshotExpr.equals(versionStr))
			return VersionFactory.defaultVersion();

		// Try Triplet. If it fails, default to String.
		// TODO: Awaits more elaborated solution involving a table of
		// supported version types per provider.
		//
		Matcher m = s_timestampPattern.matcher(versionStr);
		if(m.matches())
			return createTimestamp(m.group(1), m.group(2));

		try
		{
			return VersionFactory.TripletType.fromString(versionStr);
		}
		catch(VersionSyntaxException e)
		{
			return VersionFactory.StringType.fromString(versionStr);
		}
	}

	static IVersionDesignator createVersionDesignator(String versionStr) throws CoreException
	{
		IVersion version = createVersion(versionStr);
		if(version == null)
			return null;

		if(version instanceof TripletVersion)
		{
			TripletVersion tripletVersion = (TripletVersion)version;

			// Create a version range that is limited by the next minor
			// number (non inclusive), i.e.
			// [1.2.4,1.3.0.a)
			//
			StringBuilder bld = new StringBuilder();
			bld.append('[');
			tripletVersion.toString(bld);
			bld.append(',');
			bld.append(tripletVersion.getMajor());
			bld.append('.');
			bld.append(tripletVersion.getMinor() + 1);
			bld.append(".0.a)");
			return VersionFactory.createDesignator(version.getType(), bld.toString());
		}

		// We use an open ended range starting at versionStr here since
		// we don't know where to end. Although it's very likely that
		// an exact match will be found in the maven repo, it's also very
		// likely that a graph will introduce conflicts.
		//
		return VersionFactory.createGTEqualDesignator(version);
	}

	static VersionMatch createVersionMatch(String versionStr, String typeInfo) throws CoreException
	{
		if(versionStr == null || versionStr.length() == 0)
			//
			// No version at all. Treat as if it was an unversioned SNAPSHOT
			//
			return VersionMatch.DEFAULT;

		Matcher m = MavenComponentType.s_timestampDesignatorPattern.matcher(versionStr);
		if(m.matches())
		{
			IVersion branch = createVersion(m.group(1));
			IVersion ts = createTimestamp(m.group(2), m.group(3));
			return new VersionMatch(branch, VersionSelectorFactory.timestamp(null, ts.toLong(), typeInfo));
		}

		m = MavenComponentType.s_snapshotDesignatorPattern.matcher(versionStr);
		if(m.matches())
		{
			versionStr = m.group(1);
			return new VersionMatch(createVersion(versionStr), VersionSelectorFactory.latest(versionStr, typeInfo));
		}

		IVersion version = createVersion(versionStr);
		if(version.isDefault())
			//
			// Unversioned SNAPSHOT
			//
			return new VersionMatch(version, VersionSelectorFactory.latest(null, typeInfo));

		if(version.getType().equals(VersionFactory.TimestampType))
			//
			// Unversioned timestamp
			//
			return new VersionMatch(version, VersionSelectorFactory.timestamp(null, version.toLong(), typeInfo));

		return new VersionMatch(version, VersionSelectorFactory.tag(null, versionStr, typeInfo));
	}

	private static void addDependency(ComponentQuery query, Provider provider, CSpecBuilder cspec,
			GroupBuilder archives, ExpandingProperties properties, Node dep) throws CoreException
	{
		String id = null;
		String groupId = null;
		String artifactId = null;
		String versionStr = null;
		String type = null;
		// String scope = null;
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
			// else if("scope".equals(localName))
			// scope = nodeValue;
		}

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

		DependencyBuilder depBld = cspec.createDependencyBuilder();
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
			MavenPlugin.getLogger().warning(
				"Dependency to " + componentName + " was defined more then once in " + cspec.getName());
		}
	}

	private static void processParentNode(MavenReader reader, CSpecBuilder cspec, IPath pomPath, GroupBuilder archives,
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
		IVersionSelector vs = (versionStr == null)
				? reader.getVersionSelector()
				: VersionSelectorFactory.tag(versionStr);
		IPath parentPath;
		MavenReaderType mrt = (MavenReaderType)reader.getReaderType();
		parentPath = mrt.getPomPath(entry, vs);

		MavenPlugin.getLogger().debug(
				"Getting POM information for parent: " + groupId + " - " + artifactId + " at path " + parentPath);
		Document parentDoc = reader.getPOMDocument(entry, vs, parentPath, new NullProgressMonitor());
		if(parentDoc == null)
			return;

		addDependencies(reader, parentDoc, parentPath, cspec, archives, properties);
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
}
