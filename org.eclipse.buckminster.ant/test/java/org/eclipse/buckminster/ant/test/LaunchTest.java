/*****************************************************************************
 * (c) 2004-2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 ****************************************************************************/

package org.eclipse.buckminster.ant.test;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import junit.framework.TestCase;

import org.eclipse.buckminster.ant.internal.build.AntBuilder;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;

/**
 * @author Thomas Hallgren
 */
public class LaunchTest extends TestCase
{
	// This is the actual ant project.
	//
	private Object m_internalAntActor;
	private IPath m_scriptPath;

	@Override
	public void setUp() throws Exception
	{
		URL buildURL = this.getClass().getResource("build.xml");
		if(buildURL != null)
		{
			buildURL = FileLocator.toFileURL(buildURL);
			if(!"file".equals(buildURL.getProtocol()))
				buildURL = null;
		}
		if(buildURL == null)
			throw new FileNotFoundException("build.xml");

		m_internalAntActor = AntBuilder.createInternalAntBuilder();
		m_scriptPath = FileUtils.getFileAsPath(buildURL);
	}

	public void testProperties() throws Exception
	{
		ArrayList<IPath> paths = new ArrayList<IPath>();
		paths.add(Path.fromPortableString(FileLocator.toFileURL(Platform.getInstallLocation().getURL()).getPath()));
		paths.add(new Path("startup.jar"));
		paths.add(new Path("configuration/config.ini"));
		AntBuilder.invokeInternalAntBuilder(
			m_internalAntActor,
			m_scriptPath,
			new Path("C:\\Home\\thhal"),
			(String[])null,
			Collections.<String,String>singletonMap("test.prop", "first value"),
			Collections.<String,List<IPath>>singletonMap("test.path", paths), null, null);
	}
}
