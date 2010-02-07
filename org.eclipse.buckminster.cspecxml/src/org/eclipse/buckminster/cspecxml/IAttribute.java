/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspecxml;

import org.eclipse.buckminster.model.common.Documentation;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Attribute</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.cspecxml.IAttribute#getDocumentation <em>
 * Documentation</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IAttribute#getParameter <em>
 * Parameter</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IAttribute#getName <em>Name</em>}
 * </li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IAttribute#getVersion <em>Version
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getAttribute()
 * @model extendedMetaData="name='Attribute' kind='elementOnly'"
 * @generated
 */
public interface IAttribute extends EObject {
	/**
	 * Returns the value of the '<em><b>Documentation</b></em>' containment
	 * reference. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Documentation</em>' containment reference
	 * isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Documentation</em>' containment reference.
	 * @see #setDocumentation(Documentation)
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getAttribute_Documentation()
	 * @model containment="true" extendedMetaData=
	 *        "kind='element' name='documentation' namespace='##targetNamespace'"
	 * @generated
	 */
	Documentation getDocumentation();

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
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getAttribute_Name()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute' name='name'"
	 * @generated
	 */
	String getName();

	/**
	 * Returns the value of the '<em><b>Parameter</b></em>' containment
	 * reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.cspecxml.IParameterType}. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameter</em>' containment reference list
	 * isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Parameter</em>' containment reference list.
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getAttribute_Parameter()
	 * @model containment="true" extendedMetaData=
	 *        "kind='element' name='parameter' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<IParameterType> getParameter();

	/**
	 * Returns the value of the '<em><b>Version</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Version</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Version</em>' attribute.
	 * @see #setVersion(String)
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getAttribute_Version()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='version'"
	 * @generated
	 */
	String getVersion();

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.cspecxml.IAttribute#getDocumentation
	 * <em>Documentation</em>}' containment reference. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Documentation</em>' containment
	 *            reference.
	 * @see #getDocumentation()
	 * @generated
	 */
	void setDocumentation(Documentation value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.cspecxml.IAttribute#getName <em>Name</em>}
	 * ' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.cspecxml.IAttribute#getVersion
	 * <em>Version</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @param value
	 *            the new value of the '<em>Version</em>' attribute.
	 * @see #getVersion()
	 * @generated
	 */
	void setVersion(String value);

} // IAttribute
