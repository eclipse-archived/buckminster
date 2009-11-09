/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang.impl;

import org.eclipse.b3.beeLang.BeeLangPackage;
import org.eclipse.b3.beeLang.Capability;
import org.eclipse.b3.beeLang.CapabilityReferencedPart;
import org.eclipse.b3.beeLang.ParameterList;
import org.eclipse.b3.beeLang.VersionRange;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Capability Referenced Part</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.impl.CapabilityReferencedPartImpl#getCapability <em>Capability</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.CapabilityReferencedPartImpl#getRange <em>Range</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.CapabilityReferencedPartImpl#getPartName <em>Part Name</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.CapabilityReferencedPartImpl#getParameters <em>Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CapabilityReferencedPartImpl extends PrerequisiteEntryImpl implements CapabilityReferencedPart
{
  /**
   * The cached value of the '{@link #getCapability() <em>Capability</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCapability()
   * @generated
   * @ordered
   */
  protected Capability capability;

  /**
   * The cached value of the '{@link #getRange() <em>Range</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRange()
   * @generated
   * @ordered
   */
  protected VersionRange range;

  /**
   * The default value of the '{@link #getPartName() <em>Part Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPartName()
   * @generated
   * @ordered
   */
  protected static final String PART_NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getPartName() <em>Part Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPartName()
   * @generated
   * @ordered
   */
  protected String partName = PART_NAME_EDEFAULT;

  /**
   * The cached value of the '{@link #getParameters() <em>Parameters</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getParameters()
   * @generated
   * @ordered
   */
  protected ParameterList parameters;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected CapabilityReferencedPartImpl()
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
    return BeeLangPackage.Literals.CAPABILITY_REFERENCED_PART;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Capability getCapability()
  {
    return capability;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetCapability(Capability newCapability, NotificationChain msgs)
  {
    Capability oldCapability = capability;
    capability = newCapability;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.CAPABILITY_REFERENCED_PART__CAPABILITY, oldCapability, newCapability);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCapability(Capability newCapability)
  {
    if (newCapability != capability)
    {
      NotificationChain msgs = null;
      if (capability != null)
        msgs = ((InternalEObject)capability).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.CAPABILITY_REFERENCED_PART__CAPABILITY, null, msgs);
      if (newCapability != null)
        msgs = ((InternalEObject)newCapability).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.CAPABILITY_REFERENCED_PART__CAPABILITY, null, msgs);
      msgs = basicSetCapability(newCapability, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.CAPABILITY_REFERENCED_PART__CAPABILITY, newCapability, newCapability));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public VersionRange getRange()
  {
    return range;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetRange(VersionRange newRange, NotificationChain msgs)
  {
    VersionRange oldRange = range;
    range = newRange;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.CAPABILITY_REFERENCED_PART__RANGE, oldRange, newRange);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setRange(VersionRange newRange)
  {
    if (newRange != range)
    {
      NotificationChain msgs = null;
      if (range != null)
        msgs = ((InternalEObject)range).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.CAPABILITY_REFERENCED_PART__RANGE, null, msgs);
      if (newRange != null)
        msgs = ((InternalEObject)newRange).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.CAPABILITY_REFERENCED_PART__RANGE, null, msgs);
      msgs = basicSetRange(newRange, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.CAPABILITY_REFERENCED_PART__RANGE, newRange, newRange));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getPartName()
  {
    return partName;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPartName(String newPartName)
  {
    String oldPartName = partName;
    partName = newPartName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.CAPABILITY_REFERENCED_PART__PART_NAME, oldPartName, partName));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ParameterList getParameters()
  {
    return parameters;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetParameters(ParameterList newParameters, NotificationChain msgs)
  {
    ParameterList oldParameters = parameters;
    parameters = newParameters;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.CAPABILITY_REFERENCED_PART__PARAMETERS, oldParameters, newParameters);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setParameters(ParameterList newParameters)
  {
    if (newParameters != parameters)
    {
      NotificationChain msgs = null;
      if (parameters != null)
        msgs = ((InternalEObject)parameters).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.CAPABILITY_REFERENCED_PART__PARAMETERS, null, msgs);
      if (newParameters != null)
        msgs = ((InternalEObject)newParameters).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.CAPABILITY_REFERENCED_PART__PARAMETERS, null, msgs);
      msgs = basicSetParameters(newParameters, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.CAPABILITY_REFERENCED_PART__PARAMETERS, newParameters, newParameters));
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
      case BeeLangPackage.CAPABILITY_REFERENCED_PART__CAPABILITY:
        return basicSetCapability(null, msgs);
      case BeeLangPackage.CAPABILITY_REFERENCED_PART__RANGE:
        return basicSetRange(null, msgs);
      case BeeLangPackage.CAPABILITY_REFERENCED_PART__PARAMETERS:
        return basicSetParameters(null, msgs);
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
      case BeeLangPackage.CAPABILITY_REFERENCED_PART__CAPABILITY:
        return getCapability();
      case BeeLangPackage.CAPABILITY_REFERENCED_PART__RANGE:
        return getRange();
      case BeeLangPackage.CAPABILITY_REFERENCED_PART__PART_NAME:
        return getPartName();
      case BeeLangPackage.CAPABILITY_REFERENCED_PART__PARAMETERS:
        return getParameters();
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
      case BeeLangPackage.CAPABILITY_REFERENCED_PART__CAPABILITY:
        setCapability((Capability)newValue);
        return;
      case BeeLangPackage.CAPABILITY_REFERENCED_PART__RANGE:
        setRange((VersionRange)newValue);
        return;
      case BeeLangPackage.CAPABILITY_REFERENCED_PART__PART_NAME:
        setPartName((String)newValue);
        return;
      case BeeLangPackage.CAPABILITY_REFERENCED_PART__PARAMETERS:
        setParameters((ParameterList)newValue);
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
      case BeeLangPackage.CAPABILITY_REFERENCED_PART__CAPABILITY:
        setCapability((Capability)null);
        return;
      case BeeLangPackage.CAPABILITY_REFERENCED_PART__RANGE:
        setRange((VersionRange)null);
        return;
      case BeeLangPackage.CAPABILITY_REFERENCED_PART__PART_NAME:
        setPartName(PART_NAME_EDEFAULT);
        return;
      case BeeLangPackage.CAPABILITY_REFERENCED_PART__PARAMETERS:
        setParameters((ParameterList)null);
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
      case BeeLangPackage.CAPABILITY_REFERENCED_PART__CAPABILITY:
        return capability != null;
      case BeeLangPackage.CAPABILITY_REFERENCED_PART__RANGE:
        return range != null;
      case BeeLangPackage.CAPABILITY_REFERENCED_PART__PART_NAME:
        return PART_NAME_EDEFAULT == null ? partName != null : !PART_NAME_EDEFAULT.equals(partName);
      case BeeLangPackage.CAPABILITY_REFERENCED_PART__PARAMETERS:
        return parameters != null;
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
    result.append(" (partName: ");
    result.append(partName);
    result.append(')');
    return result.toString();
  }

} //CapabilityReferencedPartImpl
