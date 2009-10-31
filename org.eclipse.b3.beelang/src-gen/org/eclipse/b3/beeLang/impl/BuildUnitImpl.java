/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang.impl;

import java.util.Collection;

import org.eclipse.b3.beeLang.BeeLangPackage;
import org.eclipse.b3.beeLang.BuildMethod;
import org.eclipse.b3.beeLang.BuildUnit;
import org.eclipse.b3.beeLang.ExecutionMode;
import org.eclipse.b3.beeLang.NamedAdvice;
import org.eclipse.b3.beeLang.NamedProperties;
import org.eclipse.b3.beeLang.ProvidedCapability;
import org.eclipse.b3.beeLang.RepositoryConfiguration;
import org.eclipse.b3.beeLang.RequiredCapability;
import org.eclipse.b3.beeLang.Synchronization;
import org.eclipse.b3.beeLang.Version;

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
 *   <li>{@link org.eclipse.b3.beeLang.impl.BuildUnitImpl#getCompoundPropertyOperation <em>Compound Property Operation</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.BuildUnitImpl#getProvidedCapabilities <em>Provided Capabilities</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.BuildUnitImpl#getRequiredCapabilities <em>Required Capabilities</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.BuildUnitImpl#getMetaRequiredCapabilities <em>Meta Required Capabilities</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.BuildUnitImpl#getAdvice <em>Advice</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.BuildUnitImpl#getSynchronizations <em>Synchronizations</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.BuildUnitImpl#getBuildMethods <em>Build Methods</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.BuildUnitImpl#getRepositoryConfigurations <em>Repository Configurations</em>}</li>
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
   * The cached value of the '{@link #getVersion() <em>Version</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVersion()
   * @generated
   * @ordered
   */
  protected Version version;

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
   * The cached value of the '{@link #getCompoundPropertyOperation() <em>Compound Property Operation</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCompoundPropertyOperation()
   * @generated
   * @ordered
   */
  protected EList<NamedProperties> compoundPropertyOperation;

  /**
   * The cached value of the '{@link #getProvidedCapabilities() <em>Provided Capabilities</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getProvidedCapabilities()
   * @generated
   * @ordered
   */
  protected EList<ProvidedCapability> providedCapabilities;

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
   * The cached value of the '{@link #getSynchronizations() <em>Synchronizations</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSynchronizations()
   * @generated
   * @ordered
   */
  protected EList<Synchronization> synchronizations;

  /**
   * The cached value of the '{@link #getBuildMethods() <em>Build Methods</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBuildMethods()
   * @generated
   * @ordered
   */
  protected EList<BuildMethod> buildMethods;

  /**
   * The cached value of the '{@link #getRepositoryConfigurations() <em>Repository Configurations</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRepositoryConfigurations()
   * @generated
   * @ordered
   */
  protected EList<RepositoryConfiguration> repositoryConfigurations;

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
  public Version getVersion()
  {
    return version;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetVersion(Version newVersion, NotificationChain msgs)
  {
    Version oldVersion = version;
    version = newVersion;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.BUILD_UNIT__VERSION, oldVersion, newVersion);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setVersion(Version newVersion)
  {
    if (newVersion != version)
    {
      NotificationChain msgs = null;
      if (version != null)
        msgs = ((InternalEObject)version).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.BUILD_UNIT__VERSION, null, msgs);
      if (newVersion != null)
        msgs = ((InternalEObject)newVersion).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.BUILD_UNIT__VERSION, null, msgs);
      msgs = basicSetVersion(newVersion, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.BUILD_UNIT__VERSION, newVersion, newVersion));
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
  public EList<NamedProperties> getCompoundPropertyOperation()
  {
    if (compoundPropertyOperation == null)
    {
      compoundPropertyOperation = new EObjectContainmentEList<NamedProperties>(NamedProperties.class, this, BeeLangPackage.BUILD_UNIT__COMPOUND_PROPERTY_OPERATION);
    }
    return compoundPropertyOperation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<ProvidedCapability> getProvidedCapabilities()
  {
    if (providedCapabilities == null)
    {
      providedCapabilities = new EObjectContainmentEList<ProvidedCapability>(ProvidedCapability.class, this, BeeLangPackage.BUILD_UNIT__PROVIDED_CAPABILITIES);
    }
    return providedCapabilities;
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
  public EList<Synchronization> getSynchronizations()
  {
    if (synchronizations == null)
    {
      synchronizations = new EObjectContainmentEList<Synchronization>(Synchronization.class, this, BeeLangPackage.BUILD_UNIT__SYNCHRONIZATIONS);
    }
    return synchronizations;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<BuildMethod> getBuildMethods()
  {
    if (buildMethods == null)
    {
      buildMethods = new EObjectContainmentEList<BuildMethod>(BuildMethod.class, this, BeeLangPackage.BUILD_UNIT__BUILD_METHODS);
    }
    return buildMethods;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<RepositoryConfiguration> getRepositoryConfigurations()
  {
    if (repositoryConfigurations == null)
    {
      repositoryConfigurations = new EObjectContainmentEList<RepositoryConfiguration>(RepositoryConfiguration.class, this, BeeLangPackage.BUILD_UNIT__REPOSITORY_CONFIGURATIONS);
    }
    return repositoryConfigurations;
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
      case BeeLangPackage.BUILD_UNIT__VERSION:
        return basicSetVersion(null, msgs);
      case BeeLangPackage.BUILD_UNIT__COMPOUND_PROPERTY_OPERATION:
        return ((InternalEList<?>)getCompoundPropertyOperation()).basicRemove(otherEnd, msgs);
      case BeeLangPackage.BUILD_UNIT__PROVIDED_CAPABILITIES:
        return ((InternalEList<?>)getProvidedCapabilities()).basicRemove(otherEnd, msgs);
      case BeeLangPackage.BUILD_UNIT__REQUIRED_CAPABILITIES:
        return ((InternalEList<?>)getRequiredCapabilities()).basicRemove(otherEnd, msgs);
      case BeeLangPackage.BUILD_UNIT__META_REQUIRED_CAPABILITIES:
        return ((InternalEList<?>)getMetaRequiredCapabilities()).basicRemove(otherEnd, msgs);
      case BeeLangPackage.BUILD_UNIT__ADVICE:
        return ((InternalEList<?>)getAdvice()).basicRemove(otherEnd, msgs);
      case BeeLangPackage.BUILD_UNIT__SYNCHRONIZATIONS:
        return ((InternalEList<?>)getSynchronizations()).basicRemove(otherEnd, msgs);
      case BeeLangPackage.BUILD_UNIT__BUILD_METHODS:
        return ((InternalEList<?>)getBuildMethods()).basicRemove(otherEnd, msgs);
      case BeeLangPackage.BUILD_UNIT__REPOSITORY_CONFIGURATIONS:
        return ((InternalEList<?>)getRepositoryConfigurations()).basicRemove(otherEnd, msgs);
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
      case BeeLangPackage.BUILD_UNIT__COMPOUND_PROPERTY_OPERATION:
        return getCompoundPropertyOperation();
      case BeeLangPackage.BUILD_UNIT__PROVIDED_CAPABILITIES:
        return getProvidedCapabilities();
      case BeeLangPackage.BUILD_UNIT__REQUIRED_CAPABILITIES:
        return getRequiredCapabilities();
      case BeeLangPackage.BUILD_UNIT__META_REQUIRED_CAPABILITIES:
        return getMetaRequiredCapabilities();
      case BeeLangPackage.BUILD_UNIT__ADVICE:
        return getAdvice();
      case BeeLangPackage.BUILD_UNIT__SYNCHRONIZATIONS:
        return getSynchronizations();
      case BeeLangPackage.BUILD_UNIT__BUILD_METHODS:
        return getBuildMethods();
      case BeeLangPackage.BUILD_UNIT__REPOSITORY_CONFIGURATIONS:
        return getRepositoryConfigurations();
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
        setVersion((Version)newValue);
        return;
      case BeeLangPackage.BUILD_UNIT__IMPLEMENTS:
        getImplements().clear();
        getImplements().addAll((Collection<? extends String>)newValue);
        return;
      case BeeLangPackage.BUILD_UNIT__COMPOUND_PROPERTY_OPERATION:
        getCompoundPropertyOperation().clear();
        getCompoundPropertyOperation().addAll((Collection<? extends NamedProperties>)newValue);
        return;
      case BeeLangPackage.BUILD_UNIT__PROVIDED_CAPABILITIES:
        getProvidedCapabilities().clear();
        getProvidedCapabilities().addAll((Collection<? extends ProvidedCapability>)newValue);
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
      case BeeLangPackage.BUILD_UNIT__SYNCHRONIZATIONS:
        getSynchronizations().clear();
        getSynchronizations().addAll((Collection<? extends Synchronization>)newValue);
        return;
      case BeeLangPackage.BUILD_UNIT__BUILD_METHODS:
        getBuildMethods().clear();
        getBuildMethods().addAll((Collection<? extends BuildMethod>)newValue);
        return;
      case BeeLangPackage.BUILD_UNIT__REPOSITORY_CONFIGURATIONS:
        getRepositoryConfigurations().clear();
        getRepositoryConfigurations().addAll((Collection<? extends RepositoryConfiguration>)newValue);
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
        setVersion((Version)null);
        return;
      case BeeLangPackage.BUILD_UNIT__IMPLEMENTS:
        getImplements().clear();
        return;
      case BeeLangPackage.BUILD_UNIT__COMPOUND_PROPERTY_OPERATION:
        getCompoundPropertyOperation().clear();
        return;
      case BeeLangPackage.BUILD_UNIT__PROVIDED_CAPABILITIES:
        getProvidedCapabilities().clear();
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
      case BeeLangPackage.BUILD_UNIT__SYNCHRONIZATIONS:
        getSynchronizations().clear();
        return;
      case BeeLangPackage.BUILD_UNIT__BUILD_METHODS:
        getBuildMethods().clear();
        return;
      case BeeLangPackage.BUILD_UNIT__REPOSITORY_CONFIGURATIONS:
        getRepositoryConfigurations().clear();
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
        return version != null;
      case BeeLangPackage.BUILD_UNIT__IMPLEMENTS:
        return implements_ != null && !implements_.isEmpty();
      case BeeLangPackage.BUILD_UNIT__COMPOUND_PROPERTY_OPERATION:
        return compoundPropertyOperation != null && !compoundPropertyOperation.isEmpty();
      case BeeLangPackage.BUILD_UNIT__PROVIDED_CAPABILITIES:
        return providedCapabilities != null && !providedCapabilities.isEmpty();
      case BeeLangPackage.BUILD_UNIT__REQUIRED_CAPABILITIES:
        return requiredCapabilities != null && !requiredCapabilities.isEmpty();
      case BeeLangPackage.BUILD_UNIT__META_REQUIRED_CAPABILITIES:
        return metaRequiredCapabilities != null && !metaRequiredCapabilities.isEmpty();
      case BeeLangPackage.BUILD_UNIT__ADVICE:
        return advice != null && !advice.isEmpty();
      case BeeLangPackage.BUILD_UNIT__SYNCHRONIZATIONS:
        return synchronizations != null && !synchronizations.isEmpty();
      case BeeLangPackage.BUILD_UNIT__BUILD_METHODS:
        return buildMethods != null && !buildMethods.isEmpty();
      case BeeLangPackage.BUILD_UNIT__REPOSITORY_CONFIGURATIONS:
        return repositoryConfigurations != null && !repositoryConfigurations.isEmpty();
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
    result.append(", implements: ");
    result.append(implements_);
    result.append(')');
    return result.toString();
  }

} //BuildUnitImpl
