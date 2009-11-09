/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang;

import org.eclipse.b3.RegularExpression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Unit Selector</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.UnitSelector#getInterface <em>Interface</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.UnitSelector#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.UnitSelector#getNamePattern <em>Name Pattern</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.UnitSelector#getVersionRange <em>Version Range</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getUnitSelector()
 * @model
 * @generated
 */
public interface UnitSelector extends ContextSelector
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
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getUnitSelector_Interface()
   * @model
   * @generated
   */
  String getInterface();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.UnitSelector#getInterface <em>Interface</em>}' attribute.
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
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getUnitSelector_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.UnitSelector#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Name Pattern</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name Pattern</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name Pattern</em>' attribute.
   * @see #setNamePattern(RegularExpression)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getUnitSelector_NamePattern()
   * @model dataType="beelangtypes.RegularExpression"
   * @generated
   */
  RegularExpression getNamePattern();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.UnitSelector#getNamePattern <em>Name Pattern</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name Pattern</em>' attribute.
   * @see #getNamePattern()
   * @generated
   */
  void setNamePattern(RegularExpression value);

  /**
   * Returns the value of the '<em><b>Version Range</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Version Range</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Version Range</em>' containment reference.
   * @see #setVersionRange(VersionRange)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getUnitSelector_VersionRange()
   * @model containment="true"
   * @generated
   */
  VersionRange getVersionRange();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.UnitSelector#getVersionRange <em>Version Range</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Version Range</em>' containment reference.
   * @see #getVersionRange()
   * @generated
   */
  void setVersionRange(VersionRange value);

} // UnitSelector
