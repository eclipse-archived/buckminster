/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspec.impl;

import java.util.regex.Pattern;
import org.eclipse.buckminster.cspec.CspecPackage;
import org.eclipse.buckminster.cspec.Prerequisite;

import org.eclipse.buckminster.model.common.ComponentRequest;

import org.eclipse.buckminster.osgi.filter.Filter;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Prerequisite</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.cspec.impl.PrerequisiteImpl#getComponent <em>Component</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.impl.PrerequisiteImpl#getAttribute <em>Attribute</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.impl.PrerequisiteImpl#getAlias <em>Alias</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.impl.PrerequisiteImpl#isContributor <em>Contributor</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.impl.PrerequisiteImpl#isOptional <em>Optional</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.impl.PrerequisiteImpl#getFilter <em>Filter</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.impl.PrerequisiteImpl#getIncludePattern <em>Include Pattern</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.impl.PrerequisiteImpl#getExcludePattern <em>Exclude Pattern</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class PrerequisiteImpl extends EObjectImpl implements Prerequisite
{
	/**
	 * The cached value of the '{@link #getComponent() <em>Component</em>}' reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getComponent()
	 * @generated
	 * @ordered
	 */
	protected ComponentRequest component;

	/**
	 * The default value of the '{@link #getAttribute() <em>Attribute</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getAttribute()
	 * @generated
	 * @ordered
	 */
	protected static final String ATTRIBUTE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAttribute() <em>Attribute</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getAttribute()
	 * @generated
	 * @ordered
	 */
	protected String attribute = ATTRIBUTE_EDEFAULT;

	/**
	 * The default value of the '{@link #getAlias() <em>Alias</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getAlias()
	 * @generated
	 * @ordered
	 */
	protected static final String ALIAS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAlias() <em>Alias</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getAlias()
	 * @generated
	 * @ordered
	 */
	protected String alias = ALIAS_EDEFAULT;

	/**
	 * The default value of the '{@link #isContributor() <em>Contributor</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #isContributor()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CONTRIBUTOR_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isContributor() <em>Contributor</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #isContributor()
	 * @generated
	 * @ordered
	 */
	protected boolean contributor = CONTRIBUTOR_EDEFAULT;

	/**
	 * The default value of the '{@link #isOptional() <em>Optional</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #isOptional()
	 * @generated
	 * @ordered
	 */
	protected static final boolean OPTIONAL_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isOptional() <em>Optional</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #isOptional()
	 * @generated
	 * @ordered
	 */
	protected boolean optional = OPTIONAL_EDEFAULT;

	/**
	 * The default value of the '{@link #getFilter() <em>Filter</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getFilter()
	 * @generated
	 * @ordered
	 */
	protected static final Filter FILTER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFilter() <em>Filter</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getFilter()
	 * @generated
	 * @ordered
	 */
	protected Filter filter = FILTER_EDEFAULT;

	/**
	 * The default value of the '{@link #getIncludePattern() <em>Include Pattern</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getIncludePattern()
	 * @generated
	 * @ordered
	 */
	protected static final Pattern INCLUDE_PATTERN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIncludePattern() <em>Include Pattern</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getIncludePattern()
	 * @generated
	 * @ordered
	 */
	protected Pattern includePattern = INCLUDE_PATTERN_EDEFAULT;

	/**
	 * The default value of the '{@link #getExcludePattern() <em>Exclude Pattern</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getExcludePattern()
	 * @generated
	 * @ordered
	 */
	protected static final Pattern EXCLUDE_PATTERN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getExcludePattern() <em>Exclude Pattern</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getExcludePattern()
	 * @generated
	 * @ordered
	 */
	protected Pattern excludePattern = EXCLUDE_PATTERN_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected PrerequisiteImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ComponentRequest basicGetComponent()
	{
		return component;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType)
	{
		switch(featureID)
		{
		case CspecPackage.PREREQUISITE__COMPONENT:
			if(resolve)
				return getComponent();
			return basicGetComponent();
		case CspecPackage.PREREQUISITE__ATTRIBUTE:
			return getAttribute();
		case CspecPackage.PREREQUISITE__ALIAS:
			return getAlias();
		case CspecPackage.PREREQUISITE__CONTRIBUTOR:
			return isContributor();
		case CspecPackage.PREREQUISITE__OPTIONAL:
			return isOptional();
		case CspecPackage.PREREQUISITE__FILTER:
			return getFilter();
		case CspecPackage.PREREQUISITE__INCLUDE_PATTERN:
			return getIncludePattern();
		case CspecPackage.PREREQUISITE__EXCLUDE_PATTERN:
			return getExcludePattern();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID)
	{
		switch(featureID)
		{
		case CspecPackage.PREREQUISITE__COMPONENT:
			return component != null;
		case CspecPackage.PREREQUISITE__ATTRIBUTE:
			return ATTRIBUTE_EDEFAULT == null
					? attribute != null
					: !ATTRIBUTE_EDEFAULT.equals(attribute);
		case CspecPackage.PREREQUISITE__ALIAS:
			return ALIAS_EDEFAULT == null
					? alias != null
					: !ALIAS_EDEFAULT.equals(alias);
		case CspecPackage.PREREQUISITE__CONTRIBUTOR:
			return contributor != CONTRIBUTOR_EDEFAULT;
		case CspecPackage.PREREQUISITE__OPTIONAL:
			return optional != OPTIONAL_EDEFAULT;
		case CspecPackage.PREREQUISITE__FILTER:
			return FILTER_EDEFAULT == null
					? filter != null
					: !FILTER_EDEFAULT.equals(filter);
		case CspecPackage.PREREQUISITE__INCLUDE_PATTERN:
			return INCLUDE_PATTERN_EDEFAULT == null
					? includePattern != null
					: !INCLUDE_PATTERN_EDEFAULT.equals(includePattern);
		case CspecPackage.PREREQUISITE__EXCLUDE_PATTERN:
			return EXCLUDE_PATTERN_EDEFAULT == null
					? excludePattern != null
					: !EXCLUDE_PATTERN_EDEFAULT.equals(excludePattern);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue)
	{
		switch(featureID)
		{
		case CspecPackage.PREREQUISITE__COMPONENT:
			setComponent((ComponentRequest)newValue);
			return;
		case CspecPackage.PREREQUISITE__ATTRIBUTE:
			setAttribute((String)newValue);
			return;
		case CspecPackage.PREREQUISITE__ALIAS:
			setAlias((String)newValue);
			return;
		case CspecPackage.PREREQUISITE__CONTRIBUTOR:
			setContributor((Boolean)newValue);
			return;
		case CspecPackage.PREREQUISITE__OPTIONAL:
			setOptional((Boolean)newValue);
			return;
		case CspecPackage.PREREQUISITE__FILTER:
			setFilter((Filter)newValue);
			return;
		case CspecPackage.PREREQUISITE__INCLUDE_PATTERN:
			setIncludePattern((Pattern)newValue);
			return;
		case CspecPackage.PREREQUISITE__EXCLUDE_PATTERN:
			setExcludePattern((Pattern)newValue);
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
	public void eUnset(int featureID)
	{
		switch(featureID)
		{
		case CspecPackage.PREREQUISITE__COMPONENT:
			setComponent((ComponentRequest)null);
			return;
		case CspecPackage.PREREQUISITE__ATTRIBUTE:
			setAttribute(ATTRIBUTE_EDEFAULT);
			return;
		case CspecPackage.PREREQUISITE__ALIAS:
			setAlias(ALIAS_EDEFAULT);
			return;
		case CspecPackage.PREREQUISITE__CONTRIBUTOR:
			setContributor(CONTRIBUTOR_EDEFAULT);
			return;
		case CspecPackage.PREREQUISITE__OPTIONAL:
			setOptional(OPTIONAL_EDEFAULT);
			return;
		case CspecPackage.PREREQUISITE__FILTER:
			setFilter(FILTER_EDEFAULT);
			return;
		case CspecPackage.PREREQUISITE__INCLUDE_PATTERN:
			setIncludePattern(INCLUDE_PATTERN_EDEFAULT);
			return;
		case CspecPackage.PREREQUISITE__EXCLUDE_PATTERN:
			setExcludePattern(EXCLUDE_PATTERN_EDEFAULT);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getAlias()
	{
		return alias;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getAttribute()
	{
		return attribute;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ComponentRequest getComponent()
	{
		if(component != null && component.eIsProxy())
		{
			InternalEObject oldComponent = (InternalEObject)component;
			component = (ComponentRequest)eResolveProxy(oldComponent);
			if(component != oldComponent)
			{
				if(eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CspecPackage.PREREQUISITE__COMPONENT,
							oldComponent, component));
			}
		}
		return component;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Pattern getExcludePattern()
	{
		return excludePattern;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Filter getFilter()
	{
		return filter;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Pattern getIncludePattern()
	{
		return includePattern;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isContributor()
	{
		return contributor;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isOptional()
	{
		return optional;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setAlias(String newAlias)
	{
		String oldAlias = alias;
		alias = newAlias;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CspecPackage.PREREQUISITE__ALIAS, oldAlias, alias));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setAttribute(String newAttribute)
	{
		String oldAttribute = attribute;
		attribute = newAttribute;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CspecPackage.PREREQUISITE__ATTRIBUTE, oldAttribute,
					attribute));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setComponent(ComponentRequest newComponent)
	{
		ComponentRequest oldComponent = component;
		component = newComponent;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CspecPackage.PREREQUISITE__COMPONENT, oldComponent,
					component));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setContributor(boolean newContributor)
	{
		boolean oldContributor = contributor;
		contributor = newContributor;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CspecPackage.PREREQUISITE__CONTRIBUTOR,
					oldContributor, contributor));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setExcludePattern(Pattern newExcludePattern)
	{
		Pattern oldExcludePattern = excludePattern;
		excludePattern = newExcludePattern;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CspecPackage.PREREQUISITE__EXCLUDE_PATTERN,
					oldExcludePattern, excludePattern));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setFilter(Filter newFilter)
	{
		Filter oldFilter = filter;
		filter = newFilter;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CspecPackage.PREREQUISITE__FILTER, oldFilter, filter));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setIncludePattern(Pattern newIncludePattern)
	{
		Pattern oldIncludePattern = includePattern;
		includePattern = newIncludePattern;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CspecPackage.PREREQUISITE__INCLUDE_PATTERN,
					oldIncludePattern, includePattern));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setOptional(boolean newOptional)
	{
		boolean oldOptional = optional;
		optional = newOptional;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CspecPackage.PREREQUISITE__OPTIONAL, oldOptional,
					optional));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String toString()
	{
		if(eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (attribute: ");
		result.append(attribute);
		result.append(", alias: ");
		result.append(alias);
		result.append(", contributor: ");
		result.append(contributor);
		result.append(", optional: ");
		result.append(optional);
		result.append(", filter: ");
		result.append(filter);
		result.append(", includePattern: ");
		result.append(includePattern);
		result.append(", excludePattern: ");
		result.append(excludePattern);
		result.append(')');
		return result.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass()
	{
		return CspecPackage.Literals.PREREQUISITE;
	}

} // PrerequisiteImpl
