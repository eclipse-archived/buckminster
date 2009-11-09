/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Closure Type Ref</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.ClosureTypeRef#getParameterTypes <em>Parameter Types</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.ClosureTypeRef#getReturnType <em>Return Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getClosureTypeRef()
 * @model
 * @generated
 */
public interface ClosureTypeRef extends TypeRef
{
  /**
   * Returns the value of the '<em><b>Parameter Types</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.b3.beeLang.TypeRef}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Parameter Types</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Parameter Types</em>' containment reference list.
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getClosureTypeRef_ParameterTypes()
   * @model containment="true"
   * @generated
   */
  EList<TypeRef> getParameterTypes();

  /**
   * Returns the value of the '<em><b>Return Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Return Type</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Return Type</em>' containment reference.
   * @see #setReturnType(TypeRef)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getClosureTypeRef_ReturnType()
   * @model containment="true"
   * @generated
   */
  TypeRef getReturnType();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.ClosureTypeRef#getReturnType <em>Return Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Return Type</em>' containment reference.
   * @see #getReturnType()
   * @generated
   */
  void setReturnType(TypeRef value);

} // ClosureTypeRef
