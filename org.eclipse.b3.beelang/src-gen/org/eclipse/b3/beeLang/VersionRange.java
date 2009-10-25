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
 *   <li>{@link org.eclipse.b3.beeLang.VersionRange#getMinLimit <em>Min Limit</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.VersionRange#getMin <em>Min</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.VersionRange#getMax <em>Max</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.VersionRange#getMaxLimit <em>Max Limit</em>}</li>
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
   * Returns the value of the '<em><b>Min Limit</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Min Limit</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Min Limit</em>' attribute.
   * @see #setMinLimit(String)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getVersionRange_MinLimit()
   * @model
   * @generated
   */
  String getMinLimit();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.VersionRange#getMinLimit <em>Min Limit</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Min Limit</em>' attribute.
   * @see #getMinLimit()
   * @generated
   */
  void setMinLimit(String value);

  /**
   * Returns the value of the '<em><b>Min</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Min</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Min</em>' containment reference.
   * @see #setMin(Version)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getVersionRange_Min()
   * @model containment="true"
   * @generated
   */
  Version getMin();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.VersionRange#getMin <em>Min</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Min</em>' containment reference.
   * @see #getMin()
   * @generated
   */
  void setMin(Version value);

  /**
   * Returns the value of the '<em><b>Max</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Max</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Max</em>' containment reference.
   * @see #setMax(Version)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getVersionRange_Max()
   * @model containment="true"
   * @generated
   */
  Version getMax();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.VersionRange#getMax <em>Max</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Max</em>' containment reference.
   * @see #getMax()
   * @generated
   */
  void setMax(Version value);

  /**
   * Returns the value of the '<em><b>Max Limit</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Max Limit</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Max Limit</em>' attribute.
   * @see #setMaxLimit(String)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getVersionRange_MaxLimit()
   * @model
   * @generated
   */
  String getMaxLimit();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.VersionRange#getMaxLimit <em>Max Limit</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Max Limit</em>' attribute.
   * @see #getMaxLimit()
   * @generated
   */
  void setMaxLimit(String value);

} // VersionRange
