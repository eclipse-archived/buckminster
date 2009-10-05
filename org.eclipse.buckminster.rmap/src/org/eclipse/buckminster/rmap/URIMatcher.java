/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap;

import org.eclipse.buckminster.model.common.RxPart;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>URI Matcher</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.rmap.URIMatcher#getBase <em>Base</em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.URIMatcher#getRxParts <em>Rx Parts</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.rmap.RmapPackage#getURIMatcher()
 * @model
 * @generated
 */
public interface URIMatcher extends EObject
{
	/**
	 * Returns the value of the '<em><b>Base</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Base</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Base</em>' attribute.
	 * @see #setBase(String)
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getURIMatcher_Base()
	 * @model extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getBase();

	/**
	 * Returns the value of the '<em><b>Rx Parts</b></em>' containment reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.model.common.RxPart}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rx Parts</em>' containment reference list isn't clear, there really should be more of
	 * a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Rx Parts</em>' containment reference list.
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getURIMatcher_RxParts()
	 * @model containment="true" extendedMetaData="name='rxPart' kind='element' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<RxPart> getRxParts();

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.rmap.URIMatcher#getBase <em>Base</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Base</em>' attribute.
	 * @see #getBase()
	 * @generated
	 */
	void setBase(String value);

} // URIMatcher
