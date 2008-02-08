/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec;

import java.io.File;
import java.util.Map;

import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.mspec.model.ConflictResolution;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @author Thomas Hallgren
 */
public class PathGroup
{
	public static final PathGroup[] EMPTY_ARRAY = new PathGroup[0];

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

		IPath relPath = path;
		if(!isCompletelyEmpty)
			relPath = path.removeFirstSegments(m_base.segmentCount()).setDevice(null);

		// Check for exact matches or for directories that are parents to
		// the given path.
		//
		for(IPath rel : m_paths)
		{
			IPath cmpPath = rel.isAbsolute() ? path : relPath;
			if(rel.equals(cmpPath) || rel.hasTrailingSeparator() && rel.isPrefixOf(cmpPath))
				return true;
		}
		return false;
	}

	public final IPath getBase()
	{
		return m_base;
	}

	public void copyTo(IPath destination, IProgressMonitor monitor) throws CoreException
	{
		if(!destination.isAbsolute())
			throw new IllegalArgumentException("destination must be absolute");

		if(!m_base.isAbsolute())
			throw new IllegalArgumentException("source must be absolute");

		File destDir = destination.toFile().getAbsoluteFile();
		File baseDir = m_base.toFile().getAbsoluteFile();

		int idx = m_paths.length;
		if(idx == 0)
		{
			// We don't have any paths. Use everything below base
			//
			if(baseDir.isDirectory())
				FileUtils.deepCopy(baseDir, destDir, ConflictResolution.UPDATE, monitor);
			return;
		}

		monitor.beginTask(null, idx * 100);
		while(--idx >= 0)
		{
			String fileName = null;
			File basedDestDir = null; 
			IPath path = m_paths[idx];
			if(!path.hasTrailingSeparator())
			{
				// Path denotes a file
				//
				fileName = path.lastSegment();
				if(path.isAbsolute() || path.segmentCount() == 1)
				{
					basedDestDir = destDir;
					if(!path.isAbsolute())
						path = m_base.append(path);
				}
				else
					basedDestDir = destination.append(path.removeLastSegments(1)).toFile();

				File sourceFile = path.toFile();
				if(sourceFile.exists())
				{
					FileUtils.prepareDestination(basedDestDir, ConflictResolution.UPDATE, MonitorUtils.subMonitor(monitor,20));
					FileUtils.copyFile(sourceFile, basedDestDir, fileName, MonitorUtils.subMonitor(monitor,80));
				}
			}
			else
			{
				if(path.isAbsolute())
					basedDestDir = destDir;
				else
				{
					basedDestDir = destination.append(path).toFile();
					path = m_base.append(path);
				}
				File sourceDir = path.toFile();
				if(sourceDir.exists())
					FileUtils.deepCopy(sourceDir, basedDestDir, ConflictResolution.UPDATE, MonitorUtils.subMonitor(monitor, 100));
			}
		}
		monitor.done();
	}

	public long getFirstModified(int expectedCount, int[] fileCount)
	{
		int idx = m_paths.length;
		if(idx == 0)
			//
			// We don't have any paths. Use everything below base
			//
			return FileUtils.getFirstModified(m_base.toFile(), expectedCount, fileCount);

		if(idx > 1 && expectedCount > 0)
			expectedCount = -1;

		long firstMod = Long.MAX_VALUE;
		while(--idx >= 0)
		{
			IPath absPath = m_paths[idx];
			if(!absPath.isAbsolute())
				absPath = m_base.append(absPath);
			long modTime = FileUtils.getFirstModified(absPath.toFile(), expectedCount, fileCount);
			if(modTime < firstMod)
			{
				firstMod = modTime;
				if(modTime == 0)
					break;
			}
		}
		return firstMod;
	}

	public void appendRelativeFiles(Map<String,Long> fileNames)
	{
		int idx = m_paths.length;
		if(idx == 0)
		{
			// We don't have any paths. Use everything below base
			//
			FileUtils.appendRelativeFiles(m_base.toFile(), fileNames);
			return;
		}

		File baseDir = m_base.toFile();
		while(--idx >= 0)
			FileUtils.appendRelativeFiles(baseDir, m_paths[idx].toFile(), fileNames);
	}

	public long getLastModified(long threshold, int[] fileCount)
	{
		int idx = m_paths.length;
		if(idx == 0)
			//
			// We don't have any paths. Use everything below base
			//
			return FileUtils.getLastModified(m_base.toFile(), threshold, fileCount);

		long lastMod = 0;
		int count = 0;
		int[] countBin = new int[1];
		while(--idx >= 0)
		{
			IPath absPath = m_paths[idx];
			if(!absPath.isAbsolute())
				absPath = m_base.append(absPath);
			countBin[0] = 0;
			long modTime = FileUtils.getLastModified(absPath.toFile(), threshold, countBin);
			count += countBin[0];
			if(modTime > lastMod)
			{
				lastMod = modTime;
				if(modTime > threshold)
					break;
			}
		}
		fileCount[0] = count;
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
