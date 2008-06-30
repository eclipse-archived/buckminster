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

import org.eclipse.equinox.p2.authoring.forms.EditAdapterFormPart;
import org.eclipse.equinox.p2.authoring.forms.Mutator;
import org.eclipse.equinox.p2.authoring.forms.RichFormPage;
import org.eclipse.equinox.p2.authoring.forms.validators.NullValidator;
import org.eclipse.equinox.p2.authoring.forms.validators.PositiveIntValidator;
import org.eclipse.equinox.p2.authoring.forms.validators.RangeValidator;
import org.eclipse.equinox.p2.authoring.forms.validators.RequiredValidator;
import org.eclipse.equinox.p2.authoring.forms.validators.StructuredNameValidator;
import org.eclipse.equinox.p2.authoring.internal.InstallableUnitBuilder;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.forms.FormColors;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.SectionPart;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

/**
 * This is an Update page with information about what an an installable unit 
 * is an update of.
 *  
 * @author Henrik Lindberg
 * 
 */
class UpdatePage extends RichFormPage
{
	public static final String PAGE_ID = "update.id"; //$NON-NLS-1$

	private EditAdapterFormPart m_editAdapters = new EditAdapterFormPart();

	public UpdatePage(FormEditor editor)
	{
		super(editor, PAGE_ID, "Update");
		m_header = "Update";
		m_headerImage = P2AuthoringImages.getImage(P2AuthoringImages.IMG_OVERVIEW);
		m_makeColumnsEqualWidth = true;
	}

	@Override
	public void setInput(IEditorInput input)
	{
		super.setInput(input);
	}

	/**
	 * @see org.eclipse.equinox.p2.authoring.forms.RichFormPage#addFormContent(org.eclipse.ui.forms.IManagedForm)
	 */
	@Override
	protected void addFormContent(IManagedForm managedForm)
	{
		// Page uses one edit adapters form part to manage the lifecycle of fields.
		managedForm.addPart(m_editAdapters);

		final ScrolledForm scrolledForm = managedForm.getForm();
		FormToolkit toolkit = managedForm.getToolkit();
		// create two columns

		Composite leftColumn = toolkit.createComposite(scrolledForm.getBody());
		TableWrapLayout layout = new TableWrapLayout();
		layout.numColumns = 1;
		TableWrapData twd = new TableWrapData(TableWrapData.LEFT, TableWrapData.TOP);
		leftColumn.setLayout(layout);
		leftColumn.setLayoutData(twd);

		Composite rightColumn = toolkit.createComposite(scrolledForm.getBody());
		layout = new TableWrapLayout();
		layout.numColumns = 1;
		twd = new TableWrapData(TableWrapData.LEFT, TableWrapData.TOP);
		rightColumn.setLayout(layout);
		rightColumn.setLayoutData(twd);

		// Update
		Section section = createUpdateSection(toolkit, leftColumn);
		managedForm.addPart(new SectionPart(section));
		section.addExpansionListener(getReflowListener());

	}

	private InstallableUnitBuilder getIU()
	{
		return ((InstallableUnitEditor)getEditor()).getInstallableUnit();
	}

