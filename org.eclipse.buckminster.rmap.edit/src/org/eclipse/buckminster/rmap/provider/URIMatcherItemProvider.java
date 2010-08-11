/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.buckminster.model.common.CommonFactory;
import org.eclipse.buckminster.model.common.CommonPackage;

import org.eclipse.buckminster.model.common.provider.RxAssemblyItemProvider;
import org.eclipse.buckminster.rmap.RmapFactory;
import org.eclipse.buckminster.rmap.RmapPackage;
import org.eclipse.buckminster.rmap.URIMatcher;

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
 * This is the item provider adapter for a {@link org.eclipse.buckminster.rmap.URIMatcher} object.
 * <!-- begin-user-doc
 * --> <!-- end-user-doc -->
 * @generated
 */
public class URIMatcherItemProvider extends RxAssemblyItemProvider implements IEditingDomainItemProvider, IStructuredItemContentProvider,
		ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public URIMatcherItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the label text for
	 * {@link org.eclipse.emf.edit.command.CreateChildCommand}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */

	@Override
	public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
		Object childFeature = feature;
		if (childFeature instanceof EStructuralFeature && FeatureMapUtil.isFeatureMap((EStructuralFeature) childFeature)) {
			FeatureMap.Entry entry = (FeatureMap.Entry) child;
			childFeature = entry.getEStructuralFeature();
		}
		return getFeatureText(childFeature);
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

			addBasePropertyDescriptor(object);
			addVersionFormatPropertyDescriptor(object);
			addVersionTypePropertyDescriptor(object);
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
		String label = ((URIMatcher)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_URIMatcher_type") :
			getString("_UI_URIMatcher_type") + " " + label;
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

		switch (notification.getFeatureID(URIMatcher.class)) {
			case RmapPackage.URI_MATCHER__BASE:
			case RmapPackage.URI_MATCHER__VERSION_FORMAT:
			case RmapPackage.URI_MATCHER__VERSION_TYPE:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds a property descriptor for the Base feature.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 */
	protected void addBasePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_URIMatcher_base_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_URIMatcher_base_feature", "_UI_URIMatcher_type"),
				 RmapPackage.Literals.URI_MATCHER__BASE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Version Format feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addVersionFormatPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_URIMatcher_versionFormat_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_URIMatcher_versionFormat_feature", "_UI_URIMatcher_type"),
				 RmapPackage.Literals.URI_MATCHER__VERSION_FORMAT,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Version Type feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addVersionTypePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_URIMatcher_versionType_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_URIMatcher_versionType_feature", "_UI_URIMatcher_type"),
				 RmapPackage.Literals.URI_MATCHER__VERSION_TYPE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
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

		newChildDescriptors.add(createChildParameter(CommonPackage.Literals.RX_GROUP__RX_PARTS_GROUP,
				FeatureMapUtil.createEntry(RmapPackage.Literals.DOCUMENT_ROOT__MATCH, CommonFactory.eINSTANCE.createRxPattern())));

		newChildDescriptors.add(createChildParameter(CommonPackage.Literals.RX_GROUP__RX_PARTS_GROUP,
				FeatureMapUtil.createEntry(RmapPackage.Literals.DOCUMENT_ROOT__GROUP, CommonFactory.eINSTANCE.createRxGroup())));

		newChildDescriptors.add(createChildParameter(CommonPackage.Literals.RX_GROUP__RX_PARTS_GROUP,
				FeatureMapUtil.createEntry(RmapPackage.Literals.DOCUMENT_ROOT__NAME, CommonFactory.eINSTANCE.createRxPattern())));

		newChildDescriptors.add(createChildParameter(CommonPackage.Literals.RX_GROUP__RX_PARTS_GROUP,
				FeatureMapUtil.createEntry(RmapPackage.Literals.DOCUMENT_ROOT__VERSION, CommonFactory.eINSTANCE.createRxPattern())));

		newChildDescriptors.add(createChildParameter(CommonPackage.Literals.RX_GROUP__RX_PARTS_GROUP,
				FeatureMapUtil.createEntry(RmapPackage.Literals.DOCUMENT_ROOT__REVISION, CommonFactory.eINSTANCE.createRxPattern())));

		newChildDescriptors.add(createChildParameter(CommonPackage.Literals.RX_GROUP__RX_PARTS_GROUP,
				FeatureMapUtil.createEntry(RmapPackage.Literals.DOCUMENT_ROOT__TIMESTAMP, CommonFactory.eINSTANCE.createRxPattern())));

		newChildDescriptors.add(createChildParameter(CommonPackage.Literals.RX_GROUP__RX_PARTS_GROUP,
				FeatureMapUtil.createEntry(RmapPackage.Literals.DOCUMENT_ROOT__OS, CommonFactory.eINSTANCE.createRxPattern())));

		newChildDescriptors.add(createChildParameter(CommonPackage.Literals.RX_GROUP__RX_PARTS_GROUP,
				FeatureMapUtil.createEntry(RmapPackage.Literals.DOCUMENT_ROOT__WS, CommonFactory.eINSTANCE.createRxPattern())));

		newChildDescriptors.add(createChildParameter(CommonPackage.Literals.RX_GROUP__RX_PARTS_GROUP,
				FeatureMapUtil.createEntry(RmapPackage.Literals.DOCUMENT_ROOT__ARCH, CommonFactory.eINSTANCE.createRxPattern())));

		newChildDescriptors.add(createChildParameter(CommonPackage.Literals.RX_GROUP__RX_PARTS_GROUP,
				FeatureMapUtil.createEntry(RmapPackage.Literals.DOCUMENT_ROOT__NL, CommonFactory.eINSTANCE.createRxPattern())));
	}

}
