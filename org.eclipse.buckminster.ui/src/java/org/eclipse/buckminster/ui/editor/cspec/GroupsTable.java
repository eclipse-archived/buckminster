package org.eclipse.buckminster.ui.editor.cspec;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.builder.GroupBuilder;
import org.eclipse.buckminster.core.cspec.builder.PrerequisiteBuilder;
import org.eclipse.buckminster.core.cspec.model.PrerequisiteAlreadyDefinedException;
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

public class GroupsTable extends AttributesTable<GroupBuilder>
{
	private Text m_rebasePathText;

	private List<PrerequisiteBuilder> m_prerequisites = new ArrayList<PrerequisiteBuilder>();

	private SimpleTableEditor<PrerequisiteBuilder> m_prerequisitesEditor;

	public GroupsTable(CSpecEditor editor, List<GroupBuilder> data, CSpecBuilder cspec, boolean readOnly)
	{
		super(editor, data, cspec, readOnly);
	}

	@Override
	public void enableFields(boolean enabled)
	{
		super.enableFields(enabled);

		m_rebasePathText.setEnabled(enabled);
		m_prerequisitesEditor.setEnabled(enabled);
	}

	@Override
	public void fillStack(Composite stackComposite)
	{
		addStackMapping(Messages.general, createGeneralStackLayer(stackComposite));
		addStackMapping(Messages.documentation, createDocumentationStackLayer(stackComposite));
	}

	@Override
	protected GroupBuilder createNewRow()
	{
		return getCSpecBuilder().createGroupBuilder();
	}

	@Override
	protected void refreshRow(GroupBuilder builder)
	{
		super.refreshRow(builder);

		IPath rebasePath = builder.getPrerequisiteRebase();
		m_rebasePathText.setText(TextUtils.notNullString(rebasePath == null
				? null
				: rebasePath.toOSString()));

		CSpecEditorUtils.copyAndSortItems(builder.getPrerequisites(), m_prerequisites,
				CSpecEditorUtils.getPrerequisiteComparator());
		m_prerequisitesEditor.refresh();
	}

	@Override
	protected void setRowValues(GroupBuilder builder) throws ValidatorException
	{
		super.setRowValues(builder);

		String rebasePathString = UiUtils.trimmedValue(m_rebasePathText);
		IPath rebasePath = null;

		if(rebasePathString != null)
		{
			rebasePath = Path.fromOSString(rebasePathString);
		}
		builder.setPrerequisiteRebase(rebasePath);

		List<PrerequisiteBuilder> prerequisites = builder.getPrerequisites();

		if(prerequisites != null)
		{
			prerequisites.clear();
		}
		for(PrerequisiteBuilder prerequisite : m_prerequisites)
		{
			// Original "prerequisite" is created with a different GroupBuilder (an empty one)
			// Need to created again and copy attributes
			PrerequisiteBuilder newPrerequisite = builder.createPrerequisiteBuilder();
			newPrerequisite.initFrom(prerequisite.createPrerequisite());

			try
			{
				builder.addPrerequisite(newPrerequisite);
			}
			catch(PrerequisiteAlreadyDefinedException e)
			{
				throw new ValidatorException(e.getMessage());
			}
		}
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

		UiUtils.createGridLabel(geComposite, Messages.release_path_with_colon, 1, 0, SWT.NONE);

		m_rebasePathText = UiUtils.createGridText(geComposite, 1, 0, isReadOnly(), SWT.NONE);
		m_rebasePathText.addModifyListener(FIELD_LISTENER);

		UiUtils.createEmptyLabel(geComposite);
		UiUtils.createEmptyLabel(geComposite);

		Label label = UiUtils.createGridLabel(geComposite, Messages.prerequisites_with_colon, 1, 0, SWT.NONE);
		GridData gridData = new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false);
		gridData.horizontalSpan = 2;
		label.setLayoutData(gridData);

		// Uses an empty GroupBuilder (createNewRow())
		// "PrerequisiteBuilder"s will be created with this empty GroupBuilder
		// Need to create "PrerequisiteBuilder"s again while saving them

		GroupPrerequisitesTable preTable = new GroupPrerequisitesTable(getCSpecEditor(), this, m_prerequisites,
				createNewRow(), isReadOnly());
		preTable.addTableModifyListener(FIELD_LISTENER);

		m_prerequisitesEditor = new SimpleTableEditor<PrerequisiteBuilder>(geComposite, preTable, null,
				Messages.group_prerequisite_with_dash, null, null, SWT.NONE);

		gridData = new GridData(GridData.FILL_BOTH);
		gridData.horizontalSpan = 2;
		m_prerequisitesEditor.setLayoutData(gridData);

		geComposite.setData("focusControl", getNameText()); //$NON-NLS-1$

		return geComposite;
	}
}
