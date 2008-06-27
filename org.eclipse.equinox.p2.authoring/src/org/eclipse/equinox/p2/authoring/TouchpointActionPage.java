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

import org.eclipse.equinox.p2.authoring.forms.Mutator;
import org.eclipse.equinox.p2.authoring.forms.RichDetailsPage;
import org.eclipse.equinox.p2.authoring.forms.validators.RequiredValidator;
import org.eclipse.equinox.p2.authoring.forms.validators.StructuredNameValidator;
import org.eclipse.equinox.p2.authoring.internal.InstallableUnitBuilder.TouchpointActionBuilder;
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
 * A Detail page for p2 Touchpoint Action tree node
 * 
 * @author Henrik Lindberg
 * 
 */
public class TouchpointActionPage extends RichDetailsPage

{
	private static final String ACTION_TEXT = "actionText";
	private TouchpointActionBuilder m_input;
	
	private static int MAX_PARAMETERS = 5;
	private Label m_labels[] = new Label[MAX_PARAMETERS];
	private Text m_texts[] = new Text[MAX_PARAMETERS];
	
	// the current set of parameter names = start with default names
	private String m_parameterNames[] = { "param1", "param2", "param3", "param4", "param5" };

	public TouchpointActionPage()
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
		section.setText("Touchpoint Action");
		Composite sectionClient = toolkit.createComposite(section);
		GridLayout layout = new GridLayout(2, false);
		sectionClient.setLayout(layout);

		FormColors colors = toolkit.getColors();
		Color headerColor = colors.getColor("org.eclipse.ui.forms.TITLE");

		// -- ACTION NAME
		Label label = toolkit.createLabel(sectionClient, "Action:");
		label.setForeground(headerColor);
		Text actionText = toolkit.createText(sectionClient, "");
		actionText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		m_editAdapters.createEditAdapter(ACTION_TEXT, actionText, //$NON-NLS-1$
				new RequiredValidator(StructuredNameValidator.instance()),
				new Mutator()
				{
					@Override
					public String getValue()
					{
						return m_input != null && m_input.getActionKey() != null
								? m_input.getActionKey()
								: ""; //$NON-NLS-1$
					}

					@Override
					public void setValue(String input) throws Exception
					{
						// Do nothing - action text can not be changed
					}
				});
		// The action key/action name can not be changed - disable it
		m_editAdapters.getAdapter(ACTION_TEXT).setEnabled(false);
		
		// -- LABEL TEXT
		for(int i = 0; i < MAX_PARAMETERS; i++)
		{
		m_labels[i] = toolkit.createLabel(sectionClient, m_parameterNames[i] + ":");
		m_labels[i].setForeground(headerColor);
		m_texts[i] = toolkit.createText(sectionClient, "");
		m_texts[i].setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		// TODO: all parameters now get the RequiredValidator - much check if parameter is
		// optional (none for touchpoint types 1.0 are though).
		m_editAdapters.createEditAdapter("text"+Integer.toString(i), m_texts[i], //$NON-NLS-1$
				new RequiredValidator(StructuredNameValidator.instance()),
				new IndexedMutator(0));
		}
	
		// TODO: Add text from extension for the touchpoint type that explains the phase
		//
		section.setClient(sectionClient);
	}
	/**
	 * Mutator for an indexed parameter
	 * @author henrik
	 *
	 */
	private class IndexedMutator extends Mutator
	{
		private final int m_index;
		IndexedMutator(int index)
		{
			m_index = index;
		}
		@Override
		public String getValue()
		{
			return m_input != null && m_input.getParameter(m_parameterNames[m_index]) != null
					? m_input.getParameter(m_parameterNames[m_index])
					: ""; //$NON-NLS-1$
		}

		@Override
		public void setValue(String input) throws Exception
		{
			if(m_input == null)
				return;
			m_input.setParameter(m_parameterNames[m_index], input == null ? "" : input); //$NON-NLS-1$
		}
	}

	public void setFocus()
	{
		// sets focus on first parameter (may be hidden - but that is ok).
		m_texts[0].setFocus();
	}

	@Override
	public void selectionChanged(IFormPart part, ISelection selection)
	{
		IStructuredSelection ssel = (IStructuredSelection)selection;
		m_input = null; // clear old input
		if(ssel.size() == 1 && ssel.getFirstElement() instanceof TouchpointActionBuilder)
		{
			m_input = (TouchpointActionBuilder)ssel.getFirstElement();
			refreshLabels();
		}
		refresh();
	}
	/**
	 * Update the labels to reflect the action parameters, and hide unused parameter fields
	 */
	private void refreshLabels()
	{		
		String[] parameterNames = (String[])m_input.getParameterNames().toArray();
		int i = 0;
		for(; i < parameterNames.length && i < MAX_PARAMETERS; i++)
		{
			// TODO: set the text for the label from the extension point information
			// TODO: set the validation type for the text field
			// TODO: hook advanced (browse) function to applicable types
			m_labels[i].setText(parameterNames[i]);
			m_labels[i].setVisible(true);
			m_texts[i].setVisible(true);
		}
		for(; i < MAX_PARAMETERS; i++)
		{
			m_labels[i].setVisible(false);
			m_texts[i].setVisible(false);
		}
	}
	
}
