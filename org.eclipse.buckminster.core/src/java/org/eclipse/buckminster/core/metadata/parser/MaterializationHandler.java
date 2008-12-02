/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.metadata.parser;

import java.util.UUID;

import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.cspec.model.NamedElement;
import org.eclipse.buckminster.core.metadata.StorageManager;
import org.eclipse.buckminster.core.metadata.model.Materialization;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.parser.ExtensionAwareHandler;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.IVersionType;
import org.eclipse.buckminster.core.version.VersionFactory;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @author Thomas Hallgren
 */
public class MaterializationHandler extends ExtensionAwareHandler
{
	public static final String TAG = Materialization.TAG;

	private Materialization m_materialization;

	public MaterializationHandler(AbstractHandler parent)
	{
		super(parent);
	}

	public Materialization getMaterialization()
	{
		return m_materialization;
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException
	{
		ComponentIdentifier cid;
		String name = getOptionalStringValue(attrs, NamedElement.ATTR_NAME);
		if(name == null)
		{
			// Backward compatibility. Look for resolutionId
			//
			UUID resolutionId = UUID.fromString(getStringValue(attrs, "resolutionId")); //$NON-NLS-1$
			try
			{
				Resolution res = StorageManager.getDefault().getResolutions().getElement(resolutionId);
				cid = res.getComponentIdentifier();
			}
			catch(CoreException e)
			{
				throw new SAXParseException(e.getMessage(), getDocumentLocator(), e);
			}
		}
		else
		{
			String ctype = getComponentType(attrs);
			IVersion version = null;

			String tmp = getOptionalStringValue(attrs, ComponentIdentifier.ATTR_VERSION);
			if(tmp != null)
			{
				String type = getOptionalStringValue(attrs, ComponentIdentifier.ATTR_VERSION_TYPE);
				if(type == null)
					type = IVersionType.OSGI;
				try
				{
					version = VersionFactory.createVersion(type, tmp);
				}
				catch(CoreException e)
				{
					throw new SAXParseException(e.getMessage(), this.getDocumentLocator());
				}
			}
			cid = new ComponentIdentifier(name, ctype, version);
		}
		m_materialization = new Materialization(Path.fromPortableString(this.getStringValue(attrs,
				Materialization.ATTR_LOCATION)), cid);
	}
}
