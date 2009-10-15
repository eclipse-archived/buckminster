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
 * A representation of the model object '<em><b>Compound Advice</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.CompoundAdvice#getAdvice <em>Advice</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getCompoundAdvice()
 * @model
 * @generated
 */
public interface CompoundAdvice extends EObject
{
  /**
   * Returns the value of the '<em><b>Advice</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.b3.beeLang.AdviceStatement}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Advice</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Advice</em>' containment reference list.
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getCompoundAdvice_Advice()
   * @model containment="true"
   * @generated
   */
  EList<AdviceStatement> getAdvice();

} // CompoundAdvice
