/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.mspec.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.buckminster.model.common.CommonFactory;
import org.eclipse.buckminster.model.common.ConflictResolution;
import org.eclipse.buckminster.model.common.provider.PropertiesItemProvider;
import org.eclipse.buckminster.model.common.provider.util.ExtensionPropertyDescriptor;
import org.eclipse.buckminster.mspec.MaterializationDirective;
import org.eclipse.buckminster.mspec.MspecPackage;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
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
 * This is the item provider adapter for a
 * {@link org.eclipse.buckminster.mspec.MaterializationDirective} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class MaterializationDirectiveItemProvider extends PropertiesItemProvider implements IEditingDomainItemProvider,
		IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public MaterializationDirectiveItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to
	 * deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand},
	 * {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in
	 * {@link #createCommand}. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(MspecPackage.Literals.MATERIALIZATION_DIRECTIVE__DOCUMENTATION);
		}
		return childrenFeatures;
	}

	/**
	 * This returns MaterializationDirective.gif. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/MaterializationDirective"));
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

			addConflictResolutionPropertyDescriptor(object);
			addInstallLocationPropertyDescriptor(object);
			addMaterializerPropertyDescriptor(object);
			addWorkspaceLocationPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This returns the label text for the adapted class. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public String getText(Object object) {
		ConflictResolution labelValue = ((MaterializationDirective) object).getConflictResolution();
		String label = labelValue == null ? null : labelValue.toString();
		return label == null || label.length() == 0 ? getString("_UI_MaterializationDirective_type") : getString("_UI_MaterializationDirective_type")
				+ " " + label;
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

		switch (notification.getFeatureID(MaterializationDirective.class)) {
			case MspecPackage.MATERIALIZATION_DIRECTIVE__CONFLICT_RESOLUTION:
			case MspecPackage.MATERIALIZATION_DIRECTIVE__INSTALL_LOCATION:
			case MspecPackage.MATERIALIZATION_DIRECTIVE__MATERIALIZER:
			case MspecPackage.MATERIALIZATION_DIRECTIVE__WORKSPACE_LOCATION:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case MspecPackage.MATERIALIZATION_DIRECTIVE__DOCUMENTATION:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds a property descriptor for the Conflict Resolution feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addConflictResolutionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
				getResourceLocator(),
				getString("_UI_MaterializationDirective_conflictResolution_feature"),
				getString("_UI_PropertyDescriptor_description", "_UI_MaterializationDirective_conflictResolution_feature",
						"_UI_MaterializationDirective_type"), MspecPackage.Literals.MATERIALIZATION_DIRECTIVE__CONFLICT_RESOLUTION, true, false,
				false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Install Location feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addInstallLocationPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
				getResourceLocator(),
				getString("_UI_MaterializationDirective_installLocation_feature"),
				getString("_UI_PropertyDescriptor_description", "_UI_MaterializationDirective_installLocation_feature",
						"_UI_MaterializationDirective_type"), MspecPackage.Literals.MATERIALIZATION_DIRECTIVE__INSTALL_LOCATION, true, false, false,
				ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Materializer feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	protected void addMaterializerPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ExtensionPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
				getResourceLocator(), getString("_UI_MaterializationDirective_materializer_feature"), getString("_UI_PropertyDescriptor_description",
						"_UI_MaterializationDirective_materializer_feature", "_UI_MaterializationDirective_type"),
				MspecPackage.Literals.MATERIALIZATION_DIRECTIVE__MATERIALIZER, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null,
				null, "org.eclipse.buckminster.core.materializers", "id", true));
	}

	/**
	 * This adds a property descriptor for the Workspace Location feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addWorkspaceLocationPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
				getResourceLocator(),
				getString("_UI_MaterializationDirective_workspaceLocation_feature"),
				getString("_UI_PropertyDescriptor_description", "_UI_MaterializationDirective_workspaceLocation_feature",
						"_UI_MaterializationDirective_type"), MspecPackage.Literals.MATERIALIZATION_DIRECTIVE__WORKSPACE_LOCATION, true, false,
				false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
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

		newChildDescriptors.add(createChildParameter(MspecPackage.Literals.MATERIALIZATION_DIRECTIVE__DOCUMENTATION,
				CommonFactory.eINSTANCE.createDocumentation()));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper
		// feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

}
