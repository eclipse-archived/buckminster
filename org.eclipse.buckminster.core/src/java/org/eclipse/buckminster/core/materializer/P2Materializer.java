package org.eclipse.buckminster.core.materializer;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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
import org.eclipse.equinox.internal.provisional.p2.director.IDirector;
import org.eclipse.equinox.internal.provisional.p2.director.ProfileChangeRequest;
import org.eclipse.equinox.internal.provisional.p2.engine.IProfile;
import org.eclipse.equinox.internal.provisional.p2.engine.IProfileRegistry;
import org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.internal.provisional.p2.metadata.query.InstallableUnitQuery;
import org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepository;
import org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepositoryManager;
import org.eclipse.equinox.internal.provisional.p2.query.Collector;
import org.eclipse.osgi.service.environment.EnvironmentInfo;
import org.eclipse.osgi.util.NLS;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;

@SuppressWarnings("restriction")
public class P2Materializer extends AbstractMaterializer
{
	private static final String ECLIPSE_P2_PROFILE_ID = "eclipse.p2.profile.id"; //$NON-NLS-1$

	private static final String SDK_PROFILE = "SDKProfile"; //$NON-NLS-1$

	private static final String P2_SETUP_BUNDLE_NAME = "org.eclipse.equinox.p2.exemplarysetup"; //$NON-NLS-1$

	private static final String P2_CORE_BUNDLE_NAME = "org.eclipse.equinox.p2.core"; //$NON-NLS-1$

	private static final String ECLIPSE_DATA_AREA_PROPERTY = "eclipse.p2.data.area"; //$NON-NLS-1$

	public static IArtifactRepository getArtifactRepository(URI repoLocation, IProgressMonitor monitor)
			throws CoreException
	{
		CorePlugin bundle = CorePlugin.getDefault();
		repoLocation = cleanURIFromImportType(repoLocation);
		IArtifactRepositoryManager manager = bundle.getService(IArtifactRepositoryManager.class);
		SubMonitor subMon = SubMonitor.convert(monitor, 200);
		try
		{
			return manager.loadRepository(repoLocation, subMon.newChild(100));
		}
		catch(ProvisionException e)
		{
			return manager.refreshRepository(repoLocation, subMon.newChild(100));
		}
		finally
		{
			bundle.ungetService(manager);
			if(monitor != null)
				monitor.done();
		}
	}

