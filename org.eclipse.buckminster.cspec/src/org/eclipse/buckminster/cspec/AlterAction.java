/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspec;

import org.eclipse.buckminster.model.common.PropertyConstant;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Alter Action</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.cspec.AlterAction#getReplaceProperties <em>Replace Properties</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.AlterAction#getReplaceActorProperties <em>Replace Actor Properties</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.AlterAction#getRemoveProperties <em>Remove Properties</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.AlterAction#getRemoveActorProperties <em>Remove Actor Properties</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.AlterAction#getRemoveProducts <em>Remove Products</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.AlterAction#getRemovePaths <em>Remove Paths</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.cspec.CspecPackage#getAlterAction()
 * @model
 * @generated
 */
public interface AlterAction extends Action, AlterGroup
{
	/**
	 * Returns the value of the '<em><b>Remove Actor Properties</b></em>' containment reference list. The list contents
	 * are of type {@link org.eclipse.buckminster.cspec.Remove}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Remove Actor Properties</em>' containment reference list isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Remove Actor Properties</em>' containment reference list.
	 * @see org.eclipse.buckminster.cspec.CspecPackage#getAlterAction_RemoveActorProperties()
	 * @model containment="true"
	 * @generated
	 */
	EList<Remove> getRemoveActorProperties();

	/**
	 * Returns the value of the '<em><b>Remove Paths</b></em>' containment reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.cspec.Remove}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Remove Paths</em>' containment reference list isn't clear, there really should be more
	 * of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Remove Paths</em>' containment reference list.
	 * @see org.eclipse.buckminster.cspec.CspecPackage#getAlterAction_RemovePaths()
	 * @model containment="true"
	 * @generated
	 */
	EList<Remove> getRemovePaths();

	/**
	 * Returns the value of the '<em><b>Remove Products</b></em>' containment reference list. The list contents are of
	 * type {@link org.eclipse.buckminster.cspec.Remove}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Remove Products</em>' containment reference list isn't clear, there really should be
	 * more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Remove Products</em>' containment reference list.
	 * @see org.eclipse.buckminster.cspec.CspecPackage#getAlterAction_RemoveProducts()
	 * @model containment="true"
	 * @generated
	 */
	EList<Remove> getRemoveProducts();

	/**
	 * Returns the value of the '<em><b>Remove Properties</b></em>' containment reference list. The list contents are of
	 * type {@link org.eclipse.buckminster.cspec.Remove}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Remove Properties</em>' containment reference list isn't clear, there really should be
	 * more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Remove Properties</em>' containment reference list.
	 * @see org.eclipse.buckminster.cspec.CspecPackage#getAlterAction_RemoveProperties()
	 * @model containment="true"
	 * @generated
	 */
	EList<Remove> getRemoveProperties();

	/**
	 * Returns the value of the '<em><b>Replace Actor Properties</b></em>' containment reference list. The list contents
	 * are of type {@link org.eclipse.buckminster.model.common.PropertyConstant}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Replace Actor Properties</em>' containment reference list isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Replace Actor Properties</em>' containment reference list.
	 * @see org.eclipse.buckminster.cspec.CspecPackage#getAlterAction_ReplaceActorProperties()
	 * @model containment="true"
	 * @generated
	 */
	EList<PropertyConstant> getReplaceActorProperties();

	/**
	 * Returns the value of the '<em><b>Replace Properties</b></em>' containment reference list. The list contents are
	 * of type {@link org.eclipse.buckminster.model.common.PropertyConstant}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Replace Properties</em>' containment reference list isn't clear, there really should
	 * be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Replace Properties</em>' containment reference list.
	 * @see org.eclipse.buckminster.cspec.CspecPackage#getAlterAction_ReplaceProperties()
	 * @model containment="true"
	 * @generated
	 */
	EList<PropertyConstant> getReplaceProperties();

} // AlterAction
