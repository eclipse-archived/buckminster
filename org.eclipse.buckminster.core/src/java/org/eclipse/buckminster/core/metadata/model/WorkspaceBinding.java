/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.metadata.model;

import java.util.Map;

import org.eclipse.buckminster.core.common.model.SAXEmitter;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.metadata.StorageManager;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 */
public class WorkspaceBinding extends Materialization
{
	@SuppressWarnings("hiding")
	public static final int SEQUENCE_NUMBER = 1;
	@SuppressWarnings("hiding")
	public static final String TAG = "workspaceBinding";

	public static final String ATTR_WS_RELATIVE_PATH = "workspaceRelativePath";
	public static final String ATTR_WS_LOCATION = "workspaceLocation";

	private final IPath m_workspaceRoot;
	private final IPath m_workspaceRelativePath;
	private final Map<String,String> m_properties;

	public WorkspaceBinding(IPath componentLocation, ComponentIdentifier cid, IPath workspaceRoot, IPath workspaceRelativePath, Map<String,String> properties)
	{
		super(componentLocation, cid);
		m_workspaceRoot = workspaceRoot;
		m_workspaceRelativePath = workspaceRelativePath;
		m_properties = UUIDKeyed.createUnmodifiableProperties(properties);
	}

	@Override
	public String getDefaultTag()
	{
		return TAG;
	}

	public Materialization getMaterialization()
	{
		return new Materialization(getComponentLocation(), getComponentIdentifier());
	}

	public Map<String,String> getProperties()
	{
		return m_properties;
	}

	public IPath getWorkspaceRelativePath()
	{
		return m_workspaceRelativePath;
	}

	public IPath getWorkspaceRoot()
	{
		return m_workspaceRoot;
	}

	@Override
	public boolean isPersisted(StorageManager sm) throws CoreException
	{
		return sm.getWorkspaceBindings().contains(this);
	}

	@Override
	public void remove(StorageManager sm) throws CoreException
	{
		sm.getWorkspaceBindings().removeElement(this.getId());
	}

	@Override
	public void store(StorageManager sm) throws CoreException
	{
		sm.getWorkspaceBindings().putElement(this);
	}

	@Override
	void addAttributes(AttributesImpl attrs)
	{
		super.addAttributes(attrs);
		Utils.addAttribute(attrs, ATTR_WS_RELATIVE_PATH, m_workspaceRelativePath.toPortableString());
		Utils.addAttribute(attrs, ATTR_WS_LOCATION, m_workspaceRoot.toPortableString());
	}

	@Override
	void emitElements(ContentHandler receiver, String namespace, String prefix) throws SAXException
	{
		super.emitElements(receiver, namespace, prefix);
		SAXEmitter.emitProperties(receiver, m_properties, namespace, prefix, true, false);
	}
}

