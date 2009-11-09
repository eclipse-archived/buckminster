/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Direct Part Referemce</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.DirectPartReferemce#getUnit <em>Unit</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.DirectPartReferemce#getBuilder <em>Builder</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.DirectPartReferemce#getParameters <em>Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getDirectPartReferemce()
 * @model
 * @generated
 */
public interface DirectPartReferemce extends PrerequisiteEntry
{
  /**
   * Returns the value of the '<em><b>Unit</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Unit</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Unit</em>' attribute.
   * @see #setUnit(String)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getDirectPartReferemce_Unit()
   * @model
   * @generated
   */
  String getUnit();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.DirectPartReferemce#getUnit <em>Unit</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Unit</em>' attribute.
   * @see #getUnit()
   * @generated
   */
  void setUnit(String value);

  /**
   * Returns the value of the '<em><b>Builder</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Builder</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Builder</em>' attribute.
   * @see #setBuilder(String)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getDirectPartReferemce_Builder()
   * @model
   * @generated
   */
  String getBuilder();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.DirectPartReferemce#getBuilder <em>Builder</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Builder</em>' attribute.
   * @see #getBuilder()
   * @generated
   */
  void setBuilder(String value);

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
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getDirectPartReferemce_Parameters()
   * @model containment="true"
   * @generated
   */
  ParameterList getParameters();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.DirectPartReferemce#getParameters <em>Parameters</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Parameters</em>' containment reference.
   * @see #getParameters()
   * @generated
   */
  void setParameters(ParameterList value);

} // DirectPartReferemce
