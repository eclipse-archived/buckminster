/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.ui.internal;

import java.security.cert.CertPath;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;

/**
 * @author kolwing
 *
 */
public class CertificateTrustInquiryUIDialog extends MessageDialog
{
	public static boolean promptUser(Shell shell, String title, String message, CertPath certPath)
	{
		String[] buttonLabels = new String[] { IDialogConstants.YES_LABEL, IDialogConstants.NO_LABEL, "Certificate details..." };
		CertificateTrustInquiryUIDialog dlg = new CertificateTrustInquiryUIDialog(shell, title, null, message, WARNING, buttonLabels, 2, certPath);
		return dlg.open() == 0;
	}

	private final CertPath m_certPath;
	
	private CertificateTrustInquiryUIDialog(Shell shell, String title, Image image, String msg, int type, String[] labels, int index, CertPath certPath)
	{
		super(shell, title, image, msg, type, labels, index);
		m_certPath = certPath;
	}

	@Override
	protected void buttonPressed(int buttonId)
	{
		if (buttonId != 2)
			super.buttonPressed(buttonId);
		else
			// TODO: this is not the nicest way to display it...
			MessageDialog.openInformation(this.getShell(), "Certificate details", m_certPath.toString());
	}
}
