/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Artifacts Part</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.ArtifactsPart#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.ArtifactsPart#getAsserts <em>Asserts</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.ArtifactsPart#getPaths <em>Paths</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getArtifactsPart()
 * @model
 * @generated
 */
public interface ArtifactsPart extends BuildPart
{
  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getArtifactsPart_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.ArtifactsPart#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Asserts</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.b3.beeLang.PostConditionAssert}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Asserts</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Asserts</em>' containment reference list.
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getArtifactsPart_Asserts()
   * @model containment="true"
   * @generated
   */
  EList<PostConditionAssert> getAsserts();

  /**
   * Returns the value of the '<em><b>Paths</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.b3.beeLang.PathGroup}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Paths</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Paths</em>' containment reference list.
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getArtifactsPart_Paths()
   * @model containment="true"
   * @generated
   */
  EList<PathGroup> getPaths();

} // ArtifactsPart
