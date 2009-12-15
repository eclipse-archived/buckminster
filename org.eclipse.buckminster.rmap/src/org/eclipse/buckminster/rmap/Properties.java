/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap;

import org.eclipse.buckminster.model.common.PropertyConstant;
import org.eclipse.buckminster.model.common.PropertyElement;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Properties</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.rmap.Properties#getProperties <em>Properties</em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.Properties#getPropertyElements <em>Property Elements</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.rmap.RmapPackage#getProperties()
 * @model
 * @generated
 */
public interface Properties extends EObject
{
	/**
	 * Returns the value of the '<em><b>Properties</b></em>' containment reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.model.common.PropertyConstant}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Properties</em>' containment reference list isn't clear, there really should be more
	 * of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Properties</em>' containment reference list.
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getProperties_Properties()
	 * @model containment="true" extendedMetaData="name='property' namespace='##targetNamespace' kind='element'"
	 * @generated
	 */
	EList<PropertyConstant> getProperties();

	/**
	 * Returns the value of the '<em><b>Property Elements</b></em>' containment reference list. The list contents are of
	 * type {@link org.eclipse.buckminster.model.common.PropertyElement}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Property Elements</em>' containment reference list isn't clear, there really should be
	 * more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Property Elements</em>' containment reference list.
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getProperties_PropertyElements()
	 * @model containment="true" extendedMetaData="name='propertyElement' namespace='##targetNamespace' kind='element'"
	 * @generated
	 */
	EList<PropertyElement> getPropertyElements();

} // Properties
