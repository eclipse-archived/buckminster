/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Wildcard Ref Param</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.WildcardRefParam#getExtends <em>Extends</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.WildcardRefParam#getSuperRef <em>Super Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getWildcardRefParam()
 * @model
 * @generated
 */
public interface WildcardRefParam extends RuleTypeParam
{
  /**
   * Returns the value of the '<em><b>Extends</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.b3.beeLang.TypeRef}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Extends</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Extends</em>' containment reference list.
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getWildcardRefParam_Extends()
   * @model containment="true"
   * @generated
   */
  EList<TypeRef> getExtends();

  /**
   * Returns the value of the '<em><b>Super Ref</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Super Ref</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Super Ref</em>' containment reference.
   * @see #setSuperRef(TypeRef)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getWildcardRefParam_SuperRef()
   * @model containment="true"
   * @generated
   */
  TypeRef getSuperRef();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.WildcardRefParam#getSuperRef <em>Super Ref</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Super Ref</em>' containment reference.
   * @see #getSuperRef()
   * @generated
   */
  void setSuperRef(TypeRef value);

} // WildcardRefParam
