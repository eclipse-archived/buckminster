/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspecxml.impl;

import java.util.regex.Pattern;
import org.eclipse.buckminster.cspecxml.ICSpecXMLPackage;
import org.eclipse.buckminster.cspecxml.IPrerequisite;

import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Prerequisite</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.PrerequisiteImpl#getAlias
 * <em>Alias</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.cspecxml.impl.PrerequisiteImpl#getComponent
 * <em>Component</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.cspecxml.impl.PrerequisiteImpl#getComponentType
 * <em>Component Type</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.cspecxml.impl.PrerequisiteImpl#isContributor
 * <em>Contributor</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.cspecxml.impl.PrerequisiteImpl#getExcludePattern
 * <em>Exclude Pattern</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.PrerequisiteImpl#getFilter
 * <em>Filter</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.cspecxml.impl.PrerequisiteImpl#getIncludePattern
 * <em>Include Pattern</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.PrerequisiteImpl#getName
 * <em>Name</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.PrerequisiteImpl#isOptional
 * <em>Optional</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class PrerequisiteImpl extends EObjectImpl implements IPrerequisite {
	/**
	 * The default value of the '{@link #getAlias() <em>Alias</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getAlias()
	 * @generated
	 * @ordered
	 */
	protected static final String ALIAS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAlias() <em>Alias</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getAlias()
	 * @generated
	 * @ordered
	 */
	protected String alias = ALIAS_EDEFAULT;

	/**
	 * The default value of the '{@link #getComponent() <em>Component</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getComponent()
	 * @generated
	 * @ordered
	 */
	protected static final String COMPONENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getComponent() <em>Component</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getComponent()
	 * @generated
	 * @ordered
	 */
	protected String component = COMPONENT_EDEFAULT;

	/**
	 * The default value of the '{@link #getComponentType()
	 * <em>Component Type</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getComponentType()
	 * @generated
	 * @ordered
	 */
	protected static final String COMPONENT_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getComponentType()
	 * <em>Component Type</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getComponentType()
	 * @generated
	 * @ordered
	 */
	protected String componentType = COMPONENT_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #isContributor() <em>Contributor</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isContributor()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CONTRIBUTOR_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isContributor() <em>Contributor</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isContributor()
	 * @generated
	 * @ordered
	 */
	protected boolean contributor = CONTRIBUTOR_EDEFAULT;

	/**
	 * This is true if the Contributor attribute has been set. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	protected boolean contributorESet;

	/**
	 * The default value of the '{@link #getExcludePattern()
	 * <em>Exclude Pattern</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getExcludePattern()
	 * @generated
	 * @ordered
	 */
	protected static final Pattern EXCLUDE_PATTERN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getExcludePattern()
	 * <em>Exclude Pattern</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getExcludePattern()
	 * @generated
	 * @ordered
	 */
	protected Pattern excludePattern = EXCLUDE_PATTERN_EDEFAULT;

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
	 * The default value of the '{@link #getIncludePattern()
	 * <em>Include Pattern</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getIncludePattern()
	 * @generated
	 * @ordered
	 */
	protected static final Pattern INCLUDE_PATTERN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIncludePattern()
	 * <em>Include Pattern</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getIncludePattern()
	 * @generated
	 * @ordered
	 */
	protected Pattern includePattern = INCLUDE_PATTERN_EDEFAULT;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #isOptional() <em>Optional</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isOptional()
	 * @generated
	 * @ordered
	 */
	protected static final boolean OPTIONAL_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isOptional() <em>Optional</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isOptional()
	 * @generated
	 * @ordered
	 */
	protected boolean optional = OPTIONAL_EDEFAULT;

	/**
	 * This is true if the Optional attribute has been set. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	protected boolean optionalESet;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected PrerequisiteImpl() {
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
			case ICSpecXMLPackage.PREREQUISITE__ALIAS:
				return getAlias();
			case ICSpecXMLPackage.PREREQUISITE__COMPONENT:
				return getComponent();
			case ICSpecXMLPackage.PREREQUISITE__COMPONENT_TYPE:
				return getComponentType();
			case ICSpecXMLPackage.PREREQUISITE__CONTRIBUTOR:
				return isContributor();
			case ICSpecXMLPackage.PREREQUISITE__EXCLUDE_PATTERN:
				return getExcludePattern();
			case ICSpecXMLPackage.PREREQUISITE__FILTER:
				return getFilter();
			case ICSpecXMLPackage.PREREQUISITE__INCLUDE_PATTERN:
				return getIncludePattern();
			case ICSpecXMLPackage.PREREQUISITE__NAME:
				return getName();
			case ICSpecXMLPackage.PREREQUISITE__OPTIONAL:
				return isOptional();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ICSpecXMLPackage.PREREQUISITE__ALIAS:
				return ALIAS_EDEFAULT == null ? alias != null : !ALIAS_EDEFAULT.equals(alias);
			case ICSpecXMLPackage.PREREQUISITE__COMPONENT:
				return COMPONENT_EDEFAULT == null ? component != null : !COMPONENT_EDEFAULT.equals(component);
			case ICSpecXMLPackage.PREREQUISITE__COMPONENT_TYPE:
				return COMPONENT_TYPE_EDEFAULT == null ? componentType != null : !COMPONENT_TYPE_EDEFAULT.equals(componentType);
			case ICSpecXMLPackage.PREREQUISITE__CONTRIBUTOR:
				return isSetContributor();
			case ICSpecXMLPackage.PREREQUISITE__EXCLUDE_PATTERN:
				return EXCLUDE_PATTERN_EDEFAULT == null ? excludePattern != null : !EXCLUDE_PATTERN_EDEFAULT.equals(excludePattern);
			case ICSpecXMLPackage.PREREQUISITE__FILTER:
				return FILTER_EDEFAULT == null ? filter != null : !FILTER_EDEFAULT.equals(filter);
			case ICSpecXMLPackage.PREREQUISITE__INCLUDE_PATTERN:
				return INCLUDE_PATTERN_EDEFAULT == null ? includePattern != null : !INCLUDE_PATTERN_EDEFAULT.equals(includePattern);
			case ICSpecXMLPackage.PREREQUISITE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ICSpecXMLPackage.PREREQUISITE__OPTIONAL:
				return isSetOptional();
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
			case ICSpecXMLPackage.PREREQUISITE__ALIAS:
				setAlias((String) newValue);
				return;
			case ICSpecXMLPackage.PREREQUISITE__COMPONENT:
				setComponent((String) newValue);
				return;
			case ICSpecXMLPackage.PREREQUISITE__COMPONENT_TYPE:
				setComponentType((String) newValue);
				return;
			case ICSpecXMLPackage.PREREQUISITE__CONTRIBUTOR:
				setContributor((Boolean) newValue);
				return;
			case ICSpecXMLPackage.PREREQUISITE__EXCLUDE_PATTERN:
				setExcludePattern((Pattern) newValue);
				return;
			case ICSpecXMLPackage.PREREQUISITE__FILTER:
				setFilter((Filter) newValue);
				return;
			case ICSpecXMLPackage.PREREQUISITE__INCLUDE_PATTERN:
				setIncludePattern((Pattern) newValue);
				return;
			case ICSpecXMLPackage.PREREQUISITE__NAME:
				setName((String) newValue);
				return;
			case ICSpecXMLPackage.PREREQUISITE__OPTIONAL:
				setOptional((Boolean) newValue);
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
			case ICSpecXMLPackage.PREREQUISITE__ALIAS:
				setAlias(ALIAS_EDEFAULT);
				return;
			case ICSpecXMLPackage.PREREQUISITE__COMPONENT:
				setComponent(COMPONENT_EDEFAULT);
				return;
			case ICSpecXMLPackage.PREREQUISITE__COMPONENT_TYPE:
				setComponentType(COMPONENT_TYPE_EDEFAULT);
				return;
			case ICSpecXMLPackage.PREREQUISITE__CONTRIBUTOR:
				unsetContributor();
				return;
			case ICSpecXMLPackage.PREREQUISITE__EXCLUDE_PATTERN:
				setExcludePattern(EXCLUDE_PATTERN_EDEFAULT);
				return;
			case ICSpecXMLPackage.PREREQUISITE__FILTER:
				setFilter(FILTER_EDEFAULT);
				return;
			case ICSpecXMLPackage.PREREQUISITE__INCLUDE_PATTERN:
				setIncludePattern(INCLUDE_PATTERN_EDEFAULT);
				return;
			case ICSpecXMLPackage.PREREQUISITE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ICSpecXMLPackage.PREREQUISITE__OPTIONAL:
				unsetOptional();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getComponent() {
		return component;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getComponentType() {
		return componentType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Pattern getExcludePattern() {
		return excludePattern;
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
	public Pattern getIncludePattern() {
		return includePattern;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isContributor() {
		return contributor;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isOptional() {
		return optional;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isSetContributor() {
		return contributorESet;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isSetOptional() {
		return optionalESet;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setAlias(String newAlias) {
		String oldAlias = alias;
		alias = newAlias;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ICSpecXMLPackage.PREREQUISITE__ALIAS, oldAlias, alias));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setComponent(String newComponent) {
		String oldComponent = component;
		component = newComponent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ICSpecXMLPackage.PREREQUISITE__COMPONENT, oldComponent, component));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setComponentType(String newComponentType) {
		String oldComponentType = componentType;
		componentType = newComponentType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ICSpecXMLPackage.PREREQUISITE__COMPONENT_TYPE, oldComponentType, componentType));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setContributor(boolean newContributor) {
		boolean oldContributor = contributor;
		contributor = newContributor;
		boolean oldContributorESet = contributorESet;
		contributorESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ICSpecXMLPackage.PREREQUISITE__CONTRIBUTOR, oldContributor, contributor,
					!oldContributorESet));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setExcludePattern(Pattern newExcludePattern) {
		Pattern oldExcludePattern = excludePattern;
		excludePattern = newExcludePattern;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ICSpecXMLPackage.PREREQUISITE__EXCLUDE_PATTERN, oldExcludePattern, excludePattern));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ICSpecXMLPackage.PREREQUISITE__FILTER, oldFilter, filter));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setIncludePattern(Pattern newIncludePattern) {
		Pattern oldIncludePattern = includePattern;
		includePattern = newIncludePattern;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ICSpecXMLPackage.PREREQUISITE__INCLUDE_PATTERN, oldIncludePattern, includePattern));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ICSpecXMLPackage.PREREQUISITE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setOptional(boolean newOptional) {
		boolean oldOptional = optional;
		optional = newOptional;
		boolean oldOptionalESet = optionalESet;
		optionalESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ICSpecXMLPackage.PREREQUISITE__OPTIONAL, oldOptional, optional, !oldOptionalESet));
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
		result.append(" (alias: ");
		result.append(alias);
		result.append(", component: ");
		result.append(component);
		result.append(", componentType: ");
		result.append(componentType);
		result.append(", contributor: ");
		if (contributorESet)
			result.append(contributor);
		else
			result.append("<unset>");
		result.append(", excludePattern: ");
		result.append(excludePattern);
		result.append(", filter: ");
		result.append(filter);
		result.append(", includePattern: ");
		result.append(includePattern);
		result.append(", name: ");
		result.append(name);
		result.append(", optional: ");
		if (optionalESet)
			result.append(optional);
		else
			result.append("<unset>");
		result.append(')');
		return result.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void unsetContributor() {
		boolean oldContributor = contributor;
		boolean oldContributorESet = contributorESet;
		contributor = CONTRIBUTOR_EDEFAULT;
		contributorESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ICSpecXMLPackage.PREREQUISITE__CONTRIBUTOR, oldContributor, CONTRIBUTOR_EDEFAULT,
					oldContributorESet));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void unsetOptional() {
		boolean oldOptional = optional;
		boolean oldOptionalESet = optionalESet;
		optional = OPTIONAL_EDEFAULT;
		optionalESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ICSpecXMLPackage.PREREQUISITE__OPTIONAL, oldOptional, OPTIONAL_EDEFAULT,
					oldOptionalESet));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ICSpecXMLPackage.Literals.PREREQUISITE;
	}

} // PrerequisiteImpl
