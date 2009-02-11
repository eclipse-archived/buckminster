package org.eclipse.buckminster.core.materializer;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.cspec.IComponentIdentifier;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.metadata.model.Materialization;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.mspec.IMaterializationNode;
import org.eclipse.buckminster.core.mspec.IMaterializationSpec;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.equinox.internal.provisional.p2.artifact.repository.IArtifactRepository;
import org.eclipse.equinox.internal.provisional.p2.artifact.repository.IArtifactRepositoryManager;
import org.eclipse.equinox.internal.provisional.p2.core.ProvisionException;
import org.eclipse.equinox.internal.provisional.p2.core.Version;
import org.eclipse.equinox.internal.provisional.p2.core.VersionRange;
import org.eclipse.equinox.internal.provisional.p2.engine.IEngine;
import org.eclipse.equinox.internal.provisional.p2.engine.IProfile;
import org.eclipse.equinox.internal.provisional.p2.engine.IProfileRegistry;
import org.eclipse.equinox.internal.provisional.p2.engine.InstallableUnitOperand;
import org.eclipse.equinox.internal.provisional.p2.engine.Phase;
import org.eclipse.equinox.internal.provisional.p2.engine.PhaseSet;
import org.eclipse.equinox.internal.provisional.p2.engine.ProvisioningContext;
import org.eclipse.equinox.internal.provisional.p2.engine.phases.Collect;
import org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.internal.provisional.p2.metadata.query.InstallableUnitQuery;
import org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepository;
import org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepositoryManager;
import org.eclipse.equinox.internal.provisional.p2.query.Collector;
import org.eclipse.osgi.util.NLS;

@SuppressWarnings("restriction")
public class P2Materializer extends AbstractMaterializer
{
	static IArtifactRepository getArtifactRepository(IArtifactRepositoryManager manager, URI repoLocation,
			IProgressMonitor monitor) throws CoreException
	{
		SubMonitor subMon = SubMonitor.convert(monitor, 200);
		try
		{
			return manager.loadRepository(repoLocation, subMon.newChild(100));
		}
		catch(ProvisionException e)
		{
			return manager.refreshRepository(repoLocation, subMon.newChild(100));
		}
	}

	static IMetadataRepository getMetadataRepository(IMetadataRepositoryManager manager, URI repoLocation,
			IProgressMonitor monitor) throws CoreException
	{
		SubMonitor subMon = SubMonitor.convert(monitor, 200);
		try
		{
			return manager.loadRepository(repoLocation, subMon.newChild(100));
		}
		catch(ProvisionException e)
		{
			return manager.refreshRepository(repoLocation, subMon.newChild(100));
		}
	}

	private static URI cleanURIFromImportType(URI repoLocation)
	{
		Map<String, String> props = URLUtils.queryAsParameters(repoLocation.getQuery());
		if(props.remove("importType") != null) //$NON-NLS-1$
			try
			{
				repoLocation = new URI(repoLocation.getScheme(), repoLocation.getAuthority(), repoLocation.getPath(),
						URLUtils.encodeFromQueryPairs(props), repoLocation.getFragment());
			}
			catch(URISyntaxException e)
			{
				throw new IllegalArgumentException(e);
			}
		return repoLocation;
	}

	@Override
	public boolean canWorkInParallel()
	{
		// Since we start and stop services
		//
		return false;
	}

	@Override
	public String getMaterializerRootDir()
	{
		return Platform.getLocation().toOSString();
	}

