/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp.wizard.tp;

import static org.eclipse.buckminster.jnlp.MaterializationConstants.LOCALPROP_ENABLE_TP_WIZARD;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.VALUE_TRUE;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.VALUE_FALSE;

import java.io.File;

import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.jnlp.MaterializationUtils;
import org.eclipse.buckminster.jnlp.ui.general.wizard.AdvancedWizard;
import org.eclipse.buckminster.jnlp.wizard.install.InstallWizard;
import org.eclipse.swt.graphics.Image;

/**
 * @author Karel Brezina
 * 
 */
public class TPWizard extends AdvancedWizard
{
	private static final String TP_WINDOW_TITLE = "Setup Eclipse Installation";

	private static final String ECLIPSE_FOLDER_NAME = "eclipse";

	private InstallWizard m_installWizard;

	private TPNewOrCurrentPage m_newOrCurrentPage;

	private TPNewLocationPage m_newLocationPage;

	private TPProjectSelectionPage m_projectSelectionPage;

	private boolean m_newEclipse;

	public TPWizard(InstallWizard installWizard)
	{
		m_installWizard = installWizard;
	}

	public void enableWizardNextTime(boolean enable)
	{
		m_installWizard.getLocalProperties().put(LOCALPROP_ENABLE_TP_WIZARD, enable
				? VALUE_TRUE
				: VALUE_FALSE);
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
	public String getWindowTitle()
	{
		return TP_WINDOW_TITLE;
	}

	@Override
	public boolean performFinish()
	{
		if(isNewEclipse())
		{
			File eclipseFolder = new File(getEclipseFolder());
			
			if(eclipseFolder.exists())
			{
				File backupFolder = MaterializationUtils.getBackupFolder(eclipseFolder);
				eclipseFolder.renameTo(backupFolder);
			}
		}

		return false;
	}

	@Override
	protected void addAdvancedPages()
	{
		addAdvancedPage(new TPIntroPage());

		m_newOrCurrentPage = new TPNewOrCurrentPage();
		addAdvancedPage(m_newOrCurrentPage);

		addAdvancedPage(new TPNewRecommendedPage());

		m_newLocationPage = new TPNewLocationPage();
		addAdvancedPage(m_newLocationPage);

		addAdvancedPage(new TPBackupFolderPage());

		m_projectSelectionPage = new TPProjectSelectionPage();
		addAdvancedPage(m_projectSelectionPage);
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

	IVersion getCurrentEclipseVersion()
	{
		return m_newOrCurrentPage.getCurrentEclipseVersion();
	}

	IVersion getProvidedEclipseVersion()
	{
		return m_installWizard.getEclipseDistroVersion();
	}

	boolean isNewEclipse()
	{
		return m_newEclipse;
	}

	void setNewEclipse(boolean newEclipse)
	{
		m_newEclipse = newEclipse;
	}

	boolean isBuckminsterInstalled()
	{
		return !isNewEclipse() && m_newOrCurrentPage.isBuckminsterInstalled();
	}
	
	boolean isSpacesInstalled()
	{
		return !isNewEclipse() && m_newOrCurrentPage.isSpacesInstalled();
	}
	
	boolean isRSSOwlInstalled()
	{
		return !isNewEclipse() && m_newOrCurrentPage.isRSSOwlInstalled();
	}
	
	String getNewEclipseDestinationFolder()
	{
		return m_newLocationPage.getDestinationFolder();
	}

	String getEclipseFolder()
	{
		if(isNewEclipse())
			return getNewEclipseDestinationFolder() == null
					? null
					: (getNewEclipseDestinationFolder() + File.separatorChar + ECLIPSE_FOLDER_NAME);

		return m_newOrCurrentPage.getEclipseFolder();
	}
}
