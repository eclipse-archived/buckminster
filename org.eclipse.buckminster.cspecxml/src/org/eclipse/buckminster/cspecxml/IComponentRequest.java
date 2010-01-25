/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspecxml;

import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.equinox.p2.metadata.VersionRange;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Component Request</b></em>'. <!-- end-user-doc
 * -->
 * 
 * <!-- begin-model-doc -->
 * 
 * A resolution request for another component
 * 
 * <!-- end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.cspecxml.IComponentRequest#getImport <em>Import</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IComponentRequest#getComponentType <em>Component Type</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IComponentRequest#getFilter <em>Filter</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IComponentRequest#getName <em>Name</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IComponentRequest#getVersionDesignator <em>Version Designator</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IComponentRequest#getVersionType <em>Version Type</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getComponentRequest()
 * @model extendedMetaData="name='ComponentRequest' kind='elementOnly'"
 * @generated
 */
public interface IComponentRequest extends EObject
{
	/**
	 * Returns the value of the '<em><b>Component Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * --> <!-- begin-model-doc -->
	 * 
	 * The component type, if any, of the required component.
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Component Type</em>' attribute.
	 * @see #setComponentType(String)
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getComponentRequest_ComponentType()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" extendedMetaData="kind='attribute' name='componentType'"
	 * @generated
	 */
	String getComponentType();

	/**
	 * Returns the value of the '<em><b>Filter</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Filter</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Filter</em>' attribute.
	 * @see #setFilter(Filter)
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getComponentRequest_Filter()
	 * @model dataType="org.eclipse.buckminster.model.common.Filter" extendedMetaData="kind='attribute' name='filter'"
	 * @generated
	 */
	Filter getFilter();

	/**
	 * Returns the value of the '<em><b>Import</b></em>' containment reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.cspecxml.IImport}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Import</em>' containment reference list isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Import</em>' containment reference list.
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getComponentRequest_Import()
	 * @model containment="true" extendedMetaData="kind='element' name='import' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<IImport> getImport();

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
	 * begin-model-doc -->
	 * 
	 * The name of the component. This name is required if the request has no import elements
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getComponentRequest_Name()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" extendedMetaData="kind='attribute' name='name'"
	 * @generated
	 */
	String getName();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model kind="operation" dataType="org.eclipse.buckminster.model.common.VersionRange"
	 * @generated
	 */
	VersionRange getRange();

	/**
	 * Returns the value of the '<em><b>Version Designator</b></em>' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc --> <!-- begin-model-doc -->
	 * 
	 * The version designator, if any, of the required component
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Version Designator</em>' attribute.
	 * @see #setVersionDesignator(String)
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getComponentRequest_VersionDesignator()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='versionDesignator'"
	 * @generated
	 */
	String getVersionDesignator();

	/**
	 * Returns the value of the '<em><b>Version Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * The type of versions used in the version designator
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Version Type</em>' attribute.
	 * @see #setVersionType(String)
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getComponentRequest_VersionType()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" extendedMetaData="kind='attribute' name='versionType'"
	 * @generated
	 */
	String getVersionType();

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.cspecxml.IComponentRequest#getComponentType
	 * <em>Component Type</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Component Type</em>' attribute.
	 * @see #getComponentType()
	 * @generated
	 */
	void setComponentType(String value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.cspecxml.IComponentRequest#getFilter <em>Filter</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Filter</em>' attribute.
	 * @see #getFilter()
	 * @generated
	 */
	void setFilter(Filter value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.cspecxml.IComponentRequest#getName <em>Name</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model rangeDataType="org.eclipse.buckminster.model.common.VersionRange"
	 * @generated
	 */
	void setRange(VersionRange range);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.cspecxml.IComponentRequest#getVersionDesignator
	 * <em>Version Designator</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Version Designator</em>' attribute.
	 * @see #getVersionDesignator()
	 * @generated
	 */
	void setVersionDesignator(String value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.cspecxml.IComponentRequest#getVersionType
	 * <em>Version Type</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Version Type</em>' attribute.
	 * @see #getVersionType()
	 * @generated
	 */
	void setVersionType(String value);

} // IComponentRequest
