/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang.impl;

import java.util.Collection;

import org.eclipse.b3.beeLang.BeeLangPackage;
import org.eclipse.b3.beeLang.BuildPart;
import org.eclipse.b3.beeLang.BuildUnit;
import org.eclipse.b3.beeLang.ExecutionMode;
import org.eclipse.b3.beeLang.NamedAdvice;
import org.eclipse.b3.beeLang.PropertyStatements;
import org.eclipse.b3.beeLang.ProvidedCapability;
import org.eclipse.b3.beeLang.RepositoryConfiguration;
import org.eclipse.b3.beeLang.RequiredCapability;
import org.eclipse.b3.beeLang.Synchronization;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Build Unit</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.impl.BuildUnitImpl#getExecutionMode <em>Execution Mode</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.BuildUnitImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.BuildUnitImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.BuildUnitImpl#getImplements <em>Implements</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.BuildUnitImpl#getPropertyStatements <em>Property Statements</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.BuildUnitImpl#getProvidedCapability <em>Provided Capability</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.BuildUnitImpl#getRequiredCapabilities <em>Required Capabilities</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.BuildUnitImpl#getMetaRequiredCapabilities <em>Meta Required Capabilities</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.BuildUnitImpl#getAdvice <em>Advice</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.BuildUnitImpl#getSynchronize <em>Synchronize</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.BuildUnitImpl#getParts <em>Parts</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.BuildUnitImpl#getRepositoryConfig <em>Repository Config</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BuildUnitImpl extends MinimalEObjectImpl.Container implements BuildUnit
{
  /**
   * The default value of the '{@link #getExecutionMode() <em>Execution Mode</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExecutionMode()
   * @generated
   * @ordered
   */
  protected static final ExecutionMode EXECUTION_MODE_EDEFAULT = ExecutionMode.PARALLEL;

  /**
   * The cached value of the '{@link #getExecutionMode() <em>Execution Mode</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExecutionMode()
   * @generated
   * @ordered
   */
  protected ExecutionMode executionMode = EXECUTION_MODE_EDEFAULT;

  /**
   * The default value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected static final String NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected String name = NAME_EDEFAULT;

  /**
   * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVersion()
   * @generated
   * @ordered
   */
  protected static final String VERSION_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVersion()
   * @generated
   * @ordered
   */
  protected String version = VERSION_EDEFAULT;

  /**
   * The cached value of the '{@link #getImplements() <em>Implements</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getImplements()
   * @generated
   * @ordered
   */
  protected EList<String> implements_;

  /**
   * The cached value of the '{@link #getPropertyStatements() <em>Property Statements</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPropertyStatements()
   * @generated
   * @ordered
   */
  protected EList<PropertyStatements> propertyStatements;

  /**
   * The cached value of the '{@link #getProvidedCapability() <em>Provided Capability</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getProvidedCapability()
   * @generated
   * @ordered
   */
  protected EList<ProvidedCapability> providedCapability;

  /**
   * The cached value of the '{@link #getRequiredCapabilities() <em>Required Capabilities</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRequiredCapabilities()
   * @generated
   * @ordered
   */
  protected EList<RequiredCapability> requiredCapabilities;

  /**
   * The cached value of the '{@link #getMetaRequiredCapabilities() <em>Meta Required Capabilities</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMetaRequiredCapabilities()
   * @generated
   * @ordered
   */
  protected EList<RequiredCapability> metaRequiredCapabilities;

  /**
   * The cached value of the '{@link #getAdvice() <em>Advice</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAdvice()
   * @generated
   * @ordered
   */
  protected EList<NamedAdvice> advice;

  /**
   * The cached value of the '{@link #getSynchronize() <em>Synchronize</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSynchronize()
   * @generated
   * @ordered
   */
  protected EList<Synchronization> synchronize;

  /**
   * The cached value of the '{@link #getParts() <em>Parts</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getParts()
   * @generated
   * @ordered
   */
  protected EList<BuildPart> parts;

  /**
   * The cached value of the '{@link #getRepositoryConfig() <em>Repository Config</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRepositoryConfig()
   * @generated
   * @ordered
   */
  protected EList<RepositoryConfiguration> repositoryConfig;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected BuildUnitImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return BeeLangPackage.Literals.BUILD_UNIT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ExecutionMode getExecutionMode()
  {
    return executionMode;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setExecutionMode(ExecutionMode newExecutionMode)
  {
    ExecutionMode oldExecutionMode = executionMode;
    executionMode = newExecutionMode == null ? EXECUTION_MODE_EDEFAULT : newExecutionMode;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.BUILD_UNIT__EXECUTION_MODE, oldExecutionMode, executionMode));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setName(String newName)
  {
    String oldName = name;
    name = newName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.BUILD_UNIT__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getVersion()
  {
    return version;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setVersion(String newVersion)
  {
    String oldVersion = version;
    version = newVersion;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.BUILD_UNIT__VERSION, oldVersion, version));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getImplements()
  {
    if (implements_ == null)
    {
      implements_ = new EDataTypeEList<String>(String.class, this, BeeLangPackage.BUILD_UNIT__IMPLEMENTS);
    }
    return implements_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<PropertyStatements> getPropertyStatements()
  {
    if (propertyStatements == null)
    {
      propertyStatements = new EObjectContainmentEList<PropertyStatements>(PropertyStatements.class, this, BeeLangPackage.BUILD_UNIT__PROPERTY_STATEMENTS);
    }
    return propertyStatements;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<ProvidedCapability> getProvidedCapability()
  {
    if (providedCapability == null)
    {
      providedCapability = new EObjectContainmentEList<ProvidedCapability>(ProvidedCapability.class, this, BeeLangPackage.BUILD_UNIT__PROVIDED_CAPABILITY);
    }
    return providedCapability;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<RequiredCapability> getRequiredCapabilities()
  {
    if (requiredCapabilities == null)
    {
      requiredCapabilities = new EObjectContainmentEList<RequiredCapability>(RequiredCapability.class, this, BeeLangPackage.BUILD_UNIT__REQUIRED_CAPABILITIES);
    }
    return requiredCapabilities;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<RequiredCapability> getMetaRequiredCapabilities()
  {
    if (metaRequiredCapabilities == null)
    {
      metaRequiredCapabilities = new EObjectContainmentEList<RequiredCapability>(RequiredCapability.class, this, BeeLangPackage.BUILD_UNIT__META_REQUIRED_CAPABILITIES);
    }
    return metaRequiredCapabilities;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<NamedAdvice> getAdvice()
  {
    if (advice == null)
    {
      advice = new EObjectContainmentEList<NamedAdvice>(NamedAdvice.class, this, BeeLangPackage.BUILD_UNIT__ADVICE);
    }
    return advice;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Synchronization> getSynchronize()
  {
    if (synchronize == null)
    {
      synchronize = new EObjectContainmentEList<Synchronization>(Synchronization.class, this, BeeLangPackage.BUILD_UNIT__SYNCHRONIZE);
    }
    return synchronize;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<BuildPart> getParts()
  {
    if (parts == null)
    {
      parts = new EObjectContainmentEList<BuildPart>(BuildPart.class, this, BeeLangPackage.BUILD_UNIT__PARTS);
    }
    return parts;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<RepositoryConfiguration> getRepositoryConfig()
  {
    if (repositoryConfig == null)
    {
      repositoryConfig = new EObjectContainmentEList<RepositoryConfiguration>(RepositoryConfiguration.class, this, BeeLangPackage.BUILD_UNIT__REPOSITORY_CONFIG);
    }
    return repositoryConfig;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case BeeLangPackage.BUILD_UNIT__PROPERTY_STATEMENTS:
        return ((InternalEList<?>)getPropertyStatements()).basicRemove(otherEnd, msgs);
      case BeeLangPackage.BUILD_UNIT__PROVIDED_CAPABILITY:
        return ((InternalEList<?>)getProvidedCapability()).basicRemove(otherEnd, msgs);
      case BeeLangPackage.BUILD_UNIT__REQUIRED_CAPABILITIES:
        return ((InternalEList<?>)getRequiredCapabilities()).basicRemove(otherEnd, msgs);
      case BeeLangPackage.BUILD_UNIT__META_REQUIRED_CAPABILITIES:
        return ((InternalEList<?>)getMetaRequiredCapabilities()).basicRemove(otherEnd, msgs);
      case BeeLangPackage.BUILD_UNIT__ADVICE:
        return ((InternalEList<?>)getAdvice()).basicRemove(otherEnd, msgs);
      case BeeLangPackage.BUILD_UNIT__SYNCHRONIZE:
        return ((InternalEList<?>)getSynchronize()).basicRemove(otherEnd, msgs);
      case BeeLangPackage.BUILD_UNIT__PARTS:
        return ((InternalEList<?>)getParts()).basicRemove(otherEnd, msgs);
      case BeeLangPackage.BUILD_UNIT__REPOSITORY_CONFIG:
        return ((InternalEList<?>)getRepositoryConfig()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case BeeLangPackage.BUILD_UNIT__EXECUTION_MODE:
        return getExecutionMode();
      case BeeLangPackage.BUILD_UNIT__NAME:
        return getName();
      case BeeLangPackage.BUILD_UNIT__VERSION:
        return getVersion();
      case BeeLangPackage.BUILD_UNIT__IMPLEMENTS:
        return getImplements();
      case BeeLangPackage.BUILD_UNIT__PROPERTY_STATEMENTS:
        return getPropertyStatements();
      case BeeLangPackage.BUILD_UNIT__PROVIDED_CAPABILITY:
        return getProvidedCapability();
      case BeeLangPackage.BUILD_UNIT__REQUIRED_CAPABILITIES:
        return getRequiredCapabilities();
      case BeeLangPackage.BUILD_UNIT__META_REQUIRED_CAPABILITIES:
        return getMetaRequiredCapabilities();
      case BeeLangPackage.BUILD_UNIT__ADVICE:
        return getAdvice();
      case BeeLangPackage.BUILD_UNIT__SYNCHRONIZE:
        return getSynchronize();
      case BeeLangPackage.BUILD_UNIT__PARTS:
        return getParts();
      case BeeLangPackage.BUILD_UNIT__REPOSITORY_CONFIG:
        return getRepositoryConfig();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case BeeLangPackage.BUILD_UNIT__EXECUTION_MODE:
        setExecutionMode((ExecutionMode)newValue);
        return;
      case BeeLangPackage.BUILD_UNIT__NAME:
        setName((String)newValue);
        return;
      case BeeLangPackage.BUILD_UNIT__VERSION:
        setVersion((String)newValue);
        return;
      case BeeLangPackage.BUILD_UNIT__IMPLEMENTS:
        getImplements().clear();
        getImplements().addAll((Collection<? extends String>)newValue);
        return;
      case BeeLangPackage.BUILD_UNIT__PROPERTY_STATEMENTS:
        getPropertyStatements().clear();
        getPropertyStatements().addAll((Collection<? extends PropertyStatements>)newValue);
        return;
      case BeeLangPackage.BUILD_UNIT__PROVIDED_CAPABILITY:
        getProvidedCapability().clear();
        getProvidedCapability().addAll((Collection<? extends ProvidedCapability>)newValue);
        return;
      case BeeLangPackage.BUILD_UNIT__REQUIRED_CAPABILITIES:
        getRequiredCapabilities().clear();
        getRequiredCapabilities().addAll((Collection<? extends RequiredCapability>)newValue);
        return;
      case BeeLangPackage.BUILD_UNIT__META_REQUIRED_CAPABILITIES:
        getMetaRequiredCapabilities().clear();
        getMetaRequiredCapabilities().addAll((Collection<? extends RequiredCapability>)newValue);
        return;
      case BeeLangPackage.BUILD_UNIT__ADVICE:
        getAdvice().clear();
        getAdvice().addAll((Collection<? extends NamedAdvice>)newValue);
        return;
      case BeeLangPackage.BUILD_UNIT__SYNCHRONIZE:
        getSynchronize().clear();
        getSynchronize().addAll((Collection<? extends Synchronization>)newValue);
        return;
      case BeeLangPackage.BUILD_UNIT__PARTS:
        getParts().clear();
        getParts().addAll((Collection<? extends BuildPart>)newValue);
        return;
      case BeeLangPackage.BUILD_UNIT__REPOSITORY_CONFIG:
        getRepositoryConfig().clear();
        getRepositoryConfig().addAll((Collection<? extends RepositoryConfiguration>)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case BeeLangPackage.BUILD_UNIT__EXECUTION_MODE:
        setExecutionMode(EXECUTION_MODE_EDEFAULT);
        return;
      case BeeLangPackage.BUILD_UNIT__NAME:
        setName(NAME_EDEFAULT);
        return;
      case BeeLangPackage.BUILD_UNIT__VERSION:
        setVersion(VERSION_EDEFAULT);
        return;
      case BeeLangPackage.BUILD_UNIT__IMPLEMENTS:
        getImplements().clear();
        return;
      case BeeLangPackage.BUILD_UNIT__PROPERTY_STATEMENTS:
        getPropertyStatements().clear();
        return;
      case BeeLangPackage.BUILD_UNIT__PROVIDED_CAPABILITY:
        getProvidedCapability().clear();
        return;
      case BeeLangPackage.BUILD_UNIT__REQUIRED_CAPABILITIES:
        getRequiredCapabilities().clear();
        return;
      case BeeLangPackage.BUILD_UNIT__META_REQUIRED_CAPABILITIES:
        getMetaRequiredCapabilities().clear();
        return;
      case BeeLangPackage.BUILD_UNIT__ADVICE:
        getAdvice().clear();
        return;
      case BeeLangPackage.BUILD_UNIT__SYNCHRONIZE:
        getSynchronize().clear();
        return;
      case BeeLangPackage.BUILD_UNIT__PARTS:
        getParts().clear();
        return;
      case BeeLangPackage.BUILD_UNIT__REPOSITORY_CONFIG:
        getRepositoryConfig().clear();
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case BeeLangPackage.BUILD_UNIT__EXECUTION_MODE:
        return executionMode != EXECUTION_MODE_EDEFAULT;
      case BeeLangPackage.BUILD_UNIT__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case BeeLangPackage.BUILD_UNIT__VERSION:
        return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
      case BeeLangPackage.BUILD_UNIT__IMPLEMENTS:
        return implements_ != null && !implements_.isEmpty();
      case BeeLangPackage.BUILD_UNIT__PROPERTY_STATEMENTS:
        return propertyStatements != null && !propertyStatements.isEmpty();
      case BeeLangPackage.BUILD_UNIT__PROVIDED_CAPABILITY:
        return providedCapability != null && !providedCapability.isEmpty();
      case BeeLangPackage.BUILD_UNIT__REQUIRED_CAPABILITIES:
        return requiredCapabilities != null && !requiredCapabilities.isEmpty();
      case BeeLangPackage.BUILD_UNIT__META_REQUIRED_CAPABILITIES:
        return metaRequiredCapabilities != null && !metaRequiredCapabilities.isEmpty();
      case BeeLangPackage.BUILD_UNIT__ADVICE:
        return advice != null && !advice.isEmpty();
      case BeeLangPackage.BUILD_UNIT__SYNCHRONIZE:
        return synchronize != null && !synchronize.isEmpty();
      case BeeLangPackage.BUILD_UNIT__PARTS:
        return parts != null && !parts.isEmpty();
      case BeeLangPackage.BUILD_UNIT__REPOSITORY_CONFIG:
        return repositoryConfig != null && !repositoryConfig.isEmpty();
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (executionMode: ");
    result.append(executionMode);
    result.append(", name: ");
    result.append(name);
    result.append(", version: ");
    result.append(version);
    result.append(", implements: ");
    result.append(implements_);
    result.append(')');
    return result.toString();
  }

} //BuildUnitImpl
