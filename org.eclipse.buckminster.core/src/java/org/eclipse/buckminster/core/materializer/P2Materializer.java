package org.eclipse.buckminster.core.materializer;

import java.io.File;
import java.io.IOException;
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
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.equinox.internal.p2.metadata.ArtifactKey;
import org.eclipse.equinox.internal.provisional.p2.artifact.repository.ArtifactDescriptor;
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
	private static final String CLASSIFIER_OSGI_BUNDLE = "osgi.bundle"; //$NON-NLS-1$

	private static final String CLASSIFIER_ORG_ECLIPSE_UPDATE_FEATURE = "org.eclipse.update.feature"; //$NON-NLS-1$

	private static final String PROP_ARTIFACT_FOLDER = "artifact.folder"; //$NON-NLS-1$

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

		Buckminster bucky = Buckminster.getDefault();
		SubMonitor subMon = SubMonitor.convert(monitor, resPerLocation.size() * 1000);
		List<URI> mdrsToRemove = null;
		List<URI> arsToRemove = null;
		IMetadataRepositoryManager mdrManager = bucky.getService(IMetadataRepositoryManager.class);
		IArtifactRepositoryManager arManager = bucky.getService(IArtifactRepositoryManager.class);
		IEngine engine = bucky.getService(IEngine.class);
		IProfileRegistry registry = bucky.getService(IProfileRegistry.class);
		Map<URI, IMetadataRepository> knownMDRs = new HashMap<URI, IMetadataRepository>();
		Map<URI, IArtifactRepository> knownARs = new HashMap<URI, IArtifactRepository>();
		try
		{
			for(Map.Entry<IPath, List<Resolution>> entry : resPerLocation.entrySet())
			{
				IPath installLocation = entry.getKey();

				// ensure the user-specified artifact repos will be consulted by loading them

				File destDir = installLocation.toFile();

				// do a create here to ensure that we don't default to a #load later and grab a repo which is the wrong
				// type
				// e.g. extension location type because a plugins/ directory exists.
				IArtifactRepository destAR;
				try
				{
					destAR = arManager.createRepository(destDir.toURI(), "Runnable repository.", //$NON-NLS-1$
							IArtifactRepositoryManager.TYPE_SIMPLE_REPOSITORY, null);
				}
				catch(ProvisionException e)
				{
					// ignore... perhaps one already exists and we will just load it later
					destAR = arManager.loadRepository(destDir.toURI(), null);
				}

				List<Resolution> ress = entry.getValue();
				List<IInstallableUnit> ius = new ArrayList<IInstallableUnit>(ress.size());
				for(Resolution res : ress)
				{
					SubMonitor subSubMon = subMon.newChild(800 / ress.size());
					subSubMon.setWorkRemaining(1000);

					IComponentIdentifier cid = res.getComponentIdentifier();
					Version version = Version.create(cid.getVersion().toString());
					URI repoURI = cleanURIFromImportType(URI.create(res.getRepository()));
					String path = repoURI.getPath();
					if(path.endsWith(".jar")) //$NON-NLS-1$
					{
						// This is a direct pointer to an artifact, not a repository
						//
						String rType = res.getReaderTypeId();
						if(IReaderType.ECLIPSE_IMPORT.equals(rType))
							rType = IReaderType.URL;

						if(!IReaderType.URL.equals(rType))
							throw BuckminsterException.fromMessage(NLS.bind(
									Messages.p2_materializer_cannot_process_readertype_0, rType));

						IComponentType ctype = CorePlugin.getDefault().getComponentType(cid.getComponentTypeID());
						IPath location = installLocation;
						IPath ctypeRelative = ctype.getRelativeLocation();
						if(ctypeRelative != null)
							location = location.append(ctypeRelative);
						location.toFile().mkdirs();

						String leafName = cid.getName() + '_' + cid.getVersion();
						if(res.isUnpack())
						{
							location = location.append(leafName);
							location = location.addTrailingSeparator();
						}
						else
							location = location.append(leafName + ".jar"); //$NON-NLS-1$

						IReaderType readerType = CorePlugin.getDefault().getReaderType(rType);
						IComponentReader reader = readerType.getReader(res, context, subSubMon.newChild(10));
						try
						{
							reader.materialize(location, res, context, subSubMon.newChild(500));
						}
						finally
						{
							try
							{
								reader.close();
							}
							catch(IOException e)
							{
								throw BuckminsterException.wrap(e);
							}
						}

						ArtifactDescriptor desc;
						if(IComponentType.ECLIPSE_FEATURE.equals(cid.getComponentTypeID()))
						{
							desc = new ArtifactDescriptor(new ArtifactKey(CLASSIFIER_ORG_ECLIPSE_UPDATE_FEATURE,
									cid.getName(), version));
							desc.addRepositoryProperties(Collections.singletonMap(PROP_ARTIFACT_FOLDER,
									Boolean.toString(true)));
						}
						else
						{
							desc = new ArtifactDescriptor(new ArtifactKey(CLASSIFIER_OSGI_BUNDLE, cid.getName(),
									version));
							if(res.isUnpack())
								desc.addRepositoryProperties(Collections.singletonMap(PROP_ARTIFACT_FOLDER,
										Boolean.toString(true)));
						}
						destAR.addDescriptor(desc);
						continue;
					}

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
					engine = bucky.getService(IEngine.class);
					IStatus status = engine.perform(profile, phaseSet, operands, new ProvisioningContext(),
							subMon.newChild(200));
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

			bucky.ungetService(mdrManager);
			bucky.ungetService(arManager);
			bucky.ungetService(registry);
			bucky.ungetService(engine);
		}
		return Collections.emptyList();
	}
}
