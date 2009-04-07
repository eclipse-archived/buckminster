package org.eclipse.buckminster.galileo.builder;

import java.io.File;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.buckminster.galileo.builder.BuildModel.Contribution;
import org.eclipse.buckminster.galileo.builder.BuildModel.Repository;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.core.runtime.CoreException;
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

	public void run(BuildModel buildModel) throws CoreException
	{
		System.out.println("Starting generation of composite repository");
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

		for(Contribution contrib : buildModel.getContributions())
			for(Repository repo : contrib.getRepositories())
			{
				URI location = mangleLocation(repo.getLocation());
				mdr.addChild(location);
				ar.addChild(location);
			}
		System.out.println("Done. Took " + (System.currentTimeMillis() - now) + " ms");
	}
}
