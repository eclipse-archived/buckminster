/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Resolution Strategy</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.ResolutionStrategy#getStrategy <em>Strategy</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.ResolutionStrategy#getRepositoryConfig <em>Repository Config</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getResolutionStrategy()
 * @model
 * @generated
 */
public interface ResolutionStrategy extends RepositoryConfiguration
{
  /**
   * Returns the value of the '<em><b>Strategy</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Strategy</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Strategy</em>' attribute.
   * @see #setStrategy(String)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getResolutionStrategy_Strategy()
   * @model
   * @generated
   */
  String getStrategy();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.ResolutionStrategy#getStrategy <em>Strategy</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Strategy</em>' attribute.
   * @see #getStrategy()
   * @generated
   */
  void setStrategy(String value);

  /**
   * Returns the value of the '<em><b>Repository Config</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.b3.beeLang.RepositoryConfiguration}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Repository Config</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Repository Config</em>' containment reference list.
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getResolutionStrategy_RepositoryConfig()
   * @model containment="true"
   * @generated
   */
  EList<RepositoryConfiguration> getRepositoryConfig();

} // ResolutionStrategy
