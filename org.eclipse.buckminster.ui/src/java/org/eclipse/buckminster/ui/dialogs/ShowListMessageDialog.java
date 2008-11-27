/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.ui.dialogs;

import org.eclipse.buckminster.ui.Messages;
import org.eclipse.buckminster.ui.UiUtils;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;

/**
 * @author ken1
 * 
 */
public class ShowListMessageDialog extends MessageDialog
{
	private final String[] m_list;

	private final int m_maxHeightHint;

	private final int m_maxWidthHint;

	private final Font m_font;

	public ShowListMessageDialog(Shell shell, String title, String msg, String[] list, Font font,
			int maxHeightHint, int maxWidthHint)
	{
		super(shell, title, null, msg, MessageDialog.NONE, new String[] { Messages.ok }, 0);
		int shellStyle = this.getShellStyle();
		shellStyle |= SWT.RESIZE;
		this.setShellStyle(shellStyle);
		m_list = list;
		m_maxHeightHint = maxHeightHint;
		m_maxWidthHint = maxWidthHint;
		m_font = font;
	}

	@Override
	protected Control createCustomArea(Composite parent)
	{
		Group grp = new Group(parent, SWT.NONE);
		grp.setLayout(new GridLayout(1, false));
		grp.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		grp.setText(Messages.command);

		UiUtils.createListViewer(grp, m_list, m_font, m_maxHeightHint, m_maxWidthHint);

		return grp;
	}

}
