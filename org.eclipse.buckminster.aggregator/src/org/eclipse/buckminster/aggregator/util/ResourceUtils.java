/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.aggregator.util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.buckminster.aggregator.Aggregator;
import org.eclipse.buckminster.aggregator.Contribution;
import org.eclipse.buckminster.aggregator.MappedRepository;
import org.eclipse.buckminster.aggregator.MetadataRepositoryReference;
import org.eclipse.buckminster.aggregator.p2.MetadataRepository;
import org.eclipse.buckminster.aggregator.p2.util.MetadataRepositoryResourceImpl;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

/**
 * Utilities for managing aggregator specific resources
 * 
 * @author filip.hrbek@cloudsmith.com
 */
public class ResourceUtils
{
	/**
	 * Cleans up unreferenced resources on demand
	 * 
	 * @param aggregator
	 */
	public static void cleanUpResources(Aggregator aggregator)
	{
		Resource topResource = aggregator.eResource();
		ResourceSet topSet = topResource.getResourceSet();
		Set<Resource> referencedResources = new HashSet<Resource>();
		referencedResources.add(topResource);
		for(Contribution contribution : aggregator.getContributions(true))
		{
			for(MappedRepository mappedRepository : contribution.getRepositories(true))
			{
				org.eclipse.emf.common.util.URI repoURI = org.eclipse.emf.common.util.URI.createGenericURI("p2",
						mappedRepository.getLocation(), null);
				referencedResources.add(topSet.getResource(repoURI, false));
			}
		}
		for(MetadataRepositoryReference repoRef : aggregator.getValidationRepositories(true))
		{
			org.eclipse.emf.common.util.URI repoURI = org.eclipse.emf.common.util.URI.createGenericURI("p2",
					repoRef.getLocation(), null);
			referencedResources.add(topSet.getResource(repoURI, false));
		}
		Iterator<Resource> allResources = topSet.getResources().iterator();

		while(allResources.hasNext())
		{
			if(!referencedResources.contains(allResources.next()))
				allResources.remove();
		}
	}

	/**
	 * Tries to get metadata repository from mapped repository. If it fails to load, an exception is thrown.
	 * 
	 * @param repoRef
	 * @return
	 * @throws CoreException
	 */
	public static MetadataRepository getMetadataRepository(MetadataRepositoryReference repoRef) throws CoreException
	{
		MetadataRepository mdr = repoRef.getMetadataRepository();

		if(mdr == null)
		{
			Resource resource = repoRef.eResource();
			if(resource != null && resource instanceof MetadataRepositoryResourceImpl
					&& ((MetadataRepositoryResourceImpl)resource).getLastException() != null)
				throw BuckminsterException.wrap(((MetadataRepositoryResourceImpl)resource).getLastException());

			throw BuckminsterException.fromMessage("Unable to load repository " + repoRef.getLocation());
		}

		return mdr;
	}

	/**
	 * Loads resource for specified repository
	 * 
	 * @param repoRef
	 */
	public static void loadResourceForMappedRepository(MetadataRepositoryReference repoRef)
	{
		if(repoRef.getLocation() == null)
			return;
		Aggregator aggregator = repoRef.getAggregator();
		MetadataRepositoryResourceImpl.loadRepository(repoRef.getLocation(), aggregator);
	}
}
