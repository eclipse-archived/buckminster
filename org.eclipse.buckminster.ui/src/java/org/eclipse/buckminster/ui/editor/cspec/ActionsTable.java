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
import java.util.Map;
import java.util.Set;

import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.cspec.builder.ActionArtifactBuilder;
import org.eclipse.buckminster.core.cspec.builder.ActionBuilder;
import org.eclipse.buckminster.core.cspec.builder.ArtifactBuilder;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.builder.PrerequisiteBuilder;
import org.eclipse.buckminster.core.cspec.builder.PrerequisitesBuilder;
import org.eclipse.buckminster.core.cspec.model.PrerequisiteAlreadyDefinedException;
import org.eclipse.buckminster.core.cspec.model.UpToDatePolicy;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.osgi.filter.FilterFactory;
import org.eclipse.buckminster.ui.Messages;
import org.eclipse.buckminster.ui.UiUtils;
import org.eclipse.buckminster.ui.editor.EditorUtils;
import org.eclipse.buckminster.ui.general.editor.ValidatorException;
import org.eclipse.buckminster.ui.general.editor.simple.SimpleTableEditor;
import org.eclipse.buckminster.ui.general.editor.structured.FieldModifyEvent;
import org.eclipse.buckminster.ui.general.editor.structured.IFieldModifyListener;
import org.eclipse.buckminster.ui.general.editor.structured.TwoPagesTableEditor;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.osgi.framework.InvalidSyntaxException;

/**
 * @author Karel Brezina
 * 
 */
public class ActionsTable extends AttributesTable<ActionBuilder>
{
	private Map<ActionBuilder, List<ActionArtifactBuilder>> m_actionArtifacts;

	private Text m_actorNameText;

	private Button m_alwaysCheck;

	private Button m_assignConsoleSupportCheck;

	private Text m_actionFilter;

	private Combo m_upToDatePolicy;

	private Text m_fileCountText;

	private Text m_additionalFileCountText;

	private Text m_prodAliasText;

	private Text m_prodBaseText;

	private List<Property> m_actorProperties = new ArrayList<Property>();

	private SimpleTableEditor<Property> m_actorPropertiesEditor;

	private Button m_pathsButton;

	private List<PathWrapper> m_productPaths = new ArrayList<PathWrapper>();

	private SimpleTableEditor<PathWrapper> m_productPathsEditor;

	private Button m_artifactsButton;

	private List<ArtifactBuilder> m_productArtifacts = new ArrayList<ArtifactBuilder>();

	private TwoPagesTableEditor<ArtifactBuilder> m_productArtifactsEditor;

	private List<Property> m_properties = new ArrayList<Property>();

	private SimpleTableEditor<Property> m_propertiesEditor;

	private Text m_prereqNameText;

	private Text m_prereqRebasePathText;

	private List<PrerequisiteBuilder> m_prerequisites = new ArrayList<PrerequisiteBuilder>();

	private SimpleTableEditor<PrerequisiteBuilder> m_prerequisitesEditor;

	public ActionsTable(CSpecEditor editor, List<ActionBuilder> data,
			Map<ActionBuilder, List<ActionArtifactBuilder>> actionArtifacts, CSpecBuilder cspec, boolean readOnly)
	{
		super(editor, data, cspec, readOnly);
		m_actionArtifacts = actionArtifacts;
	}

	@Override
	public void enableFields(boolean enabled)
	{
		super.enableFields(enabled);

		m_actorNameText.setEnabled(enabled);
		m_alwaysCheck.setEnabled(enabled);
		m_assignConsoleSupportCheck.setEnabled(enabled);
		m_actionFilter.setEnabled(enabled);
		m_upToDatePolicy.setEnabled(enabled);
		refreshFileCountFields();
		m_prodAliasText.setEnabled(enabled);
		m_prodBaseText.setEnabled(enabled);
		m_actorPropertiesEditor.setEnabled(enabled);
		m_pathsButton.setEnabled(enabled);
		m_productPathsEditor.setEnabled(enabled && m_pathsButton.getSelection());
		m_artifactsButton.setEnabled(enabled);
		m_productArtifactsEditor.setEnabled(enabled && m_artifactsButton.getSelection());
		m_propertiesEditor.setEnabled(enabled);
		m_prereqNameText.setEnabled(enabled);
		m_prereqRebasePathText.setEnabled(enabled);
		m_prerequisitesEditor.setEnabled(enabled);
	}

