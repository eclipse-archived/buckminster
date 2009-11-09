/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Required Capability</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.RequiredCapability#getCapability <em>Capability</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.RequiredCapability#getRange <em>Range</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getRequiredCapability()
 * @model
 * @generated
 */
public interface RequiredCapability extends EObject
{
  /**
   * Returns the value of the '<em><b>Capability</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Capability</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Capability</em>' containment reference.
   * @see #setCapability(FilteredCapability)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getRequiredCapability_Capability()
   * @model containment="true"
   * @generated
   */
  FilteredCapability getCapability();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.RequiredCapability#getCapability <em>Capability</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Capability</em>' containment reference.
   * @see #getCapability()
   * @generated
   */
  void setCapability(FilteredCapability value);

  /**
   * Returns the value of the '<em><b>Range</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Range</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Range</em>' containment reference.
   * @see #setRange(VersionRange)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getRequiredCapability_Range()
   * @model containment="true"
   * @generated
   */
  VersionRange getRange();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.RequiredCapability#getRange <em>Range</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Range</em>' containment reference.
   * @see #getRange()
   * @generated
   */
  void setRange(VersionRange value);

} // RequiredCapability
