/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.helpers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.reader.IStreamConsumer;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @author Thomas Hallgren
 */
public class PropertiesParser implements IStreamConsumer<Map<String,String>>
{
	public static Map<String,String> readProperties(InputStream stream)
	throws IOException
	{
		Properties props = new Properties();
		props.load(stream);
		return new PropertiesWrapper(props);		
	}

	public Map<String,String> consumeStream(IComponentReader reader, String streamName, InputStream stream, IProgressMonitor monitor)
	throws IOException
	{
		monitor.beginTask(streamName, 1);
		try
		{
			return readProperties(stream);
		}
		finally
		{
			MonitorUtils.worked(monitor, 1);
			monitor.done();
		}
	}
}
