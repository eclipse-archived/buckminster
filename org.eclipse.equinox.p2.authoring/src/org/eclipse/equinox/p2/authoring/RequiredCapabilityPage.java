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

import org.eclipse.equinox.p2.authoring.forms.EditAdapterFormPart;
import org.eclipse.equinox.p2.authoring.forms.Mutator;
import org.eclipse.equinox.p2.authoring.internal.IEditEventBusProvider;
import org.eclipse.equinox.p2.authoring.internal.InstallableUnitBuilder.RequiredCapabilityBuilder;
import org.eclipse.equinx.p2.authoring.forms.validators.LDAPFilterValidator;
import org.eclipse.equinx.p2.authoring.forms.validators.NullValidator;
import org.eclipse.equinx.p2.authoring.forms.validators.RangeValidator;
import org.eclipse.equinx.p2.authoring.forms.validators.RequiredValidator;
import org.eclipse.equinx.p2.authoring.forms.validators.StructuredNameValidator;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.FormColors;
import org.eclipse.ui.forms.IDetailsPage;
import org.eclipse.ui.forms.IFormPart;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

/**
 * A Detail page for an Installable Unit Required Capability
 * 
 * @author Henrik Lindberg
 * 
 */

public class RequiredCapabilityPage implements IDetailsPage
{
	protected IManagedForm m_mform;

	protected RequiredCapabilityBuilder m_input;

	protected EditAdapterFormPart m_editAdapters;

	private Text m_labelText; // needed as member to set focus on it

	public RequiredCapabilityPage()
	{
		m_editAdapters = new EditAdapterFormPart();
	}

	public void initialize(IManagedForm mform)
	{
		m_mform = mform;
		mform.addPart(m_editAdapters);
	}

	/**
	 * Called when master selection changes.
	 */
	public void selectionChanged(IFormPart part, ISelection selection)
	{
		IStructuredSelection ssel = (IStructuredSelection)selection;
		m_input = null; // clear old input
		if(ssel.size() == 1 && ssel.getFirstElement() instanceof RequiredCapabilityBuilder)
			m_input = (RequiredCapabilityBuilder)(ssel.getFirstElement());
		refresh();
	}

	public boolean setFormInput(Object input)
	{
		return false;
	}

	public void refresh()
	{
		m_editAdapters.refresh();
	}

	/**
	 * @see org.eclipse.ui.forms.IDetailsPage#commit()
	 */
	public void commit(boolean onSave)
	{
		m_editAdapters.commit(onSave);
		// send event that the input was changed
		if(m_mform.getToolkit() instanceof IEditEventBusProvider)
		{
			((IEditEventBusProvider)m_mform.getToolkit()).getEventBus().publishEvent(new ChangeEvent(m_input));
		}
	}

