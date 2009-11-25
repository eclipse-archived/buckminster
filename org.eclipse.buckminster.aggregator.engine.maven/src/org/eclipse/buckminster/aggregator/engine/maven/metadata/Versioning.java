/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.engine.maven.metadata;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Versioning</b></em>'. <!-- end-user-doc -->
 * 
 * <!-- begin-model-doc --> 1.0.0
 * 
 * Container for the current set of versions and the last update element.
 * 
 * <!-- end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.metadata.Versioning#getRelease <em>Release</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.metadata.Versioning#getVersions <em>Versions</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.metadata.Versioning#getLastUpdated <em>Last Updated</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.aggregator.engine.maven.metadata.MetadataPackage#getVersioning()
 * @model extendedMetaData="name='Versioning' kind='elementOnly'"
 * @generated
 */
public interface Versioning extends EObject
{
	/**
	 * Returns the value of the '<em><b>Last Updated</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 1.0.0
	 * 
	 * 
	 * The timinig of the last update of the current version
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Last Updated</em>' attribute.
	 * @see #setLastUpdated(String)
	 * @see org.eclipse.buckminster.aggregator.engine.maven.metadata.MetadataPackage#getVersioning_LastUpdated()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='lastUpdated' namespace='##targetNamespace'"
	 * @generated
	 */
	String getLastUpdated();

	/**
	 * Returns the value of the '<em><b>Release</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
	 * begin-model-doc -->
	 * 
	 * 1.0.0
	 * 
	 * 
	 * The latest release version of the project.
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Release</em>' attribute.
	 * @see #setRelease(String)
	 * @see org.eclipse.buckminster.aggregator.engine.maven.metadata.MetadataPackage#getVersioning_Release()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='release' namespace='##targetNamespace'"
	 * @generated
	 */
	String getRelease();

	/**
	 * Returns the value of the '<em><b>Versions</b></em>' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc --> <!-- begin-model-doc -->
	 * 
	 * 1.0.0
	 * 
	 * 
	 * The minimum version of Maven required to build the project, or to use this plugin.
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Versions</em>' containment reference.
	 * @see #setVersions(Versions)
	 * @see org.eclipse.buckminster.aggregator.engine.maven.metadata.MetadataPackage#getVersioning_Versions()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='versions' namespace='##targetNamespace'"
	 * @generated
	 */
	Versions getVersions();

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.engine.maven.metadata.Versioning#getLastUpdated
	 * <em>Last Updated</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Last Updated</em>' attribute.
	 * @see #getLastUpdated()
	 * @generated
	 */
	void setLastUpdated(String value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.engine.maven.metadata.Versioning#getRelease
	 * <em>Release</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Release</em>' attribute.
	 * @see #getRelease()
	 * @generated
	 */
	void setRelease(String value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.engine.maven.metadata.Versioning#getVersions
	 * <em>Versions</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Versions</em>' containment reference.
	 * @see #getVersions()
	 * @generated
	 */
	void setVersions(Versions value);

} // Versioning
