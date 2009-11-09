/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>With Clause Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.WithClauseExpression#getWithclause <em>Withclause</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.WithClauseExpression#getExpr <em>Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getWithClauseExpression()
 * @model
 * @generated
 */
public interface WithClauseExpression extends Expression
{
  /**
   * Returns the value of the '<em><b>Withclause</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Withclause</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Withclause</em>' containment reference.
   * @see #setWithclause(WithClause)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getWithClauseExpression_Withclause()
   * @model containment="true"
   * @generated
   */
  WithClause getWithclause();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.WithClauseExpression#getWithclause <em>Withclause</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Withclause</em>' containment reference.
   * @see #getWithclause()
   * @generated
   */
  void setWithclause(WithClause value);

  /**
   * Returns the value of the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Expr</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Expr</em>' containment reference.
   * @see #setExpr(Expression)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getWithClauseExpression_Expr()
   * @model containment="true"
   * @generated
   */
  Expression getExpr();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.WithClauseExpression#getExpr <em>Expr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Expr</em>' containment reference.
   * @see #getExpr()
   * @generated
   */
  void setExpr(Expression value);

} // WithClauseExpression
