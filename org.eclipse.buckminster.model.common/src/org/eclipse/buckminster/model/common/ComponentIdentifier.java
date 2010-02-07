/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.model.common;

import java.lang.Comparable;
import org.eclipse.equinox.p2.metadata.IVersionedId;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Component Identifier</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.model.common.ComponentIdentifier#getType
 * <em>Type</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.model.common.CommonPackage#getComponentIdentifier()
 * @model superTypes="org.eclipse.buckminster.model.common.IVersionedId org.eclipse.buckminster.model.common.Comparable<org.eclipse.buckminster.model.common.ComponentIdentifier>"
 * @extends BObject
 * @generated
 */
public interface ComponentIdentifier extends BObject, IVersionedId, Comparable<ComponentIdentifier> {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(String)
	 * @see org.eclipse.buckminster.model.common.CommonPackage#getComponentIdentifier_Type()
	 * @model
	 * @generated
	 */
	String getType();

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.model.common.ComponentIdentifier#getType
	 * <em>Type</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

} // ComponentIdentifier
