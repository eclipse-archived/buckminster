/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.model;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.eclipse.buckminster.core.common.model.Documentation;
import org.eclipse.buckminster.core.cspec.IAttribute;
import org.eclipse.buckminster.core.cspec.IAttributeFilter;
import org.eclipse.buckminster.core.cspec.PathGroup;
import org.eclipse.buckminster.core.cspec.builder.AttributeBuilder;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.metadata.model.IModelCache;
import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 */
public class Attribute extends NamedElement implements Cloneable, IAttribute {
	public static final String TAG = "define"; //$NON-NLS-1$

	public static final String ATTR_FILTER = "filter"; //$NON-NLS-1$

	private final Documentation documentation;

	private final Filter filter;

	private CSpec cspec;

	public Attribute(AttributeBuilder builder) {
		super(builder.getName());
		documentation = builder.getDocumentation();
		filter = builder.getFilter();
	}

	Attribute(String name) {
		super(name);
		documentation = null;
		filter = null;
	}

	public void addDynamicProperties(Map<String, String> properties) throws CoreException {
	}

	/**
	 * Create a copy of this Attribute with the owner set to <code>null</code>.
	 * 
	 * @return A copy that has no cspec owner assigned.
	 */
	public IAttribute copy() {
		Attribute copy;
		try {
			copy = (Attribute) clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
		copy.cspec = null;
		return copy;
	}

	@Override
	public AttributeBuilder getAttributeBuilder(CSpecBuilder cspecBuilder) {
		AttributeBuilder bld = createAttributeBuilder(cspecBuilder);
		bld.initFrom(this);
		return bld;
	}

	public final CSpec getCSpec() {
		assert cspec != null;
		return cspec;
	}

	@Override
	public String getDefaultTag() {
		return TAG;
	}

	@Override
	public Documentation getDocumentation() {
		return documentation;
	}

	@Override
	public Filter getFilter() {
		return filter;
	}

	public PathGroup[] getPathGroups(IModelCache ctx, Stack<IAttributeFilter> filters) throws CoreException {
		return PathGroup.EMPTY_ARRAY;
	}

	@Override
	public List<Prerequisite> getPrerequisites() {
		return getPrerequisites(null);
	}

	public List<Prerequisite> getPrerequisites(Stack<IAttributeFilter> filters) {
		// Only targets have artifact group prerequisites
		//
		return Collections.emptyList();
	}

	@Override
	public String getQualifiedName() {
		return getCSpec().getComponentIdentifier().toString() + '#' + getName();
	}

	public boolean isEnabled(IModelCache ctx) throws CoreException {
		return filter == null || filter.matchCase(ctx.getProperties());
	}

	public boolean isProducedByActions(IModelCache cache) throws CoreException {
		return false;
	}

	@Override
	public boolean isPublic() {
		return true;
	}

	@Override
	public final String toString() {
		StringBuilder bld = new StringBuilder();
		toString(bld);
		return bld.toString();
	}

	public void toString(StringBuilder bld) {
		getCSpec().getComponentIdentifier().toString(bld);
		bld.append('#');
		bld.append(getName());
	}

	@Override
	protected void addAttributes(AttributesImpl attrs) {
		super.addAttributes(attrs);
		if (filter != null)
			Utils.addAttribute(attrs, ATTR_FILTER, filter.toString());
	}

	protected AttributeBuilder createAttributeBuilder(CSpecBuilder cspecBuilder) {
		return cspecBuilder.createAttributeBuilder();
	}

	@Override
	protected void emitElements(ContentHandler handler, String namespace, String prefix) throws SAXException {
		if (documentation != null)
			documentation.toSax(handler, namespace, prefix, documentation.getDefaultTag());
	}

	/**
	 * It would be wonderful if we could have everything final. Double
	 * references does however create a hen and egg problem. This is the hen
	 * telling the egg that it is its mother.
	 * 
	 * @param cspec
	 *            The owner cspec
	 */
	void setCSPec(CSpec cspec) {
		this.cspec = cspec;
	}
}
