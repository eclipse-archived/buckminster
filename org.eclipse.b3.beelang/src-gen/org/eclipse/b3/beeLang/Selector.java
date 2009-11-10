/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang;

import org.eclipse.b3.RegularExpression;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Selector</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.Selector#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.Selector#getOp <em>Op</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.Selector#getPattern <em>Pattern</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.Selector#getPredicate <em>Predicate</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getSelector()
 * @model
 * @generated
 */
public interface Selector extends EObject
{
  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getSelector_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.Selector#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Op</b></em>' attribute.
   * The literals are from the enumeration {@link org.eclipse.b3.beeLang.SelectorOperator}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Op</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Op</em>' attribute.
   * @see org.eclipse.b3.beeLang.SelectorOperator
   * @see #setOp(SelectorOperator)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getSelector_Op()
   * @model
   * @generated
   */
  SelectorOperator getOp();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.Selector#getOp <em>Op</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Op</em>' attribute.
   * @see org.eclipse.b3.beeLang.SelectorOperator
   * @see #getOp()
   * @generated
   */
  void setOp(SelectorOperator value);

  /**
   * Returns the value of the '<em><b>Pattern</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Pattern</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Pattern</em>' attribute.
   * @see #setPattern(RegularExpression)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getSelector_Pattern()
   * @model dataType="beelangtypes.RegularExpression"
   * @generated
   */
  RegularExpression getPattern();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.Selector#getPattern <em>Pattern</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Pattern</em>' attribute.
   * @see #getPattern()
   * @generated
   */
  void setPattern(RegularExpression value);

  /**
   * Returns the value of the '<em><b>Predicate</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Predicate</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Predicate</em>' containment reference.
   * @see #setPredicate(Expression)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getSelector_Predicate()
   * @model containment="true"
   * @generated
   */
  Expression getPredicate();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.Selector#getPredicate <em>Predicate</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Predicate</em>' containment reference.
   * @see #getPredicate()
   * @generated
   */
  void setPredicate(Expression value);

} // Selector
