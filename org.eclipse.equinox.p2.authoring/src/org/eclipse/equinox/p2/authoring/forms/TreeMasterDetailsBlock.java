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
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.SectionPart;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

/**
 * Creates a MasterDetailsBlock with a Tree view.
 * 
 * @author Henrik Lindberg
 *
 */
public abstract class TreeMasterDetailsBlock extends AbstractMasterDetailsBlock
{
	private TreeViewer m_viewer;
	
	public TreeMasterDetailsBlock(FormPage page, Object layoutData)
	{
		super(page, layoutData);
	}
	
	@Override
	protected void createMasterPart(final IManagedForm managedForm, Composite parent) {

		FormToolkit toolkit = managedForm.getToolkit();
		Section section = toolkit.createSection(parent, 
				m_description != null ? Section.DESCRIPTION : 0 | //
				Section.TITLE_BAR);
		section.setText(getName());
		if(m_description != null)
			section.setDescription(getDescription());
		section.marginWidth = 0;
		section.marginHeight = 0;

		Composite client = toolkit.createComposite(section, SWT.WRAP);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginWidth = 0;
		layout.marginHeight = 10;
		layout.horizontalSpacing = 0;
		layout.verticalSpacing = 0;
		
		client.setLayout(layout);

		Tree t = toolkit.createTree(client, SWT.NULL);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 250;
		gd.widthHint = 270;
		t.setLayoutData(gd);
		toolkit.paintBordersFor(client);

		createStandardButtonBar(toolkit, client);

		section.setClient(client);
		final SectionPart spart = new SectionPart(section);
		managedForm.addPart(spart);

		m_viewer = new TreeViewer(t);
		
		m_viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				managedForm.fireSelectionChanged(spart, event.getSelection());
			}
		});

		m_viewer.setContentProvider(getMasterContentProvider());
		m_viewer.setLabelProvider(getMasterLabelProvider());
		m_viewer.setInput(m_formPage.getEditor().getEditorInput());
	}
	@Override
	public void setSelected(ISelection selection)
	{
		m_viewer.setSelection(selection);
	}

}