	public static IMetadataRepository getMetadataRepository(URI repoLocation, IProgressMonitor monitor)
			throws CoreException
	{
		CorePlugin bundle = CorePlugin.getDefault();
		repoLocation = cleanURIFromImportType(repoLocation);
		IMetadataRepositoryManager manager = bundle.getService(IMetadataRepositoryManager.class);
		SubMonitor subMon = SubMonitor.convert(monitor, 200);
		try
		{
			return manager.loadRepository(repoLocation, subMon.newChild(100));
		}
		catch(ProvisionException e)
		{
			return manager.refreshRepository(repoLocation, subMon.newChild(100));
		}
		finally
		{
			bundle.ungetService(manager);
			if(monitor != null)
				monitor.done();
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

			String profileId = (String)props.get(ECLIPSE_P2_PROFILE_ID);
			if(profileId == null)
				profileId = SDK_PROFILE;

			if(installLocation == null)
				installLocation = installRoot.append(profileId);
			else
				installLocation = installLocation.append(profileId);

			List<Resolution> rss = resPerLocation.get(installLocation);
			if(rss == null)
			{
				rss = new ArrayList<Resolution>();
				resPerLocation.put(installLocation, rss);
			}
			rss.add(res);
		}

		CorePlugin bundle = CorePlugin.getDefault();
		Bundle p2SetUp = Platform.getBundle(P2_SETUP_BUNDLE_NAME);
		if(p2SetUp == null)
			throw BuckminsterException.fromMessage(NLS.bind(Messages.Missing_OSGi_Bundle_0, P2_SETUP_BUNDLE_NAME));

		Bundle p2Core = Platform.getBundle(P2_CORE_BUNDLE_NAME);
		if(p2Core == null)
			throw BuckminsterException.fromMessage(NLS.bind(Messages.Missing_OSGi_Bundle_0, P2_SETUP_BUNDLE_NAME));

		String origLocation = System.getProperty(ECLIPSE_DATA_AREA_PROPERTY);
		boolean wasStarted = (p2SetUp.getState() == Bundle.ACTIVE);

		SubMonitor subMon = SubMonitor.convert(monitor, resPerLocation.size() * 1000);
		try
		{
			if(wasStarted)
			{
				p2SetUp.stop(Bundle.STOP_TRANSIENT);
				p2Core.stop(Bundle.STOP_TRANSIENT);
			}

			for(Map.Entry<IPath, List<Resolution>> entry : resPerLocation.entrySet())
			{
				IPath installLocation = entry.getKey();
				String profileId = installLocation.lastSegment();

				installLocation = installLocation.removeLastSegments(1);
				System.setProperty(ECLIPSE_DATA_AREA_PROPERTY, installLocation.toOSString());
				p2Core.start(Bundle.START_TRANSIENT);
				p2SetUp.start(Bundle.START_TRANSIENT);

				try
				{
					IProfile profile = null;
					IProfileRegistry profileRegistry = bundle.getService(IProfileRegistry.class);
					try
					{
						for(IProfile p : profileRegistry.getProfiles())
							if(profileId.equals(p.getProfileId()))
							{
								profile = p;
								break;
							}

						if(profile == null)
						{
							Map<String, String> properties = new HashMap<String, String>();
							properties.put(IProfile.PROP_INSTALL_FOLDER, installLocation.toString());
							EnvironmentInfo info = bundle.getService(EnvironmentInfo.class);
							String env = "osgi.os=" + info.getOS() + ",osgi.ws=" + info.getWS() + ",osgi.arch=" + info.getOSArch(); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
							properties.put(IProfile.PROP_ENVIRONMENTS, env);
							properties.put(IProfile.PROP_CACHE, installLocation.toString());
							properties.put(IProfile.PROP_FLAVOR, "tooling"); //$NON-NLS-1$
							properties.put(IProfile.PROP_ROAMING, "true"); //$NON-NLS-1$
							properties.put(IProfile.PROP_INSTALL_FEATURES, "true"); //$NON-NLS-1$
							properties.put(IProfile.PROP_NAME, profileId);
							profile = profileRegistry.addProfile(profileId, properties);
						}
					}
					finally
					{
						bundle.ungetService(profileRegistry);
					}

					ProfileChangeRequest request = new ProfileChangeRequest(profile);
					List<Resolution> ress = entry.getValue();
					for(Resolution res : ress)
					{
						SubMonitor subSubMon = subMon.newChild(200 / ress.size());

						subSubMon.setWorkRemaining(1000);
						URI repoURI = URI.create(res.getRepository());
						IMetadataRepository mdr = getMetadataRepository(repoURI, subSubMon.newChild(500));
						IComponentIdentifier cid = res.getComponentIdentifier();
						Version version = new Version(cid.getVersion().toString());
						VersionRange range = new VersionRange(version, true, version, true);
						Collector collector = new Collector();
						String name = cid.getName();
						if(IComponentType.ECLIPSE_FEATURE.equals(cid.getComponentTypeID())
								&& !name.endsWith(".feature.group")) //$NON-NLS-1$
							name = name + ".feature.group"; //$NON-NLS-1$

						mdr.query(new InstallableUnitQuery(name, range), collector, subSubMon.newChild(250));
						if(collector.isEmpty())
							throw new ProvisionException(NLS.bind(Messages.Unable_to_resolve_0_1_in_MDR_2,
									new Object[] { cid.getName(), version, res.getRepository() }));

						IInstallableUnit[] ius = (IInstallableUnit[])collector.toArray(IInstallableUnit.class);
						request.addInstallableUnits(ius);

						// Check if this IU has artifacts and if so, load the artifact repository
						//
						boolean hasArtifacts = false;
						int idx = ius.length;
						while(--idx >= 0 && !hasArtifacts)
							hasArtifacts = ius[idx].getArtifacts().length > 0;
						if(hasArtifacts)
							getArtifactRepository(repoURI, subSubMon.newChild(250));
						else
							subSubMon.worked(250);
					}

					IDirector director = bundle.getService(IDirector.class);
					try
					{
						IStatus status = director.provision(request, null, subMon.newChild(800));
						if(status.getSeverity() == IStatus.ERROR)
							throw BuckminsterException.wrap(status);
					}
					finally
					{
						bundle.ungetService(director);
					}
				}
				finally
				{
					p2SetUp.stop(Bundle.STOP_TRANSIENT);
					p2Core.stop(Bundle.STOP_TRANSIENT);
				}
			}
			return Collections.emptyList();
		}
		catch(BundleException e)
		{
			throw BuckminsterException.wrap(e);
		}
		finally
		{
			monitor.done();
			if(origLocation == null)
				System.clearProperty(ECLIPSE_DATA_AREA_PROPERTY);
			else
				System.setProperty(ECLIPSE_DATA_AREA_PROPERTY, origLocation);

			if(wasStarted)
				try
				{
					p2Core.start(Bundle.START_TRANSIENT);
					p2SetUp.start(Bundle.START_TRANSIENT);
				}
				catch(BundleException e)
				{
					throw BuckminsterException.wrap(e);
				}
		}
	}
}
