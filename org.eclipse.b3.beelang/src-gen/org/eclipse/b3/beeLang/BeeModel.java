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
 * A representation of the model object '<em><b>Bee Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.BeeModel#getImports <em>Imports</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.BeeModel#getFunctions <em>Functions</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.BeeModel#getConcern <em>Concern</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.BeeModel#getBody <em>Body</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getBeeModel()
 * @model
 * @generated
 */
public interface BeeModel extends EObject
{
  /**
   * Returns the value of the '<em><b>Imports</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.b3.beeLang.Import}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Imports</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Imports</em>' containment reference list.
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getBeeModel_Imports()
   * @model containment="true"
   * @generated
   */
  EList<Import> getImports();

  /**
   * Returns the value of the '<em><b>Functions</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.b3.beeLang.Statment}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Functions</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Functions</em>' containment reference list.
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getBeeModel_Functions()
   * @model containment="true"
   * @generated
   */
  EList<Statment> getFunctions();

  /**
   * Returns the value of the '<em><b>Concern</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.b3.beeLang.Concern}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Concern</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Concern</em>' containment reference list.
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getBeeModel_Concern()
   * @model containment="true"
   * @generated
   */
  EList<Concern> getConcern();

  /**
   * Returns the value of the '<em><b>Body</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Body</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Body</em>' containment reference.
   * @see #setBody(BuildUnit)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getBeeModel_Body()
   * @model containment="true"
   * @generated
   */
  BuildUnit getBody();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.BeeModel#getBody <em>Body</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Body</em>' containment reference.
   * @see #getBody()
   * @generated
   */
  void setBody(BuildUnit value);

} // BeeModel
