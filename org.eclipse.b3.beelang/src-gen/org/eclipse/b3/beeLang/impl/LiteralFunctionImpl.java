/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang.impl;

import org.eclipse.b3.beeLang.BeeLangPackage;
import org.eclipse.b3.beeLang.ClosureExpression;
import org.eclipse.b3.beeLang.LiteralFunction;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Literal Function</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.impl.LiteralFunctionImpl#getClosure <em>Closure</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LiteralFunctionImpl extends ExpressionImpl implements LiteralFunction
{
  /**
   * The cached value of the '{@link #getClosure() <em>Closure</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getClosure()
   * @generated
   * @ordered
   */
  protected ClosureExpression closure;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected LiteralFunctionImpl()
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
    return BeeLangPackage.Literals.LITERAL_FUNCTION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ClosureExpression getClosure()
  {
    return closure;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetClosure(ClosureExpression newClosure, NotificationChain msgs)
  {
    ClosureExpression oldClosure = closure;
    closure = newClosure;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.LITERAL_FUNCTION__CLOSURE, oldClosure, newClosure);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setClosure(ClosureExpression newClosure)
  {
    if (newClosure != closure)
    {
      NotificationChain msgs = null;
      if (closure != null)
        msgs = ((InternalEObject)closure).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.LITERAL_FUNCTION__CLOSURE, null, msgs);
      if (newClosure != null)
        msgs = ((InternalEObject)newClosure).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.LITERAL_FUNCTION__CLOSURE, null, msgs);
      msgs = basicSetClosure(newClosure, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.LITERAL_FUNCTION__CLOSURE, newClosure, newClosure));
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
      case BeeLangPackage.LITERAL_FUNCTION__CLOSURE:
        return basicSetClosure(null, msgs);
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
      case BeeLangPackage.LITERAL_FUNCTION__CLOSURE:
        return getClosure();
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
      case BeeLangPackage.LITERAL_FUNCTION__CLOSURE:
        setClosure((ClosureExpression)newValue);
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
      case BeeLangPackage.LITERAL_FUNCTION__CLOSURE:
        setClosure((ClosureExpression)null);
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
      case BeeLangPackage.LITERAL_FUNCTION__CLOSURE:
        return closure != null;
    }
    return super.eIsSet(featureID);
  }

} //LiteralFunctionImpl
