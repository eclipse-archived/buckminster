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
import org.eclipse.equinox.p2.authoring.internal.P2AuthoringLabelProvider;
import org.eclipse.equinox.p2.authoring.internal.P2StyledLabelProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.FormColors;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;

/**
 * A Page for editing touchpoint type and touchpoint data for a p2 IU.
 * @author Henrik Lindberg
 * 
 */
public class TouchpointPage extends RichFormPage implements IPageMementoProvider
{
	public static final String PAGE_ID = "touchpoint.id";
	private final TouchpointBodyBlock m_touchpointBodyBlock;

	public TouchpointPage(FormEditor editor)
	{
		super(editor, PAGE_ID, "Touchpoint");
		m_header = "Touchpoint";
		m_numColumns = 1;
		TableWrapData wrapData = new TableWrapData(TableWrapData.FILL_GRAB,TableWrapData.FILL_GRAB);
		wrapData.indent = 0;

		m_touchpointBodyBlock = new TouchpointBodyBlock(this, wrapData);
	}

	@Override
	protected void addFormContent(IManagedForm managedForm)
	{
		final ScrolledForm scrolledForm = managedForm.getForm();
		FormToolkit toolkit = managedForm.getToolkit();
		
		Section section = createGeneralSection(toolkit, scrolledForm.getBody());
		section.addExpansionListener(getReflowListener());
		
		m_touchpointBodyBlock.createContent(managedForm);
				
	}
	/**
	 * Creates a general section above the master detail to allow setting the touchpoint type from 
	 * a selection of available touchpoint type/versions.
	 * 
	 * @param toolkit
	 * @param parent
	 * @return
	 */
	private Section createGeneralSection(FormToolkit toolkit, Composite parent)
	{
		Section section = toolkit.createSection(parent, //
				Section.DESCRIPTION | //
				Section.TITLE_BAR | //
				Section.TWISTIE | // Expandable by user
				Section.EXPANDED);

		TableWrapData td = new TableWrapData(TableWrapData.FILL_GRAB, TableWrapData.TOP);
		td.colspan = 1;
		section.setLayoutData(td);
		section.setText("Touchpoint Type");
		section.setDescription("The Touchpoint Type determines the type of installation.");
		Composite sectionClient = toolkit.createComposite(section);
		GridLayout layout = new GridLayout(2, false);
		sectionClient.setLayout(layout);
		
		FormColors colors = toolkit.getColors();
		Label label = toolkit.createLabel(sectionClient, "Type:");
		label.setForeground(colors.getColor("org.eclipse.ui.forms.TITLE"));
		
		Combo ttype = new Combo(sectionClient, SWT.READ_ONLY);
		ttype.setLayoutData(new GridData(SWT.FILL,SWT.CENTER, true,false));
		final ComboViewer comboViewer = new ComboViewer(ttype);
		comboViewer.setLabelProvider(new P2StyledLabelProvider());
		comboViewer.setContentProvider(new IStructuredContentProvider(){

			public Object[] getElements(Object inputElement)
			{
				return P2AuthoringUIPlugin.getDefault().getTouchpointTypes();
			}

			public void dispose()
			{
			}

			public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
			{
				comboViewer.refresh();
			}
			
		});
		comboViewer.setInput("foo"); // irrelevant - the view shows static data
		toolkit.adapt(ttype, true, true);
		
		// TODD: Change to a drop down selection
//		Text text = toolkit.createText(sectionClient, "");
//		text.setLayoutData(new GridData(SWT.FILL,SWT.CENTER, true,false));
//		text.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_YELLOW));
		
		section.setClient(sectionClient);
		return section;
	}
	public Object getPageMemento()
	{
		return m_touchpointBodyBlock.getPageMemento();
	}

	public void setPageMemento(Object memento)
	{
		m_touchpointBodyBlock.setPageMemento(memento);
	}

}
