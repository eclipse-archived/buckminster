package org.eclipse.buckminster.pde.internal;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.VersionFactory;
import org.eclipse.buckminster.core.version.VersionSyntaxException;
import org.eclipse.buckminster.pde.IPDEConstants;
import org.eclipse.buckminster.pde.internal.model.ExternalBundleModel;
import org.eclipse.buckminster.pde.internal.model.ExternalEditableFeatureModel;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.pde.core.IModelChangedEvent;
import org.eclipse.pde.core.IModelChangedListener;
import org.eclipse.pde.core.plugin.IPluginBase;
import org.eclipse.pde.internal.build.IBuildPropertiesConstants;
import org.eclipse.pde.internal.core.bundle.BundleFragmentModel;
import org.eclipse.pde.internal.core.bundle.BundlePluginModel;
import org.eclipse.pde.internal.core.ibundle.IBundlePluginModelBase;
import org.eclipse.pde.internal.core.ifeature.IFeature;
import org.eclipse.pde.internal.core.ifeature.IFeatureChild;
import org.eclipse.pde.internal.core.ifeature.IFeatureModel;
import org.eclipse.pde.internal.core.ifeature.IFeaturePlugin;
import org.eclipse.pde.internal.core.plugin.ExternalFragmentModel;
import org.eclipse.pde.internal.core.plugin.ExternalPluginModel;

@SuppressWarnings("restriction")
public class FeatureConsolidator implements IModelChangedListener, IBuildPropertiesConstants, IPDEConstants
{
	private final Map<String, IVersion[]> m_featureVersions = new HashMap<String, IVersion[]>();

	private final Map<String, IVersion[]> m_pluginVersions = new HashMap<String, IVersion[]>();

	private final ExternalEditableFeatureModel m_featureModel;

	public FeatureConsolidator(File featureFile, String[] featureAndPluginPath) throws CoreException
	{
		m_featureModel = FeatureModelReader.readEditableFeatureModel(featureFile);
		m_featureModel.addModelChangedListener(this);

		for(String elem : featureAndPluginPath)
		{
			File component = new File(elem);
			InputStream input = null;
			try
			{
				try
				{
					input = getInput(component, FEATURE_FILE);
					IFeatureModel model = FeatureModelReader.readFeatureModel(input);
					IFeature feature = model.getFeature();
					addVersion(m_featureVersions, feature.getId(), feature.getVersion());
					continue;
				}
				catch(FileNotFoundException e)
				{
				}
				try
				{
					input = getInput(component, BUNDLE_FILE);
					ExternalBundleModel model = new ExternalBundleModel();
					model.load(input, true);
					IBundlePluginModelBase bmodel = model.isFragmentModel()
							? new BundleFragmentModel()
							: new BundlePluginModel();

					bmodel.setEnabled(true);
					bmodel.setBundleModel(model);
					IPluginBase pb = bmodel.getPluginBase();

					addVersion(m_pluginVersions, pb.getId(), pb.getVersion());
					continue;
				}
				catch(FileNotFoundException e)
				{
				}
				try
				{
					input = getInput(component, PLUGIN_FILE);
					ExternalPluginModel model = new ExternalPluginModel();
					IPluginBase pb = model.getPluginBase();
					addVersion(m_pluginVersions, pb.getId(), pb.getVersion());
					continue;
				}
				catch(FileNotFoundException e)
				{
				}
				try
				{
					input = getInput(component, FRAGMENT_FILE);
					ExternalFragmentModel model = new ExternalFragmentModel();
					IPluginBase pb = model.getPluginBase();
					addVersion(m_pluginVersions, pb.getId(), pb.getVersion());
					continue;
				}
				catch(FileNotFoundException e)
				{
				}
			}
			finally
			{
				IOUtils.close(input);
			}
		}
	}

	public void modelChanged(IModelChangedEvent event)
	{
		m_featureModel.setDirty(true);
	}

	public void run() throws CoreException, FileNotFoundException
	{
		IFeature feature = m_featureModel.getFeature();
		String id = feature.getId();
		for(IFeatureChild ref : feature.getIncludedFeatures())
			replaceFeatureReferenceVersion(m_featureVersions, id, ref);

		for(IFeaturePlugin ref : feature.getPlugins())
			replacePluginReferenceVersion(m_pluginVersions, id, ref);

		if(m_featureModel.isDirty())
			m_featureModel.save();
	}


	private static void replaceFeatureReferenceVersion(Map<String, IVersion[]> versionMap, String id, IFeatureChild ref) throws CoreException
	{
		IVersion version = findBestVersion(versionMap, id, "feature", ref.getId(), ref.getVersion());
		String newVer = version.toString();
		if(!newVer.equals(ref.getVersion()))
			ref.setVersion(newVer);
	}

	private static void replacePluginReferenceVersion(Map<String, IVersion[]> versionMap, String id, IFeaturePlugin ref) throws CoreException
	{
		IVersion version = findBestVersion(versionMap, id, "plugin", ref.getId(), ref.getVersion());
		String newVer = version.toString();
		if(!newVer.equals(ref.getVersion()))
			ref.setVersion(newVer);
	}

	private static void addVersion(Map<String, IVersion[]> versionMap, String id, String versionStr)
			throws VersionSyntaxException
	{
		IVersion version = VersionFactory.OSGiType.fromString(versionStr);
		IVersion[] arr = versionMap.get(id);
		if(arr == null)
			arr = new IVersion[] { version };
		else
		{
			for(IVersion old : arr)
				if(Trivial.equalsAllowNull(old, version))
					return;

			IVersion[] newArr = new IVersion[arr.length + 1];
			System.arraycopy(arr, 0, newArr, 0, arr.length);
			newArr[arr.length] = version;
			arr = newArr;
		}
		versionMap.put(id, arr);
	}

	private static IVersion findBestVersion(Map<String, IVersion[]> versionMap, String id, String category, String refId, String versionStr) throws CoreException
	{
		IVersion version;
		try
		{
			version = VersionFactory.OSGiType.fromString(versionStr);
			if(version.toString().equals("0.0.0"))
				version = null;
		}
		catch(VersionSyntaxException e)
		{
			version = null;
		}

		IVersion candidate = null;
		IVersion[] versions = versionMap.get(refId);
		if(versions != null)
		{
			for(IVersion v : versions)
			{
				if(version == null)
				{
					// Highest found version wins
					//
					if(candidate == null || v == null || v.compareTo(candidate) > 0)
						candidate = v;
				}
				else if(version.equals(v))
				{
					// Must have an explicit match
					//
					candidate = v;
					break;
				}
			}
		}
		
		if(candidate == null)
			throw new BuckminsterException("No suitable match found for " + category + ' ' + refId + " referenced from " + id);

		return candidate;
	}

	private static InputStream getInput(File dirOrZip, String fileName) throws FileNotFoundException, CoreException
	{
		if(dirOrZip.isFile())
		{
			try
			{
				URL zipURL = new URL("jar:" + dirOrZip.toURI().toURL().toString() + "!/" + fileName);
				return new BufferedInputStream(zipURL.openStream());
			}
			catch(MalformedURLException e)
			{
				throw new RuntimeException(e);
			}
			catch(FileNotFoundException e)
			{
				throw e;
			}
			catch(IOException e)
			{
				throw BuckminsterException.wrap(e);
			}
		}
		return new BufferedInputStream(new FileInputStream(new File(dirOrZip, fileName)));
	}
}
