/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang.impl;

import org.eclipse.b3.beeLang.AdviceStatement;
import org.eclipse.b3.beeLang.AssignmentOperator;
import org.eclipse.b3.beeLang.BeeLangPackage;
import org.eclipse.b3.beeLang.CompoundConcern;
import org.eclipse.b3.beeLang.Expression;
import org.eclipse.b3.beeLang.QueryPath;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Advice Statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.impl.AdviceStatementImpl#getPath <em>Path</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.AdviceStatementImpl#getOp <em>Op</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.AdviceStatementImpl#getValue <em>Value</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.AdviceStatementImpl#getAdvice <em>Advice</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AdviceStatementImpl extends MinimalEObjectImpl.Container implements AdviceStatement
{
  /**
   * The cached value of the '{@link #getPath() <em>Path</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPath()
   * @generated
   * @ordered
   */
  protected QueryPath path;

  /**
   * The default value of the '{@link #getOp() <em>Op</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOp()
   * @generated
   * @ordered
   */
  protected static final AssignmentOperator OP_EDEFAULT = AssignmentOperator.SET;

  /**
   * The cached value of the '{@link #getOp() <em>Op</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOp()
   * @generated
   * @ordered
   */
  protected AssignmentOperator op = OP_EDEFAULT;

  /**
   * The cached value of the '{@link #getValue() <em>Value</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getValue()
   * @generated
   * @ordered
   */
  protected Expression value;

  /**
   * The cached value of the '{@link #getAdvice() <em>Advice</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAdvice()
   * @generated
   * @ordered
   */
  protected CompoundConcern advice;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected AdviceStatementImpl()
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
    return BeeLangPackage.Literals.ADVICE_STATEMENT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public QueryPath getPath()
  {
    return path;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetPath(QueryPath newPath, NotificationChain msgs)
  {
    QueryPath oldPath = path;
    path = newPath;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.ADVICE_STATEMENT__PATH, oldPath, newPath);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPath(QueryPath newPath)
  {
    if (newPath != path)
    {
      NotificationChain msgs = null;
      if (path != null)
        msgs = ((InternalEObject)path).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.ADVICE_STATEMENT__PATH, null, msgs);
      if (newPath != null)
        msgs = ((InternalEObject)newPath).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.ADVICE_STATEMENT__PATH, null, msgs);
      msgs = basicSetPath(newPath, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.ADVICE_STATEMENT__PATH, newPath, newPath));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AssignmentOperator getOp()
  {
    return op;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setOp(AssignmentOperator newOp)
  {
    AssignmentOperator oldOp = op;
    op = newOp == null ? OP_EDEFAULT : newOp;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.ADVICE_STATEMENT__OP, oldOp, op));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Expression getValue()
  {
    return value;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetValue(Expression newValue, NotificationChain msgs)
  {
    Expression oldValue = value;
    value = newValue;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.ADVICE_STATEMENT__VALUE, oldValue, newValue);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setValue(Expression newValue)
  {
    if (newValue != value)
    {
      NotificationChain msgs = null;
      if (value != null)
        msgs = ((InternalEObject)value).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.ADVICE_STATEMENT__VALUE, null, msgs);
      if (newValue != null)
        msgs = ((InternalEObject)newValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.ADVICE_STATEMENT__VALUE, null, msgs);
      msgs = basicSetValue(newValue, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.ADVICE_STATEMENT__VALUE, newValue, newValue));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CompoundConcern getAdvice()
  {
    return advice;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetAdvice(CompoundConcern newAdvice, NotificationChain msgs)
  {
    CompoundConcern oldAdvice = advice;
    advice = newAdvice;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.ADVICE_STATEMENT__ADVICE, oldAdvice, newAdvice);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAdvice(CompoundConcern newAdvice)
  {
    if (newAdvice != advice)
    {
      NotificationChain msgs = null;
      if (advice != null)
        msgs = ((InternalEObject)advice).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.ADVICE_STATEMENT__ADVICE, null, msgs);
      if (newAdvice != null)
        msgs = ((InternalEObject)newAdvice).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.ADVICE_STATEMENT__ADVICE, null, msgs);
      msgs = basicSetAdvice(newAdvice, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.ADVICE_STATEMENT__ADVICE, newAdvice, newAdvice));
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
      case BeeLangPackage.ADVICE_STATEMENT__PATH:
        return basicSetPath(null, msgs);
      case BeeLangPackage.ADVICE_STATEMENT__VALUE:
        return basicSetValue(null, msgs);
      case BeeLangPackage.ADVICE_STATEMENT__ADVICE:
        return basicSetAdvice(null, msgs);
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
      case BeeLangPackage.ADVICE_STATEMENT__PATH:
        return getPath();
      case BeeLangPackage.ADVICE_STATEMENT__OP:
        return getOp();
      case BeeLangPackage.ADVICE_STATEMENT__VALUE:
        return getValue();
      case BeeLangPackage.ADVICE_STATEMENT__ADVICE:
        return getAdvice();
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
      case BeeLangPackage.ADVICE_STATEMENT__PATH:
        setPath((QueryPath)newValue);
        return;
      case BeeLangPackage.ADVICE_STATEMENT__OP:
        setOp((AssignmentOperator)newValue);
        return;
      case BeeLangPackage.ADVICE_STATEMENT__VALUE:
        setValue((Expression)newValue);
        return;
      case BeeLangPackage.ADVICE_STATEMENT__ADVICE:
        setAdvice((CompoundConcern)newValue);
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
      case BeeLangPackage.ADVICE_STATEMENT__PATH:
        setPath((QueryPath)null);
        return;
      case BeeLangPackage.ADVICE_STATEMENT__OP:
        setOp(OP_EDEFAULT);
        return;
      case BeeLangPackage.ADVICE_STATEMENT__VALUE:
        setValue((Expression)null);
        return;
      case BeeLangPackage.ADVICE_STATEMENT__ADVICE:
        setAdvice((CompoundConcern)null);
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
      case BeeLangPackage.ADVICE_STATEMENT__PATH:
        return path != null;
      case BeeLangPackage.ADVICE_STATEMENT__OP:
        return op != OP_EDEFAULT;
      case BeeLangPackage.ADVICE_STATEMENT__VALUE:
        return value != null;
      case BeeLangPackage.ADVICE_STATEMENT__ADVICE:
        return advice != null;
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
    result.append(" (op: ");
    result.append(op);
    result.append(')');
    return result.toString();
  }

} //AdviceStatementImpl
