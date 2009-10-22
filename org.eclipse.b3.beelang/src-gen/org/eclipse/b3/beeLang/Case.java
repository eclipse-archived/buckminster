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
 * A representation of the model object '<em><b>Case</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.Case#getCondition <em>Condition</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.Case#getStatements <em>Statements</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getCase()
 * @model
 * @generated
 */
public interface Case extends EObject
{
  /**
   * Returns the value of the '<em><b>Condition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Condition</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Condition</em>' containment reference.
   * @see #setCondition(ValueLiteral)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getCase_Condition()
   * @model containment="true"
   * @generated
   */
  ValueLiteral getCondition();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.Case#getCondition <em>Condition</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Condition</em>' containment reference.
   * @see #getCondition()
   * @generated
   */
  void setCondition(ValueLiteral value);

  /**
   * Returns the value of the '<em><b>Statements</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.b3.beeLang.Statement}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Statements</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Statements</em>' containment reference list.
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getCase_Statements()
   * @model containment="true"
   * @generated
   */
  EList<Statement> getStatements();

} // Case
