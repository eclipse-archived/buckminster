/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Repository Declaration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.RepositoryDeclaration#getLocation <em>Location</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.RepositoryDeclaration#getResolverClass <em>Resolver Class</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.RepositoryDeclaration#getContext <em>Context</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getRepositoryDeclaration()
 * @model
 * @generated
 */
public interface RepositoryDeclaration extends RepositoryConfiguration
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
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getRepositoryDeclaration_Location()
   * @model
   * @generated
   */
  String getLocation();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.RepositoryDeclaration#getLocation <em>Location</em>}' attribute.
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
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getRepositoryDeclaration_ResolverClass()
   * @model
   * @generated
   */
  String getResolverClass();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.RepositoryDeclaration#getResolverClass <em>Resolver Class</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Resolver Class</em>' attribute.
   * @see #getResolverClass()
   * @generated
   */
  void setResolverClass(String value);

  /**
   * Returns the value of the '<em><b>Context</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Context</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Context</em>' containment reference.
   * @see #setContext(ContextBlock)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getRepositoryDeclaration_Context()
   * @model containment="true"
   * @generated
   */
  ContextBlock getContext();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.RepositoryDeclaration#getContext <em>Context</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Context</em>' containment reference.
   * @see #getContext()
   * @generated
   */
  void setContext(ContextBlock value);

} // RepositoryDeclaration
