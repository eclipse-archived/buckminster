/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.model.common;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Property Element</b></em>'. <!-- end-user-doc
 * -->
 * 
 * <!-- begin-model-doc --> A simple property that maps a key to a constant value. <!-- end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.model.common.PropertyElement#getConstant <em>Constant</em>}</li>
 * <li>{@link org.eclipse.buckminster.model.common.PropertyElement#getFormat <em>Format</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.model.common.PropertyElement#getPropertyRef <em>Property Ref</em>}</li>
 * <li>{@link org.eclipse.buckminster.model.common.PropertyElement#getReplace <em>Replace</em>}</li>
 * <li>{@link org.eclipse.buckminster.model.common.PropertyElement#getToLower <em>To Lower</em>}</li>
 * <li>{@link org.eclipse.buckminster.model.common.PropertyElement#getToUpper <em>To Upper</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.model.common.CommonPackage#getPropertyElement()
 * @model
 * @generated
 */
public interface PropertyElement extends Property
{
	/**
	 * Returns the value of the '<em><b>Constant</b></em>' containment reference. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Constant</em>' containment reference isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Constant</em>' containment reference.
	 * @see #setConstant(Constant)
	 * @see org.eclipse.buckminster.model.common.CommonPackage#getPropertyElement_Constant()
	 * @model containment="true" extendedMetaData="namespace='##targetNamespace' kind='element'"
	 * @generated
	 */
	Constant getConstant();

	/**
	 * Returns the value of the '<em><b>Format</b></em>' containment reference. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Format</em>' containment reference isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Format</em>' containment reference.
	 * @see #setFormat(Format)
	 * @see org.eclipse.buckminster.model.common.CommonPackage#getPropertyElement_Format()
	 * @model containment="true" extendedMetaData="namespace='##targetNamespace' kind='element'"
	 * @generated
	 */
	Format getFormat();

	/**
	 * Returns the value of the '<em><b>Property Ref</b></em>' containment reference. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Property Ref</em>' containment reference isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Property Ref</em>' containment reference.
	 * @see #setPropertyRef(PropertyRef)
	 * @see org.eclipse.buckminster.model.common.CommonPackage#getPropertyElement_PropertyRef()
	 * @model containment="true" extendedMetaData="namespace='##targetNamespace' kind='element'"
	 * @generated
	 */
	PropertyRef getPropertyRef();

	/**
	 * Returns the value of the '<em><b>Replace</b></em>' containment reference. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Replace</em>' containment reference isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Replace</em>' containment reference.
	 * @see #setReplace(Replace)
	 * @see org.eclipse.buckminster.model.common.CommonPackage#getPropertyElement_Replace()
	 * @model containment="true" extendedMetaData="namespace='##targetNamespace' kind='element'"
	 * @generated
	 */
	Replace getReplace();

	/**
	 * Returns the value of the '<em><b>To Lower</b></em>' containment reference. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>To Lower</em>' containment reference isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>To Lower</em>' containment reference.
	 * @see #setToLower(ToLower)
	 * @see org.eclipse.buckminster.model.common.CommonPackage#getPropertyElement_ToLower()
	 * @model containment="true" extendedMetaData="namespace='##targetNamespace' kind='element'"
	 * @generated
	 */
	ToLower getToLower();

	/**
	 * Returns the value of the '<em><b>To Upper</b></em>' containment reference. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>To Upper</em>' containment reference isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>To Upper</em>' containment reference.
	 * @see #setToUpper(ToUpper)
	 * @see org.eclipse.buckminster.model.common.CommonPackage#getPropertyElement_ToUpper()
	 * @model containment="true" extendedMetaData="namespace='##targetNamespace' kind='element'"
	 * @generated
	 */
	ToUpper getToUpper();

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.model.common.PropertyElement#getConstant <em>Constant</em>}
	 * ' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Constant</em>' containment reference.
	 * @see #getConstant()
	 * @generated
	 */
	void setConstant(Constant value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.model.common.PropertyElement#getFormat <em>Format</em>}'
	 * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Format</em>' containment reference.
	 * @see #getFormat()
	 * @generated
	 */
	void setFormat(Format value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.model.common.PropertyElement#getPropertyRef
	 * <em>Property Ref</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Property Ref</em>' containment reference.
	 * @see #getPropertyRef()
	 * @generated
	 */
	void setPropertyRef(PropertyRef value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.model.common.PropertyElement#getReplace <em>Replace</em>}'
	 * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Replace</em>' containment reference.
	 * @see #getReplace()
	 * @generated
	 */
	void setReplace(Replace value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.model.common.PropertyElement#getToLower <em>To Lower</em>}'
	 * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>To Lower</em>' containment reference.
	 * @see #getToLower()
	 * @generated
	 */
	void setToLower(ToLower value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.model.common.PropertyElement#getToUpper <em>To Upper</em>}'
	 * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>To Upper</em>' containment reference.
	 * @see #getToUpper()
	 * @generated
	 */
	void setToUpper(ToUpper value);

} // PropertyElement
