/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.engine.maven.pom;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each non-abstract class of
 * the model. <!-- end-user-doc -->
 * 
 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage
 * @generated
 */
public interface PomFactory extends EFactory
{
	/**
	 * The singleton instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	PomFactory eINSTANCE = org.eclipse.buckminster.aggregator.engine.maven.pom.impl.PomFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Activation</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Activation</em>'.
	 * @generated
	 */
	Activation createActivation();

	/**
	 * Returns a new object of class '<em>Activation File</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Activation File</em>'.
	 * @generated
	 */
	ActivationFile createActivationFile();

	/**
	 * Returns a new object of class '<em>Activation OS</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Activation OS</em>'.
	 * @generated
	 */
	ActivationOS createActivationOS();

	/**
	 * Returns a new object of class '<em>Activation Property</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Activation Property</em>'.
	 * @generated
	 */
	ActivationProperty createActivationProperty();

	/**
	 * Returns a new object of class '<em>Build</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Build</em>'.
	 * @generated
	 */
	Build createBuild();

	/**
	 * Returns a new object of class '<em>Build Base</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Build Base</em>'.
	 * @generated
	 */
	BuildBase createBuildBase();

	/**
	 * Returns a new object of class '<em>Ci Management</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Ci Management</em>'.
	 * @generated
	 */
	CiManagement createCiManagement();

	/**
	 * Returns a new object of class '<em>Configuration Type</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Configuration Type</em>'.
	 * @generated
	 */
	ConfigurationType createConfigurationType();

	/**
	 * Returns a new object of class '<em>Contributor</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Contributor</em>'.
	 * @generated
	 */
	Contributor createContributor();

	/**
	 * Returns a new object of class '<em>Contributors Type</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Contributors Type</em>'.
	 * @generated
	 */
	ContributorsType createContributorsType();

	/**
	 * Returns a new object of class '<em>Dependencies Type</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Dependencies Type</em>'.
	 * @generated
	 */
	DependenciesType createDependenciesType();

	/**
	 * Returns a new object of class '<em>Dependency</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Dependency</em>'.
	 * @generated
	 */
	Dependency createDependency();

	/**
	 * Returns a new object of class '<em>Dependency Management</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Dependency Management</em>'.
	 * @generated
	 */
	DependencyManagement createDependencyManagement();

	/**
	 * Returns a new object of class '<em>Deployment Repository</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Deployment Repository</em>'.
	 * @generated
	 */
	DeploymentRepository createDeploymentRepository();

	/**
	 * Returns a new object of class '<em>Developer</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Developer</em>'.
	 * @generated
	 */
	Developer createDeveloper();

	/**
	 * Returns a new object of class '<em>Developers Type</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Developers Type</em>'.
	 * @generated
	 */
	DevelopersType createDevelopersType();

	/**
	 * Returns a new object of class '<em>Distribution Management</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Distribution Management</em>'.
	 * @generated
	 */
	DistributionManagement createDistributionManagement();

	/**
	 * Returns a new object of class '<em>Document Root</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Document Root</em>'.
	 * @generated
	 */
	DocumentRoot createDocumentRoot();

	/**
	 * Returns a new object of class '<em>Excludes Type</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Excludes Type</em>'.
	 * @generated
	 */
	ExcludesType createExcludesType();

	/**
	 * Returns a new object of class '<em>Exclusion</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Exclusion</em>'.
	 * @generated
	 */
	Exclusion createExclusion();

	/**
	 * Returns a new object of class '<em>Exclusions Type</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Exclusions Type</em>'.
	 * @generated
	 */
	ExclusionsType createExclusionsType();

	/**
	 * Returns a new object of class '<em>Execution Goals Type</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Execution Goals Type</em>'.
	 * @generated
	 */
	ExecutionGoalsType createExecutionGoalsType();

	/**
	 * Returns a new object of class '<em>Executions Type</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Executions Type</em>'.
	 * @generated
	 */
	ExecutionsType createExecutionsType();

	/**
	 * Returns a new object of class '<em>Extension</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Extension</em>'.
	 * @generated
	 */
	Extension createExtension();

	/**
	 * Returns a new object of class '<em>Extensions Type</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Extensions Type</em>'.
	 * @generated
	 */
	ExtensionsType createExtensionsType();

	/**
	 * Returns a new object of class '<em>Filters Type</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Filters Type</em>'.
	 * @generated
	 */
	FiltersType createFiltersType();

	/**
	 * Returns a new object of class '<em>Goals Type</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Goals Type</em>'.
	 * @generated
	 */
	GoalsType createGoalsType();

	/**
	 * Returns a new object of class '<em>Includes Type</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Includes Type</em>'.
	 * @generated
	 */
	IncludesType createIncludesType();

	/**
	 * Returns a new object of class '<em>Issue Management</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Issue Management</em>'.
	 * @generated
	 */
	IssueManagement createIssueManagement();

	/**
	 * Returns a new object of class '<em>License</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>License</em>'.
	 * @generated
	 */
	License createLicense();

	/**
	 * Returns a new object of class '<em>Licenses Type</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Licenses Type</em>'.
	 * @generated
	 */
	LicensesType createLicensesType();

	/**
	 * Returns a new object of class '<em>Mailing List</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Mailing List</em>'.
	 * @generated
	 */
	MailingList createMailingList();

	/**
	 * Returns a new object of class '<em>Mailing Lists Type</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Mailing Lists Type</em>'.
	 * @generated
	 */
	MailingListsType createMailingListsType();

