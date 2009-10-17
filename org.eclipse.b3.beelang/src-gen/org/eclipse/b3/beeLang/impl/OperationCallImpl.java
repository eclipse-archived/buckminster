/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang.impl;

import java.util.Collection;

import org.eclipse.b3.beeLang.AnonymousParameter;
import org.eclipse.b3.beeLang.BeeLangPackage;
import org.eclipse.b3.beeLang.Expression;
import org.eclipse.b3.beeLang.FeatureCall;
import org.eclipse.b3.beeLang.OperationCall;

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
 * An implementation of the model object '<em><b>Operation Call</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.impl.OperationCallImpl#getTarget <em>Target</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.OperationCallImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.OperationCallImpl#getParams <em>Params</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.OperationCallImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.OperationCallImpl#getParamsParameters <em>Params Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OperationCallImpl extends ExpressionImpl implements OperationCall
{
  /**
   * The cached value of the '{@link #getTarget() <em>Target</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTarget()
   * @generated
   * @ordered
   */
  protected Expression target;

  /**
   * The default value of the '{@link #getType() <em>Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getType()
   * @generated
   * @ordered
   */
  protected static final String TYPE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getType()
   * @generated
   * @ordered
   */
  protected String type = TYPE_EDEFAULT;

  /**
   * The cached value of the '{@link #getParams() <em>Params</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getParams()
   * @generated
   * @ordered
   */
  protected EList<EObject> params;

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
   * The cached value of the '{@link #getParamsParameters() <em>Params Parameters</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getParamsParameters()
   * @generated
   * @ordered
   */
  protected EList<AnonymousParameter> paramsParameters;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected OperationCallImpl()
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
    return BeeLangPackage.Literals.OPERATION_CALL;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Expression getTarget()
  {
    return target;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetTarget(Expression newTarget, NotificationChain msgs)
  {
    Expression oldTarget = target;
    target = newTarget;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.OPERATION_CALL__TARGET, oldTarget, newTarget);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTarget(Expression newTarget)
  {
    if (newTarget != target)
    {
      NotificationChain msgs = null;
      if (target != null)
        msgs = ((InternalEObject)target).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.OPERATION_CALL__TARGET, null, msgs);
      if (newTarget != null)
        msgs = ((InternalEObject)newTarget).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.OPERATION_CALL__TARGET, null, msgs);
      msgs = basicSetTarget(newTarget, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.OPERATION_CALL__TARGET, newTarget, newTarget));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getType()
  {
    return type;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setType(String newType)
  {
    String oldType = type;
    type = newType;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.OPERATION_CALL__TYPE, oldType, type));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<EObject> getParams()
  {
    if (params == null)
    {
      params = new EObjectContainmentEList<EObject>(EObject.class, this, BeeLangPackage.OPERATION_CALL__PARAMS);
    }
    return params;
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
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.OPERATION_CALL__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<AnonymousParameter> getParamsParameters()
  {
    if (paramsParameters == null)
    {
      paramsParameters = new EObjectContainmentEList<AnonymousParameter>(AnonymousParameter.class, this, BeeLangPackage.OPERATION_CALL__PARAMS_PARAMETERS);
    }
    return paramsParameters;
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
      case BeeLangPackage.OPERATION_CALL__TARGET:
        return basicSetTarget(null, msgs);
      case BeeLangPackage.OPERATION_CALL__PARAMS:
        return ((InternalEList<?>)getParams()).basicRemove(otherEnd, msgs);
      case BeeLangPackage.OPERATION_CALL__PARAMS_PARAMETERS:
        return ((InternalEList<?>)getParamsParameters()).basicRemove(otherEnd, msgs);
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
      case BeeLangPackage.OPERATION_CALL__TARGET:
        return getTarget();
      case BeeLangPackage.OPERATION_CALL__TYPE:
        return getType();
      case BeeLangPackage.OPERATION_CALL__PARAMS:
        return getParams();
      case BeeLangPackage.OPERATION_CALL__NAME:
        return getName();
      case BeeLangPackage.OPERATION_CALL__PARAMS_PARAMETERS:
        return getParamsParameters();
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
      case BeeLangPackage.OPERATION_CALL__TARGET:
        setTarget((Expression)newValue);
        return;
      case BeeLangPackage.OPERATION_CALL__TYPE:
        setType((String)newValue);
        return;
      case BeeLangPackage.OPERATION_CALL__PARAMS:
        getParams().clear();
        getParams().addAll((Collection<? extends EObject>)newValue);
        return;
      case BeeLangPackage.OPERATION_CALL__NAME:
        setName((String)newValue);
        return;
      case BeeLangPackage.OPERATION_CALL__PARAMS_PARAMETERS:
        getParamsParameters().clear();
        getParamsParameters().addAll((Collection<? extends AnonymousParameter>)newValue);
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
      case BeeLangPackage.OPERATION_CALL__TARGET:
        setTarget((Expression)null);
        return;
      case BeeLangPackage.OPERATION_CALL__TYPE:
        setType(TYPE_EDEFAULT);
        return;
      case BeeLangPackage.OPERATION_CALL__PARAMS:
        getParams().clear();
        return;
      case BeeLangPackage.OPERATION_CALL__NAME:
        setName(NAME_EDEFAULT);
        return;
      case BeeLangPackage.OPERATION_CALL__PARAMS_PARAMETERS:
        getParamsParameters().clear();
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
      case BeeLangPackage.OPERATION_CALL__TARGET:
        return target != null;
      case BeeLangPackage.OPERATION_CALL__TYPE:
        return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
      case BeeLangPackage.OPERATION_CALL__PARAMS:
        return params != null && !params.isEmpty();
      case BeeLangPackage.OPERATION_CALL__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case BeeLangPackage.OPERATION_CALL__PARAMS_PARAMETERS:
        return paramsParameters != null && !paramsParameters.isEmpty();
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass)
  {
    if (baseClass == FeatureCall.class)
    {
      switch (derivedFeatureID)
      {
        case BeeLangPackage.OPERATION_CALL__TARGET: return BeeLangPackage.FEATURE_CALL__TARGET;
        case BeeLangPackage.OPERATION_CALL__TYPE: return BeeLangPackage.FEATURE_CALL__TYPE;
        default: return -1;
      }
    }
    return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass)
  {
    if (baseClass == FeatureCall.class)
    {
      switch (baseFeatureID)
      {
        case BeeLangPackage.FEATURE_CALL__TARGET: return BeeLangPackage.OPERATION_CALL__TARGET;
        case BeeLangPackage.FEATURE_CALL__TYPE: return BeeLangPackage.OPERATION_CALL__TYPE;
        default: return -1;
      }
    }
    return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
    result.append(" (type: ");
    result.append(type);
    result.append(", name: ");
    result.append(name);
    result.append(')');
    return result.toString();
  }

} //OperationCallImpl
