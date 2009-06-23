/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.p2.impl;

import java.util.Map;

import org.eclipse.buckminster.aggregator.p2.P2Package;
import org.eclipse.buckminster.aggregator.p2.TouchpointData;
import org.eclipse.buckminster.aggregator.p2.TouchpointInstruction;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.equinox.internal.provisional.p2.metadata.ITouchpointInstruction;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Touchpoint Data</b></em>'. <!-- end-user-doc
 * -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.p2.impl.TouchpointDataImpl#getInstructionMap <em>Instruction Map</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class TouchpointDataImpl extends MinimalEObjectImpl.Container implements TouchpointData
{
	/**
	 * A set of bit flags representing the values of boolean attributes and whether unsettable features have been set.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected int eFlags = 0;

	/**
	 * The cached value of the '{@link #getInstructionMap() <em>Instruction Map</em>}' map.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #getInstructionMap()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, TouchpointInstruction> instructionMap;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected TouchpointDataImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass()
	{
		return P2Package.Literals.TOUCHPOINT_DATA;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, TouchpointInstruction> getInstructionMap()
	{
		if (instructionMap == null)
		{
			instructionMap = new EcoreEMap<String,TouchpointInstruction>(P2Package.Literals.INSTRUCTION_MAP, InstructionMapImpl.class, this, P2Package.TOUCHPOINT_DATA__INSTRUCTION_MAP);
		}
		return instructionMap;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public ITouchpointInstruction getInstruction(String instructionKey)
	{
		return getInstructionMap().get(instructionKey);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@SuppressWarnings("unchecked")
	public Map getInstructions()
	{
		return (Map)getInstructionMap();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
	{
		switch (featureID)
		{
			case P2Package.TOUCHPOINT_DATA__INSTRUCTION_MAP:
				return ((InternalEList<?>)getInstructionMap()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType)
	{
		switch (featureID)
		{
			case P2Package.TOUCHPOINT_DATA__INSTRUCTION_MAP:
				if (coreType) return getInstructionMap();
				else return getInstructionMap().map();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue)
	{
		switch (featureID)
		{
			case P2Package.TOUCHPOINT_DATA__INSTRUCTION_MAP:
				((EStructuralFeature.Setting)getInstructionMap()).set(newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID)
	{
		switch (featureID)
		{
			case P2Package.TOUCHPOINT_DATA__INSTRUCTION_MAP:
				getInstructionMap().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID)
	{
		switch (featureID)
		{
			case P2Package.TOUCHPOINT_DATA__INSTRUCTION_MAP:
				return instructionMap != null && !instructionMap.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} // TouchpointDataImpl
