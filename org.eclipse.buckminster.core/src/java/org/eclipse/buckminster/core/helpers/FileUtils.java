/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.helpers;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.security.DigestOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.mspec.ConflictResolution;
import org.eclipse.buckminster.download.DownloadManager;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.ecf.core.security.IConnectContext;
import org.eclipse.osgi.util.NLS;
import org.osgi.framework.Bundle;

/**
 * @author Thomas Hallgren
 */
public abstract class FileUtils {
	public static class CopyOntoSelfException extends LocalizedException {
		private static final long serialVersionUID = 379621474603864267L;

		public CopyOntoSelfException(File source, File destination) {
			super(NLS.bind(Messages.Cannot_copy_0_to_1_since_destination_equal_or_contained_in_source, source, destination));
		}
	}

	public static class DeleteException extends LocalizedException {
		private static final long serialVersionUID = -8216112755038789300L;

		public DeleteException(File file) {
			this(file, null);
		}

		public DeleteException(File file, Throwable e) {
			super(e, NLS.bind(Messages.Unable_to_delete_0, file));
		}
	}

	public static class DestinationNotEmptyException extends LocalizedException {
		private static final long serialVersionUID = -4126568695654461634L;

		public DestinationNotEmptyException(File destination) {
			super(NLS.bind(Messages.Unable_to_use_0_destination_for_copy_not_empty, destination));
		}
	}

	public static class MkdirException extends LocalizedException {
		private static final long serialVersionUID = -1919074286465177750L;

		public MkdirException(File directory) {
			this(directory, null);
		}

		public MkdirException(File directory, Throwable e) {
			super(e, NLS.bind(Messages.Unable_to_create_directory_0, directory));
		}
	}

	public static final String FILE_SEP = System.getProperty("file.separator"); //$NON-NLS-1$

	public static final String PATH_SEP = System.getProperty("path.separator"); //$NON-NLS-1$

	public static final boolean CASE_INSENSITIVE_FS = (new File("a").equals(new File("A"))); //$NON-NLS-1$ //$NON-NLS-2$

	private static final Pattern[] defaultExcludes;

	private static final Object THREADLOCK = new Object();

	private static HashSet<File> foldersToRemove;

	static {
		ArrayList<Pattern> bld = new ArrayList<Pattern>();

		// Standard Apache ANT defaultexcludes
		//
		addPattern(bld, ".*/[^/]*~$"); //$NON-NLS-1$
		addPattern(bld, ".*/#[^/]*#$"); //$NON-NLS-1$
		addPattern(bld, ".*/\\.#[^/]*$"); //$NON-NLS-1$
		addPattern(bld, ".*/%_[^/]*$"); //$NON-NLS-1$
		addPattern(bld, ".*/CVS$"); //$NON-NLS-1$
		addPattern(bld, ".*/CVS/.*"); //$NON-NLS-1$
		addPattern(bld, ".*/\\.cvsignore$"); //$NON-NLS-1$
		addPattern(bld, ".*/SCCS$"); //$NON-NLS-1$
		addPattern(bld, ".*/SCCS/.*"); //$NON-NLS-1$
		addPattern(bld, ".*/vssver\\.scc$"); //$NON-NLS-1$
		addPattern(bld, ".*/\\.svn$"); //$NON-NLS-1$
		addPattern(bld, ".*/\\.svn/.*"); //$NON-NLS-1$
		addPattern(bld, ".*/\\.DS_Store$"); //$NON-NLS-1$
		defaultExcludes = bld.toArray(new Pattern[bld.size()]);
	}

	public static void appendRelativeFiles(File directory, File relPath, Map<String, Long> fileNames) {
		String path = relPath.getPath();
		File fileOrDir = relPath.isAbsolute() ? relPath : new File(directory, path);
		if (fileOrDir.isDirectory()) {
			StringBuilder builder = new StringBuilder();
			builder.append(path);
			appendRelativeFiles(fileOrDir, fileNames, builder);
		} else {
			long ts = fileOrDir.lastModified();
			if (ts != 0L)
				fileNames.put(path, new Long(ts));
		}
	}

