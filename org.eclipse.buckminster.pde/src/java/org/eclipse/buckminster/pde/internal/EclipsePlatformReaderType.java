/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.pde.internal;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.common.model.Format;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.metadata.model.BOMNode;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.reader.CatalogReaderType;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.core.reader.IVersionFinder;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.resolver.ResolverDecisionType;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.site.ISiteFeatureConverter;
import org.eclipse.buckminster.core.site.SaxableSite;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.IVersionDesignator;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.core.version.VersionFactory;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.pde.Messages;
import org.eclipse.buckminster.pde.internal.model.EditableFeatureModel;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.osgi.service.resolver.BundleDescription;
import org.eclipse.osgi.util.NLS;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.core.plugin.PluginRegistry;
import org.eclipse.pde.internal.core.PDECore;
import org.eclipse.pde.internal.core.feature.FeaturePlugin;
import org.eclipse.pde.internal.core.ifeature.IFeature;
import org.eclipse.pde.internal.core.ifeature.IFeatureModel;
import org.eclipse.pde.internal.core.ifeature.IFeaturePlugin;
import org.eclipse.update.core.ISite;
import org.eclipse.update.core.SiteFeatureReferenceModel;
import org.eclipse.update.core.model.ArchiveReferenceModel;
import org.eclipse.update.internal.core.ExtendedSite;
import org.osgi.framework.Version;

/**
 * A Reader type that knows about features and plugins that are part of an Eclipse installation.
 * 
 * @author thhal
 */
@SuppressWarnings("restriction")
public class EclipsePlatformReaderType extends CatalogReaderType implements ISiteFeatureConverter
{
	private static final String TEMP_FEATURE_ID = "buckminster.temp"; //$NON-NLS-1$

	private static final String TEMP_FEATURE_VERSION = "'0.1.0.'yyyyMMddHHmmss"; //$NON-NLS-1$

	public static IFeatureModel getBestFeature(String componentName, IVersionDesignator versionDesignator,
			NodeQuery query)
	{
		IFeatureModel candidate = null;
		IVersion candidateVersion = null;
		for(IFeatureModel model : PDECore.getDefault().getFeatureModelManager().findFeatureModels(componentName))
		{
			IFeature feature = model.getFeature();
			String ov = feature.getVersion();
			if(ov == null)
			{
				if(candidate == null && versionDesignator == null)
					candidate = model;
				continue;
			}

			IVersion v = VersionFactory.OSGiType.coerce(ov);
			if(!(versionDesignator == null || versionDesignator.designates(v)))
			{
				if(query != null)
					query.logDecision(ResolverDecisionType.VERSION_REJECTED, v, NLS.bind(Messages
							.getString("EclipsePlatformReaderType.not_designated_by_0"), //$NON-NLS-1$
							versionDesignator));
				continue;
			}

			if(candidateVersion == null || candidateVersion.compareTo(v) < 0)
			{
				candidate = model;
				candidateVersion = v;
			}
		}
		return candidate;
	}

	public static IPluginModelBase getBestPlugin(String componentName, IVersionDesignator versionDesignator,
			NodeQuery query)
	{
		IPluginModelBase candidate = null;
		IVersion candidateVersion = null;
		for(IPluginModelBase model : PluginRegistry.getActiveModels())
		{
			BundleDescription desc = model.getBundleDescription();
			if(desc == null)
				continue;

			if(!desc.getSymbolicName().equals(componentName))
				continue;

			Version ov = desc.getVersion();
			if(ov == null)
			{
				if(candidate == null && versionDesignator == null)
					candidate = model;
				continue;
			}

			IVersion v = VersionFactory.OSGiType.coerce(ov);
			if(!(versionDesignator == null || versionDesignator.designates(v)))
			{
				if(query != null)
					query.logDecision(ResolverDecisionType.VERSION_REJECTED, v, String.format(Messages
							.getString("EclipsePlatformReaderType.not_designated_by_0"), //$NON-NLS-1$
							versionDesignator));
				continue;
			}

			if(candidateVersion == null || candidateVersion.compareTo(v) < 0)
			{
				candidate = model;
				candidateVersion = v;
			}
		}
		return candidate;
	}

	private static String getArtifactURLString(RMContext context, Resolution res) throws CoreException
	{
		// This plug-in is not here. It's in a remote location
		//
		URI artifactURI = res.getArtifactURI(context);
		if(artifactURI == null)
			throw BuckminsterException.fromMessage(NLS.bind(Messages
					.getString("EclipsePlatformReaderType.unable_to_obtain_URI_for_0"), res.getComponentIdentifier())); //$NON-NLS-1$
		try
		{
			URL artifactURL = artifactURI.toURL();
			return artifactURL.toString();
		}
		catch(MalformedURLException e)
		{
			throw BuckminsterException.fromMessage(e, NLS.bind(Messages
					.getString("EclipsePlatformReaderType.unable_to_obtain_URL_for_0"), res.getComponentIdentifier())); //$NON-NLS-1$
		}
	}

