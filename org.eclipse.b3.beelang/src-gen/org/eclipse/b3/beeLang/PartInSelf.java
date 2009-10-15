/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Part In Self</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.PartInSelf#getPartName <em>Part Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getPartInSelf()
 * @model
 * @generated
 */
public interface PartInSelf extends PrerequisiteEntry
{
  /**
   * Returns the value of the '<em><b>Part Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Part Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Part Name</em>' attribute.
   * @see #setPartName(String)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getPartInSelf_PartName()
   * @model
   * @generated
   */
  String getPartName();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.PartInSelf#getPartName <em>Part Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Part Name</em>' attribute.
   * @see #getPartName()
   * @generated
   */
  void setPartName(String value);

} // PartInSelf