	public static void appendRelativeFiles(File directory, Map<String, Long> fileNames) {
		appendRelativeFiles(directory, fileNames, new StringBuilder());
	}

	/**
	 * Helper method to calculate a digest for a file or a tree
	 */
	static public byte[] calculateDigest(File f, String algorithm, IProgressMonitor monitor) throws NoSuchAlgorithmException, IOException {
		MessageDigest md = MessageDigest.getInstance(algorithm);
		DigestOutputStream dos = new DigestOutputStream(NullOutputStream.INSTANCE, md);
		if (f.isFile()) {
			FileInputStream fis = new FileInputStream(f);
			try {
				copyFile(fis, dos, monitor);
			} finally {
				IOUtils.close(fis);
			}
		} else
			deepCalculateDigest(f, dos, f.getCanonicalPath().length() + 1, monitor);
		dos.close();
		return md.digest();
	}

	/**
	 * This method will assert that the <code>source</code> is not present
	 * inside the <code>destination</code> and then call
	 * {@link #prepareDestination(File destination, ConflictResolution strategy, IProgressMonitor monitor)}
	 * .
	 * 
	 * @param source
	 *            The source. Might be a file or a directory.
	 * @param destination
	 *            The destination directory.
	 * @param strategy
	 *            how to handle a destination that is not empty
	 * @throws BuckminsterException
	 */
	public static void checkCopyConditions(File sourceFile, File destination, ConflictResolution strategy, IProgressMonitor monitor)
			throws CoreException {
		// Assert that the destination is different from source and not a
		// subdirectory inside
		// of the source.
		//
		File tmp = destination;
		while (tmp != null) {
			if (tmp.equals(sourceFile))
				throw new CopyOntoSelfException(sourceFile, destination);
			tmp = tmp.getParentFile();
		}
		prepareDestination(destination, strategy, monitor);
	}

	public static boolean compareContents(InputStream a, InputStream b) throws IOException {
		byte[] bufA = new byte[0x400];
		byte[] bufB = new byte[0x400];
		int countA;
		while ((countA = a.read(bufA)) > 0) {
			if (b.read(bufB) != countA)
				return false;
			if (!ArrayUtils.equals(bufA, bufB, 0, countA))
				return false;
		}
		return b.read(bufB) == countA;
	}

	/**
	 * Copy the <code>source</code> file to a <code>destDir</code> directory and
	 * give it the name <code>destName</code>. This mehtod assumes that the
	 * source is a common file, that destDir is a directory, and that a file
	 * named destName can be created in destDir. If such a file exists already,
	 * an attempt will be made to overwrite.
	 * 
	 * @param source
	 *            The source. Cannot be a directory.
	 * @param destDir
	 *            The destination directory.
	 * @param destName
	 *            The name of the file relative to the destination.
	 * @throws IOException
	 *             , MkdirException
	 * @return The total number of bytes copied
	 */
	public static long copyFile(File source, File destDir, String destName, IProgressMonitor monitor) throws CoreException {
		InputStream input = null;
		try {
			input = new FileInputStream(source);
			return copyFile(input, destDir, destName, monitor);
		} catch (IOException e) {
			throw BuckminsterException.wrap(e);
		} finally {
			IOUtils.close(input);
		}
	}

	public static long copyFile(InputStream input, File destDir, String destName, IProgressMonitor monitor) throws CoreException {
		OutputStream output = null;
		MonitorUtils.begin(monitor, 1000);
		try {
			if (destDir != null) {
				File destFile = new File(destDir, destName);
				destDir = destFile.getParentFile(); // destName might have many
				// components
				if (destDir != null && !destDir.exists())
					createDirectory(destDir, MonitorUtils.subMonitor(monitor, 100));
				else
					MonitorUtils.worked(monitor, 100);
				output = new FileOutputStream(destFile);
				MonitorUtils.worked(monitor, 100);
			} else
				output = NullOutputStream.INSTANCE;

			return copyFile(input, output, MonitorUtils.subMonitor(monitor, 700));
		} catch (IOException e) {
			throw BuckminsterException.wrap(e);
		} finally {
			IOUtils.close(output);
			MonitorUtils.done(monitor);
		}
	}

