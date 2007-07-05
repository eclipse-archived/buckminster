/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.internal.ctype;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.UUID;

import org.eclipse.buckminster.core.KeyConstants;
import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.cspec.AbstractResolutionBuilder;
import org.eclipse.buckminster.core.cspec.QualifiedDependency;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.model.ComponentName;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.metadata.model.DepNode;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.metadata.model.ResolvedNode;
import org.eclipse.buckminster.core.metadata.model.UnresolvedNode;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.reader.SiteFeatureReader;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.resolver.ResolverNode;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.IVersionDesignator;
import org.eclipse.buckminster.core.version.VersionFactory;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.update.core.IFeature;
import org.eclipse.update.core.IIncludedFeatureReference;
import org.eclipse.update.core.VersionedIdentifier;

/**
 * @author Thomas Hallgren
 */
public class SiteFeatureResolutionBuilder extends AbstractResolutionBuilder
{
	public synchronized DepNode build(IComponentReader[] readerHandle, boolean forResolutionAidOnly, IProgressMonitor monitor)
	throws CoreException
	{
		IComponentReader reader = readerHandle[0];
		if(!(reader instanceof SiteFeatureReader))
			throw BuckminsterException.fromMessage(getId() + " resolution builder can only work with a site.feature reader");

		monitor.beginTask(null, 100);
		try
		{
			IFeature siteFeature = ((SiteFeatureReader)reader).getFeature(MonitorUtils.subMonitor(monitor, 50));
			if(forResolutionAidOnly)
				return new ResolvedNode(new Resolution(getCSpecBuilder(siteFeature).createCSpec(), reader), null);

			NodeQuery query = reader.getNodeQuery();
			QualifiedDependency qdep = new QualifiedDependency(query.getComponentRequest(), null);
			ResolverNode rNode = buildCSpecFromSiteFeature(reader, new HashMap<ComponentName, ResolverNode>(), siteFeature, new UnresolvedNode(qdep), MonitorUtils.subMonitor(monitor, 50));
			return rNode.collectNodes(new HashMap<UUID, DepNode>(), new Stack<Resolution>(), true);
		}
		finally
		{
			monitor.done();
		}
	}

	@Override
	public String getCategory()
	{
		return KeyConstants.SITE_FEATURE_CATEGORY;
	}

	private ResolverNode buildCSpecFromSiteFeature(IComponentReader reader, Map<ComponentName, ResolverNode> nodes, IFeature siteFeature, DepNode depNode, IProgressMonitor monitor) throws CoreException
	{
		NodeQuery query = reader.getNodeQuery();
		RMContext context = query.getContext();
		ResolverNode node = getResolverNode(nodes, context, depNode.getQualifiedDependency());
		if(node.isResolved() || query.skipComponent())
		{
			MonitorUtils.complete(monitor);
			return node;
		}

		IIncludedFeatureReference[] refs = siteFeature.getRawIncludedFeatureReferences();
		int numChildren = refs.length;
		monitor.beginTask(null, 10 + numChildren * 10);

		CSpecBuilder cspecBld = getCSpecBuilder(siteFeature);
		MonitorUtils.worked(monitor, 5);

		node.startResolvingChildren(depNode);
		ResolverNode[] children = null;
		if(numChildren > 0)
		{
			ArrayList<ResolverNode> childArr = new ArrayList<ResolverNode>(numChildren);
			Provider provider = reader.getProviderMatch().getProvider();
			for(IIncludedFeatureReference ref : refs)
			{
				IFeature refFeature = ref.getFeature(MonitorUtils.subMonitor(monitor, 4));
				VersionedIdentifier vid = ref.getVersionedIdentifier();
				IVersion version = VersionFactory.OSGiType.coerce(vid.getVersion());
				IVersionDesignator vd = (version == null) ? null : VersionFactory.createExplicitDesignator(version);
				ComponentRequest request = new ComponentRequest(vid.getIdentifier(), getCategory(), vd);
				cspecBld.addDependency(request);

				VersionMatch match = new VersionMatch(version, null, provider.getSpace(), -1, null, null);
				QualifiedDependency qdep = new QualifiedDependency(request, null);
				NodeQuery childQuery = new NodeQuery(context, qdep);
				IComponentReader childReader = reader.getReaderType().getReader(provider, childQuery, match, MonitorUtils.subMonitor(monitor, 1));
				try
				{
					childArr.add(buildCSpecFromSiteFeature(childReader, nodes, refFeature, new UnresolvedNode(qdep), MonitorUtils.subMonitor(monitor, 5)));
				}
				finally
				{
					childReader.close();
				}
			}
			children = childArr.toArray(new ResolverNode[childArr.size()]);
		}
		node.setResolution(new Resolution(cspecBld.createCSpec(), reader), children);
		MonitorUtils.worked(monitor, 5);
		monitor.done();
		return node;
	}

	private static ResolverNode getResolverNode(Map<ComponentName, ResolverNode> nodes, RMContext context, QualifiedDependency qDep) throws CoreException
	{
		// We use a ComponentName as the key since we don't want the
		// designator to play a role here.
		//
		ComponentName key = qDep.getRequest().toPureComponentName();
		ResolverNode nr;
		boolean infant;
		synchronized(nodes)
		{
			nr = nodes.get(key);
			infant = (nr == null);
			if(infant)
			{
				nr = new ResolverNode(new NodeQuery(context, qDep));
				nodes.put(key, nr);
			}
		}

		if(!infant)
			nr.addDependencyQualification(qDep);
		return nr;
	}

	private CSpecBuilder getCSpecBuilder(IFeature siteFeature)
	{
		CSpecBuilder cspecBld = new CSpecBuilder();
		VersionedIdentifier vi = siteFeature.getVersionedIdentifier();
		cspecBld.setName(vi.getIdentifier());
		cspecBld.setCategory(getCategory());
		cspecBld.setVersion(VersionFactory.OSGiType.coerce(vi.getVersion()));
		return cspecBld;
	}
}

