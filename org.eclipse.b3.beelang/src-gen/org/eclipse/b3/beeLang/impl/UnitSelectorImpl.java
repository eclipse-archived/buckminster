/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang.impl;

import org.eclipse.b3.RegularExpression;

import org.eclipse.b3.beeLang.BeeLangPackage;
import org.eclipse.b3.beeLang.UnitSelector;
import org.eclipse.b3.beeLang.VersionRange;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Unit Selector</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.impl.UnitSelectorImpl#getInterface <em>Interface</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.UnitSelectorImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.UnitSelectorImpl#getNamePattern <em>Name Pattern</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.UnitSelectorImpl#getVersionRange <em>Version Range</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UnitSelectorImpl extends ContextSelectorImpl implements UnitSelector
{
  /**
   * The default value of the '{@link #getInterface() <em>Interface</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getInterface()
   * @generated
   * @ordered
   */
  protected static final String INTERFACE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getInterface() <em>Interface</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getInterface()
   * @generated
   * @ordered
   */
  protected String interface_ = INTERFACE_EDEFAULT;

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
   * The default value of the '{@link #getNamePattern() <em>Name Pattern</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNamePattern()
   * @generated
   * @ordered
   */
  protected static final RegularExpression NAME_PATTERN_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getNamePattern() <em>Name Pattern</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNamePattern()
   * @generated
   * @ordered
   */
  protected RegularExpression namePattern = NAME_PATTERN_EDEFAULT;

  /**
   * The cached value of the '{@link #getVersionRange() <em>Version Range</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVersionRange()
   * @generated
   * @ordered
   */
  protected VersionRange versionRange;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected UnitSelectorImpl()
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
    return BeeLangPackage.Literals.UNIT_SELECTOR;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getInterface()
  {
    return interface_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setInterface(String newInterface)
  {
    String oldInterface = interface_;
    interface_ = newInterface;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.UNIT_SELECTOR__INTERFACE, oldInterface, interface_));
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
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.UNIT_SELECTOR__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public RegularExpression getNamePattern()
  {
    return namePattern;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setNamePattern(RegularExpression newNamePattern)
  {
    RegularExpression oldNamePattern = namePattern;
    namePattern = newNamePattern;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.UNIT_SELECTOR__NAME_PATTERN, oldNamePattern, namePattern));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public VersionRange getVersionRange()
  {
    return versionRange;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetVersionRange(VersionRange newVersionRange, NotificationChain msgs)
  {
    VersionRange oldVersionRange = versionRange;
    versionRange = newVersionRange;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.UNIT_SELECTOR__VERSION_RANGE, oldVersionRange, newVersionRange);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setVersionRange(VersionRange newVersionRange)
  {
    if (newVersionRange != versionRange)
    {
      NotificationChain msgs = null;
      if (versionRange != null)
        msgs = ((InternalEObject)versionRange).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.UNIT_SELECTOR__VERSION_RANGE, null, msgs);
      if (newVersionRange != null)
        msgs = ((InternalEObject)newVersionRange).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.UNIT_SELECTOR__VERSION_RANGE, null, msgs);
      msgs = basicSetVersionRange(newVersionRange, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.UNIT_SELECTOR__VERSION_RANGE, newVersionRange, newVersionRange));
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
      case BeeLangPackage.UNIT_SELECTOR__VERSION_RANGE:
        return basicSetVersionRange(null, msgs);
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
      case BeeLangPackage.UNIT_SELECTOR__INTERFACE:
        return getInterface();
      case BeeLangPackage.UNIT_SELECTOR__NAME:
        return getName();
      case BeeLangPackage.UNIT_SELECTOR__NAME_PATTERN:
        return getNamePattern();
      case BeeLangPackage.UNIT_SELECTOR__VERSION_RANGE:
        return getVersionRange();
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
      case BeeLangPackage.UNIT_SELECTOR__INTERFACE:
        setInterface((String)newValue);
        return;
      case BeeLangPackage.UNIT_SELECTOR__NAME:
        setName((String)newValue);
        return;
      case BeeLangPackage.UNIT_SELECTOR__NAME_PATTERN:
        setNamePattern((RegularExpression)newValue);
        return;
      case BeeLangPackage.UNIT_SELECTOR__VERSION_RANGE:
        setVersionRange((VersionRange)newValue);
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
      case BeeLangPackage.UNIT_SELECTOR__INTERFACE:
        setInterface(INTERFACE_EDEFAULT);
        return;
      case BeeLangPackage.UNIT_SELECTOR__NAME:
        setName(NAME_EDEFAULT);
        return;
      case BeeLangPackage.UNIT_SELECTOR__NAME_PATTERN:
        setNamePattern(NAME_PATTERN_EDEFAULT);
        return;
      case BeeLangPackage.UNIT_SELECTOR__VERSION_RANGE:
        setVersionRange((VersionRange)null);
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
      case BeeLangPackage.UNIT_SELECTOR__INTERFACE:
        return INTERFACE_EDEFAULT == null ? interface_ != null : !INTERFACE_EDEFAULT.equals(interface_);
      case BeeLangPackage.UNIT_SELECTOR__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case BeeLangPackage.UNIT_SELECTOR__NAME_PATTERN:
        return NAME_PATTERN_EDEFAULT == null ? namePattern != null : !NAME_PATTERN_EDEFAULT.equals(namePattern);
      case BeeLangPackage.UNIT_SELECTOR__VERSION_RANGE:
        return versionRange != null;
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
    result.append(" (interface: ");
    result.append(interface_);
    result.append(", name: ");
    result.append(name);
    result.append(", namePattern: ");
    result.append(namePattern);
    result.append(')');
    return result.toString();
  }

} //UnitSelectorImpl
