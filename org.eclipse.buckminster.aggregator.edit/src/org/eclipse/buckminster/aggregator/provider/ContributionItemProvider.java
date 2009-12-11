/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.buckminster.aggregator.Aggregator;
import org.eclipse.buckminster.aggregator.AggregatorFactory;
import org.eclipse.buckminster.aggregator.AggregatorPackage;
import org.eclipse.buckminster.aggregator.Contribution;
import org.eclipse.buckminster.aggregator.CustomCategory;
import org.eclipse.buckminster.aggregator.EnabledStatusProvider;
import org.eclipse.buckminster.aggregator.Feature;
import org.eclipse.buckminster.aggregator.MappedRepository;
import org.eclipse.buckminster.aggregator.MappedUnit;
import org.eclipse.buckminster.aggregator.MavenMapping;
import org.eclipse.buckminster.aggregator.p2.InstallableUnit;
import org.eclipse.buckminster.aggregator.p2.MetadataRepository;
import org.eclipse.buckminster.aggregator.p2view.IUPresentation;
import org.eclipse.buckminster.aggregator.p2view.MetadataRepositoryStructuredView;
import org.eclipse.buckminster.aggregator.util.ItemSorter;
import org.eclipse.buckminster.aggregator.util.ItemUtils;
import org.eclipse.buckminster.aggregator.util.AddIUsToContributionCommand;
import org.eclipse.buckminster.aggregator.util.ResourceUtils;
import org.eclipse.buckminster.aggregator.util.ItemSorter.ItemGroup;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
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
 * This is the item provider adapter for a {@link org.eclipse.buckminster.aggregator.Contribution} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class ContributionItemProvider extends AggregatorItemProviderAdapter implements IEditingDomainItemProvider,
		IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource,
		IItemColorProvider, IItemFontProvider
{
	static class DynamicItemPropertyDescriptor extends AggregatorItemPropertyDescriptor
	{

		public DynamicItemPropertyDescriptor(AdapterFactory adapterFactory, ResourceLocator resourceLocator,
				String displayName, String description, EStructuralFeature feature, boolean isSettable,
				boolean multiLine, boolean sortChoices, Object staticImage, String category, String[] filterFlags)
		{
			super(adapterFactory, resourceLocator, displayName, description, feature, isSettable, multiLine,
					sortChoices, staticImage, category, filterFlags);
		}

		@Override
		public boolean canSetProperty(Object object)
		{
			boolean result = super.canSetProperty(object);

			if(result)
			{
				if(object instanceof Contribution)
					result = ((Contribution)object).isEnabled()
							|| AggregatorPackage.Literals.ENABLED_STATUS_PROVIDER__ENABLED.getName().equals(
									getId(object));
				else
				{
					MappedRepository mappedRepository = findMappedRepository(object);
					if(mappedRepository != null)
					{
						Contribution contribution = (Contribution)((EObject)mappedRepository).eContainer();

						if(contribution.isEnabled())
						{
							result = object instanceof MappedRepository
									&& AggregatorPackage.Literals.ENABLED_STATUS_PROVIDER__ENABLED.getName().equals(
											getId(mappedRepository)) || mappedRepository.isEnabled();

							if(result && object instanceof MappedUnit)
								result = (AggregatorPackage.Literals.ENABLED_STATUS_PROVIDER__ENABLED.getName().equals(
										getId(object)) || ((MappedUnit)object).isEnabled())
										&& !((MappedUnit)object).isMappedRepositoryBroken();
						}
						else
							result = false;
					}
				}
			}

			return result;
		}

		private MappedRepository findMappedRepository(Object object)
		{
			while(object != null)
			{
				if(object instanceof MappedRepository)
					return (MappedRepository)object;
				object = ((EObject)object).eContainer();
			}

			return null;
		}
	}

	/**
	 * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ContributionItemProvider(AdapterFactory adapterFactory)
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
			childrenFeatures.add(AggregatorPackage.Literals.CONTRIBUTION__REPOSITORIES);
			childrenFeatures.add(AggregatorPackage.Literals.CONTRIBUTION__MAVEN_MAPPINGS);
		}
		return childrenFeatures;
	}

	/**
	 * Grey out the label if this item is (directly or indirectly) disabled
	 */
	@Override
	public Object getForeground(Object object)
	{
		return ((Contribution)object).isEnabled()
				? null
				: IItemColorProvider.GRAYED_OUT_COLOR;
	}

	/**
	 * This returns Contribution.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public Object getImage(Object object)
	{
		return overlayImage(object, getResourceLocator().getImage(
				"full/obj16/Contribution" + (((Contribution)object).isEnabled()
						? ""
						: "Disabled")));
	}

	/**
	 * Allow adding children only if the contribution enabled
	 */
	@Override
	public Collection<?> getNewChildDescriptors(Object object, EditingDomain editingDomain, Object sibling)
	{
		if(!((Contribution)object).isEnabled())
			return Collections.emptySet();

		return super.getNewChildDescriptors(object, editingDomain, sibling);
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

			addEnabledPropertyDescriptor(object);
			addDescriptionPropertyDescriptor(object);
			addLabelPropertyDescriptor(object);
			addContactsPropertyDescriptor(object);
			addMavenMappingsPropertyDescriptor(object);
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
		String label = ((Contribution)object).getLabel();
		return label == null || label.length() == 0
				? getString("_UI_Contribution_type")
				: getString("_UI_Contribution_type") + " " + label;
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

		// Update also content if enabled flag has been changed
		if(notification.getFeatureID(Contribution.class) == AggregatorPackage.CONTRIBUTION__ENABLED)
		{
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));

			Set<Object> affectedNodeLabels = new HashSet<Object>();
			Set<Object> affectedNodes = new HashSet<Object>();

			// Go through all direct ancestors first, and add also the top resource
			EObject container = ((EObject)notification.getNotifier());
			affectedNodeLabels.add(container.eResource());
			while(container != null)
			{
				affectedNodeLabels.add(container);
				container = container.eContainer();
			}

			boolean newValue = notification.getNewBooleanValue();
			// Browse all mapped repositories which may have changed their virtual status (inherently enabled/disabled)
			for(MappedRepository mappedRepository : ((Contribution)notification.getNotifier()).getRepositories(!newValue))
			{
				if(newValue)
					ResourceUtils.loadResourceForMappedRepository(mappedRepository);

				affectedNodes.add(mappedRepository);

				// Browse all mapped units which may have changed their virtual status (inherently enabled/disabled)
				for(MappedUnit unit : mappedRepository.getUnits(!notification.getNewBooleanValue()))
				{
					affectedNodes.add(unit);
					// And now, find all categories which may contain the feature just being enabled/disabled
					if(unit instanceof Feature)
						for(CustomCategory category : ((Feature)unit).getCategories())
							affectedNodes.add(category);
				}
			}

			for(Object affectedNode : affectedNodes)
				fireNotifyChanged(new ViewerNotification(notification, affectedNode, true, true));
			for(Object affectedNode : affectedNodeLabels)
				fireNotifyChanged(new ViewerNotification(notification, affectedNode, false, true));

			if(!newValue)
				ResourceUtils.cleanUpResources((Aggregator)((EObject)notification.getNotifier()).eContainer());
		}
		// If a repository is removed, update possible warning overlays
		else if(notification.getEventType() == Notification.REMOVE
				&& (notification.getOldValue() instanceof MappedRepository || notification.getOldValue() instanceof MavenMapping))
		{
			Set<Object> affectedNodes = new HashSet<Object>();

			// Go through all direct ancestors first, and add also the top resource
			EObject container = ((EObject)notification.getNotifier());
			affectedNodes.add(container.eResource());
			while(container != null)
			{
				affectedNodes.add(container);
				container = container.eContainer();
			}

			if(notification.getOldValue() instanceof MappedRepository)
			{
				for(Feature mappedFeature : ((MappedRepository)notification.getOldValue()).getFeatures())
					// And now, find all categories which may contain the feature or the repository just being removed
					for(CustomCategory category : mappedFeature.getCategories())
						affectedNodes.add(category);

				ResourceUtils.cleanUpResources((Aggregator)((EObject)notification.getNotifier()).eContainer());
			}

			for(Object affectedNode : affectedNodes)
				fireNotifyChanged(new ViewerNotification(notification, affectedNode, false, true));
		}
		// If a repository is added (e.g. Undo Delete), reload MDR
		else if(notification.getEventType() == Notification.ADD
				&& (notification.getNewValue() instanceof MappedRepository || notification.getNewValue() instanceof MavenMapping))
		{
			if(notification.getNewValue() instanceof MappedRepository)
				ResourceUtils.loadResourceForMappedRepository((MappedRepository)notification.getNewValue());

			Set<Object> affectedNodes = new HashSet<Object>();
			// Go through all ancestors to mark warnings
			EObject container = ((EObject)notification.getNotifier());
			affectedNodes.add(container.eResource());
			while(container != null)
			{
				affectedNodes.add(container);
				container = container.eContainer();
			}
			for(Object affectedNode : affectedNodes)
				fireNotifyChanged(new ViewerNotification(notification, affectedNode, false, true));
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

		switch(notification.getFeatureID(Contribution.class))
		{
		case AggregatorPackage.CONTRIBUTION__ENABLED:
		case AggregatorPackage.CONTRIBUTION__DESCRIPTION:
		case AggregatorPackage.CONTRIBUTION__LABEL:
		case AggregatorPackage.CONTRIBUTION__CONTACTS:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
			return;
		case AggregatorPackage.CONTRIBUTION__REPOSITORIES:
		case AggregatorPackage.CONTRIBUTION__MAVEN_MAPPINGS:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
			return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds a property descriptor for the Contacts feature. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addContactsPropertyDescriptor(Object object)
	{
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_Contribution_contacts_feature"), getString("_UI_PropertyDescriptor_description",
						"_UI_Contribution_contacts_feature", "_UI_Contribution_type"),
				AggregatorPackage.Literals.CONTRIBUTION__CONTACTS, true, false, false, null, null, null));
	}

	/**
	 * This adds a property descriptor for the Description feature. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addDescriptionPropertyDescriptor(Object object)
	{
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_DescriptionProvider_description_feature"), getString(
						"_UI_PropertyDescriptor_description", "_UI_DescriptionProvider_description_feature",
						"_UI_DescriptionProvider_type"), AggregatorPackage.Literals.DESCRIPTION_PROVIDER__DESCRIPTION,
				true, true, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Enabled feature. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addEnabledPropertyDescriptor(Object object)
	{
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_EnabledStatusProvider_enabled_feature"), getString("_UI_PropertyDescriptor_description",
						"_UI_EnabledStatusProvider_enabled_feature", "_UI_EnabledStatusProvider_type"),
				AggregatorPackage.Literals.ENABLED_STATUS_PROVIDER__ENABLED, true, false, false,
				ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
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
				getString("_UI_Contribution_label_feature"), getString("_UI_PropertyDescriptor_description",
						"_UI_Contribution_label_feature", "_UI_Contribution_type"),
				AggregatorPackage.Literals.CONTRIBUTION__LABEL, true, false, false,
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
				getString("_UI_Contribution_mavenMappings_feature"), getString("_UI_PropertyDescriptor_description",
						"_UI_Contribution_mavenMappings_feature", "_UI_Contribution_type"),
				AggregatorPackage.Literals.CONTRIBUTION__MAVEN_MAPPINGS, true, false, true, null, null, null));
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

		newChildDescriptors.add(createChildParameter(AggregatorPackage.Literals.CONTRIBUTION__REPOSITORIES,
				AggregatorFactory.eINSTANCE.createMappedRepository()));

		newChildDescriptors.add(createChildParameter(AggregatorPackage.Literals.CONTRIBUTION__MAVEN_MAPPINGS,
				AggregatorFactory.eINSTANCE.createMavenMapping()));
	}

	/**
	 * Supports DnD from MDRs and IUs to Contribution
	 */
	@Override
	protected Command createDragAndDropCommand(EditingDomain domain, Object owner, float location, int operations,
			int operation, Collection<?> collection)
	{
		Command command = createAddIUsToContributionCommand(owner, collection);

		if(command != null)
			return command;

		return super.createDragAndDropCommand(domain, owner, location, operations, operation, collection);
	}

	/**
	 * Creates a dynamic property descriptor which alters the readonly attribute according to the "enabled" flag
	 */
	@Override
	protected ItemPropertyDescriptor createItemPropertyDescriptor(AdapterFactory adapterFactory,
			ResourceLocator resourceLocator, String displayName, String description, EStructuralFeature feature,
			boolean isSettable, boolean multiLine, boolean sortChoices, Object staticImage, String category,
			String[] filterFlags)
	{
		return new ContributionItemProvider.DynamicItemPropertyDescriptor(adapterFactory, resourceLocator, displayName,
				description, feature, isSettable, multiLine, sortChoices, staticImage, category, filterFlags);
	}

	/**
	 * Allow deleting a child from mapped repository only if the contribution is enabled
	 */
	@Override
	@Deprecated
	protected Command createRemoveCommand(EditingDomain domain, EObject owner, EReference feature,
			Collection<?> collection)
	{
		if(((Contribution)owner).isEnabled())
			return new RemoveCommand(domain, owner, feature, collection);

		return UnexecutableCommand.INSTANCE;
	}

	/**
	 * Allow deleting a child from mapped repository only if the contribution is enabled
	 */
	@Override
	protected Command createRemoveCommand(EditingDomain domain, EObject owner, EStructuralFeature feature,
			Collection<?> collection)
	{
		if(feature instanceof EReference)
		{
			return createRemoveCommand(domain, owner, (EReference)feature, collection);
		}

		if(((Contribution)owner).isEnabled())
			return new RemoveCommand(domain, owner, feature, collection);

		return UnexecutableCommand.INSTANCE;
	}

	/**
	 * Supports copy&paste from IUs to COntribution
	 */
	@Override
	protected Command factorAddCommand(EditingDomain domain, CommandParameter commandParameter)
	{
		Command command = createAddIUsToContributionCommand(commandParameter.getOwner(),
				commandParameter.getCollection());

		if(command != null)
			return command;

		return super.factorAddCommand(domain, commandParameter);
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

	@SuppressWarnings("unchecked")
	private Command createAddIUsToContributionCommand(Object owner, Collection<?> collection)
	{
		ItemSorter itemSorter = new ItemSorter(collection);

		if(((EnabledStatusProvider)owner).isEnabled()
				&& itemSorter.getTotalItemCount() > 0
				&& (itemSorter.getTotalItemCount() == (itemSorter.getGroupItems(ItemGroup.MDR).size() + itemSorter.getGroupItems(
						ItemGroup.IU).size()) || itemSorter.getTotalItemCount() == (itemSorter.getGroupItems(
						ItemGroup.MDR_STRUCTURED).size() + itemSorter.getGroupItems(ItemGroup.IU_STRUCTURED).size())))
		{
			List<MetadataRepository> mdrs = new ArrayList<MetadataRepository>();
			List<InstallableUnit> ius = new ArrayList<InstallableUnit>();

			mdrs.addAll((List<MetadataRepository>)itemSorter.getGroupItems(ItemGroup.MDR));
			mdrs.addAll(ItemUtils.getMDRs((List<MetadataRepositoryStructuredView>)itemSorter.getGroupItems(ItemGroup.MDR_STRUCTURED)));
			ius.addAll((List<InstallableUnit>)itemSorter.getGroupItems(ItemGroup.IU));
			ius.addAll(ItemUtils.getIUs((List<IUPresentation>)itemSorter.getGroupItems(ItemGroup.IU_STRUCTURED)));

			return new AddIUsToContributionCommand((Contribution)owner, mdrs, ius);
		}

		return null;
	}
}
