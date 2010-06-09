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
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Resources Type</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.ResourcesType#getResource <em>Resource</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage#getResourcesType()
 * @model extendedMetaData="name='resources_._type' kind='elementOnly'"
 * @generated
 */
public interface ResourcesType extends EObject
{
	/**
	 * Returns the value of the '<em><b>Resource</b></em>' containment reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.Resource}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resource</em>' containment reference list isn't clear, there really should be more of
	 * a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Resource</em>' containment reference list.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage#getResourcesType_Resource()
	 * @model containment="true" extendedMetaData="kind='element' name='resource' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<Resource> getResource();

} // ResourcesType
