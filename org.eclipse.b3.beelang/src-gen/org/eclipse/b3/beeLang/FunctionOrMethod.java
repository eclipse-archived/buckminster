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
 * A representation of the model object '<em><b>Function Or Method</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.FunctionOrMethod#getDocumentation <em>Documentation</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.FunctionOrMethod#getVisibility <em>Visibility</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.FunctionOrMethod#getExecutionMode <em>Execution Mode</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.FunctionOrMethod#isFinal <em>Final</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.FunctionOrMethod#isCached <em>Cached</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.FunctionOrMethod#getForm <em>Form</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.FunctionOrMethod#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.FunctionOrMethod#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.FunctionOrMethod#getTypeParams <em>Type Params</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.FunctionOrMethod#getParams <em>Params</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.FunctionOrMethod#getBody <em>Body</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getFunctionOrMethod()
 * @model
 * @generated
 */
public interface FunctionOrMethod extends EObject
{
  /**
   * Returns the value of the '<em><b>Documentation</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Documentation</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Documentation</em>' attribute.
   * @see #setDocumentation(String)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getFunctionOrMethod_Documentation()
   * @model
   * @generated
   */
  String getDocumentation();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.FunctionOrMethod#getDocumentation <em>Documentation</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Documentation</em>' attribute.
   * @see #getDocumentation()
   * @generated
   */
  void setDocumentation(String value);

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
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getFunctionOrMethod_Visibility()
   * @model
   * @generated
   */
  Visibility getVisibility();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.FunctionOrMethod#getVisibility <em>Visibility</em>}' attribute.
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
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getFunctionOrMethod_ExecutionMode()
   * @model
   * @generated
   */
  ExecutionMode getExecutionMode();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.FunctionOrMethod#getExecutionMode <em>Execution Mode</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Execution Mode</em>' attribute.
   * @see org.eclipse.b3.beeLang.ExecutionMode
   * @see #getExecutionMode()
   * @generated
   */
  void setExecutionMode(ExecutionMode value);

  /**
   * Returns the value of the '<em><b>Final</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Final</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Final</em>' attribute.
   * @see #setFinal(boolean)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getFunctionOrMethod_Final()
   * @model
   * @generated
   */
  boolean isFinal();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.FunctionOrMethod#isFinal <em>Final</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Final</em>' attribute.
   * @see #isFinal()
   * @generated
   */
  void setFinal(boolean value);

  /**
   * Returns the value of the '<em><b>Cached</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Cached</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Cached</em>' attribute.
   * @see #setCached(boolean)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getFunctionOrMethod_Cached()
   * @model
   * @generated
   */
  boolean isCached();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.FunctionOrMethod#isCached <em>Cached</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Cached</em>' attribute.
   * @see #isCached()
   * @generated
   */
  void setCached(boolean value);

  /**
   * Returns the value of the '<em><b>Form</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Form</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Form</em>' attribute.
   * @see #setForm(String)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getFunctionOrMethod_Form()
   * @model
   * @generated
   */
  String getForm();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.FunctionOrMethod#getForm <em>Form</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Form</em>' attribute.
   * @see #getForm()
   * @generated
   */
  void setForm(String value);

  /**
   * Returns the value of the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Type</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type</em>' containment reference.
   * @see #setType(TypeRef)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getFunctionOrMethod_Type()
   * @model containment="true"
   * @generated
   */
  TypeRef getType();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.FunctionOrMethod#getType <em>Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type</em>' containment reference.
   * @see #getType()
   * @generated
   */
  void setType(TypeRef value);

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
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getFunctionOrMethod_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.FunctionOrMethod#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Type Params</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.b3.beeLang.TypeParamDeclaration}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Type Params</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type Params</em>' containment reference list.
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getFunctionOrMethod_TypeParams()
   * @model containment="true"
   * @generated
   */
  EList<TypeParamDeclaration> getTypeParams();

  /**
   * Returns the value of the '<em><b>Params</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.b3.beeLang.ParameterDeclaration}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Params</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Params</em>' containment reference list.
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getFunctionOrMethod_Params()
   * @model containment="true"
   * @generated
   */
  EList<ParameterDeclaration> getParams();

  /**
   * Returns the value of the '<em><b>Body</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Body</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Body</em>' containment reference.
   * @see #setBody(Expression)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getFunctionOrMethod_Body()
   * @model containment="true"
   * @generated
   */
  Expression getBody();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.FunctionOrMethod#getBody <em>Body</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Body</em>' containment reference.
   * @see #getBody()
   * @generated
   */
  void setBody(Expression value);

} // FunctionOrMethod
