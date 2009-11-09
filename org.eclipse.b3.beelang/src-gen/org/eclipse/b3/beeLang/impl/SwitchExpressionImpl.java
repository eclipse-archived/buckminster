/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang.impl;

import java.util.Collection;

import org.eclipse.b3.beeLang.BeeLangPackage;
import org.eclipse.b3.beeLang.Case;
import org.eclipse.b3.beeLang.Expression;
import org.eclipse.b3.beeLang.SwitchExpression;

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
 * An implementation of the model object '<em><b>Switch Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.impl.SwitchExpressionImpl#getSwitchExpr <em>Switch Expr</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.SwitchExpressionImpl#getCase <em>Case</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SwitchExpressionImpl extends ExpressionImpl implements SwitchExpression
{
  /**
   * The cached value of the '{@link #getSwitchExpr() <em>Switch Expr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSwitchExpr()
   * @generated
   * @ordered
   */
  protected Expression switchExpr;

  /**
   * The cached value of the '{@link #getCase() <em>Case</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCase()
   * @generated
   * @ordered
   */
  protected EList<Case> case_;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected SwitchExpressionImpl()
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
    return BeeLangPackage.Literals.SWITCH_EXPRESSION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Expression getSwitchExpr()
  {
    return switchExpr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetSwitchExpr(Expression newSwitchExpr, NotificationChain msgs)
  {
    Expression oldSwitchExpr = switchExpr;
    switchExpr = newSwitchExpr;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.SWITCH_EXPRESSION__SWITCH_EXPR, oldSwitchExpr, newSwitchExpr);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSwitchExpr(Expression newSwitchExpr)
  {
    if (newSwitchExpr != switchExpr)
    {
      NotificationChain msgs = null;
      if (switchExpr != null)
        msgs = ((InternalEObject)switchExpr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.SWITCH_EXPRESSION__SWITCH_EXPR, null, msgs);
      if (newSwitchExpr != null)
        msgs = ((InternalEObject)newSwitchExpr).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.SWITCH_EXPRESSION__SWITCH_EXPR, null, msgs);
      msgs = basicSetSwitchExpr(newSwitchExpr, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.SWITCH_EXPRESSION__SWITCH_EXPR, newSwitchExpr, newSwitchExpr));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Case> getCase()
  {
    if (case_ == null)
    {
      case_ = new EObjectContainmentEList<Case>(Case.class, this, BeeLangPackage.SWITCH_EXPRESSION__CASE);
    }
    return case_;
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
      case BeeLangPackage.SWITCH_EXPRESSION__SWITCH_EXPR:
        return basicSetSwitchExpr(null, msgs);
      case BeeLangPackage.SWITCH_EXPRESSION__CASE:
        return ((InternalEList<?>)getCase()).basicRemove(otherEnd, msgs);
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
      case BeeLangPackage.SWITCH_EXPRESSION__SWITCH_EXPR:
        return getSwitchExpr();
      case BeeLangPackage.SWITCH_EXPRESSION__CASE:
        return getCase();
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
      case BeeLangPackage.SWITCH_EXPRESSION__SWITCH_EXPR:
        setSwitchExpr((Expression)newValue);
        return;
      case BeeLangPackage.SWITCH_EXPRESSION__CASE:
        getCase().clear();
        getCase().addAll((Collection<? extends Case>)newValue);
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
      case BeeLangPackage.SWITCH_EXPRESSION__SWITCH_EXPR:
        setSwitchExpr((Expression)null);
        return;
      case BeeLangPackage.SWITCH_EXPRESSION__CASE:
        getCase().clear();
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
      case BeeLangPackage.SWITCH_EXPRESSION__SWITCH_EXPR:
        return switchExpr != null;
      case BeeLangPackage.SWITCH_EXPRESSION__CASE:
        return case_ != null && !case_.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //SwitchExpressionImpl
