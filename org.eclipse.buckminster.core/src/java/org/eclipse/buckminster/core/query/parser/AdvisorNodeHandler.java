/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.query.parser;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;
import java.util.StringTokenizer;

import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.common.parser.DocumentationHandler;
import org.eclipse.buckminster.core.common.parser.PropertyManagerHandler;
import org.eclipse.buckminster.core.helpers.DateAndTimeUtils;
import org.eclipse.buckminster.core.query.IAdvisorNode;
import org.eclipse.buckminster.core.query.builder.AdvisorNodeBuilder;
import org.eclipse.buckminster.core.query.model.AdvisorNode;
import org.eclipse.buckminster.core.query.model.MutableLevel;
import org.eclipse.buckminster.core.query.model.SourceLevel;
import org.eclipse.buckminster.core.version.VersionHelper;
import org.eclipse.buckminster.core.version.VersionSelector;
import org.eclipse.buckminster.osgi.filter.FilterFactory;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.core.runtime.CoreException;
import org.osgi.framework.InvalidSyntaxException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @author Thomas Hallgren
 */
public class AdvisorNodeHandler extends PropertyManagerHandler
{
	public static final String TAG = AdvisorNode.TAG;

	private DocumentationHandler m_documentationHandler;

	private AdvisorNodeBuilder m_builder;

	public AdvisorNodeHandler(AbstractHandler parent)
	{
		super(parent, TAG);
	}

	@Override
	public void childPopped(ChildHandler child) throws SAXException
	{
		if(child == m_documentationHandler)
			m_builder.setDocumentation(m_documentationHandler.createDocumentation());
		else
			super.childPopped(child);
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs) throws SAXException
	{
		ChildHandler ch;
		if(DocumentationHandler.TAG.equals(localName))
		{
			if(m_documentationHandler == null)
				m_documentationHandler = new DocumentationHandler(this);
			ch = m_documentationHandler;
		}
		else
			ch = super.createHandler(uri, localName, attrs);
		return ch;
	}

