/*******************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
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
public class JNLPModel extends SAXModel {
	@SuppressWarnings("serial")
	static class Resource extends HashMap<String, String> implements ISaxableElement {
		private final String arch;

		private final String key;

		private final String os;

		private final String tag;

		Resource(String os, String arch, String tag) {
			this.os = os;
			this.arch = arch;
			this.tag = tag;
			StringBuilder bld = new StringBuilder();
			if (os != null)
				bld.append(os);
			bld.append('/');
			if (arch != null)
				bld.append(arch);
			this.key = bld.toString();
		}

		public String getArch() {
			return arch;
		}

		@Override
		public String getDefaultTag() {
			return tag;
		}

		public String getKey() {
			return key;
		}

		public String getOs() {
			return os;
		}

		@Override
		public void toSax(ContentHandler receiver, String namespace, String prefix, String localName) throws SAXException {
			AttributesImpl attrs = new AttributesImpl();
			for (Map.Entry<String, String> attribute : entrySet())
				Utils.addAttribute(attrs, attribute.getKey(), attribute.getValue());
			startElement(receiver, localName, attrs);
			endElement(receiver, localName);
		}
	}

	private String codeBase = "$$codebase"; //$NON-NLS-1$

	private String description;

	private String href = "$$name"; //$NON-NLS-1$

	private boolean offLineAllowed = true;

	private Map<String, Resource> resourceById = new HashMap<String, Resource>();

	private Map<String, List<Resource>> resourcesByPlatform = new LinkedHashMap<String, List<Resource>>();

	private String specVersion = "1.5+"; //$NON-NLS-1$

	private String title;

	private String vendor;

	private String version;

	public Map<String, Resource> getResources() {
		return resourceById;
	}

	public void setCodeBase(String codeBase) {
		this.codeBase = codeBase;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public void setOffLineAllowed(boolean offLineAllowed) {
		this.offLineAllowed = offLineAllowed;
	}

	public void setSpecVersion(String specVersion) {
		this.specVersion = specVersion;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public void toSax(ContentHandler receiver) throws SAXException {
		receiver.startDocument();
		AttributesImpl attrs = new AttributesImpl();
		if (specVersion != null)
			Utils.addAttribute(attrs, "spec", specVersion); //$NON-NLS-1$
		if (codeBase != null)
			Utils.addAttribute(attrs, "codebase", codeBase); //$NON-NLS-1$
		if (version != null)
			Utils.addAttribute(attrs, "version", version); //$NON-NLS-1$
		if (href != null)
			Utils.addAttribute(attrs, "href", href); //$NON-NLS-1$
		startElement(receiver, "jnlp", attrs); //$NON-NLS-1$
		emitInformation(receiver);
		emitSecurity(receiver);
		emitBooleanElement(receiver, "component-desc"); //$NON-NLS-1$
		emitResources(receiver);
		endElement(receiver, "jnlp"); //$NON-NLS-1$
		receiver.endDocument();
	}

	Map<String, String> createResource(String id, String os, String arch, String tag) {
		Resource resource = new Resource(os, arch, tag);
		List<Resource> resources = resourcesByPlatform.get(resource.getKey());
		if (resources == null) {
			resources = new ArrayList<Resource>();
			resourcesByPlatform.put(resource.getKey(), resources);
		}
		resources.add(resource);
		if (id != null)
			resourceById.put(id, resource);
		return resource;
	}

	void emitInformation(ContentHandler receiver) throws SAXException {
		startElement(receiver, "information"); //$NON-NLS-1$

		if (title != null)
			emitTextElement(receiver, "title", title); //$NON-NLS-1$
		if (vendor != null)
			emitTextElement(receiver, "vendor", vendor); //$NON-NLS-1$
		if (description != null)
			emitTextElement(receiver, "description", description); //$NON-NLS-1$
		if (offLineAllowed)
			emitBooleanElement(receiver, "offline-allowed"); //$NON-NLS-1$
		endElement(receiver, "information"); //$NON-NLS-1$
	}

	void emitResources(ContentHandler receiver) throws SAXException {
		AttributesImpl attrs = new AttributesImpl();
		for (List<Resource> resourceList : resourcesByPlatform.values()) {
			int top = resourceList.size();
			if (top == 0)
				continue;

			Resource first = resourceList.get(0);
			if (first.getOs() != null)
				Utils.addAttribute(attrs, "os", first.getOs()); //$NON-NLS-1$
			if (first.getArch() != null)
				Utils.addAttribute(attrs, "arch", first.getArch()); //$NON-NLS-1$

			startElement(receiver, "resources", attrs); //$NON-NLS-1$
			for (int idx = 0; idx < top; ++idx) {
				Resource resource = resourceList.get(idx);
				resource.toSax(receiver, null, null, resource.getDefaultTag());
			}
			endElement(receiver, "resources"); //$NON-NLS-1$
			attrs.clear();
		}
	}

	void emitSecurity(ContentHandler receiver) throws SAXException {
		startElement(receiver, "security"); //$NON-NLS-1$
		emitBooleanElement(receiver, "all-permissions"); //$NON-NLS-1$
		endElement(receiver, "security"); //$NON-NLS-1$
	}
}
