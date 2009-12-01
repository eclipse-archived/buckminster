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
 * <li>{@link org.eclipse.buckminster.aggregator.MetadataRepositoryReference#getNature <em>Nature</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getMetadataRepositoryReference()
 * @model
 * @generated
 */
public interface MetadataRepositoryReference extends EnabledStatusProvider, StatusProvider, InfosProvider
{
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model
	 * @generated
	 */
	void cancelRepositoryLoad();

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
	 * Returns the value of the '<em><b>Nature</b></em>' attribute. The default value is <code>"p2"</code>. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Nature</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Nature</em>' attribute.
	 * @see #setNature(String)
	 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getMetadataRepositoryReference_Nature()
	 * @model default="p2" required="true"
	 * @generated
	 */
	String getNature();

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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model
	 * @generated
	 */
	void onRepositoryLoad();

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

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.MetadataRepositoryReference#getNature
	 * <em>Nature</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Nature</em>' attribute.
	 * @see #getNature()
	 * @generated
	 */
	void setNature(String value);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model
	 * @generated
	 */
	void startRepositoryLoad(boolean forceReload);

} // MetadataRepositoryReference
