/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspecext.model.CSpecExtension;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.ctype.IResolutionBuilder;
import org.eclipse.buckminster.core.helpers.AbstractExtension;
import org.eclipse.buckminster.core.metadata.model.DepNode;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.metadata.model.ResolvedNode;
import org.eclipse.buckminster.core.parser.IParser;
import org.eclipse.buckminster.core.reader.ICatalogReader;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.reader.IStreamConsumer;
import org.eclipse.buckminster.opml.model.OPML;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;

/**
 * @author Thomas Hallgren
 */
public abstract class AbstractResolutionBuilder extends AbstractExtension implements IResolutionBuilder
{
	private String m_nature;
	
	private int m_weight = 0;

	public int compareTo(IResolutionBuilder o)
	{
		int ow = o.getWeight();
		return m_weight > ow ? 1 : (m_weight == ow ? 0 : -1);
	}

	@Override
	public void setInitializationData(IConfigurationElement config, String propertyName, Object data)
		throws CoreException
	{
		String tmp = config.getAttribute("weight");
		if (tmp != null)
			m_weight = Integer.parseInt(tmp);
		m_nature = config.getAttribute("nature");
		super.setInitializationData(config, propertyName, data);
	}

	public String getComponentTypeID()
	{
		return null;
	}

	public String getNature()
	{
		return m_nature;
	}

	public int getWeight()
	{
		return m_weight;
	}

	public DepNode createResolution(IComponentReader reader, CSpec cspec, OPML opml) throws CoreException
	{
		return new ResolvedNode(reader.getNodeQuery(), new Resolution(cspec, opml, reader));
	}

	protected CSpec applyExtensions(CSpec cspec, boolean forResolutionAidOnly, IComponentReader reader, IProgressMonitor monitor)
		throws CoreException
	{
		if(!(reader instanceof ICatalogReader))
		{
			MonitorUtils.complete(monitor);
			return cspec;
		}

		ICatalogReader catReader = (ICatalogReader)reader;
		try
		{
			CSpecExtension cspecExt = catReader.readFile(CorePlugin.CSPECEXT_FILE, new IStreamConsumer<CSpecExtension>()
			{
				public CSpecExtension consumeStream(IComponentReader rdr, String streamName, InputStream stream, IProgressMonitor mon)
					throws CoreException
				{
					mon.beginTask(null, 1);
					mon.subTask(streamName);
					try
					{
						IParser<CSpecExtension> cspecExtParser = CorePlugin.getDefault().getParserFactory().getAlterCSpecParser(true);
						CSpecExtension ce = cspecExtParser.parse(streamName, stream);
						MonitorUtils.worked(mon, 1);
						return ce;
					}
					finally
					{
						mon.done();
					}
				}
			}, monitor);

			// The cspec might be incomplete when the forResolutionOnly flag is set so
			// we only patch the top element when that is the case.
			//
			return forResolutionAidOnly
				? cspecExt.alterTopElement(cspec)
				: cspecExt.alterCSpec(cspec);
		}
		catch(FileNotFoundException e)
		{
			return cspec;
		}
		catch(IOException e)
		{
			throw BuckminsterException.wrap(e);
		}
	}

	public static String getMetadataFile(ICatalogReader reader, String prefName, String defaultPath, IProgressMonitor monitor) throws CoreException
	{
		return getMetadataFile(reader.readBuckminsterPreferences(monitor), prefName, defaultPath);
	}

	public static String getMetadataFile(IEclipsePreferences prefs, String prefName, String defaultPath)
	{
		if(prefs == null)
			return defaultPath;

		defaultPath = prefs.get(prefName, defaultPath);
		IPath path = Path.fromPortableString(defaultPath);
		if(!path.isAbsolute())
		{
			String metadataFolder = prefs.get(IComponentType.PREF_METADATA_FOLDER, null);
			if(metadataFolder != null)
				path = Path.fromPortableString(metadataFolder).append(path);
		}
		return path.makeRelative().toPortableString();
	}
}
