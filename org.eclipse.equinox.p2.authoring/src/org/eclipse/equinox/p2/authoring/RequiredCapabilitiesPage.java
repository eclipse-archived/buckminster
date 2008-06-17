/*******************************************************************************
 * Copyright (c) 2008
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed below, as Initial Contributors under such license.
 * The text of such license is available at 
 * http://www.eclipse.org/legal/epl-v10.html.
 * 
 * Contributors:
 * 		Henrik Lindberg
 *******************************************************************************/

package org.eclipse.equinox.p2.authoring;

import org.eclipse.equinox.p2.authoring.forms.IPageMementoProvider;
import org.eclipse.equinox.p2.authoring.forms.RichFormPage;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.widgets.TableWrapData;

/**
 * Page for editing Installable Unit Required Capabilities.
 * @author Henrik Lindberg
 *
 */
public class RequiredCapabilitiesPage extends RichFormPage implements IPageMementoProvider
{
	public static final String PAGE_ID = "required.id"; //$NON-NLS-1$

	private final RequiredBodyBlock m_requiredBodyBlock;

	public RequiredCapabilitiesPage(FormEditor editor)
	{
		super(editor, PAGE_ID, "Required Capabilities");
		m_header = "Required Capabilities";
		m_headerImage = P2AuthoringImages.getImage(P2AuthoringImages.IMG_REQ_CAPABILITIES);

		m_numColumns = 1;
		TableWrapData wrapData = new TableWrapData(TableWrapData.FILL_GRAB, TableWrapData.FILL_GRAB);
		wrapData.indent = 0;

		m_requiredBodyBlock = new RequiredBodyBlock(this, wrapData);
	}

	@Override
	protected void addFormContent(IManagedForm managedForm)
	{
		m_requiredBodyBlock.createContent(managedForm);
	}

	public Object getPageMemento()
	{
		return m_requiredBodyBlock.getPageMemento();
	}

	public void setPageMemento(Object memento)
	{
		m_requiredBodyBlock.setPageMemento(memento);
	}
}
