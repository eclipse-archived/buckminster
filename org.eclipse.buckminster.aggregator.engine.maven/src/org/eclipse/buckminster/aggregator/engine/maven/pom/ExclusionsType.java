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
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Exclusions Type</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.ExclusionsType#getExclusion <em>Exclusion</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage#getExclusionsType()
 * @model extendedMetaData="name='exclusions_._type' kind='elementOnly'"
 * @generated
 */
public interface ExclusionsType extends EObject
{
	/**
	 * Returns the value of the '<em><b>Exclusion</b></em>' containment reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.Exclusion}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Exclusion</em>' containment reference list isn't clear, there really should be more of
	 * a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Exclusion</em>' containment reference list.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage#getExclusionsType_Exclusion()
	 * @model containment="true" extendedMetaData="kind='element' name='exclusion' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<Exclusion> getExclusion();

} // ExclusionsType
