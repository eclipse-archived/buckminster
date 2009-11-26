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
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Alter Groups Type</b></em>'. <!-- end-user-doc
 * -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.cspecxml.IAlterGroupsType#getGroup <em>Group</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IAlterGroupsType#getPublic <em>Public</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IAlterGroupsType#getPrivate <em>Private</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IAlterGroupsType#getRemove <em>Remove</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IAlterGroupsType#getRename <em>Rename</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getAlterGroupsType()
 * @model extendedMetaData="name='alterGroups_._type' kind='elementOnly'"
 * @generated
 */
public interface IAlterGroupsType extends EObject
{
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
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getAlterGroupsType_Group()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='group:0'"
	 * @generated
	 */
	FeatureMap getGroup();

	/**
	 * Returns the value of the '<em><b>Private</b></em>' containment reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.cspecxml.IAlterGroup}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Private</em>' containment reference list isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Private</em>' containment reference list.
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getAlterGroupsType_Private()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='private' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<IAlterGroup> getPrivate();

	/**
	 * Returns the value of the '<em><b>Public</b></em>' containment reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.cspecxml.IAlterGroup}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Public</em>' containment reference list isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Public</em>' containment reference list.
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getAlterGroupsType_Public()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='public' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<IAlterGroup> getPublic();

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
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getAlterGroupsType_Remove()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='remove' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<IRemove> getRemove();

	/**
	 * Returns the value of the '<em><b>Rename</b></em>' containment reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.cspecxml.IRename}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rename</em>' containment reference list isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Rename</em>' containment reference list.
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getAlterGroupsType_Rename()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='rename' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<IRename> getRename();

} // IAlterGroupsType
