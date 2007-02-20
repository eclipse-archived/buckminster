/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.core;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.common.model.IProperties;
import org.eclipse.buckminster.core.cspec.QualifiedDependency;
import org.eclipse.buckminster.core.cspec.model.Action;
import org.eclipse.buckminster.core.cspec.model.Attribute;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentCategory;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.cspec.model.Generator;
import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.core.materializer.DestinationChangeException;
import org.eclipse.buckminster.core.metadata.MissingComponentException;
import org.eclipse.buckminster.core.metadata.WorkspaceInfo;
import org.eclipse.buckminster.core.metadata.model.GeneratorNode;
import org.eclipse.buckminster.core.metadata.model.Materialization;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.query.model.AdvisorNode;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.core.query.model.MaterializationAdvice;
import org.eclipse.buckminster.core.query.model.NotEmptyAction;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;

/**
 * The <i>Resolution and Materialization</i> context. Maintains information with
 * a lifecycle that lasts throughout the resolution and materialization process.
 * 
 * @author Thomas Hallgren
 */
public class RMContext extends ExpandingProperties
{
	private final ComponentQuery m_componentQuery;
	private boolean m_continueOnError;
	private HashMap<Resolution,MaterializationAdvice> m_materializationAdvices;
	private final RMContext m_parentContext;
	private MultiStatus m_status;
	private HashMap<Object,Object> m_userCache;
	private HashMap<String,GeneratorNode> m_generators;

	public RMContext(ComponentQuery componentQuery)
	{
		this(componentQuery, null);
	}

	public RMContext(ComponentQuery componentQuery, RMContext parentContext)
	{
		super(componentQuery.getGlobalProperties());
		m_componentQuery = componentQuery;
		m_parentContext = parentContext;
	}

	/**
	 * This is where the exceptions that occur during resolve will end up if the
	 * {@link #isContinueOnError()} returns <code>true</code>.
	 * 
	 * @param resolveStatus
	 *            A status that indicates an error during resolvement.
	 */
	public synchronized void addResolveException(IStatus resolveStatus)
	{
		if(m_parentContext != null)
			m_parentContext.addResolveException(resolveStatus);
		else
		{
			if(m_status == null)
			{
				m_status = new MultiStatus(
					CorePlugin.getID(),
					IStatus.OK,
					resolveStatus instanceof MultiStatus
						? ((MultiStatus)resolveStatus).getChildren()
						: new IStatus[] { resolveStatus },
					"Errors during resolve", null);
			}
			else
				m_status.merge(resolveStatus);
		}
	}

	/**
	 * Clears the status so that next call to {@link #getStatus()}
	 * returns {@link IStatus#OK_STATUS}.
	 */
	public synchronized void clearStatus()
	{
		if(m_parentContext != null)
			m_parentContext.clearStatus();
		else
			m_status = null;
	}

	public String getBindingName(Resolution resolution, Map<String,String> props) throws CoreException
	{
		ComponentRequest request = resolution.getRequest();
		String name = null;

		Attribute bindEntryPoint = resolution.getCSpec().getBindEntryPoint();
		if(bindEntryPoint instanceof Action)
		{
			if(props == null)
				props = getProperties(request);
			name = ((Action)bindEntryPoint).getBindingName(props);
		}

		if(name == null)
			name = request.getName();
		return name;
	}

	public ComponentQuery getComponentQuery()
	{
		return m_componentQuery;
	}

	public IPath getDestination(Resolution resolution) throws CoreException
	{
		IPath destination = null;
		MaterializationAdvice mat = getMaterializationAdvice(resolution);
		if(mat != null)
			destination = mat.getDestination();

		if(destination == null)
		{
			boolean[] optional = new boolean[] { true };
			destination = getDefaultDestination(resolution, getProjectName(resolution), optional);
		}
		return destination;
	}

	public NodeQuery getNodeQuery(ComponentRequest request)
	{
		return getNodeQuery(new QualifiedDependency(request, m_componentQuery.getAttributes(request)));
	}

	public NodeQuery getNodeQuery(QualifiedDependency qualifiedDependency)
	{
		return new NodeQuery(this, qualifiedDependency);
	}

	public NotEmptyAction getNotEmptyAction(Resolution resolution) throws CoreException
	{
		MaterializationAdvice mat = getMaterializationAdvice(resolution);
		NotEmptyAction notEmptyAction = null;
		if(mat != null)
			notEmptyAction = mat.getNotEmptyAction();

		if(notEmptyAction == null)
			notEmptyAction = getDefaultNotEmptyAction(resolution);
		return notEmptyAction;
	}

