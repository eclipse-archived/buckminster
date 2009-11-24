/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang.impl;

import java.util.Collection;

import org.eclipse.b3.beeLang.BeeLangPackage;
import org.eclipse.b3.beeLang.BuildUnit;
import org.eclipse.b3.beeLang.Builder;
import org.eclipse.b3.beeLang.Concern;
import org.eclipse.b3.beeLang.ContainerConfiguration;
import org.eclipse.b3.beeLang.ExecutionMode;
import org.eclipse.b3.beeLang.NamedPropertySet;
import org.eclipse.b3.beeLang.PropertySet;
import org.eclipse.b3.beeLang.ProvidedCapability;
import org.eclipse.b3.beeLang.RepositoryConfiguration;
import org.eclipse.b3.beeLang.RequiredCapability;
import org.eclipse.b3.beeLang.Statement;
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
 *   <li>{@link org.eclipse.b3.beeLang.impl.BuildUnitImpl#getDocumentation <em>Documentation</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.BuildUnitImpl#getExecutionMode <em>Execution Mode</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.BuildUnitImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.BuildUnitImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.BuildUnitImpl#getImplements <em>Implements</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.BuildUnitImpl#getDefaultProperties <em>Default Properties</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.BuildUnitImpl#getProvidedCapabilities <em>Provided Capabilities</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.BuildUnitImpl#getRequiredCapabilities <em>Required Capabilities</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.BuildUnitImpl#getMetaRequiredCapabilities <em>Meta Required Capabilities</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.BuildUnitImpl#getConcerns <em>Concerns</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.BuildUnitImpl#getSynchronizations <em>Synchronizations</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.BuildUnitImpl#getBuilders <em>Builders</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.BuildUnitImpl#getMethods <em>Methods</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.BuildUnitImpl#getRepositoryConfigurations <em>Repository Configurations</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.BuildUnitImpl#getPropertySets <em>Property Sets</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.BuildUnitImpl#getContainers <em>Containers</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BuildUnitImpl extends MinimalEObjectImpl.Container implements BuildUnit
{
  /**
   * The default value of the '{@link #getDocumentation() <em>Documentation</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDocumentation()
   * @generated
   * @ordered
   */
  protected static final String DOCUMENTATION_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getDocumentation() <em>Documentation</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDocumentation()
   * @generated
   * @ordered
   */
  protected String documentation = DOCUMENTATION_EDEFAULT;

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
   * The cached value of the '{@link #getDefaultProperties() <em>Default Properties</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDefaultProperties()
   * @generated
   * @ordered
   */
  protected PropertySet defaultProperties;

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
   * The cached value of the '{@link #getConcerns() <em>Concerns</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getConcerns()
   * @generated
   * @ordered
   */
  protected EList<Concern> concerns;

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
   * The cached value of the '{@link #getBuilders() <em>Builders</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBuilders()
   * @generated
   * @ordered
   */
  protected EList<Builder> builders;

  /**
   * The cached value of the '{@link #getMethods() <em>Methods</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMethods()
   * @generated
   * @ordered
   */
  protected EList<Statement> methods;

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
   * The cached value of the '{@link #getPropertySets() <em>Property Sets</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPropertySets()
   * @generated
   * @ordered
   */
  protected EList<NamedPropertySet> propertySets;

  /**
   * The cached value of the '{@link #getContainers() <em>Containers</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getContainers()
   * @generated
   * @ordered
   */
  protected EList<ContainerConfiguration> containers;

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
  public String getDocumentation()
  {
    return documentation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDocumentation(String newDocumentation)
  {
    String oldDocumentation = documentation;
    documentation = newDocumentation;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.BUILD_UNIT__DOCUMENTATION, oldDocumentation, documentation));
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
  public PropertySet getDefaultProperties()
  {
    return defaultProperties;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetDefaultProperties(PropertySet newDefaultProperties, NotificationChain msgs)
  {
    PropertySet oldDefaultProperties = defaultProperties;
    defaultProperties = newDefaultProperties;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.BUILD_UNIT__DEFAULT_PROPERTIES, oldDefaultProperties, newDefaultProperties);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDefaultProperties(PropertySet newDefaultProperties)
  {
    if (newDefaultProperties != defaultProperties)
    {
      NotificationChain msgs = null;
      if (defaultProperties != null)
        msgs = ((InternalEObject)defaultProperties).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.BUILD_UNIT__DEFAULT_PROPERTIES, null, msgs);
      if (newDefaultProperties != null)
        msgs = ((InternalEObject)newDefaultProperties).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.BUILD_UNIT__DEFAULT_PROPERTIES, null, msgs);
      msgs = basicSetDefaultProperties(newDefaultProperties, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.BUILD_UNIT__DEFAULT_PROPERTIES, newDefaultProperties, newDefaultProperties));
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
  public EList<Concern> getConcerns()
  {
    if (concerns == null)
    {
      concerns = new EObjectContainmentEList<Concern>(Concern.class, this, BeeLangPackage.BUILD_UNIT__CONCERNS);
    }
    return concerns;
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
  public EList<Builder> getBuilders()
  {
    if (builders == null)
    {
      builders = new EObjectContainmentEList<Builder>(Builder.class, this, BeeLangPackage.BUILD_UNIT__BUILDERS);
    }
    return builders;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Statement> getMethods()
  {
    if (methods == null)
    {
      methods = new EObjectContainmentEList<Statement>(Statement.class, this, BeeLangPackage.BUILD_UNIT__METHODS);
    }
    return methods;
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
  public EList<NamedPropertySet> getPropertySets()
  {
    if (propertySets == null)
    {
      propertySets = new EObjectContainmentEList<NamedPropertySet>(NamedPropertySet.class, this, BeeLangPackage.BUILD_UNIT__PROPERTY_SETS);
    }
    return propertySets;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<ContainerConfiguration> getContainers()
  {
    if (containers == null)
    {
      containers = new EObjectContainmentEList<ContainerConfiguration>(ContainerConfiguration.class, this, BeeLangPackage.BUILD_UNIT__CONTAINERS);
    }
    return containers;
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
      case BeeLangPackage.BUILD_UNIT__DEFAULT_PROPERTIES:
        return basicSetDefaultProperties(null, msgs);
      case BeeLangPackage.BUILD_UNIT__PROVIDED_CAPABILITIES:
        return ((InternalEList<?>)getProvidedCapabilities()).basicRemove(otherEnd, msgs);
      case BeeLangPackage.BUILD_UNIT__REQUIRED_CAPABILITIES:
        return ((InternalEList<?>)getRequiredCapabilities()).basicRemove(otherEnd, msgs);
      case BeeLangPackage.BUILD_UNIT__META_REQUIRED_CAPABILITIES:
        return ((InternalEList<?>)getMetaRequiredCapabilities()).basicRemove(otherEnd, msgs);
      case BeeLangPackage.BUILD_UNIT__CONCERNS:
        return ((InternalEList<?>)getConcerns()).basicRemove(otherEnd, msgs);
      case BeeLangPackage.BUILD_UNIT__SYNCHRONIZATIONS:
        return ((InternalEList<?>)getSynchronizations()).basicRemove(otherEnd, msgs);
      case BeeLangPackage.BUILD_UNIT__BUILDERS:
        return ((InternalEList<?>)getBuilders()).basicRemove(otherEnd, msgs);
      case BeeLangPackage.BUILD_UNIT__METHODS:
        return ((InternalEList<?>)getMethods()).basicRemove(otherEnd, msgs);
      case BeeLangPackage.BUILD_UNIT__REPOSITORY_CONFIGURATIONS:
        return ((InternalEList<?>)getRepositoryConfigurations()).basicRemove(otherEnd, msgs);
      case BeeLangPackage.BUILD_UNIT__PROPERTY_SETS:
        return ((InternalEList<?>)getPropertySets()).basicRemove(otherEnd, msgs);
      case BeeLangPackage.BUILD_UNIT__CONTAINERS:
        return ((InternalEList<?>)getContainers()).basicRemove(otherEnd, msgs);
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
      case BeeLangPackage.BUILD_UNIT__DOCUMENTATION:
        return getDocumentation();
      case BeeLangPackage.BUILD_UNIT__EXECUTION_MODE:
        return getExecutionMode();
      case BeeLangPackage.BUILD_UNIT__NAME:
        return getName();
      case BeeLangPackage.BUILD_UNIT__VERSION:
        return getVersion();
      case BeeLangPackage.BUILD_UNIT__IMPLEMENTS:
        return getImplements();
      case BeeLangPackage.BUILD_UNIT__DEFAULT_PROPERTIES:
        return getDefaultProperties();
      case BeeLangPackage.BUILD_UNIT__PROVIDED_CAPABILITIES:
        return getProvidedCapabilities();
      case BeeLangPackage.BUILD_UNIT__REQUIRED_CAPABILITIES:
        return getRequiredCapabilities();
      case BeeLangPackage.BUILD_UNIT__META_REQUIRED_CAPABILITIES:
        return getMetaRequiredCapabilities();
      case BeeLangPackage.BUILD_UNIT__CONCERNS:
        return getConcerns();
      case BeeLangPackage.BUILD_UNIT__SYNCHRONIZATIONS:
        return getSynchronizations();
      case BeeLangPackage.BUILD_UNIT__BUILDERS:
        return getBuilders();
      case BeeLangPackage.BUILD_UNIT__METHODS:
        return getMethods();
      case BeeLangPackage.BUILD_UNIT__REPOSITORY_CONFIGURATIONS:
        return getRepositoryConfigurations();
      case BeeLangPackage.BUILD_UNIT__PROPERTY_SETS:
        return getPropertySets();
      case BeeLangPackage.BUILD_UNIT__CONTAINERS:
        return getContainers();
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
      case BeeLangPackage.BUILD_UNIT__DOCUMENTATION:
        setDocumentation((String)newValue);
        return;
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
      case BeeLangPackage.BUILD_UNIT__DEFAULT_PROPERTIES:
        setDefaultProperties((PropertySet)newValue);
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
      case BeeLangPackage.BUILD_UNIT__CONCERNS:
        getConcerns().clear();
        getConcerns().addAll((Collection<? extends Concern>)newValue);
        return;
      case BeeLangPackage.BUILD_UNIT__SYNCHRONIZATIONS:
        getSynchronizations().clear();
        getSynchronizations().addAll((Collection<? extends Synchronization>)newValue);
        return;
      case BeeLangPackage.BUILD_UNIT__BUILDERS:
        getBuilders().clear();
        getBuilders().addAll((Collection<? extends Builder>)newValue);
        return;
      case BeeLangPackage.BUILD_UNIT__METHODS:
        getMethods().clear();
        getMethods().addAll((Collection<? extends Statement>)newValue);
        return;
      case BeeLangPackage.BUILD_UNIT__REPOSITORY_CONFIGURATIONS:
        getRepositoryConfigurations().clear();
        getRepositoryConfigurations().addAll((Collection<? extends RepositoryConfiguration>)newValue);
        return;
      case BeeLangPackage.BUILD_UNIT__PROPERTY_SETS:
        getPropertySets().clear();
        getPropertySets().addAll((Collection<? extends NamedPropertySet>)newValue);
        return;
      case BeeLangPackage.BUILD_UNIT__CONTAINERS:
        getContainers().clear();
        getContainers().addAll((Collection<? extends ContainerConfiguration>)newValue);
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
      case BeeLangPackage.BUILD_UNIT__DOCUMENTATION:
        setDocumentation(DOCUMENTATION_EDEFAULT);
        return;
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
      case BeeLangPackage.BUILD_UNIT__DEFAULT_PROPERTIES:
        setDefaultProperties((PropertySet)null);
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
      case BeeLangPackage.BUILD_UNIT__CONCERNS:
        getConcerns().clear();
        return;
      case BeeLangPackage.BUILD_UNIT__SYNCHRONIZATIONS:
        getSynchronizations().clear();
        return;
      case BeeLangPackage.BUILD_UNIT__BUILDERS:
        getBuilders().clear();
        return;
      case BeeLangPackage.BUILD_UNIT__METHODS:
        getMethods().clear();
        return;
      case BeeLangPackage.BUILD_UNIT__REPOSITORY_CONFIGURATIONS:
        getRepositoryConfigurations().clear();
        return;
      case BeeLangPackage.BUILD_UNIT__PROPERTY_SETS:
        getPropertySets().clear();
        return;
      case BeeLangPackage.BUILD_UNIT__CONTAINERS:
        getContainers().clear();
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
      case BeeLangPackage.BUILD_UNIT__DOCUMENTATION:
        return DOCUMENTATION_EDEFAULT == null ? documentation != null : !DOCUMENTATION_EDEFAULT.equals(documentation);
      case BeeLangPackage.BUILD_UNIT__EXECUTION_MODE:
        return executionMode != EXECUTION_MODE_EDEFAULT;
      case BeeLangPackage.BUILD_UNIT__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case BeeLangPackage.BUILD_UNIT__VERSION:
        return version != null;
      case BeeLangPackage.BUILD_UNIT__IMPLEMENTS:
        return implements_ != null && !implements_.isEmpty();
      case BeeLangPackage.BUILD_UNIT__DEFAULT_PROPERTIES:
        return defaultProperties != null;
      case BeeLangPackage.BUILD_UNIT__PROVIDED_CAPABILITIES:
        return providedCapabilities != null && !providedCapabilities.isEmpty();
      case BeeLangPackage.BUILD_UNIT__REQUIRED_CAPABILITIES:
        return requiredCapabilities != null && !requiredCapabilities.isEmpty();
      case BeeLangPackage.BUILD_UNIT__META_REQUIRED_CAPABILITIES:
        return metaRequiredCapabilities != null && !metaRequiredCapabilities.isEmpty();
      case BeeLangPackage.BUILD_UNIT__CONCERNS:
        return concerns != null && !concerns.isEmpty();
      case BeeLangPackage.BUILD_UNIT__SYNCHRONIZATIONS:
        return synchronizations != null && !synchronizations.isEmpty();
      case BeeLangPackage.BUILD_UNIT__BUILDERS:
        return builders != null && !builders.isEmpty();
      case BeeLangPackage.BUILD_UNIT__METHODS:
        return methods != null && !methods.isEmpty();
      case BeeLangPackage.BUILD_UNIT__REPOSITORY_CONFIGURATIONS:
        return repositoryConfigurations != null && !repositoryConfigurations.isEmpty();
      case BeeLangPackage.BUILD_UNIT__PROPERTY_SETS:
        return propertySets != null && !propertySets.isEmpty();
      case BeeLangPackage.BUILD_UNIT__CONTAINERS:
        return containers != null && !containers.isEmpty();
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
    result.append(" (documentation: ");
    result.append(documentation);
    result.append(", executionMode: ");
    result.append(executionMode);
    result.append(", name: ");
    result.append(name);
    result.append(", implements: ");
    result.append(implements_);
    result.append(')');
    return result.toString();
  }

} //BuildUnitImpl
