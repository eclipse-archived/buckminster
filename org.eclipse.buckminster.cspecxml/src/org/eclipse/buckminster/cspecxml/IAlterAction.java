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
 * <em><b>Alter Action</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.cspecxml.IAlterAction#getGroup1 <em>Group1
 * </em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.cspecxml.IAlterAction#getAlterPrerequisites
 * <em>Alter Prerequisites</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.cspecxml.IAlterAction#getAlterActorProperties
 * <em>Alter Actor Properties</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IAlterAction#getAlterProperties
 * <em>Alter Properties</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IAlterAction#getAlterProducts
 * <em>Alter Products</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getAlterAction()
 * @model extendedMetaData="name='AlterAction' kind='elementOnly'"
 * @generated
 */
public interface IAlterAction extends IAction {
	/**
	 * Returns the value of the '<em><b>Alter Actor Properties</b></em>'
	 * containment reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.cspecxml.IAlterProperties}. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Alter Actor Properties</em>' containment
	 * reference list isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Alter Actor Properties</em>' containment
	 *         reference list.
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getAlterAction_AlterActorProperties()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='alterActorProperties' namespace='##targetNamespace' group='#group:16'"
	 * @generated
	 */
	EList<IAlterProperties> getAlterActorProperties();

	/**
	 * Returns the value of the '<em><b>Alter Prerequisites</b></em>'
	 * containment reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.cspecxml.IAlterPrerequisites}. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Alter Prerequisites</em>' containment
	 * reference list isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Alter Prerequisites</em>' containment
	 *         reference list.
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getAlterAction_AlterPrerequisites()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='alterPrerequisites' namespace='##targetNamespace' group='#group:16'"
	 * @generated
	 */
	EList<IAlterPrerequisites> getAlterPrerequisites();

	/**
	 * Returns the value of the '<em><b>Alter Products</b></em>' containment
	 * reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.cspecxml.IAlterProductsType}. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Alter Products</em>' containment reference
	 * list isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Alter Products</em>' containment reference
	 *         list.
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getAlterAction_AlterProducts()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='alterProducts' namespace='##targetNamespace' group='#group:16'"
	 * @generated
	 */
	EList<IAlterProductsType> getAlterProducts();

	/**
	 * Returns the value of the '<em><b>Alter Properties</b></em>' containment
	 * reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.cspecxml.IAlterProperties}. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Alter Properties</em>' containment reference
	 * list isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Alter Properties</em>' containment
	 *         reference list.
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getAlterAction_AlterProperties()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='alterProperties' namespace='##targetNamespace' group='#group:16'"
	 * @generated
	 */
	EList<IAlterProperties> getAlterProperties();

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
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getAlterAction_Group1()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry"
	 *        many="true" extendedMetaData="kind='group' name='group:16'"
	 * @generated
	 */
	FeatureMap getGroup1();

} // IAlterAction
