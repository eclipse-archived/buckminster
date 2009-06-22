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
import org.eclipse.buckminster.aggregator.p2.MetadataRepository;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
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
 * This is the item provider adapter for a {@link org.eclipse.buckminster.aggregator.MappedRepository} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class MappedRepositoryItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider,
		IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource
{
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public MappedRepositoryItemProvider(AdapterFactory adapterFactory)
	{
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object)
	{
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addMapVerbatimPropertyDescriptor(object);
			addMetadataRepositoryPropertyDescriptor(object);
			addLocationPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Map Verbatim feature.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected void addMapVerbatimPropertyDescriptor(Object object)
	{
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_MappedRepository_mapVerbatim_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_MappedRepository_mapVerbatim_feature", "_UI_MappedRepository_type"),
				 AggregatorPackage.Literals.MAPPED_REPOSITORY__MAP_VERBATIM,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Metadata Repository feature. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated NOT
	 */
	protected void addMetadataRepositoryPropertyDescriptor(Object object)
	{
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(
				((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_MappedRepository_metadataRepository_feature"), getString(
						"_UI_PropertyDescriptor_description", "_UI_MappedRepository_metadataRepository_feature",
						"_UI_MappedRepository_type"),
				AggregatorPackage.Literals.MAPPED_REPOSITORY__METADATA_REPOSITORY, true, false, true, null, null, null)
		{
			@Override
			public Collection<?> getChoiceOfValues(Object object)
			{
				// Provide a list of repositories that has not already been mapped
				//
				MappedRepository self = (MappedRepository)object;
				Aggregator aggregator = (Aggregator)self.eContainer().eContainer();
				Collection<?> repos = super.getChoiceOfValues(object);
				for(Contribution contribution : aggregator.getContributions())
				{
					for(MappedRepository mappedRepo : contribution.getRepositories())
					{
						if(mappedRepo == self)
							continue;
						MetadataRepository repo = mappedRepo.getMetadataRepository();
						if(repo != null)
							repos.remove(repo);
					}
				}
				return repos;
			}
		});
	}

	/**
	 * This adds a property descriptor for the Location feature.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected void addLocationPropertyDescriptor(Object object)
	{
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_MappedRepository_location_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_MappedRepository_location_feature", "_UI_MappedRepository_type"),
				 AggregatorPackage.Literals.MAPPED_REPOSITORY__LOCATION,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object)
	{
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(AggregatorPackage.Literals.MAPPED_REPOSITORY__PRODUCTS);
			childrenFeatures.add(AggregatorPackage.Literals.MAPPED_REPOSITORY__BUNDLES);
			childrenFeatures.add(AggregatorPackage.Literals.MAPPED_REPOSITORY__FEATURES);
			childrenFeatures.add(AggregatorPackage.Literals.MAPPED_REPOSITORY__CATEGORIES);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
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
	 * This returns MappedRepository.gif.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object)
	{
		return overlayImage(object, getResourceLocator().getImage("full/obj16/MappedRepository"));
	}

	/**
	 * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public String getText(Object object)
	{
		MappedRepository mappedRepository = (MappedRepository)object;
		MetadataRepository mdr = mappedRepository.getMetadataRepository();
		StringBuilder bld = new StringBuilder();
		bld.append(getString("_UI_MappedRepository_type"));
		bld.append(' ');
		if(mdr != null)
		{
			if(mdr.getName() != null)
			{
				bld.append(mdr.getName());
				bld.append(' ');
			}
			bld.append(mdr.getLocation());
			if(mappedRepository.isMapVerbatim())
				bld.append(" mapped verbatim");
		}
		else
			bld.append("not mapped");
		return bld.toString();
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

		switch (notification.getFeatureID(MappedRepository.class)) {
			case AggregatorPackage.MAPPED_REPOSITORY__MAP_VERBATIM:
			case AggregatorPackage.MAPPED_REPOSITORY__METADATA_REPOSITORY:
			case AggregatorPackage.MAPPED_REPOSITORY__LOCATION:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case AggregatorPackage.MAPPED_REPOSITORY__PRODUCTS:
			case AggregatorPackage.MAPPED_REPOSITORY__BUNDLES:
			case AggregatorPackage.MAPPED_REPOSITORY__FEATURES:
			case AggregatorPackage.MAPPED_REPOSITORY__CATEGORIES:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * Experimental. Loads a resource when the user types in a URL.
	 */
	@Override
	public void notifyChanged(Notification notification)
	{
		notifyChangedGen(notification);
		if(notification.getEventType() != Notification.SET)
			return;

		Object feature = notification.getFeature();
		if(!(feature instanceof EAttribute))
			return;

		if(!"location".equals(((EAttribute)feature).getName()))
			return;

		onLocationChange((MappedRepository)notification.getNotifier(), notification.getNewStringValue());
	}

	private void onLocationChange(MappedRepository repository, String location)
	{
		MetadataRepository repo = null;
		try
		{
			EObject topObject = repository;
			EObject parent = topObject.eContainer();
			while(parent != null)
			{
				topObject = parent;
				parent = topObject.eContainer();
			}
			ResourceSet topSet = topObject.eResource().getResourceSet();
			Resource mdr = topSet.getResource(URI.createGenericURI("p2", location, null), true);
			List<EObject> contents = mdr.getContents();
			if(contents.size() == 1)
				repo = (MetadataRepository)contents.get(0);
		}
		finally
		{
			repository.setMetadataRepository(repo);
		}
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object)
	{
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(AggregatorPackage.Literals.MAPPED_REPOSITORY__PRODUCTS,
				 AggregatorFactory.eINSTANCE.createProduct()));

		newChildDescriptors.add
			(createChildParameter
				(AggregatorPackage.Literals.MAPPED_REPOSITORY__BUNDLES,
				 AggregatorFactory.eINSTANCE.createBundle()));

		newChildDescriptors.add
			(createChildParameter
				(AggregatorPackage.Literals.MAPPED_REPOSITORY__FEATURES,
				 AggregatorFactory.eINSTANCE.createFeature()));

		newChildDescriptors.add
			(createChildParameter
				(AggregatorPackage.Literals.MAPPED_REPOSITORY__CATEGORIES,
				 AggregatorFactory.eINSTANCE.createCategory()));
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator()
	{
		return AggregatorEditPlugin.INSTANCE;
	}

}
