/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.impl;

import java.net.URI;

import org.eclipse.buckminster.aggregator.*;
import org.eclipse.buckminster.aggregator.p2.InstallableUnit;
import org.eclipse.buckminster.aggregator.p2.MetadataRepository;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!-- end-user-doc -->
 * 
 * @generated
 */
public class AggregatorFactoryImpl extends EFactoryImpl implements AggregatorFactory
{
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static AggregatorPackage getPackage()
	{
		return AggregatorPackage.eINSTANCE;
	}

	/**
	 * Creates the default factory implementation. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static AggregatorFactory init()
	{
		try
		{
			AggregatorFactory theAggregatorFactory = (AggregatorFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.eclipse.org/buckminster/2009/aggregator");
			if(theAggregatorFactory != null)
			{
				return theAggregatorFactory;
			}
		}
		catch(Exception exception)
		{
			EcorePlugin.INSTANCE.log(exception);
		}
		return new AggregatorFactoryImpl();
	}

	/**
	 * Creates an instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public AggregatorFactoryImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String convertAggregateTypeToString(EDataType eDataType, Object instanceValue)
	{
		return instanceValue == null
				? null
				: instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String convertArchitectureToString(EDataType eDataType, Object instanceValue)
	{
		return instanceValue == null
				? null
				: instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String convertOperatingSystemToString(EDataType eDataType, Object instanceValue)
	{
		return instanceValue == null
				? null
				: instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String convertPackedStrategyToString(EDataType eDataType, Object instanceValue)
	{
		return instanceValue == null
				? null
				: instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue)
	{
		switch(eDataType.getClassifierID())
		{
		case AggregatorPackage.AGGREGATE_TYPE:
			return convertAggregateTypeToString(eDataType, instanceValue);
		case AggregatorPackage.OPERATING_SYSTEM:
			return convertOperatingSystemToString(eDataType, instanceValue);
		case AggregatorPackage.WINDOW_SYSTEM:
			return convertWindowSystemToString(eDataType, instanceValue);
		case AggregatorPackage.ARCHITECTURE:
			return convertArchitectureToString(eDataType, instanceValue);
		case AggregatorPackage.PACKED_STRATEGY:
			return convertPackedStrategyToString(eDataType, instanceValue);
		case AggregatorPackage.URI:
			return convertURIToString(eDataType, instanceValue);
		default:
			throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String convertURIToString(EDataType eDataType, Object instanceValue)
	{
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String convertWindowSystemToString(EDataType eDataType, Object instanceValue)
	{
		return instanceValue == null
				? null
				: instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass)
	{
		switch(eClass.getClassifierID())
		{
		case AggregatorPackage.AGGREGATOR:
			return createAggregator();
		case AggregatorPackage.MAPPED_REPOSITORY:
			return createMappedRepository();
		case AggregatorPackage.CONFIGURATION:
			return createConfiguration();
		case AggregatorPackage.CONTRIBUTION:
			return createContribution();
		case AggregatorPackage.CONTACT:
			return createContact();
		case AggregatorPackage.FEATURE:
			return createFeature();
		case AggregatorPackage.BUNDLE:
			return createBundle();
		case AggregatorPackage.PRODUCT:
			return createProduct();
		case AggregatorPackage.PROPERTY:
			return createProperty();
		case AggregatorPackage.CATEGORY:
			return createCategory();
		case AggregatorPackage.CUSTOM_CATEGORY:
			return createCustomCategory();
		case AggregatorPackage.EXCLUSION_RULE:
			return createExclusionRule();
		case AggregatorPackage.VALID_CONFIGURATIONS_RULE:
			return createValidConfigurationsRule();
		case AggregatorPackage.METADATA_REPOSITORY_REFERENCE:
			return createMetadataRepositoryReference();
		default:
			throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public AggregateType createAggregateTypeFromString(EDataType eDataType, String initialValue)
	{
		AggregateType result = AggregateType.get(initialValue);
		if(result == null)
			throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '"
					+ eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Aggregator createAggregator()
	{
		AggregatorImpl aggregator = new AggregatorImpl();
		return aggregator;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Architecture createArchitectureFromString(EDataType eDataType, String initialValue)
	{
		Architecture result = Architecture.get(initialValue);
		if(result == null)
			throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '"
					+ eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Bundle createBundle()
	{
		BundleImpl bundle = new BundleImpl();
		return bundle;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Category createCategory()
	{
		CategoryImpl category = new CategoryImpl();
		return category;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Configuration createConfiguration()
	{
		ConfigurationImpl configuration = new ConfigurationImpl();
		return configuration;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Contact createContact()
	{
		ContactImpl contact = new ContactImpl();
		return contact;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Contribution createContribution()
	{
		ContributionImpl contribution = new ContributionImpl();
		return contribution;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public CustomCategory createCustomCategory()
	{
		CustomCategoryImpl customCategory = new CustomCategoryImpl();
		return customCategory;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ExclusionRule createExclusionRule()
	{
		ExclusionRuleImpl exclusionRule = new ExclusionRuleImpl();
		return exclusionRule;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Feature createFeature()
	{
		FeatureImpl feature = new FeatureImpl();
		return feature;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue)
	{
		switch(eDataType.getClassifierID())
		{
		case AggregatorPackage.AGGREGATE_TYPE:
			return createAggregateTypeFromString(eDataType, initialValue);
		case AggregatorPackage.OPERATING_SYSTEM:
			return createOperatingSystemFromString(eDataType, initialValue);
		case AggregatorPackage.WINDOW_SYSTEM:
			return createWindowSystemFromString(eDataType, initialValue);
		case AggregatorPackage.ARCHITECTURE:
			return createArchitectureFromString(eDataType, initialValue);
		case AggregatorPackage.PACKED_STRATEGY:
			return createPackedStrategyFromString(eDataType, initialValue);
		case AggregatorPackage.URI:
			return createURIFromString(eDataType, initialValue);
		default:
			throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public MappedRepository createMappedRepository()
	{
		MappedRepositoryImpl mappedRepository = new MappedRepositoryImpl();
		return mappedRepository;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public MappedRepository createMappedRepository(MetadataRepository mdr)
	{
		MappedRepository mappedRepo = createMappedRepository();
		mappedRepo.setMetadataRepository(mdr);
		mappedRepo.setLocation(mdr.getLocation().toString());

		return mappedRepo;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public MappedUnit createMappedUnit(InstallableUnit iu)
	{
		MappedUnit mu = null;

		switch(iu.getType())
		{
		case FEATURE:
			mu = createFeature();
			break;
		case CATEGORY:
			mu = createCategory();
			break;
		case BUNDLE:
		case FRAGMENT:
			mu = createBundle();
			break;
		case PRODUCT:
			mu = createProduct();
			break;
		default:
			throw new IllegalArgumentException("Unknown IU type");
		}

		mu.setInstallableUnit(iu);

		return mu;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public MetadataRepositoryReference createMetadataRepositoryReference()
	{
		MetadataRepositoryReferenceImpl metadataRepositoryReference = new MetadataRepositoryReferenceImpl();
		return metadataRepositoryReference;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public OperatingSystem createOperatingSystemFromString(EDataType eDataType, String initialValue)
	{
		OperatingSystem result = OperatingSystem.get(initialValue);
		if(result == null)
			throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '"
					+ eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public PackedStrategy createPackedStrategyFromString(EDataType eDataType, String initialValue)
	{
		PackedStrategy result = PackedStrategy.get(initialValue);
		if(result == null)
			throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '"
					+ eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Product createProduct()
	{
		ProductImpl product = new ProductImpl();
		return product;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Property createProperty()
	{
		PropertyImpl property = new PropertyImpl();
		return property;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public URI createURIFromString(EDataType eDataType, String initialValue)
	{
		return (URI)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ValidConfigurationsRule createValidConfigurationsRule()
	{
		ValidConfigurationsRuleImpl validConfigurationsRule = new ValidConfigurationsRuleImpl();
		return validConfigurationsRule;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public WindowSystem createWindowSystemFromString(EDataType eDataType, String initialValue)
	{
		WindowSystem result = WindowSystem.get(initialValue);
		if(result == null)
			throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '"
					+ eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public AggregatorPackage getAggregatorPackage()
	{
		return (AggregatorPackage)getEPackage();
	}

} // AggregatorFactoryImpl
