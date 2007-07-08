/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.resolver;

import java.net.MalformedURLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.UUID;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.KeyConstants;
import org.eclipse.buckminster.core.common.model.Format;
import org.eclipse.buckminster.core.cspec.QualifiedDependency;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.cspec.model.ComponentName;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.cspec.model.ComponentRequestConflictException;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.ctype.MissingCSpecSourceException;
import org.eclipse.buckminster.core.metadata.MetadataSynchronizer;
import org.eclipse.buckminster.core.metadata.MissingComponentException;
import org.eclipse.buckminster.core.metadata.WorkspaceInfo;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.metadata.model.DepNode;
import org.eclipse.buckminster.core.metadata.model.GeneratorNode;
import org.eclipse.buckminster.core.metadata.model.Materialization;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.metadata.model.ResolvedNode;
import org.eclipse.buckminster.core.metadata.model.UnresolvedNode;
import org.eclipse.buckminster.core.query.builder.ComponentQueryBuilder;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.core.rmap.model.BidirectionalTransformer;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.rmap.model.ProviderScore;
import org.eclipse.buckminster.core.rmap.model.VersionConverterDesc;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.core.version.VersionFactory;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.NullProgressMonitor;

/**
 * The LocalResolver will attempt to resolve the query using locally available resources. This includes:
 * <ul>
 * <li>Projects in the Eclipse Workspace</li>
 * <li>Materializations previously done by Buckminster using this workspace</li>
 * <li>Features and bundles in the target platform</li>
 * </ul>
 * 
 * @author Thomas Hallgren
 */
@SuppressWarnings("serial")
public class LocalResolver extends HashMap<ComponentName, ResolverNode[]> implements IResolver
{
	public static final Provider INSTALLED_BUNDLE_PROVIDER;

	public static final Provider INSTALLED_FEATURE_PROVIDER;
	static
	{
		VersionConverterDesc pdeConverter = new VersionConverterDesc("tag", VersionFactory.OSGiType,
				new BidirectionalTransformer[0]);
		INSTALLED_BUNDLE_PROVIDER = new Provider(IReaderType.ECLIPSE_PLATFORM,
				new String[] { IComponentType.OSGI_BUNDLE }, pdeConverter, new Format("plugin/${"
						+ KeyConstants.COMPONENT_NAME + "}"), null, false, false, null);

		INSTALLED_FEATURE_PROVIDER = new Provider(IReaderType.ECLIPSE_PLATFORM,
				new String[] { IComponentType.ECLIPSE_FEATURE }, pdeConverter, new Format("feature/${"
						+ KeyConstants.COMPONENT_NAME + "}"), null, false, false, null);
	}

	private final ResolutionContext m_context;

	private boolean m_recursiveResolve = true;

	public LocalResolver(ComponentQuery componentQuery) throws CoreException
	{
		this(new ResolutionContext(componentQuery));
	}

	public LocalResolver(ResolutionContext context) throws CoreException
	{
		m_context = context;
	}

	public ResolutionContext getContext()
	{
		return m_context;
	}

	public boolean isRecursiveResolve()
	{
		return m_recursiveResolve;
	}

	public BillOfMaterials resolve(ComponentRequest request, IProgressMonitor monitor) throws CoreException
	{
		NodeQuery query = m_context.getNodeQuery(request);
		ResolverNode node = deepResolve(m_context, new UnresolvedNode(query.getQualifiedDependency()));
		return createBillOfMaterials(node);
	}

	public BillOfMaterials resolve(IProgressMonitor monitor) throws CoreException
	{
		return resolve(m_context.getComponentQuery().getRootRequest(), monitor);
	}

	public BillOfMaterials resolveRemaining(BillOfMaterials bom, IProgressMonitor monitor) throws CoreException
	{
		if(bom.isFullyResolved())
		{
			MonitorUtils.complete(monitor);
			return bom;
		}
		ComponentQuery cquery = bom.getQuery();
		ResolutionContext context = (cquery == null || cquery.equals(m_context.getComponentQuery()))
				? m_context
				: new ResolutionContext(cquery, m_context);
		BillOfMaterials newBom = createBillOfMaterials(deepResolve(context, bom));
		if(!newBom.contentEqual(bom))
			bom = newBom;
		return bom;
	}

	public void setRecursiveResolve(boolean flag)
	{
		m_recursiveResolve = flag;
	}

