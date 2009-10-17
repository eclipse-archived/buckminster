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
 * A representation of the model object '<em><b>Operation Call</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.OperationCall#getParams <em>Params</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.OperationCall#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.OperationCall#getParamsParameters <em>Params Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getOperationCall()
 * @model
 * @generated
 */
public interface OperationCall extends Expression, FeatureCall
{
  /**
   * Returns the value of the '<em><b>Params</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Params</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Params</em>' containment reference list.
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getOperationCall_Params()
   * @model containment="true"
   * @generated
   */
  EList<EObject> getParams();

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
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getOperationCall_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.OperationCall#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Params Parameters</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.b3.beeLang.AnonymousParameter}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Params Parameters</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Params Parameters</em>' containment reference list.
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getOperationCall_ParamsParameters()
   * @model containment="true"
   * @generated
   */
  EList<AnonymousParameter> getParamsParameters();

} // OperationCall
