/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang.impl;

import org.eclipse.b3.beeLang.BeeLangPackage;
import org.eclipse.b3.beeLang.Closure;
import org.eclipse.b3.beeLang.Filter;
import org.eclipse.b3.beeLang.Prerequisite;
import org.eclipse.b3.beeLang.PrerequisiteEntry;

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
 *   <li>{@link org.eclipse.b3.beeLang.impl.PrerequisiteImpl#isSurpressed <em>Surpressed</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.PrerequisiteImpl#getFilter <em>Filter</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.PrerequisiteImpl#getAlias <em>Alias</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.PrerequisiteImpl#getPartReference <em>Part Reference</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.PrerequisiteImpl#getClosure <em>Closure</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PrerequisiteImpl extends MinimalEObjectImpl.Container implements Prerequisite
{
  /**
   * The default value of the '{@link #isSurpressed() <em>Surpressed</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSurpressed()
   * @generated
   * @ordered
   */
  protected static final boolean SURPRESSED_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isSurpressed() <em>Surpressed</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSurpressed()
   * @generated
   * @ordered
   */
  protected boolean surpressed = SURPRESSED_EDEFAULT;

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
   * The cached value of the '{@link #getPartReference() <em>Part Reference</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPartReference()
   * @generated
   * @ordered
   */
  protected PrerequisiteEntry partReference;

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
  public boolean isSurpressed()
  {
    return surpressed;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSurpressed(boolean newSurpressed)
  {
    boolean oldSurpressed = surpressed;
    surpressed = newSurpressed;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.PREREQUISITE__SURPRESSED, oldSurpressed, surpressed));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.PREREQUISITE__CLOSURE, oldClosure, newClosure);
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
        msgs = ((InternalEObject)closure).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.PREREQUISITE__CLOSURE, null, msgs);
      if (newClosure != null)
        msgs = ((InternalEObject)newClosure).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.PREREQUISITE__CLOSURE, null, msgs);
      msgs = basicSetClosure(newClosure, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.PREREQUISITE__CLOSURE, newClosure, newClosure));
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
      case BeeLangPackage.PREREQUISITE__PART_REFERENCE:
        return basicSetPartReference(null, msgs);
      case BeeLangPackage.PREREQUISITE__CLOSURE:
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
      case BeeLangPackage.PREREQUISITE__SURPRESSED:
        return isSurpressed();
      case BeeLangPackage.PREREQUISITE__FILTER:
        return getFilter();
      case BeeLangPackage.PREREQUISITE__ALIAS:
        return getAlias();
      case BeeLangPackage.PREREQUISITE__PART_REFERENCE:
        return getPartReference();
      case BeeLangPackage.PREREQUISITE__CLOSURE:
        return getClosure();
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
      case BeeLangPackage.PREREQUISITE__SURPRESSED:
        setSurpressed((Boolean)newValue);
        return;
      case BeeLangPackage.PREREQUISITE__FILTER:
        setFilter((Filter)newValue);
        return;
      case BeeLangPackage.PREREQUISITE__ALIAS:
        setAlias((String)newValue);
        return;
      case BeeLangPackage.PREREQUISITE__PART_REFERENCE:
        setPartReference((PrerequisiteEntry)newValue);
        return;
      case BeeLangPackage.PREREQUISITE__CLOSURE:
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
      case BeeLangPackage.PREREQUISITE__SURPRESSED:
        setSurpressed(SURPRESSED_EDEFAULT);
        return;
      case BeeLangPackage.PREREQUISITE__FILTER:
        setFilter((Filter)null);
        return;
      case BeeLangPackage.PREREQUISITE__ALIAS:
        setAlias(ALIAS_EDEFAULT);
        return;
      case BeeLangPackage.PREREQUISITE__PART_REFERENCE:
        setPartReference((PrerequisiteEntry)null);
        return;
      case BeeLangPackage.PREREQUISITE__CLOSURE:
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
      case BeeLangPackage.PREREQUISITE__SURPRESSED:
        return surpressed != SURPRESSED_EDEFAULT;
      case BeeLangPackage.PREREQUISITE__FILTER:
        return filter != null;
      case BeeLangPackage.PREREQUISITE__ALIAS:
        return ALIAS_EDEFAULT == null ? alias != null : !ALIAS_EDEFAULT.equals(alias);
      case BeeLangPackage.PREREQUISITE__PART_REFERENCE:
        return partReference != null;
      case BeeLangPackage.PREREQUISITE__CLOSURE:
        return closure != null;
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
    result.append(" (surpressed: ");
    result.append(surpressed);
    result.append(", alias: ");
    result.append(alias);
    result.append(')');
    return result.toString();
  }

} //PrerequisiteImpl
