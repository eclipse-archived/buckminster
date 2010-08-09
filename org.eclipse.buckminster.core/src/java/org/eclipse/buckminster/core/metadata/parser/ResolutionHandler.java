/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.metadata.parser;

import java.util.ArrayList;
import java.util.UUID;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.XMLConstants;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.parser.ComponentRequestHandler;
import org.eclipse.buckminster.core.metadata.StorageManager;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.parser.ExtensionAwareHandler;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.model.common.ComponentRequest;
import org.eclipse.buckminster.rmap.Provider;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.buckminster.sax.ChildPoppedListener;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.osgi.util.NLS;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @author Thomas Hallgren
 */
public class ResolutionHandler extends ExtensionAwareHandler implements ChildPoppedListener {
	public static final String TAG = Resolution.TAG;

	private final ComponentRequestHandler componentRequestHandler = new ComponentRequestHandler(this);

	private final VersionMatchHandler versionMatchHandler = new VersionMatchHandler(this);

	private final ArrayList<String> attributes = new ArrayList<String>();

	private UUID cspecId;

	private URI providerId;

	private ComponentRequest request;

	private String componentType;

	private VersionMatch versionMatch;

	private boolean materializable;

	private String persistentId;

	private String repository;

	private String remoteName;

	private String contentType;

	private long lastModified;

	private long size;

	private boolean unpack;

	public ResolutionHandler(AbstractHandler parent) {
		super(parent);
	}

	@Override
	public void childPopped(ChildHandler child) throws SAXException {
		if (child == componentRequestHandler)
			request = EcoreUtil.copy(componentRequestHandler.getBuilder());
		else if (child == versionMatchHandler)
			versionMatch = versionMatchHandler.getVersionMatch();
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs) throws SAXException {
		ChildHandler ch;
		if (Resolution.ELEM_REQUEST.equals(localName))
			ch = componentRequestHandler;
		else if (VersionMatch.TAG.equals(localName))
			ch = versionMatchHandler;
		else
			ch = super.createHandler(uri, localName, attrs);
		return ch;
	}

	public Resolution getResolution() throws SAXException {
		if (request == null)
			throw new SAXParseException(
					NLS.bind(Messages.Missing_required_element_0, XMLConstants.BM_METADATA_PREFIX + '.' + Resolution.ELEM_REQUEST),
					this.getDocumentLocator());

		if (versionMatch == null)
			throw new SAXParseException(NLS.bind(Messages.Missing_required_element_0, XMLConstants.BM_METADATA_PREFIX + '.' + VersionMatch.TAG),
					this.getDocumentLocator());

		ResourceSet resourceSet = CorePlugin.getDefault().getResourceSet();
		Resource rmap = resourceSet.getResource(providerId, true);
		Provider provider = (Provider) rmap.getEObject(providerId.fragment());

		AbstractHandler parent = getParentHandler();
		CSpec cspec;
		if (parent instanceof IDWrapperHandler) {
			IDWrapperHandler wh = (IDWrapperHandler) parent;
			cspec = (CSpec) wh.getWrapped(cspecId);
		} else {
			try {
				StorageManager sm = StorageManager.getDefault();
				cspec = sm.getCSpecs().getElement(cspecId);
			} catch (CoreException e) {
				throw new SAXParseException(e.getMessage(), getDocumentLocator(), e);
			}
		}

		return new Resolution(cspec, componentType, versionMatch, provider, materializable, request, attributes, persistentId, repository,
				remoteName, contentType, lastModified, size, unpack);
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException {
		versionMatch = null;
		cspecId = UUID.fromString(this.getStringValue(attrs, Resolution.ATTR_CSPEC_ID));
		materializable = getBooleanValue(attrs, Resolution.ATTR_MATERIALIZABLE);
		providerId = URI.createURI(getStringValue(attrs, Resolution.ATTR_PROVIDER_ID));
		componentType = getStringValue(attrs, Resolution.ATTR_COMPONENT_TYPE);
		persistentId = getOptionalStringValue(attrs, Resolution.ATTR_PERSISTENT_ID);
		repository = getStringValue(attrs, Resolution.ATTR_REPOSITORY);
		remoteName = getOptionalStringValue(attrs, Resolution.ATTR_REMOTE_NAME);
		contentType = getOptionalStringValue(attrs, Resolution.ATTR_CONTENT_TYPE);
		size = getOptionalLongValue(attrs, Resolution.ATTR_SIZE, -1);
		lastModified = getOptionalLongValue(attrs, Resolution.ATTR_LAST_MODIFIED, -1);
		unpack = getOptionalBooleanValue(attrs, Resolution.ATTR_UNPACK, false);
		request = null;

		attributes.clear();
		String attrStr = getOptionalStringValue(attrs, Resolution.ATTR_ATTRIBUTES);
		if (attrStr != null) {
			for (String attr : attrStr.split(",")) //$NON-NLS-1$
			{
				if (!attributes.contains(attr))
					attributes.add(attr);
			}
		}
	}
}
