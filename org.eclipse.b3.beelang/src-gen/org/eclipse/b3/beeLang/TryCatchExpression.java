/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Try Catch Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.TryCatchExpression#getTryBlock <em>Try Block</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.TryCatchExpression#getCatch <em>Catch</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.TryCatchExpression#getFinally <em>Finally</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getTryCatchExpression()
 * @model
 * @generated
 */
public interface TryCatchExpression extends Expression
{
  /**
   * Returns the value of the '<em><b>Try Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Try Block</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Try Block</em>' containment reference.
   * @see #setTryBlock(Expression)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getTryCatchExpression_TryBlock()
   * @model containment="true"
   * @generated
   */
  Expression getTryBlock();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.TryCatchExpression#getTryBlock <em>Try Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Try Block</em>' containment reference.
   * @see #getTryBlock()
   * @generated
   */
  void setTryBlock(Expression value);

  /**
   * Returns the value of the '<em><b>Catch</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.b3.beeLang.CatchBlock}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Catch</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Catch</em>' containment reference list.
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getTryCatchExpression_Catch()
   * @model containment="true"
   * @generated
   */
  EList<CatchBlock> getCatch();

  /**
   * Returns the value of the '<em><b>Finally</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Finally</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Finally</em>' containment reference.
   * @see #setFinally(FinallyBlock)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getTryCatchExpression_Finally()
   * @model containment="true"
   * @generated
   */
  FinallyBlock getFinally();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.TryCatchExpression#getFinally <em>Finally</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Finally</em>' containment reference.
   * @see #getFinally()
   * @generated
   */
  void setFinally(FinallyBlock value);

} // TryCatchExpression
