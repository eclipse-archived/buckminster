/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang.impl;

import org.eclipse.b3.beeLang.BeeLangPackage;
import org.eclipse.b3.beeLang.Import;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Import</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.b3.beeLang.impl.ImportImpl#isReexport <em>Reexport</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.ImportImpl#getImporter <em>Importer</em>}</li>
 *   <li>{@link org.eclipse.b3.beeLang.impl.ImportImpl#getNameSpace <em>Name Space</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ImportImpl extends MinimalEObjectImpl.Container implements Import
{
  /**
   * The default value of the '{@link #isReexport() <em>Reexport</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isReexport()
   * @generated
   * @ordered
   */
  protected static final boolean REEXPORT_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isReexport() <em>Reexport</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isReexport()
   * @generated
   * @ordered
   */
  protected boolean reexport = REEXPORT_EDEFAULT;

  /**
   * The cached value of the '{@link #getImporter() <em>Importer</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getImporter()
   * @generated
   * @ordered
   */
  protected EObject importer;

  /**
   * The default value of the '{@link #getNameSpace() <em>Name Space</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNameSpace()
   * @generated
   * @ordered
   */
  protected static final String NAME_SPACE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getNameSpace() <em>Name Space</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNameSpace()
   * @generated
   * @ordered
   */
  protected String nameSpace = NAME_SPACE_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ImportImpl()
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
    return BeeLangPackage.Literals.IMPORT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isReexport()
  {
    return reexport;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setReexport(boolean newReexport)
  {
    boolean oldReexport = reexport;
    reexport = newReexport;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.IMPORT__REEXPORT, oldReexport, reexport));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EObject getImporter()
  {
    return importer;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetImporter(EObject newImporter, NotificationChain msgs)
  {
    EObject oldImporter = importer;
    importer = newImporter;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BeeLangPackage.IMPORT__IMPORTER, oldImporter, newImporter);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setImporter(EObject newImporter)
  {
    if (newImporter != importer)
    {
      NotificationChain msgs = null;
      if (importer != null)
        msgs = ((InternalEObject)importer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.IMPORT__IMPORTER, null, msgs);
      if (newImporter != null)
        msgs = ((InternalEObject)newImporter).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BeeLangPackage.IMPORT__IMPORTER, null, msgs);
      msgs = basicSetImporter(newImporter, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.IMPORT__IMPORTER, newImporter, newImporter));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getNameSpace()
  {
    return nameSpace;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setNameSpace(String newNameSpace)
  {
    String oldNameSpace = nameSpace;
    nameSpace = newNameSpace;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, BeeLangPackage.IMPORT__NAME_SPACE, oldNameSpace, nameSpace));
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
      case BeeLangPackage.IMPORT__IMPORTER:
        return basicSetImporter(null, msgs);
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
      case BeeLangPackage.IMPORT__REEXPORT:
        return isReexport();
      case BeeLangPackage.IMPORT__IMPORTER:
        return getImporter();
      case BeeLangPackage.IMPORT__NAME_SPACE:
        return getNameSpace();
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
      case BeeLangPackage.IMPORT__REEXPORT:
        setReexport((Boolean)newValue);
        return;
      case BeeLangPackage.IMPORT__IMPORTER:
        setImporter((EObject)newValue);
        return;
      case BeeLangPackage.IMPORT__NAME_SPACE:
        setNameSpace((String)newValue);
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
      case BeeLangPackage.IMPORT__REEXPORT:
        setReexport(REEXPORT_EDEFAULT);
        return;
      case BeeLangPackage.IMPORT__IMPORTER:
        setImporter((EObject)null);
        return;
      case BeeLangPackage.IMPORT__NAME_SPACE:
        setNameSpace(NAME_SPACE_EDEFAULT);
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
      case BeeLangPackage.IMPORT__REEXPORT:
        return reexport != REEXPORT_EDEFAULT;
      case BeeLangPackage.IMPORT__IMPORTER:
        return importer != null;
      case BeeLangPackage.IMPORT__NAME_SPACE:
        return NAME_SPACE_EDEFAULT == null ? nameSpace != null : !NAME_SPACE_EDEFAULT.equals(nameSpace);
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
    result.append(" (reexport: ");
    result.append(reexport);
    result.append(", nameSpace: ");
    result.append(nameSpace);
    result.append(')');
    return result.toString();
  }

} //ImportImpl
