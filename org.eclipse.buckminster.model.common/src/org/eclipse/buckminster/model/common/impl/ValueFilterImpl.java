/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.model.common.impl;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.model.common.CommonPackage;
import org.eclipse.buckminster.model.common.Value;
import org.eclipse.buckminster.model.common.ValueFilter;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Value Filter</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.buckminster.model.common.impl.ValueFilterImpl#getMultiValueGroup
 * <em>Multi Value Group</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.model.common.impl.ValueFilterImpl#getValues
 * <em>Values</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public abstract class ValueFilterImpl extends ValueImpl implements ValueFilter {
	/**
	 * The cached value of the '{@link #getMultiValueGroup()
	 * <em>Multi Value Group</em>}' attribute list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getMultiValueGroup()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap multiValueGroup;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ValueFilterImpl() {
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
			case CommonPackage.VALUE_FILTER__MULTI_VALUE_GROUP:
				if (coreType)
					return getMultiValueGroup();
				return ((FeatureMap.Internal) getMultiValueGroup()).getWrapper();
			case CommonPackage.VALUE_FILTER__VALUES:
				return getValues();
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
			case CommonPackage.VALUE_FILTER__MULTI_VALUE_GROUP:
				return ((InternalEList<?>) getMultiValueGroup()).basicRemove(otherEnd, msgs);
			case CommonPackage.VALUE_FILTER__VALUES:
				return ((InternalEList<?>) getValues()).basicRemove(otherEnd, msgs);
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
			case CommonPackage.VALUE_FILTER__MULTI_VALUE_GROUP:
				return multiValueGroup != null && !multiValueGroup.isEmpty();
			case CommonPackage.VALUE_FILTER__VALUES:
				return !getValues().isEmpty();
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
			case CommonPackage.VALUE_FILTER__MULTI_VALUE_GROUP:
				((FeatureMap.Internal) getMultiValueGroup()).set(newValue);
				return;
			case CommonPackage.VALUE_FILTER__VALUES:
				getValues().clear();
				getValues().addAll((Collection<? extends Value>) newValue);
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
			case CommonPackage.VALUE_FILTER__MULTI_VALUE_GROUP:
				getMultiValueGroup().clear();
				return;
			case CommonPackage.VALUE_FILTER__VALUES:
				getValues().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public FeatureMap getMultiValueGroup() {
		if (multiValueGroup == null) {
			multiValueGroup = new BasicFeatureMap(this, CommonPackage.VALUE_FILTER__MULTI_VALUE_GROUP);
		}
		return multiValueGroup;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EList<Value> getValues() {
		return getMultiValueGroup().list(CommonPackage.Literals.VALUE_FILTER__VALUES);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public List<String> getValues(Map<String, String> properties) {
		return checkedGetValues(properties, 0);
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
		result.append(" (multiValueGroup: ");
		result.append(multiValueGroup);
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
		return CommonPackage.Literals.VALUE_FILTER;
	}

	String checkedGetSourceValue(Map<String, String> properties, int recursionGuard) {
		List<Value> values = getValues();
		int top = values.size();

		if (top == 0)
			return NO_VALUE;
		if (top == 1)
			return ((ValueImpl) values.get(0)).checkedGetValue(properties, recursionGuard);

		StringBuilder bld = new StringBuilder();
		for (int idx = 0; idx < top; ++idx) {
			ValueImpl valueHolder = (ValueImpl) values.get(idx);
			if (valueHolder.isMultiValued()) {
				List<?> vs = valueHolder.checkedGetValues(properties, recursionGuard);
				for (int vidx = 0; vidx < vs.size(); ++vidx)
					bld.append(vs.get(vidx));
			} else
				bld.append(valueHolder.checkedGetValue(properties, recursionGuard));
		}
		return bld.toString();
	}

	List<String> checkedGetSourceValues(Map<String, String> properties, int recursionGuard) {
		List<Value> values = getValues();
		int top = values.size();

		if (top == 0)
			return Collections.emptyList();
		if (top == 1)
			return ((ValueImpl) values.get(0)).checkedGetValues(properties, recursionGuard);

		ArrayList<String> parameters = new ArrayList<String>(top);
		for (int idx = 0; idx < top; ++idx) {
			ValueImpl valueHolder = (ValueImpl) values.get(idx);
			if (valueHolder.isMultiValued()) {
				List<String> vs = valueHolder.checkedGetValues(properties, recursionGuard);
				for (int vidx = 0; vidx < vs.size(); ++vidx)
					parameters.add(vs.get(vidx));
			} else
				parameters.add(valueHolder.checkedGetValue(properties, recursionGuard));
		}
		return parameters;
	}

} // ValueFilterImpl
