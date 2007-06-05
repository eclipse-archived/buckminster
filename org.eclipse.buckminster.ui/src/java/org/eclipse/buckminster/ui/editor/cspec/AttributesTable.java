/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.ui.editor.cspec;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.buckminster.core.common.model.Documentation;
import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.cspec.builder.AttributeBuilder;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.ui.UiUtils;
import org.eclipse.buckminster.ui.editor.EditorUtils;
import org.eclipse.buckminster.ui.general.editor.ValidatorException;
import org.eclipse.buckminster.ui.general.editor.simple.SimpleTableEditor;
import org.eclipse.buckminster.ui.general.editor.structured.StructuredTable;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

/**
 * @author Karel Brezina
 *
 */
public abstract class AttributesTable<T extends AttributeBuilder> extends StructuredTable<T>
{
	private CSpecBuilder m_cspec;
	
	private Text m_nameText;
	private Button m_publicCheck;
	private List<Property> m_installerHints = new ArrayList<Property>();
	private SimpleTableEditor<Property> m_installerHintsEditor;
	private Text m_documentationText;

	public AttributesTable(List<T> data, CSpecBuilder cspec)
	{
		super(data);
		m_cspec = cspec;
	}

	protected CSpecBuilder getCSpecBuilder()
	{
		return m_cspec;
	}
	
	protected Text getNameText()
	{
		return m_nameText;
	}
	
	protected void setNameText(Text nameText)
	{
		m_nameText = nameText;
	}
	
	protected void setPublicCheck(Button publicCheck)
	{
		m_publicCheck = publicCheck;
	}
	
	@Override
	protected void setRowValues(T builder) throws ValidatorException
	{
		builder.setName(UiUtils.trimmedValue(m_nameText));	
		builder.setPublic(m_publicCheck.getSelection());
		
		ExpandingProperties hints = builder.getInstallerHints();
		
		if(hints != null)
		{
			hints.clear();
		}
		for(Property property : m_installerHints)
		{
			builder.addInstallerHint(property.getKey(), property.getValue());
		}
		
		
		String doc = UiUtils.trimmedValue(m_documentationText);
		
		try
		{
			builder.setDocumentation(doc == null ? null : Documentation.parse(doc));
		}
		catch(Exception e)
		{
			throw new ValidatorException(e.getMessage());
		}
	}

	public void enableFields(boolean enabled)
	{
		m_nameText.setEnabled(enabled);
		m_publicCheck.setEnabled(enabled);
		m_installerHintsEditor.setEnabled(enabled);
		m_documentationText.setEnabled(enabled);
	}

	protected Control createInstallerHintsStackLayer(Composite stackComposite)
	{
		Composite ihComposite = new Composite(stackComposite, SWT.NONE);
		GridLayout layout = new GridLayout(1, false);
		layout.marginHeight = layout.marginWidth = 0;
		ihComposite.setLayout(layout);

		EditorUtils.createHeaderLabel(ihComposite, "Installer Hints", 1);

		PropertiesTable ihTable = new PropertiesTable(m_installerHints);
		
		m_installerHintsEditor = new SimpleTableEditor<Property>(
				ihComposite,
				ihTable,
				null,
				"Attribute - Installer Hint",
				null,
				null,
				SWT.NONE);

		return ihComposite;
	}
	
	protected Control createDocumentationStackLayer(Composite stackComposite)
	{
		Composite docComposite = new Composite(stackComposite, SWT.NONE);
		GridLayout layout = new GridLayout(1, false);
		layout.marginHeight = layout.marginWidth = 0;
		docComposite.setLayout(layout);

		EditorUtils.createHeaderLabel(docComposite, "Documentation", 1);

		m_documentationText = UiUtils.createGridText(docComposite, 1, 0, null, SWT.MULTI);
		m_documentationText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		docComposite.setData("focusControl", m_documentationText);

		return docComposite;
	}
	
	public String[] getTableViewerColumnHeaders()
	{
		return new String[] {"Name", "Public"};
	}

	public int[] getTableViewerColumnWeights()
	{
		return new int[] {80, 20};
	}

	public Object getTableViewerField(T builder, int columnIndex)
	{
		switch(columnIndex)
		{
		case 0:
			return builder.getName();
		case 1:
			return Boolean.valueOf(builder.isPublic());
		default:
			return null;
		}
	}

	@Override
	protected void refreshRow(T builder)
	{
		m_nameText.setText(TextUtils.notNullString(builder.getName()));
		m_publicCheck.setSelection(builder.isPublic());
		
		CSpecEditorUtils.copyAndSortItems(builder.getInstallerHints(), m_installerHints);
		m_installerHintsEditor.refresh();
			
		Documentation doc = builder.getDocumentation();
		m_documentationText.setText(TextUtils.notNullString(doc == null ? null : doc.toString()));
	}

}
