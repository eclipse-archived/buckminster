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
public class ArtifactsTable extends AttributesTable<ArtifactBuilder>
{
	private Text m_basePathText;
	private Text m_typeText;
	private List<PathWrapper> m_paths = new ArrayList<PathWrapper>();
	private SimpleTableEditor<PathWrapper> m_pathsEditor;
	
	public ArtifactsTable(CSpecEditor editor, List<ArtifactBuilder> data, CSpecBuilder cspec)
	{
		super(editor, data, cspec);
	}

	@Override
	protected ArtifactBuilder createNewRow()
	{
		return getCSpecBuilder().createArtifactBuilder();
	}
	
	@Override
	protected void setRowValues(ArtifactBuilder builder) throws ValidatorException
	{
		super.setRowValues(builder);
		
		String basePathString = UiUtils.trimmedValue(m_basePathText);
		IPath basePath = null;
		
		if(basePathString != null)
		{
			basePath = Path.fromOSString(basePathString);
		}
		builder.setBase(basePath);
		
		builder.setType(UiUtils.trimmedValue(m_typeText));
		
		Set<IPath> paths = builder.getPaths();
		
		if(paths != null)
		{
			builder.getPaths().clear();
		}
		for(PathWrapper path : m_paths)
		{
			IPath p = path.getPath();
			
			if(p == null)
				continue;
			
			try
			{
				builder.addPath(p);
			}
			catch(PathAlreadyDefinedException e)
			{
				throw new ValidatorException(e.getMessage());
			}
		}
	}
	
	@Override
	public void fillStack(Composite stackComposite)
	{
		addStackMapping(Messages.general, createGeneralStackLayer(stackComposite));
		addStackMapping(Messages.installer_hints, createInstallerHintsStackLayer(stackComposite));
		addStackMapping(Messages.documentation, createDocumentationStackLayer(stackComposite));
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

		setNameText(UiUtils.createGridText(geComposite, 1, 0, SWT.NONE));

		UiUtils.createGridLabel(geComposite, Messages.public_with_colon, 1, 0, SWT.NONE);

		setPublicCheck(UiUtils.createCheckButton(geComposite, null, null));
		
		UiUtils.createGridLabel(geComposite, Messages.base_path_with_colon, 1, 0, SWT.NONE);

		m_basePathText = UiUtils.createGridText(geComposite, 1, 0, SWT.NONE);
		m_basePathText.addModifyListener(FIELD_LISTENER);
		
		UiUtils.createGridLabel(geComposite, Messages.type_with_colon, 1, 0, SWT.NONE);

		m_typeText = UiUtils.createGridText(geComposite, 1, 0, SWT.NONE);
		m_typeText.addModifyListener(FIELD_LISTENER);
		
		UiUtils.createEmptyLabel(geComposite);
		UiUtils.createEmptyLabel(geComposite);
		
		Label label = UiUtils.createGridLabel(geComposite, Messages.paths_with_colon, 1, 0, SWT.NONE);
		GridData gridData = new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false);
		gridData.horizontalSpan = 2;
		label.setLayoutData(gridData);

		PathsTable phTable = new PathsTable(m_paths);
		phTable.addTableModifyListener(FIELD_LISTENER);
		
		m_pathsEditor = new SimpleTableEditor<PathWrapper>(
				geComposite,
				phTable,
				null,
				Messages.artifact_path_with_dash,
				null,
				null,
				SWT.NONE);
		
		gridData = new GridData(GridData.FILL_BOTH);
		gridData.horizontalSpan = 2;
		m_pathsEditor.setLayoutData(gridData);

		geComposite.setData("focusControl", getNameText()); //$NON-NLS-1$

		return geComposite;
	}
	
	@Override
	protected void refreshRow(ArtifactBuilder builder)
	{
		super.refreshRow(builder);
		
		IPath basePath = builder.getBase();
		m_basePathText.setText(TextUtils.notNullString(basePath ==  null ? null : basePath.toOSString()));
		m_typeText.setText(TextUtils.notNullString(builder.getType()));
		
		CSpecEditorUtils.copyAndSortItems(builder.getPaths(), m_paths);
		m_pathsEditor.refresh();
	}

	@Override
	public void enableFields(boolean enabled)
	{
		super.enableFields(enabled);
		
		m_basePathText.setEnabled(enabled);
		m_typeText.setEnabled(enabled);
		m_pathsEditor.setEnabled(enabled);
	}
}
