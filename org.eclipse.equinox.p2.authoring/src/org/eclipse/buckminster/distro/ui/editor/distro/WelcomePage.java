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

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IFormColors;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormText;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Hyperlink;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

public class WelcomePage extends FormPage
{
	public static final String PAGE_ID = "welcome.id";
	
	public WelcomePage(FormEditor editor)
	{
		super(editor, PAGE_ID, "Welcome tab");
	}

	@Override
	protected void createFormContent(IManagedForm managedForm)
	{
		ScrolledForm form = managedForm.getForm();
		FormToolkit toolkit = managedForm.getToolkit();
		form.setText("Welcome to the Distro Editor");
		toolkit.decorateFormHeading(form.getForm());
		
		// form.setBackgroundImage(FormArticlePlugin.getDefault().getImage(FormArticlePlugin.IMG_FORM_BG));
		TableWrapLayout layout = new TableWrapLayout();
		layout.leftMargin = 10;
		layout.rightMargin = 10;

		form.getBody().setLayout(layout);
		TableWrapData td;
		Hyperlink link = toolkit.createHyperlink(form.getBody(),
		"This is some link", SWT.WRAP);

		link.addHyperlinkListener(new HyperlinkAdapter()
		{
			@Override
			public void linkActivated(HyperlinkEvent e)
			{
				try
				{
					Thread.sleep(1000);
				}
				catch(InterruptedException ex)
				{

				}
			}
		});

		td = new TableWrapData();
		td.align = TableWrapData.LEFT;
		link.setLayoutData(td);
		createExpandable(form, toolkit);
		createFormTextSection(form, toolkit);
	}

	private void createExpandable(final ScrolledForm form, final FormToolkit toolkit)
	{
		final ExpandableComposite exp = toolkit.createExpandableComposite(form
		.getBody(), ExpandableComposite.TREE_NODE

		// ExpandableComposite.NONE
				);

		exp.setActiveToggleColor(toolkit.getHyperlinkGroup().getActiveForeground());
		exp.setToggleColor(toolkit.getColors().getColor(IFormColors.SEPARATOR));

		Composite client = toolkit.createComposite(exp);
		exp.setClient(client);
		TableWrapLayout elayout = new TableWrapLayout();
		client.setLayout(elayout);
		elayout.leftMargin = elayout.rightMargin = 0;
		final Button button = toolkit.createButton(client, "A button", SWT.PUSH);

		button.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				// openFormWizard(button.getShell(), toolkit.getColors());
			}

		});

		exp.addExpansionListener(new ExpansionAdapter()
		{
			@Override
			public void expansionStateChanged(ExpansionEvent e)
			{
				form.reflow(true);
			}
		});

		exp.setText("A Section Title");

		TableWrapData td = new TableWrapData();

		// td.colspan = 2;

		td.align = TableWrapData.LEFT;

		// td.align = TableWrapData.FILL;

		exp.setLayoutData(td);
	}

	private void createFormTextSection(final ScrolledForm form, FormToolkit toolkit)
	{

		Section section = toolkit.createSection(
				form.getBody(),
				Section.TWISTIE | Section.DESCRIPTION);

		section.setActiveToggleColor(toolkit.getHyperlinkGroup().getActiveForeground());
		section.setToggleColor(toolkit.getColors().getColor(IFormColors.SEPARATOR));
		toolkit.createCompositeSeparator(section);
		FormText rtext = toolkit.createFormText(section, false);
		section.setClient(rtext);
		loadFormText(rtext, toolkit);
		section.addExpansionListener(new ExpansionAdapter()
		{
			@Override
			public void expansionStateChanged(ExpansionEvent e)
			{
				form.reflow(false);
			}
		});

		section.setText("Section title 2");
		section.setDescription("This is a section description");

		TableWrapData td = new TableWrapData();
		td.align = TableWrapData.FILL;
		td.grabHorizontal = true;
		section.setLayoutData(td);
	}

	private void loadFormText(final FormText rtext, FormToolkit toolkit)
	{
		rtext.addHyperlinkListener(new HyperlinkAdapter()
		{
			@Override
			public void linkActivated(HyperlinkEvent e)
			{
				MessageDialog.openInformation(rtext.getShell(), "Message title",
						"Message text" + e.getHref());
			}
		});

		rtext.setHyperlinkSettings(toolkit.getHyperlinkGroup());

		// rtext.setImage("image1",
		// FormArticlePlugin.getDefault().getImage(FormArticlePlugin.IMG_LARGE)); //$NON-NLS-1$
		//
		// InputStream is = FreeFormPage.class.getResourceAsStream("index.xml"); //$NON-NLS-1$
		//
		// if (is!=null) {
		//
		// rtext.setContents(is, true);
		//
		// try {
		//
		// is.close();
		//
		// }
		//
		// catch (IOException e) {
		//
		// }
		//
		// }

	}

}
