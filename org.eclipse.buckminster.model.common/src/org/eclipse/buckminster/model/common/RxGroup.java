/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.model.common;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Rx Group</b></em>'. <!-- end-user-doc -->
 * 
 * <!-- begin-model-doc --> Defines a regexp capturing group as a named assembly of part <!-- end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.model.common.RxGroup#getRxParts <em>Rx Parts</em>}</li>
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
	 * Returns the value of the '<em><b>Rx Parts</b></em>' containment reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.model.common.RxPart}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rx Parts</em>' containment reference list isn't clear, there really should be more of
	 * a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Rx Parts</em>' containment reference list.
	 * @see org.eclipse.buckminster.model.common.CommonPackage#getRxGroup_RxParts()
	 * @model containment="true" extendedMetaData="namespace='##targetNamespace' kind='element'"
	 * @generated
	 */
	EList<RxPart> getRxParts();

} // RxGroup
