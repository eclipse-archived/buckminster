/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Import</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.Import#isReexport <em>Reexport</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.Import#getImporter <em>Importer</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.Import#getNameSpace <em>Name Space</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getImport()
 * @model
 * @generated
 */
public interface Import extends EObject
{
  /**
   * Returns the value of the '<em><b>Reexport</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Reexport</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Reexport</em>' attribute.
   * @see #setReexport(boolean)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getImport_Reexport()
   * @model
   * @generated
   */
  boolean isReexport();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.Import#isReexport <em>Reexport</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Reexport</em>' attribute.
   * @see #isReexport()
   * @generated
   */
  void setReexport(boolean value);

  /**
   * Returns the value of the '<em><b>Importer</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Importer</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Importer</em>' containment reference.
   * @see #setImporter(EObject)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getImport_Importer()
   * @model containment="true"
   * @generated
   */
  EObject getImporter();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.Import#getImporter <em>Importer</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Importer</em>' containment reference.
   * @see #getImporter()
   * @generated
   */
  void setImporter(EObject value);

  /**
   * Returns the value of the '<em><b>Name Space</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name Space</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name Space</em>' attribute.
   * @see #setNameSpace(String)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getImport_NameSpace()
   * @model
   * @generated
   */
  String getNameSpace();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.Import#getNameSpace <em>Name Space</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name Space</em>' attribute.
   * @see #getNameSpace()
   * @generated
   */
  void setNameSpace(String value);

} // Import
