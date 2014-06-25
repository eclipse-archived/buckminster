/*******************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.pde.tasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.core.version.VersionHelper;
import org.eclipse.buckminster.pde.IPDEConstants;
import org.eclipse.buckminster.pde.PDEPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.equinox.internal.p2.metadata.IRequiredCapability;
import org.eclipse.equinox.internal.p2.updatesite.SiteCategory;
import org.eclipse.equinox.internal.p2.updatesite.SiteFeature;
import org.eclipse.equinox.internal.p2.updatesite.SiteIU;
import org.eclipse.equinox.internal.p2.updatesite.SiteModel;
import org.eclipse.equinox.p2.metadata.IArtifactKey;
import org.eclipse.equinox.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.p2.metadata.IProvidedCapability;
import org.eclipse.equinox.p2.metadata.IRequirement;
import org.eclipse.equinox.p2.metadata.IVersionedId;
import org.eclipse.equinox.p2.metadata.MetadataFactory;
import org.eclipse.equinox.p2.metadata.MetadataFactory.InstallableUnitDescription;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.equinox.p2.metadata.VersionRange;
import org.eclipse.equinox.p2.metadata.VersionedId;
import org.eclipse.equinox.p2.publisher.AbstractPublisherAction;
import org.eclipse.equinox.p2.publisher.IPublisherInfo;
import org.eclipse.equinox.p2.publisher.IPublisherResult;
import org.eclipse.equinox.p2.query.IQuery;
import org.eclipse.equinox.p2.query.IQueryResult;
import org.eclipse.equinox.p2.query.QueryUtil;
import org.eclipse.equinox.spi.p2.publisher.LocalizationHelper;
import org.eclipse.equinox.spi.p2.publisher.PublisherHelper;

/**
 * Action which processes a feature.xml, build.properties, and feature
 * localization files and generates categories, mirrors url, and referenced
 * repositories for a p2 MDR. The process relies on IUs for the various features
 * to have already been generated.
 */
@SuppressWarnings("restriction")
public class CategoriesAction extends AbstractPublisherAction {
	private static class Category {
		private String description;

		private String label;

		private final String name;

		Category(String name) {
			this.name = name;
		}

		@Override
		public boolean equals(Object value) {
			return value instanceof Category && ((Category) value).name.equals(name);
		}

		public String getDescription() {
			return description;
		}

		public String getLabel() {
			return label;
		}

		public String getName() {
			return name;
		}

		@Override
		public int hashCode() {
			return name.hashCode();
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public void setLabel(String label) {
			this.label = label;
		}
	}

	private static final String PROP_CATEGORY_DESCRIPTION_PREFIX = "category.description."; //$NON-NLS-1$

	private static final String PROP_CATEGORY_MEMBERS_PREFIX = "category.members."; //$NON-NLS-1$

	private static final String PROP_CATEGORY_DEFAULT = "category.default"; //$NON-NLS-1$

	private static final String PROP_CATEGORY_ID_PREFIX = "category.id."; //$NON-NLS-1$

	/**
	 * This category is excluded from categorized view.
	 */
	private static final String HIDDEN_CATEGORY_ID = "hidden_category"; //$NON-NLS-1$

	private static final Pattern idAndVersionPattern = Pattern.compile("^(\\S)_([0-9]+(?:\\.[0-9]+){0,2}(?:\\.[A-Za-z0-9_-]))$"); //$NON-NLS-1$

	private static final List<Category> defaultCategoryList;

	static {
		Category defaultCategory = new Category("Default"); //$NON-NLS-1$
		defaultCategory.setDescription("Default category for otherwise uncategorized features"); //$NON-NLS-1$
		defaultCategory.setLabel("Uncategorized"); //$NON-NLS-1$
		defaultCategoryList = Collections.singletonList(defaultCategory);
	}

