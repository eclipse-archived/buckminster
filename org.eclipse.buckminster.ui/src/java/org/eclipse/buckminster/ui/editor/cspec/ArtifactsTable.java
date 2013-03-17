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
import java.util.Set;

import org.eclipse.buckminster.core.cspec.builder.ArtifactBuilder;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.model.PathAlreadyDefinedException;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.ui.Messages;
import org.eclipse.buckminster.ui.UiUtils;
import org.eclipse.buckminster.ui.editor.EditorUtils;
import org.eclipse.buckminster.ui.general.editor.ValidatorException;
import org.eclipse.buckminster.ui.general.editor.simple.SimpleTableEditor;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author Karel Brezina
 * 
 */
public class ArtifactsTable extends AttributesTable<ArtifactBuilder> {
	private Text basePathText;

	private List<PathWrapper> paths = new ArrayList<PathWrapper>();

	private SimpleTableEditor<PathWrapper> pathsEditor;

	public ArtifactsTable(CSpecEditor editor, List<ArtifactBuilder> data, CSpecBuilder cspec, boolean readOnly) {
		super(editor, data, cspec, readOnly);
	}

	@Override
	public void enableFields(boolean enabled) {
		super.enableFields(enabled);

		basePathText.setEnabled(enabled);
		pathsEditor.setEnabled(enabled);
	}

	@Override
	public void fillStack(Composite stackComposite) {
		addStackMapping(Messages.general, createGeneralStackLayer(stackComposite));
		addStackMapping(Messages.documentation, createDocumentationStackLayer(stackComposite));
	}

	@Override
	protected ArtifactBuilder createNewRow() {
		return getCSpecBuilder().createArtifactBuilder();
	}

	@Override
	protected void refreshRow(ArtifactBuilder builder) {
		super.refreshRow(builder);

		IPath basePath = builder.getBase();
		basePathText.setText(TextUtils.notNullString(basePath == null ? null : basePath.toOSString()));

		CSpecEditorUtils.copyAndSortItems(builder.getPaths(), paths);
		pathsEditor.refresh();
	}

	@Override
	protected void setRowValues(ArtifactBuilder builder) throws ValidatorException {
		super.setRowValues(builder);

		String basePathString = UiUtils.trimmedValue(basePathText);
		IPath basePath = null;

		if (basePathString != null) {
			basePath = Path.fromOSString(basePathString);
		}
		builder.setBase(basePath);

		Set<IPath> pathSet = builder.getPaths();

		if (pathSet != null) {
			builder.getPaths().clear();
		}
		for (PathWrapper path : paths) {
			IPath p = path.getPath();

			if (p == null)
				continue;

			try {
				builder.addPath(p);
			} catch (PathAlreadyDefinedException e) {
				throw new ValidatorException(e.getMessage());
			}
		}
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

		UiUtils.createGridLabel(geComposite, Messages.base_path_with_colon, 1, 0, SWT.NONE);

		basePathText = UiUtils.createGridText(geComposite, 1, 0, isReadOnly(), SWT.NONE);
		basePathText.addModifyListener(FIELD_LISTENER);

		UiUtils.createEmptyLabel(geComposite);
		UiUtils.createEmptyLabel(geComposite);

		Label label = UiUtils.createGridLabel(geComposite, Messages.paths_with_colon, 1, 0, SWT.NONE);
		GridData gridData = new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false);
		gridData.horizontalSpan = 2;
		label.setLayoutData(gridData);

		PathsTable phTable = new PathsTable(paths, isReadOnly());
		phTable.addTableModifyListener(FIELD_LISTENER);

		pathsEditor = new SimpleTableEditor<PathWrapper>(geComposite, phTable, null, Messages.artifact_path_with_dash, null, null, SWT.NONE);

		gridData = new GridData(GridData.FILL_BOTH);
		gridData.horizontalSpan = 2;
		pathsEditor.setLayoutData(gridData);

		geComposite.setData("focusControl", getNameText()); //$NON-NLS-1$

		return geComposite;
	}
}
