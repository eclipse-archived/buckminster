/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap.util;

import org.eclipse.buckminster.model.common.CommonPackage;
import org.eclipse.buckminster.rmap.RmapPackage;
import org.eclipse.emf.common.util.URI;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.eclipse.emf.ecore.util.ExtendedMetaData;

import org.eclipse.emf.ecore.xmi.XMLResource;

/**
 * <!-- begin-user-doc --> The <b>Resource Factory</b> associated with the
 * package. <!-- end-user-doc -->
 * @see org.eclipse.buckminster.rmap.util.RmapResourceImpl
 * @generated
 */
public class RmapResourceFactoryImpl extends ResourceFactoryImpl {
	/**
	 * Creates an instance of the resource factory.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	public RmapResourceFactoryImpl() {
		super();
	}

	@Override
	public Resource createResource(URI uri) {
		// We subclass the Properties instance but we don't want to use the
		// Common
		// namespace so we redefine the namespace here.
		//
		ExtendedMetaData extMeta = ExtendedMetaData.INSTANCE;
		extMeta.setNamespace(CommonPackage.Literals.PROPERTIES__PROPERTY_CONSTANTS, RmapPackage.eNS_URI);
		extMeta.setNamespace(CommonPackage.Literals.PROPERTIES__PROPERTY_ELEMENTS, RmapPackage.eNS_URI);

		XMLResource result = (XMLResource) createResourceGen(uri);
		// Don't want the document root in the editor
		result.getDefaultLoadOptions().put(XMLResource.OPTION_SUPPRESS_DOCUMENT_ROOT, Boolean.TRUE);
		return result;
	}

	/**
	 * Creates an instance of the resource.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	public Resource createResourceGen(URI uri) {
		XMLResource result = new RmapResourceImpl(uri);
		result.getDefaultSaveOptions().put(XMLResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE);
		result.getDefaultLoadOptions().put(XMLResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE);

		result.getDefaultSaveOptions().put(XMLResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);

		result.getDefaultLoadOptions().put(XMLResource.OPTION_USE_ENCODED_ATTRIBUTE_STYLE, Boolean.TRUE);
		result.getDefaultSaveOptions().put(XMLResource.OPTION_USE_ENCODED_ATTRIBUTE_STYLE, Boolean.TRUE);

		result.getDefaultLoadOptions().put(XMLResource.OPTION_USE_LEXICAL_HANDLER, Boolean.TRUE);
		return result;
	}
} // RmapResourceFactoryImpl
