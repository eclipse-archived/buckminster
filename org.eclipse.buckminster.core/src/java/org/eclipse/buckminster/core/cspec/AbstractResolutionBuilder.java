/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspecext.model.CSpecExtension;
import org.eclipse.buckminster.core.ctype.IResolutionBuilder;
import org.eclipse.buckminster.core.helpers.AbstractExtension;
import org.eclipse.buckminster.core.metadata.model.DepNode;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.metadata.model.ResolvedNode;
import org.eclipse.buckminster.core.parser.IParser;
import org.eclipse.buckminster.core.reader.ICatalogReader;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.reader.IStreamConsumer;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.xml.sax.SAXException;

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

	public String getCategory()
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

	public DepNode createResolution(IComponentReader reader, CSpec cspec) throws CoreException
	{
		return new ResolvedNode(reader.getNodeQuery(), new Resolution(cspec, reader));
	}

	protected CSpec applyExtensions(CSpec cspec, IComponentReader reader, IProgressMonitor monitor)
		throws CoreException
	{
		if(!(reader instanceof ICatalogReader))
		{
			MonitorUtils.complete(monitor);
			return cspec;
		}

		ICatalogReader catReader = (ICatalogReader)reader;
		monitor.beginTask(null, 3000);
		try
		{
			if (catReader.exists(CorePlugin.CSPECEXT_FILE, MonitorUtils.subMonitor(monitor, 1000)))
			{
				CSpecExtension cspecExt = catReader.readFile(CorePlugin.CSPECEXT_FILE, new IStreamConsumer<CSpecExtension>()
				{
					public CSpecExtension consumeStream(IComponentReader rdr, String streamName, InputStream stream, IProgressMonitor mon)
						throws CoreException
					{
						try
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
						catch(SAXException e)
						{
							throw BuckminsterException.wrap(e);
						}
					}
				}, MonitorUtils.subMonitor(monitor, 2000));
				cspec = cspecExt.alterCSpec(cspec);
			}
			else
				MonitorUtils.worked(monitor, 2000);

			return cspec;
		}
		catch(IOException e)
		{
			throw BuckminsterException.wrap(e);
		}
		finally
		{
			monitor.done();
		}
	}
}
