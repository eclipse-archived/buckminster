/*****************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec;

import java.io.File;
import java.util.Map;

import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.mspec.ConflictResolution;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @author Thomas Hallgren
 */
public class PathGroup {
	public static final PathGroup[] EMPTY_ARRAY = new PathGroup[0];

	private final IPath base;

	private final IPath[] paths;

	public PathGroup(IPath base, IPath[] paths) {
		if (base == null)
			throw new IllegalArgumentException(Messages.Base_cannot_be_null);
		this.base = base;
		this.paths = paths;
	}

	public void appendRelativeFiles(Map<String, Long> fileNames) {
		int idx = paths.length;
		if (idx == 0) {
			// We don't have any paths. Use everything below base
			//
			FileUtils.appendRelativeFiles(base.toFile(), fileNames);
			return;
		}

		File baseDir = base.toFile();
		while (--idx >= 0)
			FileUtils.appendRelativeFiles(baseDir, paths[idx].toFile(), fileNames);
	}

	public boolean containsPath(IPath path) {
		if (path == null)
			return false;

		// We consider the completely empty path to be a prefix of
		// all other paths.
		//
		boolean isCompletelyEmpty = (base.isEmpty() && base.getDevice() == null);
		boolean isPrefix = isCompletelyEmpty || base.isPrefixOf(path);

		if (!isPrefix)
			return false;

		IPath relPath = path;
		if (!isCompletelyEmpty)
			relPath = path.removeFirstSegments(base.segmentCount()).setDevice(null);

		// Check for exact matches or for directories that are parents to
		// the given path.
		//
		for (IPath rel : paths) {
			IPath cmpPath = rel.isAbsolute() ? path : relPath;
			if (rel.equals(cmpPath) || rel.hasTrailingSeparator() && rel.isPrefixOf(cmpPath))
				return true;
		}
		return false;
	}

	public void copyPathTo(IPath path, IPath destination, IProgressMonitor monitor) throws CoreException {
		File destDir = destination.toFile().getAbsoluteFile();
		File basedDestDir = null;
		if (!path.hasTrailingSeparator()) {
			// Path denotes a file
			//
			String fileName = path.lastSegment();
			if (path.isAbsolute() || path.segmentCount() == 1) {
				basedDestDir = destDir;
				if (!path.isAbsolute())
					path = base.append(path);
			} else
				basedDestDir = destination.append(path.removeLastSegments(1)).toFile();

			File sourceFile = path.toFile();
			if (sourceFile.exists()) {
				MonitorUtils.begin(monitor, 100);
				FileUtils.prepareDestination(basedDestDir, ConflictResolution.UPDATE, MonitorUtils.subMonitor(monitor, 20));
				FileUtils.copyFile(sourceFile, basedDestDir, fileName, MonitorUtils.subMonitor(monitor, 80));
				MonitorUtils.done(monitor);
			} else {
				MonitorUtils.complete(monitor);
			}
		} else {
			if (path.isAbsolute())
				basedDestDir = destDir;
			else {
				basedDestDir = destination.append(path).toFile();
				path = base.append(path);
			}
			File sourceDir = path.toFile();
			if (sourceDir.exists())
				FileUtils.deepCopy(sourceDir, basedDestDir, ConflictResolution.UPDATE, monitor);
			else
				MonitorUtils.complete(monitor);
		}
	}

	public void copyTo(IPath destination, IProgressMonitor monitor) throws CoreException {
		if (!destination.isAbsolute())
			throw new IllegalArgumentException(Messages.Destination_must_be_absolute);

		if (!base.isAbsolute())
			throw new IllegalArgumentException(Messages.Source_must_be_absolute);

		int idx = paths.length;
		if (idx == 0) {
			// We don't have any paths. Use everything below base
			//
			File baseDir = base.toFile().getAbsoluteFile();
			if (baseDir.isDirectory())
				FileUtils.deepCopy(baseDir, destination.toFile().getAbsoluteFile(), ConflictResolution.UPDATE, monitor);
			else
				MonitorUtils.complete(monitor);
			return;
		}

		monitor.beginTask(null, idx * 100);
		while (--idx >= 0)
			copyPathTo(paths[idx], destination, MonitorUtils.subMonitor(monitor, 100));
		monitor.done();
	}

	public final IPath getBase() {
		return base;
	}

	public long getFirstModified(int expectedCount, int[] fileCount) {
		int idx = paths.length;
		if (idx == 0)
			//
			// We don't have any paths. Use everything below base
			//
			return FileUtils.getFirstModified(base.toFile(), expectedCount, fileCount);

		if (idx > 1 && expectedCount > 0)
			expectedCount = -1;

		long firstMod = Long.MAX_VALUE;
		while (--idx >= 0) {
			IPath absPath = paths[idx];
			if (!absPath.isAbsolute())
				absPath = base.append(absPath);
			long modTime = FileUtils.getFirstModified(absPath.toFile(), expectedCount, fileCount);
			if (modTime < firstMod) {
				firstMod = modTime;
				if (modTime == 0)
					break;
			}
		}
		return firstMod;
	}

	public long getLastModified(long threshold, int[] fileCount) {
		int idx = paths.length;
		if (idx == 0)
			//
			// We don't have any paths. Use everything below base
			//
			return FileUtils.getLastModified(base.toFile(), threshold, fileCount);

		long lastMod = 0;
		int count = 0;
		int[] countBin = new int[1];
		while (--idx >= 0) {
			IPath absPath = paths[idx];
			if (!absPath.isAbsolute())
				absPath = base.append(absPath);
			countBin[0] = 0;
			long modTime = FileUtils.getLastModified(absPath.toFile(), threshold, countBin);
			count += countBin[0];
			if (modTime > lastMod) {
				lastMod = modTime;
				if (modTime > threshold)
					break;
			}
		}
		fileCount[0] = count;
		return lastMod;
	}

	public final IPath[] getPaths() {
		return paths;
	}

	@Override
	public String toString() {
		StringBuilder bld = new StringBuilder();
		bld.append(base.toPortableString());
		bld.append('{');
		int top = paths.length;
		if (top > 0) {
			bld.append(paths[0]);
			for (int idx = 1; idx < top; ++idx) {
				bld.append(',');
				bld.append(paths[idx]);
			}
		}
		bld.append('}');
		return bld.toString();
	}
}
