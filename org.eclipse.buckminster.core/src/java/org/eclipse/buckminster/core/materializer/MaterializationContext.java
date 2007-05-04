/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.materializer;

import java.util.Map;

import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentCategory;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.cspec.model.ComponentName;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.metadata.MissingComponentException;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.mspec.model.MaterializationNode;
import org.eclipse.buckminster.core.mspec.model.MaterializationSpec;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

/**
 * @author Thomas Hallgren
 */
public class MaterializationContext extends RMContext
{
	private final BillOfMaterials m_bom;
	private final MaterializationSpec m_materializationSpec;

	public MaterializationContext(BillOfMaterials bom, MaterializationSpec mspec)
	{
		super(mspec.getProperties());
		m_bom = bom;
		m_materializationSpec = mspec;
	}

	public BillOfMaterials getBillOfMaterials()
	{
		return m_bom;
	}

	@Override
	public ComponentQuery getComponentQuery() throws CoreException
	{
		return m_bom.getQuery();
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
			// materialization entry
		}

		// Consult the reader type.
		//
		IReaderType rd = resolution.getProvider().getReaderType();
		IPath location = rd.getMaterializationLocation(resolution, this, optional);
		if(location != null)
			return location;

		// Consult the component category to get a relative location
		//
		return getDefaultRootInstallLocation(resolution).append(getDefaultRelativeInstallLocation(resolution));
	}

	public IPath getDefaultRootInstallLocation(Resolution resolution) throws CoreException
	{		
		return getMaterializationSpec().getMaterializer(resolution.getComponentIdentifier()).getDefaultInstallRoot(this);
	}

	/**
	 * Obtains the default relative install locatoin for a given resolution. The method
	 * consults the component category if present and then appends the name of the
	 * component. A trailing separater will be appended if the expected artifact is
	 * a directory.
	 *
	 * @param resolution The resolution for which we want a relative install location
	 * @return A relative path.
	 * @throws CoreException
	 */
	public IPath getDefaultRelativeInstallLocation(Resolution resolution) throws CoreException
	{
		IPath relativeLocation = null;
		ComponentName cName = resolution.getRequest();
		ComponentCategory cc = ComponentCategory.getCategory(cName.getCategory());
		if(cc != null)
			relativeLocation = cc.getRelativeLocation();

		IPath leaf = Path.fromPortableString(cName.getName());
		if(relativeLocation == null)
			relativeLocation = leaf;
		else
			relativeLocation = relativeLocation.append(leaf);

		if(!resolution.getProvider().getReaderType().isFileReader())
			relativeLocation = relativeLocation.addTrailingSeparator();
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
}
