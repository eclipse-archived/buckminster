/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.aggregator.engine.maven;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.aggregator.engine.maven.pom.Build;
import org.eclipse.buckminster.aggregator.engine.maven.pom.CiManagement;
import org.eclipse.buckminster.aggregator.engine.maven.pom.Contributor;
import org.eclipse.buckminster.aggregator.engine.maven.pom.ContributorsType;
import org.eclipse.buckminster.aggregator.engine.maven.pom.DependenciesType;
import org.eclipse.buckminster.aggregator.engine.maven.pom.Dependency;
import org.eclipse.buckminster.aggregator.engine.maven.pom.DependencyManagement;
import org.eclipse.buckminster.aggregator.engine.maven.pom.Developer;
import org.eclipse.buckminster.aggregator.engine.maven.pom.DevelopersType;
import org.eclipse.buckminster.aggregator.engine.maven.pom.DistributionManagement;
import org.eclipse.buckminster.aggregator.engine.maven.pom.IssueManagement;
import org.eclipse.buckminster.aggregator.engine.maven.pom.License;
import org.eclipse.buckminster.aggregator.engine.maven.pom.LicensesType;
import org.eclipse.buckminster.aggregator.engine.maven.pom.MailingList;
import org.eclipse.buckminster.aggregator.engine.maven.pom.MailingListsType;
import org.eclipse.buckminster.aggregator.engine.maven.pom.ModulesType;
import org.eclipse.buckminster.aggregator.engine.maven.pom.Organization;
import org.eclipse.buckminster.aggregator.engine.maven.pom.Parent;
import org.eclipse.buckminster.aggregator.engine.maven.pom.PluginRepositoriesType;
import org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage;
import org.eclipse.buckminster.aggregator.engine.maven.pom.Prerequisites;
import org.eclipse.buckminster.aggregator.engine.maven.pom.Profile;
import org.eclipse.buckminster.aggregator.engine.maven.pom.ProfilesType;
import org.eclipse.buckminster.aggregator.engine.maven.pom.PropertiesType;
import org.eclipse.buckminster.aggregator.engine.maven.pom.Reporting;
import org.eclipse.buckminster.aggregator.engine.maven.pom.ReportsType;
import org.eclipse.buckminster.aggregator.engine.maven.pom.RepositoriesType;
import org.eclipse.buckminster.aggregator.engine.maven.pom.Repository;
import org.eclipse.buckminster.aggregator.engine.maven.pom.Scm;
import org.eclipse.buckminster.aggregator.engine.maven.pom.impl.ModelImpl;
import org.eclipse.buckminster.aggregator.util.GeneralUtils;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap.Entry;
import org.eclipse.emf.ecore.xml.type.impl.AnyTypeImpl;

/**
 * @author Filip Hrbek (filip.hrbek@cloudsmith.com)
 * 
 */
public class ResolvedModel extends ResolvedModelBase
{
	private String m_repoRoot;

	private ModelImpl m_original;

	private Map<String, String> m_propertyMap;

	private String m_artifactId;

	private boolean m_artifactIdResolved;

	private Build m_build;

	private boolean m_buildResolved;

	private CiManagement m_ciManagement;

	private boolean m_ciManagementResolved;

	private ContributorsType m_contributors;

	private boolean m_contributorsResolved;

	private DependenciesType m_dependencies;

	private boolean m_dependenciesResolved;

	private DependencyManagement m_dependencyManagement;

	private boolean m_dependencyManagementResolved;

	private String m_description;

	private boolean m_descriptionResolved;

	private DevelopersType m_developers;

	private boolean m_developersResolved;

	private DistributionManagement m_distributionManagement;

	private boolean m_distributionManagementResolved;

	private String m_groupId;

	private boolean m_groupIdResolved;

	private String m_inceptionYear;

	private boolean m_inceptionYearResolved;

	private IssueManagement m_issueManagement;

	private boolean m_issueManagementResolved;

	private LicensesType m_licenses;

	private boolean m_licensesResolved;

	private MailingListsType m_mailingLists;

	private boolean m_mailingListsResolved;

	private String m_modelVersion;

	private boolean m_modelVersionResolved;

	private ModulesType m_modules;

	private boolean m_modulesResolved;

	private String m_name;

	private boolean m_nameResolved;

	private Organization m_organization;

