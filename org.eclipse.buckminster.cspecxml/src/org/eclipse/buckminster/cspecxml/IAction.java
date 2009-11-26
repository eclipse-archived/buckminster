/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspecxml;

import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Action</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.cspecxml.IAction#getGroup <em>Group</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IAction#getDefinitions <em>Definitions</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IAction#getActorProperties <em>Actor Properties</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IAction#getProperties <em>Properties</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IAction#getPrerequisites <em>Prerequisites</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IAction#getProducts <em>Products</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IAction#getActor <em>Actor</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IAction#isAlways <em>Always</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IAction#isAssignConsoleSupport <em>Assign Console Support</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IAction#isEnabled <em>Enabled</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IAction#getFilter <em>Filter</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getAction()
 * @model extendedMetaData="name='Action' kind='elementOnly'"
 * @generated
 */
public interface IAction extends IAttribute
{
	/**
	 * Returns the value of the '<em><b>Actor</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
	 * begin-model-doc -->
	 * 
	 * The id of the actor. Lack of this attribute indicates a predefine internal action
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Actor</em>' attribute.
	 * @see #setActor(String)
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getAction_Actor()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" extendedMetaData="kind='attribute' name='actor'"
	 * @generated
	 */
	String getActor();

	/**
	 * Returns the value of the '<em><b>Actor Properties</b></em>' containment reference list. The list contents are of
	 * type {@link org.eclipse.buckminster.cspecxml.IProperties}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Actor Properties</em>' containment reference list isn't clear, there really should be
	 * more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Actor Properties</em>' containment reference list.
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getAction_ActorProperties()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='actorProperties' namespace='##targetNamespace' group='#group:4'"
	 * @generated
	 */
	EList<IProperties> getActorProperties();

	/**
	 * Returns the value of the '<em><b>Definitions</b></em>' containment reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.cspecxml.IDefinitions}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Definitions</em>' containment reference list isn't clear, there really should be more
	 * of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Definitions</em>' containment reference list.
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getAction_Definitions()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='definitions' namespace='##targetNamespace' group='#group:4'"
	 * @generated
	 */
	EList<IDefinitions> getDefinitions();

	/**
	 * Returns the value of the '<em><b>Filter</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
	 * begin-model-doc -->
	 * 
	 * LDAP filter controlling if this action is enabled or disabled
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Filter</em>' attribute.
	 * @see #setFilter(Filter)
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getAction_Filter()
	 * @model dataType="org.eclipse.buckminster.model.common.Filter" extendedMetaData="kind='attribute' name='filter'"
	 * @generated
	 */
	Filter getFilter();

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
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getAction_Group()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='group:4'"
	 * @generated
	 */
	FeatureMap getGroup();

	/**
	 * Returns the value of the '<em><b>Prerequisites</b></em>' containment reference list. The list contents are of
	 * type {@link org.eclipse.buckminster.cspecxml.IPrerequisites}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Prerequisites</em>' containment reference list isn't clear, there really should be
	 * more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Prerequisites</em>' containment reference list.
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getAction_Prerequisites()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='prerequisites' namespace='##targetNamespace' group='#group:4'"
	 * @generated
	 */
	EList<IPrerequisites> getPrerequisites();

	/**
	 * Returns the value of the '<em><b>Products</b></em>' containment reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.cspecxml.IProductsType}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Products</em>' containment reference list isn't clear, there really should be more of
	 * a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Products</em>' containment reference list.
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getAction_Products()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='products' namespace='##targetNamespace' group='#group:4'"
	 * @generated
	 */
	EList<IProductsType> getProducts();

	/**
	 * Returns the value of the '<em><b>Properties</b></em>' containment reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.cspecxml.IProperties}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Properties</em>' containment reference list isn't clear, there really should be more
	 * of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Properties</em>' containment reference list.
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getAction_Properties()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='properties' namespace='##targetNamespace' group='#group:4'"
	 * @generated
	 */
	EList<IProperties> getProperties();

	/**
	 * Returns the value of the '<em><b>Always</b></em>' attribute. The default value is <code>"false"</code>. <!--
	 * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
	 * 
	 * Used to turn on/off the timestamp dependent execution
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Always</em>' attribute.
	 * @see #isSetAlways()
	 * @see #unsetAlways()
	 * @see #setAlways(boolean)
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getAction_Always()
	 * @model default="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='always'"
	 * @generated
	 */
	boolean isAlways();

