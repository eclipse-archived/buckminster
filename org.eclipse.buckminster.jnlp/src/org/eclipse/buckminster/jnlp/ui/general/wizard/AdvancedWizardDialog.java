/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp.ui.general.wizard;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.buckminster.jnlp.Messages;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.operation.ModalContext;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.ProgressMonitorPart;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Shell;

/**
 * A WizardDialog that is not application modal
 * 
 * @author Karel Brezina
 */
public class AdvancedWizardDialog extends WizardDialog
{
	private static final String HELP_LABEL = Messages.more_info;

	private boolean m_runningOperation = false;

	public AdvancedWizardDialog(AdvancedWizard newWizard, int styleFilter)
	{
		super(null, newWizard);
		setShellStyle(getShellStyle() & styleFilter);
	}

	public AdvancedWizardDialog(Shell parentShell, AdvancedWizard newWizard)
	{
		super(parentShell, newWizard);
	}

	public Button getButtonFromButtonArea(int buttonId)
	{
		return getButton(buttonId);
	}

	@Override
	public boolean isHelpAvailable()
	{
		return true;
	}

	@Override
	public int open()
	{
		// need to call showPage to call beforeDisplaySetup() method of the starting page
		showPage(getWizard().getStartingPage());
		return super.open();
	}

	@Override
	public void run(boolean fork, boolean cancelable, IRunnableWithProgress runnable) throws InvocationTargetException,
			InterruptedException
	{

		m_runningOperation = true;

		if(getWizard().needsProgressMonitor())
		{
			getProgressMonitor().setCanceled(false);
			ProgressMonitorPart progressMonitorPart = (ProgressMonitorPart)getProgressMonitor();
			progressMonitorPart.setVisible(true);
		}

		try
		{
			ModalContext.run(runnable, fork, getProgressMonitor(), getShell().getDisplay());
		}
		finally
		{
			m_runningOperation = false;

			if(getWizard().needsProgressMonitor())
			{
				ProgressMonitorPart progressMonitorPart = (ProgressMonitorPart)getProgressMonitor();
				progressMonitorPart.setVisible(false);
			}
		}
	}

	@Override
	public void showPage(IWizardPage page)
	{
		if(page == null)
			return;

		((AdvancedWizardPage)page).beforeDisplaySetup();

		if(page == getCurrentPage())
			return;

		super.showPage(page);
	}

	@Override
	public void updateButtons()
	{
		super.updateButtons();

		String finishButtonText = ((AdvancedWizardPage)getCurrentPage()).getOverrideFinishButtonText();

		if(finishButtonText != null)
		{
			getButton(IDialogConstants.FINISH_ID).setText(finishButtonText);
		}

		String cancelButtonText = ((AdvancedWizardPage)getCurrentPage()).getOverrideCancelButtonText();

		if(cancelButtonText != null)
		{
			getButton(IDialogConstants.CANCEL_ID).setText(cancelButtonText);
		}

		int defaultButtonId = ((AdvancedWizardPage)getCurrentPage()).getOverrideDefaultButtonId();

		if(defaultButtonId != -1)
		{
			getShell().setDefaultButton(getButton(defaultButtonId));
		}
	}

	@Override
	protected void cancelPressed()
	{
		if(m_runningOperation)
		{
			getProgressMonitor().setCanceled(true);
		}
		else
		{
			super.cancelPressed();
		}
	}

	/**
	 * Creates a new help control that provides access to context help.
	 * <p>
	 * The <code>TrayDialog</code> implementation of this method creates the control, registers it for selection events
	 * including selection, Note that the parent's layout is assumed to be a <code>GridLayout</code> and the number of
	 * columns in this layout is incremented. Subclasses may override.
	 * </p>
	 * 
	 * @param parent
	 *            the parent composite
	 * @return the help control
	 */
	@Override
	protected Control createHelpControl(Composite parent)
	{
		return createHelpLink(parent);
	}

	@Override
	protected void finishPressed()
	{
		for(IWizardPage page : getWizard().getPages())
		{
			AdvancedWizardPage instalPage = (AdvancedWizardPage)page;
			if(!instalPage.isPageCommitted())
			{
				if(!instalPage.commitPage())
				{
					return;
				}
			}
		}

		super.finishPressed();
	}

	@Override
	protected void nextPressed()
	{
		AdvancedWizardPage currentPage = (AdvancedWizardPage)getCurrentPage();

		if(!currentPage.commitPage())
		{
			return;
		}

		super.nextPressed();
	}

	private Link createHelpLink(Composite parent)
	{
		Link link = new Link(parent, SWT.WRAP | SWT.NO_FOCUS);
		((GridLayout)parent.getLayout()).numColumns++;
		link.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));
		link.setText("<a>" + HELP_LABEL + "</a>"); //$NON-NLS-1$ //$NON-NLS-2$
		link.setToolTipText(HELP_LABEL);
		link.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				helpPressed();
			}
		});
		return link;
	}
}
