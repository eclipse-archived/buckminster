/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.core.commands;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;

import org.eclipse.buckminster.cmdline.UsageException;
import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.materializer.IMaterializer;
import org.eclipse.buckminster.core.materializer.MaterializationContext;
import org.eclipse.buckminster.core.materializer.MaterializerJob;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.metadata.model.ExportedBillOfMaterials;
import org.eclipse.buckminster.core.mspec.builder.MaterializationSpecBuilder;
import org.eclipse.buckminster.core.mspec.model.MaterializationSpec;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;

/**
 * @author Thomas Hallgren
 */
public class Import extends WorkspaceInitCommand
{
	private URL m_url;

	@Override
	protected int internalRun(boolean continueOnError, IProgressMonitor monitor) throws Exception
	{
		Logger logger = Buckminster.getLogger();
		try
		{
			logger.info("Using workspace at " + FileUtils.getFile(Platform.getInstanceLocation().getURL()) + "...");

			InputStream bomIn = null;
			try
			{
				MaterializationSpec mspec = null;
				monitor.beginTask(null, 100);
				URL url = FileLocator.resolve(m_url);
				if(url.getPath().endsWith(".mspec"))
				{
					mspec = MaterializationSpec.fromURL(m_url, MonitorUtils.subMonitor(monitor, 5));
					url = FileLocator.resolve(mspec.getURL());
				}

				bomIn = new BufferedInputStream(URLUtils.openStream(url, MonitorUtils.subMonitor(monitor, 5)));
				BillOfMaterials bom = CorePlugin.getDefault().getParserFactory().getBillOfMaterialsParser(true).parse(url.toString(), bomIn);
				IOUtils.close(bomIn);
				bomIn = null;

				bom = BillOfMaterials.importGraph((ExportedBillOfMaterials)bom);

				if(mspec == null)
				{
					// Create a default mspec
					//
					MaterializationSpecBuilder mspecBuilder = new MaterializationSpecBuilder();
					mspecBuilder.setName(bom.getViewName());
					mspecBuilder.setMaterializer(IMaterializer.WORKSPACE);
					mspec = mspecBuilder.createMaterializationSpec();
				}
				MaterializationContext matCtx = new MaterializationContext(bom, mspec);
				matCtx.setContinueOnError(continueOnError);
				MaterializerJob.run(matCtx);
				logger.info("Import complete.");
			}
			finally
			{
				IOUtils.close(bomIn);
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

	@Override
	protected void handleUnparsed(String[] unparsed) throws Exception
	{
		int len = unparsed.length;
		if(len > 1)
			throw new UsageException("Too many arguments");
		else if(len < 1)
			throw new UsageException("Missing BOM URL");
		m_url = URLUtils.normalizeToURL(unparsed[0]);
	}
}
