/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.jnlp.cache;

import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandlerFactory;

/**
 * @author Filip Hrbek
 *
 * An enhanced URL class loader which publishes the addURL method.
 */
public class SimpleJNLPCacheClassLoader extends URLClassLoader
{

	public SimpleJNLPCacheClassLoader(URL[] urls, ClassLoader parent, URLStreamHandlerFactory factory)
	{
		super(urls, parent, factory);
	}

	public SimpleJNLPCacheClassLoader(URL[] urls, ClassLoader parent)
	{
		super(urls, parent);
	}

	public SimpleJNLPCacheClassLoader(URL[] urls)
	{
		super(urls);
	}

	/**
	 * Published method from URLClassLoader
	 * 
	 * @param url
	 */
	public void addUrl(URL url)
	{
		super.addURL(url);
	}
}
