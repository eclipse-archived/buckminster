/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap.impl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.Map;

import org.eclipse.buckminster.model.common.Documentation;
import org.eclipse.buckminster.model.common.impl.PropertiesImpl;
import org.eclipse.buckminster.model.common.util.DynamicFeatureEList;
import org.eclipse.buckminster.model.common.util.UnmodifiableMapUnion;
import org.eclipse.buckminster.rmap.Locator;
import org.eclipse.buckminster.rmap.Matcher;
import org.eclipse.buckminster.rmap.Redirect;
import org.eclipse.buckminster.rmap.Repository;
import org.eclipse.buckminster.rmap.ResourceMap;
import org.eclipse.buckminster.rmap.RmapPackage;
import org.eclipse.buckminster.rmap.SearchPath;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.notify.impl.NotificationImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Resource Map</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.buckminster.rmap.impl.ResourceMapImpl#getMatcherGroup <em>Matcher Group</em>}</li>
 *   <li>{@link org.eclipse.buckminster.rmap.impl.ResourceMapImpl#getRepositories <em>Repositories</em>}</li>
 *   <li>{@link org.eclipse.buckminster.rmap.impl.ResourceMapImpl#getMatchers <em>Matchers</em>}</li>
 *   <li>{@link org.eclipse.buckminster.rmap.impl.ResourceMapImpl#getSearchPaths <em>Search Paths</em>}</li>
 *   <li>{@link org.eclipse.buckminster.rmap.impl.ResourceMapImpl#getDocumentation <em>Documentation</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ResourceMapImpl extends PropertiesImpl implements ResourceMap {
	/**
	 * The cached value of the '{@link #getMatcherGroup() <em>Matcher Group</em>}' attribute list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #getMatcherGroup()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap matcherGroup;

	/**
	 * The cached value of the '{@link #getRepositories() <em>Repositories</em>}
	 * ' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getRepositories()
	 * @generated
	 * @ordered
	 */
	protected EList<Repository> repositories;

	/**
	 * The cached value of the '{@link #getSearchPaths() <em>Search Paths</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getSearchPaths()
	 * @generated
	 * @ordered
	 */
	protected EList<SearchPath> searchPaths;

	/**
	 * The cached value of the '{@link #getDocumentation() <em>Documentation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDocumentation()
	 * @generated
	 * @ordered
	 */
	protected Documentation documentation;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected ResourceMapImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDocumentation(Documentation newDocumentation, NotificationChain msgs) {
		Documentation oldDocumentation = documentation;
		documentation = newDocumentation;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RmapPackage.RESOURCE_MAP__DOCUMENTATION, oldDocumentation, newDocumentation);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RmapPackage.RESOURCE_MAP__MATCHER_GROUP:
				if (coreType) return getMatcherGroup();
				return ((FeatureMap.Internal)getMatcherGroup()).getWrapper();
			case RmapPackage.RESOURCE_MAP__REPOSITORIES:
				return getRepositories();
			case RmapPackage.RESOURCE_MAP__MATCHERS:
				return getMatchers();
			case RmapPackage.RESOURCE_MAP__SEARCH_PATHS:
				return getSearchPaths();
			case RmapPackage.RESOURCE_MAP__DOCUMENTATION:
				return getDocumentation();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RmapPackage.RESOURCE_MAP__MATCHER_GROUP:
				return ((InternalEList<?>)getMatcherGroup()).basicRemove(otherEnd, msgs);
			case RmapPackage.RESOURCE_MAP__REPOSITORIES:
				return ((InternalEList<?>)getRepositories()).basicRemove(otherEnd, msgs);
			case RmapPackage.RESOURCE_MAP__MATCHERS:
				return ((InternalEList<?>)getMatchers()).basicRemove(otherEnd, msgs);
			case RmapPackage.RESOURCE_MAP__SEARCH_PATHS:
				return ((InternalEList<?>)getSearchPaths()).basicRemove(otherEnd, msgs);
			case RmapPackage.RESOURCE_MAP__DOCUMENTATION:
				return basicSetDocumentation(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case RmapPackage.RESOURCE_MAP__MATCHER_GROUP:
				return matcherGroup != null && !matcherGroup.isEmpty();
			case RmapPackage.RESOURCE_MAP__REPOSITORIES:
				return repositories != null && !repositories.isEmpty();
			case RmapPackage.RESOURCE_MAP__MATCHERS:
				return !getMatchers().isEmpty();
			case RmapPackage.RESOURCE_MAP__SEARCH_PATHS:
				return searchPaths != null && !searchPaths.isEmpty();
			case RmapPackage.RESOURCE_MAP__DOCUMENTATION:
				return documentation != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case RmapPackage.RESOURCE_MAP__MATCHER_GROUP:
				((FeatureMap.Internal)getMatcherGroup()).set(newValue);
				return;
			case RmapPackage.RESOURCE_MAP__REPOSITORIES:
				getRepositories().clear();
				getRepositories().addAll((Collection<? extends Repository>)newValue);
				return;
			case RmapPackage.RESOURCE_MAP__MATCHERS:
				getMatchers().clear();
				getMatchers().addAll((Collection<? extends Matcher>)newValue);
				return;
			case RmapPackage.RESOURCE_MAP__SEARCH_PATHS:
				getSearchPaths().clear();
				getSearchPaths().addAll((Collection<? extends SearchPath>)newValue);
				return;
			case RmapPackage.RESOURCE_MAP__DOCUMENTATION:
				setDocumentation((Documentation)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case RmapPackage.RESOURCE_MAP__MATCHER_GROUP:
				getMatcherGroup().clear();
				return;
			case RmapPackage.RESOURCE_MAP__REPOSITORIES:
				getRepositories().clear();
				return;
			case RmapPackage.RESOURCE_MAP__MATCHERS:
				getMatchers().clear();
				return;
			case RmapPackage.RESOURCE_MAP__SEARCH_PATHS:
				getSearchPaths().clear();
				return;
			case RmapPackage.RESOURCE_MAP__DOCUMENTATION:
				setDocumentation((Documentation)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public URL getContextURL() {
		Resource resource = eResource();
		if (resource == null)
			return null;

		try {
			return new URL(resource.getURI().toString());
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public Documentation getDocumentation() {
		return documentation;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */

	@SuppressWarnings("serial")
	public FeatureMap getMatcherGroup() {
		if (matcherGroup == null) {
			matcherGroup = new BasicFeatureMap(this, RmapPackage.RESOURCE_MAP__MATCHER_GROUP) {
				@Override
				public <T> EList<T> list(EStructuralFeature feature) {
					return new DynamicFeatureEList<T>(feature, this) {
						@Override
						protected EStructuralFeature getEStructuralFeature(Object value) {
							if (value instanceof Locator)
								return RmapPackage.Literals.DOCUMENT_ROOT__LOCATORS;
							if (value instanceof Redirect)
								return RmapPackage.Literals.DOCUMENT_ROOT__REDIRECTS;
							return getEStructuralFeature();
						}
					};
				}

				@Override
				protected NotificationImpl createNotification(int eventType, EStructuralFeature feature, Object oldObject, Object newObject,
						int index, boolean wasSet) {
					if (feature == RmapPackage.Literals.DOCUMENT_ROOT__LOCATORS || feature == RmapPackage.Literals.DOCUMENT_ROOT__REDIRECTS)
						feature = RmapPackage.Literals.RESOURCE_MAP__MATCHERS;
					return new FeatureMapUtil.FeatureENotificationImpl(owner, eventType, feature, oldObject, newObject, index, wasSet);
				}
			};
		}
		return matcherGroup;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EList<Matcher> getMatchers() {
		return getMatcherGroup().list(RmapPackage.Literals.RESOURCE_MAP__MATCHERS);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EList<Repository> getRepositories() {
		if (repositories == null) {
			repositories = new EObjectContainmentEList<Repository>(Repository.class, this, RmapPackage.RESOURCE_MAP__REPOSITORIES);
		}
		return repositories;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EList<SearchPath> getSearchPaths() {
		if (searchPaths == null) {
			searchPaths = new EObjectContainmentEList<SearchPath>(SearchPath.class, this, RmapPackage.RESOURCE_MAP__SEARCH_PATHS);
		}
		return searchPaths;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public void setDocumentation(Documentation newDocumentation) {
		if (newDocumentation != documentation) {
			NotificationChain msgs = null;
			if (documentation != null)
				msgs = ((InternalEObject)documentation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RmapPackage.RESOURCE_MAP__DOCUMENTATION, null, msgs);
			if (newDocumentation != null)
				msgs = ((InternalEObject)newDocumentation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RmapPackage.RESOURCE_MAP__DOCUMENTATION, null, msgs);
			msgs = basicSetDocumentation(newDocumentation, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RmapPackage.RESOURCE_MAP__DOCUMENTATION, newDocumentation, newDocumentation));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (matcherGroup: ");
		result.append(matcherGroup);
		result.append(')');
		return result.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	protected EClass eStaticClass() {
		return RmapPackage.Literals.RESOURCE_MAP;
	}

	Map<String, String> getProperties(Map<String, String> props) {
		Map<String, String> properties = getProperties();
		if (!properties.isEmpty())
			props = new UnmodifiableMapUnion<String, String>(props, properties);
		return props;
	}

} // ResourceMapImpl