	public List<Resolution> convertToSiteFeatures(RMContext context, File siteFolder, List<Resolution> features,
			List<Resolution> plugins) throws CoreException
	{
		HashSet<ComponentIdentifier> pluginNames = new HashSet<ComponentIdentifier>();

		HashMap<String, List<Resolution>> siteAndFeatures = new HashMap<String, List<Resolution>>();
		for(Resolution res : features)
		{
			String urlString = getArtifactURLString(context, res);
			int idx = urlString.indexOf("/features/"); //$NON-NLS-1$
			if(idx < 0)
				continue;

			String siteURL = urlString.substring(0, idx);
			List<Resolution> featuresOnSite = siteAndFeatures.get(siteURL);
			if(featuresOnSite == null)
			{
				featuresOnSite = new ArrayList<Resolution>();
				siteAndFeatures.put(siteURL, featuresOnSite);
			}
			featuresOnSite.add(res);
			for(ComponentRequest dep : res.getCSpec().getDependencies().values())
			{
				if(!IComponentType.OSGI_BUNDLE.equals(dep.getComponentTypeID()))
					continue;

				// A feature reference is always explicit
				//
				IVersionDesignator vd = dep.getVersionDesignator();
				if(vd == null)
				{
					CorePlugin.getLogger().warning(
							NLS.bind(Messages.getString("EclipsePlatformReaderType.bogus_ref_to_0_in_1_at_2"), //$NON-NLS-1$
									new Object[] { dep.getName(), res.getComponentIdentifier(), siteURL }));
					continue;
				}
				pluginNames.add(new ComponentIdentifier(dep.getName(), IComponentType.OSGI_BUNDLE, vd == null
						? null
						: vd.getVersion()));
			}
		}

		ArrayList<Resolution> siteFeatureResolutions = new ArrayList<Resolution>();
		for(Map.Entry<String, List<Resolution>> entry : siteAndFeatures.entrySet())
		{
			String siteURL = entry.getKey();
			IComponentType siteFeatureType = CorePlugin.getDefault().getComponentType(
					IComponentType.ECLIPSE_SITE_FEATURE);
			Provider provider = new Provider(null, IReaderType.ECLIPSE_SITE_FEATURE,
					new String[] { IComponentType.ECLIPSE_SITE_FEATURE }, null, new Format(siteURL), null, null, null,
					false, false, null, null);

			for(Resolution res : entry.getValue())
			{
				ProviderMatch orig = res.getProviderMatch(context);
				NodeQuery nq = new NodeQuery(context, new ComponentRequest(res.getName(), siteFeatureType.getId(), res
						.getVersionDesignator()), null);
				ProviderMatch pm = new ProviderMatch(provider, siteFeatureType, orig.getVersionMatch(), nq);
				BOMNode node = siteFeatureType.getResolution(pm, new NullProgressMonitor());
				Resolution siteFeatureResolution = node.getResolution();
				if(siteFeatureResolution != null)
					siteFeatureResolutions.add(siteFeatureResolution);
			}
		}

		EditableFeatureModel generatedFeatureModel = null;
		IFeature generatedFeature = null;
		String generatedFeatureJar = null;
		ExtendedSite site = null;
		for(Resolution res : plugins)
		{
			ComponentIdentifier ci = res.getComponentIdentifier();
			String id = ci.getName();
			IVersion v = ci.getVersion();
			String vStr = (v == null)
					? "0.0.0" //$NON-NLS-1$
					: v.toString();

			if(pluginNames.contains(ci))
				continue;

			if(site == null)
			{
				site = new ExtendedSite();
				site.setSupportsPack200(true);
			}
			// This plug-in is not here. It's in a remote location
			//
			ArchiveReferenceModel arf = new ArchiveReferenceModel();
			arf.setPath("plugins/" + id + '_' + vStr + ".jar"); //$NON-NLS-1$ //$NON-NLS-2$
			arf.setURLString(getArtifactURLString(context, res));
			site.addArchiveReferenceModel(arf);
			pluginNames.add(ci);

			if(generatedFeature == null)
			{
				// Free standing plugins needs a feature.
				//
				DateFormat dateFormat = new SimpleDateFormat(TEMP_FEATURE_VERSION);
				String featureVer = dateFormat.format(new Date());
				generatedFeatureJar = TEMP_FEATURE_ID + '_' + featureVer + ".jar"; //$NON-NLS-1$
				generatedFeatureModel = new EditableFeatureModel(null);
				generatedFeature = generatedFeatureModel.getFeature();
				generatedFeature.setId(TEMP_FEATURE_ID);
				generatedFeature.setLabel(Messages.getString("EclipsePlatformReaderType.placeholder_feature")); //$NON-NLS-1$
				generatedFeature.setVersion(featureVer);
			}

			FeaturePlugin plugin = new FeaturePlugin();
			plugin.setModel(generatedFeatureModel);
			plugin.setId(id);
			plugin.setVersion(vStr);
			plugin.setUnpack(false);
			generatedFeature.addPlugins(new IFeaturePlugin[] { plugin });
		}

		if(site == null)
			//
			// No free standing plugins were found
			//
			return siteFeatureResolutions;

		JarOutputStream featureOutput = null;
		try
		{
			File featureFolder = new File(siteFolder, "features"); //$NON-NLS-1$
			featureFolder.mkdir();
			featureOutput = new JarOutputStream(new FileOutputStream(new File(featureFolder, generatedFeatureJar)));
			featureOutput.putNextEntry(new JarEntry("feature.xml")); //$NON-NLS-1$
			generatedFeatureModel.save(featureOutput);
			featureOutput.closeEntry();
		}
		catch(IOException e)
		{
			throw BuckminsterException.wrap(e);
		}
		finally
		{
			IOUtils.close(featureOutput);
		}

		SiteFeatureReferenceModel model = new SiteFeatureReferenceModel();
		model.setSiteModel(site);
		model.setFeatureIdentifier(generatedFeature.getId());
		model.setFeatureVersion(generatedFeature.getVersion());
		model.setLabel(generatedFeature.getLabel());
		model.setURLString("features/" + generatedFeatureJar); //$NON-NLS-1$
		model.setType(ISite.DEFAULT_PACKAGED_FEATURE_TYPE);
		site.addFeatureReferenceModel(model);

		// Save the site.xml
		//
		SaxableSite saxSite = new SaxableSite(site);
		OutputStream output = null;
		try
		{
			output = new BufferedOutputStream(new FileOutputStream(new File(siteFolder, "site.xml"))); //$NON-NLS-1$
			Utils.serialize(saxSite, output);
		}
		catch(Exception e)
		{
			throw BuckminsterException.wrap(e);
		}
		finally
		{
			IOUtils.close(output);
		}

		try
		{
			IComponentType siteFeatureType = CorePlugin.getDefault().getComponentType(
					IComponentType.ECLIPSE_SITE_FEATURE);
			Provider provider = new Provider(null, IReaderType.ECLIPSE_SITE_FEATURE,
					new String[] { IComponentType.ECLIPSE_SITE_FEATURE }, null, new Format(siteFolder.toURI().toURL()
							.toString()), null, null, null, false, false, null, null);

			IVersion version = VersionFactory.OSGiType.fromString(generatedFeature.getVersion());
			VersionMatch vm = new VersionMatch(version, null, -1, null, null);
			ComponentRequest cr = new ComponentRequest(generatedFeature.getId(), siteFeatureType.getId(),
					VersionFactory.createExplicitDesignator(version));
			NodeQuery nq = new NodeQuery(context, cr, null);
			ProviderMatch pm = new ProviderMatch(provider, siteFeatureType, vm, nq);
			BOMNode node = siteFeatureType.getResolution(pm, new NullProgressMonitor());
			Resolution siteFeatureResolution = node.getResolution();
			if(siteFeatureResolution != null)
				siteFeatureResolutions.add(siteFeatureResolution);
		}
		catch(MalformedURLException e)
		{
			throw BuckminsterException.wrap(e);
		}
		return siteFeatureResolutions;
	}

