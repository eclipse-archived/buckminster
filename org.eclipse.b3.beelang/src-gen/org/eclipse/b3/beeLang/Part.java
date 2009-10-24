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
 * A representation of the model object '<em><b>Part</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.Part#getVisibility <em>Visibility</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.Part#getExecutionMode <em>Execution Mode</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.Part#getProvidedCapabilities <em>Provided Capabilities</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.Part#getAsserts <em>Asserts</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.Part#getProperties <em>Properties</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.Part#getAdvice <em>Advice</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.Part#getResultGroup <em>Result Group</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.Part#getLayout <em>Layout</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.Part#getStatements <em>Statements</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getPart()
 * @model
 * @generated
 */
public interface Part extends EObject
{
  /**
   * Returns the value of the '<em><b>Visibility</b></em>' attribute.
   * The literals are from the enumeration {@link org.eclipse.b3.beeLang.Visibility}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Visibility</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Visibility</em>' attribute.
   * @see org.eclipse.b3.beeLang.Visibility
   * @see #setVisibility(Visibility)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getPart_Visibility()
   * @model
   * @generated
   */
  Visibility getVisibility();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.Part#getVisibility <em>Visibility</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Visibility</em>' attribute.
   * @see org.eclipse.b3.beeLang.Visibility
   * @see #getVisibility()
   * @generated
   */
  void setVisibility(Visibility value);

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
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getPart_ExecutionMode()
   * @model
   * @generated
   */
  ExecutionMode getExecutionMode();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.Part#getExecutionMode <em>Execution Mode</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Execution Mode</em>' attribute.
   * @see org.eclipse.b3.beeLang.ExecutionMode
   * @see #getExecutionMode()
   * @generated
   */
  void setExecutionMode(ExecutionMode value);

  /**
   * Returns the value of the '<em><b>Provided Capabilities</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.b3.beeLang.ProvidedCapability}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Provided Capabilities</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Provided Capabilities</em>' containment reference list.
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getPart_ProvidedCapabilities()
   * @model containment="true"
   * @generated
   */
  EList<ProvidedCapability> getProvidedCapabilities();

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
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getPart_Asserts()
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
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getPart_Properties()
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
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getPart_Advice()
   * @model containment="true"
   * @generated
   */
  Advice getAdvice();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.Part#getAdvice <em>Advice</em>}' containment reference.
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
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getPart_ResultGroup()
   * @model containment="true"
   * @generated
   */
  ActionInputGroup getResultGroup();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.Part#getResultGroup <em>Result Group</em>}' containment reference.
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
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getPart_Layout()
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
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getPart_Statements()
   * @model containment="true"
   * @generated
   */
  Statements getStatements();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.Part#getStatements <em>Statements</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Statements</em>' containment reference.
   * @see #getStatements()
   * @generated
   */
  void setStatements(Statements value);

} // Part
