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

import org.eclipse.buckminster.aggregator.p2view.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance hierarchy. It supports the call
 * {@link #doSwitch(EObject) doSwitch(object)} to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object and proceeding up the inheritance hierarchy until a non-null result is
 * returned, which is the result of the switch. <!-- end-user-doc -->
 * 
 * @see org.eclipse.buckminster.aggregator.p2view.P2viewPackage
 * @generated
 */
public class P2viewSwitch<T>
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
	public T caseBundle(Bundle object)
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
	public T caseBundles(Bundles object)
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
	public T caseCategories(Categories object)
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
	 * Returns the result of interpreting the object as an instance of '<em>Features</em>'. <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Features</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFeatures(Features object)
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
	public T caseFragment(Fragment object)
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
	public T caseFragments(Fragments object)
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
	public T caseInstallableUnits(InstallableUnits object)
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
	public T caseIUDetails(IUDetails object)
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
	public T caseIUPresentation(IUPresentation object)
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
	public T caseMetadataRepositoryStructuredView(MetadataRepositoryStructuredView object)
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
	public T caseMiscellaneous(Miscellaneous object)
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
	public T caseOtherIU(OtherIU object)
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
	 * Returns the result of interpreting the object as an instance of '<em>Products</em>'. <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Products</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProducts(Products object)
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
	public T caseProperties(Properties object)
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
	public T caseProvidedCapabilities(ProvidedCapabilities object)
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
	public T caseRequiredCapabilities(RequiredCapabilities object)
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
	public T caseTouchpoints(Touchpoints object)
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
		case P2viewPackage.METADATA_REPOSITORY_STRUCTURED_VIEW:
		{
			MetadataRepositoryStructuredView metadataRepositoryStructuredView = (MetadataRepositoryStructuredView)theEObject;
			T result = caseMetadataRepositoryStructuredView(metadataRepositoryStructuredView);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case P2viewPackage.INSTALLABLE_UNITS:
		{
			InstallableUnits installableUnits = (InstallableUnits)theEObject;
			T result = caseInstallableUnits(installableUnits);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case P2viewPackage.CATEGORIES:
		{
			Categories categories = (Categories)theEObject;
			T result = caseCategories(categories);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case P2viewPackage.FEATURES:
		{
			Features features = (Features)theEObject;
			T result = caseFeatures(features);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case P2viewPackage.PRODUCTS:
		{
			Products products = (Products)theEObject;
			T result = caseProducts(products);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case P2viewPackage.BUNDLES:
		{
			Bundles bundles = (Bundles)theEObject;
			T result = caseBundles(bundles);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case P2viewPackage.FRAGMENTS:
		{
			Fragments fragments = (Fragments)theEObject;
			T result = caseFragments(fragments);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case P2viewPackage.MISCELLANEOUS:
		{
			Miscellaneous miscellaneous = (Miscellaneous)theEObject;
			T result = caseMiscellaneous(miscellaneous);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case P2viewPackage.IU_PRESENTATION:
		{
			IUPresentation iuPresentation = (IUPresentation)theEObject;
			T result = caseIUPresentation(iuPresentation);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case P2viewPackage.CATEGORY:
		{
			Category category = (Category)theEObject;
			T result = caseCategory(category);
			if(result == null)
				result = caseIUPresentation(category);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case P2viewPackage.FEATURE:
		{
			Feature feature = (Feature)theEObject;
			T result = caseFeature(feature);
			if(result == null)
				result = caseIUPresentation(feature);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case P2viewPackage.PRODUCT:
		{
			Product product = (Product)theEObject;
			T result = caseProduct(product);
			if(result == null)
				result = caseIUPresentation(product);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case P2viewPackage.BUNDLE:
		{
			Bundle bundle = (Bundle)theEObject;
			T result = caseBundle(bundle);
			if(result == null)
				result = caseIUPresentation(bundle);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case P2viewPackage.FRAGMENT:
		{
			Fragment fragment = (Fragment)theEObject;
			T result = caseFragment(fragment);
			if(result == null)
				result = caseBundle(fragment);
			if(result == null)
				result = caseIUPresentation(fragment);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case P2viewPackage.OTHER_IU:
		{
			OtherIU otherIU = (OtherIU)theEObject;
			T result = caseOtherIU(otherIU);
			if(result == null)
				result = caseIUPresentation(otherIU);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case P2viewPackage.PROPERTIES:
		{
			Properties properties = (Properties)theEObject;
			T result = caseProperties(properties);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case P2viewPackage.REQUIRED_CAPABILITIES:
		{
			RequiredCapabilities requiredCapabilities = (RequiredCapabilities)theEObject;
			T result = caseRequiredCapabilities(requiredCapabilities);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case P2viewPackage.PROVIDED_CAPABILITIES:
		{
			ProvidedCapabilities providedCapabilities = (ProvidedCapabilities)theEObject;
			T result = caseProvidedCapabilities(providedCapabilities);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case P2viewPackage.TOUCHPOINTS:
		{
			Touchpoints touchpoints = (Touchpoints)theEObject;
			T result = caseTouchpoints(touchpoints);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case P2viewPackage.IU_DETAILS:
		{
			IUDetails iuDetails = (IUDetails)theEObject;
			T result = caseIUDetails(iuDetails);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		default:
			return defaultCase(theEObject);
		}
	}

} // P2viewSwitch
