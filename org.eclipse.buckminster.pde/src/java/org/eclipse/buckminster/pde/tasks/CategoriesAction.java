/*******************************************************************************
 * Copyright (c) 2006-2009, Cloudsmith Inc.
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
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.core.version.VersionHelper;
import org.eclipse.buckminster.pde.IPDEConstants;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.equinox.internal.p2.updatesite.SiteCategory;
import org.eclipse.equinox.internal.p2.updatesite.SiteFeature;
import org.eclipse.equinox.internal.p2.updatesite.SiteModel;
import org.eclipse.equinox.internal.provisional.p2.core.Version;
import org.eclipse.equinox.internal.provisional.p2.core.VersionRange;
import org.eclipse.equinox.internal.provisional.p2.core.VersionedName;
import org.eclipse.equinox.internal.provisional.p2.metadata.IArtifactKey;
import org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.internal.provisional.p2.metadata.IProvidedCapability;
import org.eclipse.equinox.internal.provisional.p2.metadata.IRequiredCapability;
import org.eclipse.equinox.internal.provisional.p2.metadata.MetadataFactory;
import org.eclipse.equinox.internal.provisional.p2.metadata.MetadataFactory.InstallableUnitDescription;
import org.eclipse.equinox.internal.provisional.p2.metadata.query.InstallableUnitQuery;
import org.eclipse.equinox.internal.provisional.p2.metadata.query.LatestIUVersionQuery;
import org.eclipse.equinox.internal.provisional.p2.query.Collector;
import org.eclipse.equinox.internal.provisional.p2.query.CompositeQuery;
import org.eclipse.equinox.internal.provisional.p2.query.Query;
import org.eclipse.equinox.p2.publisher.AbstractPublisherAction;
import org.eclipse.equinox.p2.publisher.IPublisherInfo;
import org.eclipse.equinox.p2.publisher.IPublisherResult;
import org.eclipse.equinox.spi.p2.publisher.LocalizationHelper;
import org.eclipse.equinox.spi.p2.publisher.PublisherHelper;

/**
 * Action which processes a feature.xml, build.properties, and feature localization files and generates categories,
 * mirrors url, and referenced repositories for a p2 MDR. The process relies on IUs for the various features to have
 * already been generated.
 */
@SuppressWarnings("restriction")
public class CategoriesAction extends AbstractPublisherAction
{
	private static class Category
	{
		private String m_description;

		private String m_label;

		private final String m_name;

		Category(String name)
		{
			m_name = name;
		}

		@Override
		public boolean equals(Object value)
		{
			return value instanceof Category && ((Category)value).m_name.equals(m_name);
		}

		public String getDescription()
		{
			return m_description;
		}

		public String getLabel()
		{
			return m_label;
		}

		public String getName()
		{
			return m_name;
		}

		@Override
		public int hashCode()
		{
			return m_name.hashCode();
		}

		public void setDescription(String description)
		{
			m_description = description;
		}

		public void setLabel(String label)
		{
			m_label = label;
		}
	}

	private static final String PROP_CATEGORY_DESCRIPTION_PREFIX = "category.description."; //$NON-NLS-1$

	private static final String PROP_CATEGORY_MEMBERS_PREFIX = "category.members."; //$NON-NLS-1$

	private static final String PROP_CATEGORY_DEFAULT = "category.default"; //$NON-NLS-1$

	private static final String PROP_CATEGORY_ID_PREFIX = "category.id."; //$NON-NLS-1$

	private static final Pattern s_idAndVersionPattern = Pattern.compile("^(\\S)_([0-9]+(?:\\.[0-9]+){0,2}(?:\\.[A-Za-z0-9_-]))$"); //$NON-NLS-1$

	private static final List<Category> s_defaultCategoryList;

	static
	{
		Category defaultCategory = new Category("Default"); //$NON-NLS-1$
		defaultCategory.setDescription("Default category for otherwise uncategorized features"); //$NON-NLS-1$
		defaultCategory.setLabel("Uncategorized"); //$NON-NLS-1$
		s_defaultCategoryList = Collections.singletonList(defaultCategory);
	}

