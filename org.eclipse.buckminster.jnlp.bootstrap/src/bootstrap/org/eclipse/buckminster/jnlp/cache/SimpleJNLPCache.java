/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.jnlp.cache;

import static org.eclipse.buckminster.jnlp.bootstrap.BootstrapConstants.ERROR_CODE_FILE_IO_EXCEPTION;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.buckminster.jnlp.bootstrap.BootstrapConstants;
import org.eclipse.buckminster.jnlp.bootstrap.JNLPException;
import org.eclipse.buckminster.jnlp.bootstrap.Messages;
import org.eclipse.buckminster.jnlp.bootstrap.OperationCanceledException;
import org.w3c.dom.DOMException;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author Filip Hrbek
 * 
 * An alternative and simplified implementation of JNLP cache.
 * <br><br>
 * It's main purpose is to parse a JNLP specified by an URL and possibly download its referenced resources.
 * If the JNLP is from the same location and the timestamp is equal to the cached one, no action is performed
 * even if the content of the JNLP file has changed. This enables using random mirrors inside the JNLP file.
 * To force updating resources referred to a JNLP file, the JNLP timestamp must change or the resources must
 * be deleted from cache first.
 * <br>
 * The cache is safe to be used in concurrent environment. If two different processes materialize the same
 * JNLP, no conflict should occur, however the stuff will be cached twice. Following registrations of the JNLP
 * will delete obsolete stuff from the cache.
 * <br>
 * Description:
 * <br>
 * <pre>
 * CACHE ROOT
 * |
 * +[MD5 hash for the URL]
 * |
 * +jnlptimestamp.entrytimestamp
 *      |
 *      +jars
 *          |
 *          +00000000001.jar
 *          +00000000002.jar
 *      +resources
 * +jnlptimestamp.entrytimestamp
 * +jnlptimestamp.entrytimestamp.temp
 * </pre>
 * 
 * How registration of a JNLP to cache works:<br>
 *<br>
 *  1) Obtain connection headers and contents<br>
 *  2) Create MD5 hash for the URL<br>
 *  3) Find all folders in the URL's cache root<br>
 *  4) Select the latest non-temp folder<br>
 *  5) Delete recursively all non-latest folders older than a threshold of 1 day (if an exception is thrown, ignore it)<br>
 *  6) If the latest folder timestamp equals to the last modified timestamp, delegate the contents to the class loader and return "no change"<br>
 *  7) Create new folder jnlptimestamp.entrytimestamp.temp<br>
 *  8) Download all resources referenced by the JNLP<br>
 *  9) If everything was downloaded successfully, remove the ".temp" suffix from the folder, or delete it otherwise (if an exception is thrown, ignore it)<br>
 * 10) Delegate the contents to the class loader and return "updated"<br>
 */
public class SimpleJNLPCache
{
	private static final String TEMP_SEGMENT = "temp"; //$NON-NLS-1$

	// Delete everything which is not the latest and is older than 24 hours
	private static final long OBSOLETE_THRESHOLD = 86400000;

	private static final String CORRUPTED_DOWNLOAD_FOLDER = "corrupted.download"; //$NON-NLS-1$
	
	private final SimpleJNLPCacheClassLoader m_classLoader;

	private final File m_location;
	
	private List<ISimpleJNLPCacheListener> m_listeners;
	
	private File m_latestFile = null;

	public SimpleJNLPCache(File location)
	{
		m_location = location;
		m_classLoader = new SimpleJNLPCacheClassLoader(new URL[0], getClass().getClassLoader());
		m_listeners = new ArrayList<ISimpleJNLPCacheListener>();
	}

