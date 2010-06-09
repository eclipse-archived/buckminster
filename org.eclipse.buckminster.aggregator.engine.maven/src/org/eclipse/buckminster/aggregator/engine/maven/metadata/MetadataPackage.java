/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.engine.maven.metadata;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
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
 * @see org.eclipse.buckminster.aggregator.engine.maven.metadata.MetadataFactory
 * @model kind="package"
 * @generated
 */
public interface MetadataPackage extends EPackage
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
		 * The meta object literal for the '
		 * {@link org.eclipse.buckminster.aggregator.engine.maven.metadata.impl.DocumentRootImpl <em>Document Root</em>}
		 * ' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.engine.maven.metadata.impl.DocumentRootImpl
		 * @see org.eclipse.buckminster.aggregator.engine.maven.metadata.impl.MetadataPackageImpl#getDocumentRoot()
		 * @generated
		 */
		EClass DOCUMENT_ROOT = eINSTANCE.getDocumentRoot();

		/**
		 * The meta object literal for the '<em><b>Mixed</b></em>' attribute list feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__MIXED = eINSTANCE.getDocumentRoot_Mixed();

		/**
		 * The meta object literal for the '<em><b>XMLNS Prefix Map</b></em>' map feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__XMLNS_PREFIX_MAP = eINSTANCE.getDocumentRoot_XMLNSPrefixMap();

		/**
		 * The meta object literal for the '<em><b>XSI Schema Location</b></em>' map feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = eINSTANCE.getDocumentRoot_XSISchemaLocation();

		/**
		 * The meta object literal for the '<em><b>Metadata</b></em>' containment reference feature. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__METADATA = eINSTANCE.getDocumentRoot_Metadata();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.buckminster.aggregator.engine.maven.metadata.impl.MetaDataImpl <em>Meta Data</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.engine.maven.metadata.impl.MetaDataImpl
		 * @see org.eclipse.buckminster.aggregator.engine.maven.metadata.impl.MetadataPackageImpl#getMetaData()
		 * @generated
		 */
		EClass META_DATA = eINSTANCE.getMetaData();

		/**
		 * The meta object literal for the '<em><b>Group Id</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute META_DATA__GROUP_ID = eINSTANCE.getMetaData_GroupId();

		/**
		 * The meta object literal for the '<em><b>Artifact Id</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute META_DATA__ARTIFACT_ID = eINSTANCE.getMetaData_ArtifactId();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute META_DATA__VERSION = eINSTANCE.getMetaData_Version();

		/**
		 * The meta object literal for the '<em><b>Versioning</b></em>' containment reference feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference META_DATA__VERSIONING = eINSTANCE.getMetaData_Versioning();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.buckminster.aggregator.engine.maven.metadata.impl.VersioningImpl <em>Versioning</em>}'
		 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.engine.maven.metadata.impl.VersioningImpl
		 * @see org.eclipse.buckminster.aggregator.engine.maven.metadata.impl.MetadataPackageImpl#getVersioning()
		 * @generated
		 */
		EClass VERSIONING = eINSTANCE.getVersioning();

		/**
		 * The meta object literal for the '<em><b>Release</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute VERSIONING__RELEASE = eINSTANCE.getVersioning_Release();

		/**
		 * The meta object literal for the '<em><b>Versions</b></em>' containment reference feature. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference VERSIONING__VERSIONS = eINSTANCE.getVersioning_Versions();

		/**
		 * The meta object literal for the '<em><b>Last Updated</b></em>' attribute feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute VERSIONING__LAST_UPDATED = eINSTANCE.getVersioning_LastUpdated();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.buckminster.aggregator.engine.maven.metadata.impl.VersionsImpl <em>Versions</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.engine.maven.metadata.impl.VersionsImpl
		 * @see org.eclipse.buckminster.aggregator.engine.maven.metadata.impl.MetadataPackageImpl#getVersions()
		 * @generated
		 */
		EClass VERSIONS = eINSTANCE.getVersions();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute list feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute VERSIONS__VERSION = eINSTANCE.getVersions_Version();

	}

	/**
	 * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNAME = "metadata";

	/**
	 * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_URI = "http://maven.apache.org/METADATA/1.0.0";

	/**
	 * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_PREFIX = "";

	/**
	 * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	MetadataPackage eINSTANCE = org.eclipse.buckminster.aggregator.engine.maven.metadata.impl.MetadataPackageImpl.init();

	/**
	 * The meta object id for the '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.metadata.impl.DocumentRootImpl <em>Document Root</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.engine.maven.metadata.impl.DocumentRootImpl
	 * @see org.eclipse.buckminster.aggregator.engine.maven.metadata.impl.MetadataPackageImpl#getDocumentRoot()
	 * @generated
	 */
	int DOCUMENT_ROOT = 0;

	/**
	 * The feature id for the '<em><b>Mixed</b></em>' attribute list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__MIXED = 0;

	/**
	 * The feature id for the '<em><b>XMLNS Prefix Map</b></em>' map. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__XMLNS_PREFIX_MAP = 1;

	/**
	 * The feature id for the '<em><b>XSI Schema Location</b></em>' map. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = 2;

	/**
	 * The feature id for the '<em><b>Metadata</b></em>' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__METADATA = 3;

	/**
	 * The number of structural features of the '<em>Document Root</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.aggregator.engine.maven.metadata.impl.MetaDataImpl
	 * <em>Meta Data</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.engine.maven.metadata.impl.MetaDataImpl
	 * @see org.eclipse.buckminster.aggregator.engine.maven.metadata.impl.MetadataPackageImpl#getMetaData()
	 * @generated
	 */
	int META_DATA = 1;

	/**
	 * The feature id for the '<em><b>Group Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int META_DATA__GROUP_ID = 0;

	/**
	 * The feature id for the '<em><b>Artifact Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int META_DATA__ARTIFACT_ID = 1;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int META_DATA__VERSION = 2;

	/**
	 * The feature id for the '<em><b>Versioning</b></em>' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int META_DATA__VERSIONING = 3;

	/**
	 * The number of structural features of the '<em>Meta Data</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int META_DATA_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.aggregator.engine.maven.metadata.impl.VersioningImpl
	 * <em>Versioning</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.engine.maven.metadata.impl.VersioningImpl
	 * @see org.eclipse.buckminster.aggregator.engine.maven.metadata.impl.MetadataPackageImpl#getVersioning()
	 * @generated
	 */
	int VERSIONING = 2;

	/**
	 * The feature id for the '<em><b>Release</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int VERSIONING__RELEASE = 0;

	/**
	 * The feature id for the '<em><b>Versions</b></em>' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int VERSIONING__VERSIONS = 1;

	/**
	 * The feature id for the '<em><b>Last Updated</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int VERSIONING__LAST_UPDATED = 2;

	/**
	 * The number of structural features of the '<em>Versioning</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int VERSIONING_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.aggregator.engine.maven.metadata.impl.VersionsImpl
	 * <em>Versions</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.engine.maven.metadata.impl.VersionsImpl
	 * @see org.eclipse.buckminster.aggregator.engine.maven.metadata.impl.MetadataPackageImpl#getVersions()
	 * @generated
	 */
	int VERSIONS = 3;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int VERSIONS__VERSION = 0;

	/**
	 * The number of structural features of the '<em>Versions</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int VERSIONS_FEATURE_COUNT = 1;

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.engine.maven.metadata.DocumentRoot
	 * <em>Document Root</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Document Root</em>'.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.metadata.DocumentRoot
	 * @generated
	 */
	EClass getDocumentRoot();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.metadata.DocumentRoot#getMetadata <em>Metadata</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Metadata</em>'.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.metadata.DocumentRoot#getMetadata()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Metadata();

	/**
	 * Returns the meta object for the attribute list '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.metadata.DocumentRoot#getMixed <em>Mixed</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute list '<em>Mixed</em>'.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.metadata.DocumentRoot#getMixed()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Mixed();

	/**
	 * Returns the meta object for the map '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.metadata.DocumentRoot#getXMLNSPrefixMap
	 * <em>XMLNS Prefix Map</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the map '<em>XMLNS Prefix Map</em>'.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.metadata.DocumentRoot#getXMLNSPrefixMap()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XMLNSPrefixMap();

	/**
	 * Returns the meta object for the map '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.metadata.DocumentRoot#getXSISchemaLocation
	 * <em>XSI Schema Location</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the map '<em>XSI Schema Location</em>'.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.metadata.DocumentRoot#getXSISchemaLocation()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XSISchemaLocation();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.engine.maven.metadata.MetaData
	 * <em>Meta Data</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Meta Data</em>'.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.metadata.MetaData
	 * @generated
	 */
	EClass getMetaData();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.metadata.MetaData#getArtifactId <em>Artifact Id</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Artifact Id</em>'.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.metadata.MetaData#getArtifactId()
	 * @see #getMetaData()
	 * @generated
	 */
	EAttribute getMetaData_ArtifactId();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.metadata.MetaData#getGroupId <em>Group Id</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Group Id</em>'.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.metadata.MetaData#getGroupId()
	 * @see #getMetaData()
	 * @generated
	 */
	EAttribute getMetaData_GroupId();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.metadata.MetaData#getVersion <em>Version</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.metadata.MetaData#getVersion()
	 * @see #getMetaData()
	 * @generated
	 */
	EAttribute getMetaData_Version();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.metadata.MetaData#getVersioning <em>Versioning</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Versioning</em>'.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.metadata.MetaData#getVersioning()
	 * @see #getMetaData()
	 * @generated
	 */
	EReference getMetaData_Versioning();

	/**
	 * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	MetadataFactory getMetadataFactory();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.engine.maven.metadata.Versioning
	 * <em>Versioning</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Versioning</em>'.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.metadata.Versioning
	 * @generated
	 */
	EClass getVersioning();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.metadata.Versioning#getLastUpdated <em>Last Updated</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Last Updated</em>'.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.metadata.Versioning#getLastUpdated()
	 * @see #getVersioning()
	 * @generated
	 */
	EAttribute getVersioning_LastUpdated();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.metadata.Versioning#getRelease <em>Release</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Release</em>'.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.metadata.Versioning#getRelease()
	 * @see #getVersioning()
	 * @generated
	 */
	EAttribute getVersioning_Release();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.metadata.Versioning#getVersions <em>Versions</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Versions</em>'.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.metadata.Versioning#getVersions()
	 * @see #getVersioning()
	 * @generated
	 */
	EReference getVersioning_Versions();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.engine.maven.metadata.Versions
	 * <em>Versions</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Versions</em>'.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.metadata.Versions
	 * @generated
	 */
	EClass getVersions();

	/**
	 * Returns the meta object for the attribute list '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.metadata.Versions#getVersion <em>Version</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute list '<em>Version</em>'.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.metadata.Versions#getVersion()
	 * @see #getVersions()
	 * @generated
	 */
	EAttribute getVersions_Version();

} // MetadataPackage
