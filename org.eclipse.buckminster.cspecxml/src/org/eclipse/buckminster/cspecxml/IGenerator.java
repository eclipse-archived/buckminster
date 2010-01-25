/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspecxml;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.equinox.p2.metadata.Version;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Generator</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.cspecxml.IGenerator#getAttribute <em>Attribute</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IGenerator#getComponent <em>Component</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IGenerator#getComponentType <em>Component Type</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IGenerator#getGenerates <em>Generates</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IGenerator#getGeneratesType <em>Generates Type</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IGenerator#getGeneratesVersionString <em>Generates Version String</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IGenerator#getGeneratesVersionType <em>Generates Version Type</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getGenerator()
 * @model extendedMetaData="name='Generator' kind='empty'"
 * @generated
 */
public interface IGenerator extends EObject
{
	/**
	 * Returns the value of the '<em><b>Attribute</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attribute</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Attribute</em>' attribute.
	 * @see #setAttribute(String)
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getGenerator_Attribute()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute' name='attribute'"
	 * @generated
	 */
	String getAttribute();

	/**
	 * Returns the value of the '<em><b>Component</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Component</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Component</em>' attribute.
	 * @see #setComponent(String)
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getGenerator_Component()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" extendedMetaData="kind='attribute' name='component'"
	 * @generated
	 */
	String getComponent();

	/**
	 * Returns the value of the '<em><b>Component Type</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Component Type</em>' attribute isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Component Type</em>' attribute.
	 * @see #setComponentType(String)
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getGenerator_ComponentType()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" extendedMetaData="kind='attribute' name='componentType'"
	 * @generated
	 */
	String getComponentType();

	/**
	 * Returns the value of the '<em><b>Generates</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Generates</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Generates</em>' attribute.
	 * @see #setGenerates(String)
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getGenerator_Generates()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute' name='generates'"
	 * @generated
	 */
	String getGenerates();

	/**
	 * Returns the value of the '<em><b>Generates Type</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Generates Type</em>' attribute isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Generates Type</em>' attribute.
	 * @see #setGeneratesType(String)
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getGenerator_GeneratesType()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" extendedMetaData="kind='attribute' name='generatesType'"
	 * @generated
	 */
	String getGeneratesType();

	/**
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Generates Version</em>' attribute isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @model kind="operation" dataType="org.eclipse.buckminster.model.common.Version"
	 * @generated
	 */
	Version getGeneratesVersion();

	/**
	 * Returns the value of the '<em><b>Generates Version String</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Generates Version String</em>' attribute isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Generates Version String</em>' attribute.
	 * @see #setGeneratesVersionString(String)
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getGenerator_GeneratesVersionString()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='generatesVersion'"
	 * @generated
	 */
	String getGeneratesVersionString();

	/**
	 * Returns the value of the '<em><b>Generates Version Type</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Generates Version Type</em>' attribute isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Generates Version Type</em>' attribute.
	 * @see #setGeneratesVersionType(String)
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getGenerator_GeneratesVersionType()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='generatesVersionType'"
	 * @generated
	 */
	String getGeneratesVersionType();

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.cspecxml.IGenerator#getAttribute <em>Attribute</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Attribute</em>' attribute.
	 * @see #getAttribute()
	 * @generated
	 */
	void setAttribute(String value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.cspecxml.IGenerator#getComponent <em>Component</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Component</em>' attribute.
	 * @see #getComponent()
	 * @generated
	 */
	void setComponent(String value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.cspecxml.IGenerator#getComponentType
	 * <em>Component Type</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Component Type</em>' attribute.
	 * @see #getComponentType()
	 * @generated
	 */
	void setComponentType(String value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.cspecxml.IGenerator#getGenerates <em>Generates</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Generates</em>' attribute.
	 * @see #getGenerates()
	 * @generated
	 */
	void setGenerates(String value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.cspecxml.IGenerator#getGeneratesType
	 * <em>Generates Type</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Generates Type</em>' attribute.
	 * @see #getGeneratesType()
	 * @generated
	 */
	void setGeneratesType(String value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.cspecxml.IGenerator#getGeneratesVersionString
	 * <em>Generates Version String</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Generates Version String</em>' attribute.
	 * @see #getGeneratesVersionString()
	 * @generated
	 */
	void setGeneratesVersionString(String value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.cspecxml.IGenerator#getGeneratesVersionType
	 * <em>Generates Version Type</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Generates Version Type</em>' attribute.
	 * @see #getGeneratesVersionType()
	 * @generated
	 */
	void setGeneratesVersionType(String value);

} // IGenerator
