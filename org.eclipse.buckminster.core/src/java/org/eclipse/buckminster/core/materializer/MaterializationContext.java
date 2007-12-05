/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.materializer;

import java.util.ArrayList;
import java.util.Map;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.cspec.model.ComponentName;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.helpers.MapUnion;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.metadata.model.DepNode;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.mspec.model.MaterializationNode;
import org.eclipse.buckminster.core.mspec.model.MaterializationSpec;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;

/**
 * @author Thomas Hallgren
 */
public class MaterializationContext extends RMContext
{
	public static final String DECOMPRESSORS_POINT = CorePlugin.CORE_NAMESPACE + ".decompressors";
	public static final String EXPANDERS_POINT = CorePlugin.CORE_NAMESPACE + ".expanders";

	private final BillOfMaterials m_bom;
	private final MaterializationSpec m_materializationSpec;

	public MaterializationContext(BillOfMaterials bom, MaterializationSpec mspec, RMContext context)
	{
		super(context == null ? mspec.getProperties() : new MapUnion<String, String>(mspec.getProperties(), context));
		m_bom = bom;
		m_materializationSpec = mspec;
		if(context != null)
		{
			getUserCache().putAll(context.getUserCache());
			getTagInfos().putAll(context.getTagInfos());
		}
		else
			addTagInfosFromBom();
	}

	public BillOfMaterials getBillOfMaterials()
	{
		return m_bom;
	}

	@Override
	public ComponentQuery getComponentQuery()
	{
		return m_bom.getQuery();
	}

	/**
	 * Returns the designated full path to the installed artifact for the resolution. This
	 * is a shortcut for<pre>
	 * getInstallLocation(resolution).append(getLeafArtifact(resolution))
	 * </pre>
	 * @param resolution The resolution for which we want the artifact location
	 * @return An absolute path in the local file system.
	 * @throws CoreException
	 */
	public IPath getArtifactLocation(Resolution resolution) throws CoreException
	{
		IPath installLocation = getInstallLocation(resolution);
		IPath leafArtifact = getLeafArtifact(resolution);
		if(leafArtifact == null)
			installLocation = installLocation.addTrailingSeparator();
		else
			installLocation = installLocation.append(leafArtifact);
		return installLocation;
	}

	/**
	 * Returns the install location for the resolution as specified in the {@link MaterializationSpec}
	 * or the default location if it is not specified.
	 * @param resolution The resolution for which we want the install location
	 * @return An absolute path in the local file system.
	 * @throws CoreException
	 */
	public IPath getInstallLocation(Resolution resolution) throws CoreException
	{
		IPath relativeLocation = getRelativeInstallLocation(resolution);
		if(relativeLocation != null)
		{
			IPath tmp = expand(relativeLocation);
			if(tmp.isAbsolute())
				return tmp;
		}

		IPath location = getRootInstallLocation(resolution);
		if(relativeLocation != null)
			location = location.append(relativeLocation);
		return expand(location);
	}

	public IPath getLeafArtifact(Resolution resolution) throws CoreException
	{
		ComponentIdentifier ci = resolution.getComponentIdentifier();
		MaterializationSpec mspec = getMaterializationSpec();
		IPath leaf = mspec.getLeafArtifact(ci);
		boolean isExpand = mspec.isExpand(ci);

		if(leaf != null)
		{
			// MSpec always take precedence
			//
			if(isExpand)
				leaf = leaf.addTrailingSeparator();
			return leaf;
		}

		IReaderType rd = resolution.getProvider().getReaderType();
		if(isExpand)
			//
			// We only name files, not expanded folders
			//
			return null;

		leaf = rd.getLeafArtifact(resolution, this);
		if(leaf == null)
		{
			// No filename is available, let's use a name built from <componentname>_<version>
			//
			StringBuilder nameBld = new StringBuilder(ci.getName());
			IVersion version = ci.getVersion();
			if(version != null)
			{
				nameBld.append('_');
				version.toString(nameBld);
			}
			nameBld.append(".dat");
			leaf = Path.fromPortableString(nameBld.toString());
			if(leaf.segmentCount() > 1)
				leaf = leaf.removeFirstSegments(leaf.segmentCount() - 1);
		}
		return leaf;
	}

	public MaterializationSpec getMaterializationSpec()
	{
		return m_materializationSpec;
	}

	public int getMaxParallelJobs()
	{
		int maxParallelJobs = m_materializationSpec.getMaxParallelJobs();
		if(maxParallelJobs == -1)
			maxParallelJobs = MaterializationJob.getMaxParallelJobs();
		return maxParallelJobs;
	}

	@Override
	public Map<String, String> getProperties(ComponentName cName)
	{
		Map<String,String> p = super.getProperties(cName);
		MaterializationNode node = m_materializationSpec.getMatchingNode(cName);
		if(node != null)
			p.putAll(node.getProperties());
		return p;
	}

	public IPath getWorkspaceLocation(Resolution resolution) throws CoreException
	{
		IPath nodeLocation = null;
		ComponentIdentifier ci = resolution.getComponentIdentifier();
		MaterializationNode node = m_materializationSpec.getMatchingNode(ci);
		if(node != null)
		{
			nodeLocation = node.getWorkspaceLocation();
			if(nodeLocation != null)
			{
				nodeLocation = Path.fromOSString(ExpandingProperties.expand(getProperties(ci), nodeLocation.toOSString(), 0));
				IPath tmp = expand(nodeLocation);
				if(tmp.isAbsolute())
					return tmp;
			}
		}

		IPath rootLocation = m_materializationSpec.getWorkspaceLocation();
		if(rootLocation == null)
		{
			if(nodeLocation != null)
				//
				// At this point the nodeLocation must be relative so this
				// is illegal.
				//
				throw BuckminsterException.fromMessage(
					String.format("WorkspaceLocation %s in node matching %s cannot be relative unless a main workspace location is present",
						nodeLocation, ci));

			// Default to location of current workspace
			//
			return ResourcesPlugin.getWorkspace().getRoot().getLocation();
		}

		return expand((nodeLocation == null)
			? rootLocation
			: rootLocation.append(nodeLocation));
	}

