/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap.maven;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Scopes</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.rmap.maven.Scopes#getScope <em>Scope</em>}
 * </li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.rmap.maven.MavenPackage#getScopes()
 * @model
 * @generated
 */
public interface Scopes extends EObject {
	/**
	 * Returns the value of the '<em><b>Scope</b></em>' containment reference
	 * list. The list contents are of type
	 * {@link org.eclipse.buckminster.rmap.maven.Scope}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Scope</em>' containment reference list isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Scope</em>' containment reference list.
	 * @see org.eclipse.buckminster.rmap.maven.MavenPackage#getScopes_Scope()
	 * @model containment="true" extendedMetaData=
	 *        "kind='element' name='scope' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<Scope> getScope();

} // Scopes
