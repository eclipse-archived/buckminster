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
 * A representation of the model object '<em><b>Synchronization</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.Synchronization#getPartrefs <em>Partrefs</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getSynchronization()
 * @model
 * @generated
 */
public interface Synchronization extends EObject
{
  /**
   * Returns the value of the '<em><b>Partrefs</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Partrefs</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Partrefs</em>' attribute list.
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getSynchronization_Partrefs()
   * @model unique="false"
   * @generated
   */
  EList<String> getPartrefs();

} // Synchronization
