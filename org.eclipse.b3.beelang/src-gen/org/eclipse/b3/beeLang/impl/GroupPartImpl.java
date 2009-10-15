/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang.impl;

import java.util.Collection;

import org.eclipse.b3.beeLang.BeeLangPackage;
import org.eclipse.b3.beeLang.GroupPart;
import org.eclipse.b3.beeLang.Prerequisite;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Group Part</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.impl.GroupPartImpl#isSynchronized <em>Synchronized</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.GroupPartImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.GroupPartImpl#getAsserts <em>Asserts</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.GroupPartImpl#getPrerequisites <em>Prerequisites</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GroupPartImpl extends BuildPartImpl implements GroupPart
{
  /**
   * The default value of the '{@link #isSynchronized() <em>Synchronized</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSynchronized()
   * @generated
   * @ordered
   */
  protected static final boolean SYNCHRONIZED_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isSynchronized() <em>Synchronized</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSynchronized()
   * @generated
   * @ordered
   */
  protected boolean synchronized_ = SYNCHRONIZED_EDEFAULT;

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
  protected EList<EObject> asserts;

  /**
   * The cached value of the '{@link #getPrerequisites() <em>Prerequisites</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPrerequisites()
   * @generated
   * @ordered
   */
  protected EList<Prerequisite> prerequisites;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected GroupPartImpl()
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
    return BeeLangPackage.Literals.GROUP_PART;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSynchronized()
  {
    return synchronized_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSynchronized(boolean newSynchronized)
  {
    boolean oldSynchronized = synchronized_;
    synchronized_ = newSynchronized;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.GROUP_PART__SYNCHRONIZED, oldSynchronized, synchronized_));
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
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.GROUP_PART__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<EObject> getAsserts()
  {
    if (asserts == null)
    {
      asserts = new EObjectContainmentEList<EObject>(EObject.class, this, BeeLangPackage.GROUP_PART__ASSERTS);
    }
    return asserts;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Prerequisite> getPrerequisites()
  {
    if (prerequisites == null)
    {
      prerequisites = new EObjectContainmentEList<Prerequisite>(Prerequisite.class, this, BeeLangPackage.GROUP_PART__PREREQUISITES);
    }
    return prerequisites;
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
      case BeeLangPackage.GROUP_PART__ASSERTS:
        return ((InternalEList<?>)getAsserts()).basicRemove(otherEnd, msgs);
      case BeeLangPackage.GROUP_PART__PREREQUISITES:
        return ((InternalEList<?>)getPrerequisites()).basicRemove(otherEnd, msgs);
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
      case BeeLangPackage.GROUP_PART__SYNCHRONIZED:
        return isSynchronized();
      case BeeLangPackage.GROUP_PART__NAME:
        return getName();
      case BeeLangPackage.GROUP_PART__ASSERTS:
        return getAsserts();
      case BeeLangPackage.GROUP_PART__PREREQUISITES:
        return getPrerequisites();
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
      case BeeLangPackage.GROUP_PART__SYNCHRONIZED:
        setSynchronized((Boolean)newValue);
        return;
      case BeeLangPackage.GROUP_PART__NAME:
        setName((String)newValue);
        return;
      case BeeLangPackage.GROUP_PART__ASSERTS:
        getAsserts().clear();
        getAsserts().addAll((Collection<? extends EObject>)newValue);
        return;
      case BeeLangPackage.GROUP_PART__PREREQUISITES:
        getPrerequisites().clear();
        getPrerequisites().addAll((Collection<? extends Prerequisite>)newValue);
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
      case BeeLangPackage.GROUP_PART__SYNCHRONIZED:
        setSynchronized(SYNCHRONIZED_EDEFAULT);
        return;
      case BeeLangPackage.GROUP_PART__NAME:
        setName(NAME_EDEFAULT);
        return;
      case BeeLangPackage.GROUP_PART__ASSERTS:
        getAsserts().clear();
        return;
      case BeeLangPackage.GROUP_PART__PREREQUISITES:
        getPrerequisites().clear();
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
      case BeeLangPackage.GROUP_PART__SYNCHRONIZED:
        return synchronized_ != SYNCHRONIZED_EDEFAULT;
      case BeeLangPackage.GROUP_PART__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case BeeLangPackage.GROUP_PART__ASSERTS:
        return asserts != null && !asserts.isEmpty();
      case BeeLangPackage.GROUP_PART__PREREQUISITES:
        return prerequisites != null && !prerequisites.isEmpty();
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
    result.append(" (synchronized: ");
    result.append(synchronized_);
    result.append(", name: ");
    result.append(name);
    result.append(')');
    return result.toString();
  }

} //GroupPartImpl
