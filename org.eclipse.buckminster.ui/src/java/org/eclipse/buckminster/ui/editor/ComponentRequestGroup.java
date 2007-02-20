/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.ui.editor;

import org.eclipse.buckminster.core.cspec.model.ComponentCategory;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.ui.ChangeAdapter;
import org.eclipse.buckminster.ui.LabeledCombo;
import org.eclipse.buckminster.ui.UiUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

/**
 * Mini editor for the ComponentRequest. Used both in the
 * <code>QueryEditor</code> and in the <code>OpenProjectWizard</code>.
 * 
 * @author Thomas Hallgren
 */
public abstract class ComponentRequestGroup extends Composite
{
	private final Text m_componentName;

	private final LabeledCombo m_componentCategory;

	private final VersionDesignator m_versionDesignator;
	
	private boolean m_silent = false;

	public ComponentRequestGroup(Composite parent, int style)
	{
		super(parent, style);
		GridLayout layout = new GridLayout(4, false);
		layout.marginHeight = layout.marginWidth = 0;
		this.setLayout(layout);
		ChangeAdapter tcrl = this.getChangeAdapter();
		m_componentName = UiUtils.createLabeledText(this, "Component name:", 0, tcrl);
		GridData gd = new GridData(SWT.FILL, SWT.FILL, true, false);
		gd.widthHint = 200;
		m_componentName.setLayoutData(gd);

		m_componentCategory = new LabeledCombo(this, SWT.DROP_DOWN | SWT.READ_ONLY | SWT.SIMPLE);
		gd = new GridData(SWT.FILL, SWT.FILL, true, false);
		gd.widthHint = 50;
		m_componentCategory.setLayoutData(gd);
		m_componentCategory.setLabel("Category:");
		m_componentCategory.setItems(ComponentCategory.getCategoryNames(true));

		m_versionDesignator = new VersionDesignator(this, SWT.NONE);
		m_versionDesignator.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 4, 1));
		m_versionDesignator.addVersionDesignatorListener(tcrl);
	}

	public void refreshValues(ComponentRequest request)
	{
		m_silent = true;
		m_componentName.setText(TextUtils.notNullString(request.getName()));
		m_componentCategory.select(m_componentCategory.indexOf(TextUtils.notNullString(request.getCategory())));
		m_versionDesignator.refreshValues(request.getVersionDesignator());
		m_silent = false;
	}

	@Override
	public void setEnabled(boolean flag)
	{
		m_componentName.setEnabled(flag);
		m_componentCategory.setEnabled(flag);
		m_versionDesignator.setEnabled(flag);
		super.setEnabled(flag);
	}

	public String commitChanges(ComponentRequest[] requestRet)
	{
		if(m_silent)
			return null;

		String name = UiUtils.trimmedValue(m_componentName);
		if(name == null)
			return "The component must have a name";

		String category = m_componentCategory.getItem(m_componentCategory.getSelectionIndex());
		if(category.length() == 0)
			category = null;

		requestRet[0] = new ComponentRequest(name, category, m_versionDesignator.getVersionDesignator());
		return null;
	}

	protected abstract ChangeAdapter getChangeAdapter();
}
