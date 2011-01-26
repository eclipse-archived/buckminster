/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.mspec.util;

import org.eclipse.buckminster.model.common.CommonPackage;
import org.eclipse.buckminster.mspec.MspecPackage;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.ElementHandlerImpl;

/**
 * <!-- begin-user-doc --> The <b>Resource Factory</b> associated with the
 * package. <!-- end-user-doc -->
 * 
 * @see org.eclipse.buckminster.mspec.util.MspecResourceImpl
 * @generated
 */
public class MspecResourceFactoryImpl extends ResourceFactoryImpl {
	/**
	 * Creates an instance of the resource factory. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public MspecResourceFactoryImpl() {
		super();
	}

	@Override
	public Resource createResource(URI uri) {
		// We subclass the Properties instance but we don't want to use the
		// Common namespace so we redefine the namespace here.
		//
		ExtendedMetaData extMeta = ExtendedMetaData.INSTANCE;
		extMeta.setNamespace(CommonPackage.Literals.PROPERTIES__PROPERTY_CONSTANTS, MspecPackage.eNS_URI);
		extMeta.setNamespace(CommonPackage.Literals.PROPERTIES__PROPERTY_ELEMENTS, MspecPackage.eNS_URI);
		XMLResource result = (XMLResource) createResourceGen(uri);

		result.getDefaultSaveOptions().put(XMLResource.OPTION_SCHEMA_LOCATION, Boolean.FALSE);
		result.getDefaultSaveOptions().put(XMLResource.OPTION_ELEMENT_HANDLER, new ElementHandlerImpl(false));

		result.getDefaultLoadOptions().put(XMLResource.OPTION_USE_ENCODED_ATTRIBUTE_STYLE, Boolean.FALSE);
		result.getDefaultSaveOptions().put(XMLResource.OPTION_USE_ENCODED_ATTRIBUTE_STYLE, Boolean.FALSE);

		result.getDefaultLoadOptions().put(XMLResource.OPTION_SUPPRESS_DOCUMENT_ROOT, Boolean.TRUE);
		return result;
	}

	/**
	 * Creates an instance of the resource. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public Resource createResourceGen(URI uri) {
		XMLResource result = new MspecResourceImpl(uri);
		result.getDefaultSaveOptions().put(XMLResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE);
		result.getDefaultLoadOptions().put(XMLResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE);

		result.getDefaultSaveOptions().put(XMLResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);

		result.getDefaultLoadOptions().put(XMLResource.OPTION_USE_ENCODED_ATTRIBUTE_STYLE, Boolean.TRUE);
		result.getDefaultSaveOptions().put(XMLResource.OPTION_USE_ENCODED_ATTRIBUTE_STYLE, Boolean.TRUE);

		result.getDefaultLoadOptions().put(XMLResource.OPTION_USE_LEXICAL_HANDLER, Boolean.TRUE);
		return result;
	}

} // MspecResourceFactoryImpl
