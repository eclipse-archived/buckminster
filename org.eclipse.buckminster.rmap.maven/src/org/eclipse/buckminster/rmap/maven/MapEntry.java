/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap.maven;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Map Entry</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.rmap.maven.MapEntry#getName <em>Name</em>}
 * </li>
 * <li>{@link org.eclipse.buckminster.rmap.maven.MapEntry#getAliases <em>Aliases
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.rmap.maven.MavenPackage#getMapEntry()
 * @model
 * @generated
 */
public interface MapEntry extends GroupAndArtifact {
	/**
	 * Returns the value of the '<em><b>Aliases</b></em>' reference list. The
	 * list contents are of type
	 * {@link org.eclipse.buckminster.rmap.maven.GroupAndArtifact}. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Aliases</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Aliases</em>' reference list.
	 * @see org.eclipse.buckminster.rmap.maven.MavenPackage#getMapEntry_Aliases()
	 * @model resolveProxies="false"
	 *        extendedMetaData="name='alias' kind='element'"
	 * @generated
	 */
	EList<GroupAndArtifact> getAliases();

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.buckminster.rmap.maven.MavenPackage#getMapEntry_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.rmap.maven.MapEntry#getName <em>Name</em>}
	 * ' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // MapEntry
