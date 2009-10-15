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
 * A representation of the model object '<em><b>Advice Path</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.AdvicePath#getPathElements <em>Path Elements</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.AdvicePath#getPathElement <em>Path Element</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getAdvicePath()
 * @model
 * @generated
 */
public interface AdvicePath extends EObject
{
  /**
   * Returns the value of the '<em><b>Path Elements</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.b3.beeLang.AdvicePathElement}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Path Elements</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Path Elements</em>' containment reference list.
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getAdvicePath_PathElements()
   * @model containment="true"
   * @generated
   */
  EList<AdvicePathElement> getPathElements();

  /**
   * Returns the value of the '<em><b>Path Element</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.b3.beeLang.AdvicePathElement}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Path Element</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Path Element</em>' containment reference list.
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getAdvicePath_PathElement()
   * @model containment="true"
   * @generated
   */
  EList<AdvicePathElement> getPathElement();

} // AdvicePath
