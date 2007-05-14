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

import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.pde.internal.FeatureModelReader;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.pde.internal.core.ifeature.IFeatureModel;
import org.eclipse.pde.internal.core.ifeature.IFeaturePlugin;

/**
 * @author Thomas Hallgren
 *
 */
@SuppressWarnings("restriction")
public class ConvertSiteToRuntime
{
	private static final String FEATURES_DIR = "features";
	private static final String FEATURE_FILE = "feature.xml";
	private static final String PLUGINS_DIR = "plugins";

	private final File m_productRoot;
	
	public ConvertSiteToRuntime(File productRoot)
	{
		m_productRoot = productRoot;
	}

	public void run() throws CoreException
	{
		IProgressMonitor nullMonitor = new NullProgressMonitor();
		File featuresDir = new File(m_productRoot, FEATURES_DIR);
		String[] featureCandiates = featuresDir.list();
		if(featureCandiates == null)
			return;

		for(String featureCandidate : featureCandiates)
		{
			if(!featureCandidate.endsWith(".jar"))
				continue;

			File featureJar = new File(featuresDir, featureCandidate);
			File featureDir = new File(featuresDir, featureCandidate.substring(0, featureCandidate.length() - 4));
			InputStream input = null;
			try
			{
				input = new BufferedInputStream(new FileInputStream(featureJar));
				FileUtils.unzip(input, null, featureDir, true, nullMonitor);
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

		ArrayList<String> pluginsToUnpack = null;
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
					if(plugin.isUnpack())
					{
						if(pluginsToUnpack == null)
							pluginsToUnpack = new ArrayList<String>();
						pluginsToUnpack.add(plugin.getId() + '_' + plugin.getVersion());
					}
				}
			}
			catch(FileNotFoundException e)
			{
				continue;
			}
		}

		if(pluginsToUnpack == null)
			return;

		File pluginsDir = new File(m_productRoot, PLUGINS_DIR);
		for(String pluginToUnpack : pluginsToUnpack)
		{
			File pluginJar = new File(pluginsDir, pluginToUnpack + ".jar");
			File pluginDir = new File(pluginsDir, pluginToUnpack);
			InputStream input = null;
			try
			{
				input = new BufferedInputStream(new FileInputStream(pluginJar));
				FileUtils.unzip(input, null, pluginDir, true, nullMonitor);
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
