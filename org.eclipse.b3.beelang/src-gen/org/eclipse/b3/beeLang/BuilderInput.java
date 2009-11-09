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
 * A representation of the model object '<em><b>Builder Input</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.BuilderInput#getPreCondition <em>Pre Condition</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.BuilderInput#getPostCondition <em>Post Condition</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.BuilderInput#getPrerequisites <em>Prerequisites</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getBuilderInput()
 * @model
 * @generated
 */
public interface BuilderInput extends EObject
{
  /**
   * Returns the value of the '<em><b>Pre Condition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Pre Condition</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Pre Condition</em>' containment reference.
   * @see #setPreCondition(PreCondition)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getBuilderInput_PreCondition()
   * @model containment="true"
   * @generated
   */
  PreCondition getPreCondition();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.BuilderInput#getPreCondition <em>Pre Condition</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Pre Condition</em>' containment reference.
   * @see #getPreCondition()
   * @generated
   */
  void setPreCondition(PreCondition value);

  /**
   * Returns the value of the '<em><b>Post Condition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Post Condition</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Post Condition</em>' containment reference.
   * @see #setPostCondition(PostCondition)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getBuilderInput_PostCondition()
   * @model containment="true"
   * @generated
   */
  PostCondition getPostCondition();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.BuilderInput#getPostCondition <em>Post Condition</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Post Condition</em>' containment reference.
   * @see #getPostCondition()
   * @generated
   */
  void setPostCondition(PostCondition value);

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
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getBuilderInput_Prerequisites()
   * @model containment="true"
   * @generated
   */
  EList<Prerequisite> getPrerequisites();

} // BuilderInput
