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
import org.eclipse.b3.beeLang.Statements;
import org.eclipse.b3.beeLang.SwitchStatement;

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
 * An implementation of the model object '<em><b>Switch Statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.impl.SwitchStatementImpl#getSwitchExpr <em>Switch Expr</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.SwitchStatementImpl#getCase <em>Case</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.SwitchStatementImpl#getStatements <em>Statements</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SwitchStatementImpl extends StatementImpl implements SwitchStatement
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
  protected SwitchStatementImpl()
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
    return BeeLangPackage.Literals.SWITCH_STATEMENT;
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.SWITCH_STATEMENT__SWITCH_EXPR, oldSwitchExpr, newSwitchExpr);
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
        msgs = ((InternalEObject)switchExpr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.SWITCH_STATEMENT__SWITCH_EXPR, null, msgs);
      if (newSwitchExpr != null)
        msgs = ((InternalEObject)newSwitchExpr).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.SWITCH_STATEMENT__SWITCH_EXPR, null, msgs);
      msgs = basicSetSwitchExpr(newSwitchExpr, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.SWITCH_STATEMENT__SWITCH_EXPR, newSwitchExpr, newSwitchExpr));
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
      case_ = new EObjectContainmentEList<Case>(Case.class, this, BeeLangPackage.SWITCH_STATEMENT__CASE);
    }
    return case_;
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.SWITCH_STATEMENT__STATEMENTS, oldStatements, newStatements);
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
        msgs = ((InternalEObject)statements).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.SWITCH_STATEMENT__STATEMENTS, null, msgs);
      if (newStatements != null)
        msgs = ((InternalEObject)newStatements).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.SWITCH_STATEMENT__STATEMENTS, null, msgs);
      msgs = basicSetStatements(newStatements, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.SWITCH_STATEMENT__STATEMENTS, newStatements, newStatements));
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
      case BeeLangPackage.SWITCH_STATEMENT__SWITCH_EXPR:
        return basicSetSwitchExpr(null, msgs);
      case BeeLangPackage.SWITCH_STATEMENT__CASE:
        return ((InternalEList<?>)getCase()).basicRemove(otherEnd, msgs);
      case BeeLangPackage.SWITCH_STATEMENT__STATEMENTS:
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
      case BeeLangPackage.SWITCH_STATEMENT__SWITCH_EXPR:
        return getSwitchExpr();
      case BeeLangPackage.SWITCH_STATEMENT__CASE:
        return getCase();
      case BeeLangPackage.SWITCH_STATEMENT__STATEMENTS:
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
      case BeeLangPackage.SWITCH_STATEMENT__SWITCH_EXPR:
        setSwitchExpr((Expression)newValue);
        return;
      case BeeLangPackage.SWITCH_STATEMENT__CASE:
        getCase().clear();
        getCase().addAll((Collection<? extends Case>)newValue);
        return;
      case BeeLangPackage.SWITCH_STATEMENT__STATEMENTS:
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
      case BeeLangPackage.SWITCH_STATEMENT__SWITCH_EXPR:
        setSwitchExpr((Expression)null);
        return;
      case BeeLangPackage.SWITCH_STATEMENT__CASE:
        getCase().clear();
        return;
      case BeeLangPackage.SWITCH_STATEMENT__STATEMENTS:
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
      case BeeLangPackage.SWITCH_STATEMENT__SWITCH_EXPR:
        return switchExpr != null;
      case BeeLangPackage.SWITCH_STATEMENT__CASE:
        return case_ != null && !case_.isEmpty();
      case BeeLangPackage.SWITCH_STATEMENT__STATEMENTS:
        return statements != null;
    }
    return super.eIsSet(featureID);
  }

} //SwitchStatementImpl