	private boolean m_organizationResolved;

	private String m_packaging;

	private boolean m_packagingResolved;

	private PluginRepositoriesType m_pluginRepositories;

	private boolean m_pluginRepositoriesResolved;

	private Prerequisites m_prerequisites;

	private boolean m_prerequisitesResolved;

	private ProfilesType m_profiles;

	private boolean m_profilesResolved;

	private Reporting m_reporting;

	private boolean m_reportingResolved;

	private RepositoriesType m_repositories;

	private boolean m_repositoriesResolved;

	private Scm m_scm;

	private boolean m_scmResolved;

	private String m_url;

	private boolean m_urlResolved;

	private String m_version;

	private boolean m_versionResolved;

	public ResolvedModel(String repoRoot, ModelImpl original)
	{
		m_repoRoot = repoRoot;
		m_original = original;

		m_propertyMap = null;
	}

	public String getArtifactId()
	{
		if(!m_artifactIdResolved)
		{
			m_artifactIdResolved = true;
			m_artifactId = resolveFeature(PomPackage.MODEL__ARTIFACT_ID);
		}

		return m_artifactId;
	}

	public Build getBuild()
	{
		if(!m_buildResolved)
		{
			m_buildResolved = true;
			m_build = resolveFeature(PomPackage.MODEL__BUILD);
		}

		return m_build;
	}

	public CiManagement getCiManagement()
	{
		if(!m_ciManagementResolved)
		{
			m_ciManagementResolved = true;
			m_ciManagement = resolveFeature(PomPackage.MODEL__CI_MANAGEMENT);
		}

		return m_ciManagement;
	}

	public ContributorsType getContributors()
	{
		if(!m_contributorsResolved)
		{
			m_contributorsResolved = true;
			m_contributors = resolveWrappedListFeature(PomPackage.MODEL__CONTRIBUTORS,
					PomPackage.CONTRIBUTORS_TYPE__CONTRIBUTOR, ContributorsType.class, Contributor.class);
		}

		return m_contributors;
	}

	public DependenciesType getDependencies()
	{
		if(!m_dependenciesResolved)
		{
			m_dependenciesResolved = true;
			m_dependencies = resolveWrappedListFeature(PomPackage.MODEL__DEPENDENCIES,
					PomPackage.DEPENDENCIES_TYPE__DEPENDENCY, DependenciesType.class, Dependency.class);
		}

		return m_dependencies;
	}

	public DependencyManagement getDependencyManagement()
	{
		if(!m_dependencyManagementResolved)
		{
			m_dependencyManagementResolved = true;
			m_dependencyManagement = resolveFeature(PomPackage.MODEL__DEPENDENCY_MANAGEMENT);
		}

		return m_dependencyManagement;
	}

	public String getDescription()
	{
		if(!m_descriptionResolved)
		{
			m_descriptionResolved = true;
			m_description = resolveFeature(PomPackage.MODEL__DESCRIPTION);
		}

		return m_description;
	}

	public DevelopersType getDevelopers()
	{
		if(!m_developersResolved)
		{
			m_developersResolved = true;
			m_developers = resolveWrappedListFeature(PomPackage.MODEL__DEVELOPERS,
					PomPackage.DEVELOPERS_TYPE__DEVELOPER, DevelopersType.class, Developer.class);
		}

		return m_developers;
	}

	public DistributionManagement getDistributionManagement()
	{
		if(!m_distributionManagementResolved)
		{
			m_distributionManagementResolved = true;
			m_distributionManagement = resolveFeature(PomPackage.MODEL__DISTRIBUTION_MANAGEMENT);
		}

		return m_distributionManagement;
	}

	public String getGroupId()
	{
		if(!m_groupIdResolved)
		{
			m_groupIdResolved = true;
			m_groupId = resolveFeature(PomPackage.MODEL__GROUP_ID);
		}

		return m_groupId;
	}

	public String getInceptionYear()
	{
		if(!m_inceptionYearResolved)
		{
			m_inceptionYearResolved = true;
			m_inceptionYear = resolveFeature(PomPackage.MODEL__INCEPTION_YEAR);
		}

		return m_inceptionYear;
	}

	public IssueManagement getIssueManagement()
	{
		if(!m_issueManagementResolved)
		{
			m_issueManagementResolved = true;
			m_issueManagement = resolveFeature(PomPackage.MODEL__ISSUE_MANAGEMENT);
		}

		return m_issueManagement;
	}

