/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.model.common.impl;

import java.util.List;
import java.util.regex.PatternSyntaxException;

import org.eclipse.buckminster.model.common.CommonPackage;
import org.eclipse.buckminster.model.common.RxPart;
import org.eclipse.buckminster.model.common.RxPattern;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Rx Pattern</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.buckminster.model.common.impl.RxPatternImpl#getPattern
 * <em>Pattern</em>}</li>
 * <li>{@link org.eclipse.buckminster.model.common.impl.RxPatternImpl#getPrefix
 * <em>Prefix</em>}</li>
 * <li>{@link org.eclipse.buckminster.model.common.impl.RxPatternImpl#getSuffix
 * <em>Suffix</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class RxPatternImpl extends RxPartImpl implements RxPattern {
	/**
	 * The default value of the '{@link #getPattern() <em>Pattern</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getPattern()
	 * @generated
	 * @ordered
	 */
	protected static final String PATTERN_EDEFAULT = null;

	private static void addEscapedPattern(StringBuilder bld, String pattern, boolean willBeGroup) {
		// No capturing groups permitted. All groups must therefore be converted
		// into non capturing groups
		//
		int orDepth = -1;
		int startPos = bld.length();
		int parenDepth = 0;
		boolean inCharGroup = false;
		boolean stripOuter = false;

		int top = pattern.length();
		for (int idx = 0; idx < top;) {
			char c = pattern.charAt(idx++);
			switch (c) {
				case '\\':
					// Next is escaped
					//
					bld.append(c);
					if (idx < top)
						c = pattern.charAt(idx++);
					break;
				case '|':
					if (orDepth == -1 || orDepth > parenDepth)
						orDepth = parenDepth;
					break;
				case '[':
					inCharGroup = true;
					break;
				case ']':
					inCharGroup = false;
					break;
				case '(':
					if (inCharGroup)
						break;

					++parenDepth;
					if (idx == top)
						break;

					if (pattern.charAt(idx) != '?') {
						// If the pattern starts with a group and this group
						// contains the whole pattern
						// then it should be stripped off
						//
						if (idx == 1)
							stripOuter = true;
						bld.append("(?"); //$NON-NLS-1$
						c = ':';
					} else {
						if (idx == 1 && top > 2)
							stripOuter = (pattern.charAt(2) == ':');
					}
					break;
				case ')':
					if (inCharGroup)
						break;

					parenDepth--;
					if (parenDepth < 0)
						break;

					if (parenDepth == 0 && idx < top)
						stripOuter = false;
					break;
			}
			bld.append(c);
		}

		if (parenDepth != 0)
			throw new PatternSyntaxException("Unbalanced parenthesis", pattern, 0);

		if (stripOuter) {
			if (willBeGroup || orDepth != 1) {
				int tpos = startPos;
				int fpos = startPos + 3; // We strip '(?:'
				int epos = bld.length() - 1; // and ')'
				while (fpos < epos)
					bld.setCharAt(tpos++, bld.charAt(fpos++));
				bld.setLength(tpos);
			}
		} else if (!willBeGroup && orDepth == 0) {
			// A group must be added to limit what's affected by the
			// OR expression
			//
			String subExpr = bld.substring(startPos, bld.length());
			bld.setLength(startPos);
			bld.append("(?:"); //$NON-NLS-1$
			bld.append(subExpr);
			bld.append(')');
		}
	}

	private static void addQuotedString(StringBuilder bld, String str) {
		int top = str.length();
		for (int idx = 0; idx < top; ++idx) {
			char c = str.charAt(idx);
			switch (c) {
				case '\\':
				case '(':
				case ')':
				case '[':
				case ']':
				case '{':
				case '}':
				case '.':
				case '?':
				case '+':
				case '*':
				case '|':
				case '^':
				case '$':
					bld.append('\\');
			}
			bld.append(c);
		}
	}

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
	 * The default value of the '{@link #getPrefix() <em>Prefix</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getPrefix()
	 * @generated
	 * @ordered
	 */
	protected static final String PREFIX_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPrefix() <em>Prefix</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getPrefix()
	 * @generated
	 * @ordered
	 */
	protected String prefix = PREFIX_EDEFAULT;

	/**
	 * The default value of the '{@link #getSuffix() <em>Suffix</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getSuffix()
	 * @generated
	 * @ordered
	 */
	protected static final String SUFFIX_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSuffix() <em>Suffix</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getSuffix()
	 * @generated
	 * @ordered
	 */
	protected String suffix = SUFFIX_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected RxPatternImpl() {
		super();
	}

	@Override
	public void addPattern(StringBuilder bld, List<RxPart> namedParts) {
		if (!isOptional()) {
			addInnerPattern(bld, namedParts, false);
			return;
		}

		// Everything must be in a group that is marked as optional
		//
		bld.append('(');
		if (prefix == null && suffix == null) {
			if (name == null)
				bld.append("?:"); // Non capturing group //$NON-NLS-1$
			else
				namedParts.add(this);
			if (pattern != null)
				addEscapedPattern(bld, pattern, true);
		} else {
			// Group as a whole must be a non capturing group
			//
			bld.append("?:"); //$NON-NLS-1$
			addInnerPattern(bld, namedParts, true);
		}
		bld.append(")?"); //$NON-NLS-1$
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CommonPackage.RX_PATTERN__PATTERN:
				return getPattern();
			case CommonPackage.RX_PATTERN__PREFIX:
				return getPrefix();
			case CommonPackage.RX_PATTERN__SUFFIX:
				return getSuffix();
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
			case CommonPackage.RX_PATTERN__PATTERN:
				return PATTERN_EDEFAULT == null ? pattern != null : !PATTERN_EDEFAULT.equals(pattern);
			case CommonPackage.RX_PATTERN__PREFIX:
				return PREFIX_EDEFAULT == null ? prefix != null : !PREFIX_EDEFAULT.equals(prefix);
			case CommonPackage.RX_PATTERN__SUFFIX:
				return SUFFIX_EDEFAULT == null ? suffix != null : !SUFFIX_EDEFAULT.equals(suffix);
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
			case CommonPackage.RX_PATTERN__PATTERN:
				setPattern((String) newValue);
				return;
			case CommonPackage.RX_PATTERN__PREFIX:
				setPrefix((String) newValue);
				return;
			case CommonPackage.RX_PATTERN__SUFFIX:
				setSuffix((String) newValue);
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
			case CommonPackage.RX_PATTERN__PATTERN:
				setPattern(PATTERN_EDEFAULT);
				return;
			case CommonPackage.RX_PATTERN__PREFIX:
				setPrefix(PREFIX_EDEFAULT);
				return;
			case CommonPackage.RX_PATTERN__SUFFIX:
				setSuffix(SUFFIX_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
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

	public String getPrefix() {
		return prefix;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public String getSuffix() {
		return suffix;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public void setPattern(String newPattern) {
		String oldPattern = pattern;
		pattern = newPattern;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.RX_PATTERN__PATTERN, oldPattern, pattern));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public void setPrefix(String newPrefix) {
		String oldPrefix = prefix;
		prefix = newPrefix;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.RX_PATTERN__PREFIX, oldPrefix, prefix));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public void setSuffix(String newSuffix) {
		String oldSuffix = suffix;
		suffix = newSuffix;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.RX_PATTERN__SUFFIX, oldSuffix, suffix));
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
		result.append(", prefix: ");
		result.append(prefix);
		result.append(", suffix: ");
		result.append(suffix);
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
		return CommonPackage.Literals.RX_PATTERN;
	}

	private void addInnerPattern(StringBuilder bld, List<RxPart> namedParts, boolean willBeGroup) {
		if (prefix != null)
			addQuotedString(bld, prefix);

		if (pattern != null) {
			if (getName() != null) {
				// Pattern must be a capturing group
				//
				bld.append('(');
				addEscapedPattern(bld, pattern, true);
				bld.append(')');
				namedParts.add(this);
			} else
				addEscapedPattern(bld, pattern, willBeGroup && prefix == null && suffix == null);
		}

		if (suffix != null)
			addQuotedString(bld, suffix);
	}

} // RxPatternImpl
