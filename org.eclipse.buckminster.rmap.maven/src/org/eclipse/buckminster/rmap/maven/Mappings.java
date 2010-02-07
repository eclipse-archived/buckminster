/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap.maven;

import org.eclipse.buckminster.rmap.Transform;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Mappings</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.rmap.maven.Mappings#getEntries <em>Entries
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.maven.Mappings#getRules <em>Rules
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.rmap.maven.MavenPackage#getMappings()
 * @model
 * @generated
 */
public interface Mappings extends EObject {
	/**
	 * Returns the value of the '<em><b>Entries</b></em>' containment reference
	 * list. The list contents are of type
	 * {@link org.eclipse.buckminster.rmap.maven.MapEntry}. <!-- begin-user-doc
	 * -->
	 * <p>
	 * If the meaning of the '<em>Entries</em>' containment reference list isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Entries</em>' containment reference list.
	 * @see org.eclipse.buckminster.rmap.maven.MavenPackage#getMappings_Entries()
	 * @model containment="true" extendedMetaData="kind='element' name='entry'"
	 * @generated
	 */
	EList<MapEntry> getEntries();

	/**
	 * Returns the value of the '<em><b>Rules</b></em>' containment reference
	 * list. The list contents are of type
	 * {@link org.eclipse.buckminster.rmap.Transform}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rules</em>' containment reference list isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Rules</em>' containment reference list.
	 * @see org.eclipse.buckminster.rmap.maven.MavenPackage#getMappings_Rules()
	 * @model containment="true" extendedMetaData="name='rule' kind='element'"
	 * @generated
	 */
	EList<Transform> getRules();

} // Mappings
