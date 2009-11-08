/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.provider;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.buckminster.aggregator.AggregatorPackage;
import org.eclipse.buckminster.aggregator.CustomCategory;
import org.eclipse.buckminster.aggregator.Feature;
import org.eclipse.buckminster.aggregator.InstallableUnitReference;
import org.eclipse.buckminster.aggregator.MappedUnit;
import org.eclipse.buckminster.aggregator.p2.InstallableUnit;
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
 * This is the item provider adapter for a {@link org.eclipse.buckminster.aggregator.MappedUnit} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class MappedUnitItemProvider extends InstallableUnitReferenceItemProvider implements IEditingDomainItemProvider,
		IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource,
		IItemColorProvider, IItemFontProvider

{
	protected static InstallableUnit getInstallableUnit(InstallableUnitReference iuRef)
	{
		MappedUnit mu = (MappedUnit)iuRef;
		return mu.getInstallableUnit(mu.isBranchEnabled() && !mu.isMappedRepositoryBroken());
	}

	/**
	 * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public MappedUnitItemProvider(AdapterFactory adapterFactory)
	{
		super(adapterFactory);
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
			addValidConfigurationsPropertyDescriptor(object);
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
		MappedUnit mappedUnit = (MappedUnit)object;
		return getString("_UI_MappedUnit_type") + " " + mappedUnit.isEnabled();
	}

	/**
	 * @generated NOT
	 */
	@Override
	public void notifyChanged(Notification notification)
	{
		notifyChangedGen(notification);

		boolean updateContent = false;
		boolean updateLabel = true;

		switch(notification.getFeatureID(MappedUnit.class))
		{
		case AggregatorPackage.MAPPED_UNIT__ENABLED:
			updateContent = true;
			updateLabel = false;
			// no break here, it is intentional
		case AggregatorPackage.MAPPED_UNIT__INSTALLABLE_UNIT:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), updateContent,
					updateLabel));

			Set<EObject> affectedNodes = new HashSet<EObject>();

			// Go through all direct ancestors first
			EObject container = ((EObject)notification.getNotifier()).eContainer();
			while(container != null)
			{
				affectedNodes.add(container);
				container = container.eContainer();
			}

			// And now, find all categories which may contain the feature just being enabled/disabled
			if(notification.getNotifier() instanceof Feature)
				for(CustomCategory category : ((Feature)notification.getNotifier()).getCategories())
					affectedNodes.add(category);

			for(EObject affectedNode : affectedNodes)
				fireNotifyChanged(new ViewerNotification(notification, affectedNode, false, true));

			return;
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

		switch(notification.getFeatureID(MappedUnit.class))
		{
		case AggregatorPackage.MAPPED_UNIT__ENABLED:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
			return;
		}
		super.notifyChanged(notification);
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
	 * This adds a property descriptor for the Valid Configurations feature. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	protected void addValidConfigurationsPropertyDescriptor(Object object)
	{
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_MappedUnit_validConfigurations_feature"), getString(
						"_UI_PropertyDescriptor_description", "_UI_MappedUnit_validConfigurations_feature",
						"_UI_MappedUnit_type"), AggregatorPackage.Literals.MAPPED_UNIT__VALID_CONFIGURATIONS, true,
				false, true, null, null, null));
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
}
