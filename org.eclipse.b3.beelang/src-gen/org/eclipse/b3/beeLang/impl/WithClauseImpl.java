/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang.impl;

import java.util.Collection;

import org.eclipse.b3.beeLang.BeeLangPackage;
import org.eclipse.b3.beeLang.ConcernBlock;
import org.eclipse.b3.beeLang.Expression;
import org.eclipse.b3.beeLang.PropertySet;
import org.eclipse.b3.beeLang.WithClause;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>With Clause</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.impl.WithClauseImpl#getReferences <em>References</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.WithClauseImpl#getProperties <em>Properties</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.WithClauseImpl#getConcern <em>Concern</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class WithClauseImpl extends MinimalEObjectImpl.Container implements WithClause
{
  /**
   * The cached value of the '{@link #getReferences() <em>References</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getReferences()
   * @generated
   * @ordered
   */
  protected EList<Expression> references;

  /**
   * The cached value of the '{@link #getProperties() <em>Properties</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getProperties()
   * @generated
   * @ordered
   */
  protected EList<PropertySet> properties;

  /**
   * The cached value of the '{@link #getConcern() <em>Concern</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getConcern()
   * @generated
   * @ordered
   */
  protected EList<ConcernBlock> concern;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected WithClauseImpl()
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
    return BeeLangPackage.Literals.WITH_CLAUSE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Expression> getReferences()
  {
    if (references == null)
    {
      references = new EObjectContainmentEList<Expression>(Expression.class, this, BeeLangPackage.WITH_CLAUSE__REFERENCES);
    }
    return references;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<PropertySet> getProperties()
  {
    if (properties == null)
    {
      properties = new EObjectContainmentEList<PropertySet>(PropertySet.class, this, BeeLangPackage.WITH_CLAUSE__PROPERTIES);
    }
    return properties;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<ConcernBlock> getConcern()
  {
    if (concern == null)
    {
      concern = new EObjectContainmentEList<ConcernBlock>(ConcernBlock.class, this, BeeLangPackage.WITH_CLAUSE__CONCERN);
    }
    return concern;
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
      case BeeLangPackage.WITH_CLAUSE__REFERENCES:
        return ((InternalEList<?>)getReferences()).basicRemove(otherEnd, msgs);
      case BeeLangPackage.WITH_CLAUSE__PROPERTIES:
        return ((InternalEList<?>)getProperties()).basicRemove(otherEnd, msgs);
      case BeeLangPackage.WITH_CLAUSE__CONCERN:
        return ((InternalEList<?>)getConcern()).basicRemove(otherEnd, msgs);
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
      case BeeLangPackage.WITH_CLAUSE__REFERENCES:
        return getReferences();
      case BeeLangPackage.WITH_CLAUSE__PROPERTIES:
        return getProperties();
      case BeeLangPackage.WITH_CLAUSE__CONCERN:
        return getConcern();
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
      case BeeLangPackage.WITH_CLAUSE__REFERENCES:
        getReferences().clear();
        getReferences().addAll((Collection<? extends Expression>)newValue);
        return;
      case BeeLangPackage.WITH_CLAUSE__PROPERTIES:
        getProperties().clear();
        getProperties().addAll((Collection<? extends PropertySet>)newValue);
        return;
      case BeeLangPackage.WITH_CLAUSE__CONCERN:
        getConcern().clear();
        getConcern().addAll((Collection<? extends ConcernBlock>)newValue);
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
      case BeeLangPackage.WITH_CLAUSE__REFERENCES:
        getReferences().clear();
        return;
      case BeeLangPackage.WITH_CLAUSE__PROPERTIES:
        getProperties().clear();
        return;
      case BeeLangPackage.WITH_CLAUSE__CONCERN:
        getConcern().clear();
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
      case BeeLangPackage.WITH_CLAUSE__REFERENCES:
        return references != null && !references.isEmpty();
      case BeeLangPackage.WITH_CLAUSE__PROPERTIES:
        return properties != null && !properties.isEmpty();
      case BeeLangPackage.WITH_CLAUSE__CONCERN:
        return concern != null && !concern.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //WithClauseImpl
