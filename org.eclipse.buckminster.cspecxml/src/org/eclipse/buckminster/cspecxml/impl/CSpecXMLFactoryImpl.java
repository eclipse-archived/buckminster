/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspecxml.impl;

import org.eclipse.buckminster.cspecxml.*;

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
public class CSpecXMLFactoryImpl extends EFactoryImpl implements ICSpecXMLFactory {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ICSpecXMLPackage getPackage() {
		return ICSpecXMLPackage.eINSTANCE;
	}

	/**
	 * Creates the default factory implementation. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public static ICSpecXMLFactory init() {
		try {
			ICSpecXMLFactory theCSpecXMLFactory = (ICSpecXMLFactory) EPackage.Registry.INSTANCE
					.getEFactory("http://www.eclipse.org/buckminster/CSpec-1.0");
			if (theCSpecXMLFactory != null) {
				return theCSpecXMLFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new CSpecXMLFactoryImpl();
	}

	/**
	 * Creates an instance of the factory. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public CSpecXMLFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case ICSpecXMLPackage.UP_TO_DATE_POLICY:
				return convertUpToDatePolicyToString(eDataType, instanceValue);
			case ICSpecXMLPackage.UP_TO_DATE_POLICY_OBJECT:
				return convertUpToDatePolicyObjectToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String convertUpToDatePolicyObjectToString(EDataType eDataType, Object instanceValue) {
		return convertUpToDatePolicyToString(ICSpecXMLPackage.Literals.UP_TO_DATE_POLICY, instanceValue);
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
			case ICSpecXMLPackage.ACTION:
				return createAction();
			case ICSpecXMLPackage.ACTION_ARTIFACT:
				return createActionArtifact();
			case ICSpecXMLPackage.ACTIONS_TYPE:
				return createActionsType();
			case ICSpecXMLPackage.ALTER_ACTION:
				return createAlterAction();
			case ICSpecXMLPackage.ALTER_ACTIONS_TYPE:
				return createAlterActionsType();
			case ICSpecXMLPackage.ALTER_ARTIFACT:
				return createAlterArtifact();
			case ICSpecXMLPackage.ALTER_ARTIFACTS_TYPE:
				return createAlterArtifactsType();
			case ICSpecXMLPackage.ALTER_DEPENDENCIES_TYPE:
				return createAlterDependenciesType();
			case ICSpecXMLPackage.ALTER_GROUP:
				return createAlterGroup();
			case ICSpecXMLPackage.ALTER_GROUPS_TYPE:
				return createAlterGroupsType();
			case ICSpecXMLPackage.ALTER_PREREQUISITES:
				return createAlterPrerequisites();
			case ICSpecXMLPackage.ALTER_PRODUCTS_TYPE:
				return createAlterProductsType();
			case ICSpecXMLPackage.ALTER_PROPERTIES:
				return createAlterProperties();
			case ICSpecXMLPackage.ARTIFACT:
				return createArtifact();
			case ICSpecXMLPackage.ARTIFACTS_TYPE:
				return createArtifactsType();
			case ICSpecXMLPackage.ATTRIBUTE:
				return createAttribute();
			case ICSpecXMLPackage.COMPONENT_REQUEST:
				return createComponentRequest();
			case ICSpecXMLPackage.COMPONENT_SPEC:
				return createComponentSpec();
			case ICSpecXMLPackage.COMPONENT_SPEC_BASE:
				return createComponentSpecBase();
			case ICSpecXMLPackage.CSPEC_EXTENSION:
				return createCSpecExtension();
			case ICSpecXMLPackage.DEFINITIONS:
				return createDefinitions();
			case ICSpecXMLPackage.DEPENDENCIES_TYPE:
				return createDependenciesType();
			case ICSpecXMLPackage.DOCUMENT_ROOT:
				return createDocumentRoot();
			case ICSpecXMLPackage.GENERATOR:
				return createGenerator();
			case ICSpecXMLPackage.GENERATORS_TYPE:
				return createGeneratorsType();
			case ICSpecXMLPackage.GROUP:
				return createGroup();
			case ICSpecXMLPackage.GROUPS_TYPE:
				return createGroupsType();
			case ICSpecXMLPackage.IMPORT:
				return createImport();
			case ICSpecXMLPackage.PARAMETER_TYPE:
				return createParameterType();
			case ICSpecXMLPackage.PATH:
				return createPath();
			case ICSpecXMLPackage.PREREQUISITE:
				return createPrerequisite();
			case ICSpecXMLPackage.PREREQUISITES:
				return createPrerequisites();
			case ICSpecXMLPackage.PRODUCTS_TYPE:
				return createProductsType();
			case ICSpecXMLPackage.PROPERTIES:
				return createProperties();
			case ICSpecXMLPackage.REMOVE:
				return createRemove();
			case ICSpecXMLPackage.REMOVE_PATH:
				return createRemovePath();
			case ICSpecXMLPackage.REMOVE_PROPERTY:
				return createRemoveProperty();
			case ICSpecXMLPackage.RENAME:
				return createRename();
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
	public IAction createAction() {
		ActionImpl action = new ActionImpl();
		return action;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public IActionArtifact createActionArtifact() {
		ActionArtifactImpl actionArtifact = new ActionArtifactImpl();
		return actionArtifact;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public IActionsType createActionsType() {
		ActionsTypeImpl actionsType = new ActionsTypeImpl();
		return actionsType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public IAlterAction createAlterAction() {
		AlterActionImpl alterAction = new AlterActionImpl();
		return alterAction;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public IAlterActionsType createAlterActionsType() {
		AlterActionsTypeImpl alterActionsType = new AlterActionsTypeImpl();
		return alterActionsType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public IAlterArtifact createAlterArtifact() {
		AlterArtifactImpl alterArtifact = new AlterArtifactImpl();
		return alterArtifact;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public IAlterArtifactsType createAlterArtifactsType() {
		AlterArtifactsTypeImpl alterArtifactsType = new AlterArtifactsTypeImpl();
		return alterArtifactsType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public IAlterDependenciesType createAlterDependenciesType() {
		AlterDependenciesTypeImpl alterDependenciesType = new AlterDependenciesTypeImpl();
		return alterDependenciesType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public IAlterGroup createAlterGroup() {
		AlterGroupImpl alterGroup = new AlterGroupImpl();
		return alterGroup;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public IAlterGroupsType createAlterGroupsType() {
		AlterGroupsTypeImpl alterGroupsType = new AlterGroupsTypeImpl();
		return alterGroupsType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public IAlterPrerequisites createAlterPrerequisites() {
		AlterPrerequisitesImpl alterPrerequisites = new AlterPrerequisitesImpl();
		return alterPrerequisites;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public IAlterProductsType createAlterProductsType() {
		AlterProductsTypeImpl alterProductsType = new AlterProductsTypeImpl();
		return alterProductsType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public IAlterProperties createAlterProperties() {
		AlterPropertiesImpl alterProperties = new AlterPropertiesImpl();
		return alterProperties;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public IArtifact createArtifact() {
		ArtifactImpl artifact = new ArtifactImpl();
		return artifact;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public IArtifactsType createArtifactsType() {
		ArtifactsTypeImpl artifactsType = new ArtifactsTypeImpl();
		return artifactsType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public IAttribute createAttribute() {
		AttributeImpl attribute = new AttributeImpl();
		return attribute;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public IComponentRequest createComponentRequest() {
		ComponentRequestImpl componentRequest = new ComponentRequestImpl();
		return componentRequest;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public IComponentSpec createComponentSpec() {
		ComponentSpecImpl componentSpec = new ComponentSpecImpl();
		return componentSpec;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public IComponentSpecBase createComponentSpecBase() {
		ComponentSpecBaseImpl componentSpecBase = new ComponentSpecBaseImpl();
		return componentSpecBase;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public ICSpecExtension createCSpecExtension() {
		CSpecExtensionImpl cSpecExtension = new CSpecExtensionImpl();
		return cSpecExtension;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public IDefinitions createDefinitions() {
		DefinitionsImpl definitions = new DefinitionsImpl();
		return definitions;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public IDependenciesType createDependenciesType() {
		DependenciesTypeImpl dependenciesType = new DependenciesTypeImpl();
		return dependenciesType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public IDocumentRoot createDocumentRoot() {
		DocumentRootImpl documentRoot = new DocumentRootImpl();
		return documentRoot;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case ICSpecXMLPackage.UP_TO_DATE_POLICY:
				return createUpToDatePolicyFromString(eDataType, initialValue);
			case ICSpecXMLPackage.UP_TO_DATE_POLICY_OBJECT:
				return createUpToDatePolicyObjectFromString(eDataType, initialValue);
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
	public IGenerator createGenerator() {
		GeneratorImpl generator = new GeneratorImpl();
		return generator;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public IGeneratorsType createGeneratorsType() {
		GeneratorsTypeImpl generatorsType = new GeneratorsTypeImpl();
		return generatorsType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public IGroup createGroup() {
		GroupImpl group = new GroupImpl();
		return group;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public IGroupsType createGroupsType() {
		GroupsTypeImpl groupsType = new GroupsTypeImpl();
		return groupsType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public IImport createImport() {
		ImportImpl import_ = new ImportImpl();
		return import_;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public IParameterType createParameterType() {
		ParameterTypeImpl parameterType = new ParameterTypeImpl();
		return parameterType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public IPath createPath() {
		PathImpl path = new PathImpl();
		return path;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public IPrerequisite createPrerequisite() {
		PrerequisiteImpl prerequisite = new PrerequisiteImpl();
		return prerequisite;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public IPrerequisites createPrerequisites() {
		PrerequisitesImpl prerequisites = new PrerequisitesImpl();
		return prerequisites;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public IProductsType createProductsType() {
		ProductsTypeImpl productsType = new ProductsTypeImpl();
		return productsType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public IProperties createProperties() {
		PropertiesImpl properties = new PropertiesImpl();
		return properties;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public IRemove createRemove() {
		RemoveImpl remove = new RemoveImpl();
		return remove;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public IRemovePath createRemovePath() {
		RemovePathImpl removePath = new RemovePathImpl();
		return removePath;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public IRemoveProperty createRemoveProperty() {
		RemovePropertyImpl removeProperty = new RemovePropertyImpl();
		return removeProperty;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public IRename createRename() {
		RenameImpl rename = new RenameImpl();
		return rename;
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
	public UpToDatePolicy createUpToDatePolicyObjectFromString(EDataType eDataType, String initialValue) {
		return createUpToDatePolicyFromString(ICSpecXMLPackage.Literals.UP_TO_DATE_POLICY, initialValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public ICSpecXMLPackage getCSpecXMLPackage() {
		return (ICSpecXMLPackage) getEPackage();
	}

} // CSpecXMLFactoryImpl
