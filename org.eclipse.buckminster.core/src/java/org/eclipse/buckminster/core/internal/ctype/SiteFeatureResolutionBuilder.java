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

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.cspec.AbstractResolutionBuilder;
import org.eclipse.buckminster.core.cspec.QualifiedDependency;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.builder.ComponentRequestBuilder;
import org.eclipse.buckminster.core.cspec.model.ComponentName;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.ctype.MissingCSpecSourceException;
import org.eclipse.buckminster.core.helpers.FilterUtils;
import org.eclipse.buckminster.core.metadata.model.BOMNode;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.metadata.model.UnresolvedNode;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.reader.SiteFeatureReader;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.resolver.ResolverNode;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.VersionHelper;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.PluginVersionIdentifier;
import org.eclipse.equinox.internal.provisional.p2.core.Version;
import org.eclipse.equinox.internal.provisional.p2.core.VersionFormat;
import org.eclipse.osgi.util.NLS;
import org.eclipse.update.core.IFeature;
import org.eclipse.update.core.IIncludedFeatureReference;
import org.eclipse.update.core.VersionedIdentifier;

/**
 * @author Thomas Hallgren
 */
@SuppressWarnings( { "restriction", "deprecation" })
public class SiteFeatureResolutionBuilder extends AbstractResolutionBuilder
{
	private static ResolverNode getResolverNode(Map<ComponentName, ResolverNode> nodes, RMContext context,
			QualifiedDependency qDep, String tagInfo) throws CoreException
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
				nr = new ResolverNode(context.getNodeQuery(qDep), tagInfo);
				nodes.put(key, nr);
			}
		}

		if(!infant)
			nr.addDependencyQualification(qDep, tagInfo);
		return nr;
	}

	public synchronized BOMNode build(IComponentReader[] readerHandle, boolean forResolutionAidOnly,
			IProgressMonitor monitor) throws CoreException
	{
		IComponentReader reader = readerHandle[0];
		if(!(reader instanceof SiteFeatureReader))
			throw BuckminsterException.fromMessage(NLS.bind(
					Messages._0_resolution_builder_can_only_work_with_a_site_feature_reader, getId()));

		monitor.beginTask(null, 100);
		try
		{
			IFeature siteFeature = ((SiteFeatureReader)reader).getFeature(MonitorUtils.subMonitor(monitor, 50));
			if(siteFeature == null)
				throw new MissingCSpecSourceException(reader.getProviderMatch());

			if(forResolutionAidOnly)
				return createNode(reader, getCSpecBuilder(siteFeature), null);

			NodeQuery query = reader.getNodeQuery();
			QualifiedDependency qdep = new QualifiedDependency(query.getComponentRequest(), null);
			ResolverNode rNode = buildCSpecFromSiteFeature(reader, new HashMap<ComponentName, ResolverNode>(),
					siteFeature, new UnresolvedNode(qdep), null, MonitorUtils.subMonitor(monitor, 50));
			BOMNode node = rNode.collectNodes(new HashMap<UUID, BOMNode>(), new Stack<Resolution>(), true);
			if(node == null)
				node = new UnresolvedNode(query.getQualifiedDependency());
			return node;
		}
		finally
		{
			monitor.done();
		}
	}

	@Override
	public String getComponentTypeID()
	{
		return IComponentType.ECLIPSE_SITE_FEATURE;
	}

	private ResolverNode buildCSpecFromSiteFeature(IComponentReader reader, Map<ComponentName, ResolverNode> nodes,
			IFeature siteFeature, BOMNode depNode, String tagInfo, IProgressMonitor monitor) throws CoreException
	{
		NodeQuery query = reader.getNodeQuery();
		RMContext context = query.getContext();
		ResolverNode node = getResolverNode(nodes, context, depNode.getQualifiedDependency(), tagInfo);
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
			QualifiedDependency qDeps[] = new QualifiedDependency[numChildren];
			VersionMatch matches[] = new VersionMatch[numChildren];

			IComponentType ctype = CorePlugin.getDefault().getComponentType(IComponentType.ECLIPSE_SITE_FEATURE);
			ArrayList<ResolverNode> childArr = new ArrayList<ResolverNode>(numChildren);
			Provider provider = reader.getProviderMatch().getProvider();
			for(int idx = 0; idx < numChildren; ++idx)
			{
				IIncludedFeatureReference ref = refs[idx];
				VersionedIdentifier vid = ref.getVersionedIdentifier();
				PluginVersionIdentifier pvi = vid.getVersion();
				Version version = (pvi == null)
						? null
						: VersionFormat.OSGI_FORMAT.parse(pvi.toString());
				ComponentRequestBuilder bld = cspecBld.createDependencyBuilder();
				bld.setName(vid.getIdentifier());
				bld.setComponentTypeID(getComponentTypeID());
				bld.setVersionRange(VersionHelper.exactRange(version));
				bld.setFilter(FilterUtils.createFilter(ref.getOS(), ref.getWS(), ref.getOSArch(), ref.getName()));
				cspecBld.addDependency(bld);
				matches[idx] = new VersionMatch(version, null, -1, null, null);
				qDeps[idx] = new QualifiedDependency(bld.createComponentRequest(), null);
			}
			String childTagInfo = cspecBld.getTagInfo(tagInfo);

			for(int idx = 0; idx < numChildren; ++idx)
			{
				QualifiedDependency qdep = qDeps[idx];
				NodeQuery childQuery = context.getNodeQuery(qdep);
				IFeature refFeature = refs[idx].getFeature(MonitorUtils.subMonitor(monitor, 4));
				IComponentReader childReader = reader.getReaderType().getReader(provider, ctype, childQuery,
						matches[idx], MonitorUtils.subMonitor(monitor, 1));
				try
				{
					childArr.add(buildCSpecFromSiteFeature(childReader, nodes, refFeature, new UnresolvedNode(qdep),
							childTagInfo, MonitorUtils.subMonitor(monitor, 5)));
				}
				finally
				{
					IOUtils.close(childReader);
				}
			}
			children = childArr.toArray(new ResolverNode[childArr.size()]);
		}
		node.setResolution(createResolution(reader, cspecBld, null), children);
		MonitorUtils.worked(monitor, 5);
		monitor.done();
		return node;
	}

	private CSpecBuilder getCSpecBuilder(IFeature siteFeature)
	{
		CSpecBuilder cspecBld = new CSpecBuilder();
		VersionedIdentifier vi = siteFeature.getVersionedIdentifier();
		cspecBld.setName(vi.getIdentifier());
		cspecBld.setComponentTypeID(getComponentTypeID());
		PluginVersionIdentifier pvi = vi.getVersion();
		cspecBld.setVersion(pvi == null
				? null
				: VersionFormat.OSGI_FORMAT.parse(pvi.toString()));
		return cspecBld;
	}
}
