/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Creator Call</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.CreatorCall#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.CreatorCall#getTarget <em>Target</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.CreatorCall#getParameters <em>Parameters</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.CreatorCall#getAlias <em>Alias</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.CreatorCall#getContextBlock <em>Context Block</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getCreatorCall()
 * @model
 * @generated
 */
public interface CreatorCall extends Expression
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
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getCreatorCall_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.CreatorCall#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Target</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Target</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Target</em>' containment reference.
   * @see #setTarget(TypeRef)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getCreatorCall_Target()
   * @model containment="true"
   * @generated
   */
  TypeRef getTarget();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.CreatorCall#getTarget <em>Target</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Target</em>' containment reference.
   * @see #getTarget()
   * @generated
   */
  void setTarget(TypeRef value);

  /**
   * Returns the value of the '<em><b>Parameters</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Parameters</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Parameters</em>' containment reference.
   * @see #setParameters(ParameterList)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getCreatorCall_Parameters()
   * @model containment="true"
   * @generated
   */
  ParameterList getParameters();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.CreatorCall#getParameters <em>Parameters</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Parameters</em>' containment reference.
   * @see #getParameters()
   * @generated
   */
  void setParameters(ParameterList value);

  /**
   * Returns the value of the '<em><b>Alias</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Alias</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Alias</em>' attribute.
   * @see #setAlias(String)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getCreatorCall_Alias()
   * @model
   * @generated
   */
  String getAlias();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.CreatorCall#getAlias <em>Alias</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Alias</em>' attribute.
   * @see #getAlias()
   * @generated
   */
  void setAlias(String value);

  /**
   * Returns the value of the '<em><b>Context Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Context Block</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Context Block</em>' containment reference.
   * @see #setContextBlock(ContextBlock)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getCreatorCall_ContextBlock()
   * @model containment="true"
   * @generated
   */
  ContextBlock getContextBlock();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.CreatorCall#getContextBlock <em>Context Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Context Block</em>' containment reference.
   * @see #getContextBlock()
   * @generated
   */
  void setContextBlock(ContextBlock value);

} // CreatorCall
