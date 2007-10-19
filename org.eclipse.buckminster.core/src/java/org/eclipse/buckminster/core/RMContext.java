/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.core;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.UUID;

import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.cspec.QualifiedDependency;
import org.eclipse.buckminster.core.cspec.model.Action;
import org.eclipse.buckminster.core.cspec.model.Attribute;
import org.eclipse.buckminster.core.cspec.model.ComponentName;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.helpers.BMProperties;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.helpers.MapUnion;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;

/**
 * The <i>Resolution and Materialization</i> context. Maintains information with
 * a lifecycle that lasts throughout the resolution and materialization process.
 * 
 * @author Thomas Hallgren
 */
public class RMContext extends MapUnion<String, String>
{
	private int m_tagInfoSquenceNumber = 0;

	private final ArrayList<TagInfo> m_knownInfos = new ArrayList<TagInfo>();

	private TagInfo getTagInfo(String infoString)
	{
		return getTagInfo(Collections.singleton(infoString));
	}

	private TagInfo getTagInfo(Set<String> infoStrings)
	{
		for(TagInfo info : m_knownInfos)
			if(info.m_infoStrings.equals(infoStrings))
				return info;

		TagInfo info = new TagInfo(infoStrings);
		m_knownInfos.add(info);
		return info;
	}

	private TagInfo getTagInfo(TagInfo old, String infoString)
	{
		if(old.m_infoStrings.contains(infoString))
			return old;

		Set<String> infos = new TreeSet<String>();
		infos.addAll(old.m_infoStrings);
		infos.add(infoString);
		return getTagInfo(infos);
	}

	public class TagInfo
	{
		private final String m_tagId;
		
		private final Set<String> m_infoStrings;

		private boolean m_used = false;

		private TagInfo(Set<String> infoStrings)
		{
			m_tagId = String.format("%04d", new Integer(++m_tagInfoSquenceNumber));
			m_infoStrings = infoStrings;
		}

		public String getTagId()
		{
			return m_tagId;
		}

		public boolean isUsed()
		{
			return m_used;
		}

		public void setUsed()
		{
			m_used = true;
		}

		@Override
		public String toString()
		{
			StringBuilder bld = new StringBuilder();
			bld.append("TAG-ID ");
			bld.append(m_tagId);
			bld.append(" = ");
			Iterator<String> infos = m_infoStrings.iterator();
			if(infos.hasNext())
			{
				bld.append(infos.next());
				while(infos.hasNext())
				{
					bld.append(", ");
					bld.append(infos.next());
				}
			}
			return bld.toString();
		}
	}

	private static final Map<String, String> s_globalAdditions;

	static
	{
		Map<String,String> additions = new HashMap<String, String>();

		URL eclipseHome = Platform.getInstallLocation().getURL();
		if(eclipseHome != null)
		{
			CorePlugin.getLogger().debug("Platform install location: " + eclipseHome);
			assert ("file".equals(eclipseHome.getProtocol()));
			File homeFile = FileUtils.getFile(eclipseHome);
			if(homeFile != null)
				additions.put("eclipse.home", homeFile.toString());
		}
		else
			CorePlugin.getLogger().debug("Platform install location is NULL!");

		additions.put("workspace.root", ResourcesPlugin.getWorkspace().getRoot().getLocation()
				.toPortableString());
		try
		{
			additions.put("localhost", InetAddress.getLocalHost().getHostName());
		}
		catch(UnknownHostException e1)
		{
			// We'll just have to do without it.
		}
		s_globalAdditions = new MapUnion<String, String>(additions, BMProperties.getSystemProperties());
	}

	public static Map<String, String> getGlobalPropertyAdditions()
	{
		return s_globalAdditions;
	}

	private static Map<String,String> makeExpanding(Map<String,String> properties)
	{
		return (properties instanceof ExpandingProperties)
			? properties
			: new ExpandingProperties(properties);
	}
	private boolean m_continueOnError;
	private final Map<ComponentRequest, TagInfo> m_tagInfos = new HashMap<ComponentRequest, TagInfo>();

	private MultiStatus m_status;
	private Map<UUID,Object> m_userCache;

	public RMContext(Map<String,String> properties)
	{
		super(makeExpanding(properties), s_globalAdditions);
	}

	/**
	 * This is where the exceptions that occur during processing will end up if the
	 * {@link #isContinueOnError()} returns <code>true</code>.
	 * 
	 * @param resolveStatus
	 *            A status that indicates an error during processing.
	 */
	public synchronized void addException(ComponentRequest request, IStatus status)
	{
		status = addTagId(getTagId(request), status);

		Logger logger = CorePlugin.getLogger();
		switch(status.getSeverity())
		{
		case IStatus.ERROR:
			logger.error(formatStatus(status));
			break;
		case IStatus.WARNING:
			logger.warning(formatStatus(status));
			break;
		case IStatus.INFO:
			logger.info(formatStatus(status));
		}

		if(m_status == null)
		{
			m_status = new MultiStatus(
				CorePlugin.getID(),
				IStatus.OK,
				status instanceof MultiStatus
					? ((MultiStatus)status).getChildren()
					: new IStatus[] { status },
				"Errors and Warnings", null);
		}
		else
			m_status.merge(status);
	}

	public static String formatStatus(IStatus status)
	{
		StringWriter bld = new StringWriter();
		BufferedWriter wrt = new BufferedWriter(bld);
		try
		{
			formatStatus(wrt, 0, status);
			wrt.flush();
		}
		catch(IOException e)
		{}
		return bld.toString();
	}

