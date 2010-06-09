/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.download.policy;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Properties;
import java.util.UUID;

import org.eclipse.buckminster.download.ICache;
import org.eclipse.buckminster.download.IFetchPolicy;
import org.eclipse.buckminster.download.Messages;
import org.eclipse.buckminster.download.internal.CacheImpl;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.FileInfoBuilder;
import org.eclipse.buckminster.runtime.IFileInfo;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.osgi.util.NLS;

/**
 * @author Thomas Hallgren
 */
public abstract class AbstractFetchPolicy implements IFetchPolicy {
	protected static void safeRename(File sourceFile, File destFile) throws CoreException {
		File toDelete = null;
		if (destFile.exists()) {
			toDelete = new File(destFile.getPath() + ".old"); //$NON-NLS-1$
			if (toDelete.exists())
				toDelete.delete();
			if (!destFile.renameTo(toDelete))
				throw BuckminsterException.fromMessage(NLS.bind(Messages.unable_to_rename_0, destFile));
		}

		if (sourceFile.renameTo(destFile)) {
			if (toDelete != null)
				toDelete.delete();
		} else {
			if (toDelete != null)
				toDelete.renameTo(destFile);
			throw BuckminsterException.fromMessage(NLS.bind(Messages.unable_to_rename_temp_0, destFile));
		}
	}

	private final ICache cache;

	protected AbstractFetchPolicy(ICache cache) {
		this.cache = cache;
	}

	protected ICache getCache() {
		return cache;
	}

	protected File getFileInfoFile(URL url) {
		CacheImpl c = (CacheImpl) getCache();
		File folder = c.getSubFolder(url);
		UUID hash = c.getHash(url.toString());
		return new File(folder, hash.toString() + ".properties"); //$NON-NLS-1$
	}

	protected IFileInfo readLocalFileInfo(URL url) throws CoreException {
		InputStream in = null;
		Properties loadProps = new Properties();
		try {
			in = new BufferedInputStream(new FileInputStream(getFileInfoFile(url)));
			loadProps.load(in);
		} catch (FileNotFoundException e) {
			return null;
		} catch (IOException e) {
			throw BuckminsterException.wrap(e);
		} finally {
			IOUtils.close(in);
		}
		return new FileInfoBuilder(loadProps);
	}

	protected void saveLocalFileInfo(URL url, IFileInfo fileInfo) throws CoreException {
		FileInfoBuilder fiBld = (fileInfo instanceof FileInfoBuilder) ? (FileInfoBuilder) fileInfo : new FileInfoBuilder(fileInfo);
		Properties saveProps = new Properties();
		fiBld.addProperties(saveProps);
		OutputStream out = null;
		try {
			out = new BufferedOutputStream(new FileOutputStream(getFileInfoFile(url)));
			saveProps.store(out, null);
		} catch (IOException e) {
			throw BuckminsterException.wrap(e);
		} finally {
			IOUtils.close(out);
		}
	}
}
