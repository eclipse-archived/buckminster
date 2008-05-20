/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp;

import static org.eclipse.buckminster.jnlp.MaterializationConstants.LOCALPROP_ENABLE_TP_WIZARD;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.VALUE_TRUE;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.VALUE_FALSE;
import org.eclipse.buckminster.jnlp.ui.general.wizard.AdvancedWizard;
import org.eclipse.swt.graphics.Image;

/**
 * @author Karel Brezina
 *
 */
public class TPWizard extends AdvancedWizard
{
	private static final String TP_WINDOW_TITLE = "Setup Eclipse Installation";

	InstallWizard m_installWizard;
	
	public TPWizard(InstallWizard installWizard)
	{
		m_installWizard = installWizard;
	}
	
	@Override
	protected void addAdvancedPages()
	{
		addAdvancedPage(new TPIntroPage());
	}

	@Override
	public String getWindowTitle()
	{
		return TP_WINDOW_TITLE;
	}

	@Override
	public String getHelpURL()
	{
		return m_installWizard.getHelpURL();
	}
	
	@Override
	public String getMoreInfoURL()
	{
		return m_installWizard.getMoreInfoURL();
	}

	@Override
	protected Image getWindowImage()
	{
		return m_installWizard.getWindowImage();
	}
	
	@Override
	protected Image getWizardImage()
	{
		return m_installWizard.getWizardImage();
	}
	
	@Override
	public boolean performFinish()
	{
		// TODO Auto-generated method stub
		return false;
	}

	public void enableWizardNextTime(boolean enable)
	{
		m_installWizard.getLocalProperties().put(LOCALPROP_ENABLE_TP_WIZARD, enable ? VALUE_TRUE : VALUE_FALSE);
	}

}
