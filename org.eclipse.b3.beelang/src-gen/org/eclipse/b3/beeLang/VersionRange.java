/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Version Range</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.VersionRange#getRange <em>Range</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getVersionRange()
 * @model
 * @generated
 */
public interface VersionRange extends EObject
{
  /**
   * Returns the value of the '<em><b>Range</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Range</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Range</em>' attribute.
   * @see #setRange(org.eclipse.equinox.internal.provisional.p2.core.VersionRange)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getVersionRange_Range()
   * @model dataType="beelangtypes.VersionRange"
   * @generated
   */
  org.eclipse.equinox.internal.provisional.p2.core.VersionRange getRange();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.VersionRange#getRange <em>Range</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Range</em>' attribute.
   * @see #getRange()
   * @generated
   */
  void setRange(org.eclipse.equinox.internal.provisional.p2.core.VersionRange value);

} // VersionRange
