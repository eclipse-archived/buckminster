/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>At Call</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.AtCall#getTarget <em>Target</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.AtCall#getIndex <em>Index</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.AtCall#getParams <em>Params</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getAtCall()
 * @model
 * @generated
 */
public interface AtCall extends Expression
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
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getAtCall_Target()
   * @model containment="true"
   * @generated
   */
  Expression getTarget();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.AtCall#getTarget <em>Target</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Target</em>' containment reference.
   * @see #getTarget()
   * @generated
   */
  void setTarget(Expression value);

  /**
   * Returns the value of the '<em><b>Index</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Index</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Index</em>' containment reference.
   * @see #setIndex(Expression)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getAtCall_Index()
   * @model containment="true"
   * @generated
   */
  Expression getIndex();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.AtCall#getIndex <em>Index</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Index</em>' containment reference.
   * @see #getIndex()
   * @generated
   */
  void setIndex(Expression value);

  /**
   * Returns the value of the '<em><b>Params</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.b3.beeLang.Parameter}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Params</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Params</em>' containment reference list.
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getAtCall_Params()
   * @model containment="true"
   * @generated
   */
  EList<Parameter> getParams();

} // AtCall
