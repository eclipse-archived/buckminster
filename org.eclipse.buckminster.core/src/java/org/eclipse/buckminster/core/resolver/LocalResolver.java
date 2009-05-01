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
import org.eclipse.buckminster.core.common.model.PropertyRef;
import org.eclipse.buckminster.core.cspec.QualifiedDependency;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.cspec.model.ComponentName;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.cspec.model.ComponentRequestConflictException;
import org.eclipse.buckminster.core.ctype.AbstractComponentType;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.ctype.MissingCSpecSourceException;
import org.eclipse.buckminster.core.metadata.MissingComponentException;
import org.eclipse.buckminster.core.metadata.StorageManager;
import org.eclipse.buckminster.core.metadata.WorkspaceInfo;
import org.eclipse.buckminster.core.metadata.model.BOMNode;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
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
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
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
import org.eclipse.core.runtime.Status;

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
		VersionConverterDesc pdeConverter = new VersionConverterDesc("tag", null, //$NON-NLS-1$
				new BidirectionalTransformer[0]);
		INSTALLED_BUNDLE_PROVIDER = new Provider(null, IReaderType.ECLIPSE_PLATFORM,
				new String[] { IComponentType.OSGI_BUNDLE }, pdeConverter, new Format("plugin/${" //$NON-NLS-1$
						+ KeyConstants.COMPONENT_NAME + "}"), null, null, null, false, false, null, null); //$NON-NLS-1$

		INSTALLED_FEATURE_PROVIDER = new Provider(null, IReaderType.ECLIPSE_PLATFORM,
				new String[] { IComponentType.ECLIPSE_FEATURE }, pdeConverter, new Format("feature/${" //$NON-NLS-1$
						+ KeyConstants.COMPONENT_NAME + "}"), null, null, null, false, false, null, null); //$NON-NLS-1$
	}

	public static Resolution fromPath(IPath productPath, String name) throws CoreException
	{
		return fromPath(productPath, name, null, new NullProgressMonitor());
	}

	public static Resolution fromPath(NodeQuery query, IPath path, Resolution oldInfo) throws CoreException
	{
		ComponentRequest request = query.getComponentRequest();
		Resolution resolution = fromPath(path, request.getName(), request.getComponentTypeID(),
				new NullProgressMonitor());

		// Retain old component info if present. We only wanted the cspec
		// changes
		//
		if(oldInfo != null)
			resolution = new Resolution(resolution.getCSpec(), resolution.getOPML(), oldInfo);
		return resolution;
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
			for(IComponentType ctype : AbstractComponentType.getComponentTypes())
			{
				if(ctype.isMetaFileBased() && ctype.hasAllRequiredMetaFiles(productPath))
					possibleTypes.add(ctype.getId());
			}
			if(possibleTypes.isEmpty())
				possibleTypes.add(IComponentType.UNKNOWN);
		}

		Format repoURI;
		try
		{
			String repoStr = productPath.toFile().toURI().toURL().toString();
			int nameIndex = repoStr.lastIndexOf(name);
			if(nameIndex > 0)
			{
				String parameterized = repoStr.substring(0, nameIndex);
				parameterized += "{0}"; //$NON-NLS-1$
				nameIndex += name.length();
				if(repoStr.length() > nameIndex)
					parameterized += repoStr.substring(nameIndex, repoStr.length());

				repoURI = new Format(parameterized);
				repoURI.addValueHolder(new PropertyRef<String>(String.class, KeyConstants.COMPONENT_NAME));
			}
			else
				repoURI = new Format(repoStr);
		}
		catch(MalformedURLException e)
		{
			throw BuckminsterException.wrap(e);
		}

		// We might have more then one possible type. Select the one that produces the
		// largest CSPEC (should be fast considering the IPath is local
		//
		ComponentRequest rq = new ComponentRequest(name, givenCtypeId, null);
		ComponentQueryBuilder queryBld = new ComponentQueryBuilder();
		queryBld.setRootRequest(rq);
		queryBld.setPlatformAgnostic(true);
		ComponentQuery cquery = queryBld.createComponentQuery();
		ResolutionContext context = new ResolutionContext(cquery);
		NodeQuery nq = new NodeQuery(context, rq, null);
		Provider provider = new Provider(null, IReaderType.LOCAL,
				possibleTypes.toArray(new String[possibleTypes.size()]), null, repoURI, null, null, null, false, false,
				null, null);
		monitor.beginTask(null, possibleTypes.size() * 100);
		int largestCSpecSize = -1;
		Resolution bestMatch = null;
		for(String ctypeId : possibleTypes)
		{
			IComponentType ctype = CorePlugin.getDefault().getComponentType(ctypeId);
			ProviderMatch pm = new ProviderMatch(provider, ctype, VersionMatch.DEFAULT, ProviderScore.GOOD, nq);

			try
			{
				BOMNode node = ctype.getResolution(pm, MonitorUtils.subMonitor(monitor, 100));
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
		if(bestMatch == null)
			throw new MissingComponentException(rq.toString());
		return bestMatch;
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

	public ResolverDecision logDecision(ComponentRequest request, ResolverDecisionType decisionType, Object... args)
	{
		return m_context.logDecision(request, decisionType, args);
	}

	public ResolverDecision logDecision(ResolverDecisionType decisionType, Object... args)
	{
		return m_context.logDecision(decisionType, args);
	}

	public BillOfMaterials resolve(ComponentRequest request, IProgressMonitor monitor) throws CoreException
	{
		monitor.beginTask(null, IProgressMonitor.UNKNOWN);
		try
		{
			NodeQuery query = m_context.getNodeQuery(request);
			ResolverNode node = deepResolve(m_context, new HashMap<ComponentName, ResolverNode>(), new UnresolvedNode(
					query.getQualifiedDependency()), null, monitor);
			return createBillOfMaterials(node);
		}
		finally
		{
			monitor.done();
		}
	}

	public BillOfMaterials resolve(IProgressMonitor monitor) throws CoreException
	{
		return resolve(m_context.getComponentQuery().getExpandedRootRequest(m_context), monitor);
	}

	public BillOfMaterials resolveRemaining(BillOfMaterials bom, IProgressMonitor monitor) throws CoreException
	{
		if(bom.isFullyResolved())
		{
			MonitorUtils.complete(monitor);
			return bom;
		}

		monitor.beginTask(null, IProgressMonitor.UNKNOWN);
		try
		{
			ComponentQuery cquery = bom.getQuery();
			ResolutionContext context = (cquery == null || cquery.equals(m_context.getComponentQuery()))
					? m_context
					: new ResolutionContext(cquery, m_context);
			BillOfMaterials newBom = createBillOfMaterials(deepResolve(context,
					new HashMap<ComponentName, ResolverNode>(), bom, bom.getTagInfo(), monitor));
			if(!newBom.contentEqual(bom))
				bom = newBom;
			return bom;
		}
		finally
		{
			monitor.done();
		}
	}

	public void setRecursiveResolve(boolean flag)
	{
		m_recursiveResolve = flag;
	}

	protected BOMNode localResolve(NodeQuery query, IProgressMonitor monitor) throws CoreException
	{
		ComponentRequest request = query.getComponentRequest();
		if(query.useMaterialization() || query.useWorkspace())
		{
			try
			{
				Resolution res = WorkspaceInfo.getResolution(request, true);
				if(res.getProvider().getReaderTypeId().equals(IReaderType.ECLIPSE_PLATFORM))
				{
					// Resolution is from target platform.
					//
					if(!query.useTargetPlatform())
						res = null;
				}
				else
				{
					Materialization mat = WorkspaceInfo.getMaterialization(res);
					if(mat == null)
					{
						// Resolution is not materialized so don't use it
						//
						res = null;
					}
					else if(!query.useWorkspace() && WorkspaceInfo.getProject(mat) != null)
					{
						// This component is bound to the workspace and we
						// are not allowed to use it when we resolve
						//
						res = null;
					}
				}
				if(res != null && res.isFilterMatchFor(query))
				{
					MonitorUtils.complete(monitor);
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
		if(query.useWorkspace())
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
				if(request.designates(ci) && resolution.isFilterMatchFor(query))
				{
					// Make sure we have a materialization for the project.
					//
					StorageManager sm = StorageManager.getDefault();
					Materialization mat = new Materialization(existingProject.getLocation().addTrailingSeparator(), ci);
					mat.store(sm);
					resolution.store(sm);
					MonitorUtils.complete(monitor);
					return new ResolvedNode(query, resolution);
				}
			}
		}

		if(query.useTargetPlatform())
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

			MultiStatus problemCollector = new MultiStatus(CorePlugin.getID(), IStatus.OK, "", null); //$NON-NLS-1$
			ProviderMatch match = provider.findMatch(query, problemCollector, new NullProgressMonitor());
			if(match == null)
				return null;

			monitor.beginTask(null, 30);
			try
			{
				IComponentReader[] reader = new IComponentReader[] { match.getReader(MonitorUtils.subMonitor(monitor,
						10)) };
				BOMNode node = match.getComponentType().getResolutionBuilder(reader[0],
						MonitorUtils.subMonitor(monitor, 10)).build(reader, false, MonitorUtils.subMonitor(monitor, 10));
				IOUtils.close(reader[0]);

				Resolution res = node.getResolution();
				if(res.isFilterMatchFor(query))
				{
					res.store(StorageManager.getDefault());
					return node;
				}
			}
			finally
			{
				monitor.done();
			}
		}
		return null;
	}

	BillOfMaterials createBillOfMaterials(ResolverNode topNode) throws CoreException
	{
		HashMap<UUID, BOMNode> nodeMap = new HashMap<UUID, BOMNode>();
		Stack<Resolution> circularDepTrap = new Stack<Resolution>();
		BOMNode node = topNode.collectNodes(nodeMap, circularDepTrap, true);
		if(node == null)
			node = new UnresolvedNode(topNode.getQuery().getQualifiedDependency());
		return BillOfMaterials.create(node, getContext().getComponentQuery());
	}

	ResolverNode createResolverNode(ResolutionContext context, QualifiedDependency qDep, String requestorInfo)
	{
		return new ResolverNode(context.getNodeQuery(qDep), requestorInfo);
	}

	ResolverNode getResolverNode(ResolutionContext context, QualifiedDependency qDep, String requestorInfo)
			throws CoreException
	{
		// We use a ComponentName as the key since we don't want the
		// designator to play a role here.
		//
		ComponentRequest request = qDep.getRequest();
		ComponentName key = request.toPureComponentName();
		ResolverNode[] nrs;
		boolean infant;
		synchronized(this)
		{
			nrs = get(key);
			infant = (nrs == null);
			if(infant)
			{
				nrs = new ResolverNode[] { createResolverNode(context, qDep, requestorInfo) };
				put(key, nrs);
			}
		}

		ResolverNode nr;
		if(infant)
			return nrs[0];

		int top = nrs.length;
		for(int idx = 0; idx < top; ++idx)
		{
			nr = nrs[idx];
			try
			{
				nr.addDependencyQualification(qDep);
				return nr;
			}
			catch(ComponentRequestConflictException e)
			{
				// We have a conflict. Two components with the same
				// name but incompatible versions.
				//
				IStatus err = e.getStatus();
				context.addRequestStatus(nr.getQuery().getComponentRequest(), new Status(IStatus.WARNING,
						err.getPlugin(), err.getMessage()));
			}
		}

		synchronized(this)
		{
			// No known ResolverNode could accommodate the requirements from
			// this qualified dependency. We need a new one.
			//
			nrs = get(key);
			if(nrs.length == top)
			{
				nr = createResolverNode(context, qDep, requestorInfo);
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
			nr = getResolverNode(context, qDep, requestorInfo);

		return nr;
	}

	private ResolverNode deepResolve(ResolutionContext context, Map<ComponentName, ResolverNode> visited,
			BOMNode depNode, String tagInfo, IProgressMonitor monitor) throws CoreException
	{
		QualifiedDependency qDep = depNode.getQualifiedDependency();
		ComponentName key = qDep.getRequest().toPureComponentName();

		// The visited map is to prevent endless recursion. The LocalResolver needs this since the query is often
		// created on-the-fly and without a chance to allow circular dependencies.
		//
		ResolverNode node = visited.get(key);
		if(node != null)
			return node;

		node = getResolverNode(context, qDep, tagInfo);
		visited.put(key, node);

		if(node.isResolved())
			return node;

		NodeQuery query = context.getNodeQuery(qDep);
		if(query.skipComponent())
			return node;

		GeneratorNode generatorNode = query.getResolutionContext().getGeneratorNode(qDep.getRequest().getName());
		if(generatorNode != null)
		{
			node.setGeneratorNode(generatorNode);
			return node;
		}

		Resolution res = depNode.getResolution();
		if(res == null)
		{
			try
			{
				depNode = localResolve(query, MonitorUtils.subMonitor(monitor, 1));
			}
			catch(CoreException e)
			{
				if(!context.isContinueOnError())
					throw e;
			}

			if(depNode == null)
				//
				// We don't get any further.
				//
				return node;

			res = depNode.getResolution();
			if(res == null)
				return node;
		}

		context = node.startResolvingChildren(depNode);
		if(context == null)
			//
			// Resolution was unsuccessful
			//
			return node;

		List<BOMNode> children = depNode.getChildren();
		int top = children.size();
		if(top == 0)
		{
			node.setResolution(res, null);
			return node;
		}

		ResolverNode[] resolvedChildren = new ResolverNode[top];
		String childTagInfo = res.getCSpec().getTagInfo(tagInfo);
		for(int idx = 0; idx < top; ++idx)
		{
			BOMNode child = children.get(idx);
			ComponentQuery cquery = child.getQuery();
			ResolutionContext childContext = (cquery == null)
					? context
					: new ResolutionContext(cquery, context);

			resolvedChildren[idx] = m_recursiveResolve
					? deepResolve(childContext, visited, child, childTagInfo, monitor)
					: getResolverNode(childContext, child.getQualifiedDependency(), childTagInfo);
		}
		node.setResolution(res, resolvedChildren);
		return node;
	}
}
