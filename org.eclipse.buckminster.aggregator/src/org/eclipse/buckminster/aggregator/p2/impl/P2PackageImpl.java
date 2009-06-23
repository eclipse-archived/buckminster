/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.p2.impl;

import java.util.Map;

import org.eclipse.buckminster.aggregator.AggregatorPackage;
import org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl;
import org.eclipse.buckminster.aggregator.p2.ArtifactKey;
import org.eclipse.buckminster.aggregator.p2.Copyright;
import org.eclipse.buckminster.aggregator.p2.InstallableUnit;
import org.eclipse.buckminster.aggregator.p2.InstallableUnitFragment;
import org.eclipse.buckminster.aggregator.p2.License;
import org.eclipse.buckminster.aggregator.p2.MetadataRepository;
import org.eclipse.buckminster.aggregator.p2.P2Factory;
import org.eclipse.buckminster.aggregator.p2.P2Package;
import org.eclipse.buckminster.aggregator.p2.ProvidedCapability;
import org.eclipse.buckminster.aggregator.p2.RepositoryReference;
import org.eclipse.buckminster.aggregator.p2.RequiredCapability;
import org.eclipse.buckminster.aggregator.p2.TouchpointData;
import org.eclipse.buckminster.aggregator.p2.TouchpointInstruction;
import org.eclipse.buckminster.aggregator.p2.TouchpointType;
import org.eclipse.buckminster.aggregator.p2.UpdateDescriptor;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.eclipse.equinox.internal.provisional.p2.core.Version;
import org.eclipse.equinox.internal.provisional.p2.core.VersionRange;
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
import org.eclipse.equinox.internal.provisional.p2.query.Collector;
import org.eclipse.equinox.internal.provisional.p2.query.IQueryable;
import org.eclipse.equinox.internal.provisional.p2.query.Query;
import org.eclipse.equinox.internal.provisional.p2.repository.IRepository;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 * @generated
 */
