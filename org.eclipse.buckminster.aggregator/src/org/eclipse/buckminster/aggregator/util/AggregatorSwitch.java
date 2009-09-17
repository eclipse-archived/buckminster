/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.util;

import java.util.List;

import org.eclipse.buckminster.aggregator.*;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance hierarchy. It supports the call
 * {@link #doSwitch(EObject) doSwitch(object)} to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object and proceeding up the inheritance hierarchy until a non-null result is
 * returned, which is the result of the switch. <!-- end-user-doc -->
 * 
 * @see org.eclipse.buckminster.aggregator.AggregatorPackage
 * @generated
 */
public class AggregatorSwitch<T>
{
	/**
	 * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected static AggregatorPackage modelPackage;

	/**
	 * Creates an instance of the switch. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public AggregatorSwitch()
	{
		if(modelPackage == null)
		{
			modelPackage = AggregatorPackage.eINSTANCE;
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Aggregator</em>'. <!-- begin-user-doc -->
	 * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Aggregator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAggregator(Aggregator object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Bundle</em>'. <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Bundle</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBundle(Bundle object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Category</em>'. <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Category</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCategory(Category object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Configuration</em>'. <!-- begin-user-doc -->
	 * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Configuration</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConfiguration(Configuration object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Contact</em>'. <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Contact</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseContact(Contact object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Contribution</em>'. <!-- begin-user-doc -->
	 * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Contribution</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseContribution(Contribution object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Custom Category</em>'. <!-- begin-user-doc
	 * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
	 * -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Custom Category</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCustomCategory(CustomCategory object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Enabled Status Provider</em>'. <!--
	 * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Enabled Status Provider</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEnabledStatusProvider(EnabledStatusProvider object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Exclusion Rule</em>'. <!-- begin-user-doc
	 * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
	 * -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Exclusion Rule</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExclusionRule(ExclusionRule object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Feature</em>'. <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Feature</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFeature(Feature object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Installable Unit Reference</em>'. <!--
	 * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Installable Unit Reference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInstallableUnitReference(InstallableUnitReference object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Mapped Repository</em>'. <!-- begin-user-doc
	 * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
	 * -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Mapped Repository</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMappedRepository(MappedRepository object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Mapped Unit</em>'. <!-- begin-user-doc -->
	 * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Mapped Unit</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMappedUnit(MappedUnit object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Map Rule</em>'. <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Map Rule</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMapRule(MapRule object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Metadata Repository Reference</em>'. <!--
	 * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Metadata Repository Reference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMetadataRepositoryReference(MetadataRepositoryReference object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Product</em>'. <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Product</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProduct(Product object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Property</em>'. <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProperty(Property object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Valid Configurations Rule</em>'. <!--
	 * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Valid Configurations Rule</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseValidConfigurationsRule(ValidConfigurationsRule object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'. <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate the switch, but this is the last case
	 * anyway. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	public T defaultCase(EObject object)
	{
		return null;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that
	 * result. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public T doSwitch(EObject theEObject)
	{
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that
	 * result. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(EClass theEClass, EObject theEObject)
	{
		if(theEClass.eContainer() == modelPackage)
		{
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else
		{
			List<EClass> eSuperTypes = theEClass.getESuperTypes();
			return eSuperTypes.isEmpty()
					? defaultCase(theEObject)
					: doSwitch(eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that
	 * result. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(int classifierID, EObject theEObject)
	{
		switch(classifierID)
		{
		case AggregatorPackage.AGGREGATOR:
		{
			Aggregator aggregator = (Aggregator)theEObject;
			T result = caseAggregator(aggregator);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AggregatorPackage.MAPPED_REPOSITORY:
		{
			MappedRepository mappedRepository = (MappedRepository)theEObject;
			T result = caseMappedRepository(mappedRepository);
			if(result == null)
				result = caseMetadataRepositoryReference(mappedRepository);
			if(result == null)
				result = caseEnabledStatusProvider(mappedRepository);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AggregatorPackage.CONFIGURATION:
		{
			Configuration configuration = (Configuration)theEObject;
			T result = caseConfiguration(configuration);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AggregatorPackage.CONTRIBUTION:
		{
			Contribution contribution = (Contribution)theEObject;
			T result = caseContribution(contribution);
			if(result == null)
				result = caseEnabledStatusProvider(contribution);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AggregatorPackage.CONTACT:
		{
			Contact contact = (Contact)theEObject;
			T result = caseContact(contact);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AggregatorPackage.FEATURE:
		{
			Feature feature = (Feature)theEObject;
			T result = caseFeature(feature);
			if(result == null)
				result = caseMappedUnit(feature);
			if(result == null)
				result = caseInstallableUnitReference(feature);
			if(result == null)
				result = caseEnabledStatusProvider(feature);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AggregatorPackage.BUNDLE:
		{
			Bundle bundle = (Bundle)theEObject;
			T result = caseBundle(bundle);
			if(result == null)
				result = caseMappedUnit(bundle);
			if(result == null)
				result = caseInstallableUnitReference(bundle);
			if(result == null)
				result = caseEnabledStatusProvider(bundle);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AggregatorPackage.MAPPED_UNIT:
		{
			MappedUnit mappedUnit = (MappedUnit)theEObject;
			T result = caseMappedUnit(mappedUnit);
			if(result == null)
				result = caseInstallableUnitReference(mappedUnit);
			if(result == null)
				result = caseEnabledStatusProvider(mappedUnit);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AggregatorPackage.PRODUCT:
		{
			Product product = (Product)theEObject;
			T result = caseProduct(product);
			if(result == null)
				result = caseMappedUnit(product);
			if(result == null)
				result = caseInstallableUnitReference(product);
			if(result == null)
				result = caseEnabledStatusProvider(product);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AggregatorPackage.PROPERTY:
		{
			Property property = (Property)theEObject;
			T result = caseProperty(property);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AggregatorPackage.CATEGORY:
		{
			Category category = (Category)theEObject;
			T result = caseCategory(category);
			if(result == null)
				result = caseMappedUnit(category);
			if(result == null)
				result = caseInstallableUnitReference(category);
			if(result == null)
				result = caseEnabledStatusProvider(category);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AggregatorPackage.CUSTOM_CATEGORY:
		{
			CustomCategory customCategory = (CustomCategory)theEObject;
			T result = caseCustomCategory(customCategory);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AggregatorPackage.ENABLED_STATUS_PROVIDER:
		{
			EnabledStatusProvider enabledStatusProvider = (EnabledStatusProvider)theEObject;
			T result = caseEnabledStatusProvider(enabledStatusProvider);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AggregatorPackage.MAP_RULE:
		{
			MapRule mapRule = (MapRule)theEObject;
			T result = caseMapRule(mapRule);
			if(result == null)
				result = caseInstallableUnitReference(mapRule);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AggregatorPackage.INSTALLABLE_UNIT_REFERENCE:
		{
			InstallableUnitReference installableUnitReference = (InstallableUnitReference)theEObject;
			T result = caseInstallableUnitReference(installableUnitReference);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AggregatorPackage.EXCLUSION_RULE:
		{
			ExclusionRule exclusionRule = (ExclusionRule)theEObject;
			T result = caseExclusionRule(exclusionRule);
			if(result == null)
				result = caseMapRule(exclusionRule);
			if(result == null)
				result = caseInstallableUnitReference(exclusionRule);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AggregatorPackage.VALID_CONFIGURATIONS_RULE:
		{
			ValidConfigurationsRule validConfigurationsRule = (ValidConfigurationsRule)theEObject;
			T result = caseValidConfigurationsRule(validConfigurationsRule);
			if(result == null)
				result = caseMapRule(validConfigurationsRule);
			if(result == null)
				result = caseInstallableUnitReference(validConfigurationsRule);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AggregatorPackage.METADATA_REPOSITORY_REFERENCE:
		{
			MetadataRepositoryReference metadataRepositoryReference = (MetadataRepositoryReference)theEObject;
			T result = caseMetadataRepositoryReference(metadataRepositoryReference);
			if(result == null)
				result = caseEnabledStatusProvider(metadataRepositoryReference);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		default:
			return defaultCase(theEObject);
		}
	}

} // AggregatorSwitch
