/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.Expression#getSval <em>Sval</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getExpression()
 * @model
 * @generated
 */
public interface Expression extends Statement
{
  /**
   * Returns the value of the '<em><b>Sval</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Sval</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Sval</em>' attribute.
   * @see #setSval(String)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getExpression_Sval()
   * @model
   * @generated
   */
  String getSval();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.Expression#getSval <em>Sval</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Sval</em>' attribute.
   * @see #getSval()
   * @generated
   */
  void setSval(String value);

} // Expression
