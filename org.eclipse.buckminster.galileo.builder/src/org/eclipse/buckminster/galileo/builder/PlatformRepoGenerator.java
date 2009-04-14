package org.eclipse.buckminster.galileo.builder;

import java.io.File;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.Logger;
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
public class PlatformRepoGenerator extends BuilderPhase
{
	private final File repoLocation;

	private final File targetPlatformLocation;

	public PlatformRepoGenerator(Builder builder, File repoLocation, File targetPlatformLocation)
	{
		super(builder);
		this.repoLocation = repoLocation;
		this.targetPlatformLocation = targetPlatformLocation;
	}

	@Override
	public void run(IProgressMonitor monitor) throws CoreException
	{
		Logger log = Buckminster.getLogger();
		log.info("Starting generation of platform repository");
		long now = System.currentTimeMillis();

		File targetPlatformRepoLocation = new File(repoLocation, Builder.PLATFORM_REPO_FOLDER);
		FileUtils.deleteAll(targetPlatformRepoLocation);

		Map<String, String> properties = new HashMap<String, String>();
		URI locationURI = Builder.createURI(targetPlatformRepoLocation);
		Buckminster bucky = Buckminster.getDefault();

		IMetadataRepositoryManager mdrMgr = bucky.getService(IMetadataRepositoryManager.class);
		try
		{
			mdrMgr.removeRepository(locationURI);
			IMetadataRepository mdr = mdrMgr.createRepository(locationURI, Builder.PLATFORM_REPO_NAME,
					Builder.SIMPLE_METADATA_TYPE, properties);

			CompositeMetadataRepository globalMdr = (CompositeMetadataRepository)mdrMgr.loadRepository(
					repoLocation.toURI(), new NullProgressMonitor());

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
		getBuilder().setTargetPlatformRepo(locationURI);
		log.info("Done. Took %d ms", Long.valueOf(System.currentTimeMillis() - now));
	}

	private IPublisherAction[] createActions()
	{
		return new IPublisherAction[] { new JREAction(new File(System.getProperty("java.home"))), //$NON-NLS-1$
				new FeaturesAction(new File[] { new File(targetPlatformLocation, "features") }), //$NON-NLS-1$
				new BundlesAction(new File[] { new File(targetPlatformLocation, "plugins") }) }; //$NON-NLS-1$
	}
}
