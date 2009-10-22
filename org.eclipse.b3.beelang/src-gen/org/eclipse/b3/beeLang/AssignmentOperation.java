/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Assignment Operation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.AssignmentOperation#getLeft <em>Left</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.AssignmentOperation#getOp <em>Op</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.AssignmentOperation#getRight <em>Right</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getAssignmentOperation()
 * @model
 * @generated
 */
public interface AssignmentOperation extends Expression
{
  /**
   * Returns the value of the '<em><b>Left</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.b3.beeLang.Expression}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Left</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Left</em>' containment reference list.
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getAssignmentOperation_Left()
   * @model containment="true"
   * @generated
   */
  EList<Expression> getLeft();

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
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getAssignmentOperation_Op()
   * @model
   * @generated
   */
  String getOp();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.AssignmentOperation#getOp <em>Op</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Op</em>' attribute.
   * @see #getOp()
   * @generated
   */
  void setOp(String value);

  /**
   * Returns the value of the '<em><b>Right</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.b3.beeLang.Expression}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Right</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Right</em>' containment reference list.
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getAssignmentOperation_Right()
   * @model containment="true"
   * @generated
   */
  EList<Expression> getRight();

} // AssignmentOperation