	public LicensesType getLicenses()
	{
		if(!m_licensesResolved)
		{
			m_licensesResolved = true;
			m_licenses = resolveWrappedListFeature(PomPackage.MODEL__LICENSES, PomPackage.LICENSES_TYPE__LICENSE,
					LicensesType.class, License.class);
		}

		return m_licenses;
	}

	public MailingListsType getMailingLists()
	{
		if(!m_mailingListsResolved)
		{
			m_mailingListsResolved = true;
			m_mailingLists = resolveWrappedListFeature(PomPackage.MODEL__MAILING_LISTS,
					PomPackage.MAILING_LISTS_TYPE__MAILING_LIST, MailingListsType.class, MailingList.class);
		}

		return m_mailingLists;
	}

	public String getModelVersion()
	{
		if(!m_modelVersionResolved)
		{
			m_modelVersionResolved = true;
			m_modelVersion = resolveFeature(PomPackage.MODEL__MODEL_VERSION);
		}

		return m_modelVersion;
	}

	public ModulesType getModules()
	{
		if(!m_modulesResolved)
		{
			m_modulesResolved = true;
			m_modules = resolveWrappedListFeature(PomPackage.MODEL__MODULES, PomPackage.MODULES_TYPE__MODULE,
					ModulesType.class, String.class);
		}

		return m_modules;
	}

	public String getName()
	{
		if(!m_nameResolved)
		{
			m_nameResolved = true;
			m_name = resolveFeature(PomPackage.MODEL__NAME);
		}

		return m_name;
	}

	public Organization getOrganization()
	{
		if(!m_organizationResolved)
		{
			m_organizationResolved = true;
			m_organization = resolveFeature(PomPackage.MODEL__ORGANIZATION);
		}

		return m_organization;
	}

	public String getPackaging()
	{
		if(!m_packagingResolved)
		{
			m_packagingResolved = true;
			m_packaging = resolveFeature(PomPackage.MODEL__PACKAGING);
		}

		return m_packaging;
	}

	public Parent getParent()
	{
		return m_original.getParent();
	}

	public PluginRepositoriesType getPluginRepositories()
	{
		if(!m_pluginRepositoriesResolved)
		{
			m_pluginRepositoriesResolved = true;
			m_pluginRepositories = resolveWrappedListFeature(PomPackage.MODEL__PLUGIN_REPOSITORIES,
					PomPackage.PLUGIN_REPOSITORIES_TYPE__PLUGIN_REPOSITORY, PluginRepositoriesType.class,
					Repository.class);
		}

		return m_pluginRepositories;
	}

	public Prerequisites getPrerequisites()
	{
		if(!m_prerequisitesResolved)
		{
			m_prerequisitesResolved = true;
			m_prerequisites = resolveFeature(PomPackage.MODEL__PREREQUISITES);
		}

		return m_prerequisites;
	}

	public ProfilesType getProfiles()
	{
		if(!m_profilesResolved)
		{
			m_profilesResolved = true;
			m_profiles = resolveWrappedListFeature(PomPackage.MODEL__PROFILES, PomPackage.PROFILES_TYPE__PROFILE,
					ProfilesType.class, Profile.class);
		}

		return m_profiles;
	}

	public PropertiesType getProperties()
	{
		throw new UnsupportedOperationException("Use POM.getFullPropertyMap() instead");
	}

	public Map<String, String> getPropertyMap() throws CoreException
	{
		if(m_propertyMap == null)
		{
			m_propertyMap = new HashMap<String, String>();

			String version = getVersion();
			m_propertyMap.put("version", version);
			m_propertyMap.put("pom.version", version);
			m_propertyMap.put("project.version", version);

			String name = getName();
			m_propertyMap.put("pom.name", name);
			m_propertyMap.put("project.name", name);

			m_propertyMap.putAll(getPropertiesAsMap());

			Parent parent = getParent();
			if(parent != null)
			{
				m_propertyMap.put("parent.version", parent.getVersion());

				ResolvedModel parentModel = POM.getPOM(m_repoRoot, parent.getGroupId(), parent.getArtifactId(),
						parent.getVersion()).getResolvedProject();

				for(Map.Entry<String, String> entry : parentModel.getPropertyMap().entrySet())
					if(!m_propertyMap.containsKey(entry.getKey()))
						m_propertyMap.put(entry.getKey(), entry.getValue());
			}
		}

		return m_propertyMap;
	}

