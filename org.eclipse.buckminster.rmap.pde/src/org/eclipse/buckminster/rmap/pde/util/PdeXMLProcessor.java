/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap.pde.util;

import java.util.Map;

import org.eclipse.buckminster.rmap.pde.PdePackage;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class PdeXMLProcessor extends XMLProcessor {

	/**
	 * Public constructor to instantiate the helper. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public PdeXMLProcessor() {
		super((EPackage.Registry.INSTANCE));
		PdePackage.eINSTANCE.eClass();
	}

	/**
	 * Register for "*" and "xml" file extensions the PdeResourceFactoryImpl
	 * factory. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected Map<String, Resource.Factory> getRegistrations() {
		if (registrations == null) {
			super.getRegistrations();
			registrations.put(XML_EXTENSION, new PdeResourceFactoryImpl());
			registrations.put(STAR_EXTENSION, new PdeResourceFactoryImpl());
		}
		return registrations;
	}

} // PdeXMLProcessor
