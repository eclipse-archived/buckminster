/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.jnlp.bootstrap;

import java.io.IOException;

import javax.jnlp.DownloadService;
import javax.jnlp.DownloadServiceListener;
import javax.jnlp.ServiceManager;
import javax.jnlp.UnavailableServiceException;

/**
 * This class is supposed to be called as a JNLP application. It pops up a splash and the in will access a resource. The
 * idea is that the resource should be declared for lazy downloading and thus not triggered until someone tries to
 * access it. Since that access happens after the splash has popped up, everything should be done in the right order.
 * 
 * @author Thomas Hallgren
 */
public class Main
{
	// The package of the product.zip must correspond with the package declaration
	// in the product.jnlp file.
	//
	private static final String PRODUCT_INSTALLER_CLASS = "org.eclipse.buckminster.jnlp.product.ProductInstaller";

	private static final String PRODUCT = "product";

	private static final String USER_HOME = "@user.home";

	public static void main(String[] args)
	{
		try
		{
			Main main = new Main();
			main.run(args);
		}
		catch(Throwable t)
		{
			t.printStackTrace();
		}
	}

	String getWorkspaceDir() throws IOException
	{
		String workspaceDir = System.getProperty("osgi.instance.area", null);
		if(workspaceDir != null)
		{
			if(workspaceDir.startsWith(USER_HOME))
				workspaceDir = System.getProperty("user.home") + workspaceDir.substring(USER_HOME.length());
		}
		return workspaceDir;
	}

	void installProduct() throws IOException, UnavailableServiceException
	{
	}

	void run(String[] args)
	{
		showSplash();
		try
		{
			DownloadService ds = (DownloadService)ServiceManager.lookup("javax.jnlp.DownloadService");
			DownloadServiceListener dsl = ds.getDefaultProgressWindow();
			if(!ds.isPartCached(PRODUCT))
				ds.loadPart(PRODUCT, dsl);
			
			Class<?> installerClass = Class.forName(PRODUCT_INSTALLER_CLASS);
			IProductInstaller installer = (IProductInstaller)installerClass.newInstance();
			installer.installProduct();
			installer.startProduct(args);
		}
		catch(Throwable t)
		{
			t.printStackTrace();
		}
		finally
		{
			tearDownSplash();
		}
	}

	private void showSplash()
	{

	}

	private void tearDownSplash()
	{

	}
}