	@Override
	public void fillStack(Composite stackComposite)
	{
		addStackMapping(Messages.general, createGeneralStackLayer(stackComposite));
		addStackMapping(Messages.properties, createPropertiesStackLayer(stackComposite));
		addStackMapping(Messages.products, createProductsStackLayer(stackComposite));
		addStackMapping(Messages.documentation, createDocumentationStackLayer(stackComposite));
	}

	@Override
	protected ActionBuilder createNewRow()
	{
		return getCSpecBuilder().createActionBuilder();
	}

	@Override
	protected void refreshRow(ActionBuilder builder)
	{
		super.refreshRow(builder);

		m_actorNameText.setText(TextUtils.notNullString(builder.getActorName()));
		m_alwaysCheck.setSelection(builder.isAlways());
		m_assignConsoleSupportCheck.setSelection(builder.isAssignConsoleSupport());
		m_actionFilter.setText(TextUtils.notNullString(builder.getFilter()));
		m_upToDatePolicy.select(builder.getUpToDatePolicy().ordinal());
		switch(builder.getUpToDatePolicy())
		{
		case COUNT:
			m_fileCountText.setText(String.valueOf(builder.getProductFileCount()));
			m_additionalFileCountText.setText("");
			break;
		case MAPPER:
			m_fileCountText.setText("");
			m_additionalFileCountText.setText(String.valueOf(builder.getProductFileCount()));
			break;
		default:
			m_fileCountText.setText("");
			m_additionalFileCountText.setText("");
		}
		refreshFileCountFields();

		m_prodAliasText.setText(TextUtils.notNullString(builder.getProductAlias()));

		IPath prodBasePath = builder.getProductBase();
		m_prodBaseText.setText(TextUtils.notNullString(prodBasePath == null
				? null
				: prodBasePath.toOSString()));

		CSpecEditorUtils.copyAndSortItems(builder.getActorProperties(), m_actorProperties);
		m_actorPropertiesEditor.refresh();

		CSpecEditorUtils.copyAndSortItems(builder.getProductPaths(), m_productPaths);
		m_productPathsEditor.refresh();

		CSpecEditorUtils.copyAndSortItems(m_actionArtifacts.get(builder), m_productArtifacts,
				CSpecEditorUtils.getAttributeComparator());
		createProductArtifactsCopy();
		m_productArtifactsEditor.refresh();

		chooseProductPathsButton(m_productPaths.size() > 0 || m_productArtifacts.size() == 0);

		CSpecEditorUtils.copyAndSortItems(builder.getProperties(), m_properties);
		m_propertiesEditor.refresh();

		PrerequisitesBuilder prereqBuilder = builder.getPrerequisitesBuilder();

		m_prereqNameText.setText(TextUtils.notNullString(prereqBuilder.getName()));

		IPath rebasePath = prereqBuilder.getPrerequisiteRebase();
		m_prereqRebasePathText.setText(TextUtils.notNullString(rebasePath == null
				? null
				: rebasePath.toOSString()));

		CSpecEditorUtils.copyAndSortItems(prereqBuilder.getPrerequisites(), m_prerequisites,
				CSpecEditorUtils.getPrerequisiteComparator());
		m_prerequisitesEditor.refresh();
	}

	@Override
	protected void setRowValues(ActionBuilder builder) throws ValidatorException
	{
		super.setRowValues(builder);

		builder.setActorName(UiUtils.trimmedValue(m_actorNameText));
		builder.setAlways(m_alwaysCheck.getSelection());
		builder.setAssignConsoleSupport(m_assignConsoleSupportCheck.getSelection());
		String filterStr = UiUtils.trimmedValue(m_actionFilter);
		if(filterStr != null)
		{
			try
			{
				builder.setFilter(FilterFactory.newInstance(filterStr));
			}
			catch(InvalidSyntaxException e)
			{
				throw new ValidatorException(e.getMessage());
			}
		}
		else
			builder.setFilter(null);

		builder.setUpToDatePolicy(UpToDatePolicy.values()[m_upToDatePolicy.getSelectionIndex()]);

		Text validFileCountField = null;

		switch(builder.getUpToDatePolicy())
		{
		case COUNT:
			validFileCountField = m_fileCountText;
			break;
		case MAPPER:
			validFileCountField = m_additionalFileCountText;
			break;
		}
		String fileCount = UiUtils.trimmedValue(validFileCountField);
		if(fileCount != null)
			try
			{
				builder.setProductFileCount(Integer.valueOf(fileCount).intValue());
			}
			catch(NumberFormatException e)
			{
				throw new ValidatorException(Messages.invalid_number + ": " + fileCount);
			}

		builder.setProductAlias(UiUtils.trimmedValue(m_prodAliasText));

		String prodBasePathString = UiUtils.trimmedValue(m_prodBaseText);
		IPath prodBasePath = null;
		if(prodBasePathString != null)
		{
			prodBasePath = Path.fromOSString(prodBasePathString);
		}
		builder.setProductBase(prodBasePath);

		ExpandingProperties<String> properties = builder.getActorProperties();

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
			paths.clear();
		}

