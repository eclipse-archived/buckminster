/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Post Op Call</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.PostOpCall#getTarget <em>Target</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getPostOpCall()
 * @model
 * @generated
 */
public interface PostOpCall extends Expression
{
  /**
   * Returns the value of the '<em><b>Target</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Target</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Target</em>' containment reference.
   * @see #setTarget(Expression)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getPostOpCall_Target()
   * @model containment="true"
   * @generated
   */
  Expression getTarget();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.PostOpCall#getTarget <em>Target</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Target</em>' containment reference.
   * @see #getTarget()
   * @generated
   */
  void setTarget(Expression value);

} // PostOpCall
