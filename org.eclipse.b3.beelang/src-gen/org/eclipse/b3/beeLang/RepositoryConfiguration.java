/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Repository Configuration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.RepositoryConfiguration#getLocation <em>Location</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.RepositoryConfiguration#getResolverClass <em>Resolver Class</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.RepositoryConfiguration#getAdvice <em>Advice</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getRepositoryConfiguration()
 * @model
 * @generated
 */
public interface RepositoryConfiguration extends EObject
{
  /**
   * Returns the value of the '<em><b>Location</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Location</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Location</em>' attribute.
   * @see #setLocation(String)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getRepositoryConfiguration_Location()
   * @model
   * @generated
   */
  String getLocation();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.RepositoryConfiguration#getLocation <em>Location</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Location</em>' attribute.
   * @see #getLocation()
   * @generated
   */
  void setLocation(String value);

  /**
   * Returns the value of the '<em><b>Resolver Class</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Resolver Class</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Resolver Class</em>' attribute.
   * @see #setResolverClass(String)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getRepositoryConfiguration_ResolverClass()
   * @model
   * @generated
   */
  String getResolverClass();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.RepositoryConfiguration#getResolverClass <em>Resolver Class</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Resolver Class</em>' attribute.
   * @see #getResolverClass()
   * @generated
   */
  void setResolverClass(String value);

  /**
   * Returns the value of the '<em><b>Advice</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Advice</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Advice</em>' containment reference.
   * @see #setAdvice(CompoundAdvice)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getRepositoryConfiguration_Advice()
   * @model containment="true"
   * @generated
   */
  CompoundAdvice getAdvice();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.RepositoryConfiguration#getAdvice <em>Advice</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Advice</em>' containment reference.
   * @see #getAdvice()
   * @generated
   */
  void setAdvice(CompoundAdvice value);

} // RepositoryConfiguration
