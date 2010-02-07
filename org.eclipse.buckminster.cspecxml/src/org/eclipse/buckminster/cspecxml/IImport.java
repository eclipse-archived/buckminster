/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspecxml;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Import</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.cspecxml.IImport#getAttribute <em>
 * Attribute</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IImport#getFilter <em>Filter
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IImport#getVersionDesignator <em>
 * Version Designator</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getImport()
 * @model extendedMetaData="name='Import' kind='empty'"
 * @generated
 */
public interface IImport extends EObject {
	/**
	 * Returns the value of the '<em><b>Attribute</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
	 * 
	 * The name of the required attribute
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Attribute</em>' attribute.
	 * @see #setAttribute(String)
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getImport_Attribute()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute' name='attribute'"
	 * @generated
	 */
	String getAttribute();

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
	 * @see #setFilter(String)
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getImport_Filter()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='filter'"
	 * @generated
	 */
	String getFilter();

	/**
	 * Returns the value of the '<em><b>Version Designator</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
	 * 
	 * The version designator, if any, of the required attribute
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Version Designator</em>' attribute.
	 * @see #setVersionDesignator(String)
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getImport_VersionDesignator()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='versionDesignator'"
	 * @generated
	 */
	String getVersionDesignator();

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.cspecxml.IImport#getAttribute
	 * <em>Attribute</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @param value
	 *            the new value of the '<em>Attribute</em>' attribute.
	 * @see #getAttribute()
	 * @generated
	 */
	void setAttribute(String value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.cspecxml.IImport#getFilter
	 * <em>Filter</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @param value
	 *            the new value of the '<em>Filter</em>' attribute.
	 * @see #getFilter()
	 * @generated
	 */
	void setFilter(String value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.cspecxml.IImport#getVersionDesignator
	 * <em>Version Designator</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Version Designator</em>' attribute.
	 * @see #getVersionDesignator()
	 * @generated
	 */
	void setVersionDesignator(String value);

} // IImport
