/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspecxml;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>CSpec Extension</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.cspecxml.ICSpecExtension#getGroup1 <em>
 * Group1</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.ICSpecExtension#getAlterActions
 * <em>Alter Actions</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.cspecxml.ICSpecExtension#getAlterArtifacts
 * <em>Alter Artifacts</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.cspecxml.ICSpecExtension#getAlterDependencies
 * <em>Alter Dependencies</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.ICSpecExtension#getAlterGroups
 * <em>Alter Groups</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getCSpecExtension()
 * @model extendedMetaData="name='CSpecExtension' kind='elementOnly'"
 * @generated
 */
public interface ICSpecExtension extends IComponentSpecBase {
	/**
	 * Returns the value of the '<em><b>Alter Actions</b></em>' containment
	 * reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.cspecxml.IAlterActionsType}. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Alter Actions</em>' containment reference list
	 * isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Alter Actions</em>' containment reference
	 *         list.
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getCSpecExtension_AlterActions()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='alterActions' namespace='##targetNamespace' group='#group:14'"
	 * @generated
	 */
	EList<IAlterActionsType> getAlterActions();

	/**
	 * Returns the value of the '<em><b>Alter Artifacts</b></em>' containment
	 * reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.cspecxml.IAlterArtifactsType}. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Alter Artifacts</em>' containment reference
	 * list isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Alter Artifacts</em>' containment reference
	 *         list.
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getCSpecExtension_AlterArtifacts()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='alterArtifacts' namespace='##targetNamespace' group='#group:14'"
	 * @generated
	 */
	EList<IAlterArtifactsType> getAlterArtifacts();

	/**
	 * Returns the value of the '<em><b>Alter Dependencies</b></em>' containment
	 * reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.cspecxml.IAlterDependenciesType}. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Alter Dependencies</em>' containment reference
	 * list isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Alter Dependencies</em>' containment
	 *         reference list.
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getCSpecExtension_AlterDependencies()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='alterDependencies' namespace='##targetNamespace' group='#group:14'"
	 * @generated
	 */
	EList<IAlterDependenciesType> getAlterDependencies();

	/**
	 * Returns the value of the '<em><b>Alter Groups</b></em>' containment
	 * reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.cspecxml.IAlterGroupsType}. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Alter Groups</em>' containment reference list
	 * isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Alter Groups</em>' containment reference
	 *         list.
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getCSpecExtension_AlterGroups()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='alterGroups' namespace='##targetNamespace' group='#group:14'"
	 * @generated
	 */
	EList<IAlterGroupsType> getAlterGroups();

	/**
	 * Returns the value of the '<em><b>Group1</b></em>' attribute list. The
	 * list contents are of type
	 * {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}. <!-- begin-user-doc
	 * -->
	 * <p>
	 * If the meaning of the '<em>Group1</em>' attribute list isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Group1</em>' attribute list.
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getCSpecExtension_Group1()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry"
	 *        many="true" extendedMetaData="kind='group' name='group:14'"
	 * @generated
	 */
	FeatureMap getGroup1();

} // ICSpecExtension
