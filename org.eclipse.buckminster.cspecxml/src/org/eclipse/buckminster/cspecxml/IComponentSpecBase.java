/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspecxml;

import java.net.URL;

import org.eclipse.buckminster.model.common.Documentation;

import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.equinox.p2.metadata.Version;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Component Spec Base</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.buckminster.cspecxml.IComponentSpecBase#getDocumentation
 * <em>Documentation</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IComponentSpecBase#getGroup <em>
 * Group</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.cspecxml.IComponentSpecBase#getDependencies
 * <em>Dependencies</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IComponentSpecBase#getGenerators
 * <em>Generators</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IComponentSpecBase#getArtifacts
 * <em>Artifacts</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IComponentSpecBase#getGroups <em>
 * Groups</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IComponentSpecBase#getActions
 * <em>Actions</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IComponentSpecBase#getCategory
 * <em>Category</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.cspecxml.IComponentSpecBase#getComponentType
 * <em>Component Type</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IComponentSpecBase#getFilter <em>
 * Filter</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.cspecxml.IComponentSpecBase#getProjectInfo
 * <em>Project Info</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IComponentSpecBase#getShortDesc
 * <em>Short Desc</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.cspecxml.IComponentSpecBase#getVersionString
 * <em>Version String</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.cspecxml.IComponentSpecBase#getVersionType
 * <em>Version Type</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getComponentSpecBase()
 * @model extendedMetaData="name='ComponentSpecBase' kind='elementOnly'"
 * @generated
 */
public interface IComponentSpecBase extends EObject {
	/**
	 * Returns the value of the '<em><b>Actions</b></em>' containment reference
	 * list. The list contents are of type
	 * {@link org.eclipse.buckminster.cspecxml.IActionsType}. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Actions</em>' containment reference list isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Actions</em>' containment reference list.
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getComponentSpecBase_Actions()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData=
	 *        "kind='element' name='actions' namespace='##targetNamespace' group='#group:1'"
	 * @generated
	 */
	EList<IActionsType> getActions();

	/**
	 * Returns the value of the '<em><b>Artifacts</b></em>' containment
	 * reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.cspecxml.IArtifactsType}. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Artifacts</em>' containment reference list
	 * isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Artifacts</em>' containment reference list.
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getComponentSpecBase_Artifacts()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData=
	 *        "kind='element' name='artifacts' namespace='##targetNamespace' group='#group:1'"
	 * @generated
	 */
	EList<IArtifactsType> getArtifacts();

	/**
	 * Returns the value of the '<em><b>Category</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Category</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Category</em>' attribute.
	 * @see #setCategory(String)
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getComponentSpecBase_Category()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='category'"
	 * @generated
	 */
	String getCategory();

	/**
	 * Returns the value of the '<em><b>Component Type</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Component Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Component Type</em>' attribute.
	 * @see #setComponentType(String)
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getComponentSpecBase_ComponentType()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='componentType'"
	 * @generated
	 */
	String getComponentType();

	/**
	 * Returns the value of the '<em><b>Dependencies</b></em>' containment
	 * reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.cspecxml.IDependenciesType}. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dependencies</em>' containment reference list
	 * isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Dependencies</em>' containment reference
	 *         list.
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getComponentSpecBase_Dependencies()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='dependencies' namespace='##targetNamespace' group='#group:1'"
	 * @generated
	 */
	EList<IDependenciesType> getDependencies();

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
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getComponentSpecBase_Documentation()
	 * @model containment="true" extendedMetaData=
	 *        "kind='element' name='documentation' namespace='##targetNamespace'"
	 * @generated
	 */
	Documentation getDocumentation();

	/**
	 * Returns the value of the '<em><b>Filter</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Filter</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Filter</em>' attribute.
	 * @see #setFilter(Filter)
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getComponentSpecBase_Filter()
	 * @model dataType="org.eclipse.buckminster.model.common.Filter"
	 *        extendedMetaData="kind='attribute' name='filter'"
	 * @generated
	 */
	Filter getFilter();

