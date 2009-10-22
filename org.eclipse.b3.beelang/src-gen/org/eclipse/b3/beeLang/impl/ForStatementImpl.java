/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang.impl;

import org.eclipse.b3.beeLang.BeeLangPackage;
import org.eclipse.b3.beeLang.Expression;
import org.eclipse.b3.beeLang.ForStatement;
import org.eclipse.b3.beeLang.Statement;
import org.eclipse.b3.beeLang.VarExpressionList;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>For Statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.impl.ForStatementImpl#getInit <em>Init</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.ForStatementImpl#getCond <em>Cond</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.ForStatementImpl#getIterate <em>Iterate</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.ForStatementImpl#getBody <em>Body</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ForStatementImpl extends StatementImpl implements ForStatement
{
  /**
   * The cached value of the '{@link #getInit() <em>Init</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getInit()
   * @generated
   * @ordered
   */
  protected VarExpressionList init;

  /**
   * The cached value of the '{@link #getCond() <em>Cond</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCond()
   * @generated
   * @ordered
   */
  protected Expression cond;

  /**
   * The cached value of the '{@link #getIterate() <em>Iterate</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIterate()
   * @generated
   * @ordered
   */
  protected Expression iterate;

  /**
   * The cached value of the '{@link #getBody() <em>Body</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBody()
   * @generated
   * @ordered
   */
  protected Statement body;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ForStatementImpl()
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
    return BeeLangPackage.Literals.FOR_STATEMENT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public VarExpressionList getInit()
  {
    return init;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetInit(VarExpressionList newInit, NotificationChain msgs)
  {
    VarExpressionList oldInit = init;
    init = newInit;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.FOR_STATEMENT__INIT, oldInit, newInit);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setInit(VarExpressionList newInit)
  {
    if (newInit != init)
    {
      NotificationChain msgs = null;
      if (init != null)
        msgs = ((InternalEObject)init).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.FOR_STATEMENT__INIT, null, msgs);
      if (newInit != null)
        msgs = ((InternalEObject)newInit).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.FOR_STATEMENT__INIT, null, msgs);
      msgs = basicSetInit(newInit, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.FOR_STATEMENT__INIT, newInit, newInit));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Expression getCond()
  {
    return cond;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetCond(Expression newCond, NotificationChain msgs)
  {
    Expression oldCond = cond;
    cond = newCond;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.FOR_STATEMENT__COND, oldCond, newCond);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCond(Expression newCond)
  {
    if (newCond != cond)
    {
      NotificationChain msgs = null;
      if (cond != null)
        msgs = ((InternalEObject)cond).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.FOR_STATEMENT__COND, null, msgs);
      if (newCond != null)
        msgs = ((InternalEObject)newCond).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.FOR_STATEMENT__COND, null, msgs);
      msgs = basicSetCond(newCond, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.FOR_STATEMENT__COND, newCond, newCond));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Expression getIterate()
  {
    return iterate;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetIterate(Expression newIterate, NotificationChain msgs)
  {
    Expression oldIterate = iterate;
    iterate = newIterate;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.FOR_STATEMENT__ITERATE, oldIterate, newIterate);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setIterate(Expression newIterate)
  {
    if (newIterate != iterate)
    {
      NotificationChain msgs = null;
      if (iterate != null)
        msgs = ((InternalEObject)iterate).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.FOR_STATEMENT__ITERATE, null, msgs);
      if (newIterate != null)
        msgs = ((InternalEObject)newIterate).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.FOR_STATEMENT__ITERATE, null, msgs);
      msgs = basicSetIterate(newIterate, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.FOR_STATEMENT__ITERATE, newIterate, newIterate));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Statement getBody()
  {
    return body;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetBody(Statement newBody, NotificationChain msgs)
  {
    Statement oldBody = body;
    body = newBody;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.FOR_STATEMENT__BODY, oldBody, newBody);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setBody(Statement newBody)
  {
    if (newBody != body)
    {
      NotificationChain msgs = null;
      if (body != null)
        msgs = ((InternalEObject)body).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.FOR_STATEMENT__BODY, null, msgs);
      if (newBody != null)
        msgs = ((InternalEObject)newBody).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.FOR_STATEMENT__BODY, null, msgs);
      msgs = basicSetBody(newBody, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.FOR_STATEMENT__BODY, newBody, newBody));
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
      case BeeLangPackage.FOR_STATEMENT__INIT:
        return basicSetInit(null, msgs);
      case BeeLangPackage.FOR_STATEMENT__COND:
        return basicSetCond(null, msgs);
      case BeeLangPackage.FOR_STATEMENT__ITERATE:
        return basicSetIterate(null, msgs);
      case BeeLangPackage.FOR_STATEMENT__BODY:
        return basicSetBody(null, msgs);
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
      case BeeLangPackage.FOR_STATEMENT__INIT:
        return getInit();
      case BeeLangPackage.FOR_STATEMENT__COND:
        return getCond();
      case BeeLangPackage.FOR_STATEMENT__ITERATE:
        return getIterate();
      case BeeLangPackage.FOR_STATEMENT__BODY:
        return getBody();
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
      case BeeLangPackage.FOR_STATEMENT__INIT:
        setInit((VarExpressionList)newValue);
        return;
      case BeeLangPackage.FOR_STATEMENT__COND:
        setCond((Expression)newValue);
        return;
      case BeeLangPackage.FOR_STATEMENT__ITERATE:
        setIterate((Expression)newValue);
        return;
      case BeeLangPackage.FOR_STATEMENT__BODY:
        setBody((Statement)newValue);
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
      case BeeLangPackage.FOR_STATEMENT__INIT:
        setInit((VarExpressionList)null);
        return;
      case BeeLangPackage.FOR_STATEMENT__COND:
        setCond((Expression)null);
        return;
      case BeeLangPackage.FOR_STATEMENT__ITERATE:
        setIterate((Expression)null);
        return;
      case BeeLangPackage.FOR_STATEMENT__BODY:
        setBody((Statement)null);
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
      case BeeLangPackage.FOR_STATEMENT__INIT:
        return init != null;
      case BeeLangPackage.FOR_STATEMENT__COND:
        return cond != null;
      case BeeLangPackage.FOR_STATEMENT__ITERATE:
        return iterate != null;
      case BeeLangPackage.FOR_STATEMENT__BODY:
        return body != null;
    }
    return super.eIsSet(featureID);
  }

} //ForStatementImpl
