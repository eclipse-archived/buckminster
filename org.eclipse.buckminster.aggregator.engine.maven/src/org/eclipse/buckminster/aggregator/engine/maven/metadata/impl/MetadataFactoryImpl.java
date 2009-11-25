/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.engine.maven.metadata.impl;

import org.eclipse.buckminster.aggregator.engine.maven.metadata.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!-- end-user-doc -->
 * 
 * @generated
 */
public class MetadataFactoryImpl extends EFactoryImpl implements MetadataFactory
{
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static MetadataPackage getPackage()
	{
		return MetadataPackage.eINSTANCE;
	}

	/**
	 * Creates the default factory implementation. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static MetadataFactory init()
	{
		try
		{
			MetadataFactory theMetadataFactory = (MetadataFactory)EPackage.Registry.INSTANCE.getEFactory("http://maven.apache.org/METADATA/1.0.0");
			if(theMetadataFactory != null)
			{
				return theMetadataFactory;
			}
		}
		catch(Exception exception)
		{
			EcorePlugin.INSTANCE.log(exception);
		}
		return new MetadataFactoryImpl();
	}

	/**
	 * Creates an instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public MetadataFactoryImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass)
	{
		switch(eClass.getClassifierID())
		{
		case MetadataPackage.DOCUMENT_ROOT:
			return createDocumentRoot();
		case MetadataPackage.META_DATA:
			return createMetaData();
		case MetadataPackage.VERSIONING:
			return createVersioning();
		case MetadataPackage.VERSIONS:
			return createVersions();
		default:
			throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public DocumentRoot createDocumentRoot()
	{
		DocumentRootImpl documentRoot = new DocumentRootImpl();
		return documentRoot;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public MetaData createMetaData()
	{
		MetaDataImpl metaData = new MetaDataImpl();
		return metaData;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Versioning createVersioning()
	{
		VersioningImpl versioning = new VersioningImpl();
		return versioning;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Versions createVersions()
	{
		VersionsImpl versions = new VersionsImpl();
		return versions;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public MetadataPackage getMetadataPackage()
	{
		return (MetadataPackage)getEPackage();
	}

} // MetadataFactoryImpl
