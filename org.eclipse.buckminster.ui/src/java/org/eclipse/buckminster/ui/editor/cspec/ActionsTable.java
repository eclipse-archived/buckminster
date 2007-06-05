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
import java.util.Set;

import org.eclipse.buckminster.core.common.model.Documentation;
import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.cspec.builder.ActionBuilder;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.builder.PrerequisiteBuilder;
import org.eclipse.buckminster.core.cspec.builder.PrerequisitesBuilder;
import org.eclipse.buckminster.core.cspec.model.PrerequisiteAlreadyDefinedException;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.ui.UiUtils;
import org.eclipse.buckminster.ui.editor.EditorUtils;
import org.eclipse.buckminster.ui.general.editor.ValidatorException;
import org.eclipse.buckminster.ui.general.editor.simple.SimpleTableEditor;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
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
public class ActionsTable extends AttributesTable<ActionBuilder>
{
	private Text m_actorNameText;
	private Button m_alwaysCheck;
	private Button m_assignConsoleSupportCheck;
	private Button m_enabledCheck;
	private Text m_prodAliasText;
	private Text m_prodBaseText;
	
	private List<Property> m_actorProperties = new ArrayList<Property>();
	private SimpleTableEditor<Property> m_actorPropertiesEditor;
	private List<IPath> m_productPaths = new ArrayList<IPath>();
	private SimpleTableEditor<IPath> m_productPathsEditor;
	private List<Property> m_properties = new ArrayList<Property>();
	private SimpleTableEditor<Property> m_propertiesEditor;
	
	private Text m_prereqNameText;
	private Button m_prereqPublicCheck;
	private List<Property> m_prereqInstallerHints = new ArrayList<Property>();
	private SimpleTableEditor<Property> m_prereqInstallerHintsEditor;
	private Text m_prereqDocumentationText;
	private Text m_prereqRebasePathText;
	private List<PrerequisiteBuilder> m_prerequisites = new ArrayList<PrerequisiteBuilder>();
	private SimpleTableEditor<PrerequisiteBuilder> m_prerequisitesEditor;

	public ActionsTable(List<ActionBuilder> data, CSpecBuilder cspec)
	{
		super(data, cspec);
	}

	@Override
	protected ActionBuilder createNewRow()
	{
		return getCSpecBuilder().createActionBuilder();
	}

	@Override
	public void fillStack(Composite stackComposite)
	{
		addStackMapping("General", createGeneralStackLayer(stackComposite));
		addStackMapping("Actor Properties", createActorPropertiesStackLayer(stackComposite));
		addStackMapping("Product Paths", createProductPathsStackLayer(stackComposite));
		addStackMapping("Properties", createPropertiesStackLayer(stackComposite));
		addStackMapping("Hints", createInstallerHintsStackLayer(stackComposite));
		addStackMapping("Documentation", createDocumentationStackLayer(stackComposite));
		addStackMapping("Prerequisites - Main", createPrereqGeneralStackLayer(stackComposite));
		addStackMapping("Prerequisites - List", createPrereqListStackLayer(stackComposite));
		addStackMapping("Prerequisites - Hints", createPrereqInstallerHintsStackLayer(stackComposite));
		addStackMapping("Prerequisites - Doc", createPrereqDocumentationStackLayer(stackComposite));
	}

