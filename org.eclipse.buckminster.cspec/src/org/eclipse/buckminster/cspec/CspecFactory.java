/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspec;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each non-abstract class of
 * the model. <!-- end-user-doc -->
 * 
 * @see org.eclipse.buckminster.cspec.CspecPackage
 * @generated
 */
public interface CspecFactory extends EFactory
{
	/**
	 * The singleton instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	CspecFactory eINSTANCE = org.eclipse.buckminster.cspec.impl.CspecFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Action</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Action</em>'.
	 * @generated
	 */
	Action createAction();

	/**
	 * Returns a new object of class '<em>Action Attribute</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Action Attribute</em>'.
	 * @generated
	 */
	ActionAttribute createActionAttribute();

	/**
	 * Returns a new object of class '<em>Alter Action</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Alter Action</em>'.
	 * @generated
	 */
	AlterAction createAlterAction();

	/**
	 * Returns a new object of class '<em>Alter Artifact</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Alter Artifact</em>'.
	 * @generated
	 */
	AlterArtifact createAlterArtifact();

	/**
	 * Returns a new object of class '<em>Alter Group</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Alter Group</em>'.
	 * @generated
	 */
	AlterGroup createAlterGroup();

	/**
	 * Returns a new object of class '<em>Artifact</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Artifact</em>'.
	 * @generated
	 */
	Artifact createArtifact();

	/**
	 * Returns a new object of class '<em>CSpec</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>CSpec</em>'.
	 * @generated
	 */
	CSpec createCSpec();

	/**
	 * Returns a new object of class '<em>CSpec Extension</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>CSpec Extension</em>'.
	 * @generated
	 */
	CSpecExtension createCSpecExtension();

	/**
	 * Returns a new object of class '<em>Generator</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Generator</em>'.
	 * @generated
	 */
	Generator createGenerator();

	/**
	 * Returns a new object of class '<em>Group</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Group</em>'.
	 * @generated
	 */
	Group createGroup();

	/**
	 * Returns a new object of class '<em>Path Group</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Path Group</em>'.
	 * @generated
	 */
	PathGroup createPathGroup();

	/**
	 * Returns a new object of class '<em>Prerequisite</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Prerequisite</em>'.
	 * @generated
	 */
	Prerequisite createPrerequisite();

	/**
	 * Returns a new object of class '<em>Remove</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Remove</em>'.
	 * @generated
	 */
	Remove createRemove();

	/**
	 * Returns a new object of class '<em>Remove Path</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Remove Path</em>'.
	 * @generated
	 */
	RemovePath createRemovePath();

	/**
	 * Returns a new object of class '<em>Rename</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Rename</em>'.
	 * @generated
	 */
	Rename createRename();

	/**
	 * Returns the package supported by this factory. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the package supported by this factory.
	 * @generated
	 */
	CspecPackage getCspecPackage();

} // CspecFactory
