/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Switch Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.SwitchStatement#getSwitchExpr <em>Switch Expr</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.SwitchStatement#getCase <em>Case</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.SwitchStatement#getStatements <em>Statements</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getSwitchStatement()
 * @model
 * @generated
 */
public interface SwitchStatement extends Statement
{
  /**
   * Returns the value of the '<em><b>Switch Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Switch Expr</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Switch Expr</em>' containment reference.
   * @see #setSwitchExpr(VarExpression)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getSwitchStatement_SwitchExpr()
   * @model containment="true"
   * @generated
   */
  VarExpression getSwitchExpr();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.SwitchStatement#getSwitchExpr <em>Switch Expr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Switch Expr</em>' containment reference.
   * @see #getSwitchExpr()
   * @generated
   */
  void setSwitchExpr(VarExpression value);

  /**
   * Returns the value of the '<em><b>Case</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.b3.beeLang.Case}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Case</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Case</em>' containment reference list.
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getSwitchStatement_Case()
   * @model containment="true"
   * @generated
   */
  EList<Case> getCase();

  /**
   * Returns the value of the '<em><b>Statements</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Statements</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Statements</em>' containment reference.
   * @see #setStatements(Statements)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getSwitchStatement_Statements()
   * @model containment="true"
   * @generated
   */
  Statements getStatements();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.SwitchStatement#getStatements <em>Statements</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Statements</em>' containment reference.
   * @see #getStatements()
   * @generated
   */
  void setStatements(Statements value);

} // SwitchStatement