public class P2PackageImpl extends EPackageImpl implements P2Package
{
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iArtifactKeyEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iCopyrightEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iInstallableUnitEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iInstallableUnitFragmentEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iLicenseEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iProvidedCapabilityEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iRequiredCapabilityEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iTouchpointDataEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iTouchpointInstructionEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iTouchpointTypeEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iUpdateDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass artifactKeyEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass copyrightEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass metadataRepositoryEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass installableUnitEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass installableUnitFragmentEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass licenseEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass providedCapabilityEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass requiredCapabilityEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass touchpointDataEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass touchpointInstructionEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass touchpointTypeEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass updateDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass propertyEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass instructionMapEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iQueryableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iMetadataRepositoryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iRepositoryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass repositoryReferenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iAdaptableEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType versionEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType versionRangeEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType iInstallableUnitArrayEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType iRequiredCapabilityArrayEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType iProvidedCapabilityArrayEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType iInstallableUnitFragmentArrayEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType iArtifactKeyArrayEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType iTouchpointDataArrayEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType stringArrayEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType untypedMapEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType queryEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType collectorEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType iProgressMonitorEDataType = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with {@link org.eclipse.emf.ecore.EPackage.Registry
	 * EPackage.Registry} by the package package URI value.
	 * <p>
	 * Note: the correct way to create the package is via the static factory method {@link #init init()}, which also
	 * performs initialization of the package, or returns the registered package, if one already exists. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.buckminster.aggregator.p2.P2Package#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private P2PackageImpl()
	{
		super(eNS_URI, P2Factory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link P2Package#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static P2Package init()
	{
		if (isInited) return (P2Package)EPackage.Registry.INSTANCE.getEPackage(P2Package.eNS_URI);

		// Obtain or create and register package
		P2PackageImpl theP2Package = (P2PackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof P2PackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new P2PackageImpl());

		isInited = true;

		// Initialize simple dependencies
		XMLTypePackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		AggregatorPackageImpl theAggregatorPackage = (AggregatorPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(AggregatorPackage.eNS_URI) instanceof AggregatorPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(AggregatorPackage.eNS_URI) : AggregatorPackage.eINSTANCE);

		// Create package meta-data objects
		theP2Package.createPackageContents();
		theAggregatorPackage.createPackageContents();

		// Initialize created meta-data
		theP2Package.initializePackageContents();
		theAggregatorPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theP2Package.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(P2Package.eNS_URI, theP2Package);
		return theP2Package;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIArtifactKey()
	{
		return iArtifactKeyEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIArtifactKey_Classifier()
	{
		return (EAttribute)iArtifactKeyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIArtifactKey_Id()
	{
		return (EAttribute)iArtifactKeyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIArtifactKey_Version()
	{
		return (EAttribute)iArtifactKeyEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getICopyright()
	{
		return iCopyrightEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getICopyright_Location()
	{
		return (EAttribute)iCopyrightEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getICopyright_Body()
	{
		return (EAttribute)iCopyrightEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIInstallableUnit()
	{
		return iInstallableUnitEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIInstallableUnit_Filter()
	{
		return (EAttribute)iInstallableUnitEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIInstallableUnit_Id()
	{
		return (EAttribute)iInstallableUnitEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIInstallableUnit_TouchpointType()
	{
		return (EReference)iInstallableUnitEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIInstallableUnit_Version()
	{
		return (EAttribute)iInstallableUnitEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIInstallableUnit_Fragment()
	{
		return (EAttribute)iInstallableUnitEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIInstallableUnit_Resolved()
	{
		return (EAttribute)iInstallableUnitEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIInstallableUnit_Singleton()
	{
		return (EAttribute)iInstallableUnitEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIInstallableUnit_UpdateDescriptor()
	{
		return (EReference)iInstallableUnitEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIInstallableUnit_License()
	{
		return (EReference)iInstallableUnitEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIInstallableUnit_Copyright()
	{
		return (EReference)iInstallableUnitEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIInstallableUnitFragment()
	{
		return iInstallableUnitFragmentEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIInstallableUnitFragment_Host()
	{
		return (EAttribute)iInstallableUnitFragmentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getILicense()
	{
		return iLicenseEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getILicense_Location()
	{
		return (EAttribute)iLicenseEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getILicense_Body()
	{
		return (EAttribute)iLicenseEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getILicense_Digest()
	{
		return (EAttribute)iLicenseEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIProvidedCapability()
	{
		return iProvidedCapabilityEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIProvidedCapability_Name()
	{
		return (EAttribute)iProvidedCapabilityEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIProvidedCapability_Namespace()
	{
		return (EAttribute)iProvidedCapabilityEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIProvidedCapability_Version()
	{
		return (EAttribute)iProvidedCapabilityEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIRequiredCapability()
	{
		return iRequiredCapabilityEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIRequiredCapability_Filter()
	{
		return (EAttribute)iRequiredCapabilityEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIRequiredCapability_Name()
	{
		return (EAttribute)iRequiredCapabilityEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIRequiredCapability_Namespace()
	{
		return (EAttribute)iRequiredCapabilityEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIRequiredCapability_Range()
	{
		return (EAttribute)iRequiredCapabilityEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIRequiredCapability_Selectors()
	{
		return (EAttribute)iRequiredCapabilityEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIRequiredCapability_Multiple()
	{
		return (EAttribute)iRequiredCapabilityEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIRequiredCapability_Optional()
	{
		return (EAttribute)iRequiredCapabilityEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIRequiredCapability_Greedy()
	{
		return (EAttribute)iRequiredCapabilityEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getITouchpointData()
	{
		return iTouchpointDataEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getITouchpointInstruction()
	{
		return iTouchpointInstructionEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getITouchpointInstruction_Body()
	{
		return (EAttribute)iTouchpointInstructionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getITouchpointInstruction_ImportAttribute()
	{
		return (EAttribute)iTouchpointInstructionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getITouchpointType()
	{
		return iTouchpointTypeEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getITouchpointType_Id()
	{
		return (EAttribute)iTouchpointTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getITouchpointType_Version()
	{
		return (EAttribute)iTouchpointTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIUpdateDescriptor()
	{
		return iUpdateDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIUpdateDescriptor_Id()
	{
		return (EAttribute)iUpdateDescriptorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIUpdateDescriptor_Range()
	{
		return (EAttribute)iUpdateDescriptorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIUpdateDescriptor_Description()
	{
		return (EAttribute)iUpdateDescriptorEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIUpdateDescriptor_Severity()
	{
		return (EAttribute)iUpdateDescriptorEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getArtifactKey()
	{
		return artifactKeyEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCopyright()
	{
		return copyrightEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMetadataRepository()
	{
		return metadataRepositoryEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMetadataRepository_InstallableUnits()
	{
		return (EReference)metadataRepositoryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMetadataRepository_RepositoryReferences() {
		return (EReference)metadataRepositoryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMetadataRepository_PropertyMap() {
		return (EReference)metadataRepositoryEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInstallableUnit()
	{
		return installableUnitEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInstallableUnit_ArtifactList()
	{
		return (EReference)installableUnitEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInstallableUnit_ProvidedCapabilityList()
	{
		return (EReference)installableUnitEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInstallableUnit_RequiredCapabilityList()
	{
		return (EReference)installableUnitEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInstallableUnit_MetaRequiredCapabilityList()
	{
		return (EReference)installableUnitEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInstallableUnit_PropertyMap()
	{
		return (EReference)installableUnitEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInstallableUnit_TouchpointDataList()
	{
		return (EReference)installableUnitEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInstallableUnitFragment()
	{
		return installableUnitFragmentEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInstallableUnitFragment_HostList()
	{
		return (EReference)installableUnitFragmentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLicense()
	{
		return licenseEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProvidedCapability()
	{
		return providedCapabilityEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRequiredCapability()
	{
		return requiredCapabilityEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTouchpointData()
	{
		return touchpointDataEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTouchpointData_InstructionMap()
	{
		return (EReference)touchpointDataEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTouchpointInstruction()
	{
		return touchpointInstructionEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTouchpointType()
	{
		return touchpointTypeEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUpdateDescriptor()
	{
		return updateDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProperty()
	{
		return propertyEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProperty_Key()
	{
		return (EAttribute)propertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProperty_Value()
	{
		return (EAttribute)propertyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInstructionMap()
	{
		return instructionMapEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInstructionMap_Key()
	{
		return (EAttribute)instructionMapEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInstructionMap_Value()
	{
		return (EReference)instructionMapEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIQueryable()
	{
		return iQueryableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIMetadataRepository() {
		return iMetadataRepositoryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIRepository() {
		return iRepositoryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIRepository_Location() {
		return (EAttribute)iRepositoryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIRepository_Name() {
		return (EAttribute)iRepositoryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIRepository_Type() {
		return (EAttribute)iRepositoryEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIRepository_Version() {
		return (EAttribute)iRepositoryEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIRepository_Description() {
		return (EAttribute)iRepositoryEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIRepository_Provider() {
		return (EAttribute)iRepositoryEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIRepository_Modifiable() {
		return (EAttribute)iRepositoryEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRepositoryReference() {
		return repositoryReferenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRepositoryReference_Location() {
		return (EAttribute)repositoryReferenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRepositoryReference_Type() {
		return (EAttribute)repositoryReferenceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRepositoryReference_Options() {
		return (EAttribute)repositoryReferenceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRepositoryReference_Nickname() {
		return (EAttribute)repositoryReferenceEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIAdaptable() {
		return iAdaptableEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getVersion()
	{
		return versionEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getVersionRange()
	{
		return versionRangeEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getIInstallableUnitArray()
	{
		return iInstallableUnitArrayEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getIRequiredCapabilityArray()
	{
		return iRequiredCapabilityArrayEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getIProvidedCapabilityArray()
	{
		return iProvidedCapabilityArrayEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getIInstallableUnitFragmentArray()
	{
		return iInstallableUnitFragmentArrayEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getIArtifactKeyArray()
	{
		return iArtifactKeyArrayEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getITouchpointDataArray()
	{
		return iTouchpointDataArrayEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getStringArray()
	{
		return stringArrayEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getUntypedMap()
	{
		return untypedMapEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getQuery()
	{
		return queryEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getCollector()
	{
		return collectorEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getIProgressMonitor()
	{
		return iProgressMonitorEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public P2Factory getP2Factory()
	{
		return (P2Factory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents()
	{
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		iArtifactKeyEClass = createEClass(IARTIFACT_KEY);
		createEAttribute(iArtifactKeyEClass, IARTIFACT_KEY__CLASSIFIER);
		createEAttribute(iArtifactKeyEClass, IARTIFACT_KEY__ID);
		createEAttribute(iArtifactKeyEClass, IARTIFACT_KEY__VERSION);

		iCopyrightEClass = createEClass(ICOPYRIGHT);
		createEAttribute(iCopyrightEClass, ICOPYRIGHT__LOCATION);
		createEAttribute(iCopyrightEClass, ICOPYRIGHT__BODY);

		iInstallableUnitEClass = createEClass(IINSTALLABLE_UNIT);
		createEAttribute(iInstallableUnitEClass, IINSTALLABLE_UNIT__FILTER);
		createEAttribute(iInstallableUnitEClass, IINSTALLABLE_UNIT__ID);
		createEReference(iInstallableUnitEClass, IINSTALLABLE_UNIT__TOUCHPOINT_TYPE);
		createEAttribute(iInstallableUnitEClass, IINSTALLABLE_UNIT__VERSION);
		createEAttribute(iInstallableUnitEClass, IINSTALLABLE_UNIT__FRAGMENT);
		createEAttribute(iInstallableUnitEClass, IINSTALLABLE_UNIT__RESOLVED);
		createEAttribute(iInstallableUnitEClass, IINSTALLABLE_UNIT__SINGLETON);
		createEReference(iInstallableUnitEClass, IINSTALLABLE_UNIT__UPDATE_DESCRIPTOR);
		createEReference(iInstallableUnitEClass, IINSTALLABLE_UNIT__LICENSE);
		createEReference(iInstallableUnitEClass, IINSTALLABLE_UNIT__COPYRIGHT);

		iInstallableUnitFragmentEClass = createEClass(IINSTALLABLE_UNIT_FRAGMENT);
		createEAttribute(iInstallableUnitFragmentEClass, IINSTALLABLE_UNIT_FRAGMENT__HOST);

		iLicenseEClass = createEClass(ILICENSE);
		createEAttribute(iLicenseEClass, ILICENSE__LOCATION);
		createEAttribute(iLicenseEClass, ILICENSE__BODY);
		createEAttribute(iLicenseEClass, ILICENSE__DIGEST);

		iProvidedCapabilityEClass = createEClass(IPROVIDED_CAPABILITY);
		createEAttribute(iProvidedCapabilityEClass, IPROVIDED_CAPABILITY__NAME);
		createEAttribute(iProvidedCapabilityEClass, IPROVIDED_CAPABILITY__NAMESPACE);
		createEAttribute(iProvidedCapabilityEClass, IPROVIDED_CAPABILITY__VERSION);

		iRequiredCapabilityEClass = createEClass(IREQUIRED_CAPABILITY);
		createEAttribute(iRequiredCapabilityEClass, IREQUIRED_CAPABILITY__FILTER);
		createEAttribute(iRequiredCapabilityEClass, IREQUIRED_CAPABILITY__NAME);
		createEAttribute(iRequiredCapabilityEClass, IREQUIRED_CAPABILITY__NAMESPACE);
		createEAttribute(iRequiredCapabilityEClass, IREQUIRED_CAPABILITY__RANGE);
		createEAttribute(iRequiredCapabilityEClass, IREQUIRED_CAPABILITY__SELECTORS);
		createEAttribute(iRequiredCapabilityEClass, IREQUIRED_CAPABILITY__MULTIPLE);
		createEAttribute(iRequiredCapabilityEClass, IREQUIRED_CAPABILITY__OPTIONAL);
		createEAttribute(iRequiredCapabilityEClass, IREQUIRED_CAPABILITY__GREEDY);

		iTouchpointDataEClass = createEClass(ITOUCHPOINT_DATA);

		iTouchpointInstructionEClass = createEClass(ITOUCHPOINT_INSTRUCTION);
		createEAttribute(iTouchpointInstructionEClass, ITOUCHPOINT_INSTRUCTION__BODY);
		createEAttribute(iTouchpointInstructionEClass, ITOUCHPOINT_INSTRUCTION__IMPORT_ATTRIBUTE);

		iTouchpointTypeEClass = createEClass(ITOUCHPOINT_TYPE);
		createEAttribute(iTouchpointTypeEClass, ITOUCHPOINT_TYPE__ID);
		createEAttribute(iTouchpointTypeEClass, ITOUCHPOINT_TYPE__VERSION);

		iUpdateDescriptorEClass = createEClass(IUPDATE_DESCRIPTOR);
		createEAttribute(iUpdateDescriptorEClass, IUPDATE_DESCRIPTOR__ID);
		createEAttribute(iUpdateDescriptorEClass, IUPDATE_DESCRIPTOR__RANGE);
		createEAttribute(iUpdateDescriptorEClass, IUPDATE_DESCRIPTOR__DESCRIPTION);
		createEAttribute(iUpdateDescriptorEClass, IUPDATE_DESCRIPTOR__SEVERITY);

		artifactKeyEClass = createEClass(ARTIFACT_KEY);

		copyrightEClass = createEClass(COPYRIGHT);

		metadataRepositoryEClass = createEClass(METADATA_REPOSITORY);
		createEReference(metadataRepositoryEClass, METADATA_REPOSITORY__INSTALLABLE_UNITS);
		createEReference(metadataRepositoryEClass, METADATA_REPOSITORY__REPOSITORY_REFERENCES);
		createEReference(metadataRepositoryEClass, METADATA_REPOSITORY__PROPERTY_MAP);

		installableUnitEClass = createEClass(INSTALLABLE_UNIT);
		createEReference(installableUnitEClass, INSTALLABLE_UNIT__ARTIFACT_LIST);
		createEReference(installableUnitEClass, INSTALLABLE_UNIT__PROVIDED_CAPABILITY_LIST);
		createEReference(installableUnitEClass, INSTALLABLE_UNIT__REQUIRED_CAPABILITY_LIST);
		createEReference(installableUnitEClass, INSTALLABLE_UNIT__META_REQUIRED_CAPABILITY_LIST);
		createEReference(installableUnitEClass, INSTALLABLE_UNIT__PROPERTY_MAP);
		createEReference(installableUnitEClass, INSTALLABLE_UNIT__TOUCHPOINT_DATA_LIST);

		installableUnitFragmentEClass = createEClass(INSTALLABLE_UNIT_FRAGMENT);
		createEReference(installableUnitFragmentEClass, INSTALLABLE_UNIT_FRAGMENT__HOST_LIST);

		licenseEClass = createEClass(LICENSE);

		providedCapabilityEClass = createEClass(PROVIDED_CAPABILITY);

		requiredCapabilityEClass = createEClass(REQUIRED_CAPABILITY);

		touchpointDataEClass = createEClass(TOUCHPOINT_DATA);
		createEReference(touchpointDataEClass, TOUCHPOINT_DATA__INSTRUCTION_MAP);

		touchpointInstructionEClass = createEClass(TOUCHPOINT_INSTRUCTION);

		touchpointTypeEClass = createEClass(TOUCHPOINT_TYPE);

		updateDescriptorEClass = createEClass(UPDATE_DESCRIPTOR);

		propertyEClass = createEClass(PROPERTY);
		createEAttribute(propertyEClass, PROPERTY__KEY);
		createEAttribute(propertyEClass, PROPERTY__VALUE);

		instructionMapEClass = createEClass(INSTRUCTION_MAP);
		createEAttribute(instructionMapEClass, INSTRUCTION_MAP__KEY);
		createEReference(instructionMapEClass, INSTRUCTION_MAP__VALUE);

		iQueryableEClass = createEClass(IQUERYABLE);

		iMetadataRepositoryEClass = createEClass(IMETADATA_REPOSITORY);

		iRepositoryEClass = createEClass(IREPOSITORY);
		createEAttribute(iRepositoryEClass, IREPOSITORY__LOCATION);
		createEAttribute(iRepositoryEClass, IREPOSITORY__NAME);
		createEAttribute(iRepositoryEClass, IREPOSITORY__TYPE);
		createEAttribute(iRepositoryEClass, IREPOSITORY__VERSION);
		createEAttribute(iRepositoryEClass, IREPOSITORY__DESCRIPTION);
		createEAttribute(iRepositoryEClass, IREPOSITORY__PROVIDER);
		createEAttribute(iRepositoryEClass, IREPOSITORY__MODIFIABLE);

		repositoryReferenceEClass = createEClass(REPOSITORY_REFERENCE);
		createEAttribute(repositoryReferenceEClass, REPOSITORY_REFERENCE__LOCATION);
		createEAttribute(repositoryReferenceEClass, REPOSITORY_REFERENCE__TYPE);
		createEAttribute(repositoryReferenceEClass, REPOSITORY_REFERENCE__OPTIONS);
		createEAttribute(repositoryReferenceEClass, REPOSITORY_REFERENCE__NICKNAME);

		iAdaptableEClass = createEClass(IADAPTABLE);

		// Create data types
		versionEDataType = createEDataType(VERSION);
		versionRangeEDataType = createEDataType(VERSION_RANGE);
		iInstallableUnitArrayEDataType = createEDataType(IINSTALLABLE_UNIT_ARRAY);
		iRequiredCapabilityArrayEDataType = createEDataType(IREQUIRED_CAPABILITY_ARRAY);
		iProvidedCapabilityArrayEDataType = createEDataType(IPROVIDED_CAPABILITY_ARRAY);
		iInstallableUnitFragmentArrayEDataType = createEDataType(IINSTALLABLE_UNIT_FRAGMENT_ARRAY);
		iArtifactKeyArrayEDataType = createEDataType(IARTIFACT_KEY_ARRAY);
		iTouchpointDataArrayEDataType = createEDataType(ITOUCHPOINT_DATA_ARRAY);
		stringArrayEDataType = createEDataType(STRING_ARRAY);
		untypedMapEDataType = createEDataType(UNTYPED_MAP);
		queryEDataType = createEDataType(QUERY);
		collectorEDataType = createEDataType(COLLECTOR);
		iProgressMonitorEDataType = createEDataType(IPROGRESS_MONITOR);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents()
	{
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		AggregatorPackage theAggregatorPackage = (AggregatorPackage)EPackage.Registry.INSTANCE.getEPackage(AggregatorPackage.eNS_URI);
		XMLTypePackage theXMLTypePackage = (XMLTypePackage)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		iInstallableUnitFragmentEClass.getESuperTypes().add(this.getIInstallableUnit());
		artifactKeyEClass.getESuperTypes().add(this.getIArtifactKey());
		copyrightEClass.getESuperTypes().add(this.getICopyright());
		metadataRepositoryEClass.getESuperTypes().add(this.getIMetadataRepository());
		installableUnitEClass.getESuperTypes().add(this.getIInstallableUnit());
		installableUnitFragmentEClass.getESuperTypes().add(this.getInstallableUnit());
		installableUnitFragmentEClass.getESuperTypes().add(this.getIInstallableUnitFragment());
		licenseEClass.getESuperTypes().add(this.getILicense());
		providedCapabilityEClass.getESuperTypes().add(this.getIProvidedCapability());
		requiredCapabilityEClass.getESuperTypes().add(this.getIRequiredCapability());
		touchpointDataEClass.getESuperTypes().add(this.getITouchpointData());
		touchpointInstructionEClass.getESuperTypes().add(this.getITouchpointInstruction());
		touchpointTypeEClass.getESuperTypes().add(this.getITouchpointType());
		updateDescriptorEClass.getESuperTypes().add(this.getIUpdateDescriptor());
		iMetadataRepositoryEClass.getESuperTypes().add(this.getIQueryable());
		iMetadataRepositoryEClass.getESuperTypes().add(this.getIRepository());
		iRepositoryEClass.getESuperTypes().add(this.getIAdaptable());

		// Initialize classes and features; add operations and parameters
		initEClass(iArtifactKeyEClass, IArtifactKey.class, "IArtifactKey", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIArtifactKey_Classifier(), ecorePackage.getEString(), "classifier", null, 1, 1, IArtifactKey.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIArtifactKey_Id(), ecorePackage.getEString(), "id", null, 1, 1, IArtifactKey.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIArtifactKey_Version(), this.getVersion(), "version", null, 0, 1, IArtifactKey.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(iArtifactKeyEClass, ecorePackage.getEString(), "toExternalForm", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(iCopyrightEClass, ICopyright.class, "ICopyright", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getICopyright_Location(), theAggregatorPackage.getURI(), "location", null, 0, 1, ICopyright.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getICopyright_Body(), ecorePackage.getEString(), "body", null, 0, 1, ICopyright.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(iInstallableUnitEClass, IInstallableUnit.class, "IInstallableUnit", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIInstallableUnit_Filter(), ecorePackage.getEString(), "filter", "", 0, 1, IInstallableUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIInstallableUnit_Id(), ecorePackage.getEString(), "id", "", 0, 1, IInstallableUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIInstallableUnit_TouchpointType(), this.getITouchpointType(), null, "touchpointType", null, 0, 1, IInstallableUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIInstallableUnit_Version(), this.getVersion(), "version", null, 0, 1, IInstallableUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIInstallableUnit_Fragment(), ecorePackage.getEBoolean(), "fragment", null, 0, 1, IInstallableUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIInstallableUnit_Resolved(), ecorePackage.getEBoolean(), "resolved", null, 0, 1, IInstallableUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIInstallableUnit_Singleton(), ecorePackage.getEBoolean(), "singleton", null, 0, 1, IInstallableUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIInstallableUnit_UpdateDescriptor(), this.getIUpdateDescriptor(), null, "updateDescriptor", null, 0, 1, IInstallableUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIInstallableUnit_License(), this.getILicense(), null, "license", null, 0, 1, IInstallableUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIInstallableUnit_Copyright(), this.getICopyright(), null, "copyright", null, 0, 1, IInstallableUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(iInstallableUnitEClass, this.getIArtifactKeyArray(), "getArtifacts", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(iInstallableUnitEClass, this.getIInstallableUnitFragmentArray(), "getFragments", 0, 1, IS_UNIQUE, IS_ORDERED);

		EOperation op = addEOperation(iInstallableUnitEClass, ecorePackage.getEString(), "getProperty", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "key", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(iInstallableUnitEClass, this.getUntypedMap(), "getProperties", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(iInstallableUnitEClass, this.getIRequiredCapabilityArray(), "getMetaRequiredCapabilities", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(iInstallableUnitEClass, this.getIRequiredCapabilityArray(), "getRequiredCapabilities", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(iInstallableUnitEClass, this.getIProvidedCapabilityArray(), "getProvidedCapabilities", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(iInstallableUnitEClass, this.getITouchpointDataArray(), "getTouchpointData", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(iInstallableUnitEClass, ecorePackage.getEBoolean(), "satisfies", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIRequiredCapability(), "candidate", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(iInstallableUnitEClass, this.getIInstallableUnit(), "unresolved", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(iInstallableUnitFragmentEClass, IInstallableUnitFragment.class, "IInstallableUnitFragment", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIInstallableUnitFragment_Host(), this.getIRequiredCapabilityArray(), "host", null, 0, 1, IInstallableUnitFragment.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(iLicenseEClass, ILicense.class, "ILicense", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getILicense_Location(), theAggregatorPackage.getURI(), "location", null, 0, 1, ILicense.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getILicense_Body(), ecorePackage.getEString(), "body", null, 0, 1, ILicense.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getILicense_Digest(), ecorePackage.getEBigInteger(), "digest", null, 0, 1, ILicense.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(iProvidedCapabilityEClass, IProvidedCapability.class, "IProvidedCapability", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIProvidedCapability_Name(), ecorePackage.getEString(), "name", null, 0, 1, IProvidedCapability.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIProvidedCapability_Namespace(), ecorePackage.getEString(), "namespace", null, 0, 1, IProvidedCapability.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIProvidedCapability_Version(), this.getVersion(), "version", null, 0, 1, IProvidedCapability.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(iProvidedCapabilityEClass, ecorePackage.getEBoolean(), "satisfies", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIRequiredCapability(), "requirement", 1, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(iRequiredCapabilityEClass, IRequiredCapability.class, "IRequiredCapability", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIRequiredCapability_Filter(), ecorePackage.getEString(), "filter", null, 0, 1, IRequiredCapability.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIRequiredCapability_Name(), ecorePackage.getEString(), "name", null, 0, 1, IRequiredCapability.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIRequiredCapability_Namespace(), ecorePackage.getEString(), "namespace", null, 0, 1, IRequiredCapability.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIRequiredCapability_Range(), this.getVersionRange(), "range", null, 0, 1, IRequiredCapability.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIRequiredCapability_Selectors(), this.getStringArray(), "selectors", null, 0, 1, IRequiredCapability.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIRequiredCapability_Multiple(), ecorePackage.getEBoolean(), "multiple", null, 0, 1, IRequiredCapability.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIRequiredCapability_Optional(), ecorePackage.getEBoolean(), "optional", null, 0, 1, IRequiredCapability.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIRequiredCapability_Greedy(), ecorePackage.getEBoolean(), "greedy", null, 0, 1, IRequiredCapability.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(iTouchpointDataEClass, ITouchpointData.class, "ITouchpointData", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		op = addEOperation(iTouchpointDataEClass, this.getITouchpointInstruction(), "getInstruction", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theXMLTypePackage.getString(), "instructionKey", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(iTouchpointDataEClass, this.getUntypedMap(), "getInstructions", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(iTouchpointInstructionEClass, ITouchpointInstruction.class, "ITouchpointInstruction", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getITouchpointInstruction_Body(), ecorePackage.getEString(), "body", null, 0, 1, ITouchpointInstruction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getITouchpointInstruction_ImportAttribute(), ecorePackage.getEString(), "importAttribute", null, 0, 1, ITouchpointInstruction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(iTouchpointTypeEClass, ITouchpointType.class, "ITouchpointType", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getITouchpointType_Id(), ecorePackage.getEString(), "id", null, 0, 1, ITouchpointType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getITouchpointType_Version(), this.getVersion(), "version", null, 0, 1, ITouchpointType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(iUpdateDescriptorEClass, IUpdateDescriptor.class, "IUpdateDescriptor", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIUpdateDescriptor_Id(), ecorePackage.getEString(), "id", null, 0, 1, IUpdateDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIUpdateDescriptor_Range(), this.getVersionRange(), "range", null, 0, 1, IUpdateDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIUpdateDescriptor_Description(), ecorePackage.getEString(), "description", null, 0, 1, IUpdateDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIUpdateDescriptor_Severity(), ecorePackage.getEInt(), "severity", null, 0, 1, IUpdateDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(iUpdateDescriptorEClass, ecorePackage.getEBoolean(), "isUpdateOf", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIInstallableUnit(), "iu", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(artifactKeyEClass, ArtifactKey.class, "ArtifactKey", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(copyrightEClass, Copyright.class, "Copyright", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(metadataRepositoryEClass, MetadataRepository.class, "MetadataRepository", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMetadataRepository_InstallableUnits(), this.getInstallableUnit(), null, "installableUnits", null, 0, -1, MetadataRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		getMetadataRepository_InstallableUnits().getEKeys().add(this.getIInstallableUnit_Id());
		getMetadataRepository_InstallableUnits().getEKeys().add(this.getIInstallableUnit_Version());
		initEReference(getMetadataRepository_RepositoryReferences(), this.getRepositoryReference(), null, "repositoryReferences", null, 0, -1, MetadataRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMetadataRepository_PropertyMap(), this.getProperty(), null, "propertyMap", null, 0, -1, MetadataRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(installableUnitEClass, InstallableUnit.class, "InstallableUnit", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getInstallableUnit_ArtifactList(), this.getArtifactKey(), null, "artifactList", null, 0, -1, InstallableUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getInstallableUnit_ProvidedCapabilityList(), this.getProvidedCapability(), null, "providedCapabilityList", null, 0, -1, InstallableUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getInstallableUnit_RequiredCapabilityList(), this.getRequiredCapability(), null, "requiredCapabilityList", null, 0, -1, InstallableUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getInstallableUnit_MetaRequiredCapabilityList(), this.getRequiredCapability(), null, "metaRequiredCapabilityList", null, 0, -1, InstallableUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getInstallableUnit_PropertyMap(), this.getProperty(), null, "propertyMap", null, 0, -1, InstallableUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getInstallableUnit_TouchpointDataList(), this.getTouchpointData(), null, "touchpointDataList", null, 0, -1, InstallableUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(installableUnitEClass, ecorePackage.getEInt(), "compareTo", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theXMLTypePackage.getAnySimpleType(), "other", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(installableUnitFragmentEClass, InstallableUnitFragment.class, "InstallableUnitFragment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getInstallableUnitFragment_HostList(), this.getRequiredCapability(), null, "hostList", null, 0, -1, InstallableUnitFragment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(licenseEClass, License.class, "License", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(providedCapabilityEClass, ProvidedCapability.class, "ProvidedCapability", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(requiredCapabilityEClass, RequiredCapability.class, "RequiredCapability", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(touchpointDataEClass, TouchpointData.class, "TouchpointData", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTouchpointData_InstructionMap(), this.getInstructionMap(), null, "instructionMap", null, 0, -1, TouchpointData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(touchpointInstructionEClass, TouchpointInstruction.class, "TouchpointInstruction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(touchpointTypeEClass, TouchpointType.class, "TouchpointType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(updateDescriptorEClass, UpdateDescriptor.class, "UpdateDescriptor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(propertyEClass, Map.Entry.class, "Property", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getProperty_Key(), ecorePackage.getEString(), "key", null, 1, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProperty_Value(), ecorePackage.getEString(), "value", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(instructionMapEClass, Map.Entry.class, "InstructionMap", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getInstructionMap_Key(), ecorePackage.getEString(), "key", null, 1, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getInstructionMap_Value(), this.getTouchpointInstruction(), null, "value", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(iQueryableEClass, IQueryable.class, "IQueryable", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		op = addEOperation(iQueryableEClass, this.getCollector(), "query", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getQuery(), "query", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getCollector(), "collector", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "progress", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(iMetadataRepositoryEClass, IMetadataRepository.class, "IMetadataRepository", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		op = addEOperation(iMetadataRepositoryEClass, null, "addInstallableUnits", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIInstallableUnitArray(), "installableUnits", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(iMetadataRepositoryEClass, null, "addReference", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theAggregatorPackage.getURI(), "location", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "nickname", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEInt(), "type", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEInt(), "options", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(iMetadataRepositoryEClass, ecorePackage.getEBoolean(), "removeInstallableUnits", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getQuery(), "query", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(iMetadataRepositoryEClass, null, "removeAll", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(iRepositoryEClass, IRepository.class, "IRepository", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIRepository_Location(), theAggregatorPackage.getURI(), "location", null, 1, 1, IRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIRepository_Name(), ecorePackage.getEString(), "name", null, 0, 1, IRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIRepository_Type(), ecorePackage.getEString(), "type", null, 0, 1, IRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIRepository_Version(), ecorePackage.getEString(), "version", null, 0, 1, IRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIRepository_Description(), ecorePackage.getEString(), "description", null, 0, 1, IRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIRepository_Provider(), ecorePackage.getEString(), "provider", null, 0, 1, IRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIRepository_Modifiable(), ecorePackage.getEBoolean(), "modifiable", null, 0, 1, IRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(iRepositoryEClass, this.getUntypedMap(), "getProperties", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(iRepositoryEClass, ecorePackage.getEString(), "setProperty", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "key", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "value", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(repositoryReferenceEClass, RepositoryReference.class, "RepositoryReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRepositoryReference_Location(), theAggregatorPackage.getURI(), "location", null, 0, 1, RepositoryReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRepositoryReference_Type(), ecorePackage.getEInt(), "type", null, 0, 1, RepositoryReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRepositoryReference_Options(), ecorePackage.getEInt(), "options", null, 0, 1, RepositoryReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRepositoryReference_Nickname(), ecorePackage.getEString(), "nickname", null, 0, 1, RepositoryReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(iAdaptableEClass, IAdaptable.class, "IAdaptable", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		op = addEOperation(iAdaptableEClass, theXMLTypePackage.getAnySimpleType(), "getAdapter", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEJavaClass(), "adapter", 0, 1, IS_UNIQUE, IS_ORDERED);

		// Initialize data types
		initEDataType(versionEDataType, Version.class, "Version", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(versionRangeEDataType, VersionRange.class, "VersionRange", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(iInstallableUnitArrayEDataType, IInstallableUnit[].class, "IInstallableUnitArray", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(iRequiredCapabilityArrayEDataType, IRequiredCapability[].class, "IRequiredCapabilityArray", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(iProvidedCapabilityArrayEDataType, IProvidedCapability[].class, "IProvidedCapabilityArray", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(iInstallableUnitFragmentArrayEDataType, IInstallableUnitFragment[].class, "IInstallableUnitFragmentArray", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(iArtifactKeyArrayEDataType, IArtifactKey[].class, "IArtifactKeyArray", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(iTouchpointDataArrayEDataType, ITouchpointData[].class, "ITouchpointDataArray", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(stringArrayEDataType, String[].class, "StringArray", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(untypedMapEDataType, Map.class, "UntypedMap", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(queryEDataType, Query.class, "Query", !IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(collectorEDataType, Collector.class, "Collector", !IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(iProgressMonitorEDataType, IProgressMonitor.class, "IProgressMonitor", !IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create annotations
		// http:///org/eclipse/emf/ecore/util/ExtendedMetaData
		createExtendedMetaDataAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createExtendedMetaDataAnnotations() {
		String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";		
		addAnnotation
		  (getRepositoryReference_Location(), 
		   source, 
		   new String[] {
		   });
	}

} // P2PackageImpl
