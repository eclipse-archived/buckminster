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
 * A representation of the model object '<em><b>Query Path</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.QueryPath#isAbsolute <em>Absolute</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.QueryPath#getSelectors <em>Selectors</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getQueryPath()
 * @model
 * @generated
 */
public interface QueryPath extends EObject
{
  /**
   * Returns the value of the '<em><b>Absolute</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Absolute</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Absolute</em>' attribute.
   * @see #setAbsolute(boolean)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getQueryPath_Absolute()
   * @model
   * @generated
   */
  boolean isAbsolute();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.QueryPath#isAbsolute <em>Absolute</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Absolute</em>' attribute.
   * @see #isAbsolute()
   * @generated
   */
  void setAbsolute(boolean value);

  /**
   * Returns the value of the '<em><b>Selectors</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.b3.beeLang.Selector}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Selectors</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Selectors</em>' containment reference list.
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getQueryPath_Selectors()
   * @model containment="true"
   * @generated
   */
  EList<Selector> getSelectors();

} // QueryPath
