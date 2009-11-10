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
 * A representation of the model object '<em><b>Concern Block</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.ConcernBlock#getSuperConcerns <em>Super Concerns</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.ConcernBlock#getContexts <em>Contexts</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.ConcernBlock#getFunctions <em>Functions</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getConcernBlock()
 * @model
 * @generated
 */
public interface ConcernBlock extends EObject
{
  /**
   * Returns the value of the '<em><b>Super Concerns</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Super Concerns</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Super Concerns</em>' attribute list.
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getConcernBlock_SuperConcerns()
   * @model unique="false"
   * @generated
   */
  EList<String> getSuperConcerns();

  /**
   * Returns the value of the '<em><b>Contexts</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.b3.beeLang.Context}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Contexts</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Contexts</em>' containment reference list.
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getConcernBlock_Contexts()
   * @model containment="true"
   * @generated
   */
  EList<Context> getContexts();

  /**
   * Returns the value of the '<em><b>Functions</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.b3.beeLang.Statment}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Functions</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Functions</em>' containment reference list.
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getConcernBlock_Functions()
   * @model containment="true"
   * @generated
   */
  EList<Statment> getFunctions();

} // ConcernBlock
