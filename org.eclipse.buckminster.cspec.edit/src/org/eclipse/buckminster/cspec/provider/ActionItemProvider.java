/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspec.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.buckminster.cspec.Action;
import org.eclipse.buckminster.cspec.CspecFactory;
import org.eclipse.buckminster.cspec.CspecPackage;

import org.eclipse.buckminster.model.common.CommonFactory;

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
 * {@link org.eclipse.buckminster.cspec.Action} object. <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class ActionItemProvider extends GroupItemProvider implements IEditingDomainItemProvider, IStructuredItemContentProvider,
		ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ActionItemProvider(AdapterFactory adapterFactory) {
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
			childrenFeatures.add(CspecPackage.Literals.ACTION__PROPERTIES);
			childrenFeatures.add(CspecPackage.Literals.ACTION__ACTOR_PROPERTIES);
			childrenFeatures.add(CspecPackage.Literals.ACTION__ACTOR);
			childrenFeatures.add(CspecPackage.Literals.ACTION__PRODUCT);
			childrenFeatures.add(CspecPackage.Literals.ACTION__PREREQUISITES_REBASE);
		}
		return childrenFeatures;
	}

	/**
	 * This returns the label text for
	 * {@link org.eclipse.emf.edit.command.CreateChildCommand}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
		Object childFeature = feature;
		Object childObject = child;

		boolean qualify = childFeature == CspecPackage.Literals.ACTION__PROPERTIES || childFeature == CspecPackage.Literals.ACTION__ACTOR_PROPERTIES;

		if (qualify) {
			return getString("_UI_CreateChild_text2", new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

	/**
	 * This returns Action.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Action"));
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

			addProductsPropertyDescriptor(object);
			addPrerequisitesAliasPropertyDescriptor(object);
			addProductAliasPropertyDescriptor(object);
			addUpToDatePolicyPropertyDescriptor(object);
			addProductFileCountPropertyDescriptor(object);
			addPatternPropertyDescriptor(object);
			addReplacementPropertyDescriptor(object);
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
		String label = ((Action) object).getName();
		return label == null || label.length() == 0 ? getString("_UI_Action_type") : getString("_UI_Action_type") + " " + label;
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

		switch (notification.getFeatureID(Action.class)) {
			case CspecPackage.ACTION__PREREQUISITES_ALIAS:
			case CspecPackage.ACTION__PRODUCT_ALIAS:
			case CspecPackage.ACTION__UP_TO_DATE_POLICY:
			case CspecPackage.ACTION__PRODUCT_FILE_COUNT:
			case CspecPackage.ACTION__PATTERN:
			case CspecPackage.ACTION__REPLACEMENT:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case CspecPackage.ACTION__PROPERTIES:
			case CspecPackage.ACTION__ACTOR_PROPERTIES:
			case CspecPackage.ACTION__ACTOR:
			case CspecPackage.ACTION__PRODUCT:
			case CspecPackage.ACTION__PREREQUISITES_REBASE:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds a property descriptor for the Pattern feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addPatternPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
				getResourceLocator(), getString("_UI_Action_pattern_feature"), getString("_UI_PropertyDescriptor_description",
						"_UI_Action_pattern_feature", "_UI_Action_type"), CspecPackage.Literals.ACTION__PATTERN, true, false, false,
				ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Prerequisites Alias feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addPrerequisitesAliasPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
				getResourceLocator(), getString("_UI_Action_prerequisitesAlias_feature"), getString("_UI_PropertyDescriptor_description",
						"_UI_Action_prerequisitesAlias_feature", "_UI_Action_type"), CspecPackage.Literals.ACTION__PREREQUISITES_ALIAS, true, false,
				false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Product Alias feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addProductAliasPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
				getResourceLocator(), getString("_UI_Action_productAlias_feature"), getString("_UI_PropertyDescriptor_description",
						"_UI_Action_productAlias_feature", "_UI_Action_type"), CspecPackage.Literals.ACTION__PRODUCT_ALIAS, true, false, false,
				ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Product File Count feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addProductFileCountPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
				getResourceLocator(), getString("_UI_Action_productFileCount_feature"), getString("_UI_PropertyDescriptor_description",
						"_UI_Action_productFileCount_feature", "_UI_Action_type"), CspecPackage.Literals.ACTION__PRODUCT_FILE_COUNT, true, false,
				false, ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Products feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addProductsPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
				getResourceLocator(), getString("_UI_Action_products_feature"), getString("_UI_PropertyDescriptor_description",
						"_UI_Action_products_feature", "_UI_Action_type"), CspecPackage.Literals.ACTION__PRODUCTS, true, false, true, null, null,
				null));
	}

	/**
	 * This adds a property descriptor for the Replacement feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addReplacementPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
				getResourceLocator(), getString("_UI_Action_replacement_feature"), getString("_UI_PropertyDescriptor_description",
						"_UI_Action_replacement_feature", "_UI_Action_type"), CspecPackage.Literals.ACTION__REPLACEMENT, true, false, false,
				ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Up To Date Policy feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addUpToDatePolicyPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
				getResourceLocator(), getString("_UI_Action_upToDatePolicy_feature"), getString("_UI_PropertyDescriptor_description",
						"_UI_Action_upToDatePolicy_feature", "_UI_Action_type"), CspecPackage.Literals.ACTION__UP_TO_DATE_POLICY, true, false, false,
				ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
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

		newChildDescriptors.add(createChildParameter(CspecPackage.Literals.ACTION__PROPERTIES, CommonFactory.eINSTANCE.createPropertyConstant()));

		newChildDescriptors
				.add(createChildParameter(CspecPackage.Literals.ACTION__ACTOR_PROPERTIES, CommonFactory.eINSTANCE.createPropertyConstant()));

		newChildDescriptors.add(createChildParameter(CspecPackage.Literals.ACTION__ACTOR, ""));

		newChildDescriptors.add(createChildParameter(CspecPackage.Literals.ACTION__PRODUCT, CspecFactory.eINSTANCE.createArtifact()));

		newChildDescriptors.add(createChildParameter(CspecPackage.Literals.ACTION__PRODUCT, CspecFactory.eINSTANCE.createActionAttribute()));

		newChildDescriptors.add(createChildParameter(CspecPackage.Literals.ACTION__PRODUCT, CspecFactory.eINSTANCE.createPathGroup()));

		newChildDescriptors.add(createChildParameter(CspecPackage.Literals.ACTION__PRODUCT, CspecFactory.eINSTANCE.createAlterArtifact()));

		newChildDescriptors.add(createChildParameter(CspecPackage.Literals.ACTION__PRODUCT, CspecFactory.eINSTANCE.createSelfArtifact()));

		newChildDescriptors.add(createChildParameter(CspecPackage.Literals.ACTION__PREREQUISITES_REBASE, CspecFactory.eINSTANCE.createFromString(
				CspecPackage.Literals.IPATH, null))); // TODO: ensure this is a
														// valid literal value
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

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected boolean shouldComposeCreationImage() {
		return true;
	}

}
