/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Filtered Capability</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.FilteredCapability#getFilter <em>Filter</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.FilteredCapability#getCapability <em>Capability</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getFilteredCapability()
 * @model
 * @generated
 */
public interface FilteredCapability extends EObject
{
  /**
   * Returns the value of the '<em><b>Filter</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Filter</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Filter</em>' containment reference.
   * @see #setFilter(Filter)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getFilteredCapability_Filter()
   * @model containment="true"
   * @generated
   */
  Filter getFilter();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.FilteredCapability#getFilter <em>Filter</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Filter</em>' containment reference.
   * @see #getFilter()
   * @generated
   */
  void setFilter(Filter value);

  /**
   * Returns the value of the '<em><b>Capability</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Capability</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Capability</em>' containment reference.
   * @see #setCapability(Capability)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getFilteredCapability_Capability()
   * @model containment="true"
   * @generated
   */
  Capability getCapability();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.FilteredCapability#getCapability <em>Capability</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Capability</em>' containment reference.
   * @see #getCapability()
   * @generated
   */
  void setCapability(Capability value);

} // FilteredCapability
