/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang.impl;

import java.util.Collection;

import org.eclipse.b3.beeLang.BeeLangPackage;
import org.eclipse.b3.beeLang.Feature;
import org.eclipse.b3.beeLang.LiteralMap;
import org.eclipse.b3.beeLang.TypeRef;

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
 * An implementation of the model object '<em><b>Literal Map</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.impl.LiteralMapImpl#getFeatures <em>Features</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.LiteralMapImpl#getKeyType <em>Key Type</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.LiteralMapImpl#getValType <em>Val Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LiteralMapImpl extends ExpressionImpl implements LiteralMap
{
  /**
   * The cached value of the '{@link #getFeatures() <em>Features</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFeatures()
   * @generated
   * @ordered
   */
  protected EList<Feature> features;

  /**
   * The cached value of the '{@link #getKeyType() <em>Key Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getKeyType()
   * @generated
   * @ordered
   */
  protected TypeRef keyType;

  /**
   * The cached value of the '{@link #getValType() <em>Val Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getValType()
   * @generated
   * @ordered
   */
  protected TypeRef valType;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected LiteralMapImpl()
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
    return BeeLangPackage.Literals.LITERAL_MAP;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Feature> getFeatures()
  {
    if (features == null)
    {
      features = new EObjectContainmentEList<Feature>(Feature.class, this, BeeLangPackage.LITERAL_MAP__FEATURES);
    }
    return features;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TypeRef getKeyType()
  {
    return keyType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetKeyType(TypeRef newKeyType, NotificationChain msgs)
  {
    TypeRef oldKeyType = keyType;
    keyType = newKeyType;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.LITERAL_MAP__KEY_TYPE, oldKeyType, newKeyType);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setKeyType(TypeRef newKeyType)
  {
    if (newKeyType != keyType)
    {
      NotificationChain msgs = null;
      if (keyType != null)
        msgs = ((InternalEObject)keyType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.LITERAL_MAP__KEY_TYPE, null, msgs);
      if (newKeyType != null)
        msgs = ((InternalEObject)newKeyType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.LITERAL_MAP__KEY_TYPE, null, msgs);
      msgs = basicSetKeyType(newKeyType, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.LITERAL_MAP__KEY_TYPE, newKeyType, newKeyType));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TypeRef getValType()
  {
    return valType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetValType(TypeRef newValType, NotificationChain msgs)
  {
    TypeRef oldValType = valType;
    valType = newValType;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.LITERAL_MAP__VAL_TYPE, oldValType, newValType);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setValType(TypeRef newValType)
  {
    if (newValType != valType)
    {
      NotificationChain msgs = null;
      if (valType != null)
        msgs = ((InternalEObject)valType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.LITERAL_MAP__VAL_TYPE, null, msgs);
      if (newValType != null)
        msgs = ((InternalEObject)newValType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.LITERAL_MAP__VAL_TYPE, null, msgs);
      msgs = basicSetValType(newValType, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.LITERAL_MAP__VAL_TYPE, newValType, newValType));
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
      case BeeLangPackage.LITERAL_MAP__FEATURES:
        return ((InternalEList<?>)getFeatures()).basicRemove(otherEnd, msgs);
      case BeeLangPackage.LITERAL_MAP__KEY_TYPE:
        return basicSetKeyType(null, msgs);
      case BeeLangPackage.LITERAL_MAP__VAL_TYPE:
        return basicSetValType(null, msgs);
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
      case BeeLangPackage.LITERAL_MAP__FEATURES:
        return getFeatures();
      case BeeLangPackage.LITERAL_MAP__KEY_TYPE:
        return getKeyType();
      case BeeLangPackage.LITERAL_MAP__VAL_TYPE:
        return getValType();
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
      case BeeLangPackage.LITERAL_MAP__FEATURES:
        getFeatures().clear();
        getFeatures().addAll((Collection<? extends Feature>)newValue);
        return;
      case BeeLangPackage.LITERAL_MAP__KEY_TYPE:
        setKeyType((TypeRef)newValue);
        return;
      case BeeLangPackage.LITERAL_MAP__VAL_TYPE:
        setValType((TypeRef)newValue);
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
      case BeeLangPackage.LITERAL_MAP__FEATURES:
        getFeatures().clear();
        return;
      case BeeLangPackage.LITERAL_MAP__KEY_TYPE:
        setKeyType((TypeRef)null);
        return;
      case BeeLangPackage.LITERAL_MAP__VAL_TYPE:
        setValType((TypeRef)null);
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
      case BeeLangPackage.LITERAL_MAP__FEATURES:
        return features != null && !features.isEmpty();
      case BeeLangPackage.LITERAL_MAP__KEY_TYPE:
        return keyType != null;
      case BeeLangPackage.LITERAL_MAP__VAL_TYPE:
        return valType != null;
    }
    return super.eIsSet(featureID);
  }

} //LiteralMapImpl
