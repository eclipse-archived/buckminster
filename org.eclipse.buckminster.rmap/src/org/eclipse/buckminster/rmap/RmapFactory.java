/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap;

import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a
 * create method for each non-abstract class of the model. <!-- end-user-doc -->
 * 
 * @see org.eclipse.buckminster.rmap.RmapPackage
 * @generated
 */
public interface RmapFactory extends EFactory {
	/**
	 * The singleton instance of the factory. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	RmapFactory eINSTANCE = org.eclipse.buckminster.rmap.impl.RmapFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Document Root</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Document Root</em>'.
	 * @generated
	 */
	EObject createDocumentRoot();

	/**
	 * Returns a new object of class '<em>Locator</em>'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Locator</em>'.
	 * @generated
	 */
	Locator createLocator();

	/**
	 * Returns a new object of class '<em>Properties</em>'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Properties</em>'.
	 * @generated
	 */
	Properties createProperties();

	/**
	 * Returns a new object of class '<em>Provider</em>'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Provider</em>'.
	 * @generated
	 */
	Provider createProvider();

	/**
	 * Returns a new object of class '<em>Redirect</em>'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Redirect</em>'.
	 * @generated
	 */
	Redirect createRedirect();

	/**
	 * Returns a new object of class '<em>Resource Map</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Resource Map</em>'.
	 * @generated
	 */
	ResourceMap createResourceMap();

	/**
	 * Returns a new object of class '<em>Search Path</em>'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Search Path</em>'.
	 * @generated
	 */
	SearchPath createSearchPath();

	/**
	 * Returns a new object of class '<em>Transform</em>'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Transform</em>'.
	 * @generated
	 */
	Transform createTransform();

	/**
	 * Returns a new object of class '<em>URI Matcher</em>'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>URI Matcher</em>'.
	 * @generated
	 */
	URIMatcher createURIMatcher();

	/**
	 * Returns a new object of class '<em>Version Converter</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Version Converter</em>'.
	 * @generated
	 */
	VersionConverter createVersionConverter();

	/**
	 * Returns the package supported by this factory. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the package supported by this factory.
	 * @generated
	 */
	RmapPackage getRmapPackage();

} // RmapFactory
