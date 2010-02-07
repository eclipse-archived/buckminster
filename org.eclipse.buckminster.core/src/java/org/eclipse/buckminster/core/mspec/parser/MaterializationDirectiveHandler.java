/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.mspec.parser;

import java.util.Map;

import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.common.parser.DocumentationHandler;
import org.eclipse.buckminster.core.common.parser.PropertyManagerHandler;
import org.eclipse.buckminster.core.mspec.ConflictResolution;
import org.eclipse.buckminster.core.mspec.builder.MaterializationDirectiveBuilder;
import org.eclipse.buckminster.core.mspec.model.MaterializationDirective;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.core.runtime.Path;
import org.eclipse.osgi.util.NLS;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @author Thomas Hallgren
 */
public abstract class MaterializationDirectiveHandler extends PropertyManagerHandler {
	private DocumentationHandler documentationHandler;

	private final MaterializationDirectiveBuilder builder;

	public MaterializationDirectiveHandler(AbstractHandler parent, String tag, MaterializationDirectiveBuilder builder) {
		super(parent, tag);
		this.builder = builder;
	}

	@Override
	public void childPopped(ChildHandler child) throws SAXException {
		if (child == documentationHandler)
			builder.setDocumentation(documentationHandler.createDocumentation());
		else
			super.childPopped(child);
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs) throws SAXException {
		ChildHandler ch;
		if (DocumentationHandler.TAG.equals(localName)) {
			if (documentationHandler == null)
				documentationHandler = new DocumentationHandler(this);
			ch = documentationHandler;
		} else
			ch = super.createHandler(uri, localName, attrs);
		return ch;
	}

	@Override
	public Map<String, String> getProperties() {
		return builder.getProperties();
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException {
		builder.clear();
		String tmp = getOptionalStringValue(attrs, MaterializationDirective.ATTR_INSTALL_LOCATION);
		if (tmp != null)
			builder.setInstallLocation(Path.fromPortableString(tmp));

		tmp = getOptionalStringValue(attrs, MaterializationDirective.ATTR_WORKSPACE_LOCATION);
		if (tmp != null)
			builder.setWorkspaceLocation(Path.fromPortableString(tmp));

		builder.setMaterializerID(getOptionalStringValue(attrs, MaterializationDirective.ATTR_MATERIALIZER));
		builder.setMaxParallelJobs(getOptionalIntValue(attrs, MaterializationDirective.ATTR_MAX_PARALLEL_JOBS, -1));

		tmp = getOptionalStringValue(attrs, MaterializationDirective.ATTR_CONFLICT_RESOLUTION);
		if (tmp != null) {
			try {
				builder.setConflictResolution(ConflictResolution.valueOf(tmp));
			} catch (IllegalArgumentException e) {
				throw new SAXParseException(NLS.bind(Messages.Invalid_value_for_attribute_0, MaterializationDirective.ATTR_CONFLICT_RESOLUTION), this
						.getDocumentLocator());
			}
		}
	}

	MaterializationDirectiveBuilder getBuilder() {
		return builder;
	}
}
