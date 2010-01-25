/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.model.common;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Rx Group</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Defines a regexp capturing group as a named assembly of part
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.buckminster.model.common.RxGroup#getRxPartsGroup <em>Rx Parts Group</em>}</li>
 *   <li>{@link org.eclipse.buckminster.model.common.RxGroup#getRxPart <em>Rx Part</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.buckminster.model.common.CommonPackage#getRxGroup()
 * @model
 * @generated
 */
public interface RxGroup extends RxPart
{
	/**
	 * Returns the value of the '<em><b>Rx Part</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.buckminster.model.common.RxPart}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rx Part</em>' containment reference list isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rx Part</em>' containment reference list.
	 * @see org.eclipse.buckminster.model.common.CommonPackage#getRxGroup_RxPart()
	 * @model containment="true" transient="true" changeable="false" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='rxPart' namespace='##targetNamespace' group='rxPart:group'"
	 * @generated
	 */
	EList<RxPart> getRxPart();

	/**
	 * Returns the value of the '<em><b>Rx Parts Group</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rx Parts Group</em>' attribute list isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rx Parts Group</em>' attribute list.
	 * @see org.eclipse.buckminster.model.common.CommonPackage#getRxGroup_RxPartsGroup()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='rxPart:group' namespace='##targetNamespace'"
	 * @generated
	 */
	FeatureMap getRxPartsGroup();

} // RxGroup
