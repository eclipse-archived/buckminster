/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspecxml;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains
 * accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * 
 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLFactory
 * @model kind="package"
 * @generated
 */
public interface ICSpecXMLPackage extends EPackage {
	/**
	 * <!-- begin-user-doc --> Defines literals for the meta objects that
	 * represent
	 * <ul>
	 * <li>each class,</li>
	 * <li>each feature of each class,</li>
	 * <li>each enum,</li>
	 * <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.buckminster.cspecxml.impl.ActionImpl
		 * <em>Action</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
		 * -->
		 * 
		 * @see org.eclipse.buckminster.cspecxml.impl.ActionImpl
		 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getAction()
		 * @generated
		 */
		EClass ACTION = eINSTANCE.getAction();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute
		 * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ACTION__GROUP = eINSTANCE.getAction_Group();

		/**
		 * The meta object literal for the '<em><b>Definitions</b></em>'
		 * containment reference list feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ACTION__DEFINITIONS = eINSTANCE.getAction_Definitions();

		/**
		 * The meta object literal for the '<em><b>Actor Properties</b></em>'
		 * containment reference list feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ACTION__ACTOR_PROPERTIES = eINSTANCE.getAction_ActorProperties();

		/**
		 * The meta object literal for the '<em><b>Properties</b></em>'
		 * containment reference list feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ACTION__PROPERTIES = eINSTANCE.getAction_Properties();

		/**
		 * The meta object literal for the '<em><b>Prerequisites</b></em>'
		 * containment reference list feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ACTION__PREREQUISITES = eINSTANCE.getAction_Prerequisites();

		/**
		 * The meta object literal for the '<em><b>Products</b></em>'
		 * containment reference list feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ACTION__PRODUCTS = eINSTANCE.getAction_Products();

		/**
		 * The meta object literal for the '<em><b>Actor</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ACTION__ACTOR = eINSTANCE.getAction_Actor();

		/**
		 * The meta object literal for the '<em><b>Always</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ACTION__ALWAYS = eINSTANCE.getAction_Always();

		/**
		 * The meta object literal for the '
		 * <em><b>Assign Console Support</b></em>' attribute feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ACTION__ASSIGN_CONSOLE_SUPPORT = eINSTANCE.getAction_AssignConsoleSupport();

		/**
		 * The meta object literal for the '<em><b>Enabled</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ACTION__ENABLED = eINSTANCE.getAction_Enabled();

		/**
		 * The meta object literal for the '<em><b>Filter</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ACTION__FILTER = eINSTANCE.getAction_Filter();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.buckminster.cspecxml.impl.ActionArtifactImpl
		 * <em>Action Artifact</em>}' class. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.cspecxml.impl.ActionArtifactImpl
		 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getActionArtifact()
		 * @generated
		 */
		EClass ACTION_ARTIFACT = eINSTANCE.getActionArtifact();

		/**
		 * The meta object literal for the '<em><b>Alias</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ACTION_ARTIFACT__ALIAS = eINSTANCE.getActionArtifact_Alias();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.buckminster.cspecxml.impl.ActionsTypeImpl
		 * <em>Actions Type</em>}' class. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.cspecxml.impl.ActionsTypeImpl
		 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getActionsType()
		 * @generated
		 */
		EClass ACTIONS_TYPE = eINSTANCE.getActionsType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute
		 * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ACTIONS_TYPE__GROUP = eINSTANCE.getActionsType_Group();

		/**
		 * The meta object literal for the '<em><b>Public</b></em>' containment
		 * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ACTIONS_TYPE__PUBLIC = eINSTANCE.getActionsType_Public();

		/**
		 * The meta object literal for the '<em><b>Private</b></em>' containment
		 * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ACTIONS_TYPE__PRIVATE = eINSTANCE.getActionsType_Private();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.buckminster.cspecxml.impl.AlterActionImpl
		 * <em>Alter Action</em>}' class. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.cspecxml.impl.AlterActionImpl
		 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getAlterAction()
		 * @generated
		 */
		EClass ALTER_ACTION = eINSTANCE.getAlterAction();

		/**
		 * The meta object literal for the '<em><b>Group1</b></em>' attribute
		 * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ALTER_ACTION__GROUP1 = eINSTANCE.getAlterAction_Group1();

		/**
		 * The meta object literal for the '<em><b>Alter Prerequisites</b></em>'
		 * containment reference list feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ALTER_ACTION__ALTER_PREREQUISITES = eINSTANCE.getAlterAction_AlterPrerequisites();

		/**
		 * The meta object literal for the '
		 * <em><b>Alter Actor Properties</b></em>' containment reference list
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ALTER_ACTION__ALTER_ACTOR_PROPERTIES = eINSTANCE.getAlterAction_AlterActorProperties();

		/**
		 * The meta object literal for the '<em><b>Alter Properties</b></em>'
		 * containment reference list feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ALTER_ACTION__ALTER_PROPERTIES = eINSTANCE.getAlterAction_AlterProperties();

		/**
		 * The meta object literal for the '<em><b>Alter Products</b></em>'
		 * containment reference list feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ALTER_ACTION__ALTER_PRODUCTS = eINSTANCE.getAlterAction_AlterProducts();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.buckminster.cspecxml.impl.AlterActionsTypeImpl
		 * <em>Alter Actions Type</em>}' class. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.cspecxml.impl.AlterActionsTypeImpl
		 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getAlterActionsType()
		 * @generated
		 */
		EClass ALTER_ACTIONS_TYPE = eINSTANCE.getAlterActionsType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute
		 * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ALTER_ACTIONS_TYPE__GROUP = eINSTANCE.getAlterActionsType_Group();

		/**
		 * The meta object literal for the '<em><b>Public</b></em>' containment
		 * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ALTER_ACTIONS_TYPE__PUBLIC = eINSTANCE.getAlterActionsType_Public();

		/**
		 * The meta object literal for the '<em><b>Private</b></em>' containment
		 * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ALTER_ACTIONS_TYPE__PRIVATE = eINSTANCE.getAlterActionsType_Private();

		/**
		 * The meta object literal for the '<em><b>Remove</b></em>' containment
		 * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ALTER_ACTIONS_TYPE__REMOVE = eINSTANCE.getAlterActionsType_Remove();

		/**
		 * The meta object literal for the '<em><b>Rename</b></em>' containment
		 * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ALTER_ACTIONS_TYPE__RENAME = eINSTANCE.getAlterActionsType_Rename();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.buckminster.cspecxml.impl.AlterArtifactImpl
		 * <em>Alter Artifact</em>}' class. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.cspecxml.impl.AlterArtifactImpl
		 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getAlterArtifact()
		 * @generated
		 */
		EClass ALTER_ARTIFACT = eINSTANCE.getAlterArtifact();

		/**
		 * The meta object literal for the '<em><b>Group1</b></em>' attribute
		 * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ALTER_ARTIFACT__GROUP1 = eINSTANCE.getAlterArtifact_Group1();

		/**
		 * The meta object literal for the '<em><b>Remove Path</b></em>'
		 * containment reference list feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ALTER_ARTIFACT__REMOVE_PATH = eINSTANCE.getAlterArtifact_RemovePath();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.buckminster.cspecxml.impl.AlterArtifactsTypeImpl
		 * <em>Alter Artifacts Type</em>}' class. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.cspecxml.impl.AlterArtifactsTypeImpl
		 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getAlterArtifactsType()
		 * @generated
		 */
		EClass ALTER_ARTIFACTS_TYPE = eINSTANCE.getAlterArtifactsType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute
		 * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ALTER_ARTIFACTS_TYPE__GROUP = eINSTANCE.getAlterArtifactsType_Group();

		/**
		 * The meta object literal for the '<em><b>Public</b></em>' containment
		 * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ALTER_ARTIFACTS_TYPE__PUBLIC = eINSTANCE.getAlterArtifactsType_Public();

		/**
		 * The meta object literal for the '<em><b>Private</b></em>' containment
		 * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ALTER_ARTIFACTS_TYPE__PRIVATE = eINSTANCE.getAlterArtifactsType_Private();

		/**
		 * The meta object literal for the '<em><b>Remove</b></em>' containment
		 * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ALTER_ARTIFACTS_TYPE__REMOVE = eINSTANCE.getAlterArtifactsType_Remove();

		/**
		 * The meta object literal for the '<em><b>Rename</b></em>' containment
		 * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ALTER_ARTIFACTS_TYPE__RENAME = eINSTANCE.getAlterArtifactsType_Rename();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.buckminster.cspecxml.impl.AlterDependenciesTypeImpl
		 * <em>Alter Dependencies Type</em>}' class. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.cspecxml.impl.AlterDependenciesTypeImpl
		 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getAlterDependenciesType()
		 * @generated
		 */
		EClass ALTER_DEPENDENCIES_TYPE = eINSTANCE.getAlterDependenciesType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute
		 * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ALTER_DEPENDENCIES_TYPE__GROUP = eINSTANCE.getAlterDependenciesType_Group();

		/**
		 * The meta object literal for the '<em><b>Dependency</b></em>'
		 * containment reference list feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ALTER_DEPENDENCIES_TYPE__DEPENDENCY = eINSTANCE.getAlterDependenciesType_Dependency();

		/**
		 * The meta object literal for the '<em><b>Remove</b></em>' containment
		 * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ALTER_DEPENDENCIES_TYPE__REMOVE = eINSTANCE.getAlterDependenciesType_Remove();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.buckminster.cspecxml.impl.AlterGroupImpl
		 * <em>Alter Group</em>}' class. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.cspecxml.impl.AlterGroupImpl
		 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getAlterGroup()
		 * @generated
		 */
		EClass ALTER_GROUP = eINSTANCE.getAlterGroup();

		/**
		 * The meta object literal for the '<em><b>Group1</b></em>' attribute
		 * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ALTER_GROUP__GROUP1 = eINSTANCE.getAlterGroup_Group1();

		/**
		 * The meta object literal for the '<em><b>Alter Attribute</b></em>'
		 * containment reference list feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ALTER_GROUP__ALTER_ATTRIBUTE = eINSTANCE.getAlterGroup_AlterAttribute();

		/**
		 * The meta object literal for the '<em><b>Remove</b></em>' containment
		 * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ALTER_GROUP__REMOVE = eINSTANCE.getAlterGroup_Remove();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.buckminster.cspecxml.impl.AlterGroupsTypeImpl
		 * <em>Alter Groups Type</em>}' class. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.cspecxml.impl.AlterGroupsTypeImpl
		 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getAlterGroupsType()
		 * @generated
		 */
		EClass ALTER_GROUPS_TYPE = eINSTANCE.getAlterGroupsType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute
		 * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ALTER_GROUPS_TYPE__GROUP = eINSTANCE.getAlterGroupsType_Group();

		/**
		 * The meta object literal for the '<em><b>Public</b></em>' containment
		 * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ALTER_GROUPS_TYPE__PUBLIC = eINSTANCE.getAlterGroupsType_Public();

		/**
		 * The meta object literal for the '<em><b>Private</b></em>' containment
		 * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ALTER_GROUPS_TYPE__PRIVATE = eINSTANCE.getAlterGroupsType_Private();

		/**
		 * The meta object literal for the '<em><b>Remove</b></em>' containment
		 * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ALTER_GROUPS_TYPE__REMOVE = eINSTANCE.getAlterGroupsType_Remove();

		/**
		 * The meta object literal for the '<em><b>Rename</b></em>' containment
		 * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ALTER_GROUPS_TYPE__RENAME = eINSTANCE.getAlterGroupsType_Rename();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.buckminster.cspecxml.impl.AlterPrerequisitesImpl
		 * <em>Alter Prerequisites</em>}' class. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.cspecxml.impl.AlterPrerequisitesImpl
		 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getAlterPrerequisites()
		 * @generated
		 */
		EClass ALTER_PREREQUISITES = eINSTANCE.getAlterPrerequisites();

		/**
		 * The meta object literal for the '<em><b>Group1</b></em>' attribute
		 * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ALTER_PREREQUISITES__GROUP1 = eINSTANCE.getAlterPrerequisites_Group1();

		/**
		 * The meta object literal for the '<em><b>Alter Attribute</b></em>'
		 * containment reference list feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ALTER_PREREQUISITES__ALTER_ATTRIBUTE = eINSTANCE.getAlterPrerequisites_AlterAttribute();

		/**
		 * The meta object literal for the '<em><b>Remove</b></em>' containment
		 * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ALTER_PREREQUISITES__REMOVE = eINSTANCE.getAlterPrerequisites_Remove();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.buckminster.cspecxml.impl.AlterProductsTypeImpl
		 * <em>Alter Products Type</em>}' class. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.cspecxml.impl.AlterProductsTypeImpl
		 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getAlterProductsType()
		 * @generated
		 */
		EClass ALTER_PRODUCTS_TYPE = eINSTANCE.getAlterProductsType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute
		 * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ALTER_PRODUCTS_TYPE__GROUP = eINSTANCE.getAlterProductsType_Group();

		/**
		 * The meta object literal for the '<em><b>Public</b></em>' containment
		 * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ALTER_PRODUCTS_TYPE__PUBLIC = eINSTANCE.getAlterProductsType_Public();

		/**
		 * The meta object literal for the '<em><b>Private</b></em>' containment
		 * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ALTER_PRODUCTS_TYPE__PRIVATE = eINSTANCE.getAlterProductsType_Private();

		/**
		 * The meta object literal for the '<em><b>Remove Product</b></em>'
		 * containment reference list feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ALTER_PRODUCTS_TYPE__REMOVE_PRODUCT = eINSTANCE.getAlterProductsType_RemoveProduct();

		/**
		 * The meta object literal for the '<em><b>Remove Path</b></em>'
		 * containment reference list feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ALTER_PRODUCTS_TYPE__REMOVE_PATH = eINSTANCE.getAlterProductsType_RemovePath();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.buckminster.cspecxml.impl.AlterPropertiesImpl
		 * <em>Alter Properties</em>}' class. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.cspecxml.impl.AlterPropertiesImpl
		 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getAlterProperties()
		 * @generated
		 */
		EClass ALTER_PROPERTIES = eINSTANCE.getAlterProperties();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute
		 * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ALTER_PROPERTIES__GROUP = eINSTANCE.getAlterProperties_Group();

		/**
		 * The meta object literal for the '<em><b>Property</b></em>'
		 * containment reference list feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ALTER_PROPERTIES__PROPERTY = eINSTANCE.getAlterProperties_Property();

		/**
		 * The meta object literal for the '<em><b>Remove</b></em>' containment
		 * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ALTER_PROPERTIES__REMOVE = eINSTANCE.getAlterProperties_Remove();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.buckminster.cspecxml.impl.ArtifactImpl
		 * <em>Artifact</em>} ' class. <!-- begin-user-doc --> <!-- end-user-doc
		 * -->
		 * 
		 * @see org.eclipse.buckminster.cspecxml.impl.ArtifactImpl
		 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getArtifact()
		 * @generated
		 */
		EClass ARTIFACT = eINSTANCE.getArtifact();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute
		 * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ARTIFACT__GROUP = eINSTANCE.getArtifact_Group();

		/**
		 * The meta object literal for the '<em><b>Definitions</b></em>'
		 * containment reference list feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ARTIFACT__DEFINITIONS = eINSTANCE.getArtifact_Definitions();

		/**
		 * The meta object literal for the '<em><b>Path</b></em>' containment
		 * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ARTIFACT__PATH = eINSTANCE.getArtifact_Path();

		/**
		 * The meta object literal for the '<em><b>Base</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ARTIFACT__BASE = eINSTANCE.getArtifact_Base();

		/**
		 * The meta object literal for the '<em><b>Filter</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ARTIFACT__FILTER = eINSTANCE.getArtifact_Filter();

		/**
		 * The meta object literal for the '<em><b>Path1</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ARTIFACT__PATH1 = eINSTANCE.getArtifact_Path1();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ARTIFACT__TYPE = eINSTANCE.getArtifact_Type();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.buckminster.cspecxml.impl.ArtifactsTypeImpl
		 * <em>Artifacts Type</em>}' class. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.cspecxml.impl.ArtifactsTypeImpl
		 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getArtifactsType()
		 * @generated
		 */
		EClass ARTIFACTS_TYPE = eINSTANCE.getArtifactsType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute
		 * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ARTIFACTS_TYPE__GROUP = eINSTANCE.getArtifactsType_Group();

