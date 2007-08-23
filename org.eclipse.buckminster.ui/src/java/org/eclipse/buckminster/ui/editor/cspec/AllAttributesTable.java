/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.ui.editor.cspec;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.buckminster.core.cspec.builder.ActionArtifactBuilder;
import org.eclipse.buckminster.core.cspec.builder.ActionBuilder;
import org.eclipse.buckminster.core.cspec.builder.ArtifactBuilder;
import org.eclipse.buckminster.core.cspec.builder.AttributeBuilder;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.builder.GroupBuilder;
import org.eclipse.buckminster.ui.UiUtils;
import org.eclipse.buckminster.ui.editor.EditorUtils;
import org.eclipse.buckminster.ui.general.editor.ITableModifyListener;
import org.eclipse.buckminster.ui.general.editor.TableModifyEvent;
import org.eclipse.buckminster.ui.general.editor.TableModifyEventType;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

/**
 * @author Karel Brezina
 *
 */
public class AllAttributesTable extends AttributesTable<AttributeBuilder>
{

	public AllAttributesTable(CSpecEditor editor, CSpecBuilder cspec)
	{
		super(editor, new ArrayList<AttributeBuilder>(), cspec);
		addTableModifyListener(new ITableModifyListener<AttributeBuilder>()
		{

			public void modifyTable(TableModifyEvent<AttributeBuilder> e)
			{
				if(e.getEventType() == TableModifyEventType.REMOVE_ROW)
				{
					if(e.getChangedTableRow() instanceof ActionBuilder)
					{
						getCSpecEditor().getActionBuilders().remove(e.getChangedTableRow());
					} else if(e.getChangedTableRow() instanceof ActionArtifactBuilder)
					{
						for(List<ActionArtifactBuilder> list : getCSpecEditor().getActionArtifactBuilders().values())
							list.remove(e.getChangedTableRow());
					}
					else if(e.getChangedTableRow() instanceof ArtifactBuilder)
					{
						getCSpecEditor().getArtifactBuilders().remove(e.getChangedTableRow());
					}
					else if(e.getChangedTableRow() instanceof GroupBuilder)
					{
						getCSpecEditor().getGroupBuilders().remove(e.getChangedTableRow());
					}
				}
			}
		});
	}

	@Override
	protected AttributeBuilder createNewRow()
	{
		// don't need it really here - but when you delete the last row, you need to initialize fields (new AttributeBuilder() is not visible)
		return getCSpecBuilder().createActionBuilder();
	}

	@Override
	protected void fillStack(Composite stackComposite)
	{
		addStackMapping("General", createGeneralStackLayer(stackComposite));
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
		setNameText(UiUtils.createGridText(geComposite, 1, 0, SWT.NONE));

		UiUtils.createGridLabel(geComposite, "Public:", 1, 0, SWT.NONE);
		setPublicCheck(UiUtils.createCheckButton(geComposite, null, null));

		UiUtils.createEmptyLabel(geComposite);
		UiUtils.createEmptyLabel(geComposite);
		
		Label label = UiUtils.createGridLabel(geComposite, "Installer Hints:", 1, 0, SWT.NONE);
		label.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false));
		Control ihEditor = createInstallerHintsEditor(geComposite);
		ihEditor.setLayoutData(new GridData(GridData.FILL_BOTH));

		geComposite.setData("focusControl", getNameText());

		return geComposite;
	}
	
	@Override
	public void refresh()
	{
		getRows().clear();
		getRows().addAll(getCSpecEditor().getActionBuilders());
		for(List<ActionArtifactBuilder> list : getCSpecEditor().getActionArtifactBuilders().values())
			getRows().addAll(list);
		getRows().addAll(getCSpecEditor().getArtifactBuilders());
		getRows().addAll(getCSpecEditor().getGroupBuilders());

		Collections.sort(getRows(), CSpecEditorUtils.getAttributeComparator());
	}
}
