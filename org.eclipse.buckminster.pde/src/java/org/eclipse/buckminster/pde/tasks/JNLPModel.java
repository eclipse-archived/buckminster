/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.pde.tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.sax.ISaxableElement;
import org.eclipse.buckminster.sax.Utils;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 */
public class JNLPModel extends SAXModel
{
	@SuppressWarnings("serial")
	static class Resource extends HashMap<String, String> implements ISaxableElement
	{
		private final String m_arch;

		private final String m_key;

		private final String m_os;

		private final String m_tag;

		Resource(String os, String arch, String tag)
		{
			m_os = os;
			m_arch = arch;
			m_tag = tag;
			StringBuilder bld = new StringBuilder();
			if(m_os != null)
				bld.append(m_os);
			bld.append('/');
			if(m_arch != null)
				bld.append(m_arch);
			m_key = bld.toString();
		}

		public String getArch()
		{
			return m_arch;
		}

		public String getDefaultTag()
		{
			return m_tag;
		}

		public String getKey()
		{
			return m_key;
		}

		public String getOs()
		{
			return m_os;
		}

		public void toSax(ContentHandler receiver, String namespace, String prefix, String localName)
				throws SAXException
		{
			AttributesImpl attrs = new AttributesImpl();
			for(Map.Entry<String, String> attribute : entrySet())
				Utils.addAttribute(attrs, attribute.getKey(), attribute.getValue());
			startElement(receiver, localName, attrs);
			endElement(receiver, localName);
		}
	}

	private String m_codeBase = "$$codebase";

	private String m_description;

	private String m_href = "$$name";

	private boolean m_offLineAllowed = true;

	private Map<String, Resource> m_resourceById = new HashMap<String, Resource>();

	private Map<String, List<Resource>> m_resourcesByPlatform = new LinkedHashMap<String, List<Resource>>();

	private String m_specVersion = "1.5+";

	private String m_title;

	private String m_vendor;

	private String m_version;

	public Map<String, Resource> getResources()
	{
		return m_resourceById;
	}

	public void setCodeBase(String codeBase)
	{
		m_codeBase = codeBase;
	}

	public void setDescription(String description)
	{
		m_description = description;
	}

	public void setHref(String href)
	{
		m_href = href;
	}

	public void setOffLineAllowed(boolean offLineAllowed)
	{
		m_offLineAllowed = offLineAllowed;
	}

	public void setSpecVersion(String specVersion)
	{
		m_specVersion = specVersion;
	}

	public void setTitle(String title)
	{
		m_title = title;
	}

	public void setVendor(String vendor)
	{
		m_vendor = vendor;
	}

	public void setVersion(String version)
	{
		m_version = version;
	}

	public void toSax(ContentHandler receiver) throws SAXException
	{
		receiver.startDocument();
		AttributesImpl attrs = new AttributesImpl();
		if(m_specVersion != null)
			Utils.addAttribute(attrs, "spec", m_specVersion);
		if(m_codeBase != null)
			Utils.addAttribute(attrs, "codebase", m_codeBase);
		if(m_version != null)
			Utils.addAttribute(attrs, "version", m_version);
		if(m_href != null)
			Utils.addAttribute(attrs, "href", m_href);
		startElement(receiver, "jnlp", attrs);
		emitInformation(receiver);
		emitSecurity(receiver);
		emitBooleanElement(receiver, "component-desc");
		emitResources(receiver);
		endElement(receiver, "jnlp");
		receiver.endDocument();
	}

	Map<String, String> createResource(String id, String os, String arch, String tag)
	{
		Resource resource = new Resource(os, arch, tag);
		List<Resource> resources = m_resourcesByPlatform.get(resource.getKey());
		if(resources == null)
		{
			resources = new ArrayList<Resource>();
			m_resourcesByPlatform.put(resource.getKey(), resources);
		}
		resources.add(resource);
		if(id != null)
			m_resourceById.put(id, resource);
		return resource;
	}

	void emitInformation(ContentHandler receiver) throws SAXException
	{
		startElement(receiver, "information");

		if(m_title != null)
			emitTextElement(receiver, "title", m_title);
		if(m_vendor != null)
			emitTextElement(receiver, "vendor", m_vendor);
		if(m_description != null)
			emitTextElement(receiver, "description", m_description);
		if(m_offLineAllowed)
			emitBooleanElement(receiver, "offline-allowed");
		endElement(receiver, "information");
	}

	void emitResources(ContentHandler receiver) throws SAXException
	{
		AttributesImpl attrs = new AttributesImpl();
		for(List<Resource> resourceList : m_resourcesByPlatform.values())
		{
			int top = resourceList.size();
			if(top == 0)
				continue;

			Resource first = resourceList.get(0);
			if(first.getOs() != null)
				Utils.addAttribute(attrs, "os", first.getOs());
			if(first.getArch() != null)
				Utils.addAttribute(attrs, "arch", first.getArch());

			startElement(receiver, "resources", attrs);
			for(int idx = 0; idx < top; ++idx)
			{
				Resource resource = resourceList.get(idx);
				resource.toSax(receiver, null, null, resource.getDefaultTag());
			}
			endElement(receiver, "resources");
			attrs.clear();
		}
	}

	void emitSecurity(ContentHandler receiver) throws SAXException
	{
		startElement(receiver, "security");
		emitBooleanElement(receiver, "all-permissions");
		endElement(receiver, "security");
	}
}
