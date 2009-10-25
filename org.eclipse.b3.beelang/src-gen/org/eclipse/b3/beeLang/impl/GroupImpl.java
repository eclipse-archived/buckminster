/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang.impl;

import java.util.Collection;

import org.eclipse.b3.beeLang.BeeLangPackage;
import org.eclipse.b3.beeLang.Group;
import org.eclipse.b3.beeLang.PostCondition;
import org.eclipse.b3.beeLang.PreCondition;
import org.eclipse.b3.beeLang.Prerequisite;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Group</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.impl.GroupImpl#getPreCondition <em>Pre Condition</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.GroupImpl#getPostCondition <em>Post Condition</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.GroupImpl#getPrerequisites <em>Prerequisites</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GroupImpl extends MinimalEObjectImpl.Container implements Group
{
  /**
   * The cached value of the '{@link #getPreCondition() <em>Pre Condition</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPreCondition()
   * @generated
   * @ordered
   */
  protected PreCondition preCondition;

  /**
   * The cached value of the '{@link #getPostCondition() <em>Post Condition</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPostCondition()
   * @generated
   * @ordered
   */
  protected PostCondition postCondition;

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
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected GroupImpl()
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
    return BeeLangPackage.Literals.GROUP;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PreCondition getPreCondition()
  {
    return preCondition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetPreCondition(PreCondition newPreCondition, NotificationChain msgs)
  {
    PreCondition oldPreCondition = preCondition;
    preCondition = newPreCondition;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.GROUP__PRE_CONDITION, oldPreCondition, newPreCondition);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPreCondition(PreCondition newPreCondition)
  {
    if (newPreCondition != preCondition)
    {
      NotificationChain msgs = null;
      if (preCondition != null)
        msgs = ((InternalEObject)preCondition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.GROUP__PRE_CONDITION, null, msgs);
      if (newPreCondition != null)
        msgs = ((InternalEObject)newPreCondition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.GROUP__PRE_CONDITION, null, msgs);
      msgs = basicSetPreCondition(newPreCondition, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.GROUP__PRE_CONDITION, newPreCondition, newPreCondition));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PostCondition getPostCondition()
  {
    return postCondition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetPostCondition(PostCondition newPostCondition, NotificationChain msgs)
  {
    PostCondition oldPostCondition = postCondition;
    postCondition = newPostCondition;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.GROUP__POST_CONDITION, oldPostCondition, newPostCondition);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPostCondition(PostCondition newPostCondition)
  {
    if (newPostCondition != postCondition)
    {
      NotificationChain msgs = null;
      if (postCondition != null)
        msgs = ((InternalEObject)postCondition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.GROUP__POST_CONDITION, null, msgs);
      if (newPostCondition != null)
        msgs = ((InternalEObject)newPostCondition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.GROUP__POST_CONDITION, null, msgs);
      msgs = basicSetPostCondition(newPostCondition, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.GROUP__POST_CONDITION, newPostCondition, newPostCondition));
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
      prerequisites = new EObjectContainmentEList<Prerequisite>(Prerequisite.class, this, BeeLangPackage.GROUP__PREREQUISITES);
    }
    return prerequisites;
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
      case BeeLangPackage.GROUP__PRE_CONDITION:
        return basicSetPreCondition(null, msgs);
      case BeeLangPackage.GROUP__POST_CONDITION:
        return basicSetPostCondition(null, msgs);
      case BeeLangPackage.GROUP__PREREQUISITES:
        return ((InternalEList<?>)getPrerequisites()).basicRemove(otherEnd, msgs);
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
      case BeeLangPackage.GROUP__PRE_CONDITION:
        return getPreCondition();
      case BeeLangPackage.GROUP__POST_CONDITION:
        return getPostCondition();
      case BeeLangPackage.GROUP__PREREQUISITES:
        return getPrerequisites();
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
      case BeeLangPackage.GROUP__PRE_CONDITION:
        setPreCondition((PreCondition)newValue);
        return;
      case BeeLangPackage.GROUP__POST_CONDITION:
        setPostCondition((PostCondition)newValue);
        return;
      case BeeLangPackage.GROUP__PREREQUISITES:
        getPrerequisites().clear();
        getPrerequisites().addAll((Collection<? extends Prerequisite>)newValue);
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
      case BeeLangPackage.GROUP__PRE_CONDITION:
        setPreCondition((PreCondition)null);
        return;
      case BeeLangPackage.GROUP__POST_CONDITION:
        setPostCondition((PostCondition)null);
        return;
      case BeeLangPackage.GROUP__PREREQUISITES:
        getPrerequisites().clear();
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
      case BeeLangPackage.GROUP__PRE_CONDITION:
        return preCondition != null;
      case BeeLangPackage.GROUP__POST_CONDITION:
        return postCondition != null;
      case BeeLangPackage.GROUP__PREREQUISITES:
        return prerequisites != null && !prerequisites.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //GroupImpl
