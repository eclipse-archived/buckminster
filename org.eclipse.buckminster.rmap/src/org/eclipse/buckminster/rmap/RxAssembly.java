/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap;

import java.util.regex.Pattern;

import org.eclipse.buckminster.model.common.RxPart;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Rx Assembly</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.rmap.RxAssembly#getPattern <em>Pattern
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.RxAssembly#getRxPartsGroup <em>Rx
 * Parts Group</em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.RxAssembly#getRxParts <em>Rx Parts
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.rmap.RmapPackage#getRxAssembly()
 * @model abstract="true"
 * @generated
 */
public interface RxAssembly extends EObject {
	/**
	 * Returns the value of the '<em><b>Pattern</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pattern</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Pattern</em>' attribute.
	 * @see #isSetPattern()
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getRxAssembly_Pattern()
	 * @model unsettable="true"
	 *        dataType="org.eclipse.buckminster.model.common.Pattern"
	 *        transient="true" changeable="false"
	 * @generated
	 */
	Pattern getPattern();

	/**
	 * Returns the value of the '<em><b>Rx Parts</b></em>' containment reference
	 * list. The list contents are of type
	 * {@link org.eclipse.buckminster.model.common.RxPart}. <!-- begin-user-doc
	 * -->
	 * <p>
	 * If the meaning of the '<em>Rx Parts</em>' containment reference list
	 * isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Rx Parts</em>' containment reference list.
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getRxAssembly_RxParts()
	 * @model containment="true" required="true" transient="true"
	 *        changeable="false" volatile="true" derived="true"
	 *        extendedMetaData=
	 *        "kind='element' name='rxPart' namespace='http://www.eclipse.org/buckminster/Common-1.0' group='http://www.eclipse.org/buckminster/Common-1.0#rxPart:group'"
	 * @generated
	 */
	EList<RxPart> getRxParts();

	/**
	 * Returns the value of the '<em><b>Rx Parts Group</b></em>' attribute list.
	 * The list contents are of type
	 * {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}. <!-- begin-user-doc
	 * -->
	 * <p>
	 * If the meaning of the '<em>Rx Parts Group</em>' attribute list isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Rx Parts Group</em>' attribute list.
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getRxAssembly_RxPartsGroup()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry"
	 *        required="true" many="true" extendedMetaData=
	 *        "kind='group' name='rxPart:group' namespace='http://www.eclipse.org/buckminster/Common-1.0'"
	 * @generated
	 */
	FeatureMap getRxPartsGroup();

	/**
	 * Returns whether the value of the '
	 * {@link org.eclipse.buckminster.rmap.RxAssembly#getPattern
	 * <em>Pattern</em>}' attribute is set. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return whether the value of the '<em>Pattern</em>' attribute is set.
	 * @see #getPattern()
	 * @generated
	 */
	boolean isSetPattern();

} // RxAssembly
