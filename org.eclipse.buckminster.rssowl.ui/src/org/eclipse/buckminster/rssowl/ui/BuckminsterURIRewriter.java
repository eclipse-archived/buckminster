/*******************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein
 * are the sole and exclusive property of Cloudsmith Inc. and may
 * not be disclosed, used, modified, copied or distributed without
 * prior written consent or license from Cloudsmith Inc.
 ******************************************************************/

package org.eclipse.buckminster.rssowl.ui;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import org.eclipse.buckminster.download.DownloadManager;

import org.eclipse.buckminster.jnlp.ide.IDEApplication;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.ui.ExternalFileEditorInput;
import org.eclipse.buckminster.ui.UiPlugin;
import org.eclipse.buckminster.ui.UiUtils;
import org.eclipse.buckminster.ui.actions.BlankQueryAction;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;
import org.rssowl.ui.spi.IRewriteResult;
import org.rssowl.ui.spi.IRewriter;
import org.rssowl.ui.spi.Result;

/**
 * Rewrites URLs for buckminster: scheme, and buckminster artifacts on display in the RSS OWL browser.
 * Runs materializer, or opens cquery editor.
 * 
 * @author Henrik Lindberg
 * 
 */
public class BuckminsterURIRewriter implements IRewriter
{
	private static final IRewriteResult s_continue = new Result(false);

	public IRewriteResult rewrite(final String location)
	{
		if(location == null || location.length() < 1)
			return s_continue;

		URI uri = null;
		try
		{
			uri = new URI(location);
		}
		catch(URISyntaxException e)
		{
			return s_continue; // let someone else worry about the malformed URI
		}
		cloudsmithRules:
		{
			if(uri.getHost() == null || !uri.getHost().contains("cloudsmith.com"))
				break cloudsmithRules;
			String path = uri.getRawPath();
			if(path == null || path.length() < 1)
				break cloudsmithRules;

			rule:
			// add eclipse=1 URL parameter
			{
				// This rule adds an "eclipse=1" parameter to an URL that points to cloudsmith materialization page
				// to allow it to render the materialization using the buckminster: scheme
				//
				if(!path.contains("dynamic/view") || !path.contains("component"))
					break rule;

				StringBuilder bld = new StringBuilder(location + 10);
				bld.append(location);
				// if there was a query string already, the added parameter must have & separator
				bld.append(uri.getRawQuery() == null
						? "?"
						: "&");
				bld.append("eclipse=1");
				return new Result(bld.toString());
			}
		}
		rule:
		{
			if(uri.getScheme() == null || !uri.getScheme().equals("buckminster"))
				break rule;

			// parse the buckminster scheme specific part into Action and parameters
			//
			String schemeSpecificPart = uri.getRawSchemeSpecificPart();
			try
			{
				URI schemeURI = new URI(schemeSpecificPart);
				String actionName = schemeURI.getScheme();
				String actionData = schemeURI.getRawSchemeSpecificPart();

				// buckminster:materialize:URL
				if("materialize".equals(actionName))
				{
					// what follows the materialize scheme should be a jnlp URI
					//
					URI jnlpURI = new URI(actionData);
					return new Result(new MaterializeAction(jnlpURI), true);
				}
			}
			catch(URISyntaxException e)
			{
				e.printStackTrace();
				return s_continue;
			}
		}
		rule:
		{
			if(uri.getPath() == null || !uri.getPath().endsWith(".cquery"))
				break rule;
			return new Result(new OpenCQueryAction(uri), true);
		}

		return s_continue;
	}

	private static class MaterializeAction extends Action
	{
		private final URI m_jnlpURI;

		/**
		 * Runs materialization inside the IDE (or the external materializer). The input should be a URI to a
		 * materializer .jnlp. The Execution is asynchronous on the UI thread as we are deep in processing of an event
		 * from the internal browser.
		 * 
		 * @param m_jnlpURI
		 */
		public MaterializeAction(URI jnlpURI)
		{
			m_jnlpURI = jnlpURI;
		}

