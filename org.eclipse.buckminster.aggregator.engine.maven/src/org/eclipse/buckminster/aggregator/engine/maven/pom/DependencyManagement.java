/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.engine.maven.pom;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Dependency Management</b></em>'. <!--
 * end-user-doc -->
 * 
 * <!-- begin-model-doc --> 4.0.0
 * 
 * Section for management of default dependency information for use in a group of POMs.
 * 
 * <!-- end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.DependencyManagement#getDependencies <em>Dependencies
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage#getDependencyManagement()
 * @model extendedMetaData="name='DependencyManagement' kind='elementOnly'"
 * @generated
 */
public interface DependencyManagement extends EObject
{
	/**
	 * Returns the value of the '<em><b>Dependencies</b></em>' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc --> <!-- begin-model-doc --> 4.0.0
	 * 
	 * The dependencies specified here are not used until they are referenced in a POM within the group. This allows the
	 * specification of a "standard" version for a particular dependency.
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Dependencies</em>' containment reference.
	 * @see #setDependencies(DependenciesType)
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage#getDependencyManagement_Dependencies()
	 * @model containment="true" extendedMetaData="kind='element' name='dependencies' namespace='##targetNamespace'"
	 * @generated
	 */
	DependenciesType getDependencies();

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.DependencyManagement#getDependencies
	 * <em>Dependencies</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Dependencies</em>' containment reference.
	 * @see #getDependencies()
	 * @generated
	 */
	void setDependencies(DependenciesType value);

} // DependencyManagement
