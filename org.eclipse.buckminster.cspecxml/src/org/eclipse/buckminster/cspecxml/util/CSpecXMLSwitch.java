/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspecxml.util;

import java.util.List;

import org.eclipse.buckminster.cspecxml.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance
 * hierarchy. It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object and proceeding up the
 * inheritance hierarchy until a non-null result is returned, which is the
 * result of the switch. <!-- end-user-doc -->
 * 
 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage
 * @generated
 */
public class CSpecXMLSwitch<T> {
	/**
	 * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected static ICSpecXMLPackage modelPackage;

	/**
	 * Creates an instance of the switch. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public CSpecXMLSwitch() {
		if (modelPackage == null) {
			modelPackage = ICSpecXMLPackage.eINSTANCE;
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Action</em>'. <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Action</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAction(IAction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Action Artifact</em>'. <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Action Artifact</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseActionArtifact(IActionArtifact object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Actions Type</em>'. <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Actions Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseActionsType(IActionsType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Alter Action</em>'. <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Alter Action</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAlterAction(IAlterAction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Alter Actions Type</em>'. <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Alter Actions Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAlterActionsType(IAlterActionsType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Alter Artifact</em>'. <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Alter Artifact</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAlterArtifact(IAlterArtifact object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Alter Artifacts Type</em>'. <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate
	 * the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Alter Artifacts Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAlterArtifactsType(IAlterArtifactsType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Alter Dependencies Type</em>'. <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate
	 * the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Alter Dependencies Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAlterDependenciesType(IAlterDependenciesType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Alter Group</em>'. <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Alter Group</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAlterGroup(IAlterGroup object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Alter Groups Type</em>'. <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Alter Groups Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAlterGroupsType(IAlterGroupsType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Alter Prerequisites</em>'. <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate
	 * the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Alter Prerequisites</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAlterPrerequisites(IAlterPrerequisites object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Alter Products Type</em>'. <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate
	 * the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Alter Products Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAlterProductsType(IAlterProductsType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Alter Properties</em>'. <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Alter Properties</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAlterProperties(IAlterProperties object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Artifact</em>'. <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Artifact</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseArtifact(IArtifact object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Artifacts Type</em>'. <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Artifacts Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseArtifactsType(IArtifactsType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Attribute</em>'. <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Attribute</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAttribute(IAttribute object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Component Request</em>'. <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Component Request</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseComponentRequest(IComponentRequest object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Component Spec</em>'. <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Component Spec</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseComponentSpec(IComponentSpec object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Component Spec Base</em>'. <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate
	 * the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Component Spec Base</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseComponentSpecBase(IComponentSpecBase object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>CSpec Extension</em>'. <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>CSpec Extension</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCSpecExtension(ICSpecExtension object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Definitions</em>'. <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Definitions</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDefinitions(IDefinitions object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Dependencies Type</em>'. <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Dependencies Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDependenciesType(IDependenciesType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Document Root</em>'. <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Document Root</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDocumentRoot(IDocumentRoot object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Generator</em>'. <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Generator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGenerator(IGenerator object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Generators Type</em>'. <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Generators Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGeneratorsType(IGeneratorsType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Group</em>'. <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Group</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGroup(IGroup object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Groups Type</em>'. <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Groups Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGroupsType(IGroupsType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Import</em>'. <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Import</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseImport(IImport object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Parameter Type</em>'. <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Parameter Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseParameterType(IParameterType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Path</em>'. <!-- begin-user-doc --> This implementation returns null;
	 * returning a non-null result will terminate the switch. <!-- end-user-doc
	 * -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Path</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePath(IPath object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Prerequisite</em>'. <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Prerequisite</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePrerequisite(IPrerequisite object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Prerequisites</em>'. <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Prerequisites</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePrerequisites(IPrerequisites object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Products Type</em>'. <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Products Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProductsType(IProductsType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Properties</em>'. <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Properties</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProperties(IProperties object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Remove</em>'. <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Remove</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRemove(IRemove object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Remove Path</em>'. <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Remove Path</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRemovePath(IRemovePath object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Remove Property</em>'. <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Remove Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRemoveProperty(IRemoveProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Rename</em>'. <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Rename</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRename(IRename object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>EObject</em>'. <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch, but this is
	 * the last case anyway. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	public T defaultCase(EObject object) {
		return null;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns
	 * a non null result; it yields that result. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the first non-null result returned by a <code>caseXXX</code>
	 *         call.
	 * @generated
	 */
	public T doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns
	 * a non null result; it yields that result. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the first non-null result returned by a <code>caseXXX</code>
	 *         call.
	 * @generated
	 */
	protected T doSwitch(EClass theEClass, EObject theEObject) {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		} else {
			List<EClass> eSuperTypes = theEClass.getESuperTypes();
			return eSuperTypes.isEmpty() ? defaultCase(theEObject) : doSwitch(eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns
	 * a non null result; it yields that result. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the first non-null result returned by a <code>caseXXX</code>
	 *         call.
	 * @generated
	 */
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case ICSpecXMLPackage.ACTION: {
				IAction action = (IAction) theEObject;
				T result = caseAction(action);
				if (result == null)
					result = caseAttribute(action);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case ICSpecXMLPackage.ACTION_ARTIFACT: {
				IActionArtifact actionArtifact = (IActionArtifact) theEObject;
				T result = caseActionArtifact(actionArtifact);
				if (result == null)
					result = caseArtifact(actionArtifact);
				if (result == null)
					result = caseAttribute(actionArtifact);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case ICSpecXMLPackage.ACTIONS_TYPE: {
				IActionsType actionsType = (IActionsType) theEObject;
				T result = caseActionsType(actionsType);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case ICSpecXMLPackage.ALTER_ACTION: {
				IAlterAction alterAction = (IAlterAction) theEObject;
				T result = caseAlterAction(alterAction);
				if (result == null)
					result = caseAction(alterAction);
				if (result == null)
					result = caseAttribute(alterAction);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case ICSpecXMLPackage.ALTER_ACTIONS_TYPE: {
				IAlterActionsType alterActionsType = (IAlterActionsType) theEObject;
				T result = caseAlterActionsType(alterActionsType);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case ICSpecXMLPackage.ALTER_ARTIFACT: {
				IAlterArtifact alterArtifact = (IAlterArtifact) theEObject;
				T result = caseAlterArtifact(alterArtifact);
				if (result == null)
					result = caseArtifact(alterArtifact);
				if (result == null)
					result = caseAttribute(alterArtifact);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case ICSpecXMLPackage.ALTER_ARTIFACTS_TYPE: {
				IAlterArtifactsType alterArtifactsType = (IAlterArtifactsType) theEObject;
				T result = caseAlterArtifactsType(alterArtifactsType);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case ICSpecXMLPackage.ALTER_DEPENDENCIES_TYPE: {
				IAlterDependenciesType alterDependenciesType = (IAlterDependenciesType) theEObject;
				T result = caseAlterDependenciesType(alterDependenciesType);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case ICSpecXMLPackage.ALTER_GROUP: {
				IAlterGroup alterGroup = (IAlterGroup) theEObject;
				T result = caseAlterGroup(alterGroup);
				if (result == null)
					result = caseGroup(alterGroup);
				if (result == null)
					result = caseAttribute(alterGroup);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case ICSpecXMLPackage.ALTER_GROUPS_TYPE: {
				IAlterGroupsType alterGroupsType = (IAlterGroupsType) theEObject;
				T result = caseAlterGroupsType(alterGroupsType);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case ICSpecXMLPackage.ALTER_PREREQUISITES: {
				IAlterPrerequisites alterPrerequisites = (IAlterPrerequisites) theEObject;
				T result = caseAlterPrerequisites(alterPrerequisites);
				if (result == null)
					result = casePrerequisites(alterPrerequisites);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case ICSpecXMLPackage.ALTER_PRODUCTS_TYPE: {
				IAlterProductsType alterProductsType = (IAlterProductsType) theEObject;
				T result = caseAlterProductsType(alterProductsType);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case ICSpecXMLPackage.ALTER_PROPERTIES: {
				IAlterProperties alterProperties = (IAlterProperties) theEObject;
				T result = caseAlterProperties(alterProperties);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case ICSpecXMLPackage.ARTIFACT: {
				IArtifact artifact = (IArtifact) theEObject;
				T result = caseArtifact(artifact);
				if (result == null)
					result = caseAttribute(artifact);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case ICSpecXMLPackage.ARTIFACTS_TYPE: {
				IArtifactsType artifactsType = (IArtifactsType) theEObject;
				T result = caseArtifactsType(artifactsType);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case ICSpecXMLPackage.ATTRIBUTE: {
				IAttribute attribute = (IAttribute) theEObject;
				T result = caseAttribute(attribute);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case ICSpecXMLPackage.COMPONENT_REQUEST: {
				IComponentRequest componentRequest = (IComponentRequest) theEObject;
				T result = caseComponentRequest(componentRequest);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case ICSpecXMLPackage.COMPONENT_SPEC: {
				IComponentSpec componentSpec = (IComponentSpec) theEObject;
				T result = caseComponentSpec(componentSpec);
				if (result == null)
					result = caseComponentSpecBase(componentSpec);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case ICSpecXMLPackage.COMPONENT_SPEC_BASE: {
				IComponentSpecBase componentSpecBase = (IComponentSpecBase) theEObject;
				T result = caseComponentSpecBase(componentSpecBase);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case ICSpecXMLPackage.CSPEC_EXTENSION: {
				ICSpecExtension cSpecExtension = (ICSpecExtension) theEObject;
				T result = caseCSpecExtension(cSpecExtension);
				if (result == null)
					result = caseComponentSpecBase(cSpecExtension);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case ICSpecXMLPackage.DEFINITIONS: {
				IDefinitions definitions = (IDefinitions) theEObject;
				T result = caseDefinitions(definitions);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case ICSpecXMLPackage.DEPENDENCIES_TYPE: {
				IDependenciesType dependenciesType = (IDependenciesType) theEObject;
				T result = caseDependenciesType(dependenciesType);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case ICSpecXMLPackage.DOCUMENT_ROOT: {
				IDocumentRoot documentRoot = (IDocumentRoot) theEObject;
				T result = caseDocumentRoot(documentRoot);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case ICSpecXMLPackage.GENERATOR: {
				IGenerator generator = (IGenerator) theEObject;
				T result = caseGenerator(generator);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case ICSpecXMLPackage.GENERATORS_TYPE: {
				IGeneratorsType generatorsType = (IGeneratorsType) theEObject;
				T result = caseGeneratorsType(generatorsType);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case ICSpecXMLPackage.GROUP: {
				IGroup group = (IGroup) theEObject;
				T result = caseGroup(group);
				if (result == null)
					result = caseAttribute(group);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case ICSpecXMLPackage.GROUPS_TYPE: {
				IGroupsType groupsType = (IGroupsType) theEObject;
				T result = caseGroupsType(groupsType);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case ICSpecXMLPackage.IMPORT: {
				IImport import_ = (IImport) theEObject;
				T result = caseImport(import_);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case ICSpecXMLPackage.PARAMETER_TYPE: {
				IParameterType parameterType = (IParameterType) theEObject;
				T result = caseParameterType(parameterType);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case ICSpecXMLPackage.PATH: {
				IPath path = (IPath) theEObject;
				T result = casePath(path);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case ICSpecXMLPackage.PREREQUISITE: {
				IPrerequisite prerequisite = (IPrerequisite) theEObject;
				T result = casePrerequisite(prerequisite);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case ICSpecXMLPackage.PREREQUISITES: {
				IPrerequisites prerequisites = (IPrerequisites) theEObject;
				T result = casePrerequisites(prerequisites);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case ICSpecXMLPackage.PRODUCTS_TYPE: {
				IProductsType productsType = (IProductsType) theEObject;
				T result = caseProductsType(productsType);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case ICSpecXMLPackage.PROPERTIES: {
				IProperties properties = (IProperties) theEObject;
				T result = caseProperties(properties);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case ICSpecXMLPackage.REMOVE: {
				IRemove remove = (IRemove) theEObject;
				T result = caseRemove(remove);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case ICSpecXMLPackage.REMOVE_PATH: {
				IRemovePath removePath = (IRemovePath) theEObject;
				T result = caseRemovePath(removePath);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case ICSpecXMLPackage.REMOVE_PROPERTY: {
				IRemoveProperty removeProperty = (IRemoveProperty) theEObject;
				T result = caseRemoveProperty(removeProperty);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case ICSpecXMLPackage.RENAME: {
				IRename rename = (IRename) theEObject;
				T result = caseRename(rename);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			default:
				return defaultCase(theEObject);
		}
	}

} // CSpecXMLSwitch
