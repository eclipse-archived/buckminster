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
 * <em><b>Rename</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.cspecxml.IRename#getNewName <em>New Name
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IRename#getOldName <em>Old Name
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getRename()
 * @model extendedMetaData="name='Rename' kind='empty'"
 * @generated
 */
public interface IRename extends EObject {
	/**
	 * Returns the value of the '<em><b>New Name</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>New Name</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>New Name</em>' attribute.
	 * @see #setNewName(String)
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getRename_NewName()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute' name='newName'"
	 * @generated
	 */
	String getNewName();

	/**
	 * Returns the value of the '<em><b>Old Name</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Old Name</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Old Name</em>' attribute.
	 * @see #setOldName(String)
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getRename_OldName()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute' name='oldName'"
	 * @generated
	 */
	String getOldName();

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.cspecxml.IRename#getNewName
	 * <em>New Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @param value
	 *            the new value of the '<em>New Name</em>' attribute.
	 * @see #getNewName()
	 * @generated
	 */
	void setNewName(String value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.cspecxml.IRename#getOldName
	 * <em>Old Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @param value
	 *            the new value of the '<em>Old Name</em>' attribute.
	 * @see #getOldName()
	 * @generated
	 */
	void setOldName(String value);

} // IRename
