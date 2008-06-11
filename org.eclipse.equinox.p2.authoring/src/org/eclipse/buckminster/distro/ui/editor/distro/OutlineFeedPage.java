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

import java.net.URI;

import org.eclipse.equinox.p2.authoring.forms.IMutator;
import org.eclipse.equinox.p2.authoring.forms.Mutator;
import org.eclipse.equinx.p2.authoring.forms.validators.NullValidator;
import org.eclipse.equinx.p2.authoring.forms.validators.RequiredValidator;
import org.eclipse.equinx.p2.authoring.forms.validators.URIEditValidator;
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

@SuppressWarnings("unused")
public class OutlineFeedPage extends AbstractOutlineDetailsPage
{
	private Text m_labelText; // needed as member to set focus on it
	
	public OutlineFeedPage()
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
		
		TableWrapData td = new TableWrapData(TableWrapData.FILL_GRAB, TableWrapData.TOP);
		td.colspan = 1;
		section.setLayoutData(td);
		section.setText("Edit Feed");

		Composite sectionClient = toolkit.createComposite(section);
		
		GridLayout layout = new GridLayout(2, false);
		sectionClient.setLayout(layout);

		FormColors colors = toolkit.getColors();
		Color headerColor = colors.getColor("org.eclipse.ui.forms.TITLE");

		// -- LABEL TEXT
		Label label = toolkit.createLabel(sectionClient, "Label:");
		label.setForeground(headerColor);
		m_labelText = toolkit.createText(sectionClient, "");
		m_labelText.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
		m_editAdapters.createEditAdapter("label", m_labelText,
				NullValidator.instance(),
				new Mutator(){

					@Override
					public String getValue()
					{
						return m_input != null && m_input.getText() != null ? m_input.getText() : "";
					}

					@Override
					public void setValue(String input) throws Exception
					{
						m_input.setText(m_labelText.getText());
					}
		});
		
		// -- FEED URL
		label = toolkit.createLabel(sectionClient, "Feed URL:");
		label.setForeground(headerColor);
		final Text feedText = toolkit.createText(sectionClient, "");
		feedText.setLayoutData(new GridData(SWT.FILL,SWT.CENTER, true,false));
		m_editAdapters.createEditAdapter("feed",feedText,
				new RequiredValidator(URIEditValidator.instance()),
				new Mutator(){

					@Override
					public String getValue()
					{
						return m_input != null && m_input.getXmlUrl() != null
						? m_input.getXmlUrl().toString() : "";
					}

					@Override
					public void setValue(String input) throws Exception
					{
						m_input.setXmlUrl(new URI(feedText.getText()));
					}
		});

		// -- HTML URL
		label = toolkit.createLabel(sectionClient, "HTML URL:");
		label.setForeground(headerColor);
		final Text htmlText = toolkit.createText(sectionClient, "");
		htmlText.setLayoutData(new GridData(SWT.FILL,SWT.CENTER, true,false));
		m_editAdapters.createEditAdapter("html", htmlText,
				new RequiredValidator(URIEditValidator.instance(), false),
				new Mutator(){

					@Override
					public String getValue()
					{
						return m_input != null && m_input.getHtmlUrl() != null
						? m_input.getXmlUrl().toString() : "";
					}

					@Override
					public void setValue(String input) throws Exception
					{
						String val = htmlText.getText();
						m_input.setHtmlUrl(val == null || val.length()< 1 ? null :
							new URI(htmlText.getText()));
					}
		});

		// -- DESCRIPTION
		label = toolkit.createLabel(sectionClient, "Description:");
		label.setForeground(headerColor);
		GridData gd = new GridData(SWT.FILL,SWT.CENTER, true,false);
		gd.horizontalSpan = 2;
		label.setLayoutData(gd);
		
		final Text descText = toolkit.createText(sectionClient, "");
		gd = new GridData(SWT.FILL,SWT.CENTER, true,false);
		gd.horizontalSpan = 2;
		descText.setLayoutData(gd);
		m_editAdapters.createEditAdapter("desc", descText,
				NullValidator.instance(),
				new Mutator(){

					@Override
					public String getValue()
					{
						return m_input != null && m_input.getText() != null ? m_input.getText() : "";
					}

					@Override
					public void setValue(String input) throws Exception
					{
						m_input.setText(descText.getText());
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
