/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspec;

import org.eclipse.buckminster.model.common.CommonPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * 
 * @see org.eclipse.buckminster.cspec.CspecFactory
 * @model kind="package"
 * @generated
 */
public interface CspecPackage extends EPackage
{
	/**
	 * <!-- begin-user-doc --> Defines literals for the meta objects that represent
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
	interface Literals
	{
		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.cspec.impl.CSpecImpl <em>CSpec</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.cspec.impl.CSpecImpl
		 * @see org.eclipse.buckminster.cspec.impl.CspecPackageImpl#getCSpec()
		 * @generated
		 */
		EClass CSPEC = eINSTANCE.getCSpec();

		/**
		 * The meta object literal for the '<em><b>Dependencies</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference CSPEC__DEPENDENCIES = eINSTANCE.getCSpec_Dependencies();

		/**
		 * The meta object literal for the '<em><b>Generators</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference CSPEC__GENERATORS = eINSTANCE.getCSpec_Generators();

		/**
		 * The meta object literal for the '<em><b>Attributes</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference CSPEC__ATTRIBUTES = eINSTANCE.getCSpec_Attributes();

		/**
		 * The meta object literal for the '<em><b>Documentation</b></em>' containment reference feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference CSPEC__DOCUMENTATION = eINSTANCE.getCSpec_Documentation();

		/**
		 * The meta object literal for the '<em><b>Short Desc</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute CSPEC__SHORT_DESC = eINSTANCE.getCSpec_ShortDesc();

		/**
		 * The meta object literal for the '<em><b>Filter</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute CSPEC__FILTER = eINSTANCE.getCSpec_Filter();

		/**
		 * The meta object literal for the '<em><b>Project Info</b></em>' attribute feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute CSPEC__PROJECT_INFO = eINSTANCE.getCSpec_ProjectInfo();

		/**
		 * The meta object literal for the '<em><b>Self</b></em>' reference feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference CSPEC__SELF = eINSTANCE.getCSpec_Self();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.cspec.impl.AttributeImpl <em>Attribute</em>}'
		 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.cspec.impl.AttributeImpl
		 * @see org.eclipse.buckminster.cspec.impl.CspecPackageImpl#getAttribute()
		 * @generated
		 */
		EClass ATTRIBUTE = eINSTANCE.getAttribute();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ATTRIBUTE__NAME = eINSTANCE.getAttribute_Name();

		/**
		 * The meta object literal for the '<em><b>Public</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ATTRIBUTE__PUBLIC = eINSTANCE.getAttribute_Public();

		/**
		 * The meta object literal for the '<em><b>Filter</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ATTRIBUTE__FILTER = eINSTANCE.getAttribute_Filter();

		/**
		 * The meta object literal for the '<em><b>Cspec</b></em>' container reference feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ATTRIBUTE__CSPEC = eINSTANCE.getAttribute_Cspec();

		/**
		 * The meta object literal for the '<em><b>Documentation</b></em>' containment reference feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ATTRIBUTE__DOCUMENTATION = eINSTANCE.getAttribute_Documentation();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.cspec.impl.GroupImpl <em>Group</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.cspec.impl.GroupImpl
		 * @see org.eclipse.buckminster.cspec.impl.CspecPackageImpl#getGroup()
		 * @generated
		 */
		EClass GROUP = eINSTANCE.getGroup();

		/**
		 * The meta object literal for the '<em><b>Prerequisites</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference GROUP__PREREQUISITES = eINSTANCE.getGroup_Prerequisites();

		/**
		 * The meta object literal for the '<em><b>Rebase</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute GROUP__REBASE = eINSTANCE.getGroup_Rebase();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.cspec.impl.PrerequisiteImpl
		 * <em>Prerequisite</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.cspec.impl.PrerequisiteImpl
		 * @see org.eclipse.buckminster.cspec.impl.CspecPackageImpl#getPrerequisite()
		 * @generated
		 */
		EClass PREREQUISITE = eINSTANCE.getPrerequisite();

		/**
		 * The meta object literal for the '<em><b>Component</b></em>' reference feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference PREREQUISITE__COMPONENT = eINSTANCE.getPrerequisite_Component();

		/**
		 * The meta object literal for the '<em><b>Attribute</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PREREQUISITE__ATTRIBUTE = eINSTANCE.getPrerequisite_Attribute();

		/**
		 * The meta object literal for the '<em><b>Alias</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PREREQUISITE__ALIAS = eINSTANCE.getPrerequisite_Alias();

		/**
		 * The meta object literal for the '<em><b>Contributor</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PREREQUISITE__CONTRIBUTOR = eINSTANCE.getPrerequisite_Contributor();

		/**
		 * The meta object literal for the '<em><b>Optional</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PREREQUISITE__OPTIONAL = eINSTANCE.getPrerequisite_Optional();

		/**
		 * The meta object literal for the '<em><b>Filter</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PREREQUISITE__FILTER = eINSTANCE.getPrerequisite_Filter();

		/**
		 * The meta object literal for the '<em><b>Include Pattern</b></em>' attribute feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PREREQUISITE__INCLUDE_PATTERN = eINSTANCE.getPrerequisite_IncludePattern();

		/**
		 * The meta object literal for the '<em><b>Exclude Pattern</b></em>' attribute feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PREREQUISITE__EXCLUDE_PATTERN = eINSTANCE.getPrerequisite_ExcludePattern();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.cspec.impl.ActionImpl <em>Action</em>}'
		 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.cspec.impl.ActionImpl
		 * @see org.eclipse.buckminster.cspec.impl.CspecPackageImpl#getAction()
		 * @generated
		 */
		EClass ACTION = eINSTANCE.getAction();

		/**
		 * The meta object literal for the '<em><b>Properties</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ACTION__PROPERTIES = eINSTANCE.getAction_Properties();

		/**
		 * The meta object literal for the '<em><b>Actor Properties</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ACTION__ACTOR_PROPERTIES = eINSTANCE.getAction_ActorProperties();

		/**
		 * The meta object literal for the '<em><b>Product</b></em>' containment reference feature. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ACTION__PRODUCT = eINSTANCE.getAction_Product();

		/**
		 * The meta object literal for the '<em><b>Products</b></em>' reference list feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ACTION__PRODUCTS = eINSTANCE.getAction_Products();

		/**
		 * The meta object literal for the '<em><b>Prerequisites Rebase</b></em>' containment reference feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ACTION__PREREQUISITES_REBASE = eINSTANCE.getAction_PrerequisitesRebase();

		/**
		 * The meta object literal for the '<em><b>Prerequisites Alias</b></em>' attribute feature. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ACTION__PREREQUISITES_ALIAS = eINSTANCE.getAction_PrerequisitesAlias();

		/**
		 * The meta object literal for the '<em><b>Product Alias</b></em>' attribute feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ACTION__PRODUCT_ALIAS = eINSTANCE.getAction_ProductAlias();

		/**
		 * The meta object literal for the '<em><b>Up To Date Policy</b></em>' attribute feature. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ACTION__UP_TO_DATE_POLICY = eINSTANCE.getAction_UpToDatePolicy();

		/**
		 * The meta object literal for the '<em><b>Product File Count</b></em>' attribute feature. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ACTION__PRODUCT_FILE_COUNT = eINSTANCE.getAction_ProductFileCount();

		/**
		 * The meta object literal for the '<em><b>Pattern</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ACTION__PATTERN = eINSTANCE.getAction_Pattern();

		/**
		 * The meta object literal for the '<em><b>Replacement</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ACTION__REPLACEMENT = eINSTANCE.getAction_Replacement();

		/**
		 * The meta object literal for the '<em><b>Actor</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ACTION__ACTOR = eINSTANCE.getAction_Actor();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.cspec.impl.ArtifactImpl <em>Artifact</em>}'
		 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.cspec.impl.ArtifactImpl
		 * @see org.eclipse.buckminster.cspec.impl.CspecPackageImpl#getArtifact()
		 * @generated
		 */
		EClass ARTIFACT = eINSTANCE.getArtifact();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.cspec.impl.ActionAttributeImpl
		 * <em>Action Attribute</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.cspec.impl.ActionAttributeImpl
		 * @see org.eclipse.buckminster.cspec.impl.CspecPackageImpl#getActionAttribute()
		 * @generated
		 */
		EClass ACTION_ATTRIBUTE = eINSTANCE.getActionAttribute();