	private static void formatStatus(BufferedWriter wrt, int indent, IStatus status) throws IOException
	{
		for(int idx = 0; idx < indent; ++idx)
			wrt.append(' ');
		switch(status.getSeverity())
		{
		case IStatus.INFO:
			wrt.append("INFO    ");
			break;
		case IStatus.WARNING:
			wrt.append("WARNING ");
			break;
		case IStatus.ERROR:
			wrt.append("ERROR   ");
			break;
		}
		wrt.append(status.getMessage());		
		for(IStatus child : status.getChildren())
		{
			wrt.newLine();
			formatStatus(wrt, indent + 2, child);
		}
	}

	public synchronized void addTagInfo(ComponentRequest request, String info)
	{
		TagInfo tagInfo = m_tagInfos.get(request);
		if(tagInfo == null)
			tagInfo = getTagInfo(info);
		else
			tagInfo = getTagInfo(tagInfo, info);
		m_tagInfos.put(request, tagInfo);
	}

	private synchronized String getTagId(ComponentRequest request)
	{
		TagInfo tagInfo = m_tagInfos.get(request);
		if(tagInfo != null)
		{
			tagInfo.setUsed();
			return tagInfo.getTagId();
		}
		return "0000";
	}

	/**
	 * Clears the status so that next call to {@link #getStatus()}
	 * returns {@link IStatus#OK_STATUS}.
	 */
	public synchronized void clearStatus()
	{
		m_status = null;
	}

	private void emitTagInfos()
	{
		Map<String,TagInfo> sorted = new TreeMap<String, TagInfo>();
		for(TagInfo tagInfo : m_tagInfos.values())
			if(tagInfo.isUsed())
				sorted.put(tagInfo.getTagId(), tagInfo);

		if(sorted.size() == 0)
			return;

		StringWriter bld = new StringWriter();
		BufferedWriter wrt = new BufferedWriter(bld);
		try
		{
			for(TagInfo tagInfo : sorted.values())
			{
				wrt.write(tagInfo.toString());
				wrt.newLine();
			}
			wrt.flush();
		}
		catch(IOException e)
		{
			// On a StringWriter? Don't think so.
		}
		CorePlugin.getLogger().warning(bld.toString());
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
		return null;
	}

	public NodeQuery getNodeQuery(ComponentRequest request)
	{
		return getNodeQuery(new QualifiedDependency(request, getComponentQuery().getAttributes(request)));
	}

	public NodeQuery getNodeQuery(QualifiedDependency qualifiedDependency)
	{
		return new NodeQuery(this, qualifiedDependency);
	}

	public Map<String, String> getProperties(ComponentName cName)
	{
		return new MapUnion<String, String>(cName.getProperties(), this);
	}

	public synchronized Map<ComponentRequest,TagInfo> getTagInfos()
	{
		return m_tagInfos;
	}

	public NodeQuery getRootNodeQuery()
	{
		return getNodeQuery(getComponentQuery().getRootRequest());
	}

	/**
	 * Returns the status that reflects the outcome of the process.
	 * If the status is
	 * {@link org.eclipse.core.runtime.Status#OK_STATUS OK_STATUS} everything
	 * went OK.
	 * 
	 * @return The status of the process
	 */
	public IStatus getStatus()
	{
		IStatus status = m_status;
		if(status == null)
			return Status.OK_STATUS;

		while(status.isMultiStatus() && status.getChildren().length == 1)
			status = status.getChildren()[0];

		return status;
	}

	public boolean emitWarningsAndErrors()
	{
		IStatus status = getStatus();
		switch(status.getSeverity())
		{
		case IStatus.ERROR:
			emitTagInfos();
			return true;
		case IStatus.WARNING:
		case IStatus.INFO:
			emitTagInfos();
		}
		return false;
	}

	private static IStatus addTagId(String tagId, IStatus status)
	{
		if(status instanceof MultiStatus)
		{
			IStatus[] children = status.getChildren();
			int idx = children.length;
			IStatus[] taggedChildren = new IStatus[idx];
			while(--idx >= 0)
				taggedChildren[idx] = addTagId(tagId, children[idx]);
			return new MultiStatus(status.getPlugin(), status.getCode(), taggedChildren, addTagId(tagId, status.getMessage()), status.getException());
		}
		return new Status(status.getSeverity(), status.getPlugin(), addTagId(tagId, status.getMessage()), status.getException());		
	}

	private static String addTagId(String tagId, String msg)
	{
		String prefix = '[' + tagId + "] : ";
		if(msg == null)
			msg = prefix;
		else if(!msg.startsWith(prefix))
			msg = prefix + msg;
		return msg;
	}

	/**
	 * Returns a map intended for caching purposes during resolution and
	 * materialization. The map is synchronized. Users of the map must
	 * create UUID's to use as keys in the map.
	 * @return A map to be used for caching purposes
	 */
	public synchronized Map<UUID,Object> getUserCache()
	{
		if(m_userCache == null)
			m_userCache = Collections.synchronizedMap(new HashMap<UUID, Object>());
		return m_userCache;
	}

	public synchronized boolean isContinueOnError()
	{
		return m_continueOnError;
	}

	public void setContinueOnError(boolean flag)
	{
		m_continueOnError = flag;
	}
}
