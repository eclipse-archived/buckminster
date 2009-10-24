/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang.impl;

import java.util.Collection;

import org.eclipse.b3.beeLang.ActionInputGroup;
import org.eclipse.b3.beeLang.Advice;
import org.eclipse.b3.beeLang.BeeLangPackage;
import org.eclipse.b3.beeLang.ExecutionMode;
import org.eclipse.b3.beeLang.Layout;
import org.eclipse.b3.beeLang.Part;
import org.eclipse.b3.beeLang.PropertyStatement;
import org.eclipse.b3.beeLang.ProvidedCapability;
import org.eclipse.b3.beeLang.Statements;
import org.eclipse.b3.beeLang.Visibility;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Part</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.impl.PartImpl#getVisibility <em>Visibility</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.PartImpl#getExecutionMode <em>Execution Mode</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.PartImpl#getProvidedCapabilities <em>Provided Capabilities</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.PartImpl#getAsserts <em>Asserts</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.PartImpl#getProperties <em>Properties</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.PartImpl#getAdvice <em>Advice</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.PartImpl#getResultGroup <em>Result Group</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.PartImpl#getLayout <em>Layout</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.PartImpl#getStatements <em>Statements</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PartImpl extends MinimalEObjectImpl.Container implements Part
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
   * The cached value of the '{@link #getProvidedCapabilities() <em>Provided Capabilities</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getProvidedCapabilities()
   * @generated
   * @ordered
   */
  protected EList<ProvidedCapability> providedCapabilities;

  /**
   * The cached value of the '{@link #getAsserts() <em>Asserts</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAsserts()
   * @generated
   * @ordered
   */
  protected EList<EObject> asserts;

  /**
   * The cached value of the '{@link #getProperties() <em>Properties</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getProperties()
   * @generated
   * @ordered
   */
  protected EList<PropertyStatement> properties;

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
   * The cached value of the '{@link #getResultGroup() <em>Result Group</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getResultGroup()
   * @generated
   * @ordered
   */
  protected ActionInputGroup resultGroup;

  /**
   * The cached value of the '{@link #getLayout() <em>Layout</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLayout()
   * @generated
   * @ordered
   */
  protected EList<Layout> layout;

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
  protected PartImpl()
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
    return BeeLangPackage.Literals.PART;
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
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.PART__VISIBILITY, oldVisibility, visibility));
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
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.PART__EXECUTION_MODE, oldExecutionMode, executionMode));
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
      providedCapabilities = new EObjectContainmentEList<ProvidedCapability>(ProvidedCapability.class, this, BeeLangPackage.PART__PROVIDED_CAPABILITIES);
    }
    return providedCapabilities;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<EObject> getAsserts()
  {
    if (asserts == null)
    {
      asserts = new EObjectContainmentEList<EObject>(EObject.class, this, BeeLangPackage.PART__ASSERTS);
    }
    return asserts;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<PropertyStatement> getProperties()
  {
    if (properties == null)
    {
      properties = new EObjectContainmentEList<PropertyStatement>(PropertyStatement.class, this, BeeLangPackage.PART__PROPERTIES);
    }
    return properties;
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.PART__ADVICE, oldAdvice, newAdvice);
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
        msgs = ((InternalEObject)advice).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.PART__ADVICE, null, msgs);
      if (newAdvice != null)
        msgs = ((InternalEObject)newAdvice).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.PART__ADVICE, null, msgs);
      msgs = basicSetAdvice(newAdvice, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.PART__ADVICE, newAdvice, newAdvice));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ActionInputGroup getResultGroup()
  {
    return resultGroup;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetResultGroup(ActionInputGroup newResultGroup, NotificationChain msgs)
  {
    ActionInputGroup oldResultGroup = resultGroup;
    resultGroup = newResultGroup;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.PART__RESULT_GROUP, oldResultGroup, newResultGroup);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setResultGroup(ActionInputGroup newResultGroup)
  {
    if (newResultGroup != resultGroup)
    {
      NotificationChain msgs = null;
      if (resultGroup != null)
        msgs = ((InternalEObject)resultGroup).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.PART__RESULT_GROUP, null, msgs);
      if (newResultGroup != null)
        msgs = ((InternalEObject)newResultGroup).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.PART__RESULT_GROUP, null, msgs);
      msgs = basicSetResultGroup(newResultGroup, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.PART__RESULT_GROUP, newResultGroup, newResultGroup));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Layout> getLayout()
  {
    if (layout == null)
    {
      layout = new EObjectContainmentEList<Layout>(Layout.class, this, BeeLangPackage.PART__LAYOUT);
    }
    return layout;
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.PART__STATEMENTS, oldStatements, newStatements);
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
        msgs = ((InternalEObject)statements).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.PART__STATEMENTS, null, msgs);
      if (newStatements != null)
        msgs = ((InternalEObject)newStatements).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.PART__STATEMENTS, null, msgs);
      msgs = basicSetStatements(newStatements, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.PART__STATEMENTS, newStatements, newStatements));
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
      case BeeLangPackage.PART__PROVIDED_CAPABILITIES:
        return ((InternalEList<?>)getProvidedCapabilities()).basicRemove(otherEnd, msgs);
      case BeeLangPackage.PART__ASSERTS:
        return ((InternalEList<?>)getAsserts()).basicRemove(otherEnd, msgs);
      case BeeLangPackage.PART__PROPERTIES:
        return ((InternalEList<?>)getProperties()).basicRemove(otherEnd, msgs);
      case BeeLangPackage.PART__ADVICE:
        return basicSetAdvice(null, msgs);
      case BeeLangPackage.PART__RESULT_GROUP:
        return basicSetResultGroup(null, msgs);
      case BeeLangPackage.PART__LAYOUT:
        return ((InternalEList<?>)getLayout()).basicRemove(otherEnd, msgs);
      case BeeLangPackage.PART__STATEMENTS:
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
      case BeeLangPackage.PART__VISIBILITY:
        return getVisibility();
      case BeeLangPackage.PART__EXECUTION_MODE:
        return getExecutionMode();
      case BeeLangPackage.PART__PROVIDED_CAPABILITIES:
        return getProvidedCapabilities();
      case BeeLangPackage.PART__ASSERTS:
        return getAsserts();
      case BeeLangPackage.PART__PROPERTIES:
        return getProperties();
      case BeeLangPackage.PART__ADVICE:
        return getAdvice();
      case BeeLangPackage.PART__RESULT_GROUP:
        return getResultGroup();
      case BeeLangPackage.PART__LAYOUT:
        return getLayout();
      case BeeLangPackage.PART__STATEMENTS:
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
      case BeeLangPackage.PART__VISIBILITY:
        setVisibility((Visibility)newValue);
        return;
      case BeeLangPackage.PART__EXECUTION_MODE:
        setExecutionMode((ExecutionMode)newValue);
        return;
      case BeeLangPackage.PART__PROVIDED_CAPABILITIES:
        getProvidedCapabilities().clear();
        getProvidedCapabilities().addAll((Collection<? extends ProvidedCapability>)newValue);
        return;
      case BeeLangPackage.PART__ASSERTS:
        getAsserts().clear();
        getAsserts().addAll((Collection<? extends EObject>)newValue);
        return;
      case BeeLangPackage.PART__PROPERTIES:
        getProperties().clear();
        getProperties().addAll((Collection<? extends PropertyStatement>)newValue);
        return;
      case BeeLangPackage.PART__ADVICE:
        setAdvice((Advice)newValue);
        return;
      case BeeLangPackage.PART__RESULT_GROUP:
        setResultGroup((ActionInputGroup)newValue);
        return;
      case BeeLangPackage.PART__LAYOUT:
        getLayout().clear();
        getLayout().addAll((Collection<? extends Layout>)newValue);
        return;
      case BeeLangPackage.PART__STATEMENTS:
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
      case BeeLangPackage.PART__VISIBILITY:
        setVisibility(VISIBILITY_EDEFAULT);
        return;
      case BeeLangPackage.PART__EXECUTION_MODE:
        setExecutionMode(EXECUTION_MODE_EDEFAULT);
        return;
      case BeeLangPackage.PART__PROVIDED_CAPABILITIES:
        getProvidedCapabilities().clear();
        return;
      case BeeLangPackage.PART__ASSERTS:
        getAsserts().clear();
        return;
      case BeeLangPackage.PART__PROPERTIES:
        getProperties().clear();
        return;
      case BeeLangPackage.PART__ADVICE:
        setAdvice((Advice)null);
        return;
      case BeeLangPackage.PART__RESULT_GROUP:
        setResultGroup((ActionInputGroup)null);
        return;
      case BeeLangPackage.PART__LAYOUT:
        getLayout().clear();
        return;
      case BeeLangPackage.PART__STATEMENTS:
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
      case BeeLangPackage.PART__VISIBILITY:
        return visibility != VISIBILITY_EDEFAULT;
      case BeeLangPackage.PART__EXECUTION_MODE:
        return executionMode != EXECUTION_MODE_EDEFAULT;
      case BeeLangPackage.PART__PROVIDED_CAPABILITIES:
        return providedCapabilities != null && !providedCapabilities.isEmpty();
      case BeeLangPackage.PART__ASSERTS:
        return asserts != null && !asserts.isEmpty();
      case BeeLangPackage.PART__PROPERTIES:
        return properties != null && !properties.isEmpty();
      case BeeLangPackage.PART__ADVICE:
        return advice != null;
      case BeeLangPackage.PART__RESULT_GROUP:
        return resultGroup != null;
      case BeeLangPackage.PART__LAYOUT:
        return layout != null && !layout.isEmpty();
      case BeeLangPackage.PART__STATEMENTS:
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
    result.append(')');
    return result.toString();
  }

} //PartImpl
