package org.eclipse.buckminster.galileo.builder;

import java.io.File;
import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.equinox.internal.p2.artifact.repository.RawMirrorRequest;
import org.eclipse.equinox.internal.p2.core.helpers.FileUtils;
import org.eclipse.equinox.internal.provisional.p2.artifact.repository.IArtifactDescriptor;
import org.eclipse.equinox.internal.provisional.p2.artifact.repository.IArtifactRepository;
import org.eclipse.equinox.internal.provisional.p2.artifact.repository.IArtifactRepositoryManager;
import org.eclipse.equinox.internal.provisional.p2.metadata.IArtifactKey;
import org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepository;
import org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepositoryManager;
import org.eclipse.equinox.internal.provisional.p2.query.Collector;
import org.eclipse.equinox.internal.provisional.p2.query.IQueryable;
import org.eclipse.equinox.internal.provisional.p2.query.MatchQuery;
import org.eclipse.equinox.internal.provisional.p2.query.Query;
import org.eclipse.equinox.internal.provisional.p2.repository.ICompositeRepository;
import org.eclipse.equinox.internal.provisional.p2.repository.IRepository;
import org.eclipse.equinox.p2.publisher.Publisher;

@SuppressWarnings("restriction")
public class MirrorGenerator
{
	private static final Query ALL_BUT_CATEGORIES = new MatchQuery()
	{
		@Override
		public boolean isMatch(Object candidate)
		{
			if(candidate instanceof IInstallableUnit)
			{
				IInstallableUnit iu = (IInstallableUnit)candidate;
				return !Boolean.parseBoolean(iu.getProperty(IInstallableUnit.PROP_TYPE_CATEGORY));
			}
			return true;
		}
	};

	private static final Query ONLY_CATEGORIES = new MatchQuery()
	{
		@Override
		public boolean isMatch(Object candidate)
		{
			if(candidate instanceof IInstallableUnit)
			{
				IInstallableUnit iu = (IInstallableUnit)candidate;
				return Boolean.parseBoolean(iu.getProperty(IInstallableUnit.PROP_TYPE_CATEGORY));
			}
			return false;
		}
	};

	@SuppressWarnings("unchecked")
	private static List<URI> getCompositeChildren(IRepository repository)
	{
		return (repository instanceof ICompositeRepository)
				? ((ICompositeRepository)repository).getChildren()
				: Collections.emptyList();
	}

	private static void mirror(IArtifactRepository source, IArtifactRepository dest, IArtifactDescriptor descriptor,
			IProgressMonitor monitor) throws CoreException
	{
		if(dest.contains(descriptor))
			return;

		RawMirrorRequest request = new RawMirrorRequest(descriptor, descriptor, dest);
		request.setSourceRepository(source);
		request.perform(monitor);
		IStatus result = request.getResult();
		if(result.getSeverity() == IStatus.ERROR)
		{
			if(result.getCode() != org.eclipse.equinox.internal.provisional.p2.core.ProvisionException.ARTIFACT_EXISTS)
			{
				throw BuckminsterException.fromMessage(result.getException(),
						"Unable to mirror artifact %s from repository %s: %s", descriptor.getArtifactKey(),
						source.getLocation(), result.getMessage());
			}
		}
	}

	private static void mirror(IArtifactRepository source, IArtifactRepository dest, IProgressMonitor monitor)
	{
		IArtifactKey[] keys = source.getArtifactKeys();
		MonitorUtils.begin(monitor, keys.length * 100);
		for(IArtifactKey key : keys)
		{
			IArtifactDescriptor[] aDescs = source.getArtifactDescriptors(key);
			IProgressMonitor keyMon = MonitorUtils.subMonitor(monitor, 100);
			MonitorUtils.begin(keyMon, aDescs.length);
			try
			{
				for(IArtifactDescriptor desc : source.getArtifactDescriptors(key))
					mirror(source, dest, desc, MonitorUtils.subMonitor(keyMon, 1));
			}
			catch(CoreException e)
			{
				Buckminster.getLogger().error(e, e.getMessage());
			}
			finally
			{
				MonitorUtils.done(keyMon);
			}
		}
		MonitorUtils.done(monitor);
	}

	private static void mirror(Query filter, IQueryable source, IMetadataRepository dest, IProgressMonitor monitor)
	{
		Collector allIUs = source.query(filter, new Collector(), monitor);
		dest.addInstallableUnits((IInstallableUnit[])allIUs.toArray(IInstallableUnit.class));
	}

