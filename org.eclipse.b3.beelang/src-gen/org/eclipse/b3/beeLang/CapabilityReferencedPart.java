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
 *   <li>{@link org.eclipse.b3.beeLang.CapabilityReferencedPart#getCapability <em>Capability</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.CapabilityReferencedPart#getRange <em>Range</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.CapabilityReferencedPart#getPartName <em>Part Name</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.CapabilityReferencedPart#getParameters <em>Parameters</em>}</li>
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
   * Returns the value of the '<em><b>Capability</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Capability</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Capability</em>' containment reference.
   * @see #setCapability(Capability)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getCapabilityReferencedPart_Capability()
   * @model containment="true"
   * @generated
   */
  Capability getCapability();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.CapabilityReferencedPart#getCapability <em>Capability</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Capability</em>' containment reference.
   * @see #getCapability()
   * @generated
   */
  void setCapability(Capability value);

  /**
   * Returns the value of the '<em><b>Range</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Range</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Range</em>' containment reference.
   * @see #setRange(VersionRange)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getCapabilityReferencedPart_Range()
   * @model containment="true"
   * @generated
   */
  VersionRange getRange();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.CapabilityReferencedPart#getRange <em>Range</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Range</em>' containment reference.
   * @see #getRange()
   * @generated
   */
  void setRange(VersionRange value);

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

  /**
   * Returns the value of the '<em><b>Parameters</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Parameters</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Parameters</em>' containment reference.
   * @see #setParameters(ParameterList)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getCapabilityReferencedPart_Parameters()
   * @model containment="true"
   * @generated
   */
  ParameterList getParameters();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.CapabilityReferencedPart#getParameters <em>Parameters</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Parameters</em>' containment reference.
   * @see #getParameters()
   * @generated
   */
  void setParameters(ParameterList value);

} // CapabilityReferencedPart
