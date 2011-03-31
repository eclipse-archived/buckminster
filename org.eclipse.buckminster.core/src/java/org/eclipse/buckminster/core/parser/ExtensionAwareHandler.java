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

import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.cspec.model.ComponentName;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.osgi.util.NLS;
import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

public abstract class ExtensionAwareHandler extends ChildHandler {
	protected ExtensionAwareHandler(AbstractHandler parent) {
		super(parent);
	}

	public <H extends ChildHandler> H createContentHandler(Class<H> instanceClass, String namespace, Attributes attrs) throws SAXException {
		String xsiType = attrs.getValue(XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI, "type"); //$NON-NLS-1$
		return ((AbstractParser<?>) this.getTopHandler()).createContentHandler(this, instanceClass, namespace, xsiType);
	}

	protected String getComponentType(Attributes attrs) throws SAXException {
		String tmp = getOptionalStringValue(attrs, ComponentName.ATTR_COMPONENT_TYPE);
		if (tmp == null) {
			// Legacy. 0.1.0 had component category "plugin" and "feature"
			//
			tmp = getOptionalStringValue(attrs, "category"); //$NON-NLS-1$
			if (tmp != null) {
				logAttributeDeprecation(getTAG(), "category", ComponentName.ATTR_COMPONENT_TYPE); //$NON-NLS-1$
				if (tmp.equals("plugin")) //$NON-NLS-1$
					tmp = IComponentType.OSGI_BUNDLE;
				else if (tmp.equals("feature")) //$NON-NLS-1$
					tmp = IComponentType.ECLIPSE_FEATURE;
			}
		}
		return tmp;
	}

	protected void logAttributeDeprecation(String elementName, String attrName, String useInstead) {
		Locator locator = this.getDocumentLocator();
		warningOnce(NLS.bind(Messages.Use_of_deprecated_attribute_0_1_Use_attribute_2_instead_3_line_4, new Object[] { elementName, attrName,
				useInstead, locator.getSystemId(), new Integer(locator.getLineNumber()) }));
	}

	protected void logAttributeIgnored(String elementName, String attrName, String useInstead) {
		Locator locator = this.getDocumentLocator();
		warningOnce(NLS.bind(Messages.Use_of_deprecated_attribute_0_1_was_ignored_Use_attribute_2_instead_3_line_4, new Object[] { elementName,
				attrName, useInstead, locator.getSystemId(), new Integer(locator.getLineNumber()) }));
	}

	protected void logAttributeValueDeprecation(String elementName, String attrName, String oldValue, String useInstead) {
		Locator locator = this.getDocumentLocator();
		warningOnce(NLS.bind(Messages.Use_of_deprecated_value_for_attribute_0_1_Was_2_should_be_3_4_line_5, new Object[] { elementName, attrName,
				oldValue, useInstead, locator.getSystemId(), new Integer(locator.getLineNumber()) }));
	}

	protected void logElementDeprecated(String elementName, String useInstead) {
		Locator locator = this.getDocumentLocator();
		warningOnce(NLS.bind(Messages.Use_of_deprecated_element_0_Use_element_1_instead_2_line_3,
				new Object[] { elementName, useInstead, locator.getSystemId(), new Integer(locator.getLineNumber()) }));
	}

	protected void logElementIgnored(String elementName) {
		Locator locator = this.getDocumentLocator();
		warningOnce(NLS.bind(Messages.Use_of_deprecated_element_0_was_ignored_1_line_2, new Object[] { elementName, locator.getSystemId(),
				new Integer(locator.getLineNumber()) }));
	}

	protected void warningOnce(String warning) {
		((AbstractParser<?>) getTopHandler()).warningOnce(warning);
	}
}
