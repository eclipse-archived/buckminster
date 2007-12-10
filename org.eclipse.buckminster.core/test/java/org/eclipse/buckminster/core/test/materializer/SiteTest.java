package org.eclipse.buckminster.core.test.materializer;

import java.net.URL;

import junit.framework.TestCase;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.update.core.ISite;
import org.eclipse.update.core.SiteManager;

public class SiteTest extends TestCase
{
	public void testSite() throws Exception
	{
		IProgressMonitor nullMon = new NullProgressMonitor();
		ISite site = SiteManager.getSite(new URL("file:/c:/Buckminster/packaging/buckminster"), true, nullMon);
		System.out.println(site.getClass());
	}
}