		@Override
		public void run()
		{
			// Cloudsmith rearranges not only the last part of the jnlp to get the properties
			// but also the URL itself - we could parse the .jnlp, but the transformation on the
			// URL itself is known.
			// All non cloudsmith URL's just gets the last "jnlp" modified to a "prop"

			String urlString = null;
			String host = m_jnlpURI.getHost();
			if(host != null && host.contains("cloudsmith.com"))
			{
				// a cloudsmith .jnlp
				String scheme = m_jnlpURI.getScheme();
				int port = m_jnlpURI.getPort();
				String userInfo = m_jnlpURI.getRawUserInfo();
				String path = m_jnlpURI.getPath();
				String query = m_jnlpURI.getRawQuery();
				String fragment = m_jnlpURI.getRawFragment();

				// transform the path
				// from: /dynamic/jnlp/materialize/xxxx-nnnn.jnlp
				// to: /dynamic/prop/jnlp/xxxx-nnnn.prop
				//
				String[] segments = path.split("/");
				segments[2] = "prop";
				segments[3] = "jnlp";
				segments[4] = segments[4].replace(".jnlp", ".prop");

				// put new URI together
				// - concatenate the path
				// - construct new URI
				//
				StringBuilder bld = new StringBuilder();
				// append them - initial segment is an empty "" so skip it
				for(int i = 1; i < segments.length; i++)
				{
					bld.append("/");
					bld.append(segments[i]);
				}
				path = bld.toString();

				URI propURI = null;
				try
				{
					propURI = new URI(scheme, userInfo, host, port, path, query, fragment);
				}
				catch(URISyntaxException e)
				{
					e.printStackTrace();
					MessageDialog.openError(null, "Internal Problem", "Could not create resulting URI from: "
							+ m_jnlpURI.toString());
					return;
				}
				urlString = propURI.toString();
			}
			else
			{
				// non cloudsmith .jnlp
				urlString = m_jnlpURI.toString().replace(".jnlp", ".prop");
			}
			// Ask if user wants to run this in the IDE or in the browser.
			IPreferenceStore prefsStore = UiPlugin.getDefault().getPreferenceStore();
			String prefsKey = "buckminster.materializer.jnlp.materializeInExternalBrowser";
			MessageDialogWithToggle m = MessageDialogWithToggle.openOkCancelConfirm(null,
					"Start Materialization Wizard", "Do you want to run the materialization wizard now?",
					"Run in external browser", prefsStore.getBoolean(prefsKey), null, prefsKey);
			prefsStore.setValue(prefsKey, m.getToggleState());
			if(prefsStore.needsSaving())
				UiPlugin.getDefault().savePluginPreferences();

			if(m.getReturnCode() == MessageDialogWithToggle.CANCEL)
				return;
			if(m.getToggleState())
			{
				// in external browser
				IWebBrowser browser;
				try
				{
					browser = org.eclipse.buckminster.generic.ui.utils.UiUtils.getWorkbench().getBrowserSupport()
							.createBrowser(IWorkbenchBrowserSupport.AS_EXTERNAL, null, "Materialization", "");
					browser.openURL(new URL(m_jnlpURI.toString()));
				}
				catch(PartInitException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch(MalformedURLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			else
			{
				final IDEApplication ideapp = new IDEApplication();
				try
				{
					ideapp.start(urlString);
				}
				catch(Exception e)
				{
					ideapp.setState(IDEApplication.State.FAILED);
				}
			}
		}

	}

	/**
	 * Opens the CQUery editor for a cquery URI/URL
	 * @author Henrik Lindberg
	 *
	 */
	public static class OpenCQueryAction extends Action
	{
		final private URI m_cqueryURI;

		public OpenCQueryAction(URI cqueryURI)
		{
			m_cqueryURI = cqueryURI;
		}

		@Override
		public void run()
		{
			String urlStr = m_cqueryURI.toString();
			IWorkbench workbench = PlatformUI.getWorkbench();
			try
			{
				URL url = new URL(urlStr);
				File tempFile = File.createTempFile(BlankQueryAction.TEMP_FILE_PREFIX, ".cquery");
				tempFile.deleteOnExit();
				IEditorDescriptor ed = workbench.getEditorRegistry().getDefaultEditor("buckminster.cquery");
				OutputStream output = null;
				try
				{
					output = new FileOutputStream(tempFile);
					DownloadManager.readInto(url, output, null);
				}
				finally
				{
					IOUtils.close(output);
				}
				workbench.getActiveWorkbenchWindow().getActivePage().openEditor(
						new ExternalFileEditorInput(tempFile, new Path(m_cqueryURI.getPath()).lastSegment(), urlStr),
						ed.getId());
			}
			catch(Exception e)
			{
				UiUtils.openError(workbench.getActiveWorkbenchWindow().getShell(), "Unable to open editor", e);
			}
		}

	}

}
