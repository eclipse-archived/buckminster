/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspecxml;

import org.eclipse.buckminster.model.common.PropertyConstant;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Properties</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.cspecxml.IProperties#getProperty <em>
 * Property</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getProperties()
 * @model extendedMetaData="name='Properties' kind='elementOnly'"
 * @generated
 */
public interface IProperties extends EObject {
	/**
	 * Returns the value of the '<em><b>Property</b></em>' containment reference
	 * list. The list contents are of type
	 * {@link org.eclipse.buckminster.model.common.PropertyConstant}. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Property</em>' containment reference list
	 * isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Property</em>' containment reference list.
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getProperties_Property()
	 * @model containment="true" extendedMetaData=
	 *        "kind='element' name='property' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<PropertyConstant> getProperty();

} // IProperties