	public static long copyFile(InputStream input, OutputStream output, byte[] buf, IProgressMonitor monitor) throws IOException {
		long total = 0;
		MonitorUtils.begin(monitor, IProgressMonitor.UNKNOWN);
		try {
			int count;
			while ((count = input.read(buf)) > 0) {
				MonitorUtils.worked(monitor, 1);
				output.write(buf, 0, count);
				total += count;
			}
			return total;
		} finally {
			MonitorUtils.done(monitor);
		}
	}

	public static long copyFile(InputStream input, OutputStream output, IProgressMonitor monitor) throws IOException {
		if (input instanceof FileInputStream && output instanceof FileOutputStream) {
			// Use nio style copying with FileChannels
			FileChannel in = ((FileInputStream) input).getChannel();
			FileChannel out = ((FileOutputStream) output).getChannel();
			long size = in.size();
			if (in.transferTo(0, size, out) != size)
				throw new IOException("Incomplete copy!"); //$NON-NLS-1$
			MonitorUtils.complete(monitor);
			return size;
		}
		return copyFile(input, output, new byte[0x2000], monitor);
	}

	public static synchronized void createDirectory(File file, IProgressMonitor monitor) throws MkdirException {
		MonitorUtils.begin(monitor, 1);
		try {
			if (!(file.mkdirs() || file.isDirectory()))
				throw new MkdirException(file);
			MonitorUtils.worked(monitor, 1);
		} catch (SecurityException e) {
			throw new MkdirException(file, e);
		} finally {
			MonitorUtils.done(monitor);
		}
	}

	/**
	 * Creates the given folder and any needed but nonexistent parent folders.
	 * 
	 * @param container
	 * @throws CoreException
	 */
	public static void createFolder(IContainer container) throws CoreException {
		IProgressMonitor nullMonitor = new NullProgressMonitor();
		if (container instanceof IFolder) {
			IFolder folder = (IFolder) container;
			createFolder(folder.getParent());
			folder.refreshLocal(IResource.DEPTH_ZERO, nullMonitor);
			if (!folder.exists())
				folder.create(false, true, nullMonitor);
		}
	}

	/**
	 * Creates a folder based on an abstract file handle. The folder and all its
	 * content will be deleted when the process exists. The <code>tmpDir</code>
	 * directory must not exist when this method is called.
	 * 
	 * @param tmpDir
	 *            The directory to create
	 * @throws MkdirException
	 *             If the directory could not be created.
	 */
	public static synchronized void createTempFolder(File tmpDir) throws MkdirException {
		if (!tmpDir.mkdirs())
			throw new MkdirException(tmpDir);

		if (foldersToRemove == null) {
			foldersToRemove = new HashSet<File>();
			Runtime.getRuntime().addShutdownHook(new Thread() {
				@Override
				public void run() {
					// Prevent that foldersToRemove is updated during the
					// remove
					//
					HashSet<File> folders = new HashSet<File>(foldersToRemove);
					foldersToRemove = null;
					for (File folder : folders) {
						try {
							deleteRecursive(folder, null);
						} catch (Exception e) {
							// We're shutting down so this is ignored
							System.err.println("Failed to remove directory " + folder + " :" + e.toString()); //$NON-NLS-1$ //$NON-NLS-2$
						}
					}
				}
			});
		}
		foldersToRemove.add(tmpDir);
	}

	/**
	 * Creates a folder based on a generated name. The folder and all its
	 * content will be deleted when the process exists.
	 */
	public static synchronized File createTempFolder(String prefix, String suffix) throws CoreException {
		File tmpFile;
		try {
			tmpFile = File.createTempFile(prefix, suffix);
		} catch (IOException e) {
			throw BuckminsterException.wrap(e);
		}
		if (!tmpFile.delete())
			throw new MkdirException(tmpFile);

		createTempFolder(tmpFile);
		return tmpFile;
	}

