/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.model;

import java.util.Collections;
import java.util.Map;
import java.util.Stack;

import org.eclipse.buckminster.core.KeyConstants;
import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.common.model.SAXEmitter;
import org.eclipse.buckminster.core.cspec.PathGroup;
import org.eclipse.buckminster.core.cspec.builder.AttributeBuilder;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.builder.TopLevelAttributeBuilder;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.helpers.FilterUtils;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.core.metadata.model.IModelCache;
import org.eclipse.buckminster.core.metadata.model.UUIDKeyed;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.sax.ISaxableElement;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.osgi.framework.Filter;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/**
 * The super class for actions, artifacts, and groups
 *
 * @author Thomas Hallgren
 */
public abstract class TopLevelAttribute extends Attribute implements Cloneable
{
	public final static String PROPERTY_PREFIX = "buckminster.";
	public final static String INSTALLER_HINT_PREFIX = PROPERTY_PREFIX + "install.";

	public static final String ELEM_INSTALLER_HINTS = "installerHints";

	public static final String PUBLIC_TAG = "public";

	public static final String PRIVATE_TAG = "private";

	public static final String DEFINITION_TAG = "definitions";

	public static final String DEFINE_TAG = "define";

	private final Map<String, String> m_installerHints;

	private final boolean m_public;

	TopLevelAttribute(TopLevelAttributeBuilder builder)
	{
		super(builder);
		m_public = builder.isPublic();
		m_installerHints = UUIDKeyed.createUnmodifiableProperties(builder.getInstallerHints());
	}

	TopLevelAttribute(String name)
	{
		super(name);
		m_public = false;
		m_installerHints = Collections.emptyMap();
	}

	@Override
	public void addDynamicProperties(Map<String, String> properties)
	throws CoreException
	{
		String actionOutput;
		CSpec cspec = getCSpec();

		// Create a unique folder name to use as sub-folder in the temporary area
		// and under the output root
		//
		StringBuilder bld = new StringBuilder();

		bld.append(cspec.getName());
		IVersion version = cspec.getVersion();
		if(version != null)
		{
			bld.append('_');
			bld.append(version.replaceQualifier(null));
		}
		String ctype = cspec.getComponentTypeID();
		if(!IComponentType.UNKNOWN.equals(ctype))
		{
			bld.append('-');
			bld.append(cspec.getComponentTypeID());
		}
		String uniqueFolder = bld.toString();

		IPath buckminsterTempRoot = Path.fromOSString(
				System.getProperty("java.io.tmpdir")).append("buckminster").append(uniqueFolder);

		String outputRoot = properties.get(KeyConstants.ACTION_OUTPUT_ROOT);
		if(outputRoot != null)
		{
			// Output root must be qualified with component name to avoid
			// conflicts
			//
			actionOutput = Path.fromOSString(outputRoot).append(uniqueFolder).toPortableString();
		}
		else
			actionOutput = buckminsterTempRoot.append("build").toPortableString();

		properties.put(KeyConstants.ACTION_OUTPUT, actionOutput);
		properties.put(KeyConstants.ACTION_TEMP, buckminsterTempRoot.append("temp").toPortableString());
		properties.put(KeyConstants.ACTION_HOME, cspec.getComponentLocation().toOSString());
		properties.putAll(cspec.getComponentIdentifier().getProperties());
	}

	public void getDeepInstallerHints(IModelCache ctx, Map<String, String> hints, Stack<IAttributeFilter> filters) throws CoreException
	{
		Map<String, String> myHints = getInstallerHints();
		if(myHints.size() > 0)
		{
			StringBuilder bld = new StringBuilder(100);
			bld.append(INSTALLER_HINT_PREFIX);
			int pfLen = INSTALLER_HINT_PREFIX.length();
			for(Map.Entry<String, String> hint : myHints.entrySet())
			{
				// Check for '/' since it indicates a key that is augmented with
				// a platform specifier in the form os.ws.arch
				//
				String key = hint.getKey();
				int slashIdx = key.lastIndexOf('/');
				if(slashIdx > 0)
				{
					String[] triplet = TextUtils.split(key.substring(slashIdx + 1), ".");
					if(triplet.length == 3)
					{
						Filter filter = FilterUtils.createFilter(triplet[0], triplet[1], triplet[2], null);
						if(!FilterUtils.isMatch(filter, ctx.getProperties()))
							//
							// Not applicable for the current build
							//
							continue;
						key = key.substring(0, slashIdx);
					}
				}
				bld.setLength(pfLen);
				bld.append(hint.getKey());
				bld.append('.');
				bld.append(getName());
				hints.put(bld.toString(), hint.getValue());
			}
		}

		CSpec cspec = getCSpec();
		for(Prerequisite child : getPrerequisites(filters))
		{
			Attribute refAttr = child.getReferencedAttribute(cspec, ctx);
			if(!(refAttr instanceof TopLevelAttribute))
				continue;

			if(child.isPatternFilter())
			{
				if(filters == null)
					filters = new Stack<IAttributeFilter>();
				filters.push(child);
				((TopLevelAttribute)refAttr).getDeepInstallerHints(ctx, hints, filters);
				filters.pop();
			}
			else
				((TopLevelAttribute)refAttr).getDeepInstallerHints(ctx, hints, filters);
		}
	}

