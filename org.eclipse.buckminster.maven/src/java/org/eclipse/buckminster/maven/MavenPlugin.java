/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.maven;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;

import org.eclipse.buckminster.maven.internal.LocalCache;
import org.eclipse.buckminster.maven.internal.Maven2ReaderType;
import org.eclipse.buckminster.maven.internal.Maven2VersionFinder;
import org.eclipse.buckminster.maven.internal.MavenComponentType;
import org.eclipse.buckminster.runtime.LogAwarePlugin;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ecf.core.security.IConnectContext;
import org.eclipse.equinox.internal.provisional.p2.metadata.Version;
import org.osgi.framework.BundleContext;
import org.w3c.dom.Document;

/**
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public class MavenPlugin extends LogAwarePlugin
{
	public static final String PLUGIN_ID = "org.eclipse.buckminster.maven"; //$NON-NLS-1$

	private static MavenPlugin s_plugin;

	public static Version createVersion(String versionStr) throws CoreException
	{
		return MavenComponentType.createVersion(versionStr);
	}

	public static MavenPlugin getDefault()
	{
		return s_plugin;
	}

	public static Logger getLogger()
	{
		return s_plugin.getBundleLogger();
	}

	public static Document getMetadataDocument(DocumentBuilder docBld, URL url, IConnectContext cctx,
			IProgressMonitor monitor) throws CoreException, FileNotFoundException
	{
		return Maven2ReaderType.getMetadataDocument(docBld, url, new LocalCache(
				Maven2VersionFinder.getDefaultLocalRepoPath()), cctx, monitor);
	}

	public static String getSnapshotVersion(Document doc, String version) throws CoreException
	{
		return Maven2ReaderType.getSnapshotVersion(doc, version);
	}

	public static List<String> getVersions(Document doc)
	{
		return Maven2ReaderType.getVersions(doc);
	}

	public MavenPlugin()
	{
		s_plugin = this;
	}

	@Override
	public void start(BundleContext context) throws Exception
	{
		super.start(context);
	}

	@Override
	public void stop(BundleContext context) throws Exception
	{
		super.stop(context);
		s_plugin = null;
	}
}
