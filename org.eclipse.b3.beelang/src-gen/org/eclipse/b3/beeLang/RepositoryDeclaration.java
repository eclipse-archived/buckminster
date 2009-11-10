/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang;

import java.net.URI;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Repository Declaration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.RepositoryDeclaration#getDocumentation <em>Documentation</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.RepositoryDeclaration#getLocation <em>Location</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.RepositoryDeclaration#getType <em>Type</em>}</li>
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
   * Returns the value of the '<em><b>Documentation</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Documentation</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Documentation</em>' attribute.
   * @see #setDocumentation(String)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getRepositoryDeclaration_Documentation()
   * @model
   * @generated
   */
  String getDocumentation();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.RepositoryDeclaration#getDocumentation <em>Documentation</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Documentation</em>' attribute.
   * @see #getDocumentation()
   * @generated
   */
  void setDocumentation(String value);

  /**
   * Returns the value of the '<em><b>Location</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Location</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Location</em>' attribute.
   * @see #setLocation(URI)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getRepositoryDeclaration_Location()
   * @model dataType="beelangtypes.URI"
   * @generated
   */
  URI getLocation();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.RepositoryDeclaration#getLocation <em>Location</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Location</em>' attribute.
   * @see #getLocation()
   * @generated
   */
  void setLocation(URI value);

  /**
   * Returns the value of the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Type</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type</em>' containment reference.
   * @see #setType(TypeRef)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getRepositoryDeclaration_Type()
   * @model containment="true"
   * @generated
   */
  TypeRef getType();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.RepositoryDeclaration#getType <em>Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type</em>' containment reference.
   * @see #getType()
   * @generated
   */
  void setType(TypeRef value);

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
