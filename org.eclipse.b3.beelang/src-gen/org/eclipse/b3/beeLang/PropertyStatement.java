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
 * A representation of the model object '<em><b>Property Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.PropertyStatement#getFilter <em>Filter</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.PropertyStatement#getStatements <em>Statements</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.PropertyStatement#isImmutable <em>Immutable</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.PropertyStatement#getKey <em>Key</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.PropertyStatement#getOp <em>Op</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.PropertyStatement#getValue <em>Value</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.PropertyStatement#getUnsetProperties <em>Unset Properties</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getPropertyStatement()
 * @model
 * @generated
 */
public interface PropertyStatement extends EObject
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
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getPropertyStatement_Filter()
   * @model containment="true"
   * @generated
   */
  Filter getFilter();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.PropertyStatement#getFilter <em>Filter</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Filter</em>' containment reference.
   * @see #getFilter()
   * @generated
   */
  void setFilter(Filter value);

  /**
   * Returns the value of the '<em><b>Statements</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.b3.beeLang.PropertyStatement}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Statements</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Statements</em>' containment reference list.
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getPropertyStatement_Statements()
   * @model containment="true"
   * @generated
   */
  EList<PropertyStatement> getStatements();

  /**
   * Returns the value of the '<em><b>Immutable</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Immutable</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Immutable</em>' attribute.
   * @see #setImmutable(boolean)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getPropertyStatement_Immutable()
   * @model
   * @generated
   */
  boolean isImmutable();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.PropertyStatement#isImmutable <em>Immutable</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Immutable</em>' attribute.
   * @see #isImmutable()
   * @generated
   */
  void setImmutable(boolean value);

  /**
   * Returns the value of the '<em><b>Key</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Key</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Key</em>' attribute.
   * @see #setKey(String)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getPropertyStatement_Key()
   * @model
   * @generated
   */
  String getKey();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.PropertyStatement#getKey <em>Key</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Key</em>' attribute.
   * @see #getKey()
   * @generated
   */
  void setKey(String value);

  /**
   * Returns the value of the '<em><b>Op</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Op</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Op</em>' attribute.
   * @see #setOp(String)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getPropertyStatement_Op()
   * @model
   * @generated
   */
  String getOp();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.PropertyStatement#getOp <em>Op</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Op</em>' attribute.
   * @see #getOp()
   * @generated
   */
  void setOp(String value);

  /**
   * Returns the value of the '<em><b>Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Value</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Value</em>' containment reference.
   * @see #setValue(PropertyExpression)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getPropertyStatement_Value()
   * @model containment="true"
   * @generated
   */
  PropertyExpression getValue();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.PropertyStatement#getValue <em>Value</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Value</em>' containment reference.
   * @see #getValue()
   * @generated
   */
  void setValue(PropertyExpression value);

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
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getPropertyStatement_UnsetProperties()
   * @model unique="false"
   * @generated
   */
  EList<String> getUnsetProperties();

} // PropertyStatement
