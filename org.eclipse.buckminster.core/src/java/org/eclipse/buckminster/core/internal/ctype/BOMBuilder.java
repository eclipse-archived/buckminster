/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.internal.ctype;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.cspec.AbstractResolutionBuilder;
import org.eclipse.buckminster.core.ctype.MissingCSpecSourceException;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.metadata.model.DepNode;
import org.eclipse.buckminster.core.metadata.model.UnresolvedNodeException;
import org.eclipse.buckminster.core.parser.IParser;
import org.eclipse.buckminster.core.reader.ICatalogReader;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.reader.IFileReader;
import org.eclipse.buckminster.core.reader.IStreamConsumer;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;


/**
 * @author Thomas Hallgren
 */
public class BOMBuilder extends AbstractResolutionBuilder implements IStreamConsumer<BillOfMaterials>
{
	public BillOfMaterials consumeStream(IComponentReader reader, String streamName, InputStream stream, IProgressMonitor monitor)
	throws CoreException
	{
		IParser<BillOfMaterials> bomParser = CorePlugin.getDefault().getParserFactory().getBillOfMaterialsParser(true);
		return bomParser.parse(streamName, stream);
	}

	public synchronized DepNode build(IComponentReader[] readerHandle, boolean forResolutionAidOnly, IProgressMonitor monitor)
	throws CoreException
	{
		IComponentReader reader = readerHandle[0];
		try
		{
			BillOfMaterials bom;
			if(reader instanceof ICatalogReader)
				bom = ((ICatalogReader)reader).readFile(CorePlugin.BOM_FILE, this, monitor);
			else
				bom = ((IFileReader)reader).readFile(this, monitor);

			if(bom.getResolution() == null)
				throw new UnresolvedNodeException(reader.getNodeQuery().getComponentRequest());

			return bom;
		}
		catch(FileNotFoundException e)
		{
			throw new MissingCSpecSourceException(reader.getProviderMatch());
		}
		catch(IOException e)
		{
			throw BuckminsterException.wrap(e);
		}
	}
}

