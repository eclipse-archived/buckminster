/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspecext.parser;

import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.model.AttributeAlreadyDefinedException;
import org.eclipse.buckminster.core.cspec.model.DependencyAlreadyDefinedException;
import org.eclipse.buckminster.core.cspec.parser.CSpecHandler;
import org.eclipse.buckminster.core.cspecext.builder.AlterAttributeBuilder;
import org.eclipse.buckminster.core.cspecext.builder.AlterCSpecBuilder;
import org.eclipse.buckminster.core.cspecext.builder.AlterDependencyBuilder;
import org.eclipse.buckminster.core.cspecext.model.CSpecExtension;
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
class AlterCSpecHandler extends AlterHandler
{
	public static final String TAG = CSpecExtension.TAG;

	private final CSpecHandler m_baseHandler;
	private AlterCSpecBuilder m_builder;

	private final AlterAttributesHandler m_alterActionsHandler = new AlterAttributesHandler(this)
	{
		@Override
		public String getTAG()
		{
			return CSpecExtension.ELEM_ALTER_ACTIONS;
		}

		@Override
		AlterAttributeHandler createAttributeHandler(boolean publ)
		{
			return new AlterActionHandler(this, publ);
		}		
	};

	private final AlterAttributesHandler m_alterArtifactsHandler = new AlterAttributesHandler(this)
	{
		@Override
		public String getTAG()
		{
			return CSpecExtension.ELEM_ALTER_ARTIFACTS;
		}

		@Override
		AlterAttributeHandler createAttributeHandler(boolean publ)
		{
			return new AlterArtifactHandler(this, publ);
		}		
	};

	private final AlterAttributesHandler m_alterGroupsHandler = new AlterAttributesHandler(this)
	{
		@Override
		public String getTAG()
		{
			return CSpecExtension.ELEM_ALTER_GROUPS;
		}

		@Override
		AlterAttributeHandler createAttributeHandler(boolean publ)
		{
			return new AlterGroupHandler(this, publ);
		}		
	};

	private final AlterDependenciesHandler m_alterDependenciesHandler = new AlterDependenciesHandler(this);

	AlterCSpecHandler(AbstractHandler parent)
	{
		super(parent);
		m_baseHandler = new CSpecHandler(parent);
	}

	public void childPopped(ChildHandler child) throws SAXException
	{
		((ChildPoppedListener)m_baseHandler).childPopped(child);
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs)
	throws SAXException
	{
		ChildHandler ch;
		if(m_alterActionsHandler.getTAG().equals(localName))
			ch = m_alterActionsHandler;
		else if(m_alterGroupsHandler.getTAG().equals(localName))
			ch = m_alterGroupsHandler;
		else if(m_alterArtifactsHandler.getTAG().equals(localName))
			ch = m_alterArtifactsHandler;
		else if(AlterDependenciesHandler.TAG.equals(localName))
			ch = m_alterDependenciesHandler;
		else
			ch = m_baseHandler.createHandler(uri, localName, attrs);
		return ch;
	}

	@Override
	public void handleAttributes(Attributes attrs)
	throws SAXException
	{
		m_baseHandler.handleAttributes(attrs);
		m_builder = new AlterCSpecBuilder(m_baseHandler.getCSpecBuilder());
	}

	final void addAlterAttribute(AlterAttributeBuilder alterAttribute) throws SAXException
	{
		try
		{
			m_builder.addAlterAttribute(alterAttribute);
		}
		catch(AttributeAlreadyDefinedException e)
		{
			throw new SAXParseException(e.getMessage(), this.getDocumentLocator());
		}
	}

	final void addAlterDependency(AlterDependencyBuilder alterDependency) throws SAXException
	{
		try
		{
			m_builder.addAlterDependency(alterDependency);
		}
		catch(DependencyAlreadyDefinedException e)
		{
			throw new SAXParseException(e.getMessage(), this.getDocumentLocator());
		}
	}

	final void addRemoveAttribute(String name)
	{
		m_builder.addRemoveAttribute(name);
	}

	final void addRemoveDependency(String name)
	{
		m_builder.addRemoveDependency(name);
	}

	@Override
	public CSpecBuilder getCSpecBuilder()
	{
		return m_baseHandler.getCSpecBuilder();
	}

	@Override
	final AlterCSpecBuilder getAlterCSpecBuilder()
	{
		return m_builder;
	}

	final CSpecExtension getCSpecExtension() throws SAXException
	{
		try
		{
			return m_builder.createAlteredCSpec();
		}
		catch(CoreException e)
		{
			throw new SAXParseException(e.getMessage(), this.getDocumentLocator());
		}
	}

	@Override
	final String getCSpecExtensionName()
	{
		return m_builder.getBaseBuilder().getName();
	}
}

