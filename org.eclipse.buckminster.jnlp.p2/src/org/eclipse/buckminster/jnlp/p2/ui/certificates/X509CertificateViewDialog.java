/*******************************************************************************
 * Copyright (c) 2008,  Jay Rosenthal and others
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jay Rosenthal - initial API and implementation
 *     Cloudsmith Inc.
 *******************************************************************************/

package org.eclipse.buckminster.jnlp.p2.ui.certificates;

import java.security.cert.X509Certificate;
import java.text.DateFormat;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

public class X509CertificateViewDialog extends TitleAreaDialog
{
	private final static String titleImageName = "/titleAreaCert.gif"; //$NON-NLS-1$

	// We use the "bannerFont" for our bold font
	private static Font boldFont = JFaceResources.getBannerFont();

	protected static void configureLayout(Control c, int horizontalSpan, int verticalSpan, int horizontalIndent,
			int vertIndent)
	{
		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING | GridData.VERTICAL_ALIGN_CENTER);

		gd.horizontalSpan = horizontalSpan;
		gd.verticalSpan = verticalSpan;
		gd.horizontalIndent = horizontalIndent;
		gd.verticalIndent = vertIndent;

		c.setLayoutData(gd);

	}

	private Image m_windowImage;

	private Image m_titleImage;

	private X509Certificate m_theCert;

	private static final DateFormat _df = DateFormat.getDateInstance(DateFormat.LONG);

	private X500PrincipalHelper nameHelper = new X500PrincipalHelper();

	public X509CertificateViewDialog(Shell parentShell, Image windowImage, Image titleImage, X509Certificate cert)
	{
		super(parentShell);
		m_windowImage = windowImage;
		m_titleImage = titleImage;
		m_theCert = cert;
	}

	@Override
	protected void configureShell(Shell shell)
	{
		super.configureShell(shell);
		if(m_windowImage != null)
		{
			shell.setImage(m_windowImage);
		}
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent)
	{
		// The default has only a "Close" button, but it returns the CANCEL Id
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CLOSE_LABEL, true);

	}

	@Override
	protected Control createContents(Composite parent)
	{
		return super.createContents(parent);
	}

	@Override
	protected Control createDialogArea(Composite parent)
	{
		nameHelper.setPrincipal(m_theCert.getSubjectX500Principal());

		setTitle((nameHelper.getCN() != null
				? nameHelper.getCN()
				: nameHelper.getOU()));

		if(m_titleImage != null)
			setTitleImage(m_titleImage);

		Composite composite = (Composite)super.createDialogArea(parent);

		TabFolder tabFolder = new TabFolder(composite, SWT.BORDER);
		GridData bdata = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.VERTICAL_ALIGN_FILL
				| GridData.GRAB_VERTICAL);
		tabFolder.setLayoutData(bdata);

		createBasicTab(tabFolder);

		createAdvancedTab(tabFolder);

		return composite;
	}

	@Override
	protected void setShellStyle(int newShellStyle)
	{

		super.setShellStyle(newShellStyle | SWT.RESIZE | SWT.DIALOG_TRIM);
	}

	private void createAdvancedTab(final TabFolder tabFolder)
	{
		TabItem advancedTab = new TabItem(tabFolder, SWT.NULL);
		advancedTab.setText("Details");
		Composite advTabComposite = new Composite(tabFolder, SWT.NONE);
		advTabComposite.setLayout(new FillLayout(SWT.VERTICAL));

		CertificateViewer certViewer = new CertificateViewer(advTabComposite);
		certViewer.setCertificate(m_theCert);
		advancedTab.setControl(advTabComposite);
	}

	private void createBasicTab(TabFolder tabFolder)
	{
		String displayName = null;
		int labelIndent = 10;
		int dataIdent = 10;

		TabItem basicTab = new TabItem(tabFolder, SWT.NULL);
		basicTab.setText("General");
		Composite basicTabComposite = new Composite(tabFolder, SWT.NONE);

		GridLayout tabLayout = new GridLayout();
		tabLayout.numColumns = 2;
		basicTabComposite.setLayout(tabLayout);

		Label issueToLabel = new Label(basicTabComposite, SWT.NONE);
		issueToLabel.setText("Issued to");
		issueToLabel.setFont(boldFont);
		configureLayout(issueToLabel, 2, 0, 0, 0);

		// Display the RDNs of the Subject
		nameHelper.setPrincipal(m_theCert.getSubjectX500Principal());

		Label CNLabel = new Label(basicTabComposite, SWT.NONE);
		CNLabel.setText("Common Name");
		configureLayout(CNLabel, 0, 0, labelIndent, 0);

		Label subjectCN = new Label(basicTabComposite, SWT.NONE);
		displayName = nameHelper.getCN();
		subjectCN.setText((displayName != null
				? displayName
				: "<Not Defined>"));
		configureLayout(subjectCN, 0, 0, dataIdent, 0);

		Label OLabel = new Label(basicTabComposite, SWT.NONE);
		OLabel.setText("Organization");
		configureLayout(OLabel, 0, 0, labelIndent, 0);

		Label subjectO = new Label(basicTabComposite, SWT.NONE);
		displayName = nameHelper.getO();
		subjectO.setText((displayName != null
				? displayName
				: "<Not Defined>"));
		configureLayout(subjectO, 0, 0, dataIdent, 0);

		Label OULabel = new Label(basicTabComposite, SWT.NONE);
		OULabel.setText("Organizational Unit");
		configureLayout(OULabel, 0, 0, labelIndent, 0);

		Label subjectOU = new Label(basicTabComposite, SWT.NONE);
		displayName = nameHelper.getOU();
		subjectOU.setText((displayName != null
				? displayName
				: "<Not Defined>"));
		configureLayout(subjectOU, 0, 0, dataIdent, 0);

		Label issueByLabel = new Label(basicTabComposite, SWT.NONE);
		issueByLabel.setText("Issued by");
		configureLayout(issueByLabel, 2, 0, 0, 0);
		issueByLabel.setFont(boldFont);

		// Display the RDNs of the Issuer
		nameHelper.setPrincipal(m_theCert.getIssuerX500Principal());

		Label CNLabel2 = new Label(basicTabComposite, SWT.NONE);
		CNLabel2.setText("Common Name");
		configureLayout(CNLabel2, 0, 0, labelIndent, 0);

		Label issuerCN = new Label(basicTabComposite, SWT.NONE);
		displayName = nameHelper.getCN();
		issuerCN.setText((displayName != null
				? displayName
				: "<Not Defined>"));
		configureLayout(issuerCN, 0, 0, dataIdent, 0);

		Label OLabel2 = new Label(basicTabComposite, SWT.NONE);
		OLabel2.setText("Organization");
		configureLayout(OLabel2, 0, 0, labelIndent, 0);

		Label issuerO = new Label(basicTabComposite, SWT.NONE);
		displayName = nameHelper.getO();
		issuerO.setText((displayName != null
				? displayName
				: "<Not Defined>"));
		configureLayout(issuerO, 0, 0, dataIdent, 0);

		Label OULabel2 = new Label(basicTabComposite, SWT.NONE);
		OULabel2.setText("Organizational Unit");
		configureLayout(OULabel2, 0, 0, labelIndent, 0);

		Label issuerOU = new Label(basicTabComposite, SWT.NONE);
		displayName = nameHelper.getOU();
		issuerOU.setText((displayName != null
				? displayName
				: "<Not Defined>"));
		configureLayout(issuerOU, 0, 0, dataIdent, 0);

		Label datesLabel = new Label(basicTabComposite, SWT.NONE);
		datesLabel.setText("Validity Dates");
		configureLayout(datesLabel, 2, 0, 0, 0);
		datesLabel.setFont(boldFont);

		Label validFrom = new Label(basicTabComposite, SWT.NONE);
		validFrom.setText("Valid From");
		configureLayout(validFrom, 0, 0, labelIndent, 0);

		Label fromDate = new Label(basicTabComposite, SWT.NONE);
		fromDate.setText(_df.format(m_theCert.getNotBefore()));
		configureLayout(fromDate, 0, 0, dataIdent, 0);

		Label validTo = new Label(basicTabComposite, SWT.NONE);
		validTo.setText("Valid Until");
		configureLayout(validTo, 0, 0, labelIndent, 0);

		Label toDate = new Label(basicTabComposite, SWT.NONE);
		toDate.setText(_df.format(m_theCert.getNotAfter()));
		configureLayout(toDate, 0, 0, dataIdent, 0);

		basicTab.setControl(basicTabComposite);
	}

}
