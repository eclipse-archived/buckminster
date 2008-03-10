/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.pde.cspecgen;

import org.eclipse.buckminster.core.cspec.AbstractResolutionBuilder;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.metadata.model.DepNode;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.metadata.model.ResolvedNode;
import org.eclipse.buckminster.core.reader.ICatalogReader;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.reader.IFileReader;
import org.eclipse.buckminster.core.reader.ZipArchiveReader;
import org.eclipse.buckminster.pde.IPDEConstants;
import org.eclipse.buckminster.pde.internal.EclipsePlatformReader;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.pde.core.IModel;

/**
 * This abstract builder contains all functionality that is common to the PDE Cspec builders.
 * Subclasses must implement {@link #parseFile(IComponentReader reader)}.
 * @author thhal
 */
public abstract class PDEBuilder extends AbstractResolutionBuilder implements IPDEConstants
{
	/**
	 * Name of the default generated target.
	 */
	public static final String DEFAULT_TARGET = "default";

	/**
	 * Name of the optional target.
	 */
	public static final String OPTIONAL_TARGET = "optional";

	private boolean m_usingInstalledReader;

	private IModel m_model;

	public synchronized DepNode build(IComponentReader[] readerHandle, boolean forResolutionAidOnly, IProgressMonitor monitor) throws CoreException
	{
		IComponentReader reader = readerHandle[0];
		if(!(reader instanceof ICatalogReader))
			reader = new ZipArchiveReader((IFileReader)reader);

		monitor.beginTask(null, 2000);
		monitor.subTask("Generating cspec from PDE artifacts");
		try
		{
			m_usingInstalledReader = reader instanceof EclipsePlatformReader;
			CSpecBuilder cspecBuilder = new CSpecBuilder();
			parseFile(cspecBuilder, forResolutionAidOnly, (ICatalogReader)reader, MonitorUtils.subMonitor(monitor, 1000));
			CSpec cspec = applyExtensions(cspecBuilder.createCSpec(), forResolutionAidOnly, reader, MonitorUtils.subMonitor(
				monitor, 1000));
			return new ResolvedNode(reader.getNodeQuery(), new Resolution(cspec, reader));
		}
		finally
		{
			monitor.done();
		}
	}

	public IModel getModel()
	{
		return m_model;
	}

	protected boolean isUsingInstalledReader()
	{
		return m_usingInstalledReader;
	}

	protected abstract void parseFile(CSpecBuilder cspecBuilder, boolean forResolutionAidOnly, ICatalogReader reader, IProgressMonitor monitor) throws CoreException;
	
	protected void setModel(IModel model)
	{
		m_model = model;
	}
}
