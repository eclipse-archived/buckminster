/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang.impl;

import java.util.Collection;

import org.eclipse.b3.beeLang.BeeLangPackage;
import org.eclipse.b3.beeLang.CompoundReferences;
import org.eclipse.b3.beeLang.Prerequisite;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Compound References</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.impl.CompoundReferencesImpl#getPrerequisites <em>Prerequisites</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CompoundReferencesImpl extends PrerequisiteEntryImpl implements CompoundReferences
{
  /**
   * The cached value of the '{@link #getPrerequisites() <em>Prerequisites</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPrerequisites()
   * @generated
   * @ordered
   */
  protected EList<Prerequisite> prerequisites;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected CompoundReferencesImpl()
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
    return BeeLangPackage.Literals.COMPOUND_REFERENCES;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Prerequisite> getPrerequisites()
  {
    if (prerequisites == null)
    {
      prerequisites = new EObjectContainmentEList<Prerequisite>(Prerequisite.class, this, BeeLangPackage.COMPOUND_REFERENCES__PREREQUISITES);
    }
    return prerequisites;
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
      case BeeLangPackage.COMPOUND_REFERENCES__PREREQUISITES:
        return ((InternalEList<?>)getPrerequisites()).basicRemove(otherEnd, msgs);
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
      case BeeLangPackage.COMPOUND_REFERENCES__PREREQUISITES:
        return getPrerequisites();
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
      case BeeLangPackage.COMPOUND_REFERENCES__PREREQUISITES:
        getPrerequisites().clear();
        getPrerequisites().addAll((Collection<? extends Prerequisite>)newValue);
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
      case BeeLangPackage.COMPOUND_REFERENCES__PREREQUISITES:
        getPrerequisites().clear();
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
      case BeeLangPackage.COMPOUND_REFERENCES__PREREQUISITES:
        return prerequisites != null && !prerequisites.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //CompoundReferencesImpl
