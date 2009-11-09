/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang.impl;

import java.util.Collection;

import org.eclipse.b3.beeLang.BeeLangPackage;
import org.eclipse.b3.beeLang.TypeRef;
import org.eclipse.b3.beeLang.WildcardRefParam;

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
 * An implementation of the model object '<em><b>Wildcard Ref Param</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.impl.WildcardRefParamImpl#getExtends <em>Extends</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.WildcardRefParamImpl#getSuperRef <em>Super Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class WildcardRefParamImpl extends RuleTypeParamImpl implements WildcardRefParam
{
  /**
   * The cached value of the '{@link #getExtends() <em>Extends</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExtends()
   * @generated
   * @ordered
   */
  protected EList<TypeRef> extends_;

  /**
   * The cached value of the '{@link #getSuperRef() <em>Super Ref</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSuperRef()
   * @generated
   * @ordered
   */
  protected TypeRef superRef;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected WildcardRefParamImpl()
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
    return BeeLangPackage.Literals.WILDCARD_REF_PARAM;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<TypeRef> getExtends()
  {
    if (extends_ == null)
    {
      extends_ = new EObjectContainmentEList<TypeRef>(TypeRef.class, this, BeeLangPackage.WILDCARD_REF_PARAM__EXTENDS);
    }
    return extends_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TypeRef getSuperRef()
  {
    return superRef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetSuperRef(TypeRef newSuperRef, NotificationChain msgs)
  {
    TypeRef oldSuperRef = superRef;
    superRef = newSuperRef;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.WILDCARD_REF_PARAM__SUPER_REF, oldSuperRef, newSuperRef);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSuperRef(TypeRef newSuperRef)
  {
    if (newSuperRef != superRef)
    {
      NotificationChain msgs = null;
      if (superRef != null)
        msgs = ((InternalEObject)superRef).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.WILDCARD_REF_PARAM__SUPER_REF, null, msgs);
      if (newSuperRef != null)
        msgs = ((InternalEObject)newSuperRef).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.WILDCARD_REF_PARAM__SUPER_REF, null, msgs);
      msgs = basicSetSuperRef(newSuperRef, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.WILDCARD_REF_PARAM__SUPER_REF, newSuperRef, newSuperRef));
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
      case BeeLangPackage.WILDCARD_REF_PARAM__EXTENDS:
        return ((InternalEList<?>)getExtends()).basicRemove(otherEnd, msgs);
      case BeeLangPackage.WILDCARD_REF_PARAM__SUPER_REF:
        return basicSetSuperRef(null, msgs);
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
      case BeeLangPackage.WILDCARD_REF_PARAM__EXTENDS:
        return getExtends();
      case BeeLangPackage.WILDCARD_REF_PARAM__SUPER_REF:
        return getSuperRef();
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
      case BeeLangPackage.WILDCARD_REF_PARAM__EXTENDS:
        getExtends().clear();
        getExtends().addAll((Collection<? extends TypeRef>)newValue);
        return;
      case BeeLangPackage.WILDCARD_REF_PARAM__SUPER_REF:
        setSuperRef((TypeRef)newValue);
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
      case BeeLangPackage.WILDCARD_REF_PARAM__EXTENDS:
        getExtends().clear();
        return;
      case BeeLangPackage.WILDCARD_REF_PARAM__SUPER_REF:
        setSuperRef((TypeRef)null);
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
      case BeeLangPackage.WILDCARD_REF_PARAM__EXTENDS:
        return extends_ != null && !extends_.isEmpty();
      case BeeLangPackage.WILDCARD_REF_PARAM__SUPER_REF:
        return superRef != null;
    }
    return super.eIsSet(featureID);
  }

} //WildcardRefParamImpl
