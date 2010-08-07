/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.model.common.impl;

import java.lang.CharSequence;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.buckminster.model.common.CommonPackage;
import org.eclipse.buckminster.model.common.RxAssembly;
import org.eclipse.buckminster.model.common.RxPart;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.osgi.util.NLS;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Rx Assembly</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.buckminster.model.common.impl.RxAssemblyImpl#getPattern <em>Pattern</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RxAssemblyImpl extends RxGroupImpl implements RxAssembly {
	/**
	 * The default value of the '{@link #getPattern() <em>Pattern</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getPattern()
	 * @generated
	 * @ordered
	 */
	protected static final Pattern PATTERN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPattern() <em>Pattern</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getPattern()
	 * @generated
	 * @ordered
	 */
	protected Pattern pattern = PATTERN_EDEFAULT;

	private List<RxPart> parameters;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected RxAssemblyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CommonPackage.RX_ASSEMBLY__PATTERN:
				return getPattern();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case CommonPackage.RX_ASSEMBLY__PATTERN:
				return PATTERN_EDEFAULT == null ? pattern != null : !PATTERN_EDEFAULT.equals(pattern);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */

	public Map<String, String> getMatchMap(CharSequence input) {
		if (input == null)
			return null;

		Logger logger = Buckminster.getLogger();
		getPattern();
		Matcher m = pattern.matcher(input);
		if (!m.matches()) {
			logger.debug("Pattern does not match %s", input); //$NON-NLS-1$
			return null;
		}

		int groupCount = m.groupCount();
		int top = parameters.size();
		if (logger.isDebugEnabled()) {
			logger.debug("Pattern captured %d groups in %s", Integer.valueOf(groupCount), input); //$NON-NLS-1$
		}

		if (top != groupCount) {
			logger.warning(NLS.bind("Pattern group count was {0}, expected {1}", Integer.valueOf(groupCount), Integer.valueOf(top)));
			top = groupCount;
		}

		if (top == 0)
			return Collections.emptyMap();

		HashMap<String, String> matchMap = new HashMap<String, String>();

		for (int idx = 0; idx < top; ++idx) {
			RxPart param = parameters.get(idx);
			String value = Trivial.trim(m.group(idx + 1));
			if (value != null) {
				logger.debug("Assigning pattern parameter %s=\"%s\"", param.getName(), value); //$NON-NLS-1$
				matchMap.put(param.getName(), value);
			}
		}
		return matchMap;
	}

	public Pattern getPattern() {
		// TODO: Reset pattern if RxParts change
		if(pattern == null) {
			StringBuilder bld = new StringBuilder();
			bld.append('^');
			parameters = new ArrayList<RxPart>();
			for (RxPart part : getRxParts())
				part.addPattern(bld, parameters);
			bld.append('$');

			String patternStr = bld.toString();
			pattern = Pattern.compile(patternStr);
			Buckminster.getLogger().debug("URI pattern %s created", patternStr); //$NON-NLS-1$
		}
		return pattern;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (pattern: ");
		result.append(pattern);
		result.append(')');
		return result.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	protected EClass eStaticClass() {
		return CommonPackage.Literals.RX_ASSEMBLY;
	}

} // RxAssemblyImpl
