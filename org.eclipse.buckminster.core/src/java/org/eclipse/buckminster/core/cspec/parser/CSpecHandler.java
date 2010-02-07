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
import org.eclipse.buckminster.core.cspec.model.NamedElement;
import org.eclipse.buckminster.core.parser.ExtensionAwareHandler;
import org.eclipse.buckminster.core.version.VersionHelper;
import org.eclipse.buckminster.osgi.filter.FilterFactory;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.buckminster.sax.ChildPoppedListener;
import org.eclipse.core.runtime.CoreException;
import org.osgi.framework.InvalidSyntaxException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @author Thomas Hallgren
 */
public class CSpecHandler extends ExtensionAwareHandler implements ICSpecBuilderSupport, ChildPoppedListener {
	public static final String TAG = CSpec.TAG;

	private DocumentationHandler documentationHandler;

	private DependenciesHandler dependenciesHandler;

	private GeneratorsHandler generatorsHandler;

	private ArtifactsHandler artifactsHandler;

	private ActionsHandler actionsHandler;

	private GroupsHandler groupsHandler;

	private CSpecBuilder builder;

	public CSpecHandler(AbstractHandler parent) {
		super(parent);
	}

	public void childPopped(ChildHandler child) throws SAXException {
		if (child instanceof DocumentationHandler)
			builder.setDocumentation(((DocumentationHandler) child).createDocumentation());
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs) throws SAXException {
		ChildHandler ch;
		if (DocumentationHandler.TAG.equals(localName)) {
			if (documentationHandler == null)
				documentationHandler = new DocumentationHandler(this);
			ch = documentationHandler;
		} else if (DependenciesHandler.TAG.equals(localName)) {
			if (dependenciesHandler == null)
				dependenciesHandler = new DependenciesHandler(this);
			ch = dependenciesHandler;
		} else if (GeneratorsHandler.TAG.equals(localName)) {
			if (generatorsHandler == null)
				generatorsHandler = new GeneratorsHandler(this);
			ch = generatorsHandler;
		} else if (ArtifactsHandler.TAG.equals(localName)) {
			if (artifactsHandler == null)
				artifactsHandler = new ArtifactsHandler(this);
			ch = artifactsHandler;
		} else if (ActionsHandler.TAG.equals(localName)) {
			if (actionsHandler == null)
				actionsHandler = new ActionsHandler(this);
			ch = actionsHandler;
		} else if (GroupsHandler.TAG.equals(localName)) {
			if (groupsHandler == null)
				groupsHandler = new GroupsHandler(this);
			ch = groupsHandler;
		} else
			ch = super.createHandler(uri, localName, attrs);
		return ch;
	}

	public final CSpec getCSpec() {
		return builder.createCSpec();
	}

	public final CSpecBuilder getCSpecBuilder() {
		return builder;
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException {
		super.handleAttributes(attrs);

		builder = new CSpecBuilder();
		builder.setName(getOptionalStringValue(attrs, NamedElement.ATTR_NAME));
		builder.setComponentTypeID(getComponentType(attrs));
		builder.setProjectInfo(getOptionalURLValue(attrs, CSpec.ATTR_PROJECT_INFO));
		builder.setShortDesc(getOptionalStringValue(attrs, CSpec.ATTR_SHORT_DESC));

		try {
			builder.setVersion(VersionHelper.parseVersionAttributes(attrs));
		} catch (CoreException e) {
			throw new SAXParseException(e.getMessage(), this.getDocumentLocator());
		}

		String filter = getOptionalStringValue(attrs, CSpec.ATTR_FILTER);
		if (filter != null) {
			try {
				builder.setFilter(FilterFactory.newInstance(filter));
			} catch (InvalidSyntaxException e) {
				throw new SAXParseException(e.getMessage(), getDocumentLocator());
			}
		}
	}
}
