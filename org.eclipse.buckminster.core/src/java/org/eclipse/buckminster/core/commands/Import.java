/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.core.commands;

import java.net.URL;

import org.eclipse.buckminster.cmdline.UsageException;
import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.helpers.AccessibleByteArrayOutputStream;
import org.eclipse.buckminster.core.materializer.IMaterializer;
import org.eclipse.buckminster.core.materializer.MaterializationContext;
import org.eclipse.buckminster.core.materializer.MaterializationJob;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.mspec.builder.MaterializationSpecBuilder;
import org.eclipse.buckminster.core.mspec.model.MaterializationSpec;
import org.eclipse.buckminster.core.parser.IParserFactory;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.core.resolver.IResolver;
import org.eclipse.buckminster.core.resolver.MainResolver;
import org.eclipse.buckminster.core.resolver.ResolutionContext;
import org.eclipse.buckminster.download.DownloadManager;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @author Thomas Hallgren
 */
public class Import extends WorkspaceInitCommand
{
	private URL m_url;

	public void setURL(URL url)
	{
		m_url = url;
	}

	@Override
	protected int internalRun(boolean continueOnError, IProgressMonitor monitor) throws Exception
	{
		Logger logger = Buckminster.getLogger();
		MonitorUtils.begin(monitor, 100);
		try
		{
			IParserFactory pf = CorePlugin.getDefault().getParserFactory();
			URL url = FileLocator.resolve(m_url);

			AccessibleByteArrayOutputStream byteBld = new AccessibleByteArrayOutputStream();
			DownloadManager.readInto(url, byteBld, MonitorUtils.subMonitor(monitor, 20));
	
			// Assume that the URL is pointing to an MSPEC.
			//
			MaterializationSpec mspec;
			try
			{
				mspec = pf.getMaterializationSpecParser(true).parse(url.toString(), byteBld.getInputStream());
			}
			catch(CoreException e)
			{
				mspec = null;
			}
			MonitorUtils.worked(monitor, 5);

			if(mspec != null)
			{
				// We have an MSPEC. Now let's parse whatever it points to.
				//
				url = mspec.getResolvedURL();
				byteBld.reset();
				DownloadManager.readInto(url, byteBld, MonitorUtils.subMonitor(monitor, 20));
			}
			else
			{
				MonitorUtils.worked(monitor, 20);
			}

			// Let's see if we can parse a CQUERY
			//
			ComponentQuery cquery;
			try
			{
				cquery = ComponentQuery.fromStream(url, byteBld.getInputStream(), true);
			}
			catch(CoreException e)
			{
				// Assume this was not a CQUERY, restart input
				//
				cquery = null;
			}
			MonitorUtils.worked(monitor, 5);

			BillOfMaterials bom;
			if(cquery != null)
			{
				IResolver resolver = new MainResolver(new ResolutionContext(cquery));
				resolver.getContext().setContinueOnError(true);
				bom = resolver.resolve(MonitorUtils.subMonitor(monitor, 40));
			}
			else
			{
				// If CQUERY parsing failed, our last attempt is to parse the BOM.
				//
				bom = pf.getBillOfMaterialsParser(true).parse(url.toString(), byteBld.getInputStream());
				MonitorUtils.worked(monitor, 40);
			}
			MonitorUtils.worked(monitor, 10);

			if(mspec == null)
			{
				// Create a default MSPEC
				//
				MaterializationSpecBuilder mspecBld = new MaterializationSpecBuilder();
				mspecBld.setURL(url.toString());
				mspecBld.setName(bom.getViewName());
				mspecBld.setMaterializerID(IMaterializer.WORKSPACE);
				bom.addMaterializationNodes(mspecBld);
				mspec = mspecBld.createMaterializationSpec();
			}

			MaterializationContext matCtx = new MaterializationContext(bom, mspec);
			matCtx.setContinueOnError(continueOnError);
			MaterializationJob.run(matCtx, true);
			if(matCtx.emitWarningAndErrorTags())
				return 1;
		}
		finally
		{
			MonitorUtils.done(monitor);
		}
		logger.info("Import complete.");
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
		setURL(URLUtils.normalizeToURL(unparsed[0]));
	}
}
