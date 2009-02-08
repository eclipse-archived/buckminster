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
import org.eclipse.buckminster.core.cspec.builder.ComponentRequestBuilder;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.metadata.OPMLConsumer;
import org.eclipse.buckminster.core.metadata.model.BOMNode;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.metadata.model.ResolvedNode;
import org.eclipse.buckminster.core.reader.ICatalogReader;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.reader.IFileReader;
import org.eclipse.buckminster.core.reader.ZipArchiveReader;
import org.eclipse.buckminster.core.version.IVersionType;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.opml.builder.OPMLBuilder;
import org.eclipse.buckminster.opml.model.OPML;
import org.eclipse.buckminster.osgi.filter.FilterFactory;
import org.eclipse.buckminster.pde.IPDEConstants;
import org.eclipse.buckminster.pde.Messages;
import org.eclipse.buckminster.pde.internal.EclipsePlatformReader;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.equinox.internal.provisional.p2.core.Version;
import org.eclipse.equinox.internal.provisional.p2.core.VersionRange;
import org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.internal.provisional.p2.metadata.IRequiredCapability;
import org.eclipse.pde.core.IModel;
import org.osgi.framework.InvalidSyntaxException;

/**
 * This abstract builder contains all functionality that is common to the PDE Cspec builders. Subclasses must implement
 * {@link #parseFile(IComponentReader reader)}.
 * 
 * @author thhal
 */
@SuppressWarnings("restriction")
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

	public static void createCSpecFromIU(CSpecBuilder cspecBuilder, IInstallableUnit iu) throws CoreException
	{
		String name = iu.getId();
		boolean isFeature = name.endsWith(IPDEConstants.FEATURE_GROUP);
		if(isFeature)
		{
			name = name.substring(0, name.length() - IPDEConstants.FEATURE_GROUP.length());
			cspecBuilder.setComponentTypeID(IComponentType.ECLIPSE_FEATURE);
		}
		else
			cspecBuilder.setComponentTypeID(IComponentType.OSGI_BUNDLE);

		cspecBuilder.setName(name);

		Version v = iu.getVersion();
		if(v != null)
			cspecBuilder.setVersion(v.toString(), IVersionType.OSGI);

		String filter = iu.getFilter();
		if(filter != null)
			try
			{
				cspecBuilder.setFilter(FilterFactory.newInstance(filter));
			}
			catch(InvalidSyntaxException e)
			{
				throw BuckminsterException.wrap(e);
			}

		for(IRequiredCapability cap : iu.getRequiredCapabilities())
		{
			// We only bother with direct dependencies to other IU's here
			// since package imports etc. are not yet supported
			//
			String namespace = cap.getNamespace();
			name = cap.getName();
			String ctype;
			if(IInstallableUnit.NAMESPACE_IU_ID.equals(namespace))
			{
				if(name.endsWith(IPDEConstants.FEATURE_GROUP))
				{
					name = name.substring(0, name.length() - IPDEConstants.FEATURE_GROUP.length());
					ctype = IComponentType.ECLIPSE_FEATURE;
				}
				else if(isFeature)
					ctype = IComponentType.OSGI_BUNDLE;
				else
					continue;
			}
			else if(IComponentType.OSGI_BUNDLE.equals(namespace))
				ctype = namespace;
			else
				// Package or something else that we don't care about here
				continue;

			ComponentRequestBuilder crb = new ComponentRequestBuilder();
			crb.setName(name);
			crb.setComponentTypeID(ctype);

			VersionRange vr = cap.getRange();
			if(vr != null)
				crb.setVersionDesignator(vr.toString(), IVersionType.OSGI);

			filter = cap.getFilter();
			if(filter != null)
				try
				{
					crb.setFilter(FilterFactory.newInstance(filter));
				}
				catch(InvalidSyntaxException e)
				{
					throw BuckminsterException.wrap(e);
				}
			CSpecGenerator.addDependency(cspecBuilder, crb);
		}
	}

	public static ResolvedNode createNode(ProviderMatch providerMatch, CSpecBuilder cspecBuilder,
			OPMLBuilder opmlBuilder) throws CoreException
	{

		return new ResolvedNode(providerMatch.getNodeQuery(), new Resolution(providerMatch.createResolution(
				cspecBuilder, opmlBuilder, false)));
	}

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
		monitor.subTask(Messages.generating_cspec_from_PDE_artifacts);
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
