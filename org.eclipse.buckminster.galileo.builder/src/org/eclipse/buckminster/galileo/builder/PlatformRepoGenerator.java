package org.eclipse.buckminster.galileo.builder;

import java.io.File;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.equinox.internal.p2.core.helpers.FileUtils;
import org.eclipse.equinox.internal.p2.metadata.repository.CompositeMetadataRepository;
import org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepository;
import org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepositoryManager;
import org.eclipse.equinox.p2.publisher.IPublisherAction;
import org.eclipse.equinox.p2.publisher.Publisher;
import org.eclipse.equinox.p2.publisher.PublisherInfo;
import org.eclipse.equinox.p2.publisher.actions.JREAction;
import org.eclipse.equinox.p2.publisher.eclipse.BundlesAction;
import org.eclipse.equinox.p2.publisher.eclipse.FeaturesAction;

@SuppressWarnings("restriction")
public class PlatformRepoGenerator
{
	private final File m_repoLocation;

	private final File m_targetPlatformLocation;

	private static final String PROP_TARGET_PLATFORM_URI = "target.platform.uri"; //$NON-NLS-1$

	private static final String[][] PLATFORM_MAPPING_RULES = { //
	{ "(& (classifier=osgi.bundle))", "${target.platform.uri}plugins/${id}_${version}.jar" }, //$NON-NLS-1$//$NON-NLS-2$
			{ "(& (classifier=binary))", "${target.platform.uri}binary/${id}_${version}" }, //$NON-NLS-1$ //$NON-NLS-2$
			{ "(& (classifier=org.eclipse.update.feature))", "${target.platform.uri}features/${id}_${version}.jar" } }; //$NON-NLS-1$//$NON-NLS-2$

	public PlatformRepoGenerator(File repoLocation, File targetPlatformLocation)
	{
		m_repoLocation = repoLocation;
		m_targetPlatformLocation = targetPlatformLocation;
	}

	public void run(IProgressMonitor monitor) throws CoreException
	{
		System.out.println("Starting generation of platform repository");
		long now = System.currentTimeMillis();

		File extraLocation = new File(m_repoLocation, Activator.PLATFORM_REPO_FOLDER);
		FileUtils.deleteAll(extraLocation);

		Map<String, String> properties = new HashMap<String, String>();
		URI locationURI = extraLocation.toURI();
		Buckminster bucky = Buckminster.getDefault();

		IMetadataRepositoryManager mdrMgr = bucky.getService(IMetadataRepositoryManager.class);
		try
		{
			mdrMgr.removeRepository(locationURI);
			IMetadataRepository mdr = mdrMgr.createRepository(locationURI, Activator.PLATFORM_REPO_NAME,
					Activator.SIMPLE_METADATA_TYPE, properties);

			CompositeMetadataRepository globalMdr = (CompositeMetadataRepository)mdrMgr.loadRepository(
					m_repoLocation.toURI(), new NullProgressMonitor());

			PublisherInfo info = new PublisherInfo();
			info.setMetadataRepository(mdr);
			Publisher publisher = new Publisher(info);
			IStatus result = publisher.publish(createActions(), monitor);
			if(result.getSeverity() == IStatus.ERROR)
				throw new CoreException(result);

			globalMdr.addChild(mdr.getLocation());
		}
		finally
		{
			bucky.ungetService(mdrMgr);
		}
		System.out.println("Done. Took " + (System.currentTimeMillis() - now) + " ms");
	}

	private IPublisherAction[] createActions()
	{
		return new IPublisherAction[] { new JREAction(new File(System.getProperty("java.home"))),
				new FeaturesAction(new File[] { new File(m_targetPlatformLocation, "features") }),
				new BundlesAction(new File[] { new File(m_targetPlatformLocation, "plugins") }) };
	}
}
