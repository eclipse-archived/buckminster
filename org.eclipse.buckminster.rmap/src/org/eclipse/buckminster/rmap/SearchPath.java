/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Search Path</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.rmap.SearchPath#getName <em>Name</em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.SearchPath#getProviders <em>Providers</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.rmap.RmapPackage#getSearchPath()
 * @model
 * @generated
 */
public interface SearchPath extends EObject
{
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getSearchPath_Name()
	 * @model id="true" dataType="org.eclipse.emf.ecore.xml.type.ID" required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Returns the value of the '<em><b>Providers</b></em>' containment reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.rmap.Provider}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Providers</em>' containment reference list isn't clear, there really should be more of
	 * a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Providers</em>' containment reference list.
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getSearchPath_Providers()
	 * @model containment="true" extendedMetaData="name='provider' namespace='##targetNamespace' kind='element'"
	 * @generated
	 */
	EList<Provider> getProviders();

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.rmap.SearchPath#getName <em>Name</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // SearchPath
