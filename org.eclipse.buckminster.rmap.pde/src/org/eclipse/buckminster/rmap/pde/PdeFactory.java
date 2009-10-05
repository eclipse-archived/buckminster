/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap.pde;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each non-abstract class of
 * the model. <!-- end-user-doc -->
 * 
 * @see org.eclipse.buckminster.rmap.pde.PdePackage
 * @generated
 */
public interface PdeFactory extends EFactory
{
	/**
	 * The singleton instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	PdeFactory eINSTANCE = org.eclipse.buckminster.rmap.pde.impl.PdeFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>PDE Map Provider</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>PDE Map Provider</em>'.
	 * @generated
	 */
	PDEMapProvider createPDEMapProvider();

	/**
	 * Returns the package supported by this factory. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the package supported by this factory.
	 * @generated
	 */
	PdePackage getPdePackage();

} // PdeFactory
