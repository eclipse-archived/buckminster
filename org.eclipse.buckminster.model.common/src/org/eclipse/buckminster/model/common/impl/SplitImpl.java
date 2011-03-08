/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.model.common.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.buckminster.model.common.CommonPackage;
import org.eclipse.buckminster.model.common.Split;
import org.eclipse.buckminster.model.common.SplitType;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Split</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.model.common.impl.SplitImpl#getLimit <em>
 * Limit</em>}</li>
 * <li>{@link org.eclipse.buckminster.model.common.impl.SplitImpl#getPattern
 * <em>Pattern</em>}</li>
 * <li>{@link org.eclipse.buckminster.model.common.impl.SplitImpl#getStyle <em>
 * Style</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.model.common.impl.SplitImpl#getCompiledPattern
 * <em>Compiled Pattern</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class SplitImpl extends ValueFilterImpl implements Split {
	/**
	 * The default value of the '{@link #getLimit() <em>Limit</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getLimit()
	 * @generated
	 * @ordered
	 */
	protected static final int LIMIT_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getLimit() <em>Limit</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getLimit()
	 * @generated
	 * @ordered
	 */
	protected int limit = LIMIT_EDEFAULT;

	/**
	 * The default value of the '{@link #getPattern() <em>Pattern</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getPattern()
	 * @generated
	 * @ordered
	 */
	protected static final String PATTERN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPattern() <em>Pattern</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getPattern()
	 * @generated
	 * @ordered
	 */
	protected String pattern = PATTERN_EDEFAULT;

	/**
	 * The default value of the '{@link #getStyle() <em>Style</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getStyle()
	 * @generated
	 * @ordered
	 */
	protected static final SplitType STYLE_EDEFAULT = SplitType.UNQUOTED;

	/**
	 * The cached value of the '{@link #getStyle() <em>Style</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getStyle()
	 * @generated
	 * @ordered
	 */
	protected SplitType style = STYLE_EDEFAULT;

	/**
	 * This is true if the Style attribute has been set. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	protected boolean styleESet;

	/**
	 * The default value of the '{@link #getCompiledPattern()
	 * <em>Compiled Pattern</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getCompiledPattern()
	 * @generated
	 * @ordered
	 */
	protected static final Pattern COMPILED_PATTERN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCompiledPattern()
	 * <em>Compiled Pattern</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getCompiledPattern()
	 * @generated
	 * @ordered
	 */
	protected Pattern compiledPattern = COMPILED_PATTERN_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected SplitImpl() {
		super();
	}

	@Override
	public String checkedGetValue(Map<String, String> properties, int recursionGuard) {
		List<String> values = checkedGetValues(properties, recursionGuard);
		int top = values.size();
		if (top == 0)
			return NO_VALUE;

		if (top == 1)
			return values.get(0);

		StringBuilder bld = new StringBuilder();
		for (int idx = 0; idx < top; ++idx)
			bld.append(values.get(idx));
		return bld.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CommonPackage.SPLIT__LIMIT:
				return getLimit();
			case CommonPackage.SPLIT__PATTERN:
				return getPattern();
			case CommonPackage.SPLIT__STYLE:
				return getStyle();
			case CommonPackage.SPLIT__COMPILED_PATTERN:
				return getCompiledPattern();
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
			case CommonPackage.SPLIT__LIMIT:
				return limit != LIMIT_EDEFAULT;
			case CommonPackage.SPLIT__PATTERN:
				return PATTERN_EDEFAULT == null ? pattern != null : !PATTERN_EDEFAULT.equals(pattern);
			case CommonPackage.SPLIT__STYLE:
				return isSetStyle();
			case CommonPackage.SPLIT__COMPILED_PATTERN:
				return COMPILED_PATTERN_EDEFAULT == null ? compiledPattern != null : !COMPILED_PATTERN_EDEFAULT.equals(compiledPattern);
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
			case CommonPackage.SPLIT__LIMIT:
				setLimit((Integer) newValue);
				return;
			case CommonPackage.SPLIT__PATTERN:
				setPattern((String) newValue);
				return;
			case CommonPackage.SPLIT__STYLE:
				setStyle((SplitType) newValue);
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
			case CommonPackage.SPLIT__LIMIT:
				setLimit(LIMIT_EDEFAULT);
				return;
			case CommonPackage.SPLIT__PATTERN:
				setPattern(PATTERN_EDEFAULT);
				return;
			case CommonPackage.SPLIT__STYLE:
				unsetStyle();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */

	@Override
	public synchronized Pattern getCompiledPattern() {
		if (compiledPattern == null) {
			String tmp = getPattern();
			if (tmp == null)
				return null;

			if (getStyle() == SplitType.QUOTED)
				tmp = Pattern.quote(tmp);
			compiledPattern = Pattern.compile(tmp);
		}
		return compiledPattern;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public int getLimit() {
		return limit;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public String getPattern() {
		return pattern;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public SplitType getStyle() {
		return style;
	}

	@Override
	public boolean isMultiValued() {
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public boolean isSetStyle() {
		return styleESet;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public void setLimit(int newLimit) {
		int oldLimit = limit;
		limit = newLimit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SPLIT__LIMIT, oldLimit, limit));
	}

	@Override
	public synchronized void setPattern(String newPattern) {
		setPatternGen(newPattern);
		compiledPattern = null;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setPatternGen(String newPattern) {
		String oldPattern = pattern;
		pattern = newPattern;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SPLIT__PATTERN, oldPattern, pattern));
	}

	@Override
	public void setStyle(SplitType newStyle) {
		setStyleGen(newStyle);
		compiledPattern = null;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setStyleGen(SplitType newStyle) {
		SplitType oldStyle = style;
		style = newStyle == null ? STYLE_EDEFAULT : newStyle;
		boolean oldStyleESet = styleESet;
		styleESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SPLIT__STYLE, oldStyle, style, !oldStyleESet));
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
		result.append(" (limit: ");
		result.append(limit);
		result.append(", pattern: ");
		result.append(pattern);
		result.append(", style: ");
		if (styleESet)
			result.append(style);
		else
			result.append("<unset>");
		result.append(", compiledPattern: ");
		result.append(compiledPattern);
		result.append(')');
		return result.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public void unsetStyle() {
		SplitType oldStyle = style;
		boolean oldStyleESet = styleESet;
		style = STYLE_EDEFAULT;
		styleESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, CommonPackage.SPLIT__STYLE, oldStyle, STYLE_EDEFAULT, oldStyleESet));
	}

	@Override
	protected List<String> checkedGetValues(Map<String, String> properties, int recursionGuard) {
		String source = checkedGetSourceValue(properties, recursionGuard);
		if (source == null)
			return Collections.emptyList();
		if (getStyle() != SplitType.GROUPS)
			return Arrays.asList(getCompiledPattern().split(source, limit));

		Matcher m = getCompiledPattern().matcher(source);
		if (!m.matches())
			return Collections.emptyList();

		int nGroups = m.groupCount();
		ArrayList<String> result = new ArrayList<String>(nGroups);
		for (int idx = 0; idx < nGroups; ++idx)
			result.add(m.group(idx + 1));
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	protected EClass eStaticClass() {
		return CommonPackage.Literals.SPLIT;
	}

} // SplitImpl
