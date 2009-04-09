/*******************************************************************************
 * Copyright (c) 2006-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.galileo.builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.amalgam.releng.build.Build;
import org.eclipse.amalgam.releng.build.Category;
import org.eclipse.amalgam.releng.build.Contribution;
import org.eclipse.amalgam.releng.build.Feature;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.equinox.internal.provisional.p2.core.Version;
import org.eclipse.equinox.internal.provisional.p2.core.VersionRange;
import org.eclipse.equinox.internal.provisional.p2.metadata.IArtifactKey;
import org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.internal.provisional.p2.metadata.IProvidedCapability;
import org.eclipse.equinox.internal.provisional.p2.metadata.IRequiredCapability;
import org.eclipse.equinox.internal.provisional.p2.metadata.MetadataFactory;
import org.eclipse.equinox.internal.provisional.p2.metadata.MetadataFactory.InstallableUnitDescription;
import org.eclipse.equinox.internal.provisional.p2.metadata.query.InstallableUnitQuery;
import org.eclipse.equinox.internal.provisional.p2.metadata.query.LatestIUVersionQuery;
import org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepository;
import org.eclipse.equinox.internal.provisional.p2.query.Collector;
import org.eclipse.equinox.internal.provisional.p2.query.CompositeQuery;
import org.eclipse.equinox.internal.provisional.p2.query.Query;
import org.eclipse.equinox.p2.publisher.AbstractPublisherAction;
import org.eclipse.equinox.p2.publisher.IPublisherInfo;
import org.eclipse.equinox.p2.publisher.IPublisherResult;
import org.eclipse.equinox.spi.p2.publisher.PublisherHelper;

@SuppressWarnings("restriction")
public class CategoriesAction extends AbstractPublisherAction
{
	private final Build m_build;

	private final IMetadataRepository m_globalRepo;

	public CategoriesAction(Build build, IMetadataRepository globalRepo)
	{
		m_build = build;
		m_globalRepo = globalRepo;
	}

	/**
	 * Creates an IU corresponding to an update site category
	 * 
	 * @param category
	 *            The category descriptor
	 * @param featureIUs
	 *            The IUs of the features that belong to the category
	 * @param parentCategory
	 *            The parent category, or <code>null</code>
	 * @return an IU representing the category
	 */
	public IInstallableUnit createCategoryIU(Category category, Set<IInstallableUnit> featureIUs,
			IInstallableUnit parentCategory)
	{
		InstallableUnitDescription cat = new MetadataFactory.InstallableUnitDescription();
		cat.setSingleton(true);
		String categoryId = category.getName();
		cat.setId(categoryId);
		cat.setVersion(Version.emptyVersion);
		cat.setProperty(IInstallableUnit.PROP_NAME, category.getLabel());
		cat.setProperty(IInstallableUnit.PROP_DESCRIPTION, category.getDescription());

		ArrayList<IRequiredCapability> reqsConfigurationUnits = new ArrayList<IRequiredCapability>(featureIUs.size());
		for(IInstallableUnit iu : featureIUs)
		{
			VersionRange range = new VersionRange(iu.getVersion(), true, iu.getVersion(), true);
			reqsConfigurationUnits.add(MetadataFactory.createRequiredCapability(IInstallableUnit.NAMESPACE_IU_ID,
					iu.getId(), range, iu.getFilter(), false, false));
		}
		// note that update sites don't currently support nested categories, but it may be useful to add in the future
		if(parentCategory != null)
		{
			reqsConfigurationUnits.add(MetadataFactory.createRequiredCapability(IInstallableUnit.NAMESPACE_IU_ID,
					parentCategory.getId(), VersionRange.emptyRange, parentCategory.getFilter(), false, false));
		}
		cat.setRequiredCapabilities(reqsConfigurationUnits.toArray(new IRequiredCapability[reqsConfigurationUnits.size()]));

		// Create set of provided capabilities
		ArrayList<IProvidedCapability> providedCapabilities = new ArrayList<IProvidedCapability>();
		providedCapabilities.add(PublisherHelper.createSelfCapability(categoryId, Version.emptyVersion));

		cat.setCapabilities(providedCapabilities.toArray(new IProvidedCapability[providedCapabilities.size()]));

		cat.setArtifacts(new IArtifactKey[0]);
		cat.setProperty(IInstallableUnit.PROP_TYPE_CATEGORY, "true"); //$NON-NLS-1$
		return MetadataFactory.createInstallableUnit(cat);
	}

	@Override
	public IStatus perform(IPublisherInfo publisherInfo, IPublisherResult results, IProgressMonitor monitor)
	{
		Map<Category, Set<IInstallableUnit>> categoriesToFeatureIUs = new HashMap<Category, Set<IInstallableUnit>>();
		try
		{
			for(Contribution contrib : m_build.getContributions())
			{
				for(Feature feature : contrib.getFeatures())
				{
					Category category = feature.getCategory();
					if(category != null)
					{
						IInstallableUnit featureIU = getFeatureIU(feature.getId(), feature.getVersion(), publisherInfo,
								monitor);

						Set<IInstallableUnit> featureIUs = categoriesToFeatureIUs.get(category);
						if(featureIUs == null)
						{
							featureIUs = new HashSet<IInstallableUnit>();
							categoriesToFeatureIUs.put(category, featureIUs);
						}
						featureIUs.add(featureIU);
					}
				}
			}
		}
		catch(CoreException e)
		{
			return e.getStatus();
		}
		generateCategoryIUs(categoriesToFeatureIUs, results);
		return Status.OK_STATUS;
	}

	/**
	 * Generates IUs corresponding to update site categories.
	 * 
	 * @param categoriesToFeatures
	 *            Map of Category ->Set (Feature IUs in that category).
	 * @param result
	 *            The generator result being built
	 */
	private void generateCategoryIUs(Map<Category, Set<IInstallableUnit>> categoriesToFeatures, IPublisherResult result)
	{
		for(Map.Entry<Category, Set<IInstallableUnit>> entry : categoriesToFeatures.entrySet())
			result.addIU(createCategoryIU(entry.getKey(), entry.getValue(), null), IPublisherResult.NON_ROOT);
	}

	private IInstallableUnit getFeatureIU(String name, String versionStr, IPublisherInfo publisherInfo,
			IProgressMonitor monitor) throws CoreException
	{
		if(monitor.isCanceled())
			throw new OperationCanceledException();

		String id = name + Activator.FEATURE_GROUP_SUFFIX;
		Query query = null;
		Collector collector = null;
		Version version = Version.parseVersion(versionStr);
		if(version.equals(Version.emptyVersion))
		{
			query = new CompositeQuery(new Query[] { new InstallableUnitQuery(id), new LatestIUVersionQuery() });
			collector = new Collector();
		}
		else
		{
			query = new InstallableUnitQuery(id, version);
			collector = new Collector()
			{
				@Override
				public boolean accept(Object object)
				{
					super.accept(object);
					return false; // stop searching once we've found one
				}
			};
		}

		collector = m_globalRepo.query(query, collector, monitor);
		if(collector.size() == 0)
			collector = publisherInfo.getMetadataRepository().query(query, collector, null);
		if(collector.size() == 0 && publisherInfo.getContextMetadataRepository() != null)
			collector = publisherInfo.getContextMetadataRepository().query(query, collector, null);
		if(collector.size() == 1)
			return (IInstallableUnit)collector.iterator().next();
		throw BuckminsterException.fromMessage("Unable to find feature %s/%s using composite repository", name, version); //$NON-NLS-1$
	}
}