		// save only if selected
		if(m_pathsButton.getSelection())
		{
			for(PathWrapper path : m_productPaths)
			{
				IPath p = path.getPath();

				if(p == null)
					continue;

				builder.addProductPath(p);
			}
		}

		m_actionArtifacts.remove(builder);

		// save only if selected
		if(m_artifactsButton.getSelection())
		{
			if(m_productArtifacts.size() > 0)
			{
				List<ActionArtifactBuilder> list = new ArrayList<ActionArtifactBuilder>();

				for(ArtifactBuilder artifactBuilder : m_productArtifacts)
				{
					((ActionArtifactBuilder)artifactBuilder).setActionName(builder.getName());
					list.add((ActionArtifactBuilder)artifactBuilder);
				}

				m_actionArtifacts.put(builder, list);
			}
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

		String rebasePathString = UiUtils.trimmedValue(m_prereqRebasePathText);
		IPath rebasePath = null;

		if(rebasePathString != null)
		{
			rebasePath = Path.fromOSString(rebasePathString);
		}
		prereqBuilder.setPrerequisiteRebase(rebasePath);

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

	void showProductArtifact(ArtifactBuilder builder)
	{
		// find according to name
		ArtifactBuilder foundBuilder = null;
		for(ArtifactBuilder item : m_productArtifacts)
		{
			if(item.getName().equals(builder.getName()))
			{
				foundBuilder = item;
				break;
			}
		}

		if(foundBuilder != null)
		{
			m_productArtifactsEditor.setFocus();
			m_productArtifactsEditor.show(foundBuilder);
		}
	}

	private void chooseProductPathsButton(boolean choose)
	{
		m_pathsButton.setSelection(choose);
		m_artifactsButton.setSelection(!choose);

		if(m_pathsButton.getEnabled())
			enableProductPathsEditor(choose);
	}

	@SuppressWarnings("unchecked")
	private Control createGeneralStackLayer(Composite stackComposite)
	{
		Composite geComposite = new Composite(stackComposite, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		layout.marginHeight = layout.marginWidth = 0;
		geComposite.setLayout(layout);

		EditorUtils.createHeaderLabel(geComposite, Messages.general, 2);

		UiUtils.createGridLabel(geComposite, Messages.name_with_colon, 1, 0, SWT.NONE);
		setNameText(UiUtils.createGridText(geComposite, 1, 0, isReadOnly(), SWT.NONE));

		UiUtils.createGridLabel(geComposite, Messages.public_with_colon, 1, 0, SWT.NONE);
		setPublicCheck(UiUtils.createCheckButton(geComposite, null, isReadOnly(), null));

		UiUtils.createGridLabel(geComposite, Messages.actor_name_with_colon, 1, 0, SWT.NONE);
		m_actorNameText = UiUtils.createGridText(geComposite, 1, 0, isReadOnly(), SWT.NONE);
		m_actorNameText.addModifyListener(FIELD_LISTENER);

		UiUtils.createGridLabel(geComposite, Messages.always_with_colon, 1, 0, SWT.NONE);
		m_alwaysCheck = UiUtils.createCheckButton(geComposite, null, isReadOnly(), null);
		m_alwaysCheck.addSelectionListener(FIELD_LISTENER);

		UiUtils.createGridLabel(geComposite, Messages.assign_console_support_with_colon, 1, 0, SWT.NONE);
		m_assignConsoleSupportCheck = UiUtils.createCheckButton(geComposite, null, isReadOnly(), null);
		m_assignConsoleSupportCheck.addSelectionListener(FIELD_LISTENER);

		UiUtils.createGridLabel(geComposite, Messages.filter_with_colon, 1, 0, SWT.NONE);
		m_actionFilter = UiUtils.createGridText(geComposite, 1, 0, isReadOnly(), SWT.NONE);
		m_actionFilter.addModifyListener(FIELD_LISTENER);

		UiUtils.createGridLabel(geComposite, Messages.uptodate_policy, 1, 0, SWT.NONE);
		m_upToDatePolicy = UiUtils.createGridEnumCombo(geComposite, 1, 0, UpToDatePolicy.values(), isReadOnly(), null,
				FIELD_LISTENER, SWT.DROP_DOWN | SWT.READ_ONLY);
		addFieldModifyListener(new IFieldModifyListener()
		{
			public void modifyField(FieldModifyEvent e)
			{
				if(e.getOriginalEvent() instanceof ModifyEvent
						&& ((ModifyEvent)e.getOriginalEvent()).getSource() == m_upToDatePolicy)
					refreshFileCountFields();
			}
		});

		m_fileCountText = UiUtils.createLabeledText(geComposite, Messages.file_count, isReadOnly(), SWT.NONE);
		m_fileCountText.addModifyListener(FIELD_LISTENER);

		m_additionalFileCountText = UiUtils.createLabeledText(geComposite, Messages.additional_file_count,
				isReadOnly(), SWT.NONE);
		m_additionalFileCountText.addModifyListener(FIELD_LISTENER);

		UiUtils.createGridLabel(geComposite, Messages.prerequisites_alias_with_colon, 1, 0, SWT.NONE);
		m_prereqNameText = UiUtils.createGridText(geComposite, 1, 0, isReadOnly(), SWT.NONE);
		m_prereqNameText.addModifyListener(FIELD_LISTENER);

		UiUtils.createGridLabel(geComposite, Messages.prerequisites_rebase_path_with_colon, 1, 0, SWT.NONE);
		m_prereqRebasePathText = UiUtils.createGridText(geComposite, 1, 0, isReadOnly(), SWT.NONE);
		m_prereqRebasePathText.addModifyListener(FIELD_LISTENER);

		UiUtils.createEmptyLabel(geComposite);
		UiUtils.createEmptyLabel(geComposite);

		Label label = UiUtils.createGridLabel(geComposite, Messages.prerequisites_with_colon, 1, 0, SWT.NONE);
		GridData gridData = new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false);
		gridData.horizontalSpan = 2;
		label.setLayoutData(gridData);

		// Uses an empty GroupBuilder (createNewRow())
		// "PrerequisiteBuilder"s will be created with this empty GroupBuilder
		// Need to create "PrerequisiteBuilder"s again while saving them

		PrerequisitesTable preTable = new PrerequisitesTable(getCSpecEditor(), this, m_prerequisites,
				createNewRow().getPrerequisitesBuilder(), isReadOnly());
		preTable.addTableModifyListener(FIELD_LISTENER);

		m_prerequisitesEditor = new SimpleTableEditor<PrerequisiteBuilder>(geComposite, preTable, null,
				Messages.action_prerequisite_with_dash, null, null, SWT.NONE);

		gridData = new GridData(GridData.FILL_BOTH);
		gridData.horizontalSpan = 2;
		m_prerequisitesEditor.setLayoutData(gridData);

		geComposite.setData("focusControl", getNameText()); //$NON-NLS-1$

		return geComposite;
	}

	private void createProductArtifactsCopy()
	{
		for(int i = 0; i < m_productArtifacts.size(); i++)
		{
			ActionArtifactBuilder builder = (ActionArtifactBuilder)m_productArtifacts.get(i);
			ActionArtifactBuilder newBuilder = getCSpecBuilder().createActionArtifactBuilder();
			newBuilder.initFrom(builder.createAttribute());
			m_productArtifacts.set(i, newBuilder);
		}
	}

	@SuppressWarnings("unchecked")
	private Control createProductsStackLayer(Composite stackComposite)
	{
		Composite composite = new Composite(stackComposite, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		layout.marginHeight = layout.marginWidth = 0;
		composite.setLayout(layout);

		EditorUtils.createHeaderLabel(composite, Messages.products, 2);

		UiUtils.createGridLabel(composite, Messages.product_alias_with_colon, 1, 0, SWT.NONE);
		m_prodAliasText = UiUtils.createGridText(composite, 1, 0, isReadOnly(), SWT.NONE);
		m_prodAliasText.addModifyListener(FIELD_LISTENER);

		UiUtils.createGridLabel(composite, Messages.product_base_path_with_colon, 1, 0, SWT.NONE);
		m_prodBaseText = UiUtils.createGridText(composite, 1, 0, isReadOnly(), SWT.NONE);
		m_prodBaseText.addModifyListener(FIELD_LISTENER);

		UiUtils.createEmptyLabel(composite);
		UiUtils.createEmptyLabel(composite);

		m_pathsButton = new Button(composite, SWT.RADIO);
		m_pathsButton.setText(Messages.product_paths_with_colon);
		GridData gridData = new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false);
		gridData.horizontalSpan = 2;
		m_pathsButton.setLayoutData(gridData);
		m_pathsButton.addSelectionListener(new SelectionAdapter()
		{

			@Override
			public void widgetSelected(SelectionEvent e)
			{
				enableProductPathsEditor(true);
			}
		});

		PathsTable table = new PathsTable(m_productPaths, isReadOnly());
		table.addTableModifyListener(FIELD_LISTENER);

		m_productPathsEditor = new SimpleTableEditor<PathWrapper>(composite, table, null,
				Messages.action_product_path_with_dash, null, null, SWT.NONE);

		gridData = new GridData(GridData.FILL_BOTH);
		gridData.horizontalSpan = 2;
		m_productPathsEditor.setLayoutData(gridData);

		UiUtils.createEmptyLabel(composite);
		UiUtils.createEmptyLabel(composite);

		m_artifactsButton = new Button(composite, SWT.RADIO);
		m_artifactsButton.setText(Messages.product_artifacts);
		gridData = new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false);
		gridData.horizontalSpan = 2;
		m_artifactsButton.setLayoutData(gridData);
		m_artifactsButton.addSelectionListener(new SelectionAdapter()
		{

			@Override
			public void widgetSelected(SelectionEvent e)
			{
				enableProductPathsEditor(false);
			}
		});

		ArtifactsTable artifactsTable = new ArtifactsTable(getCSpecEditor(), m_productArtifacts, getCSpecBuilder(),
				isReadOnly())
		{
			@Override
			protected ArtifactBuilder createNewRow()
			{
				return getCSpecBuilder().createActionArtifactBuilder();
			}
		};
		artifactsTable.addTableModifyListener(FIELD_LISTENER);

		m_productArtifactsEditor = new TwoPagesTableEditor<ArtifactBuilder>(composite, artifactsTable, false, null,
				Messages.action_product_artifact_with_dash, null, null, SWT.NONE);
		gridData = new GridData(GridData.FILL_BOTH);
		gridData.horizontalSpan = 2;
		m_productArtifactsEditor.setLayoutData(gridData);

		composite.setData("focusControl", m_prodAliasText); //$NON-NLS-1$

		return composite;
	}

