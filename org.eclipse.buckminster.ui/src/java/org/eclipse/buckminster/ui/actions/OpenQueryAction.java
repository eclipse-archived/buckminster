/*****************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.ui.actions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.download.DownloadManager;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.buckminster.ui.ComboInputDialog;
import org.eclipse.buckminster.ui.Messages;
import org.eclipse.buckminster.ui.UiPlugin;
import org.eclipse.buckminster.ui.UiUtils;
import org.eclipse.buckminster.ui.editor.ExternalFileEditorInput;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;

public class OpenQueryAction implements IWorkbenchWindowActionDelegate {
	private static class URLValidator implements IInputValidator {
		@Override
		public String isValid(String newText) {
			if (newText != null) {
				newText = newText.trim();
				if (newText.length() > 0) {
					try {
						new URL(newText);
						return null;
					} catch (MalformedURLException e) {
					}
				}
			}
			return Messages.please_enter_a_valid_url;
		}
	}

	// just for backward compatibility
	private static final String LAST_CQUERY_URL = "lastCQueryURL"; //$NON-NLS-1$

	private static final String LAST_CQUERY_URLS = "lastCQueryURLs"; //$NON-NLS-1$

	private static final int MAX_REMEMBERED_CQUERIES = 5;

	private IWorkbenchWindow workbenchWindow;

	@Override
	public void dispose() {
	}

	@Override
	public void init(IWorkbenchWindow window) {
		workbenchWindow = window;
	}

	@Override
	public void run(IAction action) {
		IPreferenceStore preferences = UiPlugin.getDefault().getPreferenceStore();
		Shell shell = workbenchWindow.getShell();

		String lastURLsString = preferences.getString(LAST_CQUERY_URLS);
		String[] lastURLs = null;

		if (lastURLsString != null && lastURLsString.length() > 0)
			lastURLs = lastURLsString.split(";"); //$NON-NLS-1$
		else {
			String lastURLString = preferences.getString(LAST_CQUERY_URL);

			if (lastURLString != null)
				lastURLs = new String[] { lastURLString };
		}

		ComboInputDialog askURL = new ComboInputDialog(shell, null, Messages.url_for_query_with_colon, lastURLs, lastURLs.length > 0 ? lastURLs[0]
				: null, new URLValidator());

		if (askURL.open() != Window.OK)
			return;

		String urlStr = askURL.getValue();
		if (urlStr == null)
			return;

		urlStr = urlStr.trim();
		if (urlStr.length() == 0)
			return;

		StringBuilder sb = new StringBuilder();

		sb.append(urlStr);

		int cnt = 1;
		for (String str : lastURLs) {
			if (cnt >= MAX_REMEMBERED_CQUERIES)
				break;

			if (!urlStr.equals(str)) {
				sb.append(';');
				sb.append(str);
				cnt++;
			}
		}

		preferences.setValue(LAST_CQUERY_URLS, sb.toString());
		try {
			URL url = URLUtils.normalizeToURL(urlStr);
			File cqueryFile = FileUtils.getFile(url);
			if (cqueryFile == null) {
				File tempFile = File.createTempFile(BlankQueryAction.TEMP_FILE_PREFIX, ".cquery"); //$NON-NLS-1$
				tempFile.deleteOnExit();
				OutputStream output = null;
				try {
					output = new FileOutputStream(tempFile);
					DownloadManager.readInto(url, null, output, null);
				} finally {
					IOUtils.close(output);
				}
				cqueryFile = tempFile;
			}

			IWorkbench workbench = PlatformUI.getWorkbench();
			IEditorDescriptor ed = workbench.getEditorRegistry().getDefaultEditor("buckminster.cquery"); //$NON-NLS-1$
			workbenchWindow.getActivePage().openEditor(
					new ExternalFileEditorInput(cqueryFile, new Path(url.toURI().getPath()).lastSegment(), urlStr), ed.getId());
		} catch (Exception e) {
			UiUtils.openError(shell, Messages.unable_to_open_editor, e);
		}
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
	}
}
