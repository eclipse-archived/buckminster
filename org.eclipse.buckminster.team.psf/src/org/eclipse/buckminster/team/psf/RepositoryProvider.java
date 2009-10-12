/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.team.psf;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Repository Provider</b></em>'. <!-- end-user-doc
 * -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.team.psf.RepositoryProvider#getId <em>Id</em>}</li>
 * <li>{@link org.eclipse.buckminster.team.psf.RepositoryProvider#getProjects <em>Projects</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.team.psf.PsfPackage#getRepositoryProvider()
 * @model
 * @generated
 */
public interface RepositoryProvider extends EObject
{
	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.eclipse.buckminster.team.psf.PsfPackage#getRepositoryProvider_Id()
	 * @model id="true" required="true"
	 * @generated
	 */
	String getId();

	/**
	 * Returns the value of the '<em><b>Projects</b></em>' containment reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.team.psf.Project}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Projects</em>' containment reference list isn't clear, there really should be more of
	 * a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Projects</em>' containment reference list.
	 * @see org.eclipse.buckminster.team.psf.PsfPackage#getRepositoryProvider_Projects()
	 * @model containment="true" extendedMetaData="name='project' kind='element'"
	 * @generated
	 */
	EList<Project> getProjects();

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.team.psf.RepositoryProvider#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // RepositoryProvider
