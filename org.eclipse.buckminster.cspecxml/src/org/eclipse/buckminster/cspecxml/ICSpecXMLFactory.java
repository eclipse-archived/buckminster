/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspecxml;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a
 * create method for each non-abstract class of the model. <!-- end-user-doc -->
 * 
 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage
 * @generated
 */
public interface ICSpecXMLFactory extends EFactory {
	/**
	 * The singleton instance of the factory. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	ICSpecXMLFactory eINSTANCE = org.eclipse.buckminster.cspecxml.impl.CSpecXMLFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Action</em>'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Action</em>'.
	 * @generated
	 */
	IAction createAction();

	/**
	 * Returns a new object of class '<em>Action Artifact</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Action Artifact</em>'.
	 * @generated
	 */
	IActionArtifact createActionArtifact();

	/**
	 * Returns a new object of class '<em>Actions Type</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Actions Type</em>'.
	 * @generated
	 */
	IActionsType createActionsType();

	/**
	 * Returns a new object of class '<em>Alter Action</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Alter Action</em>'.
	 * @generated
	 */
	IAlterAction createAlterAction();

	/**
	 * Returns a new object of class '<em>Alter Actions Type</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Alter Actions Type</em>'.
	 * @generated
	 */
	IAlterActionsType createAlterActionsType();

	/**
	 * Returns a new object of class '<em>Alter Artifact</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Alter Artifact</em>'.
	 * @generated
	 */
	IAlterArtifact createAlterArtifact();

	/**
	 * Returns a new object of class '<em>Alter Artifacts Type</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Alter Artifacts Type</em>'.
	 * @generated
	 */
	IAlterArtifactsType createAlterArtifactsType();

	/**
	 * Returns a new object of class '<em>Alter Dependencies Type</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Alter Dependencies Type</em>'.
	 * @generated
	 */
	IAlterDependenciesType createAlterDependenciesType();

	/**
	 * Returns a new object of class '<em>Alter Group</em>'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Alter Group</em>'.
	 * @generated
	 */
	IAlterGroup createAlterGroup();

	/**
	 * Returns a new object of class '<em>Alter Groups Type</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Alter Groups Type</em>'.
	 * @generated
	 */
	IAlterGroupsType createAlterGroupsType();

	/**
	 * Returns a new object of class '<em>Alter Prerequisites</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Alter Prerequisites</em>'.
	 * @generated
	 */
	IAlterPrerequisites createAlterPrerequisites();

	/**
	 * Returns a new object of class '<em>Alter Products Type</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Alter Products Type</em>'.
	 * @generated
	 */
	IAlterProductsType createAlterProductsType();

	/**
	 * Returns a new object of class '<em>Alter Properties</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Alter Properties</em>'.
	 * @generated
	 */
	IAlterProperties createAlterProperties();

	/**
	 * Returns a new object of class '<em>Artifact</em>'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Artifact</em>'.
	 * @generated
	 */
	IArtifact createArtifact();

	/**
	 * Returns a new object of class '<em>Artifacts Type</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Artifacts Type</em>'.
	 * @generated
	 */
	IArtifactsType createArtifactsType();

	/**
	 * Returns a new object of class '<em>Attribute</em>'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Attribute</em>'.
	 * @generated
	 */
	IAttribute createAttribute();

	/**
	 * Returns a new object of class '<em>Component Request</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Component Request</em>'.
	 * @generated
	 */
	IComponentRequest createComponentRequest();

	/**
	 * Returns a new object of class '<em>Component Spec</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Component Spec</em>'.
	 * @generated
	 */
	IComponentSpec createComponentSpec();

	/**
	 * Returns a new object of class '<em>Component Spec Base</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Component Spec Base</em>'.
	 * @generated
	 */
	IComponentSpecBase createComponentSpecBase();

	/**
	 * Returns a new object of class '<em>CSpec Extension</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>CSpec Extension</em>'.
	 * @generated
	 */
	ICSpecExtension createCSpecExtension();

	/**
	 * Returns a new object of class '<em>Definitions</em>'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Definitions</em>'.
	 * @generated
	 */
	IDefinitions createDefinitions();

	/**
	 * Returns a new object of class '<em>Dependencies Type</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Dependencies Type</em>'.
	 * @generated
	 */
	IDependenciesType createDependenciesType();

	/**
	 * Returns a new object of class '<em>Document Root</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Document Root</em>'.
	 * @generated
	 */
	IDocumentRoot createDocumentRoot();

	/**
	 * Returns a new object of class '<em>Generator</em>'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Generator</em>'.
	 * @generated
	 */
	IGenerator createGenerator();

	/**
	 * Returns a new object of class '<em>Generators Type</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Generators Type</em>'.
	 * @generated
	 */
	IGeneratorsType createGeneratorsType();

	/**
	 * Returns a new object of class '<em>Group</em>'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Group</em>'.
	 * @generated
	 */
	IGroup createGroup();

	/**
	 * Returns a new object of class '<em>Groups Type</em>'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Groups Type</em>'.
	 * @generated
	 */
	IGroupsType createGroupsType();

	/**
	 * Returns a new object of class '<em>Import</em>'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Import</em>'.
	 * @generated
	 */
	IImport createImport();

	/**
	 * Returns a new object of class '<em>Parameter Type</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Parameter Type</em>'.
	 * @generated
	 */
	IParameterType createParameterType();

	/**
	 * Returns a new object of class '<em>Path</em>'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Path</em>'.
	 * @generated
	 */
	IPath createPath();

	/**
	 * Returns a new object of class '<em>Prerequisite</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Prerequisite</em>'.
	 * @generated
	 */
	IPrerequisite createPrerequisite();

	/**
	 * Returns a new object of class '<em>Prerequisites</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Prerequisites</em>'.
	 * @generated
	 */
	IPrerequisites createPrerequisites();

	/**
	 * Returns a new object of class '<em>Products Type</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Products Type</em>'.
	 * @generated
	 */
	IProductsType createProductsType();

	/**
	 * Returns a new object of class '<em>Properties</em>'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Properties</em>'.
	 * @generated
	 */
	IProperties createProperties();

	/**
	 * Returns a new object of class '<em>Remove</em>'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Remove</em>'.
	 * @generated
	 */
	IRemove createRemove();

	/**
	 * Returns a new object of class '<em>Remove Path</em>'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Remove Path</em>'.
	 * @generated
	 */
	IRemovePath createRemovePath();

	/**
	 * Returns a new object of class '<em>Remove Property</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Remove Property</em>'.
	 * @generated
	 */
	IRemoveProperty createRemoveProperty();

	/**
	 * Returns a new object of class '<em>Rename</em>'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Rename</em>'.
	 * @generated
	 */
	IRename createRename();

	/**
	 * Returns the package supported by this factory. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the package supported by this factory.
	 * @generated
	 */
	ICSpecXMLPackage getCSpecXMLPackage();

} // ICSpecXMLFactory
