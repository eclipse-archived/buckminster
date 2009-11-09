/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Literal Map</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.LiteralMap#getFeatures <em>Features</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.LiteralMap#getKeyType <em>Key Type</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.LiteralMap#getValType <em>Val Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getLiteralMap()
 * @model
 * @generated
 */
public interface LiteralMap extends Expression
{
  /**
   * Returns the value of the '<em><b>Features</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.b3.beeLang.Feature}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Features</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Features</em>' containment reference list.
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getLiteralMap_Features()
   * @model containment="true"
   * @generated
   */
  EList<Feature> getFeatures();

  /**
   * Returns the value of the '<em><b>Key Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Key Type</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Key Type</em>' containment reference.
   * @see #setKeyType(TypeRef)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getLiteralMap_KeyType()
   * @model containment="true"
   * @generated
   */
  TypeRef getKeyType();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.LiteralMap#getKeyType <em>Key Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Key Type</em>' containment reference.
   * @see #getKeyType()
   * @generated
   */
  void setKeyType(TypeRef value);

  /**
   * Returns the value of the '<em><b>Val Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Val Type</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Val Type</em>' containment reference.
   * @see #setValType(TypeRef)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getLiteralMap_ValType()
   * @model containment="true"
   * @generated
   */
  TypeRef getValType();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.LiteralMap#getValType <em>Val Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Val Type</em>' containment reference.
   * @see #getValType()
   * @generated
   */
  void setValType(TypeRef value);

} // LiteralMap
