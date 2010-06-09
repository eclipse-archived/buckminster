/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspec.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.buckminster.cspec.CSpec;
import org.eclipse.buckminster.cspec.CspecPackage;

import org.eclipse.buckminster.model.common.provider.ComponentIdentifierItemProvider;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandWrapper;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IDisposable;
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
 * This is the item provider adapter for a
 * {@link org.eclipse.buckminster.cspec.CSpec} object. <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class CSpecItemProvider extends ComponentIdentifierItemProvider implements IEditingDomainItemProvider, IStructuredItemContentProvider,
		ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	private List<?> children = null;

	/**
	 * This constructs an instance from a factory and a notifier. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public CSpecItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * Disposes the non-modeled children.
	 */
	@Override
	public void dispose() // 19.2.3
	{
		super.dispose();
		if (children != null) {
			for (int i = 0; i < children.size(); ++i)
				((IDisposable) children.get(i)).dispose();
			children = null;
		}
	}

	public Object getAttributes() {
		return children.get(0);
	}

	@Override
	public Collection<?> getChildren(Object object) {
		if (children != null)
			return children;

		CSpec cspec = (CSpec) object;
		ArrayList<ItemProviderAdapter> groups = new ArrayList<ItemProviderAdapter>();
		groups.add(new AttributesItemProvider(adapterFactory, cspec));
		groups.add(new DependenciesItemProvider(adapterFactory, cspec));
		groups.add(new GeneratorsItemProvider(adapterFactory, cspec));

		children = groups;
		return children;
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
			childrenFeatures.add(CspecPackage.Literals.CSPEC__DEPENDENCIES);
			childrenFeatures.add(CspecPackage.Literals.CSPEC__GENERATORS);
			childrenFeatures.add(CspecPackage.Literals.CSPEC__ATTRIBUTES);
		}
		return childrenFeatures;
	}

	public Object getDependencies() {
		return children.get(1);
	}

	public Object getGenerators() {
		return children.get(2);
	}

	/**
	 * This returns CSpec.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/CSpec"));
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

			addDocumentationPropertyDescriptor(object);
			addShortDescPropertyDescriptor(object);
			addFilterPropertyDescriptor(object);
			addProjectInfoPropertyDescriptor(object);
			addSelfPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * Return the resource locator for this item provider's resources. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return CspecEditPlugin.INSTANCE;
	}

	/**
	 * This returns the label text for the adapted class. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((CSpec) object).getId();
		return label == null || label.length() == 0 ? getString("_UI_CSpec_type") : getString("_UI_CSpec_type") + " " + label;
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

		switch (notification.getFeatureID(CSpec.class)) {
			case CspecPackage.CSPEC__DOCUMENTATION:
			case CspecPackage.CSPEC__SHORT_DESC:
			case CspecPackage.CSPEC__FILTER:
			case CspecPackage.CSPEC__PROJECT_INFO:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case CspecPackage.CSPEC__DEPENDENCIES:
			case CspecPackage.CSPEC__GENERATORS:
			case CspecPackage.CSPEC__ATTRIBUTES:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds a property descriptor for the Documentation feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addDocumentationPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
				getResourceLocator(), getString("_UI_CSpec_documentation_feature"), getString("_UI_PropertyDescriptor_description",
						"_UI_CSpec_documentation_feature", "_UI_CSpec_type"), CspecPackage.Literals.CSPEC__DOCUMENTATION, true, true, false, null,
				null, null));
	}

	/**
	 * This adds a property descriptor for the Filter feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addFilterPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
				getResourceLocator(), getString("_UI_CSpec_filter_feature"), getString("_UI_PropertyDescriptor_description",
						"_UI_CSpec_filter_feature", "_UI_CSpec_type"), CspecPackage.Literals.CSPEC__FILTER, true, false, false,
				ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Project Info feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addProjectInfoPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
				getResourceLocator(), getString("_UI_CSpec_projectInfo_feature"), getString("_UI_PropertyDescriptor_description",
						"_UI_CSpec_projectInfo_feature", "_UI_CSpec_type"), CspecPackage.Literals.CSPEC__PROJECT_INFO, true, false, false,
				ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Self feature. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addSelfPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
				getResourceLocator(), getString("_UI_CSpec_self_feature"), getString("_UI_PropertyDescriptor_description", "_UI_CSpec_self_feature",
						"_UI_CSpec_type"), CspecPackage.Literals.CSPEC__SELF, false, false, false, null, null, null));
	}

	/**
	 * This adds a property descriptor for the Short Desc feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addShortDescPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
				getResourceLocator(), getString("_UI_CSpec_shortDesc_feature"), getString("_UI_PropertyDescriptor_description",
						"_UI_CSpec_shortDesc_feature", "_UI_CSpec_type"), CspecPackage.Literals.CSPEC__SHORT_DESC, true, false, false,
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
	}

	/**
	 * Creates an add command that is wrapped to return the correct non-modeled
	 * item, in place of the target supplier, as part of the affected objects.
	 */
	@Override
	protected Command createAddCommand(EditingDomain domain, EObject owner, EStructuralFeature feature, Collection<?> collection, int index) {
		return createWrappedCommand(super.createAddCommand(domain, owner, feature, collection, index), owner, feature);
	}

	/**
	 * Creates a remove command that is wrapped to return the correct
	 * non-modeled item, in place of the target supplier, as part of the
	 * affected objects.
	 */
	@Override
	protected Command createRemoveCommand(EditingDomain domain, EObject owner, EStructuralFeature feature, Collection<?> collection) {
		return createWrappedCommand(super.createRemoveCommand(domain, owner, feature, collection), owner, feature);
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

	/**
	 * Returns a wrapper for the given command that returns the correct
	 * non-modeled item, in place of the target supplier, as part of the
	 * affected objects.
	 */
	private Command createWrappedCommand(Command command, final EObject owner, final EStructuralFeature feature) {
		if (feature == CspecPackage.Literals.CSPEC__ATTRIBUTES || feature == CspecPackage.Literals.CSPEC__DEPENDENCIES
				|| feature == CspecPackage.Literals.CSPEC__GENERATORS) {
			return new CommandWrapper(command) {
				@Override
				public Collection<?> getAffectedObjects() {
					Collection<?> affected = super.getAffectedObjects();
					if (!affected.contains(owner))
						return affected;
					Object affectedObject;
					if (feature == CspecPackage.Literals.CSPEC__ATTRIBUTES)
						affectedObject = getAttributes();
					else if (feature == CspecPackage.Literals.CSPEC__DEPENDENCIES)
						affectedObject = getDependencies();
					else
						affectedObject = getGenerators();
					return Collections.singleton(affectedObject);
				}
			};
		}
		return command;
	}

}
