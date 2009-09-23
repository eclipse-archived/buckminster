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

import org.eclipse.buckminster.aggregator.p2.MetadataRepository;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Metadata Repository Reference</b></em>'. <!--
 * end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.MetadataRepositoryReference#getMetadataRepository <em>Metadata
 * Repository</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.MetadataRepositoryReference#getLocation <em>Location</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getMetadataRepositoryReference()
 * @model
 * @generated
 */
public interface MetadataRepositoryReference extends EnabledStatusProvider
{
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model kind="operation"
	 * @generated
	 */
	Aggregator getAggregator();

	/**
	 * Returns the value of the '<em><b>Location</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Location</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Location</em>' attribute.
	 * @see #setLocation(String)
	 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getMetadataRepositoryReference_Location()
	 * @model required="true"
	 * @generated
	 */
	String getLocation();

	/**
	 * Returns the value of the '<em><b>Metadata Repository</b></em>' reference. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Metadata Repository</em>' reference isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Metadata Repository</em>' reference.
	 * @see #setMetadataRepository(MetadataRepository)
	 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getMetadataRepositoryReference_MetadataRepository()
	 * @model
	 * @generated
	 */
	MetadataRepository getMetadataRepository();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model
	 * @generated
	 */
	MetadataRepository getMetadataRepository(boolean forceResolve);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model kind="operation"
	 * @generated
	 */
	String getResolvedLocation();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model kind="operation"
	 * @generated
	 */
	boolean isBranchEnabled();

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.MetadataRepositoryReference#getLocation
	 * <em>Location</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Location</em>' attribute.
	 * @see #getLocation()
	 * @generated
	 */
	void setLocation(String value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.aggregator.MetadataRepositoryReference#getMetadataRepository
	 * <em>Metadata Repository</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Metadata Repository</em>' reference.
	 * @see #getMetadataRepository()
	 * @generated
	 */
	void setMetadataRepository(MetadataRepository value);

} // MetadataRepositoryReference
