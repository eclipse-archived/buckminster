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
 * A representation of the model object '<em><b>Path Group</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.PathGroup#getPaths <em>Paths</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.PathGroup#getAnnotations <em>Annotations</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getPathGroup()
 * @model
 * @generated
 */
public interface PathGroup extends EObject
{
  /**
   * Returns the value of the '<em><b>Paths</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.b3.beeLang.PathVectorElement}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Paths</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Paths</em>' containment reference list.
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getPathGroup_Paths()
   * @model containment="true"
   * @generated
   */
  EList<PathVectorElement> getPaths();

  /**
   * Returns the value of the '<em><b>Annotations</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Annotations</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Annotations</em>' containment reference.
   * @see #setAnnotations(PropertySet)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getPathGroup_Annotations()
   * @model containment="true"
   * @generated
   */
  PropertySet getAnnotations();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.PathGroup#getAnnotations <em>Annotations</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Annotations</em>' containment reference.
   * @see #getAnnotations()
   * @generated
   */
  void setAnnotations(PropertySet value);

} // PathGroup
