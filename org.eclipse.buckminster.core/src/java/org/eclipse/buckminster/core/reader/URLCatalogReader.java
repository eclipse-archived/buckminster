/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.reader;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.regex.Pattern;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.helpers.FileHandle;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.mspec.ConflictResolution;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;

/**
 * @author Thomas Hallgren
 */
public class URLCatalogReader extends AbstractCatalogReader {
	private final URI uri;

	protected URLCatalogReader(URLCatalogReaderType readerType, ProviderMatch rInfo) throws CoreException {
		super(readerType, rInfo);
		uri = readerType.getURI(rInfo);
	}

	public URL getURL() throws CoreException {
		try {
			return uri.toURL();
		} catch (MalformedURLException e) {
			throw BuckminsterException.wrap(e);
		}
	}

	public void innerMaterialize(IPath destination, IProgressMonitor monitor) throws CoreException {
		URL url = getURL();
		File source = FileUtils.getFile(url);
		if (source == null)
			throw new UnsupportedOperationException(Messages.Only_file_protocol_is_supported_at_this_time);

		File destDir = destination.toFile();
		boolean success = false;
		try {
			if (destDir.toURI().toURL().equals(url)) {
				// Component is already where it's supposed to be.
				//
				success = true;
				return;
			}
			FileUtils.deepCopy(source, destDir, ConflictResolution.UPDATE, monitor);
			success = true;
		} catch (MalformedURLException e) {
			throw BuckminsterException.wrap(e);
		} finally {
			if (!success) {
				// Remove any stray stuff. The materialization should be
				// as atomic as possible.
				//
				try {
					FileUtils.deleteRecursive(destDir, new NullProgressMonitor());
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
		}
	}

	protected final URI getURI() {
		return uri;
	}

	@Override
	protected boolean innerExists(String fileName, IProgressMonitor monitor) throws CoreException {
		InputStream input = null;
		try {
			File source = FileUtils.getFile(getURL());
			if (source != null)
				return new File(source, fileName).exists();

			URL fileUrl = new URL(getURL(), fileName);
			input = CorePlugin.getDefault().openCachedURL(fileUrl, getConnectContext(), monitor);
			return true;
		} catch (IOException e) {
			return false;
		} finally {
			IOUtils.close(input);
		}
	}

	@Override
	protected FileHandle innerGetContents(String fileName, IProgressMonitor monitor) throws CoreException, IOException {
		File source = FileUtils.getFile(getURL());
		if (source == null)
			return super.innerGetContents(fileName, monitor);

		monitor.beginTask(null, 5);
		try {
			File file = new File(source, fileName);
			if (!file.isFile())
				throw new FileNotFoundException(file.getAbsolutePath());

			return new FileHandle(fileName, file, false);
		} finally {
			monitor.done();
		}
	}

	@Override
	protected void innerGetMatchingRootFiles(Pattern pattern, List<FileHandle> files, IProgressMonitor monitor) throws CoreException, IOException {
		URL url = getURL();
		File source = FileUtils.getFile(url);
		if (source == null)
			return;

		String[] rootFiles = source.list();
		if (rootFiles == null)
			return;

		for (String rootFile : rootFiles) {
			if (pattern.matcher(rootFile).matches()) {
				File f = new File(source, rootFile);
				if (f.isFile() && f.canRead())
					files.add(new FileHandle(rootFile, f, false));
			}
		}
	}

	@Override
	protected void innerList(List<String> files, IProgressMonitor monitor) throws CoreException {
		URL url = getURL();
		File source = FileUtils.getFile(url);
		if (source == null)
			return;

		File[] rootFiles = source.listFiles();
		if (rootFiles == null)
			return;

		for (File rootFile : rootFiles) {
			String name = rootFile.getName();
			if (rootFile.isDirectory())
				name += "/"; //$NON-NLS-1$
			files.add(name);
		}
	}

	@Override
	protected <T> T innerReadFile(String fileName, IStreamConsumer<T> consumer, IProgressMonitor monitor) throws CoreException, IOException {
		InputStream input = null;
		monitor.beginTask(fileName, 2);
		try {
			String fullName;
			File source = FileUtils.getFile(getURL());
			if (source != null) {
				File file = new File(source, fileName);
				input = new FileInputStream(file);
				fullName = file.getAbsolutePath();
			} else {
				URL fileUrl = new URL(getURL(), fileName);
				input = CorePlugin.getDefault().openCachedURL(fileUrl, getConnectContext(), MonitorUtils.subMonitor(monitor, 1));
				fullName = fileUrl.toString();
			}
			input = new BufferedInputStream(input);
			return consumer.consumeStream(this, fullName, input, MonitorUtils.subMonitor(monitor, 1));
		} finally {
			IOUtils.close(input);
			monitor.done();
		}
	}
}
