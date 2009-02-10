/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.pde.tasks;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.mspec.ConflictResolution;
import org.eclipse.buckminster.pde.internal.FeatureModelReader;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.osgi.util.ManifestElement;
import org.eclipse.pde.internal.core.ifeature.IFeatureModel;
import org.eclipse.pde.internal.core.ifeature.IFeaturePlugin;
import org.osgi.framework.BundleException;
import org.osgi.framework.Constants;

/**
 * @author Thomas Hallgren
 * 
 */
@SuppressWarnings("restriction")
public class ConvertSiteToRuntime
{
	private static final String FEATURES_DIR = "features"; //$NON-NLS-1$

	private static final String FEATURE_FILE = "feature.xml"; //$NON-NLS-1$

	private static final String PLUGINS_DIR = "plugins"; //$NON-NLS-1$

	private final File m_productRoot;

	public ConvertSiteToRuntime(File productRoot)
	{
		m_productRoot = productRoot;
	}

	public boolean guessUnpack(File bundleJar) throws CoreException
	{
		try
		{
			JarFile jf = new JarFile(bundleJar);
			try
			{
				Manifest mf = jf.getManifest();
				if(mf == null)
					return false;

				Attributes attrs = mf.getMainAttributes();
				String value = attrs.getValue(Constants.FRAGMENT_HOST);
				if(value != null)
				{
					ManifestElement[] elements = ManifestElement.parseHeader(Constants.FRAGMENT_HOST, value);
					if(elements.length > 0)
					{
						if("org.eclipse.equinox.launcher".equals(elements[0].getValue())) //$NON-NLS-1$
							return true;
					}
				}

				value = attrs.getValue(Constants.BUNDLE_CLASSPATH);
				if(value != null)
				{
					for(ManifestElement elem : ManifestElement.parseHeader(Constants.BUNDLE_CLASSPATH, value))
					{
						if(elem.getValue().equals(".")) //$NON-NLS-1$
							return false;
					}
					return true;
				}
			}
			finally
			{
				jf.close();
			}
		}
		catch(BundleException e)
		{
			throw BuckminsterException.wrap(e);
		}
		catch(IOException e)
		{
			throw BuckminsterException.wrap(e);
		}
		return false;
	}

	public void run() throws CoreException
	{
		IProgressMonitor nullMonitor = new NullProgressMonitor();

		File pluginsDir = new File(m_productRoot, PLUGINS_DIR);
		String[] pluginCandiates = pluginsDir.list();
		if(pluginCandiates == null)
			return;

		File featuresDir = new File(m_productRoot, FEATURES_DIR);
		String[] featureCandiates = featuresDir.list();
		ArrayList<String> pluginsToUnpack = null;
		HashSet<String> seenPlugins = new HashSet<String>();

		if(featureCandiates != null && featureCandiates.length > 0)
		{
			for(String featureCandidate : featureCandiates)
			{
				if(!featureCandidate.endsWith(".jar")) //$NON-NLS-1$
					continue;

				File featureJar = new File(featuresDir, featureCandidate);
				File featureDir = new File(featuresDir, featureCandidate.substring(0, featureCandidate.length() - 4));
				InputStream input = null;
				try
				{
					input = new BufferedInputStream(new FileInputStream(featureJar));
					FileUtils.unzip(input, null, featureDir, ConflictResolution.REPLACE, nullMonitor);
				}
				catch(IOException e)
				{
					throw BuckminsterException.wrap(e);
				}
				finally
				{
					IOUtils.close(input);
					featureJar.delete();
				}
			}

			for(File featureCandidate : featuresDir.listFiles())
			{
				if(!featureCandidate.isDirectory())
					continue;

				InputStream input = null;
				try
				{
					input = new BufferedInputStream(new FileInputStream(new File(featureCandidate, FEATURE_FILE)));
					IFeatureModel feature = FeatureModelReader.readFeatureModel(input);
					for(IFeaturePlugin plugin : feature.getFeature().getPlugins())
					{
						String fullName = plugin.getId() + '_' + plugin.getVersion();
						if(seenPlugins.contains(fullName))
							continue;

						seenPlugins.add(fullName);
						if(plugin.isUnpack())
						{
							if(pluginsToUnpack == null)
								pluginsToUnpack = new ArrayList<String>();
							pluginsToUnpack.add(fullName);
						}
					}
				}
				catch(FileNotFoundException e)
				{
					continue;
				}
			}
		}

		for(String pluginCandidate : pluginCandiates)
		{
			if(!pluginCandidate.endsWith(".jar")) //$NON-NLS-1$
				continue;

			String fullName = pluginCandidate.substring(0, pluginCandidate.length() - 4);
			if(seenPlugins.contains(fullName))
				continue;

			seenPlugins.add(fullName);
			File pluginJar = new File(pluginsDir, pluginCandidate);
			if(guessUnpack(pluginJar))
			{
				if(pluginsToUnpack == null)
					pluginsToUnpack = new ArrayList<String>();
				pluginsToUnpack.add(fullName);
			}
		}

		if(pluginsToUnpack == null)
			return;

		for(String pluginToUnpack : pluginsToUnpack)
		{
			File pluginJar = new File(pluginsDir, pluginToUnpack + ".jar"); //$NON-NLS-1$
			File pluginDir = new File(pluginsDir, pluginToUnpack);
			InputStream input = null;
			try
			{
				input = new BufferedInputStream(new FileInputStream(pluginJar));
				FileUtils.unzip(input, null, pluginDir, ConflictResolution.REPLACE, nullMonitor);
			}
			catch(FileNotFoundException e)
			{
				continue;
			}
			finally
			{
				IOUtils.close(input);
				pluginJar.delete();
			}
		}
	}
}
