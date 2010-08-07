/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.metadata.parser;

import java.util.UUID;

import org.eclipse.buckminster.core.cspec.parser.CSpecHandler;
import org.eclipse.buckminster.core.metadata.model.IDWrapper;
import org.eclipse.buckminster.core.parser.ExtensionAwareHandler;
import org.eclipse.buckminster.core.query.parser.ComponentQueryHandler;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.buckminster.sax.ChildPoppedListener;
import org.eclipse.buckminster.sax.UUIDKeyed;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 */
public class IDWrapperHandler extends ExtensionAwareHandler implements ChildPoppedListener {
	public static final String TAG = IDWrapper.TAG;

	private final CSpecHandler cspecHandler = new CSpecHandler(this);

	private final ResolutionHandler resolutionHandler = new ResolutionHandler(this);

	private final ResolvedNodeHandler resolvedNodeHandler = new ResolvedNodeHandler(this);

	private final UnresolvedNodeHandler unresolvedNodeHandler = new UnresolvedNodeHandler(this);

	private final GeneratorNodeHandler generatorNodeHandler = new GeneratorNodeHandler(this);

	private final ComponentQueryHandler componentQueryHandler = new ComponentQueryHandler(this, null);

	private BillOfMaterialsHandler billOfMaterialsHandler;

	private UUID id;

	private IDWrapper wrapper;

	public IDWrapperHandler(AbstractHandler parent) {
		super(parent);
	}

	@Override
	public void childPopped(ChildHandler child) throws SAXException {
		if (child instanceof BomNodeHandler)
			wrapper = new IDWrapper(id, ((BomNodeHandler) child).getDepNode());
		else if (child == cspecHandler)
			wrapper = new IDWrapper(id, cspecHandler.getCSpec());
		else if (child == resolutionHandler)
			wrapper = new IDWrapper(id, resolutionHandler.getResolution());
		else if (child == componentQueryHandler)
			wrapper = new IDWrapper(id, componentQueryHandler.getComponentQuery());
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs) throws SAXException {
		ChildHandler ch;
		if (CSpecHandler.TAG.equals(localName))
			ch = cspecHandler;
		else if (ResolutionHandler.TAG.equals(localName))
			ch = resolutionHandler;
		else if (ResolvedNodeHandler.TAG.equals(localName))
			ch = resolvedNodeHandler;
		else if (BillOfMaterialsHandler.TAG.equals(localName)) {
			if (billOfMaterialsHandler == null)
				billOfMaterialsHandler = new BillOfMaterialsHandler(this);
			ch = billOfMaterialsHandler;
		} else if (UnresolvedNodeHandler.TAG.equals(localName))
			ch = unresolvedNodeHandler;
		else if (GeneratorNodeHandler.TAG.equals(localName))
			ch = generatorNodeHandler;
		else if (componentQueryHandler.getTAG().equals(localName))
			ch = componentQueryHandler;
		else
			ch = super.createHandler(uri, localName, attrs);
		return ch;
	}

	public IDWrapper getWrapper() {
		return wrapper;
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException {
		id = UUID.fromString(this.getStringValue(attrs, IDWrapper.ATTR_ID));
		wrapper = null;
	}

	UUIDKeyed getWrapped(UUID wid) throws SAXException {
		return ((IWrapperParent) getParentHandler()).getWrapped(wid);
	}
}
