/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.metadata;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.parser.IParser;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.reader.IStreamConsumer;
import org.eclipse.buckminster.opml.model.OPML;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @author Thomas Hallgren
 */
public class OPMLConsumer implements IStreamConsumer<OPML>
{
	public OPML consumeStream(IComponentReader reader, String streamName, InputStream stream, IProgressMonitor monitor)
			throws CoreException, IOException
	{
		IParser<OPML> opmlParser = CorePlugin.getDefault().getParserFactory().getOPMLParser(true);
		return opmlParser.parse(streamName, stream);
	}
}
