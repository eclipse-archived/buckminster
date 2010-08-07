/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap.impl;

import org.eclipse.buckminster.rmap.*;

import org.eclipse.buckminster.rmap.util.TransformMismatchException;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!--
 * end-user-doc -->
 * @generated
 */
public class RmapFactoryImpl extends EFactoryImpl implements RmapFactory {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static RmapPackage getPackage() {
		return RmapPackage.eINSTANCE;
	}

	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	public static RmapFactory init() {
		try {
			RmapFactory theRmapFactory = (RmapFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.eclipse.org/buckminster/RMap-1.0"); 
			if (theRmapFactory != null) {
				return theRmapFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new RmapFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	public RmapFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String convertConflictPolicyToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransformMismatchException createTransformMismatchExceptionFromString(EDataType eDataType, String initialValue) {
		return (TransformMismatchException)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTransformMismatchExceptionToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case RmapPackage.VERSION_SELECTOR_TYPE:
				return convertVersionSelectorTypeToString(eDataType, instanceValue);
			case RmapPackage.CONFLICT_POLICY:
				return convertConflictPolicyToString(eDataType, instanceValue);
			case RmapPackage.TRANSFORM_MISMATCH_EXCEPTION:
				return convertTransformMismatchExceptionToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case RmapPackage.BRANCH_POINT: return createBranchPoint();
			case RmapPackage.DOCUMENT_ROOT: return createDocumentRoot();
			case RmapPackage.LOCATOR: return createLocator();
			case RmapPackage.PROVIDER: return createProvider();
			case RmapPackage.REDIRECT: return createRedirect();
			case RmapPackage.REPOSITORY: return createRepository();
			case RmapPackage.RESOURCE_MAP: return createResourceMap();
			case RmapPackage.SEARCH_PATH: return createSearchPath();
			case RmapPackage.TRANSFORM: return createTransform();
			case RmapPackage.URI_MATCHER: return createURIMatcher();
			case RmapPackage.VERSION_CONVERTER: return createVersionConverter();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public BranchPoint createBranchPoint() {
		BranchPointImpl branchPoint = new BranchPointImpl();
		return branchPoint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VersionSelectorType createVersionSelectorTypeFromString(EDataType eDataType, String initialValue) {
		VersionSelectorType result = VersionSelectorType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertVersionSelectorTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public ConflictPolicy createConflictPolicyFromString(EDataType eDataType, String initialValue) {
		ConflictPolicy result = ConflictPolicy.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EObject createDocumentRoot() {
		EObject documentRoot = super.create(RmapPackage.Literals.DOCUMENT_ROOT);
		return documentRoot;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case RmapPackage.VERSION_SELECTOR_TYPE:
				return createVersionSelectorTypeFromString(eDataType, initialValue);
			case RmapPackage.CONFLICT_POLICY:
				return createConflictPolicyFromString(eDataType, initialValue);
			case RmapPackage.TRANSFORM_MISMATCH_EXCEPTION:
				return createTransformMismatchExceptionFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public Locator createLocator() {
		LocatorImpl locator = new LocatorImpl();
		return locator;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public Provider createProvider() {
		ProviderImpl provider = new ProviderImpl();
		return provider;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public Redirect createRedirect() {
		RedirectImpl redirect = new RedirectImpl();
		return redirect;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public Repository createRepository() {
		RepositoryImpl repository = new RepositoryImpl();
		return repository;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public ResourceMap createResourceMap() {
		ResourceMapImpl resourceMap = new ResourceMapImpl();
		return resourceMap;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public SearchPath createSearchPath() {
		SearchPathImpl searchPath = new SearchPathImpl();
		return searchPath;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public Transform createTransform() {
		TransformImpl transform = new TransformImpl();
		return transform;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public URIMatcher createURIMatcher() {
		URIMatcherImpl uriMatcher = new URIMatcherImpl();
		return uriMatcher;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public VersionConverter createVersionConverter() {
		VersionConverterImpl versionConverter = new VersionConverterImpl();
		return versionConverter;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public RmapPackage getRmapPackage() {
		return (RmapPackage)getEPackage();
	}

} // RmapFactoryImpl
