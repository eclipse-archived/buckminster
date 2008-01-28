/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp;

import java.io.File;
import java.util.List;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.wizard.IWizardPage;
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

/**
 * @author Karel Brezina
 *
 */
public class DonePage extends InstallWizardPage
{
	private static final int VERTICAL_SPACING = 10;
	
	private static final int HORIZONTAL_INDENT = 50;
	
	//private static final int BUTTON_WIDTH = 100;
	
	//private static final String[] BOM_FILTER_NAMES = {"Bill Of Materials (*.bom)"};

	//private static final String[] BOM_FILTER_EXTS = { "*.bom"};

	//private static final String[] MSPEC_FILTER_NAMES = {"Materialization Specification (*.mspec)"};

	//private static final String[] MSPEC_FILTER_EXTS = { "*.mspec"};

	private static final String ICON_LEARN = "library_wiz.png";
	
	//private static final String ICON_LOCAL_FOLDER = "workset_wiz.png";
	
	private static final String ICON_PUBLISH = "xhtml_wiz.png";
	
	protected DonePage()
	{
		super(MaterializationConstants.STEP_DONE, "Materialization Completed", "Close the materialization dialog.", null);
		setPreviousPage(this);
	}

	public void createControl(Composite parent)
	{
		Composite pageComposite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		layout.verticalSpacing = VERTICAL_SPACING;
		pageComposite.setLayout(layout);

		Label label = new Label(pageComposite, SWT.NONE);
		label.setText("Learn More:");
		GridData layoutData = new GridData();
		layoutData.horizontalSpan = 2;
		label.setLayoutData(layoutData);
			
		List<LearnMoreItem> learnMores = getInstallWizard().getLearnMores();
		
		label = new Label(pageComposite, SWT.NONE);
		label.setImage(getInstallWizard().getImage(ICON_LEARN));
		GridData learnMoreIconlayoutData = new GridData();
		learnMoreIconlayoutData.horizontalIndent = HORIZONTAL_INDENT;
		
		learnMoreIconlayoutData.verticalSpan = learnMores.size();		
		if(learnMoreIconlayoutData.verticalSpan == 0)
		{
			learnMoreIconlayoutData.verticalSpan = 1;
			new Label(pageComposite, SWT.NONE);
		}
		
		learnMoreIconlayoutData.verticalAlignment = GridData.CENTER;
		label.setLayoutData(learnMoreIconlayoutData);

		for(LearnMoreItem item : learnMores)
		{
			createLink(pageComposite, item.getString(), item.getUrl());
		}
		
		new Label(pageComposite, SWT.NONE);
		new Label(pageComposite, SWT.NONE);
		
		label = new Label(pageComposite, SWT.NONE);
		label.setText("Distro Actions:");
		layoutData = new GridData();
		layoutData.horizontalSpan = 2;
		label.setLayoutData(layoutData);
/*
		label = new Label(pageComposite, SWT.NONE);
		label.setImage(getInstallWizard().getImage(ICON_LOCAL_FOLDER));
		layoutData = new GridData();
		layoutData.horizontalIndent = HORIZONTAL_INDENT;
		layoutData.verticalSpan = 2;
		layoutData.verticalAlignment = GridData.CENTER;
		label.setLayoutData(layoutData);
		
		Link saveBOMLink = new Link(pageComposite, SWT.NONE);
		saveBOMLink.setText("<a>Save BOM</a>");
		layoutData = new GridData();
		layoutData.horizontalIndent = HORIZONTAL_INDENT;
		layoutData.widthHint = BUTTON_WIDTH;
		saveBOMLink.setLayoutData(layoutData);
		saveBOMLink.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				SafeSaveDialog dlg = new SafeSaveDialog(getInstallWizard().getShell());
				dlg.setFileName(getMaterializationSpecBuilder().getName());
				dlg.setFilterNames(BOM_FILTER_NAMES);
				dlg.setFilterExtensions(BOM_FILTER_EXTS);
				String fn = dlg.open();
				if(fn != null)
				{
					try
					{
						getInstallWizard().saveBOM(getInstallWizard().getBOM(), new File(fn));
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
		
		Link saveMSPECLink = new Link(pageComposite, SWT.NONE);
		saveMSPECLink.setText("<a>Save MSPEC</a>");
		layoutData = new GridData();
		layoutData.horizontalIndent = HORIZONTAL_INDENT;
		layoutData.widthHint = BUTTON_WIDTH;
		saveMSPECLink.setLayoutData(layoutData);
		saveMSPECLink.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				SafeSaveDialog dlg = new SafeSaveDialog(getInstallWizard().getShell());
				dlg.setFileName(getMaterializationSpecBuilder().getName());
				dlg.setFilterNames(MSPEC_FILTER_NAMES);
				dlg.setFilterExtensions(MSPEC_FILTER_EXTS);
				String fn = dlg.open();
				if(fn != null)
				{
					try
					{
						try
						{
							FileOutputStream os = new FileOutputStream(fn);
							Utils.serialize(getMaterializationSpecBuilder().createMaterializationSpec(), os);
							os.close();
						}
						catch(FileNotFoundException e1)
						{
							throw new JNLPException("File cannot be opened or created", MaterializationConstants.ERROR_CODE_FILE_IO_EXCEPTION, e1);
						}
						catch(SAXException e1)
						{
							throw new JNLPException("Unable to read materialization specification", MaterializationConstants.ERROR_CODE_ARTIFACT_SAX_EXCEPTION, e1);
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
*/
		label = new Label(pageComposite, SWT.NONE);
		label.setImage(getInstallWizard().getImage(ICON_PUBLISH));
		layoutData = new GridData();
		layoutData.horizontalIndent = HORIZONTAL_INDENT;
		layoutData.verticalAlignment = GridData.CENTER;
		label.setLayoutData(layoutData);

		Link publishMSPECLink = new Link(pageComposite, SWT.NONE);
		publishMSPECLink.setText("<a>Publish changed settings as a distro</a>");
		layoutData = new GridData();
		layoutData.horizontalIndent = HORIZONTAL_INDENT;
		//layoutData.widthHint = BUTTON_WIDTH;
		publishMSPECLink.setLayoutData(layoutData);
		publishMSPECLink.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				MaterializationUtils.startPublishingWizard(getInstallWizard(), getShell());
			}
		});
		
		setControl(pageComposite);
	}

	private void createLink(Composite parent, String string, final String url)
	{
		Link learnMoreLink = new Link(parent, SWT.NONE);
		learnMoreLink.setText("<a>" + string + "</a>");
		GridData layoutData = new GridData();
		layoutData.horizontalIndent = HORIZONTAL_INDENT;
		learnMoreLink.setLayoutData(layoutData);
		learnMoreLink.addSelectionListener(new SelectionAdapter()
		{

			@Override
			public void widgetSelected(SelectionEvent e)
			{
				String linkURL = url;
				
				if(linkURL != null)
				{
					Program.launch(linkURL);
				}
			}
		});

	}

	// Last page after materialization - only cancel is enabled
	@Override
	public boolean isPageComplete()
	{
		if(getContainer().getCurrentPage() == this)
		{
			return false;
		}
		
		return true;
	}
	
	// Last page after materialization - only cancel is enabled
    @Override
	public IWizardPage getPreviousPage()
    {
    	return null;
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
