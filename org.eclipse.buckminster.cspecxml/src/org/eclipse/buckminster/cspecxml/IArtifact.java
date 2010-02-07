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
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Artifact</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.cspecxml.IArtifact#getGroup <em>Group
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IArtifact#getDefinitions <em>
 * Definitions</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IArtifact#getPath <em>Path</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IArtifact#getBase <em>Base</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IArtifact#getFilter <em>Filter
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IArtifact#getPath1 <em>Path1
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IArtifact#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getArtifact()
 * @model extendedMetaData="name='Artifact' kind='elementOnly'"
 * @generated
 */
public interface IArtifact extends IAttribute {
	/**
	 * Returns the value of the '<em><b>Base</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
	 * 
	 * Base of all paths, relative to component root
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Base</em>' attribute.
	 * @see #setBase(String)
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getArtifact_Base()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='base'"
	 * @generated
	 */
	String getBase();

	/**
	 * Returns the value of the '<em><b>Definitions</b></em>' containment
	 * reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.cspecxml.IDefinitions}. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Definitions</em>' containment reference list
	 * isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Definitions</em>' containment reference
	 *         list.
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getArtifact_Definitions()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='definitions' namespace='##targetNamespace' group='#group:4'"
	 * @generated
	 */
	EList<IDefinitions> getDefinitions();

	/**
	 * Returns the value of the '<em><b>Filter</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
	 * 
	 * LDAP filter controlling if this artifact is enabled or disabled
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Filter</em>' attribute.
	 * @see #setFilter(Filter)
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getArtifact_Filter()
	 * @model dataType="org.eclipse.buckminster.model.common.Filter"
	 *        extendedMetaData="kind='attribute' name='filter'"
	 * @generated
	 */
	Filter getFilter();

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
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getArtifact_Group()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry"
	 *        many="true" extendedMetaData="kind='group' name='group:4'"
	 * @generated
	 */
	FeatureMap getGroup();

	/**
	 * Returns the value of the '<em><b>Path</b></em>' containment reference
	 * list. The list contents are of type
	 * {@link org.eclipse.buckminster.cspecxml.IPath}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Path</em>' containment reference list isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Path</em>' containment reference list.
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getArtifact_Path()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData=
	 *        "kind='element' name='path' namespace='##targetNamespace' group='#group:4'"
	 * @generated
	 */
	EList<IPath> getPath();

	/**
	 * Returns the value of the '<em><b>Path1</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Path1</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Path1</em>' attribute.
	 * @see #setPath1(String)
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getArtifact_Path1()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='path'"
	 * @generated
	 */
	String getPath1();

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
	 * 
	 * Deprecated and ignored.
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(String)
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getArtifact_Type()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='type'"
	 * @generated
	 */
	String getType();

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.cspecxml.IArtifact#getBase <em>Base</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Base</em>' attribute.
	 * @see #getBase()
	 * @generated
	 */
	void setBase(String value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.cspecxml.IArtifact#getFilter
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
	 * {@link org.eclipse.buckminster.cspecxml.IArtifact#getPath1
	 * <em>Path1</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Path1</em>' attribute.
	 * @see #getPath1()
	 * @generated
	 */
	void setPath1(String value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.cspecxml.IArtifact#getType <em>Type</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

} // IArtifact
