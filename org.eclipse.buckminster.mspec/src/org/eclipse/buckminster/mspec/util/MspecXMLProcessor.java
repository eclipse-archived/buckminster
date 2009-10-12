/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.mspec.util;

import java.util.Map;

import org.eclipse.buckminster.mspec.MspecPackage;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

/**
 * This class contains helper methods to serialize and deserialize XML documents <!-- begin-user-doc --> <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class MspecXMLProcessor extends XMLProcessor
{

	/**
	 * Public constructor to instantiate the helper. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public MspecXMLProcessor()
	{
		super((EPackage.Registry.INSTANCE));
		MspecPackage.eINSTANCE.eClass();
	}

	/**
	 * Register for "*" and "xml" file extensions the MspecResourceFactoryImpl factory. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected Map<String, Resource.Factory> getRegistrations()
	{
		if(registrations == null)
		{
			super.getRegistrations();
			registrations.put(XML_EXTENSION, new MspecResourceFactoryImpl());
			registrations.put(STAR_EXTENSION, new MspecResourceFactoryImpl());
		}
		return registrations;
	}

} // MspecXMLProcessor
