/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang.impl;

import java.util.Collection;

import org.eclipse.b3.beeLang.BeeLangPackage;
import org.eclipse.b3.beeLang.Closure;
import org.eclipse.b3.beeLang.CompoundAdvice;
import org.eclipse.b3.beeLang.PropertyStatement;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Closure</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.impl.ClosureImpl#getUnsetProperties <em>Unset Properties</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.ClosureImpl#getSetProperties <em>Set Properties</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.ClosureImpl#getAdvice <em>Advice</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ClosureImpl extends MinimalEObjectImpl.Container implements Closure
{
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
  protected EList<PropertyStatement> setProperties;

  /**
   * The cached value of the '{@link #getAdvice() <em>Advice</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAdvice()
   * @generated
   * @ordered
   */
  protected EList<CompoundAdvice> advice;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ClosureImpl()
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
    return BeeLangPackage.Literals.CLOSURE;
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
      unsetProperties = new EDataTypeEList<String>(String.class, this, BeeLangPackage.CLOSURE__UNSET_PROPERTIES);
    }
    return unsetProperties;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<PropertyStatement> getSetProperties()
  {
    if (setProperties == null)
    {
      setProperties = new EObjectContainmentEList<PropertyStatement>(PropertyStatement.class, this, BeeLangPackage.CLOSURE__SET_PROPERTIES);
    }
    return setProperties;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<CompoundAdvice> getAdvice()
  {
    if (advice == null)
    {
      advice = new EObjectContainmentEList<CompoundAdvice>(CompoundAdvice.class, this, BeeLangPackage.CLOSURE__ADVICE);
    }
    return advice;
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
      case BeeLangPackage.CLOSURE__SET_PROPERTIES:
        return ((InternalEList<?>)getSetProperties()).basicRemove(otherEnd, msgs);
      case BeeLangPackage.CLOSURE__ADVICE:
        return ((InternalEList<?>)getAdvice()).basicRemove(otherEnd, msgs);
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
      case BeeLangPackage.CLOSURE__UNSET_PROPERTIES:
        return getUnsetProperties();
      case BeeLangPackage.CLOSURE__SET_PROPERTIES:
        return getSetProperties();
      case BeeLangPackage.CLOSURE__ADVICE:
        return getAdvice();
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
      case BeeLangPackage.CLOSURE__UNSET_PROPERTIES:
        getUnsetProperties().clear();
        getUnsetProperties().addAll((Collection<? extends String>)newValue);
        return;
      case BeeLangPackage.CLOSURE__SET_PROPERTIES:
        getSetProperties().clear();
        getSetProperties().addAll((Collection<? extends PropertyStatement>)newValue);
        return;
      case BeeLangPackage.CLOSURE__ADVICE:
        getAdvice().clear();
        getAdvice().addAll((Collection<? extends CompoundAdvice>)newValue);
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
      case BeeLangPackage.CLOSURE__UNSET_PROPERTIES:
        getUnsetProperties().clear();
        return;
      case BeeLangPackage.CLOSURE__SET_PROPERTIES:
        getSetProperties().clear();
        return;
      case BeeLangPackage.CLOSURE__ADVICE:
        getAdvice().clear();
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
      case BeeLangPackage.CLOSURE__UNSET_PROPERTIES:
        return unsetProperties != null && !unsetProperties.isEmpty();
      case BeeLangPackage.CLOSURE__SET_PROPERTIES:
        return setProperties != null && !setProperties.isEmpty();
      case BeeLangPackage.CLOSURE__ADVICE:
        return advice != null && !advice.isEmpty();
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
    result.append(" (unsetProperties: ");
    result.append(unsetProperties);
    result.append(')');
    return result.toString();
  }

} //ClosureImpl
