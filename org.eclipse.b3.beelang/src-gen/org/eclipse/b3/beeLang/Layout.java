/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Layout</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.Layout#getPostCondition <em>Post Condition</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.Layout#getBody <em>Body</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getLayout()
 * @model
 * @generated
 */
public interface Layout extends EObject
{
  /**
   * Returns the value of the '<em><b>Post Condition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Post Condition</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Post Condition</em>' containment reference.
   * @see #setPostCondition(PostCondition)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getLayout_PostCondition()
   * @model containment="true"
   * @generated
   */
  PostCondition getPostCondition();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.Layout#getPostCondition <em>Post Condition</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Post Condition</em>' containment reference.
   * @see #getPostCondition()
   * @generated
   */
  void setPostCondition(PostCondition value);

  /**
   * Returns the value of the '<em><b>Body</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Body</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Body</em>' containment reference.
   * @see #setBody(PathGroup)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getLayout_Body()
   * @model containment="true"
   * @generated
   */
  PathGroup getBody();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.Layout#getBody <em>Body</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Body</em>' containment reference.
   * @see #getBody()
   * @generated
   */
  void setBody(PathGroup value);

} // Layout
