/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspec;

import org.eclipse.core.runtime.IPath;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Path Group</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.cspec.PathGroup#getBase <em>Base</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.PathGroup#getPaths <em>Paths</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.cspec.CspecPackage#getPathGroup()
 * @model
 * @generated
 */
public interface PathGroup extends EObject
{
	/**
	 * Returns the value of the '<em><b>Base</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Base</em>' containment reference isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Base</em>' attribute.
	 * @see #setBase(IPath)
	 * @see org.eclipse.buckminster.cspec.CspecPackage#getPathGroup_Base()
	 * @model dataType="org.eclipse.buckminster.cspec.IPath" required="true"
	 * @generated
	 */
	IPath getBase();

	/**
	 * Returns the value of the '<em><b>Paths</b></em>' attribute list. The list contents are of type
	 * {@link org.eclipse.core.runtime.IPath}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Paths</em>' containment reference list isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Paths</em>' attribute list.
	 * @see org.eclipse.buckminster.cspec.CspecPackage#getPathGroup_Paths()
	 * @model dataType="org.eclipse.buckminster.cspec.IPath"
	 * @generated
	 */
	EList<IPath> getPaths();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model pathDataType="org.eclipse.buckminster.cspec.IPath"
	 * @generated
	 */
	PathGroup resolve(IPath path);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.cspec.PathGroup#getBase <em>Base</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Base</em>' attribute.
	 * @see #getBase()
	 * @generated
	 */
	void setBase(IPath value);

} // PathGroup
