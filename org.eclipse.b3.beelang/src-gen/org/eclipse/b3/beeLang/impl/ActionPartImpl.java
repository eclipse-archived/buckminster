/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang.impl;

import java.util.Collection;

import org.eclipse.b3.beeLang.ActionPart;
import org.eclipse.b3.beeLang.BeeLangPackage;
import org.eclipse.b3.beeLang.Parameter;
import org.eclipse.b3.beeLang.Result;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Action Part</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.impl.ActionPartImpl#isSynchronized <em>Synchronized</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.ActionPartImpl#getActorParameters <em>Actor Parameters</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.ActionPartImpl#getAsserts <em>Asserts</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.ActionPartImpl#getResultGroups <em>Result Groups</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ActionPartImpl extends BuildPartImpl implements ActionPart
{
  /**
   * The default value of the '{@link #isSynchronized() <em>Synchronized</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSynchronized()
   * @generated
   * @ordered
   */
  protected static final boolean SYNCHRONIZED_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isSynchronized() <em>Synchronized</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSynchronized()
   * @generated
   * @ordered
   */
  protected boolean synchronized_ = SYNCHRONIZED_EDEFAULT;

  /**
   * The cached value of the '{@link #getActorParameters() <em>Actor Parameters</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getActorParameters()
   * @generated
   * @ordered
   */
  protected EList<Parameter> actorParameters;

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
   * The cached value of the '{@link #getResultGroups() <em>Result Groups</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getResultGroups()
   * @generated
   * @ordered
   */
  protected EList<Result> resultGroups;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ActionPartImpl()
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
    return BeeLangPackage.Literals.ACTION_PART;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSynchronized()
  {
    return synchronized_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSynchronized(boolean newSynchronized)
  {
    boolean oldSynchronized = synchronized_;
    synchronized_ = newSynchronized;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.ACTION_PART__SYNCHRONIZED, oldSynchronized, synchronized_));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Parameter> getActorParameters()
  {
    if (actorParameters == null)
    {
      actorParameters = new EObjectContainmentEList<Parameter>(Parameter.class, this, BeeLangPackage.ACTION_PART__ACTOR_PARAMETERS);
    }
    return actorParameters;
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
      asserts = new EObjectContainmentEList<EObject>(EObject.class, this, BeeLangPackage.ACTION_PART__ASSERTS);
    }
    return asserts;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Result> getResultGroups()
  {
    if (resultGroups == null)
    {
      resultGroups = new EObjectContainmentEList<Result>(Result.class, this, BeeLangPackage.ACTION_PART__RESULT_GROUPS);
    }
    return resultGroups;
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
      case BeeLangPackage.ACTION_PART__ACTOR_PARAMETERS:
        return ((InternalEList<?>)getActorParameters()).basicRemove(otherEnd, msgs);
      case BeeLangPackage.ACTION_PART__ASSERTS:
        return ((InternalEList<?>)getAsserts()).basicRemove(otherEnd, msgs);
      case BeeLangPackage.ACTION_PART__RESULT_GROUPS:
        return ((InternalEList<?>)getResultGroups()).basicRemove(otherEnd, msgs);
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
      case BeeLangPackage.ACTION_PART__SYNCHRONIZED:
        return isSynchronized();
      case BeeLangPackage.ACTION_PART__ACTOR_PARAMETERS:
        return getActorParameters();
      case BeeLangPackage.ACTION_PART__ASSERTS:
        return getAsserts();
      case BeeLangPackage.ACTION_PART__RESULT_GROUPS:
        return getResultGroups();
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
      case BeeLangPackage.ACTION_PART__SYNCHRONIZED:
        setSynchronized((Boolean)newValue);
        return;
      case BeeLangPackage.ACTION_PART__ACTOR_PARAMETERS:
        getActorParameters().clear();
        getActorParameters().addAll((Collection<? extends Parameter>)newValue);
        return;
      case BeeLangPackage.ACTION_PART__ASSERTS:
        getAsserts().clear();
        getAsserts().addAll((Collection<? extends EObject>)newValue);
        return;
      case BeeLangPackage.ACTION_PART__RESULT_GROUPS:
        getResultGroups().clear();
        getResultGroups().addAll((Collection<? extends Result>)newValue);
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
      case BeeLangPackage.ACTION_PART__SYNCHRONIZED:
        setSynchronized(SYNCHRONIZED_EDEFAULT);
        return;
      case BeeLangPackage.ACTION_PART__ACTOR_PARAMETERS:
        getActorParameters().clear();
        return;
      case BeeLangPackage.ACTION_PART__ASSERTS:
        getAsserts().clear();
        return;
      case BeeLangPackage.ACTION_PART__RESULT_GROUPS:
        getResultGroups().clear();
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
      case BeeLangPackage.ACTION_PART__SYNCHRONIZED:
        return synchronized_ != SYNCHRONIZED_EDEFAULT;
      case BeeLangPackage.ACTION_PART__ACTOR_PARAMETERS:
        return actorParameters != null && !actorParameters.isEmpty();
      case BeeLangPackage.ACTION_PART__ASSERTS:
        return asserts != null && !asserts.isEmpty();
      case BeeLangPackage.ACTION_PART__RESULT_GROUPS:
        return resultGroups != null && !resultGroups.isEmpty();
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
    result.append(" (synchronized: ");
    result.append(synchronized_);
    result.append(')');
    return result.toString();
  }

} //ActionPartImpl
