/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap;

import java.net.URL;

import org.eclipse.buckminster.model.common.Documentation;

import org.eclipse.buckminster.model.common.Properties;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Resource Map</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.buckminster.rmap.ResourceMap#getMatcherGroup <em>Matcher Group</em>}</li>
 *   <li>{@link org.eclipse.buckminster.rmap.ResourceMap#getRepositories <em>Repositories</em>}</li>
 *   <li>{@link org.eclipse.buckminster.rmap.ResourceMap#getMatchers <em>Matchers</em>}</li>
 *   <li>{@link org.eclipse.buckminster.rmap.ResourceMap#getSearchPaths <em>Search Paths</em>}</li>
 *   <li>{@link org.eclipse.buckminster.rmap.ResourceMap#getDocumentation <em>Documentation</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.buckminster.rmap.RmapPackage#getResourceMap()
 * @model extendedMetaData="name='rmap'"
 * @generated
 */
public interface ResourceMap extends Properties {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @model kind="operation" dataType="org.eclipse.buckminster.model.common.URL"
	 * @generated
	 */
	URL getContextURL();

	/**
	 * Returns the value of the '<em><b>Documentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Documentation</em>' containment reference
	 * isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Documentation</em>' containment reference.
	 * @see #setDocumentation(Documentation)
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getResourceMap_Documentation()
	 * @model containment="true"
	 *        extendedMetaData="name='documentation' kind='element' namespace='##targetNamespace'"
	 * @generated
	 */
	Documentation getDocumentation();

	/**
	 * Returns the value of the '<em><b>Matcher Group</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc
	 * -->
	 * <p>
	 * If the meaning of the '<em>Matcher Group</em>' attribute list isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Matcher Group</em>' attribute list.
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getResourceMap_MatcherGroup()
	 * @model dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='matcher:group' namespace='##targetNamespace'"
	 * @generated
	 */
	FeatureMap getMatcherGroup();

	/**
	 * Returns the value of the '<em><b>Matchers</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.buckminster.rmap.Matcher}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Matchers</em>' containment reference list.
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getResourceMap_Matchers()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='matcher' namespace='##targetNamespace' group='matcher:group'"
	 * @generated
	 */
	EList<Matcher> getMatchers();

	/**
	 * Returns the value of the '<em><b>Repositories</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.buckminster.rmap.Repository}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Repositories</em>' containment reference list
	 * isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Repositories</em>' containment reference list.
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getResourceMap_Repositories()
	 * @model containment="true"
	 * @generated
	 */
	EList<Repository> getRepositories();

	/**
	 * Returns the value of the '<em><b>Search Paths</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.buckminster.rmap.SearchPath}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Search Paths</em>' containment reference list
	 * isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Search Paths</em>' containment reference list.
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getResourceMap_SearchPaths()
	 * @model containment="true"
	 *        extendedMetaData="name='searchPath' namespace='##targetNamespace' kind='element'"
	 * @generated
	 */
	EList<SearchPath> getSearchPaths();

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.rmap.ResourceMap#getDocumentation <em>Documentation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Documentation</em>' containment reference.
	 * @see #getDocumentation()
	 * @generated
	 */
	void setDocumentation(Documentation value);

} // ResourceMap
