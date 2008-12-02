/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp.distroprovider;

import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.mspec.model.MaterializationSpec;

/**
 * @author Karel Brezina
 * 
 */
public class Distro
{
	private BillOfMaterials m_bom;

	private MaterializationSpec m_mspec;

	public Distro(BillOfMaterials bom, MaterializationSpec mspec)
	{
		m_bom = bom;
		m_mspec = mspec;
	}

	public BillOfMaterials getBom()
	{
		return m_bom;
	}

	public MaterializationSpec getMspec()
	{
		return m_mspec;
	}

	public void setBom(BillOfMaterials bom)
	{
		m_bom = bom;
	}

	public void setMspec(MaterializationSpec mspec)
	{
		m_mspec = mspec;
	}
}