	/**
	 * Copy everything found in the <code>sourceDirectory</code> to the
	 * <code>destinationDirectory</code>. The destination is prepared according
	 * to the given <code>strategy</code>.
	 * 
	 * @param sourceDirectory
	 *            The source directory for the copy
	 * @param destinationDirectory
	 *            The destination directory for the copy.
	 * @param strategy
	 *            how to handle a destination that is not empty
	 * @throws CoreException
	 */
	public static void deepCopy(File sourceDirectory, File destinationDirectory, ConflictResolution strategy, IProgressMonitor monitor)
			throws CoreException {
		MonitorUtils.begin(monitor, 1000);
		try {
			checkCopyConditions(sourceDirectory, destinationDirectory, strategy, MonitorUtils.subMonitor(monitor, 100));
			deepCopyUnchecked(sourceDirectory, destinationDirectory, MonitorUtils.subMonitor(monitor, 900));
		} finally {
			MonitorUtils.done(monitor);
		}
	}

	public static void deepCopyUnchecked(File source, File dest, IProgressMonitor monitor) throws CoreException {
		deepCopyUnchecked(source, dest, null, defaultExcludes, monitor);
	}

	public static void deepCopyUnchecked(File source, File dest, Pattern[] includes, Pattern[] excludes, IProgressMonitor monitor)
			throws CoreException {
		String sourceStr = source.toString().replace('\\', '/');
		boolean isDir = source.isDirectory();
		if (isDir && sourceStr.charAt(sourceStr.length() - 1) != '/')
			sourceStr += '/';

		if (!isMatch(sourceStr, includes, true) || isMatch(sourceStr, excludes, false)) {
			MonitorUtils.complete(monitor);
			return;
		}

		File[] files = source.listFiles();
		if (files == null || files.length == 0) {
			// Reason might be that the files is in fact a file or
			// an empty directory. Both are ok. A missing entry is
			// not OK.
			//
			try {
				if (isDir)
					MonitorUtils.complete(monitor);
				else if (source.isFile())
					copyFile(source, dest, source.getName(), monitor);
				else
					throw new FileNotFoundException(source.getAbsolutePath());
			} catch (IOException e) {
				throw BuckminsterException.wrap(e);
			}
		}

		MonitorUtils.begin(monitor, 10 + files.length * 100);
		try {
			createDirectory(dest, MonitorUtils.subMonitor(monitor, 10));
			for (File file : files) {
				IProgressMonitor subMonitor = MonitorUtils.subMonitor(monitor, 100);
				if (file.isFile()) {
					sourceStr = source.toString().replace('\\', '/');
					if (isMatch(sourceStr, includes, true) && !isMatch(sourceStr, excludes, false))
						copyFile(file, dest, file.getName(), subMonitor);
					else
						MonitorUtils.complete(subMonitor);
				} else
					deepCopyUnchecked(file, new File(dest, file.getName()), includes, excludes, subMonitor);
			}
		} finally {
			MonitorUtils.done(monitor);
		}
	}

	public static void deleteRecursive(File file, IProgressMonitor monitor) throws DeleteException {
		MonitorUtils.begin(monitor, 1000);
		try {
			if (file == null)
				return;

			File[] list = file.listFiles();
			int count = (list == null) ? 0 : list.length;
			if (count > 0) {
				IProgressMonitor subMon = MonitorUtils.subMonitor(monitor, 900);
				MonitorUtils.begin(subMon, count * 100);
				try {
					if (foldersToRemove != null)
						foldersToRemove.remove(file);

					while (--count >= 0)
						deleteRecursive(list[count], MonitorUtils.subMonitor(subMon, 100));
				} finally {
					MonitorUtils.done(subMon);
				}
			} else
				MonitorUtils.worked(monitor, 900);

			if (!file.delete() && file.exists())
				throw new DeleteException(file);
			MonitorUtils.worked(monitor, 100);
		} catch (SecurityException e) {
			throw new DeleteException(file, e);
		} finally {
			MonitorUtils.done(monitor);
		}
	}

	public static File findPath(Bundle bundle, String path) throws CoreException {
		try {
			return path == null ? null : new File(FileLocator.toFileURL(FileLocator.find(bundle, new Path(path), null)).toURI());
		} catch (Exception e) {
			throw BuckminsterException.wrap(e);
		}
	}

	public static File findPath(Plugin plugin, String path) throws CoreException {
		return findPath(plugin.getBundle(), path);
	}

