/**
 * Copyright (c) 2006-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.p2view.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.buckminster.aggregator.AggregatorPackage;
import org.eclipse.buckminster.aggregator.Aggregator;
import org.eclipse.buckminster.aggregator.p2view.MetadataRepositoryStructuredView;
import org.eclipse.buckminster.aggregator.p2view.P2viewPackage;

import org.eclipse.buckminster.aggregator.provider.AggregatorEditPlugin;
import org.eclipse.buckminster.aggregator.provider.AggregatorItemProviderAdapter;
import org.eclipse.buckminster.aggregator.util.ResourceUtils;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemColorProvider;
import org.eclipse.emf.edit.provider.IItemFontProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a
 * {@link org.eclipse.buckminster.aggregator.p2view.MetadataRepositoryStructuredView} object. <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class MetadataRepositoryStructuredViewItemProvider extends AggregatorItemProviderAdapter implements
		IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider,
		IItemPropertySource, IItemColorProvider, IItemFontProvider
{
	/**
	 * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public MetadataRepositoryStructuredViewItemProvider(AdapterFactory adapterFactory)
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
			childrenFeatures.add(P2viewPackage.Literals.METADATA_REPOSITORY_STRUCTURED_VIEW__INSTALLABLE_UNIT_LIST);
			childrenFeatures.add(P2viewPackage.Literals.METADATA_REPOSITORY_STRUCTURED_VIEW__PROPERTIES);
		}
		return childrenFeatures;
	}

	/**
	 * This returns MetadataRepositoryStructuredView.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object getImage(Object object)
	{
		return overlayImage(object, getResourceLocator().getImage("full/obj16/MetadataRepositoryStructuredView"));
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

			addChildrenPropertyDescriptor(object);
			addNamePropertyDescriptor(object);
			addInstallableUnitListPropertyDescriptor(object);
			addPropertiesPropertyDescriptor(object);
			addLoadedPropertyDescriptor(object);
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
		return AggregatorEditPlugin.INSTANCE;
	}

	/**
	 * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public String getText(Object object)
	{
		String label = ((MetadataRepositoryStructuredView)object).getName();
		return (label == null || label.length() == 0
				? getString("_UI_MetadataRepositoryStructuredView_type")
				: getString("_UI_MetadataRepositoryStructuredView_type") + " " + label)
				+ (((MetadataRepositoryStructuredView)object).isLoaded()
						? ""
						: " (loading...)");
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached children and by creating
	 * a viewer notification, which it passes to {@link #fireNotifyChanged}. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated NOT
	 */
	@Override
	public void notifyChanged(Notification notification)
	{
		notifyChangedGen(notification);

		if(notification.getFeatureID(MetadataRepositoryStructuredView.class) == P2viewPackage.METADATA_REPOSITORY_STRUCTURED_VIEW__LOADED)
		{
			MetadataRepositoryStructuredView mdrView = (MetadataRepositoryStructuredView)notification.getNotifier();
			Aggregator aggregator = ResourceUtils.getAggregator(((EObject)mdrView).eResource().getResourceSet());

			if(aggregator != null)
				fireNotifyChanged(new ViewerNotification(notification, aggregator, true, true));
		}
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached children and by creating
	 * a viewer notification, which it passes to {@link #fireNotifyChanged}. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public void notifyChangedGen(Notification notification)
	{
		updateChildren(notification);

		switch(notification.getFeatureID(MetadataRepositoryStructuredView.class))
		{
		case P2viewPackage.METADATA_REPOSITORY_STRUCTURED_VIEW__NAME:
		case P2viewPackage.METADATA_REPOSITORY_STRUCTURED_VIEW__METADATA_REPOSITORY:
		case P2viewPackage.METADATA_REPOSITORY_STRUCTURED_VIEW__LOADED:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
			return;
		case P2viewPackage.METADATA_REPOSITORY_STRUCTURED_VIEW__INSTALLABLE_UNIT_LIST:
		case P2viewPackage.METADATA_REPOSITORY_STRUCTURED_VIEW__PROPERTIES:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
			return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds a property descriptor for the Children feature. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addChildrenPropertyDescriptor(Object object)
	{
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_ChildrenProvider_children_feature"), getString("_UI_PropertyDescriptor_description",
						"_UI_ChildrenProvider_children_feature", "_UI_ChildrenProvider_type"),
				AggregatorPackage.Literals.CHILDREN_PROVIDER__CHILDREN, false, false, false, null, null, null));
	}

	/**
	 * This adds a property descriptor for the Installable Unit List feature. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	protected void addInstallableUnitListPropertyDescriptor(Object object)
	{
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_MetadataRepositoryStructuredView_installableUnitList_feature"), getString(
						"_UI_PropertyDescriptor_description",
						"_UI_MetadataRepositoryStructuredView_installableUnitList_feature",
						"_UI_MetadataRepositoryStructuredView_type"),
				P2viewPackage.Literals.METADATA_REPOSITORY_STRUCTURED_VIEW__INSTALLABLE_UNIT_LIST, false, false, true,
				null, null, null));
	}

	/**
	 * This adds a property descriptor for the Loaded feature. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addLoadedPropertyDescriptor(Object object)
	{
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_MetadataRepositoryStructuredView_loaded_feature"), getString(
						"_UI_PropertyDescriptor_description", "_UI_MetadataRepositoryStructuredView_loaded_feature",
						"_UI_MetadataRepositoryStructuredView_type"),
				P2viewPackage.Literals.METADATA_REPOSITORY_STRUCTURED_VIEW__LOADED, false, false, false,
				ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Name feature. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addNamePropertyDescriptor(Object object)
	{
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_MetadataRepositoryStructuredView_name_feature"), getString(
						"_UI_PropertyDescriptor_description", "_UI_MetadataRepositoryStructuredView_name_feature",
						"_UI_MetadataRepositoryStructuredView_type"),
				P2viewPackage.Literals.METADATA_REPOSITORY_STRUCTURED_VIEW__NAME, false, false, false,
				ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Properties feature. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addPropertiesPropertyDescriptor(Object object)
	{
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_MetadataRepositoryStructuredView_properties_feature"), getString(
						"_UI_PropertyDescriptor_description",
						"_UI_MetadataRepositoryStructuredView_properties_feature",
						"_UI_MetadataRepositoryStructuredView_type"),
				P2viewPackage.Literals.METADATA_REPOSITORY_STRUCTURED_VIEW__PROPERTIES, false, false, true, null, null,
				null));
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

	/**
	 * Force the children to be wrapped
	 */
	@Override
	protected boolean isWrappingNeeded(Object object)
	{
		return true;
	}

}
