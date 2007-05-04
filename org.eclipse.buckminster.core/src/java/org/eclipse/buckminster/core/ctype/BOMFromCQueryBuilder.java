/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.ctype;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.cspec.AbstractResolutionBuilder;
import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.metadata.model.DepNode;
import org.eclipse.buckminster.core.metadata.model.UnresolvedNodeException;
import org.eclipse.buckminster.core.parser.IParser;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.core.reader.ICatalogReader;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.reader.IFileReader;
import org.eclipse.buckminster.core.reader.IStreamConsumer;
import org.eclipse.buckminster.core.resolver.IResolver;
import org.eclipse.buckminster.core.resolver.MainResolver;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.resolver.ResolutionContext;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 */
public class BOMFromCQueryBuilder extends AbstractResolutionBuilder implements
	IStreamConsumer<ComponentQuery>
{
	public ComponentQuery consumeStream(IComponentReader reader, String streamName, InputStream stream,
		IProgressMonitor monitor) throws CoreException
	{
		try
		{
			IParser<ComponentQuery> queryParser = CorePlugin.getDefault().getParserFactory().getComponentQueryParser(
				true);
			return queryParser.parse(streamName, stream);
		}
		catch(SAXException e)
		{
			throw BuckminsterException.wrap(e);
		}
	}

	public synchronized DepNode build(IComponentReader[] readerHandle, IProgressMonitor monitor)
	throws CoreException
	{
		monitor.beginTask(null, 2000);
		try
		{
			ComponentQuery cquery;
			IComponentReader reader = readerHandle[0];

			NodeQuery query = reader.getNodeQuery();
			if(reader instanceof ICatalogReader)
				cquery = ((ICatalogReader)reader).readFile(CorePlugin.CQUERY_FILE, this,
					MonitorUtils.subMonitor(monitor, 200));
			else
				cquery = ((IFileReader)reader).readFile(this, MonitorUtils.subMonitor(monitor, 200));
			reader.close();
			readerHandle[0] = null;

			ResolutionContext newCtx = new ResolutionContext(cquery, query.getResolutionContext());
			IResolver resolver = new MainResolver(newCtx);
			BillOfMaterials bom = resolver.resolve(MonitorUtils.subMonitor(monitor, 1800));
			if(bom.getResolution() == null)
				throw new UnresolvedNodeException(query.getComponentRequest());
			return bom;
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
