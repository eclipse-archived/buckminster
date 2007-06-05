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
import org.eclipse.buckminster.ui.UiUtils;
import org.eclipse.buckminster.ui.editor.EditorUtils;
import org.eclipse.buckminster.ui.general.editor.ValidatorException;
import org.eclipse.buckminster.ui.general.editor.simple.SimpleTableEditor;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

/**
 * @author Karel Brezina
 *
 */
public class ArtifactsTable extends AttributesTable<ArtifactBuilder>
{
	private Text m_basePathText;
	private Text m_typeText;
	private List<IPath> m_paths = new ArrayList<IPath>();
	private SimpleTableEditor<IPath> m_pathsEditor;
	
	public ArtifactsTable(List<ArtifactBuilder> data, CSpecBuilder cspec)
	{
		super(data, cspec);
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
		for(IPath path : m_paths)
		{
			try
			{
				builder.addPath(path);
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
		addStackMapping("General", createGeneralStackLayer(stackComposite));
		addStackMapping("Paths", createPathsStackLayer(stackComposite));
		addStackMapping("Hints", createInstallerHintsStackLayer(stackComposite));
		addStackMapping("Documentation", createDocumentationStackLayer(stackComposite));
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
		
		UiUtils.createGridLabel(geComposite, "Base Path:", 1, 0, SWT.NONE);

		m_basePathText = UiUtils.createGridText(geComposite, 1, 0, null, SWT.NONE);
		
		UiUtils.createGridLabel(geComposite, "Type:", 1, 0, SWT.NONE);

		m_typeText = UiUtils.createGridText(geComposite, 1, 0, null, SWT.NONE);
		
		return geComposite;
	}
	
	private Control createPathsStackLayer(Composite stackComposite)
	{
		Composite pthComposite = new Composite(stackComposite, SWT.NONE);
		GridLayout layout = new GridLayout(1, false);
		layout.marginHeight = layout.marginWidth = 0;
		pthComposite.setLayout(layout);

		EditorUtils.createHeaderLabel(pthComposite, "Paths", 1);

		PathsTable phTable = new PathsTable(m_paths);
		
		m_pathsEditor = new SimpleTableEditor<IPath>(
				pthComposite,
				phTable,
				null,
				"Artifact - Path",
				null,
				null,
				SWT.NONE);
		
		return pthComposite;
	}
	
	@Override
	protected void refreshRow(ArtifactBuilder builder)
	{
		super.refreshRow(builder);
		
		IPath basePath = builder.getBase();
		m_basePathText.setText(TextUtils.notNullString(basePath ==  null ? null : basePath.toOSString()));
		m_typeText.setText(TextUtils.notNullString(builder.getType()));
		
		CSpecEditorUtils.copyAndSortItems(builder.getPaths(), m_paths, EditorUtils.getPathComparator());
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
