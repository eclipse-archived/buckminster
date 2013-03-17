/*******************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
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
public class ActionsTable extends AttributesTable<ActionBuilder> {
	private Map<ActionBuilder, List<ActionArtifactBuilder>> actionArtifacts;

	private Text actorNameText;

	private Button alwaysCheck;

	private Button assignConsoleSupportCheck;

	private Text actionFilter;

	private Combo upToDatePolicy;

	private Text fileCountText;

	private Text additionalFileCountText;

	private Text prodAliasText;

	private Text prodBaseText;

	private List<Property> actorProperties = new ArrayList<Property>();

	private SimpleTableEditor<Property> actorPropertiesEditor;

	private Button pathsButton;

	private List<PathWrapper> productPaths = new ArrayList<PathWrapper>();

	private SimpleTableEditor<PathWrapper> productPathsEditor;

	private Button artifactsButton;

	private List<ArtifactBuilder> productArtifacts = new ArrayList<ArtifactBuilder>();

	private TwoPagesTableEditor<ArtifactBuilder> productArtifactsEditor;

	private List<Property> properties = new ArrayList<Property>();

	private SimpleTableEditor<Property> propertiesEditor;

	private Text prereqNameText;

	private Text prereqRebasePathText;

	private List<PrerequisiteBuilder> prerequisites = new ArrayList<PrerequisiteBuilder>();

	private SimpleTableEditor<PrerequisiteBuilder> prerequisitesEditor;

	public ActionsTable(CSpecEditor editor, List<ActionBuilder> data, Map<ActionBuilder, List<ActionArtifactBuilder>> actionArtifacts,
			CSpecBuilder cspec, boolean readOnly) {
		super(editor, data, cspec, readOnly);
		this.actionArtifacts = actionArtifacts;
	}

	@Override
	public void enableFields(boolean enabled) {
		super.enableFields(enabled);

		actorNameText.setEnabled(enabled);
		alwaysCheck.setEnabled(enabled);
		assignConsoleSupportCheck.setEnabled(enabled);
		actionFilter.setEnabled(enabled);
		upToDatePolicy.setEnabled(enabled);
		refreshFileCountFields();
		prodAliasText.setEnabled(enabled);
		prodBaseText.setEnabled(enabled);
		actorPropertiesEditor.setEnabled(enabled);
		pathsButton.setEnabled(enabled);
		productPathsEditor.setEnabled(enabled && pathsButton.getSelection());
		artifactsButton.setEnabled(enabled);
		productArtifactsEditor.setEnabled(enabled && artifactsButton.getSelection());
		propertiesEditor.setEnabled(enabled);
		prereqNameText.setEnabled(enabled);
		prereqRebasePathText.setEnabled(enabled);
		prerequisitesEditor.setEnabled(enabled);
	}

	@Override
	public void fillStack(Composite stackComposite) {
		addStackMapping(Messages.general, createGeneralStackLayer(stackComposite));
		addStackMapping(Messages.properties, createPropertiesStackLayer(stackComposite));
		addStackMapping(Messages.products, createProductsStackLayer(stackComposite));
		addStackMapping(Messages.documentation, createDocumentationStackLayer(stackComposite));
	}

	@Override
	protected ActionBuilder createNewRow() {
		return getCSpecBuilder().createActionBuilder();
	}

	@Override
	protected void refreshRow(ActionBuilder builder) {
		super.refreshRow(builder);

		actorNameText.setText(TextUtils.notNullString(builder.getActorName()));
		alwaysCheck.setSelection(builder.isAlways());
		assignConsoleSupportCheck.setSelection(builder.isAssignConsoleSupport());
		actionFilter.setText(TextUtils.notNullString(builder.getFilter()));
		upToDatePolicy.select(builder.getUpToDatePolicy().ordinal());
		switch (builder.getUpToDatePolicy()) {
			case COUNT:
				fileCountText.setText(String.valueOf(builder.getProductFileCount()));
				additionalFileCountText.setText(""); //$NON-NLS-1$
				break;
			case MAPPER:
				fileCountText.setText(""); //$NON-NLS-1$
				additionalFileCountText.setText(String.valueOf(builder.getProductFileCount()));
				break;
			default:
				fileCountText.setText(""); //$NON-NLS-1$
				additionalFileCountText.setText(""); //$NON-NLS-1$
		}
		refreshFileCountFields();

		prodAliasText.setText(TextUtils.notNullString(builder.getProductAlias()));

		IPath prodBasePath = builder.getProductBase();
		prodBaseText.setText(TextUtils.notNullString(prodBasePath == null ? null : prodBasePath.toOSString()));

		CSpecEditorUtils.copyAndSortItems(builder.getActorProperties(), actorProperties);
		actorPropertiesEditor.refresh();

		CSpecEditorUtils.copyAndSortItems(builder.getProductPaths(), productPaths);
		productPathsEditor.refresh();

		CSpecEditorUtils.copyAndSortItems(actionArtifacts.get(builder), productArtifacts, CSpecEditorUtils.getAttributeComparator());
		createProductArtifactsCopy();
		productArtifactsEditor.refresh();

		chooseProductPathsButton(productPaths.size() > 0 || productArtifacts.size() == 0);

		CSpecEditorUtils.copyAndSortItems(builder.getProperties(), properties);
		propertiesEditor.refresh();

		PrerequisitesBuilder prereqBuilder = builder.getPrerequisitesBuilder();

		prereqNameText.setText(TextUtils.notNullString(prereqBuilder.getName()));

		IPath rebasePath = prereqBuilder.getPrerequisiteRebase();
		prereqRebasePathText.setText(TextUtils.notNullString(rebasePath == null ? null : rebasePath.toOSString()));

		CSpecEditorUtils.copyAndSortItems(prereqBuilder.getPrerequisites(), prerequisites, CSpecEditorUtils.getPrerequisiteComparator());
		prerequisitesEditor.refresh();
	}

	@Override
	protected void setRowValues(ActionBuilder builder) throws ValidatorException {
		super.setRowValues(builder);

		builder.setActorName(UiUtils.trimmedValue(actorNameText));
		builder.setAlways(alwaysCheck.getSelection());
		builder.setAssignConsoleSupport(assignConsoleSupportCheck.getSelection());
		String filterStr = UiUtils.trimmedValue(actionFilter);
		if (filterStr != null) {
			try {
				builder.setFilter(FilterFactory.newInstance(filterStr));
			} catch (InvalidSyntaxException e) {
				throw new ValidatorException(e.getMessage());
			}
		} else
			builder.setFilter(null);

		builder.setUpToDatePolicy(UpToDatePolicy.values()[upToDatePolicy.getSelectionIndex()]);

		Text validFileCountField = null;

		switch (builder.getUpToDatePolicy()) {
			case COUNT:
				validFileCountField = fileCountText;
				break;
			case MAPPER:
				validFileCountField = additionalFileCountText;
				break;
		}
		String fileCount = UiUtils.trimmedValue(validFileCountField);
		if (fileCount != null)
			try {
				builder.setProductFileCount(Integer.valueOf(fileCount).intValue());
			} catch (NumberFormatException e) {
				throw new ValidatorException(Messages.invalid_number + ": " + fileCount); //$NON-NLS-1$
			}

		builder.setProductAlias(UiUtils.trimmedValue(prodAliasText));

		String prodBasePathString = UiUtils.trimmedValue(prodBaseText);
		IPath prodBasePath = null;
		if (prodBasePathString != null) {
			prodBasePath = Path.fromOSString(prodBasePathString);
		}
		builder.setProductBase(prodBasePath);

		ExpandingProperties<String> props = builder.getActorProperties();

		if (props != null) {
			props.clear();
		}
		for (Property property : actorProperties) {
			builder.addActorProperty(property.getKey(), property.getValue(), true);
		}

		Set<IPath> paths = builder.getProductPaths();

		if (paths != null) {
			paths.clear();
		}

		// save only if selected
		if (pathsButton.getSelection()) {
			for (PathWrapper path : productPaths) {
				IPath p = path.getPath();

				if (p == null)
					continue;

				builder.addProductPath(p);
			}
		}

		actionArtifacts.remove(builder);

		// save only if selected
		if (artifactsButton.getSelection()) {
			if (productArtifacts.size() > 0) {
				List<ActionArtifactBuilder> list = new ArrayList<ActionArtifactBuilder>();

				for (ArtifactBuilder artifactBuilder : productArtifacts) {
					((ActionArtifactBuilder) artifactBuilder).setActionName(builder.getName());
					list.add((ActionArtifactBuilder) artifactBuilder);
				}

				actionArtifacts.put(builder, list);
			}
		}

		props = builder.getProperties();

		if (props != null) {
			props.clear();
		}
		for (Property property : properties) {
			builder.addProperty(property.getKey(), property.getValue(), true);
		}

		PrerequisitesBuilder prereqBuilder = builder.getPrerequisitesBuilder();

		prereqBuilder.setName(UiUtils.trimmedValue(prereqNameText));

		String rebasePathString = UiUtils.trimmedValue(prereqRebasePathText);
		IPath rebasePath = null;

		if (rebasePathString != null) {
			rebasePath = Path.fromOSString(rebasePathString);
		}
		prereqBuilder.setPrerequisiteRebase(rebasePath);

		List<PrerequisiteBuilder> prereqs = prereqBuilder.getPrerequisites();

		if (prereqs != null) {
			prereqs.clear();
		}
		for (PrerequisiteBuilder prerequisite : prerequisites) {
			// Original "prerequisite" is created with a different
			// PrerequisitesBuilder (an empty one)
			// Need to created again and copy attributes
			PrerequisiteBuilder newPrerequisite = prereqBuilder.createPrerequisiteBuilder();
			newPrerequisite.initFrom(prerequisite.createPrerequisite());

			try {
				prereqBuilder.addPrerequisite(newPrerequisite);
			} catch (PrerequisiteAlreadyDefinedException e) {
				throw new ValidatorException(e.getMessage());
			}
		}
	}

	void showProductArtifact(ArtifactBuilder builder) {
		// find according to name
		ArtifactBuilder foundBuilder = null;
		for (ArtifactBuilder item : productArtifacts) {
			if (item.getName().equals(builder.getName())) {
				foundBuilder = item;
				break;
			}
		}

		if (foundBuilder != null) {
			productArtifactsEditor.setFocus();
			productArtifactsEditor.show(foundBuilder);
		}
	}

	private void chooseProductPathsButton(boolean choose) {
		pathsButton.setSelection(choose);
		artifactsButton.setSelection(!choose);

		if (pathsButton.getEnabled())
			enableProductPathsEditor(choose);
	}

	@SuppressWarnings("unchecked")
	private Control createGeneralStackLayer(Composite stackComposite) {
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
		actorNameText = UiUtils.createGridText(geComposite, 1, 0, isReadOnly(), SWT.NONE);
		actorNameText.addModifyListener(FIELD_LISTENER);

		UiUtils.createGridLabel(geComposite, Messages.always_with_colon, 1, 0, SWT.NONE);
		alwaysCheck = UiUtils.createCheckButton(geComposite, null, isReadOnly(), null);
		alwaysCheck.addSelectionListener(FIELD_LISTENER);

		UiUtils.createGridLabel(geComposite, Messages.assign_console_support_with_colon, 1, 0, SWT.NONE);
		assignConsoleSupportCheck = UiUtils.createCheckButton(geComposite, null, isReadOnly(), null);
		assignConsoleSupportCheck.addSelectionListener(FIELD_LISTENER);

		UiUtils.createGridLabel(geComposite, Messages.filter_with_colon, 1, 0, SWT.NONE);
		actionFilter = UiUtils.createGridText(geComposite, 1, 0, isReadOnly(), SWT.NONE);
		actionFilter.addModifyListener(FIELD_LISTENER);

		UiUtils.createGridLabel(geComposite, Messages.uptodate_policy, 1, 0, SWT.NONE);
		upToDatePolicy = UiUtils.createGridEnumCombo(geComposite, 1, 0, UpToDatePolicy.values(), isReadOnly(), null, FIELD_LISTENER, SWT.DROP_DOWN
				| SWT.READ_ONLY);
		addFieldModifyListener(new IFieldModifyListener() {
			@Override
			public void modifyField(FieldModifyEvent e) {
				if (e.getOriginalEvent() instanceof ModifyEvent && ((ModifyEvent) e.getOriginalEvent()).getSource() == upToDatePolicy)
					refreshFileCountFields();
			}
		});

		fileCountText = UiUtils.createLabeledText(geComposite, Messages.file_count, isReadOnly(), SWT.NONE);
		fileCountText.addModifyListener(FIELD_LISTENER);

		additionalFileCountText = UiUtils.createLabeledText(geComposite, Messages.additional_file_count, isReadOnly(), SWT.NONE);
		additionalFileCountText.addModifyListener(FIELD_LISTENER);

		UiUtils.createGridLabel(geComposite, Messages.prerequisites_alias_with_colon, 1, 0, SWT.NONE);
		prereqNameText = UiUtils.createGridText(geComposite, 1, 0, isReadOnly(), SWT.NONE);
		prereqNameText.addModifyListener(FIELD_LISTENER);

		UiUtils.createGridLabel(geComposite, Messages.prerequisites_rebase_path_with_colon, 1, 0, SWT.NONE);
		prereqRebasePathText = UiUtils.createGridText(geComposite, 1, 0, isReadOnly(), SWT.NONE);
		prereqRebasePathText.addModifyListener(FIELD_LISTENER);

		UiUtils.createEmptyLabel(geComposite);
		UiUtils.createEmptyLabel(geComposite);

		Label label = UiUtils.createGridLabel(geComposite, Messages.prerequisites_with_colon, 1, 0, SWT.NONE);
		GridData gridData = new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false);
		gridData.horizontalSpan = 2;
		label.setLayoutData(gridData);

		// Uses an empty GroupBuilder (createNewRow())
		// "PrerequisiteBuilder"s will be created with this empty GroupBuilder
		// Need to create "PrerequisiteBuilder"s again while saving them

		PrerequisitesTable preTable = new PrerequisitesTable(getCSpecEditor(), this, prerequisites, createNewRow().getPrerequisitesBuilder(),
				isReadOnly());
		preTable.addTableModifyListener(FIELD_LISTENER);

		prerequisitesEditor = new SimpleTableEditor<PrerequisiteBuilder>(geComposite, preTable, null, Messages.action_prerequisite_with_dash, null,
				null, SWT.NONE);

		gridData = new GridData(GridData.FILL_BOTH);
		gridData.horizontalSpan = 2;
		prerequisitesEditor.setLayoutData(gridData);

		geComposite.setData("focusControl", getNameText()); //$NON-NLS-1$

		return geComposite;
	}

	private void createProductArtifactsCopy() {
		for (int i = 0; i < productArtifacts.size(); i++) {
			ActionArtifactBuilder builder = (ActionArtifactBuilder) productArtifacts.get(i);
			ActionArtifactBuilder newBuilder = getCSpecBuilder().createActionArtifactBuilder();
			newBuilder.initFrom(builder.createAttribute());
			productArtifacts.set(i, newBuilder);
		}
	}

	@SuppressWarnings("unchecked")
	private Control createProductsStackLayer(Composite stackComposite) {
		Composite composite = new Composite(stackComposite, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		layout.marginHeight = layout.marginWidth = 0;
		composite.setLayout(layout);

		EditorUtils.createHeaderLabel(composite, Messages.products, 2);

		UiUtils.createGridLabel(composite, Messages.product_alias_with_colon, 1, 0, SWT.NONE);
		prodAliasText = UiUtils.createGridText(composite, 1, 0, isReadOnly(), SWT.NONE);
		prodAliasText.addModifyListener(FIELD_LISTENER);

		UiUtils.createGridLabel(composite, Messages.product_base_path_with_colon, 1, 0, SWT.NONE);
		prodBaseText = UiUtils.createGridText(composite, 1, 0, isReadOnly(), SWT.NONE);
		prodBaseText.addModifyListener(FIELD_LISTENER);

		UiUtils.createEmptyLabel(composite);
		UiUtils.createEmptyLabel(composite);

		pathsButton = new Button(composite, SWT.RADIO);
		pathsButton.setText(Messages.product_paths_with_colon);
		GridData gridData = new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false);
		gridData.horizontalSpan = 2;
		pathsButton.setLayoutData(gridData);
		pathsButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				enableProductPathsEditor(true);
			}
		});

		PathsTable table = new PathsTable(productPaths, isReadOnly());
		table.addTableModifyListener(FIELD_LISTENER);

		productPathsEditor = new SimpleTableEditor<PathWrapper>(composite, table, null, Messages.action_product_path_with_dash, null, null, SWT.NONE);

		gridData = new GridData(GridData.FILL_BOTH);
		gridData.horizontalSpan = 2;
		productPathsEditor.setLayoutData(gridData);

		UiUtils.createEmptyLabel(composite);
		UiUtils.createEmptyLabel(composite);

		artifactsButton = new Button(composite, SWT.RADIO);
		artifactsButton.setText(Messages.product_artifacts);
		gridData = new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false);
		gridData.horizontalSpan = 2;
		artifactsButton.setLayoutData(gridData);
		artifactsButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				enableProductPathsEditor(false);
			}
		});

		ArtifactsTable artifactsTable = new ArtifactsTable(getCSpecEditor(), productArtifacts, getCSpecBuilder(), isReadOnly()) {
			@Override
			protected ArtifactBuilder createNewRow() {
				return getCSpecBuilder().createActionArtifactBuilder();
			}
		};
		artifactsTable.addTableModifyListener(FIELD_LISTENER);

		productArtifactsEditor = new TwoPagesTableEditor<ArtifactBuilder>(composite, artifactsTable, false, null,
				Messages.action_product_artifact_with_dash, null, null, SWT.NONE);
		gridData = new GridData(GridData.FILL_BOTH);
		gridData.horizontalSpan = 2;
		productArtifactsEditor.setLayoutData(gridData);

		composite.setData("focusControl", prodAliasText); //$NON-NLS-1$

		return composite;
	}

	@SuppressWarnings("unchecked")
	private Control createPropertiesStackLayer(Composite stackComposite) {
		Composite composite = new Composite(stackComposite, SWT.NONE);
		GridLayout layout = new GridLayout(1, false);
		layout.marginHeight = layout.marginWidth = 0;
		composite.setLayout(layout);

		EditorUtils.createHeaderLabel(composite, Messages.general_properties, 1);

		PropertiesTable table = new PropertiesTable(properties, isReadOnly());
		table.addTableModifyListener(FIELD_LISTENER);

		propertiesEditor = new SimpleTableEditor<Property>(composite, table, null, Messages.action_properties_with_dash, null, null, SWT.NONE);

		UiUtils.createEmptyLabel(composite);

		EditorUtils.createHeaderLabel(composite, Messages.actor_properties, 1);

		table = new PropertiesTable(actorProperties, isReadOnly());
		table.addTableModifyListener(FIELD_LISTENER);

		actorPropertiesEditor = new SimpleTableEditor<Property>(composite, table, null, Messages.action_actor_properties_with_dash, null, null,
				SWT.NONE);

		return composite;
	}

	private void enableProductPathsEditor(boolean enable) {
		productPathsEditor.setEnabled(enable);
		productArtifactsEditor.setEnabled(!enable);
	}

	private void refreshFileCountFields() {
		boolean fileCountEnabled = false;
		boolean additionalFileCountEnabled = false;

		if (upToDatePolicy.isEnabled()) {
			switch (UpToDatePolicy.values()[upToDatePolicy.getSelectionIndex()]) {
				case COUNT:
					fileCountEnabled = true;
					break;
				case MAPPER:
					additionalFileCountEnabled = true;
					break;
			}
		}

		fileCountText.setEnabled(fileCountEnabled);
		additionalFileCountText.setEnabled(additionalFileCountEnabled);
	}
}
