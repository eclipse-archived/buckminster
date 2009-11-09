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
 * A representation of the model object '<em><b>Type Param Declaration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.TypeParamDeclaration#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.TypeParamDeclaration#getSuperType <em>Super Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getTypeParamDeclaration()
 * @model
 * @generated
 */
public interface TypeParamDeclaration extends EObject
{
  /**
   * Returns the value of the '<em><b>Type</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.b3.beeLang.TypeRef}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Type</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type</em>' containment reference list.
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getTypeParamDeclaration_Type()
   * @model containment="true"
   * @generated
   */
  EList<TypeRef> getType();

  /**
   * Returns the value of the '<em><b>Super Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Super Type</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Super Type</em>' containment reference.
   * @see #setSuperType(TypeRef)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getTypeParamDeclaration_SuperType()
   * @model containment="true"
   * @generated
   */
  TypeRef getSuperType();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.TypeParamDeclaration#getSuperType <em>Super Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Super Type</em>' containment reference.
   * @see #getSuperType()
   * @generated
   */
  void setSuperType(TypeRef value);

} // TypeParamDeclaration
