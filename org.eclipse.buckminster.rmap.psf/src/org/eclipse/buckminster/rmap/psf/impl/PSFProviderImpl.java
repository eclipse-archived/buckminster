/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap.psf.impl;

import org.eclipse.buckminster.rmap.impl.ProviderImpl;

import org.eclipse.buckminster.rmap.psf.PSFProvider;
import org.eclipse.buckminster.rmap.psf.PsfPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>PSF Provider</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.rmap.psf.impl.PSFProviderImpl#getPsfFile <em>Psf File</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class PSFProviderImpl extends ProviderImpl implements PSFProvider
{
	/**
	 * The default value of the '{@link #getPsfFile() <em>Psf File</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getPsfFile()
	 * @generated
	 * @ordered
	 */
	protected static final String PSF_FILE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPsfFile() <em>Psf File</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getPsfFile()
	 * @generated
	 * @ordered
	 */
	protected String psfFile = PSF_FILE_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected PSFProviderImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType)
	{
		switch(featureID)
		{
		case PsfPackage.PSF_PROVIDER__PSF_FILE:
			return getPsfFile();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID)
	{
		switch(featureID)
		{
		case PsfPackage.PSF_PROVIDER__PSF_FILE:
			return PSF_FILE_EDEFAULT == null
					? psfFile != null
					: !PSF_FILE_EDEFAULT.equals(psfFile);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue)
	{
		switch(featureID)
		{
		case PsfPackage.PSF_PROVIDER__PSF_FILE:
			setPsfFile((String)newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass()
	{
		return PsfPackage.Literals.PSF_PROVIDER;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eUnset(int featureID)
	{
		switch(featureID)
		{
		case PsfPackage.PSF_PROVIDER__PSF_FILE:
			setPsfFile(PSF_FILE_EDEFAULT);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getPsfFile()
	{
		return psfFile;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setPsfFile(String newPsfFile)
	{
		String oldPsfFile = psfFile;
		psfFile = newPsfFile;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PsfPackage.PSF_PROVIDER__PSF_FILE, oldPsfFile,
					psfFile));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String toString()
	{
		if(eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (psfFile: ");
		result.append(psfFile);
		result.append(')');
		return result.toString();
	}

} // PSFProviderImpl
