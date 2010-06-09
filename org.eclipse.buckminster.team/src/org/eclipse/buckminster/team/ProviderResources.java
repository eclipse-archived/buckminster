/*******************************************************************************
 * Copyright (c) 2010, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.team;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.mapping.ResourceTraversal;
import org.eclipse.team.core.RepositoryProvider;

/**
 * A helper class dividing resources associated with a single
 * {@link RepositoryProvider} by depth. Based on (in fact almost identical to):
 * <code>org.eclipse.team.internal.ccvs.ui.operations.RepositoryProviderOperation.TraversalMapEntry</code>
 * 
 * @author michal.ruzicka@cloudsmith.com
 * 
 */
class ProviderResources {

	// The provider for this entry
	private RepositoryProvider provider;
	// Files are always shallow
	private List<IResource> files = new ArrayList<IResource>();
	// Not sure what to do with zero depth folders but we'll record them
	private List<IResource> zeroFolders = new ArrayList<IResource>();
	// Non-recursive folder
	private List<IResource> shallowFolders = new ArrayList<IResource>();
	// Recursive folders
	private List<IResource> deepFolders = new ArrayList<IResource>();

	public ProviderResources(RepositoryProvider provider) {
		this.provider = provider;
	}

	/**
	 * Add the resources from the traversal to the entry.
	 * 
	 * @param traversal
	 *            the traversal
	 */
	public void add(ResourceTraversal traversal) {
		for (IResource resource : traversal.getResources()) {
			if (resource.getProject().equals(provider.getProject())) {
				if (resource.getType() == IResource.FILE) {
					files.add(resource);
				} else {
					switch (traversal.getDepth()) {
						case IResource.DEPTH_ZERO:
							zeroFolders.add(resource);
							break;
						case IResource.DEPTH_ONE:
							shallowFolders.add(resource);
							break;
						case IResource.DEPTH_INFINITE:
							deepFolders.add(resource);
							break;
						default:
							deepFolders.add(resource);
					}
				}
			}
		}
	}

	/**
	 * Add the resources from the traversals to the entry.
	 * 
	 * @param traversals
	 *            the traversals
	 */
	public void add(ResourceTraversal[] traversals) {
		for (ResourceTraversal traversal : traversals) {
			add(traversal);
		}
	}

	/**
	 * Return the resources to be included in a deep operation. If there are no
	 * shallow folders, this will include any files.
	 * 
	 * @return
	 */
	public IResource[] getDeepResources() {
		if (deepFolders.isEmpty())
			return new IResource[0];
		if (!shallowFolders.isEmpty())
			return deepFolders.toArray(new IResource[deepFolders.size()]);
		List<IResource> result = new ArrayList<IResource>();
		result.addAll(deepFolders);
		result.addAll(files);
		return result.toArray(new IResource[result.size()]);
	}

	/**
	 * Return the folders that are depth zero.
	 */
	public IResource[] getNontraversedFolders() {
		return zeroFolders.toArray(new IResource[zeroFolders.size()]);
	}

	/**
	 * Return the repository provider for this map entry.
	 * 
	 * @return the provider for this entry
	 */
	public RepositoryProvider getProvider() {
		return provider;
	}

	/**
	 * Return the resources that can be included in a shallow operation. Include
	 * files with the shallow resources if there are shallow folders or if there
	 * are no shallow or deep folders.
	 * 
	 * @return the resources that can be included in a shallow operation
	 */
	public IResource[] getShallowResources() {
		if (shallowFolders.isEmpty() && deepFolders.isEmpty() && !files.isEmpty()) {
			return files.toArray(new IResource[files.size()]);
		}
		if (!shallowFolders.isEmpty()) {
			if (files.isEmpty())
				return shallowFolders.toArray(new IResource[shallowFolders.size()]);
			List<IResource> result = new ArrayList<IResource>();
			result.addAll(shallowFolders);
			result.addAll(files);
			return result.toArray(new IResource[result.size()]);
		}
		return new IResource[0];
	}

}
