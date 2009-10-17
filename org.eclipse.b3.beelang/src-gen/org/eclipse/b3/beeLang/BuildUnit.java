/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Build Unit</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.BuildUnit#getExecutionMode <em>Execution Mode</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.BuildUnit#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.BuildUnit#getVersion <em>Version</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.BuildUnit#getImplements <em>Implements</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.BuildUnit#getPropertyStatements <em>Property Statements</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.BuildUnit#getProvidedCapability <em>Provided Capability</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.BuildUnit#getRequiredCapabilities <em>Required Capabilities</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.BuildUnit#getMetaRequiredCapabilities <em>Meta Required Capabilities</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.BuildUnit#getAdvice <em>Advice</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.BuildUnit#getSynchronize <em>Synchronize</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.BuildUnit#getParts <em>Parts</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.BuildUnit#getRepositoryConfig <em>Repository Config</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.b3.beeLang.BeeLangPackage#getBuildUnit()
 * @model
 * @generated
 */
public interface BuildUnit extends EObject
{
  /**
   * Returns the value of the '<em><b>Execution Mode</b></em>' attribute.
   * The literals are from the enumeration {@link org.eclipse.b3.beeLang.ExecutionMode}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Execution Mode</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Execution Mode</em>' attribute.
   * @see org.eclipse.b3.beeLang.ExecutionMode
   * @see #setExecutionMode(ExecutionMode)
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getBuildUnit_ExecutionMode()
   * @model
   * @generated
   */
  ExecutionMode getExecutionMode();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.BuildUnit#getExecutionMode <em>Execution Mode</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Execution Mode</em>' attribute.
   * @see org.eclipse.b3.beeLang.ExecutionMode
   * @see #getExecutionMode()
   * @generated
   */
  void setExecutionMode(ExecutionMode value);

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
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getBuildUnit_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.BuildUnit#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

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
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getBuildUnit_Version()
   * @model
   * @generated
   */
  String getVersion();

  /**
   * Sets the value of the '{@link org.eclipse.b3.beeLang.BuildUnit#getVersion <em>Version</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Version</em>' attribute.
   * @see #getVersion()
   * @generated
   */
  void setVersion(String value);

  /**
   * Returns the value of the '<em><b>Implements</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Implements</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Implements</em>' attribute list.
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getBuildUnit_Implements()
   * @model unique="false"
   * @generated
   */
  EList<String> getImplements();

  /**
   * Returns the value of the '<em><b>Property Statements</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.b3.beeLang.PropertyStatements}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Property Statements</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Property Statements</em>' containment reference list.
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getBuildUnit_PropertyStatements()
   * @model containment="true"
   * @generated
   */
  EList<PropertyStatements> getPropertyStatements();

  /**
   * Returns the value of the '<em><b>Provided Capability</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.b3.beeLang.ProvidedCapability}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Provided Capability</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Provided Capability</em>' containment reference list.
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getBuildUnit_ProvidedCapability()
   * @model containment="true"
   * @generated
   */
  EList<ProvidedCapability> getProvidedCapability();

  /**
   * Returns the value of the '<em><b>Required Capabilities</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.b3.beeLang.RequiredCapability}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Required Capabilities</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Required Capabilities</em>' containment reference list.
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getBuildUnit_RequiredCapabilities()
   * @model containment="true"
   * @generated
   */
  EList<RequiredCapability> getRequiredCapabilities();

  /**
   * Returns the value of the '<em><b>Meta Required Capabilities</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.b3.beeLang.RequiredCapability}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Meta Required Capabilities</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Meta Required Capabilities</em>' containment reference list.
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getBuildUnit_MetaRequiredCapabilities()
   * @model containment="true"
   * @generated
   */
  EList<RequiredCapability> getMetaRequiredCapabilities();

  /**
   * Returns the value of the '<em><b>Advice</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.b3.beeLang.NamedAdvice}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Advice</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Advice</em>' containment reference list.
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getBuildUnit_Advice()
   * @model containment="true"
   * @generated
   */
  EList<NamedAdvice> getAdvice();

  /**
   * Returns the value of the '<em><b>Synchronize</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.b3.beeLang.Synchronization}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Synchronize</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Synchronize</em>' containment reference list.
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getBuildUnit_Synchronize()
   * @model containment="true"
   * @generated
   */
  EList<Synchronization> getSynchronize();

  /**
   * Returns the value of the '<em><b>Parts</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.b3.beeLang.BuildPart}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Parts</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Parts</em>' containment reference list.
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getBuildUnit_Parts()
   * @model containment="true"
   * @generated
   */
  EList<BuildPart> getParts();

  /**
   * Returns the value of the '<em><b>Repository Config</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.b3.beeLang.RepositoryConfiguration}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Repository Config</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Repository Config</em>' containment reference list.
   * @see org.eclipse.b3.beeLang.BeeLangPackage#getBuildUnit_RepositoryConfig()
   * @model containment="true"
   * @generated
   */
  EList<RepositoryConfiguration> getRepositoryConfig();

} // BuildUnit
