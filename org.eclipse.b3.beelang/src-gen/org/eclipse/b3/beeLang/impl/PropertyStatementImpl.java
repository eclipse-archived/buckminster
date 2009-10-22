/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang.impl;

import java.util.Collection;

import org.eclipse.b3.beeLang.BeeLangPackage;
import org.eclipse.b3.beeLang.Filter;
import org.eclipse.b3.beeLang.PropertyExpression;
import org.eclipse.b3.beeLang.PropertyStatement;

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
 * An implementation of the model object '<em><b>Property Statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.impl.PropertyStatementImpl#getFilter <em>Filter</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.PropertyStatementImpl#getStatements <em>Statements</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.PropertyStatementImpl#isImmutable <em>Immutable</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.PropertyStatementImpl#getKey <em>Key</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.PropertyStatementImpl#getOp <em>Op</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.PropertyStatementImpl#getValue <em>Value</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.PropertyStatementImpl#getUnsetProperties <em>Unset Properties</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PropertyStatementImpl extends MinimalEObjectImpl.Container implements PropertyStatement
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
   * The cached value of the '{@link #getStatements() <em>Statements</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStatements()
   * @generated
   * @ordered
   */
  protected EList<PropertyStatement> statements;

  /**
   * The default value of the '{@link #isImmutable() <em>Immutable</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isImmutable()
   * @generated
   * @ordered
   */
  protected static final boolean IMMUTABLE_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isImmutable() <em>Immutable</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isImmutable()
   * @generated
   * @ordered
   */
  protected boolean immutable = IMMUTABLE_EDEFAULT;

  /**
   * The default value of the '{@link #getKey() <em>Key</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getKey()
   * @generated
   * @ordered
   */
  protected static final String KEY_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getKey() <em>Key</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getKey()
   * @generated
   * @ordered
   */
  protected String key = KEY_EDEFAULT;

  /**
   * The default value of the '{@link #getOp() <em>Op</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOp()
   * @generated
   * @ordered
   */
  protected static final String OP_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getOp() <em>Op</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOp()
   * @generated
   * @ordered
   */
  protected String op = OP_EDEFAULT;

  /**
   * The cached value of the '{@link #getValue() <em>Value</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getValue()
   * @generated
   * @ordered
   */
  protected PropertyExpression value;

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
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected PropertyStatementImpl()
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
    return BeeLangPackage.Literals.PROPERTY_STATEMENT;
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.PROPERTY_STATEMENT__FILTER, oldFilter, newFilter);
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
        msgs = ((InternalEObject)filter).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.PROPERTY_STATEMENT__FILTER, null, msgs);
      if (newFilter != null)
        msgs = ((InternalEObject)newFilter).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.PROPERTY_STATEMENT__FILTER, null, msgs);
      msgs = basicSetFilter(newFilter, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.PROPERTY_STATEMENT__FILTER, newFilter, newFilter));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<PropertyStatement> getStatements()
  {
    if (statements == null)
    {
      statements = new EObjectContainmentEList<PropertyStatement>(PropertyStatement.class, this, BeeLangPackage.PROPERTY_STATEMENT__STATEMENTS);
    }
    return statements;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isImmutable()
  {
    return immutable;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setImmutable(boolean newImmutable)
  {
    boolean oldImmutable = immutable;
    immutable = newImmutable;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.PROPERTY_STATEMENT__IMMUTABLE, oldImmutable, immutable));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getKey()
  {
    return key;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setKey(String newKey)
  {
    String oldKey = key;
    key = newKey;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.PROPERTY_STATEMENT__KEY, oldKey, key));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getOp()
  {
    return op;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setOp(String newOp)
  {
    String oldOp = op;
    op = newOp;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.PROPERTY_STATEMENT__OP, oldOp, op));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PropertyExpression getValue()
  {
    return value;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetValue(PropertyExpression newValue, NotificationChain msgs)
  {
    PropertyExpression oldValue = value;
    value = newValue;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.PROPERTY_STATEMENT__VALUE, oldValue, newValue);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setValue(PropertyExpression newValue)
  {
    if (newValue != value)
    {
      NotificationChain msgs = null;
      if (value != null)
        msgs = ((InternalEObject)value).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.PROPERTY_STATEMENT__VALUE, null, msgs);
      if (newValue != null)
        msgs = ((InternalEObject)newValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.PROPERTY_STATEMENT__VALUE, null, msgs);
      msgs = basicSetValue(newValue, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.PROPERTY_STATEMENT__VALUE, newValue, newValue));
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
      unsetProperties = new EDataTypeEList<String>(String.class, this, BeeLangPackage.PROPERTY_STATEMENT__UNSET_PROPERTIES);
    }
    return unsetProperties;
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
      case BeeLangPackage.PROPERTY_STATEMENT__FILTER:
        return basicSetFilter(null, msgs);
      case BeeLangPackage.PROPERTY_STATEMENT__STATEMENTS:
        return ((InternalEList<?>)getStatements()).basicRemove(otherEnd, msgs);
      case BeeLangPackage.PROPERTY_STATEMENT__VALUE:
        return basicSetValue(null, msgs);
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
      case BeeLangPackage.PROPERTY_STATEMENT__FILTER:
        return getFilter();
      case BeeLangPackage.PROPERTY_STATEMENT__STATEMENTS:
        return getStatements();
      case BeeLangPackage.PROPERTY_STATEMENT__IMMUTABLE:
        return isImmutable();
      case BeeLangPackage.PROPERTY_STATEMENT__KEY:
        return getKey();
      case BeeLangPackage.PROPERTY_STATEMENT__OP:
        return getOp();
      case BeeLangPackage.PROPERTY_STATEMENT__VALUE:
        return getValue();
      case BeeLangPackage.PROPERTY_STATEMENT__UNSET_PROPERTIES:
        return getUnsetProperties();
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
      case BeeLangPackage.PROPERTY_STATEMENT__FILTER:
        setFilter((Filter)newValue);
        return;
      case BeeLangPackage.PROPERTY_STATEMENT__STATEMENTS:
        getStatements().clear();
        getStatements().addAll((Collection<? extends PropertyStatement>)newValue);
        return;
      case BeeLangPackage.PROPERTY_STATEMENT__IMMUTABLE:
        setImmutable((Boolean)newValue);
        return;
      case BeeLangPackage.PROPERTY_STATEMENT__KEY:
        setKey((String)newValue);
        return;
      case BeeLangPackage.PROPERTY_STATEMENT__OP:
        setOp((String)newValue);
        return;
      case BeeLangPackage.PROPERTY_STATEMENT__VALUE:
        setValue((PropertyExpression)newValue);
        return;
      case BeeLangPackage.PROPERTY_STATEMENT__UNSET_PROPERTIES:
        getUnsetProperties().clear();
        getUnsetProperties().addAll((Collection<? extends String>)newValue);
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
      case BeeLangPackage.PROPERTY_STATEMENT__FILTER:
        setFilter((Filter)null);
        return;
      case BeeLangPackage.PROPERTY_STATEMENT__STATEMENTS:
        getStatements().clear();
        return;
      case BeeLangPackage.PROPERTY_STATEMENT__IMMUTABLE:
        setImmutable(IMMUTABLE_EDEFAULT);
        return;
      case BeeLangPackage.PROPERTY_STATEMENT__KEY:
        setKey(KEY_EDEFAULT);
        return;
      case BeeLangPackage.PROPERTY_STATEMENT__OP:
        setOp(OP_EDEFAULT);
        return;
      case BeeLangPackage.PROPERTY_STATEMENT__VALUE:
        setValue((PropertyExpression)null);
        return;
      case BeeLangPackage.PROPERTY_STATEMENT__UNSET_PROPERTIES:
        getUnsetProperties().clear();
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
      case BeeLangPackage.PROPERTY_STATEMENT__FILTER:
        return filter != null;
      case BeeLangPackage.PROPERTY_STATEMENT__STATEMENTS:
        return statements != null && !statements.isEmpty();
      case BeeLangPackage.PROPERTY_STATEMENT__IMMUTABLE:
        return immutable != IMMUTABLE_EDEFAULT;
      case BeeLangPackage.PROPERTY_STATEMENT__KEY:
        return KEY_EDEFAULT == null ? key != null : !KEY_EDEFAULT.equals(key);
      case BeeLangPackage.PROPERTY_STATEMENT__OP:
        return OP_EDEFAULT == null ? op != null : !OP_EDEFAULT.equals(op);
      case BeeLangPackage.PROPERTY_STATEMENT__VALUE:
        return value != null;
      case BeeLangPackage.PROPERTY_STATEMENT__UNSET_PROPERTIES:
        return unsetProperties != null && !unsetProperties.isEmpty();
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
    result.append(" (immutable: ");
    result.append(immutable);
    result.append(", key: ");
    result.append(key);
    result.append(", op: ");
    result.append(op);
    result.append(", unsetProperties: ");
    result.append(unsetProperties);
    result.append(')');
    return result.toString();
  }

} //PropertyStatementImpl
