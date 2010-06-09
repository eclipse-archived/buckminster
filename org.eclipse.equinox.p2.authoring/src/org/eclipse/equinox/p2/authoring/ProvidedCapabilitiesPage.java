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
 * Form page for Provided Capabilities.
 * (Most of the work done in {@link ProvidedBodyBlock}).
 * @author henrik
 *
 */
public class ProvidedCapabilitiesPage extends RichFormPage implements IPageMementoProvider
{
	public static final String PAGE_ID = "provided.id"; //$NON-NLS-1$
	private final ProvidedBodyBlock m_providedBodyBlock;

	public ProvidedCapabilitiesPage(FormEditor editor)
	{
		super(editor, PAGE_ID, "Provided Capabilities");
		m_header = "Provided Capabilities";
		m_headerImage = P2AuthoringImages.getImage(P2AuthoringImages.IMG_PROV_CAPABILITIES);
		
		m_numColumns = 1;
		TableWrapData wrapData = new TableWrapData(TableWrapData.FILL_GRAB,TableWrapData.FILL_GRAB);
		wrapData.indent = 0;

		m_providedBodyBlock = new ProvidedBodyBlock(this, wrapData);
	}

	@Override
	protected void addFormContent(IManagedForm managedForm)
	{		
		m_providedBodyBlock.createContent(managedForm);
				
	}

	public Object getPageMemento()
	{
		return m_providedBodyBlock.getPageMemento();
	}

	public void setPageMemento(Object memento)
	{
		m_providedBodyBlock.setPageMemento(memento);
	}
}
