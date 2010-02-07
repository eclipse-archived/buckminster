/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap.psf;

import org.eclipse.buckminster.rmap.RmapPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains
 * accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * 
 * @see org.eclipse.buckminster.rmap.psf.PsfFactory
 * @model kind="package"
 * @generated
 */
public interface PsfPackage extends EPackage {
	/**
	 * <!-- begin-user-doc --> Defines literals for the meta objects that
	 * represent
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
	interface Literals {
		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.buckminster.rmap.psf.impl.PSFProviderImpl
		 * <em>PSF Provider</em>}' class. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.rmap.psf.impl.PSFProviderImpl
		 * @see org.eclipse.buckminster.rmap.psf.impl.PsfPackageImpl#getPSFProvider()
		 * @generated
		 */
		EClass PSF_PROVIDER = eINSTANCE.getPSFProvider();

		/**
		 * The meta object literal for the '<em><b>Psf File</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PSF_PROVIDER__PSF_FILE = eINSTANCE.getPSFProvider_PsfFile();

	}

	/**
	 * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNAME = "psf";

	/**
	 * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/buckminster/PSFProvider-1.0";

	/**
	 * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_PREFIX = "psf";

	/**
	 * The singleton instance of the package. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	PsfPackage eINSTANCE = org.eclipse.buckminster.rmap.psf.impl.PsfPackageImpl.init();

	/**
	 * The meta object id for the '
	 * {@link org.eclipse.buckminster.rmap.psf.impl.PSFProviderImpl
	 * <em>PSF Provider</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see org.eclipse.buckminster.rmap.psf.impl.PSFProviderImpl
	 * @see org.eclipse.buckminster.rmap.psf.impl.PsfPackageImpl#getPSFProvider()
	 * @generated
	 */
	int PSF_PROVIDER = 0;

	/**
	 * The feature id for the '<em><b>Component Types</b></em>' attribute list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PSF_PROVIDER__COMPONENT_TYPES = RmapPackage.PROVIDER__COMPONENT_TYPES;

	/**
	 * The feature id for the '<em><b>Component Types Attr</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PSF_PROVIDER__COMPONENT_TYPES_ATTR = RmapPackage.PROVIDER__COMPONENT_TYPES_ATTR;

	/**
	 * The feature id for the '<em><b>Reader Type</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PSF_PROVIDER__READER_TYPE = RmapPackage.PROVIDER__READER_TYPE;

	/**
	 * The feature id for the '<em><b>Source</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PSF_PROVIDER__SOURCE = RmapPackage.PROVIDER__SOURCE;

	/**
	 * The feature id for the '<em><b>Mutable</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PSF_PROVIDER__MUTABLE = RmapPackage.PROVIDER__MUTABLE;

	/**
	 * The feature id for the '<em><b>Resolution Filter</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PSF_PROVIDER__RESOLUTION_FILTER = RmapPackage.PROVIDER__RESOLUTION_FILTER;

	/**
	 * The feature id for the '<em><b>Version Converter</b></em>' containment
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PSF_PROVIDER__VERSION_CONVERTER = RmapPackage.PROVIDER__VERSION_CONVERTER;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' containment reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PSF_PROVIDER__URI = RmapPackage.PROVIDER__URI;

	/**
	 * The feature id for the '<em><b>Matchers</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PSF_PROVIDER__MATCHERS = RmapPackage.PROVIDER__MATCHERS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PSF_PROVIDER__DOCUMENTATION = RmapPackage.PROVIDER__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Psf File</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PSF_PROVIDER__PSF_FILE = RmapPackage.PROVIDER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>PSF Provider</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PSF_PROVIDER_FEATURE_COUNT = RmapPackage.PROVIDER_FEATURE_COUNT + 1;

	/**
	 * Returns the factory that creates the instances of the model. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	PsfFactory getPsfFactory();

	/**
	 * Returns the meta object for class '
	 * {@link org.eclipse.buckminster.rmap.psf.PSFProvider
	 * <em>PSF Provider</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>PSF Provider</em>'.
	 * @see org.eclipse.buckminster.rmap.psf.PSFProvider
	 * @generated
	 */
	EClass getPSFProvider();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.rmap.psf.PSFProvider#getPsfFile
	 * <em>Psf File</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Psf File</em>'.
	 * @see org.eclipse.buckminster.rmap.psf.PSFProvider#getPsfFile()
	 * @see #getPSFProvider()
	 * @generated
	 */
	EAttribute getPSFProvider_PsfFile();

} // PsfPackage
