/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang.impl;

import org.eclipse.b3.beeLang.BeeLangPackage;
import org.eclipse.b3.beeLang.Expression;
import org.eclipse.b3.beeLang.IfExpression;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>If Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.impl.IfExpressionImpl#getCondition <em>Condition</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.IfExpressionImpl#getThenPart <em>Then Part</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.IfExpressionImpl#getElsePart <em>Else Part</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IfExpressionImpl extends ExpressionImpl implements IfExpression
{
  /**
   * The cached value of the '{@link #getCondition() <em>Condition</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCondition()
   * @generated
   * @ordered
   */
  protected Expression condition;

  /**
   * The cached value of the '{@link #getThenPart() <em>Then Part</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getThenPart()
   * @generated
   * @ordered
   */
  protected Expression thenPart;

  /**
   * The cached value of the '{@link #getElsePart() <em>Else Part</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getElsePart()
   * @generated
   * @ordered
   */
  protected Expression elsePart;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected IfExpressionImpl()
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
    return BeeLangPackage.Literals.IF_EXPRESSION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Expression getCondition()
  {
    return condition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetCondition(Expression newCondition, NotificationChain msgs)
  {
    Expression oldCondition = condition;
    condition = newCondition;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.IF_EXPRESSION__CONDITION, oldCondition, newCondition);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCondition(Expression newCondition)
  {
    if (newCondition != condition)
    {
      NotificationChain msgs = null;
      if (condition != null)
        msgs = ((InternalEObject)condition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.IF_EXPRESSION__CONDITION, null, msgs);
      if (newCondition != null)
        msgs = ((InternalEObject)newCondition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.IF_EXPRESSION__CONDITION, null, msgs);
      msgs = basicSetCondition(newCondition, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.IF_EXPRESSION__CONDITION, newCondition, newCondition));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Expression getThenPart()
  {
    return thenPart;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetThenPart(Expression newThenPart, NotificationChain msgs)
  {
    Expression oldThenPart = thenPart;
    thenPart = newThenPart;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.IF_EXPRESSION__THEN_PART, oldThenPart, newThenPart);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setThenPart(Expression newThenPart)
  {
    if (newThenPart != thenPart)
    {
      NotificationChain msgs = null;
      if (thenPart != null)
        msgs = ((InternalEObject)thenPart).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.IF_EXPRESSION__THEN_PART, null, msgs);
      if (newThenPart != null)
        msgs = ((InternalEObject)newThenPart).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.IF_EXPRESSION__THEN_PART, null, msgs);
      msgs = basicSetThenPart(newThenPart, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.IF_EXPRESSION__THEN_PART, newThenPart, newThenPart));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Expression getElsePart()
  {
    return elsePart;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetElsePart(Expression newElsePart, NotificationChain msgs)
  {
    Expression oldElsePart = elsePart;
    elsePart = newElsePart;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.IF_EXPRESSION__ELSE_PART, oldElsePart, newElsePart);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setElsePart(Expression newElsePart)
  {
    if (newElsePart != elsePart)
    {
      NotificationChain msgs = null;
      if (elsePart != null)
        msgs = ((InternalEObject)elsePart).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.IF_EXPRESSION__ELSE_PART, null, msgs);
      if (newElsePart != null)
        msgs = ((InternalEObject)newElsePart).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.IF_EXPRESSION__ELSE_PART, null, msgs);
      msgs = basicSetElsePart(newElsePart, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.IF_EXPRESSION__ELSE_PART, newElsePart, newElsePart));
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
      case BeeLangPackage.IF_EXPRESSION__CONDITION:
        return basicSetCondition(null, msgs);
      case BeeLangPackage.IF_EXPRESSION__THEN_PART:
        return basicSetThenPart(null, msgs);
      case BeeLangPackage.IF_EXPRESSION__ELSE_PART:
        return basicSetElsePart(null, msgs);
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
      case BeeLangPackage.IF_EXPRESSION__CONDITION:
        return getCondition();
      case BeeLangPackage.IF_EXPRESSION__THEN_PART:
        return getThenPart();
      case BeeLangPackage.IF_EXPRESSION__ELSE_PART:
        return getElsePart();
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
      case BeeLangPackage.IF_EXPRESSION__CONDITION:
        setCondition((Expression)newValue);
        return;
      case BeeLangPackage.IF_EXPRESSION__THEN_PART:
        setThenPart((Expression)newValue);
        return;
      case BeeLangPackage.IF_EXPRESSION__ELSE_PART:
        setElsePart((Expression)newValue);
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
      case BeeLangPackage.IF_EXPRESSION__CONDITION:
        setCondition((Expression)null);
        return;
      case BeeLangPackage.IF_EXPRESSION__THEN_PART:
        setThenPart((Expression)null);
        return;
      case BeeLangPackage.IF_EXPRESSION__ELSE_PART:
        setElsePart((Expression)null);
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
      case BeeLangPackage.IF_EXPRESSION__CONDITION:
        return condition != null;
      case BeeLangPackage.IF_EXPRESSION__THEN_PART:
        return thenPart != null;
      case BeeLangPackage.IF_EXPRESSION__ELSE_PART:
        return elsePart != null;
    }
    return super.eIsSet(featureID);
  }

} //IfExpressionImpl