	/**
	 * Return localizations for %xxx properties found in <code>properties</code>
	 * .
	 * 
	 * @param properties
	 * @param featureRoot
	 * @return
	 */
	private static Map<Locale, Map<String, String>> getLocalizations(Map<String, String> properties, File featureRoot) {
		if (featureRoot == null || properties == null)
			return Collections.emptyMap();

		List<String> msgKeys = null;
		for (String value : properties.values()) {
			if (value != null && value.length() > 1 && value.charAt(0) == '%') {
				if (msgKeys == null)
					msgKeys = new ArrayList<String>();
				msgKeys.add(value.substring(1));
			}
		}
		if (msgKeys == null)
			return Collections.emptyMap();

		Map<Locale, Map<String, String>> localizations;
		String[] keyStrings = msgKeys.toArray(new String[msgKeys.size()]);
		if (featureRoot.isDirectory())
			localizations = LocalizationHelper.getDirPropertyLocalizations(featureRoot, "feature", null, keyStrings); //$NON-NLS-1$
		else if (featureRoot.getName().endsWith(".jar")) //$NON-NLS-1$
			localizations = LocalizationHelper.getJarPropertyLocalizations(featureRoot, "feature", null, keyStrings); //$NON-NLS-1$
		else
			localizations = Collections.emptyMap();
		return localizations;
	}

	private static boolean isKeyReference(String value, String key) {
		return value != null && value.length() > 1 && value.charAt(0) == '%' && value.substring(1).equals(key);
	}

	private final Map<Locale, Map<String, String>> localizations;

	private final Map<String, String> buildProperties;

	private final List<IVersionedId> featureEntries;

	private final File projectRoot;

