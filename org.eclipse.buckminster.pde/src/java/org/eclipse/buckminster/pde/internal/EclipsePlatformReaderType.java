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
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.common.model.Format;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.metadata.model.DepNode;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.reader.CatalogReaderType;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.core.reader.IVersionFinder;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.site.ISiteFeatureConverter;
import org.eclipse.buckminster.core.site.SaxableSite;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.core.version.VersionFactory;
import org.eclipse.buckminster.core.version.VersionMatch;
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
import org.eclipse.pde.core.plugin.IFragmentModel;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.core.plugin.PluginRegistry;
import org.eclipse.pde.internal.core.PDECore;
import org.eclipse.pde.internal.core.feature.FeaturePlugin;
import org.eclipse.pde.internal.core.ifeature.IFeature;
import org.eclipse.pde.internal.core.ifeature.IFeatureModel;
import org.eclipse.pde.internal.core.ifeature.IFeaturePlugin;
import org.eclipse.update.core.SiteFeatureReferenceModel;
import org.eclipse.update.core.model.ArchiveReferenceModel;
import org.eclipse.update.internal.core.ExtendedSite;
import org.osgi.framework.Version;

/**
 * A Reader type that knows about features and plugins that are part of an Eclipse installation.
 * @author thhal
 */
@SuppressWarnings("restriction")
public class EclipsePlatformReaderType extends CatalogReaderType implements ISiteFeatureConverter
{
	private static final String TEMP_FEATURE_ID = "buckminster.temp";
	private static final String TEMP_FEATURE_VERSION = "'0.1.0.'yyyyMMddHHmmss";

