/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp.bootstrap;

import java.io.File;

/**
 * @author Thomas Hallgren
 */
public interface IProductInstaller
{
	void installProduct(Main main, ProgressFacade monitor) throws JNLPException, OperationCanceledException, CorruptedFileException;
	
	public String getApplicationFolder();
	
	public String[] getInstallFolders();
	
	public boolean isInstalled(File installLocation) throws JNLPException;
}
