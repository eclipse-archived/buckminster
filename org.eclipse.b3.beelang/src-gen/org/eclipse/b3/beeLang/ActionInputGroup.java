/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Action Input Group</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.ActionInputGroup#getAsserts <em>Asserts</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.ActionInputGroup#getPrerequisites <em>Prerequisites</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getActionInputGroup()
 * @model
 * @generated
 */
public interface ActionInputGroup extends EObject
{
  /**
   * Returns the value of the '<em><b>Asserts</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Asserts</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Asserts</em>' containment reference list.
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getActionInputGroup_Asserts()
   * @model containment="true"
   * @generated
   */
  EList<EObject> getAsserts();

  /**
   * Returns the value of the '<em><b>Prerequisites</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.b3.beeLang.Prerequisite}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Prerequisites</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Prerequisites</em>' containment reference list.
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getActionInputGroup_Prerequisites()
   * @model containment="true"
   * @generated
   */
  EList<Prerequisite> getPrerequisites();

} // ActionInputGroup
