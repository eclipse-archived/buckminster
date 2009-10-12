/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.team.psf;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Item</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.team.psf.Item#getFactoryID <em>Factory ID</em>}</li>
 * <li>{@link org.eclipse.buckminster.team.psf.Item#getElementID <em>Element ID</em>}</li>
 * <li>{@link org.eclipse.buckminster.team.psf.Item#getPath <em>Path</em>}</li>
 * <li>{@link org.eclipse.buckminster.team.psf.Item#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.team.psf.PsfPackage#getItem()
 * @model
 * @generated
 */
public interface Item extends EObject
{
	/**
	 * Returns the value of the '<em><b>Element ID</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Element ID</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Element ID</em>' attribute.
	 * @see #setElementID(String)
	 * @see org.eclipse.buckminster.team.psf.PsfPackage#getItem_ElementID()
	 * @model
	 * @generated
	 */
	String getElementID();

	/**
	 * Returns the value of the '<em><b>Factory ID</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Factory ID</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Factory ID</em>' attribute.
	 * @see #setFactoryID(String)
	 * @see org.eclipse.buckminster.team.psf.PsfPackage#getItem_FactoryID()
	 * @model
	 * @generated
	 */
	String getFactoryID();

	/**
	 * Returns the value of the '<em><b>Path</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Path</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Path</em>' attribute.
	 * @see #setPath(String)
	 * @see org.eclipse.buckminster.team.psf.PsfPackage#getItem_Path()
	 * @model
	 * @generated
	 */
	String getPath();

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(int)
	 * @see org.eclipse.buckminster.team.psf.PsfPackage#getItem_Type()
	 * @model
	 * @generated
	 */
	int getType();

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.team.psf.Item#getElementID <em>Element ID</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Element ID</em>' attribute.
	 * @see #getElementID()
	 * @generated
	 */
	void setElementID(String value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.team.psf.Item#getFactoryID <em>Factory ID</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Factory ID</em>' attribute.
	 * @see #getFactoryID()
	 * @generated
	 */
	void setFactoryID(String value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.team.psf.Item#getPath <em>Path</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Path</em>' attribute.
	 * @see #getPath()
	 * @generated
	 */
	void setPath(String value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.team.psf.Item#getType <em>Type</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(int value);

} // Item