	@Override
	public String getDefaultTag()
	{
		return isPublic() ? PUBLIC_TAG : PRIVATE_TAG;
	}

	public long getFirstModified(IModelCache ctx, int expectedFileCount, int[] fileCount) throws CoreException
	{
		PathGroup[] pqs = getPathGroups(ctx, null);
		int idx = pqs.length;
		if(idx == 0)
			return 0L;

		if(idx > 1 && expectedFileCount > 0)
			//
			// We don't know how to distribute the count
			//
			expectedFileCount = -1;

		long oldest = Long.MAX_VALUE;
		while(--idx >= 0)
		{
			long pgModTime = pqs[idx].getFirstModified(expectedFileCount, fileCount);
			if(pgModTime < oldest)
			{
				oldest = pgModTime;
				if(oldest == 0)
					break;
			}
		}
		return oldest;
	}

	public final Map<String, String> getInstallerHints()
	{
		return m_installerHints;
	}

	public void appendRelativeFiles(IModelCache ctx, Map<String,Long> fileNames) throws CoreException
	{
		PathGroup[] pqs = getPathGroups(ctx, null);
		int idx = pqs.length;
		while(--idx >= 0)
			pqs[idx].appendRelativeFiles(fileNames);
	}

	public long getLastModified(IModelCache ctx, long threshold, int[] fileCount) throws CoreException
	{
		PathGroup[] pqs = getPathGroups(ctx, null);
		int count = 0;
		int idx = pqs.length;
		int[] countBin = new int[1];
		long newest = 0L;
		while(--idx >= 0)
		{
			countBin[0] = 0;
			long pgModTime = pqs[idx].getLastModified(threshold, countBin);
			count += countBin[0];
			if(pgModTime > newest)
			{
				newest = pgModTime;
				if(newest > threshold)
					break;
			}
		}
		fileCount[0] = count;
		return newest;
	}

	@Override
	public final PathGroup[] getPathGroups(IModelCache ctx, Stack<IAttributeFilter> filters) throws CoreException
	{
		PathGroup[] pga;
		if(filters == null || filters.isEmpty())
		{
			Map<String,PathGroup[]> cache = ctx.getPathGroupsCache();
			String qName = getQualifiedName();
			pga = cache.get(qName);
			if(pga == null)
			{
				ExpandingProperties local = new ExpandingProperties(ctx.getProperties());
				addDynamicProperties(local);
				pga = internalGetPathGroups(ctx, local, filters);
				cache.put(qName, pga);
			}
		}
		else
		{
			// Can't use the cache
			//
			ExpandingProperties local = new ExpandingProperties(ctx.getProperties());
			addDynamicProperties(local);
			pga = internalGetPathGroups(ctx, local, filters);
		}
		return pga;
	}

	public IPath getUniquePath(IPath root, IModelCache modelCtx) throws CoreException
	{
		IPath uniquePath = null;
		PathGroup[] groups = getPathGroups(modelCtx, null);
		if(groups.length == 1)
		{
			PathGroup group = groups[0];
			IPath[] paths = group.getPaths();
			if(paths.length == 1)
			{
				IPath base = group.getBase();
				if(base == null || !base.isAbsolute())
				{
					if(root == null)
						root = getCSpec().getComponentLocation();
					if(base == null)
						base = root;
					else if(!base.isAbsolute())
						base = root.append(base);
				}
				uniquePath = base.append(paths[0]);
			}
		}
		if(uniquePath == null)
			throw BuckminsterException.fromMessage("Unable to determine a unique product path for %s", this);
		return uniquePath;
	}

	@Override
	public boolean isPublic()
	{
		return m_public;
	}

	@Override
	protected abstract AttributeBuilder createAttributeBuilder(CSpecBuilder cspecBuilder);

	@Override
	protected void emitElements(ContentHandler handler, String namespace, String prefix) throws SAXException
	{
		super.emitElements(handler, namespace, prefix);
		if(!m_installerHints.isEmpty())
		{
			String qName = Utils.makeQualifiedName(prefix, ELEM_INSTALLER_HINTS);
			handler.startElement(namespace, ELEM_INSTALLER_HINTS, qName, ISaxableElement.EMPTY_ATTRIBUTES);
			SAXEmitter.emitProperties(handler, m_installerHints, namespace, prefix, true, false);
			handler.endElement(namespace, ELEM_INSTALLER_HINTS, qName);
		}
	}

	protected abstract PathGroup[] internalGetPathGroups(IModelCache ctx, Map<String, String> local, Stack<IAttributeFilter> filters) throws CoreException;
}
