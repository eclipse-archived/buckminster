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
 * <p>
 * An instanceof this class represents the JNLP <code>version.xml</code>
 * </p>
 * 
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
public class JNLPVersionModel extends SAXModel {
	public static class Platform extends Resource {
		private final String productVersionId;

		Platform(String file, String name, String versionId, String productVersionId) {
			super(file, name, versionId);
			this.productVersionId = productVersionId;
		}

		@Override
		public String getDefaultTag() {
			return "platform"; //$NON-NLS-1$
		}

		@Override
		void emitContent(ContentHandler receiver) throws SAXException {
			super.emitContent(receiver);
			emitTextElement(receiver, "product-version-id", productVersionId); //$NON-NLS-1$
		}
	}

	public static class Resource implements ISaxableElement {
		private final String name;

		private final String file;

		private final String versionId;

		private List<String> oss;

		private List<String> archs;

		private List<String> locales;

		Resource(String file, String name, String versionId) {
			this.file = file;
			this.name = name;
			this.versionId = versionId;
		}

		public void addArch(String arch) {
			if (archs == null)
				archs = new ArrayList<String>();
			archs.add(arch);
		}

		public void addLocale(String locale) {
			if (locales == null)
				locales = new ArrayList<String>();
			locales.add(locale);
		}

		public void addOs(String os) {
			if (oss == null)
				oss = new ArrayList<String>();
			oss.add(os);
		}

		@Override
		public String getDefaultTag() {
			return "resource"; //$NON-NLS-1$
		}

		@Override
		public void toSax(ContentHandler receiver, String namespace, String prefix, String localName) throws SAXException {
			startElement(receiver, localName);
			emitContent(receiver);
			endElement(receiver, localName);
		}

		void emitContent(ContentHandler receiver) throws SAXException {
			startElement(receiver, "pattern"); //$NON-NLS-1$
			emitTextElement(receiver, "name", name); //$NON-NLS-1$
			emitTextElement(receiver, "version-id", versionId); //$NON-NLS-1$
			emitTextElements(receiver, "os", oss); //$NON-NLS-1$
			emitTextElements(receiver, "arch", archs); //$NON-NLS-1$
			emitTextElements(receiver, "locale", locales); //$NON-NLS-1$
			endElement(receiver, "pattern"); //$NON-NLS-1$
			emitTextElement(receiver, "file", file); //$NON-NLS-1$
		}
	}

	private static void emitTextElements(ContentHandler receiver, String tag, List<String> values) throws SAXException {
		if (values != null)
			for (String value : values)
				emitTextElement(receiver, tag, value);
	}

	private final ArrayList<Resource> resources = new ArrayList<Resource>();

	public Platform addPlatform(String file, String name, String versionId, String productVersionId) {
		Platform platform = new Platform(file, name, versionId, productVersionId);
		resources.add(platform);
		return platform;
	}

	public Resource addResource(String file, String name, String versionId) {
		Resource resource = new Resource(file, name, versionId);
		resources.add(resource);
		return resource;
	}

	@Override
	public void toSax(ContentHandler receiver) throws SAXException {
		receiver.startDocument();
		startElement(receiver, "jnlp-versions"); //$NON-NLS-1$
		for (Resource resource : resources)
			resource.toSax(receiver, null, null, resource.getDefaultTag());
		endElement(receiver, "jnlp-versions"); //$NON-NLS-1$
		receiver.endDocument();
	}
}
