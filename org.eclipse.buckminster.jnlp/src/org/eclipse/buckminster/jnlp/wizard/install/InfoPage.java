/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp.wizard.install;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.jnlp.HelpLinkErrorDialog;
import org.eclipse.buckminster.jnlp.JNLPException;
import org.eclipse.buckminster.jnlp.MaterializationConstants;
import org.eclipse.buckminster.jnlp.MaterializationUtils;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.xml.sax.SAXException;

/**
 * @author Karel Brezina
 * 
 */
public class InfoPage extends InstallWizardPage
{
	private static final int VERTICAL_SPACING = 10;

	private static final int HORIZONTAL_INDENT = 20;

	private static final String[] OPML_FILTER_NAMES = {"OPML (*.opml)"};

	private static final String[] OPML_FILTER_EXTS = { "*.opml"};

	private static final String ICON_LEARN = "library_wiz.png";

	private static final String ICON_LOCAL_FOLDER = "workset_wiz.png";

	private Label m_heading;
	
	private Link m_openHTML;
	
	private String m_infoPageURL;
	
	private Link m_saveOPML;

	protected InfoPage()
	{
		super(MaterializationConstants.STEP_INFO, "Additional Information", "Get additional information about the materialized distro.",
				null);
		setPreviousPage(this);
	}

	public void createControl(Composite parent)
	{
		Composite pageComposite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		layout.verticalSpacing = VERTICAL_SPACING;
		pageComposite.setLayout(layout);
		GridData layoutData = new GridData();
		pageComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		m_heading = new Label(pageComposite, SWT.WRAP);
		layoutData = new GridData();
		layoutData.horizontalSpan = 2;
		m_heading.setLayoutData(layoutData);
		
		new Label(pageComposite, SWT.NONE);
		new Label(pageComposite, SWT.NONE);
		
		Label label = new Label(pageComposite, SWT.NONE);
		label.setImage(MaterializationUtils.getImage(ICON_LEARN));
		layoutData = new GridData();
		layoutData.horizontalIndent = HORIZONTAL_INDENT;
		layoutData.verticalAlignment = GridData.CENTER;
		label.setLayoutData(layoutData);

		m_openHTML = new Link(pageComposite, SWT.NONE);
		m_openHTML.setText("<a>Open additional material as a HTML file</a>");
		layoutData = new GridData();
		layoutData.horizontalIndent = HORIZONTAL_INDENT;
		m_openHTML.setLayoutData(layoutData);
		m_openHTML.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				if(m_infoPageURL != null)
				{
					Program.launch(m_infoPageURL);
				}
			}
		});
		
		new Label(pageComposite, SWT.NONE);
		new Label(pageComposite, SWT.NONE);

		label = new Label(pageComposite, SWT.NONE);
		label.setImage(MaterializationUtils.getImage(ICON_LOCAL_FOLDER));
		layoutData = new GridData();
		layoutData.horizontalIndent = HORIZONTAL_INDENT;
		layoutData.verticalAlignment = GridData.CENTER;
		label.setLayoutData(layoutData);

		m_saveOPML = new Link(pageComposite, SWT.NONE);
		m_saveOPML.setText("<a>Save additional material as an OPML file</a>");
		layoutData = new GridData();
		layoutData.horizontalIndent = HORIZONTAL_INDENT;
		m_saveOPML.setLayoutData(layoutData);
		m_saveOPML.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				SafeSaveDialog dlg = new SafeSaveDialog(getInstallWizard().getShell());
				dlg.setFileName(getMaterializationSpecBuilder().getName());
				dlg.setFilterNames(OPML_FILTER_NAMES);
				dlg.setFilterExtensions(OPML_FILTER_EXTS);
				String fn = dlg.open();
				if(fn != null)
				{
					try
					{
						try
						{
							FileOutputStream os = new FileOutputStream(fn);
							Utils.serialize(getInstallWizard().getBOM().getResolution().getOPML(), os);
							os.close();
						}
						catch(FileNotFoundException e1)
						{
							throw new JNLPException("File cannot be opened or created", MaterializationConstants.ERROR_CODE_FILE_IO_EXCEPTION, e1);
						}
						catch(SAXException e1)
						{
							throw new JNLPException("Unable to read OPML specification", MaterializationConstants.ERROR_CODE_ARTIFACT_EXCEPTION, e1);
						}
						catch(IOException e1)
						{
							throw new JNLPException("Cannot write to file", MaterializationConstants.ERROR_CODE_FILE_IO_EXCEPTION, e1);
						}
					}
					catch(JNLPException e1)
					{
						IStatus status = BuckminsterException.wrap(e1.getCause()).getStatus();
						CorePlugin.logWarningsAndErrors(status);
						HelpLinkErrorDialog.openError(null, null, MaterializationConstants.ERROR_WINDOW_TITLE,
								e1.getMessage(), MaterializationConstants.ERROR_HELP_TITLE,
								getInstallWizard().getErrorURL(), e1.getErrorCode(), status);
					}
				}
			}
		});

		setControl(pageComposite);
	}

	@Override
	protected void beforeDisplaySetup()
	{
		// Text of the label is set here to be able to WRAP it - no idea how to do it nicer 
		m_heading.setText(
				"Links to additional material was available in the materialized distro. This material can be read by the recommended tools in Eclipse," +
				" but is also available as a HTML file and as an OPML file that some browsers and RSS readers can process directly.");
		GridData layoutData = (GridData)m_heading.getLayoutData();
		layoutData.widthHint = m_heading.getShell().getSize().x - 25;
		m_heading.getParent().layout();
		
		m_infoPageURL = getInstallWizard().getComponentInfoPageURL();
		m_openHTML.setEnabled(m_infoPageURL != null);
	}

	// Finish is disabled
	@Override
	public boolean isPageComplete()
	{
		return !getInstallWizard().isMaterializationFinished();
	}

	@Override
	public String getOverrideCancelButtonText()
	{
		return "Done";
	}

	@Override
	public int getOverrideDefaultButtonId()
	{
		return IDialogConstants.CANCEL_ID;
	}
}