	/**
	 * Return localizations for %xxx properties found in <code>properties</code>.
	 * 
	 * @param properties
	 * @param featureRoot
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static Map<Locale, Properties> getLocalizations(Map<String, String> properties, File featureRoot)
	{
		if(featureRoot == null || properties == null)
			return Collections.emptyMap();

		List<String> msgKeys = null;
		for(String value : properties.values())
		{
			if(value != null && value.length() > 1 && value.charAt(0) == '%')
			{
				if(msgKeys == null)
					msgKeys = new ArrayList<String>();
				msgKeys.add(value.substring(1));
			}
		}
		if(msgKeys == null)
			return Collections.emptyMap();

		Map<Locale, Properties> localizations;
		String[] keyStrings = msgKeys.toArray(new String[msgKeys.size()]);
		if(featureRoot.isDirectory())
			localizations = LocalizationHelper.getDirPropertyLocalizations(featureRoot, "feature", null, keyStrings); //$NON-NLS-1$
		else if(featureRoot.getName().endsWith(".jar")) //$NON-NLS-1$
			localizations = LocalizationHelper.getJarPropertyLocalizations(featureRoot, "feature", null, keyStrings); //$NON-NLS-1$
		else
			localizations = Collections.emptyMap();
		return localizations;
	}

	private static boolean isKeyReference(String value, String key)
	{
		return value != null && value.length() > 1 && value.charAt(0) == '%' && value.substring(1).equals(key);
	}

	private final Map<Locale, Properties> m_localizations;

	private final Map<String, String> m_buildProperties;

	private final List<VersionedName> m_featureEntries;

	private final File m_projectRoot;

	public CategoriesAction(File projectRoot, Map<String, String> buildProperties, List<VersionedName> featureEntries)
			throws CoreException
	{
		m_buildProperties = buildProperties;
		m_localizations = getLocalizations(buildProperties, projectRoot);
		m_featureEntries = featureEntries;
		m_projectRoot = projectRoot;
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
		cat.setProperty(IInstallableUnit.PROP_NAME, category.getLabel());
		cat.setProperty(IInstallableUnit.PROP_DESCRIPTION, category.getDescription());

		ArrayList<VersionedName> fts = new ArrayList<VersionedName>(featureIUs.size());
		ArrayList<VersionedName> bds = new ArrayList<VersionedName>(featureIUs.size());
		ArrayList<IRequiredCapability> reqsConfigurationUnits = new ArrayList<IRequiredCapability>(featureIUs.size());
		for(IInstallableUnit iu : featureIUs)
		{
			VersionedName vn = new VersionedName(iu.getId(), iu.getVersion());
			if(iu.getId().endsWith(IPDEConstants.FEATURE_GROUP))
				fts.add(vn);
			else
				bds.add(vn);
			VersionRange range = new VersionRange(iu.getVersion(), true, iu.getVersion(), true);
			reqsConfigurationUnits.add(MetadataFactory.createRequiredCapability(IInstallableUnit.NAMESPACE_IU_ID,
					iu.getId(), range, iu.getFilter(), false, false));
		}
		FeatureVersionSuffixGenerator suffixGen = new FeatureVersionSuffixGenerator(-1, -1);
		Version categoryVersion = Version.createOSGi(0, 0, 1, suffixGen.generateSuffix(fts, bds));
		cat.setVersion(categoryVersion);

		// note that update sites don't currently support nested categories, but it may be useful to add in the future
		if(parentCategory != null)
		{
			reqsConfigurationUnits.add(MetadataFactory.createRequiredCapability(IInstallableUnit.NAMESPACE_IU_ID,
					parentCategory.getId(), VersionRange.emptyRange, parentCategory.getFilter(), false, false));
		}
		cat.setRequiredCapabilities(reqsConfigurationUnits.toArray(new IRequiredCapability[reqsConfigurationUnits.size()]));

		// Create set of provided capabilities
		ArrayList<IProvidedCapability> providedCapabilities = new ArrayList<IProvidedCapability>();
		providedCapabilities.add(PublisherHelper.createSelfCapability(categoryId, categoryVersion));

		for(Map.Entry<Locale, Properties> locEntry : m_localizations.entrySet())
		{
			Locale locale = locEntry.getKey();
			Properties translatedStrings = locEntry.getValue();
			Enumeration<?> propertyKeys = translatedStrings.propertyNames();
			while(propertyKeys.hasMoreElements())
			{
				String key = (String)propertyKeys.nextElement();

				// Is the category using this key?
				//
				if(isKeyReference(category.getLabel(), key) || isKeyReference(category.getDescription(), key))
					cat.setProperty(locale.toString() + '.' + key, translatedStrings.getProperty(key));
			}
			providedCapabilities.add(PublisherHelper.makeTranslationCapability(categoryId, locale));
		}

		cat.setCapabilities(providedCapabilities.toArray(new IProvidedCapability[providedCapabilities.size()]));

		cat.setArtifacts(new IArtifactKey[0]);
		cat.setProperty(IInstallableUnit.PROP_TYPE_CATEGORY, "true"); //$NON-NLS-1$
		return MetadataFactory.createInstallableUnit(cat);
	}

	@Override
	public IStatus perform(IPublisherInfo publisherInfo, IPublisherResult results, IProgressMonitor monitor)
	{
		Map<Category, Set<IInstallableUnit>> categoriesToFeatureIUs = new HashMap<Category, Set<IInstallableUnit>>();
		Map<IInstallableUnit, List<Category>> featuresToCategories;
		try
		{
			featuresToCategories = getFeatureToCategoryMappings(publisherInfo, results, monitor);
		}
		catch(CoreException e)
		{
			return e.getStatus();
		}
		for(Map.Entry<IInstallableUnit, List<Category>> entry : featuresToCategories.entrySet())
		{
			IInstallableUnit iu = entry.getKey();
			for(Category category : entry.getValue())
			{
				Set<IInstallableUnit> featureIUs = categoriesToFeatureIUs.get(category);
				if(featureIUs == null)
				{
					featureIUs = new HashSet<IInstallableUnit>();
					categoriesToFeatureIUs.put(category, featureIUs);
				}
				featureIUs.add(iu);
			}
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

	private IInstallableUnit getFeatureIU(String name, Version version, IPublisherInfo publisherInfo,
			IPublisherResult results, IProgressMonitor monitor)
	{
		if(monitor.isCanceled())
			throw new OperationCanceledException();

		String id = name + ".feature.group"; //$NON-NLS-1$
		Query query = null;
		Collector collector = null;
		if(version == null || version.equals(Version.emptyVersion))
		{
			query = new CompositeQuery(new Query[] { new InstallableUnitQuery(id), new LatestIUVersionQuery() });
			collector = new Collector();
		}
		else
		{
			if(version.getQualifier() != null && version.getQualifier().contains("qualifier")) //$NON-NLS-1$
			{
				// We won't find an IU that matches this version. We need to use a version range.
				//
				Version low = VersionHelper.replaceQualifier(version, "0"); //$NON-NLS-1$
				Version high = Version.createOSGi(version.getMajor(), version.getMinor(), version.getMicro() + 1);
				query = new InstallableUnitQuery(id, new VersionRange(low, true, high, false));
			}
			else
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

		collector = results.query(query, collector, monitor);
		if(collector.size() == 0)
			collector = publisherInfo.getMetadataRepository().query(query, collector, null);
		if(collector.size() == 0 && publisherInfo.getContextMetadataRepository() != null)
			collector = publisherInfo.getContextMetadataRepository().query(query, collector, null);

		if(collector.size() == 1)
			return (IInstallableUnit)collector.iterator().next();
		return null;
	}

	private Map<IInstallableUnit, List<Category>> getFeatureToCategoryMappings(IPublisherInfo publisherInfo,
			IPublisherResult results, IProgressMonitor monitor) throws CoreException
	{
		HashMap<IInstallableUnit, List<Category>> mappings = new HashMap<IInstallableUnit, List<Category>>();

		Map<String, Category> categories = new HashMap<String, Category>();
		for(Map.Entry<String, String> entry : m_buildProperties.entrySet())
		{
			String key = entry.getKey();
			if(key.startsWith(PROP_CATEGORY_ID_PREFIX))
			{
				String id = key.substring(PROP_CATEGORY_ID_PREFIX.length());
				Category cat = categories.get(id);
				if(cat == null)
				{
					cat = new Category(id);
					categories.put(id, cat);
				}
				cat.setLabel(entry.getValue());
			}
		}

		for(Map.Entry<String, String> entry : m_buildProperties.entrySet())
		{
			String key = entry.getKey();
			if(key.startsWith(PROP_CATEGORY_DESCRIPTION_PREFIX))
			{
				String id = key.substring(PROP_CATEGORY_DESCRIPTION_PREFIX.length());
				Category cat = categories.get(id);
				if(cat != null)
					cat.setDescription(entry.getValue());
				continue;
			}

			if(key.startsWith(PROP_CATEGORY_MEMBERS_PREFIX))
			{
				String id = key.substring(PROP_CATEGORY_MEMBERS_PREFIX.length());
				Category cat = categories.get(id);
				if(cat == null)
					continue;

				for(String name : TextUtils.splitAndTrim(entry.getValue(), ",")) //$NON-NLS-1$
				{
					Version version = null;
					Matcher m = s_idAndVersionPattern.matcher(name);
					if(m.matches())
					{
						name = m.group(1);
						version = Version.create(m.group(2));
					}

					IInstallableUnit iu = getFeatureIU(name, version, publisherInfo, results, monitor);
					if(iu == null)
						continue;

					List<Category> catList = mappings.get(iu);
					if(catList == null)
					{
						catList = new ArrayList<Category>();
						mappings.put(iu, catList);
						catList.add(cat);
					}
					else if(!catList.contains(cat))
						catList.add(cat);
				}
				continue;
			}
		}

		List<Category> defaultCategoryList = s_defaultCategoryList;
		String defaultCategory = m_buildProperties.get(PROP_CATEGORY_DEFAULT);
		if(defaultCategory != null)
		{
			Category cat = categories.get(defaultCategory);
			if(cat != null)
				defaultCategoryList = Collections.singletonList(cat);
		}

		try
		{
			SiteModel site = SiteReader.getSite(new File(m_projectRoot, "category.xml")); //$NON-NLS-1$
			for(SiteFeature feature : site.getFeatures())
			{
				IInstallableUnit iu = getFeatureIU(feature.getFeatureIdentifier(),
						Version.create(feature.getFeatureVersion()), publisherInfo, results, monitor);
				if(iu == null)
					continue;

				for(String id : feature.getCategoryNames())
				{
					Category cat = categories.get(id);
					if(cat == null)
					{
						SiteCategory siteCat = site.getCategory(id);
						if(siteCat == null)
							continue;

						cat = new Category(id);
						cat.setDescription(siteCat.getDescription());
						cat.setLabel(siteCat.getLabel());
						categories.put(id, cat);
					}
					List<Category> catList = mappings.get(iu);
					if(catList == null)
					{
						catList = new ArrayList<Category>();
						mappings.put(iu, catList);
						catList.add(cat);
					}
					else if(!catList.contains(cat))
						catList.add(cat);
				}
			}
		}
		catch(FileNotFoundException e)
		{
			// This is expected. Just ignore
		}

		for(VersionedName fe : m_featureEntries)
		{
			IInstallableUnit iu = getFeatureIU(fe.getId(), fe.getVersion(), publisherInfo, results, monitor);
			if(iu == null || mappings.containsKey(iu))
				continue;

			mappings.put(iu, defaultCategoryList);
		}
		return mappings;
	}
}