	public static File findPath(String pluginId, String path) throws CoreException {
		return findPath(Platform.getBundle(pluginId), path);
	}

	public static long getCRC(InputStream input) throws IOException {
		CRC32 crc32 = new CRC32();
		byte[] copyBuffer = new byte[0x2000]; // 8 kilobyte buffer.
		int count;
		while ((count = input.read(copyBuffer)) > 0)
			crc32.update(copyBuffer, 0, count);
		return crc32.getValue();
	}

	public static long getCRCAndCopy(InputStream input, OutputStream output) throws IOException {
		CRC32 crc32 = new CRC32();
		byte[] copyBuffer = new byte[0x2000]; // 8 kilobyte buffer.
		int count;
		while ((count = input.read(copyBuffer)) > 0) {
			output.write(copyBuffer, 0, count);
			crc32.update(copyBuffer, 0, count);
		}
		return crc32.getValue();
	}

	public static File getFile(URL url) {
		if (url == null)
			return null;

		String proto = url.getProtocol();
		if (proto == null)
			proto = "file"; //$NON-NLS-1$
		else {
			try {
				URL newUrl = FileLocator.resolve(url);
				if (newUrl != url) {
					proto = newUrl.getProtocol();
					url = newUrl;
				}
			} catch (IOException e1) {
			}
		}

		if ("file".equalsIgnoreCase(proto)) //$NON-NLS-1$
		{
			try {
				return new File(url.toURI());
			} catch (URISyntaxException e) {
				// This is probably due to spaces in the URL path. If it
				// is, the URL is per definition corrupt but let's use that
				// path verbatim anyway
				// http://bugs.eclipse.org/bugs/show_bug.cgi?id=125967
				//
				String path = url.getPath();
				if (path.indexOf(' ') >= 0)
					return new File(path);

				// Nope, that was not the problem!
				//
				CorePlugin.getLogger().warning(e, e.getMessage());
			} catch (IllegalArgumentException e) {
				// Probably because the URI has a fragement component
			}
		}
		return null;
	}

	public static IPath getFileAsPath(URL url) {
		File file = getFile(url);
		return file == null ? null : new Path(file.toString());
	}

	/**
	 * <p>
	 * If the <code>fileOrDir</code> is a file, and if the
	 * <code>expectedFileCount &lt;= 1</code>, this method returns the timestamp
	 * for that file.
	 * </p>
	 * <p>
	 * If <code>fileOrDir</code> is a directory the following rules apply:
	 * <ul>
	 * <li>if the <code>expectedFileCount</code> is <code>-1</code>, this method
	 * will return <code>0L</code>.</li>
	 * <li>if the <code>expectedFileCount</code> is <code>0</code>, this method
	 * will return the timestamp of the oldest file found using a recursive scan
	 * of all subdirectories.</li>
	 * <li>if the <code>expectedFileCount</code> is a positive number, this
	 * method will return the timestamp of the oldest file found using a
	 * recursive scan of all subdirectories provided the number of files is
	 * greater or equal to that <code>expectedFileCount</code>.</li>
	 * </ul>
	 * </p>
	 * <p>
	 * for all other cases this method returns <code>0L</code>.
	 * </p>
	 * <p>
	 * The method will return the actual number of files found in the one
	 * element array parameter <code>realFileCount</code>.
	 * 
	 * @param fileOrDir
	 *            The file or directory to check.
	 * @param expectedFileCount
	 *            <code>-1</code>, <code>0</code>, or a minimum expected file
	 *            count.
	 * @param realFileCount
	 *            A one element array where the actual number of files is
	 *            returned.
	 * @return The file modification time according to the rules outlined for
	 *         this method.
	 */
	public static long getFirstModified(File fileOrDir, int expectedFileCount, int[] realFileCount) {
		if (fileOrDir.isFile() && expectedFileCount <= 1) {
			realFileCount[0] = 1;
			return fileOrDir.lastModified();
		}

		long timestampHolder[] = new long[] { Long.MAX_VALUE };
		int count = countFilesAndGetOldest(fileOrDir, 0, timestampHolder);
		realFileCount[0] = count;
		if (count == 0 || expectedFileCount < 0 || timestampHolder[0] == Long.MAX_VALUE)
			return 0L;

		return timestampHolder[0];
	}

