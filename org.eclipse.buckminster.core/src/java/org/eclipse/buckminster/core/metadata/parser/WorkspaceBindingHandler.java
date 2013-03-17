/*****************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.metadata.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.eclipse.buckminster.core.common.parser.PropertyManagerHandler;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.cspec.model.ComponentName;
import org.eclipse.buckminster.core.cspec.model.NamedElement;
import org.eclipse.buckminster.core.metadata.model.Materialization;
import org.eclipse.buckminster.core.metadata.model.WorkspaceBinding;
import org.eclipse.buckminster.core.version.VersionHelper;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.equinox.p2.metadata.Version;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @author Thomas Hallgren
 */
public class WorkspaceBindingHandler extends PropertyManagerHandler {
	public static final String TAG = WorkspaceBinding.TAG;

	private ComponentIdentifier cid;

	private UUID resolutionId;

	private IPath location;

	private IPath wsRoot;

	private IPath wsRelativePath;

	private Map<String, String> properties;

	private long timestamp;

	public WorkspaceBindingHandler(AbstractHandler parent) {
		super(parent, TAG);
	}

	@Override
	public Map<String, String> getProperties() {
		if (properties == null)
			properties = new HashMap<String, String>();
		return properties;
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException {
		super.handleAttributes(attrs);
		String name = getStringValue(attrs, NamedElement.ATTR_NAME);
		String ctype = getOptionalStringValue(attrs, ComponentName.ATTR_COMPONENT_TYPE);
		Version version;
		try {
			version = VersionHelper.parseVersionAttributes(attrs);
		} catch (CoreException e) {
			throw new SAXParseException(e.getMessage(), getDocumentLocator());
		}
		properties = null;
		cid = new ComponentIdentifier(name, ctype, version);
		location = Path.fromPortableString(this.getStringValue(attrs, Materialization.ATTR_LOCATION));
		wsRoot = Path.fromPortableString(getStringValue(attrs, WorkspaceBinding.ATTR_WS_LOCATION));
		wsRelativePath = Path.fromPortableString(getStringValue(attrs, WorkspaceBinding.ATTR_WS_RELATIVE_PATH));
		timestamp = getLongValue(attrs, WorkspaceBinding.ATTR_TIMESTAMP);
		resolutionId = UUID.fromString(this.getStringValue(attrs, WorkspaceBinding.ATTR_RESOLUTION_ID));
	}

	WorkspaceBinding getWorkspaceBinding() throws SAXException {
		return new WorkspaceBinding(location, cid, resolutionId, wsRoot, wsRelativePath, properties, timestamp);
	}
}
