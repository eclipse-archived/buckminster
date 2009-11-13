/**
 * Copyright (c) 2006-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.p2view.util;

import java.util.List;

import org.eclipse.buckminster.aggregator.ChildrenProvider;
import org.eclipse.buckminster.aggregator.LabelProvider;
import org.eclipse.buckminster.aggregator.p2view.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.equinox.internal.provisional.p2.metadata.IProvidedCapability;
import org.eclipse.equinox.internal.provisional.p2.metadata.IRequiredCapability;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance hierarchy. It supports the call
 * {@link #doSwitch(EObject) doSwitch(object)} to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object and proceeding up the inheritance hierarchy until a non-null result is
 * returned, which is the result of the switch. <!-- end-user-doc -->
 * 
 * @see org.eclipse.buckminster.aggregator.p2view.P2viewPackage
 * @generated
 */
public class P2viewSwitch<T1>
{
	/**
	 * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected static P2viewPackage modelPackage;

	/**
	 * Creates an instance of the switch. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public P2viewSwitch()
	{
		if(modelPackage == null)
		{
			modelPackage = P2viewPackage.eINSTANCE;
		}
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
	public T1 caseBundle(Bundle object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Bundles</em>'. <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Bundles</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseBundles(Bundles object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Categories</em>'. <!-- begin-user-doc -->
	 * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Categories</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseCategories(Categories object)
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
	public T1 caseCategory(Category object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Children Provider</em>'. <!-- begin-user-doc
	 * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
	 * -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Children Provider</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T> T1 caseChildrenProvider(ChildrenProvider<T> object)
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
	public T1 caseFeature(Feature object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Features</em>'. <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Features</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseFeatures(Features object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Fragment</em>'. <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Fragment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseFragment(Fragment object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Fragments</em>'. <!-- begin-user-doc -->
	 * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Fragments</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseFragments(Fragments object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Installable Units</em>'. <!-- begin-user-doc
	 * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
	 * -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Installable Units</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseInstallableUnits(InstallableUnits object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IProvided Capability</em>'. <!--
	 * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IProvided Capability</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseIProvidedCapability(IProvidedCapability object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IRequired Capability</em>'. <!--
	 * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IRequired Capability</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseIRequiredCapability(IRequiredCapability object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IU Details</em>'. <!-- begin-user-doc -->
	 * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IU Details</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseIUDetails(IUDetails object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IU Presentation</em>'. <!-- begin-user-doc
	 * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
	 * -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IU Presentation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseIUPresentation(IUPresentation object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IU Presentation With Details</em>'. <!--
	 * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IU Presentation With Details</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseIUPresentationWithDetails(IUPresentationWithDetails object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Label Provider</em>'. <!-- begin-user-doc
	 * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
	 * -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Label Provider</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseLabelProvider(LabelProvider object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Metadata Repository Structured View</em>'.
	 * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Metadata Repository Structured View</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseMetadataRepositoryStructuredView(MetadataRepositoryStructuredView object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Miscellaneous</em>'. <!-- begin-user-doc -->
	 * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Miscellaneous</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseMiscellaneous(Miscellaneous object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Other IU</em>'. <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Other IU</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseOtherIU(OtherIU object)
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
	public T1 caseProduct(Product object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Products</em>'. <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Products</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseProducts(Products object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Properties</em>'. <!-- begin-user-doc -->
	 * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Properties</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseProperties(Properties object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Provided Capabilities</em>'. <!--
	 * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Provided Capabilities</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseProvidedCapabilities(ProvidedCapabilities object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Provided Capability Wrapper</em>'. <!--
	 * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Provided Capability Wrapper</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseProvidedCapabilityWrapper(ProvidedCapabilityWrapper object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Required Capabilities</em>'. <!--
	 * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Required Capabilities</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseRequiredCapabilities(RequiredCapabilities object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Required Capability Wrapper</em>'. <!--
	 * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Required Capability Wrapper</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseRequiredCapabilityWrapper(RequiredCapabilityWrapper object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Touchpoints</em>'. <!-- begin-user-doc -->
	 * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Touchpoints</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseTouchpoints(Touchpoints object)
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
	public T1 defaultCase(EObject object)
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
	public T1 doSwitch(EObject theEObject)
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
	protected T1 doSwitch(EClass theEClass, EObject theEObject)
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
	protected T1 doSwitch(int classifierID, EObject theEObject)
	{
		switch(classifierID)
		{
		case P2viewPackage.METADATA_REPOSITORY_STRUCTURED_VIEW:
		{
			MetadataRepositoryStructuredView metadataRepositoryStructuredView = (MetadataRepositoryStructuredView)theEObject;
			T1 result = caseMetadataRepositoryStructuredView(metadataRepositoryStructuredView);
			if(result == null)
				result = caseChildrenProvider(metadataRepositoryStructuredView);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case P2viewPackage.INSTALLABLE_UNITS:
		{
			InstallableUnits installableUnits = (InstallableUnits)theEObject;
			T1 result = caseInstallableUnits(installableUnits);
			if(result == null)
				result = caseChildrenProvider(installableUnits);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case P2viewPackage.CATEGORIES:
		{
			Categories categories = (Categories)theEObject;
			T1 result = caseCategories(categories);
			if(result == null)
				result = caseChildrenProvider(categories);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case P2viewPackage.FEATURES:
		{
			Features features = (Features)theEObject;
			T1 result = caseFeatures(features);
			if(result == null)
				result = caseChildrenProvider(features);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case P2viewPackage.PRODUCTS:
		{
			Products products = (Products)theEObject;
			T1 result = caseProducts(products);
			if(result == null)
				result = caseChildrenProvider(products);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case P2viewPackage.BUNDLES:
		{
			Bundles bundles = (Bundles)theEObject;
			T1 result = caseBundles(bundles);
			if(result == null)
				result = caseChildrenProvider(bundles);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case P2viewPackage.FRAGMENTS:
		{
			Fragments fragments = (Fragments)theEObject;
			T1 result = caseFragments(fragments);
			if(result == null)
				result = caseChildrenProvider(fragments);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case P2viewPackage.MISCELLANEOUS:
		{
			Miscellaneous miscellaneous = (Miscellaneous)theEObject;
			T1 result = caseMiscellaneous(miscellaneous);
			if(result == null)
				result = caseChildrenProvider(miscellaneous);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case P2viewPackage.IU_PRESENTATION:
		{
			IUPresentation iuPresentation = (IUPresentation)theEObject;
			T1 result = caseIUPresentation(iuPresentation);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case P2viewPackage.IU_PRESENTATION_WITH_DETAILS:
		{
			IUPresentationWithDetails iuPresentationWithDetails = (IUPresentationWithDetails)theEObject;
			T1 result = caseIUPresentationWithDetails(iuPresentationWithDetails);
			if(result == null)
				result = caseIUPresentation(iuPresentationWithDetails);
			if(result == null)
				result = caseIUDetails(iuPresentationWithDetails);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case P2viewPackage.CATEGORY:
		{
			Category category = (Category)theEObject;
			T1 result = caseCategory(category);
			if(result == null)
				result = caseIUPresentation(category);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case P2viewPackage.FEATURE:
		{
			Feature feature = (Feature)theEObject;
			T1 result = caseFeature(feature);
			if(result == null)
				result = caseIUPresentationWithDetails(feature);
			if(result == null)
				result = caseIUPresentation(feature);
			if(result == null)
				result = caseIUDetails(feature);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case P2viewPackage.PRODUCT:
		{
			Product product = (Product)theEObject;
			T1 result = caseProduct(product);
			if(result == null)
				result = caseIUPresentationWithDetails(product);
			if(result == null)
				result = caseIUPresentation(product);
			if(result == null)
				result = caseIUDetails(product);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case P2viewPackage.BUNDLE:
		{
			Bundle bundle = (Bundle)theEObject;
			T1 result = caseBundle(bundle);
			if(result == null)
				result = caseIUPresentationWithDetails(bundle);
			if(result == null)
				result = caseIUPresentation(bundle);
			if(result == null)
				result = caseIUDetails(bundle);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case P2viewPackage.FRAGMENT:
		{
			Fragment fragment = (Fragment)theEObject;
			T1 result = caseFragment(fragment);
			if(result == null)
				result = caseBundle(fragment);
			if(result == null)
				result = caseIUPresentationWithDetails(fragment);
			if(result == null)
				result = caseIUPresentation(fragment);
			if(result == null)
				result = caseIUDetails(fragment);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case P2viewPackage.OTHER_IU:
		{
			OtherIU otherIU = (OtherIU)theEObject;
			T1 result = caseOtherIU(otherIU);
			if(result == null)
				result = caseIUPresentationWithDetails(otherIU);
			if(result == null)
				result = caseIUPresentation(otherIU);
			if(result == null)
				result = caseIUDetails(otherIU);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case P2viewPackage.PROPERTIES:
		{
			Properties properties = (Properties)theEObject;
			T1 result = caseProperties(properties);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case P2viewPackage.REQUIRED_CAPABILITIES:
		{
			RequiredCapabilities requiredCapabilities = (RequiredCapabilities)theEObject;
			T1 result = caseRequiredCapabilities(requiredCapabilities);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case P2viewPackage.PROVIDED_CAPABILITIES:
		{
			ProvidedCapabilities providedCapabilities = (ProvidedCapabilities)theEObject;
			T1 result = caseProvidedCapabilities(providedCapabilities);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case P2viewPackage.TOUCHPOINTS:
		{
			Touchpoints touchpoints = (Touchpoints)theEObject;
			T1 result = caseTouchpoints(touchpoints);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case P2viewPackage.IU_DETAILS:
		{
			IUDetails iuDetails = (IUDetails)theEObject;
			T1 result = caseIUDetails(iuDetails);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case P2viewPackage.REQUIRED_CAPABILITY_WRAPPER:
		{
			RequiredCapabilityWrapper requiredCapabilityWrapper = (RequiredCapabilityWrapper)theEObject;
			T1 result = caseRequiredCapabilityWrapper(requiredCapabilityWrapper);
			if(result == null)
				result = caseIRequiredCapability(requiredCapabilityWrapper);
			if(result == null)
				result = caseLabelProvider(requiredCapabilityWrapper);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case P2viewPackage.PROVIDED_CAPABILITY_WRAPPER:
		{
			ProvidedCapabilityWrapper providedCapabilityWrapper = (ProvidedCapabilityWrapper)theEObject;
			T1 result = caseProvidedCapabilityWrapper(providedCapabilityWrapper);
			if(result == null)
				result = caseIProvidedCapability(providedCapabilityWrapper);
			if(result == null)
				result = caseLabelProvider(providedCapabilityWrapper);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		default:
			return defaultCase(theEObject);
		}
	}

} // P2viewSwitch