	public void processUnpack(Resolution resolution, IDecompressor[][] decompressorsHandle, IExpander[] expanderHandle)
	throws CoreException
	{
		MaterializationSpec mspec = getMaterializationSpec();
		ComponentName cName = resolution.getComponentIdentifier();
		if(!mspec.isUnpack(cName))
			return;

		String name = mspec.getSuffix(cName);
		if(name == null)
		{
			IReaderType rd = resolution.getProvider().getReaderType();
			IPath leaf = rd.getLeafArtifact(resolution, this);
			if(leaf == null ||leaf.segmentCount() == 0)
				throw BuckminsterException.fromMessage("Unable to determine suffix for unpack of " + cName);
			name = leaf.segment(0);
		}

		IExtensionRegistry extRegistry = Platform.getExtensionRegistry();
		IConfigurationElement[] elems = extRegistry.getConfigurationElementsFor(DECOMPRESSORS_POINT);
		int idx = elems.length;
		String[][] suffixes = new String[idx][];
		while(--idx >= 0)
			suffixes[idx] = TextUtils.split(elems[idx].getAttribute("suffixes"), ",");

		boolean first = true;
		ArrayList<IDecompressor> decompressorList = null;
		while(name.length() > 0)
		{
			// Find the suffix that matches the most characters at the
			// end of the path
			//
			int matchIdx = -1;
			int matchLen = -1;
			idx = elems.length;
			while(--idx >= 0)
			{
				for(String suffix : suffixes[idx])
				{
					if(suffix.length() > matchLen && name.endsWith(suffix))
					{
						matchLen = suffix.length();
						matchIdx = idx;
					}
				}
			}

			if(matchIdx < 0)
			{
				// No matching decompressor was found
				//
				if(first && !mspec.isExpand(cName))
					throw BuckminsterException.fromMessage("Unable find decompressor for " + cName);
				break;
			}

			if(decompressorsHandle != null)
			{
				if(decompressorList == null)
					decompressorList = new ArrayList<IDecompressor>();
	
				IConfigurationElement elem = elems[matchIdx];
				decompressorList.add(IDecompressor.class.cast(elem.createExecutableExtension("class")));
			}

			// Strip of suffix managed by this decompressor
			//
			name = name.substring(0, name.length() - matchLen);
			first = false;
		}
		if(decompressorList != null)
			decompressorsHandle[0] = decompressorList.toArray(new IDecompressor[decompressorList.size()]);

		if(!mspec.isExpand(cName))
			return;

		elems = extRegistry.getConfigurationElementsFor(EXPANDERS_POINT);
		idx = elems.length;
		suffixes = new String[idx][];
		while(--idx >= 0)
			suffixes[idx] = TextUtils.split(elems[idx].getAttribute("suffixes"), ",");

		// Find the suffix that matches the most characters at the
		// end of the path
		//
		int matchIdx = -1;
		int matchLen = -1;
		idx = elems.length;
		while(--idx >= 0)
		{
			for(String suffix : suffixes[idx])
			{
				if(suffix.length() > matchLen && name.endsWith(suffix))
				{
					matchLen = suffix.length();
					matchIdx = idx;
				}
			}
		}
		if(matchIdx >= 0)
		{
			if(expanderHandle != null)
				expanderHandle[0] = IExpander.class.cast(elems[matchIdx].createExecutableExtension("class"));
			name = name.substring(0, name.length() - matchLen);
		}
		else
			throw BuckminsterException.fromMessage("Unable find expander for " + cName);
	}

	private IPath getRelativeInstallLocation(Resolution resolution) throws CoreException
	{
		ComponentIdentifier ci = resolution.getComponentIdentifier();
		MaterializationNode node = m_materializationSpec.getMatchingNode(ci);
		IPath location = null;
		if(node != null)
		{
			location = node.getInstallLocation();
			if(location != null)
				return location;
		}

		IReaderType rd = resolution.getProvider().getReaderType();
		location = rd.getInstallLocation(resolution, this);
		IComponentType cType = resolution.getComponentType();
		if(cType != null)
		{
			IPath ctypeRelative = cType.getRelativeLocation();
			if(ctypeRelative != null)
			{
				if(location == null)
					location = ctypeRelative;
				else
					location = location.append(ctypeRelative);
			}
		}
		return location;
	}

	private IPath getRootInstallLocation(Resolution resolution) throws CoreException
	{
		IPath location = m_materializationSpec.getInstallLocation();
		if(location == null)
			location = m_materializationSpec.getMaterializer(resolution.getComponentIdentifier()).getDefaultInstallRoot(this, resolution);
		return location;
	}

	private IPath expand(IPath path)
	{
		return Path.fromOSString(ExpandingProperties.expand(this, path.toOSString(), 0));
	}

	private void addTagInfosFromBom()
	{
		addTagInfosFromNode(m_bom.getQuery().getTagInfo(), m_bom);
	}

	private void addTagInfosFromNode(String tagInfo, DepNode node)
	{
		Resolution res = node.getResolution();
		if(res == null || IReaderType.ECLIPSE_PLATFORM.equals(res.getProvider().getReaderTypeId()))
			return;

		addTagInfo(node.getRequest(), tagInfo);
		String childTagInfo = res.getCSpec().getTagInfo(tagInfo);
		for(DepNode child : node.getChildren())
			addTagInfosFromNode(childTagInfo, child);
	}
}
