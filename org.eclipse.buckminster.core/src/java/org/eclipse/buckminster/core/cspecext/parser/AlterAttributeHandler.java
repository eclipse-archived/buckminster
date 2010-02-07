/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspecext.parser;

import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.cspec.builder.AttributeBuilder;
import org.eclipse.buckminster.core.cspec.builder.TopLevelAttributeBuilder;
import org.eclipse.buckminster.core.cspec.model.TopLevelAttribute;
import org.eclipse.buckminster.core.cspec.parser.AttributeHandler;
import org.eclipse.buckminster.core.cspec.parser.IAttributeBuilderSupport;
import org.eclipse.buckminster.core.cspecext.builder.AlterAttributeBuilder;
import org.eclipse.buckminster.core.cspecext.model.AlterAttribute;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.buckminster.sax.ChildPoppedListener;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 */
abstract class AlterAttributeHandler extends AlterHandler implements IAttributeBuilderSupport {
	private final AttributeHandler baseHandler;

	private final AlterPropertiesHandler alterInstallationHints = new AlterPropertiesHandler(this, AlterAttribute.ELEM_ALTER_INSTALLER_HINTS) {
		@Override
		public ExpandingProperties<String> getProperties() {
			return builder.getAlteredHints();
		}

		@Override
		protected void addRemovedProperty(String key) throws SAXException {
			builder.addRemovedInstallerHint(key);
		}
	};

	private AlterAttributeBuilder builder;

	AlterAttributeHandler(AbstractHandler parent, AttributeHandler baseHandler) {
		super(parent);
		this.baseHandler = baseHandler;
	}

	public void childPopped(ChildHandler child) throws SAXException {
		((ChildPoppedListener) baseHandler).childPopped(child);
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs) throws SAXException {
		ChildHandler ch;
		if (alterInstallationHints.getTAG().equals(localName))
			ch = alterInstallationHints;
		else
			ch = baseHandler.createHandler(uri, localName, attrs);
		return ch;
	}

	public TopLevelAttributeBuilder getAttributeBuilder() {
		return baseHandler.getAttributeBuilder();
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException {
		baseHandler.handleAttributes(attrs);
		builder = this.createAlterAttributeBuilder(baseHandler.getAttributeBuilder());
		builder.setCSpecName(this.getCSpecExtensionName());
	}

	abstract AlterAttributeBuilder createAlterAttributeBuilder(AttributeBuilder baseBuilder);

	AlterAttribute<? extends TopLevelAttribute> getAlterAttribute() {
		return builder.createAlterAttribute();
	}

	AttributeHandler getBaseHandler() {
		return baseHandler;
	}

	AlterAttributeBuilder getBuilder() {
		return builder;
	}
}
