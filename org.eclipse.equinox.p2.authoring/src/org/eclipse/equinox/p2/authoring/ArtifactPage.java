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
import org.eclipse.equinox.p2.authoring.forms.validators.RangeValidator;
import org.eclipse.equinox.p2.authoring.forms.validators.RequiredValidator;
import org.eclipse.equinox.p2.authoring.forms.validators.StructuredNameValidator;
import org.eclipse.equinox.p2.authoring.internal.IEditEventBusProvider;
import org.eclipse.equinox.p2.authoring.internal.InstallableUnitBuilder.ArtifactKeyBuilder;
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
import org.eclipse.ui.forms.IDetailsPage;
import org.eclipse.ui.forms.IFormPart;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

/**
 * A DetailPage for an Installable Unit ArtifactKey
 * 
 * @author Henrik Lindberg
 * 
 */

public class ArtifactPage implements IDetailsPage
{
	protected IManagedForm m_mform;

	protected ArtifactKeyBuilder m_input;

	protected EditAdapterFormPart m_editAdapters;

	private Text m_labelText; // needed as member to set focus on it

	public ArtifactPage()
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
		if(ssel.size() == 1 && ssel.getFirstElement() instanceof ArtifactKeyBuilder)
			m_input = (ArtifactKeyBuilder)(ssel.getFirstElement());
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
		section.setText("Edit Artifact Key");

		Composite sectionClient = toolkit.createComposite(section);

		GridLayout layout = new GridLayout(2, false);
		sectionClient.setLayout(layout);

		FormColors colors = toolkit.getColors();
		Color headerColor = colors.getColor("org.eclipse.ui.forms.TITLE"); //$NON-NLS-1$
		Label label = null;

		// -- CLASSIFIER
		label = toolkit.createLabel(sectionClient, "Classifier:");
		label.setForeground(headerColor);
		final Text nsText = toolkit.createText(sectionClient, ""); //$NON-NLS-1$
		GridData gd = new GridData(SWT.FILL, SWT.CENTER, true, false);
		gd.minimumWidth = 200;
		nsText.setLayoutData(gd);
		m_editAdapters.createEditAdapter("classifier", nsText, //$NON-NLS-1$
				new RequiredValidator(StructuredNameValidator.instance()),
				new Mutator()
				{
					@Override
					public String getValue()
					{
						return m_input != null && m_input.getClassifier() != null
								? m_input.getClassifier()
								: ""; //$NON-NLS-1$
					}

					@Override
					public void setValue(String input) throws Exception
					{
						m_input.setClassifier(input == null
								? "" //$NON-NLS-1$
								: input.trim());
					}
				});

		// -- ID
		label = toolkit.createLabel(sectionClient, "Id:");
		label.setForeground(headerColor);
		final Text nameText = toolkit.createText(sectionClient, "");
		nameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		m_editAdapters.createEditAdapter("id", nameText, //$NON-NLS-1$
				new RequiredValidator(StructuredNameValidator.instance()), // TODO: An ArtifactKey id validator				
				new Mutator()
				{

					@Override
					public String getValue()
					{
						return m_input != null && m_input.getId() != null
								? m_input.getId()
								: ""; //$NON-NLS-1$
					}

					@Override
					public void setValue(String input) throws Exception
					{
						m_input.setId(input == null
								? ""  //$NON-NLS-1$
								: input.trim());
					}
				});

		// -- VERSION
		label = toolkit.createLabel(sectionClient, "Version:");
		label.setForeground(headerColor);
		final Text versionText = toolkit.createText(sectionClient, "");
		versionText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		m_editAdapters.createEditAdapter("version", versionText, //$NON-NLS-1$
				RangeValidator.instance(),
				new Mutator()
				{

					@Override
					public String getValue()
					{
						return m_input != null && m_input.getVersion() != null
								? m_input.getVersion()
								: ""; //$NON-NLS-1$
					}

					@Override
					public void setValue(String input) throws Exception
					{
						m_input.setVersion(input == null
								? ""  //$NON-NLS-1$
								: input.trim());
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
