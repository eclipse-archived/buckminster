/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.jnlp.cache;

import java.net.URL;

/**
 * @author Filip Hrbek
 *
 * A trivial implementation of SipleJNLPCacheListener for convenient use.
 */
public class SimpleJNLPCacheAdapter implements SimpleJNLPCacheListener
{

	public void initializing(URL jnlp)
	{
	}

	public void updateStarted(URL jnlp)
	{
	}

	public void finished(URL jnlp)
	{
	}

}