	public boolean registerJNLP(URL jnlp, IDownloadMonitor progress) throws JNLPException, DOMException, OperationCanceledException
	{
		boolean updated = false;
		
		for (ISimpleJNLPCacheListener listener : m_listeners)
			listener.initializing(jnlp);

		JNLPResource resource = new JNLPResource(jnlp);
		File jnlpCacheBase = new File(m_location, Utils.createHash(jnlp));

		jnlpCacheBase.mkdirs();

		if(!jnlpCacheBase.isDirectory())
			throw new JNLPException(Messages.getString("unable_to_create_a_cache_entry_for") + jnlp.toString(), //$NON-NLS-1$
					Messages.getString("check_your_access_permissions"), BootstrapConstants.ERROR_CODE_DIRECTORY_EXCEPTION); //$NON-NLS-1$

		long currentTimestatmp = new Date().getTime();
		long thresholdTimestamp = currentTimestatmp - OBSOLETE_THRESHOLD;
		long latestTimestamp = 0;
		File latestFile = null;
		List<File> obsoleteCandidates = new ArrayList<File>();

		for(File file : jnlpCacheBase.listFiles())
		{
			String[] segments = file.getName().split("\\."); //$NON-NLS-1$

			// Check if the file is a folder and its name is not broken
			if(!file.isDirectory() || segments.length < 2 || segments.length > 3)
				continue;

			try
			{
				if(!(segments.length == 3 && TEMP_SEGMENT.equals(segments[2])))
				{
					long timestamp = Long.parseLong(segments[0]);

					if(timestamp > latestTimestamp)
					{
						// tests if the latest cache is not corrupted
						if(!new File(file, CORRUPTED_DOWNLOAD_FOLDER).exists())
						{
							latestTimestamp = timestamp;
							latestFile = file;
						}
					}
				}

				long entryTimestamp = Long.parseLong(segments[1]);

				if(entryTimestamp < thresholdTimestamp)
					obsoleteCandidates.add(file);
			}
			catch(NumberFormatException e)
			{
				// probably corrupted folder name
			}
		}

		if(latestTimestamp != resource.getLastModified().getTime())
		{
			for (ISimpleJNLPCacheListener listener : m_listeners)
				listener.updateStarted(jnlp);

			updated = true;

			String dirname = Utils.formatDate(resource.getLastModified()) + "." //$NON-NLS-1$
					+ Utils.formatDate(Long.valueOf(currentTimestatmp));
			String tempdirname = dirname + "." + TEMP_SEGMENT; //$NON-NLS-1$
			File tempRoot = new File(jnlpCacheBase, tempdirname);
			File jarDir = new File(tempRoot, "jars"); //$NON-NLS-1$
			File resourceDir = new File(tempRoot, "resources"); //$NON-NLS-1$

			try
			{
				if(!tempRoot.mkdir() || !jarDir.mkdir() || !resourceDir.mkdir())
					throw new JNLPException(Messages.getString("unable_to_create_a_cache_entry_for") + jnlp.toString(), //$NON-NLS-1$
							Messages.getString("check_your_access_permissions"), BootstrapConstants.ERROR_CODE_DIRECTORY_EXCEPTION); //$NON-NLS-1$

				try
				{
					PrintWriter jnlpOutput = new PrintWriter(new File(tempRoot, "cached.jnlp")); //$NON-NLS-1$
					jnlpOutput.print(resource.getContent());
					jnlpOutput.close();
				}
				catch(Throwable e)
				{
					throw new JNLPException(Messages.getString("unable_to_create_a_cache_entry_for") + jnlp.toString() + ": " //$NON-NLS-1$ //$NON-NLS-2$
							+ e.getMessage(), Messages.getString("check_your_access_permissions"), //$NON-NLS-1$
							BootstrapConstants.ERROR_CODE_DIRECTORY_EXCEPTION, e);
				}

				performDownloads(resource, jarDir, resourceDir, progress);

				latestFile = new File(jnlpCacheBase, dirname);

				if(!tempRoot.renameTo(latestFile))
					throw new JNLPException(Messages.getString("unable_to_commit_a_cache_entry_for") + jnlp.toString(), //$NON-NLS-1$
							Messages.getString("check_your_access_permissions"), BootstrapConstants.ERROR_CODE_DIRECTORY_EXCEPTION); //$NON-NLS-1$

			}
			finally
			{
				try
				{
					Utils.deleteRecursive(tempRoot);
				}
				catch(Throwable e)
				{
					// ignore, perhaps something is broken
				}
			}
		}
		else if(latestFile != null)
			obsoleteCandidates.remove(latestFile);

		File jarDir = new File(latestFile, "jars"); //$NON-NLS-1$
		File resourceDir = new File(latestFile, "resources"); //$NON-NLS-1$
		try
		{
			m_classLoader.addUrl(resourceDir.toURI().toURL());

			for(File jarFile : jarDir.listFiles())
			{
				m_classLoader.addUrl(jarFile.toURI().toURL());
			}
		}
		catch(MalformedURLException e)
		{
			throw new JNLPException(Messages.getString("unable_to_delegate_a_cache_entry_to_the_class_loader"), Messages.getString("report_to_vendor"), //$NON-NLS-1$ //$NON-NLS-2$
					BootstrapConstants.ERROR_CODE_DIRECTORY_EXCEPTION);
		}

		for(File file : obsoleteCandidates)
		{
			try
			{
				Utils.deleteRecursive(file);
			}
			catch(Throwable e)
			{
				// ignore, perhaps something is broken
			}
		}

		for (ISimpleJNLPCacheListener listener : m_listeners)
			listener.finished(jnlp);

		m_latestFile = latestFile;
		
		return updated;
	}

