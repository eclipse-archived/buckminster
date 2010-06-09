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
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Plugin Repositories Type</b></em>'. <!--
 * end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.PluginRepositoriesType#getPluginRepository <em>Plugin
 * Repository</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage#getPluginRepositoriesType()
 * @model extendedMetaData="name='pluginRepositories_._type' kind='elementOnly'"
 * @generated
 */
public interface PluginRepositoriesType extends EObject
{
	/**
	 * Returns the value of the '<em><b>Plugin Repository</b></em>' containment reference list. The list contents are of
	 * type {@link org.eclipse.buckminster.aggregator.engine.maven.pom.Repository}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Plugin Repository</em>' containment reference list isn't clear, there really should be
	 * more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Plugin Repository</em>' containment reference list.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage#getPluginRepositoriesType_PluginRepository()
	 * @model containment="true" extendedMetaData="kind='element' name='pluginRepository' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<Repository> getPluginRepository();

} // PluginRepositoriesType
