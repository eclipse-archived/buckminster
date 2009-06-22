package org.eclipse.buckminster.aggregator.p2.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.aggregator.p2.ArtifactKey;
import org.eclipse.buckminster.aggregator.p2.Copyright;
import org.eclipse.buckminster.aggregator.p2.InstallableUnit;
import org.eclipse.buckminster.aggregator.p2.License;
import org.eclipse.buckminster.aggregator.p2.MetadataRepository;
import org.eclipse.buckminster.aggregator.p2.P2Factory;
import org.eclipse.buckminster.aggregator.p2.ProvidedCapability;
import org.eclipse.buckminster.aggregator.p2.RequiredCapability;
import org.eclipse.buckminster.aggregator.p2.TouchpointData;
import org.eclipse.buckminster.aggregator.p2.TouchpointInstruction;
import org.eclipse.buckminster.aggregator.p2.TouchpointType;
import org.eclipse.buckminster.aggregator.p2.UpdateDescriptor;
import org.eclipse.buckminster.aggregator.p2.impl.ArtifactKeyImpl;
import org.eclipse.buckminster.aggregator.p2.impl.CopyrightImpl;
import org.eclipse.buckminster.aggregator.p2.impl.InstallableUnitFragmentImpl;
import org.eclipse.buckminster.aggregator.p2.impl.InstallableUnitImpl;
import org.eclipse.buckminster.aggregator.p2.impl.LicenseImpl;
import org.eclipse.buckminster.aggregator.p2.impl.ProvidedCapabilityImpl;
import org.eclipse.buckminster.aggregator.p2.impl.RequiredCapabilityImpl;
import org.eclipse.buckminster.aggregator.p2.impl.TouchpointInstructionImpl;
import org.eclipse.buckminster.aggregator.p2.impl.TouchpointTypeImpl;
import org.eclipse.buckminster.aggregator.p2.impl.UpdateDescriptorImpl;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.equinox.internal.provisional.p2.metadata.IArtifactKey;
import org.eclipse.equinox.internal.provisional.p2.metadata.ICopyright;
import org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnitFragment;
import org.eclipse.equinox.internal.provisional.p2.metadata.ILicense;
import org.eclipse.equinox.internal.provisional.p2.metadata.IProvidedCapability;
import org.eclipse.equinox.internal.provisional.p2.metadata.IRequiredCapability;
import org.eclipse.equinox.internal.provisional.p2.metadata.ITouchpointData;
import org.eclipse.equinox.internal.provisional.p2.metadata.ITouchpointInstruction;
import org.eclipse.equinox.internal.provisional.p2.metadata.ITouchpointType;
import org.eclipse.equinox.internal.provisional.p2.metadata.IUpdateDescriptor;
import org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepository;
import org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepositoryManager;
import org.eclipse.equinox.internal.provisional.p2.query.Collector;
import org.eclipse.equinox.internal.provisional.p2.query.MatchQuery;
import org.eclipse.equinox.internal.provisional.p2.query.Query;

public class MetadataRepositoryResourceImpl extends ResourceImpl
{
	class RepositoryLoaderJob extends Job
	{
		private final MetadataRepository repository;

		private final java.net.URI location;

		public RepositoryLoaderJob(MetadataRepository repository, java.net.URI location)
		{
			super("Repository Loader");
			this.repository = repository;
			this.location = location;
			setUser(true);
			setPriority(Job.SHORT);
		}

		@Override
		protected IStatus run(IProgressMonitor monitor)
		{
			Buckminster bucky = Buckminster.getDefault();
			IMetadataRepositoryManager mdrMgr = null;
			MonitorUtils.begin(monitor, 100);
			try
			{
				mdrMgr = bucky.getService(IMetadataRepositoryManager.class);
				IMetadataRepository repo = mdrMgr.loadRepository(location, MonitorUtils.subMonitor(monitor, 80));
				repository.setName(repo.getName());
				Collector collector = repo.query(QUERY_ALL_IUS, new Collector(), MonitorUtils.subMonitor(monitor, 20));

				@SuppressWarnings("unchecked")
				Iterator<IInstallableUnit> itor = collector.iterator();
				while(itor.hasNext())
					repository.getInstallableUnits().add(importToModel(itor.next()));
				return Status.OK_STATUS;
			}
			catch(CoreException e)
			{
				return e.getStatus();
			}
			finally
			{
				bucky.ungetService(mdrMgr);
				MonitorUtils.done(monitor);
			}
		}
	}

