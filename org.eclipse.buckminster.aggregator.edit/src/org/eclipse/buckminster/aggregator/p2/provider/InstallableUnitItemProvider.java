/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.p2.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.buckminster.aggregator.p2.InstallableUnit;
import org.eclipse.buckminster.aggregator.p2.P2Package;
import org.eclipse.buckminster.aggregator.provider.AggregatorEditPlugin;
import org.eclipse.buckminster.aggregator.provider.AggregatorItemProviderAdapter;
import org.eclipse.buckminster.aggregator.util.GeneralUtils;
import org.eclipse.buckminster.aggregator.util.InstallableUnitUtils;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
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
import org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.internal.provisional.p2.metadata.VersionedId;

/**
 * This is the item provider adapter for a {@link org.eclipse.buckminster.aggregator.p2.InstallableUnit} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class InstallableUnitItemProvider extends AggregatorItemProviderAdapter implements IEditingDomainItemProvider,
		IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource,
		IItemColorProvider, IItemFontProvider
{
	/**
	 * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public InstallableUnitItemProvider(AdapterFactory adapterFactory)
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
			childrenFeatures.add(P2Package.Literals.IINSTALLABLE_UNIT__TOUCHPOINT_TYPE);
			childrenFeatures.add(P2Package.Literals.IINSTALLABLE_UNIT__UPDATE_DESCRIPTOR);
			childrenFeatures.add(P2Package.Literals.IINSTALLABLE_UNIT__LICENSE);
			childrenFeatures.add(P2Package.Literals.IINSTALLABLE_UNIT__COPYRIGHT);
			childrenFeatures.add(P2Package.Literals.INSTALLABLE_UNIT__ARTIFACT_LIST);
			childrenFeatures.add(P2Package.Literals.INSTALLABLE_UNIT__PROVIDED_CAPABILITY_LIST);
			childrenFeatures.add(P2Package.Literals.INSTALLABLE_UNIT__REQUIRED_CAPABILITY_LIST);
			childrenFeatures.add(P2Package.Literals.INSTALLABLE_UNIT__META_REQUIRED_CAPABILITY_LIST);
			childrenFeatures.add(P2Package.Literals.INSTALLABLE_UNIT__PROPERTY_MAP);
			childrenFeatures.add(P2Package.Literals.INSTALLABLE_UNIT__TOUCHPOINT_DATA_LIST);
		}
		return childrenFeatures;
	}

	/**
	 * This returns InstallableUnit.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object getImage(Object object)
	{
		return overlayImage(object, getResourceLocator().getImage("full/obj16/InstallableUnit"));
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

			addFilterPropertyDescriptor(object);
			addIdPropertyDescriptor(object);
			addTouchpointTypePropertyDescriptor(object);
			addVersionPropertyDescriptor(object);
			addResolvedPropertyDescriptor(object);
			addSingletonPropertyDescriptor(object);
			addUpdateDescriptorPropertyDescriptor(object);
			addLicensePropertyDescriptor(object);
			addCopyrightPropertyDescriptor(object);
			addArtifactListPropertyDescriptor(object);
			addProvidedCapabilityListPropertyDescriptor(object);
			addRequiredCapabilityListPropertyDescriptor(object);
			addMetaRequiredCapabilityListPropertyDescriptor(object);
			addPropertyMapPropertyDescriptor(object);
			addTouchpointDataListPropertyDescriptor(object);
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
		InstallableUnit iu = (InstallableUnit)object;
		String label = Trivial.trim(iu.getId());

		if(label == null)
		{
			VersionedId vn = InstallableUnitUtils.getVersionedNameFromProxy(iu);
			if(vn != null)
				label = vn.getId() + " / " + GeneralUtils.stringifyVersion(vn.getVersion()) + " (missing)";
		}
		else if("true".equalsIgnoreCase(iu.getProperty(IInstallableUnit.PROP_TYPE_CATEGORY)))
		{
			// The id and version are meaningless in categories. We display the name
			// instead.
			String name = iu.getProperty(IInstallableUnit.PROP_NAME);
			if(name != null)
				label = name;
		}
		else
		{
			String name = GeneralUtils.getLocalizedProperty(iu, IInstallableUnit.PROP_NAME);
			if(name != null && name.startsWith("%"))
				name = null;

			label += " / " + GeneralUtils.stringifyVersion(iu.getVersion()) + (name != null && name.length() > 0
					? " (" + name + ")"
					: "");
		}

		return label == null || label.length() == 0
				? getString("_UI_InstallableUnit_type")
				: label;
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

		switch(notification.getFeatureID(InstallableUnit.class))
		{
		case P2Package.INSTALLABLE_UNIT__FILTER:
		case P2Package.INSTALLABLE_UNIT__ID:
		case P2Package.INSTALLABLE_UNIT__VERSION:
		case P2Package.INSTALLABLE_UNIT__RESOLVED:
		case P2Package.INSTALLABLE_UNIT__SINGLETON:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
			return;
		case P2Package.INSTALLABLE_UNIT__TOUCHPOINT_TYPE:
		case P2Package.INSTALLABLE_UNIT__UPDATE_DESCRIPTOR:
		case P2Package.INSTALLABLE_UNIT__LICENSE:
		case P2Package.INSTALLABLE_UNIT__COPYRIGHT:
		case P2Package.INSTALLABLE_UNIT__ARTIFACT_LIST:
		case P2Package.INSTALLABLE_UNIT__PROVIDED_CAPABILITY_LIST:
		case P2Package.INSTALLABLE_UNIT__REQUIRED_CAPABILITY_LIST:
		case P2Package.INSTALLABLE_UNIT__META_REQUIRED_CAPABILITY_LIST:
		case P2Package.INSTALLABLE_UNIT__PROPERTY_MAP:
		case P2Package.INSTALLABLE_UNIT__TOUCHPOINT_DATA_LIST:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
			return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds a property descriptor for the Artifact List feature. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addArtifactListPropertyDescriptor(Object object)
	{
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_InstallableUnit_artifactList_feature"), getString("_UI_PropertyDescriptor_description",
						"_UI_InstallableUnit_artifactList_feature", "_UI_InstallableUnit_type"),
				P2Package.Literals.INSTALLABLE_UNIT__ARTIFACT_LIST, false, false, false, null, null, null));
	}

	/**
	 * This adds a property descriptor for the Copyright feature. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addCopyrightPropertyDescriptor(Object object)
	{
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_IInstallableUnit_copyright_feature"), getString("_UI_PropertyDescriptor_description",
						"_UI_IInstallableUnit_copyright_feature", "_UI_IInstallableUnit_type"),
				P2Package.Literals.IINSTALLABLE_UNIT__COPYRIGHT, false, false, false, null, null, null));
	}

	/**
	 * This adds a property descriptor for the Filter feature. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addFilterPropertyDescriptor(Object object)
	{
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_IInstallableUnit_filter_feature"), getString("_UI_PropertyDescriptor_description",
						"_UI_IInstallableUnit_filter_feature", "_UI_IInstallableUnit_type"),
				P2Package.Literals.IINSTALLABLE_UNIT__FILTER, false, false, false,
				ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Id feature. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addIdPropertyDescriptor(Object object)
	{
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_IInstallableUnit_id_feature"), getString("_UI_PropertyDescriptor_description",
						"_UI_IInstallableUnit_id_feature", "_UI_IInstallableUnit_type"),
				P2Package.Literals.IINSTALLABLE_UNIT__ID, false, false, false,
				ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the License feature. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addLicensePropertyDescriptor(Object object)
	{
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_IInstallableUnit_license_feature"), getString("_UI_PropertyDescriptor_description",
						"_UI_IInstallableUnit_license_feature", "_UI_IInstallableUnit_type"),
				P2Package.Literals.IINSTALLABLE_UNIT__LICENSE, false, false, false, null, null, null));
	}

	/**
	 * This adds a property descriptor for the Meta Required Capability List feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addMetaRequiredCapabilityListPropertyDescriptor(Object object)
	{
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_InstallableUnit_metaRequiredCapabilityList_feature"), getString(
						"_UI_PropertyDescriptor_description", "_UI_InstallableUnit_metaRequiredCapabilityList_feature",
						"_UI_InstallableUnit_type"),
				P2Package.Literals.INSTALLABLE_UNIT__META_REQUIRED_CAPABILITY_LIST, false, false, false, null, null,
				null));
	}

	/**
	 * This adds a property descriptor for the Property Map feature. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addPropertyMapPropertyDescriptor(Object object)
	{
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_InstallableUnit_propertyMap_feature"), getString("_UI_PropertyDescriptor_description",
						"_UI_InstallableUnit_propertyMap_feature", "_UI_InstallableUnit_type"),
				P2Package.Literals.INSTALLABLE_UNIT__PROPERTY_MAP, false, false, false, null, null, null));
	}

	/**
	 * This adds a property descriptor for the Provided Capability List feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addProvidedCapabilityListPropertyDescriptor(Object object)
	{
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_InstallableUnit_providedCapabilityList_feature"), getString(
						"_UI_PropertyDescriptor_description", "_UI_InstallableUnit_providedCapabilityList_feature",
						"_UI_InstallableUnit_type"), P2Package.Literals.INSTALLABLE_UNIT__PROVIDED_CAPABILITY_LIST,
				false, false, false, null, null, null));
	}

	/**
	 * This adds a property descriptor for the Required Capability List feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addRequiredCapabilityListPropertyDescriptor(Object object)
	{
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_InstallableUnit_requiredCapabilityList_feature"), getString(
						"_UI_PropertyDescriptor_description", "_UI_InstallableUnit_requiredCapabilityList_feature",
						"_UI_InstallableUnit_type"), P2Package.Literals.INSTALLABLE_UNIT__REQUIRED_CAPABILITY_LIST,
				false, false, false, null, null, null));
	}

	/**
	 * This adds a property descriptor for the Resolved feature. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addResolvedPropertyDescriptor(Object object)
	{
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_IInstallableUnit_resolved_feature"), getString("_UI_PropertyDescriptor_description",
						"_UI_IInstallableUnit_resolved_feature", "_UI_IInstallableUnit_type"),
				P2Package.Literals.IINSTALLABLE_UNIT__RESOLVED, false, false, false,
				ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Singleton feature. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addSingletonPropertyDescriptor(Object object)
	{
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_IInstallableUnit_singleton_feature"), getString("_UI_PropertyDescriptor_description",
						"_UI_IInstallableUnit_singleton_feature", "_UI_IInstallableUnit_type"),
				P2Package.Literals.IINSTALLABLE_UNIT__SINGLETON, false, false, false,
				ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Touchpoint Data List feature. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	protected void addTouchpointDataListPropertyDescriptor(Object object)
	{
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_InstallableUnit_touchpointDataList_feature"), getString(
						"_UI_PropertyDescriptor_description", "_UI_InstallableUnit_touchpointDataList_feature",
						"_UI_InstallableUnit_type"), P2Package.Literals.INSTALLABLE_UNIT__TOUCHPOINT_DATA_LIST, false,
				false, false, null, null, null));
	}

	/**
	 * This adds a property descriptor for the Touchpoint Type feature. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addTouchpointTypePropertyDescriptor(Object object)
	{
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_IInstallableUnit_touchpointType_feature"), getString(
						"_UI_PropertyDescriptor_description", "_UI_IInstallableUnit_touchpointType_feature",
						"_UI_IInstallableUnit_type"), P2Package.Literals.IINSTALLABLE_UNIT__TOUCHPOINT_TYPE, false,
				false, false, null, null, null));
	}

	/**
	 * This adds a property descriptor for the Update Descriptor feature. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addUpdateDescriptorPropertyDescriptor(Object object)
	{
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_IInstallableUnit_updateDescriptor_feature"), getString(
						"_UI_PropertyDescriptor_description", "_UI_IInstallableUnit_updateDescriptor_feature",
						"_UI_IInstallableUnit_type"), P2Package.Literals.IINSTALLABLE_UNIT__UPDATE_DESCRIPTOR, false,
				false, false, null, null, null));
	}

	/**
	 * This adds a property descriptor for the Version feature. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addVersionPropertyDescriptor(Object object)
	{
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_IInstallableUnit_version_feature"), getString("_UI_PropertyDescriptor_description",
						"_UI_IInstallableUnit_version_feature", "_UI_IInstallableUnit_type"),
				P2Package.Literals.IINSTALLABLE_UNIT__VERSION, false, false, false,
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
	}

	/**
	 * Don't allow deletion of children
	 */
	@Override
	@Deprecated
	protected Command createRemoveCommand(EditingDomain domain, EObject owner, EReference feature,
			Collection<?> collection)
	{
		return UnexecutableCommand.INSTANCE;
	}

	/**
	 * Don't allow deletion of children
	 */
	@Override
	protected Command createRemoveCommand(EditingDomain domain, EObject owner, EStructuralFeature feature,
			Collection<?> collection)
	{
		return UnexecutableCommand.INSTANCE;
	}

	/**
	 * Don't allow setting attributes
	 */
	@Override
	protected Command createSetCommand(EditingDomain domain, EObject owner, EStructuralFeature feature, Object value)
	{
		return UnexecutableCommand.INSTANCE;
	}

	/**
	 * Don't allow setting attributes
	 */
	@Override
	protected Command createSetCommand(EditingDomain domain, EObject owner, EStructuralFeature feature, Object value,
			int index)
	{
		return UnexecutableCommand.INSTANCE;
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
