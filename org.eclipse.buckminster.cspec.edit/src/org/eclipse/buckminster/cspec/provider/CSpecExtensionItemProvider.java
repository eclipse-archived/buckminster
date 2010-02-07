/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspec.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.buckminster.cspec.CSpecExtension;
import org.eclipse.buckminster.cspec.CspecFactory;
import org.eclipse.buckminster.cspec.CspecPackage;

import org.eclipse.buckminster.model.common.CommonFactory;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a
 * {@link org.eclipse.buckminster.cspec.CSpecExtension} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class CSpecExtensionItemProvider extends CSpecItemProvider implements IEditingDomainItemProvider, IStructuredItemContentProvider,
		ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public CSpecExtensionItemProvider(AdapterFactory adapterFactory) {
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
			childrenFeatures.add(CspecPackage.Literals.CSPEC_EXTENSION__ATTRIBUTE_ALTERATIONS);
			childrenFeatures.add(CspecPackage.Literals.CSPEC_EXTENSION__RENAME_ATTRIBUTES);
			childrenFeatures.add(CspecPackage.Literals.CSPEC_EXTENSION__REMOVE_DEPENDENCIES);
			childrenFeatures.add(CspecPackage.Literals.CSPEC_EXTENSION__REMOVE_GENERATORS);
			childrenFeatures.add(CspecPackage.Literals.CSPEC_EXTENSION__REPLACE_GENERATORS);
			childrenFeatures.add(CspecPackage.Literals.CSPEC_EXTENSION__REPLACE_DEPENDENCIES);
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

		boolean qualify = childFeature == CspecPackage.Literals.CSPEC_EXTENSION__REMOVE_DEPENDENCIES
				|| childFeature == CspecPackage.Literals.CSPEC_EXTENSION__REMOVE_GENERATORS;

		if (qualify) {
			return getString("_UI_CreateChild_text2", new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

	/**
	 * This returns CSpecExtension.gif. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/CSpecExtension"));
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
	 * This returns the label text for the adapted class. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((CSpecExtension) object).getId();
		return label == null || label.length() == 0 ? getString("_UI_CSpecExtension_type") : getString("_UI_CSpecExtension_type") + " " + label;
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

		switch (notification.getFeatureID(CSpecExtension.class)) {
			case CspecPackage.CSPEC_EXTENSION__ATTRIBUTE_ALTERATIONS:
			case CspecPackage.CSPEC_EXTENSION__RENAME_ATTRIBUTES:
			case CspecPackage.CSPEC_EXTENSION__REMOVE_DEPENDENCIES:
			case CspecPackage.CSPEC_EXTENSION__REMOVE_GENERATORS:
			case CspecPackage.CSPEC_EXTENSION__REPLACE_GENERATORS:
			case CspecPackage.CSPEC_EXTENSION__REPLACE_DEPENDENCIES:
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
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add(createChildParameter(CspecPackage.Literals.CSPEC_EXTENSION__ATTRIBUTE_ALTERATIONS, CspecFactory.eINSTANCE
				.createAlterArtifact()));

		newChildDescriptors.add(createChildParameter(CspecPackage.Literals.CSPEC_EXTENSION__ATTRIBUTE_ALTERATIONS, CspecFactory.eINSTANCE
				.createAlterGroup()));

		newChildDescriptors.add(createChildParameter(CspecPackage.Literals.CSPEC_EXTENSION__ATTRIBUTE_ALTERATIONS, CspecFactory.eINSTANCE
				.createAlterAction()));

		newChildDescriptors
				.add(createChildParameter(CspecPackage.Literals.CSPEC_EXTENSION__RENAME_ATTRIBUTES, CspecFactory.eINSTANCE.createRename()));

		newChildDescriptors.add(createChildParameter(CspecPackage.Literals.CSPEC_EXTENSION__REMOVE_DEPENDENCIES, CspecFactory.eINSTANCE
				.createRemove()));

		newChildDescriptors
				.add(createChildParameter(CspecPackage.Literals.CSPEC_EXTENSION__REMOVE_GENERATORS, CspecFactory.eINSTANCE.createRemove()));

		newChildDescriptors.add(createChildParameter(CspecPackage.Literals.CSPEC_EXTENSION__REPLACE_GENERATORS, CspecFactory.eINSTANCE
				.createGenerator()));

		newChildDescriptors.add(createChildParameter(CspecPackage.Literals.CSPEC_EXTENSION__REPLACE_DEPENDENCIES, CommonFactory.eINSTANCE
				.createComponentRequest()));
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
