/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.impl;

import java.util.Collection;

import org.eclipse.buckminster.aggregator.AggregatorPackage;
import org.eclipse.buckminster.aggregator.Category;
import org.eclipse.buckminster.aggregator.Feature;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Feature</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.buckminster.aggregator.impl.FeatureImpl#getCategory <em>Category</em>}</li>
 *   <li>{@link org.eclipse.buckminster.aggregator.impl.FeatureImpl#isInProduct <em>In Product</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FeatureImpl extends InstallationUnitImpl implements Feature
{
  /**
   * The cached value of the '{@link #getCategory() <em>Category</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCategory()
   * @generated
   * @ordered
   */
  protected EList<Category> category;

  /**
   * The default value of the '{@link #isInProduct() <em>In Product</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isInProduct()
   * @generated
   * @ordered
   */
  protected static final boolean IN_PRODUCT_EDEFAULT = true;

  /**
   * The flag representing the value of the '{@link #isInProduct() <em>In Product</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isInProduct()
   * @generated
   * @ordered
   */
  protected static final int IN_PRODUCT_EFLAG = 1 << 1;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected FeatureImpl()
  {
    super();
    eFlags |= IN_PRODUCT_EFLAG;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return AggregatorPackage.Literals.FEATURE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Category> getCategory()
  {
    if (category == null)
    {
      category = new EObjectWithInverseResolvingEList.ManyInverse<Category>(Category.class, this, AggregatorPackage.FEATURE__CATEGORY, AggregatorPackage.CATEGORY__FEATURES);
    }
    return category;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isInProduct()
  {
    return (eFlags & IN_PRODUCT_EFLAG) != 0;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setInProduct(boolean newInProduct)
  {
    boolean oldInProduct = (eFlags & IN_PRODUCT_EFLAG) != 0;
    if (newInProduct) eFlags |= IN_PRODUCT_EFLAG; else eFlags &= ~IN_PRODUCT_EFLAG;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, AggregatorPackage.FEATURE__IN_PRODUCT, oldInProduct, newInProduct));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case AggregatorPackage.FEATURE__CATEGORY:
        return ((InternalEList<InternalEObject>)(InternalEList<?>)getCategory()).basicAdd(otherEnd, msgs);
    }
    return super.eInverseAdd(otherEnd, featureID, msgs);
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
      case AggregatorPackage.FEATURE__CATEGORY:
        return ((InternalEList<?>)getCategory()).basicRemove(otherEnd, msgs);
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
      case AggregatorPackage.FEATURE__CATEGORY:
        return getCategory();
      case AggregatorPackage.FEATURE__IN_PRODUCT:
        return isInProduct();
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
      case AggregatorPackage.FEATURE__CATEGORY:
        getCategory().clear();
        getCategory().addAll((Collection<? extends Category>)newValue);
        return;
      case AggregatorPackage.FEATURE__IN_PRODUCT:
        setInProduct((Boolean)newValue);
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
      case AggregatorPackage.FEATURE__CATEGORY:
        getCategory().clear();
        return;
      case AggregatorPackage.FEATURE__IN_PRODUCT:
        setInProduct(IN_PRODUCT_EDEFAULT);
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
      case AggregatorPackage.FEATURE__CATEGORY:
        return category != null && !category.isEmpty();
      case AggregatorPackage.FEATURE__IN_PRODUCT:
        return ((eFlags & IN_PRODUCT_EFLAG) != 0) != IN_PRODUCT_EDEFAULT;
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
    result.append(" (inProduct: ");
    result.append((eFlags & IN_PRODUCT_EFLAG) != 0);
    result.append(')');
    return result.toString();
  }

} //FeatureImpl