	public static final Query QUERY_ALL_IUS = new MatchQuery()
	{
		@Override
		public boolean isMatch(Object candidate)
		{
			return candidate instanceof IInstallableUnit;
		}
	};

	public MetadataRepositoryResourceImpl(URI uri)
	{
		super(uri);
	}

	private ArtifactKey importToModel(IArtifactKey key)
	{
		if(key == null)
			return null;
		ArtifactKeyImpl mkey = (ArtifactKeyImpl)P2Factory.eINSTANCE.createArtifactKey();
		mkey.setClassifier(key.getClassifier());
		mkey.setId(key.getId());
		mkey.setVersion(key.getVersion());
		return mkey;
	}

	private Copyright importToModel(ICopyright cr)
	{
		if(cr == null)
			return null;
		CopyrightImpl mcr = (CopyrightImpl)P2Factory.eINSTANCE.createCopyright();
		mcr.setBody(cr.getBody());
		mcr.setLocation(cr.getLocation());
		return mcr;
	}

	private InstallableUnit importToModel(IInstallableUnit iu)
	{
		if(iu == null)
			return null;

		P2Factory factory = P2Factory.eINSTANCE;
		InstallableUnitImpl miu;
		if(iu instanceof IInstallableUnitFragment)
		{
			InstallableUnitFragmentImpl miuf = (InstallableUnitFragmentImpl)factory.createInstallableUnitFragment();
			List<RequiredCapability> mhosts = miuf.getHostList();
			for(IRequiredCapability host : ((IInstallableUnitFragment)iu).getHost())
				mhosts.add(importToModel(host));
			miu = miuf;
		}
		else
			miu = (InstallableUnitImpl)factory.createInstallableUnit();

		miu.setCopyright(importToModel(iu.getCopyright()));
		miu.setFilter(iu.getFilter());
		miu.setFragment(iu.isFragment());
		miu.setId(iu.getId());
		miu.setLicense(importToModel(iu.getLicense()));
		miu.setResolved(iu.isResolved());
		miu.setSingleton(iu.isSingleton());
		miu.setTouchpointType(importToModel(iu.getTouchpointType()));
		miu.setUpdateDescriptor(importToModel(iu.getUpdateDescriptor()));
		miu.setVersion(iu.getVersion());

		@SuppressWarnings("unchecked")
		Map<String, String> props = iu.getProperties();
		if(props.size() > 0)
			miu.getPropertyMap().putAll(props);

		List<ArtifactKey> keys = miu.getArtifactList();
		for(IArtifactKey key : iu.getArtifacts())
			keys.add(importToModel(key));

		List<RequiredCapability> rcs = miu.getMetaRequiredCapabilityList();
		for(IRequiredCapability rc : iu.getMetaRequiredCapabilities())
			rcs.add(importToModel(rc));

		rcs = miu.getRequiredCapabilityList();
		for(IRequiredCapability rc : iu.getRequiredCapabilities())
			rcs.add(importToModel(rc));

		List<ProvidedCapability> pcs = miu.getProvidedCapabilityList();
		for(IProvidedCapability pc : iu.getProvidedCapabilities())
			pcs.add(importToModel(pc));

		List<TouchpointData> tds = miu.getTouchpointDataList();
		for(ITouchpointData td : iu.getTouchpointData())
			tds.add(importToModel(td));

		return miu;
	}

	private License importToModel(ILicense lc)
	{
		if(lc == null)
			return null;
		LicenseImpl mlc = (LicenseImpl)P2Factory.eINSTANCE.createLicense();
		mlc.setBody(lc.getBody());
		mlc.setDigest(lc.getDigest());
		mlc.setLocation(lc.getLocation());
		return mlc;
	}