	/**
	 * Returns the timestamp of the last modified file. If fileOrDir is a file,
	 * the timestamp will be equal to the lastModified time of that file. If
	 * fileOrDir is a directory, this method will recurse into itself with each
	 * file or directory found there. The search stops if a file is encountered
	 * that has a timestamp greater or equal to <code>threshold</code>.
	 * 
	 * @param The
	 *            file or directory to check.
	 * @param threshold
	 *            Stop if a file is found that is newer then threshold
	 * @return The last modification time found on a file. If no file is found,
	 *         this method returns 0L.
	 */
	public static long getLastModified(File fileOrDir, long threshold, int[] realFileCount) {
		int count = 0;
		long lastModTime = 0;
		File[] files = fileOrDir.listFiles();
		if (files == null) {
			if (fileOrDir.isFile()) {
				lastModTime = fileOrDir.lastModified();
				count = 1;
			}
		} else {
			for (File p : files) {
				int dirFileCount[] = new int[] { 0 };
				long modTime = getLastModified(p, threshold, dirFileCount);
				count += dirFileCount[0];
				if (modTime > lastModTime) {
					lastModTime = modTime;
					if (modTime > threshold)
						break;
				}
			}
		}
		realFileCount[0] = count;
		return lastModTime;
	}

	/**
	 * Creates directories in a synchronized block.
	 * 
	 * @param directory
	 *            The path for which all directories should be created
	 * @throws CoreException
	 *             If the directories cannot be created
	 */
	public static void mkdirs(File directory) throws CoreException {
		synchronized (THREADLOCK) {
			if (directory == null || directory.exists() && !directory.isDirectory())
				throw BuckminsterException.fromMessage(NLS.bind(Messages.Unable_to_create_directory_0_Not_a_directory, directory != null ? directory
						: "(null)")); //$NON-NLS-1$

			if (!directory.exists() && !directory.mkdirs())
				throw BuckminsterException.fromMessage(NLS.bind(Messages.Unable_to_create_directory_0, directory));
		}
	}

	/**
	 * Perform an OS sensitive equality comparison between two paths. This is
	 * very different from the {@link IPath#equals(Object)} since that method is
	 * case sensitive on all platforms.
	 * 
	 * @param a
	 * @param b
	 * @return <code>true</code> if both paths are equal or both paths are
	 *         <code>null</code>.
	 */
	public static boolean pathEquals(IPath a, IPath b) {
		return (a == null || b == null) ? a == b : a.toFile().equals(b.toFile());
	}

	public static IPath pathForLocation(IPath location) {
		if (Platform.getLocation().equals(location))
			return Path.ROOT;

		IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
		for (int i = 0; i < projects.length; i++) {
			IProject project = projects[i];
			IPath projectLocation = project.getLocation();
			if (projectLocation != null && projectLocation.isPrefixOf(location)) {
				int segmentsToRemove = projectLocation.segmentCount();
				return project.getFullPath().append(location.removeFirstSegments(segmentsToRemove));
			}
		}
		return null;
	}

	/**
	 * This method prepares the <code>destination</code> to receive a file or
	 * files according to the given <code>strategy</code>
	 * 
	 * @param source
	 *            The source. Might be a file or a directory.
	 * @param destination
	 *            The destination directory.
	 * @param strategy
	 *            how to handle a destination that is not empty
	 * @throws BuckminsterException
	 */
	public static void prepareDestination(File destination, ConflictResolution strategy, IProgressMonitor monitor) throws CoreException {
		MonitorUtils.begin(monitor, 200);
		try {
			File[] list = destination.listFiles();
			MonitorUtils.worked(monitor, 30);
			if (list == null) {
				if (destination.isFile()) {
					if (strategy == ConflictResolution.FAIL)
						throw new DestinationNotEmptyException(destination);

					if (strategy != ConflictResolution.KEEP) {
						// Both UPDATE and REPLACE will replace a file
						//
						if (!destination.delete())
							throw new DeleteException(destination);
					}
					MonitorUtils.worked(monitor, 85);
				}
				createDirectory(destination, MonitorUtils.subMonitor(monitor, 85));
			} else {
				int numFiles = list.length;
				if (numFiles > 0) {
					IProgressMonitor subMonitor = MonitorUtils.subMonitor(monitor, 170);
					MonitorUtils.begin(subMonitor, numFiles * 100);
					try {
						if (strategy == ConflictResolution.FAIL)
							throw new DestinationNotEmptyException(destination);

						if (strategy == ConflictResolution.REPLACE) {
							for (File file : list)
								deleteRecursive(file, MonitorUtils.subMonitor(subMonitor, 100));
						}
					} finally {
						MonitorUtils.done(subMonitor);
					}
				} else
					MonitorUtils.worked(monitor, 170);
			}
		} finally {
			MonitorUtils.done(monitor);
		}
	}

