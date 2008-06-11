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
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.FormColors;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

/**
 * A Detail page for an OPML Outline that is a link.
 * @author Henrik Lindberg
 * 
 */

public class OutlineLinkPage extends AbstractOutlineDetailsPage
{
	private Text m_text;

	public OutlineLinkPage()
	{
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

		TableWrapData td = new TableWrapData(TableWrapData.FILL_GRAB);
		td.colspan = 1;
		section.setLayoutData(td);
		section.setText("Edit Link");
		Composite sectionClient = toolkit.createComposite(section);
		GridLayout layout = new GridLayout(2, false);
		sectionClient.setLayout(layout);

		FormColors colors = toolkit.getColors();
		Color headerColor = colors.getColor("org.eclipse.ui.forms.TITLE");

		// -- LABEL TEXT
		Label label = toolkit.createLabel(sectionClient, "Label:");
		label.setForeground(headerColor);
		m_text = toolkit.createText(sectionClient, "");
		m_text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		// TODO: UPDATE MODEL DATA ON CHANGE
		// text.addModifyListener(new ModifyListener() {
		// public void modifyText(ModifyEvent e) {
		// if (input!=null)
		// input.setText(text.getText());
		// }
		// });
		// -- HTML URL
		label = toolkit.createLabel(sectionClient, "Link URL:");
		label.setForeground(headerColor);
		Text text = toolkit.createText(sectionClient, "");
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
	}

	public void setFocus()
	{
		m_text.setFocus();
	}

	public void dispose()
	{
	}

	public boolean isStale()
	{
		return false;
	}
	
}