	@SuppressWarnings("unchecked")
	private Control createPropertiesStackLayer(Composite stackComposite)
	{
		Composite composite = new Composite(stackComposite, SWT.NONE);
		GridLayout layout = new GridLayout(1, false);
		layout.marginHeight = layout.marginWidth = 0;
		composite.setLayout(layout);

		EditorUtils.createHeaderLabel(composite, Messages.general_properties, 1);

		PropertiesTable table = new PropertiesTable(m_properties, isReadOnly());
		table.addTableModifyListener(FIELD_LISTENER);

		m_propertiesEditor = new SimpleTableEditor<Property>(composite, table, null,
				Messages.action_properties_with_dash, null, null, SWT.NONE);

		UiUtils.createEmptyLabel(composite);

		EditorUtils.createHeaderLabel(composite, Messages.actor_properties, 1);

		table = new PropertiesTable(m_actorProperties, isReadOnly());
		table.addTableModifyListener(FIELD_LISTENER);

		m_actorPropertiesEditor = new SimpleTableEditor<Property>(composite, table, null,
				Messages.action_actor_properties_with_dash, null, null, SWT.NONE);

		return composite;
	}

	private void enableProductPathsEditor(boolean enable)
	{
		m_productPathsEditor.setEnabled(enable);
		m_productArtifactsEditor.setEnabled(!enable);
	}

	private void refreshFileCountFields()
	{
		boolean fileCountEnabled = false;
		boolean additionalFileCountEnabled = false;

		if(m_upToDatePolicy.isEnabled())
		{
			switch(UpToDatePolicy.values()[m_upToDatePolicy.getSelectionIndex()])
			{
			case COUNT:
				fileCountEnabled = true;
				break;
			case MAPPER:
				additionalFileCountEnabled = true;
				break;
			}
		}

		m_fileCountText.setEnabled(fileCountEnabled);
		m_additionalFileCountText.setEnabled(additionalFileCountEnabled);
	}
}
