/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Compound References</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.CompoundReferences#getPrerequisites <em>Prerequisites</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getCompoundReferences()
 * @model
 * @generated
 */
public interface CompoundReferences extends PrerequisiteEntry
{
  /**
   * Returns the value of the '<em><b>Prerequisites</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.b3.beeLang.Prerequisite}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Prerequisites</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Prerequisites</em>' containment reference list.
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getCompoundReferences_Prerequisites()
   * @model containment="true"
   * @generated
   */
  EList<Prerequisite> getPrerequisites();

} // CompoundReferences
