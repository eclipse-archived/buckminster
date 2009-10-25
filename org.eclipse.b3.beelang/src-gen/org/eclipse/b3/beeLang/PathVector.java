/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Path Vector</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.PathVector#getBasePath <em>Base Path</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.PathVector#getPaths <em>Paths</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getPathVector()
 * @model
 * @generated
 */
public interface PathVector extends PathVectorElement
{
  /**
   * Returns the value of the '<em><b>Base Path</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Base Path</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Base Path</em>' attribute.
   * @see #setBasePath(String)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getPathVector_BasePath()
   * @model
   * @generated
   */
  String getBasePath();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.PathVector#getBasePath <em>Base Path</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Base Path</em>' attribute.
   * @see #getBasePath()
   * @generated
   */
  void setBasePath(String value);

  /**
   * Returns the value of the '<em><b>Paths</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Paths</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Paths</em>' attribute list.
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getPathVector_Paths()
   * @model unique="false"
   * @generated
   */
  EList<String> getPaths();

} // PathVector