	protected DepNode localResolve(NodeQuery query) throws CoreException
	{
		ComponentRequest request = query.getComponentRequest();
		if(query.useMaterialization())
		{
			try
			{
				Resolution res = WorkspaceInfo.getResolution(request, true);
				if(res.getProvider().getReaderTypeId().equals(IReaderType.ECLIPSE_PLATFORM))
				{
					// Resolution is from target platform.
					//
					if(!query.useInstalledComponent())
						res = null;
				}
				else
				{
					if(!query.useMaterialization())
						res = null;
					else
					{
						Materialization mat = WorkspaceInfo.getMaterialization(res);
						if(mat == null)
						{
							// Resolution is not materialized so don't use it
							//
							res = null;
						}
						else if(!query.useExistingProject() && WorkspaceInfo.getProject(mat) != null)
						{
							// This component is bound to the workspace and we
							// are not allowed to use it when we resolve
							//
							res = null;
						}
					}
				}
				if(res != null)
				{
					res.store();
					return new ResolvedNode(query, res);
				}
			}
			catch(MissingComponentException e)
			{
			}
		}

		// If we get to this point, we didn't find any existing resolution that
		// could be used.
		//
		if(query.useExistingProject())
		{
			// Generate the resolution from a project in the workspace
			//
			IWorkspaceRoot wsRoot = ResourcesPlugin.getWorkspace().getRoot();
			IProject existingProject = null;
			try
			{
				existingProject = wsRoot.getProject(request.getProjectName());
			}
			catch(IllegalArgumentException e)
			{
				// Query did not produce a name that is valid for a project
			}
			if(existingProject != null && existingProject.isOpen())
			{
				Resolution resolution = fromPath(query, existingProject.getLocation(), null);
				ComponentIdentifier ci = resolution.getComponentIdentifier();
				if(request.designates(ci))
				{
					// Make sure we have a materialization for the project.
					//
					Materialization mat = new Materialization(existingProject.getLocation().addTrailingSeparator(), ci);
					mat.store();
					resolution.store();
					return new ResolvedNode(query, resolution);
				}
			}
		}

		if(query.useInstalledComponent())
		{
			// Generate the resolution from the target platform
			//
			Provider provider;
			String ctypeID = request.getComponentTypeID();
			if(IComponentType.OSGI_BUNDLE.equals(ctypeID))
				provider = INSTALLED_BUNDLE_PROVIDER;
			else if(IComponentType.ECLIPSE_FEATURE.equals(ctypeID))
				provider = INSTALLED_FEATURE_PROVIDER;
			else
				return null;

			MultiStatus problemCollector = new MultiStatus(CorePlugin.getID(), IStatus.OK, "", null);
			ProviderMatch match = provider.findMatch(query, problemCollector, new NullProgressMonitor());
			if(match == null)
				return null;

			IProgressMonitor nullMonitor = new NullProgressMonitor();
			IComponentReader[] reader = new IComponentReader[] { provider.getReaderType().getReader(match, nullMonitor) };
			DepNode node = match.getComponentType().getResolutionBuilder(reader[0], nullMonitor).build(reader,
					false, nullMonitor);
			node.getResolution().store();
			if(reader[0] != null)
				reader[0].close();
			return node;
		}
		return null;
	}

	ResolverNode getResolverNode(ResolutionContext context, QualifiedDependency qDep) throws CoreException
	{
		// We use a ComponentName as the key since we don't want the
		// designator to play a role here.
		//
		ComponentName key = qDep.getRequest().toPureComponentName();
		ResolverNode[] nrs;
		boolean infant;
		synchronized(this)
		{
			nrs = get(key);
			infant = (nrs == null);
			if(infant)
			{
				nrs = new ResolverNode[] { createResolverNode(context, qDep) };
				put(key, nrs);
			}
		}

		ResolverNode nr;
		if(infant)
			return nrs[0];

		int top = nrs.length;
		for(int idx = 0; idx < top; ++idx)
		{
			try
			{
				nr = nrs[idx];
				nr.addDependencyQualification(qDep);
				return nr;
			}
			catch(ComponentRequestConflictException e)
			{
				// We have a conflict. Two components with the same
				// name but incompatible versions.
				//
				CorePlugin.getLogger().warning(e.getMessage());
			}
		}

		synchronized(this)
		{
			// No known ResolverNode could accomodate the requirements from
			// this quialified dependency. We need a new one.
			//
			nrs = get(key);
			if(nrs.length == top)
			{
				nr = createResolverNode(context, qDep);
				ResolverNode[] newNrs = new ResolverNode[top + 1];
				System.arraycopy(nrs, 0, newNrs, 0, top);
				newNrs[top] = nr;
				put(key, newNrs);
			}
			else
			{
				// Someone beat us to it. Break out from the synchronization
				// and try again from square one
				//
				nr = null;
			}
		}

		if(nr == null)
			//
			// Start from square one.
			//
			nr = getResolverNode(context, qDep);

		return nr;
	}

	BillOfMaterials createBillOfMaterials(ResolverNode topNode) throws CoreException
	{
		HashMap<UUID, DepNode> nodeMap = new HashMap<UUID, DepNode>();
		Stack<Resolution> circularDepTrap = new Stack<Resolution>();
		BillOfMaterials bom = BillOfMaterials.create(topNode.collectNodes(nodeMap, circularDepTrap, true), getContext()
				.getComponentQuery());
		bom.store();
		return bom;
	}

	ResolverNode createResolverNode(ResolutionContext context, QualifiedDependency qDep)
	{
		return new ResolverNode(new NodeQuery(context, qDep));
	}

