/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspecxml.impl;

import java.util.Collection;

import org.eclipse.buckminster.cspecxml.IAction;
import org.eclipse.buckminster.cspecxml.ICSpecXMLPackage;
import org.eclipse.buckminster.cspecxml.IDefinitions;
import org.eclipse.buckminster.cspecxml.IPrerequisites;
import org.eclipse.buckminster.cspecxml.IProductsType;
import org.eclipse.buckminster.cspecxml.IProperties;

import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Action</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.ActionImpl#getGroup <em>
 * Group</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.ActionImpl#getDefinitions
 * <em>Definitions</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.cspecxml.impl.ActionImpl#getActorProperties
 * <em>Actor Properties</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.ActionImpl#getProperties
 * <em>Properties</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.ActionImpl#getPrerequisites
 * <em>Prerequisites</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.ActionImpl#getProducts <em>
 * Products</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.ActionImpl#getActor <em>
 * Actor</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.ActionImpl#isAlways <em>
 * Always</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.cspecxml.impl.ActionImpl#isAssignConsoleSupport
 * <em>Assign Console Support</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.ActionImpl#isEnabled <em>
 * Enabled</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.ActionImpl#getFilter <em>
 * Filter</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class ActionImpl extends AttributeImpl implements IAction {
	/**
	 * The cached value of the '{@link #getGroup() <em>Group</em>}' attribute
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getGroup()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap group;

	/**
	 * The default value of the '{@link #getActor() <em>Actor</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getActor()
	 * @generated
	 * @ordered
	 */
	protected static final String ACTOR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getActor() <em>Actor</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getActor()
	 * @generated
	 * @ordered
	 */
	protected String actor = ACTOR_EDEFAULT;

	/**
	 * The default value of the '{@link #isAlways() <em>Always</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isAlways()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ALWAYS_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isAlways() <em>Always</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isAlways()
	 * @generated
	 * @ordered
	 */
	protected boolean always = ALWAYS_EDEFAULT;

	/**
	 * This is true if the Always attribute has been set. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	protected boolean alwaysESet;

	/**
	 * The default value of the '{@link #isAssignConsoleSupport()
	 * <em>Assign Console Support</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #isAssignConsoleSupport()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ASSIGN_CONSOLE_SUPPORT_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isAssignConsoleSupport()
	 * <em>Assign Console Support</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #isAssignConsoleSupport()
	 * @generated
	 * @ordered
	 */
	protected boolean assignConsoleSupport = ASSIGN_CONSOLE_SUPPORT_EDEFAULT;

	/**
	 * This is true if the Assign Console Support attribute has been set. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	protected boolean assignConsoleSupportESet;

	/**
	 * The default value of the '{@link #isEnabled() <em>Enabled</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isEnabled()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ENABLED_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isEnabled() <em>Enabled</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isEnabled()
	 * @generated
	 * @ordered
	 */
	protected boolean enabled = ENABLED_EDEFAULT;

	/**
	 * This is true if the Enabled attribute has been set. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	protected boolean enabledESet;

	/**
	 * The default value of the '{@link #getFilter() <em>Filter</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getFilter()
	 * @generated
	 * @ordered
	 */
	protected static final Filter FILTER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFilter() <em>Filter</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getFilter()
	 * @generated
	 * @ordered
	 */
	protected Filter filter = FILTER_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ActionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ICSpecXMLPackage.ACTION__GROUP:
				if (coreType)
					return getGroup();
				return ((FeatureMap.Internal) getGroup()).getWrapper();
			case ICSpecXMLPackage.ACTION__DEFINITIONS:
				return getDefinitions();
			case ICSpecXMLPackage.ACTION__ACTOR_PROPERTIES:
				return getActorProperties();
			case ICSpecXMLPackage.ACTION__PROPERTIES:
				return getProperties();
			case ICSpecXMLPackage.ACTION__PREREQUISITES:
				return getPrerequisites();
			case ICSpecXMLPackage.ACTION__PRODUCTS:
				return getProducts();
			case ICSpecXMLPackage.ACTION__ACTOR:
				return getActor();
			case ICSpecXMLPackage.ACTION__ALWAYS:
				return isAlways();
			case ICSpecXMLPackage.ACTION__ASSIGN_CONSOLE_SUPPORT:
				return isAssignConsoleSupport();
			case ICSpecXMLPackage.ACTION__ENABLED:
				return isEnabled();
			case ICSpecXMLPackage.ACTION__FILTER:
				return getFilter();
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
			case ICSpecXMLPackage.ACTION__GROUP:
				return ((InternalEList<?>) getGroup()).basicRemove(otherEnd, msgs);
			case ICSpecXMLPackage.ACTION__DEFINITIONS:
				return ((InternalEList<?>) getDefinitions()).basicRemove(otherEnd, msgs);
			case ICSpecXMLPackage.ACTION__ACTOR_PROPERTIES:
				return ((InternalEList<?>) getActorProperties()).basicRemove(otherEnd, msgs);
			case ICSpecXMLPackage.ACTION__PROPERTIES:
				return ((InternalEList<?>) getProperties()).basicRemove(otherEnd, msgs);
			case ICSpecXMLPackage.ACTION__PREREQUISITES:
				return ((InternalEList<?>) getPrerequisites()).basicRemove(otherEnd, msgs);
			case ICSpecXMLPackage.ACTION__PRODUCTS:
				return ((InternalEList<?>) getProducts()).basicRemove(otherEnd, msgs);
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
			case ICSpecXMLPackage.ACTION__GROUP:
				return group != null && !group.isEmpty();
			case ICSpecXMLPackage.ACTION__DEFINITIONS:
				return !getDefinitions().isEmpty();
			case ICSpecXMLPackage.ACTION__ACTOR_PROPERTIES:
				return !getActorProperties().isEmpty();
			case ICSpecXMLPackage.ACTION__PROPERTIES:
				return !getProperties().isEmpty();
			case ICSpecXMLPackage.ACTION__PREREQUISITES:
				return !getPrerequisites().isEmpty();
			case ICSpecXMLPackage.ACTION__PRODUCTS:
				return !getProducts().isEmpty();
			case ICSpecXMLPackage.ACTION__ACTOR:
				return ACTOR_EDEFAULT == null ? actor != null : !ACTOR_EDEFAULT.equals(actor);
			case ICSpecXMLPackage.ACTION__ALWAYS:
				return isSetAlways();
			case ICSpecXMLPackage.ACTION__ASSIGN_CONSOLE_SUPPORT:
				return isSetAssignConsoleSupport();
			case ICSpecXMLPackage.ACTION__ENABLED:
				return isSetEnabled();
			case ICSpecXMLPackage.ACTION__FILTER:
				return FILTER_EDEFAULT == null ? filter != null : !FILTER_EDEFAULT.equals(filter);
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
			case ICSpecXMLPackage.ACTION__GROUP:
				((FeatureMap.Internal) getGroup()).set(newValue);
				return;
			case ICSpecXMLPackage.ACTION__DEFINITIONS:
				getDefinitions().clear();
				getDefinitions().addAll((Collection<? extends IDefinitions>) newValue);
				return;
			case ICSpecXMLPackage.ACTION__ACTOR_PROPERTIES:
				getActorProperties().clear();
				getActorProperties().addAll((Collection<? extends IProperties>) newValue);
				return;
			case ICSpecXMLPackage.ACTION__PROPERTIES:
				getProperties().clear();
				getProperties().addAll((Collection<? extends IProperties>) newValue);
				return;
			case ICSpecXMLPackage.ACTION__PREREQUISITES:
				getPrerequisites().clear();
				getPrerequisites().addAll((Collection<? extends IPrerequisites>) newValue);
				return;
			case ICSpecXMLPackage.ACTION__PRODUCTS:
				getProducts().clear();
				getProducts().addAll((Collection<? extends IProductsType>) newValue);
				return;
			case ICSpecXMLPackage.ACTION__ACTOR:
				setActor((String) newValue);
				return;
			case ICSpecXMLPackage.ACTION__ALWAYS:
				setAlways((Boolean) newValue);
				return;
			case ICSpecXMLPackage.ACTION__ASSIGN_CONSOLE_SUPPORT:
				setAssignConsoleSupport((Boolean) newValue);
				return;
			case ICSpecXMLPackage.ACTION__ENABLED:
				setEnabled((Boolean) newValue);
				return;
			case ICSpecXMLPackage.ACTION__FILTER:
				setFilter((Filter) newValue);
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
			case ICSpecXMLPackage.ACTION__GROUP:
				getGroup().clear();
				return;
			case ICSpecXMLPackage.ACTION__DEFINITIONS:
				getDefinitions().clear();
				return;
			case ICSpecXMLPackage.ACTION__ACTOR_PROPERTIES:
				getActorProperties().clear();
				return;
			case ICSpecXMLPackage.ACTION__PROPERTIES:
				getProperties().clear();
				return;
			case ICSpecXMLPackage.ACTION__PREREQUISITES:
				getPrerequisites().clear();
				return;
			case ICSpecXMLPackage.ACTION__PRODUCTS:
				getProducts().clear();
				return;
			case ICSpecXMLPackage.ACTION__ACTOR:
				setActor(ACTOR_EDEFAULT);
				return;
			case ICSpecXMLPackage.ACTION__ALWAYS:
				unsetAlways();
				return;
			case ICSpecXMLPackage.ACTION__ASSIGN_CONSOLE_SUPPORT:
				unsetAssignConsoleSupport();
				return;
			case ICSpecXMLPackage.ACTION__ENABLED:
				unsetEnabled();
				return;
			case ICSpecXMLPackage.ACTION__FILTER:
				setFilter(FILTER_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getActor() {
		return actor;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<IProperties> getActorProperties() {
		return getGroup().list(ICSpecXMLPackage.Literals.ACTION__ACTOR_PROPERTIES);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<IDefinitions> getDefinitions() {
		return getGroup().list(ICSpecXMLPackage.Literals.ACTION__DEFINITIONS);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Filter getFilter() {
		return filter;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public FeatureMap getGroup() {
		if (group == null) {
			group = new BasicFeatureMap(this, ICSpecXMLPackage.ACTION__GROUP);
		}
		return group;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<IPrerequisites> getPrerequisites() {
		return getGroup().list(ICSpecXMLPackage.Literals.ACTION__PREREQUISITES);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<IProductsType> getProducts() {
		return getGroup().list(ICSpecXMLPackage.Literals.ACTION__PRODUCTS);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<IProperties> getProperties() {
		return getGroup().list(ICSpecXMLPackage.Literals.ACTION__PROPERTIES);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isAlways() {
		return always;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isAssignConsoleSupport() {
		return assignConsoleSupport;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isSetAlways() {
		return alwaysESet;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isSetAssignConsoleSupport() {
		return assignConsoleSupportESet;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isSetEnabled() {
		return enabledESet;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setActor(String newActor) {
		String oldActor = actor;
		actor = newActor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ICSpecXMLPackage.ACTION__ACTOR, oldActor, actor));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setAlways(boolean newAlways) {
		boolean oldAlways = always;
		always = newAlways;
		boolean oldAlwaysESet = alwaysESet;
		alwaysESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ICSpecXMLPackage.ACTION__ALWAYS, oldAlways, always, !oldAlwaysESet));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setAssignConsoleSupport(boolean newAssignConsoleSupport) {
		boolean oldAssignConsoleSupport = assignConsoleSupport;
		assignConsoleSupport = newAssignConsoleSupport;
		boolean oldAssignConsoleSupportESet = assignConsoleSupportESet;
		assignConsoleSupportESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ICSpecXMLPackage.ACTION__ASSIGN_CONSOLE_SUPPORT, oldAssignConsoleSupport,
					assignConsoleSupport, !oldAssignConsoleSupportESet));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setEnabled(boolean newEnabled) {
		boolean oldEnabled = enabled;
		enabled = newEnabled;
		boolean oldEnabledESet = enabledESet;
		enabledESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ICSpecXMLPackage.ACTION__ENABLED, oldEnabled, enabled, !oldEnabledESet));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setFilter(Filter newFilter) {
		Filter oldFilter = filter;
		filter = newFilter;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ICSpecXMLPackage.ACTION__FILTER, oldFilter, filter));
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
		result.append(" (group: ");
		result.append(group);
		result.append(", actor: ");
		result.append(actor);
		result.append(", always: ");
		if (alwaysESet)
			result.append(always);
		else
			result.append("<unset>");
		result.append(", assignConsoleSupport: ");
		if (assignConsoleSupportESet)
			result.append(assignConsoleSupport);
		else
			result.append("<unset>");
		result.append(", enabled: ");
		if (enabledESet)
			result.append(enabled);
		else
			result.append("<unset>");
		result.append(", filter: ");
		result.append(filter);
		result.append(')');
		return result.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void unsetAlways() {
		boolean oldAlways = always;
		boolean oldAlwaysESet = alwaysESet;
		always = ALWAYS_EDEFAULT;
		alwaysESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ICSpecXMLPackage.ACTION__ALWAYS, oldAlways, ALWAYS_EDEFAULT, oldAlwaysESet));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void unsetAssignConsoleSupport() {
		boolean oldAssignConsoleSupport = assignConsoleSupport;
		boolean oldAssignConsoleSupportESet = assignConsoleSupportESet;
		assignConsoleSupport = ASSIGN_CONSOLE_SUPPORT_EDEFAULT;
		assignConsoleSupportESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ICSpecXMLPackage.ACTION__ASSIGN_CONSOLE_SUPPORT, oldAssignConsoleSupport,
					ASSIGN_CONSOLE_SUPPORT_EDEFAULT, oldAssignConsoleSupportESet));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void unsetEnabled() {
		boolean oldEnabled = enabled;
		boolean oldEnabledESet = enabledESet;
		enabled = ENABLED_EDEFAULT;
		enabledESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ICSpecXMLPackage.ACTION__ENABLED, oldEnabled, ENABLED_EDEFAULT, oldEnabledESet));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ICSpecXMLPackage.Literals.ACTION;
	}

} // ActionImpl
