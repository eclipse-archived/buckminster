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

package org.eclipse.buckminster.distro.ui.editor.distro;

import org.eclipse.equinox.p2.authoring.forms.RichFormPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
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

public class FeedsPage2 extends RichFormPage
{
	public static final String PAGE_ID = "feeds.id";
	private final OPMLBodyBlock m_opmlBodyBlock;

	public FeedsPage2(FormEditor editor)
	{
		super(editor, PAGE_ID, "Distro Feeds");
		m_header = "Distro Feeds";
		m_numColumns = 1;
		TableWrapData wrapData = new TableWrapData(TableWrapData.FILL_GRAB,TableWrapData.FILL_GRAB);
		wrapData.indent = 0;

		m_opmlBodyBlock = new OPMLBodyBlock(this, wrapData);
	}

	@Override
	protected void addFormContent(IManagedForm managedForm)
	{
		final ScrolledForm scrolledForm = managedForm.getForm();
		FormToolkit toolkit = managedForm.getToolkit();
		
		Section section = createGeneralSection(toolkit, scrolledForm.getBody());
		section.addExpansionListener(getReflowListener());
		
		m_opmlBodyBlock.createContent(managedForm);
				
	}
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
		section.setText("General");
		section.setDescription("General information about the collection of feeds.");
		Composite sectionClient = toolkit.createComposite(section);
		GridLayout layout = new GridLayout(2, false);
		sectionClient.setLayout(layout);
		
		FormColors colors = toolkit.getColors();
		Label label = toolkit.createLabel(sectionClient, "Author:");
		label.setForeground(colors.getColor("org.eclipse.ui.forms.TITLE"));
		Text text = toolkit.createText(sectionClient, "");
		text.setLayoutData(new GridData(SWT.FILL,SWT.CENTER, true,false));
//		text.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_YELLOW));

		label = toolkit.createLabel(sectionClient, "Email:");
		label.setForeground(colors.getColor("org.eclipse.ui.forms.TITLE"));
		text = toolkit.createText(sectionClient, "");
		text.setLayoutData(new GridData(SWT.FILL,SWT.CENTER, true,false));
		
		section.setClient(sectionClient);
		return section;
	}
	

}
