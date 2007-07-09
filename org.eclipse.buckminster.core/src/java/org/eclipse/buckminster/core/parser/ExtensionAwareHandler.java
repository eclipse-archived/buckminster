/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.parser;

import javax.xml.XMLConstants;

import org.eclipse.buckminster.core.cspec.model.ComponentName;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;


public abstract class ExtensionAwareHandler extends ChildHandler
{
	protected ExtensionAwareHandler(AbstractHandler parent)
	{
		super(parent);
	}

	public <H extends ChildHandler> H createContentHandler(Class<H> instanceClass, String namespace, Attributes attrs)
	throws SAXException
	{
		String xsiType = attrs.getValue(XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI, "type");
		return ((AbstractParser<?>)this.getTopHandler()).createContentHandler(this, instanceClass, namespace, xsiType);
	}

	protected void logAttributeValueDeprecation(String elementName, String attrName, String oldValue, String useInstead)
	{
		Locator locator = this.getDocumentLocator();
		warningOnce(String.format(
			"Use of deprecated value for attribute <%s %s>. Was '%s', should be '%s' : %s, line %s. ",
				elementName, attrName, oldValue, useInstead,
				locator.getSystemId(), new Integer(locator.getLineNumber())));
	}

	protected void logAttributeDeprecation(String elementName, String attrName, String useInstead)
	{
		Locator locator = this.getDocumentLocator();
		warningOnce(String.format(
			"Use of deprecated attribute <%s %s> Use attribute '%s' instead : %s, line %s. ",
				elementName, attrName, useInstead,
				locator.getSystemId(), new Integer(locator.getLineNumber())));
	}

	protected void logAttributeIgnored(String elementName, String attrName, String useInstead)
	{
		Locator locator = this.getDocumentLocator();
		warningOnce(String.format(
			"Use of deprecated attribute <%s %s> was ignored. Use attribute '%s' instead : %s, line %s. ",
				elementName, attrName, useInstead,
				locator.getSystemId(), new Integer(locator.getLineNumber())));
	}

	protected void logElementDeprecated(String elementName, String useInstead)
	{
		Locator locator = this.getDocumentLocator();
		warningOnce(String.format(
			"Use of deprecated element <%s>. Use element <%s> instead : %s, line %s. ",
			elementName, useInstead, locator.getSystemId(), new Integer(locator.getLineNumber())));
	}

	protected void logElementIgnored(String elementName)
	{
		Locator locator = this.getDocumentLocator();
		warningOnce(String.format("Use of deprecated element %s was ignored : %s, line %s. ",
				elementName, locator.getSystemId(), new Integer(locator.getLineNumber())));
	}

	protected void warningOnce(String warning)
	{
		((AbstractParser<?>)getTopHandler()).warningOnce(warning);
	}

	protected String getComponentType(Attributes attrs) throws SAXException
	{
		String tmp = getOptionalStringValue(attrs, ComponentName.ATTR_COMPONENT_TYPE);
		if(tmp == null)
		{
			// Legacy. 0.1.0 had component category "plugin" and "feature"
			//
			tmp = getOptionalStringValue(attrs, "category");
			if(tmp != null)
			{
				logAttributeDeprecation(getTAG(), "category", ComponentName.ATTR_COMPONENT_TYPE);
				if(tmp.equals("plugin"))
					tmp = IComponentType.OSGI_BUNDLE;
				else if(tmp.equals("feature"))
					tmp = IComponentType.ECLIPSE_FEATURE;
			}
		}
		return tmp;
	}
}
