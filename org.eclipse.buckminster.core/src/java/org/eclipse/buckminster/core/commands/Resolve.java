/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.core.commands;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;

import org.eclipse.buckminster.cmdline.Option;
import org.eclipse.buckminster.cmdline.OptionDescriptor;
import org.eclipse.buckminster.cmdline.OptionValueType;
import org.eclipse.buckminster.cmdline.UsageException;
import org.eclipse.buckminster.core.materializer.IMaterializer;
import org.eclipse.buckminster.core.materializer.MaterializationContext;
import org.eclipse.buckminster.core.materializer.MaterializerJob;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.mspec.builder.MaterializationSpecBuilder;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.core.resolver.MainResolver;
import org.eclipse.buckminster.core.resolver.ResolutionContext;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;

/**
 * @author Thomas Hallgren
 */
public class Resolve extends WorkspaceInitCommand
{
	static private final OptionDescriptor NO_IMPORT = new OptionDescriptor('N', "noimport",
		OptionValueType.NONE);

	static private final OptionDescriptor BOM_FILE = new OptionDescriptor('B', "bomfile",
		OptionValueType.REQUIRED);

	private URL m_url;

	private boolean m_resolveOnly;

	private File m_bomFile;

	@Override
	protected int internalRun(boolean continueOnError, IProgressMonitor monitor) throws Exception
	{
		Logger logger = Buckminster.getLogger();
		try
		{
			logger.info("Using workspace at " + Platform.getInstanceLocation().getURL().toString() + "...");

			OutputStream bomOut = null;
			if(m_bomFile != null)
				//
				// We attempt to open this early. Don't want to fail because this file cannot
				// be created and loose a completed resolve.
				//
				bomOut = new BufferedOutputStream(new FileOutputStream(m_bomFile));
				
			try
			{
				monitor.beginTask(null, m_resolveOnly ? 40 : 100);

				ComponentQuery query = ComponentQuery.fromURL(m_url, MonitorUtils.subMonitor(monitor, 5));
				ResolutionContext context = new ResolutionContext(query);
				MainResolver resolver = new MainResolver(context);
				resolver.getContext().setContinueOnError(continueOnError);
				BillOfMaterials bom = resolver.resolve(query.getRootRequest(), MonitorUtils.subMonitor(monitor, 35));
				IStatus status = context.getStatus();
				switch(status.getSeverity())
				{
				case IStatus.ERROR:
					throw new CoreException(status);
				case IStatus.WARNING:
					logger.warning(status.getMessage());
					break;
				case IStatus.INFO:
					logger.info(status.getMessage());
				}

				if(bomOut != null)
					Utils.serialize(bom.exportGraph(), bomOut);

				if(!m_resolveOnly)
				{
					// TODO: Allow mspec to be specified on the command line as an alternative to
					// the CQUERY.
					//
					MaterializationSpecBuilder mspecBuilder = new MaterializationSpecBuilder();
					mspecBuilder.setName(bom.getViewName());
					mspecBuilder.setMaterializer(IMaterializer.WORKSPACE);
					MaterializationContext matCtx = new MaterializationContext(bom, mspecBuilder.createMaterializationSpec());
					MaterializerJob.run(matCtx);
				}
				logger.info("Query complete.");
			}
			finally
			{
				IOUtils.close(bomOut);
				monitor.done();
			}
		}
		catch(Throwable t)
		{
			CoreException be = BuckminsterException.wrap(t);
			if(be.getCause() instanceof javax.net.ssl.SSLHandshakeException)
				logger.error("An SSL handshake exception occurred - are all server certificates available in your keystore?");
			throw be;
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void getOptionDescriptors(List appendHere) throws Exception
	{
		super.getOptionDescriptors(appendHere);
		appendHere.add(CONTINUE_ON_ERROR);
		appendHere.add(NO_IMPORT);
		appendHere.add(BOM_FILE);
	}

	@Override
	protected void handleOption(Option option) throws Exception
	{
		if(option.is(NO_IMPORT))
		{
			m_resolveOnly = true;
		}
		else if(option.is(BOM_FILE))
		{
			m_bomFile = new File(option.getValue());
		}
		else
			super.handleOption(option);
	}

	@Override
	protected void handleUnparsed(String[] unparsed) throws Exception
	{
		int len = unparsed.length;
		if(len > 1)
			throw new UsageException("Too many arguments");
		if(len == 1)
			m_url = URLUtils.normalizeToURL(unparsed[0]);
	}
}
