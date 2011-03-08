/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.model.common;

import org.eclipse.equinox.p2.metadata.IVersionedId;
import org.eclipse.equinox.p2.metadata.Version;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Component Identifier</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.buckminster.model.common.ComponentIdentifier#getVersion
 * <em>Version</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.model.common.CommonPackage#getComponentIdentifier()
 * @model superTypes=
 *        "org.eclipse.buckminster.model.common.ComponentName org.eclipse.buckminster.model.common.IVersionedId"
 * @generated
 */
public interface ComponentIdentifier extends ComponentName, IVersionedId {
	/**
	 * Returns the value of the '<em><b>Version</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Version</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Version</em>' attribute.
	 * @see #setVersion(Version)
	 * @see org.eclipse.buckminster.model.common.CommonPackage#getComponentIdentifier_Version()
	 * @model dataType="org.eclipse.buckminster.model.common.Version"
	 * @generated
	 */
	@Override
	Version getVersion();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
	 * <p>
	 * Match this <code>ComponentIdentifier</code> with another instance. The
	 * match is done as follows
	 * </p>
	 * <ul>
	 * <li>The match is always false if the id differ</li>
	 * <li>The types match if both instances have equal types or if a type is
	 * missing</li>
	 * <li>The versions match if both instances have equal versions or if a
	 * version is missing</li>
	 * </ul>
	 * <!-- end-model-doc -->
	 * 
	 * @model
	 * @generated
	 */
	boolean matches(ComponentIdentifier ci);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.model.common.ComponentIdentifier#getVersion
	 * <em>Version</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @param value
	 *            the new value of the '<em>Version</em>' attribute.
	 * @see #getVersion()
	 * @generated
	 */
	void setVersion(Version value);

} // ComponentIdentifier
