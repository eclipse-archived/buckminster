/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.model;

import java.util.Collections;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.metadata.model.IModelCache;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.resolver.LocalResolver;
import org.eclipse.buckminster.core.version.IVersionDesignator;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 */
public class ObtainedDependency extends ComponentRequest
{
	public static final String ELEM_OBTAINED_FROM = "obtainedFrom";

	public static final String ATTR_ATTRIBUTE = "attribute";

	public static final String ATTR_COMPONENT = "component";

	private final String m_component;
	private final String m_attribute;

	public ObtainedDependency(String name, String category, IVersionDesignator versionDesignator, String component, String attribute)
	{
		super(name, category, versionDesignator);
		m_component = component;
		m_attribute = attribute;
	}

	public String getAttribute()
	{
		return m_attribute;
	}

	public String getComponent()
	{
		return m_component;
	}

	@Override
	protected void emitElements(ContentHandler handler, String namespace, String prefix) throws SAXException
	{
		AttributesImpl attrs = new AttributesImpl();
		Utils.addAttribute(attrs, ATTR_ATTRIBUTE, m_attribute);
		Utils.addAttribute(attrs, ATTR_COMPONENT, m_component);
		String qName = Utils.makeQualifiedName(prefix, ELEM_OBTAINED_FROM);
		handler.startElement(namespace, ELEM_OBTAINED_FROM, qName, attrs);
		handler.endElement(namespace, ELEM_OBTAINED_FROM, qName);
	}

	public CSpec resolveCSpec(CSpec ownerCSpec, IModelCache modelCache) throws CoreException
	{
		Attribute componentProducer = ownerCSpec.getReferencedAttribute(m_component, m_attribute, modelCache);
		IPath uniquePath = componentProducer.getUniquePath(null, modelCache);
		Resolution resolution;
		try
		{
			resolution = LocalResolver.fromPath(uniquePath, m_component);
		}
		catch(CoreException e)
		{
			CorePlugin.getPerformManager().perform(
				Collections.singletonList(componentProducer), modelCache.getProperties(), false, new NullProgressMonitor());
			resolution = LocalResolver.fromPath(uniquePath, m_component);
		}
		return resolution.getCSpec();
	}
}
