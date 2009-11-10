/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Function</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.Function#getFunc <em>Func</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getFunction()
 * @model
 * @generated
 */
public interface Function extends Statment
{
  /**
   * Returns the value of the '<em><b>Func</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Func</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Func</em>' containment reference.
   * @see #setFunc(FunctionOrMethod)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getFunction_Func()
   * @model containment="true"
   * @generated
   */
  FunctionOrMethod getFunc();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.Function#getFunc <em>Func</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Func</em>' containment reference.
   * @see #getFunc()
   * @generated
   */
  void setFunc(FunctionOrMethod value);

} // Function
