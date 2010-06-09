/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.team.psf.impl;

import org.eclipse.buckminster.team.psf.*;

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
public class PsfFactoryImpl extends EFactoryImpl implements PsfFactory {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static PsfPackage getPackage() {
		return PsfPackage.eINSTANCE;
	}

	/**
	 * Creates the default factory implementation. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public static PsfFactory init() {
		try {
			PsfFactory thePsfFactory = (PsfFactory) EPackage.Registry.INSTANCE.getEFactory("http://www.eclipse.org/team/psf");
			if (thePsfFactory != null) {
				return thePsfFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new PsfFactoryImpl();
	}

	/**
	 * Creates an instance of the factory. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public PsfFactoryImpl() {
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
			case PsfPackage.PSF:
				return createPSF();
			case PsfPackage.REPOSITORY_PROVIDER:
				return createRepositoryProvider();
			case PsfPackage.WORKING_SET:
				return createWorkingSet();
			case PsfPackage.ITEM:
				return createItem();
			case PsfPackage.PROJECT:
				return createProject();
			case PsfPackage.DOCUMENT_ROOT:
				return createDocumentRoot();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EObject createDocumentRoot() {
		EObject documentRoot = super.create(PsfPackage.Literals.DOCUMENT_ROOT);
		return documentRoot;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Item createItem() {
		ItemImpl item = new ItemImpl();
		return item;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Project createProject() {
		ProjectImpl project = new ProjectImpl();
		return project;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public PSF createPSF() {
		PSFImpl psf = new PSFImpl();
		return psf;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public RepositoryProvider createRepositoryProvider() {
		RepositoryProviderImpl repositoryProvider = new RepositoryProviderImpl();
		return repositoryProvider;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public WorkingSet createWorkingSet() {
		WorkingSetImpl workingSet = new WorkingSetImpl();
		return workingSet;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public PsfPackage getPsfPackage() {
		return (PsfPackage) getEPackage();
	}

} // PsfFactoryImpl
