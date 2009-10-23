/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.engine.maven.metadata;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Versions</b></em>'. <!-- end-user-doc -->
 * 
 * <!-- begin-model-doc --> 1.0.0
 * 
 * Container for all the versions in the repository for this artifact.
 * 
 * <!-- end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.metadata.Versions#getVersion <em>Version</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.aggregator.engine.maven.metadata.MetadataPackage#getVersions()
 * @model extendedMetaData="name='Versions' kind='elementOnly'"
 * @generated
 */
public interface Versions extends EObject
{
	/**
	 * Returns the value of the '<em><b>Version</b></em>' attribute list. The list contents are of type
	 * {@link java.lang.String}. <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
	 * 
	 * 1.0.0
	 * 
	 * 
	 * A version contained in the repository for this artifact.
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Version</em>' attribute list.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.metadata.MetadataPackage#getVersions_Version()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='element' name='version' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<String> getVersion();

} // Versions
