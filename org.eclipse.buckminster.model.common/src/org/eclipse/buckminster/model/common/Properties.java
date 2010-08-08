/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.model.common;

import java.util.Map;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Properties</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.buckminster.model.common.Properties#getPropertyConstants
 * <em>Property Constants</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.model.common.Properties#getPropertyElements
 * <em>Property Elements</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.model.common.CommonPackage#getProperties()
 * @model abstract="true"
 * @generated
 */
public interface Properties extends EObject {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model kind="operation"
	 * @generated
	 */
	Map<String, String> getProperties();

	/**
	 * Returns the value of the '<em><b>Property Constants</b></em>' map. The
	 * key is of type {@link java.lang.String}, and the value is of type
	 * {@link org.eclipse.buckminster.model.common.Value}, <!-- begin-user-doc
	 * -->
	 * <p>
	 * If the meaning of the '<em>Property Constants</em>' reference list isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Property Constants</em>' map.
	 * @see org.eclipse.buckminster.model.common.CommonPackage#getProperties_PropertyConstants()
	 * @model mapType=
	 *        "org.eclipse.buckminster.model.common.PropertyConstant<org.eclipse.emf.ecore.EString, org.eclipse.buckminster.model.common.Value>"
	 *        extendedMetaData
	 *        ="kind='element' name='property' namespace='##targetNamespace'"
	 * @generated
	 */
	EMap<String, Value> getPropertyConstants();

	/**
	 * Returns the value of the '<em><b>Property Elements</b></em>' map. The key
	 * is of type {@link java.lang.String}, and the value is of type
	 * {@link org.eclipse.buckminster.model.common.Value}, <!-- begin-user-doc
	 * -->
	 * <p>
	 * If the meaning of the '<em>Property Elements</em>' reference list isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Property Elements</em>' map.
	 * @see org.eclipse.buckminster.model.common.CommonPackage#getProperties_PropertyElements()
	 * @model mapType=
	 *        "org.eclipse.buckminster.model.common.PropertyElement<org.eclipse.emf.ecore.EString, org.eclipse.buckminster.model.common.Value>"
	 *        extendedMetaData=
	 *        "kind='element' name='propertyElement' namespace='##targetNamespace'"
	 * @generated
	 */
	EMap<String, Value> getPropertyElements();

} // Properties
