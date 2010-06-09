/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspec;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Rename</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.cspec.Rename#getOldName <em>Old Name</em>}
 * </li>
 * <li>{@link org.eclipse.buckminster.cspec.Rename#getNewName <em>New Name</em>}
 * </li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.cspec.CspecPackage#getRename()
 * @model
 * @generated
 */
public interface Rename extends EObject {
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
	 * @see org.eclipse.buckminster.cspec.CspecPackage#getRename_NewName()
	 * @model required="true"
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
	 * @see org.eclipse.buckminster.cspec.CspecPackage#getRename_OldName()
	 * @model required="true"
	 * @generated
	 */
	String getOldName();

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.cspec.Rename#getNewName <em>New Name</em>}
	 * ' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>New Name</em>' attribute.
	 * @see #getNewName()
	 * @generated
	 */
	void setNewName(String value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.cspec.Rename#getOldName <em>Old Name</em>}
	 * ' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Old Name</em>' attribute.
	 * @see #getOldName()
	 * @generated
	 */
	void setOldName(String value);

} // Rename
