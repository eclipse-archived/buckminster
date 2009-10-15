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
 * A representation of the model object '<em><b>Action Part</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.ActionPart#isSynchronized <em>Synchronized</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.ActionPart#getActorParameters <em>Actor Parameters</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.ActionPart#getAsserts <em>Asserts</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.ActionPart#getResultGroups <em>Result Groups</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getActionPart()
 * @model
 * @generated
 */
public interface ActionPart extends BuildPart
{
  /**
   * Returns the value of the '<em><b>Synchronized</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Synchronized</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Synchronized</em>' attribute.
   * @see #setSynchronized(boolean)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getActionPart_Synchronized()
   * @model
   * @generated
   */
  boolean isSynchronized();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.ActionPart#isSynchronized <em>Synchronized</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Synchronized</em>' attribute.
   * @see #isSynchronized()
   * @generated
   */
  void setSynchronized(boolean value);

  /**
   * Returns the value of the '<em><b>Actor Parameters</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.b3.beeLang.Parameter}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Actor Parameters</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Actor Parameters</em>' containment reference list.
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getActionPart_ActorParameters()
   * @model containment="true"
   * @generated
   */
  EList<Parameter> getActorParameters();

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
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getActionPart_Asserts()
   * @model containment="true"
   * @generated
   */
  EList<EObject> getAsserts();

  /**
   * Returns the value of the '<em><b>Result Groups</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.b3.beeLang.Result}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Result Groups</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Result Groups</em>' containment reference list.
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getActionPart_ResultGroups()
   * @model containment="true"
   * @generated
   */
  EList<Result> getResultGroups();

} // ActionPart
