/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.p2;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.equinox.internal.provisional.p2.metadata.ITouchpointData;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Touchpoint Data</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.p2.TouchpointData#getInstructionMap <em>Instruction Map</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.aggregator.p2.P2Package#getTouchpointData()
 * @model superTypes="org.eclipse.buckminster.aggregator.p2.ITouchpointData"
 * @generated
 */
public interface TouchpointData extends EObject, ITouchpointData
{
	/**
	 * Returns the value of the '<em><b>Instruction Map</b></em>' map. The key is of type {@link java.lang.String}, and
	 * the value is of type {@link org.eclipse.buckminster.aggregator.p2.TouchpointInstruction}, <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Instruction Map</em>' map isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Instruction Map</em>' map.
	 * @see org.eclipse.buckminster.aggregator.p2.P2Package#getTouchpointData_InstructionMap()
	 * @model mapType="org.eclipse.buckminster.aggregator.p2.InstructionMap<org.eclipse.emf.ecore.EString, org.eclipse.buckminster.aggregator.p2.TouchpointInstruction>"
	 * @generated
	 */
	EMap<String, TouchpointInstruction> getInstructionMap();

} // TouchpointData
