/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspecxml.util;

import java.util.Map;

import org.eclipse.buckminster.cspecxml.ICSpecXMLPackage;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class CSpecXMLXMLProcessor extends XMLProcessor {

	/**
	 * Public constructor to instantiate the helper. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public CSpecXMLXMLProcessor() {
		super((EPackage.Registry.INSTANCE));
		ICSpecXMLPackage.eINSTANCE.eClass();
	}

	/**
	 * Register for "*" and "xml" file extensions the
	 * CSpecXMLResourceFactoryImpl factory. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected Map<String, Resource.Factory> getRegistrations() {
		if (registrations == null) {
			super.getRegistrations();
			registrations.put(XML_EXTENSION, new CSpecXMLResourceFactoryImpl());
			registrations.put(STAR_EXTENSION, new CSpecXMLResourceFactoryImpl());
		}
		return registrations;
	}

} // CSpecXMLXMLProcessor