	public List<Materialization> materialize(List<Resolution> resolutions, MaterializationContext context,
			IProgressMonitor monitor) throws CoreException
	{
		Map<IPath, List<Resolution>> resPerLocation = new HashMap<IPath, List<Resolution>>();
		IMaterializationSpec mspec = context.getMaterializationSpec();

		IPath installRoot = mspec.getInstallLocation();
		if(installRoot == null)
			installRoot = Platform.getLocation();
		else
			installRoot = Path.fromOSString(ExpandingProperties.expand(context, installRoot.toOSString(), 0));

		for(Resolution res : resolutions)
		{
			ComponentIdentifier ci = res.getComponentIdentifier();
			IMaterializationNode node = mspec.getMatchingNode(ci);
			IPath installLocation = null;
			Map<String, ? extends Object> props = context.getProperties(ci);
			if(node != null)
			{
				installLocation = node.getInstallLocation();
				if(installLocation != null)
				{
					installLocation = Path.fromOSString(ExpandingProperties.expand(props, installLocation.toOSString(),
							0));
					if(!installLocation.isAbsolute())
						installLocation = installRoot.append(installLocation);
				}
			}

			if(installLocation == null)
				installLocation = installRoot;

			List<Resolution> rss = resPerLocation.get(installLocation);
			if(rss == null)
			{
				rss = new ArrayList<Resolution>();
				resPerLocation.put(installLocation, rss);
			}
			rss.add(res);
		}

		CorePlugin bundle = CorePlugin.getDefault();
		SubMonitor subMon = SubMonitor.convert(monitor, resPerLocation.size() * 1000);
		List<URI> mdrsToRemove = null;
		List<URI> arsToRemove = null;
		IMetadataRepositoryManager mdrManager = bundle.getService(IMetadataRepositoryManager.class);
		IArtifactRepositoryManager arManager = bundle.getService(IArtifactRepositoryManager.class);
		IEngine engine = bundle.getService(IEngine.class);
		IProfileRegistry registry = bundle.getService(IProfileRegistry.class);
		Map<URI, IMetadataRepository> knownMDRs = new HashMap<URI, IMetadataRepository>();
		Map<URI, IArtifactRepository> knownARs = new HashMap<URI, IArtifactRepository>();
		try
		{
			for(Map.Entry<IPath, List<Resolution>> entry : resPerLocation.entrySet())
			{
				IPath installLocation = entry.getKey();
				List<Resolution> ress = entry.getValue();
				List<IInstallableUnit> ius = new ArrayList<IInstallableUnit>(ress.size());
				for(Resolution res : entry.getValue())
				{
					SubMonitor subSubMon = subMon.newChild(800 / ress.size());
					subSubMon.setWorkRemaining(1000);
					URI repoURI = cleanURIFromImportType(URI.create(res.getRepository()));
					IMetadataRepository mdr = knownMDRs.get(repoURI);
					if(mdr == null)
					{
						if(mdrManager.contains(repoURI))
						{
							if(mdrsToRemove == null)
								mdrsToRemove = new ArrayList<URI>();
							mdrsToRemove.add(repoURI);
						}
						mdr = getMetadataRepository(mdrManager, repoURI, subSubMon.newChild(500));
						knownMDRs.put(repoURI, mdr);
					}

					IComponentIdentifier cid = res.getComponentIdentifier();
					Version version = new Version(cid.getVersion().toString());
					VersionRange range = new VersionRange(version, true, version, true);
					Collector collector = new Collector();
					String name = cid.getName();
					boolean isFeature = IComponentType.ECLIPSE_FEATURE.equals(cid.getComponentTypeID());

					if(isFeature)
						// Since this is what we want in the target platform
						name = name + ".feature.jar"; //$NON-NLS-1$

					mdr.query(new InstallableUnitQuery(name, range), collector, subSubMon.newChild(250));
					Iterator<?> itor = collector.iterator();
					if(!itor.hasNext())
						throw new ProvisionException(NLS.bind(Messages.Unable_to_resolve_0_1_in_MDR_2, new Object[] {
								cid.getName(), version, res.getRepository() }));

					IInstallableUnit iu = (IInstallableUnit)itor.next();
					ius.add(iu);

					// Check if this IU has artifacts and if so, load the artifact repository
					//
					if(iu.getArtifacts().length > 0)
					{
						IArtifactRepository ar = knownARs.get(repoURI);
						if(ar == null)
						{
							if(arManager.contains(repoURI))
							{
								if(arsToRemove == null)
									arsToRemove = new ArrayList<URI>();
								arsToRemove.add(repoURI);
							}
							ar = getArtifactRepository(arManager, repoURI, subSubMon.newChild(250));
							knownARs.put(repoURI, ar);
						}
					}
					else
						subSubMon.worked(250);
				}

				// create the operands from the list of IUs
				InstallableUnitOperand[] operands = new InstallableUnitOperand[ius.size()];
				int i = 0;
				for(IInstallableUnit iu : ius)
					operands[i++] = new InstallableUnitOperand(null, iu);

				// ensure the user-specified artifact repos will be consulted by loading them

				java.io.File destDir = installLocation.toFile();

				// do a create here to ensure that we don't default to a #load later and grab a repo which is the wrong
				// type
				// e.g. extension location type because a plugins/ directory exists.
				try
				{
					arManager.createRepository(destDir.toURI(), "Runnable repository.", //$NON-NLS-1$
							IArtifactRepositoryManager.TYPE_SIMPLE_REPOSITORY, null);
				}
				catch(ProvisionException e)
				{
					// ignore... perhaps one already exists and we will just load it later
				}

				// call the engine with only the "collect" phase so all we do is download

				String destDirStr = destDir.toString();
				Map<String, String> properties = new HashMap<String, String>();
				properties.put(IProfile.PROP_CACHE, destDirStr);
				properties.put(IProfile.PROP_INSTALL_FOLDER, destDirStr);
				IProfile profile = registry.addProfile(System.currentTimeMillis() + "-" + Math.random(), properties); //$NON-NLS-1$
				try
				{
					PhaseSet phaseSet = new PhaseSet(new Phase[] { new Collect(100) })
					{ /* nothing to override */
					};
					engine = bundle.getService(IEngine.class);
					IStatus status = engine.perform(profile, phaseSet, operands, new ProvisioningContext(), subMon
							.newChild(200));
					if(status.getSeverity() == IStatus.ERROR)
						throw BuckminsterException.wrap(status);
				}
				finally
				{
					registry.removeProfile(profile.getProfileId());
				}
			}
		}
		finally
		{
			if(mdrsToRemove != null)
				for(URI repoLocation : mdrsToRemove)
					mdrManager.removeRepository(repoLocation);

			if(arsToRemove != null)
				for(URI repoLocation : arsToRemove)
					arManager.removeRepository(repoLocation);

			bundle.ungetService(mdrManager);
			bundle.ungetService(arManager);
			bundle.ungetService(registry);
			bundle.ungetService(engine);
		}
		return Collections.emptyList();
	}
}