	public void removeLatest() throws JNLPException
	{
		if(m_latestFile != null)
		{
			// mark this cache as corrupted - cannot deleted right now, it could be locked by browser
			try
			{
				new File(m_latestFile, CORRUPTED_DOWNLOAD_FOLDER).createNewFile();
			}
			catch(IOException e)
			{
				throw new JNLPException(Messages.getString("can_not_create_a_new_file"), //$NON-NLS-1$
						Messages.getString("check_disk_space_system_permissions_and_try_again"), ERROR_CODE_FILE_IO_EXCEPTION, e); //$NON-NLS-1$
			}
			m_latestFile = null;
		}
	}
	
	public void addListener(ISimpleJNLPCacheListener listener)
	{
		m_listeners.add(listener);
	}

	public void removeListener(ISimpleJNLPCacheListener listener)
	{
		m_listeners.remove(listener);
	}

	private void performDownloads(JNLPResource resource, File jarDir, File resourceDir, IDownloadMonitor progress)
			throws DOMException, JNLPException, OperationCanceledException
	{
		NodeList allJars = resource.getDocument().getElementsByTagName("jar"); //$NON-NLS-1$

		String osName = System.getProperty("os.name"); //$NON-NLS-1$
		String osArch = System.getProperty("os.arch"); //$NON-NLS-1$

		if(osName != null)
			osName = osName.toLowerCase();
		else
			osName = ""; //$NON-NLS-1$
		if(osArch != null)
			osArch = osArch.toLowerCase();
		else
			osArch = ""; //$NON-NLS-1$

		int fileId = 0;

		int len = allJars.getLength();
		for(int i = 0; i < len; i++)
		{
			Node jarNode = allJars.item(i);
			Node resourcesNode = jarNode.getParentNode();

			if(resourcesNode == null || !"resources".equals(resourcesNode.getNodeName())) //$NON-NLS-1$
				continue;

			NamedNodeMap resourcesAttributes = resourcesNode.getAttributes();
			Node osNode = resourcesAttributes.getNamedItem("os"); //$NON-NLS-1$
			if(osNode != null)
			{
				String osReq = osNode.getNodeValue().toLowerCase();
				if(!osName.startsWith(osReq))
					continue;
			}
			Node archNode = resourcesAttributes.getNamedItem("arch"); //$NON-NLS-1$
			if(archNode != null)
			{
				String archReq = archNode.getNodeValue().toLowerCase();
				if(!osArch.startsWith(archReq))
					continue;
			}

			NamedNodeMap jarAttributes = jarNode.getAttributes();
			String fileName = String.format("%010d", Integer.valueOf(fileId++)) + ".jar"; //$NON-NLS-1$ //$NON-NLS-2$
			performDownload(jarDir, jarAttributes.getNamedItem("href").getNodeValue(), fileName, progress); //$NON-NLS-1$
		}
	}

	private void performDownload(File jarDir, String urlString, String fileName, IDownloadMonitor progress)
			throws JNLPException, OperationCanceledException
	{
		URL url = null;

		try
		{
			url = new URL(urlString);
			URLConnection conn = url.openConnection();
			long len = conn.getContentLength();
			InputStream input = conn.getInputStream();
			OutputStream output = new FileOutputStream(new File(jarDir, fileName));
			int count;
			long read = 0;
			byte[] buf = new byte[0x2000];

			while((count = input.read(buf)) >= 0)
			{
				progress.checkCanceled();

				output.write(buf, 0, count);
				read += count;
				progress.progress(url, null, read, len, 0);
			}

			input.close();
			output.close();
		}
		catch(OperationCanceledException e)
		{
			throw e;
		}
		catch(Throwable e)
		{
			progress.downloadFailed(url, null);
			throw new JNLPException(Messages.getString("download_failed_for") + url.toString() + ": " + e.getMessage(), Messages.getString("try_again_later"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					BootstrapConstants.ERROR_CODE_DOWNLOAD_EXCEPTION, e);
		}
	}

	public ClassLoader getClassLoader()
	{
		return m_classLoader;
	}
}
