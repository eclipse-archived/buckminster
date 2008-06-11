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

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.FormColors;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.events.IExpansionListener;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

public class FeedsPage extends FormPage
{
	public static final String PAGE_ID = "feeds.id";

	public FeedsPage(FormEditor editor)
	{
		super(editor, PAGE_ID, "Distro Feeds");
	}

	@Override
	protected void createFormContent(IManagedForm managedForm)
	{
		final ScrolledForm scrolledForm = managedForm.getForm();
		FormToolkit toolkit = managedForm.getToolkit();
		scrolledForm.setText("Distro Feeds");
		toolkit.decorateFormHeading(scrolledForm.getForm());

		TableWrapLayout layout = new TableWrapLayout();
		layout.leftMargin = 10;
		layout.rightMargin = 10;
		layout.numColumns = 2;
		scrolledForm.getBody().setLayout(layout);

		IExpansionListener expListener = new ExpansionAdapter(){

			@Override
			public void expansionStateChanged(ExpansionEvent e)
			{
				scrolledForm.reflow(true);
			}
		};
		Section section = createGeneralSection(toolkit, scrolledForm.getBody());
		section.addExpansionListener(expListener);
		
		section = createFeedListSection(toolkit, scrolledForm.getBody());		
		section = createEditSelected(toolkit, scrolledForm.getBody());
		
	}
	private Section createGeneralSection(FormToolkit toolkit, Composite parent)
	{
		Section section = toolkit.createSection(parent, //
				Section.DESCRIPTION | //
				Section.TITLE_BAR | //
				Section.TWISTIE | // Expandable by user
				Section.EXPANDED);

		TableWrapData td = new TableWrapData(TableWrapData.FILL_GRAB);
		td.colspan = 2;
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
	
	private Section createFeedListSection(FormToolkit toolkit, Composite parent)
	{
		Section section = toolkit.createSection(parent, //
				Section.DESCRIPTION | //
				Section.TITLE_BAR | //
//				Section.TWISTIE | // Expandable by user
				Section.EXPANDED);

		TableWrapData td = new TableWrapData(TableWrapData.FILL);
		td.colspan = 1;
		section.setLayoutData(td);
		section.setText("Feeds");
		section.setDescription("The feeds to include in this distro.");
		
		Composite sectionClient = toolkit.createComposite(section, SWT.WRAP);
		GridLayout layout = new GridLayout(2, false);
		sectionClient.setLayout(layout);
		
		Table t = toolkit.createTable(sectionClient, SWT.NULL);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 250;
		gd.widthHint = 200;
		t.setLayoutData(gd);

		toolkit.paintBordersFor(sectionClient);

		Composite buttonBar = toolkit.createComposite(sectionClient, SWT.WRAP);
		buttonBar.setLayout(new GridLayout(1, false));
		buttonBar.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));

		Button b = toolkit.createButton(buttonBar, "Add...", SWT.PUSH);
		b.setLayoutData(new GridData(SWT.FILL, SWT.CENTER,true, false));
		configureAddButton(b);
		b = toolkit.createButton(buttonBar, "Remove", SWT.PUSH);
		b.setLayoutData(new GridData(SWT.FILL, SWT.CENTER,true, false));
		b = toolkit.createButton(buttonBar, "Up", SWT.PUSH);
		b.setLayoutData(new GridData(SWT.FILL, SWT.CENTER,true, false));
		b = toolkit.createButton(buttonBar, "Down", SWT.PUSH);
		b.setLayoutData(new GridData(SWT.FILL, SWT.CENTER,true, false));
		
		
		section.setClient(sectionClient);
		return section;
	}
	private void configureAddButton(final Button b)
	{
		Menu addMenu = new Menu(b.getShell(), SWT.POP_UP);
		MenuItem mi = new MenuItem(addMenu, SWT.PUSH);
		mi.setText("Add Feed");
		
		mi = new MenuItem(addMenu, SWT.PUSH);
		mi.setText("Add Link");

		mi = new MenuItem(addMenu, SWT.PUSH);
		mi.setText("Add Folder");
		
		b.setMenu(addMenu);
		b.addSelectionListener(new SelectionListener(){

			public void widgetDefaultSelected(SelectionEvent e)
			{
				widgetSelected(e);
			}

			public void widgetSelected(SelectionEvent e)
			{
				b.getMenu().setVisible(true);
			}
			
		});
	}
	private Section createEditSelected(FormToolkit toolkit, Composite parent)
	{
		Section section = toolkit.createSection(parent, //
				Section.DESCRIPTION | //
				Section.TITLE_BAR | //
//				Section.TWISTIE | // Expandable by user
				Section.EXPANDED);

		TableWrapData td = new TableWrapData(TableWrapData.FILL_GRAB);
		td.colspan = 1;
		section.setLayoutData(td);
		section.setText("Edit Selected");
		Composite sectionClient = toolkit.createComposite(section);
		GridLayout layout = new GridLayout(2, false);
		sectionClient.setLayout(layout);
		
		FormColors colors = toolkit.getColors();
		Color headerColor = colors.getColor("org.eclipse.ui.forms.TITLE");
		
		// -- TYPE COMBO
		Label label = toolkit.createLabel(sectionClient, "Type:");
		label.setForeground(headerColor);
		Combo typeCombo = new Combo(sectionClient, SWT.WRAP | SWT.READ_ONLY | SWT.DROP_DOWN);
		typeCombo.setItems(new String[] { "Feed", "Link", "Folder" });
		
		// -- LABEL TEXT
		label = toolkit.createLabel(sectionClient, "Label:");
		label.setForeground(headerColor);
		Text text = toolkit.createText(sectionClient, "");
		text.setLayoutData(new GridData(SWT.FILL,SWT.CENTER, true,false));

		// -- FEED URL
		label = toolkit.createLabel(sectionClient, "Feed URL:");
		label.setForeground(headerColor);
		text = toolkit.createText(sectionClient, "");
		text.setLayoutData(new GridData(SWT.FILL,SWT.CENTER, true,false));

		// -- HTML URL
		label = toolkit.createLabel(sectionClient, "HTML URL:");
		label.setForeground(headerColor);
		text = toolkit.createText(sectionClient, "");
		text.setLayoutData(new GridData(SWT.FILL,SWT.CENTER, true,false));

		// -- DESCRIPTION
		label = toolkit.createLabel(sectionClient, "Description:");
		label.setForeground(headerColor);
		GridData gd = new GridData(SWT.FILL,SWT.CENTER, true,false);
		gd.horizontalSpan = 2;
		label.setLayoutData(gd);
		
		text = toolkit.createText(sectionClient, "", SWT.MULTI);
		gd = new GridData(SWT.FILL,SWT.CENTER, true,false);
		gd.horizontalSpan = 2;
		gd.heightHint = 100;
		text.setLayoutData(gd);
	
		section.setClient(sectionClient);
		return section;
	}

}