	public boolean isDirty()
	{
		return m_editAdapters.isDirty();
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

		TableWrapData td = new TableWrapData(TableWrapData.FILL_GRAB, TableWrapData.TOP);
		td.colspan = 1;
		section.setLayoutData(td);
		section.setText("Edit Required Capability");

		Composite sectionClient = toolkit.createComposite(section);

		GridLayout layout = new GridLayout(2, false);
		sectionClient.setLayout(layout);

		FormColors colors = toolkit.getColors();
		Color headerColor = colors.getColor("org.eclipse.ui.forms.TITLE"); //$NON-NLS-1$
		Label label = null;

		// -- NAMESPACE
		label = toolkit.createLabel(sectionClient, "Namespace:");
		label.setForeground(headerColor);
		final Text nsText = toolkit.createText(sectionClient, ""); //$NON-NLS-1$
		GridData gd = new GridData(SWT.FILL, SWT.CENTER, true, false);
		gd.minimumWidth = 200;
		nsText.setLayoutData(gd);
		m_editAdapters.createEditAdapter("nsText", nsText, //$NON-NLS-1$
				new RequiredValidator(StructuredNameValidator.instance()),
				new Mutator()
				{
					@Override
					public String getValue()
					{
						return m_input != null && m_input.getNamespace() != null
								? m_input.getNamespace()
								: ""; //$NON-NLS-1$
					}

					@Override
					public void setValue(String input) throws Exception
					{
						m_input.setNamespace(input == null
								? "" //$NON-NLS-1$
								: input.trim());
					}
				});

		// -- NAME
		label = toolkit.createLabel(sectionClient, "Name:");
		label.setForeground(headerColor);
		final Text nameText = toolkit.createText(sectionClient, "");
		nameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		m_editAdapters.createEditAdapter("name", nameText, //$NON-NLS-1$
				new RequiredValidator(NullValidator.instance()), // TODO: A RequiredCapability name validator				
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
								? ""  //$NON-NLS-1$
								: input.trim());
					}
				});

		// -- RANGE
		label = toolkit.createLabel(sectionClient, "Range:");
		label.setForeground(headerColor);
		final Text rangeText = toolkit.createText(sectionClient, "");
		rangeText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		m_editAdapters.createEditAdapter("range", rangeText, //$NON-NLS-1$
				RangeValidator.instance(),
				new Mutator()
				{

					@Override
					public String getValue()
					{
						return m_input != null && m_input.getRange() != null
								? m_input.getRange()
								: ""; //$NON-NLS-1$
					}

					@Override
					public void setValue(String input) throws Exception
					{
						m_input.setRange(input == null
								? ""  //$NON-NLS-1$
								: input.trim());
					}
				});

		// -- FILTER
		label = toolkit.createLabel(sectionClient, "Filter:");
		label.setForeground(headerColor);
		final Text filterText = toolkit.createText(sectionClient, "");
		filterText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		m_editAdapters.createEditAdapter("filter", filterText, //$NON-NLS-1$
				LDAPFilterValidator.instance(), //
				new Mutator()
				{

					@Override
					public String getValue()
					{
						return m_input != null && m_input.getCapfilter() != null
								? m_input.getCapfilter()
								: ""; //$NON-NLS-1$
					}

					@Override
					public void setValue(String input) throws Exception
					{
						m_input.setCapfilter(input == null
								? ""  //$NON-NLS-1$
								: input.trim());
					}
				});


		// -- GREEDY
		label = toolkit.createLabel(sectionClient, "");
		label.setForeground(headerColor);
		final Button greedyButton = toolkit.createButton(sectionClient, "Greedy", SWT.CHECK);
		greedyButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		m_editAdapters.createEditAdapter("greedy", greedyButton, //$NON-NLS-1$
				null, // validator of boolean not needed
				new Mutator()
				{
					@Override
					public boolean getBooleanValue()
					{
						return m_input != null
								? m_input.isGreedy()
								: false;
					}

					@Override
					public void setValue(boolean input) throws Exception
					{
						m_input.setGreedy(input);
					}
				});

		// -- MULTIPLE
		label = toolkit.createLabel(sectionClient, "");
		label.setForeground(headerColor);
		final Button multipleButton = toolkit.createButton(sectionClient, "Multiple", SWT.CHECK);
		multipleButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		m_editAdapters.createEditAdapter("mult", multipleButton, //$NON-NLS-1$ 
				null, // validator of boolean not needed
				new Mutator()
				{

					@Override
					public boolean getBooleanValue()
					{
						return m_input != null
								? m_input.isMultiple()
								: false;
					}

					@Override
					public void setValue(boolean input) throws Exception
					{
						m_input.setMultiple(input);
					}
				});

		// -- OPTIONAL
		label = toolkit.createLabel(sectionClient, "");
		label.setForeground(headerColor);
		final Button optionalButton = toolkit.createButton(sectionClient, "Optional", SWT.CHECK);
		multipleButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		m_editAdapters.createEditAdapter("opt", optionalButton, //$NON-NLS-1$
				null, // validator of boolean not needed
				new Mutator()
				{

					@Override
					public boolean getBooleanValue()
					{
						return m_input != null
								? m_input.isOptional()
								: false;
					}

					@Override
					public void setValue(boolean input) throws Exception
					{
						m_input.setOptional(input);
					}
				});
		
		section.setClient(sectionClient);
	}

	public void setFocus()
	{
		m_labelText.setFocus();
	}

	public void dispose()
	{
	}

	public boolean isStale()
	{
		return false;
	}
}
