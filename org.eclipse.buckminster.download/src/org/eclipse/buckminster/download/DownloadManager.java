/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.download;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.eclipse.buckminster.download.internal.CacheImpl;
import org.eclipse.buckminster.download.internal.FileReader;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.FileInfoBuilder;
import org.eclipse.buckminster.runtime.IFileInfo;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ecf.core.security.ConnectContextFactory;
import org.eclipse.ecf.core.security.IConnectContext;
import org.eclipse.osgi.util.NLS;

/**
 * @author Thomas Hallgren
 * 
 */
public class DownloadManager {
	private static ICache instance;

	public static IConnectContext createConnectContext(URL[] urlHandle) throws CoreException {
		URL url = urlHandle[0];
		String userInfo = url.getUserInfo();
		if (userInfo == null || userInfo.length() == 0)
			return null;

		IConnectContext cctx;
		int colonIdx = userInfo.indexOf(':');
		if (colonIdx >= 0) {
			String password = userInfo.substring(colonIdx + 1);
			if (colonIdx == 0)
				cctx = ConnectContextFactory.createPasswordConnectContext(password);
			else
				cctx = ConnectContextFactory.createUsernamePasswordConnectContext(userInfo.substring(0, colonIdx), password);
		} else
			cctx = ConnectContextFactory.createUsernamePasswordConnectContext(userInfo, null);

		try {
			URI uri = url.toURI();
			uri = new URI(uri.getScheme(), null, uri.getHost(), uri.getPort(), uri.getPath(), uri.getQuery(), uri.getFragment());
			urlHandle[0] = uri.toURL();
		} catch (URISyntaxException e) {
			throw BuckminsterException.wrap(e);
		} catch (MalformedURLException e) {
			throw BuckminsterException.wrap(e);
		}
		return cctx;
	}

	public static synchronized ICache getCache() throws CoreException {
		if (instance != null)
			return instance;

		File buckDir = null;
		if (isWindows()) {
			File appData = null;
			String appDataEnv = System.getenv("APPDATA"); //$NON-NLS-1$
			if (appDataEnv != null)
				appData = new File(appDataEnv);
			else {
				String userHome = System.getProperty("user.home"); //$NON-NLS-1$
				if (userHome != null)
					appData = new File(userHome, "Application Data"); //$NON-NLS-1$
			}
			if (appData != null)
				buckDir = new File(appData, "buckminster"); //$NON-NLS-1$
		} else {
			String userHome = System.getProperty("user.home"); //$NON-NLS-1$
			if (userHome != null)
				buckDir = new File(new File(userHome), ".buckminster"); //$NON-NLS-1$
		}
		if (buckDir == null)
			throw BuckminsterException.fromMessage(NLS.bind(Messages.system_property_0_not_set, "user.home")); //$NON-NLS-1$

		instance = new CacheImpl(new File(buckDir, "repository")); //$NON-NLS-1$
		return instance;
	}

	public static boolean isWindows() {
		String os = System.getProperty("os.name"); //$NON-NLS-1$
		return os != null && os.toLowerCase().startsWith("windows"); //$NON-NLS-1$
	}

	public static InputStream read(URL url, IConnectContext cctx) throws CoreException, FileNotFoundException {
		try {
			url = FileLocator.toFileURL(url);
			if ("file".equalsIgnoreCase(url.getProtocol())) { //$NON-NLS-1$
				File file = new File(url.toURI());
				return new FileInputStream(file);
			}
		} catch (FileNotFoundException e) {
			throw e;
		} catch (URISyntaxException e) {
			throw BuckminsterException.wrap(e);
		} catch (IOException e) {
			throw BuckminsterException.wrap(e);
		}

		if (cctx == null) {
			URL[] uh = new URL[] { url };
			cctx = createConnectContext(uh);
			url = uh[0];
		}
		FileReader reader = new FileReader(cctx);
		return reader.read(url);
	}

	public static IFileInfo readInfo(URL url, IConnectContext cctx) throws CoreException, FileNotFoundException {
		if (cctx == null) {
			URL[] uh = new URL[] { url };
			cctx = createConnectContext(uh);
			url = uh[0];
		}
		FileReader reader = new FileReader(cctx);
		return reader.readInfo(url);
	}

	public static IFileInfo readInto(URL url, IConnectContext cctx, OutputStream output, IProgressMonitor monitor) throws CoreException,
			FileNotFoundException {
		try {
			url = FileLocator.toFileURL(url);
			if ("file".equalsIgnoreCase(url.getProtocol())) { //$NON-NLS-1$
				File file = new File(url.toURI());
				InputStream input = null;
				try {
					input = new FileInputStream(file);
					IOUtils.copy(input, output, monitor);
					return new FileInfoBuilder(file);
				} finally {
					IOUtils.close(input);
				}
			}
		} catch (FileNotFoundException e) {
			throw e;
		} catch (URISyntaxException e) {
			throw BuckminsterException.wrap(e);
		} catch (IOException e) {
			throw BuckminsterException.wrap(e);
		}

		if (cctx == null) {
			URL[] uh = new URL[] { url };
			cctx = createConnectContext(uh);
			url = uh[0];
		}
		FileReader reader = new FileReader(cctx);
		reader.readInto(url, output, monitor);
		return reader.getLastFileInfo();
	}
}
