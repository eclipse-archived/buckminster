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
 * A representation of the model object '<em><b>With Clause</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.WithClause#getReferences <em>References</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.WithClause#getProperties <em>Properties</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.WithClause#getConcern <em>Concern</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getWithClause()
 * @model
 * @generated
 */
public interface WithClause extends EObject
{
  /**
   * Returns the value of the '<em><b>References</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.b3.beeLang.Expression}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>References</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>References</em>' containment reference list.
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getWithClause_References()
   * @model containment="true"
   * @generated
   */
  EList<Expression> getReferences();

  /**
   * Returns the value of the '<em><b>Properties</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.b3.beeLang.PropertySet}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Properties</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Properties</em>' containment reference list.
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getWithClause_Properties()
   * @model containment="true"
   * @generated
   */
  EList<PropertySet> getProperties();

  /**
   * Returns the value of the '<em><b>Concern</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.b3.beeLang.ConcernBlock}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Concern</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Concern</em>' containment reference list.
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getWithClause_Concern()
   * @model containment="true"
   * @generated
   */
  EList<ConcernBlock> getConcern();

} // WithClause
