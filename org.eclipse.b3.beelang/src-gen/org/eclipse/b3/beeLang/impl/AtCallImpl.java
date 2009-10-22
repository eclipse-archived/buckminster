/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang.impl;

import java.util.Collection;

import org.eclipse.b3.beeLang.AtCall;
import org.eclipse.b3.beeLang.BeeLangPackage;
import org.eclipse.b3.beeLang.Expression;
import org.eclipse.b3.beeLang.Parameter;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>At Call</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.impl.AtCallImpl#getTarget <em>Target</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.AtCallImpl#getIndex <em>Index</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.AtCallImpl#getParams <em>Params</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AtCallImpl extends ExpressionImpl implements AtCall
{
  /**
   * The cached value of the '{@link #getTarget() <em>Target</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTarget()
   * @generated
   * @ordered
   */
  protected Expression target;

  /**
   * The cached value of the '{@link #getIndex() <em>Index</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIndex()
   * @generated
   * @ordered
   */
  protected Expression index;

  /**
   * The cached value of the '{@link #getParams() <em>Params</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getParams()
   * @generated
   * @ordered
   */
  protected EList<Parameter> params;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected AtCallImpl()
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
    return BeeLangPackage.Literals.AT_CALL;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Expression getTarget()
  {
    return target;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetTarget(Expression newTarget, NotificationChain msgs)
  {
    Expression oldTarget = target;
    target = newTarget;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.AT_CALL__TARGET, oldTarget, newTarget);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTarget(Expression newTarget)
  {
    if (newTarget != target)
    {
      NotificationChain msgs = null;
      if (target != null)
        msgs = ((InternalEObject)target).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.AT_CALL__TARGET, null, msgs);
      if (newTarget != null)
        msgs = ((InternalEObject)newTarget).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.AT_CALL__TARGET, null, msgs);
      msgs = basicSetTarget(newTarget, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.AT_CALL__TARGET, newTarget, newTarget));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Expression getIndex()
  {
    return index;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetIndex(Expression newIndex, NotificationChain msgs)
  {
    Expression oldIndex = index;
    index = newIndex;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.AT_CALL__INDEX, oldIndex, newIndex);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setIndex(Expression newIndex)
  {
    if (newIndex != index)
    {
      NotificationChain msgs = null;
      if (index != null)
        msgs = ((InternalEObject)index).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.AT_CALL__INDEX, null, msgs);
      if (newIndex != null)
        msgs = ((InternalEObject)newIndex).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.AT_CALL__INDEX, null, msgs);
      msgs = basicSetIndex(newIndex, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.AT_CALL__INDEX, newIndex, newIndex));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Parameter> getParams()
  {
    if (params == null)
    {
      params = new EObjectContainmentEList<Parameter>(Parameter.class, this, BeeLangPackage.AT_CALL__PARAMS);
    }
    return params;
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
      case BeeLangPackage.AT_CALL__TARGET:
        return basicSetTarget(null, msgs);
      case BeeLangPackage.AT_CALL__INDEX:
        return basicSetIndex(null, msgs);
      case BeeLangPackage.AT_CALL__PARAMS:
        return ((InternalEList<?>)getParams()).basicRemove(otherEnd, msgs);
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
      case BeeLangPackage.AT_CALL__TARGET:
        return getTarget();
      case BeeLangPackage.AT_CALL__INDEX:
        return getIndex();
      case BeeLangPackage.AT_CALL__PARAMS:
        return getParams();
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
      case BeeLangPackage.AT_CALL__TARGET:
        setTarget((Expression)newValue);
        return;
      case BeeLangPackage.AT_CALL__INDEX:
        setIndex((Expression)newValue);
        return;
      case BeeLangPackage.AT_CALL__PARAMS:
        getParams().clear();
        getParams().addAll((Collection<? extends Parameter>)newValue);
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
      case BeeLangPackage.AT_CALL__TARGET:
        setTarget((Expression)null);
        return;
      case BeeLangPackage.AT_CALL__INDEX:
        setIndex((Expression)null);
        return;
      case BeeLangPackage.AT_CALL__PARAMS:
        getParams().clear();
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
      case BeeLangPackage.AT_CALL__TARGET:
        return target != null;
      case BeeLangPackage.AT_CALL__INDEX:
        return index != null;
      case BeeLangPackage.AT_CALL__PARAMS:
        return params != null && !params.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //AtCallImpl