	@Override
	public Map<String, String> getProperties()
	{
		return m_builder.getProperties();
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException
	{
		m_builder = new AdvisorNodeBuilder();
		m_builder.setNamePattern(getOptionalPatternValue(attrs, AdvisorNode.ATTR_NAME_PATTERN));
		m_builder.setComponentTypeID(getComponentType(attrs));

		String filterStr = getOptionalStringValue(attrs, AdvisorNode.ATTR_FILTER);
		if(filterStr != null)
		{
			try
			{
				m_builder.setFilter(FilterFactory.newInstance(filterStr));
			}
			catch(InvalidSyntaxException e)
			{
				throw new SAXParseException(e.getMessage(), getDocumentLocator(), e);
			}
		}

		String tmp = getOptionalStringValue(attrs, AdvisorNode.ATTR_OVERLAY_FOLDER);
		if(tmp != null)
		{
			try
			{
				m_builder.setOverlayFolder(URLUtils.normalizeToURL(tmp));
			}
			catch(IOException e)
			{
				throw new SAXParseException(e.getMessage(), getDocumentLocator());
			}
		}

		tmp = getOptionalStringValue(attrs, AdvisorNode.ATTR_MUTABLE_LEVEL);
		if(tmp != null)
			m_builder.setMutableLevel(MutableLevel.valueOf(tmp));

		tmp = getOptionalStringValue(attrs, AdvisorNode.ATTR_SOURCE_LEVEL);
		if(tmp != null)
			m_builder.setSourceLevel(SourceLevel.valueOf(tmp));
		try
		{
			m_builder.setVersionOverride(VersionHelper.parseVersionRangeAttributes(attrs,
					AdvisorNode.ATTR_VERSION_OVERRIDE, AdvisorNode.ATTR_VERSION_OVERRIDE_TYPE));
		}
		catch(CoreException e)
		{
			throw new SAXParseException(e.toString(), this.getDocumentLocator());
		}

		tmp = getOptionalStringValue(attrs, AdvisorNode.ATTR_BRANCH_TAG_PATH);
		if(tmp != null)
		{
			try
			{
				m_builder.setBranchTagPath(VersionSelector.fromPath(tmp));
			}
			catch(IllegalArgumentException e)
			{
				throw new SAXParseException(e.toString(), this.getDocumentLocator());
			}
		}

		tmp = getOptionalStringValue(attrs, AdvisorNode.ATTR_REVISION);
		if(tmp != null)
		{
			try
			{
				m_builder.setRevision(Long.parseLong(tmp));
			}
			catch(NumberFormatException e)
			{
				throw new SAXParseException(e.toString(), this.getDocumentLocator());
			}
		}

		tmp = getOptionalStringValue(attrs, AdvisorNode.ATTR_TIMESTAMP);
		if(tmp != null)
		{
			try
			{
				m_builder.setTimestamp(DateAndTimeUtils.fromISOFormat(tmp));
			}
			catch(ParseException e)
			{
				throw new SAXParseException(e.toString(), this.getDocumentLocator());
			}
		}

		tmp = getOptionalStringValue(attrs, AdvisorNode.ATTR_ATTRIBUTES);
		if(tmp != null)
		{
			StringTokenizer tokens = new StringTokenizer(tmp, ","); //$NON-NLS-1$
			while(tokens.hasMoreElements())
				m_builder.addAttribute(tokens.nextToken());
		}

		tmp = getOptionalStringValue(attrs, AdvisorNode.ATTR_RESOLUTION_PRIO);
		if(tmp != null)
		{
			int max = IAdvisorNode.DEFAULT_RESOLUTION_PRIO.length;
			int[] prios = new int[max];
			try
			{
				int idx = 0;
				StringTokenizer tokens = new StringTokenizer(tmp, ","); //$NON-NLS-1$
				while(tokens.hasMoreElements())
				{
					if(idx == max)
					{
						idx++; // Overflow
						break;
					}
					prios[idx++] = Integer.parseInt(tokens.nextToken());
				}
				if(idx != max)
					throw new SAXParseException(Messages.Incorrect_number_of_resolution_priorites,
							this.getDocumentLocator());
			}
			catch(NumberFormatException e)
			{
				throw new SAXParseException(e.toString(), this.getDocumentLocator());
			}
			m_builder.setResolutionPrio(prios);
		}

		m_builder.setPrune(getOptionalBooleanValue(attrs, AdvisorNode.ATTR_PRUNE, false));
		m_builder.setAllowCircularDependency(getOptionalBooleanValue(attrs, AdvisorNode.ATTR_ALLOW_CIRCULAR_DEPENDENCY,
				false));
		m_builder.setSkipComponent(getOptionalBooleanValue(attrs, AdvisorNode.ATTR_SKIP_COMPONENT, false));

		tmp = getOptionalStringValue(attrs, AdvisorNode.ATTR_USE_TARGET_PLATFORM);
		if(tmp == null)
			// Get deprecated alternative
			tmp = getOptionalStringValue(attrs, "useInstalled"); //$NON-NLS-1$
		m_builder.setUseTargetPlatform(tmp == null || "true".equalsIgnoreCase(tmp)); //$NON-NLS-1$

		tmp = getOptionalStringValue(attrs, AdvisorNode.ATTR_USE_WORKSPACE);
		if(tmp == null)
			// Get deprecated alternative
			tmp = getOptionalStringValue(attrs, "useProject"); //$NON-NLS-1$
		m_builder.setUseWorkspace(tmp == null || "true".equalsIgnoreCase(tmp)); //$NON-NLS-1$

		m_builder.setUseMaterialization(getOptionalBooleanValue(attrs, AdvisorNode.ATTR_USE_MATERIALIZATION, true));
		m_builder.setUseRemoteResolution(getOptionalBooleanValue(attrs, AdvisorNode.ATTR_USE_REMOTE_RESOLUTION, true));
		m_builder.setSystemDiscovery(getOptionalBooleanValue(attrs, AdvisorNode.ATTR_SYSTEM_DISCOVERY, true));

		String spacePath = getOptionalStringValue(attrs, "spacePath"); //$NON-NLS-1$
		if(spacePath != null)
		{
			logAttributeDeprecation(TAG, "spacePath", "property buckminster.spacePath="); //$NON-NLS-1$ //$NON-NLS-2$
			getProperties().put("buckminster.spacepath", spacePath); //$NON-NLS-1$
		}
	}

	AdvisorNodeBuilder getAdvisorNodeBuilder()
	{
		return m_builder;
	}
}
