/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Advice Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.AdviceStatement#getPath <em>Path</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.AdviceStatement#getOp <em>Op</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.AdviceStatement#getValue <em>Value</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.AdviceStatement#getAdvice <em>Advice</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getAdviceStatement()
 * @model
 * @generated
 */
public interface AdviceStatement extends EObject
{
  /**
   * Returns the value of the '<em><b>Path</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Path</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Path</em>' containment reference.
   * @see #setPath(AdvicePath)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getAdviceStatement_Path()
   * @model containment="true"
   * @generated
   */
  AdvicePath getPath();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.AdviceStatement#getPath <em>Path</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Path</em>' containment reference.
   * @see #getPath()
   * @generated
   */
  void setPath(AdvicePath value);

  /**
   * Returns the value of the '<em><b>Op</b></em>' attribute.
   * The literals are from the enumeration {@link org.eclipse.b3.beeLang.AssignmentOperator}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Op</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Op</em>' attribute.
   * @see org.eclipse.b3.beeLang.AssignmentOperator
   * @see #setOp(AssignmentOperator)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getAdviceStatement_Op()
   * @model
   * @generated
   */
  AssignmentOperator getOp();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.AdviceStatement#getOp <em>Op</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Op</em>' attribute.
   * @see org.eclipse.b3.beeLang.AssignmentOperator
   * @see #getOp()
   * @generated
   */
  void setOp(AssignmentOperator value);

  /**
   * Returns the value of the '<em><b>Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Value</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Value</em>' containment reference.
   * @see #setValue(Expression)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getAdviceStatement_Value()
   * @model containment="true"
   * @generated
   */
  Expression getValue();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.AdviceStatement#getValue <em>Value</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Value</em>' containment reference.
   * @see #getValue()
   * @generated
   */
  void setValue(Expression value);

  /**
   * Returns the value of the '<em><b>Advice</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Advice</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Advice</em>' containment reference.
   * @see #setAdvice(CompoundAdvice)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getAdviceStatement_Advice()
   * @model containment="true"
   * @generated
   */
  CompoundAdvice getAdvice();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.AdviceStatement#getAdvice <em>Advice</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Advice</em>' containment reference.
   * @see #getAdvice()
   * @generated
   */
  void setAdvice(CompoundAdvice value);

} // AdviceStatement