		/**
		 * The meta object literal for the '<em><b>Public</b></em>' containment
		 * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ARTIFACTS_TYPE__PUBLIC = eINSTANCE.getArtifactsType_Public();

		/**
		 * The meta object literal for the '<em><b>Private</b></em>' containment
		 * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ARTIFACTS_TYPE__PRIVATE = eINSTANCE.getArtifactsType_Private();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.buckminster.cspecxml.impl.AttributeImpl
		 * <em>Attribute</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
		 * -->
		 * 
		 * @see org.eclipse.buckminster.cspecxml.impl.AttributeImpl
		 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getAttribute()
		 * @generated
		 */
		EClass ATTRIBUTE = eINSTANCE.getAttribute();

		/**
		 * The meta object literal for the '<em><b>Documentation</b></em>'
		 * containment reference feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ATTRIBUTE__DOCUMENTATION = eINSTANCE.getAttribute_Documentation();

		/**
		 * The meta object literal for the '<em><b>Parameter</b></em>'
		 * containment reference list feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ATTRIBUTE__PARAMETER = eINSTANCE.getAttribute_Parameter();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ATTRIBUTE__NAME = eINSTANCE.getAttribute_Name();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ATTRIBUTE__VERSION = eINSTANCE.getAttribute_Version();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.buckminster.cspecxml.impl.ComponentRequestImpl
		 * <em>Component Request</em>}' class. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.cspecxml.impl.ComponentRequestImpl
		 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getComponentRequest()
		 * @generated
		 */
		EClass COMPONENT_REQUEST = eINSTANCE.getComponentRequest();

