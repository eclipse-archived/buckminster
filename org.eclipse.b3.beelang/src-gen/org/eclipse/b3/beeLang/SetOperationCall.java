/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Set Operation Call</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.SetOperationCall#getParams <em>Params</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.SetOperationCall#getOp <em>Op</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getSetOperationCall()
 * @model
 * @generated
 */
public interface SetOperationCall extends Expression
{
  /**
   * Returns the value of the '<em><b>Params</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.b3.beeLang.Expression}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Params</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Params</em>' containment reference list.
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getSetOperationCall_Params()
   * @model containment="true"
   * @generated
   */
  EList<Expression> getParams();

  /**
   * Returns the value of the '<em><b>Op</b></em>' attribute.
   * The literals are from the enumeration {@link org.eclipse.b3.beeLang.SetOperator}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Op</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Op</em>' attribute.
   * @see org.eclipse.b3.beeLang.SetOperator
   * @see #setOp(SetOperator)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getSetOperationCall_Op()
   * @model
   * @generated
   */
  SetOperator getOp();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.SetOperationCall#getOp <em>Op</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Op</em>' attribute.
   * @see org.eclipse.b3.beeLang.SetOperator
   * @see #getOp()
   * @generated
   */
  void setOp(SetOperator value);

} // SetOperationCall
