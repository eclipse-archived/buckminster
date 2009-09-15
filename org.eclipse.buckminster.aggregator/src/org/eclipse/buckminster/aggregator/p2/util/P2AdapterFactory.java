/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.p2.util;

import java.util.Map;

import org.eclipse.buckminster.aggregator.p2.*;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
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
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides an adapter <code>createXXX</code>
 * method for each class of the model. <!-- end-user-doc -->
 * 
 * @see org.eclipse.buckminster.aggregator.p2.P2Package
 * @generated
 */
public class P2AdapterFactory extends AdapterFactoryImpl
{
	/**
	 * The cached model package. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected static P2Package modelPackage;

	/**
	 * The switch that delegates to the <code>createXXX</code> methods. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected P2Switch<Adapter> modelSwitch = new P2Switch<Adapter>()
	{
		@Override
		public Adapter caseArtifactKey(ArtifactKey object)
		{
			return createArtifactKeyAdapter();
		}

		@Override
		public Adapter caseCopyright(Copyright object)
		{
			return createCopyrightAdapter();
		}

		@Override
		public Adapter caseIAdaptable(IAdaptable object)
		{
			return createIAdaptableAdapter();
		}

		@Override
		public Adapter caseIArtifactKey(IArtifactKey object)
		{
			return createIArtifactKeyAdapter();
		}

		@Override
		public Adapter caseICopyright(ICopyright object)
		{
			return createICopyrightAdapter();
		}

		@Override
		public Adapter caseIInstallableUnit(IInstallableUnit object)
		{
			return createIInstallableUnitAdapter();
		}

		@Override
		public Adapter caseIInstallableUnitFragment(IInstallableUnitFragment object)
		{
			return createIInstallableUnitFragmentAdapter();
		}

		@Override
		public Adapter caseILicense(ILicense object)
		{
			return createILicenseAdapter();
		}

		@Override
		public Adapter caseIMetadataRepository(IMetadataRepository object)
		{
			return createIMetadataRepositoryAdapter();
		}

		@Override
		public Adapter caseInstallableUnit(InstallableUnit object)
		{
			return createInstallableUnitAdapter();
		}

		@Override
		public Adapter caseInstallableUnitFragment(InstallableUnitFragment object)
		{
			return createInstallableUnitFragmentAdapter();
		}

		@Override
		public Adapter caseInstructionMap(Map.Entry<String, TouchpointInstruction> object)
		{
			return createInstructionMapAdapter();
		}

		@Override
		public Adapter caseIProvidedCapability(IProvidedCapability object)
		{
			return createIProvidedCapabilityAdapter();
		}

		@Override
		public Adapter caseIQueryable(IQueryable object)
		{
			return createIQueryableAdapter();
		}

		@Override
		public Adapter caseIRepository(IRepository object)
		{
			return createIRepositoryAdapter();
		}

		@Override
		public Adapter caseIRequiredCapability(IRequiredCapability object)
		{
			return createIRequiredCapabilityAdapter();
		}

		@Override
		public Adapter caseITouchpointData(ITouchpointData object)
		{
			return createITouchpointDataAdapter();
		}

		@Override
		public Adapter caseITouchpointInstruction(ITouchpointInstruction object)
		{
			return createITouchpointInstructionAdapter();
		}

		@Override
		public Adapter caseITouchpointType(ITouchpointType object)
		{
			return createITouchpointTypeAdapter();
		}

		@Override
		public Adapter caseIUpdateDescriptor(IUpdateDescriptor object)
		{
			return createIUpdateDescriptorAdapter();
		}

		@Override
		public Adapter caseLicense(License object)
		{
			return createLicenseAdapter();
		}

		@Override
		public Adapter caseMetadataRepository(MetadataRepository object)
		{
			return createMetadataRepositoryAdapter();
		}

		@Override
		public Adapter caseProperty(Map.Entry<String, String> object)
		{
			return createPropertyAdapter();
		}

		@Override
		public Adapter caseProvidedCapability(ProvidedCapability object)
		{
			return createProvidedCapabilityAdapter();
		}

		@Override
		public Adapter caseRepositoryReference(RepositoryReference object)
		{
			return createRepositoryReferenceAdapter();
		}

		@Override
		public Adapter caseRequiredCapability(RequiredCapability object)
		{
			return createRequiredCapabilityAdapter();
		}

		@Override
		public Adapter caseTouchpointData(TouchpointData object)
		{
			return createTouchpointDataAdapter();
		}

		@Override
		public Adapter caseTouchpointInstruction(TouchpointInstruction object)
		{
			return createTouchpointInstructionAdapter();
		}

		@Override
		public Adapter caseTouchpointType(TouchpointType object)
		{
			return createTouchpointTypeAdapter();
		}

		@Override
		public Adapter caseUpdateDescriptor(UpdateDescriptor object)
		{
			return createUpdateDescriptorAdapter();
		}

		@Override
		public Adapter defaultCase(EObject object)
		{
			return createEObjectAdapter();
		}
	};

	/**
	 * Creates an instance of the adapter factory. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public P2AdapterFactory()
	{
		if(modelPackage == null)
		{
			modelPackage = P2Package.eINSTANCE;
		}
	}

	/**
	 * Creates an adapter for the <code>target</code>. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param target
	 *            the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target)
	{
		return modelSwitch.doSwitch((EObject)target);
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.buckminster.aggregator.p2.ArtifactKey
	 * <em>Artifact Key</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
	 * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
	 * -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.p2.ArtifactKey
	 * @generated
	 */
	public Adapter createArtifactKeyAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.buckminster.aggregator.p2.Copyright
	 * <em>Copyright</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
	 * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
	 * -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.p2.Copyright
	 * @generated
	 */
	public Adapter createCopyrightAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for the default case. <!-- begin-user-doc --> This default implementation returns null.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.core.runtime.IAdaptable <em>IAdaptable</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful
	 * to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.core.runtime.IAdaptable
	 * @generated
	 */
	public Adapter createIAdaptableAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.equinox.internal.provisional.p2.metadata.IArtifactKey <em>IArtifact Key</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
	 * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.IArtifactKey
	 * @generated
	 */
	public Adapter createIArtifactKeyAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.equinox.internal.provisional.p2.metadata.ICopyright <em>ICopyright</em>}'. <!-- begin-user-doc
	 * --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case
	 * when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.ICopyright
	 * @generated
	 */
	public Adapter createICopyrightAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit <em>IInstallable Unit</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
	 * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit
	 * @generated
	 */
	public Adapter createIInstallableUnitAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnitFragment
	 * <em>IInstallable Unit Fragment</em>}'. <!-- begin-user-doc --> This default implementation returns null so that
	 * we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnitFragment
	 * @generated
	 */
	public Adapter createIInstallableUnitFragmentAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.equinox.internal.provisional.p2.metadata.ILicense <em>ILicense</em>}'. <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case when
	 * inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.ILicense
	 * @generated
	 */
	public Adapter createILicenseAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepository
	 * <em>IMetadata Repository</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
	 * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepository
	 * @generated
	 */
	public Adapter createIMetadataRepositoryAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.buckminster.aggregator.p2.InstallableUnit
	 * <em>Installable Unit</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
	 * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.p2.InstallableUnit
	 * @generated
	 */
	public Adapter createInstallableUnitAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.aggregator.p2.InstallableUnitFragment <em>Installable Unit Fragment</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
	 * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.p2.InstallableUnitFragment
	 * @generated
	 */
	public Adapter createInstallableUnitFragmentAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>Instruction Map</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
	 * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see java.util.Map.Entry
	 * @generated
	 */
	public Adapter createInstructionMapAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.equinox.internal.provisional.p2.metadata.IProvidedCapability <em>IProvided Capability</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful
	 * to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.IProvidedCapability
	 * @generated
	 */
	public Adapter createIProvidedCapabilityAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.equinox.internal.provisional.p2.query.IQueryable <em>IQueryable</em>}'. <!-- begin-user-doc
	 * --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case
	 * when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.equinox.internal.provisional.p2.query.IQueryable
	 * @generated
	 */
	public Adapter createIQueryableAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.equinox.internal.provisional.p2.repository.IRepository <em>IRepository</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
	 * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.equinox.internal.provisional.p2.repository.IRepository
	 * @generated
	 */
	public Adapter createIRepositoryAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.equinox.internal.provisional.p2.metadata.IRequiredCapability <em>IRequired Capability</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful
	 * to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.IRequiredCapability
	 * @generated
	 */
	public Adapter createIRequiredCapabilityAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.equinox.internal.provisional.p2.metadata.ITouchpointData <em>ITouchpoint Data</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
	 * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.ITouchpointData
	 * @generated
	 */
	public Adapter createITouchpointDataAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.equinox.internal.provisional.p2.metadata.ITouchpointInstruction
	 * <em>ITouchpoint Instruction</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we
	 * can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.ITouchpointInstruction
	 * @generated
	 */
	public Adapter createITouchpointInstructionAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.equinox.internal.provisional.p2.metadata.ITouchpointType <em>ITouchpoint Type</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
	 * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.ITouchpointType
	 * @generated
	 */
	public Adapter createITouchpointTypeAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.equinox.internal.provisional.p2.metadata.IUpdateDescriptor <em>IUpdate Descriptor</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
	 * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.IUpdateDescriptor
	 * @generated
	 */
	public Adapter createIUpdateDescriptorAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.buckminster.aggregator.p2.License
	 * <em>License</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
	 * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.p2.License
	 * @generated
	 */
	public Adapter createLicenseAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.buckminster.aggregator.p2.MetadataRepository
	 * <em>Metadata Repository</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
	 * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.p2.MetadataRepository
	 * @generated
	 */
	public Adapter createMetadataRepositoryAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>Property</em>}'. <!-- begin-user-doc
	 * --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case
	 * when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see java.util.Map.Entry
	 * @generated
	 */
	public Adapter createPropertyAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.buckminster.aggregator.p2.ProvidedCapability
	 * <em>Provided Capability</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
	 * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.p2.ProvidedCapability
	 * @generated
	 */
	public Adapter createProvidedCapabilityAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.buckminster.aggregator.p2.RepositoryReference
	 * <em>Repository Reference</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
	 * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.p2.RepositoryReference
	 * @generated
	 */
	public Adapter createRepositoryReferenceAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.buckminster.aggregator.p2.RequiredCapability
	 * <em>Required Capability</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
	 * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.p2.RequiredCapability
	 * @generated
	 */
	public Adapter createRequiredCapabilityAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.buckminster.aggregator.p2.TouchpointData
	 * <em>Touchpoint Data</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
	 * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.p2.TouchpointData
	 * @generated
	 */
	public Adapter createTouchpointDataAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.buckminster.aggregator.p2.TouchpointInstruction
	 * <em>Touchpoint Instruction</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we
	 * can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.p2.TouchpointInstruction
	 * @generated
	 */
	public Adapter createTouchpointInstructionAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.buckminster.aggregator.p2.TouchpointType
	 * <em>Touchpoint Type</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
	 * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.p2.TouchpointType
	 * @generated
	 */
	public Adapter createTouchpointTypeAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.buckminster.aggregator.p2.UpdateDescriptor
	 * <em>Update Descriptor</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
	 * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.p2.UpdateDescriptor
	 * @generated
	 */
	public Adapter createUpdateDescriptorAdapter()
	{
		return null;
	}

	/**
	 * Returns whether this factory is applicable for the type of the object. <!-- begin-user-doc --> This
	 * implementation returns <code>true</code> if the object is either the model's package or is an instance object of
	 * the model. <!-- end-user-doc -->
	 * 
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object)
	{
		if(object == modelPackage)
		{
			return true;
		}
		if(object instanceof EObject)
		{
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

} // P2AdapterFactory
