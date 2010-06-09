/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp.ui.general.wizard;

import org.eclipse.buckminster.jnlp.Messages;
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
	public void addAdvancedPage(AdvancedWizardPage page)
	{

		super.addPage(page);
	}

	/**
	 * Use addAdvancedPage(AdvancedWizardPage) instead
	 */
	@Override
	public void addPage(IWizardPage page)
	{
		throw new RuntimeException(Messages.unsupported_operation);
	}

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

	public String getHelpURL()
	{
		return null;
	}

	public String getMoreInfoURL()
	{
		return null;
	}

	abstract protected void addAdvancedPages();

	protected Image getWindowImage()
	{
		return null;
	}

	protected Image getWizardImage()
	{
		return null;
	}
}
