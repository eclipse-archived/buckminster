/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap.impl;

import java.util.Collection;

import org.eclipse.buckminster.model.common.Documentation;
import org.eclipse.buckminster.rmap.Locator;
import org.eclipse.buckminster.rmap.Redirect;
import org.eclipse.buckminster.rmap.ResourceMap;
import org.eclipse.buckminster.rmap.RmapPackage;
import org.eclipse.buckminster.rmap.SearchPath;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Resource Map</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.rmap.impl.ResourceMapImpl#getLocators <em>
 * Locators</em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.impl.ResourceMapImpl#getRedirects
 * <em>Redirects</em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.impl.ResourceMapImpl#getSearchPaths
 * <em>Search Paths</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.rmap.impl.ResourceMapImpl#getDocumentation
 * <em>Documentation</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class ResourceMapImpl extends PropertiesImpl implements ResourceMap {
	/**
	 * The cached value of the '{@link #getLocators() <em>Locators</em>}'
	 * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getLocators()
	 * @generated
	 * @ordered
	 */
	protected EList<Locator> locators;

	/**
	 * The cached value of the '{@link #getRedirects() <em>Redirects</em>}'
	 * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getRedirects()
	 * @generated
	 * @ordered
	 */
	protected EList<Redirect> redirects;

	/**
	 * The cached value of the '{@link #getSearchPaths() <em>Search Paths</em>}'
	 * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getSearchPaths()
	 * @generated
	 * @ordered
	 */
	protected EList<SearchPath> searchPaths;

	/**
	 * The cached value of the '{@link #getDocumentation()
	 * <em>Documentation</em>}' containment reference. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getDocumentation()
	 * @generated
	 * @ordered
	 */
	protected Documentation documentation;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ResourceMapImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetDocumentation(Documentation newDocumentation, NotificationChain msgs) {
		Documentation oldDocumentation = documentation;
		documentation = newDocumentation;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RmapPackage.RESOURCE_MAP__DOCUMENTATION, oldDocumentation,
					newDocumentation);
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
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RmapPackage.RESOURCE_MAP__LOCATORS:
				return getLocators();
			case RmapPackage.RESOURCE_MAP__REDIRECTS:
				return getRedirects();
			case RmapPackage.RESOURCE_MAP__SEARCH_PATHS:
				return getSearchPaths();
			case RmapPackage.RESOURCE_MAP__DOCUMENTATION:
				return getDocumentation();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RmapPackage.RESOURCE_MAP__LOCATORS:
				return ((InternalEList<?>) getLocators()).basicRemove(otherEnd, msgs);
			case RmapPackage.RESOURCE_MAP__REDIRECTS:
				return ((InternalEList<?>) getRedirects()).basicRemove(otherEnd, msgs);
			case RmapPackage.RESOURCE_MAP__SEARCH_PATHS:
				return ((InternalEList<?>) getSearchPaths()).basicRemove(otherEnd, msgs);
			case RmapPackage.RESOURCE_MAP__DOCUMENTATION:
				return basicSetDocumentation(null, msgs);
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
			case RmapPackage.RESOURCE_MAP__LOCATORS:
				return locators != null && !locators.isEmpty();
			case RmapPackage.RESOURCE_MAP__REDIRECTS:
				return redirects != null && !redirects.isEmpty();
			case RmapPackage.RESOURCE_MAP__SEARCH_PATHS:
				return searchPaths != null && !searchPaths.isEmpty();
			case RmapPackage.RESOURCE_MAP__DOCUMENTATION:
				return documentation != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case RmapPackage.RESOURCE_MAP__LOCATORS:
				getLocators().clear();
				getLocators().addAll((Collection<? extends Locator>) newValue);
				return;
			case RmapPackage.RESOURCE_MAP__REDIRECTS:
				getRedirects().clear();
				getRedirects().addAll((Collection<? extends Redirect>) newValue);
				return;
			case RmapPackage.RESOURCE_MAP__SEARCH_PATHS:
				getSearchPaths().clear();
				getSearchPaths().addAll((Collection<? extends SearchPath>) newValue);
				return;
			case RmapPackage.RESOURCE_MAP__DOCUMENTATION:
				setDocumentation((Documentation) newValue);
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
			case RmapPackage.RESOURCE_MAP__LOCATORS:
				getLocators().clear();
				return;
			case RmapPackage.RESOURCE_MAP__REDIRECTS:
				getRedirects().clear();
				return;
			case RmapPackage.RESOURCE_MAP__SEARCH_PATHS:
				getSearchPaths().clear();
				return;
			case RmapPackage.RESOURCE_MAP__DOCUMENTATION:
				setDocumentation((Documentation) null);
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
	public Documentation getDocumentation() {
		return documentation;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<Locator> getLocators() {
		if (locators == null) {
			locators = new EObjectContainmentEList<Locator>(Locator.class, this, RmapPackage.RESOURCE_MAP__LOCATORS);
		}
		return locators;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<Redirect> getRedirects() {
		if (redirects == null) {
			redirects = new EObjectContainmentEList<Redirect>(Redirect.class, this, RmapPackage.RESOURCE_MAP__REDIRECTS);
		}
		return redirects;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<SearchPath> getSearchPaths() {
		if (searchPaths == null) {
			searchPaths = new EObjectContainmentEList<SearchPath>(SearchPath.class, this, RmapPackage.RESOURCE_MAP__SEARCH_PATHS);
		}
		return searchPaths;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setDocumentation(Documentation newDocumentation) {
		if (newDocumentation != documentation) {
			NotificationChain msgs = null;
			if (documentation != null)
				msgs = ((InternalEObject) documentation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RmapPackage.RESOURCE_MAP__DOCUMENTATION, null,
						msgs);
			if (newDocumentation != null)
				msgs = ((InternalEObject) newDocumentation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RmapPackage.RESOURCE_MAP__DOCUMENTATION, null,
						msgs);
			msgs = basicSetDocumentation(newDocumentation, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RmapPackage.RESOURCE_MAP__DOCUMENTATION, newDocumentation, newDocumentation));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RmapPackage.Literals.RESOURCE_MAP;
	}

} // ResourceMapImpl
