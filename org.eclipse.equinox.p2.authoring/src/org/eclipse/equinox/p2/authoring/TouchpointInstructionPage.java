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

import org.eclipse.equinox.p2.authoring.forms.EditAdapter;
import org.eclipse.equinox.p2.authoring.forms.Mutator;
import org.eclipse.equinox.p2.authoring.forms.RichDetailsPage;
import org.eclipse.equinox.p2.authoring.forms.validators.IEditValidator;
import org.eclipse.equinox.p2.authoring.forms.validators.RequiredValidator;
import org.eclipse.equinox.p2.authoring.forms.validators.StructuredNameValidator;
import org.eclipse.equinox.p2.authoring.internal.InstallableUnitBuilder.TouchpointInstructionBuilder;
import org.eclipse.equinox.p2.authoring.internal.InstallableUnitBuilder.TouchpointTypeBuilder;
import org.eclipse.equinox.p2.authoring.spi.ITouchpointTypeDescriptor;
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
import org.eclipse.ui.forms.editor.IFormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

/**
 * A Detail page for p2 Touchpoint Instruction tree node (shows information about the phase).
 * 
 * @author Henrik Lindberg
 * 
 */
public class TouchpointInstructionPage extends RichDetailsPage

{
	private static final String PHASE_ID_ADAPTER = "phaseId";
	private Text m_text;
	private TouchpointInstructionBuilder m_input;
//	private PhaseValidator m_phaseValidator; // TODO: See other comments about phase validator
	public TouchpointInstructionPage()
	{
//		m_phaseValidator = new PhaseValidator();
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
		section.setText("Touchpoint Instruction");
		Composite sectionClient = toolkit.createComposite(section);
		GridLayout layout = new GridLayout(2, false);
		sectionClient.setLayout(layout);

		FormColors colors = toolkit.getColors();
		Color headerColor = colors.getColor("org.eclipse.ui.forms.TITLE");

		// -- LABEL TEXT
		Label label = toolkit.createLabel(sectionClient, "Phase:");
		label.setForeground(headerColor);
		m_text = toolkit.createText(sectionClient, "");
		m_text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		// TODO: Use the phase validator instead to get error feedback, but it does not
		// work well yet.
		//
		m_editAdapters.createEditAdapter(PHASE_ID_ADAPTER, m_text, //$NON-NLS-1$
				new RequiredValidator(StructuredNameValidator.instance()),
				new Mutator()
				{
					@Override
					public String getValue()
					{
						return m_input != null && m_input.getPhaseId() != null
								? m_input.getPhaseId()
								: ""; //$NON-NLS-1$
					}

					@Override
					public void setValue(String input) throws Exception
					{
						// Do nothing, the instruction can not be changed.
					}
				});
		m_editAdapters.getAdapter(PHASE_ID_ADAPTER).setEnabled(false);
	
		// TODO: Add text from extension for the touchpoint type that explains the phase
		//
		section.setClient(sectionClient);
	}
	/**
	 * The PhaseValidator validates that the phase is listed among the phases supported by
	 * a touchpoint type.
	 * TODO: Error messages and master detail needs an over haul and needs to work with problem markers.
	 * @author Henrik Lindberg
	 *
	 */
	public class PhaseValidator implements IEditValidator
	{

		public String inputFilter(String input)
		{
			return null;
		}

		public boolean isValid(String input, EditAdapter editAdapter)
		{
			IFormPage formPage = (IFormPage)m_mform.getContainer();
			TouchpointTypeBuilder type = ((InstallableUnitEditor)formPage.getEditor()).getInstallableUnit().getTouchpointType();
			ITouchpointTypeDescriptor desc = P2AuthoringUIPlugin.getDefault().getTouchpointType(type);
			String[] phases = desc.getPhases();
			if(phases != null)
				for(int i = 0; i < phases.length;i++)
					if(phases[i].equals(input))
					{
						editAdapter.clearMessages();
						return true;
					}
			
			editAdapter.setErrorMessage("Instruction phase not supported by current touchpoint type.");
			return false;
		}
		
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
		if(ssel.size() == 1 && ssel.getFirstElement() instanceof TouchpointInstructionBuilder)
			m_input = (TouchpointInstructionBuilder)ssel.getFirstElement();
		refresh();
	}
	
}
