/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.metadata.model;

import java.util.Map;
import java.util.UUID;

import org.eclipse.buckminster.core.common.model.SAXEmitter;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.metadata.StorageManager;
import org.eclipse.buckminster.model.common.util.ExpandingProperties;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 */
public class WorkspaceBinding extends Materialization implements Comparable<WorkspaceBinding> {
	@SuppressWarnings("hiding")
	public static final int SEQUENCE_NUMBER = 1;

	@SuppressWarnings("hiding")
	public static final String TAG = "workspaceBinding"; //$NON-NLS-1$

	public static final String ATTR_WS_RELATIVE_PATH = "workspaceRelativePath"; //$NON-NLS-1$

	public static final String ATTR_WS_LOCATION = "workspaceLocation"; //$NON-NLS-1$

	public static final String ATTR_TIMESTAMP = "timestamp"; //$NON-NLS-1$

	public static final String ATTR_RESOLUTION_ID = "resolutionId"; //$NON-NLS-1$

	private final long timestamp;

	private final IPath workspaceRoot;

	private final IPath workspaceRelativePath;

	private final Map<String, String> properties;

	private final UUID resolutionId;

	private static long lastTS = System.currentTimeMillis();

	/**
	 * Returns the next timestamp. This is typically the value of
	 * {@link System#currentTimeMillis()} but if several calls arrive on the
	 * same millisecond, this method will increase the timestamp to a later time
	 * in order to ensure that each call returns a unique timestamp.
	 * 
	 * @return A timestamp that is guaranteed to be unique for each call and
	 *         equal or very close to the current time.
	 */
	public static long getNextTimestamp() {
		long now = System.currentTimeMillis();
		if (now > lastTS)
			lastTS = now;
		else
			now = ++lastTS;
		return now;
	}

	public WorkspaceBinding(IPath componentLocation, ComponentIdentifier cid, UUID resId, IPath workspaceRoot, IPath workspaceRelativePath,
			Map<String, String> properties, long timestamp) {
		super(componentLocation, cid);
		this.resolutionId = resId;
		this.timestamp = timestamp;
		this.workspaceRoot = workspaceRoot;
		this.workspaceRelativePath = workspaceRelativePath;
		this.properties = ExpandingProperties.createUnmodifiableProperties(properties);
	}

	public WorkspaceBinding(IPath componentLocation, Resolution res, IPath workspaceRoot, IPath workspaceRelativePath, Map<String, String> properties) {
		this(componentLocation, res.getComponentIdentifier(), res.getId(), workspaceRoot, workspaceRelativePath, properties, getNextTimestamp());
	}

	@Override
	public int compareTo(WorkspaceBinding o) {
		return timestamp < o.timestamp ? -1 : (timestamp == o.timestamp ? 0 : 1);
	}

	@Override
	public String getDefaultTag() {
		return TAG;
	}

	public Materialization getMaterialization() {
		return new Materialization(getComponentLocation(), getComponentIdentifier());
	}

	public Map<String, String> getProperties() {
		return properties;
	}

	public Resolution getResolution(StorageManager sm) throws CoreException {
		return sm.getResolutions().getElement(resolutionId);
	}

	public IPath getWorkspaceRelativePath() {
		return workspaceRelativePath;
	}

	public IPath getWorkspaceRoot() {
		return workspaceRoot;
	}

	@Override
	public boolean isPersisted(StorageManager sm) throws CoreException {
		return sm.getWorkspaceBindings().contains(this);
	}

	@Override
	public void remove(StorageManager sm) throws CoreException {
		sm.getWorkspaceBindings().removeElement(this.getId());
	}

	@Override
	public void store(StorageManager sm) throws CoreException {
		sm.getWorkspaceBindings().putElement(this);
	}

	@Override
	protected void addAttributes(AttributesImpl attrs) throws SAXException {
		super.addAttributes(attrs);
		Utils.addAttribute(attrs, ATTR_WS_RELATIVE_PATH, workspaceRelativePath.toPortableString());
		Utils.addAttribute(attrs, ATTR_WS_LOCATION, workspaceRoot.toPortableString());
		Utils.addAttribute(attrs, ATTR_TIMESTAMP, Long.toString(timestamp));
		Utils.addAttribute(attrs, ATTR_RESOLUTION_ID, resolutionId.toString());
	}

	@Override
	protected void emitElements(ContentHandler receiver, String namespace, String prefix) throws SAXException {
		super.emitElements(receiver, namespace, prefix);
		SAXEmitter.emitProperties(receiver, properties, namespace, prefix, true, false);
	}
}
