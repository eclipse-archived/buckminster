/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Catch Block</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.CatchBlock#getVariable <em>Variable</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.CatchBlock#getCatchBlock <em>Catch Block</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getCatchBlock()
 * @model
 * @generated
 */
public interface CatchBlock extends EObject
{
  /**
   * Returns the value of the '<em><b>Variable</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Variable</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Variable</em>' attribute.
   * @see #setVariable(String)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getCatchBlock_Variable()
   * @model
   * @generated
   */
  String getVariable();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.CatchBlock#getVariable <em>Variable</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Variable</em>' attribute.
   * @see #getVariable()
   * @generated
   */
  void setVariable(String value);

  /**
   * Returns the value of the '<em><b>Catch Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Catch Block</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Catch Block</em>' containment reference.
   * @see #setCatchBlock(CompoundStatement)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getCatchBlock_CatchBlock()
   * @model containment="true"
   * @generated
   */
  CompoundStatement getCatchBlock();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.CatchBlock#getCatchBlock <em>Catch Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Catch Block</em>' containment reference.
   * @see #getCatchBlock()
   * @generated
   */
  void setCatchBlock(CompoundStatement value);

} // CatchBlock
