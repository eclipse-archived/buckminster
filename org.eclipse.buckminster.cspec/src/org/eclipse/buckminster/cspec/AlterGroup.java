/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspec;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Alter Group</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.cspec.AlterGroup#getReplacePrerequisites <em>Replace Prerequisites</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.AlterGroup#getRemovePrerequisites <em>Remove Prerequisites</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.cspec.CspecPackage#getAlterGroup()
 * @model
 * @generated
 */
public interface AlterGroup extends Group, AlterAttribute
{
	/**
	 * Returns the value of the '<em><b>Remove Prerequisites</b></em>' containment reference list. The list contents are
	 * of type {@link org.eclipse.buckminster.cspec.Remove}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Remove Prerequisites</em>' containment reference list isn't clear, there really should
	 * be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Remove Prerequisites</em>' containment reference list.
	 * @see org.eclipse.buckminster.cspec.CspecPackage#getAlterGroup_RemovePrerequisites()
	 * @model containment="true"
	 * @generated
	 */
	EList<Remove> getRemovePrerequisites();

	/**
	 * Returns the value of the '<em><b>Replace Prerequisites</b></em>' containment reference list. The list contents
	 * are of type {@link org.eclipse.buckminster.cspec.Prerequisite}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Replace Prerequisites</em>' containment reference list isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Replace Prerequisites</em>' containment reference list.
	 * @see org.eclipse.buckminster.cspec.CspecPackage#getAlterGroup_ReplacePrerequisites()
	 * @model containment="true"
	 * @generated
	 */
	EList<Prerequisite> getReplacePrerequisites();

} // AlterGroup
