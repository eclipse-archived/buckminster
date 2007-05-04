/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.parser;

import org.eclipse.buckminster.core.common.parser.DocumentationHandler;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.cspec.model.ComponentName;
import org.eclipse.buckminster.core.cspec.model.NamedElement;
import org.eclipse.buckminster.core.internal.version.OSGiVersionType;
import org.eclipse.buckminster.core.parser.ExtensionAwareHandler;
import org.eclipse.buckminster.core.version.VersionFactory;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.buckminster.sax.ChildPoppedListener;
import org.eclipse.core.runtime.CoreException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;


/**
 * @author Thomas Hallgren
 */
public class CSpecHandler extends ExtensionAwareHandler implements ICSpecBuilderSupport, ChildPoppedListener
{
	public static final String TAG = CSpec.TAG;

	private DocumentationHandler m_documentationHandler;
	private DependenciesHandler m_dependenciesHandler;
	private GeneratorsHandler m_generatorsHandler;
	private ArtifactsHandler m_artifactsHandler;
	private ActionsHandler m_actionsHandler;
	private GroupsHandler m_groupsHandler;
	private CSpecBuilder m_builder;

	public CSpecHandler(AbstractHandler parent)
	{
		super(parent);
	}

	public final CSpec getCSpec() throws SAXException
	{
		try
		{
			return m_builder.createCSpec();
		}
		catch(CoreException e)
		{
			throw new SAXParseException(e.getMessage(), this.getDocumentLocator());
		}
	}

	public final CSpecBuilder getCSpecBuilder()
	{
		return m_builder;
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs)
	throws SAXException
	{
		ChildHandler ch;
		if(DocumentationHandler.TAG.equals(localName))
		{
			if(m_documentationHandler == null)
				m_documentationHandler = new DocumentationHandler(this);
			ch = m_documentationHandler;
		}
		else if(DependenciesHandler.TAG.equals(localName))
		{
			if(m_dependenciesHandler == null)
				m_dependenciesHandler = new DependenciesHandler(this);
			ch = m_dependenciesHandler;
		}
		else if(GeneratorsHandler.TAG.equals(localName))
		{
			if(m_generatorsHandler == null)
				m_generatorsHandler = new GeneratorsHandler(this);
			ch = m_generatorsHandler;
		}
		else if(ArtifactsHandler.TAG.equals(localName))
		{
			if(m_artifactsHandler == null)
				m_artifactsHandler = new ArtifactsHandler(this);
			ch = m_artifactsHandler;
		}
		else if(ActionsHandler.TAG.equals(localName))
		{
			if(m_actionsHandler == null)
				m_actionsHandler = new ActionsHandler(this);
			ch = m_actionsHandler;
		}
		else if(GroupsHandler.TAG.equals(localName))
		{
			if(m_groupsHandler == null)
				m_groupsHandler = new GroupsHandler(this);
			ch = m_groupsHandler;			
		}
		else
			ch = super.createHandler(uri, localName, attrs);
		return ch;
	}

	@Override
	public void handleAttributes(Attributes attrs)
	throws SAXException
	{
		super.handleAttributes(attrs);

		m_builder = new CSpecBuilder();
		m_builder.setName(getOptionalStringValue(attrs, NamedElement.ATTR_NAME));
		m_builder.setCategory(getOptionalStringValue(attrs, ComponentName.ATTR_CATEGORY));
		m_builder.setShortDesc(getOptionalStringValue(attrs, CSpec.ATTR_SHORT_DESC));

		String tmp = getOptionalStringValue(attrs, ComponentIdentifier.ATTR_VERSION);
		if(tmp != null)
		{
			String type = getOptionalStringValue(attrs, ComponentIdentifier.ATTR_VERSION_TYPE);
			if(type == null)
				type = OSGiVersionType.ID;
			try
			{
				m_builder.setVersion(VersionFactory.createVersion(type, tmp));
			}
			catch(CoreException e)
			{
				throw new SAXParseException(e.getMessage(), this.getDocumentLocator());
			}
		}
	}

	public void childPopped(ChildHandler child) throws SAXException
	{
		if(child instanceof DocumentationHandler)
			m_builder.setDocumentation(((DocumentationHandler)child).createDocumentation());
	}
}

