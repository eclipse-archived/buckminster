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
import org.eclipse.buckminster.aggregator.MappedUnit;
import org.eclipse.buckminster.aggregator.MetadataRepositoryReference;
import org.eclipse.buckminster.aggregator.p2.InstallableUnit;
import org.eclipse.buckminster.aggregator.p2.MetadataRepository;
import org.eclipse.buckminster.aggregator.p2.P2Factory;
import org.eclipse.buckminster.aggregator.p2.util.MetadataRepositoryResourceImpl;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.EList;
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
		for(Contribution contribution : aggregator.getContributions())
		{
			for(MappedRepository mappedRepository : contribution.getRepositories())
			{
				if(mappedRepository.isBranchEnabled())
				{
					org.eclipse.emf.common.util.URI repoURI = org.eclipse.emf.common.util.URI.createGenericURI("p2",
							mappedRepository.getResolvedLocation(), null);
					referencedResources.add(topSet.getResource(repoURI, false));
				}
				else
				{
					for(MappedUnit unit : mappedRepository.getUnits(false))
					{
						InstallableUnit originalIU = unit.getInstallableUnit(false);

						if(!originalIU.eIsProxy())
							unit.setInstallableUnit(P2Factory.eINSTANCE.createInstallableUnitProxy(
									mappedRepository.getLocation(), originalIU.getVersionedName()));
					}
					mappedRepository.setMetadataRepository(null);
				}
			}
		}
		for(MetadataRepositoryReference repoRef : aggregator.getValidationRepositories())
		{
			if(repoRef.isBranchEnabled())
			{
				org.eclipse.emf.common.util.URI repoURI = org.eclipse.emf.common.util.URI.createGenericURI("p2",
						repoRef.getResolvedLocation(), null);
				referencedResources.add(topSet.getResource(repoURI, false));
			}
			else
				repoRef.setMetadataRepository(null);
		}
		Iterator<Resource> allResources = topSet.getResources().iterator();

		while(allResources.hasNext())
		{
			if(!referencedResources.contains(allResources.next()))
				allResources.remove();
		}
	}

	/**
	 * Returns the main aggregator node
	 * 
	 * @param resourceSet
	 * @return the aggregator instance, or null if it is not available
	 */
	public static Aggregator getAggregator(ResourceSet resourceSet)
	{
		if(resourceSet == null)
			return null;

		EList<Resource> resources = resourceSet.getResources();
		Resource aggregatorResource = null;
		for(Resource resource : resources)
			if(resource instanceof AggregatorResourceImpl)
			{
				aggregatorResource = resource;
				break;
			}
		return aggregatorResource == null
				? null
				: (Aggregator)aggregatorResource.getContents().get(0);
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

			throw BuckminsterException.fromMessage("Error loading repository " + repoRef.getResolvedLocation());
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
		repoRef.setMetadataRepository(MetadataRepositoryResourceImpl.loadRepository(repoRef.getResolvedLocation(),
				aggregator));
	}
}
