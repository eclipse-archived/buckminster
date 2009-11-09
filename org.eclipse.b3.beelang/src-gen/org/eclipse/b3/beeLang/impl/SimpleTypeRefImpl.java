/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang.impl;

import java.util.Collection;

import org.eclipse.b3.beeLang.BeeLangPackage;
import org.eclipse.b3.beeLang.RuleTypeParam;
import org.eclipse.b3.beeLang.SimpleTypeRef;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Simple Type Ref</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.impl.SimpleTypeRefImpl#getTypeName <em>Type Name</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.SimpleTypeRefImpl#getRuleTypeParameter <em>Rule Type Parameter</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SimpleTypeRefImpl extends TypeRefImpl implements SimpleTypeRef
{
  /**
   * The cached value of the '{@link #getTypeName() <em>Type Name</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTypeName()
   * @generated
   * @ordered
   */
  protected EList<String> typeName;

  /**
   * The cached value of the '{@link #getRuleTypeParameter() <em>Rule Type Parameter</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRuleTypeParameter()
   * @generated
   * @ordered
   */
  protected EList<RuleTypeParam> ruleTypeParameter;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected SimpleTypeRefImpl()
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
    return BeeLangPackage.Literals.SIMPLE_TYPE_REF;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getTypeName()
  {
    if (typeName == null)
    {
      typeName = new EDataTypeEList<String>(String.class, this, BeeLangPackage.SIMPLE_TYPE_REF__TYPE_NAME);
    }
    return typeName;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<RuleTypeParam> getRuleTypeParameter()
  {
    if (ruleTypeParameter == null)
    {
      ruleTypeParameter = new EObjectContainmentEList<RuleTypeParam>(RuleTypeParam.class, this, BeeLangPackage.SIMPLE_TYPE_REF__RULE_TYPE_PARAMETER);
    }
    return ruleTypeParameter;
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
      case BeeLangPackage.SIMPLE_TYPE_REF__RULE_TYPE_PARAMETER:
        return ((InternalEList<?>)getRuleTypeParameter()).basicRemove(otherEnd, msgs);
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
      case BeeLangPackage.SIMPLE_TYPE_REF__TYPE_NAME:
        return getTypeName();
      case BeeLangPackage.SIMPLE_TYPE_REF__RULE_TYPE_PARAMETER:
        return getRuleTypeParameter();
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
      case BeeLangPackage.SIMPLE_TYPE_REF__TYPE_NAME:
        getTypeName().clear();
        getTypeName().addAll((Collection<? extends String>)newValue);
        return;
      case BeeLangPackage.SIMPLE_TYPE_REF__RULE_TYPE_PARAMETER:
        getRuleTypeParameter().clear();
        getRuleTypeParameter().addAll((Collection<? extends RuleTypeParam>)newValue);
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
      case BeeLangPackage.SIMPLE_TYPE_REF__TYPE_NAME:
        getTypeName().clear();
        return;
      case BeeLangPackage.SIMPLE_TYPE_REF__RULE_TYPE_PARAMETER:
        getRuleTypeParameter().clear();
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
      case BeeLangPackage.SIMPLE_TYPE_REF__TYPE_NAME:
        return typeName != null && !typeName.isEmpty();
      case BeeLangPackage.SIMPLE_TYPE_REF__RULE_TYPE_PARAMETER:
        return ruleTypeParameter != null && !ruleTypeParameter.isEmpty();
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
    result.append(" (typeName: ");
    result.append(typeName);
    result.append(')');
    return result.toString();
  }

} //SimpleTypeRefImpl
