/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.download;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.download.internal.Activator;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.buckminster.runtime.NullOutputStream;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.ecf.core.util.StringUtils;

/**
 * @author Thomas Hallgren
 * @author Guillaume CHATELET
 * 
 */
public class Installer
{
	private static final HashMap<String, Installer> s_decompressorCache = new HashMap<String, Installer>();

	private static final HashMap<String, Installer> s_installerCache = new HashMap<String, Installer>();

	private static final Installer s_plainInstaller = new Installer(null, null);

	private final List<IDecompressor> m_decompressors;

	private final IExpander m_expander;

	private Installer(List<IDecompressor> decompressors, IExpander expander)
	{
		m_decompressors = (decompressors == null)
				? Collections.<IDecompressor> emptyList()
				: decompressors;
		m_expander = expander;
	}

	public static Installer getPlainInstaller()
	{
		return s_plainInstaller;
	}

	public static Installer getInstaller(final String fileName, boolean expand) throws CoreException
	{
		synchronized(s_installerCache)
		{
			Map<String, Installer> cache = expand
					? s_installerCache
					: s_decompressorCache;
			for(Map.Entry<String, Installer> entry : cache.entrySet())
			{
				if(fileName.endsWith(entry.getKey()))
					return entry.getValue();
			}
			IExtensionRegistry extRegistry = Platform.getExtensionRegistry();
			IConfigurationElement[] elems = extRegistry.getConfigurationElementsFor(Activator.DECOMPRESSORS_POINT);
			int idx = elems.length;
			String[][] suffixes = new String[idx][];
			while(--idx >= 0)
				suffixes[idx] = StringUtils.split(elems[idx].getAttribute("suffixes"), ',');

			ArrayList<IDecompressor> decompressorList = null;
			String chewedName = fileName;
			while(chewedName.length() > 0)
			{
				// Find the suffix that matches the most characters at the
				// end of the path
				//
				int matchIdx = -1;
				int matchLen = -1;
				idx = elems.length;
				suffixFound: while(--idx >= 0)
				{
					for(String suffix : suffixes[idx])
					{
						if(suffix.length() > matchLen && chewedName.endsWith(suffix))
						{
							matchLen = suffix.length();
							matchIdx = idx;
							break suffixFound;
						}
					}
				}

				if(matchIdx < 0)
					break;

				if(decompressorList == null)
					decompressorList = new ArrayList<IDecompressor>();

				IConfigurationElement elem = elems[matchIdx];
				decompressorList.add(IDecompressor.class.cast(elem.createExecutableExtension("class")));

				// Strip of suffix managed by this decompressor
				//
				chewedName = chewedName.substring(0, chewedName.length() - matchLen);
			}

			IExpander expander = null;
			if(expand)
			{
				elems = extRegistry.getConfigurationElementsFor(Activator.EXPANDERS_POINT);
				idx = elems.length;
				suffixes = new String[idx][];
				while(--idx >= 0)
					suffixes[idx] = StringUtils.split(elems[idx].getAttribute("suffixes"), ',');

				// Find the suffix that matches the most characters at the
				// end of the path
				//
				int matchIdx = -1;
				int matchLen = -1;
				idx = elems.length;
				suffixFound: while(--idx >= 0)
				{
					for(String suffix : suffixes[idx])
					{
						if(suffix.length() > matchLen && chewedName.endsWith(suffix))
						{
							matchLen = suffix.length();
							matchIdx = idx;
							break suffixFound;
						}
					}
				}

				if(matchIdx >= 0)
				{
					chewedName = chewedName.substring(0, chewedName.length() - matchLen);
					expander = IExpander.class.cast(elems[matchIdx].createExecutableExtension("class"));
				}
			}

			if(decompressorList == null && expander == null)
			{
				int lastDot = fileName.lastIndexOf('.');
				if(lastDot >= 0 && lastDot < fileName.length() - 1 && Character.isLetter(fileName.charAt(lastDot + 1)))
					//
					// Assume that this suffix will never render anything
					// but the plain installer from now on
					//
					cache.put(fileName.substring(lastDot), s_plainInstaller);

				return s_plainInstaller;
			}

			String fullSuffixMatch = fileName.substring(chewedName.length());
			Installer validator = new Installer(decompressorList, expander);
			cache.put(fullSuffixMatch, validator);
			return validator;
		}
	}

	public void validate(File file, IProgressMonitor monitor) throws CoreException
	{
		if(m_decompressors.size() == 0 && m_expander == null)
			return;

		InputStream input = null;
		try
		{
			input = new FileInputStream(file);
			install(input, null, monitor);
		}
		catch(IOException e)
		{
			throw BuckminsterException.wrap(e);
		}
		finally
		{
			IOUtils.close(input);
		}
	}

	public void install(InputStream input, File destination, IProgressMonitor monitor) throws IOException,
			CoreException
	{
		install(input, destination, null, false, monitor);
	}

	public void install(InputStream input, File destination, FileFilter fileFilter, boolean flattenHierarchy,
			IProgressMonitor monitor) throws IOException, CoreException
	{
		int dcCount = m_decompressors.size();
		MonitorUtils.begin(monitor, 100 + dcCount * 100);
		if(dcCount > 0)
		{
			// We will want to close our IDecompressor instances
			// later on but we don't want to close the input that
			// was passed in. So we protect it here.
			//
			input = new FilterInputStream(input)
			{
				@Override
				public void close()
				{
				}
			};
			for(IDecompressor decompressor : m_decompressors)
				input = decompressor.decompress(input, MonitorUtils.subMonitor(monitor, 100));
		}

		try
		{
			IProgressMonitor subMon = MonitorUtils.subMonitor(monitor, 100);
			if(m_expander != null)
			{
				m_expander.setFilter(fileFilter);
				m_expander.setFlattenHierarchy(flattenHierarchy);
				m_expander.expand(input, destination, subMon);
			}
			else
			{
				// Just verify that the stream can be read
				//
				OutputStream output = null;
				try
				{
					if(destination == null)
						output = NullOutputStream.INSTANCE;
					else
					{
						File parentFolder = destination.getParentFile();
						if(!(parentFolder == null || parentFolder.isDirectory() || parentFolder.mkdirs()))
							throw BuckminsterException.fromMessage("Unable to access directory %s", parentFolder);
						output = new FileOutputStream(destination);
					}
					IOUtils.copy(input, output, subMon);
				}
				finally
				{
					IOUtils.close(output);
				}
				MonitorUtils.done(subMon);
			}
		}
		finally
		{
			if(dcCount > 0)
				IOUtils.close(input);
			MonitorUtils.done(monitor);
		}
	}
}
