/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.resolver;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.core.parser.IParser;
import org.eclipse.buckminster.core.parser.IParserFactory;
import org.eclipse.buckminster.core.rmap.model.ResourceMap;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.core.runtime.CoreException;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 */
public abstract class ResourceMapCache
{
	private static final HashMap<URL,ResourceMap> s_rmapCache = new HashMap<URL, ResourceMap>();

	public static synchronized void clearCache()
	{
		s_rmapCache.clear();
	}

	public static synchronized ResourceMap getResourceMap(URL url) throws CoreException
	{
		ResourceMap rmap = s_rmapCache.get(url);
		if(rmap != null)
			return rmap;

		InputStream input = null;
		try
		{
			input = URLUtils.openStream(url, null);
			IParserFactory pf = CorePlugin.getDefault().getParserFactory();
			IParser<ResourceMap> rmapParser = pf.getResourceMapParser(true);
			rmap = rmapParser.parse(url.toString(), new BufferedInputStream(input));
			s_rmapCache.put(url, rmap);
			return rmap;
		}
		catch(SAXException e)
		{
			throw BuckminsterException.wrap(e);
		}
		catch(IOException e)
		{
			throw BuckminsterException.wrap(e);
		}
		finally
		{
			IOUtils.close(input);
		}
	}
}
