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

package org.eclipse.equinox.p2.authoring.forms;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.SectionPart;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

/**
 * Creates a MasterDetailsBlock with a Table view.
 * 
 * @author Henrik Lindberg
 * 
 */
public abstract class TableMasterDetailsBlock extends AbstractMasterDetailsBlock
{
	protected TableViewer m_viewer;
	private int m_heightHint;
	
	public TableMasterDetailsBlock(FormPage page, Object layoutData, int heightHint)
	{
		super(page, layoutData);
		m_heightHint = heightHint;
	}

	@Override
	protected void createMasterPart(final IManagedForm managedForm, Composite parent)
	{

		FormToolkit toolkit = managedForm.getToolkit();
		Section section = toolkit.createSection(parent, m_description != null
				? Section.DESCRIPTION
				: 0 | //
				Section.TITLE_BAR);
		section.setText(getName());
		if(m_description != null)
			section.setDescription(getDescription());
		section.marginWidth = 0;
		section.marginHeight = 0;

		Composite client = toolkit.createComposite(section, SWT.WRAP);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginWidth = 2;
		layout.marginHeight = 2;
		client.setLayout(layout);

		Table t = toolkit.createTable(client, SWT.NULL);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = m_heightHint;
		gd.widthHint = 270;
		t.setLayoutData(gd);
		toolkit.paintBordersFor(client);

		m_viewer = new TableViewer(t);
		final StandardButtons buttons = createStandardButtonBar(toolkit, client);

		section.setClient(client);
		final SectionPart spart = new SectionPart(section);
		managedForm.addPart(spart);


		m_viewer.addSelectionChangedListener(new ISelectionChangedListener()
		{
			public void selectionChanged(SelectionChangedEvent event)
			{
				managedForm.fireSelectionChanged(spart, event.getSelection());
				setStandardButtonEnablement(buttons, (IStructuredSelection)event.getSelection());
			}
		});

		m_viewer.setContentProvider(getMasterContentProvider());
		m_viewer.setLabelProvider(getMasterLabelProvider());
		m_viewer.setInput(m_formPage.getEditor().getEditorInput());
	}
	protected void setStandardButtonEnablement(StandardButtons buttons, IStructuredSelection selection)
	{
		buttons.remove.setEnabled(selection != null && selection.size() > 0);
		Object[] elements = getMasterContentProvider().getElements(m_formPage.getEditor().getEditorInput());
		if(elements == null || elements.length < 2)
		{
			buttons.down.setEnabled(false);
			buttons.up.setEnabled(false);
			return;
		}
		// down is enabled if selected is not the last
		buttons.down.setEnabled(elements[elements.length-1] != selection.getFirstElement());
		// up is enabled if selected is not the first
		buttons.up.setEnabled(elements[0] != selection.getFirstElement());
	}
	@Override
	public void setSelected(ISelection selection)
	{
		m_viewer.setSelection(selection);
	}

}
