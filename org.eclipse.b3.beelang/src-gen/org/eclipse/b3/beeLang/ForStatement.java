/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>For Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.ForStatement#getInit <em>Init</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.ForStatement#getCond <em>Cond</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.ForStatement#getIterate <em>Iterate</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.ForStatement#isInLoop <em>In Loop</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.ForStatement#getBody <em>Body</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getForStatement()
 * @model
 * @generated
 */
public interface ForStatement extends Statement
{
  /**
   * Returns the value of the '<em><b>Init</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Init</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Init</em>' containment reference.
   * @see #setInit(VarExpressionList)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getForStatement_Init()
   * @model containment="true"
   * @generated
   */
  VarExpressionList getInit();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.ForStatement#getInit <em>Init</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Init</em>' containment reference.
   * @see #getInit()
   * @generated
   */
  void setInit(VarExpressionList value);

  /**
   * Returns the value of the '<em><b>Cond</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Cond</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Cond</em>' containment reference.
   * @see #setCond(Expression)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getForStatement_Cond()
   * @model containment="true"
   * @generated
   */
  Expression getCond();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.ForStatement#getCond <em>Cond</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Cond</em>' containment reference.
   * @see #getCond()
   * @generated
   */
  void setCond(Expression value);

  /**
   * Returns the value of the '<em><b>Iterate</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Iterate</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Iterate</em>' containment reference.
   * @see #setIterate(Expression)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getForStatement_Iterate()
   * @model containment="true"
   * @generated
   */
  Expression getIterate();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.ForStatement#getIterate <em>Iterate</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Iterate</em>' containment reference.
   * @see #getIterate()
   * @generated
   */
  void setIterate(Expression value);

  /**
   * Returns the value of the '<em><b>In Loop</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>In Loop</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>In Loop</em>' attribute.
   * @see #setInLoop(boolean)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getForStatement_InLoop()
   * @model
   * @generated
   */
  boolean isInLoop();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.ForStatement#isInLoop <em>In Loop</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>In Loop</em>' attribute.
   * @see #isInLoop()
   * @generated
   */
  void setInLoop(boolean value);

  /**
   * Returns the value of the '<em><b>Body</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Body</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Body</em>' containment reference.
   * @see #setBody(Statement)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getForStatement_Body()
   * @model containment="true"
   * @generated
   */
  Statement getBody();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.ForStatement#getBody <em>Body</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Body</em>' containment reference.
   * @see #getBody()
   * @generated
   */
  void setBody(Statement value);

} // ForStatement
