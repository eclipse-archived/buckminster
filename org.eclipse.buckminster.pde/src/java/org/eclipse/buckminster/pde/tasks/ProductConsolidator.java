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
import org.eclipse.buckminster.pde.internal.model.EditableProductModel;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.equinox.internal.p2.publisher.VersionedName;
import org.eclipse.equinox.internal.provisional.p2.core.Version;
import org.eclipse.pde.core.IModelChangedEvent;
import org.eclipse.pde.core.IModelChangedListener;
import org.eclipse.pde.internal.core.iproduct.IProduct;
import org.eclipse.pde.internal.core.iproduct.IProductFeature;
import org.eclipse.pde.internal.core.iproduct.IProductPlugin;

@SuppressWarnings("restriction")
public class ProductConsolidator extends GroupConsolidator implements IModelChangedListener
{
	private final EditableProductModel m_productModel;

	public ProductConsolidator(File inputFile, File outputFile, File propertiesFile, List<File> featuresAndBundles,
			String qualifier, boolean generateVersionSuffix, int maxVersionSuffixLength, int significantDigits)
			throws CoreException
	{
		super(outputFile, propertiesFile, featuresAndBundles, qualifier, generateVersionSuffix, maxVersionSuffixLength,
				significantDigits);
		m_productModel = new EditableProductModel(inputFile);
		m_productModel.addModelChangedListener(this);
	}

	public void modelChanged(IModelChangedEvent event)
	{
		m_productModel.setDirty(true);
	}

	public void run() throws CoreException, FileNotFoundException
	{
		IProduct product = m_productModel.getProduct();
		String id = product.getId();
		Version version = Version.parseVersion(product.getVersion());

		if(product.useFeatures())
		{
			IProductFeature[] features = product.getFeatures();
			ArrayList<ComponentIdentifier> deps = new ArrayList<ComponentIdentifier>(features.length);

			for(IProductFeature ref : features)
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

			if(features.length == 1 && Version.emptyVersion.equals(version))
				product.setVersion(features[0].getVersion());
			else
				consolidateProductVersion(deps);
		}
		else
		{
			IProductPlugin[] plugins = product.getPlugins();
			ArrayList<ComponentIdentifier> deps = new ArrayList<ComponentIdentifier>(plugins.length);
			for(IProductPlugin ref : plugins)
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
			if(plugins.length == 1 && Version.emptyVersion.equals(version))
				product.setVersion(plugins[0].getVersion());
			else
				consolidateProductVersion(deps);
		}
		m_productModel.save(getOutputFile());
	}

	private void consolidateProductVersion(List<ComponentIdentifier> deps) throws CoreException
	{
		IProduct product = m_productModel.getProduct();
		String versionStr = product.getVersion();
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

		int contextQualifierLength = -1;
		if(versionStr.endsWith(PROPERTY_QUALIFIER))
		{
			ComponentIdentifier ci = new ComponentIdentifier(product.getId(), IComponentType.ECLIPSE_FEATURE, version);
			IVersion newVersion = replaceQualifier(ci, deps);
			if(newVersion != null && !version.equals(newVersion))
			{
				String newVersionStr = newVersion.toString();
				product.setVersion(newVersionStr);
				if(isContextReplacement())
				{
					int lastDot = versionStr.lastIndexOf("."); //$NON-NLS-1$
					contextQualifierLength = newVersionStr.length() - lastDot - 1;
				}
				version = newVersion;
			}
			if(isUsingGenerator(ci))
				return;
		}

		if(contextQualifierLength == -1)
			return;

		List<VersionedName> featureList = Collections.emptyList();
		List<VersionedName> bundleList = Collections.emptyList();
		if(product.useFeatures())
		{
			IProductFeature[] features = product.getFeatures();
			if(features.length > 0)
			{
				featureList = new ArrayList<VersionedName>(features.length);
				for(IProductFeature f : features)
					featureList.add(new VersionedName(f.getId(), f.getVersion()));
			}
		}
		else
		{
			IProductPlugin[] bundles = product.getPlugins();
			if(bundles.length > 0)
			{
				bundleList = new ArrayList<VersionedName>(bundles.length);
				for(IProductPlugin f : bundles)
					bundleList.add(new VersionedName(f.getId(), f.getVersion()));
			}
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
			bld.append(qualifier, 0, contextQualifierLength);
			bld.append('-');
			bld.append(suffix);
			qualifier = bld.toString();
		}
		product.setVersion(version.replaceQualifier(qualifier).toString());
	}
}
