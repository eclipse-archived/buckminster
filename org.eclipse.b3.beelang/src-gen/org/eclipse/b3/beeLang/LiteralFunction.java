/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Literal Function</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.LiteralFunction#getClosure <em>Closure</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getLiteralFunction()
 * @model
 * @generated
 */
public interface LiteralFunction extends Expression
{
  /**
   * Returns the value of the '<em><b>Closure</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Closure</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Closure</em>' containment reference.
   * @see #setClosure(ClosureExpression)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getLiteralFunction_Closure()
   * @model containment="true"
   * @generated
   */
  ClosureExpression getClosure();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.LiteralFunction#getClosure <em>Closure</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Closure</em>' containment reference.
   * @see #getClosure()
   * @generated
   */
  void setClosure(ClosureExpression value);

} // LiteralFunction
