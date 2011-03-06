/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.eclipse.buckminster.model.common.ComponentName;
import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.buckminster.rmap.Matcher;
import org.eclipse.buckminster.rmap.ResourceMap;
import org.eclipse.buckminster.rmap.RmapPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Matcher</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.rmap.impl.MatcherImpl#getPattern <em>
 * Pattern</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.rmap.impl.MatcherImpl#getComponentTypesAttr
 * <em>Component Types Attr</em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.impl.MatcherImpl#getComponentTypes
 * <em>Component Types</em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.impl.MatcherImpl#getResolutionFilter
 * <em>Resolution Filter</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public abstract class MatcherImpl extends EObjectImpl implements Matcher {
	/**
	 * The default value of the '{@link #getPattern() <em>Pattern</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getPattern()
	 * @generated
	 * @ordered
	 */
	protected static final Pattern PATTERN_EDEFAULT = null;

	static String getComponentTypesAttr(Matcher matcher) {
		EList<String> ctypes = matcher.getComponentTypes();
		int top = ctypes.size();
		switch (top) {
			case 0:
				return null;
			case 1:
				return ctypes.get(0);
		}
		StringBuilder bld = new StringBuilder();
		bld.append(ctypes.get(0));
		for (int idx = 1; idx < top; ++idx) {
			bld.append(',');
			bld.append(ctypes.get(idx));
		}
		return bld.toString();
	}

	static boolean matches(Matcher matcher, ComponentName componentName, Map<String, String> properties) {
		Pattern p = matcher.getPattern();
		if (!(p == null || p.matcher(componentName.getId()).find()))
			return false;

		String ctype = componentName.getType();
		List<String> ctypes = matcher.getComponentTypes();
		int idx = ctypes.size();
		if (idx > 0) {
			if (ctype == null)
				return false;

			while (--idx >= 0)
				if (ctypes.get(idx).equals(ctype))
					break;
			if (idx < 0)
				return false;
		}

		Filter rf = matcher.getResolutionFilter();
		if (rf != null && !rf.matches(properties))
			return false;
		return true;
	}

	static void setComponentTypesAttr(Matcher matcher, String newComponentTypesAttr) {
		EList<String> ctypes = matcher.getComponentTypes();
		ctypes.clear();
		if (newComponentTypesAttr == null || newComponentTypesAttr.length() == 0)
			return;

		int commaIdx = newComponentTypesAttr.indexOf(',');
		int idx = 0;
		while (commaIdx >= idx) {
			ctypes.add(newComponentTypesAttr.substring(idx, commaIdx));
			idx = commaIdx + 1;
			commaIdx = newComponentTypesAttr.indexOf(',', idx);
		}
		if (idx < newComponentTypesAttr.length())
			ctypes.add(newComponentTypesAttr.substring(idx));
	}

	/**
	 * The cached value of the '{@link #getPattern() <em>Pattern</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getPattern()
	 * @generated
	 * @ordered
	 */
	protected Pattern pattern = PATTERN_EDEFAULT;

	/**
	 * The default value of the '{@link #getComponentTypesAttr()
	 * <em>Component Types Attr</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getComponentTypesAttr()
	 * @generated
	 * @ordered
	 */
	protected static final String COMPONENT_TYPES_ATTR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getComponentTypes()
	 * <em>Component Types</em>}' attribute list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getComponentTypes()
	 * @generated
	 * @ordered
	 */
	protected EList<String> componentTypes;

	/**
	 * The default value of the '{@link #getResolutionFilter()
	 * <em>Resolution Filter</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getResolutionFilter()
	 * @generated
	 * @ordered
	 */
	protected static final Filter RESOLUTION_FILTER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getResolutionFilter()
	 * <em>Resolution Filter</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getResolutionFilter()
	 * @generated
	 * @ordered
	 */
	protected Filter resolutionFilter = RESOLUTION_FILTER_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected MatcherImpl() {
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
			case RmapPackage.MATCHER__PATTERN:
				return getPattern();
			case RmapPackage.MATCHER__COMPONENT_TYPES_ATTR:
				return getComponentTypesAttr();
			case RmapPackage.MATCHER__COMPONENT_TYPES:
				return getComponentTypes();
			case RmapPackage.MATCHER__RESOLUTION_FILTER:
				return getResolutionFilter();
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
			case RmapPackage.MATCHER__PATTERN:
				return PATTERN_EDEFAULT == null ? pattern != null : !PATTERN_EDEFAULT.equals(pattern);
			case RmapPackage.MATCHER__COMPONENT_TYPES_ATTR:
				return COMPONENT_TYPES_ATTR_EDEFAULT == null ? getComponentTypesAttr() != null : !COMPONENT_TYPES_ATTR_EDEFAULT
						.equals(getComponentTypesAttr());
			case RmapPackage.MATCHER__COMPONENT_TYPES:
				return componentTypes != null && !componentTypes.isEmpty();
			case RmapPackage.MATCHER__RESOLUTION_FILTER:
				return RESOLUTION_FILTER_EDEFAULT == null ? resolutionFilter != null : !RESOLUTION_FILTER_EDEFAULT.equals(resolutionFilter);
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
			case RmapPackage.MATCHER__PATTERN:
				setPattern((Pattern) newValue);
				return;
			case RmapPackage.MATCHER__COMPONENT_TYPES_ATTR:
				setComponentTypesAttr((String) newValue);
				return;
			case RmapPackage.MATCHER__COMPONENT_TYPES:
				getComponentTypes().clear();
				getComponentTypes().addAll((Collection<? extends String>) newValue);
				return;
			case RmapPackage.MATCHER__RESOLUTION_FILTER:
				setResolutionFilter((Filter) newValue);
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
			case RmapPackage.MATCHER__PATTERN:
				setPattern(PATTERN_EDEFAULT);
				return;
			case RmapPackage.MATCHER__COMPONENT_TYPES_ATTR:
				setComponentTypesAttr(COMPONENT_TYPES_ATTR_EDEFAULT);
				return;
			case RmapPackage.MATCHER__COMPONENT_TYPES:
				getComponentTypes().clear();
				return;
			case RmapPackage.MATCHER__RESOLUTION_FILTER:
				setResolutionFilter(RESOLUTION_FILTER_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<String> getComponentTypes() {
		if (componentTypes == null) {
			componentTypes = new EDataTypeUniqueEList<String>(String.class, this, RmapPackage.MATCHER__COMPONENT_TYPES);
		}
		return componentTypes;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String getComponentTypesAttr() {
		return getComponentTypesAttr(this);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public Pattern getPattern() {
		return pattern;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Filter getResolutionFilter() {
		return resolutionFilter;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public ResourceMap getResourceMap() {
		return (ResourceMap) eContainer();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean matches(ComponentName componentName, Map<String, String> properties) {
		return matches(this, componentName, properties);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setComponentTypesAttr(String newComponentTypesAttr) {
		setComponentTypesAttr(this, newComponentTypesAttr);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public void setPattern(Pattern newPattern) {
		Pattern oldPattern = pattern;
		pattern = newPattern;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RmapPackage.MATCHER__PATTERN, oldPattern, pattern));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setResolutionFilter(Filter newResolutionFilter) {
		Filter oldResolutionFilter = resolutionFilter;
		resolutionFilter = newResolutionFilter;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RmapPackage.MATCHER__RESOLUTION_FILTER, oldResolutionFilter, resolutionFilter));
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
		result.append(" (pattern: ");
		result.append(pattern);
		result.append(", componentTypes: ");
		result.append(componentTypes);
		result.append(", resolutionFilter: ");
		result.append(resolutionFilter);
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
		return RmapPackage.Literals.MATCHER;
	}

} // MatcherImpl
