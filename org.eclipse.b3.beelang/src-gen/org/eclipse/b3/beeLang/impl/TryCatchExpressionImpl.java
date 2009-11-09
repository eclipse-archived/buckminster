/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang.impl;

import java.util.Collection;

import org.eclipse.b3.beeLang.BeeLangPackage;
import org.eclipse.b3.beeLang.CatchBlock;
import org.eclipse.b3.beeLang.Expression;
import org.eclipse.b3.beeLang.FinallyBlock;
import org.eclipse.b3.beeLang.TryCatchExpression;

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
 * An implementation of the model object '<em><b>Try Catch Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.impl.TryCatchExpressionImpl#getTryBlock <em>Try Block</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.TryCatchExpressionImpl#getCatch <em>Catch</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.TryCatchExpressionImpl#getFinally <em>Finally</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TryCatchExpressionImpl extends ExpressionImpl implements TryCatchExpression
{
  /**
   * The cached value of the '{@link #getTryBlock() <em>Try Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTryBlock()
   * @generated
   * @ordered
   */
  protected Expression tryBlock;

  /**
   * The cached value of the '{@link #getCatch() <em>Catch</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCatch()
   * @generated
   * @ordered
   */
  protected EList<CatchBlock> catch_;

  /**
   * The cached value of the '{@link #getFinally() <em>Finally</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFinally()
   * @generated
   * @ordered
   */
  protected FinallyBlock finally_;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected TryCatchExpressionImpl()
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
    return BeeLangPackage.Literals.TRY_CATCH_EXPRESSION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Expression getTryBlock()
  {
    return tryBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetTryBlock(Expression newTryBlock, NotificationChain msgs)
  {
    Expression oldTryBlock = tryBlock;
    tryBlock = newTryBlock;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.TRY_CATCH_EXPRESSION__TRY_BLOCK, oldTryBlock, newTryBlock);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTryBlock(Expression newTryBlock)
  {
    if (newTryBlock != tryBlock)
    {
      NotificationChain msgs = null;
      if (tryBlock != null)
        msgs = ((InternalEObject)tryBlock).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.TRY_CATCH_EXPRESSION__TRY_BLOCK, null, msgs);
      if (newTryBlock != null)
        msgs = ((InternalEObject)newTryBlock).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.TRY_CATCH_EXPRESSION__TRY_BLOCK, null, msgs);
      msgs = basicSetTryBlock(newTryBlock, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.TRY_CATCH_EXPRESSION__TRY_BLOCK, newTryBlock, newTryBlock));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<CatchBlock> getCatch()
  {
    if (catch_ == null)
    {
      catch_ = new EObjectContainmentEList<CatchBlock>(CatchBlock.class, this, BeeLangPackage.TRY_CATCH_EXPRESSION__CATCH);
    }
    return catch_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FinallyBlock getFinally()
  {
    return finally_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetFinally(FinallyBlock newFinally, NotificationChain msgs)
  {
    FinallyBlock oldFinally = finally_;
    finally_ = newFinally;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.TRY_CATCH_EXPRESSION__FINALLY, oldFinally, newFinally);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFinally(FinallyBlock newFinally)
  {
    if (newFinally != finally_)
    {
      NotificationChain msgs = null;
      if (finally_ != null)
        msgs = ((InternalEObject)finally_).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.TRY_CATCH_EXPRESSION__FINALLY, null, msgs);
      if (newFinally != null)
        msgs = ((InternalEObject)newFinally).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.TRY_CATCH_EXPRESSION__FINALLY, null, msgs);
      msgs = basicSetFinally(newFinally, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.TRY_CATCH_EXPRESSION__FINALLY, newFinally, newFinally));
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
      case BeeLangPackage.TRY_CATCH_EXPRESSION__TRY_BLOCK:
        return basicSetTryBlock(null, msgs);
      case BeeLangPackage.TRY_CATCH_EXPRESSION__CATCH:
        return ((InternalEList<?>)getCatch()).basicRemove(otherEnd, msgs);
      case BeeLangPackage.TRY_CATCH_EXPRESSION__FINALLY:
        return basicSetFinally(null, msgs);
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
      case BeeLangPackage.TRY_CATCH_EXPRESSION__TRY_BLOCK:
        return getTryBlock();
      case BeeLangPackage.TRY_CATCH_EXPRESSION__CATCH:
        return getCatch();
      case BeeLangPackage.TRY_CATCH_EXPRESSION__FINALLY:
        return getFinally();
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
      case BeeLangPackage.TRY_CATCH_EXPRESSION__TRY_BLOCK:
        setTryBlock((Expression)newValue);
        return;
      case BeeLangPackage.TRY_CATCH_EXPRESSION__CATCH:
        getCatch().clear();
        getCatch().addAll((Collection<? extends CatchBlock>)newValue);
        return;
      case BeeLangPackage.TRY_CATCH_EXPRESSION__FINALLY:
        setFinally((FinallyBlock)newValue);
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
      case BeeLangPackage.TRY_CATCH_EXPRESSION__TRY_BLOCK:
        setTryBlock((Expression)null);
        return;
      case BeeLangPackage.TRY_CATCH_EXPRESSION__CATCH:
        getCatch().clear();
        return;
      case BeeLangPackage.TRY_CATCH_EXPRESSION__FINALLY:
        setFinally((FinallyBlock)null);
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
      case BeeLangPackage.TRY_CATCH_EXPRESSION__TRY_BLOCK:
        return tryBlock != null;
      case BeeLangPackage.TRY_CATCH_EXPRESSION__CATCH:
        return catch_ != null && !catch_.isEmpty();
      case BeeLangPackage.TRY_CATCH_EXPRESSION__FINALLY:
        return finally_ != null;
    }
    return super.eIsSet(featureID);
  }

} //TryCatchExpressionImpl