	/**
	 * Substitute parameters in the form &lt;dc&gt;&lt;paramName&gt;&lt;dc&gt;
	 * where &lt;dc&gt; is the <code>delim</code> character and
	 * &lt;paramName&gt; is a parameter that is found in
	 * <code>substitutions</code>. An unmatched parameter substitution string is
	 * replaced with an empty string
	 * 
	 * @param input
	 *            The input stream to read from
	 * @param output
	 *            The output to write to
	 * @param delim
	 *            The character that starts and ends a parameter substitution
	 * @param substitutions
	 *            The map containing valid substitutions
	 * @throws IOException
	 */
	public static void substituteParameters(InputStream input, OutputStream output, char delim, Map<String, String> substitutions) throws IOException {
		BufferedInputStream bufferedInput = new BufferedInputStream(input);
		BufferedOutputStream bufferedOutput = new BufferedOutputStream(output);

		int c;
		parseName: while ((c = bufferedInput.read()) >= 0) {
			if (c != delim) {
				bufferedOutput.write((byte) c);
				continue;
			}

			bufferedInput.mark(Integer.MAX_VALUE);
			c = bufferedInput.read();
			if (c == delim) {
				bufferedOutput.write(delim);
				continue;
			}

			StringBuilder nameBuilder = new StringBuilder();
			nameBuilder.append((char) c);

			while ((c = bufferedInput.read()) >= 0) {
				if (c == delim)
					break;

				if (Character.isJavaIdentifierPart((char) c)) {
					nameBuilder.append((char) c);
					continue;
				}

				// Not a valid parameter substitution
				//
				bufferedOutput.write(delim);
				bufferedInput.reset();
				continue parseName;
			}

			String paramName = nameBuilder.toString();
			String param = substitutions.get(paramName);
			if (param != null)
				bufferedOutput.write(param.getBytes());
		}
		bufferedOutput.flush();
	}

	public static void unzip(InputStream inputs, String sourceRelPath, File dest, ConflictResolution strategy, IProgressMonitor monitor)
			throws CoreException {
		ZipEntry entry;
		ZipInputStream input = null;
		MonitorUtils.begin(monitor, 600);
		if (dest != null)
			prepareDestination(dest, strategy, MonitorUtils.subMonitor(monitor, 100));

		try {
			int ticksLeft = 500;
			input = new ZipInputStream(inputs);

			while ((entry = input.getNextEntry()) != null) {
				String name = entry.getName();
				if (sourceRelPath != null) {
					if (!name.startsWith(sourceRelPath))
						continue;
					name = name.substring(sourceRelPath.length() + 1);
					if (name.length() == 0)
						continue;
				}

				IProgressMonitor subMonitor;
				if (ticksLeft > 0) {
					subMonitor = MonitorUtils.subMonitor(monitor, 10);
					ticksLeft -= 10;
				} else {
					subMonitor = null;
				}

				if (entry.isDirectory()) {
					if (dest != null)
						createDirectory(new File(dest, name), subMonitor);
					continue;
				}
				copyFile(input, dest, name, subMonitor);
			}
			if (ticksLeft > 0)
				MonitorUtils.worked(monitor, ticksLeft);
		} catch (IOException e) {
			throw BuckminsterException.wrap(e);
		} finally {
			MonitorUtils.done(monitor);
		}
	}

