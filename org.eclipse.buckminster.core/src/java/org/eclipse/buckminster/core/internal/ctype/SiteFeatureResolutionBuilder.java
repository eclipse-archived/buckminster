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
import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.cspec.AbstractResolutionBuilder;
import org.eclipse.buckminster.core.cspec.QualifiedDependency;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentName;
import org.eclipse.buckminster.core.cspec.model.Dependency;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.ctype.MissingCSpecSourceException;
import org.eclipse.buckminster.core.helpers.FilterUtils;
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
			if(siteFeature == null)
				throw new MissingCSpecSourceException(reader.getProviderMatch());

			if(forResolutionAidOnly)
				return new ResolvedNode(new Resolution(getCSpecBuilder(siteFeature).createCSpec(), reader), null);

			NodeQuery query = reader.getNodeQuery();
			QualifiedDependency qdep = new QualifiedDependency(query.getComponentRequest(), null);
			ResolverNode rNode = buildCSpecFromSiteFeature(reader, new HashMap<ComponentName, ResolverNode>(), siteFeature, new UnresolvedNode(qdep), null, MonitorUtils.subMonitor(monitor, 50));
			DepNode node = rNode.collectNodes(new HashMap<UUID, DepNode>(), new Stack<Resolution>(), true);
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

	private ResolverNode buildCSpecFromSiteFeature(IComponentReader reader, Map<ComponentName, ResolverNode> nodes, IFeature siteFeature, DepNode depNode, String tagInfo, IProgressMonitor monitor) throws CoreException
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
		CSpec cspec;
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
				IVersion version = VersionFactory.OSGiType.coerce(vid.getVersion());
				IVersionDesignator vd = (version == null) ? null : VersionFactory.createExplicitDesignator(version);

				Dependency dep = new Dependency(vid.getIdentifier(), getComponentTypeID(), vd, FilterUtils.createFilter(ref.getOS(), ref.getWS(), ref.getOSArch(), ref.getName()));
				cspecBld.addDependency(dep);
				matches[idx] = new VersionMatch(version, null, provider.getSpace(), -1, null, null);
				qDeps[idx] = new QualifiedDependency(dep, null);
			}
			cspec = cspecBld.createCSpec();
			String childTagInfo = cspec.getTagInfo(tagInfo);

			for(int idx = 0; idx < numChildren; ++idx)
			{
				QualifiedDependency qdep = qDeps[idx];
				NodeQuery childQuery = new NodeQuery(context, qdep);
				IFeature refFeature = refs[idx].getFeature(MonitorUtils.subMonitor(monitor, 4));
				IComponentReader childReader = reader.getReaderType().getReader(provider, ctype, childQuery, matches[idx], MonitorUtils.subMonitor(monitor, 1));
				try
				{
					childArr.add(buildCSpecFromSiteFeature(childReader, nodes, refFeature, new UnresolvedNode(qdep), childTagInfo, MonitorUtils.subMonitor(monitor, 5)));
				}
				finally
				{
					childReader.close();
				}
			}
			children = childArr.toArray(new ResolverNode[childArr.size()]);
		}
		else
			cspec = cspecBld.createCSpec();

		node.setResolution(new Resolution(cspec, reader), children);
		MonitorUtils.worked(monitor, 5);
		monitor.done();
		return node;
	}

	private static ResolverNode getResolverNode(Map<ComponentName, ResolverNode> nodes, RMContext context, QualifiedDependency qDep, String tagInfo) throws CoreException
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
				nr = new ResolverNode(new NodeQuery(context, qDep), tagInfo);
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
		cspecBld.setComponentTypeID(getComponentTypeID());
		cspecBld.setVersion(VersionFactory.OSGiType.coerce(vi.getVersion()));
		return cspecBld;
	}
}

