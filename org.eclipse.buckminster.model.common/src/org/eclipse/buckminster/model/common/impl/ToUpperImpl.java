/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.model.common.impl;

import java.util.Map;

import org.eclipse.buckminster.model.common.CommonPackage;
import org.eclipse.buckminster.model.common.ToUpper;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>To Upper</b></em>'. <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class ToUpperImpl extends ValueFilterImpl implements ToUpper {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected ToUpperImpl() {
		super();
	}

	@Override
	public String checkedGetValue(Map<String, String> props, int recursionGuard) {
		String resolved = this.checkedGetSourceValue(props, recursionGuard);
		return (resolved == null || NO_VALUE.equals(resolved)) ? NO_VALUE : resolved.toUpperCase();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	protected EClass eStaticClass() {
		return CommonPackage.Literals.TO_UPPER;
	}
} // ToUpperImpl
