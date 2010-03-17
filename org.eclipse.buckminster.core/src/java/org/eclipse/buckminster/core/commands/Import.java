/*****************************************************************************
 * Copyright (c) 2006-2009, Cloudsmith Inc.
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
import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.helpers.AccessibleByteArrayOutputStream;
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
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.ecf.core.security.IConnectContext;

/**
 * @author Thomas Hallgren
 */
public class Import extends WorkspaceInitCommand {
	static private final OptionDescriptor BOM_FILE = new OptionDescriptor('B', "bomfile", OptionValueType.REQUIRED); //$NON-NLS-1$

	static private final OptionDescriptor NO_IMPORT = new OptionDescriptor('N', "noimport", OptionValueType.NONE); //$NON-NLS-1$

	private File bomFile;

	private boolean resolveOnly;

	private URL url;

	private IConnectContext connectContext;

	public void setBomFile(File bomFile) {
		this.bomFile = bomFile;
	}

	public void setConnectContext(IConnectContext cctx) {
		connectContext = cctx;
	}

	public void setResolveOnly(boolean flag) {
		resolveOnly = flag;
	}

	public void setURL(URL url) {
		this.url = url;
	}

	@Override
	protected void getOptionDescriptors(List<OptionDescriptor> appendHere) throws Exception {
		super.getOptionDescriptors(appendHere);
		appendHere.add(NO_IMPORT);
		appendHere.add(BOM_FILE);
	}

	@Override
	protected void handleOption(Option option) throws Exception {
		if (option.is(NO_IMPORT)) {
			setResolveOnly(true);
		} else if (option.is(BOM_FILE)) {
			setBomFile(new File(option.getValue()));
		} else
			super.handleOption(option);
	}

	@Override
	protected void handleUnparsed(String[] unparsed) throws Exception {
		int len = unparsed.length;
		if (len > 1)
			throw new UsageException(Messages.Too_many_arguments);
		else if (len < 1)
			throw new UsageException(Messages.Missing_BOM_URL);
		setURL(URLUtils.normalizeToURL(unparsed[0]));
	}

	@Override
	protected int internalRun(boolean continueOnError, IProgressMonitor monitor) throws Exception {
		Logger logger = Buckminster.getLogger();
		MonitorUtils.begin(monitor, 100);
		OutputStream bomOut = null;
		try {
			IParserFactory pf = CorePlugin.getDefault().getParserFactory();
			URL resolvedURL = FileLocator.resolve(url);

			AccessibleByteArrayOutputStream byteBld = new AccessibleByteArrayOutputStream();
			DownloadManager.readInto(resolvedURL, connectContext, byteBld, MonitorUtils.subMonitor(monitor, 20));

			if (bomFile != null)
				//
				// We attempt to open this early. Don't want to fail because
				// this file cannot
				// be created and loose a completed resolve.
				//
				bomOut = new BufferedOutputStream(new FileOutputStream(bomFile));

			// Assume that the URL is pointing to an MSPEC.
			//
			MaterializationSpec mspec;
			try {
				mspec = pf.getMaterializationSpecParser(true).parse(resolvedURL.toString(), byteBld.getInputStream());
			} catch (CoreException e) {
				mspec = null;
			}
			MonitorUtils.worked(monitor, 5);

			if (mspec != null) {
				// We have an MSPEC. Now let's parse whatever it points to.
				//
				resolvedURL = mspec.getResolvedURL();
				byteBld.reset();
				DownloadManager.readInto(resolvedURL, connectContext, byteBld, MonitorUtils.subMonitor(monitor, 20));
			} else {
				MonitorUtils.worked(monitor, 20);
			}

			// Let's see if we can parse a CQUERY
			//
			ComponentQuery cquery;
			try {
				cquery = ComponentQuery.fromStream(resolvedURL, connectContext, byteBld.getInputStream(), true);
			} catch (CoreException e) {
				// Assume this was not a CQUERY, restart input
				//
				cquery = null;
			}
			MonitorUtils.worked(monitor, 5);

			BillOfMaterials bom;
			if (cquery != null) {
				ResolutionContext ctx = (mspec == null) ? new ResolutionContext(cquery) : new ResolutionContext(mspec, cquery);
				IResolver resolver = new MainResolver(ctx);
				resolver.getContext().setContinueOnError(true);
				try {
					bom = resolver.resolve(MonitorUtils.subMonitor(monitor, 50));
				} finally {
					if (ctx.emitWarningAndErrorTags() && !continueOnError)
						return 1;
				}
			} else {
				// If CQUERY parsing failed, our last attempt is to parse the
				// BOM.
				//
				bom = pf.getBillOfMaterialsParser(true).parse(resolvedURL.toString(), byteBld.getInputStream());
				MonitorUtils.worked(monitor, 50);
			}

			if (bomOut != null) {
				Utils.serialize(bom, bomOut);

				// Close now so it can be accessed during materialization
				//
				IOUtils.close(bomOut);
				bomOut = null;
			}

			if (resolveOnly)
				return 0;

			if (mspec == null) {
				// Create a default MSPEC
				//
				MaterializationSpecBuilder mspecBld = new MaterializationSpecBuilder();
				mspecBld.setURL(resolvedURL.toString());
				mspecBld.setName(bom.getViewName());
				mspecBld.setMaterializerID(getMaterializer());
				bom.addMaterializationNodes(mspecBld);
				mspec = mspecBld.createMaterializationSpec();
			}

			MaterializationContext matCtx = new MaterializationContext(bom, mspec);
			matCtx.setContinueOnError(continueOnError);
			MaterializationJob.run(matCtx);
			if (matCtx.getStatus().getSeverity() == IStatus.ERROR)
				return 1;
		} finally {
			IOUtils.close(bomOut); // If not closed earlier
			MonitorUtils.done(monitor);
		}
		logger.info(Messages.Import_complete);
		return 0;
	}
}