	private final URI m_source;

	private final File m_dest;

	private final String m_destName;

	public MirrorGenerator(URI source, File dest, String destName)
	{
		m_source = source;
		m_dest = dest;
		m_destName = destName;
	}

	public void run(IProgressMonitor monitor) throws CoreException
	{
		Logger log = Buckminster.getLogger();
		log.info("Starting mirror generation");
		long now = System.currentTimeMillis();

		URI destURI = m_dest.toURI();

		URI categoryRepoURI = URI.create(m_source + Activator.CATEGORY_REPO_FOLDER);
		URI platformRepoURI = URI.create(m_source + Activator.PLATFORM_REPO_FOLDER);

		Buckminster bucky = Buckminster.getDefault();

		IMetadataRepositoryManager mdrMgr = bucky.getService(IMetadataRepositoryManager.class);
		IArtifactRepositoryManager arMgr = bucky.getService(IArtifactRepositoryManager.class);
		MonitorUtils.begin(monitor, 100);
		try
		{
			FileUtils.deleteAll(m_dest);
			mdrMgr.removeRepository(destURI);

			arMgr.removeRepository(destURI);

			Map<String, String> properties = new HashMap<String, String>();
			properties.put(IRepository.PROP_COMPRESSED, Boolean.toString(true));
			properties.put(Publisher.PUBLISH_PACK_FILES_AS_SIBLINGS, Boolean.toString(true));
			IArtifactRepository destAr = arMgr.createRepository(destURI,
					m_destName + " artifacts", Activator.SIMPLE_ARTIFACTS_TYPE, properties); //$NON-NLS-1$

			// Step 1. Mirror all artifacts. This means copying a lot of data. We mirror
			// one repository at a time to get a more informative error in case of failure
			//
			IArtifactRepository sourceAr = arMgr.loadRepository(m_source, MonitorUtils.subMonitor(monitor, 1));
			List<URI> children = getCompositeChildren(sourceAr);
			IProgressMonitor childMonitor = MonitorUtils.subMonitor(monitor, 89);
			MonitorUtils.begin(childMonitor, children.size() * 100);
			for(URI childURI : children)
			{
				if(childURI.equals(categoryRepoURI))
					continue;

				log.info("Mirroring artifacts from from %s", childURI);
				IArtifactRepository child = arMgr.loadRepository(childURI, MonitorUtils.subMonitor(childMonitor, 1));
				mirror(child, destAr, MonitorUtils.subMonitor(childMonitor, 99));
			}
			log.info("Done mirroring artifacts");
			childMonitor.done();

			// Step 2. Mirror the composite but don't include platform nor categories. We
			// mirror one repository at a time to get a more informative error in case of
			// failure
			//
			IMetadataRepository sourceMdr = mdrMgr.loadRepository(m_source, MonitorUtils.subMonitor(monitor, 1));

			properties = new HashMap<String, String>();
			properties.put(IRepository.PROP_COMPRESSED, Boolean.toString(true));
			IMetadataRepository destMdr = mdrMgr.createRepository(destURI, m_destName, Activator.SIMPLE_METADATA_TYPE,
					properties);

			children = getCompositeChildren(sourceMdr);
			childMonitor = MonitorUtils.subMonitor(monitor, 7);
			MonitorUtils.begin(childMonitor, children.size() * 100);
			for(URI childURI : children)
			{
				if(childURI.equals(platformRepoURI) || childURI.equals(categoryRepoURI))
					continue;

				log.info("Mirroring meta-data from from %s", childURI);
				IMetadataRepository child = mdrMgr.loadRepository(childURI, MonitorUtils.subMonitor(childMonitor, 1));
				mirror(ALL_BUT_CATEGORIES, child, destMdr, MonitorUtils.subMonitor(childMonitor, 99));
			}
			log.info("Done mirroring meta-data");
			childMonitor.done();

			// Step 3. Mirror the generated categories but don't include the
			// generated 'include all' feature
			IMetadataRepository categoryRepo = mdrMgr.loadRepository(categoryRepoURI, MonitorUtils.subMonitor(monitor,
					1));
			mirror(ONLY_CATEGORIES, categoryRepo, destMdr, MonitorUtils.subMonitor(monitor, 1));
		}
		finally
		{
			bucky.ungetService(mdrMgr);
			bucky.ungetService(arMgr);
			MonitorUtils.done(monitor);
		}
		log.info("Done. Took %d ms", Long.valueOf(System.currentTimeMillis() - now));
	}
}
