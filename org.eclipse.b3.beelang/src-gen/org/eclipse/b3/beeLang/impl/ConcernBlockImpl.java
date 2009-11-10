/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang.impl;

import java.util.Collection;

import org.eclipse.b3.beeLang.BeeLangPackage;
import org.eclipse.b3.beeLang.ConcernBlock;
import org.eclipse.b3.beeLang.Context;
import org.eclipse.b3.beeLang.Statment;

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
 * An implementation of the model object '<em><b>Concern Block</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.impl.ConcernBlockImpl#getSuperConcerns <em>Super Concerns</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.ConcernBlockImpl#getContexts <em>Contexts</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.ConcernBlockImpl#getFunctions <em>Functions</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConcernBlockImpl extends MinimalEObjectImpl.Container implements ConcernBlock
{
  /**
   * The cached value of the '{@link #getSuperConcerns() <em>Super Concerns</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSuperConcerns()
   * @generated
   * @ordered
   */
  protected EList<String> superConcerns;

  /**
   * The cached value of the '{@link #getContexts() <em>Contexts</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getContexts()
   * @generated
   * @ordered
   */
  protected EList<Context> contexts;

  /**
   * The cached value of the '{@link #getFunctions() <em>Functions</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFunctions()
   * @generated
   * @ordered
   */
  protected EList<Statment> functions;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ConcernBlockImpl()
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
    return BeeLangPackage.Literals.CONCERN_BLOCK;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getSuperConcerns()
  {
    if (superConcerns == null)
    {
      superConcerns = new EDataTypeEList<String>(String.class, this, BeeLangPackage.CONCERN_BLOCK__SUPER_CONCERNS);
    }
    return superConcerns;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Context> getContexts()
  {
    if (contexts == null)
    {
      contexts = new EObjectContainmentEList<Context>(Context.class, this, BeeLangPackage.CONCERN_BLOCK__CONTEXTS);
    }
    return contexts;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Statment> getFunctions()
  {
    if (functions == null)
    {
      functions = new EObjectContainmentEList<Statment>(Statment.class, this, BeeLangPackage.CONCERN_BLOCK__FUNCTIONS);
    }
    return functions;
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
      case BeeLangPackage.CONCERN_BLOCK__CONTEXTS:
        return ((InternalEList<?>)getContexts()).basicRemove(otherEnd, msgs);
      case BeeLangPackage.CONCERN_BLOCK__FUNCTIONS:
        return ((InternalEList<?>)getFunctions()).basicRemove(otherEnd, msgs);
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
      case BeeLangPackage.CONCERN_BLOCK__SUPER_CONCERNS:
        return getSuperConcerns();
      case BeeLangPackage.CONCERN_BLOCK__CONTEXTS:
        return getContexts();
      case BeeLangPackage.CONCERN_BLOCK__FUNCTIONS:
        return getFunctions();
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
      case BeeLangPackage.CONCERN_BLOCK__SUPER_CONCERNS:
        getSuperConcerns().clear();
        getSuperConcerns().addAll((Collection<? extends String>)newValue);
        return;
      case BeeLangPackage.CONCERN_BLOCK__CONTEXTS:
        getContexts().clear();
        getContexts().addAll((Collection<? extends Context>)newValue);
        return;
      case BeeLangPackage.CONCERN_BLOCK__FUNCTIONS:
        getFunctions().clear();
        getFunctions().addAll((Collection<? extends Statment>)newValue);
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
      case BeeLangPackage.CONCERN_BLOCK__SUPER_CONCERNS:
        getSuperConcerns().clear();
        return;
      case BeeLangPackage.CONCERN_BLOCK__CONTEXTS:
        getContexts().clear();
        return;
      case BeeLangPackage.CONCERN_BLOCK__FUNCTIONS:
        getFunctions().clear();
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
      case BeeLangPackage.CONCERN_BLOCK__SUPER_CONCERNS:
        return superConcerns != null && !superConcerns.isEmpty();
      case BeeLangPackage.CONCERN_BLOCK__CONTEXTS:
        return contexts != null && !contexts.isEmpty();
      case BeeLangPackage.CONCERN_BLOCK__FUNCTIONS:
        return functions != null && !functions.isEmpty();
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
    result.append(" (superConcerns: ");
    result.append(superConcerns);
    result.append(')');
    return result.toString();
  }

} //ConcernBlockImpl