		/**
		 * The meta object literal for the '<em><b>Import</b></em>' containment
		 * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference COMPONENT_REQUEST__IMPORT = eINSTANCE.getComponentRequest_Import();

		/**
		 * The meta object literal for the '<em><b>Component Type</b></em>'
		 * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute COMPONENT_REQUEST__COMPONENT_TYPE = eINSTANCE.getComponentRequest_ComponentType();

		/**
		 * The meta object literal for the '<em><b>Filter</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute COMPONENT_REQUEST__FILTER = eINSTANCE.getComponentRequest_Filter();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute COMPONENT_REQUEST__NAME = eINSTANCE.getComponentRequest_Name();

		/**
		 * The meta object literal for the '<em><b>Version Designator</b></em>'
		 * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute COMPONENT_REQUEST__VERSION_DESIGNATOR = eINSTANCE.getComponentRequest_VersionDesignator();

		/**
		 * The meta object literal for the '<em><b>Version Type</b></em>'
		 * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute COMPONENT_REQUEST__VERSION_TYPE = eINSTANCE.getComponentRequest_VersionType();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.buckminster.cspecxml.impl.ComponentSpecImpl
		 * <em>Component Spec</em>}' class. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.cspecxml.impl.ComponentSpecImpl
		 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getComponentSpec()
		 * @generated
		 */
		EClass COMPONENT_SPEC = eINSTANCE.getComponentSpec();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute COMPONENT_SPEC__NAME = eINSTANCE.getComponentSpec_Name();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.buckminster.cspecxml.impl.ComponentSpecBaseImpl
		 * <em>Component Spec Base</em>}' class. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.cspecxml.impl.ComponentSpecBaseImpl
		 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getComponentSpecBase()
		 * @generated
		 */
		EClass COMPONENT_SPEC_BASE = eINSTANCE.getComponentSpecBase();

		/**
		 * The meta object literal for the '<em><b>Documentation</b></em>'
		 * containment reference feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference COMPONENT_SPEC_BASE__DOCUMENTATION = eINSTANCE.getComponentSpecBase_Documentation();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute
		 * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute COMPONENT_SPEC_BASE__GROUP = eINSTANCE.getComponentSpecBase_Group();

		/**
		 * The meta object literal for the '<em><b>Dependencies</b></em>'
		 * containment reference list feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference COMPONENT_SPEC_BASE__DEPENDENCIES = eINSTANCE.getComponentSpecBase_Dependencies();

		/**
		 * The meta object literal for the '<em><b>Generators</b></em>'
		 * containment reference list feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference COMPONENT_SPEC_BASE__GENERATORS = eINSTANCE.getComponentSpecBase_Generators();

		/**
		 * The meta object literal for the '<em><b>Artifacts</b></em>'
		 * containment reference list feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference COMPONENT_SPEC_BASE__ARTIFACTS = eINSTANCE.getComponentSpecBase_Artifacts();

		/**
		 * The meta object literal for the '<em><b>Groups</b></em>' containment
		 * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference COMPONENT_SPEC_BASE__GROUPS = eINSTANCE.getComponentSpecBase_Groups();

		/**
		 * The meta object literal for the '<em><b>Actions</b></em>' containment
		 * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference COMPONENT_SPEC_BASE__ACTIONS = eINSTANCE.getComponentSpecBase_Actions();

		/**
		 * The meta object literal for the '<em><b>Category</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute COMPONENT_SPEC_BASE__CATEGORY = eINSTANCE.getComponentSpecBase_Category();

		/**
		 * The meta object literal for the '<em><b>Component Type</b></em>'
		 * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute COMPONENT_SPEC_BASE__COMPONENT_TYPE = eINSTANCE.getComponentSpecBase_ComponentType();

		/**
		 * The meta object literal for the '<em><b>Filter</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute COMPONENT_SPEC_BASE__FILTER = eINSTANCE.getComponentSpecBase_Filter();

		/**
		 * The meta object literal for the '<em><b>Project Info</b></em>'
		 * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute COMPONENT_SPEC_BASE__PROJECT_INFO = eINSTANCE.getComponentSpecBase_ProjectInfo();

		/**
		 * The meta object literal for the '<em><b>Short Desc</b></em>'
		 * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute COMPONENT_SPEC_BASE__SHORT_DESC = eINSTANCE.getComponentSpecBase_ShortDesc();

		/**
		 * The meta object literal for the '<em><b>Version String</b></em>'
		 * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute COMPONENT_SPEC_BASE__VERSION_STRING = eINSTANCE.getComponentSpecBase_VersionString();

		/**
		 * The meta object literal for the '<em><b>Version Type</b></em>'
		 * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute COMPONENT_SPEC_BASE__VERSION_TYPE = eINSTANCE.getComponentSpecBase_VersionType();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.buckminster.cspecxml.impl.CSpecExtensionImpl
		 * <em>CSpec Extension</em>}' class. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.cspecxml.impl.CSpecExtensionImpl
		 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getCSpecExtension()
		 * @generated
		 */
		EClass CSPEC_EXTENSION = eINSTANCE.getCSpecExtension();

		/**
		 * The meta object literal for the '<em><b>Group1</b></em>' attribute
		 * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute CSPEC_EXTENSION__GROUP1 = eINSTANCE.getCSpecExtension_Group1();

		/**
		 * The meta object literal for the '<em><b>Alter Actions</b></em>'
		 * containment reference list feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference CSPEC_EXTENSION__ALTER_ACTIONS = eINSTANCE.getCSpecExtension_AlterActions();

		/**
		 * The meta object literal for the '<em><b>Alter Artifacts</b></em>'
		 * containment reference list feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference CSPEC_EXTENSION__ALTER_ARTIFACTS = eINSTANCE.getCSpecExtension_AlterArtifacts();

		/**
		 * The meta object literal for the '<em><b>Alter Dependencies</b></em>'
		 * containment reference list feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference CSPEC_EXTENSION__ALTER_DEPENDENCIES = eINSTANCE.getCSpecExtension_AlterDependencies();

		/**
		 * The meta object literal for the '<em><b>Alter Groups</b></em>'
		 * containment reference list feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference CSPEC_EXTENSION__ALTER_GROUPS = eINSTANCE.getCSpecExtension_AlterGroups();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.buckminster.cspecxml.impl.DefinitionsImpl
		 * <em>Definitions</em>}' class. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.cspecxml.impl.DefinitionsImpl
		 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getDefinitions()
		 * @generated
		 */
		EClass DEFINITIONS = eINSTANCE.getDefinitions();

		/**
		 * The meta object literal for the '<em><b>Define</b></em>' containment
		 * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DEFINITIONS__DEFINE = eINSTANCE.getDefinitions_Define();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.buckminster.cspecxml.impl.DependenciesTypeImpl
		 * <em>Dependencies Type</em>}' class. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.cspecxml.impl.DependenciesTypeImpl
		 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getDependenciesType()
		 * @generated
		 */
		EClass DEPENDENCIES_TYPE = eINSTANCE.getDependenciesType();

		/**
		 * The meta object literal for the '<em><b>Dependency</b></em>'
		 * containment reference list feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DEPENDENCIES_TYPE__DEPENDENCY = eINSTANCE.getDependenciesType_Dependency();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.buckminster.cspecxml.impl.DocumentRootImpl
		 * <em>Document Root</em>}' class. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.cspecxml.impl.DocumentRootImpl
		 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getDocumentRoot()
		 * @generated
		 */
		EClass DOCUMENT_ROOT = eINSTANCE.getDocumentRoot();

		/**
		 * The meta object literal for the '<em><b>Mixed</b></em>' attribute
		 * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__MIXED = eINSTANCE.getDocumentRoot_Mixed();

		/**
		 * The meta object literal for the '<em><b>XMLNS Prefix Map</b></em>'
		 * map feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__XMLNS_PREFIX_MAP = eINSTANCE.getDocumentRoot_XMLNSPrefixMap();

		/**
		 * The meta object literal for the '<em><b>XSI Schema Location</b></em>'
		 * map feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = eINSTANCE.getDocumentRoot_XSISchemaLocation();

		/**
		 * The meta object literal for the '<em><b>Cspec</b></em>' containment
		 * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__CSPEC = eINSTANCE.getDocumentRoot_Cspec();

		/**
		 * The meta object literal for the '<em><b>Cspec Extension</b></em>'
		 * containment reference feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__CSPEC_EXTENSION = eINSTANCE.getDocumentRoot_CspecExtension();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.buckminster.cspecxml.impl.GeneratorImpl
		 * <em>Generator</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
		 * -->
		 * 
		 * @see org.eclipse.buckminster.cspecxml.impl.GeneratorImpl
		 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getGenerator()
		 * @generated
		 */
		EClass GENERATOR = eINSTANCE.getGenerator();

		/**
		 * The meta object literal for the '<em><b>Attribute</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute GENERATOR__ATTRIBUTE = eINSTANCE.getGenerator_Attribute();

		/**
		 * The meta object literal for the '<em><b>Component</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute GENERATOR__COMPONENT = eINSTANCE.getGenerator_Component();

		/**
		 * The meta object literal for the '<em><b>Component Type</b></em>'
		 * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute GENERATOR__COMPONENT_TYPE = eINSTANCE.getGenerator_ComponentType();

		/**
		 * The meta object literal for the '<em><b>Generates</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute GENERATOR__GENERATES = eINSTANCE.getGenerator_Generates();

		/**
		 * The meta object literal for the '<em><b>Generates Type</b></em>'
		 * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute GENERATOR__GENERATES_TYPE = eINSTANCE.getGenerator_GeneratesType();

		/**
		 * The meta object literal for the '
		 * <em><b>Generates Version String</b></em>' attribute feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute GENERATOR__GENERATES_VERSION_STRING = eINSTANCE.getGenerator_GeneratesVersionString();

		/**
		 * The meta object literal for the '
		 * <em><b>Generates Version Type</b></em>' attribute feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute GENERATOR__GENERATES_VERSION_TYPE = eINSTANCE.getGenerator_GeneratesVersionType();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.buckminster.cspecxml.impl.GeneratorsTypeImpl
		 * <em>Generators Type</em>}' class. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.cspecxml.impl.GeneratorsTypeImpl
		 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getGeneratorsType()
		 * @generated
		 */
		EClass GENERATORS_TYPE = eINSTANCE.getGeneratorsType();

		/**
		 * The meta object literal for the '<em><b>Generator</b></em>'
		 * containment reference list feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference GENERATORS_TYPE__GENERATOR = eINSTANCE.getGeneratorsType_Generator();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.buckminster.cspecxml.impl.GroupImpl
		 * <em>Group</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.cspecxml.impl.GroupImpl
		 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getGroup()
		 * @generated
		 */
		EClass GROUP = eINSTANCE.getGroup();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute
		 * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute GROUP__GROUP = eINSTANCE.getGroup_Group();

		/**
		 * The meta object literal for the '<em><b>Definitions</b></em>'
		 * containment reference list feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference GROUP__DEFINITIONS = eINSTANCE.getGroup_Definitions();

		/**
		 * The meta object literal for the '<em><b>Attribute</b></em>'
		 * containment reference list feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference GROUP__ATTRIBUTE = eINSTANCE.getGroup_Attribute();

		/**
		 * The meta object literal for the '<em><b>Filter</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute GROUP__FILTER = eINSTANCE.getGroup_Filter();

		/**
		 * The meta object literal for the '<em><b>Rebase</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute GROUP__REBASE = eINSTANCE.getGroup_Rebase();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.buckminster.cspecxml.impl.GroupsTypeImpl
		 * <em>Groups Type</em>}' class. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.cspecxml.impl.GroupsTypeImpl
		 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getGroupsType()
		 * @generated
		 */
		EClass GROUPS_TYPE = eINSTANCE.getGroupsType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute
		 * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute GROUPS_TYPE__GROUP = eINSTANCE.getGroupsType_Group();

		/**
		 * The meta object literal for the '<em><b>Public</b></em>' containment
		 * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference GROUPS_TYPE__PUBLIC = eINSTANCE.getGroupsType_Public();

		/**
		 * The meta object literal for the '<em><b>Private</b></em>' containment
		 * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference GROUPS_TYPE__PRIVATE = eINSTANCE.getGroupsType_Private();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.buckminster.cspecxml.impl.ImportImpl
		 * <em>Import</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
		 * -->
		 * 
		 * @see org.eclipse.buckminster.cspecxml.impl.ImportImpl
		 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getImport()
		 * @generated
		 */
		EClass IMPORT = eINSTANCE.getImport();

		/**
		 * The meta object literal for the '<em><b>Attribute</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute IMPORT__ATTRIBUTE = eINSTANCE.getImport_Attribute();

		/**
		 * The meta object literal for the '<em><b>Filter</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute IMPORT__FILTER = eINSTANCE.getImport_Filter();

		/**
		 * The meta object literal for the '<em><b>Version Designator</b></em>'
		 * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute IMPORT__VERSION_DESIGNATOR = eINSTANCE.getImport_VersionDesignator();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.buckminster.cspecxml.impl.ParameterTypeImpl
		 * <em>Parameter Type</em>}' class. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.cspecxml.impl.ParameterTypeImpl
		 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getParameterType()
		 * @generated
		 */
		EClass PARAMETER_TYPE = eINSTANCE.getParameterType();

		/**
		 * The meta object literal for the '<em><b>Mandatory</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PARAMETER_TYPE__MANDATORY = eINSTANCE.getParameterType_Mandatory();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PARAMETER_TYPE__NAME = eINSTANCE.getParameterType_Name();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PARAMETER_TYPE__VALUE = eINSTANCE.getParameterType_Value();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.buckminster.cspecxml.impl.PathImpl <em>Path</em>}'
		 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.cspecxml.impl.PathImpl
		 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getPath()
		 * @generated
		 */
		EClass PATH = eINSTANCE.getPath();

		/**
		 * The meta object literal for the '<em><b>Path</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PATH__PATH = eINSTANCE.getPath_Path();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.buckminster.cspecxml.impl.PrerequisiteImpl
		 * <em>Prerequisite</em>}' class. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.cspecxml.impl.PrerequisiteImpl
		 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getPrerequisite()
		 * @generated
		 */
		EClass PREREQUISITE = eINSTANCE.getPrerequisite();

		/**
		 * The meta object literal for the '<em><b>Alias</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PREREQUISITE__ALIAS = eINSTANCE.getPrerequisite_Alias();

		/**
		 * The meta object literal for the '<em><b>Component</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PREREQUISITE__COMPONENT = eINSTANCE.getPrerequisite_Component();

		/**
		 * The meta object literal for the '<em><b>Component Type</b></em>'
		 * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PREREQUISITE__COMPONENT_TYPE = eINSTANCE.getPrerequisite_ComponentType();

		/**
		 * The meta object literal for the '<em><b>Contributor</b></em>'
		 * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PREREQUISITE__CONTRIBUTOR = eINSTANCE.getPrerequisite_Contributor();

		/**
		 * The meta object literal for the '<em><b>Exclude Pattern</b></em>'
		 * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PREREQUISITE__EXCLUDE_PATTERN = eINSTANCE.getPrerequisite_ExcludePattern();

		/**
		 * The meta object literal for the '<em><b>Filter</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PREREQUISITE__FILTER = eINSTANCE.getPrerequisite_Filter();

		/**
		 * The meta object literal for the '<em><b>Include Pattern</b></em>'
		 * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PREREQUISITE__INCLUDE_PATTERN = eINSTANCE.getPrerequisite_IncludePattern();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PREREQUISITE__NAME = eINSTANCE.getPrerequisite_Name();

		/**
		 * The meta object literal for the '<em><b>Optional</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PREREQUISITE__OPTIONAL = eINSTANCE.getPrerequisite_Optional();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.buckminster.cspecxml.impl.PrerequisitesImpl
		 * <em>Prerequisites</em>}' class. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.cspecxml.impl.PrerequisitesImpl
		 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getPrerequisites()
		 * @generated
		 */
		EClass PREREQUISITES = eINSTANCE.getPrerequisites();

		/**
		 * The meta object literal for the '<em><b>Documentation</b></em>'
		 * containment reference feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference PREREQUISITES__DOCUMENTATION = eINSTANCE.getPrerequisites_Documentation();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute
		 * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PREREQUISITES__GROUP = eINSTANCE.getPrerequisites_Group();

		/**
		 * The meta object literal for the '<em><b>Attribute</b></em>'
		 * containment reference list feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference PREREQUISITES__ATTRIBUTE = eINSTANCE.getPrerequisites_Attribute();

		/**
		 * The meta object literal for the '<em><b>Alias</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PREREQUISITES__ALIAS = eINSTANCE.getPrerequisites_Alias();

		/**
		 * The meta object literal for the '<em><b>Rebase</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PREREQUISITES__REBASE = eINSTANCE.getPrerequisites_Rebase();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.buckminster.cspecxml.impl.ProductsTypeImpl
		 * <em>Products Type</em>}' class. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.cspecxml.impl.ProductsTypeImpl
		 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getProductsType()
		 * @generated
		 */
		EClass PRODUCTS_TYPE = eINSTANCE.getProductsType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute
		 * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PRODUCTS_TYPE__GROUP = eINSTANCE.getProductsType_Group();

		/**
		 * The meta object literal for the '<em><b>Path</b></em>' containment
		 * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference PRODUCTS_TYPE__PATH = eINSTANCE.getProductsType_Path();

		/**
		 * The meta object literal for the '<em><b>Public</b></em>' containment
		 * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference PRODUCTS_TYPE__PUBLIC = eINSTANCE.getProductsType_Public();

		/**
		 * The meta object literal for the '<em><b>Private</b></em>' containment
		 * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference PRODUCTS_TYPE__PRIVATE = eINSTANCE.getProductsType_Private();

		/**
		 * The meta object literal for the '<em><b>Alias</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PRODUCTS_TYPE__ALIAS = eINSTANCE.getProductsType_Alias();

		/**
		 * The meta object literal for the '<em><b>Base</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PRODUCTS_TYPE__BASE = eINSTANCE.getProductsType_Base();

		/**
		 * The meta object literal for the '<em><b>File Count</b></em>'
		 * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PRODUCTS_TYPE__FILE_COUNT = eINSTANCE.getProductsType_FileCount();

		/**
		 * The meta object literal for the '<em><b>Pattern</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PRODUCTS_TYPE__PATTERN = eINSTANCE.getProductsType_Pattern();

		/**
		 * The meta object literal for the '<em><b>Replacement</b></em>'
		 * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PRODUCTS_TYPE__REPLACEMENT = eINSTANCE.getProductsType_Replacement();

		/**
		 * The meta object literal for the '<em><b>Up To Date Policy</b></em>'
		 * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PRODUCTS_TYPE__UP_TO_DATE_POLICY = eINSTANCE.getProductsType_UpToDatePolicy();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.buckminster.cspecxml.impl.PropertiesImpl
		 * <em>Properties</em>}' class. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.cspecxml.impl.PropertiesImpl
		 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getProperties()
		 * @generated
		 */
		EClass PROPERTIES = eINSTANCE.getProperties();

		/**
		 * The meta object literal for the '<em><b>Property</b></em>'
		 * containment reference list feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference PROPERTIES__PROPERTY = eINSTANCE.getProperties_Property();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.buckminster.cspecxml.impl.RemoveImpl
		 * <em>Remove</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
		 * -->
		 * 
		 * @see org.eclipse.buckminster.cspecxml.impl.RemoveImpl
		 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getRemove()
		 * @generated
		 */
		EClass REMOVE = eINSTANCE.getRemove();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute REMOVE__NAME = eINSTANCE.getRemove_Name();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.buckminster.cspecxml.impl.RemovePathImpl
		 * <em>Remove Path</em>}' class. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.cspecxml.impl.RemovePathImpl
		 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getRemovePath()
		 * @generated
		 */
		EClass REMOVE_PATH = eINSTANCE.getRemovePath();

		/**
		 * The meta object literal for the '<em><b>Path</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute REMOVE_PATH__PATH = eINSTANCE.getRemovePath_Path();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.buckminster.cspecxml.impl.RemovePropertyImpl
		 * <em>Remove Property</em>}' class. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.cspecxml.impl.RemovePropertyImpl
		 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getRemoveProperty()
		 * @generated
		 */
		EClass REMOVE_PROPERTY = eINSTANCE.getRemoveProperty();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute REMOVE_PROPERTY__KEY = eINSTANCE.getRemoveProperty_Key();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.buckminster.cspecxml.impl.RenameImpl
		 * <em>Rename</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
		 * -->
		 * 
		 * @see org.eclipse.buckminster.cspecxml.impl.RenameImpl
		 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getRename()
		 * @generated
		 */
		EClass RENAME = eINSTANCE.getRename();

		/**
		 * The meta object literal for the '<em><b>New Name</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute RENAME__NEW_NAME = eINSTANCE.getRename_NewName();

		/**
		 * The meta object literal for the '<em><b>Old Name</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute RENAME__OLD_NAME = eINSTANCE.getRename_OldName();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.buckminster.cspecxml.UpToDatePolicy
		 * <em>Up To Date Policy</em>}' enum. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.cspecxml.UpToDatePolicy
		 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getUpToDatePolicy()
		 * @generated
		 */
		EEnum UP_TO_DATE_POLICY = eINSTANCE.getUpToDatePolicy();

		/**
		 * The meta object literal for the '<em>Up To Date Policy Object</em>'
		 * data type. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.cspecxml.UpToDatePolicy
		 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getUpToDatePolicyObject()
		 * @generated
		 */
		EDataType UP_TO_DATE_POLICY_OBJECT = eINSTANCE.getUpToDatePolicyObject();

	}

	/**
	 * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNAME = "cspecxml";

	/**
	 * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/buckminster/CSpec-1.0";

	/**
	 * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_PREFIX = "cs";

	/**
	 * The singleton instance of the package. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	ICSpecXMLPackage eINSTANCE = org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl.init();

	/**
	 * The meta object id for the '
	 * {@link org.eclipse.buckminster.cspecxml.impl.AttributeImpl
	 * <em>Attribute</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.cspecxml.impl.AttributeImpl
	 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getAttribute()
	 * @generated
	 */
	int ATTRIBUTE = 15;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__DOCUMENTATION = 0;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__PARAMETER = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__NAME = 2;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__VERSION = 3;

	/**
	 * The number of structural features of the '<em>Attribute</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '
	 * {@link org.eclipse.buckminster.cspecxml.impl.ActionImpl <em>Action</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.cspecxml.impl.ActionImpl
	 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getAction()
	 * @generated
	 */
	int ACTION = 0;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ACTION__DOCUMENTATION = ATTRIBUTE__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ACTION__PARAMETER = ATTRIBUTE__PARAMETER;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ACTION__NAME = ATTRIBUTE__NAME;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ACTION__VERSION = ATTRIBUTE__VERSION;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ACTION__GROUP = ATTRIBUTE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Definitions</b></em>' containment
	 * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ACTION__DEFINITIONS = ATTRIBUTE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Actor Properties</b></em>' containment
	 * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ACTION__ACTOR_PROPERTIES = ATTRIBUTE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ACTION__PROPERTIES = ATTRIBUTE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Prerequisites</b></em>' containment
	 * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ACTION__PREREQUISITES = ATTRIBUTE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Products</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ACTION__PRODUCTS = ATTRIBUTE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Actor</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ACTION__ACTOR = ATTRIBUTE_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Always</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ACTION__ALWAYS = ATTRIBUTE_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Assign Console Support</b></em>'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ACTION__ASSIGN_CONSOLE_SUPPORT = ATTRIBUTE_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ACTION__ENABLED = ATTRIBUTE_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Filter</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ACTION__FILTER = ATTRIBUTE_FEATURE_COUNT + 10;

	/**
	 * The number of structural features of the '<em>Action</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ACTION_FEATURE_COUNT = ATTRIBUTE_FEATURE_COUNT + 11;

	/**
	 * The meta object id for the '
	 * {@link org.eclipse.buckminster.cspecxml.impl.ArtifactImpl
	 * <em>Artifact</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.cspecxml.impl.ArtifactImpl
	 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getArtifact()
	 * @generated
	 */
	int ARTIFACT = 13;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__DOCUMENTATION = ATTRIBUTE__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__PARAMETER = ATTRIBUTE__PARAMETER;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__NAME = ATTRIBUTE__NAME;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__VERSION = ATTRIBUTE__VERSION;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__GROUP = ATTRIBUTE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Definitions</b></em>' containment
	 * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__DEFINITIONS = ATTRIBUTE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Path</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__PATH = ATTRIBUTE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Base</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__BASE = ATTRIBUTE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Filter</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__FILTER = ATTRIBUTE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Path1</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__PATH1 = ATTRIBUTE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__TYPE = ATTRIBUTE_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>Artifact</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_FEATURE_COUNT = ATTRIBUTE_FEATURE_COUNT + 7;

	/**
	 * The meta object id for the '
	 * {@link org.eclipse.buckminster.cspecxml.impl.ActionArtifactImpl
	 * <em>Action Artifact</em>}' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.cspecxml.impl.ActionArtifactImpl
	 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getActionArtifact()
	 * @generated
	 */
	int ACTION_ARTIFACT = 1;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ACTION_ARTIFACT__DOCUMENTATION = ARTIFACT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ACTION_ARTIFACT__PARAMETER = ARTIFACT__PARAMETER;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ACTION_ARTIFACT__NAME = ARTIFACT__NAME;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ACTION_ARTIFACT__VERSION = ARTIFACT__VERSION;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ACTION_ARTIFACT__GROUP = ARTIFACT__GROUP;

	/**
	 * The feature id for the '<em><b>Definitions</b></em>' containment
	 * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ACTION_ARTIFACT__DEFINITIONS = ARTIFACT__DEFINITIONS;

	/**
	 * The feature id for the '<em><b>Path</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ACTION_ARTIFACT__PATH = ARTIFACT__PATH;

	/**
	 * The feature id for the '<em><b>Base</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ACTION_ARTIFACT__BASE = ARTIFACT__BASE;

	/**
	 * The feature id for the '<em><b>Filter</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ACTION_ARTIFACT__FILTER = ARTIFACT__FILTER;

	/**
	 * The feature id for the '<em><b>Path1</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ACTION_ARTIFACT__PATH1 = ARTIFACT__PATH1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ACTION_ARTIFACT__TYPE = ARTIFACT__TYPE;

	/**
	 * The feature id for the '<em><b>Alias</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ACTION_ARTIFACT__ALIAS = ARTIFACT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Action Artifact</em>'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ACTION_ARTIFACT_FEATURE_COUNT = ARTIFACT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '
	 * {@link org.eclipse.buckminster.cspecxml.impl.ActionsTypeImpl
	 * <em>Actions Type</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see org.eclipse.buckminster.cspecxml.impl.ActionsTypeImpl
	 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getActionsType()
	 * @generated
	 */
	int ACTIONS_TYPE = 2;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ACTIONS_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Public</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ACTIONS_TYPE__PUBLIC = 1;

	/**
	 * The feature id for the '<em><b>Private</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ACTIONS_TYPE__PRIVATE = 2;

	/**
	 * The number of structural features of the '<em>Actions Type</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ACTIONS_TYPE_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '
	 * {@link org.eclipse.buckminster.cspecxml.impl.AlterActionImpl
	 * <em>Alter Action</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see org.eclipse.buckminster.cspecxml.impl.AlterActionImpl
	 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getAlterAction()
	 * @generated
	 */
	int ALTER_ACTION = 3;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ACTION__DOCUMENTATION = ACTION__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ACTION__PARAMETER = ACTION__PARAMETER;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ACTION__NAME = ACTION__NAME;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ACTION__VERSION = ACTION__VERSION;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ACTION__GROUP = ACTION__GROUP;

	/**
	 * The feature id for the '<em><b>Definitions</b></em>' containment
	 * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ACTION__DEFINITIONS = ACTION__DEFINITIONS;

	/**
	 * The feature id for the '<em><b>Actor Properties</b></em>' containment
	 * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ACTION__ACTOR_PROPERTIES = ACTION__ACTOR_PROPERTIES;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ACTION__PROPERTIES = ACTION__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Prerequisites</b></em>' containment
	 * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ACTION__PREREQUISITES = ACTION__PREREQUISITES;

	/**
	 * The feature id for the '<em><b>Products</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ACTION__PRODUCTS = ACTION__PRODUCTS;

	/**
	 * The feature id for the '<em><b>Actor</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ACTION__ACTOR = ACTION__ACTOR;

	/**
	 * The feature id for the '<em><b>Always</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ACTION__ALWAYS = ACTION__ALWAYS;

	/**
	 * The feature id for the '<em><b>Assign Console Support</b></em>'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ACTION__ASSIGN_CONSOLE_SUPPORT = ACTION__ASSIGN_CONSOLE_SUPPORT;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ACTION__ENABLED = ACTION__ENABLED;

	/**
	 * The feature id for the '<em><b>Filter</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ACTION__FILTER = ACTION__FILTER;

	/**
	 * The feature id for the '<em><b>Group1</b></em>' attribute list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ACTION__GROUP1 = ACTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Alter Prerequisites</b></em>' containment
	 * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ACTION__ALTER_PREREQUISITES = ACTION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Alter Actor Properties</b></em>'
	 * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ACTION__ALTER_ACTOR_PROPERTIES = ACTION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Alter Properties</b></em>' containment
	 * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ACTION__ALTER_PROPERTIES = ACTION_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Alter Products</b></em>' containment
	 * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ACTION__ALTER_PRODUCTS = ACTION_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Alter Action</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ACTION_FEATURE_COUNT = ACTION_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '
	 * {@link org.eclipse.buckminster.cspecxml.impl.AlterActionsTypeImpl
	 * <em>Alter Actions Type</em>}' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.cspecxml.impl.AlterActionsTypeImpl
	 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getAlterActionsType()
	 * @generated
	 */
	int ALTER_ACTIONS_TYPE = 4;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ACTIONS_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Public</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ACTIONS_TYPE__PUBLIC = 1;

	/**
	 * The feature id for the '<em><b>Private</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ACTIONS_TYPE__PRIVATE = 2;

	/**
	 * The feature id for the '<em><b>Remove</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ACTIONS_TYPE__REMOVE = 3;

	/**
	 * The feature id for the '<em><b>Rename</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ACTIONS_TYPE__RENAME = 4;

	/**
	 * The number of structural features of the '<em>Alter Actions Type</em>'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ACTIONS_TYPE_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '
	 * {@link org.eclipse.buckminster.cspecxml.impl.AlterArtifactImpl
	 * <em>Alter Artifact</em>}' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.cspecxml.impl.AlterArtifactImpl
	 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getAlterArtifact()
	 * @generated
	 */
	int ALTER_ARTIFACT = 5;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ARTIFACT__DOCUMENTATION = ARTIFACT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ARTIFACT__PARAMETER = ARTIFACT__PARAMETER;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ARTIFACT__NAME = ARTIFACT__NAME;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ARTIFACT__VERSION = ARTIFACT__VERSION;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ARTIFACT__GROUP = ARTIFACT__GROUP;

	/**
	 * The feature id for the '<em><b>Definitions</b></em>' containment
	 * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ARTIFACT__DEFINITIONS = ARTIFACT__DEFINITIONS;

	/**
	 * The feature id for the '<em><b>Path</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ARTIFACT__PATH = ARTIFACT__PATH;

	/**
	 * The feature id for the '<em><b>Base</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ARTIFACT__BASE = ARTIFACT__BASE;

	/**
	 * The feature id for the '<em><b>Filter</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ARTIFACT__FILTER = ARTIFACT__FILTER;

	/**
	 * The feature id for the '<em><b>Path1</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ARTIFACT__PATH1 = ARTIFACT__PATH1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ARTIFACT__TYPE = ARTIFACT__TYPE;

	/**
	 * The feature id for the '<em><b>Group1</b></em>' attribute list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ARTIFACT__GROUP1 = ARTIFACT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Remove Path</b></em>' containment
	 * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ARTIFACT__REMOVE_PATH = ARTIFACT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Alter Artifact</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ARTIFACT_FEATURE_COUNT = ARTIFACT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '
	 * {@link org.eclipse.buckminster.cspecxml.impl.AlterArtifactsTypeImpl
	 * <em>Alter Artifacts Type</em>}' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.cspecxml.impl.AlterArtifactsTypeImpl
	 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getAlterArtifactsType()
	 * @generated
	 */
	int ALTER_ARTIFACTS_TYPE = 6;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ARTIFACTS_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Public</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ARTIFACTS_TYPE__PUBLIC = 1;

	/**
	 * The feature id for the '<em><b>Private</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ARTIFACTS_TYPE__PRIVATE = 2;

	/**
	 * The feature id for the '<em><b>Remove</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ARTIFACTS_TYPE__REMOVE = 3;

	/**
	 * The feature id for the '<em><b>Rename</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ARTIFACTS_TYPE__RENAME = 4;

	/**
	 * The number of structural features of the '<em>Alter Artifacts Type</em>'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ARTIFACTS_TYPE_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '
	 * {@link org.eclipse.buckminster.cspecxml.impl.AlterDependenciesTypeImpl
	 * <em>Alter Dependencies Type</em>}' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.cspecxml.impl.AlterDependenciesTypeImpl
	 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getAlterDependenciesType()
	 * @generated
	 */
	int ALTER_DEPENDENCIES_TYPE = 7;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_DEPENDENCIES_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Dependency</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_DEPENDENCIES_TYPE__DEPENDENCY = 1;

	/**
	 * The feature id for the '<em><b>Remove</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_DEPENDENCIES_TYPE__REMOVE = 2;

	/**
	 * The number of structural features of the '
	 * <em>Alter Dependencies Type</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_DEPENDENCIES_TYPE_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '
	 * {@link org.eclipse.buckminster.cspecxml.impl.GroupImpl <em>Group</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.cspecxml.impl.GroupImpl
	 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getGroup()
	 * @generated
	 */
	int GROUP = 25;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int GROUP__DOCUMENTATION = ATTRIBUTE__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int GROUP__PARAMETER = ATTRIBUTE__PARAMETER;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int GROUP__NAME = ATTRIBUTE__NAME;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int GROUP__VERSION = ATTRIBUTE__VERSION;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int GROUP__GROUP = ATTRIBUTE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Definitions</b></em>' containment
	 * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int GROUP__DEFINITIONS = ATTRIBUTE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int GROUP__ATTRIBUTE = ATTRIBUTE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Filter</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int GROUP__FILTER = ATTRIBUTE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Rebase</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int GROUP__REBASE = ATTRIBUTE_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Group</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int GROUP_FEATURE_COUNT = ATTRIBUTE_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '
	 * {@link org.eclipse.buckminster.cspecxml.impl.AlterGroupImpl
	 * <em>Alter Group</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see org.eclipse.buckminster.cspecxml.impl.AlterGroupImpl
	 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getAlterGroup()
	 * @generated
	 */
	int ALTER_GROUP = 8;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_GROUP__DOCUMENTATION = GROUP__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_GROUP__PARAMETER = GROUP__PARAMETER;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_GROUP__NAME = GROUP__NAME;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_GROUP__VERSION = GROUP__VERSION;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_GROUP__GROUP = GROUP__GROUP;

	/**
	 * The feature id for the '<em><b>Definitions</b></em>' containment
	 * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_GROUP__DEFINITIONS = GROUP__DEFINITIONS;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_GROUP__ATTRIBUTE = GROUP__ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Filter</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_GROUP__FILTER = GROUP__FILTER;

	/**
	 * The feature id for the '<em><b>Rebase</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_GROUP__REBASE = GROUP__REBASE;

	/**
	 * The feature id for the '<em><b>Group1</b></em>' attribute list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_GROUP__GROUP1 = GROUP_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Alter Attribute</b></em>' containment
	 * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_GROUP__ALTER_ATTRIBUTE = GROUP_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Remove</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_GROUP__REMOVE = GROUP_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Alter Group</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_GROUP_FEATURE_COUNT = GROUP_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '
	 * {@link org.eclipse.buckminster.cspecxml.impl.AlterGroupsTypeImpl
	 * <em>Alter Groups Type</em>}' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.cspecxml.impl.AlterGroupsTypeImpl
	 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getAlterGroupsType()
	 * @generated
	 */
	int ALTER_GROUPS_TYPE = 9;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_GROUPS_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Public</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_GROUPS_TYPE__PUBLIC = 1;

	/**
	 * The feature id for the '<em><b>Private</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_GROUPS_TYPE__PRIVATE = 2;

	/**
	 * The feature id for the '<em><b>Remove</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_GROUPS_TYPE__REMOVE = 3;

	/**
	 * The feature id for the '<em><b>Rename</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_GROUPS_TYPE__RENAME = 4;

	/**
	 * The number of structural features of the '<em>Alter Groups Type</em>'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_GROUPS_TYPE_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '
	 * {@link org.eclipse.buckminster.cspecxml.impl.PrerequisitesImpl
	 * <em>Prerequisites</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see org.eclipse.buckminster.cspecxml.impl.PrerequisitesImpl
	 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getPrerequisites()
	 * @generated
	 */
	int PREREQUISITES = 31;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PREREQUISITES__DOCUMENTATION = 0;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PREREQUISITES__GROUP = 1;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PREREQUISITES__ATTRIBUTE = 2;

	/**
	 * The feature id for the '<em><b>Alias</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PREREQUISITES__ALIAS = 3;

	/**
	 * The feature id for the '<em><b>Rebase</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PREREQUISITES__REBASE = 4;

	/**
	 * The number of structural features of the '<em>Prerequisites</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PREREQUISITES_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '
	 * {@link org.eclipse.buckminster.cspecxml.impl.AlterPrerequisitesImpl
	 * <em>Alter Prerequisites</em>}' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.cspecxml.impl.AlterPrerequisitesImpl
	 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getAlterPrerequisites()
	 * @generated
	 */
	int ALTER_PREREQUISITES = 10;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_PREREQUISITES__DOCUMENTATION = PREREQUISITES__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_PREREQUISITES__GROUP = PREREQUISITES__GROUP;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_PREREQUISITES__ATTRIBUTE = PREREQUISITES__ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Alias</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_PREREQUISITES__ALIAS = PREREQUISITES__ALIAS;

	/**
	 * The feature id for the '<em><b>Rebase</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_PREREQUISITES__REBASE = PREREQUISITES__REBASE;

	/**
	 * The feature id for the '<em><b>Group1</b></em>' attribute list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_PREREQUISITES__GROUP1 = PREREQUISITES_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Alter Attribute</b></em>' containment
	 * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_PREREQUISITES__ALTER_ATTRIBUTE = PREREQUISITES_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Remove</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_PREREQUISITES__REMOVE = PREREQUISITES_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Alter Prerequisites</em>'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_PREREQUISITES_FEATURE_COUNT = PREREQUISITES_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '
	 * {@link org.eclipse.buckminster.cspecxml.impl.AlterProductsTypeImpl
	 * <em>Alter Products Type</em>}' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.cspecxml.impl.AlterProductsTypeImpl
	 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getAlterProductsType()
	 * @generated
	 */
	int ALTER_PRODUCTS_TYPE = 11;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_PRODUCTS_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Public</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_PRODUCTS_TYPE__PUBLIC = 1;

	/**
	 * The feature id for the '<em><b>Private</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_PRODUCTS_TYPE__PRIVATE = 2;

	/**
	 * The feature id for the '<em><b>Remove Product</b></em>' containment
	 * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_PRODUCTS_TYPE__REMOVE_PRODUCT = 3;

	/**
	 * The feature id for the '<em><b>Remove Path</b></em>' containment
	 * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_PRODUCTS_TYPE__REMOVE_PATH = 4;

	/**
	 * The number of structural features of the '<em>Alter Products Type</em>'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_PRODUCTS_TYPE_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '
	 * {@link org.eclipse.buckminster.cspecxml.impl.AlterPropertiesImpl
	 * <em>Alter Properties</em>}' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.cspecxml.impl.AlterPropertiesImpl
	 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getAlterProperties()
	 * @generated
	 */
	int ALTER_PROPERTIES = 12;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_PROPERTIES__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_PROPERTIES__PROPERTY = 1;

	/**
	 * The feature id for the '<em><b>Remove</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_PROPERTIES__REMOVE = 2;

	/**
	 * The number of structural features of the '<em>Alter Properties</em>'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_PROPERTIES_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '
	 * {@link org.eclipse.buckminster.cspecxml.impl.ArtifactsTypeImpl
	 * <em>Artifacts Type</em>}' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.cspecxml.impl.ArtifactsTypeImpl
	 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getArtifactsType()
	 * @generated
	 */
	int ARTIFACTS_TYPE = 14;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ARTIFACTS_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Public</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ARTIFACTS_TYPE__PUBLIC = 1;

	/**
	 * The feature id for the '<em><b>Private</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ARTIFACTS_TYPE__PRIVATE = 2;

	/**
	 * The number of structural features of the '<em>Artifacts Type</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ARTIFACTS_TYPE_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '
	 * {@link org.eclipse.buckminster.cspecxml.impl.ComponentRequestImpl
	 * <em>Component Request</em>}' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.cspecxml.impl.ComponentRequestImpl
	 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getComponentRequest()
	 * @generated
	 */
	int COMPONENT_REQUEST = 16;

	/**
	 * The feature id for the '<em><b>Import</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPONENT_REQUEST__IMPORT = 0;

	/**
	 * The feature id for the '<em><b>Component Type</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPONENT_REQUEST__COMPONENT_TYPE = 1;

	/**
	 * The feature id for the '<em><b>Filter</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPONENT_REQUEST__FILTER = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPONENT_REQUEST__NAME = 3;

	/**
	 * The feature id for the '<em><b>Version Designator</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPONENT_REQUEST__VERSION_DESIGNATOR = 4;

	/**
	 * The feature id for the '<em><b>Version Type</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPONENT_REQUEST__VERSION_TYPE = 5;

	/**
	 * The number of structural features of the '<em>Component Request</em>'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPONENT_REQUEST_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '
	 * {@link org.eclipse.buckminster.cspecxml.impl.ComponentSpecBaseImpl
	 * <em>Component Spec Base</em>}' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.cspecxml.impl.ComponentSpecBaseImpl
	 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getComponentSpecBase()
	 * @generated
	 */
	int COMPONENT_SPEC_BASE = 18;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPEC_BASE__DOCUMENTATION = 0;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPEC_BASE__GROUP = 1;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment
	 * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPEC_BASE__DEPENDENCIES = 2;

	/**
	 * The feature id for the '<em><b>Generators</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPEC_BASE__GENERATORS = 3;

	/**
	 * The feature id for the '<em><b>Artifacts</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPEC_BASE__ARTIFACTS = 4;

	/**
	 * The feature id for the '<em><b>Groups</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPEC_BASE__GROUPS = 5;

	/**
	 * The feature id for the '<em><b>Actions</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPEC_BASE__ACTIONS = 6;

	/**
	 * The feature id for the '<em><b>Category</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPEC_BASE__CATEGORY = 7;

	/**
	 * The feature id for the '<em><b>Component Type</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPEC_BASE__COMPONENT_TYPE = 8;

	/**
	 * The feature id for the '<em><b>Filter</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPEC_BASE__FILTER = 9;

	/**
	 * The feature id for the '<em><b>Project Info</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPEC_BASE__PROJECT_INFO = 10;

	/**
	 * The feature id for the '<em><b>Short Desc</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPEC_BASE__SHORT_DESC = 11;

	/**
	 * The feature id for the '<em><b>Version String</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPEC_BASE__VERSION_STRING = 12;

	/**
	 * The feature id for the '<em><b>Version Type</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPEC_BASE__VERSION_TYPE = 13;

	/**
	 * The number of structural features of the '<em>Component Spec Base</em>'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPEC_BASE_FEATURE_COUNT = 14;

	/**
	 * The meta object id for the '
	 * {@link org.eclipse.buckminster.cspecxml.impl.ComponentSpecImpl
	 * <em>Component Spec</em>}' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.cspecxml.impl.ComponentSpecImpl
	 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getComponentSpec()
	 * @generated
	 */
	int COMPONENT_SPEC = 17;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPEC__DOCUMENTATION = COMPONENT_SPEC_BASE__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPEC__GROUP = COMPONENT_SPEC_BASE__GROUP;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment
	 * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPEC__DEPENDENCIES = COMPONENT_SPEC_BASE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Generators</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPEC__GENERATORS = COMPONENT_SPEC_BASE__GENERATORS;

	/**
	 * The feature id for the '<em><b>Artifacts</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPEC__ARTIFACTS = COMPONENT_SPEC_BASE__ARTIFACTS;

	/**
	 * The feature id for the '<em><b>Groups</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPEC__GROUPS = COMPONENT_SPEC_BASE__GROUPS;

	/**
	 * The feature id for the '<em><b>Actions</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPEC__ACTIONS = COMPONENT_SPEC_BASE__ACTIONS;

	/**
	 * The feature id for the '<em><b>Category</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPEC__CATEGORY = COMPONENT_SPEC_BASE__CATEGORY;

	/**
	 * The feature id for the '<em><b>Component Type</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPEC__COMPONENT_TYPE = COMPONENT_SPEC_BASE__COMPONENT_TYPE;

	/**
	 * The feature id for the '<em><b>Filter</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPEC__FILTER = COMPONENT_SPEC_BASE__FILTER;

	/**
	 * The feature id for the '<em><b>Project Info</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPEC__PROJECT_INFO = COMPONENT_SPEC_BASE__PROJECT_INFO;

	/**
	 * The feature id for the '<em><b>Short Desc</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPEC__SHORT_DESC = COMPONENT_SPEC_BASE__SHORT_DESC;

	/**
	 * The feature id for the '<em><b>Version String</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPEC__VERSION_STRING = COMPONENT_SPEC_BASE__VERSION_STRING;

	/**
	 * The feature id for the '<em><b>Version Type</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPEC__VERSION_TYPE = COMPONENT_SPEC_BASE__VERSION_TYPE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPEC__NAME = COMPONENT_SPEC_BASE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Component Spec</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPONENT_SPEC_FEATURE_COUNT = COMPONENT_SPEC_BASE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '
	 * {@link org.eclipse.buckminster.cspecxml.impl.CSpecExtensionImpl
	 * <em>CSpec Extension</em>}' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.cspecxml.impl.CSpecExtensionImpl
	 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getCSpecExtension()
	 * @generated
	 */
	int CSPEC_EXTENSION = 19;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CSPEC_EXTENSION__DOCUMENTATION = COMPONENT_SPEC_BASE__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CSPEC_EXTENSION__GROUP = COMPONENT_SPEC_BASE__GROUP;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment
	 * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CSPEC_EXTENSION__DEPENDENCIES = COMPONENT_SPEC_BASE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Generators</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CSPEC_EXTENSION__GENERATORS = COMPONENT_SPEC_BASE__GENERATORS;

	/**
	 * The feature id for the '<em><b>Artifacts</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CSPEC_EXTENSION__ARTIFACTS = COMPONENT_SPEC_BASE__ARTIFACTS;

	/**
	 * The feature id for the '<em><b>Groups</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CSPEC_EXTENSION__GROUPS = COMPONENT_SPEC_BASE__GROUPS;

	/**
	 * The feature id for the '<em><b>Actions</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CSPEC_EXTENSION__ACTIONS = COMPONENT_SPEC_BASE__ACTIONS;

	/**
	 * The feature id for the '<em><b>Category</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CSPEC_EXTENSION__CATEGORY = COMPONENT_SPEC_BASE__CATEGORY;

	/**
	 * The feature id for the '<em><b>Component Type</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CSPEC_EXTENSION__COMPONENT_TYPE = COMPONENT_SPEC_BASE__COMPONENT_TYPE;

	/**
	 * The feature id for the '<em><b>Filter</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CSPEC_EXTENSION__FILTER = COMPONENT_SPEC_BASE__FILTER;

	/**
	 * The feature id for the '<em><b>Project Info</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CSPEC_EXTENSION__PROJECT_INFO = COMPONENT_SPEC_BASE__PROJECT_INFO;

	/**
	 * The feature id for the '<em><b>Short Desc</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CSPEC_EXTENSION__SHORT_DESC = COMPONENT_SPEC_BASE__SHORT_DESC;

	/**
	 * The feature id for the '<em><b>Version String</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CSPEC_EXTENSION__VERSION_STRING = COMPONENT_SPEC_BASE__VERSION_STRING;

	/**
	 * The feature id for the '<em><b>Version Type</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CSPEC_EXTENSION__VERSION_TYPE = COMPONENT_SPEC_BASE__VERSION_TYPE;

	/**
	 * The feature id for the '<em><b>Group1</b></em>' attribute list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CSPEC_EXTENSION__GROUP1 = COMPONENT_SPEC_BASE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Alter Actions</b></em>' containment
	 * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CSPEC_EXTENSION__ALTER_ACTIONS = COMPONENT_SPEC_BASE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Alter Artifacts</b></em>' containment
	 * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CSPEC_EXTENSION__ALTER_ARTIFACTS = COMPONENT_SPEC_BASE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Alter Dependencies</b></em>' containment
	 * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CSPEC_EXTENSION__ALTER_DEPENDENCIES = COMPONENT_SPEC_BASE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Alter Groups</b></em>' containment
	 * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CSPEC_EXTENSION__ALTER_GROUPS = COMPONENT_SPEC_BASE_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>CSpec Extension</em>'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CSPEC_EXTENSION_FEATURE_COUNT = COMPONENT_SPEC_BASE_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '
	 * {@link org.eclipse.buckminster.cspecxml.impl.DefinitionsImpl
	 * <em>Definitions</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see org.eclipse.buckminster.cspecxml.impl.DefinitionsImpl
	 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getDefinitions()
	 * @generated
	 */
	int DEFINITIONS = 20;

	/**
	 * The feature id for the '<em><b>Define</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DEFINITIONS__DEFINE = 0;

	/**
	 * The number of structural features of the '<em>Definitions</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DEFINITIONS_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '
	 * {@link org.eclipse.buckminster.cspecxml.impl.DependenciesTypeImpl
	 * <em>Dependencies Type</em>}' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.cspecxml.impl.DependenciesTypeImpl
	 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getDependenciesType()
	 * @generated
	 */
	int DEPENDENCIES_TYPE = 21;

	/**
	 * The feature id for the '<em><b>Dependency</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DEPENDENCIES_TYPE__DEPENDENCY = 0;

	/**
	 * The number of structural features of the '<em>Dependencies Type</em>'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DEPENDENCIES_TYPE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '
	 * {@link org.eclipse.buckminster.cspecxml.impl.DocumentRootImpl
	 * <em>Document Root</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see org.eclipse.buckminster.cspecxml.impl.DocumentRootImpl
	 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getDocumentRoot()
	 * @generated
	 */
	int DOCUMENT_ROOT = 22;

	/**
	 * The feature id for the '<em><b>Mixed</b></em>' attribute list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__MIXED = 0;

	/**
	 * The feature id for the '<em><b>XMLNS Prefix Map</b></em>' map. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__XMLNS_PREFIX_MAP = 1;

	/**
	 * The feature id for the '<em><b>XSI Schema Location</b></em>' map. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = 2;

	/**
	 * The feature id for the '<em><b>Cspec</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__CSPEC = 3;

	/**
	 * The feature id for the '<em><b>Cspec Extension</b></em>' containment
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__CSPEC_EXTENSION = 4;

	/**
	 * The number of structural features of the '<em>Document Root</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '
	 * {@link org.eclipse.buckminster.cspecxml.impl.GeneratorImpl
	 * <em>Generator</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.cspecxml.impl.GeneratorImpl
	 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getGenerator()
	 * @generated
	 */
	int GENERATOR = 23;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int GENERATOR__ATTRIBUTE = 0;

	/**
	 * The feature id for the '<em><b>Component</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int GENERATOR__COMPONENT = 1;

	/**
	 * The feature id for the '<em><b>Component Type</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int GENERATOR__COMPONENT_TYPE = 2;

	/**
	 * The feature id for the '<em><b>Generates</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int GENERATOR__GENERATES = 3;

	/**
	 * The feature id for the '<em><b>Generates Type</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int GENERATOR__GENERATES_TYPE = 4;

	/**
	 * The feature id for the '<em><b>Generates Version String</b></em>'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int GENERATOR__GENERATES_VERSION_STRING = 5;

	/**
	 * The feature id for the '<em><b>Generates Version Type</b></em>'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int GENERATOR__GENERATES_VERSION_TYPE = 6;

	/**
	 * The number of structural features of the '<em>Generator</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int GENERATOR_FEATURE_COUNT = 7;

	/**
	 * The meta object id for the '
	 * {@link org.eclipse.buckminster.cspecxml.impl.GeneratorsTypeImpl
	 * <em>Generators Type</em>}' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.cspecxml.impl.GeneratorsTypeImpl
	 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getGeneratorsType()
	 * @generated
	 */
	int GENERATORS_TYPE = 24;

	/**
	 * The feature id for the '<em><b>Generator</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int GENERATORS_TYPE__GENERATOR = 0;

	/**
	 * The number of structural features of the '<em>Generators Type</em>'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int GENERATORS_TYPE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '
	 * {@link org.eclipse.buckminster.cspecxml.impl.GroupsTypeImpl
	 * <em>Groups Type</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see org.eclipse.buckminster.cspecxml.impl.GroupsTypeImpl
	 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getGroupsType()
	 * @generated
	 */
	int GROUPS_TYPE = 26;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int GROUPS_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Public</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int GROUPS_TYPE__PUBLIC = 1;

	/**
	 * The feature id for the '<em><b>Private</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int GROUPS_TYPE__PRIVATE = 2;

	/**
	 * The number of structural features of the '<em>Groups Type</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int GROUPS_TYPE_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '
	 * {@link org.eclipse.buckminster.cspecxml.impl.ImportImpl <em>Import</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.cspecxml.impl.ImportImpl
	 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getImport()
	 * @generated
	 */
	int IMPORT = 27;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IMPORT__ATTRIBUTE = 0;

	/**
	 * The feature id for the '<em><b>Filter</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IMPORT__FILTER = 1;

	/**
	 * The feature id for the '<em><b>Version Designator</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IMPORT__VERSION_DESIGNATOR = 2;

	/**
	 * The number of structural features of the '<em>Import</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IMPORT_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '
	 * {@link org.eclipse.buckminster.cspecxml.impl.ParameterTypeImpl
	 * <em>Parameter Type</em>}' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.cspecxml.impl.ParameterTypeImpl
	 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getParameterType()
	 * @generated
	 */
	int PARAMETER_TYPE = 28;

	/**
	 * The feature id for the '<em><b>Mandatory</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PARAMETER_TYPE__MANDATORY = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PARAMETER_TYPE__NAME = 1;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PARAMETER_TYPE__VALUE = 2;

	/**
	 * The number of structural features of the '<em>Parameter Type</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PARAMETER_TYPE_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '
	 * {@link org.eclipse.buckminster.cspecxml.impl.PathImpl <em>Path</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.cspecxml.impl.PathImpl
	 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getPath()
	 * @generated
	 */
	int PATH = 29;

	/**
	 * The feature id for the '<em><b>Path</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PATH__PATH = 0;

	/**
	 * The number of structural features of the '<em>Path</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PATH_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '
	 * {@link org.eclipse.buckminster.cspecxml.impl.PrerequisiteImpl
	 * <em>Prerequisite</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see org.eclipse.buckminster.cspecxml.impl.PrerequisiteImpl
	 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getPrerequisite()
	 * @generated
	 */
	int PREREQUISITE = 30;

	/**
	 * The feature id for the '<em><b>Alias</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PREREQUISITE__ALIAS = 0;

	/**
	 * The feature id for the '<em><b>Component</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PREREQUISITE__COMPONENT = 1;

	/**
	 * The feature id for the '<em><b>Component Type</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PREREQUISITE__COMPONENT_TYPE = 2;

	/**
	 * The feature id for the '<em><b>Contributor</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PREREQUISITE__CONTRIBUTOR = 3;

	/**
	 * The feature id for the '<em><b>Exclude Pattern</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PREREQUISITE__EXCLUDE_PATTERN = 4;

	/**
	 * The feature id for the '<em><b>Filter</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PREREQUISITE__FILTER = 5;

	/**
	 * The feature id for the '<em><b>Include Pattern</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PREREQUISITE__INCLUDE_PATTERN = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PREREQUISITE__NAME = 7;

	/**
	 * The feature id for the '<em><b>Optional</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PREREQUISITE__OPTIONAL = 8;

	/**
	 * The number of structural features of the '<em>Prerequisite</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PREREQUISITE_FEATURE_COUNT = 9;

	/**
	 * The meta object id for the '
	 * {@link org.eclipse.buckminster.cspecxml.impl.ProductsTypeImpl
	 * <em>Products Type</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see org.eclipse.buckminster.cspecxml.impl.ProductsTypeImpl
	 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getProductsType()
	 * @generated
	 */
	int PRODUCTS_TYPE = 32;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PRODUCTS_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Path</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PRODUCTS_TYPE__PATH = 1;

	/**
	 * The feature id for the '<em><b>Public</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PRODUCTS_TYPE__PUBLIC = 2;

	/**
	 * The feature id for the '<em><b>Private</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PRODUCTS_TYPE__PRIVATE = 3;

	/**
	 * The feature id for the '<em><b>Alias</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PRODUCTS_TYPE__ALIAS = 4;

	/**
	 * The feature id for the '<em><b>Base</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PRODUCTS_TYPE__BASE = 5;

	/**
	 * The feature id for the '<em><b>File Count</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PRODUCTS_TYPE__FILE_COUNT = 6;

	/**
	 * The feature id for the '<em><b>Pattern</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PRODUCTS_TYPE__PATTERN = 7;

	/**
	 * The feature id for the '<em><b>Replacement</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PRODUCTS_TYPE__REPLACEMENT = 8;

	/**
	 * The feature id for the '<em><b>Up To Date Policy</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PRODUCTS_TYPE__UP_TO_DATE_POLICY = 9;

	/**
	 * The number of structural features of the '<em>Products Type</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PRODUCTS_TYPE_FEATURE_COUNT = 10;

	/**
	 * The meta object id for the '
	 * {@link org.eclipse.buckminster.cspecxml.impl.PropertiesImpl
	 * <em>Properties</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see org.eclipse.buckminster.cspecxml.impl.PropertiesImpl
	 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getProperties()
	 * @generated
	 */
	int PROPERTIES = 33;

	/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROPERTIES__PROPERTY = 0;

	/**
	 * The number of structural features of the '<em>Properties</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROPERTIES_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '
	 * {@link org.eclipse.buckminster.cspecxml.impl.RemoveImpl <em>Remove</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.cspecxml.impl.RemoveImpl
	 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getRemove()
	 * @generated
	 */
	int REMOVE = 34;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REMOVE__NAME = 0;

	/**
	 * The number of structural features of the '<em>Remove</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REMOVE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '
	 * {@link org.eclipse.buckminster.cspecxml.impl.RemovePathImpl
	 * <em>Remove Path</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see org.eclipse.buckminster.cspecxml.impl.RemovePathImpl
	 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getRemovePath()
	 * @generated
	 */
	int REMOVE_PATH = 35;

	/**
	 * The feature id for the '<em><b>Path</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REMOVE_PATH__PATH = 0;

	/**
	 * The number of structural features of the '<em>Remove Path</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REMOVE_PATH_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '
	 * {@link org.eclipse.buckminster.cspecxml.impl.RemovePropertyImpl
	 * <em>Remove Property</em>}' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.cspecxml.impl.RemovePropertyImpl
	 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getRemoveProperty()
	 * @generated
	 */
	int REMOVE_PROPERTY = 36;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REMOVE_PROPERTY__KEY = 0;

	/**
	 * The number of structural features of the '<em>Remove Property</em>'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REMOVE_PROPERTY_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '
	 * {@link org.eclipse.buckminster.cspecxml.impl.RenameImpl <em>Rename</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.cspecxml.impl.RenameImpl
	 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getRename()
	 * @generated
	 */
	int RENAME = 37;

	/**
	 * The feature id for the '<em><b>New Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int RENAME__NEW_NAME = 0;

	/**
	 * The feature id for the '<em><b>Old Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int RENAME__OLD_NAME = 1;

	/**
	 * The number of structural features of the '<em>Rename</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int RENAME_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '
	 * {@link org.eclipse.buckminster.cspecxml.UpToDatePolicy
	 * <em>Up To Date Policy</em>}' enum. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.cspecxml.UpToDatePolicy
	 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getUpToDatePolicy()
	 * @generated
	 */
	int UP_TO_DATE_POLICY = 38;

	/**
	 * The meta object id for the '<em>Up To Date Policy Object</em>' data type.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.cspecxml.UpToDatePolicy
	 * @see org.eclipse.buckminster.cspecxml.impl.CSpecXMLPackageImpl#getUpToDatePolicyObject()
	 * @generated
	 */
	int UP_TO_DATE_POLICY_OBJECT = 39;

	/**
	 * Returns the meta object for class '
	 * {@link org.eclipse.buckminster.cspecxml.IAction <em>Action</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Action</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IAction
	 * @generated
	 */
	EClass getAction();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.cspecxml.IAction#getActor <em>Actor</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Actor</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IAction#getActor()
	 * @see #getAction()
	 * @generated
	 */
	EAttribute getAction_Actor();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspecxml.IAction#getActorProperties
	 * <em>Actor Properties</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Actor Properties</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IAction#getActorProperties()
	 * @see #getAction()
	 * @generated
	 */
	EReference getAction_ActorProperties();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.cspecxml.IAction#isAlways <em>Always</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Always</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IAction#isAlways()
	 * @see #getAction()
	 * @generated
	 */
	EAttribute getAction_Always();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.cspecxml.IAction#isAssignConsoleSupport
	 * <em>Assign Console Support</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for the attribute '
	 *         <em>Assign Console Support</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IAction#isAssignConsoleSupport()
	 * @see #getAction()
	 * @generated
	 */
	EAttribute getAction_AssignConsoleSupport();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspecxml.IAction#getDefinitions
	 * <em>Definitions</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Definitions</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IAction#getDefinitions()
	 * @see #getAction()
	 * @generated
	 */
	EReference getAction_Definitions();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.cspecxml.IAction#isEnabled
	 * <em>Enabled</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Enabled</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IAction#isEnabled()
	 * @see #getAction()
	 * @generated
	 */
	EAttribute getAction_Enabled();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.cspecxml.IAction#getFilter
	 * <em>Filter</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Filter</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IAction#getFilter()
	 * @see #getAction()
	 * @generated
	 */
	EAttribute getAction_Filter();

	/**
	 * Returns the meta object for the attribute list '
	 * {@link org.eclipse.buckminster.cspecxml.IAction#getGroup <em>Group</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IAction#getGroup()
	 * @see #getAction()
	 * @generated
	 */
	EAttribute getAction_Group();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspecxml.IAction#getPrerequisites
	 * <em>Prerequisites</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Prerequisites</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IAction#getPrerequisites()
	 * @see #getAction()
	 * @generated
	 */
	EReference getAction_Prerequisites();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspecxml.IAction#getProducts
	 * <em>Products</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Products</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IAction#getProducts()
	 * @see #getAction()
	 * @generated
	 */
	EReference getAction_Products();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspecxml.IAction#getProperties
	 * <em>Properties</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Properties</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IAction#getProperties()
	 * @see #getAction()
	 * @generated
	 */
	EReference getAction_Properties();

	/**
	 * Returns the meta object for class '
	 * {@link org.eclipse.buckminster.cspecxml.IActionArtifact
	 * <em>Action Artifact</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Action Artifact</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IActionArtifact
	 * @generated
	 */
	EClass getActionArtifact();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.cspecxml.IActionArtifact#getAlias
	 * <em>Alias</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Alias</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IActionArtifact#getAlias()
	 * @see #getActionArtifact()
	 * @generated
	 */
	EAttribute getActionArtifact_Alias();

	/**
	 * Returns the meta object for class '
	 * {@link org.eclipse.buckminster.cspecxml.IActionsType
	 * <em>Actions Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Actions Type</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IActionsType
	 * @generated
	 */
	EClass getActionsType();

	/**
	 * Returns the meta object for the attribute list '
	 * {@link org.eclipse.buckminster.cspecxml.IActionsType#getGroup
	 * <em>Group</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IActionsType#getGroup()
	 * @see #getActionsType()
	 * @generated
	 */
	EAttribute getActionsType_Group();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspecxml.IActionsType#getPrivate
	 * <em>Private</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Private</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IActionsType#getPrivate()
	 * @see #getActionsType()
	 * @generated
	 */
	EReference getActionsType_Private();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspecxml.IActionsType#getPublic
	 * <em>Public</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Public</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IActionsType#getPublic()
	 * @see #getActionsType()
	 * @generated
	 */
	EReference getActionsType_Public();

	/**
	 * Returns the meta object for class '
	 * {@link org.eclipse.buckminster.cspecxml.IAlterAction
	 * <em>Alter Action</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Alter Action</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IAlterAction
	 * @generated
	 */
	EClass getAlterAction();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspecxml.IAlterAction#getAlterActorProperties
	 * <em>Alter Actor Properties</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Alter Actor Properties</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IAlterAction#getAlterActorProperties()
	 * @see #getAlterAction()
	 * @generated
	 */
	EReference getAlterAction_AlterActorProperties();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspecxml.IAlterAction#getAlterPrerequisites
	 * <em>Alter Prerequisites</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Alter Prerequisites</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IAlterAction#getAlterPrerequisites()
	 * @see #getAlterAction()
	 * @generated
	 */
	EReference getAlterAction_AlterPrerequisites();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspecxml.IAlterAction#getAlterProducts
	 * <em>Alter Products</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Alter Products</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IAlterAction#getAlterProducts()
	 * @see #getAlterAction()
	 * @generated
	 */
	EReference getAlterAction_AlterProducts();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspecxml.IAlterAction#getAlterProperties
	 * <em>Alter Properties</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Alter Properties</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IAlterAction#getAlterProperties()
	 * @see #getAlterAction()
	 * @generated
	 */
	EReference getAlterAction_AlterProperties();

	/**
	 * Returns the meta object for the attribute list '
	 * {@link org.eclipse.buckminster.cspecxml.IAlterAction#getGroup1
	 * <em>Group1</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute list '<em>Group1</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IAlterAction#getGroup1()
	 * @see #getAlterAction()
	 * @generated
	 */
	EAttribute getAlterAction_Group1();

	/**
	 * Returns the meta object for class '
	 * {@link org.eclipse.buckminster.cspecxml.IAlterActionsType
	 * <em>Alter Actions Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for class '<em>Alter Actions Type</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IAlterActionsType
	 * @generated
	 */
	EClass getAlterActionsType();

	/**
	 * Returns the meta object for the attribute list '
	 * {@link org.eclipse.buckminster.cspecxml.IAlterActionsType#getGroup
	 * <em>Group</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IAlterActionsType#getGroup()
	 * @see #getAlterActionsType()
	 * @generated
	 */
	EAttribute getAlterActionsType_Group();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspecxml.IAlterActionsType#getPrivate
	 * <em>Private</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Private</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IAlterActionsType#getPrivate()
	 * @see #getAlterActionsType()
	 * @generated
	 */
	EReference getAlterActionsType_Private();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspecxml.IAlterActionsType#getPublic
	 * <em>Public</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Public</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IAlterActionsType#getPublic()
	 * @see #getAlterActionsType()
	 * @generated
	 */
	EReference getAlterActionsType_Public();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspecxml.IAlterActionsType#getRemove
	 * <em>Remove</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Remove</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IAlterActionsType#getRemove()
	 * @see #getAlterActionsType()
	 * @generated
	 */
	EReference getAlterActionsType_Remove();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspecxml.IAlterActionsType#getRename
	 * <em>Rename</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Rename</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IAlterActionsType#getRename()
	 * @see #getAlterActionsType()
	 * @generated
	 */
	EReference getAlterActionsType_Rename();

	/**
	 * Returns the meta object for class '
	 * {@link org.eclipse.buckminster.cspecxml.IAlterArtifact
	 * <em>Alter Artifact</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Alter Artifact</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IAlterArtifact
	 * @generated
	 */
	EClass getAlterArtifact();

	/**
	 * Returns the meta object for the attribute list '
	 * {@link org.eclipse.buckminster.cspecxml.IAlterArtifact#getGroup1
	 * <em>Group1</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute list '<em>Group1</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IAlterArtifact#getGroup1()
	 * @see #getAlterArtifact()
	 * @generated
	 */
	EAttribute getAlterArtifact_Group1();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspecxml.IAlterArtifact#getRemovePath
	 * <em>Remove Path</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Remove Path</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IAlterArtifact#getRemovePath()
	 * @see #getAlterArtifact()
	 * @generated
	 */
	EReference getAlterArtifact_RemovePath();

	/**
	 * Returns the meta object for class '
	 * {@link org.eclipse.buckminster.cspecxml.IAlterArtifactsType
	 * <em>Alter Artifacts Type</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Alter Artifacts Type</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IAlterArtifactsType
	 * @generated
	 */
	EClass getAlterArtifactsType();

	/**
	 * Returns the meta object for the attribute list '
	 * {@link org.eclipse.buckminster.cspecxml.IAlterArtifactsType#getGroup
	 * <em>Group</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IAlterArtifactsType#getGroup()
	 * @see #getAlterArtifactsType()
	 * @generated
	 */
	EAttribute getAlterArtifactsType_Group();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspecxml.IAlterArtifactsType#getPrivate
	 * <em>Private</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Private</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IAlterArtifactsType#getPrivate()
	 * @see #getAlterArtifactsType()
	 * @generated
	 */
	EReference getAlterArtifactsType_Private();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspecxml.IAlterArtifactsType#getPublic
	 * <em>Public</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Public</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IAlterArtifactsType#getPublic()
	 * @see #getAlterArtifactsType()
	 * @generated
	 */
	EReference getAlterArtifactsType_Public();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspecxml.IAlterArtifactsType#getRemove
	 * <em>Remove</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Remove</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IAlterArtifactsType#getRemove()
	 * @see #getAlterArtifactsType()
	 * @generated
	 */
	EReference getAlterArtifactsType_Remove();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspecxml.IAlterArtifactsType#getRename
	 * <em>Rename</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Rename</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IAlterArtifactsType#getRename()
	 * @see #getAlterArtifactsType()
	 * @generated
	 */
	EReference getAlterArtifactsType_Rename();

	/**
	 * Returns the meta object for class '
	 * {@link org.eclipse.buckminster.cspecxml.IAlterDependenciesType
	 * <em>Alter Dependencies Type</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Alter Dependencies Type</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IAlterDependenciesType
	 * @generated
	 */
	EClass getAlterDependenciesType();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspecxml.IAlterDependenciesType#getDependency
	 * <em>Dependency</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Dependency</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IAlterDependenciesType#getDependency()
	 * @see #getAlterDependenciesType()
	 * @generated
	 */
	EReference getAlterDependenciesType_Dependency();

	/**
	 * Returns the meta object for the attribute list '
	 * {@link org.eclipse.buckminster.cspecxml.IAlterDependenciesType#getGroup
	 * <em>Group</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IAlterDependenciesType#getGroup()
	 * @see #getAlterDependenciesType()
	 * @generated
	 */
	EAttribute getAlterDependenciesType_Group();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspecxml.IAlterDependenciesType#getRemove
	 * <em>Remove</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Remove</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IAlterDependenciesType#getRemove()
	 * @see #getAlterDependenciesType()
	 * @generated
	 */
	EReference getAlterDependenciesType_Remove();

	/**
	 * Returns the meta object for class '
	 * {@link org.eclipse.buckminster.cspecxml.IAlterGroup <em>Alter Group</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Alter Group</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IAlterGroup
	 * @generated
	 */
	EClass getAlterGroup();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspecxml.IAlterGroup#getAlterAttribute
	 * <em>Alter Attribute</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Alter Attribute</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IAlterGroup#getAlterAttribute()
	 * @see #getAlterGroup()
	 * @generated
	 */
	EReference getAlterGroup_AlterAttribute();

	/**
	 * Returns the meta object for the attribute list '
	 * {@link org.eclipse.buckminster.cspecxml.IAlterGroup#getGroup1
	 * <em>Group1</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute list '<em>Group1</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IAlterGroup#getGroup1()
	 * @see #getAlterGroup()
	 * @generated
	 */
	EAttribute getAlterGroup_Group1();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspecxml.IAlterGroup#getRemove
	 * <em>Remove</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Remove</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IAlterGroup#getRemove()
	 * @see #getAlterGroup()
	 * @generated
	 */
	EReference getAlterGroup_Remove();

	/**
	 * Returns the meta object for class '
	 * {@link org.eclipse.buckminster.cspecxml.IAlterGroupsType
	 * <em>Alter Groups Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for class '<em>Alter Groups Type</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IAlterGroupsType
	 * @generated
	 */
	EClass getAlterGroupsType();

	/**
	 * Returns the meta object for the attribute list '
	 * {@link org.eclipse.buckminster.cspecxml.IAlterGroupsType#getGroup
	 * <em>Group</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IAlterGroupsType#getGroup()
	 * @see #getAlterGroupsType()
	 * @generated
	 */
	EAttribute getAlterGroupsType_Group();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspecxml.IAlterGroupsType#getPrivate
	 * <em>Private</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Private</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IAlterGroupsType#getPrivate()
	 * @see #getAlterGroupsType()
	 * @generated
	 */
	EReference getAlterGroupsType_Private();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspecxml.IAlterGroupsType#getPublic
	 * <em>Public</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Public</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IAlterGroupsType#getPublic()
	 * @see #getAlterGroupsType()
	 * @generated
	 */
	EReference getAlterGroupsType_Public();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspecxml.IAlterGroupsType#getRemove
	 * <em>Remove</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Remove</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IAlterGroupsType#getRemove()
	 * @see #getAlterGroupsType()
	 * @generated
	 */
	EReference getAlterGroupsType_Remove();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspecxml.IAlterGroupsType#getRename
	 * <em>Rename</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Rename</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IAlterGroupsType#getRename()
	 * @see #getAlterGroupsType()
	 * @generated
	 */
	EReference getAlterGroupsType_Rename();

	/**
	 * Returns the meta object for class '
	 * {@link org.eclipse.buckminster.cspecxml.IAlterPrerequisites
	 * <em>Alter Prerequisites</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for class '<em>Alter Prerequisites</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IAlterPrerequisites
	 * @generated
	 */
	EClass getAlterPrerequisites();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspecxml.IAlterPrerequisites#getAlterAttribute
	 * <em>Alter Attribute</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Alter Attribute</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IAlterPrerequisites#getAlterAttribute()
	 * @see #getAlterPrerequisites()
	 * @generated
	 */
	EReference getAlterPrerequisites_AlterAttribute();

	/**
	 * Returns the meta object for the attribute list '
	 * {@link org.eclipse.buckminster.cspecxml.IAlterPrerequisites#getGroup1
	 * <em>Group1</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute list '<em>Group1</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IAlterPrerequisites#getGroup1()
	 * @see #getAlterPrerequisites()
	 * @generated
	 */
	EAttribute getAlterPrerequisites_Group1();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspecxml.IAlterPrerequisites#getRemove
	 * <em>Remove</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Remove</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IAlterPrerequisites#getRemove()
	 * @see #getAlterPrerequisites()
	 * @generated
	 */
	EReference getAlterPrerequisites_Remove();

	/**
	 * Returns the meta object for class '
	 * {@link org.eclipse.buckminster.cspecxml.IAlterProductsType
	 * <em>Alter Products Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for class '<em>Alter Products Type</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IAlterProductsType
	 * @generated
	 */
	EClass getAlterProductsType();

	/**
	 * Returns the meta object for the attribute list '
	 * {@link org.eclipse.buckminster.cspecxml.IAlterProductsType#getGroup
	 * <em>Group</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IAlterProductsType#getGroup()
	 * @see #getAlterProductsType()
	 * @generated
	 */
	EAttribute getAlterProductsType_Group();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspecxml.IAlterProductsType#getPrivate
	 * <em>Private</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Private</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IAlterProductsType#getPrivate()
	 * @see #getAlterProductsType()
	 * @generated
	 */
	EReference getAlterProductsType_Private();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspecxml.IAlterProductsType#getPublic
	 * <em>Public</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Public</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IAlterProductsType#getPublic()
	 * @see #getAlterProductsType()
	 * @generated
	 */
	EReference getAlterProductsType_Public();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspecxml.IAlterProductsType#getRemovePath
	 * <em>Remove Path</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Remove Path</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IAlterProductsType#getRemovePath()
	 * @see #getAlterProductsType()
	 * @generated
	 */
	EReference getAlterProductsType_RemovePath();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspecxml.IAlterProductsType#getRemoveProduct
	 * <em>Remove Product</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Remove Product</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IAlterProductsType#getRemoveProduct()
	 * @see #getAlterProductsType()
	 * @generated
	 */
	EReference getAlterProductsType_RemoveProduct();

	/**
	 * Returns the meta object for class '
	 * {@link org.eclipse.buckminster.cspecxml.IAlterProperties
	 * <em>Alter Properties</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for class '<em>Alter Properties</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IAlterProperties
	 * @generated
	 */
	EClass getAlterProperties();

	/**
	 * Returns the meta object for the attribute list '
	 * {@link org.eclipse.buckminster.cspecxml.IAlterProperties#getGroup
	 * <em>Group</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IAlterProperties#getGroup()
	 * @see #getAlterProperties()
	 * @generated
	 */
	EAttribute getAlterProperties_Group();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspecxml.IAlterProperties#getProperty
	 * <em>Property</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Property</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IAlterProperties#getProperty()
	 * @see #getAlterProperties()
	 * @generated
	 */
	EReference getAlterProperties_Property();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspecxml.IAlterProperties#getRemove
	 * <em>Remove</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Remove</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IAlterProperties#getRemove()
	 * @see #getAlterProperties()
	 * @generated
	 */
	EReference getAlterProperties_Remove();

	/**
	 * Returns the meta object for class '
	 * {@link org.eclipse.buckminster.cspecxml.IArtifact <em>Artifact</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Artifact</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IArtifact
	 * @generated
	 */
	EClass getArtifact();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.cspecxml.IArtifact#getBase <em>Base</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Base</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IArtifact#getBase()
	 * @see #getArtifact()
	 * @generated
	 */
	EAttribute getArtifact_Base();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspecxml.IArtifact#getDefinitions
	 * <em>Definitions</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Definitions</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IArtifact#getDefinitions()
	 * @see #getArtifact()
	 * @generated
	 */
	EReference getArtifact_Definitions();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.cspecxml.IArtifact#getFilter
	 * <em>Filter</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Filter</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IArtifact#getFilter()
	 * @see #getArtifact()
	 * @generated
	 */
	EAttribute getArtifact_Filter();

	/**
	 * Returns the meta object for the attribute list '
	 * {@link org.eclipse.buckminster.cspecxml.IArtifact#getGroup
	 * <em>Group</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IArtifact#getGroup()
	 * @see #getArtifact()
	 * @generated
	 */
	EAttribute getArtifact_Group();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspecxml.IArtifact#getPath <em>Path</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Path</em>
	 *         '.
	 * @see org.eclipse.buckminster.cspecxml.IArtifact#getPath()
	 * @see #getArtifact()
	 * @generated
	 */
	EReference getArtifact_Path();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.cspecxml.IArtifact#getPath1
	 * <em>Path1</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Path1</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IArtifact#getPath1()
	 * @see #getArtifact()
	 * @generated
	 */
	EAttribute getArtifact_Path1();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.cspecxml.IArtifact#getType <em>Type</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IArtifact#getType()
	 * @see #getArtifact()
	 * @generated
	 */
	EAttribute getArtifact_Type();

	/**
	 * Returns the meta object for class '
	 * {@link org.eclipse.buckminster.cspecxml.IArtifactsType
	 * <em>Artifacts Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Artifacts Type</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IArtifactsType
	 * @generated
	 */
	EClass getArtifactsType();

	/**
	 * Returns the meta object for the attribute list '
	 * {@link org.eclipse.buckminster.cspecxml.IArtifactsType#getGroup
	 * <em>Group</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IArtifactsType#getGroup()
	 * @see #getArtifactsType()
	 * @generated
	 */
	EAttribute getArtifactsType_Group();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspecxml.IArtifactsType#getPrivate
	 * <em>Private</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Private</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IArtifactsType#getPrivate()
	 * @see #getArtifactsType()
	 * @generated
	 */
	EReference getArtifactsType_Private();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspecxml.IArtifactsType#getPublic
	 * <em>Public</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Public</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IArtifactsType#getPublic()
	 * @see #getArtifactsType()
	 * @generated
	 */
	EReference getArtifactsType_Public();

	/**
	 * Returns the meta object for class '
	 * {@link org.eclipse.buckminster.cspecxml.IAttribute <em>Attribute</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Attribute</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IAttribute
	 * @generated
	 */
	EClass getAttribute();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link org.eclipse.buckminster.cspecxml.IAttribute#getDocumentation
	 * <em>Documentation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '
	 *         <em>Documentation</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IAttribute#getDocumentation()
	 * @see #getAttribute()
	 * @generated
	 */
	EReference getAttribute_Documentation();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.cspecxml.IAttribute#getName <em>Name</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IAttribute#getName()
	 * @see #getAttribute()
	 * @generated
	 */
	EAttribute getAttribute_Name();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspecxml.IAttribute#getParameter
	 * <em>Parameter</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Parameter</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IAttribute#getParameter()
	 * @see #getAttribute()
	 * @generated
	 */
	EReference getAttribute_Parameter();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.cspecxml.IAttribute#getVersion
	 * <em>Version</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IAttribute#getVersion()
	 * @see #getAttribute()
	 * @generated
	 */
	EAttribute getAttribute_Version();

	/**
	 * Returns the meta object for class '
	 * {@link org.eclipse.buckminster.cspecxml.IComponentRequest
	 * <em>Component Request</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for class '<em>Component Request</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IComponentRequest
	 * @generated
	 */
	EClass getComponentRequest();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.cspecxml.IComponentRequest#getComponentType
	 * <em>Component Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Component Type</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IComponentRequest#getComponentType()
	 * @see #getComponentRequest()
	 * @generated
	 */
	EAttribute getComponentRequest_ComponentType();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.cspecxml.IComponentRequest#getFilter
	 * <em>Filter</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Filter</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IComponentRequest#getFilter()
	 * @see #getComponentRequest()
	 * @generated
	 */
	EAttribute getComponentRequest_Filter();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspecxml.IComponentRequest#getImport
	 * <em>Import</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Import</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IComponentRequest#getImport()
	 * @see #getComponentRequest()
	 * @generated
	 */
	EReference getComponentRequest_Import();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.cspecxml.IComponentRequest#getName
	 * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IComponentRequest#getName()
	 * @see #getComponentRequest()
	 * @generated
	 */
	EAttribute getComponentRequest_Name();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.cspecxml.IComponentRequest#getVersionDesignator
	 * <em>Version Designator</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for the attribute '<em>Version Designator</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IComponentRequest#getVersionDesignator()
	 * @see #getComponentRequest()
	 * @generated
	 */
	EAttribute getComponentRequest_VersionDesignator();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.cspecxml.IComponentRequest#getVersionType
	 * <em>Version Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Version Type</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IComponentRequest#getVersionType()
	 * @see #getComponentRequest()
	 * @generated
	 */
	EAttribute getComponentRequest_VersionType();

	/**
	 * Returns the meta object for class '
	 * {@link org.eclipse.buckminster.cspecxml.IComponentSpec
	 * <em>Component Spec</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Component Spec</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IComponentSpec
	 * @generated
	 */
	EClass getComponentSpec();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.cspecxml.IComponentSpec#getName
	 * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IComponentSpec#getName()
	 * @see #getComponentSpec()
	 * @generated
	 */
	EAttribute getComponentSpec_Name();

	/**
	 * Returns the meta object for class '
	 * {@link org.eclipse.buckminster.cspecxml.IComponentSpecBase
	 * <em>Component Spec Base</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for class '<em>Component Spec Base</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IComponentSpecBase
	 * @generated
	 */
	EClass getComponentSpecBase();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspecxml.IComponentSpecBase#getActions
	 * <em>Actions</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Actions</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IComponentSpecBase#getActions()
	 * @see #getComponentSpecBase()
	 * @generated
	 */
	EReference getComponentSpecBase_Actions();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspecxml.IComponentSpecBase#getArtifacts
	 * <em>Artifacts</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Artifacts</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IComponentSpecBase#getArtifacts()
	 * @see #getComponentSpecBase()
	 * @generated
	 */
	EReference getComponentSpecBase_Artifacts();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.cspecxml.IComponentSpecBase#getCategory
	 * <em>Category</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Category</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IComponentSpecBase#getCategory()
	 * @see #getComponentSpecBase()
	 * @generated
	 */
	EAttribute getComponentSpecBase_Category();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.cspecxml.IComponentSpecBase#getComponentType
	 * <em>Component Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Component Type</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IComponentSpecBase#getComponentType()
	 * @see #getComponentSpecBase()
	 * @generated
	 */
	EAttribute getComponentSpecBase_ComponentType();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspecxml.IComponentSpecBase#getDependencies
	 * <em>Dependencies</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Dependencies</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IComponentSpecBase#getDependencies()
	 * @see #getComponentSpecBase()
	 * @generated
	 */
	EReference getComponentSpecBase_Dependencies();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link org.eclipse.buckminster.cspecxml.IComponentSpecBase#getDocumentation
	 * <em>Documentation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '
	 *         <em>Documentation</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IComponentSpecBase#getDocumentation()
	 * @see #getComponentSpecBase()
	 * @generated
	 */
	EReference getComponentSpecBase_Documentation();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.cspecxml.IComponentSpecBase#getFilter
	 * <em>Filter</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Filter</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IComponentSpecBase#getFilter()
	 * @see #getComponentSpecBase()
	 * @generated
	 */
	EAttribute getComponentSpecBase_Filter();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspecxml.IComponentSpecBase#getGenerators
	 * <em>Generators</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Generators</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IComponentSpecBase#getGenerators()
	 * @see #getComponentSpecBase()
	 * @generated
	 */
	EReference getComponentSpecBase_Generators();

	/**
	 * Returns the meta object for the attribute list '
	 * {@link org.eclipse.buckminster.cspecxml.IComponentSpecBase#getGroup
	 * <em>Group</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IComponentSpecBase#getGroup()
	 * @see #getComponentSpecBase()
	 * @generated
	 */
	EAttribute getComponentSpecBase_Group();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspecxml.IComponentSpecBase#getGroups
	 * <em>Groups</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Groups</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IComponentSpecBase#getGroups()
	 * @see #getComponentSpecBase()
	 * @generated
	 */
	EReference getComponentSpecBase_Groups();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.cspecxml.IComponentSpecBase#getProjectInfo
	 * <em>Project Info</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Project Info</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IComponentSpecBase#getProjectInfo()
	 * @see #getComponentSpecBase()
	 * @generated
	 */
	EAttribute getComponentSpecBase_ProjectInfo();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.cspecxml.IComponentSpecBase#getShortDesc
	 * <em>Short Desc</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Short Desc</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IComponentSpecBase#getShortDesc()
	 * @see #getComponentSpecBase()
	 * @generated
	 */
	EAttribute getComponentSpecBase_ShortDesc();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.cspecxml.IComponentSpecBase#getVersionString
	 * <em>Version String</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Version String</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IComponentSpecBase#getVersionString()
	 * @see #getComponentSpecBase()
	 * @generated
	 */
	EAttribute getComponentSpecBase_VersionString();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.cspecxml.IComponentSpecBase#getVersionType
	 * <em>Version Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Version Type</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IComponentSpecBase#getVersionType()
	 * @see #getComponentSpecBase()
	 * @generated
	 */
	EAttribute getComponentSpecBase_VersionType();

	/**
	 * Returns the meta object for class '
	 * {@link org.eclipse.buckminster.cspecxml.ICSpecExtension
	 * <em>CSpec Extension</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>CSpec Extension</em>'.
	 * @see org.eclipse.buckminster.cspecxml.ICSpecExtension
	 * @generated
	 */
	EClass getCSpecExtension();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspecxml.ICSpecExtension#getAlterActions
	 * <em>Alter Actions</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Alter Actions</em>'.
	 * @see org.eclipse.buckminster.cspecxml.ICSpecExtension#getAlterActions()
	 * @see #getCSpecExtension()
	 * @generated
	 */
	EReference getCSpecExtension_AlterActions();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspecxml.ICSpecExtension#getAlterArtifacts
	 * <em>Alter Artifacts</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Alter Artifacts</em>'.
	 * @see org.eclipse.buckminster.cspecxml.ICSpecExtension#getAlterArtifacts()
	 * @see #getCSpecExtension()
	 * @generated
	 */
	EReference getCSpecExtension_AlterArtifacts();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspecxml.ICSpecExtension#getAlterDependencies
	 * <em>Alter Dependencies</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Alter Dependencies</em>'.
	 * @see org.eclipse.buckminster.cspecxml.ICSpecExtension#getAlterDependencies()
	 * @see #getCSpecExtension()
	 * @generated
	 */
	EReference getCSpecExtension_AlterDependencies();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspecxml.ICSpecExtension#getAlterGroups
	 * <em>Alter Groups</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Alter Groups</em>'.
	 * @see org.eclipse.buckminster.cspecxml.ICSpecExtension#getAlterGroups()
	 * @see #getCSpecExtension()
	 * @generated
	 */
	EReference getCSpecExtension_AlterGroups();

	/**
	 * Returns the meta object for the attribute list '
	 * {@link org.eclipse.buckminster.cspecxml.ICSpecExtension#getGroup1
	 * <em>Group1</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute list '<em>Group1</em>'.
	 * @see org.eclipse.buckminster.cspecxml.ICSpecExtension#getGroup1()
	 * @see #getCSpecExtension()
	 * @generated
	 */
	EAttribute getCSpecExtension_Group1();

	/**
	 * Returns the factory that creates the instances of the model. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ICSpecXMLFactory getCSpecXMLFactory();

	/**
	 * Returns the meta object for class '
	 * {@link org.eclipse.buckminster.cspecxml.IDefinitions
	 * <em>Definitions</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Definitions</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IDefinitions
	 * @generated
	 */
	EClass getDefinitions();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspecxml.IDefinitions#getDefine
	 * <em>Define</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Define</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IDefinitions#getDefine()
	 * @see #getDefinitions()
	 * @generated
	 */
	EReference getDefinitions_Define();

	/**
	 * Returns the meta object for class '
	 * {@link org.eclipse.buckminster.cspecxml.IDependenciesType
	 * <em>Dependencies Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for class '<em>Dependencies Type</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IDependenciesType
	 * @generated
	 */
	EClass getDependenciesType();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspecxml.IDependenciesType#getDependency
	 * <em>Dependency</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Dependency</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IDependenciesType#getDependency()
	 * @see #getDependenciesType()
	 * @generated
	 */
	EReference getDependenciesType_Dependency();

	/**
	 * Returns the meta object for class '
	 * {@link org.eclipse.buckminster.cspecxml.IDocumentRoot
	 * <em>Document Root</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Document Root</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IDocumentRoot
	 * @generated
	 */
	EClass getDocumentRoot();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link org.eclipse.buckminster.cspecxml.IDocumentRoot#getCspec
	 * <em>Cspec</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Cspec</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IDocumentRoot#getCspec()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Cspec();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link org.eclipse.buckminster.cspecxml.IDocumentRoot#getCspecExtension
	 * <em>Cspec Extension</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '
	 *         <em>Cspec Extension</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IDocumentRoot#getCspecExtension()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_CspecExtension();

	/**
	 * Returns the meta object for the attribute list '
	 * {@link org.eclipse.buckminster.cspecxml.IDocumentRoot#getMixed
	 * <em>Mixed</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute list '<em>Mixed</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IDocumentRoot#getMixed()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Mixed();

	/**
	 * Returns the meta object for the map '
	 * {@link org.eclipse.buckminster.cspecxml.IDocumentRoot#getXMLNSPrefixMap
	 * <em>XMLNS Prefix Map</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for the map '<em>XMLNS Prefix Map</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IDocumentRoot#getXMLNSPrefixMap()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XMLNSPrefixMap();

	/**
	 * Returns the meta object for the map '
	 * {@link org.eclipse.buckminster.cspecxml.IDocumentRoot#getXSISchemaLocation
	 * <em>XSI Schema Location</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for the map '<em>XSI Schema Location</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IDocumentRoot#getXSISchemaLocation()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XSISchemaLocation();

	/**
	 * Returns the meta object for class '
	 * {@link org.eclipse.buckminster.cspecxml.IGenerator <em>Generator</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Generator</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IGenerator
	 * @generated
	 */
	EClass getGenerator();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.cspecxml.IGenerator#getAttribute
	 * <em>Attribute</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Attribute</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IGenerator#getAttribute()
	 * @see #getGenerator()
	 * @generated
	 */
	EAttribute getGenerator_Attribute();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.cspecxml.IGenerator#getComponent
	 * <em>Component</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Component</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IGenerator#getComponent()
	 * @see #getGenerator()
	 * @generated
	 */
	EAttribute getGenerator_Component();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.cspecxml.IGenerator#getComponentType
	 * <em>Component Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Component Type</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IGenerator#getComponentType()
	 * @see #getGenerator()
	 * @generated
	 */
	EAttribute getGenerator_ComponentType();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.cspecxml.IGenerator#getGenerates
	 * <em>Generates</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Generates</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IGenerator#getGenerates()
	 * @see #getGenerator()
	 * @generated
	 */
	EAttribute getGenerator_Generates();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.cspecxml.IGenerator#getGeneratesType
	 * <em>Generates Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Generates Type</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IGenerator#getGeneratesType()
	 * @see #getGenerator()
	 * @generated
	 */
	EAttribute getGenerator_GeneratesType();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.cspecxml.IGenerator#getGeneratesVersionString
	 * <em>Generates Version String</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for the attribute '
	 *         <em>Generates Version String</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IGenerator#getGeneratesVersionString()
	 * @see #getGenerator()
	 * @generated
	 */
	EAttribute getGenerator_GeneratesVersionString();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.cspecxml.IGenerator#getGeneratesVersionType
	 * <em>Generates Version Type</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for the attribute '
	 *         <em>Generates Version Type</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IGenerator#getGeneratesVersionType()
	 * @see #getGenerator()
	 * @generated
	 */
	EAttribute getGenerator_GeneratesVersionType();

	/**
	 * Returns the meta object for class '
	 * {@link org.eclipse.buckminster.cspecxml.IGeneratorsType
	 * <em>Generators Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Generators Type</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IGeneratorsType
	 * @generated
	 */
	EClass getGeneratorsType();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspecxml.IGeneratorsType#getGenerator
	 * <em>Generator</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Generator</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IGeneratorsType#getGenerator()
	 * @see #getGeneratorsType()
	 * @generated
	 */
	EReference getGeneratorsType_Generator();

	/**
	 * Returns the meta object for class '
	 * {@link org.eclipse.buckminster.cspecxml.IGroup <em>Group</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Group</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IGroup
	 * @generated
	 */
	EClass getGroup();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspecxml.IGroup#getAttribute
	 * <em>Attribute</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Attribute</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IGroup#getAttribute()
	 * @see #getGroup()
	 * @generated
	 */
	EReference getGroup_Attribute();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspecxml.IGroup#getDefinitions
	 * <em>Definitions</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Definitions</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IGroup#getDefinitions()
	 * @see #getGroup()
	 * @generated
	 */
	EReference getGroup_Definitions();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.cspecxml.IGroup#getFilter <em>Filter</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Filter</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IGroup#getFilter()
	 * @see #getGroup()
	 * @generated
	 */
	EAttribute getGroup_Filter();

	/**
	 * Returns the meta object for the attribute list '
	 * {@link org.eclipse.buckminster.cspecxml.IGroup#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IGroup#getGroup()
	 * @see #getGroup()
	 * @generated
	 */
	EAttribute getGroup_Group();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.cspecxml.IGroup#getRebase <em>Rebase</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Rebase</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IGroup#getRebase()
	 * @see #getGroup()
	 * @generated
	 */
	EAttribute getGroup_Rebase();

	/**
	 * Returns the meta object for class '
	 * {@link org.eclipse.buckminster.cspecxml.IGroupsType <em>Groups Type</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Groups Type</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IGroupsType
	 * @generated
	 */
	EClass getGroupsType();

	/**
	 * Returns the meta object for the attribute list '
	 * {@link org.eclipse.buckminster.cspecxml.IGroupsType#getGroup
	 * <em>Group</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IGroupsType#getGroup()
	 * @see #getGroupsType()
	 * @generated
	 */
	EAttribute getGroupsType_Group();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspecxml.IGroupsType#getPrivate
	 * <em>Private</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Private</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IGroupsType#getPrivate()
	 * @see #getGroupsType()
	 * @generated
	 */
	EReference getGroupsType_Private();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspecxml.IGroupsType#getPublic
	 * <em>Public</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Public</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IGroupsType#getPublic()
	 * @see #getGroupsType()
	 * @generated
	 */
	EReference getGroupsType_Public();

	/**
	 * Returns the meta object for class '
	 * {@link org.eclipse.buckminster.cspecxml.IImport <em>Import</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Import</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IImport
	 * @generated
	 */
	EClass getImport();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.cspecxml.IImport#getAttribute
	 * <em>Attribute</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Attribute</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IImport#getAttribute()
	 * @see #getImport()
	 * @generated
	 */
	EAttribute getImport_Attribute();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.cspecxml.IImport#getFilter
	 * <em>Filter</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Filter</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IImport#getFilter()
	 * @see #getImport()
	 * @generated
	 */
	EAttribute getImport_Filter();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.cspecxml.IImport#getVersionDesignator
	 * <em>Version Designator</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for the attribute '<em>Version Designator</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IImport#getVersionDesignator()
	 * @see #getImport()
	 * @generated
	 */
	EAttribute getImport_VersionDesignator();

	/**
	 * Returns the meta object for class '
	 * {@link org.eclipse.buckminster.cspecxml.IParameterType
	 * <em>Parameter Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Parameter Type</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IParameterType
	 * @generated
	 */
	EClass getParameterType();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.cspecxml.IParameterType#isMandatory
	 * <em>Mandatory</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Mandatory</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IParameterType#isMandatory()
	 * @see #getParameterType()
	 * @generated
	 */
	EAttribute getParameterType_Mandatory();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.cspecxml.IParameterType#getName
	 * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IParameterType#getName()
	 * @see #getParameterType()
	 * @generated
	 */
	EAttribute getParameterType_Name();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.cspecxml.IParameterType#getValue
	 * <em>Value</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IParameterType#getValue()
	 * @see #getParameterType()
	 * @generated
	 */
	EAttribute getParameterType_Value();

	/**
	 * Returns the meta object for class '
	 * {@link org.eclipse.buckminster.cspecxml.IPath <em>Path</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Path</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IPath
	 * @generated
	 */
	EClass getPath();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.cspecxml.IPath#getPath <em>Path</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Path</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IPath#getPath()
	 * @see #getPath()
	 * @generated
	 */
	EAttribute getPath_Path();

	/**
	 * Returns the meta object for class '
	 * {@link org.eclipse.buckminster.cspecxml.IPrerequisite
	 * <em>Prerequisite</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Prerequisite</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IPrerequisite
	 * @generated
	 */
	EClass getPrerequisite();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.cspecxml.IPrerequisite#getAlias
	 * <em>Alias</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Alias</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IPrerequisite#getAlias()
	 * @see #getPrerequisite()
	 * @generated
	 */
	EAttribute getPrerequisite_Alias();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.cspecxml.IPrerequisite#getComponent
	 * <em>Component</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Component</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IPrerequisite#getComponent()
	 * @see #getPrerequisite()
	 * @generated
	 */
	EAttribute getPrerequisite_Component();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.cspecxml.IPrerequisite#getComponentType
	 * <em>Component Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Component Type</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IPrerequisite#getComponentType()
	 * @see #getPrerequisite()
	 * @generated
	 */
	EAttribute getPrerequisite_ComponentType();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.cspecxml.IPrerequisite#isContributor
	 * <em>Contributor</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Contributor</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IPrerequisite#isContributor()
	 * @see #getPrerequisite()
	 * @generated
	 */
	EAttribute getPrerequisite_Contributor();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.cspecxml.IPrerequisite#getExcludePattern
	 * <em>Exclude Pattern</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Exclude Pattern</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IPrerequisite#getExcludePattern()
	 * @see #getPrerequisite()
	 * @generated
	 */
	EAttribute getPrerequisite_ExcludePattern();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.cspecxml.IPrerequisite#getFilter
	 * <em>Filter</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Filter</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IPrerequisite#getFilter()
	 * @see #getPrerequisite()
	 * @generated
	 */
	EAttribute getPrerequisite_Filter();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.cspecxml.IPrerequisite#getIncludePattern
	 * <em>Include Pattern</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Include Pattern</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IPrerequisite#getIncludePattern()
	 * @see #getPrerequisite()
	 * @generated
	 */
	EAttribute getPrerequisite_IncludePattern();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.cspecxml.IPrerequisite#getName
	 * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IPrerequisite#getName()
	 * @see #getPrerequisite()
	 * @generated
	 */
	EAttribute getPrerequisite_Name();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.cspecxml.IPrerequisite#isOptional
	 * <em>Optional</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Optional</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IPrerequisite#isOptional()
	 * @see #getPrerequisite()
	 * @generated
	 */
	EAttribute getPrerequisite_Optional();

	/**
	 * Returns the meta object for class '
	 * {@link org.eclipse.buckminster.cspecxml.IPrerequisites
	 * <em>Prerequisites</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Prerequisites</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IPrerequisites
	 * @generated
	 */
	EClass getPrerequisites();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.cspecxml.IPrerequisites#getAlias
	 * <em>Alias</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Alias</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IPrerequisites#getAlias()
	 * @see #getPrerequisites()
	 * @generated
	 */
	EAttribute getPrerequisites_Alias();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspecxml.IPrerequisites#getAttribute
	 * <em>Attribute</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Attribute</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IPrerequisites#getAttribute()
	 * @see #getPrerequisites()
	 * @generated
	 */
	EReference getPrerequisites_Attribute();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link org.eclipse.buckminster.cspecxml.IPrerequisites#getDocumentation
	 * <em>Documentation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '
	 *         <em>Documentation</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IPrerequisites#getDocumentation()
	 * @see #getPrerequisites()
	 * @generated
	 */
	EReference getPrerequisites_Documentation();

	/**
	 * Returns the meta object for the attribute list '
	 * {@link org.eclipse.buckminster.cspecxml.IPrerequisites#getGroup
	 * <em>Group</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IPrerequisites#getGroup()
	 * @see #getPrerequisites()
	 * @generated
	 */
	EAttribute getPrerequisites_Group();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.cspecxml.IPrerequisites#getRebase
	 * <em>Rebase</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Rebase</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IPrerequisites#getRebase()
	 * @see #getPrerequisites()
	 * @generated
	 */
	EAttribute getPrerequisites_Rebase();

	/**
	 * Returns the meta object for class '
	 * {@link org.eclipse.buckminster.cspecxml.IProductsType
	 * <em>Products Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Products Type</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IProductsType
	 * @generated
	 */
	EClass getProductsType();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.cspecxml.IProductsType#getAlias
	 * <em>Alias</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Alias</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IProductsType#getAlias()
	 * @see #getProductsType()
	 * @generated
	 */
	EAttribute getProductsType_Alias();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.cspecxml.IProductsType#getBase
	 * <em>Base</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Base</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IProductsType#getBase()
	 * @see #getProductsType()
	 * @generated
	 */
	EAttribute getProductsType_Base();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.cspecxml.IProductsType#getFileCount
	 * <em>File Count</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>File Count</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IProductsType#getFileCount()
	 * @see #getProductsType()
	 * @generated
	 */
	EAttribute getProductsType_FileCount();

	/**
	 * Returns the meta object for the attribute list '
	 * {@link org.eclipse.buckminster.cspecxml.IProductsType#getGroup
	 * <em>Group</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IProductsType#getGroup()
	 * @see #getProductsType()
	 * @generated
	 */
	EAttribute getProductsType_Group();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspecxml.IProductsType#getPath
	 * <em>Path</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Path</em>
	 *         '.
	 * @see org.eclipse.buckminster.cspecxml.IProductsType#getPath()
	 * @see #getProductsType()
	 * @generated
	 */
	EReference getProductsType_Path();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.cspecxml.IProductsType#getPattern
	 * <em>Pattern</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Pattern</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IProductsType#getPattern()
	 * @see #getProductsType()
	 * @generated
	 */
	EAttribute getProductsType_Pattern();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspecxml.IProductsType#getPrivate
	 * <em>Private</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Private</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IProductsType#getPrivate()
	 * @see #getProductsType()
	 * @generated
	 */
	EReference getProductsType_Private();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspecxml.IProductsType#getPublic
	 * <em>Public</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Public</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IProductsType#getPublic()
	 * @see #getProductsType()
	 * @generated
	 */
	EReference getProductsType_Public();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.cspecxml.IProductsType#getReplacement
	 * <em>Replacement</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Replacement</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IProductsType#getReplacement()
	 * @see #getProductsType()
	 * @generated
	 */
	EAttribute getProductsType_Replacement();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.cspecxml.IProductsType#getUpToDatePolicy
	 * <em>Up To Date Policy</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for the attribute '<em>Up To Date Policy</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IProductsType#getUpToDatePolicy()
	 * @see #getProductsType()
	 * @generated
	 */
	EAttribute getProductsType_UpToDatePolicy();

	/**
	 * Returns the meta object for class '
	 * {@link org.eclipse.buckminster.cspecxml.IProperties <em>Properties</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Properties</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IProperties
	 * @generated
	 */
	EClass getProperties();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspecxml.IProperties#getProperty
	 * <em>Property</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Property</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IProperties#getProperty()
	 * @see #getProperties()
	 * @generated
	 */
	EReference getProperties_Property();

	/**
	 * Returns the meta object for class '
	 * {@link org.eclipse.buckminster.cspecxml.IRemove <em>Remove</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Remove</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IRemove
	 * @generated
	 */
	EClass getRemove();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.cspecxml.IRemove#getName <em>Name</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IRemove#getName()
	 * @see #getRemove()
	 * @generated
	 */
	EAttribute getRemove_Name();

	/**
	 * Returns the meta object for class '
	 * {@link org.eclipse.buckminster.cspecxml.IRemovePath <em>Remove Path</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Remove Path</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IRemovePath
	 * @generated
	 */
	EClass getRemovePath();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.cspecxml.IRemovePath#getPath
	 * <em>Path</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Path</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IRemovePath#getPath()
	 * @see #getRemovePath()
	 * @generated
	 */
	EAttribute getRemovePath_Path();

	/**
	 * Returns the meta object for class '
	 * {@link org.eclipse.buckminster.cspecxml.IRemoveProperty
	 * <em>Remove Property</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Remove Property</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IRemoveProperty
	 * @generated
	 */
	EClass getRemoveProperty();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.cspecxml.IRemoveProperty#getKey
	 * <em>Key</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IRemoveProperty#getKey()
	 * @see #getRemoveProperty()
	 * @generated
	 */
	EAttribute getRemoveProperty_Key();

	/**
	 * Returns the meta object for class '
	 * {@link org.eclipse.buckminster.cspecxml.IRename <em>Rename</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Rename</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IRename
	 * @generated
	 */
	EClass getRename();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.cspecxml.IRename#getNewName
	 * <em>New Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>New Name</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IRename#getNewName()
	 * @see #getRename()
	 * @generated
	 */
	EAttribute getRename_NewName();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.cspecxml.IRename#getOldName
	 * <em>Old Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Old Name</em>'.
	 * @see org.eclipse.buckminster.cspecxml.IRename#getOldName()
	 * @see #getRename()
	 * @generated
	 */
	EAttribute getRename_OldName();

	/**
	 * Returns the meta object for enum '
	 * {@link org.eclipse.buckminster.cspecxml.UpToDatePolicy
	 * <em>Up To Date Policy</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for enum '<em>Up To Date Policy</em>'.
	 * @see org.eclipse.buckminster.cspecxml.UpToDatePolicy
	 * @generated
	 */
	EEnum getUpToDatePolicy();

	/**
	 * Returns the meta object for data type '
	 * {@link org.eclipse.buckminster.cspecxml.UpToDatePolicy
	 * <em>Up To Date Policy Object</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>Up To Date Policy Object</em>
	 *         '.
	 * @see org.eclipse.buckminster.cspecxml.UpToDatePolicy
	 * @model instanceClass="org.eclipse.buckminster.cspecxml.UpToDatePolicy"
	 *        extendedMetaData
	 *        ="name='UpToDatePolicy:Object' baseType='UpToDatePolicy'"
	 * @generated
	 */
	EDataType getUpToDatePolicyObject();

} // ICSpecXMLPackage