	/**
	 * Returns the value of the '<em><b>Generators</b></em>' containment
	 * reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.cspecxml.IGeneratorsType}. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Generators</em>' containment reference list
	 * isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Generators</em>' containment reference
	 *         list.
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getComponentSpecBase_Generators()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='generators' namespace='##targetNamespace' group='#group:1'"
	 * @generated
	 */
	EList<IGeneratorsType> getGenerators();

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
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getComponentSpecBase_Group()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry"
	 *        many="true" extendedMetaData="kind='group' name='group:1'"
	 * @generated
	 */
	FeatureMap getGroup();

	/**
	 * Returns the value of the '<em><b>Groups</b></em>' containment reference
	 * list. The list contents are of type
	 * {@link org.eclipse.buckminster.cspecxml.IGroupsType}. <!-- begin-user-doc
	 * -->
	 * <p>
	 * If the meaning of the '<em>Groups</em>' containment reference list isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Groups</em>' containment reference list.
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getComponentSpecBase_Groups()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData=
	 *        "kind='element' name='groups' namespace='##targetNamespace' group='#group:1'"
	 * @generated
	 */
	EList<IGroupsType> getGroups();

	/**
	 * Returns the value of the '<em><b>Project Info</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Project Info</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Project Info</em>' attribute.
	 * @see #setProjectInfo(URL)
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getComponentSpecBase_ProjectInfo()
	 * @model dataType="org.eclipse.buckminster.model.common.URL"
	 *        extendedMetaData="kind='attribute' name='projectInfo'"
	 * @generated
	 */
	URL getProjectInfo();

	/**
	 * Returns the value of the '<em><b>Short Desc</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Short Desc</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Short Desc</em>' attribute.
	 * @see #setShortDesc(String)
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getComponentSpecBase_ShortDesc()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='shortDesc'"
	 * @generated
	 */
	String getShortDesc();

	/**
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Version</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @model kind="operation"
	 *        dataType="org.eclipse.buckminster.model.common.Version"
	 * @generated
	 */
	Version getVersion();

	/**
	 * Returns the value of the '<em><b>Version String</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Version String</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Version String</em>' attribute.
	 * @see #setVersionString(String)
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getComponentSpecBase_VersionString()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='version'"
	 * @generated
	 */
	String getVersionString();

	/**
	 * Returns the value of the '<em><b>Version Type</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Version Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Version Type</em>' attribute.
	 * @see #setVersionType(String)
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getComponentSpecBase_VersionType()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='versionType'"
	 * @generated
	 */
	String getVersionType();

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.cspecxml.IComponentSpecBase#getCategory
	 * <em>Category</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @param value
	 *            the new value of the '<em>Category</em>' attribute.
	 * @see #getCategory()
	 * @generated
	 */
	void setCategory(String value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.cspecxml.IComponentSpecBase#getComponentType
	 * <em>Component Type</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Component Type</em>' attribute.
	 * @see #getComponentType()
	 * @generated
	 */
	void setComponentType(String value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.cspecxml.IComponentSpecBase#getDocumentation
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
	 * {@link org.eclipse.buckminster.cspecxml.IComponentSpecBase#getFilter
	 * <em>Filter</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @param value
	 *            the new value of the '<em>Filter</em>' attribute.
	 * @see #getFilter()
	 * @generated
	 */
	void setFilter(Filter value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.cspecxml.IComponentSpecBase#getProjectInfo
	 * <em>Project Info</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Project Info</em>' attribute.
	 * @see #getProjectInfo()
	 * @generated
	 */
	void setProjectInfo(URL value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.cspecxml.IComponentSpecBase#getShortDesc
	 * <em>Short Desc</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Short Desc</em>' attribute.
	 * @see #getShortDesc()
	 * @generated
	 */
	void setShortDesc(String value);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model versionDataType="org.eclipse.buckminster.model.common.Version"
	 * @generated
	 */
	void setVersion(Version version);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.cspecxml.IComponentSpecBase#getVersionString
	 * <em>Version String</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Version String</em>' attribute.
	 * @see #getVersionString()
	 * @generated
	 */
	void setVersionString(String value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.cspecxml.IComponentSpecBase#getVersionType
	 * <em>Version Type</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Version Type</em>' attribute.
	 * @see #getVersionType()
	 * @generated
	 */
	void setVersionType(String value);

} // IComponentSpecBase
