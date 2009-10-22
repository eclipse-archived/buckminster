/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Return Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.ReturnStatement#getReturn <em>Return</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getReturnStatement()
 * @model
 * @generated
 */
public interface ReturnStatement extends Statement
{
  /**
   * Returns the value of the '<em><b>Return</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Return</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Return</em>' containment reference.
   * @see #setReturn(Expression)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getReturnStatement_Return()
   * @model containment="true"
   * @generated
   */
  Expression getReturn();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.ReturnStatement#getReturn <em>Return</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Return</em>' containment reference.
   * @see #getReturn()
   * @generated
   */
  void setReturn(Expression value);

} // ReturnStatement
