/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Installation Unit</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.buckminster.aggregator.InstallationUnit#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.buckminster.aggregator.InstallationUnit#getVersion <em>Version</em>}</li>
 *   <li>{@link org.eclipse.buckminster.aggregator.InstallationUnit#getRepository <em>Repository</em>}</li>
 *   <li>{@link org.eclipse.buckminster.aggregator.InstallationUnit#isMapped <em>Mapped</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getInstallationUnit()
 * @model abstract="true"
 * @generated
 */
public interface InstallationUnit extends EObject
{
  /**
   * Returns the value of the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Id</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Id</em>' attribute.
   * @see #setId(String)
   * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getInstallationUnit_Id()
   * @model required="true"
   * @generated
   */
  String getId();

  /**
   * Sets the value of the '{@link org.eclipse.buckminster.aggregator.InstallationUnit#getId <em>Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Id</em>' attribute.
   * @see #getId()
   * @generated
   */
  void setId(String value);

  /**
   * Returns the value of the '<em><b>Version</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Version</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Version</em>' attribute.
   * @see #setVersion(String)
   * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getInstallationUnit_Version()
   * @model
   * @generated
   */
  String getVersion();

  /**
   * Sets the value of the '{@link org.eclipse.buckminster.aggregator.InstallationUnit#getVersion <em>Version</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Version</em>' attribute.
   * @see #getVersion()
   * @generated
   */
  void setVersion(String value);

  /**
   * Returns the value of the '<em><b>Repository</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Repository</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Repository</em>' reference.
   * @see #setRepository(Repository)
   * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getInstallationUnit_Repository()
   * @model
   * @generated
   */
  Repository getRepository();

  /**
   * Sets the value of the '{@link org.eclipse.buckminster.aggregator.InstallationUnit#getRepository <em>Repository</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Repository</em>' reference.
   * @see #getRepository()
   * @generated
   */
  void setRepository(Repository value);

  /**
   * Returns the value of the '<em><b>Mapped</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Mapped</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Mapped</em>' attribute.
   * @see #setMapped(boolean)
   * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getInstallationUnit_Mapped()
   * @model
   * @generated
   */
  boolean isMapped();

  /**
   * Sets the value of the '{@link org.eclipse.buckminster.aggregator.InstallationUnit#isMapped <em>Mapped</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Mapped</em>' attribute.
   * @see #isMapped()
   * @generated
   */
  void setMapped(boolean value);

} // InstallationUnit
