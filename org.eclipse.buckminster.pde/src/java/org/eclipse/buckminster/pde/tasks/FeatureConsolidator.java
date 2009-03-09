/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.pde.tasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.VersionFactory;
import org.eclipse.buckminster.core.version.VersionSyntaxException;
import org.eclipse.buckminster.pde.internal.FeatureModelReader;
import org.eclipse.buckminster.pde.internal.model.EditableFeatureModel;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.equinox.internal.p2.publisher.VersionedName;
import org.eclipse.pde.core.IModelChangedEvent;
import org.eclipse.pde.core.IModelChangedListener;
import org.eclipse.pde.internal.core.ifeature.IFeature;
import org.eclipse.pde.internal.core.ifeature.IFeatureChild;
import org.eclipse.pde.internal.core.ifeature.IFeatureImport;
import org.eclipse.pde.internal.core.ifeature.IFeaturePlugin;

@SuppressWarnings("restriction")
public class FeatureConsolidator extends GroupConsolidator implements IModelChangedListener
{
	private final EditableFeatureModel m_featureModel;

	public FeatureConsolidator(File inputFile, File outputFile, File propertiesFile, List<File> featuresAndBundles,
			String qualifier, boolean generateVersionSuffix, int maxVersionSuffixLength, int significantDigits)
			throws CoreException
	{
		super(outputFile, propertiesFile, featuresAndBundles, qualifier, generateVersionSuffix, maxVersionSuffixLength,
				significantDigits);
		m_featureModel = FeatureModelReader.readEditableFeatureModel(inputFile);
		m_featureModel.addModelChangedListener(this);
	}

	public void modelChanged(IModelChangedEvent event)
	{
		m_featureModel.setDirty(true);
	}

	public void run() throws CoreException, FileNotFoundException
	{
		IFeature feature = m_featureModel.getFeature();
		String id = feature.getId();

		ArrayList<ComponentIdentifier> deps = new ArrayList<ComponentIdentifier>();
		for(IFeatureChild ref : feature.getIncludedFeatures())
		{
			String vstr = ref.getVersion();
			ComponentIdentifier cid = replaceFeatureReferenceVersion(id, new VersionedName(ref.getId(), vstr));
			if(cid != null)
			{
				deps.add(cid);
				String nvstr = cid.getVersion().toString();
				if(!nvstr.equals(vstr))
					ref.setVersion(nvstr);
			}
		}

		for(IFeaturePlugin ref : feature.getPlugins())
		{
			String vstr = ref.getVersion();
			ComponentIdentifier cid = replacePluginReferenceVersion(id, new VersionedName(ref.getId(), vstr));
			if(cid != null)
			{
				deps.add(cid);
				String nvstr = cid.getVersion().toString();
				if(!nvstr.equals(vstr))
					ref.setVersion(nvstr);
			}
		}
		consolidateFeatureVersion(deps);
		m_featureModel.save(getOutputFile());
	}

	private void consolidateFeatureVersion(List<ComponentIdentifier> deps) throws CoreException
	{
		IFeature feature = m_featureModel.getFeature();
		String versionStr = feature.getVersion();
		if(versionStr == null)
			return;

		IVersion version;
		try
		{
			version = VersionFactory.OSGiType.fromString(versionStr);
		}
		catch(VersionSyntaxException e)
		{
			return;
		}

		if(versionStr.endsWith(PROPERTY_QUALIFIER))
		{
			ComponentIdentifier ci = new ComponentIdentifier(feature.getId(), IComponentType.ECLIPSE_FEATURE, version);
			IVersion newVersion = replaceQualifier(ci, deps);
			if(newVersion != null && !version.equals(newVersion))
			{
				String newVersionStr = newVersion.toString();
				feature.setVersion(newVersionStr);
				if(isContextReplacement())
				{
					int lastDot = versionStr.lastIndexOf("."); //$NON-NLS-1$
					m_featureModel.setContextQualifierLength(newVersionStr.length() - lastDot - 1);
				}
				version = newVersion;
			}
			if(isUsingGenerator(ci))
				return;
		}

		if(m_featureModel.getContextQualifierLength() == -1)
			return;

		IFeatureChild[] features = feature.getIncludedFeatures();
		List<VersionedName> featureList;
		if(features.length == 0)
			featureList = Collections.emptyList();
		else
		{
			featureList = new ArrayList<VersionedName>(features.length);
			for(IFeatureChild f : features)
				featureList.add(new VersionedName(f.getId(), f.getVersion()));
		}

		IFeatureImport[] bundles = feature.getImports();
		List<VersionedName> bundleList;
		if(features.length == 0)
			bundleList = Collections.emptyList();
		else
		{
			bundleList = new ArrayList<VersionedName>(bundles.length);
			for(IFeatureImport f : bundles)
				bundleList.add(new VersionedName(f.getId(), f.getVersion()));
		}

		String suffix = generateFeatureVersionSuffix(featureList, bundleList);
		if(suffix == null)
			return;

		String qualifier = version.getQualifier();
		if(qualifier == null)
			qualifier = suffix;
		else
		{
			StringBuilder bld = new StringBuilder();
			bld.append(qualifier, 0, m_featureModel.getContextQualifierLength());
			bld.append('-');
			bld.append(suffix);
			qualifier = bld.toString();
		}
		feature.setVersion(version.replaceQualifier(qualifier).toString());
	}
}
