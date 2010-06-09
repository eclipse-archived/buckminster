/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.rmap.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.buckminster.core.KeyConstants;
import org.eclipse.buckminster.core.common.model.Documentation;
import org.eclipse.buckminster.core.common.model.Format;
import org.eclipse.buckminster.core.common.parser.DocumentationHandler;
import org.eclipse.buckminster.core.common.parser.FormatHandler;
import org.eclipse.buckminster.core.common.parser.PropertyManagerHandler;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.rmap.model.SearchPath;
import org.eclipse.buckminster.core.rmap.model.URIMatcher;
import org.eclipse.buckminster.core.rmap.model.VersionConverterDesc;
import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.buckminster.osgi.filter.FilterFactory;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.buckminster.sax.MissingRequiredAttributeException;
import org.osgi.framework.InvalidSyntaxException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @author Thomas Hallgren
 */
public class ProviderHandler extends PropertyManagerHandler {
	public final static String TAG = Provider.TAG;

	private DocumentationHandler documentationHandler;

	private FormatHandler uriHandler;

	private DigestHandler digestHandler;

	private VersionConverterHandler versionConverterHandler;

	private URIMatcherHandler uriMetaDataHandler;

	private URIMatcher uriMatcher;

	private Documentation documentation;

	private String readerType;

	private String[] componentTypes;

	private Format uriFormat;

	private Format digestFormat;

	private String digestAlgorithm;

	private VersionConverterDesc versionConverter;

	private Filter resolutionFilter;

	private final Map<String, String> properties = new HashMap<String, String>();

	public ProviderHandler(AbstractHandler parent) {
		super(parent, Provider.TAG);
	}

	@Override
	public void childPopped(ChildHandler child) throws SAXException {
		if (child == uriHandler)
			uriFormat = (Format) uriHandler.getValueHolder();
		else if (child == digestHandler) {
			digestFormat = (Format) digestHandler.getValueHolder();
			digestAlgorithm = digestHandler.getAlgorithm();
		} else if (child == versionConverterHandler)
			versionConverter = versionConverterHandler.getVersionConverter();
		else if (child == documentationHandler)
			documentation = documentationHandler.createDocumentation();
		else if (child == uriMetaDataHandler) {
			try {
				uriMatcher = uriMetaDataHandler.createURIMetaData();
			} catch (Exception e) {
				throw new SAXParseException(e.getMessage(), getDocumentLocator(), e);
			}
		} else
			super.childPopped(child);
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs) throws SAXException {
		ChildHandler ch;
		if (Provider.TAG_URI.equals(localName)) {
			if (uriHandler == null)
				uriHandler = new FormatHandler(this);
			ch = uriHandler;
		} else if (Provider.TAG_DIGEST.equals(localName)) {
			if (digestHandler == null)
				digestHandler = new DigestHandler(this);
			ch = digestHandler;
		} else if (DocumentationHandler.TAG.equals(localName)) {
			if (documentationHandler == null)
				documentationHandler = new DocumentationHandler(this);
			ch = documentationHandler;
		} else if (VersionConverterDesc.TAG.equals(localName)) {
			if (versionConverterHandler == null)
				versionConverterHandler = new VersionConverterHandler(this);
			ch = versionConverterHandler;
		} else if (URIMatcher.TAG.equals(localName)) {
			if (uriMetaDataHandler == null)
				uriMetaDataHandler = new URIMatcherHandler(this);
			ch = uriMetaDataHandler;
		} else
			ch = super.createHandler(uri, localName, attrs);
		return ch;
	}

	@Override
	public Map<String, String> getProperties() {
		return properties;
	}

