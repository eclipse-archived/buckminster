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

import javax.swing.event.ChangeEvent;

import org.eclipse.equinox.p2.authoring.forms.Mutator;
import org.eclipse.equinox.p2.authoring.forms.RichDetailsPage;
import org.eclipse.equinox.p2.authoring.forms.validators.NullValidator;
import org.eclipse.equinox.p2.authoring.internal.IEditEventBusProvider;
import org.eclipse.equinox.p2.authoring.internal.InstallableUnitBuilder.TouchpointDataBuilder;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.FormColors;
import org.eclipse.ui.forms.IFormPart;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

/**
 * A Detail page for p2 TouchpointData tree node (shows/edits it's label)
 * 
 * @author Henrik Lindberg
 * 
 */
public class TouchpointDataPage extends RichDetailsPage

{
	private static final String TOUCHPOINT_LABEL = "touchpointLabel";
	private Text m_text;
	private TouchpointDataBuilder m_input;

	public TouchpointDataPage()
	{
	}

	public void createContents(Composite parent)
	{
		TableWrapLayout lo = new TableWrapLayout();
		lo.leftMargin = 0;
		lo.rightMargin = 0;
		lo.topMargin = 0;
		lo.numColumns = 1;
		parent.setLayout(lo);
		
		FormToolkit toolkit = m_mform.getToolkit();

		Section section = toolkit.createSection(parent, //
				// Section.DESCRIPTION | //
						Section.TITLE_BAR | //
						Section.EXPANDED);

		TableWrapData td = new TableWrapData(TableWrapData.FILL_GRAB, TableWrapData.TOP) ;
		td.colspan = 1;
		section.setLayoutData(td);
		section.setText("Edit Touchpoint Data");
		Composite sectionClient = toolkit.createComposite(section);
		GridLayout layout = new GridLayout(2, false);
		sectionClient.setLayout(layout);

		FormColors colors = toolkit.getColors();
		Color headerColor = colors.getColor("org.eclipse.ui.forms.TITLE");

		// -- LABEL TEXT
		Label label = toolkit.createLabel(sectionClient, "Label:");
		label.setForeground(headerColor);
		m_text = toolkit.createText(sectionClient, "");
		m_text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		m_editAdapters.createEditAdapter(TOUCHPOINT_LABEL, m_text, //$NON-NLS-1$
				NullValidator.instance(),
				new Mutator()
				{
					@Override
					public String getValue()
					{
						return m_input != null && m_input.getName() != null
								? m_input.getName()
								: ""; //$NON-NLS-1$
					}

					@Override
					public void setValue(String input) throws Exception
					{
						m_input.setName(input == null
								? "" //$NON-NLS-1$
								: input.trim());
					}
				});
		// TODO: When the touchpoint has an editable label remove the next line
		m_editAdapters.getAdapter(TOUCHPOINT_LABEL).setEnabled(false);

		section.setClient(sectionClient);
	}


	public void setFocus()
	{
		m_text.setFocus();
	}

	@Override
	public void selectionChanged(IFormPart part, ISelection selection)
	{
		IStructuredSelection ssel = (IStructuredSelection)selection;
		m_input = null; // clear old input
		if(ssel.size() == 1 && ssel.getFirstElement() instanceof TouchpointDataBuilder)
			m_input = (TouchpointDataBuilder)ssel.getFirstElement();
		refresh();
	}
	
	/**
	 * @see org.eclipse.ui.forms.IDetailsPage#commit()
	 */
	@Override
	public void commit(boolean onSave)
	{
		m_editAdapters.commit(onSave);
		// send event that the input was changed
		if(m_mform.getToolkit() instanceof IEditEventBusProvider)
		{
			((IEditEventBusProvider)m_mform.getToolkit()).getEventBus().publishEvent(new ChangeEvent(m_input));
		}
	}
	
}
