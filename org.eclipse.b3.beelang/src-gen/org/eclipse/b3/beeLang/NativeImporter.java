/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Native Importer</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.NativeImporter#getUriString <em>Uri String</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getNativeImporter()
 * @model
 * @generated
 */
public interface NativeImporter extends EObject
{
  /**
   * Returns the value of the '<em><b>Uri String</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Uri String</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Uri String</em>' attribute.
   * @see #setUriString(String)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getNativeImporter_UriString()
   * @model
   * @generated
   */
  String getUriString();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.NativeImporter#getUriString <em>Uri String</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Uri String</em>' attribute.
   * @see #getUriString()
   * @generated
   */
  void setUriString(String value);

} // NativeImporter