	public Provider getProvider() {
		return new Provider(getSearchPath(), readerType, componentTypes, versionConverter, uriFormat, digestFormat, digestAlgorithm,
				resolutionFilter, properties, uriMatcher, documentation);
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException {
		readerType = getStringValue(attrs, Provider.ATTR_READER_TYPE);
		if (readerType.equals(IReaderType.ECLIPSE_IMPORT)) {
			logAttributeValueDeprecation(getTAG(), Provider.ATTR_READER_TYPE, IReaderType.ECLIPSE_IMPORT, IReaderType.P2);
			readerType = IReaderType.P2;
		}

		componentTypes = null;
		String tmp = getOptionalStringValue(attrs, Provider.ATTR_COMPONENT_TYPES);
		if (tmp == null) {
			// legacy. Version 0.1.0 had "managedCategories". The category
			// concept is
			// merged into the componentType in 0.2.0.
			//
			// Limit component types using managed categories
			//
			tmp = getOptionalStringValue(attrs, "componentType"); //$NON-NLS-1$
			if (tmp == null)
				throw new MissingRequiredAttributeException(getTAG(), Provider.ATTR_COMPONENT_TYPES, getDocumentLocator());
			logAttributeDeprecation(getTAG(), "componentType", Provider.ATTR_COMPONENT_TYPES); //$NON-NLS-1$

			boolean canManageBundle = true;
			boolean canManageFeature = true;
			String[] managedCategories = TextUtils.split(getOptionalStringValue(attrs, "managedCategories"), ","); //$NON-NLS-1$ //$NON-NLS-2$
			if (managedCategories.length > 0) {
				logAttributeDeprecation(getTAG(), "managedCategories", Provider.ATTR_COMPONENT_TYPES); //$NON-NLS-1$

				canManageBundle = false;
				canManageFeature = false;
				for (String category : managedCategories) {
					if ("plugin".equals(category)) //$NON-NLS-1$
						canManageBundle = true;
					else if ("feature".equals(category)) //$NON-NLS-1$
						canManageFeature = true;
				}
			}

			if (tmp.equals(IComponentType.ECLIPSE_PROJECT)) {
				ArrayList<String> expanded = new ArrayList<String>(3);
				if (canManageBundle)
					expanded.add(IComponentType.OSGI_BUNDLE);
				if (canManageFeature)
					expanded.add(IComponentType.ECLIPSE_FEATURE);
				expanded.add(IComponentType.BUCKMINSTER);
				componentTypes = expanded.toArray(new String[expanded.size()]);
			} else if (tmp.equals(IComponentType.ECLIPSE_INSTALLED)) {
				ArrayList<String> expanded = new ArrayList<String>(3);
				if (canManageBundle)
					expanded.add(IComponentType.OSGI_BUNDLE);
				if (canManageFeature)
					expanded.add(IComponentType.ECLIPSE_FEATURE);
				componentTypes = expanded.toArray(new String[expanded.size()]);
			}
		} else if (IComponentType.ECLIPSE_PROJECT.equals(tmp))
			tmp = IComponentType.OSGI_BUNDLE + ',' + IComponentType.ECLIPSE_FEATURE + ',' + IComponentType.BUCKMINSTER;

		if (componentTypes == null)
			componentTypes = TextUtils.split(tmp, ","); //$NON-NLS-1$

		if (!getOptionalBooleanValue(attrs, "mutable", true)) //$NON-NLS-1$
			properties.put(KeyConstants.IS_MUTABLE, "false"); //$NON-NLS-1$
		if (!getOptionalBooleanValue(attrs, "source", true)) //$NON-NLS-1$
			properties.put(KeyConstants.IS_SOURCE, "false"); //$NON-NLS-1$

		tmp = getOptionalStringValue(attrs, "space"); //$NON-NLS-1$
		if (tmp != null)
			tmp = "(buckminster.spacepath=" + tmp + ')'; //$NON-NLS-1$

		String resFilter = getOptionalStringValue(attrs, Provider.ATTR_RESOLUTION_FILTER);
		if (resFilter == null)
			resFilter = tmp;
		else if (tmp != null)
			resFilter = "(&" + resFilter + tmp + ')'; //$NON-NLS-1$

		if (resFilter != null) {
			try {
				resolutionFilter = FilterFactory.newInstance(resFilter);
			} catch (InvalidSyntaxException e) {
				throw new SAXParseException(e.getMessage(), getDocumentLocator(), e);
			}
		} else
			resolutionFilter = null;

		uriFormat = null;
		digestFormat = null;
		digestAlgorithm = null;
		versionConverter = null;
		uriMatcher = null;
		documentation = null;
	}

	protected final String[] getComponentTypes() {
		return componentTypes;
	}

	protected final String getDigestAlgorithm() {
		return digestAlgorithm;
	}

	protected final Format getDigestFormat() {
		return digestFormat;
	}

	protected final Documentation getDocumentation() {
		return documentation;
	}

	protected final String getReaderType() {
		return readerType;
	}

	protected final Filter getResolutionFilter() {
		return resolutionFilter;
	}

	protected SearchPath getSearchPath() {
		SearchPath searchPath = null;
		AbstractHandler parent = getParentHandler();
		if (parent instanceof SearchPathHandler)
			searchPath = ((SearchPathHandler) parent).getSearchPath();
		return searchPath;
	}

	protected final Format getUriFormat() {
		return uriFormat;
	}

	protected final VersionConverterDesc getVersionConverter() {
		return versionConverter;
	}
}
