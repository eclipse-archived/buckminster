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
 *   <li>{@link org.eclipse.b3.beeLang.PathGroup#getFilter <em>Filter</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.PathGroup#getPaths <em>Paths</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.PathGroup#getBasePath <em>Base Path</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.PathGroup#getUnsetProperties <em>Unset Properties</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.PathGroup#getSetProperties <em>Set Properties</em>}</li>
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
   * Returns the value of the '<em><b>Filter</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Filter</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Filter</em>' containment reference.
   * @see #setFilter(Filter)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getPathGroup_Filter()
   * @model containment="true"
   * @generated
   */
  Filter getFilter();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.PathGroup#getFilter <em>Filter</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Filter</em>' containment reference.
   * @see #getFilter()
   * @generated
   */
  void setFilter(Filter value);

  /**
   * Returns the value of the '<em><b>Paths</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Paths</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Paths</em>' attribute list.
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getPathGroup_Paths()
   * @model unique="false"
   * @generated
   */
  EList<String> getPaths();

  /**
   * Returns the value of the '<em><b>Base Path</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Base Path</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Base Path</em>' attribute.
   * @see #setBasePath(String)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getPathGroup_BasePath()
   * @model
   * @generated
   */
  String getBasePath();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.PathGroup#getBasePath <em>Base Path</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Base Path</em>' attribute.
   * @see #getBasePath()
   * @generated
   */
  void setBasePath(String value);

  /**
   * Returns the value of the '<em><b>Unset Properties</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Unset Properties</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Unset Properties</em>' attribute list.
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getPathGroup_UnsetProperties()
   * @model unique="false"
   * @generated
   */
  EList<String> getUnsetProperties();

  /**
   * Returns the value of the '<em><b>Set Properties</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.b3.beeLang.StringProperty}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Set Properties</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Set Properties</em>' containment reference list.
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getPathGroup_SetProperties()
   * @model containment="true"
   * @generated
   */
  EList<StringProperty> getSetProperties();

} // PathGroup
