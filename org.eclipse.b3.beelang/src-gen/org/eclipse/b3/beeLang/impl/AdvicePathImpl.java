/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang.impl;

import java.util.Collection;

import org.eclipse.b3.beeLang.AdvicePath;
import org.eclipse.b3.beeLang.AdvicePathElement;
import org.eclipse.b3.beeLang.BeeLangPackage;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Advice Path</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.impl.AdvicePathImpl#getPathElements <em>Path Elements</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.AdvicePathImpl#getPathElement <em>Path Element</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AdvicePathImpl extends MinimalEObjectImpl.Container implements AdvicePath
{
  /**
   * The cached value of the '{@link #getPathElements() <em>Path Elements</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPathElements()
   * @generated
   * @ordered
   */
  protected EList<AdvicePathElement> pathElements;

  /**
   * The cached value of the '{@link #getPathElement() <em>Path Element</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPathElement()
   * @generated
   * @ordered
   */
  protected EList<AdvicePathElement> pathElement;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected AdvicePathImpl()
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
    return BeeLangPackage.Literals.ADVICE_PATH;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<AdvicePathElement> getPathElements()
  {
    if (pathElements == null)
    {
      pathElements = new EObjectContainmentEList<AdvicePathElement>(AdvicePathElement.class, this, BeeLangPackage.ADVICE_PATH__PATH_ELEMENTS);
    }
    return pathElements;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<AdvicePathElement> getPathElement()
  {
    if (pathElement == null)
    {
      pathElement = new EObjectContainmentEList<AdvicePathElement>(AdvicePathElement.class, this, BeeLangPackage.ADVICE_PATH__PATH_ELEMENT);
    }
    return pathElement;
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
      case BeeLangPackage.ADVICE_PATH__PATH_ELEMENTS:
        return ((InternalEList<?>)getPathElements()).basicRemove(otherEnd, msgs);
      case BeeLangPackage.ADVICE_PATH__PATH_ELEMENT:
        return ((InternalEList<?>)getPathElement()).basicRemove(otherEnd, msgs);
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
      case BeeLangPackage.ADVICE_PATH__PATH_ELEMENTS:
        return getPathElements();
      case BeeLangPackage.ADVICE_PATH__PATH_ELEMENT:
        return getPathElement();
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
      case BeeLangPackage.ADVICE_PATH__PATH_ELEMENTS:
        getPathElements().clear();
        getPathElements().addAll((Collection<? extends AdvicePathElement>)newValue);
        return;
      case BeeLangPackage.ADVICE_PATH__PATH_ELEMENT:
        getPathElement().clear();
        getPathElement().addAll((Collection<? extends AdvicePathElement>)newValue);
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
      case BeeLangPackage.ADVICE_PATH__PATH_ELEMENTS:
        getPathElements().clear();
        return;
      case BeeLangPackage.ADVICE_PATH__PATH_ELEMENT:
        getPathElement().clear();
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
      case BeeLangPackage.ADVICE_PATH__PATH_ELEMENTS:
        return pathElements != null && !pathElements.isEmpty();
      case BeeLangPackage.ADVICE_PATH__PATH_ELEMENT:
        return pathElement != null && !pathElement.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //AdvicePathImpl
