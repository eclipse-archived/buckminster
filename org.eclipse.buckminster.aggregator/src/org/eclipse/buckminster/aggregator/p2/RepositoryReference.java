/**
 * Copyright (c) 2006-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.p2;

import java.net.URI;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Repository Reference</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.buckminster.aggregator.p2.RepositoryReference#getLocation <em>Location</em>}</li>
 *   <li>{@link org.eclipse.buckminster.aggregator.p2.RepositoryReference#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.buckminster.aggregator.p2.RepositoryReference#getOptions <em>Options</em>}</li>
 *   <li>{@link org.eclipse.buckminster.aggregator.p2.RepositoryReference#getNickname <em>Nickname</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.buckminster.aggregator.p2.P2Package#getRepositoryReference()
 * @model
 * @generated
 */
public interface RepositoryReference extends EObject
{
	/**
	 * Returns the value of the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Location</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Location</em>' attribute.
	 * @see #setLocation(URI)
	 * @see org.eclipse.buckminster.aggregator.p2.P2Package#getRepositoryReference_Location()
	 * @model dataType="org.eclipse.buckminster.aggregator.URI"
	 * @generated
	 */
	URI getLocation();

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.p2.RepositoryReference#getLocation <em>Location</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Location</em>' attribute.
	 * @see #getLocation()
	 * @generated
	 */
	void setLocation(URI value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(int)
	 * @see org.eclipse.buckminster.aggregator.p2.P2Package#getRepositoryReference_Type()
	 * @model
	 * @generated
	 */
	int getType();

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.p2.RepositoryReference#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(int value);

	/**
	 * Returns the value of the '<em><b>Options</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Options</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Options</em>' attribute.
	 * @see #setOptions(int)
	 * @see org.eclipse.buckminster.aggregator.p2.P2Package#getRepositoryReference_Options()
	 * @model
	 * @generated
	 */
	int getOptions();

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.p2.RepositoryReference#getOptions <em>Options</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Options</em>' attribute.
	 * @see #getOptions()
	 * @generated
	 */
	void setOptions(int value);

	/**
	 * Returns the value of the '<em><b>Nickname</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Nickname</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Nickname</em>' attribute.
	 * @see #setNickname(String)
	 * @see org.eclipse.buckminster.aggregator.p2.P2Package#getRepositoryReference_Nickname()
	 * @model
	 * @generated
	 */
	String getNickname();

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.p2.RepositoryReference#getNickname <em>Nickname</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Nickname</em>' attribute.
	 * @see #getNickname()
	 * @generated
	 */
	void setNickname(String value);

} // RepositoryReference
