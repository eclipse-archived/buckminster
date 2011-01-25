/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.model.common.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.buckminster.model.common.CommonPackage;
import org.eclipse.buckminster.model.common.Split;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a
 * {@link org.eclipse.buckminster.model.common.Split} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class SplitItemProvider extends ValueFilterItemProvider implements IEditingDomainItemProvider, IStructuredItemContentProvider,
		ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public SplitItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
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

		boolean qualify = childFeature == CommonPackage.Literals.VALUE_FILTER__VALUES
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

			addLimitPropertyDescriptor(object);
			addPatternPropertyDescriptor(object);
			addStylePropertyDescriptor(object);
			addCompiledPatternPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This returns the label text for the adapted class. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */

	@Override
	public String getText(Object object) {
		Split split = (Split) object;
		return getString("_UI_Split_type") + " " + split.getPattern();
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

		switch (notification.getFeatureID(Split.class)) {
			case CommonPackage.SPLIT__LIMIT:
			case CommonPackage.SPLIT__PATTERN:
			case CommonPackage.SPLIT__STYLE:
			case CommonPackage.SPLIT__COMPILED_PATTERN:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds a property descriptor for the Compiled Pattern feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addCompiledPatternPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
				getResourceLocator(), getString("_UI_Split_compiledPattern_feature"),
				getString("_UI_PropertyDescriptor_description", "_UI_Split_compiledPattern_feature", "_UI_Split_type"),
				CommonPackage.Literals.SPLIT__COMPILED_PATTERN, false, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Limit feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addLimitPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
				getResourceLocator(), getString("_UI_Split_limit_feature"),
				getString("_UI_PropertyDescriptor_description", "_UI_Split_limit_feature", "_UI_Split_type"), CommonPackage.Literals.SPLIT__LIMIT,
				true, false, false, ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Pattern feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addPatternPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
				getResourceLocator(), getString("_UI_Split_pattern_feature"),
				getString("_UI_PropertyDescriptor_description", "_UI_Split_pattern_feature", "_UI_Split_type"),
				CommonPackage.Literals.SPLIT__PATTERN, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Style feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addStylePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
				getResourceLocator(), getString("_UI_Split_style_feature"),
				getString("_UI_PropertyDescriptor_description", "_UI_Split_style_feature", "_UI_Split_type"), CommonPackage.Literals.SPLIT__STYLE,
				true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s
	 * describing the children that can be created under this object. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);
	}

}