	private ResolverNode deepResolve(ResolutionContext context, DepNode depNode) throws CoreException
	{
		QualifiedDependency qDep = depNode.getQualifiedDependency();
		ResolverNode node = getResolverNode(context, qDep);
		if(node.isResolved())
			return node;

		NodeQuery query = m_context.getNodeQuery(qDep);
		if(query.skipComponent())
			return node;

		GeneratorNode generatorNode = query.getResolutionContext().getGeneratorNode(qDep.getRequest().getName());
		if(generatorNode != null)
		{
			node.setGeneratorNode(generatorNode);
			return node;
		}

		if(depNode.getResolution() == null)
		{
			try
			{
				depNode = localResolve(query);
			}
			catch(CoreException e)
			{
				if(!m_context.isContinueOnError())
					throw e;
			}

			if(depNode == null)
				//
				// We don't get any further.
				//
				return node;
		}

		context = node.startResolvingChildren(depNode);
		if(context == null)
			//
			// Resolution was unsuccesful
			//
			return node;

		List<DepNode> children = depNode.getChildren();
		int top = children.size();
		if(top == 0)
		{
			node.setResolution(depNode.getResolution(), null);
			return node;
		}

		ResolverNode[] resolvedChildren = new ResolverNode[top];
		for(int idx = 0; idx < top; ++idx)
		{
			DepNode child = children.get(idx);
			ComponentQuery cquery = child.getQuery();
			ResolutionContext childContext = (cquery == null)
					? context
					: new ResolutionContext(cquery, context);
			resolvedChildren[idx] = m_recursiveResolve
					? deepResolve(childContext, child)
					: getResolverNode(childContext, child.getQualifiedDependency());
		}
		node.setResolution(depNode.getResolution(), resolvedChildren);
		return node;
	}

	public static CSpec fromPath(IPath productPath, String name) throws CoreException
	{
		Resolution resolution = fromPath(productPath, name, null, new NullProgressMonitor());
		return resolution == null
				? null
				: resolution.getCSpec();
	}

	private static Resolution fromPath(IPath productPath, String name, String givenCtypeId, IProgressMonitor monitor)
			throws CoreException
	{
		Set<String> possibleTypes;
		if(givenCtypeId != null)
			possibleTypes = Collections.singleton(givenCtypeId);
		else
		{
			possibleTypes = new HashSet<String>();
			Map<IPath, String> cspecSources = MetadataSynchronizer.getDefault().getCSpecSources();
			for(Map.Entry<IPath, String> entry : cspecSources.entrySet())
			{
				IPath path = entry.getKey();
				if(productPath.append(path).toFile().exists())
					possibleTypes.add(entry.getValue());
			}
			if(possibleTypes.isEmpty())
				possibleTypes.add(IComponentType.UNKNOWN);
		}

		Format repoURI;
		try
		{
			repoURI = new Format(productPath.toFile().toURI().toURL().toString());
		}
		catch(MalformedURLException e)
		{
			throw BuckminsterException.wrap(e);
		}

		// We might have more then one possible type. Select the one that produces the
		// largest CSPEC (should be fast considering the IPath is local
		//
		ComponentRequest rq = new ComponentRequest(name, null, null);
		ComponentQueryBuilder queryBld = new ComponentQueryBuilder();
		queryBld.setRootRequest(rq);
		ComponentQuery cquery = queryBld.createComponentQuery();
		ResolutionContext context = new ResolutionContext(cquery);
		NodeQuery nq = new NodeQuery(context, rq, null);
		Provider provider = new Provider(IReaderType.LOCAL, possibleTypes.toArray(new String[possibleTypes.size()]), null, repoURI, null, false, false, null);

		monitor.beginTask(null, possibleTypes.size() * 100);
		int largestCSpecSize = -1;
		Resolution bestMatch = null;
		for(String ctypeId : possibleTypes)
		{
			IComponentType ctype = CorePlugin.getDefault().getComponentType(ctypeId);
			ProviderMatch pm = new ProviderMatch(provider, ctype, VersionMatch.DEFAULT, ProviderScore.GOOD, nq);

			try
			{
				DepNode node = ctype.getResolution(pm, MonitorUtils.subMonitor(monitor, 100));
				Resolution resolution = node.getResolution();
				if(resolution == null)
					continue;

				int imageSize = resolution.getCSpec().getImage().length;
				if(bestMatch == null || largestCSpecSize < imageSize)
				{
					largestCSpecSize = imageSize;
					bestMatch = resolution;
				}
			}
			catch(MissingCSpecSourceException e)
			{
				continue;
			}
		}
		return bestMatch;
	}

	public static Resolution fromPath(NodeQuery query, IPath path, Resolution oldInfo) throws CoreException
	{
		ComponentRequest request = query.getComponentRequest();
		Resolution resolution = fromPath(path, request.getName(), request.getComponentTypeID(), new NullProgressMonitor());

		// Retain old component info if present. We only wanted the cspec
		// changes
		//
		if(oldInfo != null)
			resolution = new Resolution(resolution.getCSpec(), oldInfo);
		return resolution;
	}
}
