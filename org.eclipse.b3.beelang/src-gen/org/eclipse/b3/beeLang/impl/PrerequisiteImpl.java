/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang.impl;

import org.eclipse.b3.beeLang.BeeLangPackage;
import org.eclipse.b3.beeLang.Filter;
import org.eclipse.b3.beeLang.Prerequisite;
import org.eclipse.b3.beeLang.PrerequisiteEntry;
import org.eclipse.b3.beeLang.WithClause;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Prerequisite</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.impl.PrerequisiteImpl#getFilter <em>Filter</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.PrerequisiteImpl#getWithClause <em>With Clause</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.PrerequisiteImpl#getPartReference <em>Part Reference</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.PrerequisiteImpl#getAlias <em>Alias</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PrerequisiteImpl extends MinimalEObjectImpl.Container implements Prerequisite
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
   * The cached value of the '{@link #getWithClause() <em>With Clause</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getWithClause()
   * @generated
   * @ordered
   */
  protected WithClause withClause;

  /**
   * The cached value of the '{@link #getPartReference() <em>Part Reference</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPartReference()
   * @generated
   * @ordered
   */
  protected PrerequisiteEntry partReference;

  /**
   * The default value of the '{@link #getAlias() <em>Alias</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAlias()
   * @generated
   * @ordered
   */
  protected static final String ALIAS_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getAlias() <em>Alias</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAlias()
   * @generated
   * @ordered
   */
  protected String alias = ALIAS_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected PrerequisiteImpl()
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
    return BeeLangPackage.Literals.PREREQUISITE;
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.PREREQUISITE__FILTER, oldFilter, newFilter);
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
        msgs = ((InternalEObject)filter).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.PREREQUISITE__FILTER, null, msgs);
      if (newFilter != null)
        msgs = ((InternalEObject)newFilter).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.PREREQUISITE__FILTER, null, msgs);
      msgs = basicSetFilter(newFilter, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.PREREQUISITE__FILTER, newFilter, newFilter));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public WithClause getWithClause()
  {
    return withClause;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetWithClause(WithClause newWithClause, NotificationChain msgs)
  {
    WithClause oldWithClause = withClause;
    withClause = newWithClause;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.PREREQUISITE__WITH_CLAUSE, oldWithClause, newWithClause);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setWithClause(WithClause newWithClause)
  {
    if (newWithClause != withClause)
    {
      NotificationChain msgs = null;
      if (withClause != null)
        msgs = ((InternalEObject)withClause).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.PREREQUISITE__WITH_CLAUSE, null, msgs);
      if (newWithClause != null)
        msgs = ((InternalEObject)newWithClause).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.PREREQUISITE__WITH_CLAUSE, null, msgs);
      msgs = basicSetWithClause(newWithClause, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.PREREQUISITE__WITH_CLAUSE, newWithClause, newWithClause));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PrerequisiteEntry getPartReference()
  {
    return partReference;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetPartReference(PrerequisiteEntry newPartReference, NotificationChain msgs)
  {
    PrerequisiteEntry oldPartReference = partReference;
    partReference = newPartReference;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.PREREQUISITE__PART_REFERENCE, oldPartReference, newPartReference);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPartReference(PrerequisiteEntry newPartReference)
  {
    if (newPartReference != partReference)
    {
      NotificationChain msgs = null;
      if (partReference != null)
        msgs = ((InternalEObject)partReference).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.PREREQUISITE__PART_REFERENCE, null, msgs);
      if (newPartReference != null)
        msgs = ((InternalEObject)newPartReference).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.PREREQUISITE__PART_REFERENCE, null, msgs);
      msgs = basicSetPartReference(newPartReference, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.PREREQUISITE__PART_REFERENCE, newPartReference, newPartReference));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getAlias()
  {
    return alias;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAlias(String newAlias)
  {
    String oldAlias = alias;
    alias = newAlias;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.PREREQUISITE__ALIAS, oldAlias, alias));
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
      case BeeLangPackage.PREREQUISITE__FILTER:
        return basicSetFilter(null, msgs);
      case BeeLangPackage.PREREQUISITE__WITH_CLAUSE:
        return basicSetWithClause(null, msgs);
      case BeeLangPackage.PREREQUISITE__PART_REFERENCE:
        return basicSetPartReference(null, msgs);
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
      case BeeLangPackage.PREREQUISITE__FILTER:
        return getFilter();
      case BeeLangPackage.PREREQUISITE__WITH_CLAUSE:
        return getWithClause();
      case BeeLangPackage.PREREQUISITE__PART_REFERENCE:
        return getPartReference();
      case BeeLangPackage.PREREQUISITE__ALIAS:
        return getAlias();
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
      case BeeLangPackage.PREREQUISITE__FILTER:
        setFilter((Filter)newValue);
        return;
      case BeeLangPackage.PREREQUISITE__WITH_CLAUSE:
        setWithClause((WithClause)newValue);
        return;
      case BeeLangPackage.PREREQUISITE__PART_REFERENCE:
        setPartReference((PrerequisiteEntry)newValue);
        return;
      case BeeLangPackage.PREREQUISITE__ALIAS:
        setAlias((String)newValue);
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
      case BeeLangPackage.PREREQUISITE__FILTER:
        setFilter((Filter)null);
        return;
      case BeeLangPackage.PREREQUISITE__WITH_CLAUSE:
        setWithClause((WithClause)null);
        return;
      case BeeLangPackage.PREREQUISITE__PART_REFERENCE:
        setPartReference((PrerequisiteEntry)null);
        return;
      case BeeLangPackage.PREREQUISITE__ALIAS:
        setAlias(ALIAS_EDEFAULT);
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
      case BeeLangPackage.PREREQUISITE__FILTER:
        return filter != null;
      case BeeLangPackage.PREREQUISITE__WITH_CLAUSE:
        return withClause != null;
      case BeeLangPackage.PREREQUISITE__PART_REFERENCE:
        return partReference != null;
      case BeeLangPackage.PREREQUISITE__ALIAS:
        return ALIAS_EDEFAULT == null ? alias != null : !ALIAS_EDEFAULT.equals(alias);
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
    result.append(" (alias: ");
    result.append(alias);
    result.append(')');
    return result.toString();
  }

} //PrerequisiteImpl
