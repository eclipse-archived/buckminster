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
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Plugins Type</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.PluginsType#getPlugin <em>Plugin</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage#getPluginsType()
 * @model extendedMetaData="name='plugins_._type' kind='elementOnly'"
 * @generated
 */
public interface PluginsType extends EObject
{
	/**
	 * Returns the value of the '<em><b>Plugin</b></em>' containment reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.Plugin}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Plugin</em>' containment reference list isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Plugin</em>' containment reference list.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage#getPluginsType_Plugin()
	 * @model containment="true" extendedMetaData="kind='element' name='plugin' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<Plugin> getPlugin();

} // PluginsType
