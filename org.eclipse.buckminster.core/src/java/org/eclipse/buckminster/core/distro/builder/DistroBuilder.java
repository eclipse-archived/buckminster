package org.eclipse.buckminster.core.distro.builder;

import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.distro.IDistro;
import org.eclipse.buckminster.core.mspec.builder.MaterializationSpecBuilder;
import org.eclipse.buckminster.core.mspec.model.MaterializationSpec;
import org.eclipse.buckminster.core.query.builder.ComponentQueryBuilder;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.opml.IOPML;
import org.eclipse.buckminster.opml.builder.OPMLBuilder;

public class DistroBuilder implements IDistro
{
	private CSpecBuilder m_cspec;

	private ComponentQueryBuilder m_cquery;

	private MaterializationSpecBuilder m_mspec;

	private OPMLBuilder m_opml;

	public void clear()
	{
		m_cspec.clear();
		m_cquery.clear();
		m_mspec.clear();
		m_opml.clear();
	}

	public CSpecBuilder getCspecBuilder()
	{
		return m_cspec;
	}

	public ComponentQueryBuilder getCqueryBuilder()
	{
		return m_cquery;
	}

	public MaterializationSpecBuilder getMspecBuilder()
	{
		return m_mspec;
	}

	public OPMLBuilder getOpmlBuilder()
	{
		return m_opml;
	}
	
	public void initFrom(IDistro distro)
	{
		m_cspec.initFrom(distro.getCspec());
		m_cquery.initFrom(distro.getCquery());
		m_mspec.initFrom(distro.getMspec());
		m_opml.initFrom(distro.getOpml());
	}

	public ComponentQuery getCquery()
	{
		// TODO: Awaits IComponentQuery interface
		return null;
	}

	public CSpec getCspec()
	{
		// TODO: Awaits IComponentSpec interface
		return null;
	}

	public MaterializationSpec getMspec()
	{
		// TODO: Awaits IMaterializationSpec interface
		return null;
	}

	public IOPML getOpml()
	{
		return getOpmlBuilder();
	}
}