	private Section createUpdateSection(FormToolkit toolkit, Composite parent)
	{
		Section section = toolkit.createSection(parent, //
				Section.DESCRIPTION | //
						Section.TITLE_BAR | //
						// Section.TWISTIE | // Expandable by user
						Section.EXPANDED);
		TableWrapData td = new TableWrapData(TableWrapData.LEFT, TableWrapData.TOP);
		td.colspan = 1;
		section.setLayoutData(td);
		section.setText("Update information");
		section.setDescription("This section describes an Installable Unit this unit is an update of");
		Composite sectionClient = toolkit.createComposite(section);
		GridLayout layout = new GridLayout(2, false);
		sectionClient.setLayout(layout);

		// setup for reuse
		FormColors colors = toolkit.getColors();
		Color headerColor = colors.getColor("org.eclipse.ui.forms.TITLE"); //$NON-NLS-1$
		Label label = null;

		// Installable Unit (this update applies to).
		//
		label = toolkit.createLabel(sectionClient, "Installable Unit:");
		label.setForeground(headerColor);
		final Text iuText = toolkit.createText(sectionClient, "");
		GridData gd = new GridData(SWT.FILL, SWT.CENTER, true, false);
		gd.minimumWidth = 200;
		iuText.setLayoutData(gd);
		m_editAdapters.createEditAdapter("iuText", iuText, //$NON-NLS-1$
				StructuredNameValidator.instance(), new Mutator()
				{
					@Override
					public String getValue()
					{
						InstallableUnitBuilder iu = getIU();
						if(iu == null)
							return ""; //$NON-NLS-1$
						String val = iu.getUpdateDescriptor().getUpdateid();
						return val != null
								? val
								: ""; //$NON-NLS-1$
					}

					@Override
					public void setValue(String input) throws Exception
					{
						InstallableUnitBuilder iu = getIU();
						if(iu != null)
							iu.getUpdateDescriptor().setUpdateid(input);
					}
				});
		
		// Version Range
		//
		label = toolkit.createLabel(sectionClient, "Version Range:");
		label.setForeground(headerColor);
		final Text rangeText = toolkit.createText(sectionClient, ""); //$NON-NLS-1$
		rangeText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		m_editAdapters.createEditAdapter("rangeText", rangeText, //$NON-NLS-1$
				RangeValidator.instance(), //
				new Mutator()
				{
					@Override
					public String getValue()
					{
						InstallableUnitBuilder iu = getIU();
						if(iu == null)
							return ""; //$NON-NLS-1$
						String val = iu.getUpdateDescriptor().getRange();
						return val != null
								? val
								: ""; //$NON-NLS-1$
					}

					@Override
					public void setValue(String input) throws Exception
					{
						InstallableUnitBuilder iu = getIU();
						if(iu != null)
							iu.getUpdateDescriptor().setRange(input);
					}
				});


		// Severity
		label = toolkit.createLabel(sectionClient, "Severity:");
		label.setForeground(headerColor);
		final Text severityText = toolkit.createText(sectionClient, "");
		severityText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		m_editAdapters.createEditAdapter("nameText", severityText, //$NON-NLS-1$
				PositiveIntValidator.instance(), //
				new Mutator()
				{
					@Override
					public String getValue()
					{
						InstallableUnitBuilder iu = getIU();
						if(iu == null)
							return ""; //$NON-NLS-1$
						String val = Integer.toString(iu.getUpdateDescriptor().getSeverity());
						return val != null
								? val
								: ""; //$NON-NLS-1$
					}

					@Override
					public void setValue(String input) throws Exception
					{
						InstallableUnitBuilder iu = getIU();
						if(iu != null)
							iu.getUpdateDescriptor().setSeverity(Integer.valueOf(input));
					}
				});


		// Description
		label = toolkit.createLabel(sectionClient, "Description:");
		label.setForeground(colors.getColor("org.eclipse.ui.forms.TITLE"));
		final Text descText = toolkit.createText(sectionClient, "");
		descText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		m_editAdapters.createEditAdapter("descText", descText, //$NON-NLS-1$
				NullValidator.instance(), //
				new Mutator()
				{
					@Override
					public String getValue()
					{
						InstallableUnitBuilder iu = getIU();
						if(iu == null)
							return ""; //$NON-NLS-1$
						String val = iu.getUpdateDescriptor().getDescription();
						return val != null
								? val
								: ""; //$NON-NLS-1$
					}

					@Override
					public void setValue(String input) throws Exception
					{
						InstallableUnitBuilder iu = getIU();
						if(iu != null)
							iu.getUpdateDescriptor().setDescription(input);
					}
				});
		
		section.setClient(sectionClient);
		return section;

	}
}
