/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Finally Block</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.FinallyBlock#getFinallyBlock <em>Finally Block</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getFinallyBlock()
 * @model
 * @generated
 */
public interface FinallyBlock extends EObject
{
  /**
   * Returns the value of the '<em><b>Finally Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Finally Block</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Finally Block</em>' containment reference.
   * @see #setFinallyBlock(CompoundStatement)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getFinallyBlock_FinallyBlock()
   * @model containment="true"
   * @generated
   */
  CompoundStatement getFinallyBlock();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.FinallyBlock#getFinallyBlock <em>Finally Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Finally Block</em>' containment reference.
   * @see #getFinallyBlock()
   * @generated
   */
  void setFinallyBlock(CompoundStatement value);

} // FinallyBlock
