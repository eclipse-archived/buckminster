/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.model.common.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import java.util.regex.Pattern;

import org.eclipse.buckminster.model.common.CommonFactory;
import org.eclipse.buckminster.model.common.CommonPackage;
import org.eclipse.buckminster.model.common.Match;
import org.eclipse.buckminster.model.common.Replace;
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
 * <em><b>Replace</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.model.common.impl.ReplaceImpl#getMatches
 * <em>Matches</em>}</li>
 * <li>{@link org.eclipse.buckminster.model.common.impl.ReplaceImpl#getPattern
 * <em>Pattern</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.model.common.impl.ReplaceImpl#isQuotePattern
 * <em>Quote Pattern</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.model.common.impl.ReplaceImpl#getReplacement
 * <em>Replacement</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.model.common.impl.ReplaceImpl#getCompiledPattern
 * <em>Compiled Pattern</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class ReplaceImpl extends ValueFilterImpl implements Replace {
	/**
	 * The cached value of the '{@link #getMatches() <em>Matches</em>}'
	 * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getMatches()
	 * @generated
	 * @ordered
	 */
	protected EList<Match> matches;

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
	protected ReplaceImpl() {
		super();
	}

	@Override
	public String checkedGetValue(Map<String, String> props, int recursionGuard) {
		String resolved = checkedGetSourceValue(props, recursionGuard);
		if (resolved == null || NO_VALUE.equals(resolved))
			return NO_VALUE;

		List<Match> ms = getMatches();
		if (ms.size() == 0) {
			Match match = getSelfMatch();
			if (match != null) {
				String result = match.match(resolved);
				if (result != null)
					resolved = result;
			}
		} else {
			for (Match match : ms) {
				String result = match.match(resolved);
				if (result != null) {
					resolved = result;
					break;
				}
			}
		}
		return resolved;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CommonPackage.REPLACE__MATCHES:
				return getMatches();
			case CommonPackage.REPLACE__PATTERN:
				return getPattern();
			case CommonPackage.REPLACE__QUOTE_PATTERN:
				return isQuotePattern();
			case CommonPackage.REPLACE__REPLACEMENT:
				return getReplacement();
			case CommonPackage.REPLACE__COMPILED_PATTERN:
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
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CommonPackage.REPLACE__MATCHES:
				return ((InternalEList<?>) getMatches()).basicRemove(otherEnd, msgs);
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
			case CommonPackage.REPLACE__MATCHES:
				return matches != null && !matches.isEmpty();
			case CommonPackage.REPLACE__PATTERN:
				return PATTERN_EDEFAULT == null ? pattern != null : !PATTERN_EDEFAULT.equals(pattern);
			case CommonPackage.REPLACE__QUOTE_PATTERN:
				return isSetQuotePattern();
			case CommonPackage.REPLACE__REPLACEMENT:
				return REPLACEMENT_EDEFAULT == null ? replacement != null : !REPLACEMENT_EDEFAULT.equals(replacement);
			case CommonPackage.REPLACE__COMPILED_PATTERN:
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
	@SuppressWarnings("unchecked")
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case CommonPackage.REPLACE__MATCHES:
				getMatches().clear();
				getMatches().addAll((Collection<? extends Match>) newValue);
				return;
			case CommonPackage.REPLACE__PATTERN:
				setPattern((String) newValue);
				return;
			case CommonPackage.REPLACE__QUOTE_PATTERN:
				setQuotePattern((Boolean) newValue);
				return;
			case CommonPackage.REPLACE__REPLACEMENT:
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
			case CommonPackage.REPLACE__MATCHES:
				getMatches().clear();
				return;
			case CommonPackage.REPLACE__PATTERN:
				setPattern(PATTERN_EDEFAULT);
				return;
			case CommonPackage.REPLACE__QUOTE_PATTERN:
				unsetQuotePattern();
				return;
			case CommonPackage.REPLACE__REPLACEMENT:
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

	@Override
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

	@Override
	public EList<Match> getMatches() {
		if (matches == null) {
			matches = new EObjectContainmentEList<Match>(Match.class, this, CommonPackage.REPLACE__MATCHES);
		}
		return matches;
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
	public String getReplacement() {
		return replacement;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public boolean isQuotePattern() {
		return quotePattern;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public boolean isSetQuotePattern() {
		return quotePatternESet;
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
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.REPLACE__PATTERN, oldPattern, pattern));
	}

	@Override
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
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.REPLACE__QUOTE_PATTERN, oldQuotePattern, quotePattern,
					!oldQuotePatternESet));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public void setReplacement(String newReplacement) {
		String oldReplacement = replacement;
		replacement = newReplacement;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.REPLACE__REPLACEMENT, oldReplacement, replacement));
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

	@Override
	public void unsetQuotePattern() {
		boolean oldQuotePattern = quotePattern;
		boolean oldQuotePatternESet = quotePatternESet;
		quotePattern = QUOTE_PATTERN_EDEFAULT;
		quotePatternESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, CommonPackage.REPLACE__QUOTE_PATTERN, oldQuotePattern, QUOTE_PATTERN_EDEFAULT,
					oldQuotePatternESet));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	protected EClass eStaticClass() {
		return CommonPackage.Literals.REPLACE;
	}

	private Match getSelfMatch() {
		Match match = CommonFactory.eINSTANCE.createMatch();
		match.setPattern(getPattern());
		match.setQuotePattern(isQuotePattern());
		match.setReplacement(getReplacement());
		return match;
	}

} // ReplaceImpl
