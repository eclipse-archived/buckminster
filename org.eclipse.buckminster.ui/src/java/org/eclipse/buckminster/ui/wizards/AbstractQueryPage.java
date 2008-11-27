/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.ui.wizards;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.RMContext;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;

public abstract class AbstractQueryPage extends WizardPage
{

	public AbstractQueryPage(String pageName)
	{
		super(pageName);
	}

	public void createControl(Composite parent)
	{
		this.setControl(this.createControls(parent));
		this.setErrorMessage(null);
	}

	@Override
	public void setVisible(boolean visible)
	{
		super.setVisible(visible);
		if(visible)
			this.pageIsShowing();
		else
			this.pageIsHiding();
	}

	abstract protected Composite createControls(Composite parent);

	protected final void displayException(CoreException e)
	{
		CorePlugin.getLogger().warning(e, e.getMessage());
		setErrorMessage(e.getMessage());
	}

	protected RMContext getContext()
	{
		return this.getQueryWizard().getContext();
	}

	protected QueryWizard getQueryWizard()
	{
		return (QueryWizard)this.getWizard();
	}

	protected void pageIsHiding()
	{
		// noop
	}

	protected void pageIsShowing()
	{
		// noop
	}
}
