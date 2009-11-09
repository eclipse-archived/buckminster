/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator;

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
 * @see org.eclipse.buckminster.aggregator.AggregatorFactory
 * @model kind="package"
 * @generated
 */
public interface AggregatorPackage extends EPackage
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
		 * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.impl.AggregatorImpl
		 * <em>Aggregator</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.impl.AggregatorImpl
		 * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getAggregator()
		 * @generated
		 */
		EClass AGGREGATOR = eINSTANCE.getAggregator();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute AGGREGATOR__TYPE = eINSTANCE.getAggregator_Type();

		/**
		 * The meta object literal for the '<em><b>Maven Result</b></em>' attribute feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute AGGREGATOR__MAVEN_RESULT = eINSTANCE.getAggregator_MavenResult();

		/**
		 * The meta object literal for the '<em><b>Validation Repositories</b></em>' containment reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference AGGREGATOR__VALIDATION_REPOSITORIES = eINSTANCE.getAggregator_ValidationRepositories();

		/**
		 * The meta object literal for the '<em><b>Maven Mappings</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference AGGREGATOR__MAVEN_MAPPINGS = eINSTANCE.getAggregator_MavenMappings();

		/**
		 * The meta object literal for the '<em><b>Build Root</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute AGGREGATOR__BUILD_ROOT = eINSTANCE.getAggregator_BuildRoot();

		/**
		 * The meta object literal for the '<em><b>Configurations</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference AGGREGATOR__CONFIGURATIONS = eINSTANCE.getAggregator_Configurations();

		/**
		 * The meta object literal for the '<em><b>Contributions</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference AGGREGATOR__CONTRIBUTIONS = eINSTANCE.getAggregator_Contributions();

		/**
		 * The meta object literal for the '<em><b>Label</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute AGGREGATOR__LABEL = eINSTANCE.getAggregator_Label();

		/**
		 * The meta object literal for the '<em><b>Buildmaster</b></em>' containment reference feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference AGGREGATOR__BUILDMASTER = eINSTANCE.getAggregator_Buildmaster();

		/**
		 * The meta object literal for the '<em><b>Sendmail</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute AGGREGATOR__SENDMAIL = eINSTANCE.getAggregator_Sendmail();

		/**
		 * The meta object literal for the '<em><b>Contacts</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference AGGREGATOR__CONTACTS = eINSTANCE.getAggregator_Contacts();

		/**
		 * The meta object literal for the '<em><b>Custom Categories</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference AGGREGATOR__CUSTOM_CATEGORIES = eINSTANCE.getAggregator_CustomCategories();

		/**
		 * The meta object literal for the '<em><b>Packed Strategy</b></em>' attribute feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute AGGREGATOR__PACKED_STRATEGY = eINSTANCE.getAggregator_PackedStrategy();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.impl.MappedRepositoryImpl
		 * <em>Mapped Repository</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.impl.MappedRepositoryImpl
		 * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getMappedRepository()
		 * @generated
		 */
		EClass MAPPED_REPOSITORY = eINSTANCE.getMappedRepository();

		/**
		 * The meta object literal for the '<em><b>Products</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference MAPPED_REPOSITORY__PRODUCTS = eINSTANCE.getMappedRepository_Products();

		/**
		 * The meta object literal for the '<em><b>Bundles</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference MAPPED_REPOSITORY__BUNDLES = eINSTANCE.getMappedRepository_Bundles();

		/**
		 * The meta object literal for the '<em><b>Features</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference MAPPED_REPOSITORY__FEATURES = eINSTANCE.getMappedRepository_Features();

		/**
		 * The meta object literal for the '<em><b>Mirror Artifacts</b></em>' attribute feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute MAPPED_REPOSITORY__MIRROR_ARTIFACTS = eINSTANCE.getMappedRepository_MirrorArtifacts();

		/**
		 * The meta object literal for the '<em><b>Category Prefix</b></em>' attribute feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute MAPPED_REPOSITORY__CATEGORY_PREFIX = eINSTANCE.getMappedRepository_CategoryPrefix();

		/**
		 * The meta object literal for the '<em><b>Map Rules</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference MAPPED_REPOSITORY__MAP_RULES = eINSTANCE.getMappedRepository_MapRules();

		/**
		 * The meta object literal for the '<em><b>Categories</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference MAPPED_REPOSITORY__CATEGORIES = eINSTANCE.getMappedRepository_Categories();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.impl.ConfigurationImpl
		 * <em>Configuration</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.impl.ConfigurationImpl
		 * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getConfiguration()
		 * @generated
		 */
		EClass CONFIGURATION = eINSTANCE.getConfiguration();

		/**
		 * The meta object literal for the '<em><b>Operating System</b></em>' attribute feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute CONFIGURATION__OPERATING_SYSTEM = eINSTANCE.getConfiguration_OperatingSystem();

		/**
		 * The meta object literal for the '<em><b>Window System</b></em>' attribute feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute CONFIGURATION__WINDOW_SYSTEM = eINSTANCE.getConfiguration_WindowSystem();

		/**
		 * The meta object literal for the '<em><b>Architecture</b></em>' attribute feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute CONFIGURATION__ARCHITECTURE = eINSTANCE.getConfiguration_Architecture();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.impl.ContributionImpl
		 * <em>Contribution</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.impl.ContributionImpl
		 * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getContribution()
		 * @generated
		 */
		EClass CONTRIBUTION = eINSTANCE.getContribution();

		/**
		 * The meta object literal for the '<em><b>Label</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute CONTRIBUTION__LABEL = eINSTANCE.getContribution_Label();

		/**
		 * The meta object literal for the '<em><b>Repositories</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference CONTRIBUTION__REPOSITORIES = eINSTANCE.getContribution_Repositories();

		/**
		 * The meta object literal for the '<em><b>Contacts</b></em>' reference list feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference CONTRIBUTION__CONTACTS = eINSTANCE.getContribution_Contacts();

		/**
		 * The meta object literal for the '<em><b>Maven Mappings</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference CONTRIBUTION__MAVEN_MAPPINGS = eINSTANCE.getContribution_MavenMappings();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.impl.ContactImpl <em>Contact</em>}
		 * ' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.impl.ContactImpl
		 * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getContact()
		 * @generated
		 */
		EClass CONTACT = eINSTANCE.getContact();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute CONTACT__NAME = eINSTANCE.getContact_Name();

		/**
		 * The meta object literal for the '<em><b>Email</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute CONTACT__EMAIL = eINSTANCE.getContact_Email();

		/**
		 * The meta object literal for the '<em><b>Aggregator</b></em>' container reference feature. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference CONTACT__AGGREGATOR = eINSTANCE.getContact_Aggregator();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.impl.FeatureImpl <em>Feature</em>}
		 * ' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.impl.FeatureImpl
		 * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getFeature()
		 * @generated
		 */
		EClass FEATURE = eINSTANCE.getFeature();

		/**
		 * The meta object literal for the '<em><b>Categories</b></em>' reference list feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference FEATURE__CATEGORIES = eINSTANCE.getFeature_Categories();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.impl.BundleImpl <em>Bundle</em>}'
		 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.impl.BundleImpl
		 * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getBundle()
		 * @generated
		 */
		EClass BUNDLE = eINSTANCE.getBundle();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.impl.MappedUnitImpl
		 * <em>Mapped Unit</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.impl.MappedUnitImpl
		 * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getMappedUnit()
		 * @generated
		 */
		EClass MAPPED_UNIT = eINSTANCE.getMappedUnit();

		/**
		 * The meta object literal for the '<em><b>Valid Configurations</b></em>' reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference MAPPED_UNIT__VALID_CONFIGURATIONS = eINSTANCE.getMappedUnit_ValidConfigurations();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.impl.ProductImpl <em>Product</em>}
		 * ' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.impl.ProductImpl
		 * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getProduct()
		 * @generated
		 */
		EClass PRODUCT = eINSTANCE.getProduct();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.impl.PropertyImpl
		 * <em>Property</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.impl.PropertyImpl
		 * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getProperty()
		 * @generated
		 */
		EClass PROPERTY = eINSTANCE.getProperty();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PROPERTY__KEY = eINSTANCE.getProperty_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PROPERTY__VALUE = eINSTANCE.getProperty_Value();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.impl.CategoryImpl
		 * <em>Category</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.impl.CategoryImpl
		 * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getCategory()
		 * @generated
		 */
		EClass CATEGORY = eINSTANCE.getCategory();

		/**
		 * The meta object literal for the '<em><b>Label Override</b></em>' attribute feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute CATEGORY__LABEL_OVERRIDE = eINSTANCE.getCategory_LabelOverride();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.impl.CustomCategoryImpl
		 * <em>Custom Category</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.impl.CustomCategoryImpl
		 * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getCustomCategory()
		 * @generated
		 */
		EClass CUSTOM_CATEGORY = eINSTANCE.getCustomCategory();

		/**
		 * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute CUSTOM_CATEGORY__IDENTIFIER = eINSTANCE.getCustomCategory_Identifier();

		/**
		 * The meta object literal for the '<em><b>Label</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute CUSTOM_CATEGORY__LABEL = eINSTANCE.getCustomCategory_Label();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute CUSTOM_CATEGORY__DESCRIPTION = eINSTANCE.getCustomCategory_Description();

		/**
		 * The meta object literal for the '<em><b>Features</b></em>' reference list feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference CUSTOM_CATEGORY__FEATURES = eINSTANCE.getCustomCategory_Features();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.EnabledStatusProvider
		 * <em>Enabled Status Provider</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.EnabledStatusProvider
		 * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getEnabledStatusProvider()
		 * @generated
		 */
		EClass ENABLED_STATUS_PROVIDER = eINSTANCE.getEnabledStatusProvider();

		/**
		 * The meta object literal for the '<em><b>Enabled</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ENABLED_STATUS_PROVIDER__ENABLED = eINSTANCE.getEnabledStatusProvider_Enabled();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.impl.MapRuleImpl
		 * <em>Map Rule</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.impl.MapRuleImpl
		 * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getMapRule()
		 * @generated
		 */
		EClass MAP_RULE = eINSTANCE.getMapRule();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.impl.InstallableUnitReferenceImpl
		 * <em>Installable Unit Reference</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.impl.InstallableUnitReferenceImpl
		 * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getInstallableUnitReference()
		 * @generated
		 */
		EClass INSTALLABLE_UNIT_REFERENCE = eINSTANCE.getInstallableUnitReference();

		/**
		 * The meta object literal for the '<em><b>Installable Unit</b></em>' reference feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference INSTALLABLE_UNIT_REFERENCE__INSTALLABLE_UNIT = eINSTANCE.getInstallableUnitReference_InstallableUnit();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.impl.ExclusionRuleImpl
		 * <em>Exclusion Rule</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.impl.ExclusionRuleImpl
		 * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getExclusionRule()
		 * @generated
		 */
		EClass EXCLUSION_RULE = eINSTANCE.getExclusionRule();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.impl.ValidConfigurationsRuleImpl
		 * <em>Valid Configurations Rule</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.impl.ValidConfigurationsRuleImpl
		 * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getValidConfigurationsRule()
		 * @generated
		 */
		EClass VALID_CONFIGURATIONS_RULE = eINSTANCE.getValidConfigurationsRule();

		/**
		 * The meta object literal for the '<em><b>Valid Configurations</b></em>' reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference VALID_CONFIGURATIONS_RULE__VALID_CONFIGURATIONS = eINSTANCE.getValidConfigurationsRule_ValidConfigurations();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.buckminster.aggregator.impl.MetadataRepositoryReferenceImpl
		 * <em>Metadata Repository Reference</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.impl.MetadataRepositoryReferenceImpl
		 * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getMetadataRepositoryReference()
		 * @generated
		 */
		EClass METADATA_REPOSITORY_REFERENCE = eINSTANCE.getMetadataRepositoryReference();

		/**
		 * The meta object literal for the '<em><b>Metadata Repository</b></em>' reference feature. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference METADATA_REPOSITORY_REFERENCE__METADATA_REPOSITORY = eINSTANCE.getMetadataRepositoryReference_MetadataRepository();

		/**
		 * The meta object literal for the '<em><b>Location</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute METADATA_REPOSITORY_REFERENCE__LOCATION = eINSTANCE.getMetadataRepositoryReference_Location();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.StatusProvider
		 * <em>Status Provider</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.StatusProvider
		 * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getStatusProvider()
		 * @generated
		 */
		EClass STATUS_PROVIDER = eINSTANCE.getStatusProvider();

		/**
		 * The meta object literal for the '{@link java.lang.Comparable <em>Comparable</em>}' class. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @see java.lang.Comparable
		 * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getComparable()
		 * @generated
		 */
		EClass COMPARABLE = eINSTANCE.getComparable();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.LabelProvider
		 * <em>Label Provider</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.LabelProvider
		 * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getLabelProvider()
		 * @generated
		 */
		EClass LABEL_PROVIDER = eINSTANCE.getLabelProvider();

		/**
		 * The meta object literal for the '<em><b>Label</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute LABEL_PROVIDER__LABEL = eINSTANCE.getLabelProvider_Label();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.impl.DescriptionProviderImpl
		 * <em>Description Provider</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.impl.DescriptionProviderImpl
		 * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getDescriptionProvider()
		 * @generated
		 */
		EClass DESCRIPTION_PROVIDER = eINSTANCE.getDescriptionProvider();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute DESCRIPTION_PROVIDER__DESCRIPTION = eINSTANCE.getDescriptionProvider_Description();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.impl.MavenMappingImpl
		 * <em>Maven Mapping</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.impl.MavenMappingImpl
		 * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getMavenMapping()
		 * @generated
		 */
		EClass MAVEN_MAPPING = eINSTANCE.getMavenMapping();

		/**
		 * The meta object literal for the '<em><b>Name Pattern</b></em>' attribute feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute MAVEN_MAPPING__NAME_PATTERN = eINSTANCE.getMavenMapping_NamePattern();

		/**
		 * The meta object literal for the '<em><b>Group Id</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute MAVEN_MAPPING__GROUP_ID = eINSTANCE.getMavenMapping_GroupId();

		/**
		 * The meta object literal for the '<em><b>Artifact Id</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute MAVEN_MAPPING__ARTIFACT_ID = eINSTANCE.getMavenMapping_ArtifactId();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.impl.MavenItemImpl
		 * <em>Maven Item</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.impl.MavenItemImpl
		 * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getMavenItem()
		 * @generated
		 */
		EClass MAVEN_ITEM = eINSTANCE.getMavenItem();

		/**
		 * The meta object literal for the '<em><b>Group Id</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute MAVEN_ITEM__GROUP_ID = eINSTANCE.getMavenItem_GroupId();

		/**
		 * The meta object literal for the '<em><b>Artifact Id</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute MAVEN_ITEM__ARTIFACT_ID = eINSTANCE.getMavenItem_ArtifactId();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.ChildrenProvider
		 * <em>Children Provider</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.ChildrenProvider
		 * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getChildrenProvider()
		 * @generated
		 */
		EClass CHILDREN_PROVIDER = eINSTANCE.getChildrenProvider();

		/**
		 * The meta object literal for the '<em><b>Children</b></em>' reference list feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference CHILDREN_PROVIDER__CHILDREN = eINSTANCE.getChildrenProvider_Children();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.AggregateType
		 * <em>Aggregate Type</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.AggregateType
		 * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getAggregateType()
		 * @generated
		 */
		EEnum AGGREGATE_TYPE = eINSTANCE.getAggregateType();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.OperatingSystem
		 * <em>Operating System</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.OperatingSystem
		 * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getOperatingSystem()
		 * @generated
		 */
		EEnum OPERATING_SYSTEM = eINSTANCE.getOperatingSystem();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.WindowSystem
		 * <em>Window System</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.WindowSystem
		 * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getWindowSystem()
		 * @generated
		 */
		EEnum WINDOW_SYSTEM = eINSTANCE.getWindowSystem();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.Architecture
		 * <em>Architecture</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.Architecture
		 * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getArchitecture()
		 * @generated
		 */
		EEnum ARCHITECTURE = eINSTANCE.getArchitecture();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.PackedStrategy
		 * <em>Packed Strategy</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.PackedStrategy
		 * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getPackedStrategy()
		 * @generated
		 */
		EEnum PACKED_STRATEGY = eINSTANCE.getPackedStrategy();

		/**
		 * The meta object literal for the '<em>URI</em>' data type. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see java.net.URI
		 * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getURI()
		 * @generated
		 */
		EDataType URI = eINSTANCE.getURI();

	}

	/**
	 * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNAME = "aggregator";

	/**
	 * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/buckminster/2009/aggregator";

	/**
	 * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_PREFIX = "aggregator";

	/**
	 * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	AggregatorPackage eINSTANCE = org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.aggregator.impl.DescriptionProviderImpl
	 * <em>Description Provider</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.impl.DescriptionProviderImpl
	 * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getDescriptionProvider()
	 * @generated
	 */
	int DESCRIPTION_PROVIDER = 21;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DESCRIPTION_PROVIDER__DESCRIPTION = 0;

	/**
	 * The number of structural features of the '<em>Description Provider</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DESCRIPTION_PROVIDER_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.aggregator.impl.AggregatorImpl <em>Aggregator</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.impl.AggregatorImpl
	 * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getAggregator()
	 * @generated
	 */
	int AGGREGATOR = 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int AGGREGATOR__DESCRIPTION = DESCRIPTION_PROVIDER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Configurations</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int AGGREGATOR__CONFIGURATIONS = DESCRIPTION_PROVIDER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Contributions</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int AGGREGATOR__CONTRIBUTIONS = DESCRIPTION_PROVIDER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Buildmaster</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int AGGREGATOR__BUILDMASTER = DESCRIPTION_PROVIDER_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Contacts</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int AGGREGATOR__CONTACTS = DESCRIPTION_PROVIDER_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Custom Categories</b></em>' containment reference list. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int AGGREGATOR__CUSTOM_CATEGORIES = DESCRIPTION_PROVIDER_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int AGGREGATOR__LABEL = DESCRIPTION_PROVIDER_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Build Root</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int AGGREGATOR__BUILD_ROOT = DESCRIPTION_PROVIDER_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Packed Strategy</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int AGGREGATOR__PACKED_STRATEGY = DESCRIPTION_PROVIDER_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Sendmail</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int AGGREGATOR__SENDMAIL = DESCRIPTION_PROVIDER_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int AGGREGATOR__TYPE = DESCRIPTION_PROVIDER_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Maven Result</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int AGGREGATOR__MAVEN_RESULT = DESCRIPTION_PROVIDER_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Validation Repositories</b></em>' containment reference list. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int AGGREGATOR__VALIDATION_REPOSITORIES = DESCRIPTION_PROVIDER_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Maven Mappings</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int AGGREGATOR__MAVEN_MAPPINGS = DESCRIPTION_PROVIDER_FEATURE_COUNT + 12;

	/**
	 * The number of structural features of the '<em>Aggregator</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int AGGREGATOR_FEATURE_COUNT = DESCRIPTION_PROVIDER_FEATURE_COUNT + 13;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.aggregator.EnabledStatusProvider
	 * <em>Enabled Status Provider</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.EnabledStatusProvider
	 * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getEnabledStatusProvider()
	 * @generated
	 */
	int ENABLED_STATUS_PROVIDER = 12;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ENABLED_STATUS_PROVIDER__ENABLED = 0;

	/**
	 * The number of structural features of the '<em>Enabled Status Provider</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ENABLED_STATUS_PROVIDER_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.aggregator.impl.MetadataRepositoryReferenceImpl
	 * <em>Metadata Repository Reference</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.impl.MetadataRepositoryReferenceImpl
	 * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getMetadataRepositoryReference()
	 * @generated
	 */
	int METADATA_REPOSITORY_REFERENCE = 17;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int METADATA_REPOSITORY_REFERENCE__ENABLED = ENABLED_STATUS_PROVIDER__ENABLED;

	/**
	 * The feature id for the '<em><b>Metadata Repository</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int METADATA_REPOSITORY_REFERENCE__METADATA_REPOSITORY = ENABLED_STATUS_PROVIDER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int METADATA_REPOSITORY_REFERENCE__LOCATION = ENABLED_STATUS_PROVIDER_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Metadata Repository Reference</em>' class. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int METADATA_REPOSITORY_REFERENCE_FEATURE_COUNT = ENABLED_STATUS_PROVIDER_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.aggregator.impl.MappedRepositoryImpl
	 * <em>Mapped Repository</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.impl.MappedRepositoryImpl
	 * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getMappedRepository()
	 * @generated
	 */
	int MAPPED_REPOSITORY = 1;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MAPPED_REPOSITORY__ENABLED = METADATA_REPOSITORY_REFERENCE__ENABLED;

	/**
	 * The feature id for the '<em><b>Metadata Repository</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MAPPED_REPOSITORY__METADATA_REPOSITORY = METADATA_REPOSITORY_REFERENCE__METADATA_REPOSITORY;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MAPPED_REPOSITORY__LOCATION = METADATA_REPOSITORY_REFERENCE__LOCATION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MAPPED_REPOSITORY__DESCRIPTION = METADATA_REPOSITORY_REFERENCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Products</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MAPPED_REPOSITORY__PRODUCTS = METADATA_REPOSITORY_REFERENCE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Bundles</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MAPPED_REPOSITORY__BUNDLES = METADATA_REPOSITORY_REFERENCE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Features</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MAPPED_REPOSITORY__FEATURES = METADATA_REPOSITORY_REFERENCE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Categories</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MAPPED_REPOSITORY__CATEGORIES = METADATA_REPOSITORY_REFERENCE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Mirror Artifacts</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MAPPED_REPOSITORY__MIRROR_ARTIFACTS = METADATA_REPOSITORY_REFERENCE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Category Prefix</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MAPPED_REPOSITORY__CATEGORY_PREFIX = METADATA_REPOSITORY_REFERENCE_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Map Rules</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MAPPED_REPOSITORY__MAP_RULES = METADATA_REPOSITORY_REFERENCE_FEATURE_COUNT + 7;

	/**
	 * The number of structural features of the '<em>Mapped Repository</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MAPPED_REPOSITORY_FEATURE_COUNT = METADATA_REPOSITORY_REFERENCE_FEATURE_COUNT + 8;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.aggregator.impl.ConfigurationImpl
	 * <em>Configuration</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.impl.ConfigurationImpl
	 * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getConfiguration()
	 * @generated
	 */
	int CONFIGURATION = 2;

	/**
	 * The feature id for the '<em><b>Operating System</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION__OPERATING_SYSTEM = 0;

	/**
	 * The feature id for the '<em><b>Window System</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION__WINDOW_SYSTEM = 1;

	/**
	 * The feature id for the '<em><b>Architecture</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION__ARCHITECTURE = 2;

	/**
	 * The number of structural features of the '<em>Configuration</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.aggregator.impl.ContributionImpl
	 * <em>Contribution</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.impl.ContributionImpl
	 * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getContribution()
	 * @generated
	 */
	int CONTRIBUTION = 3;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.aggregator.impl.ContactImpl <em>Contact</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.impl.ContactImpl
	 * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getContact()
	 * @generated
	 */
	int CONTACT = 4;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.aggregator.impl.MappedUnitImpl <em>Mapped Unit</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.impl.MappedUnitImpl
	 * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getMappedUnit()
	 * @generated
	 */
	int MAPPED_UNIT = 7;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__ENABLED = ENABLED_STATUS_PROVIDER__ENABLED;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__DESCRIPTION = ENABLED_STATUS_PROVIDER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__LABEL = ENABLED_STATUS_PROVIDER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Repositories</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__REPOSITORIES = ENABLED_STATUS_PROVIDER_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Contacts</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__CONTACTS = ENABLED_STATUS_PROVIDER_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Maven Mappings</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__MAVEN_MAPPINGS = ENABLED_STATUS_PROVIDER_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Contribution</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION_FEATURE_COUNT = ENABLED_STATUS_PROVIDER_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONTACT__NAME = 0;

	/**
	 * The feature id for the '<em><b>Email</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONTACT__EMAIL = 1;

	/**
	 * The feature id for the '<em><b>Aggregator</b></em>' container reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONTACT__AGGREGATOR = 2;

	/**
	 * The number of structural features of the '<em>Contact</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONTACT_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.aggregator.StatusProvider <em>Status Provider</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.StatusProvider
	 * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getStatusProvider()
	 * @generated
	 */
	int STATUS_PROVIDER = 18;

	/**
	 * The number of structural features of the '<em>Status Provider</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int STATUS_PROVIDER_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.aggregator.impl.FeatureImpl <em>Feature</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.impl.FeatureImpl
	 * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getFeature()
	 * @generated
	 */
	int FEATURE = 5;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.aggregator.impl.BundleImpl <em>Bundle</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.impl.BundleImpl
	 * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getBundle()
	 * @generated
	 */
	int BUNDLE = 6;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.aggregator.impl.ProductImpl <em>Product</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.impl.ProductImpl
	 * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getProduct()
	 * @generated
	 */
	int PRODUCT = 8;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.aggregator.impl.PropertyImpl <em>Property</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.impl.PropertyImpl
	 * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getProperty()
	 * @generated
	 */
	int PROPERTY = 9;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.aggregator.impl.CategoryImpl <em>Category</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.impl.CategoryImpl
	 * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getCategory()
	 * @generated
	 */
	int CATEGORY = 10;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.aggregator.impl.CustomCategoryImpl
	 * <em>Custom Category</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.impl.CustomCategoryImpl
	 * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getCustomCategory()
	 * @generated
	 */
	int CUSTOM_CATEGORY = 11;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.aggregator.impl.InstallableUnitReferenceImpl
	 * <em>Installable Unit Reference</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.impl.InstallableUnitReferenceImpl
	 * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getInstallableUnitReference()
	 * @generated
	 */
	int INSTALLABLE_UNIT_REFERENCE = 14;

	/**
	 * The feature id for the '<em><b>Installable Unit</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INSTALLABLE_UNIT_REFERENCE__INSTALLABLE_UNIT = STATUS_PROVIDER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Installable Unit Reference</em>' class. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INSTALLABLE_UNIT_REFERENCE_FEATURE_COUNT = STATUS_PROVIDER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Installable Unit</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MAPPED_UNIT__INSTALLABLE_UNIT = INSTALLABLE_UNIT_REFERENCE__INSTALLABLE_UNIT;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MAPPED_UNIT__ENABLED = INSTALLABLE_UNIT_REFERENCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Valid Configurations</b></em>' reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MAPPED_UNIT__VALID_CONFIGURATIONS = INSTALLABLE_UNIT_REFERENCE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Mapped Unit</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MAPPED_UNIT_FEATURE_COUNT = INSTALLABLE_UNIT_REFERENCE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Installable Unit</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FEATURE__INSTALLABLE_UNIT = MAPPED_UNIT__INSTALLABLE_UNIT;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FEATURE__ENABLED = MAPPED_UNIT__ENABLED;

	/**
	 * The feature id for the '<em><b>Valid Configurations</b></em>' reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FEATURE__VALID_CONFIGURATIONS = MAPPED_UNIT__VALID_CONFIGURATIONS;

	/**
	 * The feature id for the '<em><b>Categories</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FEATURE__CATEGORIES = MAPPED_UNIT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Feature</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FEATURE_FEATURE_COUNT = MAPPED_UNIT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Installable Unit</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int BUNDLE__INSTALLABLE_UNIT = MAPPED_UNIT__INSTALLABLE_UNIT;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int BUNDLE__ENABLED = MAPPED_UNIT__ENABLED;

	/**
	 * The feature id for the '<em><b>Valid Configurations</b></em>' reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int BUNDLE__VALID_CONFIGURATIONS = MAPPED_UNIT__VALID_CONFIGURATIONS;

	/**
	 * The number of structural features of the '<em>Bundle</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int BUNDLE_FEATURE_COUNT = MAPPED_UNIT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Installable Unit</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PRODUCT__INSTALLABLE_UNIT = MAPPED_UNIT__INSTALLABLE_UNIT;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PRODUCT__ENABLED = MAPPED_UNIT__ENABLED;

	/**
	 * The feature id for the '<em><b>Valid Configurations</b></em>' reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PRODUCT__VALID_CONFIGURATIONS = MAPPED_UNIT__VALID_CONFIGURATIONS;

	/**
	 * The number of structural features of the '<em>Product</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PRODUCT_FEATURE_COUNT = MAPPED_UNIT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link java.lang.Comparable <em>Comparable</em>}' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see java.lang.Comparable
	 * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getComparable()
	 * @generated
	 */
	int COMPARABLE = 19;

	/**
	 * The number of structural features of the '<em>Comparable</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPARABLE_FEATURE_COUNT = 0;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROPERTY__KEY = COMPARABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROPERTY__VALUE = COMPARABLE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Property</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROPERTY_FEATURE_COUNT = COMPARABLE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Installable Unit</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CATEGORY__INSTALLABLE_UNIT = MAPPED_UNIT__INSTALLABLE_UNIT;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CATEGORY__ENABLED = MAPPED_UNIT__ENABLED;

	/**
	 * The feature id for the '<em><b>Valid Configurations</b></em>' reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CATEGORY__VALID_CONFIGURATIONS = MAPPED_UNIT__VALID_CONFIGURATIONS;

	/**
	 * The feature id for the '<em><b>Label Override</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CATEGORY__LABEL_OVERRIDE = MAPPED_UNIT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Category</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CATEGORY_FEATURE_COUNT = MAPPED_UNIT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Identifier</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CATEGORY__IDENTIFIER = STATUS_PROVIDER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CATEGORY__LABEL = STATUS_PROVIDER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CATEGORY__DESCRIPTION = STATUS_PROVIDER_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CATEGORY__FEATURES = STATUS_PROVIDER_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Custom Category</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CATEGORY_FEATURE_COUNT = STATUS_PROVIDER_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.aggregator.impl.MapRuleImpl <em>Map Rule</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.impl.MapRuleImpl
	 * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getMapRule()
	 * @generated
	 */
	int MAP_RULE = 13;

	/**
	 * The feature id for the '<em><b>Installable Unit</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MAP_RULE__INSTALLABLE_UNIT = INSTALLABLE_UNIT_REFERENCE__INSTALLABLE_UNIT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MAP_RULE__DESCRIPTION = INSTALLABLE_UNIT_REFERENCE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Map Rule</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MAP_RULE_FEATURE_COUNT = INSTALLABLE_UNIT_REFERENCE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.aggregator.impl.ExclusionRuleImpl
	 * <em>Exclusion Rule</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.impl.ExclusionRuleImpl
	 * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getExclusionRule()
	 * @generated
	 */
	int EXCLUSION_RULE = 15;

	/**
	 * The feature id for the '<em><b>Installable Unit</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EXCLUSION_RULE__INSTALLABLE_UNIT = MAP_RULE__INSTALLABLE_UNIT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EXCLUSION_RULE__DESCRIPTION = MAP_RULE__DESCRIPTION;

	/**
	 * The number of structural features of the '<em>Exclusion Rule</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EXCLUSION_RULE_FEATURE_COUNT = MAP_RULE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.aggregator.impl.ValidConfigurationsRuleImpl
	 * <em>Valid Configurations Rule</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.impl.ValidConfigurationsRuleImpl
	 * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getValidConfigurationsRule()
	 * @generated
	 */
	int VALID_CONFIGURATIONS_RULE = 16;

	/**
	 * The feature id for the '<em><b>Installable Unit</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int VALID_CONFIGURATIONS_RULE__INSTALLABLE_UNIT = MAP_RULE__INSTALLABLE_UNIT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int VALID_CONFIGURATIONS_RULE__DESCRIPTION = MAP_RULE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Valid Configurations</b></em>' reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int VALID_CONFIGURATIONS_RULE__VALID_CONFIGURATIONS = MAP_RULE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Valid Configurations Rule</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int VALID_CONFIGURATIONS_RULE_FEATURE_COUNT = MAP_RULE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.aggregator.LabelProvider <em>Label Provider</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.LabelProvider
	 * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getLabelProvider()
	 * @generated
	 */
	int LABEL_PROVIDER = 20;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int LABEL_PROVIDER__LABEL = 0;

	/**
	 * The number of structural features of the '<em>Label Provider</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int LABEL_PROVIDER_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.aggregator.impl.MavenMappingImpl
	 * <em>Maven Mapping</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.impl.MavenMappingImpl
	 * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getMavenMapping()
	 * @generated
	 */
	int MAVEN_MAPPING = 22;

	/**
	 * The feature id for the '<em><b>Name Pattern</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MAVEN_MAPPING__NAME_PATTERN = STATUS_PROVIDER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Group Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MAVEN_MAPPING__GROUP_ID = STATUS_PROVIDER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Artifact Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MAVEN_MAPPING__ARTIFACT_ID = STATUS_PROVIDER_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Maven Mapping</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MAVEN_MAPPING_FEATURE_COUNT = STATUS_PROVIDER_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.aggregator.impl.MavenItemImpl <em>Maven Item</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.impl.MavenItemImpl
	 * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getMavenItem()
	 * @generated
	 */
	int MAVEN_ITEM = 23;

	/**
	 * The feature id for the '<em><b>Group Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MAVEN_ITEM__GROUP_ID = 0;

	/**
	 * The feature id for the '<em><b>Artifact Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MAVEN_ITEM__ARTIFACT_ID = 1;

	/**
	 * The number of structural features of the '<em>Maven Item</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MAVEN_ITEM_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.aggregator.ChildrenProvider
	 * <em>Children Provider</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.ChildrenProvider
	 * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getChildrenProvider()
	 * @generated
	 */
	int CHILDREN_PROVIDER = 24;

	/**
	 * The feature id for the '<em><b>Children</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CHILDREN_PROVIDER__CHILDREN = 0;

	/**
	 * The number of structural features of the '<em>Children Provider</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CHILDREN_PROVIDER_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.aggregator.AggregateType <em>Aggregate Type</em>}'
	 * enum. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.AggregateType
	 * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getAggregateType()
	 * @generated
	 */
	int AGGREGATE_TYPE = 25;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.aggregator.OperatingSystem <em>Operating System</em>}'
	 * enum. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.OperatingSystem
	 * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getOperatingSystem()
	 * @generated
	 */
	int OPERATING_SYSTEM = 26;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.aggregator.WindowSystem <em>Window System</em>}' enum.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.WindowSystem
	 * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getWindowSystem()
	 * @generated
	 */
	int WINDOW_SYSTEM = 27;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.aggregator.Architecture <em>Architecture</em>}' enum.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.Architecture
	 * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getArchitecture()
	 * @generated
	 */
	int ARCHITECTURE = 28;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.aggregator.PackedStrategy <em>Packed Strategy</em>}'
	 * enum. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.PackedStrategy
	 * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getPackedStrategy()
	 * @generated
	 */
	int PACKED_STRATEGY = 29;

	/**
	 * The meta object id for the '<em>URI</em>' data type. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see java.net.URI
	 * @see org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl#getURI()
	 * @generated
	 */
	int URI = 30;

	/**
	 * Returns the meta object for enum '{@link org.eclipse.buckminster.aggregator.AggregateType
	 * <em>Aggregate Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for enum '<em>Aggregate Type</em>'.
	 * @see org.eclipse.buckminster.aggregator.AggregateType
	 * @generated
	 */
	EEnum getAggregateType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.Aggregator <em>Aggregator</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Aggregator</em>'.
	 * @see org.eclipse.buckminster.aggregator.Aggregator
	 * @generated
	 */
	EClass getAggregator();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.buckminster.aggregator.Aggregator#getBuildmaster
	 * <em>Buildmaster</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Buildmaster</em>'.
	 * @see org.eclipse.buckminster.aggregator.Aggregator#getBuildmaster()
	 * @see #getAggregator()
	 * @generated
	 */
	EReference getAggregator_Buildmaster();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.aggregator.Aggregator#getBuildRoot
	 * <em>Build Root</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Build Root</em>'.
	 * @see org.eclipse.buckminster.aggregator.Aggregator#getBuildRoot()
	 * @see #getAggregator()
	 * @generated
	 */
	EAttribute getAggregator_BuildRoot();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.aggregator.Aggregator#getConfigurations <em>Configurations</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Configurations</em>'.
	 * @see org.eclipse.buckminster.aggregator.Aggregator#getConfigurations()
	 * @see #getAggregator()
	 * @generated
	 */
	EReference getAggregator_Configurations();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.aggregator.Aggregator#getContacts <em>Contacts</em>}'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Contacts</em>'.
	 * @see org.eclipse.buckminster.aggregator.Aggregator#getContacts()
	 * @see #getAggregator()
	 * @generated
	 */
	EReference getAggregator_Contacts();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.aggregator.Aggregator#getContributions <em>Contributions</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Contributions</em>'.
	 * @see org.eclipse.buckminster.aggregator.Aggregator#getContributions()
	 * @see #getAggregator()
	 * @generated
	 */
	EReference getAggregator_Contributions();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.aggregator.Aggregator#getCustomCategories <em>Custom Categories</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Custom Categories</em>'.
	 * @see org.eclipse.buckminster.aggregator.Aggregator#getCustomCategories()
	 * @see #getAggregator()
	 * @generated
	 */
	EReference getAggregator_CustomCategories();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.aggregator.Aggregator#getLabel
	 * <em>Label</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Label</em>'.
	 * @see org.eclipse.buckminster.aggregator.Aggregator#getLabel()
	 * @see #getAggregator()
	 * @generated
	 */
	EAttribute getAggregator_Label();

	/**
	 * Returns the meta object for the reference list '
	 * {@link org.eclipse.buckminster.aggregator.Aggregator#getMavenMappings <em>Maven Mappings</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference list '<em>Maven Mappings</em>'.
	 * @see org.eclipse.buckminster.aggregator.Aggregator#getMavenMappings()
	 * @see #getAggregator()
	 * @generated
	 */
	EReference getAggregator_MavenMappings();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.aggregator.Aggregator#isMavenResult
	 * <em>Maven Result</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Maven Result</em>'.
	 * @see org.eclipse.buckminster.aggregator.Aggregator#isMavenResult()
	 * @see #getAggregator()
	 * @generated
	 */
	EAttribute getAggregator_MavenResult();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.aggregator.Aggregator#getPackedStrategy <em>Packed Strategy</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Packed Strategy</em>'.
	 * @see org.eclipse.buckminster.aggregator.Aggregator#getPackedStrategy()
	 * @see #getAggregator()
	 * @generated
	 */
	EAttribute getAggregator_PackedStrategy();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.aggregator.Aggregator#isSendmail
	 * <em>Sendmail</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Sendmail</em>'.
	 * @see org.eclipse.buckminster.aggregator.Aggregator#isSendmail()
	 * @see #getAggregator()
	 * @generated
	 */
	EAttribute getAggregator_Sendmail();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.aggregator.Aggregator#getType
	 * <em>Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.eclipse.buckminster.aggregator.Aggregator#getType()
	 * @see #getAggregator()
	 * @generated
	 */
	EAttribute getAggregator_Type();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.aggregator.Aggregator#getValidationRepositories <em>Validation Repositories</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Validation Repositories</em>'.
	 * @see org.eclipse.buckminster.aggregator.Aggregator#getValidationRepositories()
	 * @see #getAggregator()
	 * @generated
	 */
	EReference getAggregator_ValidationRepositories();

	/**
	 * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	AggregatorFactory getAggregatorFactory();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.buckminster.aggregator.Architecture <em>Architecture</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for enum '<em>Architecture</em>'.
	 * @see org.eclipse.buckminster.aggregator.Architecture
	 * @generated
	 */
	EEnum getArchitecture();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.Bundle <em>Bundle</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Bundle</em>'.
	 * @see org.eclipse.buckminster.aggregator.Bundle
	 * @generated
	 */
	EClass getBundle();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.Category <em>Category</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Category</em>'.
	 * @see org.eclipse.buckminster.aggregator.Category
	 * @generated
	 */
	EClass getCategory();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.aggregator.Category#getLabelOverride
	 * <em>Label Override</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Label Override</em>'.
	 * @see org.eclipse.buckminster.aggregator.Category#getLabelOverride()
	 * @see #getCategory()
	 * @generated
	 */
	EAttribute getCategory_LabelOverride();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.ChildrenProvider
	 * <em>Children Provider</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Children Provider</em>'.
	 * @see org.eclipse.buckminster.aggregator.ChildrenProvider
	 * @generated
	 */
	EClass getChildrenProvider();

	/**
	 * Returns the meta object for the reference list '
	 * {@link org.eclipse.buckminster.aggregator.ChildrenProvider#getChildren <em>Children</em>}'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference list '<em>Children</em>'.
	 * @see org.eclipse.buckminster.aggregator.ChildrenProvider#getChildren()
	 * @see #getChildrenProvider()
	 * @generated
	 */
	EReference getChildrenProvider_Children();

	/**
	 * Returns the meta object for class '{@link java.lang.Comparable <em>Comparable</em>}'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Comparable</em>'.
	 * @see java.lang.Comparable
	 * @model instanceClass="java.lang.Comparable" typeParameters="T"
	 * @generated
	 */
	EClass getComparable();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.Configuration
	 * <em>Configuration</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Configuration</em>'.
	 * @see org.eclipse.buckminster.aggregator.Configuration
	 * @generated
	 */
	EClass getConfiguration();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.aggregator.Configuration#getArchitecture <em>Architecture</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Architecture</em>'.
	 * @see org.eclipse.buckminster.aggregator.Configuration#getArchitecture()
	 * @see #getConfiguration()
	 * @generated
	 */
	EAttribute getConfiguration_Architecture();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.aggregator.Configuration#getOperatingSystem <em>Operating System</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Operating System</em>'.
	 * @see org.eclipse.buckminster.aggregator.Configuration#getOperatingSystem()
	 * @see #getConfiguration()
	 * @generated
	 */
	EAttribute getConfiguration_OperatingSystem();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.aggregator.Configuration#getWindowSystem <em>Window System</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Window System</em>'.
	 * @see org.eclipse.buckminster.aggregator.Configuration#getWindowSystem()
	 * @see #getConfiguration()
	 * @generated
	 */
	EAttribute getConfiguration_WindowSystem();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.Contact <em>Contact</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Contact</em>'.
	 * @see org.eclipse.buckminster.aggregator.Contact
	 * @generated
	 */
	EClass getContact();

	/**
	 * Returns the meta object for the container reference '
	 * {@link org.eclipse.buckminster.aggregator.Contact#getAggregator <em>Aggregator</em>}'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the container reference '<em>Aggregator</em>'.
	 * @see org.eclipse.buckminster.aggregator.Contact#getAggregator()
	 * @see #getContact()
	 * @generated
	 */
	EReference getContact_Aggregator();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.aggregator.Contact#getEmail
	 * <em>Email</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Email</em>'.
	 * @see org.eclipse.buckminster.aggregator.Contact#getEmail()
	 * @see #getContact()
	 * @generated
	 */
	EAttribute getContact_Email();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.aggregator.Contact#getName
	 * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.buckminster.aggregator.Contact#getName()
	 * @see #getContact()
	 * @generated
	 */
	EAttribute getContact_Name();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.Contribution <em>Contribution</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Contribution</em>'.
	 * @see org.eclipse.buckminster.aggregator.Contribution
	 * @generated
	 */
	EClass getContribution();

	/**
	 * Returns the meta object for the reference list '
	 * {@link org.eclipse.buckminster.aggregator.Contribution#getContacts <em>Contacts</em>}'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference list '<em>Contacts</em>'.
	 * @see org.eclipse.buckminster.aggregator.Contribution#getContacts()
	 * @see #getContribution()
	 * @generated
	 */
	EReference getContribution_Contacts();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.aggregator.Contribution#getLabel
	 * <em>Label</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Label</em>'.
	 * @see org.eclipse.buckminster.aggregator.Contribution#getLabel()
	 * @see #getContribution()
	 * @generated
	 */
	EAttribute getContribution_Label();

	/**
	 * Returns the meta object for the reference list '
	 * {@link org.eclipse.buckminster.aggregator.Contribution#getMavenMappings <em>Maven Mappings</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference list '<em>Maven Mappings</em>'.
	 * @see org.eclipse.buckminster.aggregator.Contribution#getMavenMappings()
	 * @see #getContribution()
	 * @generated
	 */
	EReference getContribution_MavenMappings();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.aggregator.Contribution#getRepositories <em>Repositories</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Repositories</em>'.
	 * @see org.eclipse.buckminster.aggregator.Contribution#getRepositories()
	 * @see #getContribution()
	 * @generated
	 */
	EReference getContribution_Repositories();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.CustomCategory
	 * <em>Custom Category</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Custom Category</em>'.
	 * @see org.eclipse.buckminster.aggregator.CustomCategory
	 * @generated
	 */
	EClass getCustomCategory();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.aggregator.CustomCategory#getDescription <em>Description</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.eclipse.buckminster.aggregator.CustomCategory#getDescription()
	 * @see #getCustomCategory()
	 * @generated
	 */
	EAttribute getCustomCategory_Description();

	/**
	 * Returns the meta object for the reference list '
	 * {@link org.eclipse.buckminster.aggregator.CustomCategory#getFeatures <em>Features</em>}'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference list '<em>Features</em>'.
	 * @see org.eclipse.buckminster.aggregator.CustomCategory#getFeatures()
	 * @see #getCustomCategory()
	 * @generated
	 */
	EReference getCustomCategory_Features();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.aggregator.CustomCategory#getIdentifier <em>Identifier</em>}'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Identifier</em>'.
	 * @see org.eclipse.buckminster.aggregator.CustomCategory#getIdentifier()
	 * @see #getCustomCategory()
	 * @generated
	 */
	EAttribute getCustomCategory_Identifier();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.aggregator.CustomCategory#getLabel
	 * <em>Label</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Label</em>'.
	 * @see org.eclipse.buckminster.aggregator.CustomCategory#getLabel()
	 * @see #getCustomCategory()
	 * @generated
	 */
	EAttribute getCustomCategory_Label();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.DescriptionProvider
	 * <em>Description Provider</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Description Provider</em>'.
	 * @see org.eclipse.buckminster.aggregator.DescriptionProvider
	 * @generated
	 */
	EClass getDescriptionProvider();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.aggregator.DescriptionProvider#getDescription <em>Description</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.eclipse.buckminster.aggregator.DescriptionProvider#getDescription()
	 * @see #getDescriptionProvider()
	 * @generated
	 */
	EAttribute getDescriptionProvider_Description();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.EnabledStatusProvider
	 * <em>Enabled Status Provider</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Enabled Status Provider</em>'.
	 * @see org.eclipse.buckminster.aggregator.EnabledStatusProvider
	 * @generated
	 */
	EClass getEnabledStatusProvider();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.aggregator.EnabledStatusProvider#isEnabled <em>Enabled</em>}'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Enabled</em>'.
	 * @see org.eclipse.buckminster.aggregator.EnabledStatusProvider#isEnabled()
	 * @see #getEnabledStatusProvider()
	 * @generated
	 */
	EAttribute getEnabledStatusProvider_Enabled();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.ExclusionRule
	 * <em>Exclusion Rule</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Exclusion Rule</em>'.
	 * @see org.eclipse.buckminster.aggregator.ExclusionRule
	 * @generated
	 */
	EClass getExclusionRule();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.Feature <em>Feature</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Feature</em>'.
	 * @see org.eclipse.buckminster.aggregator.Feature
	 * @generated
	 */
	EClass getFeature();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.buckminster.aggregator.Feature#getCategories
	 * <em>Categories</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference list '<em>Categories</em>'.
	 * @see org.eclipse.buckminster.aggregator.Feature#getCategories()
	 * @see #getFeature()
	 * @generated
	 */
	EReference getFeature_Categories();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.InstallableUnitReference
	 * <em>Installable Unit Reference</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Installable Unit Reference</em>'.
	 * @see org.eclipse.buckminster.aggregator.InstallableUnitReference
	 * @generated
	 */
	EClass getInstallableUnitReference();

	/**
	 * Returns the meta object for the reference '
	 * {@link org.eclipse.buckminster.aggregator.InstallableUnitReference#getInstallableUnit <em>Installable Unit</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Installable Unit</em>'.
	 * @see org.eclipse.buckminster.aggregator.InstallableUnitReference#getInstallableUnit()
	 * @see #getInstallableUnitReference()
	 * @generated
	 */
	EReference getInstallableUnitReference_InstallableUnit();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.LabelProvider
	 * <em>Label Provider</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Label Provider</em>'.
	 * @see org.eclipse.buckminster.aggregator.LabelProvider
	 * @generated
	 */
	EClass getLabelProvider();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.aggregator.LabelProvider#getLabel
	 * <em>Label</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Label</em>'.
	 * @see org.eclipse.buckminster.aggregator.LabelProvider#getLabel()
	 * @see #getLabelProvider()
	 * @generated
	 */
	EAttribute getLabelProvider_Label();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.MappedRepository
	 * <em>Mapped Repository</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Mapped Repository</em>'.
	 * @see org.eclipse.buckminster.aggregator.MappedRepository
	 * @generated
	 */
	EClass getMappedRepository();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.aggregator.MappedRepository#getBundles <em>Bundles</em>}'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Bundles</em>'.
	 * @see org.eclipse.buckminster.aggregator.MappedRepository#getBundles()
	 * @see #getMappedRepository()
	 * @generated
	 */
	EReference getMappedRepository_Bundles();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.aggregator.MappedRepository#getCategories <em>Categories</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Categories</em>'.
	 * @see org.eclipse.buckminster.aggregator.MappedRepository#getCategories()
	 * @see #getMappedRepository()
	 * @generated
	 */
	EReference getMappedRepository_Categories();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.aggregator.MappedRepository#getCategoryPrefix <em>Category Prefix</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Category Prefix</em>'.
	 * @see org.eclipse.buckminster.aggregator.MappedRepository#getCategoryPrefix()
	 * @see #getMappedRepository()
	 * @generated
	 */
	EAttribute getMappedRepository_CategoryPrefix();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.aggregator.MappedRepository#getFeatures <em>Features</em>}'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Features</em>'.
	 * @see org.eclipse.buckminster.aggregator.MappedRepository#getFeatures()
	 * @see #getMappedRepository()
	 * @generated
	 */
	EReference getMappedRepository_Features();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.aggregator.MappedRepository#getMapRules <em>Map Rules</em>}'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Map Rules</em>'.
	 * @see org.eclipse.buckminster.aggregator.MappedRepository#getMapRules()
	 * @see #getMappedRepository()
	 * @generated
	 */
	EReference getMappedRepository_MapRules();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.aggregator.MappedRepository#isMirrorArtifacts <em>Mirror Artifacts</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Mirror Artifacts</em>'.
	 * @see org.eclipse.buckminster.aggregator.MappedRepository#isMirrorArtifacts()
	 * @see #getMappedRepository()
	 * @generated
	 */
	EAttribute getMappedRepository_MirrorArtifacts();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.aggregator.MappedRepository#getProducts <em>Products</em>}'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Products</em>'.
	 * @see org.eclipse.buckminster.aggregator.MappedRepository#getProducts()
	 * @see #getMappedRepository()
	 * @generated
	 */
	EReference getMappedRepository_Products();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.MappedUnit <em>Mapped Unit</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Mapped Unit</em>'.
	 * @see org.eclipse.buckminster.aggregator.MappedUnit
	 * @generated
	 */
	EClass getMappedUnit();

	/**
	 * Returns the meta object for the reference list '
	 * {@link org.eclipse.buckminster.aggregator.MappedUnit#getValidConfigurations <em>Valid Configurations</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference list '<em>Valid Configurations</em>'.
	 * @see org.eclipse.buckminster.aggregator.MappedUnit#getValidConfigurations()
	 * @see #getMappedUnit()
	 * @generated
	 */
	EReference getMappedUnit_ValidConfigurations();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.MapRule <em>Map Rule</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Map Rule</em>'.
	 * @see org.eclipse.buckminster.aggregator.MapRule
	 * @generated
	 */
	EClass getMapRule();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.MavenItem <em>Maven Item</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Maven Item</em>'.
	 * @see org.eclipse.buckminster.aggregator.MavenItem
	 * @generated
	 */
	EClass getMavenItem();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.aggregator.MavenItem#getArtifactId
	 * <em>Artifact Id</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Artifact Id</em>'.
	 * @see org.eclipse.buckminster.aggregator.MavenItem#getArtifactId()
	 * @see #getMavenItem()
	 * @generated
	 */
	EAttribute getMavenItem_ArtifactId();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.aggregator.MavenItem#getGroupId
	 * <em>Group Id</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Group Id</em>'.
	 * @see org.eclipse.buckminster.aggregator.MavenItem#getGroupId()
	 * @see #getMavenItem()
	 * @generated
	 */
	EAttribute getMavenItem_GroupId();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.MavenMapping <em>Maven Mapping</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Maven Mapping</em>'.
	 * @see org.eclipse.buckminster.aggregator.MavenMapping
	 * @generated
	 */
	EClass getMavenMapping();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.aggregator.MavenMapping#getArtifactId
	 * <em>Artifact Id</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Artifact Id</em>'.
	 * @see org.eclipse.buckminster.aggregator.MavenMapping#getArtifactId()
	 * @see #getMavenMapping()
	 * @generated
	 */
	EAttribute getMavenMapping_ArtifactId();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.aggregator.MavenMapping#getGroupId
	 * <em>Group Id</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Group Id</em>'.
	 * @see org.eclipse.buckminster.aggregator.MavenMapping#getGroupId()
	 * @see #getMavenMapping()
	 * @generated
	 */
	EAttribute getMavenMapping_GroupId();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.aggregator.MavenMapping#getNamePattern
	 * <em>Name Pattern</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Name Pattern</em>'.
	 * @see org.eclipse.buckminster.aggregator.MavenMapping#getNamePattern()
	 * @see #getMavenMapping()
	 * @generated
	 */
	EAttribute getMavenMapping_NamePattern();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.MetadataRepositoryReference
	 * <em>Metadata Repository Reference</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Metadata Repository Reference</em>'.
	 * @see org.eclipse.buckminster.aggregator.MetadataRepositoryReference
	 * @generated
	 */
	EClass getMetadataRepositoryReference();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.aggregator.MetadataRepositoryReference#getLocation <em>Location</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Location</em>'.
	 * @see org.eclipse.buckminster.aggregator.MetadataRepositoryReference#getLocation()
	 * @see #getMetadataRepositoryReference()
	 * @generated
	 */
	EAttribute getMetadataRepositoryReference_Location();

	/**
	 * Returns the meta object for the reference '
	 * {@link org.eclipse.buckminster.aggregator.MetadataRepositoryReference#getMetadataRepository
	 * <em>Metadata Repository</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Metadata Repository</em>'.
	 * @see org.eclipse.buckminster.aggregator.MetadataRepositoryReference#getMetadataRepository()
	 * @see #getMetadataRepositoryReference()
	 * @generated
	 */
	EReference getMetadataRepositoryReference_MetadataRepository();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.buckminster.aggregator.OperatingSystem
	 * <em>Operating System</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for enum '<em>Operating System</em>'.
	 * @see org.eclipse.buckminster.aggregator.OperatingSystem
	 * @generated
	 */
	EEnum getOperatingSystem();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.buckminster.aggregator.PackedStrategy
	 * <em>Packed Strategy</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for enum '<em>Packed Strategy</em>'.
	 * @see org.eclipse.buckminster.aggregator.PackedStrategy
	 * @generated
	 */
	EEnum getPackedStrategy();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.Product <em>Product</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Product</em>'.
	 * @see org.eclipse.buckminster.aggregator.Product
	 * @generated
	 */
	EClass getProduct();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.Property <em>Property</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Property</em>'.
	 * @see org.eclipse.buckminster.aggregator.Property
	 * @generated
	 */
	EClass getProperty();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.aggregator.Property#getKey
	 * <em>Key</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see org.eclipse.buckminster.aggregator.Property#getKey()
	 * @see #getProperty()
	 * @generated
	 */
	EAttribute getProperty_Key();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.aggregator.Property#getValue
	 * <em>Value</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.buckminster.aggregator.Property#getValue()
	 * @see #getProperty()
	 * @generated
	 */
	EAttribute getProperty_Value();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.StatusProvider
	 * <em>Status Provider</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Status Provider</em>'.
	 * @see org.eclipse.buckminster.aggregator.StatusProvider
	 * @generated
	 */
	EClass getStatusProvider();

	/**
	 * Returns the meta object for data type '{@link java.net.URI <em>URI</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>URI</em>'.
	 * @see java.net.URI
	 * @model instanceClass="java.net.URI"
	 * @generated
	 */
	EDataType getURI();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.ValidConfigurationsRule
	 * <em>Valid Configurations Rule</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Valid Configurations Rule</em>'.
	 * @see org.eclipse.buckminster.aggregator.ValidConfigurationsRule
	 * @generated
	 */
	EClass getValidConfigurationsRule();

	/**
	 * Returns the meta object for the reference list '
	 * {@link org.eclipse.buckminster.aggregator.ValidConfigurationsRule#getValidConfigurations
	 * <em>Valid Configurations</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference list '<em>Valid Configurations</em>'.
	 * @see org.eclipse.buckminster.aggregator.ValidConfigurationsRule#getValidConfigurations()
	 * @see #getValidConfigurationsRule()
	 * @generated
	 */
	EReference getValidConfigurationsRule_ValidConfigurations();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.buckminster.aggregator.WindowSystem <em>Window System</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for enum '<em>Window System</em>'.
	 * @see org.eclipse.buckminster.aggregator.WindowSystem
	 * @generated
	 */
	EEnum getWindowSystem();

} // AggregatorPackage
