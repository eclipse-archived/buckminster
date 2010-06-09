package org.eclipse.buckminster.model.common.provider.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;

public class ExtensionPropertyDescriptor extends ItemPropertyDescriptor {
	private final String extensionId;

	private final String attributeName;

	private final boolean addEmptyChoice;

	public ExtensionPropertyDescriptor(AdapterFactory adapterFactory, ResourceLocator resourceLocator, String displayName, String description,
			EStructuralFeature feature, boolean isSettable, boolean multiLine, boolean sortChoices, Object staticImage, String category,
			String[] filterFlags, String extensionId, String attributeName, boolean addEmptyChoice) {
		super(adapterFactory, resourceLocator, displayName, description, feature, isSettable, multiLine, sortChoices, staticImage, category,
				filterFlags);
		this.extensionId = extensionId;
		this.attributeName = attributeName;
		this.addEmptyChoice = addEmptyChoice;
	}

	@Override
	public Collection<?> getChoiceOfValues(Object object) {
		List<String> choices = new ArrayList<String>();
		if (addEmptyChoice)
			choices.add(null);
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		for (IConfigurationElement ce : registry.getConfigurationElementsFor(extensionId))
			choices.add(ce.getAttribute(attributeName));
		return choices;
	}
}
