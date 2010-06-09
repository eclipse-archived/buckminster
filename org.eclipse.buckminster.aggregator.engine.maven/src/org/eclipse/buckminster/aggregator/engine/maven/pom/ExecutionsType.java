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
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Executions Type</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.ExecutionsType#getExecution <em>Execution</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage#getExecutionsType()
 * @model extendedMetaData="name='executions_._type' kind='elementOnly'"
 * @generated
 */
public interface ExecutionsType extends EObject
{
	/**
	 * Returns the value of the '<em><b>Execution</b></em>' containment reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.PluginExecution}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Execution</em>' containment reference list isn't clear, there really should be more of
	 * a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Execution</em>' containment reference list.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage#getExecutionsType_Execution()
	 * @model containment="true" extendedMetaData="kind='element' name='execution' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<PluginExecution> getExecution();

} // ExecutionsType