	private ProvidedCapability importToModel(IProvidedCapability pc)
	{
		if(pc == null)
			return null;
		ProvidedCapabilityImpl mrq = (ProvidedCapabilityImpl)P2Factory.eINSTANCE.createProvidedCapability();
		mrq.setName(pc.getName());
		mrq.setNamespace(pc.getNamespace());
		mrq.setVersion(pc.getVersion());
		return mrq;
	}

	private RequiredCapability importToModel(IRequiredCapability rc)
	{
		if(rc == null)
			return null;
		RequiredCapabilityImpl mrc = (RequiredCapabilityImpl)P2Factory.eINSTANCE.createRequiredCapability();
		mrc.setFilter(rc.getFilter());
		mrc.setGreedy(rc.isGreedy());
		mrc.setMultiple(rc.isMultiple());
		mrc.setName(rc.getName());
		mrc.setNamespace(rc.getNamespace());
		mrc.setOptional(rc.isOptional());
		mrc.setRange(rc.getRange());
		mrc.setSelectors(rc.getSelectors());
		return mrc;
	}

	private TouchpointData importToModel(ITouchpointData ptd)
	{
		if(ptd == null)
			return null;
		TouchpointData mtpd = P2Factory.eINSTANCE.createTouchpointData();
		EMap<String, TouchpointInstruction> minstrMap = mtpd.getInstructionMap();
		@SuppressWarnings("unchecked")
		Map<String, ITouchpointInstruction> instrMap = ptd.getInstructions();
		for(Map.Entry<String, ITouchpointInstruction> instr : instrMap.entrySet())
			minstrMap.put(instr.getKey(), importToModel(instr.getValue()));
		return mtpd;
	}

	private TouchpointInstruction importToModel(ITouchpointInstruction ti)
	{
		if(ti == null)
			return null;
		TouchpointInstructionImpl mti = (TouchpointInstructionImpl)P2Factory.eINSTANCE.createTouchpointInstruction();
		mti.setBody(ti.getBody());
		mti.setImportAttribute(ti.getImportAttribute());
		return mti;
	}

	private TouchpointType importToModel(ITouchpointType tpt)
	{
		if(tpt == null)
			return null;
		TouchpointTypeImpl mtpt = (TouchpointTypeImpl)P2Factory.eINSTANCE.createTouchpointType();
		mtpt.setId(tpt.getId());
		mtpt.setVersion(tpt.getVersion());
		return mtpt;
	}

	private UpdateDescriptor importToModel(IUpdateDescriptor ud)
	{
		if(ud == null)
			return null;
		UpdateDescriptorImpl mud = (UpdateDescriptorImpl)P2Factory.eINSTANCE.createUpdateDescriptor();
		mud.setDescription(ud.getDescription());
		mud.setId(ud.getId());
		mud.setRange(ud.getRange());
		mud.setSeverity(ud.getSeverity());
		return mud;
	}

	public void load(Map<?, ?> options) throws IOException
	{
		if(isLoaded)
			return;

		Notification notification = setLoaded(true);
		java.net.URI location;
		try
		{
			location = new java.net.URI(getURI().opaquePart());
		}
		catch(URISyntaxException e1)
		{
			throw new IOException(e1.getMessage());
		}

		isLoading = true;
		if(errors != null)
		{
			errors.clear();
		}

		if(warnings != null)
		{
			warnings.clear();
		}

		MetadataRepository repository = P2Factory.eINSTANCE.createMetadataRepository();
		repository.setLocation(location);
		getContents().add(repository);

		RepositoryLoaderJob job = new RepositoryLoaderJob(repository, location);
		job.schedule();
		try
		{
			job.join();
		}
		catch(InterruptedException e)
		{
		}
		finally
		{
			isLoading = false;
			if(notification != null)
				eNotify(notification);
			setModified(false);
		}
	}

	public void save(Map<?, ?> options) throws IOException
	{
		// Let this be a no-op.
	}
}