	/**
	 * Returns the value of the '<em><b>Assign Console Support</b></em>' attribute. The default value is
	 * <code>"true"</code>. <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
	 * 
	 * Used to tell the framework if it should assign the actor a stream it can print to
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Assign Console Support</em>' attribute.
	 * @see #isSetAssignConsoleSupport()
	 * @see #unsetAssignConsoleSupport()
	 * @see #setAssignConsoleSupport(boolean)
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getAction_AssignConsoleSupport()
	 * @model default="true" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='assignConsoleSupport'"
	 * @generated
	 */
	boolean isAssignConsoleSupport();

	/**
	 * Returns the value of the '<em><b>Enabled</b></em>' attribute. The default value is <code>"true"</code>. <!--
	 * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> Deprecated and ignored <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Enabled</em>' attribute.
	 * @see #isSetEnabled()
	 * @see #unsetEnabled()
	 * @see #setEnabled(boolean)
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getAction_Enabled()
	 * @model default="true" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='enabled'"
	 * @generated
	 */
	boolean isEnabled();

	/**
	 * Returns whether the value of the '{@link org.eclipse.buckminster.cspecxml.IAction#isAlways <em>Always</em>}'
	 * attribute is set. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return whether the value of the '<em>Always</em>' attribute is set.
	 * @see #unsetAlways()
	 * @see #isAlways()
	 * @see #setAlways(boolean)
	 * @generated
	 */
	boolean isSetAlways();

	/**
	 * Returns whether the value of the '{@link org.eclipse.buckminster.cspecxml.IAction#isAssignConsoleSupport
	 * <em>Assign Console Support</em>}' attribute is set. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return whether the value of the '<em>Assign Console Support</em>' attribute is set.
	 * @see #unsetAssignConsoleSupport()
	 * @see #isAssignConsoleSupport()
	 * @see #setAssignConsoleSupport(boolean)
	 * @generated
	 */
	boolean isSetAssignConsoleSupport();

	/**
	 * Returns whether the value of the '{@link org.eclipse.buckminster.cspecxml.IAction#isEnabled <em>Enabled</em>}'
	 * attribute is set. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return whether the value of the '<em>Enabled</em>' attribute is set.
	 * @see #unsetEnabled()
	 * @see #isEnabled()
	 * @see #setEnabled(boolean)
	 * @generated
	 */
	boolean isSetEnabled();

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.cspecxml.IAction#getActor <em>Actor</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Actor</em>' attribute.
	 * @see #getActor()
	 * @generated
	 */
	void setActor(String value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.cspecxml.IAction#isAlways <em>Always</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Always</em>' attribute.
	 * @see #isSetAlways()
	 * @see #unsetAlways()
	 * @see #isAlways()
	 * @generated
	 */
	void setAlways(boolean value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.cspecxml.IAction#isAssignConsoleSupport
	 * <em>Assign Console Support</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Assign Console Support</em>' attribute.
	 * @see #isSetAssignConsoleSupport()
	 * @see #unsetAssignConsoleSupport()
	 * @see #isAssignConsoleSupport()
	 * @generated
	 */
	void setAssignConsoleSupport(boolean value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.cspecxml.IAction#isEnabled <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Enabled</em>' attribute.
	 * @see #isSetEnabled()
	 * @see #unsetEnabled()
	 * @see #isEnabled()
	 * @generated
	 */
	void setEnabled(boolean value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.cspecxml.IAction#getFilter <em>Filter</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Filter</em>' attribute.
	 * @see #getFilter()
	 * @generated
	 */
	void setFilter(Filter value);

	/**
	 * Unsets the value of the '{@link org.eclipse.buckminster.cspecxml.IAction#isAlways <em>Always</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isSetAlways()
	 * @see #isAlways()
	 * @see #setAlways(boolean)
	 * @generated
	 */
	void unsetAlways();

	/**
	 * Unsets the value of the '{@link org.eclipse.buckminster.cspecxml.IAction#isAssignConsoleSupport
	 * <em>Assign Console Support</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isSetAssignConsoleSupport()
	 * @see #isAssignConsoleSupport()
	 * @see #setAssignConsoleSupport(boolean)
	 * @generated
	 */
	void unsetAssignConsoleSupport();

	/**
	 * Unsets the value of the '{@link org.eclipse.buckminster.cspecxml.IAction#isEnabled <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isSetEnabled()
	 * @see #isEnabled()
	 * @see #setEnabled(boolean)
	 * @generated
	 */
	void unsetEnabled();

} // IAction
