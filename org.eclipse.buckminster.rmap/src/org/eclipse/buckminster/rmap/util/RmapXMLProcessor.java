/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap.util;

import java.util.Map;

import org.eclipse.buckminster.rmap.RmapPackage;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class RmapXMLProcessor extends XMLProcessor {

	/**
	 * Public constructor to instantiate the helper. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public RmapXMLProcessor() {
		super((EPackage.Registry.INSTANCE));
		RmapPackage.eINSTANCE.eClass();
	}

	/**
	 * Register for "*" and "xml" file extensions the RmapResourceFactoryImpl
	 * factory. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	protected Map<String, Resource.Factory> getRegistrations() {
		if (registrations == null) {
			super.getRegistrations();
			registrations.put(XML_EXTENSION, new RmapResourceFactoryImpl());
			registrations.put(STAR_EXTENSION, new RmapResourceFactoryImpl());
		}
		return registrations;
	}

} // RmapXMLProcessor
