package org.eclipse.buckminster.aggregator.engine;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.aggregator.Aggregator;
import org.eclipse.buckminster.aggregator.Contribution;
import org.eclipse.buckminster.aggregator.MappedRepository;
import org.eclipse.buckminster.aggregator.p2.MetadataRepository;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.equinox.internal.p2.metadata.repository.CompositeMetadataRepository;
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
		List<Contribution> contribs = buildModel.getContributions();

		MonitorUtils.begin(monitor, 100 + contribs.size() * 100);
		String info = "Starting generation of composite repository";
		Logger log = Buckminster.getLogger();
		log.info(info);
		MonitorUtils.subTask(monitor, info);

		long now = System.currentTimeMillis();

		String name = getBuilder().getAggregator().getLabel() + " Composite";
		Map<String, String> properties = new HashMap<String, String>();
		properties.put(IRepository.PROP_COMPRESSED, Boolean.toString(true));
		boolean errorsFound = false;

		Buckminster bucky = Buckminster.getDefault();
		IMetadataRepositoryManager mdrMgr = bucky.getService(IMetadataRepositoryManager.class);

		URI locationURI = getBuilder().getSourceCompositeURI();
		mdrMgr.removeRepository(locationURI);
		CompositeMetadataRepository compositeMdr = (CompositeMetadataRepository)mdrMgr.createRepository(locationURI,
				name, Builder.COMPOSITE_METADATA_TYPE, properties);

		MonitorUtils.worked(monitor, 100);
		for(Contribution contrib : contribs)
		{
			IProgressMonitor contribMonitor = MonitorUtils.subMonitor(monitor, 100);
			List<MappedRepository> repos = contrib.getRepositories();
			MonitorUtils.begin(contribMonitor, repos.size() * 200);
			List<String> errors = new ArrayList<String>();
			for(MappedRepository repo : repos)
			{
				MetadataRepository mdr = repo.getMetadataRepository();
				try
				{
					URI childLocation = mdr.getLocation();
					log.info("Adding child meta-data repository %s", childLocation);
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
		MonitorUtils.done(monitor);
		log.info("Done. Took %d ms", Long.valueOf(System.currentTimeMillis() - now));
		if(errorsFound)
			throw BuckminsterException.fromMessage("CompositeRepository generation was not succesful");
	}
}
