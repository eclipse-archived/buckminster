/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang.impl;

import java.util.Collection;

import org.eclipse.b3.beeLang.BeeLangPackage;
import org.eclipse.b3.beeLang.ExecutionMode;
import org.eclipse.b3.beeLang.Expression;
import org.eclipse.b3.beeLang.FunctionOrMethod;
import org.eclipse.b3.beeLang.ParameterDeclaration;
import org.eclipse.b3.beeLang.TypeParamDeclaration;
import org.eclipse.b3.beeLang.TypeRef;
import org.eclipse.b3.beeLang.Visibility;

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
 * An implementation of the model object '<em><b>Function Or Method</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.impl.FunctionOrMethodImpl#getDocumentation <em>Documentation</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.FunctionOrMethodImpl#getVisibility <em>Visibility</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.FunctionOrMethodImpl#getExecutionMode <em>Execution Mode</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.FunctionOrMethodImpl#isFinal <em>Final</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.FunctionOrMethodImpl#isCached <em>Cached</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.FunctionOrMethodImpl#getForm <em>Form</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.FunctionOrMethodImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.FunctionOrMethodImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.FunctionOrMethodImpl#getTypeParams <em>Type Params</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.FunctionOrMethodImpl#getParams <em>Params</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.FunctionOrMethodImpl#getBody <em>Body</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FunctionOrMethodImpl extends MinimalEObjectImpl.Container implements FunctionOrMethod
{
  /**
   * The default value of the '{@link #getDocumentation() <em>Documentation</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDocumentation()
   * @generated
   * @ordered
   */
  protected static final String DOCUMENTATION_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getDocumentation() <em>Documentation</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDocumentation()
   * @generated
   * @ordered
   */
  protected String documentation = DOCUMENTATION_EDEFAULT;

  /**
   * The default value of the '{@link #getVisibility() <em>Visibility</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVisibility()
   * @generated
   * @ordered
   */
  protected static final Visibility VISIBILITY_EDEFAULT = Visibility.PUBLIC;

  /**
   * The cached value of the '{@link #getVisibility() <em>Visibility</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVisibility()
   * @generated
   * @ordered
   */
  protected Visibility visibility = VISIBILITY_EDEFAULT;

  /**
   * The default value of the '{@link #getExecutionMode() <em>Execution Mode</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExecutionMode()
   * @generated
   * @ordered
   */
  protected static final ExecutionMode EXECUTION_MODE_EDEFAULT = ExecutionMode.PARALLEL;

  /**
   * The cached value of the '{@link #getExecutionMode() <em>Execution Mode</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExecutionMode()
   * @generated
   * @ordered
   */
  protected ExecutionMode executionMode = EXECUTION_MODE_EDEFAULT;

  /**
   * The default value of the '{@link #isFinal() <em>Final</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isFinal()
   * @generated
   * @ordered
   */
  protected static final boolean FINAL_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isFinal() <em>Final</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isFinal()
   * @generated
   * @ordered
   */
  protected boolean final_ = FINAL_EDEFAULT;

  /**
   * The default value of the '{@link #isCached() <em>Cached</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isCached()
   * @generated
   * @ordered
   */
  protected static final boolean CACHED_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isCached() <em>Cached</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isCached()
   * @generated
   * @ordered
   */
  protected boolean cached = CACHED_EDEFAULT;

  /**
   * The default value of the '{@link #getForm() <em>Form</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getForm()
   * @generated
   * @ordered
   */
  protected static final String FORM_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getForm() <em>Form</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getForm()
   * @generated
   * @ordered
   */
  protected String form = FORM_EDEFAULT;

  /**
   * The cached value of the '{@link #getType() <em>Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getType()
   * @generated
   * @ordered
   */
  protected TypeRef type;

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
   * The cached value of the '{@link #getTypeParams() <em>Type Params</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTypeParams()
   * @generated
   * @ordered
   */
  protected EList<TypeParamDeclaration> typeParams;

  /**
   * The cached value of the '{@link #getParams() <em>Params</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getParams()
   * @generated
   * @ordered
   */
  protected EList<ParameterDeclaration> params;

  /**
   * The cached value of the '{@link #getBody() <em>Body</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBody()
   * @generated
   * @ordered
   */
  protected Expression body;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected FunctionOrMethodImpl()
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
    return BeeLangPackage.Literals.FUNCTION_OR_METHOD;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getDocumentation()
  {
    return documentation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDocumentation(String newDocumentation)
  {
    String oldDocumentation = documentation;
    documentation = newDocumentation;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.FUNCTION_OR_METHOD__DOCUMENTATION, oldDocumentation, documentation));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Visibility getVisibility()
  {
    return visibility;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setVisibility(Visibility newVisibility)
  {
    Visibility oldVisibility = visibility;
    visibility = newVisibility == null ? VISIBILITY_EDEFAULT : newVisibility;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.FUNCTION_OR_METHOD__VISIBILITY, oldVisibility, visibility));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ExecutionMode getExecutionMode()
  {
    return executionMode;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setExecutionMode(ExecutionMode newExecutionMode)
  {
    ExecutionMode oldExecutionMode = executionMode;
    executionMode = newExecutionMode == null ? EXECUTION_MODE_EDEFAULT : newExecutionMode;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.FUNCTION_OR_METHOD__EXECUTION_MODE, oldExecutionMode, executionMode));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isFinal()
  {
    return final_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFinal(boolean newFinal)
  {
    boolean oldFinal = final_;
    final_ = newFinal;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.FUNCTION_OR_METHOD__FINAL, oldFinal, final_));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isCached()
  {
    return cached;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCached(boolean newCached)
  {
    boolean oldCached = cached;
    cached = newCached;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.FUNCTION_OR_METHOD__CACHED, oldCached, cached));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getForm()
  {
    return form;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setForm(String newForm)
  {
    String oldForm = form;
    form = newForm;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.FUNCTION_OR_METHOD__FORM, oldForm, form));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TypeRef getType()
  {
    return type;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetType(TypeRef newType, NotificationChain msgs)
  {
    TypeRef oldType = type;
    type = newType;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.FUNCTION_OR_METHOD__TYPE, oldType, newType);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setType(TypeRef newType)
  {
    if (newType != type)
    {
      NotificationChain msgs = null;
      if (type != null)
        msgs = ((InternalEObject)type).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.FUNCTION_OR_METHOD__TYPE, null, msgs);
      if (newType != null)
        msgs = ((InternalEObject)newType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.FUNCTION_OR_METHOD__TYPE, null, msgs);
      msgs = basicSetType(newType, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.FUNCTION_OR_METHOD__TYPE, newType, newType));
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
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.FUNCTION_OR_METHOD__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<TypeParamDeclaration> getTypeParams()
  {
    if (typeParams == null)
    {
      typeParams = new EObjectContainmentEList<TypeParamDeclaration>(TypeParamDeclaration.class, this, BeeLangPackage.FUNCTION_OR_METHOD__TYPE_PARAMS);
    }
    return typeParams;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<ParameterDeclaration> getParams()
  {
    if (params == null)
    {
      params = new EObjectContainmentEList<ParameterDeclaration>(ParameterDeclaration.class, this, BeeLangPackage.FUNCTION_OR_METHOD__PARAMS);
    }
    return params;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Expression getBody()
  {
    return body;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetBody(Expression newBody, NotificationChain msgs)
  {
    Expression oldBody = body;
    body = newBody;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.FUNCTION_OR_METHOD__BODY, oldBody, newBody);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setBody(Expression newBody)
  {
    if (newBody != body)
    {
      NotificationChain msgs = null;
      if (body != null)
        msgs = ((InternalEObject)body).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.FUNCTION_OR_METHOD__BODY, null, msgs);
      if (newBody != null)
        msgs = ((InternalEObject)newBody).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.FUNCTION_OR_METHOD__BODY, null, msgs);
      msgs = basicSetBody(newBody, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.FUNCTION_OR_METHOD__BODY, newBody, newBody));
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
      case BeeLangPackage.FUNCTION_OR_METHOD__TYPE:
        return basicSetType(null, msgs);
      case BeeLangPackage.FUNCTION_OR_METHOD__TYPE_PARAMS:
        return ((InternalEList<?>)getTypeParams()).basicRemove(otherEnd, msgs);
      case BeeLangPackage.FUNCTION_OR_METHOD__PARAMS:
        return ((InternalEList<?>)getParams()).basicRemove(otherEnd, msgs);
      case BeeLangPackage.FUNCTION_OR_METHOD__BODY:
        return basicSetBody(null, msgs);
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
      case BeeLangPackage.FUNCTION_OR_METHOD__DOCUMENTATION:
        return getDocumentation();
      case BeeLangPackage.FUNCTION_OR_METHOD__VISIBILITY:
        return getVisibility();
      case BeeLangPackage.FUNCTION_OR_METHOD__EXECUTION_MODE:
        return getExecutionMode();
      case BeeLangPackage.FUNCTION_OR_METHOD__FINAL:
        return isFinal();
      case BeeLangPackage.FUNCTION_OR_METHOD__CACHED:
        return isCached();
      case BeeLangPackage.FUNCTION_OR_METHOD__FORM:
        return getForm();
      case BeeLangPackage.FUNCTION_OR_METHOD__TYPE:
        return getType();
      case BeeLangPackage.FUNCTION_OR_METHOD__NAME:
        return getName();
      case BeeLangPackage.FUNCTION_OR_METHOD__TYPE_PARAMS:
        return getTypeParams();
      case BeeLangPackage.FUNCTION_OR_METHOD__PARAMS:
        return getParams();
      case BeeLangPackage.FUNCTION_OR_METHOD__BODY:
        return getBody();
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
      case BeeLangPackage.FUNCTION_OR_METHOD__DOCUMENTATION:
        setDocumentation((String)newValue);
        return;
      case BeeLangPackage.FUNCTION_OR_METHOD__VISIBILITY:
        setVisibility((Visibility)newValue);
        return;
      case BeeLangPackage.FUNCTION_OR_METHOD__EXECUTION_MODE:
        setExecutionMode((ExecutionMode)newValue);
        return;
      case BeeLangPackage.FUNCTION_OR_METHOD__FINAL:
        setFinal((Boolean)newValue);
        return;
      case BeeLangPackage.FUNCTION_OR_METHOD__CACHED:
        setCached((Boolean)newValue);
        return;
      case BeeLangPackage.FUNCTION_OR_METHOD__FORM:
        setForm((String)newValue);
        return;
      case BeeLangPackage.FUNCTION_OR_METHOD__TYPE:
        setType((TypeRef)newValue);
        return;
      case BeeLangPackage.FUNCTION_OR_METHOD__NAME:
        setName((String)newValue);
        return;
      case BeeLangPackage.FUNCTION_OR_METHOD__TYPE_PARAMS:
        getTypeParams().clear();
        getTypeParams().addAll((Collection<? extends TypeParamDeclaration>)newValue);
        return;
      case BeeLangPackage.FUNCTION_OR_METHOD__PARAMS:
        getParams().clear();
        getParams().addAll((Collection<? extends ParameterDeclaration>)newValue);
        return;
      case BeeLangPackage.FUNCTION_OR_METHOD__BODY:
        setBody((Expression)newValue);
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
      case BeeLangPackage.FUNCTION_OR_METHOD__DOCUMENTATION:
        setDocumentation(DOCUMENTATION_EDEFAULT);
        return;
      case BeeLangPackage.FUNCTION_OR_METHOD__VISIBILITY:
        setVisibility(VISIBILITY_EDEFAULT);
        return;
      case BeeLangPackage.FUNCTION_OR_METHOD__EXECUTION_MODE:
        setExecutionMode(EXECUTION_MODE_EDEFAULT);
        return;
      case BeeLangPackage.FUNCTION_OR_METHOD__FINAL:
        setFinal(FINAL_EDEFAULT);
        return;
      case BeeLangPackage.FUNCTION_OR_METHOD__CACHED:
        setCached(CACHED_EDEFAULT);
        return;
      case BeeLangPackage.FUNCTION_OR_METHOD__FORM:
        setForm(FORM_EDEFAULT);
        return;
      case BeeLangPackage.FUNCTION_OR_METHOD__TYPE:
        setType((TypeRef)null);
        return;
      case BeeLangPackage.FUNCTION_OR_METHOD__NAME:
        setName(NAME_EDEFAULT);
        return;
      case BeeLangPackage.FUNCTION_OR_METHOD__TYPE_PARAMS:
        getTypeParams().clear();
        return;
      case BeeLangPackage.FUNCTION_OR_METHOD__PARAMS:
        getParams().clear();
        return;
      case BeeLangPackage.FUNCTION_OR_METHOD__BODY:
        setBody((Expression)null);
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
      case BeeLangPackage.FUNCTION_OR_METHOD__DOCUMENTATION:
        return DOCUMENTATION_EDEFAULT == null ? documentation != null : !DOCUMENTATION_EDEFAULT.equals(documentation);
      case BeeLangPackage.FUNCTION_OR_METHOD__VISIBILITY:
        return visibility != VISIBILITY_EDEFAULT;
      case BeeLangPackage.FUNCTION_OR_METHOD__EXECUTION_MODE:
        return executionMode != EXECUTION_MODE_EDEFAULT;
      case BeeLangPackage.FUNCTION_OR_METHOD__FINAL:
        return final_ != FINAL_EDEFAULT;
      case BeeLangPackage.FUNCTION_OR_METHOD__CACHED:
        return cached != CACHED_EDEFAULT;
      case BeeLangPackage.FUNCTION_OR_METHOD__FORM:
        return FORM_EDEFAULT == null ? form != null : !FORM_EDEFAULT.equals(form);
      case BeeLangPackage.FUNCTION_OR_METHOD__TYPE:
        return type != null;
      case BeeLangPackage.FUNCTION_OR_METHOD__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case BeeLangPackage.FUNCTION_OR_METHOD__TYPE_PARAMS:
        return typeParams != null && !typeParams.isEmpty();
      case BeeLangPackage.FUNCTION_OR_METHOD__PARAMS:
        return params != null && !params.isEmpty();
      case BeeLangPackage.FUNCTION_OR_METHOD__BODY:
        return body != null;
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
    result.append(" (documentation: ");
    result.append(documentation);
    result.append(", visibility: ");
    result.append(visibility);
    result.append(", executionMode: ");
    result.append(executionMode);
    result.append(", final: ");
    result.append(final_);
    result.append(", cached: ");
    result.append(cached);
    result.append(", form: ");
    result.append(form);
    result.append(", name: ");
    result.append(name);
    result.append(')');
    return result.toString();
  }

} //FunctionOrMethodImpl
