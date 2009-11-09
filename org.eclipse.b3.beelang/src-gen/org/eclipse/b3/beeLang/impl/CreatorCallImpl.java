/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang.impl;

import org.eclipse.b3.beeLang.BeeLangPackage;
import org.eclipse.b3.beeLang.ContextBlock;
import org.eclipse.b3.beeLang.CreatorCall;
import org.eclipse.b3.beeLang.ParameterList;
import org.eclipse.b3.beeLang.TypeRef;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Creator Call</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.impl.CreatorCallImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.CreatorCallImpl#getTarget <em>Target</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.CreatorCallImpl#getParameters <em>Parameters</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.CreatorCallImpl#getAlias <em>Alias</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.CreatorCallImpl#getContextBlock <em>Context Block</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CreatorCallImpl extends ExpressionImpl implements CreatorCall
{
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
   * The cached value of the '{@link #getTarget() <em>Target</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTarget()
   * @generated
   * @ordered
   */
  protected TypeRef target;

  /**
   * The cached value of the '{@link #getParameters() <em>Parameters</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getParameters()
   * @generated
   * @ordered
   */
  protected ParameterList parameters;

  /**
   * The default value of the '{@link #getAlias() <em>Alias</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAlias()
   * @generated
   * @ordered
   */
  protected static final String ALIAS_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getAlias() <em>Alias</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAlias()
   * @generated
   * @ordered
   */
  protected String alias = ALIAS_EDEFAULT;

  /**
   * The cached value of the '{@link #getContextBlock() <em>Context Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getContextBlock()
   * @generated
   * @ordered
   */
  protected ContextBlock contextBlock;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected CreatorCallImpl()
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
    return BeeLangPackage.Literals.CREATOR_CALL;
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
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.CREATOR_CALL__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TypeRef getTarget()
  {
    return target;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetTarget(TypeRef newTarget, NotificationChain msgs)
  {
    TypeRef oldTarget = target;
    target = newTarget;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.CREATOR_CALL__TARGET, oldTarget, newTarget);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTarget(TypeRef newTarget)
  {
    if (newTarget != target)
    {
      NotificationChain msgs = null;
      if (target != null)
        msgs = ((InternalEObject)target).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.CREATOR_CALL__TARGET, null, msgs);
      if (newTarget != null)
        msgs = ((InternalEObject)newTarget).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.CREATOR_CALL__TARGET, null, msgs);
      msgs = basicSetTarget(newTarget, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.CREATOR_CALL__TARGET, newTarget, newTarget));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ParameterList getParameters()
  {
    return parameters;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetParameters(ParameterList newParameters, NotificationChain msgs)
  {
    ParameterList oldParameters = parameters;
    parameters = newParameters;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.CREATOR_CALL__PARAMETERS, oldParameters, newParameters);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setParameters(ParameterList newParameters)
  {
    if (newParameters != parameters)
    {
      NotificationChain msgs = null;
      if (parameters != null)
        msgs = ((InternalEObject)parameters).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.CREATOR_CALL__PARAMETERS, null, msgs);
      if (newParameters != null)
        msgs = ((InternalEObject)newParameters).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.CREATOR_CALL__PARAMETERS, null, msgs);
      msgs = basicSetParameters(newParameters, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.CREATOR_CALL__PARAMETERS, newParameters, newParameters));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getAlias()
  {
    return alias;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAlias(String newAlias)
  {
    String oldAlias = alias;
    alias = newAlias;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.CREATOR_CALL__ALIAS, oldAlias, alias));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ContextBlock getContextBlock()
  {
    return contextBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetContextBlock(ContextBlock newContextBlock, NotificationChain msgs)
  {
    ContextBlock oldContextBlock = contextBlock;
    contextBlock = newContextBlock;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.CREATOR_CALL__CONTEXT_BLOCK, oldContextBlock, newContextBlock);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setContextBlock(ContextBlock newContextBlock)
  {
    if (newContextBlock != contextBlock)
    {
      NotificationChain msgs = null;
      if (contextBlock != null)
        msgs = ((InternalEObject)contextBlock).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.CREATOR_CALL__CONTEXT_BLOCK, null, msgs);
      if (newContextBlock != null)
        msgs = ((InternalEObject)newContextBlock).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.CREATOR_CALL__CONTEXT_BLOCK, null, msgs);
      msgs = basicSetContextBlock(newContextBlock, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.CREATOR_CALL__CONTEXT_BLOCK, newContextBlock, newContextBlock));
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
      case BeeLangPackage.CREATOR_CALL__TARGET:
        return basicSetTarget(null, msgs);
      case BeeLangPackage.CREATOR_CALL__PARAMETERS:
        return basicSetParameters(null, msgs);
      case BeeLangPackage.CREATOR_CALL__CONTEXT_BLOCK:
        return basicSetContextBlock(null, msgs);
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
      case BeeLangPackage.CREATOR_CALL__NAME:
        return getName();
      case BeeLangPackage.CREATOR_CALL__TARGET:
        return getTarget();
      case BeeLangPackage.CREATOR_CALL__PARAMETERS:
        return getParameters();
      case BeeLangPackage.CREATOR_CALL__ALIAS:
        return getAlias();
      case BeeLangPackage.CREATOR_CALL__CONTEXT_BLOCK:
        return getContextBlock();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case BeeLangPackage.CREATOR_CALL__NAME:
        setName((String)newValue);
        return;
      case BeeLangPackage.CREATOR_CALL__TARGET:
        setTarget((TypeRef)newValue);
        return;
      case BeeLangPackage.CREATOR_CALL__PARAMETERS:
        setParameters((ParameterList)newValue);
        return;
      case BeeLangPackage.CREATOR_CALL__ALIAS:
        setAlias((String)newValue);
        return;
      case BeeLangPackage.CREATOR_CALL__CONTEXT_BLOCK:
        setContextBlock((ContextBlock)newValue);
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
      case BeeLangPackage.CREATOR_CALL__NAME:
        setName(NAME_EDEFAULT);
        return;
      case BeeLangPackage.CREATOR_CALL__TARGET:
        setTarget((TypeRef)null);
        return;
      case BeeLangPackage.CREATOR_CALL__PARAMETERS:
        setParameters((ParameterList)null);
        return;
      case BeeLangPackage.CREATOR_CALL__ALIAS:
        setAlias(ALIAS_EDEFAULT);
        return;
      case BeeLangPackage.CREATOR_CALL__CONTEXT_BLOCK:
        setContextBlock((ContextBlock)null);
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
      case BeeLangPackage.CREATOR_CALL__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case BeeLangPackage.CREATOR_CALL__TARGET:
        return target != null;
      case BeeLangPackage.CREATOR_CALL__PARAMETERS:
        return parameters != null;
      case BeeLangPackage.CREATOR_CALL__ALIAS:
        return ALIAS_EDEFAULT == null ? alias != null : !ALIAS_EDEFAULT.equals(alias);
      case BeeLangPackage.CREATOR_CALL__CONTEXT_BLOCK:
        return contextBlock != null;
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
    result.append(" (name: ");
    result.append(name);
    result.append(", alias: ");
    result.append(alias);
    result.append(')');
    return result.toString();
  }

} //CreatorCallImpl
