/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.model.common;

import java.lang.Comparable;
import java.util.Map;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Component Name</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.model.common.ComponentName#getId <em>Id
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.model.common.ComponentName#getType <em>
 * Type</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.model.common.CommonPackage#getComponentName()
 * @model superTypes=
 *        "org.eclipse.buckminster.model.common.Comparable<org.eclipse.buckminster.model.common.ComponentName>"
 * @generated
 */
public interface ComponentName extends EObject, Comparable<ComponentName> {
	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.eclipse.buckminster.model.common.CommonPackage#getComponentName_Id()
	 * @model
	 * @generated
	 */
	String getId();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model kind="operation"
	 * @generated
	 */
	Map<String, String> getProperties();

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(String)
	 * @see org.eclipse.buckminster.model.common.CommonPackage#getComponentName_Type()
	 * @model
	 * @generated
	 */
	String getType();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
	 * <p>
	 * Match this <code>ComponentIdentifier</code> with another instance. The
	 * match is done as follows
	 * </p>
	 * <ul>
	 * <li>The match is always false if the id differ</li>
	 * <li>The types match if both instances have equal types or if a type is
	 * missing</li>
	 * </ul>
	 * <!-- end-model-doc -->
	 * 
	 * @model
	 * @generated
	 */
	boolean matches(ComponentName cn);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.model.common.ComponentName#getId
	 * <em>Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.model.common.ComponentName#getType
	 * <em>Type</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model
	 * @generated
	 */
	ComponentName toPureComponentName();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model 
	 *        resultDataType="org.eclipse.buckminster.model.common.StringBuilder"
	 * @generated
	 */
	void toString(StringBuilder result);

} // ComponentName
