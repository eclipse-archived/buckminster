/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Prerequisite</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.Prerequisite#getFilter <em>Filter</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.Prerequisite#getWithClause <em>With Clause</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.Prerequisite#getPartReference <em>Part Reference</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.Prerequisite#getAlias <em>Alias</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getPrerequisite()
 * @model
 * @generated
 */
public interface Prerequisite extends EObject
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
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getPrerequisite_Filter()
   * @model containment="true"
   * @generated
   */
  Filter getFilter();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.Prerequisite#getFilter <em>Filter</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Filter</em>' containment reference.
   * @see #getFilter()
   * @generated
   */
  void setFilter(Filter value);

  /**
   * Returns the value of the '<em><b>With Clause</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>With Clause</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>With Clause</em>' containment reference.
   * @see #setWithClause(WithClause)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getPrerequisite_WithClause()
   * @model containment="true"
   * @generated
   */
  WithClause getWithClause();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.Prerequisite#getWithClause <em>With Clause</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>With Clause</em>' containment reference.
   * @see #getWithClause()
   * @generated
   */
  void setWithClause(WithClause value);

  /**
   * Returns the value of the '<em><b>Part Reference</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Part Reference</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Part Reference</em>' containment reference.
   * @see #setPartReference(PrerequisiteEntry)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getPrerequisite_PartReference()
   * @model containment="true"
   * @generated
   */
  PrerequisiteEntry getPartReference();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.Prerequisite#getPartReference <em>Part Reference</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Part Reference</em>' containment reference.
   * @see #getPartReference()
   * @generated
   */
  void setPartReference(PrerequisiteEntry value);

  /**
   * Returns the value of the '<em><b>Alias</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Alias</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Alias</em>' attribute.
   * @see #setAlias(String)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getPrerequisite_Alias()
   * @model
   * @generated
   */
  String getAlias();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.Prerequisite#getAlias <em>Alias</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Alias</em>' attribute.
   * @see #getAlias()
   * @generated
   */
  void setAlias(String value);

} // Prerequisite
