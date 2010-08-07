/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.model.common;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Property Ref</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.buckminster.model.common.PropertyRef#getKey <em>Key</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.buckminster.model.common.CommonPackage#getPropertyRef()
 * @model
 * @generated
 */
public interface PropertyRef extends Value {
	/**
	 * Returns the value of the '<em><b>Key</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Key</em>' attribute isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Key</em>' attribute.
	 * @see #setKey(String)
	 * @see org.eclipse.buckminster.model.common.CommonPackage#getPropertyRef_Key()
	 * @model dataType="org.eclipse.buckminster.model.common.PropertyKey"
	 *        required="true" extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getKey();

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.model.common.PropertyRef#getKey <em>Key</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Key</em>' attribute.
	 * @see #getKey()
	 * @generated
	 */
	void setKey(String value);

} // PropertyRef