	public CategoriesAction(File projectRoot, Map<String, String> buildProperties, List<IVersionedId> featureEntries) throws CoreException {
		this.buildProperties = buildProperties;
		this.localizations = getLocalizations(buildProperties, projectRoot);
		this.featureEntries = featureEntries;
		this.projectRoot = projectRoot;
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
	public IInstallableUnit createCategoryIU(Category category, Set<IInstallableUnit> featureIUs, IInstallableUnit parentCategory) {
		InstallableUnitDescription cat = new MetadataFactory.InstallableUnitDescription();
		cat.setSingleton(true);
		String categoryId = category.getName();
		cat.setId(categoryId);
		cat.setProperty(IInstallableUnit.PROP_NAME, category.getLabel());
		cat.setProperty(IInstallableUnit.PROP_DESCRIPTION, category.getDescription());

		ArrayList<IVersionedId> fts = new ArrayList<IVersionedId>(featureIUs.size());
		ArrayList<IVersionedId> bds = new ArrayList<IVersionedId>(featureIUs.size());
		ArrayList<IRequirement> reqsConfigurationUnits = new ArrayList<IRequirement>(featureIUs.size());
		for (IInstallableUnit iu : featureIUs) {
			VersionedId vn = new VersionedId(iu.getId(), iu.getVersion());
			if (iu.getId().endsWith(IPDEConstants.FEATURE_GROUP))
				fts.add(vn);
			else
				bds.add(vn);
			VersionRange range = new VersionRange(iu.getVersion(), true, iu.getVersion(), true);
			reqsConfigurationUnits.add(MetadataFactory.createRequirement(IInstallableUnit.NAMESPACE_IU_ID, iu.getId(), range, iu.getFilter(), false,
					false));
		}
		FeatureVersionSuffixGenerator suffixGen = new FeatureVersionSuffixGenerator(-1, -1);
		Version categoryVersion = Version.createOSGi(0, 0, 1, suffixGen.generateSuffix(fts, bds));
		cat.setVersion(categoryVersion);

		// note that update sites don't currently support nested categories, but
		// it may be useful to add in the future
		if (parentCategory != null) {
			reqsConfigurationUnits.add(MetadataFactory.createRequirement(IInstallableUnit.NAMESPACE_IU_ID, parentCategory.getId(),
					VersionRange.emptyRange, parentCategory.getFilter(), false, false));
		}
		cat.setRequirements(reqsConfigurationUnits.toArray(new IRequiredCapability[reqsConfigurationUnits.size()]));

		// Create set of provided capabilities
		ArrayList<IProvidedCapability> providedCapabilities = new ArrayList<IProvidedCapability>();
		providedCapabilities.add(PublisherHelper.createSelfCapability(categoryId, categoryVersion));

		for (Map.Entry<Locale, Map<String, String>> locEntry : localizations.entrySet()) {
			Locale locale = locEntry.getKey();
			for (Map.Entry<String, String> entry : locEntry.getValue().entrySet()) {
				String key = entry.getKey();

				// Is the category using this key?
				//
				if (isKeyReference(category.getLabel(), key) || isKeyReference(category.getDescription(), key))
					cat.setProperty(locale.toString() + '.' + key, entry.getValue());
			}
			providedCapabilities.add(PublisherHelper.makeTranslationCapability(categoryId, locale));
		}

		cat.setCapabilities(providedCapabilities.toArray(new IProvidedCapability[providedCapabilities.size()]));

		cat.setArtifacts(new IArtifactKey[0]);
		cat.setProperty(InstallableUnitDescription.PROP_TYPE_CATEGORY, "true"); //$NON-NLS-1$
		return MetadataFactory.createInstallableUnit(cat);
	}

	@Override
	public IStatus perform(IPublisherInfo publisherInfo, IPublisherResult results, IProgressMonitor monitor) {
		Map<Category, Set<IInstallableUnit>> categoriesToFeatureIUs = new HashMap<Category, Set<IInstallableUnit>>();
		Map<IInstallableUnit, List<Category>> featuresToCategories;
		try {
			featuresToCategories = getFeatureToCategoryMappings(publisherInfo, results, monitor);
		} catch (CoreException e) {
			return e.getStatus();
		}
		for (Map.Entry<IInstallableUnit, List<Category>> entry : featuresToCategories.entrySet()) {
			IInstallableUnit iu = entry.getKey();
			for (Category category : entry.getValue()) {
				Set<IInstallableUnit> featureIUs = categoriesToFeatureIUs.get(category);
				if (featureIUs == null) {
					featureIUs = new HashSet<IInstallableUnit>();
					categoriesToFeatureIUs.put(category, featureIUs);
				}
				featureIUs.add(iu);
			}
		}
		generateCategoryIUs(categoriesToFeatureIUs, results);
		return Status.OK_STATUS;
	}

	private StringBuilder collectIDs(Set<IInstallableUnit> iUs) {
		StringBuilder strBuilder = new StringBuilder();
		for (Iterator<IInstallableUnit> iterator = iUs.iterator(); iterator.hasNext();) {
			IInstallableUnit iInstallableUnit = iterator.next();
			strBuilder.append(iInstallableUnit.getId());
			if (iterator.hasNext()) {
				strBuilder.append(", "); //$NON-NLS-1$
			}
		}
		return strBuilder;
	}

	/**
	 * Generates IUs corresponding to update site categories.<br>
	 * Note: "hidden-category" is explicitly excluded.
	 * 
	 * @param categoriesToFeatures
	 *            Map of Category ->Set (Feature IUs in that category).
	 * @param result
	 *            The generator result being built
	 */
	private void generateCategoryIUs(Map<Category, Set<IInstallableUnit>> categoriesToFeatures, IPublisherResult result) {
		for (Map.Entry<Category, Set<IInstallableUnit>> entry : categoriesToFeatures.entrySet()) {
			Category category = entry.getKey();
			Set<IInstallableUnit> iUs = entry.getValue();
			if (!HIDDEN_CATEGORY_ID.equals(category.getName())) {
				result.addIU(createCategoryIU(category, iUs, null), IPublisherResult.NON_ROOT);
			} else {
				if (PDEPlugin.getLogger().isDebugEnabled()) {
					StringBuilder strBuilder = collectIDs(iUs);
					PDEPlugin
							.getLogger()
							.debug("Category %s is used. Following features will be hidden in categorized view: %s", HIDDEN_CATEGORY_ID, strBuilder.toString()); //$NON-NLS-1$
				}
			}
		}
	}

	private IInstallableUnit getFeatureIU(String name, Version version, IPublisherInfo publisherInfo, IPublisherResult results,
			IProgressMonitor monitor) {
		if (monitor.isCanceled())
			throw new OperationCanceledException();

		String id = name + ".feature.group"; //$NON-NLS-1$
		IQuery<IInstallableUnit> query = null;
		if (version == null || version.equals(Version.emptyVersion))
			query = QueryUtil.createLatestQuery(QueryUtil.createIUQuery(id));
		else {
			String qual = VersionHelper.getQualifier(version);
			if (qual != null && qual.contains("qualifier")) //$NON-NLS-1$
			{
				// We won't find an IU that matches this version. We need to use
				// a version range.
				//
				Version low = VersionHelper.replaceQualifier(version, null);
				org.osgi.framework.Version ov = new org.osgi.framework.Version(version.toString());
				query = QueryUtil.createIUQuery(id, new VersionRange(low, true, Version.createOSGi(ov.getMajor(), ov.getMinor(), ov.getMicro() + 1),
						false));
			} else
				query = QueryUtil.createIUQuery(id, version);

			query = QueryUtil.createLimitQuery(query, 1);
		}

		IQueryResult<IInstallableUnit> result = results.query(query, monitor);
		if (result.isEmpty())
			result = publisherInfo.getMetadataRepository().query(query, null);
		if (result.isEmpty() && publisherInfo.getContextMetadataRepository() != null)
			result = publisherInfo.getContextMetadataRepository().query(query, null);

		if (!result.isEmpty())
			return result.iterator().next();
		return null;
	}

	private Map<IInstallableUnit, List<Category>> getFeatureToCategoryMappings(IPublisherInfo publisherInfo, IPublisherResult results,
			IProgressMonitor monitor) throws CoreException {
		HashMap<IInstallableUnit, List<Category>> mappings = new HashMap<IInstallableUnit, List<Category>>();

		Map<String, Category> categories = new HashMap<String, Category>();
		for (Map.Entry<String, String> entry : buildProperties.entrySet()) {
			String key = entry.getKey();
			if (key.startsWith(PROP_CATEGORY_ID_PREFIX)) {
				String id = key.substring(PROP_CATEGORY_ID_PREFIX.length());
				Category cat = categories.get(id);
				if (cat == null) {
					cat = new Category(id);
					categories.put(id, cat);
				}
				cat.setLabel(entry.getValue());
			}
		}

		for (Map.Entry<String, String> entry : buildProperties.entrySet()) {
			String key = entry.getKey();
			if (key.startsWith(PROP_CATEGORY_DESCRIPTION_PREFIX)) {
				String id = key.substring(PROP_CATEGORY_DESCRIPTION_PREFIX.length());
				Category cat = categories.get(id);
				if (cat != null)
					cat.setDescription(entry.getValue());
				continue;
			}

			if (key.startsWith(PROP_CATEGORY_MEMBERS_PREFIX)) {
				String id = key.substring(PROP_CATEGORY_MEMBERS_PREFIX.length());
				Category cat = categories.get(id);
				if (cat == null)
					continue;

				for (String name : TextUtils.splitAndTrim(entry.getValue(), ",")) //$NON-NLS-1$
				{
					Version version = null;
					Matcher m = idAndVersionPattern.matcher(name);
					if (m.matches()) {
						name = m.group(1);
						version = Version.create(m.group(2));
					}

					IInstallableUnit iu = getFeatureIU(name, version, publisherInfo, results, monitor);
					if (iu == null)
						continue;

					List<Category> catList = mappings.get(iu);
					if (catList == null) {
						catList = new ArrayList<Category>();
						mappings.put(iu, catList);
						catList.add(cat);
					} else if (!catList.contains(cat))
						catList.add(cat);
				}
				continue;
			}
		}

		List<Category> defaultCategories = defaultCategoryList;
		String defaultCategory = buildProperties.get(PROP_CATEGORY_DEFAULT);
		if (defaultCategory != null) {
			Category cat = categories.get(defaultCategory);
			if (cat != null)
				defaultCategories = Collections.singletonList(cat);
		}

		try {
			SiteModel site = SiteReader.getSite(new File(projectRoot, "category.xml")); //$NON-NLS-1$
			for (SiteFeature feature : site.getFeatures()) {
				IInstallableUnit iu = getFeatureIU(feature.getFeatureIdentifier(), Version.create(feature.getFeatureVersion()), publisherInfo,
						results, monitor);
				if (iu == null)
					continue;

				for (String id : feature.getCategoryNames()) {
					Category cat = categories.get(id);
					if (cat == null) {
						SiteCategory siteCat = site.getCategory(id);
						if (siteCat == null)
							continue;

						cat = new Category(id);
						cat.setDescription(siteCat.getDescription());
						cat.setLabel(siteCat.getLabel());
						categories.put(id, cat);
					}
					List<Category> catList = mappings.get(iu);
					if (catList == null) {
						catList = new ArrayList<Category>();
						mappings.put(iu, catList);
						catList.add(cat);
					} else if (!catList.contains(cat))
						catList.add(cat);
				}
			}
			for (SiteIU siteIU : site.getIUs()) {
				for (IInstallableUnit iu : getIUs(siteIU, publisherInfo, results)) {
					for (String id : siteIU.getCategoryNames()) {
						Category cat = categories.get(id);
						if (cat == null) {
							SiteCategory siteCat = site.getCategory(id);
							if (siteCat == null)
								continue;

							cat = new Category(id);
							cat.setDescription(siteCat.getDescription());
							cat.setLabel(siteCat.getLabel());
							categories.put(id, cat);
						}
						List<Category> catList = mappings.get(iu);
						if (catList == null) {
							catList = new ArrayList<Category>();
							mappings.put(iu, catList);
							catList.add(cat);
						} else if (!catList.contains(cat))
							catList.add(cat);
					}
				}
			}
		} catch (FileNotFoundException e) {
			// This is expected. Just ignore
		}

		for (IVersionedId fe : featureEntries) {
			IInstallableUnit iu = getFeatureIU(fe.getId(), fe.getVersion(), publisherInfo, results, monitor);
			if (iu == null || mappings.containsKey(iu))
				continue;

			mappings.put(iu, defaultCategories);
		}
		return mappings;
	}

	private Collection<IInstallableUnit> getIUs(SiteIU siteIU, IPublisherInfo publisherInfo, IPublisherResult results) {
		String id = siteIU.getID();
		String range = siteIU.getRange();
		String type = siteIU.getQueryType();
		String expression = siteIU.getQueryExpression();
		Object[] params = siteIU.getQueryParams();
		if (id == null && (type == null || expression == null))
			return Collections.emptyList();
		IQuery<IInstallableUnit> query = null;
		if (id != null) {
			VersionRange vRange = new VersionRange(range);
			query = QueryUtil.createIUQuery(id, vRange);
		} else if (type.equals("context")) { //$NON-NLS-1$
			query = QueryUtil.createQuery(expression, params);
		} else if (type.equals("match")) //$NON-NLS-1$
			query = QueryUtil.createMatchQuery(expression, params);
		if (query == null)
			return Collections.emptyList();
		IQueryResult<IInstallableUnit> queryResult = results.query(query, null);
		if (queryResult.isEmpty())
			queryResult = publisherInfo.getMetadataRepository().query(query, null);
		if (queryResult.isEmpty() && publisherInfo.getContextMetadataRepository() != null)
			queryResult = publisherInfo.getContextMetadataRepository().query(query, null);

		return queryResult.toUnmodifiableSet();
	}
}
