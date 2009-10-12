/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap.psf.impl;

import org.eclipse.buckminster.rmap.psf.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!-- end-user-doc -->
 * 
 * @generated
 */
public class psfFactoryImpl extends EFactoryImpl implements psfFactory
{
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static psfPackage getPackage()
	{
		return psfPackage.eINSTANCE;
	}

	/**
	 * Creates the default factory implementation. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static psfFactory init()
	{
		try
		{
			psfFactory thepsfFactory = (psfFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.eclipse.org/buckminster/PSFProvider-1.0");
			if(thepsfFactory != null)
			{
				return thepsfFactory;
			}
		}
		catch(Exception exception)
		{
			EcorePlugin.INSTANCE.log(exception);
		}
		return new psfFactoryImpl();
	}

	/**
	 * Creates an instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public psfFactoryImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass)
	{
		switch(eClass.getClassifierID())
		{
		case psfPackage.PSF_PROVIDER:
			return createPSFProvider();
		default:
			throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public PSFProvider createPSFProvider()
	{
		PSFProviderImpl psfProvider = new PSFProviderImpl();
		return psfProvider;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public psfPackage getpsfPackage()
	{
		return (psfPackage)getEPackage();
	}

} // psfFactoryImpl
