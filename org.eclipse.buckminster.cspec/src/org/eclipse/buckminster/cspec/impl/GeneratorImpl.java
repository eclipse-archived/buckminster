/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspec.impl;

import org.eclipse.buckminster.cspec.CSpec;
import org.eclipse.buckminster.cspec.CspecPackage;
import org.eclipse.buckminster.cspec.Generator;

import org.eclipse.buckminster.model.common.ComponentIdentifier;
import org.eclipse.buckminster.model.common.ComponentRequest;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Generator</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.cspec.impl.GeneratorImpl#getAttribute <em>
 * Attribute</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.impl.GeneratorImpl#getComponent <em>
 * Component</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.impl.GeneratorImpl#getGenerates <em>
 * Generates</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.impl.GeneratorImpl#getCspec <em>
 * Cspec</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class GeneratorImpl extends EObjectImpl implements Generator {
	/**
	 * The default value of the '{@link #getAttribute() <em>Attribute</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getAttribute()
	 * @generated
	 * @ordered
	 */
	protected static final String ATTRIBUTE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAttribute() <em>Attribute</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getAttribute()
	 * @generated
	 * @ordered
	 */
	protected String attribute = ATTRIBUTE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getComponent() <em>Component</em>}'
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getComponent()
	 * @generated
	 * @ordered
	 */
	protected ComponentRequest component;

	/**
	 * The cached value of the '{@link #getGenerates() <em>Generates</em>}'
	 * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getGenerates()
	 * @generated
	 * @ordered
	 */
	protected ComponentIdentifier generates;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected GeneratorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ComponentRequest basicGetComponent() {
		return component;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetCspec(CSpec newCspec, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject) newCspec, CspecPackage.GENERATOR__CSPEC, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetGenerates(ComponentIdentifier newGenerates, NotificationChain msgs) {
		ComponentIdentifier oldGenerates = generates;
		generates = newGenerates;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CspecPackage.GENERATOR__GENERATES, oldGenerates,
					newGenerates);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case CspecPackage.GENERATOR__CSPEC:
				return eInternalContainer().eInverseRemove(this, CspecPackage.CSPEC__GENERATORS, CSpec.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CspecPackage.GENERATOR__ATTRIBUTE:
				return getAttribute();
			case CspecPackage.GENERATOR__COMPONENT:
				if (resolve)
					return getComponent();
				return basicGetComponent();
			case CspecPackage.GENERATOR__GENERATES:
				return getGenerates();
			case CspecPackage.GENERATOR__CSPEC:
				return getCspec();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CspecPackage.GENERATOR__CSPEC:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetCspec((CSpec) otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CspecPackage.GENERATOR__GENERATES:
				return basicSetGenerates(null, msgs);
			case CspecPackage.GENERATOR__CSPEC:
				return basicSetCspec(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case CspecPackage.GENERATOR__ATTRIBUTE:
				return ATTRIBUTE_EDEFAULT == null ? attribute != null : !ATTRIBUTE_EDEFAULT.equals(attribute);
			case CspecPackage.GENERATOR__COMPONENT:
				return component != null;
			case CspecPackage.GENERATOR__GENERATES:
				return generates != null;
			case CspecPackage.GENERATOR__CSPEC:
				return getCspec() != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case CspecPackage.GENERATOR__ATTRIBUTE:
				setAttribute((String) newValue);
				return;
			case CspecPackage.GENERATOR__COMPONENT:
				setComponent((ComponentRequest) newValue);
				return;
			case CspecPackage.GENERATOR__GENERATES:
				setGenerates((ComponentIdentifier) newValue);
				return;
			case CspecPackage.GENERATOR__CSPEC:
				setCspec((CSpec) newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case CspecPackage.GENERATOR__ATTRIBUTE:
				setAttribute(ATTRIBUTE_EDEFAULT);
				return;
			case CspecPackage.GENERATOR__COMPONENT:
				setComponent((ComponentRequest) null);
				return;
			case CspecPackage.GENERATOR__GENERATES:
				setGenerates((ComponentIdentifier) null);
				return;
			case CspecPackage.GENERATOR__CSPEC:
				setCspec((CSpec) null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getAttribute() {
		return attribute;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public ComponentRequest getComponent() {
		if (component != null && ((EObject) component).eIsProxy()) {
			InternalEObject oldComponent = (InternalEObject) component;
			component = (ComponentRequest) eResolveProxy(oldComponent);
			if (component != oldComponent) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CspecPackage.GENERATOR__COMPONENT, oldComponent, component));
			}
		}
		return component;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public CSpec getCspec() {
		if (eContainerFeatureID() != CspecPackage.GENERATOR__CSPEC)
			return null;
		return (CSpec) eContainer();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public ComponentIdentifier getGenerates() {
		return generates;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setAttribute(String newAttribute) {
		String oldAttribute = attribute;
		attribute = newAttribute;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CspecPackage.GENERATOR__ATTRIBUTE, oldAttribute, attribute));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setComponent(ComponentRequest newComponent) {
		ComponentRequest oldComponent = component;
		component = newComponent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CspecPackage.GENERATOR__COMPONENT, oldComponent, component));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setCspec(CSpec newCspec) {
		if (newCspec != eInternalContainer() || (eContainerFeatureID() != CspecPackage.GENERATOR__CSPEC && newCspec != null)) {
			if (EcoreUtil.isAncestor(this, newCspec))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newCspec != null)
				msgs = ((InternalEObject) newCspec).eInverseAdd(this, CspecPackage.CSPEC__GENERATORS, CSpec.class, msgs);
			msgs = basicSetCspec(newCspec, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CspecPackage.GENERATOR__CSPEC, newCspec, newCspec));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setGenerates(ComponentIdentifier newGenerates) {
		if (newGenerates != generates) {
			NotificationChain msgs = null;
			if (generates != null)
				msgs = ((InternalEObject) generates).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CspecPackage.GENERATOR__GENERATES, null, msgs);
			if (newGenerates != null)
				msgs = ((InternalEObject) newGenerates).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CspecPackage.GENERATOR__GENERATES, null, msgs);
			msgs = basicSetGenerates(newGenerates, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CspecPackage.GENERATOR__GENERATES, newGenerates, newGenerates));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (attribute: ");
		result.append(attribute);
		result.append(')');
		return result.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CspecPackage.Literals.GENERATOR;
	}

} // GeneratorImpl
