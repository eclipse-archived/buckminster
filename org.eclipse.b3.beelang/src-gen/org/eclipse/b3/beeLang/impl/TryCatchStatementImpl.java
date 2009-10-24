/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang.impl;

import org.eclipse.b3.beeLang.BeeLangPackage;
import org.eclipse.b3.beeLang.CatchBlock;
import org.eclipse.b3.beeLang.CompoundStatement;
import org.eclipse.b3.beeLang.FinallyBlock;
import org.eclipse.b3.beeLang.TryCatchStatement;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Try Catch Statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.impl.TryCatchStatementImpl#getTryBlock <em>Try Block</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.TryCatchStatementImpl#getCatchBlock <em>Catch Block</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.TryCatchStatementImpl#getFinallyBlock <em>Finally Block</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.TryCatchStatementImpl#getFinally <em>Finally</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TryCatchStatementImpl extends StatementImpl implements TryCatchStatement
{
  /**
   * The cached value of the '{@link #getTryBlock() <em>Try Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTryBlock()
   * @generated
   * @ordered
   */
  protected CompoundStatement tryBlock;

  /**
   * The cached value of the '{@link #getCatchBlock() <em>Catch Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCatchBlock()
   * @generated
   * @ordered
   */
  protected CatchBlock catchBlock;

  /**
   * The cached value of the '{@link #getFinallyBlock() <em>Finally Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFinallyBlock()
   * @generated
   * @ordered
   */
  protected FinallyBlock finallyBlock;

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
  protected TryCatchStatementImpl()
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
    return BeeLangPackage.Literals.TRY_CATCH_STATEMENT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CompoundStatement getTryBlock()
  {
    return tryBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetTryBlock(CompoundStatement newTryBlock, NotificationChain msgs)
  {
    CompoundStatement oldTryBlock = tryBlock;
    tryBlock = newTryBlock;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.TRY_CATCH_STATEMENT__TRY_BLOCK, oldTryBlock, newTryBlock);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTryBlock(CompoundStatement newTryBlock)
  {
    if (newTryBlock != tryBlock)
    {
      NotificationChain msgs = null;
      if (tryBlock != null)
        msgs = ((InternalEObject)tryBlock).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.TRY_CATCH_STATEMENT__TRY_BLOCK, null, msgs);
      if (newTryBlock != null)
        msgs = ((InternalEObject)newTryBlock).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.TRY_CATCH_STATEMENT__TRY_BLOCK, null, msgs);
      msgs = basicSetTryBlock(newTryBlock, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.TRY_CATCH_STATEMENT__TRY_BLOCK, newTryBlock, newTryBlock));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CatchBlock getCatchBlock()
  {
    return catchBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetCatchBlock(CatchBlock newCatchBlock, NotificationChain msgs)
  {
    CatchBlock oldCatchBlock = catchBlock;
    catchBlock = newCatchBlock;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.TRY_CATCH_STATEMENT__CATCH_BLOCK, oldCatchBlock, newCatchBlock);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCatchBlock(CatchBlock newCatchBlock)
  {
    if (newCatchBlock != catchBlock)
    {
      NotificationChain msgs = null;
      if (catchBlock != null)
        msgs = ((InternalEObject)catchBlock).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.TRY_CATCH_STATEMENT__CATCH_BLOCK, null, msgs);
      if (newCatchBlock != null)
        msgs = ((InternalEObject)newCatchBlock).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.TRY_CATCH_STATEMENT__CATCH_BLOCK, null, msgs);
      msgs = basicSetCatchBlock(newCatchBlock, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.TRY_CATCH_STATEMENT__CATCH_BLOCK, newCatchBlock, newCatchBlock));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FinallyBlock getFinallyBlock()
  {
    return finallyBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetFinallyBlock(FinallyBlock newFinallyBlock, NotificationChain msgs)
  {
    FinallyBlock oldFinallyBlock = finallyBlock;
    finallyBlock = newFinallyBlock;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.TRY_CATCH_STATEMENT__FINALLY_BLOCK, oldFinallyBlock, newFinallyBlock);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFinallyBlock(FinallyBlock newFinallyBlock)
  {
    if (newFinallyBlock != finallyBlock)
    {
      NotificationChain msgs = null;
      if (finallyBlock != null)
        msgs = ((InternalEObject)finallyBlock).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.TRY_CATCH_STATEMENT__FINALLY_BLOCK, null, msgs);
      if (newFinallyBlock != null)
        msgs = ((InternalEObject)newFinallyBlock).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.TRY_CATCH_STATEMENT__FINALLY_BLOCK, null, msgs);
      msgs = basicSetFinallyBlock(newFinallyBlock, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.TRY_CATCH_STATEMENT__FINALLY_BLOCK, newFinallyBlock, newFinallyBlock));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.TRY_CATCH_STATEMENT__FINALLY, oldFinally, newFinally);
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
        msgs = ((InternalEObject)finally_).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.TRY_CATCH_STATEMENT__FINALLY, null, msgs);
      if (newFinally != null)
        msgs = ((InternalEObject)newFinally).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.TRY_CATCH_STATEMENT__FINALLY, null, msgs);
      msgs = basicSetFinally(newFinally, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.TRY_CATCH_STATEMENT__FINALLY, newFinally, newFinally));
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
      case BeeLangPackage.TRY_CATCH_STATEMENT__TRY_BLOCK:
        return basicSetTryBlock(null, msgs);
      case BeeLangPackage.TRY_CATCH_STATEMENT__CATCH_BLOCK:
        return basicSetCatchBlock(null, msgs);
      case BeeLangPackage.TRY_CATCH_STATEMENT__FINALLY_BLOCK:
        return basicSetFinallyBlock(null, msgs);
      case BeeLangPackage.TRY_CATCH_STATEMENT__FINALLY:
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
      case BeeLangPackage.TRY_CATCH_STATEMENT__TRY_BLOCK:
        return getTryBlock();
      case BeeLangPackage.TRY_CATCH_STATEMENT__CATCH_BLOCK:
        return getCatchBlock();
      case BeeLangPackage.TRY_CATCH_STATEMENT__FINALLY_BLOCK:
        return getFinallyBlock();
      case BeeLangPackage.TRY_CATCH_STATEMENT__FINALLY:
        return getFinally();
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
      case BeeLangPackage.TRY_CATCH_STATEMENT__TRY_BLOCK:
        setTryBlock((CompoundStatement)newValue);
        return;
      case BeeLangPackage.TRY_CATCH_STATEMENT__CATCH_BLOCK:
        setCatchBlock((CatchBlock)newValue);
        return;
      case BeeLangPackage.TRY_CATCH_STATEMENT__FINALLY_BLOCK:
        setFinallyBlock((FinallyBlock)newValue);
        return;
      case BeeLangPackage.TRY_CATCH_STATEMENT__FINALLY:
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
      case BeeLangPackage.TRY_CATCH_STATEMENT__TRY_BLOCK:
        setTryBlock((CompoundStatement)null);
        return;
      case BeeLangPackage.TRY_CATCH_STATEMENT__CATCH_BLOCK:
        setCatchBlock((CatchBlock)null);
        return;
      case BeeLangPackage.TRY_CATCH_STATEMENT__FINALLY_BLOCK:
        setFinallyBlock((FinallyBlock)null);
        return;
      case BeeLangPackage.TRY_CATCH_STATEMENT__FINALLY:
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
      case BeeLangPackage.TRY_CATCH_STATEMENT__TRY_BLOCK:
        return tryBlock != null;
      case BeeLangPackage.TRY_CATCH_STATEMENT__CATCH_BLOCK:
        return catchBlock != null;
      case BeeLangPackage.TRY_CATCH_STATEMENT__FINALLY_BLOCK:
        return finallyBlock != null;
      case BeeLangPackage.TRY_CATCH_STATEMENT__FINALLY:
        return finally_ != null;
    }
    return super.eIsSet(featureID);
  }

} //TryCatchStatementImpl
