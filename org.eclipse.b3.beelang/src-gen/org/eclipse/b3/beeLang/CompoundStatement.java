/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Compound Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.CompoundStatement#getStatements <em>Statements</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getCompoundStatement()
 * @model
 * @generated
 */
public interface CompoundStatement extends Statement
{
  /**
   * Returns the value of the '<em><b>Statements</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Statements</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Statements</em>' containment reference.
   * @see #setStatements(Statements)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getCompoundStatement_Statements()
   * @model containment="true"
   * @generated
   */
  Statements getStatements();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.CompoundStatement#getStatements <em>Statements</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Statements</em>' containment reference.
   * @see #getStatements()
   * @generated
   */
  void setStatements(Statements value);

} // CompoundStatement
