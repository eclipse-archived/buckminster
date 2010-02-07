/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.model;

import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.eclipse.buckminster.core.cspec.IArtifact;
import org.eclipse.buckminster.core.cspec.IAttributeFilter;
import org.eclipse.buckminster.core.cspec.PathGroup;
import org.eclipse.buckminster.core.cspec.SaxablePath;
import org.eclipse.buckminster.core.cspec.builder.ArtifactBuilder;
import org.eclipse.buckminster.core.cspec.builder.AttributeBuilder;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.internal.actor.PerformManager;
import org.eclipse.buckminster.core.metadata.model.IModelCache;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 */
public class Artifact extends TopLevelAttribute implements IArtifact {
	public static final String ATTR_PATH = "path"; //$NON-NLS-1$

	public static final String ATTR_TYPE = "type"; //$NON-NLS-1$

	public static final String ATTR_BASE = "base"; //$NON-NLS-1$

	private final IPath base;

	private final Set<IPath> paths;

	public Artifact(ArtifactBuilder builder) {
		super(builder);
		IPath b = builder.getBase();
		if (b != null) {
			if (!b.hasTrailingSeparator())
				b = b.addTrailingSeparator();
		}
		base = b;
		paths = CSpec.createUnmodifiablePaths(builder.getPaths());
	}

	public IPath getBase() {
		return base;
	}

	public Set<IPath> getPaths() {
		return paths;
	}

	@Override
	protected void addAttributes(AttributesImpl attrs) {
		super.addAttributes(attrs);
		if (paths.size() == 1)
			Utils.addAttribute(attrs, ATTR_PATH, paths.iterator().next().toPortableString());
		if (base != null)
			Utils.addAttribute(attrs, ATTR_BASE, base.toPortableString());
	}

	@Override
	protected AttributeBuilder createAttributeBuilder(CSpecBuilder cspecBuilder) {
		return cspecBuilder.createArtifactBuilder();
	}

	@Override
	protected void emitElements(ContentHandler handler, String namespace, String prefix) throws SAXException {
		super.emitElements(handler, namespace, prefix);
		if (paths.size() > 1)
			for (IPath path : paths) {
				SaxablePath sPath = (SaxablePath) path;
				sPath.toSax(handler, namespace, prefix, sPath.getDefaultTag());
			}
	}

	protected IPath getExpandedBase(Map<String, ? extends Object> local) throws CoreException {
		if (base == null)
			return getCSpec().getComponentLocation();

		IPath expBase = PerformManager.expandPath(local, base);
		return expBase.isAbsolute() ? expBase : getCSpec().getComponentLocation().append(expBase);
	}

	@Override
	protected PathGroup[] internalGetPathGroups(IModelCache ctx, Map<String, ? extends Object> local, Stack<IAttributeFilter> filters)
			throws CoreException {
		int idx = paths.size();
		IPath[] pathArr;
		if (idx > 0) {
			pathArr = paths.toArray(new IPath[idx]);
			while (--idx >= 0)
				pathArr[idx] = PerformManager.expandPath(local, pathArr[idx]);
		} else
			pathArr = Trivial.EMPTY_PATH_ARRAY;
		return new PathGroup[] { new PathGroup(getExpandedBase(local), pathArr) };
	}
}
