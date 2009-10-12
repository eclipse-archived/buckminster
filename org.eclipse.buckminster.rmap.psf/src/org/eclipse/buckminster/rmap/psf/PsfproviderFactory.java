/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap.psf;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each non-abstract class of
 * the model. <!-- end-user-doc -->
 * 
 * @see org.eclipse.buckminster.rmap.psf.PsfproviderPackage
 * @generated
 */
public interface PsfproviderFactory extends EFactory
{
	/**
	 * The singleton instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	PsfproviderFactory eINSTANCE = org.eclipse.buckminster.rmap.psf.impl.PsfproviderFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>PSF Provider</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>PSF Provider</em>'.
	 * @generated
	 */
	PSFProvider createPSFProvider();

	/**
	 * Returns the package supported by this factory. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the package supported by this factory.
	 * @generated
	 */
	PsfproviderPackage getPsfproviderPackage();

} // PsfproviderFactory
