/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.p2.util;

import org.eclipse.emf.common.util.URI;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;

public class MetadataRepositoryResourceFactoryImpl extends ResourceFactoryImpl
{
	public MetadataRepositoryResourceFactoryImpl()
	{
		super();
	}

	public Resource createResource(URI uri)
	{
		return new MetadataRepositoryResourceImpl(uri);
	}
}
