/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap.psf.util;

import java.util.Map;

import org.eclipse.buckminster.rmap.psf.PsfPackage;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class PsfXMLProcessor extends XMLProcessor {

	/**
	 * Public constructor to instantiate the helper. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public PsfXMLProcessor() {
		super((EPackage.Registry.INSTANCE));
		PsfPackage.eINSTANCE.eClass();
	}

	/**
	 * Register for "*" and "xml" file extensions the PsfResourceFactoryImpl
	 * factory. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected Map<String, Resource.Factory> getRegistrations() {
		if (registrations == null) {
			super.getRegistrations();
			registrations.put(XML_EXTENSION, new PsfResourceFactoryImpl());
			registrations.put(STAR_EXTENSION, new PsfResourceFactoryImpl());
		}
		return registrations;
	}

} // PsfXMLProcessor
