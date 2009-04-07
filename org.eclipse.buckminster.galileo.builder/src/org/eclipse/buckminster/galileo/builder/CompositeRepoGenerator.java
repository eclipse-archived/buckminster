package org.eclipse.buckminster.galileo.builder;

import java.io.File;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.amalgam.releng.build.Build;
import org.eclipse.amalgam.releng.build.Contribution;
import org.eclipse.amalgam.releng.build.Repository;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.equinox.internal.p2.artifact.repository.CompositeArtifactRepository;
import org.eclipse.equinox.internal.p2.core.helpers.FileUtils;
import org.eclipse.equinox.internal.p2.metadata.repository.CompositeMetadataRepository;
import org.eclipse.equinox.internal.provisional.p2.artifact.repository.IArtifactRepositoryManager;
import org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepositoryManager;
import org.eclipse.equinox.internal.provisional.p2.repository.IRepository;

@SuppressWarnings("restriction")
public class CompositeRepoGenerator
{
	private static URI mangleLocation(String location)
	{
		if(location.endsWith("/site.xml")) //$NON-NLS-1$
			location = location.substring(0, location.length() - 8);
		return URI.create(location);
	}

	private final File m_location;

	private final String m_name;

	public CompositeRepoGenerator(File location, String name)
	{
		m_location = location;
		m_name = name;
	}

	public void run(Build buildModel, IProgressMonitor monitor) throws CoreException
	{
		Logger log = Buckminster.getLogger();
		log.info("Starting generation of composite repository"); //$NON-NLS-1$
		long now = System.currentTimeMillis();

		FileUtils.deleteAll(m_location);

		Map<String, String> properties = new HashMap<String, String>();
		properties.put(IRepository.PROP_COMPRESSED, Boolean.toString(true));
		URI locationURI = m_location.toURI();
		Buckminster bucky = Buckminster.getDefault();

		IMetadataRepositoryManager mdrMgr = bucky.getService(IMetadataRepositoryManager.class);
		mdrMgr.removeRepository(locationURI);
		CompositeMetadataRepository mdr = (CompositeMetadataRepository)mdrMgr.createRepository(locationURI, m_name,
				Activator.COMPOSITE_METADATA_TYPE, properties);
		bucky.ungetService(mdrMgr);

		IArtifactRepositoryManager arMgr = bucky.getService(IArtifactRepositoryManager.class);
		arMgr.removeRepository(locationURI);
		CompositeArtifactRepository ar = (CompositeArtifactRepository)arMgr.createRepository(locationURI, m_name
				+ " artifacts", Activator.COMPOSITE_ARTIFACTS_TYPE, properties); //$NON-NLS-1$
		bucky.ungetService(arMgr);

		EList<Contribution> contribs = buildModel.getContributions();
		MonitorUtils.begin(monitor, contribs.size() * 100);
		for(Contribution contrib : contribs)
		{
			IProgressMonitor contribMonitor = MonitorUtils.subMonitor(monitor, 100);
			EList<Repository> repos = contrib.getRepositories();
			MonitorUtils.begin(contribMonitor, repos.size() * 200);
			for(Repository repo : repos)
			{
				try
				{
					URI location = mangleLocation(repo.getLocation());

					log.info("Adding child meta-data repository %s", location); //$NON-NLS-1$
					mdrMgr.loadRepository(location, MonitorUtils.subMonitor(contribMonitor, 100));
					mdr.addChild(location);

					log.info("Adding child artifact repository %s", location); //$NON-NLS-1$
					arMgr.loadRepository(location, MonitorUtils.subMonitor(contribMonitor, 100));
					ar.addChild(location);
				}
				catch(Exception e)
				{
					log.error(e, "Failed to read repository at: %s", repo.getLocation()); //$NON-NLS-1$
				}
			}
			MonitorUtils.done(contribMonitor);
		}
		MonitorUtils.done(monitor);
		log.info("Done. Took %d ms", Long.valueOf(System.currentTimeMillis() - now)); //$NON-NLS-1$
	}
}
