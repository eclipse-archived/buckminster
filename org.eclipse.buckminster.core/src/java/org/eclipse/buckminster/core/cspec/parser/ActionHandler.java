/*****************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.parser;

import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.common.parser.PropertyManagerHandler;
import org.eclipse.buckminster.core.cspec.builder.ActionBuilder;
import org.eclipse.buckminster.core.cspec.builder.TopLevelAttributeBuilder;
import org.eclipse.buckminster.core.cspec.model.Action;
import org.eclipse.buckminster.core.cspec.model.UpToDatePolicy;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.core.runtime.IPath;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 */
public class ActionHandler extends TopLevelAttributeHandler {
	private final PropertyManagerHandler actorPropsHandler;

	private final PrerequisitesHandler prerequisitesHandler;

	private final ProductsHandler productsHandler;

	private final PropertyManagerHandler propsHandler;

	public ActionHandler(AbstractHandler parent, boolean publ) {
		super(parent, publ);
		actorPropsHandler = new PropertyManagerHandler(this, Action.ELEM_ACTOR_PROPERTIES) {
			@Override
			public ExpandingProperties<String> getProperties() {
				return getActionBuilder().getActorProperties();
			}
		};
		prerequisitesHandler = new PrerequisitesHandler(this);
		productsHandler = new ProductsHandler(this);
		propsHandler = new PropertyManagerHandler(this, Action.ELEM_PROPERTIES) {
			@Override
			public ExpandingProperties<String> getProperties() {
				return getActionBuilder().getProperties();
			}
		};
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs) throws SAXException {
		ChildHandler ch;
		if (actorPropsHandler.getTAG().equals(localName))
			ch = actorPropsHandler;
		else if (propsHandler.getTAG().equals(localName))
			ch = propsHandler;
		else if (PrerequisitesHandler.TAG.equals(localName))
			ch = prerequisitesHandler;
		else if (Action.ELEM_PRODUCTS.equals(localName))
			ch = productsHandler;
		else
			ch = super.createHandler(uri, localName, attrs);
		return ch;
	}

	public ActionBuilder getActionBuilder() {
		return (ActionBuilder) getBuilder();
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException {
		super.handleAttributes(attrs);
		ActionBuilder builder = getActionBuilder();
		builder.setActorName(getOptionalStringValue(attrs, Action.ATTR_ACTOR));
		builder.setAlways(getOptionalBooleanValue(attrs, Action.ATTR_ALWAYS, Action.ALWAYS_DEFAULT));
		builder.setAssignConsoleSupport(getOptionalBooleanValue(attrs, Action.ATTR_ASSIGN_CONSOLE_SUPPORT, Action.ASSIGN_CONSOLE_SUPPORT_DEFAULT));
	}

	@Override
	protected TopLevelAttributeBuilder createAttributeBuilder() {
		return getCSpecBuilder().createActionBuilder();
	}

	final void addProductPath(IPath path) {
		getActionBuilder().addProductPath(path);
	}

	final void setProductAlias(String productAlias) {
		getActionBuilder().setProductAlias(productAlias);
	}

	final void setProductBase(IPath productBase) {
		getActionBuilder().setProductBase(productBase);
	}

	final void setProductFileCount(int producFileCount) {
		getActionBuilder().setProductFileCount(producFileCount);
	}

	final void setUpToDatePolicy(UpToDatePolicy policy) {
		getActionBuilder().setUpToDatePolicy(policy);
	}
}
