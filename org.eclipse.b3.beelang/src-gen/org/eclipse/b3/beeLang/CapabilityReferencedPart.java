/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Capability Referenced Part</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.CapabilityReferencedPart#getInterface <em>Interface</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.CapabilityReferencedPart#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.CapabilityReferencedPart#getRange <em>Range</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.CapabilityReferencedPart#getPartName <em>Part Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getCapabilityReferencedPart()
 * @model
 * @generated
 */
public interface CapabilityReferencedPart extends PrerequisiteEntry
{
  /**
   * Returns the value of the '<em><b>Interface</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Interface</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Interface</em>' attribute.
   * @see #setInterface(String)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getCapabilityReferencedPart_Interface()
   * @model
   * @generated
   */
  String getInterface();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.CapabilityReferencedPart#getInterface <em>Interface</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Interface</em>' attribute.
   * @see #getInterface()
   * @generated
   */
  void setInterface(String value);

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
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getCapabilityReferencedPart_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.CapabilityReferencedPart#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Range</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Range</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Range</em>' attribute.
   * @see #setRange(String)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getCapabilityReferencedPart_Range()
   * @model
   * @generated
   */
  String getRange();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.CapabilityReferencedPart#getRange <em>Range</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Range</em>' attribute.
   * @see #getRange()
   * @generated
   */
  void setRange(String value);

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
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getCapabilityReferencedPart_PartName()
   * @model
   * @generated
   */
  String getPartName();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.CapabilityReferencedPart#getPartName <em>Part Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Part Name</em>' attribute.
   * @see #getPartName()
   * @generated
   */
  void setPartName(String value);

} // CapabilityReferencedPart
