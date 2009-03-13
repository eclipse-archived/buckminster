/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.model;

import java.util.Map;
import java.util.Stack;

import org.eclipse.buckminster.core.KeyConstants;
import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.cspec.IAttributeFilter;
import org.eclipse.buckminster.core.cspec.PathGroup;
import org.eclipse.buckminster.core.cspec.builder.AttributeBuilder;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.builder.TopLevelAttributeBuilder;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.metadata.model.IModelCache;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.osgi.util.NLS;

/**
 * The super class for actions, artifacts, and groups
 * 
 * @author Thomas Hallgren
 */
public abstract class TopLevelAttribute extends Attribute implements Cloneable
{
	public final static String PROPERTY_PREFIX = "buckminster."; //$NON-NLS-1$

	public final static String INSTALLER_HINT_PREFIX = PROPERTY_PREFIX + "install."; //$NON-NLS-1$

	public static final String PUBLIC_TAG = "public"; //$NON-NLS-1$

	public static final String PRIVATE_TAG = "private"; //$NON-NLS-1$

	public static final String DEFINITION_TAG = "definitions"; //$NON-NLS-1$

	public static final String DEFINE_TAG = "define"; //$NON-NLS-1$

	private final boolean m_public;

	TopLevelAttribute(String name)
	{
		super(name);
		m_public = false;
	}

	TopLevelAttribute(TopLevelAttributeBuilder builder)
	{
		super(builder);
		m_public = builder.isPublic();
	}

	@Override
	public void addDynamicProperties(Map<String, Object> properties) throws CoreException
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

		String tempRootStr = (String)properties.get(KeyConstants.ACTION_TEMP_ROOT);
		IPath tempRoot;
		if(tempRootStr == null)
			tempRoot = Path.fromOSString(System.getProperty("java.io.tmpdir")).append("buckminster"); //$NON-NLS-1$ //$NON-NLS-2$
		else
			tempRoot = Path.fromOSString(tempRootStr);

		String actionTemp = tempRoot.append(uniqueFolder).append("temp").toPortableString(); //$NON-NLS-1$

		String outputRoot = (String)properties.get(KeyConstants.ACTION_OUTPUT_ROOT);
		if(outputRoot != null)
		{
			// Output root must be qualified with component name to avoid
			// conflicts
			//
			actionOutput = Path.fromOSString(outputRoot).append(uniqueFolder).toPortableString();
		}
		else
			actionOutput = tempRoot.append("build").toPortableString(); //$NON-NLS-1$

		properties.put(KeyConstants.ACTION_OUTPUT, actionOutput);
		properties.put(KeyConstants.ACTION_TEMP, actionTemp);
		properties.put(KeyConstants.ACTION_HOME, cspec.getComponentLocation().toOSString());
		properties.putAll(cspec.getComponentIdentifier().getProperties());
	}

	public void appendRelativeFiles(IModelCache ctx, Map<String, Long> fileNames) throws CoreException
	{
		PathGroup[] pqs = getPathGroups(ctx, null);
		int idx = pqs.length;
		while(--idx >= 0)
			pqs[idx].appendRelativeFiles(fileNames);
	}

	@Override
	public String getDefaultTag()
	{
		return isPublic()
				? PUBLIC_TAG
				: PRIVATE_TAG;
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
			Map<String, PathGroup[]> cache = ctx.getPathGroupsCache();
			String qName = getQualifiedName();
			pga = cache.get(qName);
			if(pga == null)
			{
				ExpandingProperties<Object> local = new ExpandingProperties<Object>(ctx.getProperties());
				addDynamicProperties(local);
				pga = internalGetPathGroups(ctx, local, filters);
				cache.put(qName, pga);
			}
		}
		else
		{
			// Can't use the cache
			//
			ExpandingProperties<Object> local = new ExpandingProperties<Object>(ctx.getProperties());
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
			throw BuckminsterException.fromMessage(NLS.bind(Messages.Unable_to_determine_a_unique_product_path_for_0,
					this));
		return uniquePath;
	}

	@Override
	public boolean isPublic()
	{
		return m_public;
	}

	@Override
	protected abstract AttributeBuilder createAttributeBuilder(CSpecBuilder cspecBuilder);

	protected abstract PathGroup[] internalGetPathGroups(IModelCache ctx, Map<String, ? extends Object> local,
			Stack<IAttributeFilter> filters) throws CoreException;
}
