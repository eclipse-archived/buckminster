/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.model.common.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.buckminster.model.common.CommonFactory;
import org.eclipse.buckminster.model.common.CommonPackage;
import org.eclipse.buckminster.model.common.ValueFilter;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMapUtil;

import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a
 * {@link org.eclipse.buckminster.model.common.ValueFilter} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class ValueFilterItemProvider extends ValueItemProvider implements IEditingDomainItemProvider, IStructuredItemContentProvider,
		ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ValueFilterItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(CommonPackage.Literals.VALUE_FILTER__MULTI_VALUE_GROUP);
		}
		return childrenFeatures;
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

		}
		return itemPropertyDescriptors;
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	public String getText(Object object) {
		ValueFilter valueFilter = (ValueFilter)object;
		return getString("_UI_ValueFilter_type") + " " + valueFilter.isMutable();
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */

	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(ValueFilter.class)) {
			case CommonPackage.VALUE_FILTER__MULTI_VALUE_GROUP:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
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

		newChildDescriptors.add
			(createChildParameter
				(CommonPackage.Literals.VALUE_FILTER__MULTI_VALUE_GROUP,
				 FeatureMapUtil.createEntry
					(CommonPackage.Literals.ABSTRACT_DOCUMENT_ROOT__CONSTANT,
					 CommonFactory.eINSTANCE.createConstant())));

		newChildDescriptors.add
			(createChildParameter
				(CommonPackage.Literals.VALUE_FILTER__MULTI_VALUE_GROUP,
				 FeatureMapUtil.createEntry
					(CommonPackage.Literals.ABSTRACT_DOCUMENT_ROOT__FORMAT,
					 CommonFactory.eINSTANCE.createFormat())));

		newChildDescriptors.add
			(createChildParameter
				(CommonPackage.Literals.VALUE_FILTER__MULTI_VALUE_GROUP,
				 FeatureMapUtil.createEntry
					(CommonPackage.Literals.ABSTRACT_DOCUMENT_ROOT__PROPERTY_REF,
					 CommonFactory.eINSTANCE.createPropertyRef())));

		newChildDescriptors.add
			(createChildParameter
				(CommonPackage.Literals.VALUE_FILTER__MULTI_VALUE_GROUP,
				 FeatureMapUtil.createEntry
					(CommonPackage.Literals.ABSTRACT_DOCUMENT_ROOT__REPLACE,
					 CommonFactory.eINSTANCE.createReplace())));

		newChildDescriptors.add
			(createChildParameter
				(CommonPackage.Literals.VALUE_FILTER__MULTI_VALUE_GROUP,
				 FeatureMapUtil.createEntry
					(CommonPackage.Literals.ABSTRACT_DOCUMENT_ROOT__SPLIT,
					 CommonFactory.eINSTANCE.createSplit())));

		newChildDescriptors.add
			(createChildParameter
				(CommonPackage.Literals.VALUE_FILTER__MULTI_VALUE_GROUP,
				 FeatureMapUtil.createEntry
					(CommonPackage.Literals.ABSTRACT_DOCUMENT_ROOT__TO_LOWER,
					 CommonFactory.eINSTANCE.createToLower())));

		newChildDescriptors.add
			(createChildParameter
				(CommonPackage.Literals.VALUE_FILTER__MULTI_VALUE_GROUP,
				 FeatureMapUtil.createEntry
					(CommonPackage.Literals.ABSTRACT_DOCUMENT_ROOT__TO_UPPER,
					 CommonFactory.eINSTANCE.createToUpper())));
	}

	/**
	 * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
		Object childFeature = feature;
		Object childObject = child;

		if (childFeature instanceof EStructuralFeature && FeatureMapUtil.isFeatureMap((EStructuralFeature)childFeature)) {
			FeatureMap.Entry entry = (FeatureMap.Entry)childObject;
			childFeature = entry.getEStructuralFeature();
			childObject = entry.getValue();
		}

		boolean qualify =
			childFeature == CommonPackage.Literals.VALUE_FILTER__VALUES ||
			childFeature == CommonPackage.Literals.ABSTRACT_DOCUMENT_ROOT__CONSTANT ||
			childFeature == CommonPackage.Literals.ABSTRACT_DOCUMENT_ROOT__FORMAT ||
			childFeature == CommonPackage.Literals.ABSTRACT_DOCUMENT_ROOT__PROPERTY_REF ||
			childFeature == CommonPackage.Literals.ABSTRACT_DOCUMENT_ROOT__REPLACE ||
			childFeature == CommonPackage.Literals.ABSTRACT_DOCUMENT_ROOT__SPLIT ||
			childFeature == CommonPackage.Literals.ABSTRACT_DOCUMENT_ROOT__TO_LOWER ||
			childFeature == CommonPackage.Literals.ABSTRACT_DOCUMENT_ROOT__TO_UPPER;

		if (qualify) {
			return getString
				("_UI_CreateChild_text2",
				 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

}
