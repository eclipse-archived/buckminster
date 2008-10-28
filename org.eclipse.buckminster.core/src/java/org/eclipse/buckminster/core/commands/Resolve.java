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
import org.eclipse.buckminster.core.materializer.MaterializationJob;
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
import org.eclipse.ecf.core.security.IConnectContext;

/**
 * @author Thomas Hallgren
 */
public class Resolve extends WorkspaceInitCommand
{
	static private final OptionDescriptor BOM_FILE = new OptionDescriptor('B', "bomfile",
		OptionValueType.REQUIRED);

	static private final OptionDescriptor NO_IMPORT = new OptionDescriptor('N', "noimport",
		OptionValueType.NONE);

	private File m_bomFile;

	private boolean m_resolveOnly;

	private URL m_url;

	private IConnectContext m_connectContext;

	public void setBomFile(File bomFile)
	{
		m_bomFile = bomFile;
	}

	public void setConnectContext(IConnectContext cctx)
	{
		m_connectContext = cctx;
	}

	public void setResolveOnly(boolean flag)
	{
		m_resolveOnly = flag;
	}

	public void setURL(URL url)
	{
		m_url = url;
	}

	@Override
	protected void getOptionDescriptors(List<OptionDescriptor> appendHere) throws Exception
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
			setResolveOnly(true);
		}
		else if(option.is(BOM_FILE))
		{
			setBomFile(new File(option.getValue()));
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
			setURL(URLUtils.normalizeToURL(unparsed[0]));
	}

	@Override
	protected int internalRun(boolean continueOnError, IProgressMonitor monitor) throws Exception
	{
		Logger logger = Buckminster.getLogger();
		try
		{
			OutputStream bomOut = null;
			if(m_bomFile != null)
				//
				// We attempt to open this early. Don't want to fail because this file cannot
				// be created and loose a completed resolve.
				//
				bomOut = new BufferedOutputStream(new FileOutputStream(m_bomFile));
				
			MonitorUtils.begin(monitor, m_resolveOnly ? 40 : 100);
			try
			{
				ComponentQuery query = ComponentQuery.fromURL(m_url, m_connectContext, true);
				MonitorUtils.worked(monitor, 5);
				ResolutionContext context = new ResolutionContext(query);
				MainResolver resolver = new MainResolver(context);
				context.setContinueOnError(continueOnError);
				BillOfMaterials bom = resolver.resolve(query.getRootRequest(), MonitorUtils.subMonitor(monitor, 35));
				if(context.emitWarningAndErrorTags())
					return 1;

				if(bomOut != null)
					Utils.serialize(bom, bomOut);

				if(!m_resolveOnly)
				{
					// TODO: Allow mspec to be specified on the command line as an alternative to
					// the CQUERY.
					//
					MaterializationSpecBuilder mspecBuilder = new MaterializationSpecBuilder();
					mspecBuilder.setName(bom.getViewName());
					mspecBuilder.setMaterializerID(IMaterializer.WORKSPACE);
					MaterializationContext matCtx = new MaterializationContext(bom, mspecBuilder.createMaterializationSpec(), context);
					MaterializationJob.run(matCtx, true);
					if(matCtx.emitWarningAndErrorTags())
						return 1;
				}
			}
			finally
			{
				IOUtils.close(bomOut);
				MonitorUtils.done(monitor);
			}
		}
		catch(Throwable t)
		{
			CoreException be = BuckminsterException.wrap(t);
			if(be.getCause() instanceof javax.net.ssl.SSLHandshakeException)
				logger.error("An SSL handshake exception occurred - are all server certificates available in your keystore?");
			throw be;
		}
		logger.info("Query complete.");
		return 0;
	}
}
