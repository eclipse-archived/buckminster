package org.eclipse.buckminster.galileo.builder;

import java.io.File;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.equinox.internal.p2.artifact.repository.CompositeArtifactRepository;
import org.eclipse.equinox.internal.p2.core.helpers.FileUtils;
import org.eclipse.equinox.internal.p2.metadata.repository.CompositeMetadataRepository;
import org.eclipse.equinox.internal.provisional.p2.artifact.repository.IArtifactRepository;
import org.eclipse.equinox.internal.provisional.p2.artifact.repository.IArtifactRepositoryManager;
import org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepository;
import org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepositoryManager;
import org.eclipse.equinox.p2.publisher.IPublisherAction;
import org.eclipse.equinox.p2.publisher.IPublisherInfo;
import org.eclipse.equinox.p2.publisher.Publisher;
import org.eclipse.equinox.p2.publisher.PublisherInfo;

@SuppressWarnings("restriction")
public class ExtraRepoGenerator
{
	private final File m_location;

	private final String m_name;

	public ExtraRepoGenerator(File location, String name)
	{
		m_location = location;
		m_name = name;
	}

	public void run(BuildModel bm) throws CoreException
	{
		System.out.println("Starting generation of categories");
		long now = System.currentTimeMillis();

		File extraLocation = new File(m_location, Activator.CATEGORY_REPO_FOLDER);
		FileUtils.deleteAll(extraLocation);

		Map<String, String> properties = new HashMap<String, String>();
		URI globalLocation = m_location.toURI();
		URI locationURI = extraLocation.toURI();
		Buckminster bucky = Buckminster.getDefault();

		IMetadataRepositoryManager mdrMgr = bucky.getService(IMetadataRepositoryManager.class);
		IArtifactRepositoryManager arMgr = bucky.getService(IArtifactRepositoryManager.class);
		try
		{
			mdrMgr.removeRepository(locationURI);
			IMetadataRepository mdr = mdrMgr.createRepository(locationURI, m_name, Activator.SIMPLE_METADATA_TYPE,
					properties);

			arMgr.removeRepository(locationURI);
			IArtifactRepository ar = arMgr.createRepository(locationURI,
					m_name + " artifacts", Activator.SIMPLE_ARTIFACTS_TYPE, properties); //$NON-NLS-1$

			CompositeMetadataRepository globalMdr = (CompositeMetadataRepository)mdrMgr.loadRepository(globalLocation,
					new NullProgressMonitor());
			CompositeArtifactRepository globalAr = (CompositeArtifactRepository)arMgr.loadRepository(globalLocation,
					new NullProgressMonitor());

			PublisherInfo info = new PublisherInfo();
			info.setArtifactRepository(ar);
			info.setArtifactOptions(IPublisherInfo.A_PUBLISH | IPublisherInfo.A_INDEX);
			info.setMetadataRepository(mdr);
			Publisher publisher = new Publisher(info);
			IStatus result = publisher.publish(createActions(bm, mdr, globalMdr), new NullProgressMonitor());
			if(result.getSeverity() == IStatus.ERROR)
				throw new CoreException(result);

			globalMdr.addChild(mdr.getLocation());
			globalAr.addChild(ar.getLocation());
		}
		finally
		{
			bucky.ungetService(mdrMgr);
			bucky.ungetService(arMgr);
		}
		System.out.println("Done. Took " + (System.currentTimeMillis() - now) + " ms");
	}

	private IPublisherAction[] createActions(BuildModel bm, IMetadataRepository mdr, IMetadataRepository globalMdr)
	{
		return new IPublisherAction[] { new GalileoFeatureAction(bm, mdr), new CategoriesAction(bm, globalMdr) };
	}
}
