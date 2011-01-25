/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.mspec.impl;

import org.eclipse.buckminster.mspec.MaterializationDirective;
import org.eclipse.buckminster.mspec.MaterializationNode;
import org.eclipse.buckminster.mspec.MaterializationSpec;
import org.eclipse.buckminster.mspec.MspecFactory;
import org.eclipse.buckminster.mspec.MspecPackage;
import org.eclipse.buckminster.mspec.Unpack;
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
public class MspecFactoryImpl extends EFactoryImpl implements MspecFactory {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static MspecPackage getPackage() {
		return MspecPackage.eINSTANCE;
	}

	/**
	 * Creates the default factory implementation. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public static MspecFactory init() {
		try {
			MspecFactory theMspecFactory = (MspecFactory) EPackage.Registry.INSTANCE.getEFactory("http://www.eclipse.org/buckminster/MetaData-1.0");
			if (theMspecFactory != null) {
				return theMspecFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new MspecFactoryImpl();
	}

	/**
	 * Creates an instance of the factory. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public MspecFactoryImpl() {
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
			case MspecPackage.DOCUMENT_ROOT:
				return createDocumentRoot();
			case MspecPackage.MATERIALIZATION_NODE:
				return createMaterializationNode();
			case MspecPackage.MATERIALIZATION_DIRECTIVE:
				return createMaterializationDirective();
			case MspecPackage.MATERIALIZATION_SPEC:
				return createMaterializationSpec();
			case MspecPackage.UNPACK:
				return createUnpack();
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
		EObject documentRoot = super.create(MspecPackage.Literals.DOCUMENT_ROOT);
		return documentRoot;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public MaterializationDirective createMaterializationDirective() {
		MaterializationDirectiveImpl materializationDirective = new MaterializationDirectiveImpl();
		return materializationDirective;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public MaterializationNode createMaterializationNode() {
		MaterializationNodeImpl materializationNode = new MaterializationNodeImpl();
		return materializationNode;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public MaterializationSpec createMaterializationSpec() {
		MaterializationSpecImpl materializationSpec = new MaterializationSpecImpl();
		return materializationSpec;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public Unpack createUnpack() {
		UnpackImpl unpack = new UnpackImpl();
		return unpack;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public MspecPackage getMspecPackage() {
		return (MspecPackage) getEPackage();
	}

} // MspecFactoryImpl
