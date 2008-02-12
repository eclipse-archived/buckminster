/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.ctype;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.cspec.AbstractResolutionBuilder;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.metadata.model.DepNode;
import org.eclipse.buckminster.core.parser.IParser;
import org.eclipse.buckminster.core.reader.ICatalogReader;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.reader.IFileReader;
import org.eclipse.buckminster.core.reader.IStreamConsumer;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @author Thomas Hallgren
 */
public class BuckminsterCSpecBuilder extends AbstractResolutionBuilder implements IStreamConsumer<CSpec>
{
	public CSpec consumeStream(IComponentReader reader, String streamName, InputStream stream, IProgressMonitor monitor)
	throws CoreException
	{
		IParser<CSpec> cspecParser = CorePlugin.getDefault().getParserFactory().getCSpecParser(true);
		return cspecParser.parse(streamName, stream);
	}

	public synchronized DepNode build(IComponentReader[] readerHandle, boolean forResolutionAidOnly, IProgressMonitor monitor)
	throws CoreException
	{
		monitor.beginTask(null, 2000);
		IComponentReader reader = readerHandle[0];
		try
		{
			CSpec cspec;
			if(reader instanceof ICatalogReader)
				cspec = ((ICatalogReader)reader).readFile(CorePlugin.CSPEC_FILE, this, MonitorUtils.subMonitor(monitor, 1000));
			else
				cspec = ((IFileReader)reader).readFile(this, MonitorUtils.subMonitor(monitor, 1000));
			cspec = this.applyExtensions(cspec, forResolutionAidOnly, reader, MonitorUtils.subMonitor(monitor, 1000));
			return this.createResolution(reader, cspec);
		}
		catch(FileNotFoundException e)
		{
			throw new MissingCSpecSourceException(reader.getProviderMatch());
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