	/**
	 * Unzip the <code>source</code> contents to a <code>destDir</code>
	 * directory and give it the name <code>destName</code>. This method assumes
	 * that the source is the URL that points to zipped contents, that destDir
	 * is a directory, and that a file named destName can be created in destDir.
	 * 
	 * @param source
	 *            The source zipped content.
	 * @param sourceRelPath
	 *            Relative path to material inside the soruce file.
	 * @param dest
	 *            The destination directory.
	 * @param strategy
	 *            how to handle a destination that is not empty
	 * @param monitor
	 *            The progress monitor used during the operation
	 * @throws BuckminsterException
	 */
	public static void unzip(URL source, IConnectContext cctx, String sourceRelPath, File dest, ConflictResolution strategy, IProgressMonitor monitor)
			throws CoreException {
		InputStream input = null;
		try {
			input = DownloadManager.read(source, cctx);
			unzip(input, sourceRelPath, dest, strategy, monitor);
		} catch (IOException e) {
			throw BuckminsterException.wrap(e);
		} finally {
			IOUtils.close(input);
		}
	}

	private static void addPattern(ArrayList<Pattern> bld, String expr) {
		bld.add(Pattern.compile(expr));
	}

	private static void appendRelativeFiles(File directory, Map<String, Long> fileNames, StringBuilder path) {
		int pathLen = path.length();
		if (pathLen > 0) {
			path.append(FILE_SEP);
			pathLen = path.length();
		}

		File[] files = directory.listFiles();
		if (files == null)
			return;

		int idx = files.length;
		while (--idx >= 0) {
			File file = files[idx];
			String sourceStr = file.toString().replace('\\', '/');

			path.append(file.getName());
			if (file.isDirectory()) {
				if (!isMatch(sourceStr + '/', defaultExcludes, false))
					appendRelativeFiles(file, fileNames, path);
			} else {
				if (!isMatch(sourceStr, defaultExcludes, false))
					fileNames.put(path.toString(), new Long(file.lastModified()));
			}
			path.setLength(pathLen);
		}
	}

	private static int countFilesAndGetOldest(File fileOrDir, int count, long[] timestampHolder) {
		File[] files = fileOrDir.listFiles();
		if (files == null) {
			if (fileOrDir.isFile()) {
				long timestamp = fileOrDir.lastModified();
				if (timestamp < timestampHolder[0])
					timestampHolder[0] = timestamp;
				count++;
			}
		} else {
			for (File file : files)
				count = countFilesAndGetOldest(file, count, timestampHolder);
		}
		return count;
	}

	/**
	 * internal helper method to read all files/dirs in a directory tree to a
	 * *single* outstream
	 */
	private static void deepCalculateDigest(File from, OutputStream os, int rootOffset, IProgressMonitor monitor) throws IOException {
		// get the file list, but *always* sort it to ensure we
		// always process things in the same order as this is important
		// for always getting the same digest
		//
		File names[] = from.listFiles();
		MonitorUtils.begin(monitor, names.length * 100);
		try {
			Arrays.sort(names);
			for (int i = 0; i < names.length; i++) {
				File p = names[i];
				//
				// Make pathnames count for everything we encounter to ensure
				// changes in dir structure also count (new dirs, empty files,
				// changed names)
				// replace the platform specific separator with something
				// else...we should then
				// get identical results on any platform.
				//
				IProgressMonitor subMonitor = MonitorUtils.subMonitor(monitor, 100);
				os.write(p.getName().getBytes());
				if (p.isDirectory()) {
					os.write(1);
					deepCalculateDigest(p, os, rootOffset, subMonitor);
				} else {
					FileInputStream fis = new FileInputStream(p);
					copyFile(fis, os, subMonitor);
					fis.close();
				}
			}
		} finally {
			MonitorUtils.done(monitor);
		}
	}

	private static boolean isMatch(String fileStr, Pattern[] patterns, boolean whenEmpty) {
		if (patterns != null) {
			int idx = patterns.length;
			if (idx > 0) {
				while (--idx >= 0)
					if (patterns[idx].matcher(fileStr).matches())
						return true;
				return false;
			}
		}
		return whenEmpty;
	}
}
