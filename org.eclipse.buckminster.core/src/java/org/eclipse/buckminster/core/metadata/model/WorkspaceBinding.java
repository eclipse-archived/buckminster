/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.metadata.model;

import java.util.UUID;

import org.eclipse.buckminster.core.XMLConstants;
import org.eclipse.buckminster.core.metadata.ISaxableStorage;
import org.eclipse.buckminster.core.metadata.StorageManager;
import org.eclipse.buckminster.sax.ISaxable;
import org.eclipse.buckminster.sax.ISaxableElement;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 */
public class WorkspaceBinding extends UUIDKeyed implements ISaxable, ISaxableElement
{
	public static final String TAG = "workspaceBinding";
	public static final String ATTR_WORKSPACE_RELATIVE_PATH = "workspaceRelativePath";
	public static final String ATTR_MATERIALIZATION_ID = "materializationId";
	public static final int SEQUENCE_NUMBER = 1;

	private final IPath m_workspaceRelativePath;
	private final UUID m_materializationId;

	private transient Materialization m_materialization;

	public WorkspaceBinding(IPath workspaceRelativePath, Materialization materialization)
	{
		m_workspaceRelativePath = workspaceRelativePath;
		m_materialization = materialization;
		m_materializationId = materialization.getId();
	}

	public WorkspaceBinding(IPath workspaceRelativePath, UUID materializationId)
	{
		m_workspaceRelativePath = workspaceRelativePath;
		m_materializationId = materializationId;
	}

	public String getDefaultTag()
	{
		return TAG;
	}

	public synchronized Materialization getMaterialization() throws CoreException
	{
		if(m_materialization == null)
			m_materialization = StorageManager.getDefault().getMaterializations().getElement(m_materializationId);
		return m_materialization;
	}

	public UUID getMaterializationId()
	{
		return m_materializationId;
	}

	public IPath getWorkspaceRelativePath()
	{
		return m_workspaceRelativePath;
	}

	public boolean isPersisted() throws CoreException
	{
		return getStorage().contains(this);
	}

	public void remove() throws CoreException
	{
		getStorage().removeElement(this.getId());
	}

	public void store() throws CoreException
	{
		if(m_materializationId == null)
			this.getMaterialization();
		else
			m_materialization.store();

		// The workspace relative path is unique. Only one binding can exist
		// at any one time.
		//
		UUID thisId = this.getId();
		ISaxableStorage<WorkspaceBinding> bindings = getStorage();		
		bindings.putElement(this);

		for(WorkspaceBinding oldBinding : bindings.getElements())
		{
			if(oldBinding.getId().equals(thisId))
				continue;

			if(!oldBinding.getWorkspaceRelativePath().equals(m_workspaceRelativePath))
				continue;

			oldBinding.remove();
		}
	}

	public void toSax(ContentHandler receiver) throws SAXException
	{
		receiver.startDocument();
		this.toSax(receiver, XMLConstants.BM_METADATA_NS, XMLConstants.BM_METADATA_PREFIX, this.getDefaultTag());
		receiver.endDocument();
	}

	public void toSax(ContentHandler receiver, String namespace, String prefix, String localName)
	throws SAXException
	{
		AttributesImpl attrs = new AttributesImpl();
		Utils.addAttribute(attrs, ATTR_WORKSPACE_RELATIVE_PATH, m_workspaceRelativePath.toPortableString());
		Utils.addAttribute(attrs, ATTR_MATERIALIZATION_ID, m_materializationId.toString());
		String qName = Utils.makeQualifiedName(prefix, localName);
		receiver.startElement(namespace, localName, qName, attrs);
		receiver.endElement(namespace, localName, qName);
	}

	private static ISaxableStorage<WorkspaceBinding> getStorage() throws CoreException
	{
		return StorageManager.getDefault().getWorkspaceBindings();
	}

	public static WorkspaceBinding find(IPath path) throws CoreException
	{
		ISaxableStorage<WorkspaceBinding> bindings = getStorage();		
		for(WorkspaceBinding binding : bindings.getElements())
			if(binding.getWorkspaceRelativePath().equals(path))
				return binding;
		return null;
	}
}

