/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap.maven.util;

import java.util.Map;

import org.eclipse.buckminster.rmap.maven.MavenPackage;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class MavenXMLProcessor extends XMLProcessor {

	/**
	 * Public constructor to instantiate the helper. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public MavenXMLProcessor() {
		super((EPackage.Registry.INSTANCE));
		MavenPackage.eINSTANCE.eClass();
	}

	/**
	 * Register for "*" and "xml" file extensions the MavenResourceFactoryImpl
	 * factory. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected Map<String, Resource.Factory> getRegistrations() {
		if (registrations == null) {
			super.getRegistrations();
			registrations.put(XML_EXTENSION, new MavenResourceFactoryImpl());
			registrations.put(STAR_EXTENSION, new MavenResourceFactoryImpl());
		}
		return registrations;
	}

} // MavenXMLProcessor
