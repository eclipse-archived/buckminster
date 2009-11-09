/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Simple Type Ref</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.SimpleTypeRef#getTypeName <em>Type Name</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.SimpleTypeRef#getRuleTypeParameter <em>Rule Type Parameter</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getSimpleTypeRef()
 * @model
 * @generated
 */
public interface SimpleTypeRef extends TypeRef
{
  /**
   * Returns the value of the '<em><b>Type Name</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Type Name</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type Name</em>' attribute list.
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getSimpleTypeRef_TypeName()
   * @model unique="false"
   * @generated
   */
  EList<String> getTypeName();

  /**
   * Returns the value of the '<em><b>Rule Type Parameter</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.b3.beeLang.RuleTypeParam}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Rule Type Parameter</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Rule Type Parameter</em>' containment reference list.
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getSimpleTypeRef_RuleTypeParameter()
   * @model containment="true"
   * @generated
   */
  EList<RuleTypeParam> getRuleTypeParameter();

} // SimpleTypeRef
