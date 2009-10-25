/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Closure</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.Closure#getProperties <em>Properties</em>}</li>
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
   * Returns the value of the '<em><b>Properties</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Properties</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Properties</em>' containment reference.
   * @see #setProperties(CompoundPropertyOperation)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getClosure_Properties()
   * @model containment="true"
   * @generated
   */
  CompoundPropertyOperation getProperties();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.Closure#getProperties <em>Properties</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Properties</em>' containment reference.
   * @see #getProperties()
   * @generated
   */
  void setProperties(CompoundPropertyOperation value);

  /**
   * Returns the value of the '<em><b>Advice</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Advice</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Advice</em>' containment reference.
   * @see #setAdvice(CompoundAdvice)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getClosure_Advice()
   * @model containment="true"
   * @generated
   */
  CompoundAdvice getAdvice();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.Closure#getAdvice <em>Advice</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Advice</em>' containment reference.
   * @see #getAdvice()
   * @generated
   */
  void setAdvice(CompoundAdvice value);

} // Closure
