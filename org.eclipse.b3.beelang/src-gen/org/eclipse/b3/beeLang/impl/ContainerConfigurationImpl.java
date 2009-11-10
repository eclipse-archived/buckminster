/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang.impl;

import org.eclipse.b3.beeLang.BeeLangPackage;
import org.eclipse.b3.beeLang.ContainerConfiguration;
import org.eclipse.b3.beeLang.ContextBlock;
import org.eclipse.b3.beeLang.TypeRef;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Container Configuration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.impl.ContainerConfigurationImpl#getDocumentation <em>Documentation</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.ContainerConfigurationImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.ContainerConfigurationImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.ContainerConfigurationImpl#getContextBlock <em>Context Block</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ContainerConfigurationImpl extends MinimalEObjectImpl.Container implements ContainerConfiguration
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
   * The cached value of the '{@link #getType() <em>Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getType()
   * @generated
   * @ordered
   */
  protected TypeRef type;

  /**
   * The cached value of the '{@link #getContextBlock() <em>Context Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getContextBlock()
   * @generated
   * @ordered
   */
  protected ContextBlock contextBlock;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ContainerConfigurationImpl()
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
    return BeeLangPackage.Literals.CONTAINER_CONFIGURATION;
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
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.CONTAINER_CONFIGURATION__DOCUMENTATION, oldDocumentation, documentation));
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
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.CONTAINER_CONFIGURATION__NAME, oldName, name));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.CONTAINER_CONFIGURATION__TYPE, oldType, newType);
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
        msgs = ((InternalEObject)type).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.CONTAINER_CONFIGURATION__TYPE, null, msgs);
      if (newType != null)
        msgs = ((InternalEObject)newType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.CONTAINER_CONFIGURATION__TYPE, null, msgs);
      msgs = basicSetType(newType, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.CONTAINER_CONFIGURATION__TYPE, newType, newType));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ContextBlock getContextBlock()
  {
    return contextBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetContextBlock(ContextBlock newContextBlock, NotificationChain msgs)
  {
    ContextBlock oldContextBlock = contextBlock;
    contextBlock = newContextBlock;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.CONTAINER_CONFIGURATION__CONTEXT_BLOCK, oldContextBlock, newContextBlock);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setContextBlock(ContextBlock newContextBlock)
  {
    if (newContextBlock != contextBlock)
    {
      NotificationChain msgs = null;
      if (contextBlock != null)
        msgs = ((InternalEObject)contextBlock).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.CONTAINER_CONFIGURATION__CONTEXT_BLOCK, null, msgs);
      if (newContextBlock != null)
        msgs = ((InternalEObject)newContextBlock).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.CONTAINER_CONFIGURATION__CONTEXT_BLOCK, null, msgs);
      msgs = basicSetContextBlock(newContextBlock, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.CONTAINER_CONFIGURATION__CONTEXT_BLOCK, newContextBlock, newContextBlock));
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
      case BeeLangPackage.CONTAINER_CONFIGURATION__TYPE:
        return basicSetType(null, msgs);
      case BeeLangPackage.CONTAINER_CONFIGURATION__CONTEXT_BLOCK:
        return basicSetContextBlock(null, msgs);
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
      case BeeLangPackage.CONTAINER_CONFIGURATION__DOCUMENTATION:
        return getDocumentation();
      case BeeLangPackage.CONTAINER_CONFIGURATION__NAME:
        return getName();
      case BeeLangPackage.CONTAINER_CONFIGURATION__TYPE:
        return getType();
      case BeeLangPackage.CONTAINER_CONFIGURATION__CONTEXT_BLOCK:
        return getContextBlock();
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
      case BeeLangPackage.CONTAINER_CONFIGURATION__DOCUMENTATION:
        setDocumentation((String)newValue);
        return;
      case BeeLangPackage.CONTAINER_CONFIGURATION__NAME:
        setName((String)newValue);
        return;
      case BeeLangPackage.CONTAINER_CONFIGURATION__TYPE:
        setType((TypeRef)newValue);
        return;
      case BeeLangPackage.CONTAINER_CONFIGURATION__CONTEXT_BLOCK:
        setContextBlock((ContextBlock)newValue);
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
      case BeeLangPackage.CONTAINER_CONFIGURATION__DOCUMENTATION:
        setDocumentation(DOCUMENTATION_EDEFAULT);
        return;
      case BeeLangPackage.CONTAINER_CONFIGURATION__NAME:
        setName(NAME_EDEFAULT);
        return;
      case BeeLangPackage.CONTAINER_CONFIGURATION__TYPE:
        setType((TypeRef)null);
        return;
      case BeeLangPackage.CONTAINER_CONFIGURATION__CONTEXT_BLOCK:
        setContextBlock((ContextBlock)null);
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
      case BeeLangPackage.CONTAINER_CONFIGURATION__DOCUMENTATION:
        return DOCUMENTATION_EDEFAULT == null ? documentation != null : !DOCUMENTATION_EDEFAULT.equals(documentation);
      case BeeLangPackage.CONTAINER_CONFIGURATION__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case BeeLangPackage.CONTAINER_CONFIGURATION__TYPE:
        return type != null;
      case BeeLangPackage.CONTAINER_CONFIGURATION__CONTEXT_BLOCK:
        return contextBlock != null;
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
    result.append(", name: ");
    result.append(name);
    result.append(')');
    return result.toString();
  }

} //ContainerConfigurationImpl
