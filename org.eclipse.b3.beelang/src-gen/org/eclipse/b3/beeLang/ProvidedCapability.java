/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Provided Capability</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.ProvidedCapability#getCapability <em>Capability</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.ProvidedCapability#getVersion <em>Version</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getProvidedCapability()
 * @model
 * @generated
 */
public interface ProvidedCapability extends EObject
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
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getProvidedCapability_Capability()
   * @model containment="true"
   * @generated
   */
  FilteredCapability getCapability();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.ProvidedCapability#getCapability <em>Capability</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Capability</em>' containment reference.
   * @see #getCapability()
   * @generated
   */
  void setCapability(FilteredCapability value);

  /**
   * Returns the value of the '<em><b>Version</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Version</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Version</em>' containment reference.
   * @see #setVersion(Version)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getProvidedCapability_Version()
   * @model containment="true"
   * @generated
   */
  Version getVersion();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.ProvidedCapability#getVersion <em>Version</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Version</em>' containment reference.
   * @see #getVersion()
   * @generated
   */
  void setVersion(Version value);

} // ProvidedCapability
