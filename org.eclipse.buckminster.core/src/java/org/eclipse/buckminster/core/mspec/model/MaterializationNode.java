/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.mspec.model;

import java.util.regex.Pattern;

import org.eclipse.buckminster.core.mspec.IMaterializationNode;
import org.eclipse.buckminster.core.mspec.builder.MaterializationNodeBuilder;
import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.IPath;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 */
public class MaterializationNode extends MaterializationDirective implements IMaterializationNode {
	public static final String TAG = "mspecNode"; //$NON-NLS-1$

	public static final String ATTR_NAME_PATTERN = "namePattern"; //$NON-NLS-1$

	public static final String ATTR_FILTER = "filter"; //$NON-NLS-1$

	public static final String ATTR_LEAF_ARTIFACT = "leafArtifact"; //$NON-NLS-1$

	public static final String ATTR_COMPONENT_TYPE = "componentType"; //$NON-NLS-1$

	public static final String ATTR_EXCLUDE = "exclude"; //$NON-NLS-1$

	public static final String ATTR_RESOURCE_PATH = "resourcePath"; //$NON-NLS-1$

	public static final String ATTR_BINDING_NAME_PATTERN = "bindingNamePattern"; //$NON-NLS-1$

	public static final String ATTR_BINDING_NAME_REPLACEMENT = "bindingNameReplacement"; //$NON-NLS-1$

	public static final String ELEM_UNPACK = "unpack"; //$NON-NLS-1$

	public static final String ATTR_SUFFIX = "suffix"; //$NON-NLS-1$

	public static final String ATTR_EXPAND = "expand"; //$NON-NLS-1$

	private final Pattern namePattern;

	private final Filter filter;

	private final IPath leafArtifact;

	private final String componentTypeID;

	private final boolean exclude;

	private final IPath resourcePath;

	private final Pattern bindingNamePattern;

	private final String bindingNameReplacement;

	private final String suffix;

	private final boolean unpack;

	private final boolean expand;

	public MaterializationNode(MaterializationNodeBuilder builder) {
		super(builder);
		namePattern = builder.getNamePattern();
		filter = builder.getFilter();
		leafArtifact = builder.getLeafArtifact();
		componentTypeID = builder.getComponentTypeID();
		exclude = builder.isExclude();
		resourcePath = builder.getResourcePath();
		bindingNamePattern = builder.getBindingNamePattern();
		bindingNameReplacement = builder.getBindingNameReplacement();
		suffix = builder.getSuffix();
		unpack = builder.isUnpack();
		expand = builder.isExpand();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Object getAdapter(Class adapter) {
		if (adapter.isAssignableFrom(MaterializationNodeBuilder.class)) {
			MaterializationNodeBuilder bld = new MaterializationNodeBuilder();
			bld.initFrom(this);
			return bld;
		}
		return super.getAdapter(adapter);
	}

	@Override
	public Pattern getBindingNamePattern() {
		return bindingNamePattern;
	}

	@Override
	public String getBindingNameReplacement() {
		return bindingNameReplacement;
	}

	@Override
	public String getComponentTypeID() {
		return componentTypeID;
	}

	@Override
	public String getDefaultTag() {
		return TAG;
	}

	@Override
	public Filter getFilter() {
		return filter;
	}

	@Override
	public IPath getLeafArtifact() {
		return leafArtifact;
	}

	@Override
	public Pattern getNamePattern() {
		return namePattern;
	}

	@Override
	public IPath getResourcePath() {
		return resourcePath;
	}

	@Override
	public String getSuffix() {
		return suffix;
	}

	@Override
	public boolean isExclude() {
		return exclude;
	}

	@Override
	public boolean isExpand() {
		return expand;
	}

	@Override
	public boolean isUnpack() {
		return unpack;
	}

	@Override
	protected void addAttributes(AttributesImpl attrs) throws SAXException {
		super.addAttributes(attrs);
		if (namePattern != null)
			Utils.addAttribute(attrs, ATTR_NAME_PATTERN, namePattern.toString());
		if (filter != null)
			Utils.addAttribute(attrs, ATTR_FILTER, filter.toString());
		if (componentTypeID != null)
			Utils.addAttribute(attrs, ATTR_COMPONENT_TYPE, componentTypeID);
		if (leafArtifact != null)
			Utils.addAttribute(attrs, ATTR_LEAF_ARTIFACT, leafArtifact.toPortableString());
		if (resourcePath != null)
			Utils.addAttribute(attrs, ATTR_RESOURCE_PATH, resourcePath.toPortableString());
		if (exclude)
			Utils.addAttribute(attrs, ATTR_EXCLUDE, "true"); //$NON-NLS-1$
		if (bindingNamePattern != null)
			Utils.addAttribute(attrs, ATTR_BINDING_NAME_PATTERN, bindingNamePattern.toString());
		if (bindingNameReplacement != null)
			Utils.addAttribute(attrs, ATTR_BINDING_NAME_REPLACEMENT, bindingNameReplacement);
	}

	@Override
	protected void emitElements(ContentHandler receiver, String namespace, String prefix) throws SAXException {
		super.emitElements(receiver, namespace, prefix);
		if (unpack) {
			AttributesImpl attrs = new AttributesImpl();
			if (!expand)
				Utils.addAttribute(attrs, ATTR_EXPAND, "false"); //$NON-NLS-1$
			if (suffix != null)
				Utils.addAttribute(attrs, ATTR_SUFFIX, suffix);
			String qName = Utils.makeQualifiedName(prefix, ELEM_UNPACK);
			receiver.startElement(namespace, ELEM_UNPACK, qName, attrs);
			receiver.endElement(namespace, ELEM_UNPACK, qName);
		}
	}
}
