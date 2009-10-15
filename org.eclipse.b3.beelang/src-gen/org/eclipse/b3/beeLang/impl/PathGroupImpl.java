/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang.impl;

import java.util.Collection;

import org.eclipse.b3.beeLang.BeeLangPackage;
import org.eclipse.b3.beeLang.Filter;
import org.eclipse.b3.beeLang.PathGroup;
import org.eclipse.b3.beeLang.StringProperty;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Path Group</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.impl.PathGroupImpl#getFilter <em>Filter</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.PathGroupImpl#getPaths <em>Paths</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.PathGroupImpl#getBasePath <em>Base Path</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.PathGroupImpl#getUnsetProperties <em>Unset Properties</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.PathGroupImpl#getSetProperties <em>Set Properties</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PathGroupImpl extends MinimalEObjectImpl.Container implements PathGroup
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
   * The cached value of the '{@link #getPaths() <em>Paths</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPaths()
   * @generated
   * @ordered
   */
  protected EList<String> paths;

  /**
   * The default value of the '{@link #getBasePath() <em>Base Path</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBasePath()
   * @generated
   * @ordered
   */
  protected static final String BASE_PATH_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getBasePath() <em>Base Path</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBasePath()
   * @generated
   * @ordered
   */
  protected String basePath = BASE_PATH_EDEFAULT;

  /**
   * The cached value of the '{@link #getUnsetProperties() <em>Unset Properties</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getUnsetProperties()
   * @generated
   * @ordered
   */
  protected EList<String> unsetProperties;

  /**
   * The cached value of the '{@link #getSetProperties() <em>Set Properties</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSetProperties()
   * @generated
   * @ordered
   */
  protected EList<StringProperty> setProperties;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected PathGroupImpl()
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
    return BeeLangPackage.Literals.PATH_GROUP;
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.PATH_GROUP__FILTER, oldFilter, newFilter);
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
        msgs = ((InternalEObject)filter).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.PATH_GROUP__FILTER, null, msgs);
      if (newFilter != null)
        msgs = ((InternalEObject)newFilter).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.PATH_GROUP__FILTER, null, msgs);
      msgs = basicSetFilter(newFilter, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.PATH_GROUP__FILTER, newFilter, newFilter));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getPaths()
  {
    if (paths == null)
    {
      paths = new EDataTypeEList<String>(String.class, this, BeeLangPackage.PATH_GROUP__PATHS);
    }
    return paths;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getBasePath()
  {
    return basePath;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setBasePath(String newBasePath)
  {
    String oldBasePath = basePath;
    basePath = newBasePath;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.PATH_GROUP__BASE_PATH, oldBasePath, basePath));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getUnsetProperties()
  {
    if (unsetProperties == null)
    {
      unsetProperties = new EDataTypeEList<String>(String.class, this, BeeLangPackage.PATH_GROUP__UNSET_PROPERTIES);
    }
    return unsetProperties;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<StringProperty> getSetProperties()
  {
    if (setProperties == null)
    {
      setProperties = new EObjectContainmentEList<StringProperty>(StringProperty.class, this, BeeLangPackage.PATH_GROUP__SET_PROPERTIES);
    }
    return setProperties;
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
      case BeeLangPackage.PATH_GROUP__FILTER:
        return basicSetFilter(null, msgs);
      case BeeLangPackage.PATH_GROUP__SET_PROPERTIES:
        return ((InternalEList<?>)getSetProperties()).basicRemove(otherEnd, msgs);
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
      case BeeLangPackage.PATH_GROUP__FILTER:
        return getFilter();
      case BeeLangPackage.PATH_GROUP__PATHS:
        return getPaths();
      case BeeLangPackage.PATH_GROUP__BASE_PATH:
        return getBasePath();
      case BeeLangPackage.PATH_GROUP__UNSET_PROPERTIES:
        return getUnsetProperties();
      case BeeLangPackage.PATH_GROUP__SET_PROPERTIES:
        return getSetProperties();
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
      case BeeLangPackage.PATH_GROUP__FILTER:
        setFilter((Filter)newValue);
        return;
      case BeeLangPackage.PATH_GROUP__PATHS:
        getPaths().clear();
        getPaths().addAll((Collection<? extends String>)newValue);
        return;
      case BeeLangPackage.PATH_GROUP__BASE_PATH:
        setBasePath((String)newValue);
        return;
      case BeeLangPackage.PATH_GROUP__UNSET_PROPERTIES:
        getUnsetProperties().clear();
        getUnsetProperties().addAll((Collection<? extends String>)newValue);
        return;
      case BeeLangPackage.PATH_GROUP__SET_PROPERTIES:
        getSetProperties().clear();
        getSetProperties().addAll((Collection<? extends StringProperty>)newValue);
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
      case BeeLangPackage.PATH_GROUP__FILTER:
        setFilter((Filter)null);
        return;
      case BeeLangPackage.PATH_GROUP__PATHS:
        getPaths().clear();
        return;
      case BeeLangPackage.PATH_GROUP__BASE_PATH:
        setBasePath(BASE_PATH_EDEFAULT);
        return;
      case BeeLangPackage.PATH_GROUP__UNSET_PROPERTIES:
        getUnsetProperties().clear();
        return;
      case BeeLangPackage.PATH_GROUP__SET_PROPERTIES:
        getSetProperties().clear();
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
      case BeeLangPackage.PATH_GROUP__FILTER:
        return filter != null;
      case BeeLangPackage.PATH_GROUP__PATHS:
        return paths != null && !paths.isEmpty();
      case BeeLangPackage.PATH_GROUP__BASE_PATH:
        return BASE_PATH_EDEFAULT == null ? basePath != null : !BASE_PATH_EDEFAULT.equals(basePath);
      case BeeLangPackage.PATH_GROUP__UNSET_PROPERTIES:
        return unsetProperties != null && !unsetProperties.isEmpty();
      case BeeLangPackage.PATH_GROUP__SET_PROPERTIES:
        return setProperties != null && !setProperties.isEmpty();
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
    result.append(" (paths: ");
    result.append(paths);
    result.append(", basePath: ");
    result.append(basePath);
    result.append(", unsetProperties: ");
    result.append(unsetProperties);
    result.append(')');
    return result.toString();
  }

} //PathGroupImpl
