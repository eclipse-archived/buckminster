/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.ui.prefs;

import org.eclipse.buckminster.core.prefs.IPreferenceDescriptor;
import org.eclipse.buckminster.core.prefs.IPreferenceValidator;
import org.eclipse.buckminster.core.resolver.IResolverFactory;
import org.eclipse.buckminster.core.resolver.ResolverFactoryMaintainer;
import org.eclipse.buckminster.runtime.IBuckminsterPreferenceConstants;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.buckminster.ui.UiPlugin;
import org.eclipse.buckminster.ui.UiUtils;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.preference.PathEditor;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * @author Thomas Hallgren
 *
 */
public class DynamicPreferencePage extends FieldEditorPreferencePage  implements IWorkbenchPreferencePage, IBuckminsterPreferenceConstants
{
	private static IPreferenceValidator s_nullValidator = new IPreferenceValidator()
	{
		public boolean validate(String value)
		{
			return true;
		}
	};
	private Composite m_resolversParent;

	private StackLayout m_resolversStack;

	public DynamicPreferencePage()
	{
		super(GRID);
		setDescription("Buckminster preferences");
		setPreferenceStore(UiPlugin.getDefault().getBuckminsterPreferenceStore());
	}

	public void init(IWorkbench workbench)
	{
	}

	protected void addDynamicFieldEditors(Composite parent, String nodeName, IPreferenceDescriptor[] descriptors)
	{
		for(final IPreferenceDescriptor descriptor : descriptors)
		{
			String name = nodeName + '/' + descriptor.getName();
			String label = descriptor.getLabel();
			IPreferenceValidator descValidator = descriptor.getValidator();
			if(descValidator == null)
				descValidator = s_nullValidator;
			final IPreferenceValidator validator = descValidator;
			FieldEditor editor;
			switch(descriptor.getType())
			{
			case Boolean:
				editor = new BooleanFieldEditor(name, label, parent);
				break;
			case Directory:
				editor = new DirectoryFieldEditor(name, label, parent)
				{
					@Override
					protected boolean checkState()
					{
						return super.checkState() && validator.validate(UiUtils.trimmedValue(getTextControl()));
					}
				};
				break;
			case Enum:
				Enum<?>[] enums = descriptor.getEnums();
				int idx = enums.length;
				String[][] labelsAndValues = new String[idx][2];
				while(--idx >= 0)
				{
					labelsAndValues[idx][0] = enums[idx].toString();
					labelsAndValues[idx][1] = enums[idx].name();
				}
				editor = new RadioGroupFieldEditor(name, label, 1, labelsAndValues, parent);
				break;
			case File:
				editor = new FileFieldEditor(name, label, parent)
				{
					@Override
					protected boolean checkState()
					{
						return super.checkState() && validator.validate(UiUtils.trimmedValue(getTextControl()));
					}
				};
				break;
			case Integer:
				editor = new IntegerFieldEditor(name, label, parent, descriptor.getTextWidth())
				{
					@Override
					protected boolean checkState()
					{
						return super.checkState() && validator.validate(UiUtils.trimmedValue(getTextControl()));
					}
				};
				break;
			case Path:
				editor = new PathEditor(name, label, label, parent);
				break;
			default:
				editor = new StringFieldEditor(name, label, descriptor.getTextWidth(), parent)
				{
					@Override
					protected boolean checkState()
					{
						return validator.validate(UiUtils.trimmedValue(getTextControl()));
					}
				};
			}
			addField(editor);
		}
	}

	void selectResolverPropertyPane(Object factoryId)
	{
		if(m_resolversParent == null)
			return;

		Control[] children = m_resolversParent.getChildren();
		int idx = children.length;
		while(--idx >= 0)
		{
			Control child = children[idx];
			if(Trivial.equalsAllowNull(child.getData(), factoryId))
			{
				m_resolversStack.topControl = child;
				break;
			}
		}
		boolean visible = idx >= 0;
		m_resolversParent.setVisible(visible);
		if(visible)
			m_resolversParent.layout();
	}

	@Override
	public void propertyChange(PropertyChangeEvent event)
	{
		if(event.getProperty().equals(FieldEditor.VALUE))
		{
			if(event.getSource() instanceof ResolutionResolverListEditor)
				selectResolverPropertyPane(event.getNewValue());
		}
		else
			super.propertyChange(event);
	}

	@Override
	protected void createFieldEditors()
	{
		addField(new StringFieldEditor(SITE_NAME, "Site name", getFieldEditorParent()));
		addField(new DirectoryFieldEditor(BUCKMINSTER_PROJECT_CONTENTS, "Buckminster project folder", getFieldEditorParent()));
		addField(new EnumFieldEditor(LOG_LEVEL_CONSOLE, "Console logger level:", LogLevel.values(), getFieldEditorParent()));
		addField(new EnumFieldEditor(LOG_LEVEL_ECLIPSE_LOGGER, "Eclipse logger level:", LogLevel.values(), getFieldEditorParent()));
		addField(new BooleanFieldEditor(LOG_ECLIPSE_TO_CONSOLE, "Copy Eclipse log events to Console", getFieldEditorParent()));
		addField(new ResolutionResolverListEditor(QUERY_RESOLVER_SORT_ORDER, "Resolver order", getFieldEditorParent()));

		IResolverFactory[] factories = ResolverFactoryMaintainer.getInstance().getResolverFactories();
		int top = factories.length;
		if(top == 1)
		{
			IResolverFactory factory = factories[0];
			NestedFieldEditor nfe = new NestedFieldEditor(factory.getId(), getFieldEditorParent());
			addDynamicFieldEditors(nfe.getControl(), factory.getId(), factory.getPreferenceDescriptors());
			addField(nfe);
		}
		else if(top > 1)
		{
			// We need a parent that will allow us to switch pane
			// dependent on what resolver factory that is selected
			//
			NestedFieldEditor resolvers = new NestedFieldEditor("", getFieldEditorParent());
			addField(resolvers);
			m_resolversParent = resolvers.getControl();
			for(int idx = 0; idx < top; ++idx)
			{
				IResolverFactory factory = factories[idx];
				String factoryId = factory.getId();
				NestedFieldEditor nfe = new NestedFieldEditor(factoryId, m_resolversParent);
				Composite nfeComp = nfe.getControl();
				nfeComp.setData(factoryId);
				addDynamicFieldEditors(nfeComp, factoryId, factory.getPreferenceDescriptors());
				addField(nfe);
			}
			m_resolversStack = new StackLayout();
			m_resolversParent.setLayout(m_resolversStack);
			m_resolversParent.setVisible(false);
		}
	}
}
