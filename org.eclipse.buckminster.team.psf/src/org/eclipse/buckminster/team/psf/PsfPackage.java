/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.team.psf;

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
 * @see org.eclipse.buckminster.team.psf.PsfFactory
 * @model kind="package" extendedMetaData="qualified='false'"
 * @generated
 */
public interface PsfPackage extends EPackage
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
		 * The meta object literal for the '{@link org.eclipse.buckminster.team.psf.impl.PSFImpl <em>PSF</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.team.psf.impl.PSFImpl
		 * @see org.eclipse.buckminster.team.psf.impl.PsfPackageImpl#getPSF()
		 * @generated
		 */
		EClass PSF = eINSTANCE.getPSF();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PSF__VERSION = eINSTANCE.getPSF_Version();

		/**
		 * The meta object literal for the '<em><b>Providers</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference PSF__PROVIDERS = eINSTANCE.getPSF_Providers();

		/**
		 * The meta object literal for the '<em><b>Working Sets</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference PSF__WORKING_SETS = eINSTANCE.getPSF_WorkingSets();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.team.psf.impl.RepositoryProviderImpl
		 * <em>Repository Provider</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.team.psf.impl.RepositoryProviderImpl
		 * @see org.eclipse.buckminster.team.psf.impl.PsfPackageImpl#getRepositoryProvider()
		 * @generated
		 */
		EClass REPOSITORY_PROVIDER = eINSTANCE.getRepositoryProvider();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute REPOSITORY_PROVIDER__ID = eINSTANCE.getRepositoryProvider_Id();

		/**
		 * The meta object literal for the '<em><b>Projects</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference REPOSITORY_PROVIDER__PROJECTS = eINSTANCE.getRepositoryProvider_Projects();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.team.psf.impl.WorkingSetImpl
		 * <em>Working Set</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.team.psf.impl.WorkingSetImpl
		 * @see org.eclipse.buckminster.team.psf.impl.PsfPackageImpl#getWorkingSet()
		 * @generated
		 */
		EClass WORKING_SET = eINSTANCE.getWorkingSet();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute WORKING_SET__ID = eINSTANCE.getWorkingSet_Id();

		/**
		 * The meta object literal for the '<em><b>Label</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute WORKING_SET__LABEL = eINSTANCE.getWorkingSet_Label();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute WORKING_SET__NAME = eINSTANCE.getWorkingSet_Name();

		/**
		 * The meta object literal for the '<em><b>Edit Page Id</b></em>' attribute feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute WORKING_SET__EDIT_PAGE_ID = eINSTANCE.getWorkingSet_EditPageId();

		/**
		 * The meta object literal for the '<em><b>Items</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference WORKING_SET__ITEMS = eINSTANCE.getWorkingSet_Items();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.team.psf.impl.ItemImpl <em>Item</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.team.psf.impl.ItemImpl
		 * @see org.eclipse.buckminster.team.psf.impl.PsfPackageImpl#getItem()
		 * @generated
		 */
		EClass ITEM = eINSTANCE.getItem();

		/**
		 * The meta object literal for the '<em><b>Factory ID</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ITEM__FACTORY_ID = eINSTANCE.getItem_FactoryID();

		/**
		 * The meta object literal for the '<em><b>Element ID</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ITEM__ELEMENT_ID = eINSTANCE.getItem_ElementID();

		/**
		 * The meta object literal for the '<em><b>Path</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ITEM__PATH = eINSTANCE.getItem_Path();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ITEM__TYPE = eINSTANCE.getItem_Type();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.team.psf.impl.ProjectImpl <em>Project</em>}'
		 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.team.psf.impl.ProjectImpl
		 * @see org.eclipse.buckminster.team.psf.impl.PsfPackageImpl#getProject()
		 * @generated
		 */
		EClass PROJECT = eINSTANCE.getProject();

		/**
		 * The meta object literal for the '<em><b>Reference</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PROJECT__REFERENCE = eINSTANCE.getProject_Reference();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.team.psf.impl.DocumentRootImpl
		 * <em>Document Root</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.team.psf.impl.DocumentRootImpl
		 * @see org.eclipse.buckminster.team.psf.impl.PsfPackageImpl#getDocumentRoot()
		 * @generated
		 */
		EClass DOCUMENT_ROOT = eINSTANCE.getDocumentRoot();

		/**
		 * The meta object literal for the '<em><b>Psf</b></em>' containment reference feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__PSF = eINSTANCE.getDocumentRoot_Psf();

		/**
		 * The meta object literal for the '<em><b>Mixed</b></em>' attribute list feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__MIXED = eINSTANCE.getDocumentRoot_Mixed();

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
	String eNS_URI = "http://www.eclipse.org/team/psf";

	/**
	 * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_PREFIX = "psf";

	/**
	 * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	PsfPackage eINSTANCE = org.eclipse.buckminster.team.psf.impl.PsfPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.team.psf.impl.PSFImpl <em>PSF</em>}' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.team.psf.impl.PSFImpl
	 * @see org.eclipse.buckminster.team.psf.impl.PsfPackageImpl#getPSF()
	 * @generated
	 */
	int PSF = 0;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PSF__VERSION = 0;

	/**
	 * The feature id for the '<em><b>Providers</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PSF__PROVIDERS = 1;

	/**
	 * The feature id for the '<em><b>Working Sets</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PSF__WORKING_SETS = 2;

	/**
	 * The number of structural features of the '<em>PSF</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PSF_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.team.psf.impl.RepositoryProviderImpl
	 * <em>Repository Provider</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.team.psf.impl.RepositoryProviderImpl
	 * @see org.eclipse.buckminster.team.psf.impl.PsfPackageImpl#getRepositoryProvider()
	 * @generated
	 */
	int REPOSITORY_PROVIDER = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REPOSITORY_PROVIDER__ID = 0;

	/**
	 * The feature id for the '<em><b>Projects</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REPOSITORY_PROVIDER__PROJECTS = 1;

	/**
	 * The number of structural features of the '<em>Repository Provider</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REPOSITORY_PROVIDER_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.team.psf.impl.WorkingSetImpl <em>Working Set</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.team.psf.impl.WorkingSetImpl
	 * @see org.eclipse.buckminster.team.psf.impl.PsfPackageImpl#getWorkingSet()
	 * @generated
	 */
	int WORKING_SET = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int WORKING_SET__ID = 0;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int WORKING_SET__LABEL = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int WORKING_SET__NAME = 2;

	/**
	 * The feature id for the '<em><b>Edit Page Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int WORKING_SET__EDIT_PAGE_ID = 3;

	/**
	 * The feature id for the '<em><b>Items</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int WORKING_SET__ITEMS = 4;

	/**
	 * The number of structural features of the '<em>Working Set</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int WORKING_SET_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.team.psf.impl.ItemImpl <em>Item</em>}' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.team.psf.impl.ItemImpl
	 * @see org.eclipse.buckminster.team.psf.impl.PsfPackageImpl#getItem()
	 * @generated
	 */
	int ITEM = 3;

	/**
	 * The feature id for the '<em><b>Factory ID</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ITEM__FACTORY_ID = 0;

	/**
	 * The feature id for the '<em><b>Element ID</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ITEM__ELEMENT_ID = 1;

	/**
	 * The feature id for the '<em><b>Path</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ITEM__PATH = 2;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ITEM__TYPE = 3;

	/**
	 * The number of structural features of the '<em>Item</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ITEM_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.team.psf.impl.ProjectImpl <em>Project</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.team.psf.impl.ProjectImpl
	 * @see org.eclipse.buckminster.team.psf.impl.PsfPackageImpl#getProject()
	 * @generated
	 */
	int PROJECT = 4;

	/**
	 * The feature id for the '<em><b>Reference</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROJECT__REFERENCE = 0;

	/**
	 * The number of structural features of the '<em>Project</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROJECT_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.team.psf.impl.DocumentRootImpl <em>Document Root</em>}
	 * ' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.team.psf.impl.DocumentRootImpl
	 * @see org.eclipse.buckminster.team.psf.impl.PsfPackageImpl#getDocumentRoot()
	 * @generated
	 */
	int DOCUMENT_ROOT = 5;

	/**
	 * The feature id for the '<em><b>Psf</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__PSF = 0;

	/**
	 * The feature id for the '<em><b>Mixed</b></em>' attribute list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__MIXED = 1;

	/**
	 * The number of structural features of the '<em>Document Root</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT_FEATURE_COUNT = 2;

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.ecore.EObject <em>Document Root</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Document Root</em>'.
	 * @see org.eclipse.emf.ecore.EObject
	 * @model extendedMetaData="kind='mixed' name=''"
	 * @generated
	 */
	EClass getDocumentRoot();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.emf.ecore.EObject#getMixed <em>Mixed</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute list '<em>Mixed</em>'.
	 * @see org.eclipse.emf.ecore.EObject#getMixed()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Mixed();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.emf.ecore.EObject#getPsf <em>Psf</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Psf</em>'.
	 * @see org.eclipse.emf.ecore.EObject#getPsf()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Psf();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.team.psf.Item <em>Item</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Item</em>'.
	 * @see org.eclipse.buckminster.team.psf.Item
	 * @generated
	 */
	EClass getItem();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.team.psf.Item#getElementID
	 * <em>Element ID</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Element ID</em>'.
	 * @see org.eclipse.buckminster.team.psf.Item#getElementID()
	 * @see #getItem()
	 * @generated
	 */
	EAttribute getItem_ElementID();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.team.psf.Item#getFactoryID
	 * <em>Factory ID</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Factory ID</em>'.
	 * @see org.eclipse.buckminster.team.psf.Item#getFactoryID()
	 * @see #getItem()
	 * @generated
	 */
	EAttribute getItem_FactoryID();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.team.psf.Item#getPath <em>Path</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Path</em>'.
	 * @see org.eclipse.buckminster.team.psf.Item#getPath()
	 * @see #getItem()
	 * @generated
	 */
	EAttribute getItem_Path();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.team.psf.Item#getType <em>Type</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.eclipse.buckminster.team.psf.Item#getType()
	 * @see #getItem()
	 * @generated
	 */
	EAttribute getItem_Type();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.team.psf.Project <em>Project</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Project</em>'.
	 * @see org.eclipse.buckminster.team.psf.Project
	 * @generated
	 */
	EClass getProject();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.team.psf.Project#getReference
	 * <em>Reference</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Reference</em>'.
	 * @see org.eclipse.buckminster.team.psf.Project#getReference()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_Reference();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.team.psf.PSF <em>PSF</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>PSF</em>'.
	 * @see org.eclipse.buckminster.team.psf.PSF
	 * @generated
	 */
	EClass getPSF();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.team.psf.PSF#getProviders <em>Providers</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Providers</em>'.
	 * @see org.eclipse.buckminster.team.psf.PSF#getProviders()
	 * @see #getPSF()
	 * @generated
	 */
	EReference getPSF_Providers();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.team.psf.PSF#getVersion
	 * <em>Version</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see org.eclipse.buckminster.team.psf.PSF#getVersion()
	 * @see #getPSF()
	 * @generated
	 */
	EAttribute getPSF_Version();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.team.psf.PSF#getWorkingSets <em>Working Sets</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Working Sets</em>'.
	 * @see org.eclipse.buckminster.team.psf.PSF#getWorkingSets()
	 * @see #getPSF()
	 * @generated
	 */
	EReference getPSF_WorkingSets();

	/**
	 * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	PsfFactory getPsfFactory();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.team.psf.RepositoryProvider
	 * <em>Repository Provider</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Repository Provider</em>'.
	 * @see org.eclipse.buckminster.team.psf.RepositoryProvider
	 * @generated
	 */
	EClass getRepositoryProvider();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.team.psf.RepositoryProvider#getId
	 * <em>Id</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.buckminster.team.psf.RepositoryProvider#getId()
	 * @see #getRepositoryProvider()
	 * @generated
	 */
	EAttribute getRepositoryProvider_Id();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.team.psf.RepositoryProvider#getProjects <em>Projects</em>}'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Projects</em>'.
	 * @see org.eclipse.buckminster.team.psf.RepositoryProvider#getProjects()
	 * @see #getRepositoryProvider()
	 * @generated
	 */
	EReference getRepositoryProvider_Projects();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.team.psf.WorkingSet <em>Working Set</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Working Set</em>'.
	 * @see org.eclipse.buckminster.team.psf.WorkingSet
	 * @generated
	 */
	EClass getWorkingSet();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.team.psf.WorkingSet#getEditPageId
	 * <em>Edit Page Id</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Edit Page Id</em>'.
	 * @see org.eclipse.buckminster.team.psf.WorkingSet#getEditPageId()
	 * @see #getWorkingSet()
	 * @generated
	 */
	EAttribute getWorkingSet_EditPageId();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.team.psf.WorkingSet#getId <em>Id</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.buckminster.team.psf.WorkingSet#getId()
	 * @see #getWorkingSet()
	 * @generated
	 */
	EAttribute getWorkingSet_Id();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.team.psf.WorkingSet#getItems <em>Items</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Items</em>'.
	 * @see org.eclipse.buckminster.team.psf.WorkingSet#getItems()
	 * @see #getWorkingSet()
	 * @generated
	 */
	EReference getWorkingSet_Items();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.team.psf.WorkingSet#getLabel
	 * <em>Label</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Label</em>'.
	 * @see org.eclipse.buckminster.team.psf.WorkingSet#getLabel()
	 * @see #getWorkingSet()
	 * @generated
	 */
	EAttribute getWorkingSet_Label();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.team.psf.WorkingSet#getName
	 * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.buckminster.team.psf.WorkingSet#getName()
	 * @see #getWorkingSet()
	 * @generated
	 */
	EAttribute getWorkingSet_Name();

} // PsfPackage
