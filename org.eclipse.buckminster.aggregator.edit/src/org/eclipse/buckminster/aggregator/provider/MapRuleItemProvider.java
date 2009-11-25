/**
 * Copyright (c) 2006-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.provider;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.buckminster.aggregator.AggregatorPackage;
import org.eclipse.buckminster.aggregator.ExclusionRule;
import org.eclipse.buckminster.aggregator.InstallableUnitReference;
import org.eclipse.buckminster.aggregator.MapRule;
import org.eclipse.buckminster.aggregator.MappedRepository;
import org.eclipse.buckminster.aggregator.p2.InstallableUnit;
import org.eclipse.buckminster.aggregator.util.InstallableUnitUtils;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
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
import org.eclipse.equinox.internal.provisional.p2.metadata.query.MatchQuery;
import org.eclipse.equinox.internal.provisional.p2.metadata.query.Query;

/**
 * This is the item provider adapter for a {@link org.eclipse.buckminster.aggregator.MapRule} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class MapRuleItemProvider extends InstallableUnitReferenceItemProvider implements IEditingDomainItemProvider,
		IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource,
		IItemColorProvider, IItemFontProvider
{
	/**
	 * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public MapRuleItemProvider(AdapterFactory adapterFactory)
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

			addDescriptionPropertyDescriptor(object);
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
		String label = crop(((MapRule)object).getDescription());
		return label == null || label.length() == 0
				? getString("_UI_MapRule_type")
				: getString("_UI_MapRule_type") + " " + label;
	}

	/**
	 * 
	 * @generated NOT
	 */
	@Override
	public void notifyChanged(Notification notification)
	{
		notifyChangedGen(notification);

		if(notification.getEventType() != Notification.SET)
			return;
		MapRule notifier = ((MapRule)notification.getNotifier());
		switch(notification.getFeatureID(ExclusionRule.class))
		{
		case AggregatorPackage.EXCLUSION_RULE__INSTALLABLE_UNIT:
			fireNotifyChanged(new ViewerNotification(notification, notifier, true, false));

			Set<Object> affectedNodes = new HashSet<Object>();
			affectedNodes.add(notifier);

			// Go through all direct ancestors first
			EObject container = ((EObject)notifier).eContainer();
			affectedNodes.add(((EObject)notifier).eResource());
			while(container != null)
			{
				affectedNodes.add(container);
				container = container.eContainer();
			}
			for(Object affectedNode : affectedNodes)
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

		switch(notification.getFeatureID(MapRule.class))
		{
		case AggregatorPackage.MAP_RULE__DESCRIPTION:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
			return;
		}
		super.notifyChanged(notification);
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

	@Override
	protected List<? extends InstallableUnitReference> getContainerChildren(MappedRepository container)
	{
		return container.getMapRules();
	}

	@Override
	protected Query getInstallableUnitQuery()
	{
		return new MatchQuery()
		{
			@Override
			public boolean isMatch(Object candidate)
			{
				switch(InstallableUnitUtils.getType((InstallableUnit)candidate))
				{
				case PRODUCT:
				case FEATURE:
					return true;
				}
				return false;
			}
		};
	}
}
