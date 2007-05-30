/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.ui.editor.cspec;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.cspec.builder.ActionBuilder;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.ui.UiUtils;
import org.eclipse.buckminster.ui.editor.EditorUtils;
import org.eclipse.buckminster.ui.general.editor.ValidatorException;
import org.eclipse.buckminster.ui.general.editor.simple.SimpleTableEditor;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.swt.SWT;
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

	public ActionsTable(List<ActionBuilder> data, CSpecBuilder cspec)
	{
		super(data, cspec);
	}

	@Override
	protected ActionBuilder createNewRow()
	{
		return getCSpecBuilder().createActionBuilder();
	}

	public void fillStack(Composite stackComposite)
	{
		addStackMapping("General", createGeneralStackLayer(stackComposite));
		addStackMapping("Actor Properties", createActorPropertiesStackLayer(stackComposite));
		addStackMapping("Product Paths", createProductPathsStackLayer(stackComposite));
		addStackMapping("Properties", createPropertiesStackLayer(stackComposite));
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

		m_actorProperties.clear();
		ExpandingProperties properties = builder.getActorProperties();
		if(properties != null)
		{
			List<Property> hlpList = new ArrayList<Property>();
			for(String key : properties.keySet())
			{
				hlpList.add(new Property(key, properties.get(key)));
			}
			Property[] propertyArray = hlpList.toArray(new Property[0]);
			Arrays.sort(propertyArray, CSpecEditorUtils.getPropertyComparator());
			
			for(Property property : propertyArray)
			{
				m_actorProperties.add(property);
			}
		}
		m_actorPropertiesEditor.refresh();
		
		m_productPaths.clear();
		Set<IPath> productPaths = builder.getProductPaths();
		if(productPaths != null)
		{
			IPath[] pathArray = productPaths.toArray(new IPath[0]);
			Arrays.sort(pathArray, EditorUtils.getPathComparator());
			
			for(IPath path : pathArray)
			{
				m_productPaths.add(path);
			}
		}
		m_productPathsEditor.refresh();

		m_properties.clear();
		properties = builder.getProperties();
		if(properties != null)
		{
			List<Property> hlpList = new ArrayList<Property>();
			for(String key : properties.keySet())
			{
				hlpList.add(new Property(key, properties.get(key)));
			}
			Property[] propertyArray = hlpList.toArray(new Property[0]);
			Arrays.sort(propertyArray, CSpecEditorUtils.getPropertyComparator());
			
			for(Property property : propertyArray)
			{
				m_properties.add(property);
			}
		}
		m_propertiesEditor.refresh();
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
		
		m_actorProperties.clear();
		ExpandingProperties properties = builder.getActorProperties();
		if(properties != null)
		{
			List<Property> hlpList = new ArrayList<Property>();
			for(String key : properties.keySet())
			{
				hlpList.add(new Property(key, properties.get(key)));
			}
			Property[] propertyArray = hlpList.toArray(new Property[0]);
			Arrays.sort(propertyArray, CSpecEditorUtils.getPropertyComparator());
			
			for(Property property : propertyArray)
			{
				m_actorProperties.add(property);
			}
		}
		m_actorPropertiesEditor.refresh();

		m_productPaths.clear();
		Set<IPath> paths = builder.getProductPaths();
		if(paths != null)
		{
			IPath[] pathArray = paths.toArray(new IPath[0]);
			Arrays.sort(pathArray, EditorUtils.getPathComparator());
			
			for(IPath path : pathArray)
			{
				m_productPaths.add(path);
			}
		}
		m_productPathsEditor.refresh();

		m_properties.clear();
		properties = builder.getProperties();
		if(properties != null)
		{
			List<Property> hlpList = new ArrayList<Property>();
			for(String key : properties.keySet())
			{
				hlpList.add(new Property(key, properties.get(key)));
			}
			Property[] propertyArray = hlpList.toArray(new Property[0]);
			Arrays.sort(propertyArray, CSpecEditorUtils.getPropertyComparator());
			
			for(Property property : propertyArray)
			{
				m_properties.add(property);
			}
		}
		m_propertiesEditor.refresh();
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
	}
}
