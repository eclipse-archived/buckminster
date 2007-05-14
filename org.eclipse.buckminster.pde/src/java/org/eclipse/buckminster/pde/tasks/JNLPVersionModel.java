/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.pde.tasks;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.buckminster.sax.ISaxableElement;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/**
 * <p>An instanceof this class represents the JNLP <code>version.xml</code></p>
 * <pre>
 *  &lt;!ELEMENT jnlp-versions (resource*, platform*)&gt;
 *  &lt;!ELEMENT resource (pattern, file)&gt;
 *  &lt;!ELEMENT platform (pattern, file, product-version-id)&gt;
 *  &lt;!ELEMENT pattern (name, version-id, os*, arch*, locale*)&gt;
 *  &lt;!ELEMENT name (#PCDATA)&gt;
 *  &lt;!ELEMENT version-id (#PCDATA)&gt;
 *  &lt;!ELEMENT os (#PCDATA)&gt;
 *  &lt;!ELEMENT arch (#PCDATA)&gt;
 *  &lt;!ELEMENT locale (#PCDATA)&gt;
 *  &lt;!ELEMENT file (#PCDATA)&gt;
 *  &lt;!ELEMENT product-version-id (#PCDATA)&gt;
 * </pre>
 * 
 * @author Thomas Hallgren
 */
public class JNLPVersionModel extends SAXModel
{
	private final ArrayList<Resource> m_resources = new ArrayList<Resource>();
	
	public static class Resource implements ISaxableElement
	{
		private final String m_name;
		private final String m_file;
		private final String m_versionId;

		private List<String> m_oss;
		private List<String> m_archs;
		private List<String> m_locales;

		Resource(String file, String name, String versionId)
		{
			m_file = file;
			m_name = name;
			m_versionId = versionId;
		}

		public void addOs(String os)
		{
			if(m_oss == null)
				m_oss = new ArrayList<String>();
			m_oss.add(os);
		}

		public void addArch(String arch)
		{
			if(m_archs == null)
				m_archs = new ArrayList<String>();
			m_archs.add(arch);
		}

		public void addLocale(String locale)
		{
			if(m_locales == null)
				m_locales = new ArrayList<String>();
			m_locales.add(locale);
		}

		public String getDefaultTag()
		{
			return "resource";
		}

		public void toSax(ContentHandler receiver, String namespace, String prefix, String localName) throws SAXException
		{
			startElement(receiver, localName);
			emitContent(receiver);
			endElement(receiver, localName);
		}
		
		void emitContent(ContentHandler receiver) throws SAXException
		{
			startElement(receiver, "pattern");
			emitTextElement(receiver, "name", m_name);
			emitTextElement(receiver, "version-id", m_versionId);
			emitTextElements(receiver, "os", m_oss);
			emitTextElements(receiver, "arch", m_archs);
			emitTextElements(receiver, "locale", m_locales);
			endElement(receiver, "pattern");
			emitTextElement(receiver, "file", m_file);
		}
	}

	public static class Platform extends Resource
	{
		private final String m_productVersionId;

		Platform(String file, String name, String versionId, String productVersionId)
		{
			super(file, name, versionId);
			m_productVersionId = productVersionId;
		}

		@Override
		public String getDefaultTag()
		{
			return "platform";
		}

		@Override
		void emitContent(ContentHandler receiver) throws SAXException
		{
			super.emitContent(receiver);
			emitTextElement(receiver, "product-version-id", m_productVersionId);
		}
	}

	private static void emitTextElements(ContentHandler receiver, String tag, List<String> values) throws SAXException
	{
		if(values != null)
			for(String value : values)
				emitTextElement(receiver, tag, value);
	}

	public Resource addResource(String file, String name, String versionId)
	{
		Resource resource = new Resource(file, name, versionId);
		m_resources.add(resource);
		return resource;
	}

	public Platform addPlatform(String file, String name, String versionId, String productVersionId)
	{
		Platform platform = new Platform(file, name, versionId, productVersionId);
		m_resources.add(platform);
		return platform;
	}

	public void toSax(ContentHandler receiver) throws SAXException
	{
		receiver.startDocument();
		startElement(receiver, "jnlp-versions");
		for(Resource resource : m_resources)
			resource.toSax(receiver, null, null, resource.getDefaultTag());
		endElement(receiver, "jnlp-versions");
		receiver.endDocument();
	}	
}
