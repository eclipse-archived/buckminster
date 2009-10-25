/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang.impl;

import org.eclipse.b3.beeLang.BeeLangPackage;
import org.eclipse.b3.beeLang.Version;
import org.eclipse.b3.beeLang.VersionRange;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Version Range</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.impl.VersionRangeImpl#getMinLimit <em>Min Limit</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.VersionRangeImpl#getMin <em>Min</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.VersionRangeImpl#getMax <em>Max</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.VersionRangeImpl#getMaxLimit <em>Max Limit</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class VersionRangeImpl extends MinimalEObjectImpl.Container implements VersionRange
{
  /**
   * The default value of the '{@link #getMinLimit() <em>Min Limit</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMinLimit()
   * @generated
   * @ordered
   */
  protected static final String MIN_LIMIT_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getMinLimit() <em>Min Limit</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMinLimit()
   * @generated
   * @ordered
   */
  protected String minLimit = MIN_LIMIT_EDEFAULT;

  /**
   * The cached value of the '{@link #getMin() <em>Min</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMin()
   * @generated
   * @ordered
   */
  protected Version min;

  /**
   * The cached value of the '{@link #getMax() <em>Max</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMax()
   * @generated
   * @ordered
   */
  protected Version max;

  /**
   * The default value of the '{@link #getMaxLimit() <em>Max Limit</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMaxLimit()
   * @generated
   * @ordered
   */
  protected static final String MAX_LIMIT_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getMaxLimit() <em>Max Limit</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMaxLimit()
   * @generated
   * @ordered
   */
  protected String maxLimit = MAX_LIMIT_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected VersionRangeImpl()
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
    return BeeLangPackage.Literals.VERSION_RANGE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getMinLimit()
  {
    return minLimit;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMinLimit(String newMinLimit)
  {
    String oldMinLimit = minLimit;
    minLimit = newMinLimit;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.VERSION_RANGE__MIN_LIMIT, oldMinLimit, minLimit));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Version getMin()
  {
    return min;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetMin(Version newMin, NotificationChain msgs)
  {
    Version oldMin = min;
    min = newMin;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.VERSION_RANGE__MIN, oldMin, newMin);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMin(Version newMin)
  {
    if (newMin != min)
    {
      NotificationChain msgs = null;
      if (min != null)
        msgs = ((InternalEObject)min).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.VERSION_RANGE__MIN, null, msgs);
      if (newMin != null)
        msgs = ((InternalEObject)newMin).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.VERSION_RANGE__MIN, null, msgs);
      msgs = basicSetMin(newMin, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.VERSION_RANGE__MIN, newMin, newMin));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Version getMax()
  {
    return max;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetMax(Version newMax, NotificationChain msgs)
  {
    Version oldMax = max;
    max = newMax;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.VERSION_RANGE__MAX, oldMax, newMax);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMax(Version newMax)
  {
    if (newMax != max)
    {
      NotificationChain msgs = null;
      if (max != null)
        msgs = ((InternalEObject)max).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.VERSION_RANGE__MAX, null, msgs);
      if (newMax != null)
        msgs = ((InternalEObject)newMax).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.VERSION_RANGE__MAX, null, msgs);
      msgs = basicSetMax(newMax, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.VERSION_RANGE__MAX, newMax, newMax));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getMaxLimit()
  {
    return maxLimit;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMaxLimit(String newMaxLimit)
  {
    String oldMaxLimit = maxLimit;
    maxLimit = newMaxLimit;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.VERSION_RANGE__MAX_LIMIT, oldMaxLimit, maxLimit));
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
      case BeeLangPackage.VERSION_RANGE__MIN:
        return basicSetMin(null, msgs);
      case BeeLangPackage.VERSION_RANGE__MAX:
        return basicSetMax(null, msgs);
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
      case BeeLangPackage.VERSION_RANGE__MIN_LIMIT:
        return getMinLimit();
      case BeeLangPackage.VERSION_RANGE__MIN:
        return getMin();
      case BeeLangPackage.VERSION_RANGE__MAX:
        return getMax();
      case BeeLangPackage.VERSION_RANGE__MAX_LIMIT:
        return getMaxLimit();
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
      case BeeLangPackage.VERSION_RANGE__MIN_LIMIT:
        setMinLimit((String)newValue);
        return;
      case BeeLangPackage.VERSION_RANGE__MIN:
        setMin((Version)newValue);
        return;
      case BeeLangPackage.VERSION_RANGE__MAX:
        setMax((Version)newValue);
        return;
      case BeeLangPackage.VERSION_RANGE__MAX_LIMIT:
        setMaxLimit((String)newValue);
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
      case BeeLangPackage.VERSION_RANGE__MIN_LIMIT:
        setMinLimit(MIN_LIMIT_EDEFAULT);
        return;
      case BeeLangPackage.VERSION_RANGE__MIN:
        setMin((Version)null);
        return;
      case BeeLangPackage.VERSION_RANGE__MAX:
        setMax((Version)null);
        return;
      case BeeLangPackage.VERSION_RANGE__MAX_LIMIT:
        setMaxLimit(MAX_LIMIT_EDEFAULT);
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
      case BeeLangPackage.VERSION_RANGE__MIN_LIMIT:
        return MIN_LIMIT_EDEFAULT == null ? minLimit != null : !MIN_LIMIT_EDEFAULT.equals(minLimit);
      case BeeLangPackage.VERSION_RANGE__MIN:
        return min != null;
      case BeeLangPackage.VERSION_RANGE__MAX:
        return max != null;
      case BeeLangPackage.VERSION_RANGE__MAX_LIMIT:
        return MAX_LIMIT_EDEFAULT == null ? maxLimit != null : !MAX_LIMIT_EDEFAULT.equals(maxLimit);
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
    result.append(" (minLimit: ");
    result.append(minLimit);
    result.append(", maxLimit: ");
    result.append(maxLimit);
    result.append(')');
    return result.toString();
  }

} //VersionRangeImpl
