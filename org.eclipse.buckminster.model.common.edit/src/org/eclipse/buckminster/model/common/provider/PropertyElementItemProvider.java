/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.model.common.provider;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.model.common.CommonFactory;
import org.eclipse.buckminster.model.common.CommonPackage;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IChildCreationExtender;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link java.util.Map.Entry} object.
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class PropertyElementItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider, IStructuredItemContentProvider,
		ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public PropertyElementItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to
	 * deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand},
	 * {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in
	 * {@link #createCommand}. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(CommonPackage.Literals.PROPERTY_ELEMENT__VALUE_GROUP);
		}
		return childrenFeatures;
	}

	/**
	 * This returns the label text for
	 * {@link org.eclipse.emf.edit.command.CreateChildCommand}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
		Object childFeature = feature;
		Object childObject = child;

		if (childFeature instanceof EStructuralFeature && FeatureMapUtil.isFeatureMap((EStructuralFeature) childFeature)) {
			FeatureMap.Entry entry = (FeatureMap.Entry) childObject;
			childFeature = entry.getEStructuralFeature();
			childObject = entry.getValue();
		}

		boolean qualify = childFeature == CommonPackage.Literals.PROPERTY_ELEMENT__VALUE
				|| childFeature == CommonPackage.Literals.ABSTRACT_DOCUMENT_ROOT__CONSTANT
				|| childFeature == CommonPackage.Literals.ABSTRACT_DOCUMENT_ROOT__FORMAT
				|| childFeature == CommonPackage.Literals.ABSTRACT_DOCUMENT_ROOT__PROPERTY_REF
				|| childFeature == CommonPackage.Literals.ABSTRACT_DOCUMENT_ROOT__REPLACE
				|| childFeature == CommonPackage.Literals.ABSTRACT_DOCUMENT_ROOT__SPLIT
				|| childFeature == CommonPackage.Literals.ABSTRACT_DOCUMENT_ROOT__TO_LOWER
				|| childFeature == CommonPackage.Literals.ABSTRACT_DOCUMENT_ROOT__TO_UPPER;

		if (qualify) {
			return getString("_UI_CreateChild_text2", new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

	/**
	 * This returns the property descriptors for the adapted class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addKeyPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * Return the resource locator for this item provider's resources. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return ((IChildCreationExtender) adapterFactory).getResourceLocator();
	}

	/**
	 * This returns the label text for the adapted class. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		Map.Entry<?, ?> propertyElement = (Map.Entry<?, ?>) object;
		return "" + propertyElement.getKey() + " -> " + propertyElement.getValue();
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to
	 * update any cached children and by creating a viewer notification, which
	 * it passes to {@link #fireNotifyChanged}. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(Map.Entry.class)) {
			case CommonPackage.PROPERTY_ELEMENT__KEY:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case CommonPackage.PROPERTY_ELEMENT__VALUE_GROUP:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds a property descriptor for the Key feature. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addKeyPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
				getResourceLocator(), getString("_UI_PropertyElement_key_feature"),
				getString("_UI_PropertyDescriptor_description", "_UI_PropertyElement_key_feature", "_UI_PropertyElement_type"),
				CommonPackage.Literals.PROPERTY_ELEMENT__KEY, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s
	 * describing the children that can be created under this object. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add(createChildParameter(CommonPackage.Literals.PROPERTY_ELEMENT__VALUE_GROUP,
				FeatureMapUtil.createEntry(CommonPackage.Literals.ABSTRACT_DOCUMENT_ROOT__CONSTANT, CommonFactory.eINSTANCE.createConstant())));

		newChildDescriptors.add(createChildParameter(CommonPackage.Literals.PROPERTY_ELEMENT__VALUE_GROUP,
				FeatureMapUtil.createEntry(CommonPackage.Literals.ABSTRACT_DOCUMENT_ROOT__FORMAT, CommonFactory.eINSTANCE.createFormat())));

		newChildDescriptors
				.add(createChildParameter(
						CommonPackage.Literals.PROPERTY_ELEMENT__VALUE_GROUP,
						FeatureMapUtil.createEntry(CommonPackage.Literals.ABSTRACT_DOCUMENT_ROOT__PROPERTY_REF,
								CommonFactory.eINSTANCE.createPropertyRef())));

		newChildDescriptors.add(createChildParameter(CommonPackage.Literals.PROPERTY_ELEMENT__VALUE_GROUP,
				FeatureMapUtil.createEntry(CommonPackage.Literals.ABSTRACT_DOCUMENT_ROOT__REPLACE, CommonFactory.eINSTANCE.createReplace())));

		newChildDescriptors.add(createChildParameter(CommonPackage.Literals.PROPERTY_ELEMENT__VALUE_GROUP,
				FeatureMapUtil.createEntry(CommonPackage.Literals.ABSTRACT_DOCUMENT_ROOT__TO_LOWER, CommonFactory.eINSTANCE.createToLower())));

		newChildDescriptors.add(createChildParameter(CommonPackage.Literals.PROPERTY_ELEMENT__VALUE_GROUP,
				FeatureMapUtil.createEntry(CommonPackage.Literals.ABSTRACT_DOCUMENT_ROOT__TO_UPPER, CommonFactory.eINSTANCE.createToUpper())));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper
		// feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

}