/**
 * Wrapper for FileDialog (save version). Overwriting shows a message.
 */
class SafeSaveDialog
{
	private FileDialog m_dialog;

	public SafeSaveDialog(Shell shell)
	{
		m_dialog = new FileDialog(shell, SWT.SAVE);
	}

	public String open()
	{
		String fileName = null;

		boolean done = false;

		while(!done)
		{
			fileName = m_dialog.open();
			if(fileName == null)
			{
				done = true;
			}
			else
			{
				File file = new File(fileName);
				if(file.exists())
				{
					MessageBox mb = new MessageBox(m_dialog.getParent(), SWT.ICON_WARNING | SWT.YES | SWT.NO);
					mb.setText("Warning");
					mb.setMessage("File " + fileName + " already exists. Do you want to overwrite it?");

					done = (mb.open() == SWT.YES);
				}
				else
				{
					done = true;
				}
			}
		}
		return fileName;
	}

	public String getFileName()
	{
		return m_dialog.getFileName();
	}

	public String[] getFileNames()
	{
		return m_dialog.getFileNames();
	}

	public String[] getFilterExtensions()
	{
		return m_dialog.getFilterExtensions();
	}

	public String[] getFilterNames()
	{
		return m_dialog.getFilterNames();
	}

	public String getFilterPath()
	{
		return m_dialog.getFilterPath();
	}

	public void setFileName(String string)
	{
		m_dialog.setFileName(string);
	}

	public void setFilterExtensions(String[] extensions)
	{
		m_dialog.setFilterExtensions(extensions);
	}

	public void setFilterNames(String[] names)
	{
		m_dialog.setFilterNames(names);
	}

	public void setFilterPath(String string)
	{
		m_dialog.setFilterPath(string);
	}

	public Shell getParent()
	{
		return m_dialog.getParent();
	}

	public int getStyle()
	{
		return m_dialog.getStyle();
	}

	public String getText()
	{
		return m_dialog.getText();
	}

	public void setText(String string)
	{
		m_dialog.setText(string);
	}
}
