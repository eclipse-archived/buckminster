/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspecxml;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Parameter Type</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.cspecxml.IParameterType#isMandatory <em>
 * Mandatory</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IParameterType#getName <em>Name
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IParameterType#getValue <em>Value
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getParameterType()
 * @model extendedMetaData="name='parameter_._type' kind='empty'"
 * @generated
 */
public interface IParameterType extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getParameterType_Name()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute' name='name'"
	 * @generated
	 */
	String getName();

	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(String)
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getParameterType_Value()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute' name='value'"
	 * @generated
	 */
	String getValue();

	/**
	 * Returns the value of the '<em><b>Mandatory</b></em>' attribute. The
	 * default value is <code>"false"</code>. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mandatory</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Mandatory</em>' attribute.
	 * @see #isSetMandatory()
	 * @see #unsetMandatory()
	 * @see #setMandatory(boolean)
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getParameterType_Mandatory()
	 * @model default="false" unsettable="true"
	 *        dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='mandatory'"
	 * @generated
	 */
	boolean isMandatory();

	/**
	 * Returns whether the value of the '
	 * {@link org.eclipse.buckminster.cspecxml.IParameterType#isMandatory
	 * <em>Mandatory</em>}' attribute is set. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return whether the value of the '<em>Mandatory</em>' attribute is set.
	 * @see #unsetMandatory()
	 * @see #isMandatory()
	 * @see #setMandatory(boolean)
	 * @generated
	 */
	boolean isSetMandatory();

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.cspecxml.IParameterType#isMandatory
	 * <em>Mandatory</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @param value
	 *            the new value of the '<em>Mandatory</em>' attribute.
	 * @see #isSetMandatory()
	 * @see #unsetMandatory()
	 * @see #isMandatory()
	 * @generated
	 */
	void setMandatory(boolean value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.cspecxml.IParameterType#getName
	 * <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.cspecxml.IParameterType#getValue
	 * <em>Value</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(String value);

	/**
	 * Unsets the value of the '
	 * {@link org.eclipse.buckminster.cspecxml.IParameterType#isMandatory
	 * <em>Mandatory</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #isSetMandatory()
	 * @see #isMandatory()
	 * @see #setMandatory(boolean)
	 * @generated
	 */
	void unsetMandatory();

} // IParameterType