	public URI getArtifactURL(Resolution resolution, RMContext context) throws CoreException
	{
		return null;
	}

	@Override
	public IPath getFixedLocation(Resolution cr)
	{
		IVersion version = cr.getVersion();
		IVersionDesignator vd = version == null
				? null
				: VersionFactory.createExplicitDesignator(version);
		String location;
		ComponentRequest rq = cr.getRequest();
		if(IComponentType.ECLIPSE_FEATURE.equals(rq.getComponentTypeID()))
		{
			IFeatureModel model = getBestFeature(rq.getName(), vd, null);
			if(model == null)
				return null;
			location = model.getInstallLocation();
		}
		else
		{
			IPluginModelBase model = getBestPlugin(rq.getName(), vd, null);
			if(model == null)
				return null;
			location = model.getInstallLocation();
		}

		IPath path = null;
		if(location != null)
		{
			path = new Path(location);
			if(path.toFile().isDirectory())
				path = path.addTrailingSeparator();
		}
		return path;
	}

	public IComponentReader getReader(ProviderMatch providerMatch, IProgressMonitor monitor) throws CoreException
	{
		MonitorUtils.complete(monitor);
		return new EclipsePlatformReader(this, providerMatch);
	}

	@Override
	public IVersionFinder getVersionFinder(Provider provider, IComponentType ctype, NodeQuery nodeQuery,
			IProgressMonitor monitor) throws CoreException
	{
		MonitorUtils.complete(monitor);
		return new EclipsePlatformVersionFinder(this, provider, ctype, nodeQuery);
	}
}
