/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspecxml;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Document Root</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.cspecxml.IDocumentRoot#getMixed <em>Mixed
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IDocumentRoot#getXMLNSPrefixMap
 * <em>XMLNS Prefix Map</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.cspecxml.IDocumentRoot#getXSISchemaLocation
 * <em>XSI Schema Location</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IDocumentRoot#getCspec <em>Cspec
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IDocumentRoot#getCspecExtension
 * <em>Cspec Extension</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getDocumentRoot()
 * @model extendedMetaData="name='' kind='mixed'"
 * @generated
 */
public interface IDocumentRoot extends EObject {
	/**
	 * Returns the value of the '<em><b>Cspec</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cspec</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Cspec</em>' containment reference.
	 * @see #setCspec(IComponentSpec)
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getDocumentRoot_Cspec()
	 * @model containment="true" upper="-2" transient="true" volatile="true"
	 *        derived="true" extendedMetaData=
	 *        "kind='element' name='cspec' namespace='##targetNamespace'"
	 * @generated
	 */
	IComponentSpec getCspec();

	/**
	 * Returns the value of the '<em><b>Cspec Extension</b></em>' containment
	 * reference. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cspec Extension</em>' containment reference
	 * isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Cspec Extension</em>' containment
	 *         reference.
	 * @see #setCspecExtension(ICSpecExtension)
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getDocumentRoot_CspecExtension()
	 * @model containment="true" upper="-2" transient="true" volatile="true"
	 *        derived="true" extendedMetaData=
	 *        "kind='element' name='cspecExtension' namespace='##targetNamespace'"
	 * @generated
	 */
	ICSpecExtension getCspecExtension();

	/**
	 * Returns the value of the '<em><b>Mixed</b></em>' attribute list. The list
	 * contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mixed</em>' attribute list isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Mixed</em>' attribute list.
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getDocumentRoot_Mixed()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry"
	 *        many="true"
	 *        extendedMetaData="kind='elementWildcard' name=':mixed'"
	 * @generated
	 */
	FeatureMap getMixed();

	/**
	 * Returns the value of the '<em><b>XMLNS Prefix Map</b></em>' map. The key
	 * is of type {@link java.lang.String}, and the value is of type
	 * {@link java.lang.String}, <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>XMLNS Prefix Map</em>' map isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>XMLNS Prefix Map</em>' map.
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getDocumentRoot_XMLNSPrefixMap()
	 * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>"
	 *        transient="true"
	 *        extendedMetaData="kind='attribute' name='xmlns:prefix'"
	 * @generated
	 */
	EMap<String, String> getXMLNSPrefixMap();

	/**
	 * Returns the value of the '<em><b>XSI Schema Location</b></em>' map. The
	 * key is of type {@link java.lang.String}, and the value is of type
	 * {@link java.lang.String}, <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>XSI Schema Location</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>XSI Schema Location</em>' map.
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getDocumentRoot_XSISchemaLocation()
	 * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>"
	 *        transient="true"
	 *        extendedMetaData="kind='attribute' name='xsi:schemaLocation'"
	 * @generated
	 */
	EMap<String, String> getXSISchemaLocation();

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.cspecxml.IDocumentRoot#getCspec
	 * <em>Cspec</em>}' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Cspec</em>' containment reference.
	 * @see #getCspec()
	 * @generated
	 */
	void setCspec(IComponentSpec value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.cspecxml.IDocumentRoot#getCspecExtension
	 * <em>Cspec Extension</em>}' containment reference. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Cspec Extension</em>' containment
	 *            reference.
	 * @see #getCspecExtension()
	 * @generated
	 */
	void setCspecExtension(ICSpecExtension value);

} // IDocumentRoot
