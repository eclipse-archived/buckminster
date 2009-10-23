/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.engine.maven.metadata.util;

import java.util.Map;

import org.eclipse.buckminster.aggregator.engine.maven.metadata.MetadataPackage;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

/**
 * This class contains helper methods to serialize and deserialize XML documents <!-- begin-user-doc --> <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class MetadataXMLProcessor extends XMLProcessor
{

	/**
	 * Public constructor to instantiate the helper. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public MetadataXMLProcessor()
	{
		super((EPackage.Registry.INSTANCE));
		MetadataPackage.eINSTANCE.eClass();
	}

	/**
	 * Register for "*" and "xml" file extensions the MetadataResourceFactoryImpl factory. <!-- begin-user-doc --> <!--
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
			registrations.put(XML_EXTENSION, new MetadataResourceFactoryImpl());
			registrations.put(STAR_EXTENSION, new MetadataResourceFactoryImpl());
		}
		return registrations;
	}

} // MetadataXMLProcessor
