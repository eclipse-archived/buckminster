/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.p2;

import java.net.URI;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.equinox.internal.provisional.p2.query.IQueryable;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Metadata Repository</b></em>'. <!-- end-user-doc
 * -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.p2.MetadataRepository#getLocation <em>Location</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2.MetadataRepository#getName <em>Name</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2.MetadataRepository#getInstallableUnits <em>Installable Units</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.aggregator.p2.P2Package#getMetadataRepository()
 * @model superTypes="org.eclipse.buckminster.aggregator.p2.IQueryable"
 * @generated
 */
public interface MetadataRepository extends EObject, IQueryable
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
	 * @see org.eclipse.buckminster.aggregator.p2.P2Package#getMetadataRepository_Location()
	 * @model dataType="org.eclipse.buckminster.aggregator.URI"
	 * @generated
	 */
	URI getLocation();

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.p2.MetadataRepository#getLocation <em>Location</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Location</em>' attribute.
	 * @see #getLocation()
	 * @generated
	 */
	void setLocation(URI value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.buckminster.aggregator.p2.P2Package#getMetadataRepository_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.p2.MetadataRepository#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Installable Units</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.buckminster.aggregator.p2.InstallableUnit}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Installable Units</em>' containment reference list isn't clear, there really should be
	 * more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Installable Units</em>' containment reference list.
	 * @see org.eclipse.buckminster.aggregator.p2.P2Package#getMetadataRepository_InstallableUnits()
	 * @model containment="true" keys="id version"
	 * @generated
	 */
	EList<InstallableUnit> getInstallableUnits();

} // MetadataRepository
