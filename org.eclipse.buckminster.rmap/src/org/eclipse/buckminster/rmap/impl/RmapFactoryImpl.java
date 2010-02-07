/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap.impl;

import org.eclipse.buckminster.rmap.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class RmapFactoryImpl extends EFactoryImpl implements RmapFactory {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static RmapPackage getPackage() {
		return RmapPackage.eINSTANCE;
	}

	/**
	 * Creates the default factory implementation. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public static RmapFactory init() {
		try {
			RmapFactory theRmapFactory = (RmapFactory) EPackage.Registry.INSTANCE.getEFactory("http://www.eclipse.org/buckminster/RMap-1.0");
			if (theRmapFactory != null) {
				return theRmapFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new RmapFactoryImpl();
	}

	/**
	 * Creates an instance of the factory. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public RmapFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case RmapPackage.REDIRECT:
				return createRedirect();
			case RmapPackage.LOCATOR:
				return createLocator();
			case RmapPackage.SEARCH_PATH:
				return createSearchPath();
			case RmapPackage.RESOURCE_MAP:
				return createResourceMap();
			case RmapPackage.PROVIDER:
				return createProvider();
			case RmapPackage.TRANSFORM:
				return createTransform();
			case RmapPackage.VERSION_CONVERTER:
				return createVersionConverter();
			case RmapPackage.DOCUMENT_ROOT:
				return createDocumentRoot();
			case RmapPackage.URI_MATCHER:
				return createURIMatcher();
			case RmapPackage.PROPERTIES:
				return createProperties();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EObject createDocumentRoot() {
		EObject documentRoot = super.create(RmapPackage.Literals.DOCUMENT_ROOT);
		return documentRoot;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Locator createLocator() {
		LocatorImpl locator = new LocatorImpl();
		return locator;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Properties createProperties() {
		PropertiesImpl properties = new PropertiesImpl();
		return properties;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Provider createProvider() {
		ProviderImpl provider = new ProviderImpl();
		return provider;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Redirect createRedirect() {
		RedirectImpl redirect = new RedirectImpl();
		return redirect;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ResourceMap createResourceMap() {
		ResourceMapImpl resourceMap = new ResourceMapImpl();
		return resourceMap;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public SearchPath createSearchPath() {
		SearchPathImpl searchPath = new SearchPathImpl();
		return searchPath;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Transform createTransform() {
		TransformImpl transform = new TransformImpl();
		return transform;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public URIMatcher createURIMatcher() {
		URIMatcherImpl uriMatcher = new URIMatcherImpl();
		return uriMatcher;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public VersionConverter createVersionConverter() {
		VersionConverterImpl versionConverter = new VersionConverterImpl();
		return versionConverter;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public RmapPackage getRmapPackage() {
		return (RmapPackage) getEPackage();
	}

} // RmapFactoryImpl