	/**
	 * Returns a new object of class '<em>Model</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Model</em>'.
	 * @generated
	 */
	Model createModel();

	/**
	 * Returns a new object of class '<em>Modules Type</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Modules Type</em>'.
	 * @generated
	 */
	ModulesType createModulesType();

	/**
	 * Returns a new object of class '<em>Notifier</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Notifier</em>'.
	 * @generated
	 */
	Notifier createNotifier();

	/**
	 * Returns a new object of class '<em>Notifiers Type</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Notifiers Type</em>'.
	 * @generated
	 */
	NotifiersType createNotifiersType();

	/**
	 * Returns a new object of class '<em>Organization</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Organization</em>'.
	 * @generated
	 */
	Organization createOrganization();

	/**
	 * Returns a new object of class '<em>Other Archives Type</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Other Archives Type</em>'.
	 * @generated
	 */
	OtherArchivesType createOtherArchivesType();

	/**
	 * Returns a new object of class '<em>Parent</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Parent</em>'.
	 * @generated
	 */
	Parent createParent();

	/**
	 * Returns a new object of class '<em>Plugin</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Plugin</em>'.
	 * @generated
	 */
	Plugin createPlugin();

	/**
	 * Returns a new object of class '<em>Plugin Execution</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Plugin Execution</em>'.
	 * @generated
	 */
	PluginExecution createPluginExecution();

	/**
	 * Returns a new object of class '<em>Plugin Management</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Plugin Management</em>'.
	 * @generated
	 */
	PluginManagement createPluginManagement();

	/**
	 * Returns a new object of class '<em>Plugin Repositories Type</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Plugin Repositories Type</em>'.
	 * @generated
	 */
	PluginRepositoriesType createPluginRepositoriesType();

	/**
	 * Returns a new object of class '<em>Plugins Type</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Plugins Type</em>'.
	 * @generated
	 */
	PluginsType createPluginsType();

	/**
	 * Returns a new object of class '<em>Prerequisites</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Prerequisites</em>'.
	 * @generated
	 */
	Prerequisites createPrerequisites();

	/**
	 * Returns a new object of class '<em>Profile</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Profile</em>'.
	 * @generated
	 */
	Profile createProfile();

	/**
	 * Returns a new object of class '<em>Profiles Type</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Profiles Type</em>'.
	 * @generated
	 */
	ProfilesType createProfilesType();

	/**
	 * Returns a new object of class '<em>Properties Type</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Properties Type</em>'.
	 * @generated
	 */
	PropertiesType createPropertiesType();

	/**
	 * Returns a new object of class '<em>Relocation</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Relocation</em>'.
	 * @generated
	 */
	Relocation createRelocation();

	/**
	 * Returns a new object of class '<em>Reporting</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Reporting</em>'.
	 * @generated
	 */
	Reporting createReporting();

	/**
	 * Returns a new object of class '<em>Reporting Plugins Type</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Reporting Plugins Type</em>'.
	 * @generated
	 */
	ReportingPluginsType createReportingPluginsType();

	/**
	 * Returns a new object of class '<em>Report Plugin</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Report Plugin</em>'.
	 * @generated
	 */
	ReportPlugin createReportPlugin();

	/**
	 * Returns a new object of class '<em>Report Set</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Report Set</em>'.
	 * @generated
	 */
	ReportSet createReportSet();

	/**
	 * Returns a new object of class '<em>Report Set Reports Type</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Report Set Reports Type</em>'.
	 * @generated
	 */
	ReportSetReportsType createReportSetReportsType();

	/**
	 * Returns a new object of class '<em>Report Sets Type</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Report Sets Type</em>'.
	 * @generated
	 */
	ReportSetsType createReportSetsType();

	/**
	 * Returns a new object of class '<em>Reports Type</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Reports Type</em>'.
	 * @generated
	 */
	ReportsType createReportsType();

	/**
	 * Returns a new object of class '<em>Repositories Type</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Repositories Type</em>'.
	 * @generated
	 */
	RepositoriesType createRepositoriesType();

	/**
	 * Returns a new object of class '<em>Repository</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Repository</em>'.
	 * @generated
	 */
	Repository createRepository();

	/**
	 * Returns a new object of class '<em>Repository Policy</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Repository Policy</em>'.
	 * @generated
	 */
	RepositoryPolicy createRepositoryPolicy();

	/**
	 * Returns a new object of class '<em>Resource</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Resource</em>'.
	 * @generated
	 */
	Resource createResource();

	/**
	 * Returns a new object of class '<em>Resources Type</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Resources Type</em>'.
	 * @generated
	 */
	ResourcesType createResourcesType();

	/**
	 * Returns a new object of class '<em>Roles Type</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Roles Type</em>'.
	 * @generated
	 */
	RolesType createRolesType();

	/**
	 * Returns a new object of class '<em>Scm</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Scm</em>'.
	 * @generated
	 */
	Scm createScm();

	/**
	 * Returns a new object of class '<em>Site</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Site</em>'.
	 * @generated
	 */
	Site createSite();

	/**
	 * Returns a new object of class '<em>Test Resources Type</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Test Resources Type</em>'.
	 * @generated
	 */
	TestResourcesType createTestResourcesType();

	/**
	 * Returns the package supported by this factory. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the package supported by this factory.
	 * @generated
	 */
	PomPackage getPomPackage();

} // PomFactory
