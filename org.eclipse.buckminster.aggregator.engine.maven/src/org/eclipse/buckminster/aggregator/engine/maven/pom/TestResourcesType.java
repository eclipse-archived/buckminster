/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.engine.maven.pom;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Test Resources Type</b></em>'. <!-- end-user-doc
 * -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.TestResourcesType#getTestResource <em>Test Resource
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage#getTestResourcesType()
 * @model extendedMetaData="name='testResources_._type' kind='elementOnly'"
 * @generated
 */
public interface TestResourcesType extends EObject
{
	/**
	 * Returns the value of the '<em><b>Test Resource</b></em>' containment reference list. The list contents are of
	 * type {@link org.eclipse.buckminster.aggregator.engine.maven.pom.Resource}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Test Resource</em>' containment reference list isn't clear, there really should be
	 * more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Test Resource</em>' containment reference list.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage#getTestResourcesType_TestResource()
	 * @model containment="true" extendedMetaData="kind='element' name='testResource' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<Resource> getTestResource();

} // TestResourcesType
