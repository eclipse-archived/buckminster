/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.p2.util;

import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.aggregator.p2.*;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.equinox.internal.provisional.p2.metadata.IArtifactKey;
import org.eclipse.equinox.internal.provisional.p2.metadata.ICopyright;
import org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnitFragment;
import org.eclipse.equinox.internal.provisional.p2.metadata.ILicense;
import org.eclipse.equinox.internal.provisional.p2.metadata.IProvidedCapability;
import org.eclipse.equinox.internal.provisional.p2.metadata.IRequiredCapability;
import org.eclipse.equinox.internal.provisional.p2.metadata.ITouchpointData;
import org.eclipse.equinox.internal.provisional.p2.metadata.ITouchpointInstruction;
import org.eclipse.equinox.internal.provisional.p2.metadata.ITouchpointType;
import org.eclipse.equinox.internal.provisional.p2.metadata.IUpdateDescriptor;
import org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepository;
import org.eclipse.equinox.internal.provisional.p2.query.IQueryable;
import org.eclipse.equinox.internal.provisional.p2.repository.IRepository;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance hierarchy. It supports the call
 * {@link #doSwitch(EObject) doSwitch(object)} to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object and proceeding up the inheritance hierarchy until a non-null result is
 * returned, which is the result of the switch. <!-- end-user-doc -->
 * 
 * @see org.eclipse.buckminster.aggregator.p2.P2Package
 * @generated
 */
public class P2Switch<T>
{
	/**
	 * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected static P2Package modelPackage;

	/**
	 * Creates an instance of the switch. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public P2Switch()
	{
		if(modelPackage == null)
		{
			modelPackage = P2Package.eINSTANCE;
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Artifact Key</em>'. <!-- begin-user-doc -->
	 * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Artifact Key</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseArtifactKey(ArtifactKey object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Copyright</em>'. <!-- begin-user-doc -->
	 * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Copyright</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCopyright(Copyright object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IAdaptable</em>'. <!-- begin-user-doc -->
	 * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IAdaptable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIAdaptable(IAdaptable object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IArtifact Key</em>'. <!-- begin-user-doc -->
	 * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IArtifact Key</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIArtifactKey(IArtifactKey object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>ICopyright</em>'. <!-- begin-user-doc -->
	 * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>ICopyright</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseICopyright(ICopyright object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IInstallable Unit</em>'. <!-- begin-user-doc
	 * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
	 * -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IInstallable Unit</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIInstallableUnit(IInstallableUnit object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IInstallable Unit Fragment</em>'. <!--
	 * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IInstallable Unit Fragment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIInstallableUnitFragment(IInstallableUnitFragment object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>ILicense</em>'. <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>ILicense</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseILicense(ILicense object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IMetadata Repository</em>'. <!--
	 * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IMetadata Repository</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIMetadataRepository(IMetadataRepository object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Installable Unit</em>'. <!-- begin-user-doc
	 * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
	 * -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Installable Unit</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInstallableUnit(InstallableUnit object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Installable Unit Fragment</em>'. <!--
	 * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Installable Unit Fragment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInstallableUnitFragment(InstallableUnitFragment object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Instruction Map</em>'. <!-- begin-user-doc
	 * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
	 * -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Instruction Map</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInstructionMap(Map.Entry<String, TouchpointInstruction> object)
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
	public T caseIProvidedCapability(IProvidedCapability object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IQueryable</em>'. <!-- begin-user-doc -->
	 * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IQueryable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIQueryable(IQueryable object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IRepository</em>'. <!-- begin-user-doc -->
	 * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IRepository</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIRepository(IRepository object)
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
	public T caseIRequiredCapability(IRequiredCapability object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>ITouchpoint Data</em>'. <!-- begin-user-doc
	 * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
	 * -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>ITouchpoint Data</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseITouchpointData(ITouchpointData object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>ITouchpoint Instruction</em>'. <!--
	 * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>ITouchpoint Instruction</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseITouchpointInstruction(ITouchpointInstruction object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>ITouchpoint Type</em>'. <!-- begin-user-doc
	 * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
	 * -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>ITouchpoint Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseITouchpointType(ITouchpointType object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IUpdate Descriptor</em>'. <!--
	 * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IUpdate Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIUpdateDescriptor(IUpdateDescriptor object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>License</em>'. <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>License</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLicense(License object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Metadata Repository</em>'. <!--
	 * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Metadata Repository</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMetadataRepository(MetadataRepository object)
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
	public T caseProperty(Map.Entry<String, String> object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Provided Capability</em>'. <!--
	 * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Provided Capability</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProvidedCapability(ProvidedCapability object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Repository Reference</em>'. <!--
	 * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Repository Reference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRepositoryReference(RepositoryReference object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Required Capability</em>'. <!--
	 * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Required Capability</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRequiredCapability(RequiredCapability object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Touchpoint Data</em>'. <!-- begin-user-doc
	 * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
	 * -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Touchpoint Data</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTouchpointData(TouchpointData object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Touchpoint Instruction</em>'. <!--
	 * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Touchpoint Instruction</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTouchpointInstruction(TouchpointInstruction object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Touchpoint Type</em>'. <!-- begin-user-doc
	 * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
	 * -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Touchpoint Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTouchpointType(TouchpointType object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Update Descriptor</em>'. <!-- begin-user-doc
	 * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
	 * -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Update Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUpdateDescriptor(UpdateDescriptor object)
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
		case P2Package.ARTIFACT_KEY:
		{
			ArtifactKey artifactKey = (ArtifactKey)theEObject;
			T result = caseArtifactKey(artifactKey);
			if(result == null)
				result = caseIArtifactKey(artifactKey);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case P2Package.COPYRIGHT:
		{
			Copyright copyright = (Copyright)theEObject;
			T result = caseCopyright(copyright);
			if(result == null)
				result = caseICopyright(copyright);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case P2Package.METADATA_REPOSITORY:
		{
			MetadataRepository metadataRepository = (MetadataRepository)theEObject;
			T result = caseMetadataRepository(metadataRepository);
			if(result == null)
				result = caseIMetadataRepository(metadataRepository);
			if(result == null)
				result = caseIQueryable(metadataRepository);
			if(result == null)
				result = caseIRepository(metadataRepository);
			if(result == null)
				result = caseIAdaptable(metadataRepository);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case P2Package.INSTALLABLE_UNIT:
		{
			InstallableUnit installableUnit = (InstallableUnit)theEObject;
			T result = caseInstallableUnit(installableUnit);
			if(result == null)
				result = caseIInstallableUnit(installableUnit);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case P2Package.INSTALLABLE_UNIT_FRAGMENT:
		{
			InstallableUnitFragment installableUnitFragment = (InstallableUnitFragment)theEObject;
			T result = caseInstallableUnitFragment(installableUnitFragment);
			if(result == null)
				result = caseInstallableUnit(installableUnitFragment);
			if(result == null)
				result = caseIInstallableUnitFragment(installableUnitFragment);
			if(result == null)
				result = caseIInstallableUnit(installableUnitFragment);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case P2Package.LICENSE:
		{
			License license = (License)theEObject;
			T result = caseLicense(license);
			if(result == null)
				result = caseILicense(license);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case P2Package.PROVIDED_CAPABILITY:
		{
			ProvidedCapability providedCapability = (ProvidedCapability)theEObject;
			T result = caseProvidedCapability(providedCapability);
			if(result == null)
				result = caseIProvidedCapability(providedCapability);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case P2Package.REQUIRED_CAPABILITY:
		{
			RequiredCapability requiredCapability = (RequiredCapability)theEObject;
			T result = caseRequiredCapability(requiredCapability);
			if(result == null)
				result = caseIRequiredCapability(requiredCapability);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case P2Package.TOUCHPOINT_DATA:
		{
			TouchpointData touchpointData = (TouchpointData)theEObject;
			T result = caseTouchpointData(touchpointData);
			if(result == null)
				result = caseITouchpointData(touchpointData);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case P2Package.TOUCHPOINT_INSTRUCTION:
		{
			TouchpointInstruction touchpointInstruction = (TouchpointInstruction)theEObject;
			T result = caseTouchpointInstruction(touchpointInstruction);
			if(result == null)
				result = caseITouchpointInstruction(touchpointInstruction);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case P2Package.TOUCHPOINT_TYPE:
		{
			TouchpointType touchpointType = (TouchpointType)theEObject;
			T result = caseTouchpointType(touchpointType);
			if(result == null)
				result = caseITouchpointType(touchpointType);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case P2Package.UPDATE_DESCRIPTOR:
		{
			UpdateDescriptor updateDescriptor = (UpdateDescriptor)theEObject;
			T result = caseUpdateDescriptor(updateDescriptor);
			if(result == null)
				result = caseIUpdateDescriptor(updateDescriptor);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case P2Package.PROPERTY:
		{
			@SuppressWarnings("unchecked")
			Map.Entry<String, String> property = (Map.Entry<String, String>)theEObject;
			T result = caseProperty(property);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case P2Package.INSTRUCTION_MAP:
		{
			@SuppressWarnings("unchecked")
			Map.Entry<String, TouchpointInstruction> instructionMap = (Map.Entry<String, TouchpointInstruction>)theEObject;
			T result = caseInstructionMap(instructionMap);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case P2Package.REPOSITORY_REFERENCE:
		{
			RepositoryReference repositoryReference = (RepositoryReference)theEObject;
			T result = caseRepositoryReference(repositoryReference);
			if(result == null)
				result = defaultCase(theEObject);
			return result;
		}
		default:
			return defaultCase(theEObject);
		}
	}

} // P2Switch
