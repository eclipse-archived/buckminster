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
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Dependencies Type</b></em>'. <!-- end-user-doc
 * -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.DependenciesType#getDependency <em>Dependency</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage#getDependenciesType()
 * @model extendedMetaData="name='dependencies_._type' kind='elementOnly'"
 * @generated
 */
public interface DependenciesType extends EObject
{
	/**
	 * Returns the value of the '<em><b>Dependency</b></em>' containment reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.Dependency}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dependency</em>' containment reference list isn't clear, there really should be more
	 * of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Dependency</em>' containment reference list.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage#getDependenciesType_Dependency()
	 * @model containment="true" extendedMetaData="kind='element' name='dependency' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<Dependency> getDependency();

} // DependenciesType
