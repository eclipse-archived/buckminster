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
import org.eclipse.buckminster.rmap.RmapFactory;
import org.eclipse.buckminster.rmap.RmapPackage;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.IChildCreationExtender;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link org.eclipse.emf.ecore.EObject}
 * object. <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class DocumentRootItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider, IStructuredItemContentProvider,
		ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public DocumentRootItemProvider(AdapterFactory adapterFactory) {
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
			childrenFeatures.add(RmapPackage.Literals.DOCUMENT_ROOT__MATCH);
			childrenFeatures.add(RmapPackage.Literals.DOCUMENT_ROOT__GROUP);
			childrenFeatures.add(RmapPackage.Literals.DOCUMENT_ROOT__NAME);
			childrenFeatures.add(RmapPackage.Literals.DOCUMENT_ROOT__VERSION);
			childrenFeatures.add(RmapPackage.Literals.DOCUMENT_ROOT__REVISION);
			childrenFeatures.add(RmapPackage.Literals.DOCUMENT_ROOT__TIMESTAMP);
			childrenFeatures.add(RmapPackage.Literals.DOCUMENT_ROOT__OS);
			childrenFeatures.add(RmapPackage.Literals.DOCUMENT_ROOT__WS);
			childrenFeatures.add(RmapPackage.Literals.DOCUMENT_ROOT__ARCH);
			childrenFeatures.add(RmapPackage.Literals.DOCUMENT_ROOT__NL);
			childrenFeatures.add(RmapPackage.Literals.DOCUMENT_ROOT__RMAP);
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

		boolean qualify = childFeature == RmapPackage.Literals.DOCUMENT_ROOT__MATCH || childFeature == RmapPackage.Literals.DOCUMENT_ROOT__NAME
				|| childFeature == RmapPackage.Literals.DOCUMENT_ROOT__VERSION || childFeature == RmapPackage.Literals.DOCUMENT_ROOT__REVISION
				|| childFeature == RmapPackage.Literals.DOCUMENT_ROOT__TIMESTAMP || childFeature == RmapPackage.Literals.DOCUMENT_ROOT__OS
				|| childFeature == RmapPackage.Literals.DOCUMENT_ROOT__WS || childFeature == RmapPackage.Literals.DOCUMENT_ROOT__ARCH
				|| childFeature == RmapPackage.Literals.DOCUMENT_ROOT__NL;

		if (qualify) {
			return getString("_UI_CreateChild_text2", new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

	/**
	 * This returns DocumentRoot.gif. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/DocumentRoot"));
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
		return getString("_UI_DocumentRoot_type");
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

		switch (notification.getFeatureID(EObject.class)) {
			case RmapPackage.DOCUMENT_ROOT__MATCH:
			case RmapPackage.DOCUMENT_ROOT__GROUP:
			case RmapPackage.DOCUMENT_ROOT__NAME:
			case RmapPackage.DOCUMENT_ROOT__VERSION:
			case RmapPackage.DOCUMENT_ROOT__REVISION:
			case RmapPackage.DOCUMENT_ROOT__TIMESTAMP:
			case RmapPackage.DOCUMENT_ROOT__OS:
			case RmapPackage.DOCUMENT_ROOT__WS:
			case RmapPackage.DOCUMENT_ROOT__ARCH:
			case RmapPackage.DOCUMENT_ROOT__NL:
			case RmapPackage.DOCUMENT_ROOT__RMAP:
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

		newChildDescriptors.add(createChildParameter(RmapPackage.Literals.DOCUMENT_ROOT__MATCH, CommonFactory.eINSTANCE.createRxPattern()));

		newChildDescriptors.add(createChildParameter(RmapPackage.Literals.DOCUMENT_ROOT__GROUP, CommonFactory.eINSTANCE.createRxGroup()));

		newChildDescriptors.add(createChildParameter(RmapPackage.Literals.DOCUMENT_ROOT__NAME, CommonFactory.eINSTANCE.createRxPattern()));

		newChildDescriptors.add(createChildParameter(RmapPackage.Literals.DOCUMENT_ROOT__VERSION, CommonFactory.eINSTANCE.createRxPattern()));

		newChildDescriptors.add(createChildParameter(RmapPackage.Literals.DOCUMENT_ROOT__REVISION, CommonFactory.eINSTANCE.createRxPattern()));

		newChildDescriptors.add(createChildParameter(RmapPackage.Literals.DOCUMENT_ROOT__TIMESTAMP, CommonFactory.eINSTANCE.createRxPattern()));

		newChildDescriptors.add(createChildParameter(RmapPackage.Literals.DOCUMENT_ROOT__OS, CommonFactory.eINSTANCE.createRxPattern()));

		newChildDescriptors.add(createChildParameter(RmapPackage.Literals.DOCUMENT_ROOT__WS, CommonFactory.eINSTANCE.createRxPattern()));

		newChildDescriptors.add(createChildParameter(RmapPackage.Literals.DOCUMENT_ROOT__ARCH, CommonFactory.eINSTANCE.createRxPattern()));

		newChildDescriptors.add(createChildParameter(RmapPackage.Literals.DOCUMENT_ROOT__NL, CommonFactory.eINSTANCE.createRxPattern()));

		newChildDescriptors.add(createChildParameter(RmapPackage.Literals.DOCUMENT_ROOT__RMAP, RmapFactory.eINSTANCE.createResourceMap()));
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
