/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Property Set</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.PropertySet#getExtends <em>Extends</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.PropertySet#getOperations <em>Operations</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getPropertySet()
 * @model
 * @generated
 */
public interface PropertySet extends PropertyOperation
{
  /**
   * Returns the value of the '<em><b>Extends</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Extends</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Extends</em>' reference.
   * @see #setExtends(NamedPropertySet)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getPropertySet_Extends()
   * @model
   * @generated
   */
  NamedPropertySet getExtends();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.PropertySet#getExtends <em>Extends</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Extends</em>' reference.
   * @see #getExtends()
   * @generated
   */
  void setExtends(NamedPropertySet value);

  /**
   * Returns the value of the '<em><b>Operations</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.b3.beeLang.PropertyOperation}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Operations</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Operations</em>' containment reference list.
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getPropertySet_Operations()
   * @model containment="true"
   * @generated
   */
  EList<PropertyOperation> getOperations();

} // PropertySet