	public String getProjectName(Resolution resolution) throws CoreException
	{
		String projectName = null;
		MaterializationAdvice mat = getMaterializationAdvice(resolution);
		if(mat != null)
			projectName = mat.getProjectName();
		
		if(projectName == null)
			projectName = getDefaultProjectName(resolution);
		return projectName;
	}

	public Map<String, String> getProperties(ComponentRequest request)
	{
		// fill this up as you go..
		//
		IProperties p = new ExpandingProperties(this);
		p.putAll(request.getProperties());

		AdvisorNode node = m_componentQuery.getMatchingNode(request);
		if(node != null)
			p.putAll(node.getProperties());
		return p;
	}

	public NodeQuery getRootNodeQuery() throws CoreException
	{
		return getNodeQuery(m_componentQuery.getRootRequest());
	}

	/**
	 * Returns the status that reflects the outcome of the process.
	 * If the status is
	 * {@link org.eclipse.core.runtime.Status#OK_STATUS OK_STATUS} everything
	 * went OK.
	 * 
	 * @return The status of the process
	 */
	public synchronized IStatus getStatus()
	{
		return (m_parentContext != null)
			? m_parentContext.getStatus()
			: (m_status == null ? Status.OK_STATUS : m_status);
	}

	public synchronized Map<Object,Object> getUserCache()
	{
		if(m_parentContext != null)
			return m_parentContext.getUserCache();

		if(m_userCache == null)
			m_userCache = new HashMap<Object, Object>();
		return m_userCache;
	}

	public synchronized boolean isContinueOnError()
	{
		return (m_parentContext != null)
			? m_parentContext.isContinueOnError()
			: m_continueOnError;
	}

	public boolean isDestinationChangeable(Resolution resolution) throws CoreException
	{
		boolean[] optional = new boolean[] { true };
		getDefaultDestination(resolution, getProjectName(resolution), optional);
		return optional[0];
	}

	public boolean isUsingMaterializationDefaults(Resolution resolution)
	{
		return getMaterializationAdvice(resolution) == null;
	}

	public void setContinueOnError(boolean flag)
	{
		if(m_parentContext != null)
			m_parentContext.setContinueOnError(flag);
		else
			m_continueOnError = flag;
	}

	public synchronized void setDestination(Resolution resolution, IPath destination) throws CoreException
	{
		if(getDestination(resolution).equals(destination))
			return;

		boolean[] optional = new boolean[] { true };
		String projectName = getProjectName(resolution);
		IPath defaultDest = getDefaultDestination(resolution, projectName, optional);
		if(!optional[0] && !defaultDest.equals(destination))
			throw new DestinationChangeException(defaultDest, destination);

		MaterializationAdvice matAdvice = getMaterializationAdvice(resolution);

		NotEmptyAction notEmptyAction = null;
		if(matAdvice != null)
			notEmptyAction = matAdvice.getNotEmptyAction();

		putMaterializationAdvice(resolution, new MaterializationAdvice(projectName, destination, notEmptyAction));
	}

	public void setGenerators(Collection<Generator> generators)
	{
		for(Generator generator : generators)
		{
			if(m_generators == null)
				m_generators = new HashMap<String, GeneratorNode>();
			m_generators.put(generator.getGenerates(), new GeneratorNode(generator));
		}
	}

	public GeneratorNode getGeneratorNode(String name)
	{
		if(m_generators != null)
		{
			GeneratorNode node = m_generators.get(name);
			if(node != null)
				return node;
		}
		return (m_parentContext == null) ? null : m_parentContext.getGeneratorNode(name);
	}

	public synchronized void setNotEmptyAction(Resolution resolution, NotEmptyAction notEmptyAction) throws CoreException
	{
		if(getNotEmptyAction(resolution).equals(notEmptyAction))
			return;

		MaterializationAdvice matAdvice = getMaterializationAdvice(resolution);

		IPath destination = null;
		String projectName = null;
		if(matAdvice != null)
		{
			projectName = matAdvice.getProjectName();
			destination = matAdvice.getDestination();
		}
		putMaterializationAdvice(resolution, new MaterializationAdvice(projectName, destination, notEmptyAction));
	}