	public Reporting getReporting()
	{
		if(!m_reportingResolved)
		{
			m_reportingResolved = true;
			m_reporting = resolveFeature(PomPackage.MODEL__REPORTING);
		}

		return m_reporting;
	}

	public ReportsType getReports()
	{
		throw new UnsupportedOperationException("reports resolution is not supported in this version");
	}

	public RepositoriesType getRepositories()
	{
		if(!m_repositoriesResolved)
		{
			m_repositoriesResolved = true;
			m_repositories = resolveWrappedListFeature(PomPackage.MODEL__REPOSITORIES,
					PomPackage.REPOSITORIES_TYPE__REPOSITORY, RepositoriesType.class, Repository.class);
		}

		return m_repositories;
	}

	public Scm getScm()
	{
		if(!m_scmResolved)
		{
			m_scmResolved = true;
			m_scm = resolveFeature(PomPackage.MODEL__SCM);
		}

		return m_scm;
	}

	public String getUrl()
	{
		if(!m_urlResolved)
		{
			m_urlResolved = true;
			m_url = resolveFeature(PomPackage.MODEL__URL);
		}

		return m_url;
	}

	public String getVersion()
	{
		if(!m_versionResolved)
		{
			m_versionResolved = true;
			m_version = resolveFeature(PomPackage.MODEL__VERSION);
		}

		return m_version;
	}

	public boolean isSetPackaging()
	{
		return getPackaging() != null;
	}

	private Map<String, String> getPropertiesAsMap() throws CoreException
	{
		Map<String, String> propertyMap = new HashMap<String, String>();
		PropertiesType propertiesType = m_original.getProperties();
		if(propertiesType != null)
		{
			FeatureMap properties = m_original.getProperties().getAny();
			for(int i = properties.size() - 1; i >= 0; i--)
			{
				Entry entry = properties.get(i);
				FeatureMap valueMap = ((AnyTypeImpl)entry.getValue()).getMixed();
				String value;
				switch(valueMap.size())
				{
				case 0:
					value = null;
					break;
				case 1:
					value = (String)((AnyTypeImpl)entry.getValue()).getMixed().getValue(0);
					break;
				default:
					throw BuckminsterException.fromMessage("Unexpected property map size: %d", valueMap.size());
				}

				propertyMap.put(entry.getEStructuralFeature().getName(), value);
			}
		}

		return propertyMap;
	}

	@SuppressWarnings("unchecked")
	private <T> T resolveFeature(int featureID)
	{
		try
		{
			T result = (T)m_original.eGet(featureID, false, false);
			if(result instanceof String)
				result = (T)GeneralUtils.trimmedOrNull((String)result);

			if(result == null)
			{
				Parent parent = getParent();
				if(parent != null)
					result = (T)POM.getPOM(m_repoRoot, parent.getGroupId(), parent.getArtifactId(), parent.getVersion()).getResolvedProject().resolveFeature(
							featureID);
			}

			if(result instanceof String)
				result = (T)POM.expandProperties((String)result, getPropertyMap());
			return result;
		}
		catch(CoreException e)
		{
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	private <T, L> T resolveWrappedListFeature(int wrapperID, int featureID, Class<T> wrapperClass,
			Class<L> featureClass)
	{
		try
		{
			ModelImpl model = m_original;
			T result = wrapperClass.newInstance();
			List<L> list = new EObjectContainmentEList<L>(featureClass, (InternalEObject)result, featureID);
			((EObjectImpl)result).eSet(wrapperID, list);
			while(model != null)
			{
				T semiResult = (T)model.eGet(featureID, false, false);
				if(semiResult != null)
				{
					List<L> semiList = (List<L>)((EObjectImpl)semiResult).eGet(wrapperID, false, false);
					if(semiList != null)
						list.addAll(semiList);
				}
				Parent parent = model.getParent();
				if(parent != null)
					model = (ModelImpl)POM.getPOM(m_repoRoot, parent.getGroupId(), parent.getArtifactId(),
							parent.getVersion()).getProject();
				else
					model = null;
			}

			return result;
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	}
}
