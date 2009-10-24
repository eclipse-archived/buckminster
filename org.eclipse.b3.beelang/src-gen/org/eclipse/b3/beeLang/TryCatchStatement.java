/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Try Catch Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.TryCatchStatement#getTryBlock <em>Try Block</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.TryCatchStatement#getCatchBlock <em>Catch Block</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.TryCatchStatement#getFinallyBlock <em>Finally Block</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.TryCatchStatement#getFinally <em>Finally</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getTryCatchStatement()
 * @model
 * @generated
 */
public interface TryCatchStatement extends Statement
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
   * @see #setTryBlock(CompoundStatement)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getTryCatchStatement_TryBlock()
   * @model containment="true"
   * @generated
   */
  CompoundStatement getTryBlock();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.TryCatchStatement#getTryBlock <em>Try Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Try Block</em>' containment reference.
   * @see #getTryBlock()
   * @generated
   */
  void setTryBlock(CompoundStatement value);

  /**
   * Returns the value of the '<em><b>Catch Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Catch Block</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Catch Block</em>' containment reference.
   * @see #setCatchBlock(CatchBlock)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getTryCatchStatement_CatchBlock()
   * @model containment="true"
   * @generated
   */
  CatchBlock getCatchBlock();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.TryCatchStatement#getCatchBlock <em>Catch Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Catch Block</em>' containment reference.
   * @see #getCatchBlock()
   * @generated
   */
  void setCatchBlock(CatchBlock value);

  /**
   * Returns the value of the '<em><b>Finally Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Finally Block</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Finally Block</em>' containment reference.
   * @see #setFinallyBlock(FinallyBlock)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getTryCatchStatement_FinallyBlock()
   * @model containment="true"
   * @generated
   */
  FinallyBlock getFinallyBlock();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.TryCatchStatement#getFinallyBlock <em>Finally Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Finally Block</em>' containment reference.
   * @see #getFinallyBlock()
   * @generated
   */
  void setFinallyBlock(FinallyBlock value);

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
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getTryCatchStatement_Finally()
   * @model containment="true"
   * @generated
   */
  FinallyBlock getFinally();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.TryCatchStatement#getFinally <em>Finally</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Finally</em>' containment reference.
   * @see #getFinally()
   * @generated
   */
  void setFinally(FinallyBlock value);

} // TryCatchStatement
