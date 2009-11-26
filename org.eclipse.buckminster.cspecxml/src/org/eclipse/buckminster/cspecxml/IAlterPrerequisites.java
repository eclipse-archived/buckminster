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
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Alter Prerequisites</b></em>'. <!-- end-user-doc
 * -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.cspecxml.IAlterPrerequisites#getGroup1 <em>Group1</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IAlterPrerequisites#getAlterAttribute <em>Alter Attribute</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IAlterPrerequisites#getRemove <em>Remove</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getAlterPrerequisites()
 * @model extendedMetaData="name='AlterPrerequisites' kind='elementOnly'"
 * @generated
 */
public interface IAlterPrerequisites extends IPrerequisites
{
	/**
	 * Returns the value of the '<em><b>Alter Attribute</b></em>' containment reference list. The list contents are of
	 * type {@link org.eclipse.buckminster.cspecxml.IPrerequisite}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Alter Attribute</em>' containment reference list isn't clear, there really should be
	 * more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Alter Attribute</em>' containment reference list.
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getAlterPrerequisites_AlterAttribute()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='alterAttribute' namespace='##targetNamespace' group='#group:6'"
	 * @generated
	 */
	EList<IPrerequisite> getAlterAttribute();

	/**
	 * Returns the value of the '<em><b>Group1</b></em>' attribute list. The list contents are of type
	 * {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Group1</em>' attribute list isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Group1</em>' attribute list.
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getAlterPrerequisites_Group1()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='group:6'"
	 * @generated
	 */
	FeatureMap getGroup1();

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
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getAlterPrerequisites_Remove()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='remove' namespace='##targetNamespace' group='#group:6'"
	 * @generated
	 */
	EList<IRemove> getRemove();

} // IAlterPrerequisites
