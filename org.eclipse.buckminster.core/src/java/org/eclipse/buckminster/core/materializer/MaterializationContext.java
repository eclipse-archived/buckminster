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
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.cspec.model.ComponentName;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.helpers.MapUnion;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.core.metadata.MissingComponentException;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.mspec.model.MaterializationNode;
import org.eclipse.buckminster.core.mspec.model.MaterializationSpec;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.core.version.IVersion;
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

	public IPath getDefaultInstallLocation(Resolution resolution, boolean[] optional) throws CoreException
	{		
		// Use existing materialization if the component is materialized already
		//
		CSpec cspec = resolution.getCSpec();
		optional[0] = true;
		try
		{
			return cspec.getComponentLocation();
		}
		catch(MissingComponentException e)
		{
			// expected but if we get here there's no
			// previous materialization entry
		}

		// Consult the reader type.
		//
		IReaderType rd = resolution.getProvider().getReaderType();
		IPath location = rd.getMaterializationLocation(resolution, this, optional);
		if(location != null)
			return location;

		// Consult the component type to get a relative location
		//
		return getDefaultRootInstallLocation(resolution).append(getDefaultRelativeInstallLocation(resolution));
	}

	IPath processUnpack(Resolution resolution, IDecompressor[][] decompressorsHandle, IExpander[] expanderHandle)
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

	public IPath getDefaultRootInstallLocation(Resolution resolution) throws CoreException
	{		
		return getMaterializationSpec().getMaterializer(resolution.getComponentIdentifier()).getDefaultInstallRoot(this);
	}

	/**
	 * Obtains the default relative install location for a given resolution. The method consults the component type and
	 * then appends the name of the component. A trailing separator will be appended if the expected artifact is a
	 * directory.
	 * 
	 * @param resolution
	 *            The resolution for which we want a relative install location
	 * @return A relative path.
	 * @throws CoreException
	 */
	public IPath getDefaultRelativeInstallLocation(Resolution resolution) throws CoreException
	{
		IPath relativeLocation = null;
		ComponentName cName = resolution.getRequest();
		IComponentType cType = cName.getComponentType();
		if(cType != null)
			relativeLocation = cType.getRelativeLocation();

		if(resolution.getProvider().getReaderType().isFileReader())
		{
			if(relativeLocation == null)
				relativeLocation = Path.EMPTY;

			IPath remotePath = processUnpack(resolution, null, null);
			relativeLocation = relativeLocation.append(remotePath.lastSegment());
			if(remotePath.hasTrailingSeparator())
				//
				// Will expand into a folder
				//
				relativeLocation = relativeLocation.addTrailingSeparator();
		}
		else
		{
			IPath leaf = Path.fromPortableString(cName.getName());
			if(relativeLocation == null)
				relativeLocation = leaf;
			else
				relativeLocation = relativeLocation.append(leaf);
			relativeLocation = relativeLocation.addTrailingSeparator();
		}
		return relativeLocation;
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
		IPath location = getDefaultInstallLocation(resolution, optional);
		if(!optional[0])
			return location;

		IPath rootLocation = m_materializationSpec.getInstallLocation();
		IPath nodeLocation = null;

		ComponentIdentifier ci = resolution.getComponentIdentifier();
		MaterializationNode node = m_materializationSpec.getMatchingNode(ci);
		if(node != null)
			nodeLocation = node.getInstallLocation();

		if(nodeLocation == null)
		{
			if(rootLocation == null)
				return location;
			nodeLocation = getDefaultRelativeInstallLocation(resolution);
		}

		nodeLocation = Path.fromOSString(ExpandingProperties.expand(getProperties(ci), nodeLocation.toOSString(), 0));
		if(nodeLocation.isAbsolute())
			return nodeLocation;

		if(rootLocation == null)
			rootLocation = getDefaultRootInstallLocation(resolution);

		rootLocation = Path.fromOSString(ExpandingProperties.expand(this, rootLocation.toOSString(), 0));	
		rootLocation = rootLocation.makeAbsolute();
		location = rootLocation.append(nodeLocation);

		if(!resolution.getProvider().getReaderType().isFileReader())
			location = location.addTrailingSeparator();

		return location;
	}

	public MaterializationSpec getMaterializationSpec()
	{
		return m_materializationSpec;
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
				rootLocation = getDefaultRootInstallLocation(resolution);
		}

		rootLocation = Path.fromOSString(ExpandingProperties.expand(this, rootLocation.toOSString(), 0));	
		rootLocation = rootLocation.makeAbsolute();

		if(nodeLocation != null)
			rootLocation = rootLocation.append(nodeLocation);
		return rootLocation;
	}
}
