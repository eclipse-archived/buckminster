/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.mspec.model;

import java.util.Map;

import org.eclipse.buckminster.core.common.model.Documentation;
import org.eclipse.buckminster.core.common.model.SAXEmitter;
import org.eclipse.buckminster.core.materializer.IMaterializer;
import org.eclipse.buckminster.core.metadata.model.UUIDKeyed;
import org.eclipse.buckminster.core.mspec.builder.MaterializationDirectiveBuilder;
import org.eclipse.buckminster.sax.ISaxableElement;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.IPath;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 */
public abstract class MaterializationDirective implements ISaxableElement
{
	public static final String ATTR_INSTALL_LOCATION = "installLocation";
	public static final String ATTR_MATERIALIZER = "materializer";
	public static final String ATTR_CONFLICT_RESOLUTION = "conflictResolution";

	private final Map<String,String> m_properties;
	private final IPath m_installLocation;
	private final String m_materializer;
	private final ConflictResolution m_conflictResolution;
	private final Documentation m_documentation;

	public MaterializationDirective(MaterializationDirectiveBuilder builder)
	{
		m_documentation = builder.getDocumentation();
		m_installLocation = builder.getInstallLocation();
		String mt = builder.getMaterializer();
		if(mt == null)
			mt = IMaterializer.FILE_SYSTEM;
		m_materializer = mt;

		ConflictResolution cr = builder.getConflictResolution();
		if(cr == null)
			cr = ConflictResolution.UPDATE;
		m_conflictResolution = cr;
		m_properties = UUIDKeyed.createUnmodifiableProperties(builder.getProperties());
	}

	public Documentation getDocumentation()
	{
		return m_documentation;
	}

	public IPath getInstallLocation()
	{
		return m_installLocation;
	}

	public String getMaterializer()
	{
		return m_materializer;
	}

	public Map<String,String> getProperties()
	{
		return m_properties;
	}

	public ConflictResolution getConflictResolution()
	{
		return m_conflictResolution;
	}

	public void toSax(ContentHandler receiver, String namespace, String prefix, String localName) throws SAXException
	{
		AttributesImpl attrs = new AttributesImpl();
		appendAttributes(attrs);
		String qName = Utils.makeQualifiedName(prefix, localName);
		receiver.startElement(namespace, localName, qName, attrs);
		emitElements(receiver, namespace, prefix);
		receiver.endElement(namespace, localName, qName);
	}

	protected void appendAttributes(AttributesImpl attrs) throws SAXException
	{
		if(m_installLocation != null)
			Utils.addAttribute(attrs, ATTR_INSTALL_LOCATION, m_installLocation.toPortableString());
		if(m_materializer != null)
			Utils.addAttribute(attrs, ATTR_MATERIALIZER, m_materializer);
		Utils.addAttribute(attrs, ATTR_CONFLICT_RESOLUTION, m_conflictResolution.name());
	}

	protected void emitElements(ContentHandler receiver, String namespace, String prefix) throws SAXException
	{
		if(m_documentation != null)
			m_documentation.toSax(receiver, namespace, prefix, m_documentation.getDefaultTag());
		SAXEmitter.emitProperties(receiver, m_properties, namespace, prefix, true, false);
	}
}
