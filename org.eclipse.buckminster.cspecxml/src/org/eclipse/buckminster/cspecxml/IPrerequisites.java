/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspecxml;

import org.eclipse.buckminster.model.common.Documentation;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Prerequisites</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.cspecxml.IPrerequisites#getDocumentation
 * <em>Documentation</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IPrerequisites#getGroup <em>Group
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IPrerequisites#getAttribute <em>
 * Attribute</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IPrerequisites#getAlias <em>Alias
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IPrerequisites#getRebase <em>
 * Rebase</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getPrerequisites()
 * @model extendedMetaData="name='Prerequisites' kind='elementOnly'"
 * @generated
 */
public interface IPrerequisites extends EObject {
	/**
	 * Returns the value of the '<em><b>Alias</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
	 * 
	 * The alias to pass along to the actor
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Alias</em>' attribute.
	 * @see #setAlias(String)
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getPrerequisites_Alias()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='alias'"
	 * @generated
	 */
	String getAlias();

	/**
	 * Returns the value of the '<em><b>Attribute</b></em>' containment
	 * reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.cspecxml.IPrerequisite}. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attribute</em>' containment reference list
	 * isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Attribute</em>' containment reference list.
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getPrerequisites_Attribute()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData=
	 *        "kind='element' name='attribute' namespace='##targetNamespace' group='#group:1'"
	 * @generated
	 */
	EList<IPrerequisite> getAttribute();

	/**
	 * Returns the value of the '<em><b>Documentation</b></em>' containment
	 * reference. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Documentation</em>' containment reference
	 * isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Documentation</em>' containment reference.
	 * @see #setDocumentation(Documentation)
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getPrerequisites_Documentation()
	 * @model containment="true" extendedMetaData=
	 *        "kind='element' name='documentation' namespace='##targetNamespace'"
	 * @generated
	 */
	Documentation getDocumentation();

	/**
	 * Returns the value of the '<em><b>Group</b></em>' attribute list. The list
	 * contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Group</em>' attribute list isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Group</em>' attribute list.
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getPrerequisites_Group()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry"
	 *        many="true" extendedMetaData="kind='group' name='group:1'"
	 * @generated
	 */
	FeatureMap getGroup();

	/**
	 * Returns the value of the '<em><b>Rebase</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
	 * 
	 * New base to use on all local paths where possible. Paths not parented by
	 * this base are left "as is", i.e. the path group they belong to will not
	 * change.
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Rebase</em>' attribute.
	 * @see #setRebase(String)
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getPrerequisites_Rebase()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='rebase'"
	 * @generated
	 */
	String getRebase();

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.cspecxml.IPrerequisites#getAlias
	 * <em>Alias</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Alias</em>' attribute.
	 * @see #getAlias()
	 * @generated
	 */
	void setAlias(String value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.cspecxml.IPrerequisites#getDocumentation
	 * <em>Documentation</em>}' containment reference. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Documentation</em>' containment
	 *            reference.
	 * @see #getDocumentation()
	 * @generated
	 */
	void setDocumentation(Documentation value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.cspecxml.IPrerequisites#getRebase
	 * <em>Rebase</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @param value
	 *            the new value of the '<em>Rebase</em>' attribute.
	 * @see #getRebase()
	 * @generated
	 */
	void setRebase(String value);

} // IPrerequisites
