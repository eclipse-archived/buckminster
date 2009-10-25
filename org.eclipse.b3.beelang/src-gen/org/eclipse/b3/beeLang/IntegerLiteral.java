/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Integer Literal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.IntegerLiteral#getIval <em>Ival</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getIntegerLiteral()
 * @model
 * @generated
 */
public interface IntegerLiteral extends Expression
{
  /**
   * Returns the value of the '<em><b>Ival</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Ival</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Ival</em>' attribute.
   * @see #setIval(int)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getIntegerLiteral_Ival()
   * @model
   * @generated
   */
  int getIval();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.IntegerLiteral#getIval <em>Ival</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Ival</em>' attribute.
   * @see #getIval()
   * @generated
   */
  void setIval(int value);

} // IntegerLiteral
