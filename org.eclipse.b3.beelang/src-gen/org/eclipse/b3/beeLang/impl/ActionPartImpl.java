/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang.impl;

import java.util.Collection;

import org.eclipse.b3.beeLang.ActionInputGroup;
import org.eclipse.b3.beeLang.ActionPart;
import org.eclipse.b3.beeLang.Advice;
import org.eclipse.b3.beeLang.BeeLangPackage;
import org.eclipse.b3.beeLang.ExecutionMode;
import org.eclipse.b3.beeLang.Layout;
import org.eclipse.b3.beeLang.PropertyStatement;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Action Part</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.impl.ActionPartImpl#getExecutionMode <em>Execution Mode</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.ActionPartImpl#getActorParameters <em>Actor Parameters</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.ActionPartImpl#getAsserts <em>Asserts</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.ActionPartImpl#getProperties <em>Properties</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.ActionPartImpl#getAdvice <em>Advice</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.ActionPartImpl#getResultGroup <em>Result Group</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.ActionPartImpl#getLayout <em>Layout</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ActionPartImpl extends BuildPartImpl implements ActionPart
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
   * The cached value of the '{@link #getActorParameters() <em>Actor Parameters</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getActorParameters()
   * @generated
   * @ordered
   */
  protected EList<EObject> actorParameters;

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
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ActionPartImpl()
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
    return BeeLangPackage.Literals.ACTION_PART;
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
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.ACTION_PART__EXECUTION_MODE, oldExecutionMode, executionMode));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<EObject> getActorParameters()
  {
    if (actorParameters == null)
    {
      actorParameters = new EObjectContainmentEList<EObject>(EObject.class, this, BeeLangPackage.ACTION_PART__ACTOR_PARAMETERS);
    }
    return actorParameters;
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
      asserts = new EObjectContainmentEList<EObject>(EObject.class, this, BeeLangPackage.ACTION_PART__ASSERTS);
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
      properties = new EObjectContainmentEList<PropertyStatement>(PropertyStatement.class, this, BeeLangPackage.ACTION_PART__PROPERTIES);
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.ACTION_PART__ADVICE, oldAdvice, newAdvice);
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
        msgs = ((InternalEObject)advice).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.ACTION_PART__ADVICE, null, msgs);
      if (newAdvice != null)
        msgs = ((InternalEObject)newAdvice).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.ACTION_PART__ADVICE, null, msgs);
      msgs = basicSetAdvice(newAdvice, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.ACTION_PART__ADVICE, newAdvice, newAdvice));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.ACTION_PART__RESULT_GROUP, oldResultGroup, newResultGroup);
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
        msgs = ((InternalEObject)resultGroup).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.ACTION_PART__RESULT_GROUP, null, msgs);
      if (newResultGroup != null)
        msgs = ((InternalEObject)newResultGroup).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.ACTION_PART__RESULT_GROUP, null, msgs);
      msgs = basicSetResultGroup(newResultGroup, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.ACTION_PART__RESULT_GROUP, newResultGroup, newResultGroup));
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
      layout = new EObjectContainmentEList<Layout>(Layout.class, this, BeeLangPackage.ACTION_PART__LAYOUT);
    }
    return layout;
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
      case BeeLangPackage.ACTION_PART__ACTOR_PARAMETERS:
        return ((InternalEList<?>)getActorParameters()).basicRemove(otherEnd, msgs);
      case BeeLangPackage.ACTION_PART__ASSERTS:
        return ((InternalEList<?>)getAsserts()).basicRemove(otherEnd, msgs);
      case BeeLangPackage.ACTION_PART__PROPERTIES:
        return ((InternalEList<?>)getProperties()).basicRemove(otherEnd, msgs);
      case BeeLangPackage.ACTION_PART__ADVICE:
        return basicSetAdvice(null, msgs);
      case BeeLangPackage.ACTION_PART__RESULT_GROUP:
        return basicSetResultGroup(null, msgs);
      case BeeLangPackage.ACTION_PART__LAYOUT:
        return ((InternalEList<?>)getLayout()).basicRemove(otherEnd, msgs);
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
      case BeeLangPackage.ACTION_PART__EXECUTION_MODE:
        return getExecutionMode();
      case BeeLangPackage.ACTION_PART__ACTOR_PARAMETERS:
        return getActorParameters();
      case BeeLangPackage.ACTION_PART__ASSERTS:
        return getAsserts();
      case BeeLangPackage.ACTION_PART__PROPERTIES:
        return getProperties();
      case BeeLangPackage.ACTION_PART__ADVICE:
        return getAdvice();
      case BeeLangPackage.ACTION_PART__RESULT_GROUP:
        return getResultGroup();
      case BeeLangPackage.ACTION_PART__LAYOUT:
        return getLayout();
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
      case BeeLangPackage.ACTION_PART__EXECUTION_MODE:
        setExecutionMode((ExecutionMode)newValue);
        return;
      case BeeLangPackage.ACTION_PART__ACTOR_PARAMETERS:
        getActorParameters().clear();
        getActorParameters().addAll((Collection<? extends EObject>)newValue);
        return;
      case BeeLangPackage.ACTION_PART__ASSERTS:
        getAsserts().clear();
        getAsserts().addAll((Collection<? extends EObject>)newValue);
        return;
      case BeeLangPackage.ACTION_PART__PROPERTIES:
        getProperties().clear();
        getProperties().addAll((Collection<? extends PropertyStatement>)newValue);
        return;
      case BeeLangPackage.ACTION_PART__ADVICE:
        setAdvice((Advice)newValue);
        return;
      case BeeLangPackage.ACTION_PART__RESULT_GROUP:
        setResultGroup((ActionInputGroup)newValue);
        return;
      case BeeLangPackage.ACTION_PART__LAYOUT:
        getLayout().clear();
        getLayout().addAll((Collection<? extends Layout>)newValue);
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
      case BeeLangPackage.ACTION_PART__EXECUTION_MODE:
        setExecutionMode(EXECUTION_MODE_EDEFAULT);
        return;
      case BeeLangPackage.ACTION_PART__ACTOR_PARAMETERS:
        getActorParameters().clear();
        return;
      case BeeLangPackage.ACTION_PART__ASSERTS:
        getAsserts().clear();
        return;
      case BeeLangPackage.ACTION_PART__PROPERTIES:
        getProperties().clear();
        return;
      case BeeLangPackage.ACTION_PART__ADVICE:
        setAdvice((Advice)null);
        return;
      case BeeLangPackage.ACTION_PART__RESULT_GROUP:
        setResultGroup((ActionInputGroup)null);
        return;
      case BeeLangPackage.ACTION_PART__LAYOUT:
        getLayout().clear();
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
      case BeeLangPackage.ACTION_PART__EXECUTION_MODE:
        return executionMode != EXECUTION_MODE_EDEFAULT;
      case BeeLangPackage.ACTION_PART__ACTOR_PARAMETERS:
        return actorParameters != null && !actorParameters.isEmpty();
      case BeeLangPackage.ACTION_PART__ASSERTS:
        return asserts != null && !asserts.isEmpty();
      case BeeLangPackage.ACTION_PART__PROPERTIES:
        return properties != null && !properties.isEmpty();
      case BeeLangPackage.ACTION_PART__ADVICE:
        return advice != null;
      case BeeLangPackage.ACTION_PART__RESULT_GROUP:
        return resultGroup != null;
      case BeeLangPackage.ACTION_PART__LAYOUT:
        return layout != null && !layout.isEmpty();
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
    result.append(')');
    return result.toString();
  }

} //ActionPartImpl
