/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp;

import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Karel Brezina
 *
 */
public class DownloadProgressMonitorDialog extends ProgressMonitorDialog
{
	private final Image m_windowImage;

	private final String m_windowTitle;

	public DownloadProgressMonitorDialog(Shell parent, Image windowImage, String windowTitle)
	{
		super(parent);

		// to show my m_windowImage I need to add SWT.CLOSE (contain SWT.DIALOG_TRIM) shell style to the shell
		setShellStyle(SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL | getDefaultOrientation());

		m_windowImage = windowImage;
		m_windowTitle = windowTitle;
	}

	@Override
	protected void configureShell(final Shell shell)
	{
		super.configureShell(shell);
		shell.setImage(m_windowImage);
		shell.setText(m_windowTitle);

		// cancel operation when closing window
		shell.addShellListener(new ShellAdapter()
		{
			@Override
			public void shellClosed(ShellEvent e)
			{
				cancelPressed();
			}
		});
	}
}
