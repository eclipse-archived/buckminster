/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang.impl;

import java.util.Collection;

import org.eclipse.b3.beeLang.BeeLangPackage;
import org.eclipse.b3.beeLang.QueryPath;
import org.eclipse.b3.beeLang.Selector;

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
 * An implementation of the model object '<em><b>Query Path</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.impl.QueryPathImpl#isAbsolute <em>Absolute</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.QueryPathImpl#getSelectors <em>Selectors</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class QueryPathImpl extends MinimalEObjectImpl.Container implements QueryPath
{
  /**
   * The default value of the '{@link #isAbsolute() <em>Absolute</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isAbsolute()
   * @generated
   * @ordered
   */
  protected static final boolean ABSOLUTE_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isAbsolute() <em>Absolute</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isAbsolute()
   * @generated
   * @ordered
   */
  protected boolean absolute = ABSOLUTE_EDEFAULT;

  /**
   * The cached value of the '{@link #getSelectors() <em>Selectors</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSelectors()
   * @generated
   * @ordered
   */
  protected EList<Selector> selectors;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected QueryPathImpl()
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
    return BeeLangPackage.Literals.QUERY_PATH;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isAbsolute()
  {
    return absolute;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAbsolute(boolean newAbsolute)
  {
    boolean oldAbsolute = absolute;
    absolute = newAbsolute;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.QUERY_PATH__ABSOLUTE, oldAbsolute, absolute));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Selector> getSelectors()
  {
    if (selectors == null)
    {
      selectors = new EObjectContainmentEList<Selector>(Selector.class, this, BeeLangPackage.QUERY_PATH__SELECTORS);
    }
    return selectors;
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
      case BeeLangPackage.QUERY_PATH__SELECTORS:
        return ((InternalEList<?>)getSelectors()).basicRemove(otherEnd, msgs);
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
      case BeeLangPackage.QUERY_PATH__ABSOLUTE:
        return isAbsolute();
      case BeeLangPackage.QUERY_PATH__SELECTORS:
        return getSelectors();
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
      case BeeLangPackage.QUERY_PATH__ABSOLUTE:
        setAbsolute((Boolean)newValue);
        return;
      case BeeLangPackage.QUERY_PATH__SELECTORS:
        getSelectors().clear();
        getSelectors().addAll((Collection<? extends Selector>)newValue);
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
      case BeeLangPackage.QUERY_PATH__ABSOLUTE:
        setAbsolute(ABSOLUTE_EDEFAULT);
        return;
      case BeeLangPackage.QUERY_PATH__SELECTORS:
        getSelectors().clear();
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
      case BeeLangPackage.QUERY_PATH__ABSOLUTE:
        return absolute != ABSOLUTE_EDEFAULT;
      case BeeLangPackage.QUERY_PATH__SELECTORS:
        return selectors != null && !selectors.isEmpty();
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
    result.append(" (absolute: ");
    result.append(absolute);
    result.append(')');
    return result.toString();
  }

} //QueryPathImpl
