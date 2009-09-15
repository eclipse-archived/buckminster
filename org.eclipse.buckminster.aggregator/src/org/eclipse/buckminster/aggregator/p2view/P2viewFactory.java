/**
 * Copyright (c) 2006-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.p2view;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each non-abstract class of
 * the model. <!-- end-user-doc -->
 * 
 * @see org.eclipse.buckminster.aggregator.p2view.P2viewPackage
 * @generated
 */
public interface P2viewFactory extends EFactory
{
	/**
	 * The singleton instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	P2viewFactory eINSTANCE = org.eclipse.buckminster.aggregator.p2view.impl.P2viewFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Bundle</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Bundle</em>'.
	 * @generated
	 */
	Bundle createBundle();

	/**
	 * Returns a new object of class '<em>Bundles</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Bundles</em>'.
	 * @generated
	 */
	Bundles createBundles();

	/**
	 * Returns a new object of class '<em>Categories</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Categories</em>'.
	 * @generated
	 */
	Categories createCategories();

	/**
	 * Returns a new object of class '<em>Category</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Category</em>'.
	 * @generated
	 */
	Category createCategory();

	/**
	 * Returns a new object of class '<em>Details</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Details</em>'.
	 * @generated
	 */
	Details createDetails();

	/**
	 * Returns a new object of class '<em>Feature</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Feature</em>'.
	 * @generated
	 */
	Feature createFeature();

	/**
	 * Returns a new object of class '<em>Features</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Features</em>'.
	 * @generated
	 */
	Features createFeatures();

	/**
	 * Returns a new object of class '<em>Installable Units</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Installable Units</em>'.
	 * @generated
	 */
	InstallableUnits createInstallableUnits();

	/**
	 * Returns a new object of class '<em>Metadata Repository Structured View</em>'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return a new object of class '<em>Metadata Repository Structured View</em>'.
	 * @generated
	 */
	MetadataRepositoryStructuredView createMetadataRepositoryStructuredView();

	/**
	 * Returns a new object of class '<em>Miscellaneous</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Miscellaneous</em>'.
	 * @generated
	 */
	Miscellaneous createMiscellaneous();

	/**
	 * Returns a new object of class '<em>Other IU</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Other IU</em>'.
	 * @generated
	 */
	OtherIU createOtherIU();

	/**
	 * Returns a new object of class '<em>Product</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Product</em>'.
	 * @generated
	 */
	Product createProduct();

	/**
	 * Returns a new object of class '<em>Products</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Products</em>'.
	 * @generated
	 */
	Products createProducts();

	/**
	 * Returns a new object of class '<em>Properties</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Properties</em>'.
	 * @generated
	 */
	Properties createProperties();

	/**
	 * Returns the package supported by this factory. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the package supported by this factory.
	 * @generated
	 */
	P2viewPackage getP2viewPackage();

} // P2viewFactory
