/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap.maven.impl;

import org.eclipse.buckminster.rmap.maven.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class MavenFactoryImpl extends EFactoryImpl implements MavenFactory {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static MavenPackage getPackage() {
		return MavenPackage.eINSTANCE;
	}

	/**
	 * Creates the default factory implementation. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public static MavenFactory init() {
		try {
			MavenFactory theMavenFactory = (MavenFactory) EPackage.Registry.INSTANCE
					.getEFactory("http://www.eclipse.org/buckminster/MavenProvider-1.0");
			if (theMavenFactory != null) {
				return theMavenFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new MavenFactoryImpl();
	}

	/**
	 * Creates an instance of the factory. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public MavenFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case MavenPackage.GROUP_AND_ARTIFACT:
				return createGroupAndArtifact();
			case MavenPackage.MAP_ENTRY:
				return createMapEntry();
			case MavenPackage.MAPPINGS:
				return createMappings();
			case MavenPackage.MAVEN_PROVIDER:
				return createMavenProvider();
			case MavenPackage.SCOPES:
				return createScopes();
			case MavenPackage.SCOPE:
				return createScope();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	public MapEntry createDefaultMapEntry(String name) {
		return MavenProviderImpl.getDefaultMapEntry(name);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public GroupAndArtifact createGroupAndArtifact() {
		GroupAndArtifactImpl groupAndArtifact = new GroupAndArtifactImpl();
		return groupAndArtifact;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public MapEntry createMapEntry() {
		MapEntryImpl mapEntry = new MapEntryImpl();
		return mapEntry;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public Mappings createMappings() {
		MappingsImpl mappings = new MappingsImpl();
		return mappings;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public MavenProvider createMavenProvider() {
		MavenProviderImpl mavenProvider = new MavenProviderImpl();
		return mavenProvider;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Scope createScope() {
		ScopeImpl scope = new ScopeImpl();
		return scope;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Scopes createScopes() {
		ScopesImpl scopes = new ScopesImpl();
		return scopes;
	}

	public String getDefaultName(String groupId, String artifactId) {
		return MavenProviderImpl.getDefaultName(groupId, artifactId);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public MavenPackage getMavenPackage() {
		return (MavenPackage) getEPackage();
	}

} // MavenFactoryImpl
