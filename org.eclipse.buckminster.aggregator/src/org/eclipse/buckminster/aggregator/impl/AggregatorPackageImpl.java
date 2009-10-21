/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.impl;

import java.lang.Comparable;
import org.eclipse.buckminster.aggregator.AggregateType;
import org.eclipse.buckminster.aggregator.Aggregator;
import org.eclipse.buckminster.aggregator.AggregatorFactory;
import org.eclipse.buckminster.aggregator.AggregatorPackage;
import org.eclipse.buckminster.aggregator.Architecture;
import org.eclipse.buckminster.aggregator.Bundle;
import org.eclipse.buckminster.aggregator.Category;
import org.eclipse.buckminster.aggregator.Configuration;
import org.eclipse.buckminster.aggregator.Contact;
import org.eclipse.buckminster.aggregator.Contribution;
import org.eclipse.buckminster.aggregator.CustomCategory;
import org.eclipse.buckminster.aggregator.DescriptionProvider;
import org.eclipse.buckminster.aggregator.EnabledStatusProvider;
import org.eclipse.buckminster.aggregator.ExclusionRule;
import org.eclipse.buckminster.aggregator.Feature;
import org.eclipse.buckminster.aggregator.InstallableUnitReference;
import org.eclipse.buckminster.aggregator.LabelProvider;
import org.eclipse.buckminster.aggregator.MapRule;
import org.eclipse.buckminster.aggregator.MappedRepository;
import org.eclipse.buckminster.aggregator.MappedUnit;
import org.eclipse.buckminster.aggregator.MetadataRepositoryReference;
import org.eclipse.buckminster.aggregator.OperatingSystem;
import org.eclipse.buckminster.aggregator.PackedStrategy;
import org.eclipse.buckminster.aggregator.Product;
import org.eclipse.buckminster.aggregator.Property;
import org.eclipse.buckminster.aggregator.StatusProvider;
import org.eclipse.buckminster.aggregator.ValidConfigurationsRule;
import org.eclipse.buckminster.aggregator.WindowSystem;
import org.eclipse.buckminster.aggregator.p2.P2Package;
import org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl;
import org.eclipse.buckminster.aggregator.p2view.P2viewPackage;
import org.eclipse.buckminster.aggregator.p2view.impl.P2viewPackageImpl;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 * 
 * @generated
 */
