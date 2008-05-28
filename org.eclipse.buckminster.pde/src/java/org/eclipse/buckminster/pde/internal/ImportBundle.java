/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.pde.internal;

import java.net.URL;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.ctype.IResolutionBuilder;
import org.eclipse.buckminster.core.metadata.StorageManager;
import org.eclipse.buckminster.core.metadata.model.DepNode;
import org.eclipse.buckminster.core.metadata.model.Materialization;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.query.builder.ComponentQueryBuilder;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.core.resolver.LocalResolver;
import org.eclipse.buckminster.core.resolver.ResolutionContext;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;

/**
 * @author Thomas Hallgren
 */
public class ImportBundle
{
	private final URL m_siteURL;

	private final IPath m_outputDir;

	private final String m_bundleName;

	public ImportBundle(String bundleName, URL siteURL, IPath outputDir)
	{
		m_bundleName = bundleName;
		m_outputDir = outputDir.addTrailingSeparator();
		m_siteURL = siteURL;
	}

	public void execute() throws Exception
	{
		// First we need a component query. It will not really be used in order
		// to resolve, but we have to make it look that way for now.
		//
		ComponentQueryBuilder queryBld = new ComponentQueryBuilder();
		queryBld.setRootRequest(new ComponentRequest(m_bundleName, IComponentType.OSGI_BUNDLE, null));
		queryBld.setPlatformAgnostic(true);
		ResolutionContext context = new ResolutionContext(queryBld.createComponentQuery());

		// Create the provider that will perform the import.
		//
		IComponentType ctype = CorePlugin.getDefault().getComponentType(IComponentType.OSGI_BUNDLE);
		Provider provider = new Provider(IReaderType.ECLIPSE_IMPORT, new String[] { ctype.getId() }, m_siteURL.toString());

		// Next, we need a reader and a Resolution builder in order to create the real resolution
		// from witch we can derive the origin of the component etc.
		//
		IProgressMonitor monitor = new NullProgressMonitor();
		IReaderType rt = provider.getReaderType();
		IComponentReader[] reader = new IComponentReader[1];
		reader[0] = rt.getReader(provider, ctype, context.getRootNodeQuery(), VersionMatch.DEFAULT,
			monitor);
		try
		{
			IResolutionBuilder builder = CorePlugin.getDefault().getResolutionBuilder(
				IResolutionBuilder.PLUGIN2CSPEC);
			DepNode node = builder.build(reader, false, monitor);

			// Materialize the plugin, i.e. import it into the workspace
			//
			reader[0].materialize(m_outputDir, null, null, monitor);

			// Fetch the cspec from the materialized component (it's changed)
			//
			StorageManager sm = StorageManager.getDefault();
			Resolution newRes = LocalResolver.fromPath(m_outputDir, m_bundleName);
			newRes = new Resolution(newRes.getCSpec(), newRes.getOPML(), node.getResolution());
			newRes.store(sm);
			Materialization mat = new Materialization(m_outputDir, newRes.getComponentIdentifier());
			mat.store(sm);
		}
		finally
		{
			reader[0].close();
		}
		rt.postMaterialization(null, monitor);
	}
}
