/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap;

import org.eclipse.buckminster.model.common.Documentation;
import org.eclipse.buckminster.model.common.Format;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Repository</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.rmap.Repository#getId <em>Id</em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.Repository#getConnection <em>
 * Connection</em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.Repository#getType <em>Type</em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.Repository#getBranches <em>Branches
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.Repository#isCheckout <em>Checkout
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.Repository#isAllowDirty <em>Allow
 * Dirty</em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.Repository#isUpdate <em>Update</em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.Repository#getDocumentation <em>
 * Documentation</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.rmap.RmapPackage#getRepository()
 * @model
 * @generated
 */
public interface Repository extends EObject {
	/**
	 * Returns the value of the '<em><b>Branches</b></em>' containment reference
	 * list. The list contents are of type
	 * {@link org.eclipse.buckminster.rmap.BranchPoint}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Branches</em>' containment reference list
	 * isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Branches</em>' containment reference list.
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getRepository_Branches()
	 * @model containment="true"
	 * @generated
	 */
	EList<BranchPoint> getBranches();

	/**
	 * Returns the value of the '<em><b>Connection</b></em>' containment
	 * reference. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Connection</em>' containment reference isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Connection</em>' containment reference.
	 * @see #setConnection(Format)
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getRepository_Connection()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Format getConnection();

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
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getRepository_Documentation()
	 * @model containment="true"
	 * @generated
	 */
	Documentation getDocumentation();

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
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getRepository_Id()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.ID" required="true"
	 *        extendedMetaData="namespace=''"
	 * @generated
	 */
	String getId();

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
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getRepository_Type()
	 * @model required="true"
	 * @generated
	 */
	String getType();

	/**
	 * Returns the value of the '<em><b>Allow Dirty</b></em>' attribute. The
	 * default value is <code>"false"</code>. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Allow Dirty</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Allow Dirty</em>' attribute.
	 * @see #setAllowDirty(boolean)
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getRepository_AllowDirty()
	 * @model default="false"
	 * @generated
	 */
	boolean isAllowDirty();

	/**
	 * Returns the value of the '<em><b>Checkout</b></em>' attribute. The
	 * default value is <code>"true"</code>. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Checkout</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Checkout</em>' attribute.
	 * @see #setCheckout(boolean)
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getRepository_Checkout()
	 * @model default="true"
	 * @generated
	 */
	boolean isCheckout();

	/**
	 * Returns the value of the '<em><b>Update</b></em>' attribute. The default
	 * value is <code>"true"</code>. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Update</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Update</em>' attribute.
	 * @see #setUpdate(boolean)
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getRepository_Update()
	 * @model default="true"
	 * @generated
	 */
	boolean isUpdate();

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.rmap.Repository#isAllowDirty
	 * <em>Allow Dirty</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Allow Dirty</em>' attribute.
	 * @see #isAllowDirty()
	 * @generated
	 */
	void setAllowDirty(boolean value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.rmap.Repository#isCheckout
	 * <em>Checkout</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @param value
	 *            the new value of the '<em>Checkout</em>' attribute.
	 * @see #isCheckout()
	 * @generated
	 */
	void setCheckout(boolean value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.rmap.Repository#getConnection
	 * <em>Connection</em>}' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Connection</em>' containment
	 *            reference.
	 * @see #getConnection()
	 * @generated
	 */
	void setConnection(Format value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.rmap.Repository#getDocumentation
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
	 * {@link org.eclipse.buckminster.rmap.Repository#getId <em>Id</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.rmap.Repository#getType <em>Type</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.rmap.Repository#isUpdate <em>Update</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Update</em>' attribute.
	 * @see #isUpdate()
	 * @generated
	 */
	void setUpdate(boolean value);

} // Repository
