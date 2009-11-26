/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspec;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Alter Artifact</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.cspec.AlterArtifact#getRemovePaths <em>Remove Paths</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.cspec.CspecPackage#getAlterArtifact()
 * @model
 * @generated
 */
public interface AlterArtifact extends Artifact, AlterAttribute
{
	/**
	 * Returns the value of the '<em><b>Remove Paths</b></em>' containment reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.cspec.Remove}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Remove Paths</em>' containment reference list isn't clear, there really should be more
	 * of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Remove Paths</em>' containment reference list.
	 * @see org.eclipse.buckminster.cspec.CspecPackage#getAlterArtifact_RemovePaths()
	 * @model containment="true"
	 * @generated
	 */
	EList<Remove> getRemovePaths();

} // AlterArtifact
