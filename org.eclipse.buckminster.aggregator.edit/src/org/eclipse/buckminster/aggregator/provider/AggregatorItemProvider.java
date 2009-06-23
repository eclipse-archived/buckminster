/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.buckminster.aggregator.Aggregator;
import org.eclipse.buckminster.aggregator.AggregatorFactory;
import org.eclipse.buckminster.aggregator.AggregatorPackage;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;
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
 * This is the item provider adapter for a {@link org.eclipse.buckminster.aggregator.Aggregator} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class AggregatorItemProvider extends AggregatorItemProviderAdapter implements IEditingDomainItemProvider,
		IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource
{
	/**
	 * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public AggregatorItemProvider(AdapterFactory adapterFactory)
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
			childrenFeatures.add(AggregatorPackage.Literals.AGGREGATOR__CONFIGURATIONS);
			childrenFeatures.add(AggregatorPackage.Literals.AGGREGATOR__CONTRIBUTIONS);
			childrenFeatures.add(AggregatorPackage.Literals.AGGREGATOR__BUILDMASTER);
			childrenFeatures.add(AggregatorPackage.Literals.AGGREGATOR__CONTACTS);
			childrenFeatures.add(AggregatorPackage.Literals.AGGREGATOR__CUSTOM_CATEGORIES);
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

		boolean qualify = childFeature == AggregatorPackage.Literals.AGGREGATOR__BUILDMASTER
				|| childFeature == AggregatorPackage.Literals.AGGREGATOR__CONTACTS;

		if(qualify)
		{
			return getString("_UI_CreateChild_text2", new Object[] { getTypeText(childObject),
					getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

	/**
	 * This returns Aggregator.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object getImage(Object object)
	{
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Aggregator"));
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

			addTypePropertyDescriptor(object);
			addBuildRootPropertyDescriptor(object);
			addLabelPropertyDescriptor(object);
			addSendmailPropertyDescriptor(object);
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
	 * @generated
	 */
	@Override
	public String getText(Object object)
	{
		String label = ((Aggregator)object).getLabel();
		return label == null || label.length() == 0
				? getString("_UI_Aggregator_type")
				: getString("_UI_Aggregator_type") + " " + label;
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

		switch(notification.getFeatureID(Aggregator.class))
		{
		case AggregatorPackage.AGGREGATOR__TYPE:
		case AggregatorPackage.AGGREGATOR__BUILD_ROOT:
		case AggregatorPackage.AGGREGATOR__LABEL:
		case AggregatorPackage.AGGREGATOR__SENDMAIL:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
			return;
		case AggregatorPackage.AGGREGATOR__CONFIGURATIONS:
		case AggregatorPackage.AGGREGATOR__CONTRIBUTIONS:
		case AggregatorPackage.AGGREGATOR__BUILDMASTER:
		case AggregatorPackage.AGGREGATOR__CONTACTS:
		case AggregatorPackage.AGGREGATOR__CUSTOM_CATEGORIES:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
			return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds a property descriptor for the Build Root feature. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addBuildRootPropertyDescriptor(Object object)
	{
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_Aggregator_buildRoot_feature"), getString("_UI_PropertyDescriptor_description",
						"_UI_Aggregator_buildRoot_feature", "_UI_Aggregator_type"),
				AggregatorPackage.Literals.AGGREGATOR__BUILD_ROOT, true, false, false,
				ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Label feature. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addLabelPropertyDescriptor(Object object)
	{
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_Aggregator_label_feature"), getString("_UI_PropertyDescriptor_description",
						"_UI_Aggregator_label_feature", "_UI_Aggregator_type"),
				AggregatorPackage.Literals.AGGREGATOR__LABEL, true, false, false,
				ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Sendmail feature. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addSendmailPropertyDescriptor(Object object)
	{
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_Aggregator_sendmail_feature"), getString("_UI_PropertyDescriptor_description",
						"_UI_Aggregator_sendmail_feature", "_UI_Aggregator_type"),
				AggregatorPackage.Literals.AGGREGATOR__SENDMAIL, true, false, false,
				ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Type feature. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addTypePropertyDescriptor(Object object)
	{
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_Aggregator_type_feature"), getString("_UI_PropertyDescriptor_description",
						"_UI_Aggregator_type_feature", "_UI_Aggregator_type"),
				AggregatorPackage.Literals.AGGREGATOR__TYPE, true, false, false,
				ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
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

		newChildDescriptors.add(createChildParameter(AggregatorPackage.Literals.AGGREGATOR__CONFIGURATIONS,
				AggregatorFactory.eINSTANCE.createConfiguration()));

		newChildDescriptors.add(createChildParameter(AggregatorPackage.Literals.AGGREGATOR__CONTRIBUTIONS,
				AggregatorFactory.eINSTANCE.createContribution()));

		newChildDescriptors.add(createChildParameter(AggregatorPackage.Literals.AGGREGATOR__BUILDMASTER,
				AggregatorFactory.eINSTANCE.createContact()));

		newChildDescriptors.add(createChildParameter(AggregatorPackage.Literals.AGGREGATOR__CONTACTS,
				AggregatorFactory.eINSTANCE.createContact()));

		newChildDescriptors.add(createChildParameter(AggregatorPackage.Literals.AGGREGATOR__CUSTOM_CATEGORIES,
				AggregatorFactory.eINSTANCE.createCustomCategory()));
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
