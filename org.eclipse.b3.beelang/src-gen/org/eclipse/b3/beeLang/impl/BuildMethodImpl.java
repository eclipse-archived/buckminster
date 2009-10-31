/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang.impl;

import java.util.Collection;

import org.eclipse.b3.beeLang.Advice;
import org.eclipse.b3.beeLang.BeeLangPackage;
import org.eclipse.b3.beeLang.BuildMethod;
import org.eclipse.b3.beeLang.CompoundPropertyOperation;
import org.eclipse.b3.beeLang.ExecutionMode;
import org.eclipse.b3.beeLang.Group;
import org.eclipse.b3.beeLang.Layout;
import org.eclipse.b3.beeLang.PostCondition;
import org.eclipse.b3.beeLang.PreCondition;
import org.eclipse.b3.beeLang.ProvidedCapability;
import org.eclipse.b3.beeLang.Statements;
import org.eclipse.b3.beeLang.Visibility;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Build Method</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.impl.BuildMethodImpl#getVisibility <em>Visibility</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.BuildMethodImpl#getExecutionMode <em>Execution Mode</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.BuildMethodImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.BuildMethodImpl#getProvidedCapabilities <em>Provided Capabilities</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.BuildMethodImpl#getPreCondition <em>Pre Condition</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.BuildMethodImpl#getPostCondition <em>Post Condition</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.BuildMethodImpl#getProperties <em>Properties</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.BuildMethodImpl#getAdvice <em>Advice</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.BuildMethodImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.BuildMethodImpl#getLayout <em>Layout</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.BuildMethodImpl#getStatements <em>Statements</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BuildMethodImpl extends MinimalEObjectImpl.Container implements BuildMethod
{
  /**
   * The default value of the '{@link #getVisibility() <em>Visibility</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVisibility()
   * @generated
   * @ordered
   */
  protected static final Visibility VISIBILITY_EDEFAULT = Visibility.PUBLIC;

  /**
   * The cached value of the '{@link #getVisibility() <em>Visibility</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVisibility()
   * @generated
   * @ordered
   */
  protected Visibility visibility = VISIBILITY_EDEFAULT;

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
   * The cached value of the '{@link #getProvidedCapabilities() <em>Provided Capabilities</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getProvidedCapabilities()
   * @generated
   * @ordered
   */
  protected EList<ProvidedCapability> providedCapabilities;

  /**
   * The cached value of the '{@link #getPreCondition() <em>Pre Condition</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPreCondition()
   * @generated
   * @ordered
   */
  protected PreCondition preCondition;

  /**
   * The cached value of the '{@link #getPostCondition() <em>Post Condition</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPostCondition()
   * @generated
   * @ordered
   */
  protected PostCondition postCondition;

  /**
   * The cached value of the '{@link #getProperties() <em>Properties</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getProperties()
   * @generated
   * @ordered
   */
  protected CompoundPropertyOperation properties;

  /**
   * The cached value of the '{@link #getAdvice() <em>Advice</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAdvice()
   * @generated
   * @ordered
   */
  protected Advice advice;

  /**
   * The cached value of the '{@link #getGroup() <em>Group</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGroup()
   * @generated
   * @ordered
   */
  protected Group group;

  /**
   * The cached value of the '{@link #getLayout() <em>Layout</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLayout()
   * @generated
   * @ordered
   */
  protected Layout layout;

  /**
   * The cached value of the '{@link #getStatements() <em>Statements</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStatements()
   * @generated
   * @ordered
   */
  protected Statements statements;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected BuildMethodImpl()
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
    return BeeLangPackage.Literals.BUILD_METHOD;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Visibility getVisibility()
  {
    return visibility;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setVisibility(Visibility newVisibility)
  {
    Visibility oldVisibility = visibility;
    visibility = newVisibility == null ? VISIBILITY_EDEFAULT : newVisibility;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.BUILD_METHOD__VISIBILITY, oldVisibility, visibility));
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
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.BUILD_METHOD__EXECUTION_MODE, oldExecutionMode, executionMode));
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
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.BUILD_METHOD__NAME, oldName, name));
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
      providedCapabilities = new EObjectContainmentEList<ProvidedCapability>(ProvidedCapability.class, this, BeeLangPackage.BUILD_METHOD__PROVIDED_CAPABILITIES);
    }
    return providedCapabilities;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PreCondition getPreCondition()
  {
    return preCondition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetPreCondition(PreCondition newPreCondition, NotificationChain msgs)
  {
    PreCondition oldPreCondition = preCondition;
    preCondition = newPreCondition;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.BUILD_METHOD__PRE_CONDITION, oldPreCondition, newPreCondition);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPreCondition(PreCondition newPreCondition)
  {
    if (newPreCondition != preCondition)
    {
      NotificationChain msgs = null;
      if (preCondition != null)
        msgs = ((InternalEObject)preCondition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.BUILD_METHOD__PRE_CONDITION, null, msgs);
      if (newPreCondition != null)
        msgs = ((InternalEObject)newPreCondition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.BUILD_METHOD__PRE_CONDITION, null, msgs);
      msgs = basicSetPreCondition(newPreCondition, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.BUILD_METHOD__PRE_CONDITION, newPreCondition, newPreCondition));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PostCondition getPostCondition()
  {
    return postCondition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetPostCondition(PostCondition newPostCondition, NotificationChain msgs)
  {
    PostCondition oldPostCondition = postCondition;
    postCondition = newPostCondition;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.BUILD_METHOD__POST_CONDITION, oldPostCondition, newPostCondition);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPostCondition(PostCondition newPostCondition)
  {
    if (newPostCondition != postCondition)
    {
      NotificationChain msgs = null;
      if (postCondition != null)
        msgs = ((InternalEObject)postCondition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.BUILD_METHOD__POST_CONDITION, null, msgs);
      if (newPostCondition != null)
        msgs = ((InternalEObject)newPostCondition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.BUILD_METHOD__POST_CONDITION, null, msgs);
      msgs = basicSetPostCondition(newPostCondition, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.BUILD_METHOD__POST_CONDITION, newPostCondition, newPostCondition));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CompoundPropertyOperation getProperties()
  {
    return properties;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetProperties(CompoundPropertyOperation newProperties, NotificationChain msgs)
  {
    CompoundPropertyOperation oldProperties = properties;
    properties = newProperties;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.BUILD_METHOD__PROPERTIES, oldProperties, newProperties);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setProperties(CompoundPropertyOperation newProperties)
  {
    if (newProperties != properties)
    {
      NotificationChain msgs = null;
      if (properties != null)
        msgs = ((InternalEObject)properties).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.BUILD_METHOD__PROPERTIES, null, msgs);
      if (newProperties != null)
        msgs = ((InternalEObject)newProperties).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.BUILD_METHOD__PROPERTIES, null, msgs);
      msgs = basicSetProperties(newProperties, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.BUILD_METHOD__PROPERTIES, newProperties, newProperties));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Advice getAdvice()
  {
    return advice;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetAdvice(Advice newAdvice, NotificationChain msgs)
  {
    Advice oldAdvice = advice;
    advice = newAdvice;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.BUILD_METHOD__ADVICE, oldAdvice, newAdvice);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAdvice(Advice newAdvice)
  {
    if (newAdvice != advice)
    {
      NotificationChain msgs = null;
      if (advice != null)
        msgs = ((InternalEObject)advice).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.BUILD_METHOD__ADVICE, null, msgs);
      if (newAdvice != null)
        msgs = ((InternalEObject)newAdvice).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.BUILD_METHOD__ADVICE, null, msgs);
      msgs = basicSetAdvice(newAdvice, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.BUILD_METHOD__ADVICE, newAdvice, newAdvice));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Group getGroup()
  {
    return group;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetGroup(Group newGroup, NotificationChain msgs)
  {
    Group oldGroup = group;
    group = newGroup;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.BUILD_METHOD__GROUP, oldGroup, newGroup);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setGroup(Group newGroup)
  {
    if (newGroup != group)
    {
      NotificationChain msgs = null;
      if (group != null)
        msgs = ((InternalEObject)group).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.BUILD_METHOD__GROUP, null, msgs);
      if (newGroup != null)
        msgs = ((InternalEObject)newGroup).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.BUILD_METHOD__GROUP, null, msgs);
      msgs = basicSetGroup(newGroup, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.BUILD_METHOD__GROUP, newGroup, newGroup));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Layout getLayout()
  {
    return layout;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetLayout(Layout newLayout, NotificationChain msgs)
  {
    Layout oldLayout = layout;
    layout = newLayout;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.BUILD_METHOD__LAYOUT, oldLayout, newLayout);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLayout(Layout newLayout)
  {
    if (newLayout != layout)
    {
      NotificationChain msgs = null;
      if (layout != null)
        msgs = ((InternalEObject)layout).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.BUILD_METHOD__LAYOUT, null, msgs);
      if (newLayout != null)
        msgs = ((InternalEObject)newLayout).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.BUILD_METHOD__LAYOUT, null, msgs);
      msgs = basicSetLayout(newLayout, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.BUILD_METHOD__LAYOUT, newLayout, newLayout));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Statements getStatements()
  {
    return statements;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetStatements(Statements newStatements, NotificationChain msgs)
  {
    Statements oldStatements = statements;
    statements = newStatements;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.BUILD_METHOD__STATEMENTS, oldStatements, newStatements);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setStatements(Statements newStatements)
  {
    if (newStatements != statements)
    {
      NotificationChain msgs = null;
      if (statements != null)
        msgs = ((InternalEObject)statements).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.BUILD_METHOD__STATEMENTS, null, msgs);
      if (newStatements != null)
        msgs = ((InternalEObject)newStatements).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.BUILD_METHOD__STATEMENTS, null, msgs);
      msgs = basicSetStatements(newStatements, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.BUILD_METHOD__STATEMENTS, newStatements, newStatements));
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
      case BeeLangPackage.BUILD_METHOD__PROVIDED_CAPABILITIES:
        return ((InternalEList<?>)getProvidedCapabilities()).basicRemove(otherEnd, msgs);
      case BeeLangPackage.BUILD_METHOD__PRE_CONDITION:
        return basicSetPreCondition(null, msgs);
      case BeeLangPackage.BUILD_METHOD__POST_CONDITION:
        return basicSetPostCondition(null, msgs);
      case BeeLangPackage.BUILD_METHOD__PROPERTIES:
        return basicSetProperties(null, msgs);
      case BeeLangPackage.BUILD_METHOD__ADVICE:
        return basicSetAdvice(null, msgs);
      case BeeLangPackage.BUILD_METHOD__GROUP:
        return basicSetGroup(null, msgs);
      case BeeLangPackage.BUILD_METHOD__LAYOUT:
        return basicSetLayout(null, msgs);
      case BeeLangPackage.BUILD_METHOD__STATEMENTS:
        return basicSetStatements(null, msgs);
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
      case BeeLangPackage.BUILD_METHOD__VISIBILITY:
        return getVisibility();
      case BeeLangPackage.BUILD_METHOD__EXECUTION_MODE:
        return getExecutionMode();
      case BeeLangPackage.BUILD_METHOD__NAME:
        return getName();
      case BeeLangPackage.BUILD_METHOD__PROVIDED_CAPABILITIES:
        return getProvidedCapabilities();
      case BeeLangPackage.BUILD_METHOD__PRE_CONDITION:
        return getPreCondition();
      case BeeLangPackage.BUILD_METHOD__POST_CONDITION:
        return getPostCondition();
      case BeeLangPackage.BUILD_METHOD__PROPERTIES:
        return getProperties();
      case BeeLangPackage.BUILD_METHOD__ADVICE:
        return getAdvice();
      case BeeLangPackage.BUILD_METHOD__GROUP:
        return getGroup();
      case BeeLangPackage.BUILD_METHOD__LAYOUT:
        return getLayout();
      case BeeLangPackage.BUILD_METHOD__STATEMENTS:
        return getStatements();
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
      case BeeLangPackage.BUILD_METHOD__VISIBILITY:
        setVisibility((Visibility)newValue);
        return;
      case BeeLangPackage.BUILD_METHOD__EXECUTION_MODE:
        setExecutionMode((ExecutionMode)newValue);
        return;
      case BeeLangPackage.BUILD_METHOD__NAME:
        setName((String)newValue);
        return;
      case BeeLangPackage.BUILD_METHOD__PROVIDED_CAPABILITIES:
        getProvidedCapabilities().clear();
        getProvidedCapabilities().addAll((Collection<? extends ProvidedCapability>)newValue);
        return;
      case BeeLangPackage.BUILD_METHOD__PRE_CONDITION:
        setPreCondition((PreCondition)newValue);
        return;
      case BeeLangPackage.BUILD_METHOD__POST_CONDITION:
        setPostCondition((PostCondition)newValue);
        return;
      case BeeLangPackage.BUILD_METHOD__PROPERTIES:
        setProperties((CompoundPropertyOperation)newValue);
        return;
      case BeeLangPackage.BUILD_METHOD__ADVICE:
        setAdvice((Advice)newValue);
        return;
      case BeeLangPackage.BUILD_METHOD__GROUP:
        setGroup((Group)newValue);
        return;
      case BeeLangPackage.BUILD_METHOD__LAYOUT:
        setLayout((Layout)newValue);
        return;
      case BeeLangPackage.BUILD_METHOD__STATEMENTS:
        setStatements((Statements)newValue);
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
      case BeeLangPackage.BUILD_METHOD__VISIBILITY:
        setVisibility(VISIBILITY_EDEFAULT);
        return;
      case BeeLangPackage.BUILD_METHOD__EXECUTION_MODE:
        setExecutionMode(EXECUTION_MODE_EDEFAULT);
        return;
      case BeeLangPackage.BUILD_METHOD__NAME:
        setName(NAME_EDEFAULT);
        return;
      case BeeLangPackage.BUILD_METHOD__PROVIDED_CAPABILITIES:
        getProvidedCapabilities().clear();
        return;
      case BeeLangPackage.BUILD_METHOD__PRE_CONDITION:
        setPreCondition((PreCondition)null);
        return;
      case BeeLangPackage.BUILD_METHOD__POST_CONDITION:
        setPostCondition((PostCondition)null);
        return;
      case BeeLangPackage.BUILD_METHOD__PROPERTIES:
        setProperties((CompoundPropertyOperation)null);
        return;
      case BeeLangPackage.BUILD_METHOD__ADVICE:
        setAdvice((Advice)null);
        return;
      case BeeLangPackage.BUILD_METHOD__GROUP:
        setGroup((Group)null);
        return;
      case BeeLangPackage.BUILD_METHOD__LAYOUT:
        setLayout((Layout)null);
        return;
      case BeeLangPackage.BUILD_METHOD__STATEMENTS:
        setStatements((Statements)null);
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
      case BeeLangPackage.BUILD_METHOD__VISIBILITY:
        return visibility != VISIBILITY_EDEFAULT;
      case BeeLangPackage.BUILD_METHOD__EXECUTION_MODE:
        return executionMode != EXECUTION_MODE_EDEFAULT;
      case BeeLangPackage.BUILD_METHOD__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case BeeLangPackage.BUILD_METHOD__PROVIDED_CAPABILITIES:
        return providedCapabilities != null && !providedCapabilities.isEmpty();
      case BeeLangPackage.BUILD_METHOD__PRE_CONDITION:
        return preCondition != null;
      case BeeLangPackage.BUILD_METHOD__POST_CONDITION:
        return postCondition != null;
      case BeeLangPackage.BUILD_METHOD__PROPERTIES:
        return properties != null;
      case BeeLangPackage.BUILD_METHOD__ADVICE:
        return advice != null;
      case BeeLangPackage.BUILD_METHOD__GROUP:
        return group != null;
      case BeeLangPackage.BUILD_METHOD__LAYOUT:
        return layout != null;
      case BeeLangPackage.BUILD_METHOD__STATEMENTS:
        return statements != null;
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
    result.append(" (visibility: ");
    result.append(visibility);
    result.append(", executionMode: ");
    result.append(executionMode);
    result.append(", name: ");
    result.append(name);
    result.append(')');
    return result.toString();
  }

} //BuildMethodImpl
