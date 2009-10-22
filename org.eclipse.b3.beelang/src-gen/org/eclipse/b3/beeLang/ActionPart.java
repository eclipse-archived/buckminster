/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Action Part</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.ActionPart#getExecutionMode <em>Execution Mode</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.ActionPart#getAsserts <em>Asserts</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.ActionPart#getProperties <em>Properties</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.ActionPart#getAdvice <em>Advice</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.ActionPart#getResultGroup <em>Result Group</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.ActionPart#getLayout <em>Layout</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.ActionPart#getStatements <em>Statements</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getActionPart()
 * @model
 * @generated
 */
public interface ActionPart extends BuildPart
{
  /**
   * Returns the value of the '<em><b>Execution Mode</b></em>' attribute.
   * The literals are from the enumeration {@link org.eclipse.b3.beeLang.ExecutionMode}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Execution Mode</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Execution Mode</em>' attribute.
   * @see org.eclipse.b3.beeLang.ExecutionMode
   * @see #setExecutionMode(ExecutionMode)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getActionPart_ExecutionMode()
   * @model
   * @generated
   */
  ExecutionMode getExecutionMode();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.ActionPart#getExecutionMode <em>Execution Mode</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Execution Mode</em>' attribute.
   * @see org.eclipse.b3.beeLang.ExecutionMode
   * @see #getExecutionMode()
   * @generated
   */
  void setExecutionMode(ExecutionMode value);

  /**
   * Returns the value of the '<em><b>Asserts</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Asserts</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Asserts</em>' containment reference list.
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getActionPart_Asserts()
   * @model containment="true"
   * @generated
   */
  EList<EObject> getAsserts();

  /**
   * Returns the value of the '<em><b>Properties</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.b3.beeLang.PropertyStatement}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Properties</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Properties</em>' containment reference list.
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getActionPart_Properties()
   * @model containment="true"
   * @generated
   */
  EList<PropertyStatement> getProperties();

  /**
   * Returns the value of the '<em><b>Advice</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Advice</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Advice</em>' containment reference.
   * @see #setAdvice(Advice)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getActionPart_Advice()
   * @model containment="true"
   * @generated
   */
  Advice getAdvice();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.ActionPart#getAdvice <em>Advice</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Advice</em>' containment reference.
   * @see #getAdvice()
   * @generated
   */
  void setAdvice(Advice value);

  /**
   * Returns the value of the '<em><b>Result Group</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Result Group</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Result Group</em>' containment reference.
   * @see #setResultGroup(ActionInputGroup)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getActionPart_ResultGroup()
   * @model containment="true"
   * @generated
   */
  ActionInputGroup getResultGroup();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.ActionPart#getResultGroup <em>Result Group</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Result Group</em>' containment reference.
   * @see #getResultGroup()
   * @generated
   */
  void setResultGroup(ActionInputGroup value);

  /**
   * Returns the value of the '<em><b>Layout</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.b3.beeLang.Layout}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Layout</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Layout</em>' containment reference list.
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getActionPart_Layout()
   * @model containment="true"
   * @generated
   */
  EList<Layout> getLayout();

  /**
   * Returns the value of the '<em><b>Statements</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Statements</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Statements</em>' containment reference.
   * @see #setStatements(Statements)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getActionPart_Statements()
   * @model containment="true"
   * @generated
   */
  Statements getStatements();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.ActionPart#getStatements <em>Statements</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Statements</em>' containment reference.
   * @see #getStatements()
   * @generated
   */
  void setStatements(Statements value);

} // ActionPart
