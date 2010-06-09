/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspec.impl;

import org.eclipse.buckminster.cspec.*;

import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
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
public class CspecFactoryImpl extends EFactoryImpl implements CspecFactory {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static CspecPackage getPackage() {
		return CspecPackage.eINSTANCE;
	}

	/**
	 * Creates the default factory implementation. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public static CspecFactory init() {
		try {
			CspecFactory theCspecFactory = (CspecFactory) EPackage.Registry.INSTANCE.getEFactory("http://www.eclipse.org/buckminster/CSpec-2.0");
			if (theCspecFactory != null) {
				return theCspecFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new CspecFactoryImpl();
	}

	/**
	 * Creates an instance of the factory. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public CspecFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String convertIPathToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case CspecPackage.UP_TO_DATE_POLICY:
				return convertUpToDatePolicyToString(eDataType, instanceValue);
			case CspecPackage.IPATH:
				return convertIPathToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String convertUpToDatePolicyToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case CspecPackage.CSPEC:
				return createCSpec();
			case CspecPackage.GROUP:
				return createGroup();
			case CspecPackage.PREREQUISITE:
				return createPrerequisite();
			case CspecPackage.ACTION:
				return createAction();
			case CspecPackage.ARTIFACT:
				return createArtifact();
			case CspecPackage.ACTION_ATTRIBUTE:
				return createActionAttribute();
			case CspecPackage.PATH_GROUP:
				return createPathGroup();
			case CspecPackage.GENERATOR:
				return createGenerator();
			case CspecPackage.ALTER_ARTIFACT:
				return createAlterArtifact();
			case CspecPackage.ALTER_GROUP:
				return createAlterGroup();
			case CspecPackage.ALTER_ACTION:
				return createAlterAction();
			case CspecPackage.RENAME:
				return createRename();
			case CspecPackage.REMOVE:
				return createRemove();
			case CspecPackage.CSPEC_EXTENSION:
				return createCSpecExtension();
			case CspecPackage.SELF_ARTIFACT:
				return createSelfArtifact();
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
	public Action createAction() {
		ActionImpl action = new ActionImpl();
		return action;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public ActionAttribute createActionAttribute() {
		ActionAttributeImpl actionAttribute = new ActionAttributeImpl();
		return actionAttribute;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public AlterAction createAlterAction() {
		AlterActionImpl alterAction = new AlterActionImpl();
		return alterAction;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public AlterArtifact createAlterArtifact() {
		AlterArtifactImpl alterArtifact = new AlterArtifactImpl();
		return alterArtifact;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public AlterGroup createAlterGroup() {
		AlterGroupImpl alterGroup = new AlterGroupImpl();
		return alterGroup;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Artifact createArtifact() {
		ArtifactImpl artifact = new ArtifactImpl();
		return artifact;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public CSpec createCSpec() {
		CSpecImpl cSpec = new CSpecImpl();
		return cSpec;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public CSpecExtension createCSpecExtension() {
		CSpecExtensionImpl cSpecExtension = new CSpecExtensionImpl();
		return cSpecExtension;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case CspecPackage.UP_TO_DATE_POLICY:
				return createUpToDatePolicyFromString(eDataType, initialValue);
			case CspecPackage.IPATH:
				return createIPathFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Generator createGenerator() {
		GeneratorImpl generator = new GeneratorImpl();
		return generator;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Group createGroup() {
		GroupImpl group = new GroupImpl();
		return group;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public IPath createIPathFromString(EDataType eDataType, String initialValue) {
		return (IPath) super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public PathGroup createPathGroup() {
		PathGroupImpl pathGroup = new PathGroupImpl();
		return pathGroup;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Prerequisite createPrerequisite() {
		PrerequisiteImpl prerequisite = new PrerequisiteImpl();
		return prerequisite;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Remove createRemove() {
		RemoveImpl remove = new RemoveImpl();
		return remove;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Rename createRename() {
		RenameImpl rename = new RenameImpl();
		return rename;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public SelfArtifact createSelfArtifact() {
		SelfArtifactImpl selfArtifact = new SelfArtifactImpl();
		return selfArtifact;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public UpToDatePolicy createUpToDatePolicyFromString(EDataType eDataType, String initialValue) {
		UpToDatePolicy result = UpToDatePolicy.get(initialValue);
		if (result == null)
			throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public CspecPackage getCspecPackage() {
		return (CspecPackage) getEPackage();
	}

} // CspecFactoryImpl
