/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang.impl;

import org.eclipse.b3.beeLang.BeeLangPackage;
import org.eclipse.b3.beeLang.CompoundStatement;
import org.eclipse.b3.beeLang.FinallyBlock;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Finally Block</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.impl.FinallyBlockImpl#getFinallyBlock <em>Finally Block</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FinallyBlockImpl extends MinimalEObjectImpl.Container implements FinallyBlock
{
  /**
   * The cached value of the '{@link #getFinallyBlock() <em>Finally Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFinallyBlock()
   * @generated
   * @ordered
   */
  protected CompoundStatement finallyBlock;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected FinallyBlockImpl()
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
    return BeeLangPackage.Literals.FINALLY_BLOCK;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CompoundStatement getFinallyBlock()
  {
    return finallyBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetFinallyBlock(CompoundStatement newFinallyBlock, NotificationChain msgs)
  {
    CompoundStatement oldFinallyBlock = finallyBlock;
    finallyBlock = newFinallyBlock;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.FINALLY_BLOCK__FINALLY_BLOCK, oldFinallyBlock, newFinallyBlock);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFinallyBlock(CompoundStatement newFinallyBlock)
  {
    if (newFinallyBlock != finallyBlock)
    {
      NotificationChain msgs = null;
      if (finallyBlock != null)
        msgs = ((InternalEObject)finallyBlock).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.FINALLY_BLOCK__FINALLY_BLOCK, null, msgs);
      if (newFinallyBlock != null)
        msgs = ((InternalEObject)newFinallyBlock).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.FINALLY_BLOCK__FINALLY_BLOCK, null, msgs);
      msgs = basicSetFinallyBlock(newFinallyBlock, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.FINALLY_BLOCK__FINALLY_BLOCK, newFinallyBlock, newFinallyBlock));
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
      case BeeLangPackage.FINALLY_BLOCK__FINALLY_BLOCK:
        return basicSetFinallyBlock(null, msgs);
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
      case BeeLangPackage.FINALLY_BLOCK__FINALLY_BLOCK:
        return getFinallyBlock();
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
      case BeeLangPackage.FINALLY_BLOCK__FINALLY_BLOCK:
        setFinallyBlock((CompoundStatement)newValue);
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
      case BeeLangPackage.FINALLY_BLOCK__FINALLY_BLOCK:
        setFinallyBlock((CompoundStatement)null);
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
      case BeeLangPackage.FINALLY_BLOCK__FINALLY_BLOCK:
        return finallyBlock != null;
    }
    return super.eIsSet(featureID);
  }

} //FinallyBlockImpl
