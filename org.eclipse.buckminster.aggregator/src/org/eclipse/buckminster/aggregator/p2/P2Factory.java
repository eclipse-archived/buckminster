/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.p2;

import org.eclipse.emf.ecore.EFactory;
import org.eclipse.equinox.internal.provisional.p2.core.VersionedName;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each non-abstract class of
 * the model. <!-- end-user-doc -->
 * 
 * @see org.eclipse.buckminster.aggregator.p2.P2Package
 * @generated
 */
public interface P2Factory extends EFactory
{
	/**
	 * The singleton instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	P2Factory eINSTANCE = org.eclipse.buckminster.aggregator.p2.impl.P2FactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Artifact Key</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Artifact Key</em>'.
	 * @generated
	 */
	ArtifactKey createArtifactKey();

	/**
	 * Returns a new object of class '<em>Copyright</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Copyright</em>'.
	 * @generated
	 */
	Copyright createCopyright();

	/**
	 * Returns a new object of class '<em>Installable Unit</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Installable Unit</em>'.
	 * @generated
	 */
	InstallableUnit createInstallableUnit();

	/**
	 * Returns a new object of class '<em>Installable Unit Fragment</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Installable Unit Fragment</em>'.
	 * @generated
	 */
	InstallableUnitFragment createInstallableUnitFragment();

	/**
	 * Returns a new proxy of class '<em>Installable Unit</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new proxy of class '<em>Installable Unit</em>'.
	 * @generated NOT
	 */
	InstallableUnit createInstallableUnitProxy(String repoLocation, VersionedName iuVN);

	/**
	 * Returns a new object of class '<em>License</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>License</em>'.
	 * @generated
	 */
	License createLicense();

	/**
	 * Returns a new object of class '<em>Metadata Repository</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Metadata Repository</em>'.
	 * @generated
	 */
	MetadataRepository createMetadataRepository();

	/**
	 * Returns a new object of class '<em>Provided Capability</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Provided Capability</em>'.
	 * @generated
	 */
	ProvidedCapability createProvidedCapability();

	/**
	 * Returns a new object of class '<em>Repository Reference</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Repository Reference</em>'.
	 * @generated
	 */
	RepositoryReference createRepositoryReference();

	/**
	 * Returns a new object of class '<em>Required Capability</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Required Capability</em>'.
	 * @generated
	 */
	RequiredCapability createRequiredCapability();

	/**
	 * Returns a new object of class '<em>Touchpoint Data</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Touchpoint Data</em>'.
	 * @generated
	 */
	TouchpointData createTouchpointData();

	/**
	 * Returns a new object of class '<em>Touchpoint Instruction</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Touchpoint Instruction</em>'.
	 * @generated
	 */
	TouchpointInstruction createTouchpointInstruction();

	/**
	 * Returns a new object of class '<em>Touchpoint Type</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Touchpoint Type</em>'.
	 * @generated
	 */
	TouchpointType createTouchpointType();

	/**
	 * Returns a new object of class '<em>Update Descriptor</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Update Descriptor</em>'.
	 * @generated
	 */
	UpdateDescriptor createUpdateDescriptor();

	/**
	 * Returns the package supported by this factory. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the package supported by this factory.
	 * @generated
	 */
	P2Package getP2Package();

} // P2Factory
