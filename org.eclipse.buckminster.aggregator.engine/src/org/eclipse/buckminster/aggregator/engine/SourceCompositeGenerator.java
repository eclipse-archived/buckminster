package org.eclipse.buckminster.aggregator.engine;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.aggregator.Aggregator;
import org.eclipse.buckminster.aggregator.Contribution;
import org.eclipse.buckminster.aggregator.MappedRepository;
import org.eclipse.buckminster.aggregator.p2.MetadataRepository;
import org.eclipse.buckminster.aggregator.util.ResourceUtils;
import org.eclipse.buckminster.aggregator.util.TimeUtils;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.equinox.internal.p2.core.helpers.FileUtils;
import org.eclipse.equinox.internal.p2.metadata.repository.CompositeMetadataRepository;
import org.eclipse.equinox.internal.p2.metadata.repository.LocalMetadataRepository;
import org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepositoryManager;
import org.eclipse.equinox.internal.provisional.p2.repository.IRepository;

public class SourceCompositeGenerator extends BuilderPhase
{
	public SourceCompositeGenerator(Builder builder)
	{
		super(builder);
	}

	@Override
	public void run(IProgressMonitor monitor) throws CoreException
	{
		Aggregator buildModel = getBuilder().getAggregator();
		List<Contribution> contribs = buildModel.getContributions(true);

		SubMonitor subMon = SubMonitor.convert(monitor, 100 + contribs.size() * 100);
		String info = "Starting generation of composite repository";
		Logger log = Buckminster.getLogger();
		log.info(info);
		subMon.setTaskName("Generating composite from all sources...");

		long start = TimeUtils.getNow();

		String name = getBuilder().getAggregator().getLabel() + " Composite";
		Map<String, String> properties = new HashMap<String, String>();
		properties.put(IRepository.PROP_COMPRESSED, Boolean.toString(true));
		boolean errorsFound = false;

		Buckminster bucky = Buckminster.getDefault();
		IMetadataRepositoryManager mdrMgr = bucky.getService(IMetadataRepositoryManager.class);

		Builder builder = getBuilder();
		URI locationURI = builder.getSourceCompositeURI();
		mdrMgr.removeRepository(locationURI);
		FileUtils.deleteAll(new File(builder.getBuildRoot(), Builder.REPO_FOLDER_INTERIM));

		CompositeMetadataRepository compositeMdr = (CompositeMetadataRepository)mdrMgr.createRepository(locationURI,
				name, Builder.COMPOSITE_METADATA_TYPE, properties);

		MonitorUtils.worked(subMon, 100);
		for(Contribution contrib : contribs)
		{
			SubMonitor contribMonitor = subMon.newChild(100);
			List<MappedRepository> repos = contrib.getRepositories(true);
			MonitorUtils.begin(contribMonitor, repos.size() * 200);
			List<String> errors = new ArrayList<String>();
			for(MappedRepository repo : repos)
			{
				MetadataRepository mdr = ResourceUtils.getMetadataRepository(repo);
				try
				{
					URI childLocation = mdr.getLocation();
					log.info("Adding child meta-data repository %s", childLocation);

					// if the original repository is not p2 compatible, persist its virtual metadata as a local p2
					// repository
					if(!"p2".equals(repo.getNature()))
						childLocation = createLocalMdr(locationURI, mdr).getLocation();
					compositeMdr.addChild(childLocation);
				}
				catch(Exception e)
				{
					String msg = Builder.getExceptionMessages(e);
					errors.add(msg);
					log.error(e, msg);
				}
				contribMonitor.worked(200);
			}
			MonitorUtils.done(contribMonitor);
			if(!errors.isEmpty())
			{
				getBuilder().sendEmail(contrib, errors);
				errorsFound = true;
			}
			getBuilder().setSourceComposite(compositeMdr);
		}
		bucky.ungetService(mdrMgr);
		MonitorUtils.done(subMon);
		log.info("Done. Took %s", TimeUtils.getFormattedDuration(start)); //$NON-NLS-1$
		if(errorsFound)
			throw BuckminsterException.fromMessage("CompositeRepository generation was not successful");
	}

	private IRepository createLocalMdr(URI locationBase, MetadataRepository mdr) throws URISyntaxException,
			NoSuchAlgorithmException
	{
		URI location = new URI(locationBase.toString() + "/transformed/" + encode(mdr.getLocation().toString()));
		LocalMetadataRepository localMdr = new LocalMetadataRepository(location, mdr.getName(), mdr.getProperties());
		localMdr.setDescription(mdr.getDescription());
		localMdr.setProvider(mdr.getProvider());
		localMdr.addInstallableUnits(mdr.getInstallableUnits().toArray(
				new IInstallableUnit[mdr.getInstallableUnits().size()]));
		localMdr.setProperty(IRepository.PROP_COMPRESSED, "true");
		return localMdr;
	}

	private String encode(String location) throws NoSuchAlgorithmException
	{
		MessageDigest digest = MessageDigest.getInstance("MD5");
		StringBuilder encoded = new StringBuilder();
		for(byte b : digest.digest(location.getBytes()))
			encoded.append(String.format("%02x", Byte.valueOf(b)));

		return encoded.toString();
	}
}
