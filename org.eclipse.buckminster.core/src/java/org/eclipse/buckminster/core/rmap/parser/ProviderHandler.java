/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.rmap.parser;

import java.util.ArrayList;

import org.eclipse.buckminster.core.common.model.Documentation;
import org.eclipse.buckminster.core.common.model.Format;
import org.eclipse.buckminster.core.common.parser.DocumentationHandler;
import org.eclipse.buckminster.core.common.parser.FormatHandler;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.core.parser.ExtensionAwareHandler;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.rmap.model.VersionConverterDesc;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.buckminster.sax.ChildPoppedListener;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;


/**
 * @author Thomas Hallgren
 */
public class ProviderHandler extends ExtensionAwareHandler implements ChildPoppedListener
{
	public final static String TAG = Provider.TAG;
	
	private DocumentationHandler m_documentationHandler;
	private FormatHandler m_formatHandler;
	private VersionConverterHandler m_versionConverterHandler;
	private Documentation m_documentation;
	private String	m_readerType;
	private String[] m_componentTypes;
	private String	m_space;
	private boolean	m_source;
	private boolean	m_mutable;

	private Format m_uriFormat;
	private VersionConverterDesc m_versionConverter;

	public ProviderHandler(AbstractHandler parent)
	{
		super(parent);
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs)
	throws SAXException
	{
		ChildHandler ch;
		if(Provider.TAG_URI.equals(localName))
		{
			if(m_formatHandler == null)
				m_formatHandler = new FormatHandler(this);
			ch = m_formatHandler;
		}
		else if(DocumentationHandler.TAG.equals(localName))
		{
			if(m_documentationHandler == null)
				m_documentationHandler = new DocumentationHandler(this);
			ch = m_documentationHandler;
		}
		else if(VersionConverterDesc.TAG.equals(localName))
		{
			if(m_versionConverterHandler == null)
				m_versionConverterHandler = new VersionConverterHandler(this);
			ch = m_versionConverterHandler;			
		}
		else
			ch = super.createHandler(uri, localName, attrs);
		return ch;
	}

	@Override
	public void handleAttributes(Attributes attrs)
	throws SAXException
	{
		m_readerType = getStringValue(attrs, Provider.ATTR_READER_TYPE);

		m_componentTypes = null;
		String tmp = getOptionalStringValue(attrs, Provider.ATTR_COMPONENT_TYPES);
		if(tmp == null)
		{
			// legacy. Version 0.1.0 had "managedCategories". The category concept is
			// merged into the componentType in 0.2.0.
			//
			// Limit component types using managed categories
			//
			tmp = getStringValue(attrs, "componentType");
			boolean canManageBundle = true;
			boolean canManageFeature = true;
			String[] managedCategories = TextUtils.split(getOptionalStringValue(attrs, "managedCategories"), ",");
			if(managedCategories.length > 0)
			{
				canManageBundle = false;
				canManageFeature = false;
				for(String category : managedCategories)
				{
					if("plugin".equals(category))
						canManageBundle = true;
					else if("feature".equals(category))
						canManageFeature = true;
				}
			}

			if(tmp.equals(IComponentType.ECLIPSE_PROJECT))
			{
				ArrayList<String> expanded = new ArrayList<String>(3);
				if(canManageBundle)
					expanded.add(IComponentType.OSGI_BUNDLE);
				if(canManageFeature)
					expanded.add(IComponentType.ECLIPSE_FEATURE);
				expanded.add(IComponentType.BUCKMINSTER);
				m_componentTypes = expanded.toArray(new String[expanded.size()]);
			}
			else if(tmp.equals(IComponentType.ECLIPSE_INSTALLED))
			{
				ArrayList<String> expanded = new ArrayList<String>(3);
				if(canManageBundle)
					expanded.add(IComponentType.OSGI_BUNDLE);
				if(canManageFeature)
					expanded.add(IComponentType.ECLIPSE_FEATURE);
				m_componentTypes = expanded.toArray(new String[expanded.size()]);
			}
		}
		if(m_componentTypes == null)
			m_componentTypes = TextUtils.split(tmp, ",");

		m_mutable = getOptionalBooleanValue(attrs, Provider.ATTR_MUTABLE, true);
		m_source = getOptionalBooleanValue(attrs, Provider.ATTR_SOURCE, true);
		m_space = getOptionalStringValue(attrs, Provider.ATTR_SPACE);
		m_uriFormat = null;
		m_versionConverter = null;
		m_documentation = null;
	}

	public void childPopped(ChildHandler child)
	{
		if(child == m_formatHandler)
			m_uriFormat = (Format)m_formatHandler.getValueHolder();
		else if(child == m_versionConverterHandler)
			m_versionConverter = m_versionConverterHandler.getVersionConverter();
		else if(child == m_documentationHandler)
			m_documentation = m_documentationHandler.createDocumentation();
	}
	
	public Provider getProvider()
	{
		return new Provider(
				m_readerType,
				m_componentTypes,
				m_versionConverter,
				m_uriFormat,
				m_space,
				m_mutable,
				m_source,
				m_documentation);
	}

	protected final String[] getComponentTypes()
	{
		return m_componentTypes;
	}

	protected final Documentation getDocumentation()
	{
		return m_documentation;
	}

	protected final boolean isMutable()
	{
		return m_mutable;
	}

	protected final String getReaderType()
	{
		return m_readerType;
	}

	protected final String getSpace()
	{
		return m_space;
	}

	protected final boolean isSource()
	{
		return m_source;
	}

	protected final Format getUriFormat()
	{
		return m_uriFormat;
	}

	protected final VersionConverterDesc getVersionConverter()
	{
		return m_versionConverter;
	}
}

