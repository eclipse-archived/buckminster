/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap.psf.util;

import org.eclipse.buckminster.rmap.Provider;
import org.eclipse.buckminster.rmap.psf.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides an adapter <code>createXXX</code>
 * method for each class of the model. <!-- end-user-doc -->
 * 
 * @see org.eclipse.buckminster.rmap.psf.PsfproviderPackage
 * @generated
 */
public class PsfproviderAdapterFactory extends AdapterFactoryImpl
{
	/**
	 * The cached model package. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected static PsfproviderPackage modelPackage;

	/**
	 * The switch that delegates to the <code>createXXX</code> methods. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected PsfproviderSwitch<Adapter> modelSwitch = new PsfproviderSwitch<Adapter>()
	{
		@Override
		public Adapter caseProvider(Provider object)
		{
			return createProviderAdapter();
		}

		@Override
		public Adapter casePSFProvider(PSFProvider object)
		{
			return createPSFProviderAdapter();
		}

		@Override
		public Adapter defaultCase(EObject object)
		{
			return createEObjectAdapter();
		}
	};

	/**
	 * Creates an instance of the adapter factory. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public PsfproviderAdapterFactory()
	{
		if(modelPackage == null)
		{
			modelPackage = PsfproviderPackage.eINSTANCE;
		}
	}

	/**
	 * Creates an adapter for the <code>target</code>. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param target
	 *            the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target)
	{
		return modelSwitch.doSwitch((EObject)target);
	}

	/**
	 * Creates a new adapter for the default case. <!-- begin-user-doc --> This default implementation returns null.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.buckminster.rmap.Provider <em>Provider</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful
	 * to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.rmap.Provider
	 * @generated
	 */
	public Adapter createProviderAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.buckminster.rmap.psf.PSFProvider
	 * <em>PSF Provider</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
	 * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
	 * -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.rmap.psf.PSFProvider
	 * @generated
	 */
	public Adapter createPSFProviderAdapter()
	{
		return null;
	}

	/**
	 * Returns whether this factory is applicable for the type of the object. <!-- begin-user-doc --> This
	 * implementation returns <code>true</code> if the object is either the model's package or is an instance object of
	 * the model. <!-- end-user-doc -->
	 * 
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object)
	{
		if(object == modelPackage)
		{
			return true;
		}
		if(object instanceof EObject)
		{
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

} // PsfproviderAdapterFactory
