/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang;

import org.eclipse.b3.RegularExpression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Regexp Literal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.RegexpLiteral#getVal <em>Val</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getRegexpLiteral()
 * @model
 * @generated
 */
public interface RegexpLiteral extends Expression
{
  /**
   * Returns the value of the '<em><b>Val</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Val</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Val</em>' attribute.
   * @see #setVal(RegularExpression)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getRegexpLiteral_Val()
   * @model dataType="beelangtypes.RegularExpression"
   * @generated
   */
  RegularExpression getVal();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.RegexpLiteral#getVal <em>Val</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Val</em>' attribute.
   * @see #getVal()
   * @generated
   */
  void setVal(RegularExpression value);

} // RegexpLiteral
