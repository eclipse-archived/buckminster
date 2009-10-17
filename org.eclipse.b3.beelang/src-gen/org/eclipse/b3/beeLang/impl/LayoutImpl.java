/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang.impl;

import java.util.Collection;

import org.eclipse.b3.beeLang.BeeLangPackage;
import org.eclipse.b3.beeLang.Filter;
import org.eclipse.b3.beeLang.Layout;
import org.eclipse.b3.beeLang.PathGroup;
import org.eclipse.b3.beeLang.PostConditionAssert;

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
 * An implementation of the model object '<em><b>Layout</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.impl.LayoutImpl#getFilter <em>Filter</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.LayoutImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.LayoutImpl#getAsserts <em>Asserts</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.LayoutImpl#getPaths <em>Paths</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LayoutImpl extends MinimalEObjectImpl.Container implements Layout
{
  /**
   * The cached value of the '{@link #getFilter() <em>Filter</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFilter()
   * @generated
   * @ordered
   */
  protected Filter filter;

  /**
   * The default value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected static final String NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected String name = NAME_EDEFAULT;

  /**
   * The cached value of the '{@link #getAsserts() <em>Asserts</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAsserts()
   * @generated
   * @ordered
   */
  protected EList<PostConditionAssert> asserts;

  /**
   * The cached value of the '{@link #getPaths() <em>Paths</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPaths()
   * @generated
   * @ordered
   */
  protected EList<PathGroup> paths;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected LayoutImpl()
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
    return BeeLangPackage.Literals.LAYOUT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Filter getFilter()
  {
    return filter;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetFilter(Filter newFilter, NotificationChain msgs)
  {
    Filter oldFilter = filter;
    filter = newFilter;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.LAYOUT__FILTER, oldFilter, newFilter);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFilter(Filter newFilter)
  {
    if (newFilter != filter)
    {
      NotificationChain msgs = null;
      if (filter != null)
        msgs = ((InternalEObject)filter).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.LAYOUT__FILTER, null, msgs);
      if (newFilter != null)
        msgs = ((InternalEObject)newFilter).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.LAYOUT__FILTER, null, msgs);
      msgs = basicSetFilter(newFilter, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.LAYOUT__FILTER, newFilter, newFilter));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setName(String newName)
  {
    String oldName = name;
    name = newName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.LAYOUT__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<PostConditionAssert> getAsserts()
  {
    if (asserts == null)
    {
      asserts = new EObjectContainmentEList<PostConditionAssert>(PostConditionAssert.class, this, BeeLangPackage.LAYOUT__ASSERTS);
    }
    return asserts;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<PathGroup> getPaths()
  {
    if (paths == null)
    {
      paths = new EObjectContainmentEList<PathGroup>(PathGroup.class, this, BeeLangPackage.LAYOUT__PATHS);
    }
    return paths;
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
      case BeeLangPackage.LAYOUT__FILTER:
        return basicSetFilter(null, msgs);
      case BeeLangPackage.LAYOUT__ASSERTS:
        return ((InternalEList<?>)getAsserts()).basicRemove(otherEnd, msgs);
      case BeeLangPackage.LAYOUT__PATHS:
        return ((InternalEList<?>)getPaths()).basicRemove(otherEnd, msgs);
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
      case BeeLangPackage.LAYOUT__FILTER:
        return getFilter();
      case BeeLangPackage.LAYOUT__NAME:
        return getName();
      case BeeLangPackage.LAYOUT__ASSERTS:
        return getAsserts();
      case BeeLangPackage.LAYOUT__PATHS:
        return getPaths();
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
      case BeeLangPackage.LAYOUT__FILTER:
        setFilter((Filter)newValue);
        return;
      case BeeLangPackage.LAYOUT__NAME:
        setName((String)newValue);
        return;
      case BeeLangPackage.LAYOUT__ASSERTS:
        getAsserts().clear();
        getAsserts().addAll((Collection<? extends PostConditionAssert>)newValue);
        return;
      case BeeLangPackage.LAYOUT__PATHS:
        getPaths().clear();
        getPaths().addAll((Collection<? extends PathGroup>)newValue);
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
      case BeeLangPackage.LAYOUT__FILTER:
        setFilter((Filter)null);
        return;
      case BeeLangPackage.LAYOUT__NAME:
        setName(NAME_EDEFAULT);
        return;
      case BeeLangPackage.LAYOUT__ASSERTS:
        getAsserts().clear();
        return;
      case BeeLangPackage.LAYOUT__PATHS:
        getPaths().clear();
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
      case BeeLangPackage.LAYOUT__FILTER:
        return filter != null;
      case BeeLangPackage.LAYOUT__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case BeeLangPackage.LAYOUT__ASSERTS:
        return asserts != null && !asserts.isEmpty();
      case BeeLangPackage.LAYOUT__PATHS:
        return paths != null && !paths.isEmpty();
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
    result.append(" (name: ");
    result.append(name);
    result.append(')');
    return result.toString();
  }

} //LayoutImpl
