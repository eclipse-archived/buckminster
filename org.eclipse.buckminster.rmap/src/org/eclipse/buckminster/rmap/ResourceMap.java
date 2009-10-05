/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap;

import org.eclipse.buckminster.model.common.Documentation;
import org.eclipse.buckminster.model.common.PropertyConstant;
import org.eclipse.buckminster.model.common.PropertyElement;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Resource Map</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.rmap.ResourceMap#getLocators <em>Locators</em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.ResourceMap#getRedirects <em>Redirects</em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.ResourceMap#getSearchPaths <em>Search Paths</em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.ResourceMap#getPropertyElements <em>Property Elements</em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.ResourceMap#getProperties <em>Properties</em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.ResourceMap#getDocumentation <em>Documentation</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.rmap.RmapPackage#getResourceMap()
 * @model extendedMetaData="name='ResourceMap'"
 * @generated
 */
public interface ResourceMap extends EObject
{
	/**
	 * Returns the value of the '<em><b>Documentation</b></em>' containment reference. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Documentation</em>' containment reference isn't clear, there really should be more of
	 * a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Documentation</em>' containment reference.
	 * @see #setDocumentation(Documentation)
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getResourceMap_Documentation()
	 * @model containment="true" extendedMetaData="name='documentation' kind='element' namespace='##targetNamespace'"
	 * @generated
	 */
	Documentation getDocumentation();

	/**
	 * Returns the value of the '<em><b>Locators</b></em>' containment reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.rmap.Locator}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Locators</em>' containment reference list isn't clear, there really should be more of
	 * a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Locators</em>' containment reference list.
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getResourceMap_Locators()
	 * @model containment="true" extendedMetaData="name='locator' namespace='##targetNamespace' kind='element'"
	 * @generated
	 */
	EList<Locator> getLocators();

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
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getResourceMap_Properties()
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
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getResourceMap_PropertyElements()
	 * @model containment="true" extendedMetaData="name='propertyElement' namespace='##targetNamespace' kind='element'"
	 * @generated
	 */
	EList<PropertyElement> getPropertyElements();

	/**
	 * Returns the value of the '<em><b>Redirects</b></em>' containment reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.rmap.Redirect}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Redirects</em>' containment reference list isn't clear, there really should be more of
	 * a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Redirects</em>' containment reference list.
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getResourceMap_Redirects()
	 * @model containment="true" extendedMetaData="name='redirect' namespace='##targetNamespace' kind='element'"
	 * @generated
	 */
	EList<Redirect> getRedirects();

	/**
	 * Returns the value of the '<em><b>Search Paths</b></em>' containment reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.rmap.SearchPath}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Search Paths</em>' containment reference list isn't clear, there really should be more
	 * of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Search Paths</em>' containment reference list.
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getResourceMap_SearchPaths()
	 * @model containment="true" extendedMetaData="name='searchPath' namespace='##targetNamespace' kind='element'"
	 * @generated
	 */
	EList<SearchPath> getSearchPaths();

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.rmap.ResourceMap#getDocumentation <em>Documentation</em>}'
	 * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Documentation</em>' containment reference.
	 * @see #getDocumentation()
	 * @generated
	 */
	void setDocumentation(Documentation value);

} // ResourceMap
