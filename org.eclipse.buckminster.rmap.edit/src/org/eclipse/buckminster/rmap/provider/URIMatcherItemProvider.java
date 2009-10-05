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

import org.eclipse.buckminster.rmap.RmapPackage;
import org.eclipse.buckminster.rmap.URIMatcher;

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
 * This is the item provider adapter for a {@link org.eclipse.buckminster.rmap.URIMatcher} object. <!-- begin-user-doc
 * --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class URIMatcherItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider,
		IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource
{
	/**
	 * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public URIMatcherItemProvider(AdapterFactory adapterFactory)
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
			childrenFeatures.add(RmapPackage.Literals.URI_MATCHER__RX_PARTS_GROUP);
		}
		return childrenFeatures;
	}

	/**
	 * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection)
	{
		Object childFeature = feature;
		if(childFeature instanceof EStructuralFeature && FeatureMapUtil.isFeatureMap((EStructuralFeature)childFeature))
		{
			FeatureMap.Entry entry = (FeatureMap.Entry)child;
			childFeature = entry.getEStructuralFeature();
		}
		return getFeatureText(childFeature);
	}

	/**
	 * This returns URIMatcher.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object getImage(Object object)
	{
		return overlayImage(object, getResourceLocator().getImage("full/obj16/URIMatcher"));
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

			addBasePropertyDescriptor(object);
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
		String label = ((URIMatcher)object).getBase();
		return label == null || label.length() == 0
				? getString("_UI_URIMatcher_type")
				: getString("_UI_URIMatcher_type") + " " + label;
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

		switch(notification.getFeatureID(URIMatcher.class))
		{
		case RmapPackage.URI_MATCHER__BASE:
		case RmapPackage.URI_MATCHER__RX_PARTS:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
			return;
		case RmapPackage.URI_MATCHER__RX_PARTS_GROUP:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
			return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds a property descriptor for the Base feature. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addBasePropertyDescriptor(Object object)
	{
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_URIMatcher_base_feature"), getString("_UI_PropertyDescriptor_description",
						"_UI_URIMatcher_base_feature", "_UI_URIMatcher_type"), RmapPackage.Literals.URI_MATCHER__BASE,
				true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children that can be created
	 * under this object. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object)
	{
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add(createChildParameter(RmapPackage.Literals.URI_MATCHER__RX_PARTS_GROUP,
				FeatureMapUtil.createEntry(RmapPackage.Literals.DOCUMENT_ROOT__MATCH,
						CommonFactory.eINSTANCE.createRxPattern())));

		newChildDescriptors.add(createChildParameter(RmapPackage.Literals.URI_MATCHER__RX_PARTS_GROUP,
				FeatureMapUtil.createEntry(RmapPackage.Literals.DOCUMENT_ROOT__GROUP,
						CommonFactory.eINSTANCE.createRxGroup())));

		newChildDescriptors.add(createChildParameter(RmapPackage.Literals.URI_MATCHER__RX_PARTS_GROUP,
				FeatureMapUtil.createEntry(RmapPackage.Literals.DOCUMENT_ROOT__NAME,
						CommonFactory.eINSTANCE.createRxPattern())));

		newChildDescriptors.add(createChildParameter(RmapPackage.Literals.URI_MATCHER__RX_PARTS_GROUP,
				FeatureMapUtil.createEntry(RmapPackage.Literals.DOCUMENT_ROOT__VERSION,
						CommonFactory.eINSTANCE.createRxPattern())));

		newChildDescriptors.add(createChildParameter(RmapPackage.Literals.URI_MATCHER__RX_PARTS_GROUP,
				FeatureMapUtil.createEntry(RmapPackage.Literals.DOCUMENT_ROOT__REVISION,
						CommonFactory.eINSTANCE.createRxPattern())));

		newChildDescriptors.add(createChildParameter(RmapPackage.Literals.URI_MATCHER__RX_PARTS_GROUP,
				FeatureMapUtil.createEntry(RmapPackage.Literals.DOCUMENT_ROOT__TIMESTAMP,
						CommonFactory.eINSTANCE.createRxPattern())));

		newChildDescriptors.add(createChildParameter(RmapPackage.Literals.URI_MATCHER__RX_PARTS_GROUP,
				FeatureMapUtil.createEntry(RmapPackage.Literals.DOCUMENT_ROOT__OS,
						CommonFactory.eINSTANCE.createRxPattern())));

		newChildDescriptors.add(createChildParameter(RmapPackage.Literals.URI_MATCHER__RX_PARTS_GROUP,
				FeatureMapUtil.createEntry(RmapPackage.Literals.DOCUMENT_ROOT__WS,
						CommonFactory.eINSTANCE.createRxPattern())));

		newChildDescriptors.add(createChildParameter(RmapPackage.Literals.URI_MATCHER__RX_PARTS_GROUP,
				FeatureMapUtil.createEntry(RmapPackage.Literals.DOCUMENT_ROOT__ARCH,
						CommonFactory.eINSTANCE.createRxPattern())));

		newChildDescriptors.add(createChildParameter(RmapPackage.Literals.URI_MATCHER__RX_PARTS_GROUP,
				FeatureMapUtil.createEntry(RmapPackage.Literals.DOCUMENT_ROOT__NL,
						CommonFactory.eINSTANCE.createRxPattern())));
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
