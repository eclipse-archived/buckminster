/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.metadata.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.eclipse.buckminster.core.XMLConstants;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.metadata.ISaxableStorage;
import org.eclipse.buckminster.core.metadata.StorageManager;
import org.eclipse.buckminster.core.metadata.WorkspaceInfo;
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
public class Materialization extends UUIDKeyed implements ISaxable, ISaxableElement
{
	public static final String TAG = "materialization";

	public static final String ATTR_LOCATION = "location";
	public static final String ATTR_RESOLUTION_ID = "resolutionId";

	public static final int SEQUENCE_NUMBER = 1;

	private final IPath m_componentLocation;
	private final UUID m_resolutionId;
	private transient Resolution m_resolution;

	public Materialization(IPath destination, Resolution resolution)
	{
		this(destination, resolution.getId());
		m_resolution = resolution;
	}

	public Materialization(IPath destination, UUID resolutionId)
	{
		if(destination == null || resolutionId == null)
			throw new NullPointerException();
		m_componentLocation = destination;
		m_resolutionId = resolutionId;
	}

	public List<WorkspaceBinding> getBindings() throws CoreException
	{
		ISaxableStorage<WorkspaceBinding> bindings = StorageManager.getDefault().getWorkspaceBindings();

		List<UUID> bindingIds = bindings.getReferencingKeys(getId(), "materializationId");
		int top = bindingIds.size();
		if(top == 0)
			return Collections.emptyList();

		ArrayList<WorkspaceBinding> result = new ArrayList<WorkspaceBinding>(top);
		for(int idx = 0; idx < top; ++idx)
			result.add(bindings.getElement(bindingIds.get(idx)));
		return result;
	}

	public final IPath getComponentLocation()
	{
		return m_componentLocation;
	}

	public String getDefaultTag()
	{
		return TAG;
	}

	public ComponentIdentifier getComponentIdentifier() throws CoreException
	{
		return getResolution().getComponentIdentifier();
	}

	public CSpec getCSpec() throws CoreException
	{
		return getResolution().getCSpec();
	}

	public synchronized Resolution getResolution() throws CoreException
	{
		if(m_resolution == null)
			m_resolution = StorageManager.getDefault().getResolutions().getElement(m_resolutionId);
		return m_resolution;
	}

	public UUID getResolutionId()
	{
		return m_resolutionId;
	}

	public boolean isPersisted() throws CoreException
	{
		return getStorage().contains(this);
	}

	/**
	 * Returns <code>true</code> if this <code>MaterializationInfo</code> is
	 * valid. It will be considered valid if the destination appoints an
	 * existing file or a directory that is not empty.
	 * @return <code>true</code> if the destination is not empty.
	 */
	public boolean isValid()
	{
		IPath location = getComponentLocation();
		File destFile = location.toFile();

		// Check that this is a non-empty directory or a non-empty file with
		// as few system calls as possible.
		//
		String[] list = destFile.list();
		return (list == null) ? destFile.length() > 0 : list.length > 0;
	}

	public void remove() throws CoreException
	{
		// Also remove the bindings
		//
		for(WorkspaceBinding binding : getBindings())
			binding.remove();
		
		synchronized(this)
		{
			getStorage().removeElement(getId());
			WorkspaceInfo.clearCachedLocation(getResolution().getCSpec());
		}
	}

	public void store() throws CoreException
	{
		// Make sure the resolution is persisted
		//
		if(m_resolution == null)
			//
			// This will yield an exception if the resolution has not been
			// persisted.
			//
			getResolution();
		else
			m_resolution.store();

		// Remove any other materialization that appoints the same location
		//
		UUID thisId = getId();
		ISaxableStorage<Materialization> mats = getStorage();		
		mats.putElement(this);
		WorkspaceInfo.clearCachedLocation(getResolution().getCSpec());

		for(Materialization oldMat : mats.getElements())
		{
			if(oldMat.getId().equals(thisId))
				continue;

			if(!oldMat.getComponentLocation().equals(m_componentLocation))
				continue;


			// Move all bindings so they appoint this materialization, then remove
			// the old materialization (will remove old bindings)
			//
			List<WorkspaceBinding> oldBindings = oldMat.getBindings();
			oldMat.remove();

			if(oldBindings.size() > 0)
			{
				for(WorkspaceBinding oldBinding : oldBindings)
				{
					WorkspaceBinding ws = new WorkspaceBinding(oldBinding.getWorkspaceRelativePath(), this);
					ws.store();
				}
			}
		}
	}

	public void toSax(ContentHandler receiver) throws SAXException
	{
		receiver.startDocument();
		toSax(receiver, XMLConstants.BM_METADATA_NS, XMLConstants.BM_METADATA_PREFIX, getDefaultTag());
		receiver.endDocument();
	}

	public void toSax(ContentHandler receiver, String namespace, String prefix, String localName) throws SAXException
	{
		AttributesImpl attrs = new AttributesImpl();
		Utils.addAttribute(attrs, ATTR_LOCATION, m_componentLocation.toPortableString());
		Utils.addAttribute(attrs, ATTR_RESOLUTION_ID, m_resolutionId.toString());
		String qName = Utils.makeQualifiedName(prefix, localName);
		receiver.startElement(namespace, localName, qName, attrs);
		receiver.endElement(namespace, localName, qName);
	}

	private ISaxableStorage<Materialization> getStorage() throws CoreException
	{
		return StorageManager.getDefault().getMaterializations();
	}
}

