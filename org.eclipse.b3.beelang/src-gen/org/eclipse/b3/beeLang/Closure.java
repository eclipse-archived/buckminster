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
 * A representation of the model object '<em><b>Closure</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.Closure#getUnsetProperties <em>Unset Properties</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.Closure#getSetProperties <em>Set Properties</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.Closure#getAdvice <em>Advice</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getClosure()
 * @model
 * @generated
 */
public interface Closure extends EObject
{
  /**
   * Returns the value of the '<em><b>Unset Properties</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Unset Properties</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Unset Properties</em>' attribute list.
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getClosure_UnsetProperties()
   * @model unique="false"
   * @generated
   */
  EList<String> getUnsetProperties();

  /**
   * Returns the value of the '<em><b>Set Properties</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.b3.beeLang.StringProperty}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Set Properties</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Set Properties</em>' containment reference list.
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getClosure_SetProperties()
   * @model containment="true"
   * @generated
   */
  EList<StringProperty> getSetProperties();

  /**
   * Returns the value of the '<em><b>Advice</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.b3.beeLang.CompoundAdvice}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Advice</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Advice</em>' containment reference list.
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getClosure_Advice()
   * @model containment="true"
   * @generated
   */
  EList<CompoundAdvice> getAdvice();

} // Closure
