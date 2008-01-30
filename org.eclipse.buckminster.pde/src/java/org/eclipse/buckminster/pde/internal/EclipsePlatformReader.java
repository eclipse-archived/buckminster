/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.pde.internal;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.buckminster.core.metadata.MissingComponentException;
import org.eclipse.buckminster.core.reader.AbstractCatalogReader;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.core.reader.IStreamConsumer;
import org.eclipse.buckminster.core.rmap.model.MalformedProviderURIException;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.core.version.VersionFactory;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.pde.core.IModel;
import org.eclipse.pde.core.plugin.IFragmentModel;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.internal.core.ifeature.IFeatureModel;

/**
 * A Reader that knows about features and plugins that are part of an Eclipse
 * installation.
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public class EclipsePlatformReader extends AbstractCatalogReader implements ISiteReader
{
	public enum InstalledType { FEATURE, PLUGIN }

	/**
	 * A File filter and collector that will collect all occurances of a named
	 * component along with its version number.
	 */
	class PluginFilter implements FilenameFilter
	{
		private final ArrayList<IVersion> m_collector;

		private final Pattern m_pattern;

		PluginFilter(String componentName, ArrayList<IVersion> collector)
		{
			m_pattern = Pattern.compile('^' + Pattern.quote(componentName) + "_(.*?)(?:\\.jar)?$");
			m_collector = collector;
		}

		public boolean accept(File directory, String pathname)
		{
			Matcher m = m_pattern.matcher(pathname);
			if(!m.matches())
				return false;
			try
			{
				m_collector.add(VersionFactory.OSGiType.fromString(m.group(1)));
				return true;
			}
			catch(CoreException e)
			{
				return false;
			}
		}
	}
	private final String m_componentName;

	private IModel m_model;

	private final InstalledType m_type;

	public EclipsePlatformReader(IReaderType readerType, ProviderMatch rInfo) throws CoreException
	{
		super(readerType, rInfo);
		String uri = rInfo.getRepositoryURI();
		IPath path = new Path(uri);
		if(path.segmentCount() == 2)
		{
			m_type = InstalledType.valueOf(path.segment(0).toUpperCase());
			if(m_type != null)
			{
				m_componentName = path.segment(1);
				return;
			}
		}
		throw new MalformedProviderURIException(readerType, uri);
	}

	@Override
	public boolean canMaterialize()
	{
		return false;
	}

	public List<IFragmentModel> getFragmentsFor(String pluginId)
	{
		return ((EclipsePlatformReaderType)getReaderType()).getFragmentsFor(pluginId);
	}

	public synchronized IPluginModelBase getPluginModelBase() throws CoreException
	{
		if(m_type != InstalledType.PLUGIN)
			throw new IllegalStateException("Plugin requested from a reader initialized to read Features");

		if(m_model == null)
		{
			m_model = getBestPlugin(getDesiredVersion());
			if(m_model == null)
				throw new MissingComponentException(m_componentName);
		}
		return (IPluginModelBase)m_model;
	}

	public IPluginModelBase getPluginModelBase(String pluginId, String version)
	{
		return EclipsePlatformReaderType.getBestPlugin(pluginId, version);
	}

	public InstalledType getType()
	{
		return m_type;
	}

	/**
	 * This method should never be called. If a user wants to materialize an
	 * installed plugin, that should be done using the import plugin wizard.
	 */
	public void innerMaterialize(IPath destination, IProgressMonitor monitor)
	{
		throw new UnsupportedOperationException("checkout");
	}

	protected String getResolvedFile(String relativeFile, InputStream[] isReturn)
	throws IOException, CoreException
	{
		String installLocation;
		if(m_type == InstalledType.PLUGIN)
		{
			IPluginModelBase model;
			try
			{
				model = getPluginModelBase();
			}
			catch(IllegalStateException e)
			{
				model = null;
			}
			if(model == null)
				throw new FileNotFoundException(relativeFile);
			installLocation = model.getInstallLocation();
		}
		else
		{
			IFeatureModel model = getFeatureModel();
			if(model == null)
				return null;
			installLocation = model.getInstallLocation();
		}

		File modelRoot = new File(installLocation);
		String fileName;
		if(modelRoot.isDirectory())
		{
			File wantedFile = new File(modelRoot, relativeFile);
			fileName = wantedFile.toString();
			if(!wantedFile.exists())
				throw new FileNotFoundException(fileName);
			if(isReturn != null)
				isReturn[0] = new FileInputStream(wantedFile);
		}
		else
		{
			if(!installLocation.endsWith(".jar"))
				throw new FileNotFoundException(modelRoot.toString());

			fileName = installLocation + '!' + relativeFile;

			final JarFile jf = new JarFile(modelRoot);
			JarEntry entry = jf.getJarEntry(relativeFile);
			if(entry == null)
			{
				jf.close();
				throw new FileNotFoundException(fileName);
			}
			if(isReturn == null)
				jf.close();
			else
			{
				// Return a special InputStream that makes sure that the
				// entry and the JarFile that it stems from are both closed
				//
				isReturn[0] = new FilterInputStream(jf.getInputStream(entry))
				{
					@Override
					public void close() throws IOException
					{
						try
						{
							super.close();
						}
						catch(IOException e)
						{}
						jf.close();
					}
				};
			}
		}
		return fileName;
	}

	@Override
	protected boolean innerExists(String fileName, IProgressMonitor monitor) throws CoreException
	{
		try
		{
			getResolvedFile(fileName, null);
			return true;
		}
		catch(FileNotFoundException e)
		{
			return false;
		}
		catch(IOException e)
		{
			throw BuckminsterException.wrap(e);
		}
	}

	@Override
	protected <T> T innerReadFile(String fileName, IStreamConsumer<T> consumer, IProgressMonitor monitor) throws CoreException, IOException
	{
		InputStream input = null;
		try
		{
			InputStream[] isHolder = new InputStream[1];
			String systemId = getResolvedFile(fileName, isHolder);
			input = new BufferedInputStream(isHolder[0]);
			return consumer.consumeStream(this, systemId, input, monitor);
		}
		finally
		{
			IOUtils.close(input);
		}
	}

	public synchronized IFeatureModel getFeatureModel()
	{
		if(m_type != InstalledType.FEATURE)
			return null;

		if(m_model == null)
			m_model = getBestFeature(getDesiredVersion());
		return (IFeatureModel)m_model;
	}

	private IFeatureModel getBestFeature(String desiredVersion)
	{
		return EclipsePlatformReaderType.getBestFeature(m_componentName, desiredVersion);
	}

	private IPluginModelBase getBestPlugin(String desiredVersion)
	{
		return EclipsePlatformReaderType.getBestPlugin(m_componentName, desiredVersion);
	}

	private String getDesiredVersion()
	{
		String desiredVersion = null;
		ProviderMatch vsMatch = getProviderMatch();
		if(vsMatch != null)
		{
			IVersion version = vsMatch.getVersionMatch().getVersion();
			if(version != null)
				desiredVersion = version.toString();
		}
		return desiredVersion;
	}
}
