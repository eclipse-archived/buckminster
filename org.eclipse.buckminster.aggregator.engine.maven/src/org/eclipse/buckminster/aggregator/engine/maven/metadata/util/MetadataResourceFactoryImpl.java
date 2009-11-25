/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.engine.maven.metadata.util;

import org.eclipse.buckminster.aggregator.engine.maven.metadata.MetadataPackage;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.EPackageRegistryImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLResource;

/**
 * <!-- begin-user-doc --> The <b>Resource Factory</b> associated with the package. <!-- end-user-doc -->
 * 
 * @see org.eclipse.buckminster.aggregator.engine.maven.metadata.util.MetadataResourceImpl
 * @generated
 */
public class MetadataResourceFactoryImpl extends ResourceFactoryImpl
{
	protected ExtendedMetaData extendedMetaData;

	/**
	 * Creates an instance of the resource factory. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public MetadataResourceFactoryImpl()
	{
		super();

		extendedMetaData = new BasicExtendedMetaData(new EPackageRegistryImpl(EPackage.Registry.INSTANCE))
		{
			@Override
			public EStructuralFeature getElement(String namespace, String name)
			{
				if(namespace == null)
					namespace = MetadataPackage.eNS_URI;
				return super.getElement(namespace, name);
			}

			@Override
			public EStructuralFeature getLocalElement(EClass eClass, String namespace, String name)
			{
				if(namespace == null)
					namespace = MetadataPackage.eNS_URI;
				return super.getLocalElement(eClass, namespace, name);
			}
		};
		extendedMetaData.putPackage(null, MetadataPackage.eINSTANCE);
	}

	/**
	 * Creates an instance of the resource. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public Resource createResource(URI uri)
	{
		XMLResource result = new MetadataResourceImpl(uri);
		result.getDefaultSaveOptions().put(XMLResource.OPTION_EXTENDED_META_DATA, extendedMetaData);
		result.getDefaultLoadOptions().put(XMLResource.OPTION_EXTENDED_META_DATA, extendedMetaData);

		result.getDefaultSaveOptions().put(XMLResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);

		result.getDefaultLoadOptions().put(XMLResource.OPTION_USE_ENCODED_ATTRIBUTE_STYLE, Boolean.TRUE);
		result.getDefaultSaveOptions().put(XMLResource.OPTION_USE_ENCODED_ATTRIBUTE_STYLE, Boolean.TRUE);

		result.getDefaultLoadOptions().put(XMLResource.OPTION_USE_LEXICAL_HANDLER, Boolean.TRUE);
		return result;
	}

} // MetadataResourceFactoryImpl
