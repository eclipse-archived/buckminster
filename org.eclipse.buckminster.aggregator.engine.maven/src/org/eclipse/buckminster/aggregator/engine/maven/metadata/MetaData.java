/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.engine.maven.metadata;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Meta Data</b></em>'. <!-- end-user-doc -->
 * 
 * <!-- begin-model-doc --> 1.0.0
 * 
 * The &lt;code&gt;&amp;lt;metadata&amp;gt;&lt;/code&gt; element is the root of the descriptor. The following table
 * lists all of the possible child elements.
 * 
 * <!-- end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.metadata.MetaData#getGroupId <em>Group Id</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.metadata.MetaData#getArtifactId <em>Artifact Id</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.metadata.MetaData#getVersion <em>Version</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.metadata.MetaData#getVersioning <em>Versioning</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.aggregator.engine.maven.metadata.MetadataPackage#getMetaData()
 * @model extendedMetaData="name='MetaData' kind='elementOnly'"
 * @generated
 */
public interface MetaData extends EObject
{
	/**
	 * Returns the value of the '<em><b>Artifact Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 1.0.0
	 * 
	 * 
	 * The identifier for this artifact that is unique within the group given by the group ID. An artifact is something
	 * that is either produced or used by a project. Examples of artifacts produced by Maven for a project include:
	 * JARs, source and binary distributions, and WARs.
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Artifact Id</em>' attribute.
	 * @see #setArtifactId(String)
	 * @see org.eclipse.buckminster.aggregator.engine.maven.metadata.MetadataPackage#getMetaData_ArtifactId()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='artifactId' namespace='##targetNamespace'"
	 * @generated
	 */
	String getArtifactId();

	/**
	 * Returns the value of the '<em><b>Group Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
	 * begin-model-doc -->
	 * 
	 * 1.0.0
	 * 
	 * 
	 * A universally unique identifier for a project. It is normal to use a fully-qualified package name to distinguish
	 * it from other projects with a similar name (eg. &lt;code&gt;org.apache.maven&lt;/code&gt;).
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Group Id</em>' attribute.
	 * @see #setGroupId(String)
	 * @see org.eclipse.buckminster.aggregator.engine.maven.metadata.MetadataPackage#getMetaData_GroupId()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='groupId' namespace='##targetNamespace'"
	 * @generated
	 */
	String getGroupId();

	/**
	 * Returns the value of the '<em><b>Version</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
	 * begin-model-doc -->
	 * 
	 * 1.0.0
	 * 
	 * 
	 * The current version of the artifact produced by this project.
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Version</em>' attribute.
	 * @see #setVersion(String)
	 * @see org.eclipse.buckminster.aggregator.engine.maven.metadata.MetadataPackage#getMetaData_Version()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='version' namespace='##targetNamespace'"
	 * @generated
	 */
	String getVersion();

	/**
	 * Returns the value of the '<em><b>Versioning</b></em>' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc --> <!-- begin-model-doc -->
	 * 
	 * 1.0.0
	 * 
	 * 
	 * Container for he set of versions in the repository for this artifact and the last update time.
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Versioning</em>' containment reference.
	 * @see #setVersioning(Versioning)
	 * @see org.eclipse.buckminster.aggregator.engine.maven.metadata.MetadataPackage#getMetaData_Versioning()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='versioning' namespace='##targetNamespace'"
	 * @generated
	 */
	Versioning getVersioning();

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.engine.maven.metadata.MetaData#getArtifactId
	 * <em>Artifact Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Artifact Id</em>' attribute.
	 * @see #getArtifactId()
	 * @generated
	 */
	void setArtifactId(String value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.engine.maven.metadata.MetaData#getGroupId
	 * <em>Group Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Group Id</em>' attribute.
	 * @see #getGroupId()
	 * @generated
	 */
	void setGroupId(String value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.engine.maven.metadata.MetaData#getVersion
	 * <em>Version</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Version</em>' attribute.
	 * @see #getVersion()
	 * @generated
	 */
	void setVersion(String value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.engine.maven.metadata.MetaData#getVersioning
	 * <em>Versioning</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Versioning</em>' containment reference.
	 * @see #getVersioning()
	 * @generated
	 */
	void setVersioning(Versioning value);

} // MetaData
