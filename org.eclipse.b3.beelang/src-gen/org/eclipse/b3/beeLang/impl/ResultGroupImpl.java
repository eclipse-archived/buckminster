/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang.impl;

import java.util.Collection;

import org.eclipse.b3.beeLang.BeeLangPackage;
import org.eclipse.b3.beeLang.Closure;
import org.eclipse.b3.beeLang.Prerequisite;
import org.eclipse.b3.beeLang.ResultGroup;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Result Group</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.impl.ResultGroupImpl#getAsserts <em>Asserts</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.ResultGroupImpl#getPrerequisites <em>Prerequisites</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.ResultGroupImpl#getClosure <em>Closure</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ResultGroupImpl extends MinimalEObjectImpl.Container implements ResultGroup
{
  /**
   * The cached value of the '{@link #getAsserts() <em>Asserts</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAsserts()
   * @generated
   * @ordered
   */
  protected EList<EObject> asserts;

  /**
   * The cached value of the '{@link #getPrerequisites() <em>Prerequisites</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPrerequisites()
   * @generated
   * @ordered
   */
  protected EList<Prerequisite> prerequisites;

  /**
   * The cached value of the '{@link #getClosure() <em>Closure</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getClosure()
   * @generated
   * @ordered
   */
  protected Closure closure;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ResultGroupImpl()
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
    return BeeLangPackage.Literals.RESULT_GROUP;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<EObject> getAsserts()
  {
    if (asserts == null)
    {
      asserts = new EObjectContainmentEList<EObject>(EObject.class, this, BeeLangPackage.RESULT_GROUP__ASSERTS);
    }
    return asserts;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Prerequisite> getPrerequisites()
  {
    if (prerequisites == null)
    {
      prerequisites = new EObjectContainmentEList<Prerequisite>(Prerequisite.class, this, BeeLangPackage.RESULT_GROUP__PREREQUISITES);
    }
    return prerequisites;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Closure getClosure()
  {
    return closure;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetClosure(Closure newClosure, NotificationChain msgs)
  {
    Closure oldClosure = closure;
    closure = newClosure;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.RESULT_GROUP__CLOSURE, oldClosure, newClosure);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setClosure(Closure newClosure)
  {
    if (newClosure != closure)
    {
      NotificationChain msgs = null;
      if (closure != null)
        msgs = ((InternalEObject)closure).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.RESULT_GROUP__CLOSURE, null, msgs);
      if (newClosure != null)
        msgs = ((InternalEObject)newClosure).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.RESULT_GROUP__CLOSURE, null, msgs);
      msgs = basicSetClosure(newClosure, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.RESULT_GROUP__CLOSURE, newClosure, newClosure));
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
      case BeeLangPackage.RESULT_GROUP__ASSERTS:
        return ((InternalEList<?>)getAsserts()).basicRemove(otherEnd, msgs);
      case BeeLangPackage.RESULT_GROUP__PREREQUISITES:
        return ((InternalEList<?>)getPrerequisites()).basicRemove(otherEnd, msgs);
      case BeeLangPackage.RESULT_GROUP__CLOSURE:
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
      case BeeLangPackage.RESULT_GROUP__ASSERTS:
        return getAsserts();
      case BeeLangPackage.RESULT_GROUP__PREREQUISITES:
        return getPrerequisites();
      case BeeLangPackage.RESULT_GROUP__CLOSURE:
        return getClosure();
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
      case BeeLangPackage.RESULT_GROUP__ASSERTS:
        getAsserts().clear();
        getAsserts().addAll((Collection<? extends EObject>)newValue);
        return;
      case BeeLangPackage.RESULT_GROUP__PREREQUISITES:
        getPrerequisites().clear();
        getPrerequisites().addAll((Collection<? extends Prerequisite>)newValue);
        return;
      case BeeLangPackage.RESULT_GROUP__CLOSURE:
        setClosure((Closure)newValue);
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
      case BeeLangPackage.RESULT_GROUP__ASSERTS:
        getAsserts().clear();
        return;
      case BeeLangPackage.RESULT_GROUP__PREREQUISITES:
        getPrerequisites().clear();
        return;
      case BeeLangPackage.RESULT_GROUP__CLOSURE:
        setClosure((Closure)null);
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
      case BeeLangPackage.RESULT_GROUP__ASSERTS:
        return asserts != null && !asserts.isEmpty();
      case BeeLangPackage.RESULT_GROUP__PREREQUISITES:
        return prerequisites != null && !prerequisites.isEmpty();
      case BeeLangPackage.RESULT_GROUP__CLOSURE:
        return closure != null;
    }
    return super.eIsSet(featureID);
  }

} //ResultGroupImpl
