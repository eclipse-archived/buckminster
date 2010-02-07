/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspec;

import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Group</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.cspec.Group#getPrerequisites <em>
 * Prerequisites</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.Group#getRebase <em>Rebase</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.cspec.CspecPackage#getGroup()
 * @model
 * @generated
 */
public interface Group extends Attribute {
	/**
	 * Returns the value of the '<em><b>Prerequisites</b></em>' containment
	 * reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.cspec.Prerequisite}. <!-- begin-user-doc
	 * -->
	 * <p>
	 * If the meaning of the '<em>Prerequisites</em>' containment reference list
	 * isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Prerequisites</em>' containment reference
	 *         list.
	 * @see org.eclipse.buckminster.cspec.CspecPackage#getGroup_Prerequisites()
	 * @model containment="true"
	 * @generated
	 */
	EList<Prerequisite> getPrerequisites();

	/**
	 * Returns the value of the '<em><b>Rebase</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rebase</em>' containment reference isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Rebase</em>' attribute.
	 * @see #setRebase(IPath)
	 * @see org.eclipse.buckminster.cspec.CspecPackage#getGroup_Rebase()
	 * @model dataType="org.eclipse.buckminster.cspec.IPath"
	 * @generated
	 */
	IPath getRebase();

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.cspec.Group#getRebase <em>Rebase</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Rebase</em>' attribute.
	 * @see #getRebase()
	 * @generated
	 */
	void setRebase(IPath value);

} // Group
