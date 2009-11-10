/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang.impl;

import java.util.Collection;

import org.eclipse.b3.beeLang.BeeLangPackage;
import org.eclipse.b3.beeLang.BeeModel;
import org.eclipse.b3.beeLang.BuildUnit;
import org.eclipse.b3.beeLang.Concern;
import org.eclipse.b3.beeLang.Import;
import org.eclipse.b3.beeLang.Statment;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Bee Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.impl.BeeModelImpl#getImports <em>Imports</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.BeeModelImpl#getFunctions <em>Functions</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.BeeModelImpl#getConcern <em>Concern</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.BeeModelImpl#getBody <em>Body</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BeeModelImpl extends MinimalEObjectImpl.Container implements BeeModel
{
  /**
   * The cached value of the '{@link #getImports() <em>Imports</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getImports()
   * @generated
   * @ordered
   */
  protected EList<Import> imports;

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
   * The cached value of the '{@link #getConcern() <em>Concern</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getConcern()
   * @generated
   * @ordered
   */
  protected EList<Concern> concern;

  /**
   * The cached value of the '{@link #getBody() <em>Body</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBody()
   * @generated
   * @ordered
   */
  protected BuildUnit body;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected BeeModelImpl()
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
    return BeeLangPackage.Literals.BEE_MODEL;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Import> getImports()
  {
    if (imports == null)
    {
      imports = new EObjectContainmentEList<Import>(Import.class, this, BeeLangPackage.BEE_MODEL__IMPORTS);
    }
    return imports;
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
      functions = new EObjectContainmentEList<Statment>(Statment.class, this, BeeLangPackage.BEE_MODEL__FUNCTIONS);
    }
    return functions;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Concern> getConcern()
  {
    if (concern == null)
    {
      concern = new EObjectContainmentEList<Concern>(Concern.class, this, BeeLangPackage.BEE_MODEL__CONCERN);
    }
    return concern;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BuildUnit getBody()
  {
    return body;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetBody(BuildUnit newBody, NotificationChain msgs)
  {
    BuildUnit oldBody = body;
    body = newBody;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.BEE_MODEL__BODY, oldBody, newBody);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setBody(BuildUnit newBody)
  {
    if (newBody != body)
    {
      NotificationChain msgs = null;
      if (body != null)
        msgs = ((InternalEObject)body).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.BEE_MODEL__BODY, null, msgs);
      if (newBody != null)
        msgs = ((InternalEObject)newBody).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.BEE_MODEL__BODY, null, msgs);
      msgs = basicSetBody(newBody, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.BEE_MODEL__BODY, newBody, newBody));
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
      case BeeLangPackage.BEE_MODEL__IMPORTS:
        return ((InternalEList<?>)getImports()).basicRemove(otherEnd, msgs);
      case BeeLangPackage.BEE_MODEL__FUNCTIONS:
        return ((InternalEList<?>)getFunctions()).basicRemove(otherEnd, msgs);
      case BeeLangPackage.BEE_MODEL__CONCERN:
        return ((InternalEList<?>)getConcern()).basicRemove(otherEnd, msgs);
      case BeeLangPackage.BEE_MODEL__BODY:
        return basicSetBody(null, msgs);
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
      case BeeLangPackage.BEE_MODEL__IMPORTS:
        return getImports();
      case BeeLangPackage.BEE_MODEL__FUNCTIONS:
        return getFunctions();
      case BeeLangPackage.BEE_MODEL__CONCERN:
        return getConcern();
      case BeeLangPackage.BEE_MODEL__BODY:
        return getBody();
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
      case BeeLangPackage.BEE_MODEL__IMPORTS:
        getImports().clear();
        getImports().addAll((Collection<? extends Import>)newValue);
        return;
      case BeeLangPackage.BEE_MODEL__FUNCTIONS:
        getFunctions().clear();
        getFunctions().addAll((Collection<? extends Statment>)newValue);
        return;
      case BeeLangPackage.BEE_MODEL__CONCERN:
        getConcern().clear();
        getConcern().addAll((Collection<? extends Concern>)newValue);
        return;
      case BeeLangPackage.BEE_MODEL__BODY:
        setBody((BuildUnit)newValue);
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
      case BeeLangPackage.BEE_MODEL__IMPORTS:
        getImports().clear();
        return;
      case BeeLangPackage.BEE_MODEL__FUNCTIONS:
        getFunctions().clear();
        return;
      case BeeLangPackage.BEE_MODEL__CONCERN:
        getConcern().clear();
        return;
      case BeeLangPackage.BEE_MODEL__BODY:
        setBody((BuildUnit)null);
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
      case BeeLangPackage.BEE_MODEL__IMPORTS:
        return imports != null && !imports.isEmpty();
      case BeeLangPackage.BEE_MODEL__FUNCTIONS:
        return functions != null && !functions.isEmpty();
      case BeeLangPackage.BEE_MODEL__CONCERN:
        return concern != null && !concern.isEmpty();
      case BeeLangPackage.BEE_MODEL__BODY:
        return body != null;
    }
    return super.eIsSet(featureID);
  }

} //BeeModelImpl
