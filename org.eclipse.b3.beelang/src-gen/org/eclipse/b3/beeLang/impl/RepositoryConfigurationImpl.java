/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang.impl;

import org.eclipse.b3.beeLang.BeeLangPackage;
import org.eclipse.b3.beeLang.CompoundConcern;
import org.eclipse.b3.beeLang.RepositoryConfiguration;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Repository Configuration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.impl.RepositoryConfigurationImpl#getLocation <em>Location</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.RepositoryConfigurationImpl#getResolverClass <em>Resolver Class</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.RepositoryConfigurationImpl#getAdvice <em>Advice</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RepositoryConfigurationImpl extends MinimalEObjectImpl.Container implements RepositoryConfiguration
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
   * The cached value of the '{@link #getAdvice() <em>Advice</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAdvice()
   * @generated
   * @ordered
   */
  protected CompoundConcern advice;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected RepositoryConfigurationImpl()
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
    return BeeLangPackage.Literals.REPOSITORY_CONFIGURATION;
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
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.REPOSITORY_CONFIGURATION__LOCATION, oldLocation, location));
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
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.REPOSITORY_CONFIGURATION__RESOLVER_CLASS, oldResolverClass, resolverClass));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CompoundConcern getAdvice()
  {
    return advice;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetAdvice(CompoundConcern newAdvice, NotificationChain msgs)
  {
    CompoundConcern oldAdvice = advice;
    advice = newAdvice;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.REPOSITORY_CONFIGURATION__ADVICE, oldAdvice, newAdvice);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAdvice(CompoundConcern newAdvice)
  {
    if (newAdvice != advice)
    {
      NotificationChain msgs = null;
      if (advice != null)
        msgs = ((InternalEObject)advice).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.REPOSITORY_CONFIGURATION__ADVICE, null, msgs);
      if (newAdvice != null)
        msgs = ((InternalEObject)newAdvice).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.REPOSITORY_CONFIGURATION__ADVICE, null, msgs);
      msgs = basicSetAdvice(newAdvice, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.REPOSITORY_CONFIGURATION__ADVICE, newAdvice, newAdvice));
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
      case BeeLangPackage.REPOSITORY_CONFIGURATION__ADVICE:
        return basicSetAdvice(null, msgs);
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
      case BeeLangPackage.REPOSITORY_CONFIGURATION__LOCATION:
        return getLocation();
      case BeeLangPackage.REPOSITORY_CONFIGURATION__RESOLVER_CLASS:
        return getResolverClass();
      case BeeLangPackage.REPOSITORY_CONFIGURATION__ADVICE:
        return getAdvice();
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
      case BeeLangPackage.REPOSITORY_CONFIGURATION__LOCATION:
        setLocation((String)newValue);
        return;
      case BeeLangPackage.REPOSITORY_CONFIGURATION__RESOLVER_CLASS:
        setResolverClass((String)newValue);
        return;
      case BeeLangPackage.REPOSITORY_CONFIGURATION__ADVICE:
        setAdvice((CompoundConcern)newValue);
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
      case BeeLangPackage.REPOSITORY_CONFIGURATION__LOCATION:
        setLocation(LOCATION_EDEFAULT);
        return;
      case BeeLangPackage.REPOSITORY_CONFIGURATION__RESOLVER_CLASS:
        setResolverClass(RESOLVER_CLASS_EDEFAULT);
        return;
      case BeeLangPackage.REPOSITORY_CONFIGURATION__ADVICE:
        setAdvice((CompoundConcern)null);
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
      case BeeLangPackage.REPOSITORY_CONFIGURATION__LOCATION:
        return LOCATION_EDEFAULT == null ? location != null : !LOCATION_EDEFAULT.equals(location);
      case BeeLangPackage.REPOSITORY_CONFIGURATION__RESOLVER_CLASS:
        return RESOLVER_CLASS_EDEFAULT == null ? resolverClass != null : !RESOLVER_CLASS_EDEFAULT.equals(resolverClass);
      case BeeLangPackage.REPOSITORY_CONFIGURATION__ADVICE:
        return advice != null;
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

} //RepositoryConfigurationImpl
