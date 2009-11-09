/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang.impl;

import java.util.Collection;

import org.eclipse.b3.beeLang.BeeLangPackage;
import org.eclipse.b3.beeLang.RepositoryConfiguration;
import org.eclipse.b3.beeLang.ResolutionStrategy;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Resolution Strategy</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.impl.ResolutionStrategyImpl#getStrategy <em>Strategy</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.ResolutionStrategyImpl#getRepositoryConfig <em>Repository Config</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ResolutionStrategyImpl extends RepositoryConfigurationImpl implements ResolutionStrategy
{
  /**
   * The default value of the '{@link #getStrategy() <em>Strategy</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStrategy()
   * @generated
   * @ordered
   */
  protected static final String STRATEGY_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getStrategy() <em>Strategy</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStrategy()
   * @generated
   * @ordered
   */
  protected String strategy = STRATEGY_EDEFAULT;

  /**
   * The cached value of the '{@link #getRepositoryConfig() <em>Repository Config</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRepositoryConfig()
   * @generated
   * @ordered
   */
  protected EList<RepositoryConfiguration> repositoryConfig;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ResolutionStrategyImpl()
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
    return BeeLangPackage.Literals.RESOLUTION_STRATEGY;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getStrategy()
  {
    return strategy;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setStrategy(String newStrategy)
  {
    String oldStrategy = strategy;
    strategy = newStrategy;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.RESOLUTION_STRATEGY__STRATEGY, oldStrategy, strategy));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<RepositoryConfiguration> getRepositoryConfig()
  {
    if (repositoryConfig == null)
    {
      repositoryConfig = new EObjectContainmentEList<RepositoryConfiguration>(RepositoryConfiguration.class, this, BeeLangPackage.RESOLUTION_STRATEGY__REPOSITORY_CONFIG);
    }
    return repositoryConfig;
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
      case BeeLangPackage.RESOLUTION_STRATEGY__REPOSITORY_CONFIG:
        return ((InternalEList<?>)getRepositoryConfig()).basicRemove(otherEnd, msgs);
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
      case BeeLangPackage.RESOLUTION_STRATEGY__STRATEGY:
        return getStrategy();
      case BeeLangPackage.RESOLUTION_STRATEGY__REPOSITORY_CONFIG:
        return getRepositoryConfig();
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
      case BeeLangPackage.RESOLUTION_STRATEGY__STRATEGY:
        setStrategy((String)newValue);
        return;
      case BeeLangPackage.RESOLUTION_STRATEGY__REPOSITORY_CONFIG:
        getRepositoryConfig().clear();
        getRepositoryConfig().addAll((Collection<? extends RepositoryConfiguration>)newValue);
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
      case BeeLangPackage.RESOLUTION_STRATEGY__STRATEGY:
        setStrategy(STRATEGY_EDEFAULT);
        return;
      case BeeLangPackage.RESOLUTION_STRATEGY__REPOSITORY_CONFIG:
        getRepositoryConfig().clear();
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
      case BeeLangPackage.RESOLUTION_STRATEGY__STRATEGY:
        return STRATEGY_EDEFAULT == null ? strategy != null : !STRATEGY_EDEFAULT.equals(strategy);
      case BeeLangPackage.RESOLUTION_STRATEGY__REPOSITORY_CONFIG:
        return repositoryConfig != null && !repositoryConfig.isEmpty();
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
    result.append(" (strategy: ");
    result.append(strategy);
    result.append(')');
    return result.toString();
  }

} //ResolutionStrategyImpl
