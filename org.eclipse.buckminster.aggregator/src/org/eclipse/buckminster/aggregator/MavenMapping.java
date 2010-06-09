/**
 * Copyright (c) 2006-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator;

import org.eclipse.buckminster.aggregator.impl.MavenMappingImpl;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Maven Mapping</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.MavenMapping#getNamePattern <em>Name Pattern</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.MavenMapping#getGroupId <em>Group Id</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.MavenMapping#getArtifactId <em>Artifact Id</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getMavenMapping()
 * @model
 * @generated
 */
public interface MavenMapping extends StatusProvider, InfosProvider
{
	MavenMapping DEFAULT_MAPPING = new MavenMappingImpl("^([^.]+(?:\\.[^.]+(?:\\.[^.]+)?)?)(?:\\.[^.]+)*$", "$1", "$0");

	/**
	 * Returns the value of the '<em><b>Artifact Id</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Artifact Id</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Artifact Id</em>' attribute.
	 * @see #setArtifactId(String)
	 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getMavenMapping_ArtifactId()
	 * @model
	 * @generated
	 */
	String getArtifactId();

	/**
	 * Returns the value of the '<em><b>Group Id</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Group Id</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Group Id</em>' attribute.
	 * @see #setGroupId(String)
	 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getMavenMapping_GroupId()
	 * @model
	 * @generated
	 */
	String getGroupId();

	/**
	 * Returns the value of the '<em><b>Name Pattern</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name Pattern</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Name Pattern</em>' attribute.
	 * @see #setNamePattern(String)
	 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getMavenMapping_NamePattern()
	 * @model
	 * @generated
	 */
	String getNamePattern();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model
	 * @generated
	 */
	MavenItem map(String installableUnitID);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.MavenMapping#getArtifactId <em>Artifact Id</em>}
	 * ' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Artifact Id</em>' attribute.
	 * @see #getArtifactId()
	 * @generated
	 */
	void setArtifactId(String value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.MavenMapping#getGroupId <em>Group Id</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Group Id</em>' attribute.
	 * @see #getGroupId()
	 * @generated
	 */
	void setGroupId(String value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.MavenMapping#getNamePattern
	 * <em>Name Pattern</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Name Pattern</em>' attribute.
	 * @see #getNamePattern()
	 * @generated
	 */
	void setNamePattern(String value);

} // MavenMapping
