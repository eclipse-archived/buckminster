/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspecxml;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Alter Dependencies Type</b></em>'. <!--
 * end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.cspecxml.IAlterDependenciesType#getGroup <em>Group</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IAlterDependenciesType#getDependency <em>Dependency</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IAlterDependenciesType#getRemove <em>Remove</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getAlterDependenciesType()
 * @model extendedMetaData="name='alterDependencies_._type' kind='elementOnly'"
 * @generated
 */
public interface IAlterDependenciesType extends EObject
{
	/**
	 * Returns the value of the '<em><b>Dependency</b></em>' containment reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.cspecxml.IComponentRequest}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dependency</em>' containment reference list isn't clear, there really should be more
	 * of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Dependency</em>' containment reference list.
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getAlterDependenciesType_Dependency()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='dependency' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<IComponentRequest> getDependency();

	/**
	 * Returns the value of the '<em><b>Group</b></em>' attribute list. The list contents are of type
	 * {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Group</em>' attribute list isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Group</em>' attribute list.
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getAlterDependenciesType_Group()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='group:0'"
	 * @generated
	 */
	FeatureMap getGroup();

	/**
	 * Returns the value of the '<em><b>Remove</b></em>' containment reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.cspecxml.IRemove}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Remove</em>' containment reference list isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Remove</em>' containment reference list.
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getAlterDependenciesType_Remove()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='remove' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<IRemove> getRemove();

} // IAlterDependenciesType
