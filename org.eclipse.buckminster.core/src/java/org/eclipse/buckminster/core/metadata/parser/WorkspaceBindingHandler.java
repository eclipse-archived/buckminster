/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.metadata.parser;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.buckminster.core.common.parser.PropertyManagerHandler;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.cspec.model.ComponentName;
import org.eclipse.buckminster.core.cspec.model.NamedElement;
import org.eclipse.buckminster.core.internal.version.OSGiVersionType;
import org.eclipse.buckminster.core.metadata.model.Materialization;
import org.eclipse.buckminster.core.metadata.model.WorkspaceBinding;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.VersionFactory;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @author Thomas Hallgren
 */
public class WorkspaceBindingHandler extends PropertyManagerHandler
{
	@SuppressWarnings("hiding")
	public static final String TAG = WorkspaceBinding.TAG;

	private ComponentIdentifier m_cid;
	private IPath m_location;
	private IPath m_wsRoot;
	private IPath m_wsRelativePath;
	private Map<String,String> m_properties;

	public WorkspaceBindingHandler(AbstractHandler parent)
	{
		super(parent, TAG);
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException
	{
		super.handleAttributes(attrs);
		String name  = getStringValue(attrs, NamedElement.ATTR_NAME);
		String ctype = getOptionalStringValue(attrs, ComponentName.ATTR_COMPONENT_TYPE);
		IVersion version = null;

		String tmp = getOptionalStringValue(attrs, ComponentIdentifier.ATTR_VERSION);
		if(tmp != null)
		{
			String type = getOptionalStringValue(attrs, ComponentIdentifier.ATTR_VERSION_TYPE);
			if(type == null)
				type = OSGiVersionType.ID;
			try
			{
				version = VersionFactory.createVersion(type, tmp);
			}
			catch(CoreException e)
			{
				throw new SAXParseException(e.getMessage(), this.getDocumentLocator());
			}
		}
		m_properties = null;
		m_cid = new ComponentIdentifier(name, ctype, version);
		m_location = Path.fromPortableString(this.getStringValue(attrs, Materialization.ATTR_LOCATION));
		m_wsRoot = Path.fromPortableString(getStringValue(attrs, WorkspaceBinding.ATTR_WS_LOCATION));
		m_wsRelativePath = Path.fromPortableString(getStringValue(attrs, WorkspaceBinding.ATTR_WS_RELATIVE_PATH));
	}

	WorkspaceBinding getWorkspaceBinding() throws SAXException
	{
		return new WorkspaceBinding(m_location, m_cid, m_wsRoot, m_wsRelativePath, m_properties);
	}

	@Override
	public Map<String, String> getProperties()
	{
		if(m_properties == null)
			m_properties = new HashMap<String,String>();
		return m_properties;
	}
}
