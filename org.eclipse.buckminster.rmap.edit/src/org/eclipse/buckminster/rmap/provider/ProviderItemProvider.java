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
import org.eclipse.buckminster.model.common.provider.util.ExtensionPropertyDescriptor;
import org.eclipse.buckminster.rmap.Provider;
import org.eclipse.buckminster.rmap.RmapFactory;
import org.eclipse.buckminster.rmap.RmapPackage;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;
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
 * This is the item provider adapter for a {@link org.eclipse.buckminster.rmap.Provider} object. <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class ProviderItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider,
		IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource
{
	/**
	 * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ProviderItemProvider(AdapterFactory adapterFactory)
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
			childrenFeatures.add(RmapPackage.Literals.PROVIDER__VERSION_CONVERTER);
			childrenFeatures.add(RmapPackage.Literals.PROVIDER__URI);
			childrenFeatures.add(RmapPackage.Literals.PROVIDER__MATCHERS);
			childrenFeatures.add(RmapPackage.Literals.PROVIDER__DOCUMENTATION);
		}
		return childrenFeatures;
	}

	/**
	 * This returns Provider.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object getImage(Object object)
	{
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Provider"));
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

			addComponentTypesPropertyDescriptor(object);
			addReaderTypePropertyDescriptor(object);
			addSourcePropertyDescriptor(object);
			addMutablePropertyDescriptor(object);
			addResolutionFilterPropertyDescriptor(object);
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
		String label = ((Provider)object).getReaderType();
		return label == null || label.length() == 0
				? getString("_UI_Provider_type")
				: getString("_UI_Provider_type") + " " + label;
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

		switch(notification.getFeatureID(Provider.class))
		{
		case RmapPackage.PROVIDER__COMPONENT_TYPES:
		case RmapPackage.PROVIDER__READER_TYPE:
		case RmapPackage.PROVIDER__SOURCE:
		case RmapPackage.PROVIDER__MUTABLE:
		case RmapPackage.PROVIDER__RESOLUTION_FILTER:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
			return;
		case RmapPackage.PROVIDER__VERSION_CONVERTER:
		case RmapPackage.PROVIDER__URI:
		case RmapPackage.PROVIDER__MATCHERS:
		case RmapPackage.PROVIDER__DOCUMENTATION:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
			return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds a property descriptor for the Component Types feature. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	protected void addComponentTypesPropertyDescriptor(Object object)
	{
		itemPropertyDescriptors.add(new ExtensionPropertyDescriptor(
				((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_Provider_componentTypes_feature"), getString("_UI_PropertyDescriptor_description",
						"_UI_Provider_componentTypes_feature", "_UI_Provider_type"),
				RmapPackage.Literals.PROVIDER__COMPONENT_TYPES, true, false, false,
				ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null, "org.eclipse.buckminster.core.componentTypes",
				"id", false));
	}

	/**
	 * This adds a property descriptor for the Mutable feature. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addMutablePropertyDescriptor(Object object)
	{
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_Provider_mutable_feature"), getString("_UI_PropertyDescriptor_description",
						"_UI_Provider_mutable_feature", "_UI_Provider_type"), RmapPackage.Literals.PROVIDER__MUTABLE,
				true, false, false, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Reader Type feature. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	protected void addReaderTypePropertyDescriptor(Object object)
	{
		itemPropertyDescriptors.add(new ExtensionPropertyDescriptor(
				((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_Provider_readerType_feature"), getString("_UI_PropertyDescriptor_description",
						"_UI_Provider_readerType_feature", "_UI_Provider_type"),
				RmapPackage.Literals.PROVIDER__READER_TYPE, true, false, false,
				ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null, "org.eclipse.buckminster.core.readerTypes",
				"id", false));
	}

	/**
	 * This adds a property descriptor for the Resolution Filter feature. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addResolutionFilterPropertyDescriptor(Object object)
	{
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_Provider_resolutionFilter_feature"), getString("_UI_PropertyDescriptor_description",
						"_UI_Provider_resolutionFilter_feature", "_UI_Provider_type"),
				RmapPackage.Literals.PROVIDER__RESOLUTION_FILTER, true, false, false,
				ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Source feature. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addSourcePropertyDescriptor(Object object)
	{
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_Provider_source_feature"), getString("_UI_PropertyDescriptor_description",
						"_UI_Provider_source_feature", "_UI_Provider_type"), RmapPackage.Literals.PROVIDER__SOURCE,
				true, false, false, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
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

		newChildDescriptors.add(createChildParameter(RmapPackage.Literals.PROVIDER__VERSION_CONVERTER,
				RmapFactory.eINSTANCE.createVersionConverter()));

		newChildDescriptors.add(createChildParameter(RmapPackage.Literals.PROVIDER__URI,
				CommonFactory.eINSTANCE.createFormat()));

		newChildDescriptors.add(createChildParameter(RmapPackage.Literals.PROVIDER__MATCHERS,
				RmapFactory.eINSTANCE.createURIMatcher()));

		newChildDescriptors.add(createChildParameter(RmapPackage.Literals.PROVIDER__DOCUMENTATION,
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
