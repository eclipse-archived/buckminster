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
import org.eclipse.buckminster.aggregator.Contribution;
import org.eclipse.buckminster.aggregator.MappedRepository;
import org.eclipse.buckminster.aggregator.MavenMapping;
import org.eclipse.buckminster.aggregator.MetadataRepositoryReference;
import org.eclipse.buckminster.aggregator.util.ResourceUtils;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemColorProvider;
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
public class AggregatorItemProvider extends DescriptionProviderItemProvider implements IEditingDomainItemProvider,
		IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource,
		IItemColorProvider
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
			childrenFeatures.add(AggregatorPackage.Literals.AGGREGATOR__CONTACTS);
			childrenFeatures.add(AggregatorPackage.Literals.AGGREGATOR__CUSTOM_CATEGORIES);
			childrenFeatures.add(AggregatorPackage.Literals.AGGREGATOR__VALIDATION_REPOSITORIES);
			childrenFeatures.add(AggregatorPackage.Literals.AGGREGATOR__MAVEN_MAPPINGS);
		}
		return childrenFeatures;
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

			addBuildmasterPropertyDescriptor(object);
			addLabelPropertyDescriptor(object);
			addBuildRootPropertyDescriptor(object);
			addPackedStrategyPropertyDescriptor(object);
			addSendmailPropertyDescriptor(object);
			addTypePropertyDescriptor(object);
			addMavenResultPropertyDescriptor(object);
			addMavenMappingsPropertyDescriptor(object);
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
		String label = ((Aggregator)object).getLabel();
		return label == null || label.length() == 0
				? getString("_UI_Aggregator_type")
				: getString("_UI_Aggregator_type") + " " + label;
	}

	@Override
	public void notifyChanged(Notification notification)
	{
		notifyChangedGen(notification);

		if(notification.getEventType() == Notification.REMOVE)
		{
			Object oldV = notification.getOldValue();
			if(oldV instanceof Contribution || oldV instanceof MetadataRepositoryReference)
				ResourceUtils.cleanUpResources((Aggregator)notification.getNotifier());
			if(oldV instanceof MetadataRepositoryReference || oldV instanceof MavenMapping)
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
		}
		else if(notification.getEventType() == Notification.ADD)
		{
			Object newV = notification.getNewValue();
			if(newV instanceof Contribution)
			{
				for(MappedRepository mappedRepository : ((Contribution)newV).getRepositories(true))
					ResourceUtils.loadResourceForMappedRepository(mappedRepository);
			}
			else if(newV instanceof MetadataRepositoryReference)
			{
				ResourceUtils.loadResourceForMappedRepository((MetadataRepositoryReference)newV);
			}
			
			if(newV instanceof MetadataRepositoryReference || newV instanceof MavenMapping)
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
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

		switch(notification.getFeatureID(Aggregator.class))
		{
		case AggregatorPackage.AGGREGATOR__BUILDMASTER:
		case AggregatorPackage.AGGREGATOR__LABEL:
		case AggregatorPackage.AGGREGATOR__BUILD_ROOT:
		case AggregatorPackage.AGGREGATOR__PACKED_STRATEGY:
		case AggregatorPackage.AGGREGATOR__SENDMAIL:
		case AggregatorPackage.AGGREGATOR__TYPE:
		case AggregatorPackage.AGGREGATOR__MAVEN_RESULT:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
			return;
		case AggregatorPackage.AGGREGATOR__CONFIGURATIONS:
		case AggregatorPackage.AGGREGATOR__CONTRIBUTIONS:
		case AggregatorPackage.AGGREGATOR__CONTACTS:
		case AggregatorPackage.AGGREGATOR__CUSTOM_CATEGORIES:
		case AggregatorPackage.AGGREGATOR__VALIDATION_REPOSITORIES:
		case AggregatorPackage.AGGREGATOR__MAVEN_MAPPINGS:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
			return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds a property descriptor for the Buildmaster feature. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addBuildmasterPropertyDescriptor(Object object)
	{
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_Aggregator_buildmaster_feature"), getString("_UI_PropertyDescriptor_description",
						"_UI_Aggregator_buildmaster_feature", "_UI_Aggregator_type"),
				AggregatorPackage.Literals.AGGREGATOR__BUILDMASTER, true, false, false, null, null, null));
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
	 * This adds a property descriptor for the Maven Mappings feature. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addMavenMappingsPropertyDescriptor(Object object)
	{
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_Aggregator_mavenMappings_feature"), getString("_UI_PropertyDescriptor_description",
						"_UI_Aggregator_mavenMappings_feature", "_UI_Aggregator_type"),
				AggregatorPackage.Literals.AGGREGATOR__MAVEN_MAPPINGS, true, false, true, null, null, null));
	}

	/**
	 * This adds a property descriptor for the Maven Result feature. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addMavenResultPropertyDescriptor(Object object)
	{
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_Aggregator_mavenResult_feature"), getString("_UI_PropertyDescriptor_description",
						"_UI_Aggregator_mavenResult_feature", "_UI_Aggregator_type"),
				AggregatorPackage.Literals.AGGREGATOR__MAVEN_RESULT, true, false, false,
				ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Packed Strategy feature. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addPackedStrategyPropertyDescriptor(Object object)
	{
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_Aggregator_packedStrategy_feature"), getString("_UI_PropertyDescriptor_description",
						"_UI_Aggregator_packedStrategy_feature", "_UI_Aggregator_type"),
				AggregatorPackage.Literals.AGGREGATOR__PACKED_STRATEGY, true, false, false,
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
	 * @generated NOT
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object)
	{
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add(createChildParameter(AggregatorPackage.Literals.AGGREGATOR__CONFIGURATIONS,
				AggregatorFactory.eINSTANCE.createConfiguration()));

		newChildDescriptors.add(createChildParameter(AggregatorPackage.Literals.AGGREGATOR__CONTRIBUTIONS,
				AggregatorFactory.eINSTANCE.createContribution()));

		newChildDescriptors.add(createChildParameter(AggregatorPackage.Literals.AGGREGATOR__CONTACTS,
				AggregatorFactory.eINSTANCE.createContact()));

		newChildDescriptors.add(createChildParameter(AggregatorPackage.Literals.AGGREGATOR__CUSTOM_CATEGORIES,
				AggregatorFactory.eINSTANCE.createCustomCategory()));

		newChildDescriptors.add(createChildParameter(AggregatorPackage.Literals.AGGREGATOR__VALIDATION_REPOSITORIES,
				AggregatorFactory.eINSTANCE.createMetadataRepositoryReference()));

		newChildDescriptors.add(createChildParameter(AggregatorPackage.Literals.AGGREGATOR__MAVEN_MAPPINGS,
				AggregatorFactory.eINSTANCE.createMavenMapping()));
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
