/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspecext.parser;

import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.parser.CSpecHandler;
import org.eclipse.buckminster.core.cspecext.builder.AlterCSpecBuilder;
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
class AlterCSpecHandler extends AlterHandler {
	public static final String TAG = CSpecExtension.TAG;

	private final CSpecHandler baseHandler;

	private AlterCSpecBuilder builder;

	private final AlterAttributesHandler alterActionsHandler = new AlterAttributesHandler(this) {
		@Override
		public String getTAG() {
			return CSpecExtension.ELEM_ALTER_ACTIONS;
		}

		@Override
		AlterAttributeHandler createAttributeHandler(boolean publ) {
			return new AlterActionHandler(this, publ);
		}
	};

	private final AlterAttributesHandler alterArtifactsHandler = new AlterAttributesHandler(this) {
		@Override
		public String getTAG() {
			return CSpecExtension.ELEM_ALTER_ARTIFACTS;
		}

		@Override
		AlterAttributeHandler createAttributeHandler(boolean publ) {
			return new AlterArtifactHandler(this, publ);
		}
	};

	private final AlterAttributesHandler alterGroupsHandler = new AlterAttributesHandler(this) {
		@Override
		public String getTAG() {
			return CSpecExtension.ELEM_ALTER_GROUPS;
		}

		@Override
		AlterAttributeHandler createAttributeHandler(boolean publ) {
			return new AlterGroupHandler(this, publ);
		}
	};

	private final AlterDependenciesHandler alterDependenciesHandler = new AlterDependenciesHandler(this);

	AlterCSpecHandler(AbstractHandler parent) {
		super(parent);
		baseHandler = new CSpecHandler(parent);
	}

	public void childPopped(ChildHandler child) throws SAXException {
		((ChildPoppedListener) baseHandler).childPopped(child);
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs) throws SAXException {
		ChildHandler ch;
		if (alterActionsHandler.getTAG().equals(localName))
			ch = alterActionsHandler;
		else if (alterGroupsHandler.getTAG().equals(localName))
			ch = alterGroupsHandler;
		else if (alterArtifactsHandler.getTAG().equals(localName))
			ch = alterArtifactsHandler;
		else if (AlterDependenciesHandler.TAG.equals(localName))
			ch = alterDependenciesHandler;
		else
			ch = baseHandler.createHandler(uri, localName, attrs);
		return ch;
	}

	@Override
	public CSpecBuilder getCSpecBuilder() {
		return baseHandler.getCSpecBuilder();
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException {
		baseHandler.handleAttributes(attrs);
		builder = new AlterCSpecBuilder(baseHandler.getCSpecBuilder());
	}

	final void addRemoveAttribute(String name) {
		builder.addRemoveAttribute(name);
	}

	final void addRemoveDependency(String name) {
		builder.addRemoveDependency(name);
	}

	@Override
	final AlterCSpecBuilder getAlterCSpecBuilder() {
		return builder;
	}

	final CSpecExtension getCSpecExtension() throws SAXException {
		try {
			return builder.createAlteredCSpec();
		} catch (CoreException e) {
			throw new SAXParseException(e.getMessage(), this.getDocumentLocator());
		}
	}

	@Override
	final String getCSpecExtensionName() {
		return builder.getBaseBuilder().getName();
	}
}
