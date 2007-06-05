package org.eclipse.buckminster.ui.editor.cspec;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.builder.GroupBuilder;
import org.eclipse.buckminster.core.cspec.builder.PrerequisiteBuilder;
import org.eclipse.buckminster.core.cspec.model.PrerequisiteAlreadyDefinedException;
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

public class GroupsTable extends AttributesTable<GroupBuilder>
{
	public GroupsTable(List<GroupBuilder> data, CSpecBuilder cspec)
	{
		super(data, cspec);
	}

	private Text m_rebasePathText;

	private List<PrerequisiteBuilder> m_prerequisites = new ArrayList<PrerequisiteBuilder>();

	private SimpleTableEditor<PrerequisiteBuilder> m_prerequisitesEditor;

	@Override
	protected GroupBuilder createNewRow()
	{
		return getCSpecBuilder().createGroupBuilder();
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
		builder.setRebase(rebasePath);

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

	public void fillStack(Composite stackComposite)
	{
		addStackMapping("General", createGeneralStackLayer(stackComposite));
		addStackMapping("Hints", createInstallerHintsStackLayer(stackComposite));
		addStackMapping("Prerequisite", createPrerequisitesStackLayer(stackComposite));
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

		UiUtils.createGridLabel(geComposite, "Rebase Path:", 1, 0, SWT.NONE);

		m_rebasePathText = UiUtils.createGridText(geComposite, 1, 0, null, SWT.NONE);

		return geComposite;
	}

	private Control createPrerequisitesStackLayer(Composite stackComposite)
	{
		Composite preComposite = new Composite(stackComposite, SWT.NONE);
		GridLayout layout = new GridLayout(1, false);
		layout.marginHeight = layout.marginWidth = 0;
		preComposite.setLayout(layout);

		EditorUtils.createHeaderLabel(preComposite, "Prerequisites", 1);

		// Uses an empty GroupBuilder (createNewRow())
		// "PrerequisiteBuilder"s will be created with this empty GroupBuilder
		// Need to create "PrerequisiteBuilder"s again while saving them

		PrerequisitesTable preTable = new PrerequisitesTable(m_prerequisites, createNewRow());
		 
		m_prerequisitesEditor = new SimpleTableEditor<PrerequisiteBuilder>(
				preComposite,
				preTable,
				null,
				"Group - Prerequisite",
				null,
				null,
				SWT.NONE);

		return preComposite;
	}

	@Override
	protected void refreshRow(GroupBuilder builder)
	{
		super.refreshRow(builder);

		IPath rebasePath = builder.getRebase();
		m_rebasePathText.setText(TextUtils.notNullString(rebasePath == null
				? null
				: rebasePath.toOSString()));

		CSpecEditorUtils.copyAndSortItems(builder.getPrerequisites(), m_prerequisites, CSpecEditorUtils.getCSpecElementComparator());
		m_prerequisitesEditor.refresh();
	}

	@Override
	public void enableFields(boolean enabled)
	{
		super.enableFields(enabled);

		m_rebasePathText.setEnabled(enabled);
		m_prerequisitesEditor.setEnabled(enabled);
	}
}
