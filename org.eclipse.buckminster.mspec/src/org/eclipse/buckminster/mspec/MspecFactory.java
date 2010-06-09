/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.mspec;

import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a
 * create method for each non-abstract class of the model. <!-- end-user-doc -->
 * 
 * @see org.eclipse.buckminster.mspec.MspecPackage
 * @generated
 */
public interface MspecFactory extends EFactory {
	/**
	 * The singleton instance of the factory. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	MspecFactory eINSTANCE = org.eclipse.buckminster.mspec.impl.MspecFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Document Root</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Document Root</em>'.
	 * @generated
	 */
	EObject createDocumentRoot();

	/**
	 * Returns a new object of class '<em>Materialization Directive</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Materialization Directive</em>'.
	 * @generated
	 */
	MaterializationDirective createMaterializationDirective();

	/**
	 * Returns a new object of class '<em>Materialization Node</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Materialization Node</em>'.
	 * @generated
	 */
	MaterializationNode createMaterializationNode();

	/**
	 * Returns a new object of class '<em>Materialization Spec</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Materialization Spec</em>'.
	 * @generated
	 */
	MaterializationSpec createMaterializationSpec();

	/**
	 * Returns a new object of class '<em>Unpack</em>'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Unpack</em>'.
	 * @generated
	 */
	Unpack createUnpack();

	/**
	 * Returns the package supported by this factory. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the package supported by this factory.
	 * @generated
	 */
	MspecPackage getMspecPackage();

} // MspecFactory
