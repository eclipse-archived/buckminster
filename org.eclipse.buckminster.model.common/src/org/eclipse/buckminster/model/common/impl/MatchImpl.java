/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.model.common.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.eclipse.buckminster.model.common.CommonPackage;
import org.eclipse.buckminster.model.common.Match;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Match</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.model.common.impl.MatchImpl#getPattern
 * <em>Pattern</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.model.common.impl.MatchImpl#isQuotePattern
 * <em>Quote Pattern</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.model.common.impl.MatchImpl#getReplacement
 * <em>Replacement</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.model.common.impl.MatchImpl#getCompiledPattern
 * <em>Compiled Pattern</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class MatchImpl extends EObjectImpl implements Match {
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
	 * The default value of the '{@link #isQuotePattern()
	 * <em>Quote Pattern</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #isQuotePattern()
	 * @generated
	 * @ordered
	 */
	protected static final boolean QUOTE_PATTERN_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isQuotePattern() <em>Quote Pattern</em>}
	 * ' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isQuotePattern()
	 * @generated
	 * @ordered
	 */
	protected boolean quotePattern = QUOTE_PATTERN_EDEFAULT;

	/**
	 * This is true if the Quote Pattern attribute has been set. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	protected boolean quotePatternESet;

	/**
	 * The default value of the '{@link #getReplacement() <em>Replacement</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getReplacement()
	 * @generated
	 * @ordered
	 */
	protected static final String REPLACEMENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getReplacement() <em>Replacement</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getReplacement()
	 * @generated
	 * @ordered
	 */
	protected String replacement = REPLACEMENT_EDEFAULT;

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
	protected MatchImpl() {
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
			case CommonPackage.MATCH__PATTERN:
				return getPattern();
			case CommonPackage.MATCH__QUOTE_PATTERN:
				return isQuotePattern();
			case CommonPackage.MATCH__REPLACEMENT:
				return getReplacement();
			case CommonPackage.MATCH__COMPILED_PATTERN:
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
			case CommonPackage.MATCH__PATTERN:
				return PATTERN_EDEFAULT == null ? pattern != null : !PATTERN_EDEFAULT.equals(pattern);
			case CommonPackage.MATCH__QUOTE_PATTERN:
				return isSetQuotePattern();
			case CommonPackage.MATCH__REPLACEMENT:
				return REPLACEMENT_EDEFAULT == null ? replacement != null : !REPLACEMENT_EDEFAULT.equals(replacement);
			case CommonPackage.MATCH__COMPILED_PATTERN:
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
			case CommonPackage.MATCH__PATTERN:
				setPattern((String) newValue);
				return;
			case CommonPackage.MATCH__QUOTE_PATTERN:
				setQuotePattern((Boolean) newValue);
				return;
			case CommonPackage.MATCH__REPLACEMENT:
				setReplacement((String) newValue);
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
			case CommonPackage.MATCH__PATTERN:
				setPattern(PATTERN_EDEFAULT);
				return;
			case CommonPackage.MATCH__QUOTE_PATTERN:
				unsetQuotePattern();
				return;
			case CommonPackage.MATCH__REPLACEMENT:
				setReplacement(REPLACEMENT_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */

	public synchronized Pattern getCompiledPattern() {
		if (compiledPattern == null) {
			String tmp = getPattern();
			if (tmp == null)
				return null;

			if (isQuotePattern())
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

	public String getPattern() {
		return pattern;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public String getReplacement() {
		return replacement;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public boolean isQuotePattern() {
		return quotePattern;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public boolean isSetQuotePattern() {
		return quotePatternESet;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */

	public String match(String resolved) {
		Matcher matcher = getCompiledPattern().matcher(resolved);
		if (matcher.find()) {
			StringBuffer sb = new StringBuffer();
			do {
				matcher.appendReplacement(sb, getReplacement());
			} while (matcher.find());
			matcher.appendTail(sb);
			return sb.toString();
		}
		return null;
	}

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
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.MATCH__PATTERN, oldPattern, pattern));
	}

	public synchronized void setQuotePattern(boolean newQuotePattern) {
		setQuotePatternGen(newQuotePattern);
		compiledPattern = null;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setQuotePatternGen(boolean newQuotePattern) {
		boolean oldQuotePattern = quotePattern;
		quotePattern = newQuotePattern;
		boolean oldQuotePatternESet = quotePatternESet;
		quotePatternESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.MATCH__QUOTE_PATTERN, oldQuotePattern, quotePattern,
					!oldQuotePatternESet));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public void setReplacement(String newReplacement) {
		String oldReplacement = replacement;
		replacement = newReplacement;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.MATCH__REPLACEMENT, oldReplacement, replacement));
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
		result.append(", quotePattern: ");
		if (quotePatternESet)
			result.append(quotePattern);
		else
			result.append("<unset>");
		result.append(", replacement: ");
		result.append(replacement);
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

	public void unsetQuotePattern() {
		boolean oldQuotePattern = quotePattern;
		boolean oldQuotePatternESet = quotePatternESet;
		quotePattern = QUOTE_PATTERN_EDEFAULT;
		quotePatternESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, CommonPackage.MATCH__QUOTE_PATTERN, oldQuotePattern, QUOTE_PATTERN_EDEFAULT,
					oldQuotePatternESet));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	protected EClass eStaticClass() {
		return CommonPackage.Literals.MATCH;
	}

} // MatchImpl
