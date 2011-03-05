/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.mspec.provider;

import java.util.Collection;
import java.util.List;

import java.util.regex.Pattern;

import org.eclipse.buckminster.model.common.provider.util.ExtensionPropertyDescriptor;
import org.eclipse.buckminster.mspec.MaterializationNode;
import org.eclipse.buckminster.mspec.MspecPackage;
import org.eclipse.buckminster.osgi.filter.Filter;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

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
 * This is the item provider adapter for a
 * {@link org.eclipse.buckminster.mspec.MaterializationNode} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class MaterializationNodeItemProvider extends MaterializationDirectiveItemProvider implements IEditingDomainItemProvider,
		IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public MaterializationNodeItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns MaterializationNode.gif. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/MaterializationNode"));
	}

	/**
	 * This returns the property descriptors for the adapted class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addNamePatternPropertyDescriptor(object);
			addLeafArtifactPropertyDescriptor(object);
			addComponentTypePropertyDescriptor(object);
			addResourcePathPropertyDescriptor(object);
			addExcludePropertyDescriptor(object);
			addBindingNamePatternPropertyDescriptor(object);
			addBindingNameReplacementPropertyDescriptor(object);
			addUnpackPropertyDescriptor(object);
			addFilterPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This returns the label text for the adapted class. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */

	@Override
	public String getText(Object object) {
		MaterializationNode node = (MaterializationNode) object;
		StringBuilder bld = new StringBuilder();
		bld.append(getString("_UI_MaterializationNode_type"));
		Pattern pattern = node.getNamePattern();
		if (pattern != null) {
			bld.append(' ');
			bld.append(pattern);
		}
		Filter rule = node.getFilter();
		if (rule != null) {
			bld.append(' ');
			bld.append(rule);
		}
		return bld.toString();
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to
	 * update any cached children and by creating a viewer notification, which
	 * it passes to {@link #fireNotifyChanged}. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(MaterializationNode.class)) {
			case MspecPackage.MATERIALIZATION_NODE__NAME_PATTERN:
			case MspecPackage.MATERIALIZATION_NODE__LEAF_ARTIFACT:
			case MspecPackage.MATERIALIZATION_NODE__COMPONENT_TYPE:
			case MspecPackage.MATERIALIZATION_NODE__RESOURCE_PATH:
			case MspecPackage.MATERIALIZATION_NODE__EXCLUDE:
			case MspecPackage.MATERIALIZATION_NODE__BINDING_NAME_PATTERN:
			case MspecPackage.MATERIALIZATION_NODE__BINDING_NAME_REPLACEMENT:
			case MspecPackage.MATERIALIZATION_NODE__FILTER:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds a property descriptor for the Binding Name Pattern feature.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addBindingNamePatternPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(
						((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(),
						getString("_UI_MaterializationNode_bindingNamePattern_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_MaterializationNode_bindingNamePattern_feature",
								"_UI_MaterializationNode_type"), MspecPackage.Literals.MATERIALIZATION_NODE__BINDING_NAME_PATTERN, true, false,
						false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Binding Name Replacement feature.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addBindingNameReplacementPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
				getResourceLocator(),
				getString("_UI_MaterializationNode_bindingNameReplacement_feature"),
				getString("_UI_PropertyDescriptor_description", "_UI_MaterializationNode_bindingNameReplacement_feature",
						"_UI_MaterializationNode_type"), MspecPackage.Literals.MATERIALIZATION_NODE__BINDING_NAME_REPLACEMENT, true, false, false,
				ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Component Type feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	protected void addComponentTypePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ExtensionPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
				getResourceLocator(), getString("_UI_MaterializationNode_componentType_feature"), getString("_UI_PropertyDescriptor_description",
						"_UI_MaterializationNode_componentType_feature", "_UI_MaterializationNode_type"),
				MspecPackage.Literals.MATERIALIZATION_NODE__COMPONENT_TYPE, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null,
				null, "org.eclipse.buckminster.core.componentTypes", "id", true));
	}

	/**
	 * This adds a property descriptor for the Exclude feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addExcludePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
				getResourceLocator(), getString("_UI_MaterializationNode_exclude_feature"),
				getString("_UI_PropertyDescriptor_description", "_UI_MaterializationNode_exclude_feature", "_UI_MaterializationNode_type"),
				MspecPackage.Literals.MATERIALIZATION_NODE__EXCLUDE, true, false, false, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Filter feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addFilterPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
				getResourceLocator(), getString("_UI_MaterializationNode_filter_feature"),
				getString("_UI_PropertyDescriptor_description", "_UI_MaterializationNode_filter_feature", "_UI_MaterializationNode_type"),
				MspecPackage.Literals.MATERIALIZATION_NODE__FILTER, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Leaf Artifact feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addLeafArtifactPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(
						((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(),
						getString("_UI_MaterializationNode_leafArtifact_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_MaterializationNode_leafArtifact_feature",
								"_UI_MaterializationNode_type"), MspecPackage.Literals.MATERIALIZATION_NODE__LEAF_ARTIFACT, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Name Pattern feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addNamePatternPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(
						((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(),
						getString("_UI_MaterializationNode_namePattern_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_MaterializationNode_namePattern_feature", "_UI_MaterializationNode_type"),
						MspecPackage.Literals.MATERIALIZATION_NODE__NAME_PATTERN, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
						null, null));
	}

	/**
	 * This adds a property descriptor for the Resource Path feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addResourcePathPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(
						((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(),
						getString("_UI_MaterializationNode_resourcePath_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_MaterializationNode_resourcePath_feature",
								"_UI_MaterializationNode_type"), MspecPackage.Literals.MATERIALIZATION_NODE__RESOURCE_PATH, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Unpack feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addUnpackPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
				getResourceLocator(), getString("_UI_MaterializationNode_unpack_feature"),
				getString("_UI_PropertyDescriptor_description", "_UI_MaterializationNode_unpack_feature", "_UI_MaterializationNode_type"),
				MspecPackage.Literals.MATERIALIZATION_NODE__UNPACK, true, false, true, null, null, null));
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s
	 * describing the children that can be created under this object. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);
	}

}
