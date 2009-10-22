/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>If Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.IfExpression#getCondition <em>Condition</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.IfExpression#getThenPart <em>Then Part</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.IfExpression#getElsePart <em>Else Part</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getIfExpression()
 * @model
 * @generated
 */
public interface IfExpression extends Expression
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
   * @see #setCondition(Expression)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getIfExpression_Condition()
   * @model containment="true"
   * @generated
   */
  Expression getCondition();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.IfExpression#getCondition <em>Condition</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Condition</em>' containment reference.
   * @see #getCondition()
   * @generated
   */
  void setCondition(Expression value);

  /**
   * Returns the value of the '<em><b>Then Part</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Then Part</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Then Part</em>' containment reference.
   * @see #setThenPart(Expression)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getIfExpression_ThenPart()
   * @model containment="true"
   * @generated
   */
  Expression getThenPart();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.IfExpression#getThenPart <em>Then Part</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Then Part</em>' containment reference.
   * @see #getThenPart()
   * @generated
   */
  void setThenPart(Expression value);

  /**
   * Returns the value of the '<em><b>Else Part</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Else Part</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Else Part</em>' containment reference.
   * @see #setElsePart(Expression)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getIfExpression_ElsePart()
   * @model containment="true"
   * @generated
   */
  Expression getElsePart();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.IfExpression#getElsePart <em>Else Part</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Else Part</em>' containment reference.
   * @see #getElsePart()
   * @generated
   */
  void setElsePart(Expression value);

} // IfExpression
