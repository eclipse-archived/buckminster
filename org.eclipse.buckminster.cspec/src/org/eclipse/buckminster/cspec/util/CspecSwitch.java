/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspec.util;

import java.util.List;

import org.eclipse.buckminster.cspec.*;

import org.eclipse.buckminster.model.common.ComponentIdentifier;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.equinox.p2.metadata.IVersionedId;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance
 * hierarchy. It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object and proceeding up the
 * inheritance hierarchy until a non-null result is returned, which is the
 * result of the switch. <!-- end-user-doc -->
 * 
 * @see org.eclipse.buckminster.cspec.CspecPackage
 * @generated
 */
public class CspecSwitch<T1> {
	/**
	 * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected static CspecPackage modelPackage;

	/**
	 * Creates an instance of the switch. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public CspecSwitch() {
		if (modelPackage == null) {
			modelPackage = CspecPackage.eINSTANCE;
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
	public T1 caseAction(Action object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Action Attribute</em>'. <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Action Attribute</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseActionAttribute(ActionAttribute object) {
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
	public T1 caseAlterAction(AlterAction object) {
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
	public T1 caseAlterArtifact(AlterArtifact object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Alter Attribute</em>'. <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Alter Attribute</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseAlterAttribute(AlterAttribute object) {
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
	public T1 caseAlterGroup(AlterGroup object) {
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
	public T1 caseArtifact(Artifact object) {
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
	public T1 caseAttribute(Attribute object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Comparable</em>'. <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Comparable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T> T1 caseComparable(Comparable<T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Component Identifier</em>'. <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate
	 * the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Component Identifier</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseComponentIdentifier(ComponentIdentifier object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>CSpec</em>'. <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>CSpec</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseCSpec(CSpec object) {
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
	public T1 caseCSpecExtension(CSpecExtension object) {
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
	public T1 caseGenerator(Generator object) {
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
	public T1 caseGroup(Group object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>IContext</em>'. <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>IContext</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseIContext(IContext object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>IVersioned Id</em>'. <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>IVersioned Id</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseIVersionedId(IVersionedId object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Path Group</em>'. <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Path Group</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 casePathGroup(PathGroup object) {
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
	public T1 casePrerequisite(Prerequisite object) {
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
	public T1 caseRemove(Remove object) {
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
	public T1 caseRename(Rename object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Self Artifact</em>'. <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Self Artifact</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseSelfArtifact(SelfArtifact object) {
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
	public T1 defaultCase(EObject object) {
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
	public T1 doSwitch(EObject theEObject) {
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
	protected T1 doSwitch(EClass theEClass, EObject theEObject) {
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
	protected T1 doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case CspecPackage.CSPEC: {
				CSpec cSpec = (CSpec) theEObject;
				T1 result = caseCSpec(cSpec);
				if (result == null)
					result = caseComponentIdentifier(cSpec);
				if (result == null)
					result = caseIVersionedId(cSpec);
				if (result == null)
					result = caseComparable(cSpec);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case CspecPackage.ATTRIBUTE: {
				Attribute attribute = (Attribute) theEObject;
				T1 result = caseAttribute(attribute);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case CspecPackage.GROUP: {
				Group group = (Group) theEObject;
				T1 result = caseGroup(group);
				if (result == null)
					result = caseAttribute(group);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case CspecPackage.PREREQUISITE: {
				Prerequisite prerequisite = (Prerequisite) theEObject;
				T1 result = casePrerequisite(prerequisite);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case CspecPackage.ACTION: {
				Action action = (Action) theEObject;
				T1 result = caseAction(action);
				if (result == null)
					result = caseGroup(action);
				if (result == null)
					result = caseAttribute(action);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case CspecPackage.ARTIFACT: {
				Artifact artifact = (Artifact) theEObject;
				T1 result = caseArtifact(artifact);
				if (result == null)
					result = caseAttribute(artifact);
				if (result == null)
					result = casePathGroup(artifact);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case CspecPackage.ACTION_ATTRIBUTE: {
				ActionAttribute actionAttribute = (ActionAttribute) theEObject;
				T1 result = caseActionAttribute(actionAttribute);
				if (result == null)
					result = caseArtifact(actionAttribute);
				if (result == null)
					result = caseAttribute(actionAttribute);
				if (result == null)
					result = casePathGroup(actionAttribute);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case CspecPackage.PATH_GROUP: {
				PathGroup pathGroup = (PathGroup) theEObject;
				T1 result = casePathGroup(pathGroup);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case CspecPackage.GENERATOR: {
				Generator generator = (Generator) theEObject;
				T1 result = caseGenerator(generator);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case CspecPackage.ALTER_ATTRIBUTE: {
				AlterAttribute alterAttribute = (AlterAttribute) theEObject;
				T1 result = caseAlterAttribute(alterAttribute);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case CspecPackage.ALTER_ARTIFACT: {
				AlterArtifact alterArtifact = (AlterArtifact) theEObject;
				T1 result = caseAlterArtifact(alterArtifact);
				if (result == null)
					result = caseArtifact(alterArtifact);
				if (result == null)
					result = caseAlterAttribute(alterArtifact);
				if (result == null)
					result = caseAttribute(alterArtifact);
				if (result == null)
					result = casePathGroup(alterArtifact);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case CspecPackage.ALTER_GROUP: {
				AlterGroup alterGroup = (AlterGroup) theEObject;
				T1 result = caseAlterGroup(alterGroup);
				if (result == null)
					result = caseGroup(alterGroup);
				if (result == null)
					result = caseAlterAttribute(alterGroup);
				if (result == null)
					result = caseAttribute(alterGroup);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case CspecPackage.ALTER_ACTION: {
				AlterAction alterAction = (AlterAction) theEObject;
				T1 result = caseAlterAction(alterAction);
				if (result == null)
					result = caseAction(alterAction);
				if (result == null)
					result = caseAlterGroup(alterAction);
				if (result == null)
					result = caseGroup(alterAction);
				if (result == null)
					result = caseAlterAttribute(alterAction);
				if (result == null)
					result = caseAttribute(alterAction);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case CspecPackage.RENAME: {
				Rename rename = (Rename) theEObject;
				T1 result = caseRename(rename);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case CspecPackage.REMOVE: {
				Remove remove = (Remove) theEObject;
				T1 result = caseRemove(remove);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case CspecPackage.CSPEC_EXTENSION: {
				CSpecExtension cSpecExtension = (CSpecExtension) theEObject;
				T1 result = caseCSpecExtension(cSpecExtension);
				if (result == null)
					result = caseCSpec(cSpecExtension);
				if (result == null)
					result = caseComponentIdentifier(cSpecExtension);
				if (result == null)
					result = caseIVersionedId(cSpecExtension);
				if (result == null)
					result = caseComparable(cSpecExtension);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case CspecPackage.ICONTEXT: {
				IContext iContext = (IContext) theEObject;
				T1 result = caseIContext(iContext);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case CspecPackage.SELF_ARTIFACT: {
				SelfArtifact selfArtifact = (SelfArtifact) theEObject;
				T1 result = caseSelfArtifact(selfArtifact);
				if (result == null)
					result = caseArtifact(selfArtifact);
				if (result == null)
					result = caseAttribute(selfArtifact);
				if (result == null)
					result = casePathGroup(selfArtifact);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			default:
				return defaultCase(theEObject);
		}
	}

} // CspecSwitch
