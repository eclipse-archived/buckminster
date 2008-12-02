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
 *         Listener for SimpleJNLPCache actions
 */
public interface ISimpleJNLPCacheListener
{
	/**
	 * This method is called when the cache check/update is finished.
	 * 
	 * @param jnlp
	 */
	public void finished(URL jnlp);

	/**
	 * This method is called when the cache is initializing. It typically starts downloading the JNLP file and checks if
	 * it's up-to-date or not.
	 * 
	 * @param jnlp
	 */
	public void initializing(URL jnlp);

	/**
	 * This method is called when the downloaded JNLP files is considered as obsolete and download of its resources
	 * starts.
	 * 
	 * @param jnlp
	 */
	public void updateStarted(URL jnlp);
}
