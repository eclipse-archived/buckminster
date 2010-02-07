/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.parser;

import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.cspec.builder.ActionArtifactBuilder;
import org.eclipse.buckminster.core.cspec.builder.ActionBuilder;
import org.eclipse.buckminster.core.cspec.builder.AttributeBuilder;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.builder.TopLevelAttributeBuilder;
import org.eclipse.buckminster.core.cspec.model.Action;
import org.eclipse.buckminster.core.cspec.model.Artifact;
import org.eclipse.buckminster.core.cspec.model.AttributeAlreadyDefinedException;
import org.eclipse.buckminster.core.cspec.model.Prerequisite;
import org.eclipse.buckminster.core.cspec.model.TopLevelAttribute;
import org.eclipse.buckminster.core.cspec.model.UpToDatePolicy;
import org.eclipse.buckminster.core.parser.ExtensionAwareHandler;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.buckminster.sax.ChildPoppedListener;
import org.eclipse.core.runtime.Path;
import org.eclipse.osgi.util.NLS;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @author Thomas Hallgren
 */
public class ProductsHandler extends ExtensionAwareHandler implements ChildPoppedListener, ICSpecBuilderSupport, IAttributeBuilderSupport {
	public static class ProductArtifactHandler extends ArtifactHandler {
		public ProductArtifactHandler(AbstractHandler parent, boolean publ) {
			super(parent, publ);
		}

		@Override
		public void handleAttributes(Attributes attrs) throws SAXException {
			super.handleAttributes(attrs);
			ActionArtifactBuilder productBuilder = (ActionArtifactBuilder) this.getBuilder();
			productBuilder.setActionName(((ProductsHandler) this.getParentHandler()).getActionName());
			productBuilder.setAlias(getOptionalStringValue(attrs, Prerequisite.ATTR_ALIAS));
		}

		@Override
		protected TopLevelAttributeBuilder createAttributeBuilder() {
			return getCSpecBuilder().createActionArtifactBuilder();
		}
	}

	public static final String TAG = Action.ELEM_PRODUCTS;

	private final PathHandler pathHandler = new PathHandler(this);

	private final ProductArtifactHandler privateHandler = new ProductArtifactHandler(this, false);

	private final ProductArtifactHandler publicHandler = new ProductArtifactHandler(this, true);

	ProductsHandler(AbstractHandler parent) {
		super(parent);
	}

	public void childPopped(ChildHandler child) throws SAXException {
		if (child == pathHandler)
			((ActionBuilder) this.getAttributeBuilder()).addProductPath(pathHandler.getPath());
		else if (child == publicHandler)
			this.addProductArtifact(publicHandler.getAttributeBuilder());
		else if (child == privateHandler)
			this.addProductArtifact(privateHandler.getAttributeBuilder());
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs) throws SAXException {
		ChildHandler ch = null;
		if (PathHandler.TAG.equals(localName))
			ch = pathHandler;
		else if (TopLevelAttribute.PUBLIC_TAG.equals(localName))
			ch = publicHandler;
		else if (TopLevelAttribute.PRIVATE_TAG.equals(localName))
			ch = privateHandler;
		else
			ch = super.createHandler(uri, localName, attrs);
		return ch;
	}

	public TopLevelAttributeBuilder getAttributeBuilder() {
		return ((IAttributeBuilderSupport) this.getParentHandler()).getAttributeBuilder();
	}

	public CSpecBuilder getCSpecBuilder() {
		return ((ICSpecBuilderSupport) this.getParentHandler()).getCSpecBuilder();
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException {
		ActionHandler parent = (ActionHandler) this.getParentHandler();
		parent.setProductAlias(getOptionalStringValue(attrs, Prerequisite.ATTR_ALIAS));
		parent.setProductFileCount(getOptionalIntValue(attrs, Action.ATTR_PRODUCT_FILE_COUNT, -1));
		String tmp = getOptionalStringValue(attrs, Artifact.ATTR_BASE);
		if (tmp != null)
			parent.setProductBase(Path.fromPortableString(tmp));
		tmp = getOptionalStringValue(attrs, Action.ATTR_UP_TO_DATE_POLICY);
		if (tmp != null) {
			try {
				parent.setUpToDatePolicy(UpToDatePolicy.valueOf(tmp));
			} catch (IllegalArgumentException e) {
				throw new SAXParseException(NLS.bind(Messages._0_is_not_a_valid_UpToDatePolicy, tmp), getDocumentLocator());
			}
		}
	}

	final void addProductArtifact(AttributeBuilder artifact) throws SAXException {
		try {
			this.getCSpecBuilder().addAttribute(artifact);
		} catch (AttributeAlreadyDefinedException e) {
			throw new SAXParseException(e.getMessage(), this.getDocumentLocator());
		}
	}

	final String getActionName() {
		return ((CSpecElementHandler) this.getParentHandler()).getBuilder().getName();
	}
}