	private Control createGeneralStackLayer(Composite stackComposite)
	{
		Composite geComposite = new Composite(stackComposite, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		layout.marginHeight = layout.marginWidth = 0;
		geComposite.setLayout(layout);

		EditorUtils.createHeaderLabel(geComposite, "General", 2);

		UiUtils.createGridLabel(geComposite, "Name:", 1, 0, SWT.NONE);
		setNameText(UiUtils.createGridText(geComposite, 1, 0, null, SWT.NONE));

		UiUtils.createGridLabel(geComposite, "Public:", 1, 0, SWT.NONE);
		setPublicCheck(UiUtils.createCheckButton(geComposite, null, null));

		UiUtils.createGridLabel(geComposite, "Actor Name:", 1, 0, SWT.NONE);
		m_actorNameText = UiUtils.createGridText(geComposite, 1, 0, null, SWT.NONE);

		UiUtils.createGridLabel(geComposite, "Always:", 1, 0, SWT.NONE);
		m_alwaysCheck = UiUtils.createCheckButton(geComposite, null, null);

		UiUtils.createGridLabel(geComposite, "Assign Console Support:", 1, 0, SWT.NONE);
		m_assignConsoleSupportCheck = UiUtils.createCheckButton(geComposite, null, null);

		UiUtils.createGridLabel(geComposite, "Enabled:", 1, 0, SWT.NONE);
		m_enabledCheck = UiUtils.createCheckButton(geComposite, null, null);

		UiUtils.createGridLabel(geComposite, "Product Alias:", 1, 0, SWT.NONE);
		m_prodAliasText = UiUtils.createGridText(geComposite, 1, 0, null, SWT.NONE);

		UiUtils.createGridLabel(geComposite, "Product Base Path:", 1, 0, SWT.NONE);
		m_prodBaseText = UiUtils.createGridText(geComposite, 1, 0, null, SWT.NONE);

		geComposite.setData("focusControl", getNameText());
		
		return geComposite;
	}

	private Control createActorPropertiesStackLayer(Composite stackComposite)
	{
		Composite composite = new Composite(stackComposite, SWT.NONE);
		GridLayout layout = new GridLayout(1, false);
		layout.marginHeight = layout.marginWidth = 0;
		composite.setLayout(layout);

		EditorUtils.createHeaderLabel(composite, "Actor Properties", 1);

		PropertiesTable table = new PropertiesTable(m_actorProperties);
		
		m_actorPropertiesEditor = new SimpleTableEditor<Property>(
				composite,
				table,
				null,
				"Action - Actor Properties",
				null,
				null,
				SWT.NONE);

		return composite;
	}
	
	private Control createProductPathsStackLayer(Composite stackComposite)
	{
		Composite composite = new Composite(stackComposite, SWT.NONE);
		GridLayout layout = new GridLayout(1, false);
		layout.marginHeight = layout.marginWidth = 0;
		composite.setLayout(layout);

		EditorUtils.createHeaderLabel(composite, "Product Paths", 1);

		PathsTable table = new PathsTable(m_productPaths);
		
		m_productPathsEditor = new SimpleTableEditor<IPath>(
				composite,
				table,
				null,
				"Action - Product Path",
				null,
				null,
				SWT.NONE);
		
		return composite;
	}
	
	private Control createPropertiesStackLayer(Composite stackComposite)
	{
		Composite composite = new Composite(stackComposite, SWT.NONE);
		GridLayout layout = new GridLayout(1, false);
		layout.marginHeight = layout.marginWidth = 0;
		composite.setLayout(layout);

		EditorUtils.createHeaderLabel(composite, "Properties", 1);

		PropertiesTable table = new PropertiesTable(m_properties);
		
		m_propertiesEditor = new SimpleTableEditor<Property>(
				composite,
				table,
				null,
				"Action - Properties",
				null,
				null,
				SWT.NONE);

		return composite;
	}
	
	private Control createPrereqGeneralStackLayer(Composite stackComposite)
	{
		Composite geComposite = new Composite(stackComposite, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		layout.marginHeight = layout.marginWidth = 0;
		geComposite.setLayout(layout);

		EditorUtils.createHeaderLabel(geComposite, "Prerequisites - Main", 2);

		UiUtils.createGridLabel(geComposite, "Name:", 1, 0, SWT.NONE);

		m_prereqNameText = UiUtils.createGridText(geComposite, 1, 0, null, SWT.NONE);

		UiUtils.createGridLabel(geComposite, "Public:", 1, 0, SWT.NONE);

		m_prereqPublicCheck = UiUtils.createCheckButton(geComposite, null, null);

		UiUtils.createGridLabel(geComposite, "Rebase Path:", 1, 0, SWT.NONE);

		m_prereqRebasePathText = UiUtils.createGridText(geComposite, 1, 0, null, SWT.NONE);

		geComposite.setData("focusControl", m_prereqNameText);
		
		return geComposite;
	}

	private Control createPrereqListStackLayer(Composite stackComposite)
	{
		Composite preComposite = new Composite(stackComposite, SWT.NONE);
		GridLayout layout = new GridLayout(1, false);
		layout.marginHeight = layout.marginWidth = 0;
		preComposite.setLayout(layout);

		EditorUtils.createHeaderLabel(preComposite, "Prerequisites - List", 1);

		// Uses an empty GroupBuilder (createNewRow())
		// "PrerequisiteBuilder"s will be created with this empty GroupBuilder
		// Need to create "PrerequisiteBuilder"s again while saving them

		PrerequisitesTable preTable = new PrerequisitesTable(m_prerequisites, createNewRow().getPrerequisitesBuilder());
		 
		m_prerequisitesEditor = new SimpleTableEditor<PrerequisiteBuilder>(
				preComposite,
				preTable,
				null,
				"Action - Prerequisite",
				null,
				null,
				SWT.NONE);

		return preComposite;
	}

	protected Control createPrereqInstallerHintsStackLayer(Composite stackComposite)
	{
		Composite ihComposite = new Composite(stackComposite, SWT.NONE);
		GridLayout layout = new GridLayout(1, false);
		layout.marginHeight = layout.marginWidth = 0;
		ihComposite.setLayout(layout);

		EditorUtils.createHeaderLabel(ihComposite, "Prerequisites - Installer Hints", 1);

		PropertiesTable ihTable = new PropertiesTable(m_prereqInstallerHints);
		
		m_prereqInstallerHintsEditor = new SimpleTableEditor<Property>(
				ihComposite,
				ihTable,
				null,
				"Prerequisite - Installer Hint",
				null,
				null,
				SWT.NONE);

		return ihComposite;
	}
	
	protected Control createPrereqDocumentationStackLayer(Composite stackComposite)
	{
		Composite docComposite = new Composite(stackComposite, SWT.NONE);
		GridLayout layout = new GridLayout(1, false);
		layout.marginHeight = layout.marginWidth = 0;
		docComposite.setLayout(layout);

		EditorUtils.createHeaderLabel(docComposite, "Prerequisites - Documentation", 1);

		m_prereqDocumentationText = UiUtils.createGridText(docComposite, 1, 0, null, SWT.MULTI);
		m_prereqDocumentationText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		docComposite.setData("focusControl", m_prereqDocumentationText);
		
		return docComposite;
	}
	
	@Override
	protected void setRowValues(ActionBuilder builder) throws ValidatorException
	{
		super.setRowValues(builder);

		builder.setActorName(UiUtils.trimmedValue(m_actorNameText));
		builder.setAlways(m_alwaysCheck.getSelection());
		builder.setAssignConsoleSupport(m_assignConsoleSupportCheck.getSelection());
		builder.setEnabled(m_enabledCheck.getSelection());
		builder.setProductAlias(UiUtils.trimmedValue(m_prodAliasText));
		
		String prodBasePathString = UiUtils.trimmedValue(m_prodBaseText);
		IPath prodBasePath = null;		
		if(prodBasePathString != null)
		{
			prodBasePath = Path.fromOSString(prodBasePathString);
		}
		builder.setProductBase(prodBasePath);

		ExpandingProperties properties = builder.getActorProperties();
		
		if(properties != null)
		{
			properties.clear();
		}
		for(Property property : m_actorProperties)
		{
			builder.addActorProperty(property.getKey(), property.getValue(), true);
		}
		
		Set<IPath> paths = builder.getProductPaths();
		
		if(paths != null)
		{
			builder.getProductPaths().clear();
		}
		for(IPath path : m_productPaths)
		{
			builder.addProductPath(path);
		}

		properties = builder.getProperties();
		
		if(properties != null)
		{
			properties.clear();
		}
		for(Property property : m_properties)
		{
			builder.addProperty(property.getKey(), property.getValue(), true);
		}
		
		PrerequisitesBuilder prereqBuilder = builder.getPrerequisitesBuilder();
		
		prereqBuilder.setName(UiUtils.trimmedValue(m_prereqNameText));	
		prereqBuilder.setPublic(m_prereqPublicCheck.getSelection());
		
		ExpandingProperties hints = prereqBuilder.getInstallerHints();
		
		if(hints != null)
		{
			hints.clear();
		}
		for(Property property : m_prereqInstallerHints)
		{
			prereqBuilder.addInstallerHint(property.getKey(), property.getValue());
		}
			
		String doc = UiUtils.trimmedValue(m_prereqDocumentationText);
		
		try
		{
			prereqBuilder.setDocumentation(doc == null ? null : Documentation.parse(doc));
		}
		catch(Exception e)
		{
			throw new ValidatorException(e.getMessage());
		}
		
		String rebasePathString = UiUtils.trimmedValue(m_prereqRebasePathText);
		IPath rebasePath = null;

		if(rebasePathString != null)
		{
			rebasePath = Path.fromOSString(rebasePathString);
		}
		prereqBuilder.setRebase(rebasePath);

		List<PrerequisiteBuilder> prerequisites = prereqBuilder.getPrerequisites();

		if(prerequisites != null)
		{
			prerequisites.clear();
		}
		for(PrerequisiteBuilder prerequisite : m_prerequisites)
		{
			// Original "prerequisite" is created with a different PrerequisitesBuilder (an empty one)
			// Need to created again and copy attributes
			PrerequisiteBuilder newPrerequisite = prereqBuilder.createPrerequisiteBuilder();
			newPrerequisite.initFrom(prerequisite.createPrerequisite());
			
			try
			{
				prereqBuilder.addPrerequisite(newPrerequisite);
			}
			catch(PrerequisiteAlreadyDefinedException e)
			{
				throw new ValidatorException(e.getMessage());
			}
		}
	}
	@Override
	protected void refreshRow(ActionBuilder builder)
	{
		super.refreshRow(builder);

		m_actorNameText.setText(TextUtils.notNullString(builder.getActorName()));
		m_alwaysCheck.setSelection(builder.isAlways());
		m_assignConsoleSupportCheck.setSelection(builder.isAssignConsoleSupport());
		m_enabledCheck.setSelection(builder.isEnabled());
		m_prodAliasText.setText(TextUtils.notNullString(builder.getProductAlias()));
		
		IPath prodBasePath = builder.getProductBase();		
		m_prodBaseText.setText(
				TextUtils.notNullString(prodBasePath == null ?
						null :
						prodBasePath.toOSString()));
		
		CSpecEditorUtils.copyAndSortItems(builder.getActorProperties(), m_actorProperties);
		m_actorPropertiesEditor.refresh();

		CSpecEditorUtils.copyAndSortItems(builder.getProductPaths(), m_productPaths, EditorUtils.getPathComparator());
		m_productPathsEditor.refresh();

		CSpecEditorUtils.copyAndSortItems(builder.getProperties(), m_properties);
		m_propertiesEditor.refresh();
	
		PrerequisitesBuilder prereqBuilder = builder.getPrerequisitesBuilder();
		
		m_prereqNameText.setText(TextUtils.notNullString(prereqBuilder.getName()));
		m_prereqPublicCheck.setSelection(prereqBuilder.isPublic());
		
		CSpecEditorUtils.copyAndSortItems(prereqBuilder.getInstallerHints(), m_prereqInstallerHints);
		m_prereqInstallerHintsEditor.refresh();
			
		Documentation doc = prereqBuilder.getDocumentation();
		m_prereqDocumentationText.setText(TextUtils.notNullString(doc == null ? null : doc.toString()));

		IPath rebasePath = prereqBuilder.getRebase();
		m_prereqRebasePathText.setText(TextUtils.notNullString(rebasePath == null
				? null
				: rebasePath.toOSString()));

		CSpecEditorUtils.copyAndSortItems(prereqBuilder.getPrerequisites(), m_prerequisites, CSpecEditorUtils.getCSpecElementComparator());
		m_prerequisitesEditor.refresh();
	}

	@Override
	public void enableFields(boolean enabled)
	{
		super.enableFields(enabled);

		m_actorNameText.setEnabled(enabled);
		m_alwaysCheck.setEnabled(enabled);
		m_assignConsoleSupportCheck.setEnabled(enabled);
		m_enabledCheck.setEnabled(enabled);
		m_prodAliasText.setEnabled(enabled);
		m_prodBaseText.setEnabled(enabled);
		m_actorPropertiesEditor.setEnabled(enabled);
		m_productPathsEditor.setEnabled(enabled);
		m_propertiesEditor.setEnabled(enabled);		
		m_prereqNameText.setEnabled(enabled);
		m_prereqPublicCheck.setEnabled(enabled);
		m_prereqInstallerHintsEditor.setEnabled(enabled);
		m_prereqDocumentationText.setEnabled(enabled);
		m_prereqRebasePathText.setEnabled(enabled);
		m_prerequisitesEditor.setEnabled(enabled);
	}
}
