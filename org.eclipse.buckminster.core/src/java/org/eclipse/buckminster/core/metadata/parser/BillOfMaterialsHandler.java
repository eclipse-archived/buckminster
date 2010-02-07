/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.metadata.parser;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.helpers.DateAndTimeUtils;
import org.eclipse.buckminster.core.metadata.model.BOMNode;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.metadata.model.IDWrapper;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.buckminster.sax.ChildPoppedListener;
import org.eclipse.buckminster.sax.UUIDKeyed;
import org.eclipse.osgi.util.NLS;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @author Thomas Hallgren
 */
public class BillOfMaterialsHandler extends BomNodeHandler implements ChildPoppedListener {
	public static final String TAG = BillOfMaterials.TAG;

	private final Map<UUID, IDWrapper> wrapperMap = new HashMap<UUID, IDWrapper>();

	private final IDWrapperHandler idWrapperHandler = new IDWrapperHandler(this);

	private UUID topNodeId;

	private UUID queryId;

	private Date timestamp;

	public BillOfMaterialsHandler(AbstractHandler parent) {
		super(parent);
	}

	public void childPopped(ChildHandler child) throws SAXException {
		if (child == idWrapperHandler) {
			IDWrapper wrapper = idWrapperHandler.getWrapper();
			wrapperMap.put(wrapper.getId(), wrapper);
		}
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs) throws SAXException {
		ChildHandler ch;
		if (IDWrapperHandler.TAG.equals(localName))
			ch = idWrapperHandler;
		else
			ch = super.createHandler(uri, localName, attrs);
		return ch;
	}

	@Override
	public UUIDKeyed getWrapped(UUID id) throws SAXException {
		IDWrapper wrapper = wrapperMap.get(id);
		if (wrapper == null) {
			AbstractHandler parent = getParentHandler();
			while (parent != null) {
				if (parent instanceof BillOfMaterialsHandler)
					return ((BillOfMaterialsHandler) parent).getWrapped(id);
				if (parent instanceof ChildHandler)
					parent = ((ChildHandler) parent).getParentHandler();
			}
			throw new SAXParseException(NLS.bind(Messages.Id_0_appoints_non_existing_wrapper, id), getDocumentLocator());
		}
		return wrapper.getWrapped();
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException {
		super.handleAttributes(attrs);
		wrapperMap.clear();
		try {
			String tmp = getOptionalStringValue(attrs, BillOfMaterials.ATTR_TOP_NODE_ID);
			topNodeId = tmp == null ? null : UUID.fromString(tmp);
			queryId = UUID.fromString(this.getStringValue(attrs, BillOfMaterials.ATTR_QUERY_ID));
			timestamp = DateAndTimeUtils.fromISOFormat(this.getStringValue(attrs, BillOfMaterials.ATTR_TIMESTAMP));
		} catch (IllegalArgumentException e) {
			throw new SAXParseException(e.getMessage(), this.getDocumentLocator());
		} catch (ParseException e) {
			throw new SAXParseException(e.getMessage(), this.getDocumentLocator());
		}
	}

	@Override
	BOMNode getDepNode() throws SAXException {
		return new BillOfMaterials((BOMNode) getWrapped(topNodeId), getQuery(queryId), timestamp);
	}

	ComponentQuery getQuery(UUID qid) throws SAXException {
		try {
			return (ComponentQuery) getWrapped(qid);
		} catch (ClassCastException e) {
			throw new SAXParseException(NLS.bind(Messages.Wrapper_0_does_not_wrap_query, qid), getDocumentLocator());
		}
	}
}
