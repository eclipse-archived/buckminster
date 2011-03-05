/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap.maven;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a
 * create method for each non-abstract class of the model. <!-- end-user-doc -->
 * 
 * @see org.eclipse.buckminster.rmap.maven.MavenPackage
 * @generated
 */
public interface MavenFactory extends EFactory {
	/**
	 * The singleton instance of the factory. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	MavenFactory eINSTANCE = org.eclipse.buckminster.rmap.maven.impl.MavenFactoryImpl.init();

	/**
	 * Creates a default <code>MapEntry</code> for the given name
	 * 
	 * @param name
	 * @return A default <code>MapEntry</code>
	 */
	MapEntry createDefaultMapEntry(String name);

	/**
	 * Returns a new object of class '<em>Group And Artifact</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Group And Artifact</em>'.
	 * @generated
	 */
	GroupAndArtifact createGroupAndArtifact();

	/**
	 * Returns a new object of class '<em>Map Entry</em>'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Map Entry</em>'.
	 * @generated
	 */
	MapEntry createMapEntry();

	/**
	 * Returns a new object of class '<em>Mappings</em>'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Mappings</em>'.
	 * @generated
	 */
	Mappings createMappings();

	/**
	 * Returns a new object of class '<em>Provider</em>'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Provider</em>'.
	 * @generated
	 */
	MavenProvider createMavenProvider();

	/**
	 * Returns a new object of class '<em>Scope</em>'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Scope</em>'.
	 * @generated
	 */
	Scope createScope();

	/**
	 * Returns a new object of class '<em>Scopes</em>'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Scopes</em>'.
	 * @generated
	 */
	Scopes createScopes();

	/**
	 * Returns the default name for the given groupId/artifactId mapping
	 * 
	 * @param groupId
	 * @param artifactId
	 * @return The default name
	 */
	String getDefaultName(String groupId, String artifactId);

	/**
	 * Returns the package supported by this factory. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the package supported by this factory.
	 * @generated
	 */
	MavenPackage getMavenPackage();

} // MavenFactory
