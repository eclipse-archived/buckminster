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

import org.eclipse.buckminster.core.cspec.PathGroup;
import org.eclipse.buckminster.core.cspec.SaxablePath;
import org.eclipse.buckminster.core.cspec.builder.ArtifactBuilder;
import org.eclipse.buckminster.core.cspec.builder.AttributeBuilder;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.internal.actor.PerformManager;
import org.eclipse.buckminster.core.metadata.model.IModelCache;
import org.eclipse.buckminster.core.metadata.model.UUIDKeyed;
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
public class Artifact extends TopLevelAttribute
{
	public static final String ATTR_PATH = "path";

	public static final String ATTR_TYPE = "type";

	public static final String ATTR_BASE = "base";

	private final IPath m_base;

	private final String m_type;

	private final Set<IPath> m_paths;

	public Artifact(ArtifactBuilder builder)
	{
		super(builder);
		m_type = builder.getType();
		IPath base = builder.getBase();
		if(base != null)
		{
			if(!base.hasTrailingSeparator())
				base = base.addTrailingSeparator();
		}
		m_base = base;
		m_paths = UUIDKeyed.createUnmodifiablePaths(builder.getPaths());
	}

	public IPath getBase()
	{
		return m_base;
	}

	public Set<IPath> getPaths()
	{
		return m_paths;
	}

	public final String getType()
	{
		return m_type;
	}

	@Override
	protected void addAttributes(AttributesImpl attrs)
	{
		super.addAttributes(attrs);
		if(m_paths.size() == 1)
			Utils.addAttribute(attrs, ATTR_PATH, m_paths.iterator().next().toPortableString());
		if(m_type != null)
			Utils.addAttribute(attrs, ATTR_TYPE, m_type);
		if(m_base != null)
			Utils.addAttribute(attrs, ATTR_BASE, m_base.toPortableString());
	}

	@Override
	protected AttributeBuilder createAttributeBuilder(CSpecBuilder cspecBuilder)
	{
		return cspecBuilder.createArtifactBuilder();
	}

	@Override
	protected void emitElements(ContentHandler handler, String namespace, String prefix) throws SAXException
	{
		super.emitElements(handler, namespace, prefix);
		if(m_paths.size() > 1)
			for(IPath path : m_paths)
			{
				SaxablePath sPath = (SaxablePath)path;
				sPath.toSax(handler, namespace, prefix, sPath.getDefaultTag());
			}
	}

	protected IPath getExpandedBase(Map<String, String> local) throws CoreException
	{
		if(m_base == null)
			return getCSpec().getComponentLocation();

		IPath base = PerformManager.expandPath(local, m_base);
		return base.isAbsolute()
			? base
			: getCSpec().getComponentLocation().append(base);
	}

	@Override
	protected PathGroup[] internalGetPathGroups(IModelCache ctx, Map<String, String> local, Stack<IAttributeFilter> filters)
			throws CoreException
	{
		int idx = m_paths.size();
		IPath[] pathArr;
		if(idx > 0)
		{
			pathArr = m_paths.toArray(new IPath[idx]);
			while(--idx >= 0)
				pathArr[idx] = PerformManager.expandPath(local, pathArr[idx]);
		}
		else
			pathArr = Trivial.EMPTY_PATH_ARRAY;
		return new PathGroup[] { new PathGroup(getExpandedBase(local), pathArr) };
	}
}
