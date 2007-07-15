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
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.mspec.model.MaterializationNode;
import org.eclipse.buckminster.core.mspec.model.MaterializationSpec;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.runtime.BuckminsterException;
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
	public static final String DECOMPRESSORS_POINT = "decompressors";
	public static final String EXPANDERS_POINT = "expanders";

	private final BillOfMaterials m_bom;
	private final MaterializationSpec m_materializationSpec;

	public MaterializationContext(BillOfMaterials bom, MaterializationSpec mspec, RMContext context)
	{
		super(context == null ? mspec.getProperties() : new MapUnion<String, String>(mspec.getProperties(), context));
		m_bom = bom;
		m_materializationSpec = mspec;
		if(context != null)
			getUserCache().putAll(context.getUserCache());
	}

	public BillOfMaterials getBillOfMaterials()
	{
		return m_bom;
	}

	@Override
	public ComponentQuery getComponentQuery()
	{
		try
		{
			return m_bom.getQuery();
		}
		catch(CoreException e)
		{
			// Something is bogus with the meta-data storage.
			//
			CorePlugin.getLogger().error(e.getMessage(), e);
			return null;
		}
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
		boolean[] optional = new boolean[] { true };
		IPath relativeLocation = getRelativeInstallLocation(resolution, optional);
		if(relativeLocation.isAbsolute())
		{
			if(!optional[0])
				return relativeLocation;

			IPath rootLocation = getRootInstallLocation(resolution, !relativeLocation.hasTrailingSeparator(), optional);
			if(optional[0] || rootLocation.isPrefixOf(relativeLocation))
				return relativeLocation;

			throw BuckminsterException.fromMessage(String.format(
				"Required root install %s conflicts with absolute path %s", rootLocation, relativeLocation));
		}
		return getRootInstallLocation(resolution, !relativeLocation.hasTrailingSeparator(), optional).append(relativeLocation);
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
				if(nodeLocation.isAbsolute())
					return nodeLocation;
			}
		}

		IPath rootLocation = m_materializationSpec.getWorkspaceLocation();
		if(rootLocation == null)
		{
			if(nodeLocation == null)
				return getInstallLocation(resolution);

			rootLocation = m_materializationSpec.getInstallLocation();
			if(rootLocation == null)
				rootLocation = getRootInstallLocation(resolution, false, new boolean[1]);
		}

		rootLocation = Path.fromOSString(ExpandingProperties.expand(this, rootLocation.toOSString(), 0));	
		rootLocation = rootLocation.makeAbsolute();

		if(nodeLocation != null)
			rootLocation = rootLocation.append(nodeLocation);
		return rootLocation;
	}

	public IPath processUnpack(Resolution resolution, IDecompressor[][] decompressorsHandle, IExpander[] expanderHandle)
	throws CoreException
	{
		ComponentName cName = resolution.getComponentIdentifier();
		MaterializationSpec mspec = getMaterializationSpec();
		String name = resolution.getRemoteName();
		if(name == null)
		{
			// No filename is available, let's use a name built from <componentname>_<version><suffix>
			//
			StringBuilder nameBld = new StringBuilder(cName.getName());
			IVersion version = resolution.getVersion();
			if(version != null)
			{
				nameBld.append('_');
				version.toString(nameBld);
			}
			String suffix = mspec.getSuffix(cName);
			if(suffix == null)
				suffix = ".dat";
			nameBld.append(suffix);
			name = nameBld.toString();
		}

		if(!mspec.isUnpack(cName))
			return new Path(name);

		IExtensionRegistry extRegistry = Platform.getExtensionRegistry();
		IConfigurationElement[] elems = extRegistry.getConfigurationElementsFor(DECOMPRESSORS_POINT);
		int idx = elems.length;
		String[][] suffixes = new String[idx][];
		while(--idx >= 0)
			suffixes[idx] = TextUtils.split(elems[idx].getAttribute("suffixes"), ",");

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
				//
				// No matching decompressor was found
				//
				break;

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
		}
		if(decompressorList != null)
			decompressorsHandle[0] = decompressorList.toArray(new IDecompressor[decompressorList.size()]);

		if(!mspec.isExpand(cName))
			return new Path(name);

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

		// We now consider the result to be a folder
		//
		return new Path(name).addTrailingSeparator();
	}

	private IPath getRelativeInstallLocation(Resolution resolution, boolean[] optional) throws CoreException
	{
		optional[0] = true;
		IReaderType rd = resolution.getProvider().getReaderType();
		IPath location = rd.getRelativeInstallLocation(resolution, this, optional);
		if(optional[0])
		{
			ComponentIdentifier ci = resolution.getComponentIdentifier();
			MaterializationNode node = m_materializationSpec.getMatchingNode(ci);
			IPath specRelative = null;
			if(node != null)
				specRelative = node.getInstallLocation();

			if(!location.isAbsolute() && specRelative == null)
			{
				ComponentName cName = resolution.getRequest();
				IComponentType cType = cName.getComponentType();
				if(cType != null)
				{
					specRelative = cType.getRelativeLocation();
					if(specRelative != null)
						specRelative = specRelative.append(location);
				}
			}
			if(specRelative != null)
				location = specRelative;
		}
		return location;
	}

	private IPath getRootInstallLocation(Resolution resolution, boolean forFiles, boolean[] optional) throws CoreException
	{		
		// Consult the reader type.
		//
		optional[0] = true;
		IReaderType rd = resolution.getProvider().getReaderType();
		IPath location = rd.getRootInstallLocation(resolution, this, optional);
		if(optional[0])
		{
			IPath specRoot = m_materializationSpec.getInstallLocation();
			if(specRoot != null)
				location = specRoot;
			else
				location = m_materializationSpec.getMaterializer(resolution.getComponentIdentifier()).getDefaultInstallRoot(this, forFiles);
		}
		return location;
	}
}
