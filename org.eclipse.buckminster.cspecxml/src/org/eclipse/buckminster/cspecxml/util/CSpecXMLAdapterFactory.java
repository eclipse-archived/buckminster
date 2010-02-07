/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspecxml.util;

import org.eclipse.buckminster.cspecxml.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides
 * an adapter <code>createXXX</code> method for each class of the model. <!--
 * end-user-doc -->
 * 
 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage
 * @generated
 */
public class CSpecXMLAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected static ICSpecXMLPackage modelPackage;

	/**
	 * The switch that delegates to the <code>createXXX</code> methods. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected CSpecXMLSwitch<Adapter> modelSwitch = new CSpecXMLSwitch<Adapter>() {
		@Override
		public Adapter caseAction(IAction object) {
			return createActionAdapter();
		}

		@Override
		public Adapter caseActionArtifact(IActionArtifact object) {
			return createActionArtifactAdapter();
		}

		@Override
		public Adapter caseActionsType(IActionsType object) {
			return createActionsTypeAdapter();
		}

		@Override
		public Adapter caseAlterAction(IAlterAction object) {
			return createAlterActionAdapter();
		}

		@Override
		public Adapter caseAlterActionsType(IAlterActionsType object) {
			return createAlterActionsTypeAdapter();
		}

		@Override
		public Adapter caseAlterArtifact(IAlterArtifact object) {
			return createAlterArtifactAdapter();
		}

		@Override
		public Adapter caseAlterArtifactsType(IAlterArtifactsType object) {
			return createAlterArtifactsTypeAdapter();
		}

		@Override
		public Adapter caseAlterDependenciesType(IAlterDependenciesType object) {
			return createAlterDependenciesTypeAdapter();
		}

		@Override
		public Adapter caseAlterGroup(IAlterGroup object) {
			return createAlterGroupAdapter();
		}

		@Override
		public Adapter caseAlterGroupsType(IAlterGroupsType object) {
			return createAlterGroupsTypeAdapter();
		}

		@Override
		public Adapter caseAlterPrerequisites(IAlterPrerequisites object) {
			return createAlterPrerequisitesAdapter();
		}

		@Override
		public Adapter caseAlterProductsType(IAlterProductsType object) {
			return createAlterProductsTypeAdapter();
		}

		@Override
		public Adapter caseAlterProperties(IAlterProperties object) {
			return createAlterPropertiesAdapter();
		}

		@Override
		public Adapter caseArtifact(IArtifact object) {
			return createArtifactAdapter();
		}

		@Override
		public Adapter caseArtifactsType(IArtifactsType object) {
			return createArtifactsTypeAdapter();
		}

		@Override
		public Adapter caseAttribute(IAttribute object) {
			return createAttributeAdapter();
		}

		@Override
		public Adapter caseComponentRequest(IComponentRequest object) {
			return createComponentRequestAdapter();
		}

		@Override
		public Adapter caseComponentSpec(IComponentSpec object) {
			return createComponentSpecAdapter();
		}

		@Override
		public Adapter caseComponentSpecBase(IComponentSpecBase object) {
			return createComponentSpecBaseAdapter();
		}

		@Override
		public Adapter caseCSpecExtension(ICSpecExtension object) {
			return createCSpecExtensionAdapter();
		}

		@Override
		public Adapter caseDefinitions(IDefinitions object) {
			return createDefinitionsAdapter();
		}

		@Override
		public Adapter caseDependenciesType(IDependenciesType object) {
			return createDependenciesTypeAdapter();
		}

		@Override
		public Adapter caseDocumentRoot(IDocumentRoot object) {
			return createDocumentRootAdapter();
		}

		@Override
		public Adapter caseGenerator(IGenerator object) {
			return createGeneratorAdapter();
		}

		@Override
		public Adapter caseGeneratorsType(IGeneratorsType object) {
			return createGeneratorsTypeAdapter();
		}

		@Override
		public Adapter caseGroup(IGroup object) {
			return createGroupAdapter();
		}

		@Override
		public Adapter caseGroupsType(IGroupsType object) {
			return createGroupsTypeAdapter();
		}

		@Override
		public Adapter caseImport(IImport object) {
			return createImportAdapter();
		}

		@Override
		public Adapter caseParameterType(IParameterType object) {
			return createParameterTypeAdapter();
		}

		@Override
		public Adapter casePath(IPath object) {
			return createPathAdapter();
		}

		@Override
		public Adapter casePrerequisite(IPrerequisite object) {
			return createPrerequisiteAdapter();
		}

		@Override
		public Adapter casePrerequisites(IPrerequisites object) {
			return createPrerequisitesAdapter();
		}

		@Override
		public Adapter caseProductsType(IProductsType object) {
			return createProductsTypeAdapter();
		}

		@Override
		public Adapter caseProperties(IProperties object) {
			return createPropertiesAdapter();
		}

		@Override
		public Adapter caseRemove(IRemove object) {
			return createRemoveAdapter();
		}

		@Override
		public Adapter caseRemovePath(IRemovePath object) {
			return createRemovePathAdapter();
		}

		@Override
		public Adapter caseRemoveProperty(IRemoveProperty object) {
			return createRemovePropertyAdapter();
		}

		@Override
		public Adapter caseRename(IRename object) {
			return createRenameAdapter();
		}

		@Override
		public Adapter defaultCase(EObject object) {
			return createEObjectAdapter();
		}
	};

	/**
	 * Creates an instance of the adapter factory. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public CSpecXMLAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = ICSpecXMLPackage.eINSTANCE;
		}
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.cspecxml.IAction <em>Action</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we
	 * can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.cspecxml.IAction
	 * @generated
	 */
	public Adapter createActionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.cspecxml.IActionArtifact
	 * <em>Action Artifact</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.cspecxml.IActionArtifact
	 * @generated
	 */
	public Adapter createActionArtifactAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.cspecxml.IActionsType
	 * <em>Actions Type</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.cspecxml.IActionsType
	 * @generated
	 */
	public Adapter createActionsTypeAdapter() {
		return null;
	}

	/**
	 * Creates an adapter for the <code>target</code>. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @param target
	 *            the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject) target);
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.cspecxml.IAlterAction
	 * <em>Alter Action</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.cspecxml.IAlterAction
	 * @generated
	 */
	public Adapter createAlterActionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.cspecxml.IAlterActionsType
	 * <em>Alter Actions Type</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.cspecxml.IAlterActionsType
	 * @generated
	 */
	public Adapter createAlterActionsTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.cspecxml.IAlterArtifact
	 * <em>Alter Artifact</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.cspecxml.IAlterArtifact
	 * @generated
	 */
	public Adapter createAlterArtifactAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.cspecxml.IAlterArtifactsType
	 * <em>Alter Artifacts Type</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.cspecxml.IAlterArtifactsType
	 * @generated
	 */
	public Adapter createAlterArtifactsTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.cspecxml.IAlterDependenciesType
	 * <em>Alter Dependencies Type</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.cspecxml.IAlterDependenciesType
	 * @generated
	 */
	public Adapter createAlterDependenciesTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.cspecxml.IAlterGroup <em>Alter Group</em>}
	 * '. <!-- begin-user-doc --> This default implementation returns null so
	 * that we can easily ignore cases; it's useful to ignore a case when
	 * inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.cspecxml.IAlterGroup
	 * @generated
	 */
	public Adapter createAlterGroupAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.cspecxml.IAlterGroupsType
	 * <em>Alter Groups Type</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.cspecxml.IAlterGroupsType
	 * @generated
	 */
	public Adapter createAlterGroupsTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.cspecxml.IAlterPrerequisites
	 * <em>Alter Prerequisites</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.cspecxml.IAlterPrerequisites
	 * @generated
	 */
	public Adapter createAlterPrerequisitesAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.cspecxml.IAlterProductsType
	 * <em>Alter Products Type</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.cspecxml.IAlterProductsType
	 * @generated
	 */
	public Adapter createAlterProductsTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.cspecxml.IAlterProperties
	 * <em>Alter Properties</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.cspecxml.IAlterProperties
	 * @generated
	 */
	public Adapter createAlterPropertiesAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.cspecxml.IArtifact <em>Artifact</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that
	 * we can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.cspecxml.IArtifact
	 * @generated
	 */
	public Adapter createArtifactAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.cspecxml.IArtifactsType
	 * <em>Artifacts Type</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.cspecxml.IArtifactsType
	 * @generated
	 */
	public Adapter createArtifactsTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.cspecxml.IAttribute <em>Attribute</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that
	 * we can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.cspecxml.IAttribute
	 * @generated
	 */
	public Adapter createAttributeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.cspecxml.IComponentRequest
	 * <em>Component Request</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.cspecxml.IComponentRequest
	 * @generated
	 */
	public Adapter createComponentRequestAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.cspecxml.IComponentSpec
	 * <em>Component Spec</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.cspecxml.IComponentSpec
	 * @generated
	 */
	public Adapter createComponentSpecAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.cspecxml.IComponentSpecBase
	 * <em>Component Spec Base</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.cspecxml.IComponentSpecBase
	 * @generated
	 */
	public Adapter createComponentSpecBaseAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.cspecxml.ICSpecExtension
	 * <em>CSpec Extension</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.cspecxml.ICSpecExtension
	 * @generated
	 */
	public Adapter createCSpecExtensionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.cspecxml.IDefinitions
	 * <em>Definitions</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.cspecxml.IDefinitions
	 * @generated
	 */
	public Adapter createDefinitionsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.cspecxml.IDependenciesType
	 * <em>Dependencies Type</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.cspecxml.IDependenciesType
	 * @generated
	 */
	public Adapter createDependenciesTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.cspecxml.IDocumentRoot
	 * <em>Document Root</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.cspecxml.IDocumentRoot
	 * @generated
	 */
	public Adapter createDocumentRootAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case. <!-- begin-user-doc --> This
	 * default implementation returns null. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.cspecxml.IGenerator <em>Generator</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that
	 * we can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.cspecxml.IGenerator
	 * @generated
	 */
	public Adapter createGeneratorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.cspecxml.IGeneratorsType
	 * <em>Generators Type</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.cspecxml.IGeneratorsType
	 * @generated
	 */
	public Adapter createGeneratorsTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.cspecxml.IGroup <em>Group</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we
	 * can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.cspecxml.IGroup
	 * @generated
	 */
	public Adapter createGroupAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.cspecxml.IGroupsType <em>Groups Type</em>}
	 * '. <!-- begin-user-doc --> This default implementation returns null so
	 * that we can easily ignore cases; it's useful to ignore a case when
	 * inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.cspecxml.IGroupsType
	 * @generated
	 */
	public Adapter createGroupsTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.cspecxml.IImport <em>Import</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we
	 * can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.cspecxml.IImport
	 * @generated
	 */
	public Adapter createImportAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.cspecxml.IParameterType
	 * <em>Parameter Type</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.cspecxml.IParameterType
	 * @generated
	 */
	public Adapter createParameterTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.cspecxml.IPath <em>Path</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we
	 * can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.cspecxml.IPath
	 * @generated
	 */
	public Adapter createPathAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.cspecxml.IPrerequisite
	 * <em>Prerequisite</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.cspecxml.IPrerequisite
	 * @generated
	 */
	public Adapter createPrerequisiteAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.cspecxml.IPrerequisites
	 * <em>Prerequisites</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.cspecxml.IPrerequisites
	 * @generated
	 */
	public Adapter createPrerequisitesAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.cspecxml.IProductsType
	 * <em>Products Type</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.cspecxml.IProductsType
	 * @generated
	 */
	public Adapter createProductsTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.cspecxml.IProperties <em>Properties</em>}
	 * '. <!-- begin-user-doc --> This default implementation returns null so
	 * that we can easily ignore cases; it's useful to ignore a case when
	 * inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.cspecxml.IProperties
	 * @generated
	 */
	public Adapter createPropertiesAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.cspecxml.IRemove <em>Remove</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we
	 * can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.cspecxml.IRemove
	 * @generated
	 */
	public Adapter createRemoveAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.cspecxml.IRemovePath <em>Remove Path</em>}
	 * '. <!-- begin-user-doc --> This default implementation returns null so
	 * that we can easily ignore cases; it's useful to ignore a case when
	 * inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.cspecxml.IRemovePath
	 * @generated
	 */
	public Adapter createRemovePathAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.cspecxml.IRemoveProperty
	 * <em>Remove Property</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.cspecxml.IRemoveProperty
	 * @generated
	 */
	public Adapter createRemovePropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.cspecxml.IRename <em>Rename</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we
	 * can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.cspecxml.IRename
	 * @generated
	 */
	public Adapter createRenameAdapter() {
		return null;
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc --> This implementation returns <code>true</code> if
	 * the object is either the model's package or is an instance object of the
	 * model. <!-- end-user-doc -->
	 * 
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject) object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

} // CSpecXMLAdapterFactory