	public List<Resolution> convertToSiteFeatures(RMContext context, File siteFolder, List<Resolution> features, List<Resolution> plugins)
			throws CoreException
	{
		File featureFolder = new File(siteFolder, "features");
		featureFolder.mkdir();
		ExtendedSite site = new ExtendedSite();
		site.setSupportsPack200(true);

		HashSet<String> pluginNames = new HashSet<String>();
		for(Resolution res : features)
		{
			ComponentIdentifier ci = res.getComponentIdentifier();
			IVersion v = ci.getVersion();
			SiteFeatureReferenceModel model = new SiteFeatureReferenceModel();
			model.setFeatureIdentifier(ci.getName());
			model.setFeatureVersion(v == null ? "0.0.0" : v.toString());
			model.setURLString(getArtifactURLString(context, res));
			site.addFeatureReferenceModel(model);

			// Add all plug-ins that are included in the features
			// to the pluginNames set.
			//
			CSpec cspec = res.getCSpec();
			for(ComponentRequest dep : cspec.getDependencies().values())
			{
				if(IComponentType.OSGI_BUNDLE.equals(dep.getComponentTypeID()))
					pluginNames.add(dep.getName());
			}
		}

		EditableFeatureModel generatedFeatureModel = null;
		IFeature generatedFeature = null;
		String generatedFeatureJar = null;
		for(Resolution res : plugins)
		{
			ComponentIdentifier ci = res.getComponentIdentifier();
			String id = ci.getName();
			IVersion v = ci.getVersion();
			String vStr = (v == null) ? "0.0.0" : v.toString();

			// This plug-in is not here. It's in a remote location
			//
			ArchiveReferenceModel arf = new ArchiveReferenceModel();
			arf.setPath("plugins/" + id + '_' + vStr + ".jar");
			arf.setURLString(getArtifactURLString(context, res));
			site.addArchiveReferenceModel(arf);

			if(pluginNames.contains(id))
				continue;

			if(generatedFeature == null)
			{
				// Free standing plugins needs a feature.
				//
				DateFormat dateFormat = new SimpleDateFormat(TEMP_FEATURE_VERSION); 
				String featureVer = dateFormat.format(new Date());
				generatedFeatureJar = TEMP_FEATURE_ID + '_' + featureVer + ".jar";
				generatedFeatureModel = new EditableFeatureModel(null);
				generatedFeature = generatedFeatureModel.getFeature();
				generatedFeature.setId(TEMP_FEATURE_ID);
				generatedFeature.setLabel("Placeholder feature generated by Buckminster");
				generatedFeature.setVersion(featureVer);
			}

			FeaturePlugin plugin = new FeaturePlugin();
			plugin.setModel(generatedFeatureModel);
			plugin.setId(id);
			plugin.setVersion(vStr);
			plugin.setUnpack(false);
			generatedFeature.addPlugins(new IFeaturePlugin[] { plugin });
		}

		if(generatedFeatureModel != null)
		{
			JarOutputStream output = null;
			try
			{
				output = new JarOutputStream(new FileOutputStream(new File(featureFolder, generatedFeatureJar)));
				output.putNextEntry(new JarEntry("feature.xml"));
				generatedFeatureModel.save(output);
				output.closeEntry();
			}
			catch(IOException e)
			{
				throw BuckminsterException.wrap(e);
			}
			finally
			{
				IOUtils.close(output);
			}
			SiteFeatureReferenceModel model = new SiteFeatureReferenceModel();
			model.setFeatureIdentifier(generatedFeature.getId());
			model.setFeatureVersion(generatedFeature.getVersion());
			model.setLabel(generatedFeature.getLabel());
			model.setURLString("features/" + generatedFeatureJar);
			site.addFeatureReferenceModel(model);
		}
		
		// Save the site.xml
		//
		OutputStream output = null;
		SaxableSite saxSite = new SaxableSite(site);
		try
		{
			output = new BufferedOutputStream(new FileOutputStream(new File(siteFolder, "site.xml")));
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

		ArrayList<Resolution> siteFeatureResolutions = new ArrayList<Resolution>();
		try
		{
			IComponentType siteFeatureType = CorePlugin.getDefault().getComponentType(IComponentType.ECLIPSE_SITE_FEATURE);
			Provider provider = new Provider(null, IReaderType.ECLIPSE_SITE_FEATURE, new String[] { IComponentType.ECLIPSE_SITE_FEATURE }, null,
					new Format(siteFolder.toURI().toURL().toString()), null, null, null, false, false, null, null);

			for(Resolution res : features)
			{
				ProviderMatch orig = res.getProviderMatch(context);
				NodeQuery nq = new NodeQuery(context, new ComponentRequest(res.getName(), siteFeatureType.getId(), res.getVersionDesignator()), null);
				ProviderMatch pm = new ProviderMatch(provider, siteFeatureType, orig.getVersionMatch(), nq);
				DepNode node = siteFeatureType.getResolution(pm, new NullProgressMonitor());
				Resolution siteFeatureResolution = node.getResolution();
				if(siteFeatureResolution != null)
					siteFeatureResolutions.add(siteFeatureResolution);
			}
			
			if(generatedFeature != null)
			{
				IVersion version = VersionFactory.OSGiType.fromString(generatedFeature.getVersion());
				VersionMatch vm = new VersionMatch(version, null, null, -1, null, null);
				ComponentRequest cr = new ComponentRequest(generatedFeature.getId(), siteFeatureType.getId(), VersionFactory.createExplicitDesignator(version));
				NodeQuery nq = new NodeQuery(context, cr, null);
				ProviderMatch pm = new ProviderMatch(provider, siteFeatureType, vm, nq);
				DepNode node = siteFeatureType.getResolution(pm, new NullProgressMonitor());
				Resolution siteFeatureResolution = node.getResolution();
				if(siteFeatureResolution != null)
					siteFeatureResolutions.add(siteFeatureResolution);
			}
		}
		catch(MalformedURLException e)
		{
			throw BuckminsterException.wrap(e);
		}
		return siteFeatureResolutions;
	}

	@Override
	public IPath getFixedLocation(Resolution cr)
	{
		IVersion version = cr.getVersion();
		String versionString = version == null ? null : version.toString();
		String location;
		ComponentRequest rq = cr.getRequest();
		if(IComponentType.ECLIPSE_FEATURE.equals(rq.getComponentTypeID()))
		{
			IFeatureModel model = getBestFeature(rq.getName(), versionString);
			if(model == null)
				return null;
			location = model.getInstallLocation();
		}
		else
		{
			IPluginModelBase model = getBestPlugin(rq.getName(), versionString);
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
	public IVersionFinder getVersionFinder(Provider provider, IComponentType ctype, NodeQuery nodeQuery, IProgressMonitor monitor) throws CoreException
	{
		MonitorUtils.complete(monitor);
		return new EclipsePlatformVersionFinder(this, provider, ctype, nodeQuery);
	}

	public URI getArtifactURL(Resolution resolution, RMContext context) throws CoreException
	{
		return null;
	}

	public static IFeatureModel getBestFeature(String componentName, String desiredVersion)
	{
		if(desiredVersion == null)
			desiredVersion = "0.0.0";
		return PDECore.getDefault().getFeatureModelManager().findFeatureModel(componentName, desiredVersion);
	}

	public static IPluginModelBase getBestPlugin(String componentName, String desiredVersion)
	{
		IPluginModelBase unversioned = null;
		for(IPluginModelBase model : PluginRegistry.getActiveModels())
		{
			BundleDescription desc = model.getBundleDescription();
			if(desc == null)
				continue;

			if(desc.getSymbolicName().equals(componentName))
			{
				Version v = desc.getVersion();
				if(v == null)
				{
					if(desiredVersion == null)
						return model;
					unversioned = model;
					continue;
				}

				if(desiredVersion == null || desiredVersion.equals(v.toString()))
					return model;
			}
		}
		return unversioned;
	}

	public List<IFragmentModel> getFragmentsFor(String pluginId)
	{
		IPluginModelBase plugin = getBestPlugin(pluginId, null);
		if(plugin == null || plugin.isFragmentModel())
			return Collections.<IFragmentModel> emptyList();

		ArrayList<IFragmentModel> frags = null;
		for(IPluginModelBase candidate : PDECore.getDefault().getModelManager().getActiveModels(true))
		{
			if(candidate.isFragmentModel())
			{
				IFragmentModel frag = (IFragmentModel)candidate;
				if(frag.getFragment().getPluginId().equals(pluginId))
				{
					if(frags == null)
						frags = new ArrayList<IFragmentModel>();
					frags.add(frag);
				}
			}
		}
		return frags == null ? Collections.<IFragmentModel> emptyList() : frags;
	}

	private static String getArtifactURLString(RMContext context, Resolution res) throws CoreException
	{
		// This plug-in is not here. It's in a remote location
		//
		URI artifactURI = res.getArtifactURI(context);
		if(artifactURI == null)
			throw BuckminsterException.fromMessage("Unable to obtain URI for %s", res.getComponentIdentifier());
		try
		{
			URL artifactURL = artifactURI.toURL();
			return artifactURL.toString();
		}
		catch(MalformedURLException e)
		{
			throw BuckminsterException.fromMessage(e, "Unable to obtain URL for %s", res.getComponentIdentifier());
		}
	}
}
