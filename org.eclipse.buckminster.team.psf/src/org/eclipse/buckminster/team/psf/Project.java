/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.team.psf;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Project</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.team.psf.Project#getReference <em>
 * Reference</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.team.psf.PsfPackage#getProject()
 * @model
 * @generated
 */
public interface Project extends EObject {
	/**
	 * Returns the value of the '<em><b>Reference</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reference</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Reference</em>' attribute.
	 * @see #setReference(String)
	 * @see org.eclipse.buckminster.team.psf.PsfPackage#getProject_Reference()
	 * @model required="true"
	 * @generated
	 */
	String getReference();

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.team.psf.Project#getReference
	 * <em>Reference</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @param value
	 *            the new value of the '<em>Reference</em>' attribute.
	 * @see #getReference()
	 * @generated
	 */
	void setReference(String value);

} // Project
