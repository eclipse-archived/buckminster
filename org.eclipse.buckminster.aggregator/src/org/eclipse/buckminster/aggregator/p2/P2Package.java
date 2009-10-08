/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.p2;

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
 * @see org.eclipse.buckminster.aggregator.p2.P2Factory
 * @model kind="package"
 * @generated
 */
public interface P2Package extends EPackage
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
		 * The meta object literal for the '{@link org.eclipse.equinox.internal.provisional.p2.metadata.IArtifactKey
		 * <em>IArtifact Key</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.equinox.internal.provisional.p2.metadata.IArtifactKey
		 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getIArtifactKey()
		 * @generated
		 */
		EClass IARTIFACT_KEY = eINSTANCE.getIArtifactKey();

		/**
		 * The meta object literal for the '<em><b>Classifier</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute IARTIFACT_KEY__CLASSIFIER = eINSTANCE.getIArtifactKey_Classifier();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute IARTIFACT_KEY__ID = eINSTANCE.getIArtifactKey_Id();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute IARTIFACT_KEY__VERSION = eINSTANCE.getIArtifactKey_Version();

		/**
		 * The meta object literal for the '{@link org.eclipse.equinox.internal.provisional.p2.metadata.ICopyright
		 * <em>ICopyright</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.equinox.internal.provisional.p2.metadata.ICopyright
		 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getICopyright()
		 * @generated
		 */
		EClass ICOPYRIGHT = eINSTANCE.getICopyright();

		/**
		 * The meta object literal for the '<em><b>Location</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ICOPYRIGHT__LOCATION = eINSTANCE.getICopyright_Location();

		/**
		 * The meta object literal for the '<em><b>Body</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ICOPYRIGHT__BODY = eINSTANCE.getICopyright_Body();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit <em>IInstallable Unit</em>}'
		 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit
		 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getIInstallableUnit()
		 * @generated
		 */
		EClass IINSTALLABLE_UNIT = eINSTANCE.getIInstallableUnit();

		/**
		 * The meta object literal for the '<em><b>Filter</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute IINSTALLABLE_UNIT__FILTER = eINSTANCE.getIInstallableUnit_Filter();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute IINSTALLABLE_UNIT__ID = eINSTANCE.getIInstallableUnit_Id();

		/**
		 * The meta object literal for the '<em><b>Touchpoint Type</b></em>' containment reference feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference IINSTALLABLE_UNIT__TOUCHPOINT_TYPE = eINSTANCE.getIInstallableUnit_TouchpointType();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute IINSTALLABLE_UNIT__VERSION = eINSTANCE.getIInstallableUnit_Version();

		/**
		 * The meta object literal for the '<em><b>Resolved</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute IINSTALLABLE_UNIT__RESOLVED = eINSTANCE.getIInstallableUnit_Resolved();

		/**
		 * The meta object literal for the '<em><b>Singleton</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute IINSTALLABLE_UNIT__SINGLETON = eINSTANCE.getIInstallableUnit_Singleton();

		/**
		 * The meta object literal for the '<em><b>Update Descriptor</b></em>' containment reference feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference IINSTALLABLE_UNIT__UPDATE_DESCRIPTOR = eINSTANCE.getIInstallableUnit_UpdateDescriptor();

		/**
		 * The meta object literal for the '<em><b>License</b></em>' containment reference feature. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference IINSTALLABLE_UNIT__LICENSE = eINSTANCE.getIInstallableUnit_License();

		/**
		 * The meta object literal for the '<em><b>Copyright</b></em>' containment reference feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference IINSTALLABLE_UNIT__COPYRIGHT = eINSTANCE.getIInstallableUnit_Copyright();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnitFragment
		 * <em>IInstallable Unit Fragment</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnitFragment
		 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getIInstallableUnitFragment()
		 * @generated
		 */
		EClass IINSTALLABLE_UNIT_FRAGMENT = eINSTANCE.getIInstallableUnitFragment();

		/**
		 * The meta object literal for the '{@link org.eclipse.equinox.internal.provisional.p2.metadata.ILicense
		 * <em>ILicense</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.equinox.internal.provisional.p2.metadata.ILicense
		 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getILicense()
		 * @generated
		 */
		EClass ILICENSE = eINSTANCE.getILicense();

		/**
		 * The meta object literal for the '<em><b>Location</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ILICENSE__LOCATION = eINSTANCE.getILicense_Location();

		/**
		 * The meta object literal for the '<em><b>Body</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ILICENSE__BODY = eINSTANCE.getILicense_Body();

		/**
		 * The meta object literal for the '<em><b>Digest</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ILICENSE__DIGEST = eINSTANCE.getILicense_Digest();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.equinox.internal.provisional.p2.metadata.IProvidedCapability
		 * <em>IProvided Capability</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.equinox.internal.provisional.p2.metadata.IProvidedCapability
		 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getIProvidedCapability()
		 * @generated
		 */
		EClass IPROVIDED_CAPABILITY = eINSTANCE.getIProvidedCapability();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute IPROVIDED_CAPABILITY__NAME = eINSTANCE.getIProvidedCapability_Name();

		/**
		 * The meta object literal for the '<em><b>Namespace</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute IPROVIDED_CAPABILITY__NAMESPACE = eINSTANCE.getIProvidedCapability_Namespace();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute IPROVIDED_CAPABILITY__VERSION = eINSTANCE.getIProvidedCapability_Version();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.equinox.internal.provisional.p2.metadata.IRequiredCapability
		 * <em>IRequired Capability</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.equinox.internal.provisional.p2.metadata.IRequiredCapability
		 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getIRequiredCapability()
		 * @generated
		 */
		EClass IREQUIRED_CAPABILITY = eINSTANCE.getIRequiredCapability();

		/**
		 * The meta object literal for the '<em><b>Filter</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute IREQUIRED_CAPABILITY__FILTER = eINSTANCE.getIRequiredCapability_Filter();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute IREQUIRED_CAPABILITY__NAME = eINSTANCE.getIRequiredCapability_Name();

		/**
		 * The meta object literal for the '<em><b>Namespace</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute IREQUIRED_CAPABILITY__NAMESPACE = eINSTANCE.getIRequiredCapability_Namespace();

		/**
		 * The meta object literal for the '<em><b>Range</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute IREQUIRED_CAPABILITY__RANGE = eINSTANCE.getIRequiredCapability_Range();

		/**
		 * The meta object literal for the '<em><b>Selector List</b></em>' attribute list feature. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute IREQUIRED_CAPABILITY__SELECTOR_LIST = eINSTANCE.getIRequiredCapability_SelectorList();

		/**
		 * The meta object literal for the '<em><b>Multiple</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute IREQUIRED_CAPABILITY__MULTIPLE = eINSTANCE.getIRequiredCapability_Multiple();

		/**
		 * The meta object literal for the '<em><b>Optional</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute IREQUIRED_CAPABILITY__OPTIONAL = eINSTANCE.getIRequiredCapability_Optional();

		/**
		 * The meta object literal for the '<em><b>Greedy</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute IREQUIRED_CAPABILITY__GREEDY = eINSTANCE.getIRequiredCapability_Greedy();

		/**
		 * The meta object literal for the '{@link org.eclipse.equinox.internal.provisional.p2.metadata.ITouchpointData
		 * <em>ITouchpoint Data</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.equinox.internal.provisional.p2.metadata.ITouchpointData
		 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getITouchpointData()
		 * @generated
		 */
		EClass ITOUCHPOINT_DATA = eINSTANCE.getITouchpointData();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.equinox.internal.provisional.p2.metadata.ITouchpointInstruction
		 * <em>ITouchpoint Instruction</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.equinox.internal.provisional.p2.metadata.ITouchpointInstruction
		 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getITouchpointInstruction()
		 * @generated
		 */
		EClass ITOUCHPOINT_INSTRUCTION = eINSTANCE.getITouchpointInstruction();

		/**
		 * The meta object literal for the '<em><b>Body</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ITOUCHPOINT_INSTRUCTION__BODY = eINSTANCE.getITouchpointInstruction_Body();

		/**
		 * The meta object literal for the '<em><b>Import Attribute</b></em>' attribute feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ITOUCHPOINT_INSTRUCTION__IMPORT_ATTRIBUTE = eINSTANCE.getITouchpointInstruction_ImportAttribute();

		/**
		 * The meta object literal for the '{@link org.eclipse.equinox.internal.provisional.p2.metadata.ITouchpointType
		 * <em>ITouchpoint Type</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.equinox.internal.provisional.p2.metadata.ITouchpointType
		 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getITouchpointType()
		 * @generated
		 */
		EClass ITOUCHPOINT_TYPE = eINSTANCE.getITouchpointType();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ITOUCHPOINT_TYPE__ID = eINSTANCE.getITouchpointType_Id();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ITOUCHPOINT_TYPE__VERSION = eINSTANCE.getITouchpointType_Version();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.equinox.internal.provisional.p2.metadata.IUpdateDescriptor <em>IUpdate Descriptor</em>}'
		 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.equinox.internal.provisional.p2.metadata.IUpdateDescriptor
		 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getIUpdateDescriptor()
		 * @generated
		 */
		EClass IUPDATE_DESCRIPTOR = eINSTANCE.getIUpdateDescriptor();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute IUPDATE_DESCRIPTOR__ID = eINSTANCE.getIUpdateDescriptor_Id();

		/**
		 * The meta object literal for the '<em><b>Range</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute IUPDATE_DESCRIPTOR__RANGE = eINSTANCE.getIUpdateDescriptor_Range();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute IUPDATE_DESCRIPTOR__DESCRIPTION = eINSTANCE.getIUpdateDescriptor_Description();

		/**
		 * The meta object literal for the '<em><b>Severity</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute IUPDATE_DESCRIPTOR__SEVERITY = eINSTANCE.getIUpdateDescriptor_Severity();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.p2.impl.ArtifactKeyImpl
		 * <em>Artifact Key</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.p2.impl.ArtifactKeyImpl
		 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getArtifactKey()
		 * @generated
		 */
		EClass ARTIFACT_KEY = eINSTANCE.getArtifactKey();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.p2.impl.CopyrightImpl
		 * <em>Copyright</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.p2.impl.CopyrightImpl
		 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getCopyright()
		 * @generated
		 */
		EClass COPYRIGHT = eINSTANCE.getCopyright();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.p2.impl.MetadataRepositoryImpl
		 * <em>Metadata Repository</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.p2.impl.MetadataRepositoryImpl
		 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getMetadataRepository()
		 * @generated
		 */
		EClass METADATA_REPOSITORY = eINSTANCE.getMetadataRepository();

		/**
		 * The meta object literal for the '<em><b>Installable Units</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference METADATA_REPOSITORY__INSTALLABLE_UNITS = eINSTANCE.getMetadataRepository_InstallableUnits();

		/**
		 * The meta object literal for the '<em><b>Repository References</b></em>' containment reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference METADATA_REPOSITORY__REPOSITORY_REFERENCES = eINSTANCE.getMetadataRepository_RepositoryReferences();

		/**
		 * The meta object literal for the '<em><b>Property Map</b></em>' map feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference METADATA_REPOSITORY__PROPERTY_MAP = eINSTANCE.getMetadataRepository_PropertyMap();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.p2.impl.InstallableUnitImpl
		 * <em>Installable Unit</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.p2.impl.InstallableUnitImpl
		 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getInstallableUnit()
		 * @generated
		 */
		EClass INSTALLABLE_UNIT = eINSTANCE.getInstallableUnit();

		/**
		 * The meta object literal for the '<em><b>Artifact List</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference INSTALLABLE_UNIT__ARTIFACT_LIST = eINSTANCE.getInstallableUnit_ArtifactList();

		/**
		 * The meta object literal for the '<em><b>Provided Capability List</b></em>' containment reference list
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference INSTALLABLE_UNIT__PROVIDED_CAPABILITY_LIST = eINSTANCE.getInstallableUnit_ProvidedCapabilityList();

		/**
		 * The meta object literal for the '<em><b>Required Capability List</b></em>' containment reference list
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference INSTALLABLE_UNIT__REQUIRED_CAPABILITY_LIST = eINSTANCE.getInstallableUnit_RequiredCapabilityList();

		/**
		 * The meta object literal for the '<em><b>Meta Required Capability List</b></em>' containment reference list
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference INSTALLABLE_UNIT__META_REQUIRED_CAPABILITY_LIST = eINSTANCE.getInstallableUnit_MetaRequiredCapabilityList();

		/**
		 * The meta object literal for the '<em><b>Property Map</b></em>' map feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference INSTALLABLE_UNIT__PROPERTY_MAP = eINSTANCE.getInstallableUnit_PropertyMap();

		/**
		 * The meta object literal for the '<em><b>Touchpoint Data List</b></em>' containment reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference INSTALLABLE_UNIT__TOUCHPOINT_DATA_LIST = eINSTANCE.getInstallableUnit_TouchpointDataList();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute INSTALLABLE_UNIT__TYPE = eINSTANCE.getInstallableUnit_Type();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.buckminster.aggregator.p2.impl.InstallableUnitFragmentImpl
		 * <em>Installable Unit Fragment</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.p2.impl.InstallableUnitFragmentImpl
		 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getInstallableUnitFragment()
		 * @generated
		 */
		EClass INSTALLABLE_UNIT_FRAGMENT = eINSTANCE.getInstallableUnitFragment();

		/**
		 * The meta object literal for the '<em><b>Host List</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference INSTALLABLE_UNIT_FRAGMENT__HOST_LIST = eINSTANCE.getInstallableUnitFragment_HostList();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.p2.impl.LicenseImpl
		 * <em>License</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.p2.impl.LicenseImpl
		 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getLicense()
		 * @generated
		 */
		EClass LICENSE = eINSTANCE.getLicense();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.p2.impl.ProvidedCapabilityImpl
		 * <em>Provided Capability</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.p2.impl.ProvidedCapabilityImpl
		 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getProvidedCapability()
		 * @generated
		 */
		EClass PROVIDED_CAPABILITY = eINSTANCE.getProvidedCapability();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.p2.impl.RequiredCapabilityImpl
		 * <em>Required Capability</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.p2.impl.RequiredCapabilityImpl
		 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getRequiredCapability()
		 * @generated
		 */
		EClass REQUIRED_CAPABILITY = eINSTANCE.getRequiredCapability();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.p2.impl.TouchpointDataImpl
		 * <em>Touchpoint Data</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.p2.impl.TouchpointDataImpl
		 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getTouchpointData()
		 * @generated
		 */
		EClass TOUCHPOINT_DATA = eINSTANCE.getTouchpointData();

		/**
		 * The meta object literal for the '<em><b>Instruction Map</b></em>' map feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference TOUCHPOINT_DATA__INSTRUCTION_MAP = eINSTANCE.getTouchpointData_InstructionMap();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.p2.impl.TouchpointInstructionImpl
		 * <em>Touchpoint Instruction</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.p2.impl.TouchpointInstructionImpl
		 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getTouchpointInstruction()
		 * @generated
		 */
		EClass TOUCHPOINT_INSTRUCTION = eINSTANCE.getTouchpointInstruction();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.p2.impl.TouchpointTypeImpl
		 * <em>Touchpoint Type</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.p2.impl.TouchpointTypeImpl
		 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getTouchpointType()
		 * @generated
		 */
		EClass TOUCHPOINT_TYPE = eINSTANCE.getTouchpointType();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.p2.impl.UpdateDescriptorImpl
		 * <em>Update Descriptor</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.p2.impl.UpdateDescriptorImpl
		 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getUpdateDescriptor()
		 * @generated
		 */
		EClass UPDATE_DESCRIPTOR = eINSTANCE.getUpdateDescriptor();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.p2.impl.PropertyImpl
		 * <em>Property</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.p2.impl.PropertyImpl
		 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getProperty()
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
		 * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.p2.impl.InstructionMapImpl
		 * <em>Instruction Map</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.p2.impl.InstructionMapImpl
		 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getInstructionMap()
		 * @generated
		 */
		EClass INSTRUCTION_MAP = eINSTANCE.getInstructionMap();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute INSTRUCTION_MAP__KEY = eINSTANCE.getInstructionMap_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference INSTRUCTION_MAP__VALUE = eINSTANCE.getInstructionMap_Value();

		/**
		 * The meta object literal for the '{@link org.eclipse.equinox.internal.provisional.p2.query.IQueryable
		 * <em>IQueryable</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.equinox.internal.provisional.p2.query.IQueryable
		 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getIQueryable()
		 * @generated
		 */
		EClass IQUERYABLE = eINSTANCE.getIQueryable();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepository
		 * <em>IMetadata Repository</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepository
		 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getIMetadataRepository()
		 * @generated
		 */
		EClass IMETADATA_REPOSITORY = eINSTANCE.getIMetadataRepository();

		/**
		 * The meta object literal for the '{@link org.eclipse.equinox.internal.provisional.p2.repository.IRepository
		 * <em>IRepository</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.equinox.internal.provisional.p2.repository.IRepository
		 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getIRepository()
		 * @generated
		 */
		EClass IREPOSITORY = eINSTANCE.getIRepository();

		/**
		 * The meta object literal for the '<em><b>Location</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute IREPOSITORY__LOCATION = eINSTANCE.getIRepository_Location();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute IREPOSITORY__NAME = eINSTANCE.getIRepository_Name();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute IREPOSITORY__TYPE = eINSTANCE.getIRepository_Type();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute IREPOSITORY__VERSION = eINSTANCE.getIRepository_Version();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute IREPOSITORY__DESCRIPTION = eINSTANCE.getIRepository_Description();

		/**
		 * The meta object literal for the '<em><b>Provider</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute IREPOSITORY__PROVIDER = eINSTANCE.getIRepository_Provider();

		/**
		 * The meta object literal for the '<em><b>Modifiable</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute IREPOSITORY__MODIFIABLE = eINSTANCE.getIRepository_Modifiable();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.p2.impl.RepositoryReferenceImpl
		 * <em>Repository Reference</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.p2.impl.RepositoryReferenceImpl
		 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getRepositoryReference()
		 * @generated
		 */
		EClass REPOSITORY_REFERENCE = eINSTANCE.getRepositoryReference();

		/**
		 * The meta object literal for the '<em><b>Location</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute REPOSITORY_REFERENCE__LOCATION = eINSTANCE.getRepositoryReference_Location();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute REPOSITORY_REFERENCE__TYPE = eINSTANCE.getRepositoryReference_Type();

		/**
		 * The meta object literal for the '<em><b>Options</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute REPOSITORY_REFERENCE__OPTIONS = eINSTANCE.getRepositoryReference_Options();

		/**
		 * The meta object literal for the '<em><b>Nickname</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute REPOSITORY_REFERENCE__NICKNAME = eINSTANCE.getRepositoryReference_Nickname();

		/**
		 * The meta object literal for the '{@link org.eclipse.core.runtime.IAdaptable <em>IAdaptable</em>}' class. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.core.runtime.IAdaptable
		 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getIAdaptable()
		 * @generated
		 */
		EClass IADAPTABLE = eINSTANCE.getIAdaptable();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.p2.InstallableUnitType
		 * <em>Installable Unit Type</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.p2.InstallableUnitType
		 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getInstallableUnitType()
		 * @generated
		 */
		EEnum INSTALLABLE_UNIT_TYPE = eINSTANCE.getInstallableUnitType();

		/**
		 * The meta object literal for the '<em>Version</em>' data type. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.equinox.internal.provisional.p2.core.Version
		 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getVersion()
		 * @generated
		 */
		EDataType VERSION = eINSTANCE.getVersion();

		/**
		 * The meta object literal for the '<em>Version Range</em>' data type. <!-- begin-user-doc --> <!-- end-user-doc
		 * -->
		 * 
		 * @see org.eclipse.equinox.internal.provisional.p2.core.VersionRange
		 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getVersionRange()
		 * @generated
		 */
		EDataType VERSION_RANGE = eINSTANCE.getVersionRange();

		/**
		 * The meta object literal for the '<em>IInstallable Unit Array</em>' data type. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getIInstallableUnitArray()
		 * @generated
		 */
		EDataType IINSTALLABLE_UNIT_ARRAY = eINSTANCE.getIInstallableUnitArray();

		/**
		 * The meta object literal for the '<em>IRequired Capability Array</em>' data type. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getIRequiredCapabilityArray()
		 * @generated
		 */
		EDataType IREQUIRED_CAPABILITY_ARRAY = eINSTANCE.getIRequiredCapabilityArray();

		/**
		 * The meta object literal for the '<em>IProvided Capability Array</em>' data type. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getIProvidedCapabilityArray()
		 * @generated
		 */
		EDataType IPROVIDED_CAPABILITY_ARRAY = eINSTANCE.getIProvidedCapabilityArray();

		/**
		 * The meta object literal for the '<em>IInstallable Unit Fragment Array</em>' data type. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getIInstallableUnitFragmentArray()
		 * @generated
		 */
		EDataType IINSTALLABLE_UNIT_FRAGMENT_ARRAY = eINSTANCE.getIInstallableUnitFragmentArray();

		/**
		 * The meta object literal for the '<em>IArtifact Key Array</em>' data type. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getIArtifactKeyArray()
		 * @generated
		 */
		EDataType IARTIFACT_KEY_ARRAY = eINSTANCE.getIArtifactKeyArray();

		/**
		 * The meta object literal for the '<em>ITouchpoint Data Array</em>' data type. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getITouchpointDataArray()
		 * @generated
		 */
		EDataType ITOUCHPOINT_DATA_ARRAY = eINSTANCE.getITouchpointDataArray();

		/**
		 * The meta object literal for the '<em>String Array</em>' data type. <!-- begin-user-doc --> <!-- end-user-doc
		 * -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getStringArray()
		 * @generated
		 */
		EDataType STRING_ARRAY = eINSTANCE.getStringArray();

		/**
		 * The meta object literal for the '<em>Untyped Map</em>' data type. <!-- begin-user-doc --> <!-- end-user-doc
		 * -->
		 * 
		 * @see java.util.Map
		 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getUntypedMap()
		 * @generated
		 */
		EDataType UNTYPED_MAP = eINSTANCE.getUntypedMap();

		/**
		 * The meta object literal for the '<em>Query</em>' data type. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.equinox.internal.provisional.p2.query.Query
		 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getQuery()
		 * @generated
		 */
		EDataType QUERY = eINSTANCE.getQuery();

		/**
		 * The meta object literal for the '<em>Collector</em>' data type. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.equinox.internal.provisional.p2.query.Collector
		 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getCollector()
		 * @generated
		 */
		EDataType COLLECTOR = eINSTANCE.getCollector();

		/**
		 * The meta object literal for the '<em>IProgress Monitor</em>' data type. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.eclipse.core.runtime.IProgressMonitor
		 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getIProgressMonitor()
		 * @generated
		 */
		EDataType IPROGRESS_MONITOR = eINSTANCE.getIProgressMonitor();

	}

	/**
	 * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNAME = "p2";

	/**
	 * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/buckminster/2009/p2";

	/**
	 * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_PREFIX = "p2";

	/**
	 * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	P2Package eINSTANCE = org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.equinox.internal.provisional.p2.metadata.IArtifactKey
	 * <em>IArtifact Key</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.IArtifactKey
	 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getIArtifactKey()
	 * @generated
	 */
	int IARTIFACT_KEY = 0;

	/**
	 * The feature id for the '<em><b>Classifier</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IARTIFACT_KEY__CLASSIFIER = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IARTIFACT_KEY__ID = 1;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IARTIFACT_KEY__VERSION = 2;

	/**
	 * The number of structural features of the '<em>IArtifact Key</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IARTIFACT_KEY_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.eclipse.equinox.internal.provisional.p2.metadata.ICopyright
	 * <em>ICopyright</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.ICopyright
	 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getICopyright()
	 * @generated
	 */
	int ICOPYRIGHT = 1;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ICOPYRIGHT__LOCATION = 0;

	/**
	 * The feature id for the '<em><b>Body</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ICOPYRIGHT__BODY = 1;

	/**
	 * The number of structural features of the '<em>ICopyright</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ICOPYRIGHT_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit
	 * <em>IInstallable Unit</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit
	 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getIInstallableUnit()
	 * @generated
	 */
	int IINSTALLABLE_UNIT = 2;

	/**
	 * The feature id for the '<em><b>Filter</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IINSTALLABLE_UNIT__FILTER = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IINSTALLABLE_UNIT__ID = 1;

	/**
	 * The feature id for the '<em><b>Touchpoint Type</b></em>' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IINSTALLABLE_UNIT__TOUCHPOINT_TYPE = 2;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IINSTALLABLE_UNIT__VERSION = 3;

	/**
	 * The feature id for the '<em><b>Resolved</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IINSTALLABLE_UNIT__RESOLVED = 4;

	/**
	 * The feature id for the '<em><b>Singleton</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IINSTALLABLE_UNIT__SINGLETON = 5;

	/**
	 * The feature id for the '<em><b>Update Descriptor</b></em>' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IINSTALLABLE_UNIT__UPDATE_DESCRIPTOR = 6;

	/**
	 * The feature id for the '<em><b>License</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IINSTALLABLE_UNIT__LICENSE = 7;

	/**
	 * The feature id for the '<em><b>Copyright</b></em>' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IINSTALLABLE_UNIT__COPYRIGHT = 8;

	/**
	 * The number of structural features of the '<em>IInstallable Unit</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IINSTALLABLE_UNIT_FEATURE_COUNT = 9;

	/**
	 * The meta object id for the '{@link org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnitFragment
	 * <em>IInstallable Unit Fragment</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnitFragment
	 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getIInstallableUnitFragment()
	 * @generated
	 */
	int IINSTALLABLE_UNIT_FRAGMENT = 3;

	/**
	 * The feature id for the '<em><b>Filter</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IINSTALLABLE_UNIT_FRAGMENT__FILTER = IINSTALLABLE_UNIT__FILTER;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IINSTALLABLE_UNIT_FRAGMENT__ID = IINSTALLABLE_UNIT__ID;

	/**
	 * The feature id for the '<em><b>Touchpoint Type</b></em>' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IINSTALLABLE_UNIT_FRAGMENT__TOUCHPOINT_TYPE = IINSTALLABLE_UNIT__TOUCHPOINT_TYPE;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IINSTALLABLE_UNIT_FRAGMENT__VERSION = IINSTALLABLE_UNIT__VERSION;

	/**
	 * The feature id for the '<em><b>Resolved</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IINSTALLABLE_UNIT_FRAGMENT__RESOLVED = IINSTALLABLE_UNIT__RESOLVED;

	/**
	 * The feature id for the '<em><b>Singleton</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IINSTALLABLE_UNIT_FRAGMENT__SINGLETON = IINSTALLABLE_UNIT__SINGLETON;

	/**
	 * The feature id for the '<em><b>Update Descriptor</b></em>' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IINSTALLABLE_UNIT_FRAGMENT__UPDATE_DESCRIPTOR = IINSTALLABLE_UNIT__UPDATE_DESCRIPTOR;

	/**
	 * The feature id for the '<em><b>License</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IINSTALLABLE_UNIT_FRAGMENT__LICENSE = IINSTALLABLE_UNIT__LICENSE;

	/**
	 * The feature id for the '<em><b>Copyright</b></em>' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IINSTALLABLE_UNIT_FRAGMENT__COPYRIGHT = IINSTALLABLE_UNIT__COPYRIGHT;

	/**
	 * The number of structural features of the '<em>IInstallable Unit Fragment</em>' class. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IINSTALLABLE_UNIT_FRAGMENT_FEATURE_COUNT = IINSTALLABLE_UNIT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.equinox.internal.provisional.p2.metadata.ILicense
	 * <em>ILicense</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.ILicense
	 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getILicense()
	 * @generated
	 */
	int ILICENSE = 4;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ILICENSE__LOCATION = 0;

	/**
	 * The feature id for the '<em><b>Body</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ILICENSE__BODY = 1;

	/**
	 * The feature id for the '<em><b>Digest</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ILICENSE__DIGEST = 2;

	/**
	 * The number of structural features of the '<em>ILicense</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ILICENSE_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.eclipse.equinox.internal.provisional.p2.metadata.IProvidedCapability
	 * <em>IProvided Capability</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.IProvidedCapability
	 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getIProvidedCapability()
	 * @generated
	 */
	int IPROVIDED_CAPABILITY = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IPROVIDED_CAPABILITY__NAME = 0;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IPROVIDED_CAPABILITY__NAMESPACE = 1;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IPROVIDED_CAPABILITY__VERSION = 2;

	/**
	 * The number of structural features of the '<em>IProvided Capability</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IPROVIDED_CAPABILITY_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.eclipse.equinox.internal.provisional.p2.metadata.IRequiredCapability
	 * <em>IRequired Capability</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.IRequiredCapability
	 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getIRequiredCapability()
	 * @generated
	 */
	int IREQUIRED_CAPABILITY = 6;

	/**
	 * The feature id for the '<em><b>Filter</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IREQUIRED_CAPABILITY__FILTER = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IREQUIRED_CAPABILITY__NAME = 1;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IREQUIRED_CAPABILITY__NAMESPACE = 2;

	/**
	 * The feature id for the '<em><b>Range</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IREQUIRED_CAPABILITY__RANGE = 3;

	/**
	 * The feature id for the '<em><b>Selector List</b></em>' attribute list. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IREQUIRED_CAPABILITY__SELECTOR_LIST = 4;

	/**
	 * The feature id for the '<em><b>Multiple</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IREQUIRED_CAPABILITY__MULTIPLE = 5;

	/**
	 * The feature id for the '<em><b>Optional</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IREQUIRED_CAPABILITY__OPTIONAL = 6;

	/**
	 * The feature id for the '<em><b>Greedy</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IREQUIRED_CAPABILITY__GREEDY = 7;

	/**
	 * The number of structural features of the '<em>IRequired Capability</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IREQUIRED_CAPABILITY_FEATURE_COUNT = 8;

	/**
	 * The meta object id for the '{@link org.eclipse.equinox.internal.provisional.p2.metadata.ITouchpointData
	 * <em>ITouchpoint Data</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.ITouchpointData
	 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getITouchpointData()
	 * @generated
	 */
	int ITOUCHPOINT_DATA = 7;

	/**
	 * The number of structural features of the '<em>ITouchpoint Data</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ITOUCHPOINT_DATA_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.equinox.internal.provisional.p2.metadata.ITouchpointInstruction
	 * <em>ITouchpoint Instruction</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.ITouchpointInstruction
	 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getITouchpointInstruction()
	 * @generated
	 */
	int ITOUCHPOINT_INSTRUCTION = 8;

	/**
	 * The feature id for the '<em><b>Body</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ITOUCHPOINT_INSTRUCTION__BODY = 0;

	/**
	 * The feature id for the '<em><b>Import Attribute</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ITOUCHPOINT_INSTRUCTION__IMPORT_ATTRIBUTE = 1;

	/**
	 * The number of structural features of the '<em>ITouchpoint Instruction</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ITOUCHPOINT_INSTRUCTION_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.equinox.internal.provisional.p2.metadata.ITouchpointType
	 * <em>ITouchpoint Type</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.ITouchpointType
	 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getITouchpointType()
	 * @generated
	 */
	int ITOUCHPOINT_TYPE = 9;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ITOUCHPOINT_TYPE__ID = 0;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ITOUCHPOINT_TYPE__VERSION = 1;

	/**
	 * The number of structural features of the '<em>ITouchpoint Type</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ITOUCHPOINT_TYPE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.equinox.internal.provisional.p2.metadata.IUpdateDescriptor
	 * <em>IUpdate Descriptor</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.IUpdateDescriptor
	 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getIUpdateDescriptor()
	 * @generated
	 */
	int IUPDATE_DESCRIPTOR = 10;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IUPDATE_DESCRIPTOR__ID = 0;

	/**
	 * The feature id for the '<em><b>Range</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IUPDATE_DESCRIPTOR__RANGE = 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IUPDATE_DESCRIPTOR__DESCRIPTION = 2;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IUPDATE_DESCRIPTOR__SEVERITY = 3;

	/**
	 * The number of structural features of the '<em>IUpdate Descriptor</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IUPDATE_DESCRIPTOR_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.aggregator.p2.impl.ArtifactKeyImpl
	 * <em>Artifact Key</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.p2.impl.ArtifactKeyImpl
	 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getArtifactKey()
	 * @generated
	 */
	int ARTIFACT_KEY = 11;

	/**
	 * The feature id for the '<em><b>Classifier</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_KEY__CLASSIFIER = IARTIFACT_KEY__CLASSIFIER;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_KEY__ID = IARTIFACT_KEY__ID;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_KEY__VERSION = IARTIFACT_KEY__VERSION;

	/**
	 * The number of structural features of the '<em>Artifact Key</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_KEY_FEATURE_COUNT = IARTIFACT_KEY_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.aggregator.p2.impl.CopyrightImpl <em>Copyright</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.p2.impl.CopyrightImpl
	 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getCopyright()
	 * @generated
	 */
	int COPYRIGHT = 12;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COPYRIGHT__LOCATION = ICOPYRIGHT__LOCATION;

	/**
	 * The feature id for the '<em><b>Body</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COPYRIGHT__BODY = ICOPYRIGHT__BODY;

	/**
	 * The number of structural features of the '<em>Copyright</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COPYRIGHT_FEATURE_COUNT = ICOPYRIGHT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.equinox.internal.provisional.p2.query.IQueryable
	 * <em>IQueryable</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.equinox.internal.provisional.p2.query.IQueryable
	 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getIQueryable()
	 * @generated
	 */
	int IQUERYABLE = 25;

	/**
	 * The number of structural features of the '<em>IQueryable</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IQUERYABLE_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '
	 * {@link org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepository
	 * <em>IMetadata Repository</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepository
	 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getIMetadataRepository()
	 * @generated
	 */
	int IMETADATA_REPOSITORY = 26;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IMETADATA_REPOSITORY__LOCATION = IQUERYABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IMETADATA_REPOSITORY__NAME = IQUERYABLE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IMETADATA_REPOSITORY__TYPE = IQUERYABLE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IMETADATA_REPOSITORY__VERSION = IQUERYABLE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IMETADATA_REPOSITORY__DESCRIPTION = IQUERYABLE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Provider</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IMETADATA_REPOSITORY__PROVIDER = IQUERYABLE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Modifiable</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IMETADATA_REPOSITORY__MODIFIABLE = IQUERYABLE_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>IMetadata Repository</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IMETADATA_REPOSITORY_FEATURE_COUNT = IQUERYABLE_FEATURE_COUNT + 7;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.aggregator.p2.impl.MetadataRepositoryImpl
	 * <em>Metadata Repository</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.p2.impl.MetadataRepositoryImpl
	 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getMetadataRepository()
	 * @generated
	 */
	int METADATA_REPOSITORY = 13;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int METADATA_REPOSITORY__LOCATION = IMETADATA_REPOSITORY__LOCATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int METADATA_REPOSITORY__NAME = IMETADATA_REPOSITORY__NAME;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int METADATA_REPOSITORY__TYPE = IMETADATA_REPOSITORY__TYPE;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int METADATA_REPOSITORY__VERSION = IMETADATA_REPOSITORY__VERSION;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int METADATA_REPOSITORY__DESCRIPTION = IMETADATA_REPOSITORY__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Provider</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int METADATA_REPOSITORY__PROVIDER = IMETADATA_REPOSITORY__PROVIDER;

	/**
	 * The feature id for the '<em><b>Modifiable</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int METADATA_REPOSITORY__MODIFIABLE = IMETADATA_REPOSITORY__MODIFIABLE;

	/**
	 * The feature id for the '<em><b>Installable Units</b></em>' containment reference list. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int METADATA_REPOSITORY__INSTALLABLE_UNITS = IMETADATA_REPOSITORY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Repository References</b></em>' containment reference list. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int METADATA_REPOSITORY__REPOSITORY_REFERENCES = IMETADATA_REPOSITORY_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Property Map</b></em>' map. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int METADATA_REPOSITORY__PROPERTY_MAP = IMETADATA_REPOSITORY_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Metadata Repository</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int METADATA_REPOSITORY_FEATURE_COUNT = IMETADATA_REPOSITORY_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.aggregator.p2.impl.InstallableUnitImpl
	 * <em>Installable Unit</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.p2.impl.InstallableUnitImpl
	 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getInstallableUnit()
	 * @generated
	 */
	int INSTALLABLE_UNIT = 14;

	/**
	 * The feature id for the '<em><b>Filter</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INSTALLABLE_UNIT__FILTER = IINSTALLABLE_UNIT__FILTER;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INSTALLABLE_UNIT__ID = IINSTALLABLE_UNIT__ID;

	/**
	 * The feature id for the '<em><b>Touchpoint Type</b></em>' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INSTALLABLE_UNIT__TOUCHPOINT_TYPE = IINSTALLABLE_UNIT__TOUCHPOINT_TYPE;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INSTALLABLE_UNIT__VERSION = IINSTALLABLE_UNIT__VERSION;

	/**
	 * The feature id for the '<em><b>Resolved</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INSTALLABLE_UNIT__RESOLVED = IINSTALLABLE_UNIT__RESOLVED;

	/**
	 * The feature id for the '<em><b>Singleton</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INSTALLABLE_UNIT__SINGLETON = IINSTALLABLE_UNIT__SINGLETON;

	/**
	 * The feature id for the '<em><b>Update Descriptor</b></em>' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INSTALLABLE_UNIT__UPDATE_DESCRIPTOR = IINSTALLABLE_UNIT__UPDATE_DESCRIPTOR;

	/**
	 * The feature id for the '<em><b>License</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INSTALLABLE_UNIT__LICENSE = IINSTALLABLE_UNIT__LICENSE;

	/**
	 * The feature id for the '<em><b>Copyright</b></em>' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INSTALLABLE_UNIT__COPYRIGHT = IINSTALLABLE_UNIT__COPYRIGHT;

	/**
	 * The feature id for the '<em><b>Artifact List</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INSTALLABLE_UNIT__ARTIFACT_LIST = IINSTALLABLE_UNIT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Provided Capability List</b></em>' containment reference list. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INSTALLABLE_UNIT__PROVIDED_CAPABILITY_LIST = IINSTALLABLE_UNIT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Required Capability List</b></em>' containment reference list. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INSTALLABLE_UNIT__REQUIRED_CAPABILITY_LIST = IINSTALLABLE_UNIT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Meta Required Capability List</b></em>' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INSTALLABLE_UNIT__META_REQUIRED_CAPABILITY_LIST = IINSTALLABLE_UNIT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Property Map</b></em>' map. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INSTALLABLE_UNIT__PROPERTY_MAP = IINSTALLABLE_UNIT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Touchpoint Data List</b></em>' containment reference list. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INSTALLABLE_UNIT__TOUCHPOINT_DATA_LIST = IINSTALLABLE_UNIT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INSTALLABLE_UNIT__TYPE = IINSTALLABLE_UNIT_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>Installable Unit</em>' class. <!-- begin-user-doc --> <!-- =======
	 * The number of structural features of the '<em>Installable Unit</em>' class. <!-- begin-user-doc --> <!-- >>>>>>>
	 * .r10620 end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INSTALLABLE_UNIT_FEATURE_COUNT = IINSTALLABLE_UNIT_FEATURE_COUNT + 7;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.aggregator.p2.impl.InstallableUnitFragmentImpl
	 * <em>Installable Unit Fragment</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.p2.impl.InstallableUnitFragmentImpl
	 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getInstallableUnitFragment()
	 * @generated
	 */
	int INSTALLABLE_UNIT_FRAGMENT = 15;

	/**
	 * The feature id for the '<em><b>Filter</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INSTALLABLE_UNIT_FRAGMENT__FILTER = INSTALLABLE_UNIT__FILTER;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INSTALLABLE_UNIT_FRAGMENT__ID = INSTALLABLE_UNIT__ID;

	/**
	 * The feature id for the '<em><b>Touchpoint Type</b></em>' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INSTALLABLE_UNIT_FRAGMENT__TOUCHPOINT_TYPE = INSTALLABLE_UNIT__TOUCHPOINT_TYPE;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INSTALLABLE_UNIT_FRAGMENT__VERSION = INSTALLABLE_UNIT__VERSION;

	/**
	 * The feature id for the '<em><b>Resolved</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INSTALLABLE_UNIT_FRAGMENT__RESOLVED = INSTALLABLE_UNIT__RESOLVED;

	/**
	 * The feature id for the '<em><b>Singleton</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INSTALLABLE_UNIT_FRAGMENT__SINGLETON = INSTALLABLE_UNIT__SINGLETON;

	/**
	 * The feature id for the '<em><b>Update Descriptor</b></em>' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INSTALLABLE_UNIT_FRAGMENT__UPDATE_DESCRIPTOR = INSTALLABLE_UNIT__UPDATE_DESCRIPTOR;

	/**
	 * The feature id for the '<em><b>License</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INSTALLABLE_UNIT_FRAGMENT__LICENSE = INSTALLABLE_UNIT__LICENSE;

	/**
	 * The feature id for the '<em><b>Copyright</b></em>' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INSTALLABLE_UNIT_FRAGMENT__COPYRIGHT = INSTALLABLE_UNIT__COPYRIGHT;

	/**
	 * The feature id for the '<em><b>Artifact List</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INSTALLABLE_UNIT_FRAGMENT__ARTIFACT_LIST = INSTALLABLE_UNIT__ARTIFACT_LIST;

	/**
	 * The feature id for the '<em><b>Provided Capability List</b></em>' containment reference list. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INSTALLABLE_UNIT_FRAGMENT__PROVIDED_CAPABILITY_LIST = INSTALLABLE_UNIT__PROVIDED_CAPABILITY_LIST;

	/**
	 * The feature id for the '<em><b>Required Capability List</b></em>' containment reference list. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INSTALLABLE_UNIT_FRAGMENT__REQUIRED_CAPABILITY_LIST = INSTALLABLE_UNIT__REQUIRED_CAPABILITY_LIST;

	/**
	 * The feature id for the '<em><b>Meta Required Capability List</b></em>' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INSTALLABLE_UNIT_FRAGMENT__META_REQUIRED_CAPABILITY_LIST = INSTALLABLE_UNIT__META_REQUIRED_CAPABILITY_LIST;

	/**
	 * The feature id for the '<em><b>Property Map</b></em>' map. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INSTALLABLE_UNIT_FRAGMENT__PROPERTY_MAP = INSTALLABLE_UNIT__PROPERTY_MAP;

	/**
	 * The feature id for the '<em><b>Touchpoint Data List</b></em>' containment reference list. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INSTALLABLE_UNIT_FRAGMENT__TOUCHPOINT_DATA_LIST = INSTALLABLE_UNIT__TOUCHPOINT_DATA_LIST;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INSTALLABLE_UNIT_FRAGMENT__TYPE = INSTALLABLE_UNIT__TYPE;

	/**
	 * The feature id for the '<em><b>Host List</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INSTALLABLE_UNIT_FRAGMENT__HOST_LIST = INSTALLABLE_UNIT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Installable Unit Fragment</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INSTALLABLE_UNIT_FRAGMENT_FEATURE_COUNT = INSTALLABLE_UNIT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.aggregator.p2.impl.LicenseImpl <em>License</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.p2.impl.LicenseImpl
	 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getLicense()
	 * @generated
	 */
	int LICENSE = 16;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int LICENSE__LOCATION = ILICENSE__LOCATION;

	/**
	 * The feature id for the '<em><b>Body</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int LICENSE__BODY = ILICENSE__BODY;

	/**
	 * The feature id for the '<em><b>Digest</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int LICENSE__DIGEST = ILICENSE__DIGEST;

	/**
	 * The number of structural features of the '<em>License</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int LICENSE_FEATURE_COUNT = ILICENSE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.aggregator.p2.impl.ProvidedCapabilityImpl
	 * <em>Provided Capability</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.p2.impl.ProvidedCapabilityImpl
	 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getProvidedCapability()
	 * @generated
	 */
	int PROVIDED_CAPABILITY = 17;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROVIDED_CAPABILITY__NAME = IPROVIDED_CAPABILITY__NAME;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROVIDED_CAPABILITY__NAMESPACE = IPROVIDED_CAPABILITY__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROVIDED_CAPABILITY__VERSION = IPROVIDED_CAPABILITY__VERSION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROVIDED_CAPABILITY__LABEL = IPROVIDED_CAPABILITY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Provided Capability</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROVIDED_CAPABILITY_FEATURE_COUNT = IPROVIDED_CAPABILITY_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.aggregator.p2.impl.RequiredCapabilityImpl
	 * <em>Required Capability</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.p2.impl.RequiredCapabilityImpl
	 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getRequiredCapability()
	 * @generated
	 */
	int REQUIRED_CAPABILITY = 18;

	/**
	 * The feature id for the '<em><b>Filter</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REQUIRED_CAPABILITY__FILTER = IREQUIRED_CAPABILITY__FILTER;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REQUIRED_CAPABILITY__NAME = IREQUIRED_CAPABILITY__NAME;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REQUIRED_CAPABILITY__NAMESPACE = IREQUIRED_CAPABILITY__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Range</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REQUIRED_CAPABILITY__RANGE = IREQUIRED_CAPABILITY__RANGE;

	/**
	 * The feature id for the '<em><b>Selector List</b></em>' attribute list. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REQUIRED_CAPABILITY__SELECTOR_LIST = IREQUIRED_CAPABILITY__SELECTOR_LIST;

	/**
	 * The feature id for the '<em><b>Multiple</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REQUIRED_CAPABILITY__MULTIPLE = IREQUIRED_CAPABILITY__MULTIPLE;

	/**
	 * The feature id for the '<em><b>Optional</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REQUIRED_CAPABILITY__OPTIONAL = IREQUIRED_CAPABILITY__OPTIONAL;

	/**
	 * The feature id for the '<em><b>Greedy</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REQUIRED_CAPABILITY__GREEDY = IREQUIRED_CAPABILITY__GREEDY;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REQUIRED_CAPABILITY__LABEL = IREQUIRED_CAPABILITY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Required Capability</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REQUIRED_CAPABILITY_FEATURE_COUNT = IREQUIRED_CAPABILITY_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.aggregator.p2.impl.TouchpointDataImpl
	 * <em>Touchpoint Data</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.p2.impl.TouchpointDataImpl
	 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getTouchpointData()
	 * @generated
	 */
	int TOUCHPOINT_DATA = 19;

	/**
	 * The feature id for the '<em><b>Instruction Map</b></em>' map. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TOUCHPOINT_DATA__INSTRUCTION_MAP = ITOUCHPOINT_DATA_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Touchpoint Data</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TOUCHPOINT_DATA_FEATURE_COUNT = ITOUCHPOINT_DATA_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.aggregator.p2.impl.TouchpointInstructionImpl
	 * <em>Touchpoint Instruction</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.p2.impl.TouchpointInstructionImpl
	 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getTouchpointInstruction()
	 * @generated
	 */
	int TOUCHPOINT_INSTRUCTION = 20;

	/**
	 * The feature id for the '<em><b>Body</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TOUCHPOINT_INSTRUCTION__BODY = ITOUCHPOINT_INSTRUCTION__BODY;

	/**
	 * The feature id for the '<em><b>Import Attribute</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TOUCHPOINT_INSTRUCTION__IMPORT_ATTRIBUTE = ITOUCHPOINT_INSTRUCTION__IMPORT_ATTRIBUTE;

	/**
	 * The number of structural features of the '<em>Touchpoint Instruction</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TOUCHPOINT_INSTRUCTION_FEATURE_COUNT = ITOUCHPOINT_INSTRUCTION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.aggregator.p2.impl.TouchpointTypeImpl
	 * <em>Touchpoint Type</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.p2.impl.TouchpointTypeImpl
	 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getTouchpointType()
	 * @generated
	 */
	int TOUCHPOINT_TYPE = 21;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TOUCHPOINT_TYPE__ID = ITOUCHPOINT_TYPE__ID;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TOUCHPOINT_TYPE__VERSION = ITOUCHPOINT_TYPE__VERSION;

	/**
	 * The number of structural features of the '<em>Touchpoint Type</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TOUCHPOINT_TYPE_FEATURE_COUNT = ITOUCHPOINT_TYPE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.aggregator.p2.impl.UpdateDescriptorImpl
	 * <em>Update Descriptor</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.p2.impl.UpdateDescriptorImpl
	 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getUpdateDescriptor()
	 * @generated
	 */
	int UPDATE_DESCRIPTOR = 22;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int UPDATE_DESCRIPTOR__ID = IUPDATE_DESCRIPTOR__ID;

	/**
	 * The feature id for the '<em><b>Range</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int UPDATE_DESCRIPTOR__RANGE = IUPDATE_DESCRIPTOR__RANGE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int UPDATE_DESCRIPTOR__DESCRIPTION = IUPDATE_DESCRIPTOR__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int UPDATE_DESCRIPTOR__SEVERITY = IUPDATE_DESCRIPTOR__SEVERITY;

	/**
	 * The number of structural features of the '<em>Update Descriptor</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int UPDATE_DESCRIPTOR_FEATURE_COUNT = IUPDATE_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.aggregator.p2.impl.PropertyImpl <em>Property</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.p2.impl.PropertyImpl
	 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getProperty()
	 * @generated
	 */
	int PROPERTY = 23;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROPERTY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROPERTY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Property</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROPERTY_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.aggregator.p2.impl.InstructionMapImpl
	 * <em>Instruction Map</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.p2.impl.InstructionMapImpl
	 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getInstructionMap()
	 * @generated
	 */
	int INSTRUCTION_MAP = 24;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INSTRUCTION_MAP__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INSTRUCTION_MAP__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Instruction Map</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INSTRUCTION_MAP_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.core.runtime.IAdaptable <em>IAdaptable</em>}' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.core.runtime.IAdaptable
	 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getIAdaptable()
	 * @generated
	 */
	int IADAPTABLE = 29;

	/**
	 * The number of structural features of the '<em>IAdaptable</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IADAPTABLE_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.equinox.internal.provisional.p2.repository.IRepository
	 * <em>IRepository</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.equinox.internal.provisional.p2.repository.IRepository
	 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getIRepository()
	 * @generated
	 */
	int IREPOSITORY = 27;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IREPOSITORY__LOCATION = IADAPTABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IREPOSITORY__NAME = IADAPTABLE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IREPOSITORY__TYPE = IADAPTABLE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IREPOSITORY__VERSION = IADAPTABLE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IREPOSITORY__DESCRIPTION = IADAPTABLE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Provider</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IREPOSITORY__PROVIDER = IADAPTABLE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Modifiable</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IREPOSITORY__MODIFIABLE = IADAPTABLE_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>IRepository</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IREPOSITORY_FEATURE_COUNT = IADAPTABLE_FEATURE_COUNT + 7;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.aggregator.p2.impl.RepositoryReferenceImpl
	 * <em>Repository Reference</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.p2.impl.RepositoryReferenceImpl
	 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getRepositoryReference()
	 * @generated
	 */
	int REPOSITORY_REFERENCE = 28;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REPOSITORY_REFERENCE__LOCATION = 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REPOSITORY_REFERENCE__TYPE = 1;

	/**
	 * The feature id for the '<em><b>Options</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REPOSITORY_REFERENCE__OPTIONS = 2;

	/**
	 * The feature id for the '<em><b>Nickname</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REPOSITORY_REFERENCE__NICKNAME = 3;

	/**
	 * The number of structural features of the '<em>Repository Reference</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REPOSITORY_REFERENCE_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.aggregator.p2.InstallableUnitType
	 * <em>Installable Unit Type</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.p2.InstallableUnitType
	 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getInstallableUnitType()
	 * @generated
	 */
	int INSTALLABLE_UNIT_TYPE = 30;

	/**
	 * The meta object id for the '<em>Version</em>' data type. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.equinox.internal.provisional.p2.core.Version
	 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getVersion()
	 * @generated
	 */
	int VERSION = 31;

	/**
	 * The meta object id for the '<em>Version Range</em>' data type. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.equinox.internal.provisional.p2.core.VersionRange
	 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getVersionRange()
	 * @generated
	 */
	int VERSION_RANGE = 32;

	/**
	 * The meta object id for the '<em>IInstallable Unit Array</em>' data type. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getIInstallableUnitArray()
	 * @generated
	 */
	int IINSTALLABLE_UNIT_ARRAY = 33;

	/**
	 * The meta object id for the '<em>IRequired Capability Array</em>' data type. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getIRequiredCapabilityArray()
	 * @generated
	 */
	int IREQUIRED_CAPABILITY_ARRAY = 34;

	/**
	 * The meta object id for the '<em>IProvided Capability Array</em>' data type. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getIProvidedCapabilityArray()
	 * @generated
	 */
	int IPROVIDED_CAPABILITY_ARRAY = 35;

	/**
	 * The meta object id for the '<em>IInstallable Unit Fragment Array</em>' data type. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getIInstallableUnitFragmentArray()
	 * @generated
	 */
	int IINSTALLABLE_UNIT_FRAGMENT_ARRAY = 36;

	/**
	 * The meta object id for the '<em>IArtifact Key Array</em>' data type. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getIArtifactKeyArray()
	 * @generated
	 */
	int IARTIFACT_KEY_ARRAY = 37;

	/**
	 * The meta object id for the '<em>ITouchpoint Data Array</em>' data type. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getITouchpointDataArray()
	 * @generated
	 */
	int ITOUCHPOINT_DATA_ARRAY = 38;

	/**
	 * The meta object id for the '<em>String Array</em>' data type. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getStringArray()
	 * @generated
	 */
	int STRING_ARRAY = 39;

	/**
	 * The meta object id for the '<em>Untyped Map</em>' data type. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see java.util.Map
	 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getUntypedMap()
	 * @generated
	 */
	int UNTYPED_MAP = 40;

	/**
	 * The meta object id for the '<em>Query</em>' data type. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.equinox.internal.provisional.p2.query.Query
	 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getQuery()
	 * @generated
	 */
	int QUERY = 41;

	/**
	 * The meta object id for the '<em>Collector</em>' data type. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.equinox.internal.provisional.p2.query.Collector
	 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getCollector()
	 * @generated
	 */
	int COLLECTOR = 42;

	/**
	 * The meta object id for the '<em>IProgress Monitor</em>' data type. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.core.runtime.IProgressMonitor
	 * @see org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl#getIProgressMonitor()
	 * @generated
	 */
	int IPROGRESS_MONITOR = 43;

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.p2.ArtifactKey
	 * <em>Artifact Key</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Artifact Key</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2.ArtifactKey
	 * @generated
	 */
	EClass getArtifactKey();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.equinox.internal.provisional.p2.query.Collector
	 * <em>Collector</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>Collector</em>'.
	 * @see org.eclipse.equinox.internal.provisional.p2.query.Collector
	 * @model instanceClass="org.eclipse.equinox.internal.provisional.p2.query.Collector" serializeable="false"
	 * @generated
	 */
	EDataType getCollector();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.p2.Copyright <em>Copyright</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Copyright</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2.Copyright
	 * @generated
	 */
	EClass getCopyright();

	/**
	 * Returns the meta object for class '{@link org.eclipse.core.runtime.IAdaptable <em>IAdaptable</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>IAdaptable</em>'.
	 * @see org.eclipse.core.runtime.IAdaptable
	 * @model instanceClass="org.eclipse.core.runtime.IAdaptable"
	 * @generated
	 */
	EClass getIAdaptable();

	/**
	 * Returns the meta object for class '{@link org.eclipse.equinox.internal.provisional.p2.metadata.IArtifactKey
	 * <em>IArtifact Key</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>IArtifact Key</em>'.
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.IArtifactKey
	 * @model instanceClass="org.eclipse.equinox.internal.provisional.p2.metadata.IArtifactKey"
	 * @generated
	 */
	EClass getIArtifactKey();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.equinox.internal.provisional.p2.metadata.IArtifactKey#getClassifier <em>Classifier</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Classifier</em>'.
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.IArtifactKey#getClassifier()
	 * @see #getIArtifactKey()
	 * @generated
	 */
	EAttribute getIArtifactKey_Classifier();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.equinox.internal.provisional.p2.metadata.IArtifactKey#getId <em>Id</em>}'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.IArtifactKey#getId()
	 * @see #getIArtifactKey()
	 * @generated
	 */
	EAttribute getIArtifactKey_Id();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.equinox.internal.provisional.p2.metadata.IArtifactKey#getVersion <em>Version</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.IArtifactKey#getVersion()
	 * @see #getIArtifactKey()
	 * @generated
	 */
	EAttribute getIArtifactKey_Version();

	/**
	 * Returns the meta object for data type '<em>IArtifact Key Array</em>'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for data type '<em>IArtifact Key Array</em>'.
	 * @model instanceClass="org.eclipse.equinox.internal.provisional.p2.metadata.IArtifactKey[]"
	 * @generated
	 */
	EDataType getIArtifactKeyArray();

	/**
	 * Returns the meta object for class '{@link org.eclipse.equinox.internal.provisional.p2.metadata.ICopyright
	 * <em>ICopyright</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>ICopyright</em>'.
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.ICopyright
	 * @model instanceClass="org.eclipse.equinox.internal.provisional.p2.metadata.ICopyright"
	 * @generated
	 */
	EClass getICopyright();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.equinox.internal.provisional.p2.metadata.ICopyright#getBody <em>Body</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Body</em>'.
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.ICopyright#getBody()
	 * @see #getICopyright()
	 * @generated
	 */
	EAttribute getICopyright_Body();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.equinox.internal.provisional.p2.metadata.ICopyright#getLocation <em>Location</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Location</em>'.
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.ICopyright#getLocation()
	 * @see #getICopyright()
	 * @generated
	 */
	EAttribute getICopyright_Location();

	/**
	 * Returns the meta object for class '{@link org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit
	 * <em>IInstallable Unit</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>IInstallable Unit</em>'.
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit
	 * @model instanceClass="org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit"
	 * @generated
	 */
	EClass getIInstallableUnit();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit#getCopyright <em>Copyright</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Copyright</em>'.
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit#getCopyright()
	 * @see #getIInstallableUnit()
	 * @generated
	 */
	EReference getIInstallableUnit_Copyright();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit#getFilter <em>Filter</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Filter</em>'.
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit#getFilter()
	 * @see #getIInstallableUnit()
	 * @generated
	 */
	EAttribute getIInstallableUnit_Filter();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit#getId <em>Id</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit#getId()
	 * @see #getIInstallableUnit()
	 * @generated
	 */
	EAttribute getIInstallableUnit_Id();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit#getLicense <em>License</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>License</em>'.
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit#getLicense()
	 * @see #getIInstallableUnit()
	 * @generated
	 */
	EReference getIInstallableUnit_License();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit#isResolved <em>Resolved</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Resolved</em>'.
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit#isResolved()
	 * @see #getIInstallableUnit()
	 * @generated
	 */
	EAttribute getIInstallableUnit_Resolved();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit#isSingleton <em>Singleton</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Singleton</em>'.
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit#isSingleton()
	 * @see #getIInstallableUnit()
	 * @generated
	 */
	EAttribute getIInstallableUnit_Singleton();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit#getTouchpointType
	 * <em>Touchpoint Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Touchpoint Type</em>'.
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit#getTouchpointType()
	 * @see #getIInstallableUnit()
	 * @generated
	 */
	EReference getIInstallableUnit_TouchpointType();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit#getUpdateDescriptor
	 * <em>Update Descriptor</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Update Descriptor</em>'.
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit#getUpdateDescriptor()
	 * @see #getIInstallableUnit()
	 * @generated
	 */
	EReference getIInstallableUnit_UpdateDescriptor();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit#getVersion <em>Version</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit#getVersion()
	 * @see #getIInstallableUnit()
	 * @generated
	 */
	EAttribute getIInstallableUnit_Version();

	/**
	 * Returns the meta object for data type '<em>IInstallable Unit Array</em>'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>IInstallable Unit Array</em>'.
	 * @model instanceClass="org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit[]"
	 * @generated
	 */
	EDataType getIInstallableUnitArray();

	/**
	 * Returns the meta object for class '
	 * {@link org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnitFragment
	 * <em>IInstallable Unit Fragment</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>IInstallable Unit Fragment</em>'.
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnitFragment
	 * @model instanceClass="org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnitFragment"
	 *        superTypes="org.eclipse.buckminster.aggregator.p2.IInstallableUnit"
	 * @generated
	 */
	EClass getIInstallableUnitFragment();

	/**
	 * Returns the meta object for data type '<em>IInstallable Unit Fragment Array</em>'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>IInstallable Unit Fragment Array</em>'.
	 * @model instanceClass="org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnitFragment[]"
	 * @generated
	 */
	EDataType getIInstallableUnitFragmentArray();

	/**
	 * Returns the meta object for class '{@link org.eclipse.equinox.internal.provisional.p2.metadata.ILicense
	 * <em>ILicense</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>ILicense</em>'.
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.ILicense
	 * @model instanceClass="org.eclipse.equinox.internal.provisional.p2.metadata.ILicense"
	 * @generated
	 */
	EClass getILicense();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.equinox.internal.provisional.p2.metadata.ILicense#getBody <em>Body</em>}'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Body</em>'.
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.ILicense#getBody()
	 * @see #getILicense()
	 * @generated
	 */
	EAttribute getILicense_Body();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.equinox.internal.provisional.p2.metadata.ILicense#getDigest <em>Digest</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Digest</em>'.
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.ILicense#getDigest()
	 * @see #getILicense()
	 * @generated
	 */
	EAttribute getILicense_Digest();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.equinox.internal.provisional.p2.metadata.ILicense#getLocation <em>Location</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Location</em>'.
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.ILicense#getLocation()
	 * @see #getILicense()
	 * @generated
	 */
	EAttribute getILicense_Location();

	/**
	 * Returns the meta object for class '
	 * {@link org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepository
	 * <em>IMetadata Repository</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>IMetadata Repository</em>'.
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepository
	 * @model instanceClass="org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepository"
	 *        superTypes
	 *        ="org.eclipse.buckminster.aggregator.p2.IQueryable org.eclipse.buckminster.aggregator.p2.IRepository"
	 * @generated
	 */
	EClass getIMetadataRepository();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.p2.InstallableUnit
	 * <em>Installable Unit</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Installable Unit</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2.InstallableUnit
	 * @generated
	 */
	EClass getInstallableUnit();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.aggregator.p2.InstallableUnit#getArtifactList <em>Artifact List</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Artifact List</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2.InstallableUnit#getArtifactList()
	 * @see #getInstallableUnit()
	 * @generated
	 */
	EReference getInstallableUnit_ArtifactList();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.aggregator.p2.InstallableUnit#getMetaRequiredCapabilityList
	 * <em>Meta Required Capability List</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Meta Required Capability List</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2.InstallableUnit#getMetaRequiredCapabilityList()
	 * @see #getInstallableUnit()
	 * @generated
	 */
	EReference getInstallableUnit_MetaRequiredCapabilityList();

	/**
	 * Returns the meta object for the map '{@link org.eclipse.buckminster.aggregator.p2.InstallableUnit#getPropertyMap
	 * <em>Property Map</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the map '<em>Property Map</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2.InstallableUnit#getPropertyMap()
	 * @see #getInstallableUnit()
	 * @generated
	 */
	EReference getInstallableUnit_PropertyMap();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.aggregator.p2.InstallableUnit#getProvidedCapabilityList
	 * <em>Provided Capability List</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Provided Capability List</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2.InstallableUnit#getProvidedCapabilityList()
	 * @see #getInstallableUnit()
	 * @generated
	 */
	EReference getInstallableUnit_ProvidedCapabilityList();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.aggregator.p2.InstallableUnit#getRequiredCapabilityList
	 * <em>Required Capability List</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Required Capability List</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2.InstallableUnit#getRequiredCapabilityList()
	 * @see #getInstallableUnit()
	 * @generated
	 */
	EReference getInstallableUnit_RequiredCapabilityList();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.aggregator.p2.InstallableUnit#getTouchpointDataList <em>Touchpoint Data List</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Touchpoint Data List</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2.InstallableUnit#getTouchpointDataList()
	 * @see #getInstallableUnit()
	 * @generated
	 */
	EReference getInstallableUnit_TouchpointDataList();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.aggregator.p2.InstallableUnit#getType
	 * <em>Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2.InstallableUnit#getType()
	 * @see #getInstallableUnit()
	 * @generated
	 */
	EAttribute getInstallableUnit_Type();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.p2.InstallableUnitFragment
	 * <em>Installable Unit Fragment</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Installable Unit Fragment</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2.InstallableUnitFragment
	 * @generated
	 */
	EClass getInstallableUnitFragment();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.aggregator.p2.InstallableUnitFragment#getHostList <em>Host List</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Host List</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2.InstallableUnitFragment#getHostList()
	 * @see #getInstallableUnitFragment()
	 * @generated
	 */
	EReference getInstallableUnitFragment_HostList();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.buckminster.aggregator.p2.InstallableUnitType
	 * <em>Installable Unit Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for enum '<em>Installable Unit Type</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2.InstallableUnitType
	 * @generated
	 */
	EEnum getInstallableUnitType();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>Instruction Map</em>}'. <!-- begin-user-doc -->
	 * ======= Returns the meta object for class '{@link java.util.Map.Entry <em>Instruction Map</em>}'. <!--
	 * begin-user-doc --> >>>>>>> .r10620 <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Instruction Map</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDataType="org.eclipse.emf.ecore.EString" keyRequired="true"
	 *        valueType="org.eclipse.buckminster.aggregator.p2.TouchpointInstruction" valueContainment="true"
	 * @generated
	 */
	EClass getInstructionMap();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getInstructionMap()
	 * @generated
	 */
	EAttribute getInstructionMap_Key();

	/**
	 * Returns the meta object for the containment reference '{@link java.util.Map.Entry <em>Value</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getInstructionMap()
	 * @generated
	 */
	EReference getInstructionMap_Value();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.core.runtime.IProgressMonitor
	 * <em>IProgress Monitor</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>IProgress Monitor</em>'.
	 * @see org.eclipse.core.runtime.IProgressMonitor
	 * @model instanceClass="org.eclipse.core.runtime.IProgressMonitor" serializeable="false"
	 * @generated
	 */
	EDataType getIProgressMonitor();

	/**
	 * Returns the meta object for class '
	 * {@link org.eclipse.equinox.internal.provisional.p2.metadata.IProvidedCapability <em>IProvided Capability</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>IProvided Capability</em>'.
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.IProvidedCapability
	 * @model instanceClass="org.eclipse.equinox.internal.provisional.p2.metadata.IProvidedCapability"
	 * @generated
	 */
	EClass getIProvidedCapability();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.equinox.internal.provisional.p2.metadata.IProvidedCapability#getName <em>Name</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.IProvidedCapability#getName()
	 * @see #getIProvidedCapability()
	 * @generated
	 */
	EAttribute getIProvidedCapability_Name();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.equinox.internal.provisional.p2.metadata.IProvidedCapability#getNamespace <em>Namespace</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Namespace</em>'.
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.IProvidedCapability#getNamespace()
	 * @see #getIProvidedCapability()
	 * @generated
	 */
	EAttribute getIProvidedCapability_Namespace();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.equinox.internal.provisional.p2.metadata.IProvidedCapability#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.IProvidedCapability#getVersion()
	 * @see #getIProvidedCapability()
	 * @generated
	 */
	EAttribute getIProvidedCapability_Version();

	/**
	 * Returns the meta object for data type '<em>IProvided Capability Array</em>'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>IProvided Capability Array</em>'.
	 * @model instanceClass="org.eclipse.equinox.internal.provisional.p2.metadata.IProvidedCapability[]"
	 * @generated
	 */
	EDataType getIProvidedCapabilityArray();

	/**
	 * Returns the meta object for class '{@link org.eclipse.equinox.internal.provisional.p2.query.IQueryable
	 * <em>IQueryable</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>IQueryable</em>'.
	 * @see org.eclipse.equinox.internal.provisional.p2.query.IQueryable
	 * @model instanceClass="org.eclipse.equinox.internal.provisional.p2.query.IQueryable"
	 * @generated
	 */
	EClass getIQueryable();

	/**
	 * Returns the meta object for class '{@link org.eclipse.equinox.internal.provisional.p2.repository.IRepository
	 * <em>IRepository</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>IRepository</em>'.
	 * @see org.eclipse.equinox.internal.provisional.p2.repository.IRepository
	 * @model instanceClass="org.eclipse.equinox.internal.provisional.p2.repository.IRepository"
	 *        superTypes="org.eclipse.buckminster.aggregator.p2.IAdaptable"
	 * @generated
	 */
	EClass getIRepository();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.equinox.internal.provisional.p2.repository.IRepository#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.eclipse.equinox.internal.provisional.p2.repository.IRepository#getDescription()
	 * @see #getIRepository()
	 * @generated
	 */
	EAttribute getIRepository_Description();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.equinox.internal.provisional.p2.repository.IRepository#getLocation <em>Location</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Location</em>'.
	 * @see org.eclipse.equinox.internal.provisional.p2.repository.IRepository#getLocation()
	 * @see #getIRepository()
	 * @generated
	 */
	EAttribute getIRepository_Location();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.equinox.internal.provisional.p2.repository.IRepository#isModifiable <em>Modifiable</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Modifiable</em>'.
	 * @see org.eclipse.equinox.internal.provisional.p2.repository.IRepository#isModifiable()
	 * @see #getIRepository()
	 * @generated
	 */
	EAttribute getIRepository_Modifiable();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.equinox.internal.provisional.p2.repository.IRepository#getName <em>Name</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.equinox.internal.provisional.p2.repository.IRepository#getName()
	 * @see #getIRepository()
	 * @generated
	 */
	EAttribute getIRepository_Name();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.equinox.internal.provisional.p2.repository.IRepository#getProvider <em>Provider</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Provider</em>'.
	 * @see org.eclipse.equinox.internal.provisional.p2.repository.IRepository#getProvider()
	 * @see #getIRepository()
	 * @generated
	 */
	EAttribute getIRepository_Provider();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.equinox.internal.provisional.p2.repository.IRepository#getType <em>Type</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.eclipse.equinox.internal.provisional.p2.repository.IRepository#getType()
	 * @see #getIRepository()
	 * @generated
	 */
	EAttribute getIRepository_Type();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.equinox.internal.provisional.p2.repository.IRepository#getVersion <em>Version</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see org.eclipse.equinox.internal.provisional.p2.repository.IRepository#getVersion()
	 * @see #getIRepository()
	 * @generated
	 */
	EAttribute getIRepository_Version();

	/**
	 * Returns the meta object for class '
	 * {@link org.eclipse.equinox.internal.provisional.p2.metadata.IRequiredCapability <em>IRequired Capability</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>IRequired Capability</em>'.
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.IRequiredCapability
	 * @model instanceClass="org.eclipse.equinox.internal.provisional.p2.metadata.IRequiredCapability"
	 * @generated
	 */
	EClass getIRequiredCapability();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.equinox.internal.provisional.p2.metadata.IRequiredCapability#getFilter <em>Filter</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Filter</em>'.
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.IRequiredCapability#getFilter()
	 * @see #getIRequiredCapability()
	 * @generated
	 */
	EAttribute getIRequiredCapability_Filter();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.equinox.internal.provisional.p2.metadata.IRequiredCapability#isGreedy <em>Greedy</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Greedy</em>'.
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.IRequiredCapability#isGreedy()
	 * @see #getIRequiredCapability()
	 * @generated
	 */
	EAttribute getIRequiredCapability_Greedy();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.equinox.internal.provisional.p2.metadata.IRequiredCapability#isMultiple <em>Multiple</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Multiple</em>'.
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.IRequiredCapability#isMultiple()
	 * @see #getIRequiredCapability()
	 * @generated
	 */
	EAttribute getIRequiredCapability_Multiple();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.equinox.internal.provisional.p2.metadata.IRequiredCapability#getName <em>Name</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.IRequiredCapability#getName()
	 * @see #getIRequiredCapability()
	 * @generated
	 */
	EAttribute getIRequiredCapability_Name();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.equinox.internal.provisional.p2.metadata.IRequiredCapability#getNamespace <em>Namespace</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Namespace</em>'.
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.IRequiredCapability#getNamespace()
	 * @see #getIRequiredCapability()
	 * @generated
	 */
	EAttribute getIRequiredCapability_Namespace();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.equinox.internal.provisional.p2.metadata.IRequiredCapability#isOptional <em>Optional</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Optional</em>'.
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.IRequiredCapability#isOptional()
	 * @see #getIRequiredCapability()
	 * @generated
	 */
	EAttribute getIRequiredCapability_Optional();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.equinox.internal.provisional.p2.metadata.IRequiredCapability#getRange <em>Range</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Range</em>'.
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.IRequiredCapability#getRange()
	 * @see #getIRequiredCapability()
	 * @generated
	 */
	EAttribute getIRequiredCapability_Range();

	/**
	 * Returns the meta object for the attribute list '
	 * {@link org.eclipse.equinox.internal.provisional.p2.metadata.IRequiredCapability#getSelectorList
	 * <em>Selector List</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute list '<em>Selector List</em>'.
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.IRequiredCapability#getSelectorList()
	 * @see #getIRequiredCapability()
	 * @generated
	 */
	EAttribute getIRequiredCapability_SelectorList();

	/**
	 * Returns the meta object for data type '<em>IRequired Capability Array</em>'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>IRequired Capability Array</em>'.
	 * @model instanceClass="org.eclipse.equinox.internal.provisional.p2.metadata.IRequiredCapability[]"
	 * @generated
	 */
	EDataType getIRequiredCapabilityArray();

	/**
	 * Returns the meta object for class '{@link org.eclipse.equinox.internal.provisional.p2.metadata.ITouchpointData
	 * <em>ITouchpoint Data</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>ITouchpoint Data</em>'.
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.ITouchpointData
	 * @model instanceClass="org.eclipse.equinox.internal.provisional.p2.metadata.ITouchpointData"
	 * @generated
	 */
	EClass getITouchpointData();

	/**
	 * Returns the meta object for data type '<em>ITouchpoint Data Array</em>'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>ITouchpoint Data Array</em>'.
	 * @model instanceClass="org.eclipse.equinox.internal.provisional.p2.metadata.ITouchpointData[]"
	 * @generated
	 */
	EDataType getITouchpointDataArray();

	/**
	 * Returns the meta object for class '
	 * {@link org.eclipse.equinox.internal.provisional.p2.metadata.ITouchpointInstruction
	 * <em>ITouchpoint Instruction</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>ITouchpoint Instruction</em>'.
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.ITouchpointInstruction
	 * @model instanceClass="org.eclipse.equinox.internal.provisional.p2.metadata.ITouchpointInstruction"
	 * @generated
	 */
	EClass getITouchpointInstruction();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.equinox.internal.provisional.p2.metadata.ITouchpointInstruction#getBody <em>Body</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Body</em>'.
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.ITouchpointInstruction#getBody()
	 * @see #getITouchpointInstruction()
	 * @generated
	 */
	EAttribute getITouchpointInstruction_Body();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.equinox.internal.provisional.p2.metadata.ITouchpointInstruction#getImportAttribute
	 * <em>Import Attribute</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Import Attribute</em>'.
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.ITouchpointInstruction#getImportAttribute()
	 * @see #getITouchpointInstruction()
	 * @generated
	 */
	EAttribute getITouchpointInstruction_ImportAttribute();

	/**
	 * Returns the meta object for class '{@link org.eclipse.equinox.internal.provisional.p2.metadata.ITouchpointType
	 * <em>ITouchpoint Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>ITouchpoint Type</em>'.
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.ITouchpointType
	 * @model instanceClass="org.eclipse.equinox.internal.provisional.p2.metadata.ITouchpointType"
	 * @generated
	 */
	EClass getITouchpointType();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.equinox.internal.provisional.p2.metadata.ITouchpointType#getId <em>Id</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.ITouchpointType#getId()
	 * @see #getITouchpointType()
	 * @generated
	 */
	EAttribute getITouchpointType_Id();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.equinox.internal.provisional.p2.metadata.ITouchpointType#getVersion <em>Version</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.ITouchpointType#getVersion()
	 * @see #getITouchpointType()
	 * @generated
	 */
	EAttribute getITouchpointType_Version();

	/**
	 * Returns the meta object for class '{@link org.eclipse.equinox.internal.provisional.p2.metadata.IUpdateDescriptor
	 * <em>IUpdate Descriptor</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>IUpdate Descriptor</em>'.
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.IUpdateDescriptor
	 * @model instanceClass="org.eclipse.equinox.internal.provisional.p2.metadata.IUpdateDescriptor"
	 * @generated
	 */
	EClass getIUpdateDescriptor();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.equinox.internal.provisional.p2.metadata.IUpdateDescriptor#getDescription
	 * <em>Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.IUpdateDescriptor#getDescription()
	 * @see #getIUpdateDescriptor()
	 * @generated
	 */
	EAttribute getIUpdateDescriptor_Description();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.equinox.internal.provisional.p2.metadata.IUpdateDescriptor#getId <em>Id</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.IUpdateDescriptor#getId()
	 * @see #getIUpdateDescriptor()
	 * @generated
	 */
	EAttribute getIUpdateDescriptor_Id();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.equinox.internal.provisional.p2.metadata.IUpdateDescriptor#getRange <em>Range</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Range</em>'.
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.IUpdateDescriptor#getRange()
	 * @see #getIUpdateDescriptor()
	 * @generated
	 */
	EAttribute getIUpdateDescriptor_Range();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.equinox.internal.provisional.p2.metadata.IUpdateDescriptor#getSeverity <em>Severity</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Severity</em>'.
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.IUpdateDescriptor#getSeverity()
	 * @see #getIUpdateDescriptor()
	 * @generated
	 */
	EAttribute getIUpdateDescriptor_Severity();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.p2.License <em>License</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>License</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2.License
	 * @generated
	 */
	EClass getLicense();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.p2.MetadataRepository
	 * <em>Metadata Repository</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Metadata Repository</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2.MetadataRepository
	 * @generated
	 */
	EClass getMetadataRepository();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.aggregator.p2.MetadataRepository#getInstallableUnits <em>Installable Units</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Installable Units</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2.MetadataRepository#getInstallableUnits()
	 * @see #getMetadataRepository()
	 * @generated
	 */
	EReference getMetadataRepository_InstallableUnits();

	/**
	 * Returns the meta object for the map '
	 * {@link org.eclipse.buckminster.aggregator.p2.MetadataRepository#getPropertyMap <em>Property Map</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the map '<em>Property Map</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2.MetadataRepository#getPropertyMap()
	 * @see #getMetadataRepository()
	 * @generated
	 */
	EReference getMetadataRepository_PropertyMap();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.aggregator.p2.MetadataRepository#getRepositoryReferences
	 * <em>Repository References</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Repository References</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2.MetadataRepository#getRepositoryReferences()
	 * @see #getMetadataRepository()
	 * @generated
	 */
	EReference getMetadataRepository_RepositoryReferences();

	/**
	 * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	P2Factory getP2Factory();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>Property</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Property</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDataType="org.eclipse.emf.ecore.EString" keyRequired="true"
	 *        valueDataType="org.eclipse.emf.ecore.EString"
	 * @generated
	 */
	EClass getProperty();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getProperty()
	 * @generated
	 */
	EAttribute getProperty_Key();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Value</em>}'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getProperty()
	 * @generated
	 */
	EAttribute getProperty_Value();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.p2.ProvidedCapability
	 * <em>Provided Capability</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Provided Capability</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2.ProvidedCapability
	 * @generated
	 */
	EClass getProvidedCapability();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.equinox.internal.provisional.p2.query.Query
	 * <em>Query</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>Query</em>'.
	 * @see org.eclipse.equinox.internal.provisional.p2.query.Query
	 * @model instanceClass="org.eclipse.equinox.internal.provisional.p2.query.Query" serializeable="false"
	 * @generated
	 */
	EDataType getQuery();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.p2.RepositoryReference
	 * <em>Repository Reference</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Repository Reference</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2.RepositoryReference
	 * @generated
	 */
	EClass getRepositoryReference();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.aggregator.p2.RepositoryReference#getLocation <em>Location</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Location</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2.RepositoryReference#getLocation()
	 * @see #getRepositoryReference()
	 * @generated
	 */
	EAttribute getRepositoryReference_Location();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.aggregator.p2.RepositoryReference#getNickname <em>Nickname</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Nickname</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2.RepositoryReference#getNickname()
	 * @see #getRepositoryReference()
	 * @generated
	 */
	EAttribute getRepositoryReference_Nickname();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.aggregator.p2.RepositoryReference#getOptions <em>Options</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Options</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2.RepositoryReference#getOptions()
	 * @see #getRepositoryReference()
	 * @generated
	 */
	EAttribute getRepositoryReference_Options();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.aggregator.p2.RepositoryReference#getType <em>Type</em>}'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2.RepositoryReference#getType()
	 * @see #getRepositoryReference()
	 * @generated
	 */
	EAttribute getRepositoryReference_Type();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.p2.RequiredCapability
	 * <em>Required Capability</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Required Capability</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2.RequiredCapability
	 * @generated
	 */
	EClass getRequiredCapability();

	/**
	 * Returns the meta object for data type '<em>String Array</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>String Array</em>'.
	 * @model instanceClass="java.lang.String[]"
	 * @generated
	 */
	EDataType getStringArray();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.p2.TouchpointData
	 * <em>Touchpoint Data</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Touchpoint Data</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2.TouchpointData
	 * @generated
	 */
	EClass getTouchpointData();

	/**
	 * Returns the meta object for the map '
	 * {@link org.eclipse.buckminster.aggregator.p2.TouchpointData#getInstructionMap <em>Instruction Map</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the map '<em>Instruction Map</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2.TouchpointData#getInstructionMap()
	 * @see #getTouchpointData()
	 * @generated
	 */
	EReference getTouchpointData_InstructionMap();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.p2.TouchpointInstruction
	 * <em>Touchpoint Instruction</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Touchpoint Instruction</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2.TouchpointInstruction
	 * @generated
	 */
	EClass getTouchpointInstruction();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.p2.TouchpointType
	 * <em>Touchpoint Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Touchpoint Type</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2.TouchpointType
	 * @generated
	 */
	EClass getTouchpointType();

	/**
	 * Returns the meta object for data type '{@link java.util.Map <em>Untyped Map</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>Untyped Map</em>'.
	 * @see java.util.Map
	 * @model instanceClass="java.util.Map"
	 * @generated
	 */
	EDataType getUntypedMap();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.p2.UpdateDescriptor
	 * <em>Update Descriptor</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Update Descriptor</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2.UpdateDescriptor
	 * @generated
	 */
	EClass getUpdateDescriptor();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.equinox.internal.provisional.p2.core.Version
	 * <em>Version</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>Version</em>'.
	 * @see org.eclipse.equinox.internal.provisional.p2.core.Version
	 * @model instanceClass="org.eclipse.equinox.internal.provisional.p2.core.Version"
	 * @generated
	 */
	EDataType getVersion();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.equinox.internal.provisional.p2.core.VersionRange
	 * <em>Version Range</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>Version Range</em>'.
	 * @see org.eclipse.equinox.internal.provisional.p2.core.VersionRange
	 * @model instanceClass="org.eclipse.equinox.internal.provisional.p2.core.VersionRange"
	 * @generated
	 */
	EDataType getVersionRange();

} // P2Package
