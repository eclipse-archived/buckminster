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

import org.eclipse.buckminster.rmap.ResourceMap;
import org.eclipse.buckminster.rmap.RmapFactory;
import org.eclipse.buckminster.rmap.RmapPackage;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

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
 * This is the item provider adapter for a {@link org.eclipse.buckminster.rmap.ResourceMap} object. <!-- begin-user-doc
 * --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class ResourceMapItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider,
		IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource
{
	/**
	 * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ResourceMapItemProvider(AdapterFactory adapterFactory)
	{
		super(adapterFactory);
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object)
	{
		if(childrenFeatures == null)
		{
			super.getChildrenFeatures(object);
			childrenFeatures.add(RmapPackage.Literals.RESOURCE_MAP__LOCATORS);
			childrenFeatures.add(RmapPackage.Literals.RESOURCE_MAP__REDIRECTS);
			childrenFeatures.add(RmapPackage.Literals.RESOURCE_MAP__SEARCH_PATHS);
			childrenFeatures.add(RmapPackage.Literals.RESOURCE_MAP__PROPERTY_ELEMENTS);
			childrenFeatures.add(RmapPackage.Literals.RESOURCE_MAP__PROPERTIES);
			childrenFeatures.add(RmapPackage.Literals.RESOURCE_MAP__DOCUMENTATION);
		}
		return childrenFeatures;
	}

	/**
	 * This returns ResourceMap.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object getImage(Object object)
	{
		return overlayImage(object, getResourceLocator().getImage("full/obj16/ResourceMap"));
	}

	/**
	 * This returns the property descriptors for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object)
	{
		if(itemPropertyDescriptors == null)
		{
			super.getPropertyDescriptors(object);

		}
		return itemPropertyDescriptors;
	}

	/**
	 * Return the resource locator for this item provider's resources. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator()
	{
		return ((IChildCreationExtender)adapterFactory).getResourceLocator();
	}

	/**
	 * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getText(Object object)
	{
		return getString("_UI_ResourceMap_type");
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached children and by creating
	 * a viewer notification, which it passes to {@link #fireNotifyChanged}. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification)
	{
		updateChildren(notification);

		switch(notification.getFeatureID(ResourceMap.class))
		{
		case RmapPackage.RESOURCE_MAP__LOCATORS:
		case RmapPackage.RESOURCE_MAP__REDIRECTS:
		case RmapPackage.RESOURCE_MAP__SEARCH_PATHS:
		case RmapPackage.RESOURCE_MAP__PROPERTY_ELEMENTS:
		case RmapPackage.RESOURCE_MAP__PROPERTIES:
		case RmapPackage.RESOURCE_MAP__DOCUMENTATION:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
			return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children that can be created
	 * under this object. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object)
	{
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add(createChildParameter(RmapPackage.Literals.RESOURCE_MAP__LOCATORS,
				RmapFactory.eINSTANCE.createLocator()));

		newChildDescriptors.add(createChildParameter(RmapPackage.Literals.RESOURCE_MAP__REDIRECTS,
				RmapFactory.eINSTANCE.createRedirect()));

		newChildDescriptors.add(createChildParameter(RmapPackage.Literals.RESOURCE_MAP__SEARCH_PATHS,
				RmapFactory.eINSTANCE.createSearchPath()));

		newChildDescriptors.add(createChildParameter(RmapPackage.Literals.RESOURCE_MAP__PROPERTY_ELEMENTS,
				CommonFactory.eINSTANCE.createPropertyElement()));

		newChildDescriptors.add(createChildParameter(RmapPackage.Literals.RESOURCE_MAP__PROPERTIES,
				CommonFactory.eINSTANCE.createPropertyConstant()));

		newChildDescriptors.add(createChildParameter(RmapPackage.Literals.RESOURCE_MAP__DOCUMENTATION,
				CommonFactory.eINSTANCE.createDocumentation()));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child)
	{
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

}
