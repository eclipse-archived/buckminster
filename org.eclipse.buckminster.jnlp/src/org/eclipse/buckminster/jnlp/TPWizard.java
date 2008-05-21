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
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Preferences;
import org.eclipse.pde.core.plugin.TargetPlatform;
import org.eclipse.pde.internal.core.ICoreConstants;
import org.eclipse.pde.internal.core.PDECore;
import org.eclipse.swt.graphics.Image;

/**
 * @author Karel Brezina
 * 
 */
@SuppressWarnings("restriction")
public class TPWizard extends AdvancedWizard
{
	private static final String TP_WINDOW_TITLE = "Setup Eclipse Installation";

	InstallWizard m_installWizard;

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
		// TODO Auto-generated method stub
		return false;
	}

	public void unsetTP()
	{
		this.setTP(TargetPlatform.getDefaultLocation());
	}

	@Override
	protected void addAdvancedPages()
	{
		addAdvancedPage(new TPIntroPage());
		addAdvancedPage(new TPNewOrCurrentPage());
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

	void setTP(String targetPlatform)
	{
		PDECore pdePlugin = PDECore.getDefault();
		Preferences preferences = pdePlugin.getPluginPreferences();
		IPath newPath = new Path(targetPlatform);
		Platform.getInstallLocation();
		IPath defaultPath = new Path(TargetPlatform.getDefaultLocation());
		String mode = defaultPath.equals(newPath)
				? ICoreConstants.VALUE_USE_THIS
				: ICoreConstants.VALUE_USE_OTHER;
		preferences.setValue(ICoreConstants.TARGET_MODE, mode);
		preferences.setValue(ICoreConstants.PLATFORM_PATH, targetPlatform);
		pdePlugin.savePluginPreferences();
	}
}
