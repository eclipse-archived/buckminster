/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.ui.actions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.buckminster.ui.ExternalFileEditorInput;
import org.eclipse.buckminster.ui.UiPlugin;
import org.eclipse.buckminster.ui.UiUtils;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;

public class OpenQueryAction implements IWorkbenchWindowActionDelegate
{
	private static class URLValidator implements IInputValidator
	{
		public String isValid(String newText)
		{
			if(newText != null)
			{
				newText = newText.trim();
				if(newText.length() > 0)
				{
					try
					{
						new URL(newText);
						return null;
					}
					catch(MalformedURLException e)
					{
					}
				}
			}
			return "Please enter a valid URL";
		}
	}

	private static final String LAST_CQUERY_URL = "lastCQueryURL";

	private IWorkbenchWindow m_workbenchWindow;

	public void dispose()
	{
	}

	public void init(IWorkbenchWindow window)
	{
		m_workbenchWindow = window;
	}

	public void run(IAction action)
	{
		IPreferenceStore preferences = UiPlugin.getDefault().getPreferenceStore();
		Shell shell = m_workbenchWindow.getShell();
		InputDialog askURL = new InputDialog(shell, null, "URL for query:", preferences.getString(LAST_CQUERY_URL),
				new URLValidator());

		if(askURL.open() != Window.OK)
			return;

		String urlStr = askURL.getValue();
		if(urlStr == null)
			return;

		urlStr = urlStr.trim();
		if(urlStr.length() == 0)
			return;

		preferences.setValue(LAST_CQUERY_URL, urlStr);
		try
		{
			URL url = new URL(urlStr);
			File tempFile = File.createTempFile(BlankQueryAction.TEMP_FILE_PREFIX, ".cquery");
			tempFile.deleteOnExit();
			IWorkbench workbench = PlatformUI.getWorkbench();
			IEditorDescriptor ed = workbench.getEditorRegistry().getDefaultEditor("buckminster.cquery");
			InputStream input = null;
			OutputStream output = null;
			try
			{
				input = URLUtils.openStream(url, null);
				output = new FileOutputStream(tempFile);
				IOUtils.copy(input, output);
			}
			finally
			{
				IOUtils.close(input);
				IOUtils.close(output);
			}
			m_workbenchWindow.getActivePage().openEditor(
					new ExternalFileEditorInput(tempFile, new Path(url.toURI().getPath()).lastSegment(), urlStr), ed.getId());
		}
		catch(Exception e)
		{
			UiUtils.openError(shell, "Unable to open editor", e);
		}
	}

	public void selectionChanged(IAction action, ISelection selection)
	{
	}
}
