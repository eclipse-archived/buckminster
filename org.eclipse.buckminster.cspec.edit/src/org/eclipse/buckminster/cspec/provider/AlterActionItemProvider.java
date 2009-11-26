/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspec.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.buckminster.cspec.AlterAction;
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
 * This is the item provider adapter for a {@link org.eclipse.buckminster.cspec.AlterAction} object. <!-- begin-user-doc
 * --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class AlterActionItemProvider extends ActionItemProvider implements IEditingDomainItemProvider,
		IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource
{
	/**
	 * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public AlterActionItemProvider(AdapterFactory adapterFactory)
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
			childrenFeatures.add(CspecPackage.Literals.ALTER_GROUP__REPLACE_PREREQUISITES);
			childrenFeatures.add(CspecPackage.Literals.ALTER_GROUP__REMOVE_PREREQUISITES);
			childrenFeatures.add(CspecPackage.Literals.ALTER_ACTION__REPLACE_PROPERTIES);
			childrenFeatures.add(CspecPackage.Literals.ALTER_ACTION__REPLACE_ACTOR_PROPERTIES);
			childrenFeatures.add(CspecPackage.Literals.ALTER_ACTION__REMOVE_PROPERTIES);
			childrenFeatures.add(CspecPackage.Literals.ALTER_ACTION__REMOVE_ACTOR_PROPERTIES);
			childrenFeatures.add(CspecPackage.Literals.ALTER_ACTION__REMOVE_PRODUCTS);
			childrenFeatures.add(CspecPackage.Literals.ALTER_ACTION__REMOVE_PATHS);
		}
		return childrenFeatures;
	}

	/**
	 * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection)
	{
		Object childFeature = feature;
		Object childObject = child;

		boolean qualify = childFeature == CspecPackage.Literals.GROUP__PREREQUISITES
				|| childFeature == CspecPackage.Literals.ALTER_GROUP__REPLACE_PREREQUISITES
				|| childFeature == CspecPackage.Literals.ACTION__PROPERTIES
				|| childFeature == CspecPackage.Literals.ACTION__ACTOR_PROPERTIES
				|| childFeature == CspecPackage.Literals.ALTER_ACTION__REPLACE_PROPERTIES
				|| childFeature == CspecPackage.Literals.ALTER_ACTION__REPLACE_ACTOR_PROPERTIES
				|| childFeature == CspecPackage.Literals.ALTER_GROUP__REMOVE_PREREQUISITES
				|| childFeature == CspecPackage.Literals.ALTER_ACTION__REMOVE_PROPERTIES
				|| childFeature == CspecPackage.Literals.ALTER_ACTION__REMOVE_ACTOR_PROPERTIES
				|| childFeature == CspecPackage.Literals.ALTER_ACTION__REMOVE_PRODUCTS
				|| childFeature == CspecPackage.Literals.ALTER_ACTION__REMOVE_PATHS;

		if(qualify)
		{
			return getString("_UI_CreateChild_text2", new Object[] { getTypeText(childObject),
					getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

	/**
	 * This returns AlterAction.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object getImage(Object object)
	{
		return overlayImage(object, getResourceLocator().getImage("full/obj16/AlterAction"));
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
	 * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getText(Object object)
	{
		String label = ((AlterAction)object).getName();
		return label == null || label.length() == 0
				? getString("_UI_AlterAction_type")
				: getString("_UI_AlterAction_type") + " " + label;
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

		switch(notification.getFeatureID(AlterAction.class))
		{
		case CspecPackage.ALTER_ACTION__REPLACE_PREREQUISITES:
		case CspecPackage.ALTER_ACTION__REMOVE_PREREQUISITES:
		case CspecPackage.ALTER_ACTION__REPLACE_PROPERTIES:
		case CspecPackage.ALTER_ACTION__REPLACE_ACTOR_PROPERTIES:
		case CspecPackage.ALTER_ACTION__REMOVE_PROPERTIES:
		case CspecPackage.ALTER_ACTION__REMOVE_ACTOR_PROPERTIES:
		case CspecPackage.ALTER_ACTION__REMOVE_PRODUCTS:
		case CspecPackage.ALTER_ACTION__REMOVE_PATHS:
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

		newChildDescriptors.add(createChildParameter(CspecPackage.Literals.ALTER_GROUP__REPLACE_PREREQUISITES,
				CspecFactory.eINSTANCE.createPrerequisite()));

		newChildDescriptors.add(createChildParameter(CspecPackage.Literals.ALTER_GROUP__REMOVE_PREREQUISITES,
				CspecFactory.eINSTANCE.createRemove()));

		newChildDescriptors.add(createChildParameter(CspecPackage.Literals.ALTER_ACTION__REPLACE_PROPERTIES,
				CommonFactory.eINSTANCE.createPropertyConstant()));

		newChildDescriptors.add(createChildParameter(CspecPackage.Literals.ALTER_ACTION__REPLACE_ACTOR_PROPERTIES,
				CommonFactory.eINSTANCE.createPropertyConstant()));

		newChildDescriptors.add(createChildParameter(CspecPackage.Literals.ALTER_ACTION__REMOVE_PROPERTIES,
				CspecFactory.eINSTANCE.createRemove()));

		newChildDescriptors.add(createChildParameter(CspecPackage.Literals.ALTER_ACTION__REMOVE_ACTOR_PROPERTIES,
				CspecFactory.eINSTANCE.createRemove()));

		newChildDescriptors.add(createChildParameter(CspecPackage.Literals.ALTER_ACTION__REMOVE_PRODUCTS,
				CspecFactory.eINSTANCE.createRemove()));

		newChildDescriptors.add(createChildParameter(CspecPackage.Literals.ALTER_ACTION__REMOVE_PATHS,
				CspecFactory.eINSTANCE.createRemove()));
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
