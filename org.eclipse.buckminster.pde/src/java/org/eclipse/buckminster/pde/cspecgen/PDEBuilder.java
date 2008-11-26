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

import java.io.FileNotFoundException;
import java.io.IOException;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.cspec.AbstractResolutionBuilder;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.metadata.OPMLConsumer;
import org.eclipse.buckminster.core.metadata.model.BOMNode;
import org.eclipse.buckminster.core.reader.ICatalogReader;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.reader.IFileReader;
import org.eclipse.buckminster.core.reader.ZipArchiveReader;
import org.eclipse.buckminster.opml.builder.OPMLBuilder;
import org.eclipse.buckminster.opml.model.OPML;
import org.eclipse.buckminster.pde.IPDEConstants;
import org.eclipse.buckminster.pde.Messages;
import org.eclipse.buckminster.pde.internal.EclipsePlatformReader;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.pde.core.IModel;

/**
 * This abstract builder contains all functionality that is common to the PDE Cspec builders. Subclasses must implement
 * {@link #parseFile(IComponentReader reader)}.
 * 
 * @author thhal
 */
public abstract class PDEBuilder extends AbstractResolutionBuilder implements IPDEConstants
{
	/**
	 * Name of the default generated target.
	 */
	public static final String DEFAULT_TARGET = "default"; //$NON-NLS-1$

	/**
	 * Name of the optional target.
	 */
	public static final String OPTIONAL_TARGET = "optional"; //$NON-NLS-1$

	private boolean m_usingInstalledReader;

	private IModel m_model;

	public synchronized BOMNode build(IComponentReader[] readerHandle, boolean forResolutionAidOnly,
			IProgressMonitor monitor) throws CoreException
	{
		IComponentReader reader = readerHandle[0];
		if(!(reader instanceof ICatalogReader))
			reader = new ZipArchiveReader((IFileReader)reader);

		ICatalogReader catRdr = (ICatalogReader)reader;
		monitor.beginTask(null, forResolutionAidOnly
				? 1200
				: 1600);
		monitor.subTask(Messages.getString("PDEBuilder.generating_cspec_from_PDE_artifacts")); //$NON-NLS-1$
		try
		{
			m_usingInstalledReader = reader instanceof EclipsePlatformReader;
			CSpecBuilder cspecBuilder = new CSpecBuilder();
			parseFile(cspecBuilder, forResolutionAidOnly, catRdr, MonitorUtils.subMonitor(monitor, 1000));
			applyExtensions(cspecBuilder, forResolutionAidOnly, reader, MonitorUtils.subMonitor(monitor, 200));

			OPMLBuilder opmlBld = null;
			if(!forResolutionAidOnly)
			{
				String fileName = getMetadataFile(catRdr, IComponentType.PREF_OPML_FILE, CorePlugin.OPML_FILE,
						MonitorUtils.subMonitor(monitor, 200));
				try
				{
					OPML opml = catRdr.readFile(fileName, new OPMLConsumer(), MonitorUtils.subMonitor(monitor, 200));
					opmlBld = new OPMLBuilder();
					opmlBld.initFrom(opml);
				}
				catch(FileNotFoundException e)
				{
					// This is OK, the OPML is optional
				}
				catch(IOException e)
				{
					throw BuckminsterException.wrap(e);
				}
			}
			return createNode(reader, cspecBuilder, opmlBld);
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

	protected abstract void parseFile(CSpecBuilder cspecBuilder, boolean forResolutionAidOnly, ICatalogReader reader,
			IProgressMonitor monitor) throws CoreException;

	protected void setModel(IModel model)
	{
		m_model = model;
	}
}