	public synchronized void setProjectName(Resolution resolution, String projectName) throws CoreException
	{
		if(getProjectName(resolution).equals(projectName))
			return;

		MaterializationAdvice matAdvice = getMaterializationAdvice(resolution);

		IPath destination = null;
		NotEmptyAction notEmptyAction = null;
		if(matAdvice != null)
		{
			notEmptyAction = matAdvice.getNotEmptyAction();
			destination = matAdvice.getDestination();
		}
		putMaterializationAdvice(resolution, new MaterializationAdvice(projectName, destination, notEmptyAction));
	}

	public void setUseMaterializationDefaults(Resolution resolution, boolean flag)
	{
		if(flag)
			removeMaterializationAdvice(resolution);
		else if(getMaterializationAdvice(resolution) == null)
			putMaterializationAdvice(resolution, new MaterializationAdvice(null, null, null));
	}

	private IPath getDefaultDestination(Resolution resolution, String projectName, boolean[] optional) throws CoreException
	{
		ComponentQuery query = getComponentQuery();
		CSpec cspec = resolution.getCSpec();
		ComponentIdentifier cid = cspec.getComponentIdentifier();

		// Use existing materialization if the component is materialized already
		//
		if(query.useMaterialization(cid))
		{
			boolean mightHaveMaterialization = true;
			if(query.useInstalledComponent(cid))
			{
				try
				{
					optional[0] = false;
					return cspec.getComponentLocation();
				}
				catch(MissingComponentException e)
				{
					// expected but if we get here there's no
					// materialization entry
					//
					mightHaveMaterialization = false;
				}
			}

			if(mightHaveMaterialization)
			{
				Materialization mat = WorkspaceInfo.getMaterialization(resolution);
				if(mat != null)
					return mat.getComponentLocation();
			}
		}

		// Consult the reader type.
		//
		optional[0] = true;
		IReaderType rd = resolution.getProvider().getReaderType();
		IPath location = rd.getMaterializationLocation(resolution, this, optional);
		if(location != null)
			return location;

		// Consult the component category to get a relative location
		//
		optional[0] = true;
		IPath relativeLocation = null;
		ComponentCategory cc = ComponentCategory.getCategory(cid.getCategory());
		if(cc != null)
			relativeLocation = cc.getRelativeLocation();

		IPath cRoot = ResourcesPlugin.getWorkspace().getRoot().getLocation();
		if(relativeLocation != null)
			location = cRoot.append(relativeLocation).append(cid.getName());
		else
			location = cRoot.append(projectName);

		if(!rd.isFileReader())
			location = location.addTrailingSeparator();
		return location;
	}

	private NotEmptyAction getDefaultNotEmptyAction(Resolution resolution)
	{
		return getComponentQuery().useExistingArtifacts(resolution.getRequest());
	}

	private String getDefaultProjectName(Resolution resolution) throws CoreException
	{
		ComponentRequest cname = resolution.getRequest();
		String name = cname.getName();
		String categoryName = cname.getCategory();
		ComponentCategory cc = ComponentCategory.getCategory(categoryName);
		if(cc == null)
			return name;

		Pattern desiredMatch = cc.getDesiredNamePattern();
		if(desiredMatch == null || desiredMatch.matcher(name).find())
			//
			// We have a category but no desire to change the name
			//
			return name;

		Pattern repFrom = cc.getSubstituteNamePattern();
		String repTo = cc.getNameSubstitution();

		if(repFrom == null || repTo == null)
			throw new BuckminsterException("Category: " + categoryName + " defines desiredNamePattern but no substitution");

		Matcher matcher = repFrom.matcher(name);
		if(matcher.matches())
		{
			String repl = matcher.replaceAll(repTo).trim();
			if(repl.length() > 0)
				name = repl;
		}
		return name;
	}

	private synchronized MaterializationAdvice getMaterializationAdvice(Resolution resolution)
	{
		return (m_parentContext != null)
			? m_parentContext.getMaterializationAdvice(resolution)
			: (m_materializationAdvices == null ? null : m_materializationAdvices.get(resolution));
	}

	private void putMaterializationAdvice(Resolution resolution, MaterializationAdvice advice)
	{
		if(m_parentContext != null)
			m_parentContext.putMaterializationAdvice(resolution, advice);
		else
		{
			if(m_materializationAdvices == null)
				m_materializationAdvices = new HashMap<Resolution, MaterializationAdvice>();
			m_materializationAdvices.put(resolution, advice);
		}
	}

	private void removeMaterializationAdvice(Resolution resolution)
	{
		if(m_parentContext != null)
			m_parentContext.removeMaterializationAdvice(resolution);
		else
		{
			if(m_materializationAdvices != null)
				m_materializationAdvices.remove(resolution);
		}
	}
}
