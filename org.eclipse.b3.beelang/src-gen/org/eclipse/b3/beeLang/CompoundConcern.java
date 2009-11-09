/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Compound Concern</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.CompoundConcern#getAdvice <em>Advice</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getCompoundConcern()
 * @model
 * @generated
 */
public interface CompoundConcern extends Advice
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
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getCompoundConcern_Advice()
   * @model containment="true"
   * @generated
   */
  EList<AdviceStatement> getAdvice();

} // CompoundConcern