		/**
		 * The meta object literal for the '<em><b>Alias</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ACTION_ATTRIBUTE__ALIAS = eINSTANCE.getActionAttribute_Alias();

		/**
		 * The meta object literal for the '<em><b>Action</b></em>' reference feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ACTION_ATTRIBUTE__ACTION = eINSTANCE.getActionAttribute_Action();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.cspec.impl.PathGroupImpl <em>Path Group</em>}
		 * ' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.cspec.impl.PathGroupImpl
		 * @see org.eclipse.buckminster.cspec.impl.CspecPackageImpl#getPathGroup()
		 * @generated
		 */
		EClass PATH_GROUP = eINSTANCE.getPathGroup();

		/**
		 * The meta object literal for the '<em><b>Paths</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PATH_GROUP__PATHS = eINSTANCE.getPathGroup_Paths();

		/**
		 * The meta object literal for the '<em><b>Base</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PATH_GROUP__BASE = eINSTANCE.getPathGroup_Base();

		/**
		 * The meta object literal for the '{@link org.eclipse.core.runtime.IPath <em>IPath</em>}' class. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.core.runtime.IPath
		 * @see org.eclipse.buckminster.cspec.impl.CspecPackageImpl#getIPath()
		 * @generated
		 */
		EDataType IPATH = eINSTANCE.getIPath();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.cspec.impl.GeneratorImpl <em>Generator</em>}'
		 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.cspec.impl.GeneratorImpl
		 * @see org.eclipse.buckminster.cspec.impl.CspecPackageImpl#getGenerator()
		 * @generated
		 */
		EClass GENERATOR = eINSTANCE.getGenerator();

		/**
		 * The meta object literal for the '<em><b>Attribute</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute GENERATOR__ATTRIBUTE = eINSTANCE.getGenerator_Attribute();

		/**
		 * The meta object literal for the '<em><b>Component</b></em>' reference feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference GENERATOR__COMPONENT = eINSTANCE.getGenerator_Component();

		/**
		 * The meta object literal for the '<em><b>Generates</b></em>' containment reference feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference GENERATOR__GENERATES = eINSTANCE.getGenerator_Generates();

		/**
		 * The meta object literal for the '<em><b>Cspec</b></em>' container reference feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference GENERATOR__CSPEC = eINSTANCE.getGenerator_Cspec();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.cspec.impl.AlterAttributeImpl
		 * <em>Alter Attribute</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.cspec.impl.AlterAttributeImpl
		 * @see org.eclipse.buckminster.cspec.impl.CspecPackageImpl#getAlterAttribute()
		 * @generated
		 */
		EClass ALTER_ATTRIBUTE = eINSTANCE.getAlterAttribute();

		/**
		 * The meta object literal for the '<em><b>Cspecext</b></em>' container reference feature. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ALTER_ATTRIBUTE__CSPECEXT = eINSTANCE.getAlterAttribute_Cspecext();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.cspec.impl.AlterArtifactImpl
		 * <em>Alter Artifact</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.cspec.impl.AlterArtifactImpl
		 * @see org.eclipse.buckminster.cspec.impl.CspecPackageImpl#getAlterArtifact()
		 * @generated
		 */
		EClass ALTER_ARTIFACT = eINSTANCE.getAlterArtifact();

		/**
		 * The meta object literal for the '<em><b>Remove Paths</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ALTER_ARTIFACT__REMOVE_PATHS = eINSTANCE.getAlterArtifact_RemovePaths();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.cspec.impl.AlterGroupImpl
		 * <em>Alter Group</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.cspec.impl.AlterGroupImpl
		 * @see org.eclipse.buckminster.cspec.impl.CspecPackageImpl#getAlterGroup()
		 * @generated
		 */
		EClass ALTER_GROUP = eINSTANCE.getAlterGroup();

		/**
		 * The meta object literal for the '<em><b>Replace Prerequisites</b></em>' containment reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ALTER_GROUP__REPLACE_PREREQUISITES = eINSTANCE.getAlterGroup_ReplacePrerequisites();

		/**
		 * The meta object literal for the '<em><b>Remove Prerequisites</b></em>' containment reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ALTER_GROUP__REMOVE_PREREQUISITES = eINSTANCE.getAlterGroup_RemovePrerequisites();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.cspec.impl.AlterActionImpl
		 * <em>Alter Action</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.cspec.impl.AlterActionImpl
		 * @see org.eclipse.buckminster.cspec.impl.CspecPackageImpl#getAlterAction()
		 * @generated
		 */
		EClass ALTER_ACTION = eINSTANCE.getAlterAction();

		/**
		 * The meta object literal for the '<em><b>Replace Properties</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ALTER_ACTION__REPLACE_PROPERTIES = eINSTANCE.getAlterAction_ReplaceProperties();

		/**
		 * The meta object literal for the '<em><b>Replace Actor Properties</b></em>' containment reference list
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ALTER_ACTION__REPLACE_ACTOR_PROPERTIES = eINSTANCE.getAlterAction_ReplaceActorProperties();

		/**
		 * The meta object literal for the '<em><b>Remove Properties</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ALTER_ACTION__REMOVE_PROPERTIES = eINSTANCE.getAlterAction_RemoveProperties();

		/**
		 * The meta object literal for the '<em><b>Remove Actor Properties</b></em>' containment reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ALTER_ACTION__REMOVE_ACTOR_PROPERTIES = eINSTANCE.getAlterAction_RemoveActorProperties();

		/**
		 * The meta object literal for the '<em><b>Remove Products</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ALTER_ACTION__REMOVE_PRODUCTS = eINSTANCE.getAlterAction_RemoveProducts();

		/**
		 * The meta object literal for the '<em><b>Remove Paths</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ALTER_ACTION__REMOVE_PATHS = eINSTANCE.getAlterAction_RemovePaths();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.cspec.impl.RenameImpl <em>Rename</em>}'
		 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.cspec.impl.RenameImpl
		 * @see org.eclipse.buckminster.cspec.impl.CspecPackageImpl#getRename()
		 * @generated
		 */
		EClass RENAME = eINSTANCE.getRename();

		/**
		 * The meta object literal for the '<em><b>Old Name</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute RENAME__OLD_NAME = eINSTANCE.getRename_OldName();

		/**
		 * The meta object literal for the '<em><b>New Name</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute RENAME__NEW_NAME = eINSTANCE.getRename_NewName();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.cspec.impl.RemoveImpl <em>Remove</em>}'
		 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.cspec.impl.RemoveImpl
		 * @see org.eclipse.buckminster.cspec.impl.CspecPackageImpl#getRemove()
		 * @generated
		 */
		EClass REMOVE = eINSTANCE.getRemove();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute REMOVE__NAME = eINSTANCE.getRemove_Name();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.cspec.impl.CSpecExtensionImpl
		 * <em>CSpec Extension</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.cspec.impl.CSpecExtensionImpl
		 * @see org.eclipse.buckminster.cspec.impl.CspecPackageImpl#getCSpecExtension()
		 * @generated
		 */
		EClass CSPEC_EXTENSION = eINSTANCE.getCSpecExtension();

		/**
		 * The meta object literal for the '<em><b>Attribute Alterations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference CSPEC_EXTENSION__ATTRIBUTE_ALTERATIONS = eINSTANCE.getCSpecExtension_AttributeAlterations();

		/**
		 * The meta object literal for the '<em><b>Rename Attributes</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference CSPEC_EXTENSION__RENAME_ATTRIBUTES = eINSTANCE.getCSpecExtension_RenameAttributes();

		/**
		 * The meta object literal for the '<em><b>Remove Dependencies</b></em>' containment reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference CSPEC_EXTENSION__REMOVE_DEPENDENCIES = eINSTANCE.getCSpecExtension_RemoveDependencies();

		/**
		 * The meta object literal for the '<em><b>Remove Generators</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference CSPEC_EXTENSION__REMOVE_GENERATORS = eINSTANCE.getCSpecExtension_RemoveGenerators();

		/**
		 * The meta object literal for the '<em><b>Replace Generators</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference CSPEC_EXTENSION__REPLACE_GENERATORS = eINSTANCE.getCSpecExtension_ReplaceGenerators();

		/**
		 * The meta object literal for the '<em><b>Replace Dependencies</b></em>' containment reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference CSPEC_EXTENSION__REPLACE_DEPENDENCIES = eINSTANCE.getCSpecExtension_ReplaceDependencies();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.cspec.IContext <em>IContext</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.cspec.IContext
		 * @see org.eclipse.buckminster.cspec.impl.CspecPackageImpl#getIContext()
		 * @generated
		 */
		EClass ICONTEXT = eINSTANCE.getIContext();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.cspec.impl.SelfArtifactImpl
		 * <em>Self Artifact</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.cspec.impl.SelfArtifactImpl
		 * @see org.eclipse.buckminster.cspec.impl.CspecPackageImpl#getSelfArtifact()
		 * @generated
		 */
		EClass SELF_ARTIFACT = eINSTANCE.getSelfArtifact();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.cspec.UpToDatePolicy
		 * <em>Up To Date Policy</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.cspec.UpToDatePolicy
		 * @see org.eclipse.buckminster.cspec.impl.CspecPackageImpl#getUpToDatePolicy()
		 * @generated
		 */
		EEnum UP_TO_DATE_POLICY = eINSTANCE.getUpToDatePolicy();

	}

	/**
	 * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNAME = "cspec";

	/**
	 * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/buckminster/CSpec-2.0";

	/**
	 * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_PREFIX = "cs";

	/**
	 * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	CspecPackage eINSTANCE = org.eclipse.buckminster.cspec.impl.CspecPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.cspec.impl.CSpecImpl <em>CSpec</em>}' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.cspec.impl.CSpecImpl
	 * @see org.eclipse.buckminster.cspec.impl.CspecPackageImpl#getCSpec()
	 * @generated
	 */
	int CSPEC = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CSPEC__ID = CommonPackage.COMPONENT_IDENTIFIER__ID;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CSPEC__VERSION = CommonPackage.COMPONENT_IDENTIFIER__VERSION;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CSPEC__TYPE = CommonPackage.COMPONENT_IDENTIFIER__TYPE;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CSPEC__DEPENDENCIES = CommonPackage.COMPONENT_IDENTIFIER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Generators</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CSPEC__GENERATORS = CommonPackage.COMPONENT_IDENTIFIER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CSPEC__ATTRIBUTES = CommonPackage.COMPONENT_IDENTIFIER_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CSPEC__DOCUMENTATION = CommonPackage.COMPONENT_IDENTIFIER_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Short Desc</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CSPEC__SHORT_DESC = CommonPackage.COMPONENT_IDENTIFIER_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Filter</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CSPEC__FILTER = CommonPackage.COMPONENT_IDENTIFIER_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Project Info</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CSPEC__PROJECT_INFO = CommonPackage.COMPONENT_IDENTIFIER_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Self</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CSPEC__SELF = CommonPackage.COMPONENT_IDENTIFIER_FEATURE_COUNT + 7;

	/**
	 * The number of structural features of the '<em>CSpec</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CSPEC_FEATURE_COUNT = CommonPackage.COMPONENT_IDENTIFIER_FEATURE_COUNT + 8;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.cspec.impl.AttributeImpl <em>Attribute</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.cspec.impl.AttributeImpl
	 * @see org.eclipse.buckminster.cspec.impl.CspecPackageImpl#getAttribute()
	 * @generated
	 */
	int ATTRIBUTE = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Public</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__PUBLIC = 1;

	/**
	 * The feature id for the '<em><b>Filter</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__FILTER = 2;

	/**
	 * The feature id for the '<em><b>Cspec</b></em>' container reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__CSPEC = 3;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__DOCUMENTATION = 4;

	/**
	 * The number of structural features of the '<em>Attribute</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.cspec.impl.GroupImpl <em>Group</em>}' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.cspec.impl.GroupImpl
	 * @see org.eclipse.buckminster.cspec.impl.CspecPackageImpl#getGroup()
	 * @generated
	 */
	int GROUP = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int GROUP__NAME = ATTRIBUTE__NAME;

	/**
	 * The feature id for the '<em><b>Public</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int GROUP__PUBLIC = ATTRIBUTE__PUBLIC;

	/**
	 * The feature id for the '<em><b>Filter</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int GROUP__FILTER = ATTRIBUTE__FILTER;

	/**
	 * The feature id for the '<em><b>Cspec</b></em>' container reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int GROUP__CSPEC = ATTRIBUTE__CSPEC;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int GROUP__DOCUMENTATION = ATTRIBUTE__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Prerequisites</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int GROUP__PREREQUISITES = ATTRIBUTE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Rebase</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int GROUP__REBASE = ATTRIBUTE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Group</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int GROUP_FEATURE_COUNT = ATTRIBUTE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.cspec.impl.PrerequisiteImpl <em>Prerequisite</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.cspec.impl.PrerequisiteImpl
	 * @see org.eclipse.buckminster.cspec.impl.CspecPackageImpl#getPrerequisite()
	 * @generated
	 */
	int PREREQUISITE = 3;

	/**
	 * The feature id for the '<em><b>Component</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PREREQUISITE__COMPONENT = 0;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PREREQUISITE__ATTRIBUTE = 1;

	/**
	 * The feature id for the '<em><b>Alias</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PREREQUISITE__ALIAS = 2;

	/**
	 * The feature id for the '<em><b>Contributor</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PREREQUISITE__CONTRIBUTOR = 3;

	/**
	 * The feature id for the '<em><b>Optional</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PREREQUISITE__OPTIONAL = 4;

	/**
	 * The feature id for the '<em><b>Filter</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PREREQUISITE__FILTER = 5;

	/**
	 * The feature id for the '<em><b>Include Pattern</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PREREQUISITE__INCLUDE_PATTERN = 6;

	/**
	 * The feature id for the '<em><b>Exclude Pattern</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PREREQUISITE__EXCLUDE_PATTERN = 7;

	/**
	 * The number of structural features of the '<em>Prerequisite</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PREREQUISITE_FEATURE_COUNT = 8;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.cspec.impl.ActionImpl <em>Action</em>}' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.cspec.impl.ActionImpl
	 * @see org.eclipse.buckminster.cspec.impl.CspecPackageImpl#getAction()
	 * @generated
	 */
	int ACTION = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ACTION__NAME = GROUP__NAME;

	/**
	 * The feature id for the '<em><b>Public</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ACTION__PUBLIC = GROUP__PUBLIC;

	/**
	 * The feature id for the '<em><b>Filter</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ACTION__FILTER = GROUP__FILTER;

	/**
	 * The feature id for the '<em><b>Cspec</b></em>' container reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ACTION__CSPEC = GROUP__CSPEC;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ACTION__DOCUMENTATION = GROUP__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Prerequisites</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ACTION__PREREQUISITES = GROUP__PREREQUISITES;

	/**
	 * The feature id for the '<em><b>Rebase</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ACTION__REBASE = GROUP__REBASE;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ACTION__PROPERTIES = GROUP_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Actor Properties</b></em>' containment reference list. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ACTION__ACTOR_PROPERTIES = GROUP_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Actor</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ACTION__ACTOR = GROUP_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Product</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ACTION__PRODUCT = GROUP_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Products</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ACTION__PRODUCTS = GROUP_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Prerequisites Alias</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ACTION__PREREQUISITES_ALIAS = GROUP_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Prerequisites Rebase</b></em>' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ACTION__PREREQUISITES_REBASE = GROUP_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Product Alias</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ACTION__PRODUCT_ALIAS = GROUP_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Up To Date Policy</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ACTION__UP_TO_DATE_POLICY = GROUP_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Product File Count</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ACTION__PRODUCT_FILE_COUNT = GROUP_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Pattern</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ACTION__PATTERN = GROUP_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Replacement</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ACTION__REPLACEMENT = GROUP_FEATURE_COUNT + 11;

	/**
	 * The number of structural features of the '<em>Action</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ACTION_FEATURE_COUNT = GROUP_FEATURE_COUNT + 12;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.cspec.impl.ArtifactImpl <em>Artifact</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.cspec.impl.ArtifactImpl
	 * @see org.eclipse.buckminster.cspec.impl.CspecPackageImpl#getArtifact()
	 * @generated
	 */
	int ARTIFACT = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__NAME = ATTRIBUTE__NAME;

	/**
	 * The feature id for the '<em><b>Public</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__PUBLIC = ATTRIBUTE__PUBLIC;

	/**
	 * The feature id for the '<em><b>Filter</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__FILTER = ATTRIBUTE__FILTER;

	/**
	 * The feature id for the '<em><b>Cspec</b></em>' container reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__CSPEC = ATTRIBUTE__CSPEC;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__DOCUMENTATION = ATTRIBUTE__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Base</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__BASE = ATTRIBUTE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Paths</b></em>' attribute list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__PATHS = ATTRIBUTE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Artifact</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_FEATURE_COUNT = ATTRIBUTE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.cspec.impl.ActionAttributeImpl
	 * <em>Action Attribute</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.cspec.impl.ActionAttributeImpl
	 * @see org.eclipse.buckminster.cspec.impl.CspecPackageImpl#getActionAttribute()
	 * @generated
	 */
	int ACTION_ATTRIBUTE = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ACTION_ATTRIBUTE__NAME = ARTIFACT__NAME;

	/**
	 * The feature id for the '<em><b>Public</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ACTION_ATTRIBUTE__PUBLIC = ARTIFACT__PUBLIC;

	/**
	 * The feature id for the '<em><b>Filter</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ACTION_ATTRIBUTE__FILTER = ARTIFACT__FILTER;

	/**
	 * The feature id for the '<em><b>Cspec</b></em>' container reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ACTION_ATTRIBUTE__CSPEC = ARTIFACT__CSPEC;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ACTION_ATTRIBUTE__DOCUMENTATION = ARTIFACT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Base</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ACTION_ATTRIBUTE__BASE = ARTIFACT__BASE;

	/**
	 * The feature id for the '<em><b>Paths</b></em>' attribute list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ACTION_ATTRIBUTE__PATHS = ARTIFACT__PATHS;

	/**
	 * The feature id for the '<em><b>Alias</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ACTION_ATTRIBUTE__ALIAS = ARTIFACT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Action</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ACTION_ATTRIBUTE__ACTION = ARTIFACT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Action Attribute</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ACTION_ATTRIBUTE_FEATURE_COUNT = ARTIFACT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.cspec.impl.PathGroupImpl <em>Path Group</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.cspec.impl.PathGroupImpl
	 * @see org.eclipse.buckminster.cspec.impl.CspecPackageImpl#getPathGroup()
	 * @generated
	 */
	int PATH_GROUP = 7;

	/**
	 * The feature id for the '<em><b>Base</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PATH_GROUP__BASE = 0;

	/**
	 * The feature id for the '<em><b>Paths</b></em>' attribute list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PATH_GROUP__PATHS = 1;

	/**
	 * The number of structural features of the '<em>Path Group</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PATH_GROUP_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '<em>IPath</em>' data type. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.core.runtime.IPath
	 * @see org.eclipse.buckminster.cspec.impl.CspecPackageImpl#getIPath()
	 * @generated
	 */
	int IPATH = 19;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.cspec.impl.GeneratorImpl <em>Generator</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.cspec.impl.GeneratorImpl
	 * @see org.eclipse.buckminster.cspec.impl.CspecPackageImpl#getGenerator()
	 * @generated
	 */
	int GENERATOR = 8;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int GENERATOR__ATTRIBUTE = 0;

	/**
	 * The feature id for the '<em><b>Component</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int GENERATOR__COMPONENT = 1;

	/**
	 * The feature id for the '<em><b>Generates</b></em>' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int GENERATOR__GENERATES = 2;

	/**
	 * The feature id for the '<em><b>Cspec</b></em>' container reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int GENERATOR__CSPEC = 3;

	/**
	 * The number of structural features of the '<em>Generator</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int GENERATOR_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.cspec.impl.AlterAttributeImpl
	 * <em>Alter Attribute</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.cspec.impl.AlterAttributeImpl
	 * @see org.eclipse.buckminster.cspec.impl.CspecPackageImpl#getAlterAttribute()
	 * @generated
	 */
	int ALTER_ATTRIBUTE = 9;

	/**
	 * The feature id for the '<em><b>Cspecext</b></em>' container reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ATTRIBUTE__CSPECEXT = 0;

	/**
	 * The number of structural features of the '<em>Alter Attribute</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ATTRIBUTE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.cspec.impl.AlterArtifactImpl <em>Alter Artifact</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.cspec.impl.AlterArtifactImpl
	 * @see org.eclipse.buckminster.cspec.impl.CspecPackageImpl#getAlterArtifact()
	 * @generated
	 */
	int ALTER_ARTIFACT = 10;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ARTIFACT__NAME = ARTIFACT__NAME;

	/**
	 * The feature id for the '<em><b>Public</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ARTIFACT__PUBLIC = ARTIFACT__PUBLIC;

	/**
	 * The feature id for the '<em><b>Filter</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ARTIFACT__FILTER = ARTIFACT__FILTER;

	/**
	 * The feature id for the '<em><b>Cspec</b></em>' container reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ARTIFACT__CSPEC = ARTIFACT__CSPEC;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ARTIFACT__DOCUMENTATION = ARTIFACT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Base</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ARTIFACT__BASE = ARTIFACT__BASE;

	/**
	 * The feature id for the '<em><b>Paths</b></em>' attribute list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ARTIFACT__PATHS = ARTIFACT__PATHS;

	/**
	 * The feature id for the '<em><b>Cspecext</b></em>' container reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ARTIFACT__CSPECEXT = ARTIFACT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Remove Paths</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ARTIFACT__REMOVE_PATHS = ARTIFACT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Alter Artifact</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ARTIFACT_FEATURE_COUNT = ARTIFACT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.cspec.impl.AlterGroupImpl <em>Alter Group</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.cspec.impl.AlterGroupImpl
	 * @see org.eclipse.buckminster.cspec.impl.CspecPackageImpl#getAlterGroup()
	 * @generated
	 */
	int ALTER_GROUP = 11;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_GROUP__NAME = GROUP__NAME;

	/**
	 * The feature id for the '<em><b>Public</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_GROUP__PUBLIC = GROUP__PUBLIC;

	/**
	 * The feature id for the '<em><b>Filter</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_GROUP__FILTER = GROUP__FILTER;

	/**
	 * The feature id for the '<em><b>Cspec</b></em>' container reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_GROUP__CSPEC = GROUP__CSPEC;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_GROUP__DOCUMENTATION = GROUP__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Prerequisites</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_GROUP__PREREQUISITES = GROUP__PREREQUISITES;

	/**
	 * The feature id for the '<em><b>Rebase</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_GROUP__REBASE = GROUP__REBASE;

	/**
	 * The feature id for the '<em><b>Cspecext</b></em>' container reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_GROUP__CSPECEXT = GROUP_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Replace Prerequisites</b></em>' containment reference list. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_GROUP__REPLACE_PREREQUISITES = GROUP_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Remove Prerequisites</b></em>' containment reference list. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_GROUP__REMOVE_PREREQUISITES = GROUP_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Alter Group</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_GROUP_FEATURE_COUNT = GROUP_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.cspec.impl.AlterActionImpl <em>Alter Action</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.cspec.impl.AlterActionImpl
	 * @see org.eclipse.buckminster.cspec.impl.CspecPackageImpl#getAlterAction()
	 * @generated
	 */
	int ALTER_ACTION = 12;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ACTION__NAME = ACTION__NAME;

	/**
	 * The feature id for the '<em><b>Public</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ACTION__PUBLIC = ACTION__PUBLIC;

	/**
	 * The feature id for the '<em><b>Filter</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ACTION__FILTER = ACTION__FILTER;

	/**
	 * The feature id for the '<em><b>Cspec</b></em>' container reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ACTION__CSPEC = ACTION__CSPEC;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ACTION__DOCUMENTATION = ACTION__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Prerequisites</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ACTION__PREREQUISITES = ACTION__PREREQUISITES;

	/**
	 * The feature id for the '<em><b>Rebase</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ACTION__REBASE = ACTION__REBASE;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ACTION__PROPERTIES = ACTION__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Actor Properties</b></em>' containment reference list. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ACTION__ACTOR_PROPERTIES = ACTION__ACTOR_PROPERTIES;

	/**
	 * The feature id for the '<em><b>Actor</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ACTION__ACTOR = ACTION__ACTOR;

	/**
	 * The feature id for the '<em><b>Product</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ACTION__PRODUCT = ACTION__PRODUCT;

	/**
	 * The feature id for the '<em><b>Products</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ACTION__PRODUCTS = ACTION__PRODUCTS;

	/**
	 * The feature id for the '<em><b>Prerequisites Alias</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ACTION__PREREQUISITES_ALIAS = ACTION__PREREQUISITES_ALIAS;

	/**
	 * The feature id for the '<em><b>Prerequisites Rebase</b></em>' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ACTION__PREREQUISITES_REBASE = ACTION__PREREQUISITES_REBASE;

	/**
	 * The feature id for the '<em><b>Product Alias</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ACTION__PRODUCT_ALIAS = ACTION__PRODUCT_ALIAS;

	/**
	 * The feature id for the '<em><b>Up To Date Policy</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ACTION__UP_TO_DATE_POLICY = ACTION__UP_TO_DATE_POLICY;

	/**
	 * The feature id for the '<em><b>Product File Count</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ACTION__PRODUCT_FILE_COUNT = ACTION__PRODUCT_FILE_COUNT;

	/**
	 * The feature id for the '<em><b>Pattern</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ACTION__PATTERN = ACTION__PATTERN;

	/**
	 * The feature id for the '<em><b>Replacement</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ACTION__REPLACEMENT = ACTION__REPLACEMENT;

	/**
	 * The feature id for the '<em><b>Cspecext</b></em>' container reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ACTION__CSPECEXT = ACTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Replace Prerequisites</b></em>' containment reference list. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ACTION__REPLACE_PREREQUISITES = ACTION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Remove Prerequisites</b></em>' containment reference list. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ACTION__REMOVE_PREREQUISITES = ACTION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Replace Properties</b></em>' containment reference list. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ACTION__REPLACE_PROPERTIES = ACTION_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Replace Actor Properties</b></em>' containment reference list. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ACTION__REPLACE_ACTOR_PROPERTIES = ACTION_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Remove Properties</b></em>' containment reference list. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ACTION__REMOVE_PROPERTIES = ACTION_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Remove Actor Properties</b></em>' containment reference list. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ACTION__REMOVE_ACTOR_PROPERTIES = ACTION_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Remove Products</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ACTION__REMOVE_PRODUCTS = ACTION_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Remove Paths</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ACTION__REMOVE_PATHS = ACTION_FEATURE_COUNT + 8;

	/**
	 * The number of structural features of the '<em>Alter Action</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ALTER_ACTION_FEATURE_COUNT = ACTION_FEATURE_COUNT + 9;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.cspec.impl.RenameImpl <em>Rename</em>}' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.cspec.impl.RenameImpl
	 * @see org.eclipse.buckminster.cspec.impl.CspecPackageImpl#getRename()
	 * @generated
	 */
	int RENAME = 13;

	/**
	 * The feature id for the '<em><b>Old Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int RENAME__OLD_NAME = 0;

	/**
	 * The feature id for the '<em><b>New Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int RENAME__NEW_NAME = 1;

	/**
	 * The number of structural features of the '<em>Rename</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int RENAME_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.cspec.impl.RemoveImpl <em>Remove</em>}' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.cspec.impl.RemoveImpl
	 * @see org.eclipse.buckminster.cspec.impl.CspecPackageImpl#getRemove()
	 * @generated
	 */
	int REMOVE = 14;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REMOVE__NAME = 0;

	/**
	 * The number of structural features of the '<em>Remove</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REMOVE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.cspec.impl.CSpecExtensionImpl
	 * <em>CSpec Extension</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.cspec.impl.CSpecExtensionImpl
	 * @see org.eclipse.buckminster.cspec.impl.CspecPackageImpl#getCSpecExtension()
	 * @generated
	 */
	int CSPEC_EXTENSION = 15;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CSPEC_EXTENSION__ID = CSPEC__ID;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CSPEC_EXTENSION__VERSION = CSPEC__VERSION;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CSPEC_EXTENSION__TYPE = CSPEC__TYPE;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CSPEC_EXTENSION__DEPENDENCIES = CSPEC__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Generators</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CSPEC_EXTENSION__GENERATORS = CSPEC__GENERATORS;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CSPEC_EXTENSION__ATTRIBUTES = CSPEC__ATTRIBUTES;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CSPEC_EXTENSION__DOCUMENTATION = CSPEC__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Short Desc</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CSPEC_EXTENSION__SHORT_DESC = CSPEC__SHORT_DESC;

	/**
	 * The feature id for the '<em><b>Filter</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CSPEC_EXTENSION__FILTER = CSPEC__FILTER;

	/**
	 * The feature id for the '<em><b>Project Info</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CSPEC_EXTENSION__PROJECT_INFO = CSPEC__PROJECT_INFO;

	/**
	 * The feature id for the '<em><b>Self</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CSPEC_EXTENSION__SELF = CSPEC__SELF;

	/**
	 * The feature id for the '<em><b>Attribute Alterations</b></em>' containment reference list. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CSPEC_EXTENSION__ATTRIBUTE_ALTERATIONS = CSPEC_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Rename Attributes</b></em>' containment reference list. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CSPEC_EXTENSION__RENAME_ATTRIBUTES = CSPEC_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Remove Dependencies</b></em>' containment reference list. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CSPEC_EXTENSION__REMOVE_DEPENDENCIES = CSPEC_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Remove Generators</b></em>' containment reference list. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CSPEC_EXTENSION__REMOVE_GENERATORS = CSPEC_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Replace Generators</b></em>' containment reference list. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CSPEC_EXTENSION__REPLACE_GENERATORS = CSPEC_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Replace Dependencies</b></em>' containment reference list. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CSPEC_EXTENSION__REPLACE_DEPENDENCIES = CSPEC_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>CSpec Extension</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CSPEC_EXTENSION_FEATURE_COUNT = CSPEC_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.cspec.IContext <em>IContext</em>}' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.cspec.IContext
	 * @see org.eclipse.buckminster.cspec.impl.CspecPackageImpl#getIContext()
	 * @generated
	 */
	int ICONTEXT = 16;

	/**
	 * The number of structural features of the '<em>IContext</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ICONTEXT_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.cspec.impl.SelfArtifactImpl <em>Self Artifact</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.cspec.impl.SelfArtifactImpl
	 * @see org.eclipse.buckminster.cspec.impl.CspecPackageImpl#getSelfArtifact()
	 * @generated
	 */
	int SELF_ARTIFACT = 17;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SELF_ARTIFACT__NAME = ARTIFACT__NAME;

	/**
	 * The feature id for the '<em><b>Public</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SELF_ARTIFACT__PUBLIC = ARTIFACT__PUBLIC;

	/**
	 * The feature id for the '<em><b>Filter</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SELF_ARTIFACT__FILTER = ARTIFACT__FILTER;

	/**
	 * The feature id for the '<em><b>Cspec</b></em>' container reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SELF_ARTIFACT__CSPEC = ARTIFACT__CSPEC;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SELF_ARTIFACT__DOCUMENTATION = ARTIFACT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Base</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SELF_ARTIFACT__BASE = ARTIFACT__BASE;

	/**
	 * The feature id for the '<em><b>Paths</b></em>' attribute list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SELF_ARTIFACT__PATHS = ARTIFACT__PATHS;

	/**
	 * The number of structural features of the '<em>Self Artifact</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SELF_ARTIFACT_FEATURE_COUNT = ARTIFACT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.cspec.UpToDatePolicy <em>Up To Date Policy</em>}'
	 * enum. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.cspec.UpToDatePolicy
	 * @see org.eclipse.buckminster.cspec.impl.CspecPackageImpl#getUpToDatePolicy()
	 * @generated
	 */
	int UP_TO_DATE_POLICY = 18;

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.cspec.Action <em>Action</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Action</em>'.
	 * @see org.eclipse.buckminster.cspec.Action
	 * @generated
	 */
	EClass getAction();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.cspec.Action#getActor <em>Actor</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Actor</em>'.
	 * @see org.eclipse.buckminster.cspec.Action#getActor()
	 * @see #getAction()
	 * @generated
	 */
	EAttribute getAction_Actor();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspec.Action#getActorProperties <em>Actor Properties</em>}'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Actor Properties</em>'.
	 * @see org.eclipse.buckminster.cspec.Action#getActorProperties()
	 * @see #getAction()
	 * @generated
	 */
	EReference getAction_ActorProperties();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.cspec.Action#getPattern
	 * <em>Pattern</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Pattern</em>'.
	 * @see org.eclipse.buckminster.cspec.Action#getPattern()
	 * @see #getAction()
	 * @generated
	 */
	EAttribute getAction_Pattern();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.cspec.Action#getPrerequisitesAlias
	 * <em>Prerequisites Alias</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Prerequisites Alias</em>'.
	 * @see org.eclipse.buckminster.cspec.Action#getPrerequisitesAlias()
	 * @see #getAction()
	 * @generated
	 */
	EAttribute getAction_PrerequisitesAlias();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link org.eclipse.buckminster.cspec.Action#getPrerequisitesRebase <em>Prerequisites Rebase</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Prerequisites Rebase</em>'.
	 * @see org.eclipse.buckminster.cspec.Action#getPrerequisitesRebase()
	 * @see #getAction()
	 * @generated
	 */
	EAttribute getAction_PrerequisitesRebase();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.buckminster.cspec.Action#getProduct
	 * <em>Product</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Product</em>'.
	 * @see org.eclipse.buckminster.cspec.Action#getProduct()
	 * @see #getAction()
	 * @generated
	 */
	EReference getAction_Product();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.cspec.Action#getProductAlias
	 * <em>Product Alias</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Product Alias</em>'.
	 * @see org.eclipse.buckminster.cspec.Action#getProductAlias()
	 * @see #getAction()
	 * @generated
	 */
	EAttribute getAction_ProductAlias();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.cspec.Action#getProductFileCount
	 * <em>Product File Count</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Product File Count</em>'.
	 * @see org.eclipse.buckminster.cspec.Action#getProductFileCount()
	 * @see #getAction()
	 * @generated
	 */
	EAttribute getAction_ProductFileCount();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.buckminster.cspec.Action#getProducts
	 * <em>Products</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference list '<em>Products</em>'.
	 * @see org.eclipse.buckminster.cspec.Action#getProducts()
	 * @see #getAction()
	 * @generated
	 */
	EReference getAction_Products();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspec.Action#getProperties <em>Properties</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Properties</em>'.
	 * @see org.eclipse.buckminster.cspec.Action#getProperties()
	 * @see #getAction()
	 * @generated
	 */
	EReference getAction_Properties();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.cspec.Action#getReplacement
	 * <em>Replacement</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Replacement</em>'.
	 * @see org.eclipse.buckminster.cspec.Action#getReplacement()
	 * @see #getAction()
	 * @generated
	 */
	EAttribute getAction_Replacement();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.cspec.Action#getUpToDatePolicy
	 * <em>Up To Date Policy</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Up To Date Policy</em>'.
	 * @see org.eclipse.buckminster.cspec.Action#getUpToDatePolicy()
	 * @see #getAction()
	 * @generated
	 */
	EAttribute getAction_UpToDatePolicy();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.cspec.ActionAttribute
	 * <em>Action Attribute</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Action Attribute</em>'.
	 * @see org.eclipse.buckminster.cspec.ActionAttribute
	 * @generated
	 */
	EClass getActionAttribute();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.buckminster.cspec.ActionAttribute#getAction
	 * <em>Action</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Action</em>'.
	 * @see org.eclipse.buckminster.cspec.ActionAttribute#getAction()
	 * @see #getActionAttribute()
	 * @generated
	 */
	EReference getActionAttribute_Action();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.cspec.ActionAttribute#getAlias
	 * <em>Alias</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Alias</em>'.
	 * @see org.eclipse.buckminster.cspec.ActionAttribute#getAlias()
	 * @see #getActionAttribute()
	 * @generated
	 */
	EAttribute getActionAttribute_Alias();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.cspec.AlterAction <em>Alter Action</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Alter Action</em>'.
	 * @see org.eclipse.buckminster.cspec.AlterAction
	 * @generated
	 */
	EClass getAlterAction();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspec.AlterAction#getRemoveActorProperties <em>Remove Actor Properties</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Remove Actor Properties</em>'.
	 * @see org.eclipse.buckminster.cspec.AlterAction#getRemoveActorProperties()
	 * @see #getAlterAction()
	 * @generated
	 */
	EReference getAlterAction_RemoveActorProperties();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspec.AlterAction#getRemovePaths <em>Remove Paths</em>}'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Remove Paths</em>'.
	 * @see org.eclipse.buckminster.cspec.AlterAction#getRemovePaths()
	 * @see #getAlterAction()
	 * @generated
	 */
	EReference getAlterAction_RemovePaths();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspec.AlterAction#getRemoveProducts <em>Remove Products</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Remove Products</em>'.
	 * @see org.eclipse.buckminster.cspec.AlterAction#getRemoveProducts()
	 * @see #getAlterAction()
	 * @generated
	 */
	EReference getAlterAction_RemoveProducts();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspec.AlterAction#getRemoveProperties <em>Remove Properties</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Remove Properties</em>'.
	 * @see org.eclipse.buckminster.cspec.AlterAction#getRemoveProperties()
	 * @see #getAlterAction()
	 * @generated
	 */
	EReference getAlterAction_RemoveProperties();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspec.AlterAction#getReplaceActorProperties <em>Replace Actor Properties</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Replace Actor Properties</em>'.
	 * @see org.eclipse.buckminster.cspec.AlterAction#getReplaceActorProperties()
	 * @see #getAlterAction()
	 * @generated
	 */
	EReference getAlterAction_ReplaceActorProperties();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspec.AlterAction#getReplaceProperties <em>Replace Properties</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Replace Properties</em>'.
	 * @see org.eclipse.buckminster.cspec.AlterAction#getReplaceProperties()
	 * @see #getAlterAction()
	 * @generated
	 */
	EReference getAlterAction_ReplaceProperties();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.cspec.AlterArtifact <em>Alter Artifact</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Alter Artifact</em>'.
	 * @see org.eclipse.buckminster.cspec.AlterArtifact
	 * @generated
	 */
	EClass getAlterArtifact();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspec.AlterArtifact#getRemovePaths <em>Remove Paths</em>}'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Remove Paths</em>'.
	 * @see org.eclipse.buckminster.cspec.AlterArtifact#getRemovePaths()
	 * @see #getAlterArtifact()
	 * @generated
	 */
	EReference getAlterArtifact_RemovePaths();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.cspec.AlterAttribute <em>Alter Attribute</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Alter Attribute</em>'.
	 * @see org.eclipse.buckminster.cspec.AlterAttribute
	 * @generated
	 */
	EClass getAlterAttribute();

	/**
	 * Returns the meta object for the container reference '
	 * {@link org.eclipse.buckminster.cspec.AlterAttribute#getCspecext <em>Cspecext</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for the container reference '<em>Cspecext</em>'.
	 * @see org.eclipse.buckminster.cspec.AlterAttribute#getCspecext()
	 * @see #getAlterAttribute()
	 * @generated
	 */
	EReference getAlterAttribute_Cspecext();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.cspec.AlterGroup <em>Alter Group</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Alter Group</em>'.
	 * @see org.eclipse.buckminster.cspec.AlterGroup
	 * @generated
	 */
	EClass getAlterGroup();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspec.AlterGroup#getRemovePrerequisites <em>Remove Prerequisites</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Remove Prerequisites</em>'.
	 * @see org.eclipse.buckminster.cspec.AlterGroup#getRemovePrerequisites()
	 * @see #getAlterGroup()
	 * @generated
	 */
	EReference getAlterGroup_RemovePrerequisites();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspec.AlterGroup#getReplacePrerequisites <em>Replace Prerequisites</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Replace Prerequisites</em>'.
	 * @see org.eclipse.buckminster.cspec.AlterGroup#getReplacePrerequisites()
	 * @see #getAlterGroup()
	 * @generated
	 */
	EReference getAlterGroup_ReplacePrerequisites();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.cspec.Artifact <em>Artifact</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Artifact</em>'.
	 * @see org.eclipse.buckminster.cspec.Artifact
	 * @generated
	 */
	EClass getArtifact();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.cspec.Attribute <em>Attribute</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Attribute</em>'.
	 * @see org.eclipse.buckminster.cspec.Attribute
	 * @generated
	 */
	EClass getAttribute();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.buckminster.cspec.Attribute#getCspec
	 * <em>Cspec</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the container reference '<em>Cspec</em>'.
	 * @see org.eclipse.buckminster.cspec.Attribute#getCspec()
	 * @see #getAttribute()
	 * @generated
	 */
	EReference getAttribute_Cspec();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link org.eclipse.buckminster.cspec.Attribute#getDocumentation <em>Documentation</em>}'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Documentation</em>'.
	 * @see org.eclipse.buckminster.cspec.Attribute#getDocumentation()
	 * @see #getAttribute()
	 * @generated
	 */
	EReference getAttribute_Documentation();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.cspec.Attribute#getFilter
	 * <em>Filter</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Filter</em>'.
	 * @see org.eclipse.buckminster.cspec.Attribute#getFilter()
	 * @see #getAttribute()
	 * @generated
	 */
	EAttribute getAttribute_Filter();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.cspec.Attribute#getName <em>Name</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.buckminster.cspec.Attribute#getName()
	 * @see #getAttribute()
	 * @generated
	 */
	EAttribute getAttribute_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.cspec.Attribute#isPublic
	 * <em>Public</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Public</em>'.
	 * @see org.eclipse.buckminster.cspec.Attribute#isPublic()
	 * @see #getAttribute()
	 * @generated
	 */
	EAttribute getAttribute_Public();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.cspec.CSpec <em>CSpec</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>CSpec</em>'.
	 * @see org.eclipse.buckminster.cspec.CSpec
	 * @generated
	 */
	EClass getCSpec();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspec.CSpec#getAttributes <em>Attributes</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Attributes</em>'.
	 * @see org.eclipse.buckminster.cspec.CSpec#getAttributes()
	 * @see #getCSpec()
	 * @generated
	 */
	EReference getCSpec_Attributes();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspec.CSpec#getDependencies <em>Dependencies</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Dependencies</em>'.
	 * @see org.eclipse.buckminster.cspec.CSpec#getDependencies()
	 * @see #getCSpec()
	 * @generated
	 */
	EReference getCSpec_Dependencies();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link org.eclipse.buckminster.cspec.CSpec#getDocumentation <em>Documentation</em>}'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Documentation</em>'.
	 * @see org.eclipse.buckminster.cspec.CSpec#getDocumentation()
	 * @see #getCSpec()
	 * @generated
	 */
	EReference getCSpec_Documentation();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.cspec.CSpec#getFilter <em>Filter</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Filter</em>'.
	 * @see org.eclipse.buckminster.cspec.CSpec#getFilter()
	 * @see #getCSpec()
	 * @generated
	 */
	EAttribute getCSpec_Filter();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspec.CSpec#getGenerators <em>Generators</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Generators</em>'.
	 * @see org.eclipse.buckminster.cspec.CSpec#getGenerators()
	 * @see #getCSpec()
	 * @generated
	 */
	EReference getCSpec_Generators();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.cspec.CSpec#getProjectInfo
	 * <em>Project Info</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Project Info</em>'.
	 * @see org.eclipse.buckminster.cspec.CSpec#getProjectInfo()
	 * @see #getCSpec()
	 * @generated
	 */
	EAttribute getCSpec_ProjectInfo();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.buckminster.cspec.CSpec#getSelf <em>Self</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Self</em>'.
	 * @see org.eclipse.buckminster.cspec.CSpec#getSelf()
	 * @see #getCSpec()
	 * @generated
	 */
	EReference getCSpec_Self();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.cspec.CSpec#getShortDesc
	 * <em>Short Desc</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Short Desc</em>'.
	 * @see org.eclipse.buckminster.cspec.CSpec#getShortDesc()
	 * @see #getCSpec()
	 * @generated
	 */
	EAttribute getCSpec_ShortDesc();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.cspec.CSpecExtension <em>CSpec Extension</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>CSpec Extension</em>'.
	 * @see org.eclipse.buckminster.cspec.CSpecExtension
	 * @generated
	 */
	EClass getCSpecExtension();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspec.CSpecExtension#getAttributeAlterations <em>Attribute Alterations</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Attribute Alterations</em>'.
	 * @see org.eclipse.buckminster.cspec.CSpecExtension#getAttributeAlterations()
	 * @see #getCSpecExtension()
	 * @generated
	 */
	EReference getCSpecExtension_AttributeAlterations();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspec.CSpecExtension#getRemoveDependencies <em>Remove Dependencies</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Remove Dependencies</em>'.
	 * @see org.eclipse.buckminster.cspec.CSpecExtension#getRemoveDependencies()
	 * @see #getCSpecExtension()
	 * @generated
	 */
	EReference getCSpecExtension_RemoveDependencies();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspec.CSpecExtension#getRemoveGenerators <em>Remove Generators</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Remove Generators</em>'.
	 * @see org.eclipse.buckminster.cspec.CSpecExtension#getRemoveGenerators()
	 * @see #getCSpecExtension()
	 * @generated
	 */
	EReference getCSpecExtension_RemoveGenerators();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspec.CSpecExtension#getRenameAttributes <em>Rename Attributes</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Rename Attributes</em>'.
	 * @see org.eclipse.buckminster.cspec.CSpecExtension#getRenameAttributes()
	 * @see #getCSpecExtension()
	 * @generated
	 */
	EReference getCSpecExtension_RenameAttributes();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspec.CSpecExtension#getReplaceDependencies <em>Replace Dependencies</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Replace Dependencies</em>'.
	 * @see org.eclipse.buckminster.cspec.CSpecExtension#getReplaceDependencies()
	 * @see #getCSpecExtension()
	 * @generated
	 */
	EReference getCSpecExtension_ReplaceDependencies();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspec.CSpecExtension#getReplaceGenerators <em>Replace Generators</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Replace Generators</em>'.
	 * @see org.eclipse.buckminster.cspec.CSpecExtension#getReplaceGenerators()
	 * @see #getCSpecExtension()
	 * @generated
	 */
	EReference getCSpecExtension_ReplaceGenerators();

	/**
	 * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	CspecFactory getCspecFactory();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.cspec.Generator <em>Generator</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Generator</em>'.
	 * @see org.eclipse.buckminster.cspec.Generator
	 * @generated
	 */
	EClass getGenerator();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.cspec.Generator#getAttribute
	 * <em>Attribute</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Attribute</em>'.
	 * @see org.eclipse.buckminster.cspec.Generator#getAttribute()
	 * @see #getGenerator()
	 * @generated
	 */
	EAttribute getGenerator_Attribute();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.buckminster.cspec.Generator#getComponent
	 * <em>Component</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Component</em>'.
	 * @see org.eclipse.buckminster.cspec.Generator#getComponent()
	 * @see #getGenerator()
	 * @generated
	 */
	EReference getGenerator_Component();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.buckminster.cspec.Generator#getCspec
	 * <em>Cspec</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the container reference '<em>Cspec</em>'.
	 * @see org.eclipse.buckminster.cspec.Generator#getCspec()
	 * @see #getGenerator()
	 * @generated
	 */
	EReference getGenerator_Cspec();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link org.eclipse.buckminster.cspec.Generator#getGenerates <em>Generates</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Generates</em>'.
	 * @see org.eclipse.buckminster.cspec.Generator#getGenerates()
	 * @see #getGenerator()
	 * @generated
	 */
	EReference getGenerator_Generates();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.cspec.Group <em>Group</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Group</em>'.
	 * @see org.eclipse.buckminster.cspec.Group
	 * @generated
	 */
	EClass getGroup();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.cspec.Group#getPrerequisites <em>Prerequisites</em>}'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Prerequisites</em>'.
	 * @see org.eclipse.buckminster.cspec.Group#getPrerequisites()
	 * @see #getGroup()
	 * @generated
	 */
	EReference getGroup_Prerequisites();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.cspec.Group#getRebase <em>Rebase</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Rebase</em>'.
	 * @see org.eclipse.buckminster.cspec.Group#getRebase()
	 * @see #getGroup()
	 * @generated
	 */
	EAttribute getGroup_Rebase();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.cspec.IContext <em>IContext</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>IContext</em>'.
	 * @see org.eclipse.buckminster.cspec.IContext
	 * @generated
	 */
	EClass getIContext();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.core.runtime.IPath <em>IPath</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>IPath</em>'.
	 * @see org.eclipse.core.runtime.IPath
	 * @model instanceClass="org.eclipse.core.runtime.IPath"
	 * @generated
	 */
	EDataType getIPath();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.cspec.PathGroup <em>Path Group</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Path Group</em>'.
	 * @see org.eclipse.buckminster.cspec.PathGroup
	 * @generated
	 */
	EClass getPathGroup();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.cspec.PathGroup#getBase <em>Base</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Base</em>'.
	 * @see org.eclipse.buckminster.cspec.PathGroup#getBase()
	 * @see #getPathGroup()
	 * @generated
	 */
	EAttribute getPathGroup_Base();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.buckminster.cspec.PathGroup#getPaths
	 * <em>Paths</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute list '<em>Paths</em>'.
	 * @see org.eclipse.buckminster.cspec.PathGroup#getPaths()
	 * @see #getPathGroup()
	 * @generated
	 */
	EAttribute getPathGroup_Paths();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.cspec.Prerequisite <em>Prerequisite</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Prerequisite</em>'.
	 * @see org.eclipse.buckminster.cspec.Prerequisite
	 * @generated
	 */
	EClass getPrerequisite();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.cspec.Prerequisite#getAlias
	 * <em>Alias</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Alias</em>'.
	 * @see org.eclipse.buckminster.cspec.Prerequisite#getAlias()
	 * @see #getPrerequisite()
	 * @generated
	 */
	EAttribute getPrerequisite_Alias();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.cspec.Prerequisite#getAttribute
	 * <em>Attribute</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Attribute</em>'.
	 * @see org.eclipse.buckminster.cspec.Prerequisite#getAttribute()
	 * @see #getPrerequisite()
	 * @generated
	 */
	EAttribute getPrerequisite_Attribute();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.buckminster.cspec.Prerequisite#getComponent
	 * <em>Component</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Component</em>'.
	 * @see org.eclipse.buckminster.cspec.Prerequisite#getComponent()
	 * @see #getPrerequisite()
	 * @generated
	 */
	EReference getPrerequisite_Component();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.cspec.Prerequisite#isContributor
	 * <em>Contributor</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Contributor</em>'.
	 * @see org.eclipse.buckminster.cspec.Prerequisite#isContributor()
	 * @see #getPrerequisite()
	 * @generated
	 */
	EAttribute getPrerequisite_Contributor();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.cspec.Prerequisite#getExcludePattern
	 * <em>Exclude Pattern</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Exclude Pattern</em>'.
	 * @see org.eclipse.buckminster.cspec.Prerequisite#getExcludePattern()
	 * @see #getPrerequisite()
	 * @generated
	 */
	EAttribute getPrerequisite_ExcludePattern();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.cspec.Prerequisite#getFilter
	 * <em>Filter</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Filter</em>'.
	 * @see org.eclipse.buckminster.cspec.Prerequisite#getFilter()
	 * @see #getPrerequisite()
	 * @generated
	 */
	EAttribute getPrerequisite_Filter();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.cspec.Prerequisite#getIncludePattern
	 * <em>Include Pattern</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Include Pattern</em>'.
	 * @see org.eclipse.buckminster.cspec.Prerequisite#getIncludePattern()
	 * @see #getPrerequisite()
	 * @generated
	 */
	EAttribute getPrerequisite_IncludePattern();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.cspec.Prerequisite#isOptional
	 * <em>Optional</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Optional</em>'.
	 * @see org.eclipse.buckminster.cspec.Prerequisite#isOptional()
	 * @see #getPrerequisite()
	 * @generated
	 */
	EAttribute getPrerequisite_Optional();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.cspec.Remove <em>Remove</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Remove</em>'.
	 * @see org.eclipse.buckminster.cspec.Remove
	 * @generated
	 */
	EClass getRemove();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.cspec.Remove#getName <em>Name</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.buckminster.cspec.Remove#getName()
	 * @see #getRemove()
	 * @generated
	 */
	EAttribute getRemove_Name();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.cspec.Rename <em>Rename</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Rename</em>'.
	 * @see org.eclipse.buckminster.cspec.Rename
	 * @generated
	 */
	EClass getRename();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.cspec.Rename#getNewName
	 * <em>New Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>New Name</em>'.
	 * @see org.eclipse.buckminster.cspec.Rename#getNewName()
	 * @see #getRename()
	 * @generated
	 */
	EAttribute getRename_NewName();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.cspec.Rename#getOldName
	 * <em>Old Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Old Name</em>'.
	 * @see org.eclipse.buckminster.cspec.Rename#getOldName()
	 * @see #getRename()
	 * @generated
	 */
	EAttribute getRename_OldName();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.cspec.SelfArtifact <em>Self Artifact</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Self Artifact</em>'.
	 * @see org.eclipse.buckminster.cspec.SelfArtifact
	 * @generated
	 */
	EClass getSelfArtifact();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.buckminster.cspec.UpToDatePolicy <em>Up To Date Policy</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for enum '<em>Up To Date Policy</em>'.
	 * @see org.eclipse.buckminster.cspec.UpToDatePolicy
	 * @generated
	 */
	EEnum getUpToDatePolicy();

} // CspecPackage