public class AggregatorPackageImpl extends EPackageImpl implements AggregatorPackage
{
	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>
	 * This method is used to initialize {@link AggregatorPackage#eINSTANCE} when that field is accessed. Clients should
	 * not invoke it directly. Instead, they should simply access that field to obtain the package. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static AggregatorPackage init()
	{
		if(isInited)
			return (AggregatorPackage)EPackage.Registry.INSTANCE.getEPackage(AggregatorPackage.eNS_URI);

		// Obtain or create and register package
		AggregatorPackageImpl theAggregatorPackage = (AggregatorPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof AggregatorPackageImpl
				? EPackage.Registry.INSTANCE.get(eNS_URI)
				: new AggregatorPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		XMLTypePackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		P2PackageImpl theP2Package = (P2PackageImpl)(EPackage.Registry.INSTANCE.getEPackage(P2Package.eNS_URI) instanceof P2PackageImpl
				? EPackage.Registry.INSTANCE.getEPackage(P2Package.eNS_URI)
				: P2Package.eINSTANCE);
		P2viewPackageImpl theP2viewPackage = (P2viewPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(P2viewPackage.eNS_URI) instanceof P2viewPackageImpl
				? EPackage.Registry.INSTANCE.getEPackage(P2viewPackage.eNS_URI)
				: P2viewPackage.eINSTANCE);

		// Create package meta-data objects
		theAggregatorPackage.createPackageContents();
		theP2Package.createPackageContents();
		theP2viewPackage.createPackageContents();

		// Initialize created meta-data
		theAggregatorPackage.initializePackageContents();
		theP2Package.initializePackageContents();
		theP2viewPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theAggregatorPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(AggregatorPackage.eNS_URI, theAggregatorPackage);
		return theAggregatorPackage;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass aggregatorEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass mappedRepositoryEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass configurationEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass contributionEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass contactEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass featureEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass bundleEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass mappedUnitEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass productEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass propertyEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass categoryEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass customCategoryEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass enabledStatusProviderEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass mapRuleEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass installableUnitReferenceEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass exclusionRuleEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass validConfigurationsRuleEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass metadataRepositoryReferenceEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass statusProviderEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass comparableEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass labelProviderEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass descriptionProviderEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EEnum aggregateTypeEEnum = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EEnum operatingSystemEEnum = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EEnum windowSystemEEnum = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EEnum architectureEEnum = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EEnum packedStrategyEEnum = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EDataType uriEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with {@link org.eclipse.emf.ecore.EPackage.Registry
	 * EPackage.Registry} by the package package URI value.
	 * <p>
	 * Note: the correct way to create the package is via the static factory method {@link #init init()}, which also
	 * performs initialization of the package, or returns the registered package, if one already exists. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private AggregatorPackageImpl()
	{
		super(eNS_URI, AggregatorFactory.eINSTANCE);
	}

	/**
	 * Creates the meta-model objects for the package. This method is guarded to have no affect on any invocation but
	 * its first. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void createPackageContents()
	{
		if(isCreated)
			return;
		isCreated = true;

		// Create classes and their features
		aggregatorEClass = createEClass(AGGREGATOR);
		createEReference(aggregatorEClass, AGGREGATOR__CONFIGURATIONS);
		createEReference(aggregatorEClass, AGGREGATOR__CONTRIBUTIONS);
		createEReference(aggregatorEClass, AGGREGATOR__BUILDMASTER);
		createEReference(aggregatorEClass, AGGREGATOR__CONTACTS);
		createEReference(aggregatorEClass, AGGREGATOR__CUSTOM_CATEGORIES);
		createEAttribute(aggregatorEClass, AGGREGATOR__LABEL);
		createEAttribute(aggregatorEClass, AGGREGATOR__BUILD_ROOT);
		createEAttribute(aggregatorEClass, AGGREGATOR__PACKED_STRATEGY);
		createEAttribute(aggregatorEClass, AGGREGATOR__SENDMAIL);
		createEAttribute(aggregatorEClass, AGGREGATOR__TYPE);
		createEReference(aggregatorEClass, AGGREGATOR__VALIDATION_REPOSITORIES);

		mappedRepositoryEClass = createEClass(MAPPED_REPOSITORY);
		createEReference(mappedRepositoryEClass, MAPPED_REPOSITORY__PRODUCTS);
		createEReference(mappedRepositoryEClass, MAPPED_REPOSITORY__BUNDLES);
		createEReference(mappedRepositoryEClass, MAPPED_REPOSITORY__FEATURES);
		createEReference(mappedRepositoryEClass, MAPPED_REPOSITORY__CATEGORIES);
		createEAttribute(mappedRepositoryEClass, MAPPED_REPOSITORY__MIRROR_ARTIFACTS);
		createEAttribute(mappedRepositoryEClass, MAPPED_REPOSITORY__CATEGORY_PREFIX);
		createEReference(mappedRepositoryEClass, MAPPED_REPOSITORY__MAP_RULES);

		configurationEClass = createEClass(CONFIGURATION);
		createEAttribute(configurationEClass, CONFIGURATION__OPERATING_SYSTEM);
		createEAttribute(configurationEClass, CONFIGURATION__WINDOW_SYSTEM);
		createEAttribute(configurationEClass, CONFIGURATION__ARCHITECTURE);

		contributionEClass = createEClass(CONTRIBUTION);
		createEAttribute(contributionEClass, CONTRIBUTION__LABEL);
		createEReference(contributionEClass, CONTRIBUTION__REPOSITORIES);
		createEReference(contributionEClass, CONTRIBUTION__CONTACTS);

		contactEClass = createEClass(CONTACT);
		createEAttribute(contactEClass, CONTACT__NAME);
		createEAttribute(contactEClass, CONTACT__EMAIL);
		createEReference(contactEClass, CONTACT__AGGREGATOR);

		featureEClass = createEClass(FEATURE);
		createEReference(featureEClass, FEATURE__CATEGORIES);

		bundleEClass = createEClass(BUNDLE);

		mappedUnitEClass = createEClass(MAPPED_UNIT);
		createEReference(mappedUnitEClass, MAPPED_UNIT__VALID_CONFIGURATIONS);

		productEClass = createEClass(PRODUCT);

		propertyEClass = createEClass(PROPERTY);
		createEAttribute(propertyEClass, PROPERTY__KEY);
		createEAttribute(propertyEClass, PROPERTY__VALUE);

		categoryEClass = createEClass(CATEGORY);
		createEAttribute(categoryEClass, CATEGORY__LABEL_OVERRIDE);

		customCategoryEClass = createEClass(CUSTOM_CATEGORY);
		createEAttribute(customCategoryEClass, CUSTOM_CATEGORY__IDENTIFIER);
		createEAttribute(customCategoryEClass, CUSTOM_CATEGORY__LABEL);
		createEAttribute(customCategoryEClass, CUSTOM_CATEGORY__DESCRIPTION);
		createEReference(customCategoryEClass, CUSTOM_CATEGORY__FEATURES);

		enabledStatusProviderEClass = createEClass(ENABLED_STATUS_PROVIDER);
		createEAttribute(enabledStatusProviderEClass, ENABLED_STATUS_PROVIDER__ENABLED);

		mapRuleEClass = createEClass(MAP_RULE);

		installableUnitReferenceEClass = createEClass(INSTALLABLE_UNIT_REFERENCE);
		createEReference(installableUnitReferenceEClass, INSTALLABLE_UNIT_REFERENCE__INSTALLABLE_UNIT);

		exclusionRuleEClass = createEClass(EXCLUSION_RULE);

		validConfigurationsRuleEClass = createEClass(VALID_CONFIGURATIONS_RULE);
		createEReference(validConfigurationsRuleEClass, VALID_CONFIGURATIONS_RULE__VALID_CONFIGURATIONS);

		metadataRepositoryReferenceEClass = createEClass(METADATA_REPOSITORY_REFERENCE);
		createEReference(metadataRepositoryReferenceEClass, METADATA_REPOSITORY_REFERENCE__METADATA_REPOSITORY);
		createEAttribute(metadataRepositoryReferenceEClass, METADATA_REPOSITORY_REFERENCE__LOCATION);

		statusProviderEClass = createEClass(STATUS_PROVIDER);

		comparableEClass = createEClass(COMPARABLE);

		labelProviderEClass = createEClass(LABEL_PROVIDER);
		createEAttribute(labelProviderEClass, LABEL_PROVIDER__LABEL);

		descriptionProviderEClass = createEClass(DESCRIPTION_PROVIDER);
		createEAttribute(descriptionProviderEClass, DESCRIPTION_PROVIDER__DESCRIPTION);

		// Create enums
		aggregateTypeEEnum = createEEnum(AGGREGATE_TYPE);
		operatingSystemEEnum = createEEnum(OPERATING_SYSTEM);
		windowSystemEEnum = createEEnum(WINDOW_SYSTEM);
		architectureEEnum = createEEnum(ARCHITECTURE);
		packedStrategyEEnum = createEEnum(PACKED_STRATEGY);

		// Create data types
		uriEDataType = createEDataType(URI);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EEnum getAggregateType()
	{
		return aggregateTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getAggregator()
	{
		return aggregatorEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getAggregator_Buildmaster()
	{
		return (EReference)aggregatorEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getAggregator_BuildRoot()
	{
		return (EAttribute)aggregatorEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getAggregator_Configurations()
	{
		return (EReference)aggregatorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getAggregator_Contacts()
	{
		return (EReference)aggregatorEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getAggregator_Contributions()
	{
		return (EReference)aggregatorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getAggregator_CustomCategories()
	{
		return (EReference)aggregatorEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getAggregator_Label()
	{
		return (EAttribute)aggregatorEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getAggregator_PackedStrategy()
	{
		return (EAttribute)aggregatorEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getAggregator_Sendmail()
	{
		return (EAttribute)aggregatorEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getAggregator_Type()
	{
		return (EAttribute)aggregatorEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getAggregator_ValidationRepositories()
	{
		return (EReference)aggregatorEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public AggregatorFactory getAggregatorFactory()
	{
		return (AggregatorFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EEnum getArchitecture()
	{
		return architectureEEnum;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getBundle()
	{
		return bundleEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getCategory()
	{
		return categoryEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getCategory_LabelOverride()
	{
		return (EAttribute)categoryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getComparable()
	{
		return comparableEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getConfiguration()
	{
		return configurationEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getConfiguration_Architecture()
	{
		return (EAttribute)configurationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getConfiguration_OperatingSystem()
	{
		return (EAttribute)configurationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getConfiguration_WindowSystem()
	{
		return (EAttribute)configurationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getContact()
	{
		return contactEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getContact_Aggregator()
	{
		return (EReference)contactEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getContact_Email()
	{
		return (EAttribute)contactEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getContact_Name()
	{
		return (EAttribute)contactEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getContribution()
	{
		return contributionEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getContribution_Contacts()
	{
		return (EReference)contributionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getContribution_Label()
	{
		return (EAttribute)contributionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getContribution_Repositories()
	{
		return (EReference)contributionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getCustomCategory()
	{
		return customCategoryEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getCustomCategory_Description()
	{
		return (EAttribute)customCategoryEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getCustomCategory_Features()
	{
		return (EReference)customCategoryEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getCustomCategory_Identifier()
	{
		return (EAttribute)customCategoryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getCustomCategory_Label()
	{
		return (EAttribute)customCategoryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getDescriptionProvider()
	{
		return descriptionProviderEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getDescriptionProvider_Description()
	{
		return (EAttribute)descriptionProviderEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getEnabledStatusProvider()
	{
		return enabledStatusProviderEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getEnabledStatusProvider_Enabled()
	{
		return (EAttribute)enabledStatusProviderEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getExclusionRule()
	{
		return exclusionRuleEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getFeature()
	{
		return featureEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getFeature_Categories()
	{
		return (EReference)featureEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getInstallableUnitReference()
	{
		return installableUnitReferenceEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getInstallableUnitReference_InstallableUnit()
	{
		return (EReference)installableUnitReferenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getLabelProvider()
	{
		return labelProviderEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getLabelProvider_Label()
	{
		return (EAttribute)labelProviderEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getMappedRepository()
	{
		return mappedRepositoryEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getMappedRepository_Bundles()
	{
		return (EReference)mappedRepositoryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getMappedRepository_Categories()
	{
		return (EReference)mappedRepositoryEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getMappedRepository_CategoryPrefix()
	{
		return (EAttribute)mappedRepositoryEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getMappedRepository_Features()
	{
		return (EReference)mappedRepositoryEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getMappedRepository_MapRules()
	{
		return (EReference)mappedRepositoryEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getMappedRepository_MirrorArtifacts()
	{
		return (EAttribute)mappedRepositoryEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getMappedRepository_Products()
	{
		return (EReference)mappedRepositoryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getMappedUnit()
	{
		return mappedUnitEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getMappedUnit_ValidConfigurations()
	{
		return (EReference)mappedUnitEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getMapRule()
	{
		return mapRuleEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getMetadataRepositoryReference()
	{
		return metadataRepositoryReferenceEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getMetadataRepositoryReference_Location()
	{
		return (EAttribute)metadataRepositoryReferenceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getMetadataRepositoryReference_MetadataRepository()
	{
		return (EReference)metadataRepositoryReferenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EEnum getOperatingSystem()
	{
		return operatingSystemEEnum;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EEnum getPackedStrategy()
	{
		return packedStrategyEEnum;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getProduct()
	{
		return productEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getProperty()
	{
		return propertyEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getProperty_Key()
	{
		return (EAttribute)propertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getProperty_Value()
	{
		return (EAttribute)propertyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getStatusProvider()
	{
		return statusProviderEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EDataType getURI()
	{
		return uriEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getValidConfigurationsRule()
	{
		return validConfigurationsRuleEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getValidConfigurationsRule_ValidConfigurations()
	{
		return (EReference)validConfigurationsRuleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EEnum getWindowSystem()
	{
		return windowSystemEEnum;
	}

	/**
	 * Complete the initialization of the package and its meta-model. This method is guarded to have no affect on any
	 * invocation but its first. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void initializePackageContents()
	{
		if(isInitialized)
			return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		P2Package theP2Package = (P2Package)EPackage.Registry.INSTANCE.getEPackage(P2Package.eNS_URI);
		P2viewPackage theP2viewPackage = (P2viewPackage)EPackage.Registry.INSTANCE.getEPackage(P2viewPackage.eNS_URI);
		XMLTypePackage theXMLTypePackage = (XMLTypePackage)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);

		// Add subpackages
		getESubpackages().add(theP2Package);
		getESubpackages().add(theP2viewPackage);

		// Create type parameters
		addETypeParameter(comparableEClass, "T");

		// Set bounds for type parameters

		// Add supertypes to classes
		aggregatorEClass.getESuperTypes().add(this.getDescriptionProvider());
		mappedRepositoryEClass.getESuperTypes().add(this.getMetadataRepositoryReference());
		mappedRepositoryEClass.getESuperTypes().add(this.getDescriptionProvider());
		contributionEClass.getESuperTypes().add(this.getEnabledStatusProvider());
		contributionEClass.getESuperTypes().add(this.getDescriptionProvider());
		featureEClass.getESuperTypes().add(this.getMappedUnit());
		bundleEClass.getESuperTypes().add(this.getMappedUnit());
		mappedUnitEClass.getESuperTypes().add(this.getInstallableUnitReference());
		mappedUnitEClass.getESuperTypes().add(this.getEnabledStatusProvider());
		productEClass.getESuperTypes().add(this.getMappedUnit());
		EGenericType g1 = createEGenericType(this.getComparable());
		EGenericType g2 = createEGenericType(this.getProperty());
		g1.getETypeArguments().add(g2);
		propertyEClass.getEGenericSuperTypes().add(g1);
		categoryEClass.getESuperTypes().add(this.getMappedUnit());
		mapRuleEClass.getESuperTypes().add(this.getInstallableUnitReference());
		mapRuleEClass.getESuperTypes().add(this.getDescriptionProvider());
		installableUnitReferenceEClass.getESuperTypes().add(this.getStatusProvider());
		exclusionRuleEClass.getESuperTypes().add(this.getMapRule());
		validConfigurationsRuleEClass.getESuperTypes().add(this.getMapRule());
		metadataRepositoryReferenceEClass.getESuperTypes().add(this.getEnabledStatusProvider());

		// Initialize classes and features; add operations and parameters
		initEClass(aggregatorEClass, Aggregator.class, "Aggregator", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAggregator_Configurations(), this.getConfiguration(), null, "configurations", null, 1, -1,
				Aggregator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAggregator_Contributions(), this.getContribution(), null, "contributions", null, 0, -1,
				Aggregator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		getAggregator_Contributions().getEKeys().add(this.getContribution_Label());
		initEReference(getAggregator_Buildmaster(), this.getContact(), null, "buildmaster", null, 0, 1,
				Aggregator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		getAggregator_Buildmaster().getEKeys().add(this.getContact_Email());
		initEReference(getAggregator_Contacts(), this.getContact(), this.getContact_Aggregator(), "contacts", null, 0,
				-1, Aggregator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		getAggregator_Contacts().getEKeys().add(this.getContact_Email());
		initEReference(getAggregator_CustomCategories(), this.getCustomCategory(), null, "customCategories", null, 0,
				-1, Aggregator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		getAggregator_CustomCategories().getEKeys().add(this.getCustomCategory_Identifier());
		initEAttribute(getAggregator_Label(), ecorePackage.getEString(), "label", null, 1, 1, Aggregator.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAggregator_BuildRoot(), ecorePackage.getEString(), "buildRoot", "${user.home}/build", 0, 1,
				Aggregator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getAggregator_PackedStrategy(), this.getPackedStrategy(), "packedStrategy", null, 0, 1,
				Aggregator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getAggregator_Sendmail(), ecorePackage.getEBoolean(), "sendmail", null, 0, 1, Aggregator.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAggregator_Type(), this.getAggregateType(), "type", null, 1, 1, Aggregator.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAggregator_ValidationRepositories(), this.getMetadataRepositoryReference(), null,
				"validationRepositories", null, 0, -1, Aggregator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		EOperation op = addEOperation(aggregatorEClass, this.getContribution(), "getContributions", 0, -1, IS_UNIQUE,
				IS_ORDERED);
		addEParameter(op, ecorePackage.getEBoolean(), "enabledOnly", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(aggregatorEClass, this.getMetadataRepositoryReference(), "getValidationRepositories", 0, -1,
				IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEBoolean(), "enabledOnly", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(aggregatorEClass, this.getMetadataRepositoryReference(),
				"getAllMetadataRepositoryReferences", 0, -1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEBoolean(), "enabledOnly", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(mappedRepositoryEClass, MappedRepository.class, "MappedRepository", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMappedRepository_Products(), this.getProduct(), null, "products", null, 0, -1,
				MappedRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMappedRepository_Bundles(), this.getBundle(), null, "bundles", null, 0, -1,
				MappedRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMappedRepository_Features(), this.getFeature(), null, "features", null, 0, -1,
				MappedRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMappedRepository_Categories(), this.getCategory(), null, "categories", null, 0, -1,
				MappedRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMappedRepository_MirrorArtifacts(), ecorePackage.getEBoolean(), "mirrorArtifacts", "true", 0,
				1, MappedRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMappedRepository_CategoryPrefix(), ecorePackage.getEString(), "categoryPrefix", null, 0, 1,
				MappedRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getMappedRepository_MapRules(), this.getMapRule(), null, "mapRules", null, 0, -1,
				MappedRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(mappedRepositoryEClass, this.getMappedUnit(), "getUnits", 0, -1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEBoolean(), "enabledOnly", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(mappedRepositoryEClass, ecorePackage.getEBoolean(), "isMapExclusive", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(configurationEClass, Configuration.class, "Configuration", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getConfiguration_OperatingSystem(), this.getOperatingSystem(), "operatingSystem", null, 1, 1,
				Configuration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getConfiguration_WindowSystem(), this.getWindowSystem(), "windowSystem", null, 1, 1,
				Configuration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getConfiguration_Architecture(), this.getArchitecture(), "architecture", null, 1, 1,
				Configuration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		addEOperation(configurationEClass, ecorePackage.getEString(), "getName", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(configurationEClass, ecorePackage.getEString(), "getOSGiEnvironmentString", 0, 1, IS_UNIQUE,
				IS_ORDERED);

		initEClass(contributionEClass, Contribution.class, "Contribution", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getContribution_Label(), ecorePackage.getEString(), "label", null, 1, 1, Contribution.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContribution_Repositories(), this.getMappedRepository(), null, "repositories", null, 0, -1,
				Contribution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContribution_Contacts(), this.getContact(), null, "contacts", null, 0, -1,
				Contribution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		getContribution_Contacts().getEKeys().add(this.getContact_Email());

		op = addEOperation(contributionEClass, this.getMappedRepository(), "getRepositories", 0, -1, IS_UNIQUE,
				IS_ORDERED);
		addEParameter(op, ecorePackage.getEBoolean(), "enabledOnly", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(contactEClass, Contact.class, "Contact", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getContact_Name(), ecorePackage.getEString(), "name", null, 0, 1, Contact.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getContact_Email(), ecorePackage.getEString(), "email", null, 1, 1, Contact.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContact_Aggregator(), this.getAggregator(), this.getAggregator_Contacts(), "aggregator",
				null, 1, 1, Contact.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(featureEClass, Feature.class, "Feature", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFeature_Categories(), this.getCustomCategory(), this.getCustomCategory_Features(),
				"categories", null, 0, -1, Feature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
				IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(bundleEClass, Bundle.class, "Bundle", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(mappedUnitEClass, MappedUnit.class, "MappedUnit", IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMappedUnit_ValidConfigurations(), this.getConfiguration(), null, "validConfigurations", null,
				0, -1, MappedUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		getMappedUnit_ValidConfigurations().getEKeys().add(this.getConfiguration_OperatingSystem());
		getMappedUnit_ValidConfigurations().getEKeys().add(this.getConfiguration_WindowSystem());
		getMappedUnit_ValidConfigurations().getEKeys().add(this.getConfiguration_Architecture());

		initEClass(productEClass, Product.class, "Product", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(propertyEClass, Property.class, "Property", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getProperty_Key(), ecorePackage.getEString(), "key", null, 1, 1, Property.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProperty_Value(), ecorePackage.getEString(), "value", null, 0, 1, Property.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(categoryEClass, Category.class, "Category", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCategory_LabelOverride(), ecorePackage.getEString(), "labelOverride", null, 0, 1,
				Category.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(customCategoryEClass, CustomCategory.class, "CustomCategory", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCustomCategory_Identifier(), ecorePackage.getEString(), "identifier", null, 1, 1,
				CustomCategory.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomCategory_Label(), ecorePackage.getEString(), "label", null, 0, 1, CustomCategory.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomCategory_Description(), ecorePackage.getEString(), "description", null, 0, 1,
				CustomCategory.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getCustomCategory_Features(), this.getFeature(), this.getFeature_Categories(), "features", null,
				0, -1, CustomCategory.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
				IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(enabledStatusProviderEClass, EnabledStatusProvider.class, "EnabledStatusProvider", IS_ABSTRACT,
				IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEnabledStatusProvider_Enabled(), ecorePackage.getEBoolean(), "enabled", "true", 0, 1,
				EnabledStatusProvider.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(mapRuleEClass, MapRule.class, "MapRule", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(installableUnitReferenceEClass, InstallableUnitReference.class, "InstallableUnitReference",
				IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getInstallableUnitReference_InstallableUnit(), theP2Package.getInstallableUnit(), null,
				"installableUnit", null, 0, 1, InstallableUnitReference.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		getInstallableUnitReference_InstallableUnit().getEKeys().add(theP2Package.getIInstallableUnit_Id());
		getInstallableUnitReference_InstallableUnit().getEKeys().add(theP2Package.getIInstallableUnit_Version());

		op = addEOperation(installableUnitReferenceEClass, theP2Package.getInstallableUnit(), "getInstallableUnit", 0,
				1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEBoolean(), "forceResolve", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(installableUnitReferenceEClass, ecorePackage.getEBoolean(), "isMappedRepositoryBroken", 0, 1,
				IS_UNIQUE, IS_ORDERED);

		addEOperation(installableUnitReferenceEClass, ecorePackage.getEBoolean(), "isBranchEnabled", 0, 1, IS_UNIQUE,
				IS_ORDERED);

		initEClass(exclusionRuleEClass, ExclusionRule.class, "ExclusionRule", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);

		initEClass(validConfigurationsRuleEClass, ValidConfigurationsRule.class, "ValidConfigurationsRule",
				!IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getValidConfigurationsRule_ValidConfigurations(), this.getConfiguration(), null,
				"validConfigurations", null, 0, -1, ValidConfigurationsRule.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(metadataRepositoryReferenceEClass, MetadataRepositoryReference.class, "MetadataRepositoryReference",
				!IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMetadataRepositoryReference_MetadataRepository(), theP2Package.getMetadataRepository(), null,
				"metadataRepository", null, 0, 1, MetadataRepositoryReference.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMetadataRepositoryReference_Location(), ecorePackage.getEString(), "location", null, 1, 1,
				MetadataRepositoryReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(metadataRepositoryReferenceEClass, this.getAggregator(), "getAggregator", 0, 1, IS_UNIQUE,
				IS_ORDERED);

		op = addEOperation(metadataRepositoryReferenceEClass, theP2Package.getMetadataRepository(),
				"getMetadataRepository", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEBoolean(), "forceResolve", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(metadataRepositoryReferenceEClass, ecorePackage.getEBoolean(), "isBranchEnabled", 0, 1,
				IS_UNIQUE, IS_ORDERED);

		addEOperation(metadataRepositoryReferenceEClass, ecorePackage.getEString(), "getResolvedLocation", 0, 1,
				IS_UNIQUE, IS_ORDERED);

		initEClass(statusProviderEClass, StatusProvider.class, "StatusProvider", IS_ABSTRACT, IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);

		addEOperation(statusProviderEClass, theXMLTypePackage.getInt(), "getStatus", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(comparableEClass, Comparable.class, "Comparable", IS_ABSTRACT, IS_INTERFACE,
				!IS_GENERATED_INSTANCE_CLASS);

		initEClass(labelProviderEClass, LabelProvider.class, "LabelProvider", IS_ABSTRACT, IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLabelProvider_Label(), theXMLTypePackage.getString(), "label", null, 0, 1,
				LabelProvider.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(descriptionProviderEClass, DescriptionProvider.class, "DescriptionProvider", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDescriptionProvider_Description(), theXMLTypePackage.getString(), "description", "", 0, 1,
				DescriptionProvider.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(aggregateTypeEEnum, AggregateType.class, "AggregateType");
		addEEnumLiteral(aggregateTypeEEnum, AggregateType.STABLE);
		addEEnumLiteral(aggregateTypeEEnum, AggregateType.INTEGRATION);
		addEEnumLiteral(aggregateTypeEEnum, AggregateType.NIGHTLY);
		addEEnumLiteral(aggregateTypeEEnum, AggregateType.MAINTENANCE);
		addEEnumLiteral(aggregateTypeEEnum, AggregateType.CONTINUOUS);
		addEEnumLiteral(aggregateTypeEEnum, AggregateType.RELEASE);

		initEEnum(operatingSystemEEnum, OperatingSystem.class, "OperatingSystem");
		addEEnumLiteral(operatingSystemEEnum, OperatingSystem.WIN32);
		addEEnumLiteral(operatingSystemEEnum, OperatingSystem.LINUX);
		addEEnumLiteral(operatingSystemEEnum, OperatingSystem.MAC_OSX);

		initEEnum(windowSystemEEnum, WindowSystem.class, "WindowSystem");
		addEEnumLiteral(windowSystemEEnum, WindowSystem.WIN32);
		addEEnumLiteral(windowSystemEEnum, WindowSystem.GTK);
		addEEnumLiteral(windowSystemEEnum, WindowSystem.CARBON);
		addEEnumLiteral(windowSystemEEnum, WindowSystem.COCOA);

		initEEnum(architectureEEnum, Architecture.class, "Architecture");
		addEEnumLiteral(architectureEEnum, Architecture.X86);
		addEEnumLiteral(architectureEEnum, Architecture.PPC);
		addEEnumLiteral(architectureEEnum, Architecture.X86_64);

		initEEnum(packedStrategyEEnum, PackedStrategy.class, "PackedStrategy");
		addEEnumLiteral(packedStrategyEEnum, PackedStrategy.COPY);
		addEEnumLiteral(packedStrategyEEnum, PackedStrategy.VERIFY);
		addEEnumLiteral(packedStrategyEEnum, PackedStrategy.UNPACK_AS_SIBLING);
		addEEnumLiteral(packedStrategyEEnum, PackedStrategy.UNPACK);
		addEEnumLiteral(packedStrategyEEnum, PackedStrategy.SKIP);

		// Initialize data types
		initEDataType(uriEDataType, java.net.URI.class, "URI", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} // AggregatorPackageImpl
