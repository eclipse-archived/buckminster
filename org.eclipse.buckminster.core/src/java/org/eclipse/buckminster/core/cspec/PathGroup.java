/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec;

import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.core.runtime.IPath;

/**
 * @author Thomas Hallgren
 */
public class PathGroup
{
	private final IPath m_base;
	private final IPath[] m_paths;
	
	public PathGroup(IPath base, IPath[] paths)
	{
		if(base == null)
			throw new IllegalArgumentException("base cannot be null");
		m_base = base;
		m_paths = paths;
	}

	public boolean containsPath(IPath path)
	{
		if(path == null)
			return false;

		// We consider the completely empty path to be a prefix of
		// all other paths.
		//
		boolean isCompletelyEmpty = (m_base.isEmpty() && m_base.getDevice() == null);
		boolean isPrefix = isCompletelyEmpty || m_base.isPrefixOf(path);

		if(!isPrefix)
			return false;

		if(!isCompletelyEmpty)
			path = path.removeFirstSegments(m_base.segmentCount()).setDevice(null);

		// Check for exact matches or for directories that are parents to
		// the given path.
		//
		for(IPath rel : m_paths)
			if(rel.equals(path) || rel.hasTrailingSeparator() && rel.isPrefixOf(path))
				return true;
		
		return false;
	}

	public final IPath getBase()
	{
		return m_base;
	}

	public long getFirstModified()
	{
		if(m_paths.length == 0)
			//
			// We don't have any paths. Use everything below base
			//
			return FileUtils.getFirstModified(m_base.toFile());

		long firstMod = Long.MAX_VALUE;
		for(IPath path : m_paths)
		{
			IPath absPath = m_base.append(path);
			long modTime = FileUtils.getFirstModified(absPath.toFile());
			if(modTime < firstMod)
			{
				firstMod = modTime;
				if(modTime == 0)
					break;
			}
		}
		return firstMod;
	}

	public long getLastModified(long threshold)
	{
		if(m_paths.length == 0)
			//
			// We don't have any paths. Use everything below base
			//
			return FileUtils.getLastModified(m_base.toFile(), threshold);

		long lastMod = 0;
		for(IPath path : m_paths)
		{
			IPath absPath = m_base.append(path);
			long modTime = FileUtils.getLastModified(absPath.toFile(), threshold);
			if(modTime > lastMod)
			{
				lastMod = modTime;
				if(modTime >= threshold)
					break;
			}
		}
		return lastMod;
	}

	public final IPath[] getPaths()
	{
		return m_paths;
	}

	@Override
	public String toString()
	{
		StringBuilder bld = new StringBuilder();
		bld.append(m_base.toPortableString());
		bld.append('{');
		int top = m_paths.length;
		if(top > 0)
		{
			bld.append(m_paths[0]);
			for(int idx = 1; idx < top; ++idx)
			{
				bld.append(',');
				bld.append(m_paths[idx]);
			}
		}
		bld.append('}');
		return bld.toString();
	}
}
