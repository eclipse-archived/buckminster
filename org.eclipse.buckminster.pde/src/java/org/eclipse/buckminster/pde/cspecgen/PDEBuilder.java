/*****************************************************************************
 * Copyright (c) 2006-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.pde.cspecgen;

import org.eclipse.buckminster.core.cspec.AbstractResolutionBuilder;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.metadata.model.BOMNode;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.metadata.model.ResolvedNode;
import org.eclipse.buckminster.core.reader.ICatalogReader;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.reader.IFileReader;
import org.eclipse.buckminster.core.reader.LocalReader;
import org.eclipse.buckminster.core.reader.ZipArchiveReader;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.pde.IPDEConstants;
import org.eclipse.buckminster.pde.Messages;
import org.eclipse.buckminster.pde.internal.EclipsePlatformReader;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.pde.core.IModel;

/**
 * This abstract builder contains all functionality that is common to the PDE
 * Cspec builders. Subclasses must implement
 * {@link #parseFile(IComponentReader reader)}.
 * 
 * @author Thomas Hallgren
 */
public abstract class PDEBuilder extends AbstractResolutionBuilder implements IPDEConstants {
	/**
	 * Name of the default generated target.
	 */
	public static final String DEFAULT_TARGET = "default"; //$NON-NLS-1$

	/**
	 * Name of the optional target.
	 */
	public static final String OPTIONAL_TARGET = "optional"; //$NON-NLS-1$

	public static ResolvedNode createNode(ProviderMatch providerMatch, CSpecBuilder cspecBuilder) throws CoreException {

		return new ResolvedNode(providerMatch.getNodeQuery(), new Resolution(providerMatch.createResolution(cspecBuilder, false)));
	}

	private boolean usingInstalledReader;

	private IModel model;

	@Override
	public synchronized BOMNode build(IComponentReader[] readerHandle, boolean forResolutionAidOnly, IProgressMonitor monitor) throws CoreException {
		IComponentReader reader = readerHandle[0];

		if (!(reader instanceof ICatalogReader))
			reader = new ZipArchiveReader((IFileReader) reader);

		ICatalogReader catRdr = (ICatalogReader) reader;
		monitor.beginTask(null, forResolutionAidOnly ? 1200 : 1600);
		monitor.subTask(Messages.generating_cspec_from_PDE_artifacts);
		try {
			usingInstalledReader = reader instanceof EclipsePlatformReader;
			CSpecBuilder cspecBuilder = new CSpecBuilder();
			parseFile(cspecBuilder, forResolutionAidOnly, catRdr, MonitorUtils.subMonitor(monitor, 1000));
			if (!(reader instanceof LocalReader))
				cspecBuilder.addSourceDependency();
			applyExtensions(cspecBuilder, forResolutionAidOnly, reader, MonitorUtils.subMonitor(monitor, 200));
			return createNode(reader, cspecBuilder);
		} finally {
			monitor.done();
		}
	}

	public IModel getModel() {
		return model;
	}

	protected boolean isUsingInstalledReader() {
		return usingInstalledReader;
	}

	protected abstract void parseFile(CSpecBuilder cspecBuilder, boolean forResolutionAidOnly, ICatalogReader reader, IProgressMonitor monitor)
			throws CoreException;

	protected void setModel(IModel model) {
		this.model = model;
	}
}
