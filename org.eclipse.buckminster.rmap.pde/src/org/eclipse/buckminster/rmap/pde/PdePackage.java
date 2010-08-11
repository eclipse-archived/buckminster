/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap.pde;

import org.eclipse.buckminster.rmap.RmapPackage;

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
 * @see org.eclipse.buckminster.rmap.pde.PdeFactory
 * @model kind="package"
 * @generated
 */
public interface PdePackage extends EPackage {
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
		 * {@link org.eclipse.buckminster.rmap.pde.impl.PDEMapProviderImpl
		 * <em>PDE Map Provider</em>}' class. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.rmap.pde.impl.PDEMapProviderImpl
		 * @see org.eclipse.buckminster.rmap.pde.impl.PdePackageImpl#getPDEMapProvider()
		 * @generated
		 */
		EClass PDE_MAP_PROVIDER = eINSTANCE.getPDEMapProvider();

	}

	/**
	 * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNAME = "pde";

	/**
	 * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/buckminster/PDEMapProvider-1.0";

	/**
	 * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_PREFIX = "pde";

	/**
	 * The singleton instance of the package. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	PdePackage eINSTANCE = org.eclipse.buckminster.rmap.pde.impl.PdePackageImpl.init();

	/**
	 * The meta object id for the '
	 * {@link org.eclipse.buckminster.rmap.pde.impl.PDEMapProviderImpl
	 * <em>PDE Map Provider</em>}' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.rmap.pde.impl.PDEMapProviderImpl
	 * @see org.eclipse.buckminster.rmap.pde.impl.PdePackageImpl#getPDEMapProvider()
	 * @generated
	 */
	int PDE_MAP_PROVIDER = 0;

	/**
	 * The feature id for the '<em><b>Property Constants</b></em>' map. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PDE_MAP_PROVIDER__PROPERTY_CONSTANTS = RmapPackage.PROVIDER__PROPERTY_CONSTANTS;

	/**
	 * The feature id for the '<em><b>Property Elements</b></em>' map. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PDE_MAP_PROVIDER__PROPERTY_ELEMENTS = RmapPackage.PROVIDER__PROPERTY_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Component Types</b></em>' attribute list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PDE_MAP_PROVIDER__COMPONENT_TYPES = RmapPackage.PROVIDER__COMPONENT_TYPES;

	/**
	 * The feature id for the '<em><b>Component Types Attr</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PDE_MAP_PROVIDER__COMPONENT_TYPES_ATTR = RmapPackage.PROVIDER__COMPONENT_TYPES_ATTR;

	/**
	 * The feature id for the '<em><b>Reader Type</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PDE_MAP_PROVIDER__READER_TYPE = RmapPackage.PROVIDER__READER_TYPE;

	/**
	 * The feature id for the '<em><b>Source</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PDE_MAP_PROVIDER__SOURCE = RmapPackage.PROVIDER__SOURCE;

	/**
	 * The feature id for the '<em><b>Mutable</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PDE_MAP_PROVIDER__MUTABLE = RmapPackage.PROVIDER__MUTABLE;

	/**
	 * The feature id for the '<em><b>Resolution Filter</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PDE_MAP_PROVIDER__RESOLUTION_FILTER = RmapPackage.PROVIDER__RESOLUTION_FILTER;

	/**
	 * The feature id for the '<em><b>Version Converter</b></em>' containment
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PDE_MAP_PROVIDER__VERSION_CONVERTER = RmapPackage.PROVIDER__VERSION_CONVERTER;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' containment reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PDE_MAP_PROVIDER__URI = RmapPackage.PROVIDER__URI;

	/**
	 * The feature id for the '<em><b>Matcher</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PDE_MAP_PROVIDER__MATCHER = RmapPackage.PROVIDER__MATCHER;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PDE_MAP_PROVIDER__DOCUMENTATION = RmapPackage.PROVIDER__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Repository</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PDE_MAP_PROVIDER__REPOSITORY = RmapPackage.PROVIDER__REPOSITORY;

	/**
	 * The number of structural features of the '<em>PDE Map Provider</em>'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PDE_MAP_PROVIDER_FEATURE_COUNT = RmapPackage.PROVIDER_FEATURE_COUNT + 0;

	/**
	 * Returns the factory that creates the instances of the model. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	PdeFactory getPdeFactory();

	/**
	 * Returns the meta object for class '
	 * {@link org.eclipse.buckminster.rmap.pde.PDEMapProvider
	 * <em>PDE Map Provider</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for class '<em>PDE Map Provider</em>'.
	 * @see org.eclipse.buckminster.rmap.pde.PDEMapProvider
	 * @generated
	 */
	EClass getPDEMapProvider();

} // PdePackage
