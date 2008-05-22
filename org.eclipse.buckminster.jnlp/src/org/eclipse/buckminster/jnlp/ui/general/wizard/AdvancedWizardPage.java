/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.jnlp.ui.general.wizard;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.program.Program;

/**
 * @author Karel Brezina
 *
 */
public abstract class AdvancedWizardPage extends WizardPage
{
	private boolean m_pageCommitted = false;
	
	protected AdvancedWizardPage(String pageName)
	{
		super(pageName);
	}

	protected AdvancedWizardPage(String pageName, String title, String message, ImageDescriptor titleImage)
	{
		super(pageName, title, titleImage);
		setMessage(message);
	}

	protected AdvancedWizard getAdvancedWizard()
	{
		return (AdvancedWizard)getWizard();
	}

	void displayException(Throwable e)
	{
		CorePlugin.getLogger().warning(e, e.getMessage());
		setErrorMessage(e.getMessage());
	}
		
	@Override
	public void performHelp()
	{
		String moreInfoURL = getAdvancedWizard().getMoreInfoURL();
		
		if(moreInfoURL == null)
		{
			moreInfoURL = getAdvancedWizard().getHelpURL();
		}
		
		if(moreInfoURL != null)
		{
			Program.launch(moreInfoURL);
		}
	}

	/**
	 * It's used for setting up page before it's shown
	 *
	 */
	protected void beforeDisplaySetup()
	{
	}

	/**
	 * It's used for validation of the whole page after "Next" button is pressed.
	 * (e.g. don't want to login to a remote site each time isPageComplete() is called) 
	 * 
	 * @return true - ok, false - problem
	 */	
	protected boolean performPageCommit()
	{
		return true;
	}
	
	public final boolean commitPage()
	{
		m_pageCommitted = performPageCommit();
		return m_pageCommitted;
	}

	public final boolean isCommitted()
	{
		return m_pageCommitted;	
	}
	
	public final void uncommitPage()
	{
		m_pageCommitted = false;
	}
	
	/**
	 * Page can change "Finish" button text (e.g. to "Publish"). If null is returned no text is changed.
	 */
	public String getOverrideFinishButtonText()
	{
		return null;
	}
	
	/**
	 * Page can change "Cancel" button text (e.g. to "Done"). If null is returned no text is changed.
	 */
	public String getOverrideCancelButtonText()
	{
		return null;
	}
	
	/**
	 * Page can change a default button (e.g. when "Cancel" is changed to "Done", "Done" can be set as a default button).
	 * If -1 is returned default button is not changed.
	 */
	public int getOverrideDefaultButtonId()
	{
		return -1;
	}
}
