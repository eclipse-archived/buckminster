/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp.ui.general.wizard;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.graphics.Image;

/**
 * @author Karel Brezina
 *
 */
public abstract class AdvancedWizard extends Wizard
{
	@Override
	public void addPages()
	{
		addAdvancedPages();
		
		setWindowTitle(getWindowTitle());

		if(getWindowImage() != null)
		{
			getShell().setImage(getWindowImage());
		}
				
		if(getWizardImage() != null)
		{
			setDefaultPageImageDescriptor(ImageDescriptor.createFromImage(getWizardImage()));
		}
	}
	
	abstract protected void addAdvancedPages();
	
	/**
	 * Use addAdvancedPage(AdvancedWizardPage) instead
	 */
    @Override
	public void addPage(IWizardPage page)
    {
    	throw new RuntimeException("Unsupported Operation");
    }

	public void addAdvancedPage(AdvancedWizardPage page) {
    	
    	super.addPage(page);
    }

	public String getHelpURL()
	{
		return null;
	}
	
	public String getMoreInfoURL()
	{
		return null;
	}

	protected Image getWindowImage()
	{
		return null;
	}
	
	protected Image getWizardImage()
	{
		return null;
	}
}
