/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang.impl;

import org.eclipse.b3.beeLang.BeeLangPackage;
import org.eclipse.b3.beeLang.ContextBlock;
import org.eclipse.b3.beeLang.RepositoryDeclaration;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Repository Declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.impl.RepositoryDeclarationImpl#getLocation <em>Location</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.RepositoryDeclarationImpl#getResolverClass <em>Resolver Class</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.RepositoryDeclarationImpl#getContext <em>Context</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RepositoryDeclarationImpl extends RepositoryConfigurationImpl implements RepositoryDeclaration
{
  /**
   * The default value of the '{@link #getLocation() <em>Location</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLocation()
   * @generated
   * @ordered
   */
  protected static final String LOCATION_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getLocation() <em>Location</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLocation()
   * @generated
   * @ordered
   */
  protected String location = LOCATION_EDEFAULT;

  /**
   * The default value of the '{@link #getResolverClass() <em>Resolver Class</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getResolverClass()
   * @generated
   * @ordered
   */
  protected static final String RESOLVER_CLASS_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getResolverClass() <em>Resolver Class</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getResolverClass()
   * @generated
   * @ordered
   */
  protected String resolverClass = RESOLVER_CLASS_EDEFAULT;

  /**
   * The cached value of the '{@link #getContext() <em>Context</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getContext()
   * @generated
   * @ordered
   */
  protected ContextBlock context;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected RepositoryDeclarationImpl()
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
    return BeeLangPackage.Literals.REPOSITORY_DECLARATION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getLocation()
  {
    return location;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLocation(String newLocation)
  {
    String oldLocation = location;
    location = newLocation;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.REPOSITORY_DECLARATION__LOCATION, oldLocation, location));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getResolverClass()
  {
    return resolverClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setResolverClass(String newResolverClass)
  {
    String oldResolverClass = resolverClass;
    resolverClass = newResolverClass;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.REPOSITORY_DECLARATION__RESOLVER_CLASS, oldResolverClass, resolverClass));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ContextBlock getContext()
  {
    return context;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetContext(ContextBlock newContext, NotificationChain msgs)
  {
    ContextBlock oldContext = context;
    context = newContext;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.REPOSITORY_DECLARATION__CONTEXT, oldContext, newContext);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setContext(ContextBlock newContext)
  {
    if (newContext != context)
    {
      NotificationChain msgs = null;
      if (context != null)
        msgs = ((InternalEObject)context).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.REPOSITORY_DECLARATION__CONTEXT, null, msgs);
      if (newContext != null)
        msgs = ((InternalEObject)newContext).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.REPOSITORY_DECLARATION__CONTEXT, null, msgs);
      msgs = basicSetContext(newContext, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.REPOSITORY_DECLARATION__CONTEXT, newContext, newContext));
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
      case BeeLangPackage.REPOSITORY_DECLARATION__CONTEXT:
        return basicSetContext(null, msgs);
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
      case BeeLangPackage.REPOSITORY_DECLARATION__LOCATION:
        return getLocation();
      case BeeLangPackage.REPOSITORY_DECLARATION__RESOLVER_CLASS:
        return getResolverClass();
      case BeeLangPackage.REPOSITORY_DECLARATION__CONTEXT:
        return getContext();
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
      case BeeLangPackage.REPOSITORY_DECLARATION__LOCATION:
        setLocation((String)newValue);
        return;
      case BeeLangPackage.REPOSITORY_DECLARATION__RESOLVER_CLASS:
        setResolverClass((String)newValue);
        return;
      case BeeLangPackage.REPOSITORY_DECLARATION__CONTEXT:
        setContext((ContextBlock)newValue);
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
      case BeeLangPackage.REPOSITORY_DECLARATION__LOCATION:
        setLocation(LOCATION_EDEFAULT);
        return;
      case BeeLangPackage.REPOSITORY_DECLARATION__RESOLVER_CLASS:
        setResolverClass(RESOLVER_CLASS_EDEFAULT);
        return;
      case BeeLangPackage.REPOSITORY_DECLARATION__CONTEXT:
        setContext((ContextBlock)null);
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
      case BeeLangPackage.REPOSITORY_DECLARATION__LOCATION:
        return LOCATION_EDEFAULT == null ? location != null : !LOCATION_EDEFAULT.equals(location);
      case BeeLangPackage.REPOSITORY_DECLARATION__RESOLVER_CLASS:
        return RESOLVER_CLASS_EDEFAULT == null ? resolverClass != null : !RESOLVER_CLASS_EDEFAULT.equals(resolverClass);
      case BeeLangPackage.REPOSITORY_DECLARATION__CONTEXT:
        return context != null;
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
    result.append(" (location: ");
    result.append(location);
    result.append(", resolverClass: ");
    result.append(resolverClass);
    result.append(')');
    return result.toString();
  }

} //RepositoryDeclarationImpl
