/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.engine.maven.pom.impl;

import org.eclipse.buckminster.aggregator.engine.maven.pom.Activation;
import org.eclipse.buckminster.aggregator.engine.maven.pom.ActivationFile;
import org.eclipse.buckminster.aggregator.engine.maven.pom.ActivationOS;
import org.eclipse.buckminster.aggregator.engine.maven.pom.ActivationProperty;
import org.eclipse.buckminster.aggregator.engine.maven.pom.Build;
import org.eclipse.buckminster.aggregator.engine.maven.pom.BuildBase;
import org.eclipse.buckminster.aggregator.engine.maven.pom.CiManagement;
import org.eclipse.buckminster.aggregator.engine.maven.pom.ConfigurationType;
import org.eclipse.buckminster.aggregator.engine.maven.pom.Contributor;
import org.eclipse.buckminster.aggregator.engine.maven.pom.ContributorsType;
import org.eclipse.buckminster.aggregator.engine.maven.pom.DependenciesType;
import org.eclipse.buckminster.aggregator.engine.maven.pom.Dependency;
import org.eclipse.buckminster.aggregator.engine.maven.pom.DependencyManagement;
import org.eclipse.buckminster.aggregator.engine.maven.pom.DeploymentRepository;
import org.eclipse.buckminster.aggregator.engine.maven.pom.Developer;
import org.eclipse.buckminster.aggregator.engine.maven.pom.DevelopersType;
import org.eclipse.buckminster.aggregator.engine.maven.pom.DistributionManagement;
import org.eclipse.buckminster.aggregator.engine.maven.pom.DocumentRoot;
import org.eclipse.buckminster.aggregator.engine.maven.pom.ExcludesType;
import org.eclipse.buckminster.aggregator.engine.maven.pom.Exclusion;
import org.eclipse.buckminster.aggregator.engine.maven.pom.ExclusionsType;
import org.eclipse.buckminster.aggregator.engine.maven.pom.ExecutionGoalsType;
import org.eclipse.buckminster.aggregator.engine.maven.pom.ExecutionsType;
import org.eclipse.buckminster.aggregator.engine.maven.pom.Extension;
import org.eclipse.buckminster.aggregator.engine.maven.pom.ExtensionsType;
import org.eclipse.buckminster.aggregator.engine.maven.pom.FiltersType;
import org.eclipse.buckminster.aggregator.engine.maven.pom.GoalsType;
import org.eclipse.buckminster.aggregator.engine.maven.pom.IncludesType;
import org.eclipse.buckminster.aggregator.engine.maven.pom.IssueManagement;
import org.eclipse.buckminster.aggregator.engine.maven.pom.License;
import org.eclipse.buckminster.aggregator.engine.maven.pom.LicensesType;
import org.eclipse.buckminster.aggregator.engine.maven.pom.MailingList;
import org.eclipse.buckminster.aggregator.engine.maven.pom.MailingListsType;
import org.eclipse.buckminster.aggregator.engine.maven.pom.Model;
import org.eclipse.buckminster.aggregator.engine.maven.pom.ModulesType;
import org.eclipse.buckminster.aggregator.engine.maven.pom.Notifier;
import org.eclipse.buckminster.aggregator.engine.maven.pom.NotifiersType;
import org.eclipse.buckminster.aggregator.engine.maven.pom.Organization;
import org.eclipse.buckminster.aggregator.engine.maven.pom.OtherArchivesType;
import org.eclipse.buckminster.aggregator.engine.maven.pom.Parent;
import org.eclipse.buckminster.aggregator.engine.maven.pom.Plugin;
import org.eclipse.buckminster.aggregator.engine.maven.pom.PluginExecution;
import org.eclipse.buckminster.aggregator.engine.maven.pom.PluginManagement;
import org.eclipse.buckminster.aggregator.engine.maven.pom.PluginRepositoriesType;
import org.eclipse.buckminster.aggregator.engine.maven.pom.PluginsType;
import org.eclipse.buckminster.aggregator.engine.maven.pom.PomFactory;
import org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage;
import org.eclipse.buckminster.aggregator.engine.maven.pom.Prerequisites;
import org.eclipse.buckminster.aggregator.engine.maven.pom.Profile;
import org.eclipse.buckminster.aggregator.engine.maven.pom.ProfilesType;
import org.eclipse.buckminster.aggregator.engine.maven.pom.PropertiesType;
import org.eclipse.buckminster.aggregator.engine.maven.pom.Relocation;
import org.eclipse.buckminster.aggregator.engine.maven.pom.ReportPlugin;
import org.eclipse.buckminster.aggregator.engine.maven.pom.ReportSet;
import org.eclipse.buckminster.aggregator.engine.maven.pom.ReportSetReportsType;
import org.eclipse.buckminster.aggregator.engine.maven.pom.ReportSetsType;
import org.eclipse.buckminster.aggregator.engine.maven.pom.Reporting;
import org.eclipse.buckminster.aggregator.engine.maven.pom.ReportingPluginsType;
import org.eclipse.buckminster.aggregator.engine.maven.pom.ReportsType;
import org.eclipse.buckminster.aggregator.engine.maven.pom.RepositoriesType;
import org.eclipse.buckminster.aggregator.engine.maven.pom.Repository;
import org.eclipse.buckminster.aggregator.engine.maven.pom.RepositoryPolicy;
import org.eclipse.buckminster.aggregator.engine.maven.pom.Resource;
import org.eclipse.buckminster.aggregator.engine.maven.pom.ResourcesType;
import org.eclipse.buckminster.aggregator.engine.maven.pom.RolesType;
import org.eclipse.buckminster.aggregator.engine.maven.pom.Scm;
import org.eclipse.buckminster.aggregator.engine.maven.pom.Site;
import org.eclipse.buckminster.aggregator.engine.maven.pom.TestResourcesType;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 * 
 * @generated
 */
public class PomPackageImpl extends EPackageImpl implements PomPackage
{
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass activationEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass activationFileEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass activationOSEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass activationPropertyEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass buildEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass buildBaseEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass ciManagementEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass configurationTypeEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass contributorEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass contributorsTypeEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass dependenciesTypeEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass dependencyEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass dependencyManagementEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass deploymentRepositoryEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass developerEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass developersTypeEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass distributionManagementEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass documentRootEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass excludesTypeEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass exclusionEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass exclusionsTypeEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass executionGoalsTypeEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass executionsTypeEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass extensionEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass extensionsTypeEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass filtersTypeEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass goalsTypeEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass includesTypeEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass issueManagementEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass licenseEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass licensesTypeEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass mailingListEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass mailingListsTypeEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass modelEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass modulesTypeEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass notifierEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass notifiersTypeEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass organizationEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass otherArchivesTypeEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass parentEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass pluginEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass pluginExecutionEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass pluginManagementEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass pluginRepositoriesTypeEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass pluginsTypeEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass prerequisitesEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass profileEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass profilesTypeEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass propertiesTypeEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass relocationEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass reportingEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass reportPluginEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass reportingPluginsTypeEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass reportSetEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass reportSetReportsTypeEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass reportSetsTypeEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass reportsTypeEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass repositoriesTypeEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass repositoryEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass repositoryPolicyEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass resourceEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass resourcesTypeEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass rolesTypeEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass scmEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass siteEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass testResourcesTypeEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>
	 * This method is used to initialize {@link PomPackage#eINSTANCE} when that field is accessed. Clients should not
	 * invoke it directly. Instead, they should simply access that field to obtain the package. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static PomPackage init()
	{
		if(isInited)
			return (PomPackage)EPackage.Registry.INSTANCE.getEPackage(PomPackage.eNS_URI);

		// Obtain or create and register package
		PomPackageImpl thePomPackage = (PomPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof PomPackageImpl
				? EPackage.Registry.INSTANCE.get(eNS_URI)
				: new PomPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		XMLTypePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		thePomPackage.createPackageContents();

		// Initialize created meta-data
		thePomPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		thePomPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(PomPackage.eNS_URI, thePomPackage);
		return thePomPackage;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with {@link org.eclipse.emf.ecore.EPackage.Registry
	 * EPackage.Registry} by the package package URI value.
	 * <p>
	 * Note: the correct way to create the package is via the static factory method {@link #init init()}, which also
	 * performs initialization of the package, or returns the registered package, if one already exists. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private PomPackageImpl()
	{
		super(eNS_URI, PomFactory.eINSTANCE);
	}

	/**
	 * Creates the meta-model objects for the package. This method is guarded to have no affect on any invocation but
	 * its first. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void createPackageContents()
	{
		if(isCreated)
			return;
		isCreated = true;

		// Create classes and their features
		activationEClass = createEClass(ACTIVATION);
		createEAttribute(activationEClass, ACTIVATION__ACTIVE_BY_DEFAULT);
		createEAttribute(activationEClass, ACTIVATION__JDK);
		createEReference(activationEClass, ACTIVATION__OS);
		createEReference(activationEClass, ACTIVATION__PROPERTY);
		createEReference(activationEClass, ACTIVATION__FILE);

		activationFileEClass = createEClass(ACTIVATION_FILE);
		createEAttribute(activationFileEClass, ACTIVATION_FILE__MISSING);
		createEAttribute(activationFileEClass, ACTIVATION_FILE__EXISTS);

		activationOSEClass = createEClass(ACTIVATION_OS);
		createEAttribute(activationOSEClass, ACTIVATION_OS__NAME);
		createEAttribute(activationOSEClass, ACTIVATION_OS__FAMILY);
		createEAttribute(activationOSEClass, ACTIVATION_OS__ARCH);
		createEAttribute(activationOSEClass, ACTIVATION_OS__VERSION);

		activationPropertyEClass = createEClass(ACTIVATION_PROPERTY);
		createEAttribute(activationPropertyEClass, ACTIVATION_PROPERTY__NAME);
		createEAttribute(activationPropertyEClass, ACTIVATION_PROPERTY__VALUE);

		buildEClass = createEClass(BUILD);
		createEAttribute(buildEClass, BUILD__SOURCE_DIRECTORY);
		createEAttribute(buildEClass, BUILD__SCRIPT_SOURCE_DIRECTORY);
		createEAttribute(buildEClass, BUILD__TEST_SOURCE_DIRECTORY);
		createEAttribute(buildEClass, BUILD__OUTPUT_DIRECTORY);
		createEAttribute(buildEClass, BUILD__TEST_OUTPUT_DIRECTORY);
		createEReference(buildEClass, BUILD__EXTENSIONS);
		createEAttribute(buildEClass, BUILD__DEFAULT_GOAL);
		createEReference(buildEClass, BUILD__RESOURCES);
		createEReference(buildEClass, BUILD__TEST_RESOURCES);
		createEAttribute(buildEClass, BUILD__DIRECTORY);
		createEAttribute(buildEClass, BUILD__FINAL_NAME);
		createEReference(buildEClass, BUILD__FILTERS);
		createEReference(buildEClass, BUILD__PLUGIN_MANAGEMENT);
		createEReference(buildEClass, BUILD__PLUGINS);

		buildBaseEClass = createEClass(BUILD_BASE);
		createEAttribute(buildBaseEClass, BUILD_BASE__DEFAULT_GOAL);
		createEReference(buildBaseEClass, BUILD_BASE__RESOURCES);
		createEReference(buildBaseEClass, BUILD_BASE__TEST_RESOURCES);
		createEAttribute(buildBaseEClass, BUILD_BASE__DIRECTORY);
		createEAttribute(buildBaseEClass, BUILD_BASE__FINAL_NAME);
		createEReference(buildBaseEClass, BUILD_BASE__FILTERS);
		createEReference(buildBaseEClass, BUILD_BASE__PLUGIN_MANAGEMENT);
		createEReference(buildBaseEClass, BUILD_BASE__PLUGINS);

		ciManagementEClass = createEClass(CI_MANAGEMENT);
		createEAttribute(ciManagementEClass, CI_MANAGEMENT__SYSTEM);
		createEAttribute(ciManagementEClass, CI_MANAGEMENT__URL);
		createEReference(ciManagementEClass, CI_MANAGEMENT__NOTIFIERS);

		configurationTypeEClass = createEClass(CONFIGURATION_TYPE);
		createEAttribute(configurationTypeEClass, CONFIGURATION_TYPE__ANY);

		contributorEClass = createEClass(CONTRIBUTOR);
		createEAttribute(contributorEClass, CONTRIBUTOR__NAME);
		createEAttribute(contributorEClass, CONTRIBUTOR__EMAIL);
		createEAttribute(contributorEClass, CONTRIBUTOR__URL);
		createEAttribute(contributorEClass, CONTRIBUTOR__ORGANIZATION);
		createEAttribute(contributorEClass, CONTRIBUTOR__ORGANIZATION_URL);
		createEReference(contributorEClass, CONTRIBUTOR__ROLES);
		createEAttribute(contributorEClass, CONTRIBUTOR__TIMEZONE);
		createEReference(contributorEClass, CONTRIBUTOR__PROPERTIES);

		contributorsTypeEClass = createEClass(CONTRIBUTORS_TYPE);
		createEReference(contributorsTypeEClass, CONTRIBUTORS_TYPE__CONTRIBUTOR);

		dependenciesTypeEClass = createEClass(DEPENDENCIES_TYPE);
		createEReference(dependenciesTypeEClass, DEPENDENCIES_TYPE__DEPENDENCY);

		dependencyEClass = createEClass(DEPENDENCY);
		createEAttribute(dependencyEClass, DEPENDENCY__GROUP_ID);
		createEAttribute(dependencyEClass, DEPENDENCY__ARTIFACT_ID);
		createEAttribute(dependencyEClass, DEPENDENCY__VERSION);
		createEAttribute(dependencyEClass, DEPENDENCY__TYPE);
		createEAttribute(dependencyEClass, DEPENDENCY__CLASSIFIER);
		createEAttribute(dependencyEClass, DEPENDENCY__SCOPE);
		createEAttribute(dependencyEClass, DEPENDENCY__SYSTEM_PATH);
		createEReference(dependencyEClass, DEPENDENCY__EXCLUSIONS);
		createEAttribute(dependencyEClass, DEPENDENCY__OPTIONAL);

		dependencyManagementEClass = createEClass(DEPENDENCY_MANAGEMENT);
		createEReference(dependencyManagementEClass, DEPENDENCY_MANAGEMENT__DEPENDENCIES);

		deploymentRepositoryEClass = createEClass(DEPLOYMENT_REPOSITORY);
		createEAttribute(deploymentRepositoryEClass, DEPLOYMENT_REPOSITORY__UNIQUE_VERSION);
		createEAttribute(deploymentRepositoryEClass, DEPLOYMENT_REPOSITORY__ID);
		createEAttribute(deploymentRepositoryEClass, DEPLOYMENT_REPOSITORY__NAME);
		createEAttribute(deploymentRepositoryEClass, DEPLOYMENT_REPOSITORY__URL);
		createEAttribute(deploymentRepositoryEClass, DEPLOYMENT_REPOSITORY__LAYOUT);

		developerEClass = createEClass(DEVELOPER);
		createEAttribute(developerEClass, DEVELOPER__ID);
		createEAttribute(developerEClass, DEVELOPER__NAME);
		createEAttribute(developerEClass, DEVELOPER__EMAIL);
		createEAttribute(developerEClass, DEVELOPER__URL);
		createEAttribute(developerEClass, DEVELOPER__ORGANIZATION);
		createEAttribute(developerEClass, DEVELOPER__ORGANIZATION_URL);
		createEReference(developerEClass, DEVELOPER__ROLES);
		createEAttribute(developerEClass, DEVELOPER__TIMEZONE);
		createEReference(developerEClass, DEVELOPER__PROPERTIES);

		developersTypeEClass = createEClass(DEVELOPERS_TYPE);
		createEReference(developersTypeEClass, DEVELOPERS_TYPE__DEVELOPER);

		distributionManagementEClass = createEClass(DISTRIBUTION_MANAGEMENT);
		createEReference(distributionManagementEClass, DISTRIBUTION_MANAGEMENT__REPOSITORY);
		createEReference(distributionManagementEClass, DISTRIBUTION_MANAGEMENT__SNAPSHOT_REPOSITORY);
		createEReference(distributionManagementEClass, DISTRIBUTION_MANAGEMENT__SITE);
		createEAttribute(distributionManagementEClass, DISTRIBUTION_MANAGEMENT__DOWNLOAD_URL);
		createEReference(distributionManagementEClass, DISTRIBUTION_MANAGEMENT__RELOCATION);
		createEAttribute(distributionManagementEClass, DISTRIBUTION_MANAGEMENT__STATUS);

		documentRootEClass = createEClass(DOCUMENT_ROOT);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__MIXED);
		createEReference(documentRootEClass, DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
		createEReference(documentRootEClass, DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
		createEReference(documentRootEClass, DOCUMENT_ROOT__PROJECT);

		excludesTypeEClass = createEClass(EXCLUDES_TYPE);
		createEAttribute(excludesTypeEClass, EXCLUDES_TYPE__EXCLUDE);

		exclusionEClass = createEClass(EXCLUSION);
		createEAttribute(exclusionEClass, EXCLUSION__ARTIFACT_ID);
		createEAttribute(exclusionEClass, EXCLUSION__GROUP_ID);

		exclusionsTypeEClass = createEClass(EXCLUSIONS_TYPE);
		createEReference(exclusionsTypeEClass, EXCLUSIONS_TYPE__EXCLUSION);

		executionGoalsTypeEClass = createEClass(EXECUTION_GOALS_TYPE);
		createEAttribute(executionGoalsTypeEClass, EXECUTION_GOALS_TYPE__GOAL);

		executionsTypeEClass = createEClass(EXECUTIONS_TYPE);
		createEReference(executionsTypeEClass, EXECUTIONS_TYPE__EXECUTION);

		extensionEClass = createEClass(EXTENSION);
		createEAttribute(extensionEClass, EXTENSION__GROUP_ID);
		createEAttribute(extensionEClass, EXTENSION__ARTIFACT_ID);
		createEAttribute(extensionEClass, EXTENSION__VERSION);

		extensionsTypeEClass = createEClass(EXTENSIONS_TYPE);
		createEReference(extensionsTypeEClass, EXTENSIONS_TYPE__EXTENSION);

		filtersTypeEClass = createEClass(FILTERS_TYPE);
		createEAttribute(filtersTypeEClass, FILTERS_TYPE__FILTER);

		goalsTypeEClass = createEClass(GOALS_TYPE);
		createEAttribute(goalsTypeEClass, GOALS_TYPE__ANY);

		includesTypeEClass = createEClass(INCLUDES_TYPE);
		createEAttribute(includesTypeEClass, INCLUDES_TYPE__INCLUDE);

		issueManagementEClass = createEClass(ISSUE_MANAGEMENT);
		createEAttribute(issueManagementEClass, ISSUE_MANAGEMENT__SYSTEM);
		createEAttribute(issueManagementEClass, ISSUE_MANAGEMENT__URL);

		licenseEClass = createEClass(LICENSE);
		createEAttribute(licenseEClass, LICENSE__NAME);
		createEAttribute(licenseEClass, LICENSE__URL);
		createEAttribute(licenseEClass, LICENSE__DISTRIBUTION);
		createEAttribute(licenseEClass, LICENSE__COMMENTS);

		licensesTypeEClass = createEClass(LICENSES_TYPE);
		createEReference(licensesTypeEClass, LICENSES_TYPE__LICENSE);

		mailingListEClass = createEClass(MAILING_LIST);
		createEAttribute(mailingListEClass, MAILING_LIST__NAME);
		createEAttribute(mailingListEClass, MAILING_LIST__SUBSCRIBE);
		createEAttribute(mailingListEClass, MAILING_LIST__UNSUBSCRIBE);
		createEAttribute(mailingListEClass, MAILING_LIST__POST);
		createEAttribute(mailingListEClass, MAILING_LIST__ARCHIVE);
		createEReference(mailingListEClass, MAILING_LIST__OTHER_ARCHIVES);

		mailingListsTypeEClass = createEClass(MAILING_LISTS_TYPE);
		createEReference(mailingListsTypeEClass, MAILING_LISTS_TYPE__MAILING_LIST);

		modelEClass = createEClass(MODEL);
		createEReference(modelEClass, MODEL__PARENT);
		createEAttribute(modelEClass, MODEL__MODEL_VERSION);
		createEAttribute(modelEClass, MODEL__GROUP_ID);
		createEAttribute(modelEClass, MODEL__ARTIFACT_ID);
		createEAttribute(modelEClass, MODEL__PACKAGING);
		createEAttribute(modelEClass, MODEL__NAME);
		createEAttribute(modelEClass, MODEL__VERSION);
		createEAttribute(modelEClass, MODEL__DESCRIPTION);
		createEAttribute(modelEClass, MODEL__URL);
		createEReference(modelEClass, MODEL__PREREQUISITES);
		createEReference(modelEClass, MODEL__ISSUE_MANAGEMENT);
		createEReference(modelEClass, MODEL__CI_MANAGEMENT);
		createEAttribute(modelEClass, MODEL__INCEPTION_YEAR);
		createEReference(modelEClass, MODEL__MAILING_LISTS);
		createEReference(modelEClass, MODEL__DEVELOPERS);
		createEReference(modelEClass, MODEL__CONTRIBUTORS);
		createEReference(modelEClass, MODEL__LICENSES);
		createEReference(modelEClass, MODEL__SCM);
		createEReference(modelEClass, MODEL__ORGANIZATION);
		createEReference(modelEClass, MODEL__BUILD);
		createEReference(modelEClass, MODEL__PROFILES);
		createEReference(modelEClass, MODEL__MODULES);
		createEReference(modelEClass, MODEL__REPOSITORIES);
		createEReference(modelEClass, MODEL__PLUGIN_REPOSITORIES);
		createEReference(modelEClass, MODEL__DEPENDENCIES);
		createEReference(modelEClass, MODEL__REPORTS);
		createEReference(modelEClass, MODEL__REPORTING);
		createEReference(modelEClass, MODEL__DEPENDENCY_MANAGEMENT);
		createEReference(modelEClass, MODEL__DISTRIBUTION_MANAGEMENT);
		createEReference(modelEClass, MODEL__PROPERTIES);

		modulesTypeEClass = createEClass(MODULES_TYPE);
		createEAttribute(modulesTypeEClass, MODULES_TYPE__MODULE);

		notifierEClass = createEClass(NOTIFIER);
		createEAttribute(notifierEClass, NOTIFIER__TYPE);
		createEAttribute(notifierEClass, NOTIFIER__SEND_ON_ERROR);
		createEAttribute(notifierEClass, NOTIFIER__SEND_ON_FAILURE);
		createEAttribute(notifierEClass, NOTIFIER__SEND_ON_SUCCESS);
		createEAttribute(notifierEClass, NOTIFIER__SEND_ON_WARNING);
		createEAttribute(notifierEClass, NOTIFIER__ADDRESS);
		createEReference(notifierEClass, NOTIFIER__CONFIGURATION);

		notifiersTypeEClass = createEClass(NOTIFIERS_TYPE);
		createEReference(notifiersTypeEClass, NOTIFIERS_TYPE__NOTIFIER);

		organizationEClass = createEClass(ORGANIZATION);
		createEAttribute(organizationEClass, ORGANIZATION__NAME);
		createEAttribute(organizationEClass, ORGANIZATION__URL);

		otherArchivesTypeEClass = createEClass(OTHER_ARCHIVES_TYPE);
		createEAttribute(otherArchivesTypeEClass, OTHER_ARCHIVES_TYPE__OTHER_ARCHIVE);

		parentEClass = createEClass(PARENT);
		createEAttribute(parentEClass, PARENT__ARTIFACT_ID);
		createEAttribute(parentEClass, PARENT__GROUP_ID);
		createEAttribute(parentEClass, PARENT__VERSION);
		createEAttribute(parentEClass, PARENT__RELATIVE_PATH);

		pluginEClass = createEClass(PLUGIN);
		createEAttribute(pluginEClass, PLUGIN__GROUP_ID);
		createEAttribute(pluginEClass, PLUGIN__ARTIFACT_ID);
		createEAttribute(pluginEClass, PLUGIN__VERSION);
		createEAttribute(pluginEClass, PLUGIN__EXTENSIONS);
		createEReference(pluginEClass, PLUGIN__EXECUTIONS);
		createEReference(pluginEClass, PLUGIN__DEPENDENCIES);
		createEReference(pluginEClass, PLUGIN__GOALS);
		createEAttribute(pluginEClass, PLUGIN__INHERITED);
		createEReference(pluginEClass, PLUGIN__CONFIGURATION);

		pluginExecutionEClass = createEClass(PLUGIN_EXECUTION);
		createEAttribute(pluginExecutionEClass, PLUGIN_EXECUTION__ID);
		createEAttribute(pluginExecutionEClass, PLUGIN_EXECUTION__PHASE);
		createEReference(pluginExecutionEClass, PLUGIN_EXECUTION__GOALS);
		createEAttribute(pluginExecutionEClass, PLUGIN_EXECUTION__INHERITED);
		createEReference(pluginExecutionEClass, PLUGIN_EXECUTION__CONFIGURATION);

		pluginManagementEClass = createEClass(PLUGIN_MANAGEMENT);
		createEReference(pluginManagementEClass, PLUGIN_MANAGEMENT__PLUGINS);

		pluginRepositoriesTypeEClass = createEClass(PLUGIN_REPOSITORIES_TYPE);
		createEReference(pluginRepositoriesTypeEClass, PLUGIN_REPOSITORIES_TYPE__PLUGIN_REPOSITORY);

		pluginsTypeEClass = createEClass(PLUGINS_TYPE);
		createEReference(pluginsTypeEClass, PLUGINS_TYPE__PLUGIN);

		prerequisitesEClass = createEClass(PREREQUISITES);
		createEAttribute(prerequisitesEClass, PREREQUISITES__MAVEN);

		profileEClass = createEClass(PROFILE);
		createEAttribute(profileEClass, PROFILE__ID);
		createEReference(profileEClass, PROFILE__ACTIVATION);
		createEReference(profileEClass, PROFILE__BUILD);
		createEReference(profileEClass, PROFILE__MODULES);
		createEReference(profileEClass, PROFILE__REPOSITORIES);
		createEReference(profileEClass, PROFILE__PLUGIN_REPOSITORIES);
		createEReference(profileEClass, PROFILE__DEPENDENCIES);
		createEReference(profileEClass, PROFILE__REPORTS);
		createEReference(profileEClass, PROFILE__REPORTING);
		createEReference(profileEClass, PROFILE__DEPENDENCY_MANAGEMENT);
		createEReference(profileEClass, PROFILE__DISTRIBUTION_MANAGEMENT);
		createEReference(profileEClass, PROFILE__PROPERTIES);

		profilesTypeEClass = createEClass(PROFILES_TYPE);
		createEReference(profilesTypeEClass, PROFILES_TYPE__PROFILE);

		propertiesTypeEClass = createEClass(PROPERTIES_TYPE);
		createEAttribute(propertiesTypeEClass, PROPERTIES_TYPE__ANY);

		relocationEClass = createEClass(RELOCATION);
		createEAttribute(relocationEClass, RELOCATION__GROUP_ID);
		createEAttribute(relocationEClass, RELOCATION__ARTIFACT_ID);
		createEAttribute(relocationEClass, RELOCATION__VERSION);
		createEAttribute(relocationEClass, RELOCATION__MESSAGE);

		reportingEClass = createEClass(REPORTING);
		createEAttribute(reportingEClass, REPORTING__EXCLUDE_DEFAULTS);
		createEAttribute(reportingEClass, REPORTING__OUTPUT_DIRECTORY);
		createEReference(reportingEClass, REPORTING__PLUGINS);

		reportPluginEClass = createEClass(REPORT_PLUGIN);
		createEAttribute(reportPluginEClass, REPORT_PLUGIN__GROUP_ID);
		createEAttribute(reportPluginEClass, REPORT_PLUGIN__ARTIFACT_ID);
		createEAttribute(reportPluginEClass, REPORT_PLUGIN__VERSION);
		createEAttribute(reportPluginEClass, REPORT_PLUGIN__INHERITED);
		createEReference(reportPluginEClass, REPORT_PLUGIN__CONFIGURATION);
		createEReference(reportPluginEClass, REPORT_PLUGIN__REPORT_SETS);

		reportingPluginsTypeEClass = createEClass(REPORTING_PLUGINS_TYPE);
		createEReference(reportingPluginsTypeEClass, REPORTING_PLUGINS_TYPE__PLUGIN);

		reportSetEClass = createEClass(REPORT_SET);
		createEAttribute(reportSetEClass, REPORT_SET__ID);
		createEReference(reportSetEClass, REPORT_SET__CONFIGURATION);
		createEAttribute(reportSetEClass, REPORT_SET__INHERITED);
		createEReference(reportSetEClass, REPORT_SET__REPORTS);

		reportSetReportsTypeEClass = createEClass(REPORT_SET_REPORTS_TYPE);
		createEAttribute(reportSetReportsTypeEClass, REPORT_SET_REPORTS_TYPE__REPORT);

		reportSetsTypeEClass = createEClass(REPORT_SETS_TYPE);
		createEReference(reportSetsTypeEClass, REPORT_SETS_TYPE__REPORT_SET);

		reportsTypeEClass = createEClass(REPORTS_TYPE);
		createEAttribute(reportsTypeEClass, REPORTS_TYPE__ANY);

		repositoriesTypeEClass = createEClass(REPOSITORIES_TYPE);
		createEReference(repositoriesTypeEClass, REPOSITORIES_TYPE__REPOSITORY);

		repositoryEClass = createEClass(REPOSITORY);
		createEReference(repositoryEClass, REPOSITORY__RELEASES);
		createEReference(repositoryEClass, REPOSITORY__SNAPSHOTS);
		createEAttribute(repositoryEClass, REPOSITORY__ID);
		createEAttribute(repositoryEClass, REPOSITORY__NAME);
		createEAttribute(repositoryEClass, REPOSITORY__URL);
		createEAttribute(repositoryEClass, REPOSITORY__LAYOUT);

		repositoryPolicyEClass = createEClass(REPOSITORY_POLICY);
		createEAttribute(repositoryPolicyEClass, REPOSITORY_POLICY__ENABLED);
		createEAttribute(repositoryPolicyEClass, REPOSITORY_POLICY__UPDATE_POLICY);
		createEAttribute(repositoryPolicyEClass, REPOSITORY_POLICY__CHECKSUM_POLICY);

		resourceEClass = createEClass(RESOURCE);
		createEAttribute(resourceEClass, RESOURCE__TARGET_PATH);
		createEAttribute(resourceEClass, RESOURCE__FILTERING);
		createEAttribute(resourceEClass, RESOURCE__DIRECTORY);
		createEReference(resourceEClass, RESOURCE__INCLUDES);
		createEReference(resourceEClass, RESOURCE__EXCLUDES);

		resourcesTypeEClass = createEClass(RESOURCES_TYPE);
		createEReference(resourcesTypeEClass, RESOURCES_TYPE__RESOURCE);

		rolesTypeEClass = createEClass(ROLES_TYPE);
		createEAttribute(rolesTypeEClass, ROLES_TYPE__ROLE);

		scmEClass = createEClass(SCM);
		createEAttribute(scmEClass, SCM__CONNECTION);
		createEAttribute(scmEClass, SCM__DEVELOPER_CONNECTION);
		createEAttribute(scmEClass, SCM__TAG);
		createEAttribute(scmEClass, SCM__URL);

		siteEClass = createEClass(SITE);
		createEAttribute(siteEClass, SITE__ID);
		createEAttribute(siteEClass, SITE__NAME);
		createEAttribute(siteEClass, SITE__URL);

		testResourcesTypeEClass = createEClass(TEST_RESOURCES_TYPE);
		createEReference(testResourcesTypeEClass, TEST_RESOURCES_TYPE__TEST_RESOURCE);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getActivation()
	{
		return activationEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getActivation_ActiveByDefault()
	{
		return (EAttribute)activationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getActivation_File()
	{
		return (EReference)activationEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getActivation_Jdk()
	{
		return (EAttribute)activationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getActivation_Os()
	{
		return (EReference)activationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getActivation_Property()
	{
		return (EReference)activationEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getActivationFile()
	{
		return activationFileEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getActivationFile_Exists()
	{
		return (EAttribute)activationFileEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getActivationFile_Missing()
	{
		return (EAttribute)activationFileEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getActivationOS()
	{
		return activationOSEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getActivationOS_Arch()
	{
		return (EAttribute)activationOSEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getActivationOS_Family()
	{
		return (EAttribute)activationOSEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getActivationOS_Name()
	{
		return (EAttribute)activationOSEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getActivationOS_Version()
	{
		return (EAttribute)activationOSEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getActivationProperty()
	{
		return activationPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getActivationProperty_Name()
	{
		return (EAttribute)activationPropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getActivationProperty_Value()
	{
		return (EAttribute)activationPropertyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getBuild()
	{
		return buildEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getBuild_DefaultGoal()
	{
		return (EAttribute)buildEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getBuild_Directory()
	{
		return (EAttribute)buildEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getBuild_Extensions()
	{
		return (EReference)buildEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getBuild_Filters()
	{
		return (EReference)buildEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getBuild_FinalName()
	{
		return (EAttribute)buildEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getBuild_OutputDirectory()
	{
		return (EAttribute)buildEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getBuild_PluginManagement()
	{
		return (EReference)buildEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getBuild_Plugins()
	{
		return (EReference)buildEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getBuild_Resources()
	{
		return (EReference)buildEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getBuild_ScriptSourceDirectory()
	{
		return (EAttribute)buildEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getBuild_SourceDirectory()
	{
		return (EAttribute)buildEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getBuild_TestOutputDirectory()
	{
		return (EAttribute)buildEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getBuild_TestResources()
	{
		return (EReference)buildEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getBuild_TestSourceDirectory()
	{
		return (EAttribute)buildEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getBuildBase()
	{
		return buildBaseEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getBuildBase_DefaultGoal()
	{
		return (EAttribute)buildBaseEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getBuildBase_Directory()
	{
		return (EAttribute)buildBaseEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getBuildBase_Filters()
	{
		return (EReference)buildBaseEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getBuildBase_FinalName()
	{
		return (EAttribute)buildBaseEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getBuildBase_PluginManagement()
	{
		return (EReference)buildBaseEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getBuildBase_Plugins()
	{
		return (EReference)buildBaseEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getBuildBase_Resources()
	{
		return (EReference)buildBaseEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getBuildBase_TestResources()
	{
		return (EReference)buildBaseEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getCiManagement()
	{
		return ciManagementEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getCiManagement_Notifiers()
	{
		return (EReference)ciManagementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getCiManagement_System()
	{
		return (EAttribute)ciManagementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getCiManagement_Url()
	{
		return (EAttribute)ciManagementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getConfigurationType()
	{
		return configurationTypeEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getConfigurationType_Any()
	{
		return (EAttribute)configurationTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getContributor()
	{
		return contributorEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getContributor_Email()
	{
		return (EAttribute)contributorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getContributor_Name()
	{
		return (EAttribute)contributorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getContributor_Organization()
	{
		return (EAttribute)contributorEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getContributor_OrganizationUrl()
	{
		return (EAttribute)contributorEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getContributor_Properties()
	{
		return (EReference)contributorEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getContributor_Roles()
	{
		return (EReference)contributorEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getContributor_Timezone()
	{
		return (EAttribute)contributorEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getContributor_Url()
	{
		return (EAttribute)contributorEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getContributorsType()
	{
		return contributorsTypeEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getContributorsType_Contributor()
	{
		return (EReference)contributorsTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getDependenciesType()
	{
		return dependenciesTypeEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getDependenciesType_Dependency()
	{
		return (EReference)dependenciesTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getDependency()
	{
		return dependencyEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getDependency_ArtifactId()
	{
		return (EAttribute)dependencyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getDependency_Classifier()
	{
		return (EAttribute)dependencyEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getDependency_Exclusions()
	{
		return (EReference)dependencyEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getDependency_GroupId()
	{
		return (EAttribute)dependencyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getDependency_Optional()
	{
		return (EAttribute)dependencyEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getDependency_Scope()
	{
		return (EAttribute)dependencyEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getDependency_SystemPath()
	{
		return (EAttribute)dependencyEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getDependency_Type()
	{
		return (EAttribute)dependencyEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getDependency_Version()
	{
		return (EAttribute)dependencyEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getDependencyManagement()
	{
		return dependencyManagementEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getDependencyManagement_Dependencies()
	{
		return (EReference)dependencyManagementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getDeploymentRepository()
	{
		return deploymentRepositoryEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getDeploymentRepository_Id()
	{
		return (EAttribute)deploymentRepositoryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getDeploymentRepository_Layout()
	{
		return (EAttribute)deploymentRepositoryEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getDeploymentRepository_Name()
	{
		return (EAttribute)deploymentRepositoryEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getDeploymentRepository_UniqueVersion()
	{
		return (EAttribute)deploymentRepositoryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getDeploymentRepository_Url()
	{
		return (EAttribute)deploymentRepositoryEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getDeveloper()
	{
		return developerEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getDeveloper_Email()
	{
		return (EAttribute)developerEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getDeveloper_Id()
	{
		return (EAttribute)developerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getDeveloper_Name()
	{
		return (EAttribute)developerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getDeveloper_Organization()
	{
		return (EAttribute)developerEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getDeveloper_OrganizationUrl()
	{
		return (EAttribute)developerEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getDeveloper_Properties()
	{
		return (EReference)developerEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getDeveloper_Roles()
	{
		return (EReference)developerEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getDeveloper_Timezone()
	{
		return (EAttribute)developerEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getDeveloper_Url()
	{
		return (EAttribute)developerEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getDevelopersType()
	{
		return developersTypeEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getDevelopersType_Developer()
	{
		return (EReference)developersTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getDistributionManagement()
	{
		return distributionManagementEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getDistributionManagement_DownloadUrl()
	{
		return (EAttribute)distributionManagementEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getDistributionManagement_Relocation()
	{
		return (EReference)distributionManagementEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getDistributionManagement_Repository()
	{
		return (EReference)distributionManagementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getDistributionManagement_Site()
	{
		return (EReference)distributionManagementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getDistributionManagement_SnapshotRepository()
	{
		return (EReference)distributionManagementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getDistributionManagement_Status()
	{
		return (EAttribute)distributionManagementEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getDocumentRoot()
	{
		return documentRootEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getDocumentRoot_Mixed()
	{
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getDocumentRoot_Project()
	{
		return (EReference)documentRootEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getDocumentRoot_XMLNSPrefixMap()
	{
		return (EReference)documentRootEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getDocumentRoot_XSISchemaLocation()
	{
		return (EReference)documentRootEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getExcludesType()
	{
		return excludesTypeEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getExcludesType_Exclude()
	{
		return (EAttribute)excludesTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getExclusion()
	{
		return exclusionEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getExclusion_ArtifactId()
	{
		return (EAttribute)exclusionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getExclusion_GroupId()
	{
		return (EAttribute)exclusionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getExclusionsType()
	{
		return exclusionsTypeEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getExclusionsType_Exclusion()
	{
		return (EReference)exclusionsTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getExecutionGoalsType()
	{
		return executionGoalsTypeEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getExecutionGoalsType_Goal()
	{
		return (EAttribute)executionGoalsTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getExecutionsType()
	{
		return executionsTypeEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getExecutionsType_Execution()
	{
		return (EReference)executionsTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getExtension()
	{
		return extensionEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getExtension_ArtifactId()
	{
		return (EAttribute)extensionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getExtension_GroupId()
	{
		return (EAttribute)extensionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getExtension_Version()
	{
		return (EAttribute)extensionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getExtensionsType()
	{
		return extensionsTypeEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getExtensionsType_Extension()
	{
		return (EReference)extensionsTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getFiltersType()
	{
		return filtersTypeEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getFiltersType_Filter()
	{
		return (EAttribute)filtersTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getGoalsType()
	{
		return goalsTypeEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getGoalsType_Any()
	{
		return (EAttribute)goalsTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getIncludesType()
	{
		return includesTypeEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getIncludesType_Include()
	{
		return (EAttribute)includesTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getIssueManagement()
	{
		return issueManagementEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getIssueManagement_System()
	{
		return (EAttribute)issueManagementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getIssueManagement_Url()
	{
		return (EAttribute)issueManagementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getLicense()
	{
		return licenseEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getLicense_Comments()
	{
		return (EAttribute)licenseEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getLicense_Distribution()
	{
		return (EAttribute)licenseEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getLicense_Name()
	{
		return (EAttribute)licenseEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getLicense_Url()
	{
		return (EAttribute)licenseEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getLicensesType()
	{
		return licensesTypeEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getLicensesType_License()
	{
		return (EReference)licensesTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getMailingList()
	{
		return mailingListEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getMailingList_Archive()
	{
		return (EAttribute)mailingListEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getMailingList_Name()
	{
		return (EAttribute)mailingListEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getMailingList_OtherArchives()
	{
		return (EReference)mailingListEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getMailingList_Post()
	{
		return (EAttribute)mailingListEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getMailingList_Subscribe()
	{
		return (EAttribute)mailingListEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getMailingList_Unsubscribe()
	{
		return (EAttribute)mailingListEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getMailingListsType()
	{
		return mailingListsTypeEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getMailingListsType_MailingList()
	{
		return (EReference)mailingListsTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getModel()
	{
		return modelEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getModel_ArtifactId()
	{
		return (EAttribute)modelEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getModel_Build()
	{
		return (EReference)modelEClass.getEStructuralFeatures().get(19);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getModel_CiManagement()
	{
		return (EReference)modelEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getModel_Contributors()
	{
		return (EReference)modelEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getModel_Dependencies()
	{
		return (EReference)modelEClass.getEStructuralFeatures().get(24);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getModel_DependencyManagement()
	{
		return (EReference)modelEClass.getEStructuralFeatures().get(27);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getModel_Description()
	{
		return (EAttribute)modelEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getModel_Developers()
	{
		return (EReference)modelEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getModel_DistributionManagement()
	{
		return (EReference)modelEClass.getEStructuralFeatures().get(28);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getModel_GroupId()
	{
		return (EAttribute)modelEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getModel_InceptionYear()
	{
		return (EAttribute)modelEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getModel_IssueManagement()
	{
		return (EReference)modelEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getModel_Licenses()
	{
		return (EReference)modelEClass.getEStructuralFeatures().get(16);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getModel_MailingLists()
	{
		return (EReference)modelEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getModel_ModelVersion()
	{
		return (EAttribute)modelEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getModel_Modules()
	{
		return (EReference)modelEClass.getEStructuralFeatures().get(21);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getModel_Name()
	{
		return (EAttribute)modelEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getModel_Organization()
	{
		return (EReference)modelEClass.getEStructuralFeatures().get(18);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getModel_Packaging()
	{
		return (EAttribute)modelEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getModel_Parent()
	{
		return (EReference)modelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getModel_PluginRepositories()
	{
		return (EReference)modelEClass.getEStructuralFeatures().get(23);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getModel_Prerequisites()
	{
		return (EReference)modelEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getModel_Profiles()
	{
		return (EReference)modelEClass.getEStructuralFeatures().get(20);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getModel_Properties()
	{
		return (EReference)modelEClass.getEStructuralFeatures().get(29);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getModel_Reporting()
	{
		return (EReference)modelEClass.getEStructuralFeatures().get(26);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getModel_Reports()
	{
		return (EReference)modelEClass.getEStructuralFeatures().get(25);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getModel_Repositories()
	{
		return (EReference)modelEClass.getEStructuralFeatures().get(22);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getModel_Scm()
	{
		return (EReference)modelEClass.getEStructuralFeatures().get(17);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getModel_Url()
	{
		return (EAttribute)modelEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getModel_Version()
	{
		return (EAttribute)modelEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getModulesType()
	{
		return modulesTypeEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getModulesType_Module()
	{
		return (EAttribute)modulesTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getNotifier()
	{
		return notifierEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getNotifier_Address()
	{
		return (EAttribute)notifierEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getNotifier_Configuration()
	{
		return (EReference)notifierEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getNotifier_SendOnError()
	{
		return (EAttribute)notifierEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getNotifier_SendOnFailure()
	{
		return (EAttribute)notifierEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getNotifier_SendOnSuccess()
	{
		return (EAttribute)notifierEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getNotifier_SendOnWarning()
	{
		return (EAttribute)notifierEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getNotifier_Type()
	{
		return (EAttribute)notifierEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getNotifiersType()
	{
		return notifiersTypeEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getNotifiersType_Notifier()
	{
		return (EReference)notifiersTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getOrganization()
	{
		return organizationEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getOrganization_Name()
	{
		return (EAttribute)organizationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getOrganization_Url()
	{
		return (EAttribute)organizationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getOtherArchivesType()
	{
		return otherArchivesTypeEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getOtherArchivesType_OtherArchive()
	{
		return (EAttribute)otherArchivesTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getParent()
	{
		return parentEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getParent_ArtifactId()
	{
		return (EAttribute)parentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getParent_GroupId()
	{
		return (EAttribute)parentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getParent_RelativePath()
	{
		return (EAttribute)parentEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getParent_Version()
	{
		return (EAttribute)parentEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getPlugin()
	{
		return pluginEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getPlugin_ArtifactId()
	{
		return (EAttribute)pluginEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getPlugin_Configuration()
	{
		return (EReference)pluginEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getPlugin_Dependencies()
	{
		return (EReference)pluginEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getPlugin_Executions()
	{
		return (EReference)pluginEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getPlugin_Extensions()
	{
		return (EAttribute)pluginEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getPlugin_Goals()
	{
		return (EReference)pluginEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getPlugin_GroupId()
	{
		return (EAttribute)pluginEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getPlugin_Inherited()
	{
		return (EAttribute)pluginEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getPlugin_Version()
	{
		return (EAttribute)pluginEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getPluginExecution()
	{
		return pluginExecutionEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getPluginExecution_Configuration()
	{
		return (EReference)pluginExecutionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getPluginExecution_Goals()
	{
		return (EReference)pluginExecutionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getPluginExecution_Id()
	{
		return (EAttribute)pluginExecutionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getPluginExecution_Inherited()
	{
		return (EAttribute)pluginExecutionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getPluginExecution_Phase()
	{
		return (EAttribute)pluginExecutionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getPluginManagement()
	{
		return pluginManagementEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getPluginManagement_Plugins()
	{
		return (EReference)pluginManagementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getPluginRepositoriesType()
	{
		return pluginRepositoriesTypeEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getPluginRepositoriesType_PluginRepository()
	{
		return (EReference)pluginRepositoriesTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getPluginsType()
	{
		return pluginsTypeEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getPluginsType_Plugin()
	{
		return (EReference)pluginsTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public PomFactory getPomFactory()
	{
		return (PomFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getPrerequisites()
	{
		return prerequisitesEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getPrerequisites_Maven()
	{
		return (EAttribute)prerequisitesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getProfile()
	{
		return profileEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getProfile_Activation()
	{
		return (EReference)profileEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getProfile_Build()
	{
		return (EReference)profileEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getProfile_Dependencies()
	{
		return (EReference)profileEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getProfile_DependencyManagement()
	{
		return (EReference)profileEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getProfile_DistributionManagement()
	{
		return (EReference)profileEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getProfile_Id()
	{
		return (EAttribute)profileEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getProfile_Modules()
	{
		return (EReference)profileEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getProfile_PluginRepositories()
	{
		return (EReference)profileEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getProfile_Properties()
	{
		return (EReference)profileEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getProfile_Reporting()
	{
		return (EReference)profileEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getProfile_Reports()
	{
		return (EReference)profileEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getProfile_Repositories()
	{
		return (EReference)profileEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getProfilesType()
	{
		return profilesTypeEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getProfilesType_Profile()
	{
		return (EReference)profilesTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getPropertiesType()
	{
		return propertiesTypeEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getPropertiesType_Any()
	{
		return (EAttribute)propertiesTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getRelocation()
	{
		return relocationEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getRelocation_ArtifactId()
	{
		return (EAttribute)relocationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getRelocation_GroupId()
	{
		return (EAttribute)relocationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getRelocation_Message()
	{
		return (EAttribute)relocationEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getRelocation_Version()
	{
		return (EAttribute)relocationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getReporting()
	{
		return reportingEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getReporting_ExcludeDefaults()
	{
		return (EAttribute)reportingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getReporting_OutputDirectory()
	{
		return (EAttribute)reportingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getReporting_Plugins()
	{
		return (EReference)reportingEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getReportingPluginsType()
	{
		return reportingPluginsTypeEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getReportingPluginsType_Plugin()
	{
		return (EReference)reportingPluginsTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getReportPlugin()
	{
		return reportPluginEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getReportPlugin_ArtifactId()
	{
		return (EAttribute)reportPluginEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getReportPlugin_Configuration()
	{
		return (EReference)reportPluginEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getReportPlugin_GroupId()
	{
		return (EAttribute)reportPluginEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getReportPlugin_Inherited()
	{
		return (EAttribute)reportPluginEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getReportPlugin_ReportSets()
	{
		return (EReference)reportPluginEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getReportPlugin_Version()
	{
		return (EAttribute)reportPluginEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getReportSet()
	{
		return reportSetEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getReportSet_Configuration()
	{
		return (EReference)reportSetEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getReportSet_Id()
	{
		return (EAttribute)reportSetEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getReportSet_Inherited()
	{
		return (EAttribute)reportSetEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getReportSet_Reports()
	{
		return (EReference)reportSetEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getReportSetReportsType()
	{
		return reportSetReportsTypeEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getReportSetReportsType_Report()
	{
		return (EAttribute)reportSetReportsTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getReportSetsType()
	{
		return reportSetsTypeEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getReportSetsType_ReportSet()
	{
		return (EReference)reportSetsTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getReportsType()
	{
		return reportsTypeEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getReportsType_Any()
	{
		return (EAttribute)reportsTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getRepositoriesType()
	{
		return repositoriesTypeEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getRepositoriesType_Repository()
	{
		return (EReference)repositoriesTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getRepository()
	{
		return repositoryEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getRepository_Id()
	{
		return (EAttribute)repositoryEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getRepository_Layout()
	{
		return (EAttribute)repositoryEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getRepository_Name()
	{
		return (EAttribute)repositoryEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getRepository_Releases()
	{
		return (EReference)repositoryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getRepository_Snapshots()
	{
		return (EReference)repositoryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getRepository_Url()
	{
		return (EAttribute)repositoryEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getRepositoryPolicy()
	{
		return repositoryPolicyEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getRepositoryPolicy_ChecksumPolicy()
	{
		return (EAttribute)repositoryPolicyEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getRepositoryPolicy_Enabled()
	{
		return (EAttribute)repositoryPolicyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getRepositoryPolicy_UpdatePolicy()
	{
		return (EAttribute)repositoryPolicyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getResource()
	{
		return resourceEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getResource_Directory()
	{
		return (EAttribute)resourceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getResource_Excludes()
	{
		return (EReference)resourceEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getResource_Filtering()
	{
		return (EAttribute)resourceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getResource_Includes()
	{
		return (EReference)resourceEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getResource_TargetPath()
	{
		return (EAttribute)resourceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getResourcesType()
	{
		return resourcesTypeEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getResourcesType_Resource()
	{
		return (EReference)resourcesTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getRolesType()
	{
		return rolesTypeEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getRolesType_Role()
	{
		return (EAttribute)rolesTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getScm()
	{
		return scmEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getScm_Connection()
	{
		return (EAttribute)scmEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getScm_DeveloperConnection()
	{
		return (EAttribute)scmEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getScm_Tag()
	{
		return (EAttribute)scmEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getScm_Url()
	{
		return (EAttribute)scmEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getSite()
	{
		return siteEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getSite_Id()
	{
		return (EAttribute)siteEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getSite_Name()
	{
		return (EAttribute)siteEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getSite_Url()
	{
		return (EAttribute)siteEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getTestResourcesType()
	{
		return testResourcesTypeEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getTestResourcesType_TestResource()
	{
		return (EReference)testResourcesTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Complete the initialization of the package and its meta-model. This method is guarded to have no affect on any
	 * invocation but its first. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void initializePackageContents()
	{
		if(isInitialized)
			return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		XMLTypePackage theXMLTypePackage = (XMLTypePackage)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(activationEClass, Activation.class, "Activation", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getActivation_ActiveByDefault(), theXMLTypePackage.getBoolean(), "activeByDefault", "false", 0,
				1, Activation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getActivation_Jdk(), theXMLTypePackage.getString(), "jdk", null, 0, 1, Activation.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getActivation_Os(), this.getActivationOS(), null, "os", null, 0, 1, Activation.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getActivation_Property(), this.getActivationProperty(), null, "property", null, 0, 1,
				Activation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getActivation_File(), this.getActivationFile(), null, "file", null, 0, 1, Activation.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(activationFileEClass, ActivationFile.class, "ActivationFile", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getActivationFile_Missing(), theXMLTypePackage.getString(), "missing", null, 0, 1,
				ActivationFile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getActivationFile_Exists(), theXMLTypePackage.getString(), "exists", null, 0, 1,
				ActivationFile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(activationOSEClass, ActivationOS.class, "ActivationOS", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getActivationOS_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, ActivationOS.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getActivationOS_Family(), theXMLTypePackage.getString(), "family", null, 0, 1,
				ActivationOS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getActivationOS_Arch(), theXMLTypePackage.getString(), "arch", null, 0, 1, ActivationOS.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getActivationOS_Version(), theXMLTypePackage.getString(), "version", null, 0, 1,
				ActivationOS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(activationPropertyEClass, ActivationProperty.class, "ActivationProperty", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getActivationProperty_Name(), theXMLTypePackage.getString(), "name", null, 0, 1,
				ActivationProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getActivationProperty_Value(), theXMLTypePackage.getString(), "value", null, 0, 1,
				ActivationProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(buildEClass, Build.class, "Build", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBuild_SourceDirectory(), theXMLTypePackage.getString(), "sourceDirectory", null, 0, 1,
				Build.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getBuild_ScriptSourceDirectory(), theXMLTypePackage.getString(), "scriptSourceDirectory", null,
				0, 1, Build.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getBuild_TestSourceDirectory(), theXMLTypePackage.getString(), "testSourceDirectory", null, 0,
				1, Build.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getBuild_OutputDirectory(), theXMLTypePackage.getString(), "outputDirectory", null, 0, 1,
				Build.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getBuild_TestOutputDirectory(), theXMLTypePackage.getString(), "testOutputDirectory", null, 0,
				1, Build.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getBuild_Extensions(), this.getExtensionsType(), null, "extensions", null, 0, 1, Build.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBuild_DefaultGoal(), theXMLTypePackage.getString(), "defaultGoal", null, 0, 1, Build.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBuild_Resources(), this.getResourcesType(), null, "resources", null, 0, 1, Build.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBuild_TestResources(), this.getTestResourcesType(), null, "testResources", null, 0, 1,
				Build.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBuild_Directory(), theXMLTypePackage.getString(), "directory", null, 0, 1, Build.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBuild_FinalName(), theXMLTypePackage.getString(), "finalName", null, 0, 1, Build.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBuild_Filters(), this.getFiltersType(), null, "filters", null, 0, 1, Build.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBuild_PluginManagement(), this.getPluginManagement(), null, "pluginManagement", null, 0, 1,
				Build.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBuild_Plugins(), this.getPluginsType(), null, "plugins", null, 0, 1, Build.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(buildBaseEClass, BuildBase.class, "BuildBase", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBuildBase_DefaultGoal(), theXMLTypePackage.getString(), "defaultGoal", null, 0, 1,
				BuildBase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getBuildBase_Resources(), this.getResourcesType(), null, "resources", null, 0, 1,
				BuildBase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBuildBase_TestResources(), this.getTestResourcesType(), null, "testResources", null, 0, 1,
				BuildBase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBuildBase_Directory(), theXMLTypePackage.getString(), "directory", null, 0, 1,
				BuildBase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getBuildBase_FinalName(), theXMLTypePackage.getString(), "finalName", null, 0, 1,
				BuildBase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getBuildBase_Filters(), this.getFiltersType(), null, "filters", null, 0, 1, BuildBase.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBuildBase_PluginManagement(), this.getPluginManagement(), null, "pluginManagement", null, 0,
				1, BuildBase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBuildBase_Plugins(), this.getPluginsType(), null, "plugins", null, 0, 1, BuildBase.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(ciManagementEClass, CiManagement.class, "CiManagement", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCiManagement_System(), theXMLTypePackage.getString(), "system", null, 0, 1,
				CiManagement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getCiManagement_Url(), theXMLTypePackage.getString(), "url", null, 0, 1, CiManagement.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCiManagement_Notifiers(), this.getNotifiersType(), null, "notifiers", null, 0, 1,
				CiManagement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(configurationTypeEClass, ConfigurationType.class, "ConfigurationType", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getConfigurationType_Any(), ecorePackage.getEFeatureMapEntry(), "any", null, 0, -1,
				ConfigurationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				!IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(contributorEClass, Contributor.class, "Contributor", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getContributor_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, Contributor.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getContributor_Email(), theXMLTypePackage.getString(), "email", null, 0, 1, Contributor.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getContributor_Url(), theXMLTypePackage.getString(), "url", null, 0, 1, Contributor.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getContributor_Organization(), theXMLTypePackage.getString(), "organization", null, 0, 1,
				Contributor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getContributor_OrganizationUrl(), theXMLTypePackage.getString(), "organizationUrl", null, 0, 1,
				Contributor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getContributor_Roles(), this.getRolesType(), null, "roles", null, 0, 1, Contributor.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getContributor_Timezone(), theXMLTypePackage.getString(), "timezone", null, 0, 1,
				Contributor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getContributor_Properties(), this.getPropertiesType(), null, "properties", null, 0, 1,
				Contributor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(contributorsTypeEClass, ContributorsType.class, "ContributorsType", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getContributorsType_Contributor(), this.getContributor(), null, "contributor", null, 0, -1,
				ContributorsType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dependenciesTypeEClass, DependenciesType.class, "DependenciesType", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDependenciesType_Dependency(), this.getDependency(), null, "dependency", null, 0, -1,
				DependenciesType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dependencyEClass, Dependency.class, "Dependency", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDependency_GroupId(), theXMLTypePackage.getString(), "groupId", null, 0, 1, Dependency.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDependency_ArtifactId(), theXMLTypePackage.getString(), "artifactId", null, 0, 1,
				Dependency.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getDependency_Version(), theXMLTypePackage.getString(), "version", null, 0, 1, Dependency.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDependency_Type(), theXMLTypePackage.getString(), "type", "jar", 0, 1, Dependency.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDependency_Classifier(), theXMLTypePackage.getString(), "classifier", null, 0, 1,
				Dependency.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getDependency_Scope(), theXMLTypePackage.getString(), "scope", null, 0, 1, Dependency.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDependency_SystemPath(), theXMLTypePackage.getString(), "systemPath", null, 0, 1,
				Dependency.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getDependency_Exclusions(), this.getExclusionsType(), null, "exclusions", null, 0, 1,
				Dependency.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDependency_Optional(), theXMLTypePackage.getBoolean(), "optional", "false", 0, 1,
				Dependency.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(dependencyManagementEClass, DependencyManagement.class, "DependencyManagement", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDependencyManagement_Dependencies(), this.getDependenciesType(), null, "dependencies", null,
				0, 1, DependencyManagement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(deploymentRepositoryEClass, DeploymentRepository.class, "DeploymentRepository", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDeploymentRepository_UniqueVersion(), theXMLTypePackage.getBoolean(), "uniqueVersion",
				"true", 0, 1, DeploymentRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE,
				!IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDeploymentRepository_Id(), theXMLTypePackage.getString(), "id", null, 0, 1,
				DeploymentRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDeploymentRepository_Name(), theXMLTypePackage.getString(), "name", null, 0, 1,
				DeploymentRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDeploymentRepository_Url(), theXMLTypePackage.getString(), "url", null, 0, 1,
				DeploymentRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDeploymentRepository_Layout(), theXMLTypePackage.getString(), "layout", "default", 0, 1,
				DeploymentRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(developerEClass, Developer.class, "Developer", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDeveloper_Id(), theXMLTypePackage.getString(), "id", null, 0, 1, Developer.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDeveloper_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, Developer.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDeveloper_Email(), theXMLTypePackage.getString(), "email", null, 0, 1, Developer.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDeveloper_Url(), theXMLTypePackage.getString(), "url", null, 0, 1, Developer.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDeveloper_Organization(), theXMLTypePackage.getString(), "organization", null, 0, 1,
				Developer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getDeveloper_OrganizationUrl(), theXMLTypePackage.getString(), "organizationUrl", null, 0, 1,
				Developer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getDeveloper_Roles(), this.getRolesType(), null, "roles", null, 0, 1, Developer.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDeveloper_Timezone(), theXMLTypePackage.getString(), "timezone", null, 0, 1, Developer.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDeveloper_Properties(), this.getPropertiesType(), null, "properties", null, 0, 1,
				Developer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(developersTypeEClass, DevelopersType.class, "DevelopersType", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDevelopersType_Developer(), this.getDeveloper(), null, "developer", null, 0, -1,
				DevelopersType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(distributionManagementEClass, DistributionManagement.class, "DistributionManagement", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDistributionManagement_Repository(), this.getDeploymentRepository(), null, "repository",
				null, 0, 1, DistributionManagement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDistributionManagement_SnapshotRepository(), this.getDeploymentRepository(), null,
				"snapshotRepository", null, 0, 1, DistributionManagement.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDistributionManagement_Site(), this.getSite(), null, "site", null, 0, 1,
				DistributionManagement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDistributionManagement_DownloadUrl(), theXMLTypePackage.getString(), "downloadUrl", null, 0,
				1, DistributionManagement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDistributionManagement_Relocation(), this.getRelocation(), null, "relocation", null, 0, 1,
				DistributionManagement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDistributionManagement_Status(), theXMLTypePackage.getString(), "status", null, 0, 1,
				DistributionManagement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(documentRootEClass, DocumentRoot.class, "DocumentRoot", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDocumentRoot_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, null,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_XMLNSPrefixMap(), ecorePackage.getEStringToStringMapEntry(), null,
				"xMLNSPrefixMap", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_XSISchemaLocation(), ecorePackage.getEStringToStringMapEntry(), null,
				"xSISchemaLocation", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Project(), this.getModel(), null, "project", null, 0, -2, null, IS_TRANSIENT,
				IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED,
				IS_ORDERED);

		initEClass(excludesTypeEClass, ExcludesType.class, "ExcludesType", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getExcludesType_Exclude(), theXMLTypePackage.getString(), "exclude", null, 0, -1,
				ExcludesType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(exclusionEClass, Exclusion.class, "Exclusion", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getExclusion_ArtifactId(), theXMLTypePackage.getString(), "artifactId", null, 0, 1,
				Exclusion.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getExclusion_GroupId(), theXMLTypePackage.getString(), "groupId", null, 0, 1, Exclusion.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(exclusionsTypeEClass, ExclusionsType.class, "ExclusionsType", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getExclusionsType_Exclusion(), this.getExclusion(), null, "exclusion", null, 0, -1,
				ExclusionsType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(executionGoalsTypeEClass, ExecutionGoalsType.class, "ExecutionGoalsType", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getExecutionGoalsType_Goal(), theXMLTypePackage.getString(), "goal", null, 0, -1,
				ExecutionGoalsType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				!IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(executionsTypeEClass, ExecutionsType.class, "ExecutionsType", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getExecutionsType_Execution(), this.getPluginExecution(), null, "execution", null, 0, -1,
				ExecutionsType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(extensionEClass, Extension.class, "Extension", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getExtension_GroupId(), theXMLTypePackage.getString(), "groupId", null, 0, 1, Extension.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExtension_ArtifactId(), theXMLTypePackage.getString(), "artifactId", null, 0, 1,
				Extension.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getExtension_Version(), theXMLTypePackage.getString(), "version", null, 0, 1, Extension.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(extensionsTypeEClass, ExtensionsType.class, "ExtensionsType", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getExtensionsType_Extension(), this.getExtension(), null, "extension", null, 0, -1,
				ExtensionsType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(filtersTypeEClass, FiltersType.class, "FiltersType", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFiltersType_Filter(), theXMLTypePackage.getString(), "filter", null, 0, -1,
				FiltersType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(goalsTypeEClass, GoalsType.class, "GoalsType", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getGoalsType_Any(), ecorePackage.getEFeatureMapEntry(), "any", null, 0, -1, GoalsType.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(includesTypeEClass, IncludesType.class, "IncludesType", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIncludesType_Include(), theXMLTypePackage.getString(), "include", null, 0, -1,
				IncludesType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(issueManagementEClass, IssueManagement.class, "IssueManagement", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIssueManagement_System(), theXMLTypePackage.getString(), "system", null, 0, 1,
				IssueManagement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getIssueManagement_Url(), theXMLTypePackage.getString(), "url", null, 0, 1,
				IssueManagement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(licenseEClass, License.class, "License", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLicense_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, License.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLicense_Url(), theXMLTypePackage.getString(), "url", null, 0, 1, License.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLicense_Distribution(), theXMLTypePackage.getString(), "distribution", null, 0, 1,
				License.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getLicense_Comments(), theXMLTypePackage.getString(), "comments", null, 0, 1, License.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(licensesTypeEClass, LicensesType.class, "LicensesType", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getLicensesType_License(), this.getLicense(), null, "license", null, 0, -1, LicensesType.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(mailingListEClass, MailingList.class, "MailingList", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMailingList_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, MailingList.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMailingList_Subscribe(), theXMLTypePackage.getString(), "subscribe", null, 0, 1,
				MailingList.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getMailingList_Unsubscribe(), theXMLTypePackage.getString(), "unsubscribe", null, 0, 1,
				MailingList.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getMailingList_Post(), theXMLTypePackage.getString(), "post", null, 0, 1, MailingList.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMailingList_Archive(), theXMLTypePackage.getString(), "archive", null, 0, 1,
				MailingList.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getMailingList_OtherArchives(), this.getOtherArchivesType(), null, "otherArchives", null, 0, 1,
				MailingList.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(mailingListsTypeEClass, MailingListsType.class, "MailingListsType", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMailingListsType_MailingList(), this.getMailingList(), null, "mailingList", null, 0, -1,
				MailingListsType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(modelEClass, Model.class, "Model", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getModel_Parent(), this.getParent(), null, "parent", null, 0, 1, Model.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEAttribute(getModel_ModelVersion(), theXMLTypePackage.getString(), "modelVersion", null, 0, 1, Model.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getModel_GroupId(), theXMLTypePackage.getString(), "groupId", null, 0, 1, Model.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getModel_ArtifactId(), theXMLTypePackage.getString(), "artifactId", null, 0, 1, Model.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getModel_Packaging(), theXMLTypePackage.getString(), "packaging", "jar", 0, 1, Model.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getModel_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, Model.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getModel_Version(), theXMLTypePackage.getString(), "version", null, 0, 1, Model.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getModel_Description(), theXMLTypePackage.getString(), "description", null, 0, 1, Model.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getModel_Url(), theXMLTypePackage.getString(), "url", null, 0, 1, Model.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModel_Prerequisites(), this.getPrerequisites(), null, "prerequisites", null, 0, 1,
				Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModel_IssueManagement(), this.getIssueManagement(), null, "issueManagement", null, 0, 1,
				Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModel_CiManagement(), this.getCiManagement(), null, "ciManagement", null, 0, 1, Model.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getModel_InceptionYear(), theXMLTypePackage.getString(), "inceptionYear", null, 0, 1,
				Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getModel_MailingLists(), this.getMailingListsType(), null, "mailingLists", null, 0, 1,
				Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModel_Developers(), this.getDevelopersType(), null, "developers", null, 0, 1, Model.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModel_Contributors(), this.getContributorsType(), null, "contributors", null, 0, 1,
				Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModel_Licenses(), this.getLicensesType(), null, "licenses", null, 0, 1, Model.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModel_Scm(), this.getScm(), null, "scm", null, 0, 1, Model.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEReference(getModel_Organization(), this.getOrganization(), null, "organization", null, 0, 1, Model.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModel_Build(), this.getBuild(), null, "build", null, 0, 1, Model.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEReference(getModel_Profiles(), this.getProfilesType(), null, "profiles", null, 0, 1, Model.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModel_Modules(), this.getModulesType(), null, "modules", null, 0, 1, Model.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModel_Repositories(), this.getRepositoriesType(), null, "repositories", null, 0, 1,
				Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModel_PluginRepositories(), this.getPluginRepositoriesType(), null, "pluginRepositories",
				null, 0, 1, Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModel_Dependencies(), this.getDependenciesType(), null, "dependencies", null, 0, 1,
				Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModel_Reports(), this.getReportsType(), null, "reports", null, 0, 1, Model.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModel_Reporting(), this.getReporting(), null, "reporting", null, 0, 1, Model.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModel_DependencyManagement(), this.getDependencyManagement(), null, "dependencyManagement",
				null, 0, 1, Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModel_DistributionManagement(), this.getDistributionManagement(), null,
				"distributionManagement", null, 0, 1, Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModel_Properties(), this.getPropertiesType(), null, "properties", null, 0, 1, Model.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(modulesTypeEClass, ModulesType.class, "ModulesType", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getModulesType_Module(), theXMLTypePackage.getString(), "module", null, 0, -1,
				ModulesType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(notifierEClass, Notifier.class, "Notifier", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNotifier_Type(), theXMLTypePackage.getString(), "type", "mail", 0, 1, Notifier.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNotifier_SendOnError(), theXMLTypePackage.getBoolean(), "sendOnError", "true", 0, 1,
				Notifier.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getNotifier_SendOnFailure(), theXMLTypePackage.getBoolean(), "sendOnFailure", "true", 0, 1,
				Notifier.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getNotifier_SendOnSuccess(), theXMLTypePackage.getBoolean(), "sendOnSuccess", "true", 0, 1,
				Notifier.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getNotifier_SendOnWarning(), theXMLTypePackage.getBoolean(), "sendOnWarning", "true", 0, 1,
				Notifier.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getNotifier_Address(), theXMLTypePackage.getString(), "address", null, 0, 1, Notifier.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getNotifier_Configuration(), this.getConfigurationType(), null, "configuration", null, 0, 1,
				Notifier.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(notifiersTypeEClass, NotifiersType.class, "NotifiersType", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getNotifiersType_Notifier(), this.getNotifier(), null, "notifier", null, 0, -1,
				NotifiersType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(organizationEClass, Organization.class, "Organization", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getOrganization_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, Organization.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getOrganization_Url(), theXMLTypePackage.getString(), "url", null, 0, 1, Organization.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(otherArchivesTypeEClass, OtherArchivesType.class, "OtherArchivesType", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getOtherArchivesType_OtherArchive(), theXMLTypePackage.getString(), "otherArchive", null, 0, -1,
				OtherArchivesType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				!IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(parentEClass, Parent.class, "Parent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getParent_ArtifactId(), theXMLTypePackage.getString(), "artifactId", null, 0, 1, Parent.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getParent_GroupId(), theXMLTypePackage.getString(), "groupId", null, 0, 1, Parent.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getParent_Version(), theXMLTypePackage.getString(), "version", null, 0, 1, Parent.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getParent_RelativePath(), theXMLTypePackage.getString(), "relativePath", "../pom.xml", 0, 1,
				Parent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(pluginEClass, Plugin.class, "Plugin", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPlugin_GroupId(), theXMLTypePackage.getString(), "groupId", "org.apache.maven.plugins", 0, 1,
				Plugin.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getPlugin_ArtifactId(), theXMLTypePackage.getString(), "artifactId", null, 0, 1, Plugin.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPlugin_Version(), theXMLTypePackage.getString(), "version", null, 0, 1, Plugin.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPlugin_Extensions(), theXMLTypePackage.getBoolean(), "extensions", "false", 0, 1,
				Plugin.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getPlugin_Executions(), this.getExecutionsType(), null, "executions", null, 0, 1, Plugin.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPlugin_Dependencies(), this.getDependenciesType(), null, "dependencies", null, 0, 1,
				Plugin.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPlugin_Goals(), this.getGoalsType(), null, "goals", null, 0, 1, Plugin.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEAttribute(getPlugin_Inherited(), theXMLTypePackage.getString(), "inherited", null, 0, 1, Plugin.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPlugin_Configuration(), this.getConfigurationType(), null, "configuration", null, 0, 1,
				Plugin.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(pluginExecutionEClass, PluginExecution.class, "PluginExecution", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPluginExecution_Id(), theXMLTypePackage.getString(), "id", "default", 0, 1,
				PluginExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getPluginExecution_Phase(), theXMLTypePackage.getString(), "phase", null, 0, 1,
				PluginExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getPluginExecution_Goals(), this.getExecutionGoalsType(), null, "goals", null, 0, 1,
				PluginExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPluginExecution_Inherited(), theXMLTypePackage.getString(), "inherited", null, 0, 1,
				PluginExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getPluginExecution_Configuration(), this.getConfigurationType(), null, "configuration", null, 0,
				1, PluginExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(pluginManagementEClass, PluginManagement.class, "PluginManagement", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPluginManagement_Plugins(), this.getPluginsType(), null, "plugins", null, 0, 1,
				PluginManagement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(pluginRepositoriesTypeEClass, PluginRepositoriesType.class, "PluginRepositoriesType", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPluginRepositoriesType_PluginRepository(), this.getRepository(), null, "pluginRepository",
				null, 0, -1, PluginRepositoriesType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(pluginsTypeEClass, PluginsType.class, "PluginsType", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPluginsType_Plugin(), this.getPlugin(), null, "plugin", null, 0, -1, PluginsType.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(prerequisitesEClass, Prerequisites.class, "Prerequisites", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPrerequisites_Maven(), theXMLTypePackage.getString(), "maven", "2.0", 0, 1,
				Prerequisites.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(profileEClass, Profile.class, "Profile", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getProfile_Id(), theXMLTypePackage.getString(), "id", null, 0, 1, Profile.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProfile_Activation(), this.getActivation(), null, "activation", null, 0, 1, Profile.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProfile_Build(), this.getBuildBase(), null, "build", null, 0, 1, Profile.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProfile_Modules(), this.getModulesType(), null, "modules", null, 0, 1, Profile.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProfile_Repositories(), this.getRepositoriesType(), null, "repositories", null, 0, 1,
				Profile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProfile_PluginRepositories(), this.getPluginRepositoriesType(), null, "pluginRepositories",
				null, 0, 1, Profile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProfile_Dependencies(), this.getDependenciesType(), null, "dependencies", null, 0, 1,
				Profile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProfile_Reports(), this.getReportsType(), null, "reports", null, 0, 1, Profile.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProfile_Reporting(), this.getReporting(), null, "reporting", null, 0, 1, Profile.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProfile_DependencyManagement(), this.getDependencyManagement(), null, "dependencyManagement",
				null, 0, 1, Profile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProfile_DistributionManagement(), this.getDistributionManagement(), null,
				"distributionManagement", null, 0, 1, Profile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProfile_Properties(), this.getPropertiesType(), null, "properties", null, 0, 1,
				Profile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(profilesTypeEClass, ProfilesType.class, "ProfilesType", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getProfilesType_Profile(), this.getProfile(), null, "profile", null, 0, -1, ProfilesType.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(propertiesTypeEClass, PropertiesType.class, "PropertiesType", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPropertiesType_Any(), ecorePackage.getEFeatureMapEntry(), "any", null, 0, -1,
				PropertiesType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(relocationEClass, Relocation.class, "Relocation", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRelocation_GroupId(), theXMLTypePackage.getString(), "groupId", null, 0, 1, Relocation.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRelocation_ArtifactId(), theXMLTypePackage.getString(), "artifactId", null, 0, 1,
				Relocation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getRelocation_Version(), theXMLTypePackage.getString(), "version", null, 0, 1, Relocation.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRelocation_Message(), theXMLTypePackage.getString(), "message", null, 0, 1, Relocation.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(reportingEClass, Reporting.class, "Reporting", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getReporting_ExcludeDefaults(), theXMLTypePackage.getBoolean(), "excludeDefaults", "false", 0,
				1, Reporting.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getReporting_OutputDirectory(), theXMLTypePackage.getString(), "outputDirectory", null, 0, 1,
				Reporting.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getReporting_Plugins(), this.getReportingPluginsType(), null, "plugins", null, 0, 1,
				Reporting.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(reportPluginEClass, ReportPlugin.class, "ReportPlugin", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getReportPlugin_GroupId(), theXMLTypePackage.getString(), "groupId", "org.apache.maven.plugins",
				0, 1, ReportPlugin.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getReportPlugin_ArtifactId(), theXMLTypePackage.getString(), "artifactId", null, 0, 1,
				ReportPlugin.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getReportPlugin_Version(), theXMLTypePackage.getString(), "version", null, 0, 1,
				ReportPlugin.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getReportPlugin_Inherited(), theXMLTypePackage.getString(), "inherited", null, 0, 1,
				ReportPlugin.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getReportPlugin_Configuration(), this.getConfigurationType(), null, "configuration", null, 0, 1,
				ReportPlugin.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getReportPlugin_ReportSets(), this.getReportSetsType(), null, "reportSets", null, 0, 1,
				ReportPlugin.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(reportingPluginsTypeEClass, ReportingPluginsType.class, "ReportingPluginsType", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getReportingPluginsType_Plugin(), this.getReportPlugin(), null, "plugin", null, 0, -1,
				ReportingPluginsType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(reportSetEClass, ReportSet.class, "ReportSet", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getReportSet_Id(), theXMLTypePackage.getString(), "id", "default", 0, 1, ReportSet.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getReportSet_Configuration(), this.getConfigurationType(), null, "configuration", null, 0, 1,
				ReportSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReportSet_Inherited(), theXMLTypePackage.getString(), "inherited", null, 0, 1,
				ReportSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getReportSet_Reports(), this.getReportSetReportsType(), null, "reports", null, 0, 1,
				ReportSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(reportSetReportsTypeEClass, ReportSetReportsType.class, "ReportSetReportsType", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getReportSetReportsType_Report(), theXMLTypePackage.getString(), "report", null, 0, -1,
				ReportSetReportsType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				!IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(reportSetsTypeEClass, ReportSetsType.class, "ReportSetsType", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getReportSetsType_ReportSet(), this.getReportSet(), null, "reportSet", null, 0, -1,
				ReportSetsType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(reportsTypeEClass, ReportsType.class, "ReportsType", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getReportsType_Any(), ecorePackage.getEFeatureMapEntry(), "any", null, 0, -1, ReportsType.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(repositoriesTypeEClass, RepositoriesType.class, "RepositoriesType", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRepositoriesType_Repository(), this.getRepository(), null, "repository", null, 0, -1,
				RepositoriesType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(repositoryEClass, Repository.class, "Repository", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRepository_Releases(), this.getRepositoryPolicy(), null, "releases", null, 0, 1,
				Repository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRepository_Snapshots(), this.getRepositoryPolicy(), null, "snapshots", null, 0, 1,
				Repository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRepository_Id(), theXMLTypePackage.getString(), "id", null, 0, 1, Repository.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRepository_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, Repository.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRepository_Url(), theXMLTypePackage.getString(), "url", null, 0, 1, Repository.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRepository_Layout(), theXMLTypePackage.getString(), "layout", "default", 0, 1,
				Repository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(repositoryPolicyEClass, RepositoryPolicy.class, "RepositoryPolicy", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRepositoryPolicy_Enabled(), theXMLTypePackage.getBoolean(), "enabled", "true", 0, 1,
				RepositoryPolicy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getRepositoryPolicy_UpdatePolicy(), theXMLTypePackage.getString(), "updatePolicy", null, 0, 1,
				RepositoryPolicy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getRepositoryPolicy_ChecksumPolicy(), theXMLTypePackage.getString(), "checksumPolicy", null, 0,
				1, RepositoryPolicy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(resourceEClass, Resource.class, "Resource", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getResource_TargetPath(), theXMLTypePackage.getString(), "targetPath", null, 0, 1,
				Resource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getResource_Filtering(), theXMLTypePackage.getBoolean(), "filtering", "false", 0, 1,
				Resource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getResource_Directory(), theXMLTypePackage.getString(), "directory", null, 0, 1, Resource.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getResource_Includes(), this.getIncludesType(), null, "includes", null, 0, 1, Resource.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getResource_Excludes(), this.getExcludesType(), null, "excludes", null, 0, 1, Resource.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(resourcesTypeEClass, ResourcesType.class, "ResourcesType", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getResourcesType_Resource(), this.getResource(), null, "resource", null, 0, -1,
				ResourcesType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(rolesTypeEClass, RolesType.class, "RolesType", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRolesType_Role(), theXMLTypePackage.getString(), "role", null, 0, -1, RolesType.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(scmEClass, Scm.class, "Scm", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getScm_Connection(), theXMLTypePackage.getString(), "connection", null, 0, 1, Scm.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getScm_DeveloperConnection(), theXMLTypePackage.getString(), "developerConnection", null, 0, 1,
				Scm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEAttribute(getScm_Tag(), theXMLTypePackage.getString(), "tag", "HEAD", 0, 1, Scm.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getScm_Url(), theXMLTypePackage.getString(), "url", null, 0, 1, Scm.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(siteEClass, Site.class, "Site", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSite_Id(), theXMLTypePackage.getString(), "id", null, 0, 1, Site.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSite_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, Site.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSite_Url(), theXMLTypePackage.getString(), "url", null, 0, 1, Site.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(testResourcesTypeEClass, TestResourcesType.class, "TestResourcesType", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTestResourcesType_TestResource(), this.getResource(), null, "testResource", null, 0, -1,
				TestResourcesType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http:///org/eclipse/emf/ecore/util/ExtendedMetaData
		createExtendedMetaDataAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void createExtendedMetaDataAnnotations()
	{
		String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";
		addAnnotation(activationEClass, source, new String[] { "name", "Activation", "kind", "elementOnly" });
		addAnnotation(getActivation_ActiveByDefault(), source, new String[] { "kind", "element", "name",
				"activeByDefault", "namespace", "##targetNamespace" });
		addAnnotation(getActivation_Jdk(), source, new String[] { "kind", "element", "name", "jdk", "namespace",
				"##targetNamespace" });
		addAnnotation(getActivation_Os(), source, new String[] { "kind", "element", "name", "os", "namespace",
				"##targetNamespace" });
		addAnnotation(getActivation_Property(), source, new String[] { "kind", "element", "name", "property",
				"namespace", "##targetNamespace" });
		addAnnotation(getActivation_File(), source, new String[] { "kind", "element", "name", "file", "namespace",
				"##targetNamespace" });
		addAnnotation(activationFileEClass, source, new String[] { "name", "ActivationFile", "kind", "elementOnly" });
		addAnnotation(getActivationFile_Missing(), source, new String[] { "kind", "element", "name", "missing",
				"namespace", "##targetNamespace" });
		addAnnotation(getActivationFile_Exists(), source, new String[] { "kind", "element", "name", "exists",
				"namespace", "##targetNamespace" });
		addAnnotation(activationOSEClass, source, new String[] { "name", "ActivationOS", "kind", "elementOnly" });
		addAnnotation(getActivationOS_Name(), source, new String[] { "kind", "element", "name", "name", "namespace",
				"##targetNamespace" });
		addAnnotation(getActivationOS_Family(), source, new String[] { "kind", "element", "name", "family",
				"namespace", "##targetNamespace" });
		addAnnotation(getActivationOS_Arch(), source, new String[] { "kind", "element", "name", "arch", "namespace",
				"##targetNamespace" });
		addAnnotation(getActivationOS_Version(), source, new String[] { "kind", "element", "name", "version",
				"namespace", "##targetNamespace" });
		addAnnotation(activationPropertyEClass, source, new String[] { "name", "ActivationProperty", "kind",
				"elementOnly" });
		addAnnotation(getActivationProperty_Name(), source, new String[] { "kind", "element", "name", "name",
				"namespace", "##targetNamespace" });
		addAnnotation(getActivationProperty_Value(), source, new String[] { "kind", "element", "name", "value",
				"namespace", "##targetNamespace" });
		addAnnotation(buildEClass, source, new String[] { "name", "Build", "kind", "elementOnly" });
		addAnnotation(getBuild_SourceDirectory(), source, new String[] { "kind", "element", "name", "sourceDirectory",
				"namespace", "##targetNamespace" });
		addAnnotation(getBuild_ScriptSourceDirectory(), source, new String[] { "kind", "element", "name",
				"scriptSourceDirectory", "namespace", "##targetNamespace" });
		addAnnotation(getBuild_TestSourceDirectory(), source, new String[] { "kind", "element", "name",
				"testSourceDirectory", "namespace", "##targetNamespace" });
		addAnnotation(getBuild_OutputDirectory(), source, new String[] { "kind", "element", "name", "outputDirectory",
				"namespace", "##targetNamespace" });
		addAnnotation(getBuild_TestOutputDirectory(), source, new String[] { "kind", "element", "name",
				"testOutputDirectory", "namespace", "##targetNamespace" });
		addAnnotation(getBuild_Extensions(), source, new String[] { "kind", "element", "name", "extensions",
				"namespace", "##targetNamespace" });
		addAnnotation(getBuild_DefaultGoal(), source, new String[] { "kind", "element", "name", "defaultGoal",
				"namespace", "##targetNamespace" });
		addAnnotation(getBuild_Resources(), source, new String[] { "kind", "element", "name", "resources", "namespace",
				"##targetNamespace" });
		addAnnotation(getBuild_TestResources(), source, new String[] { "kind", "element", "name", "testResources",
				"namespace", "##targetNamespace" });
		addAnnotation(getBuild_Directory(), source, new String[] { "kind", "element", "name", "directory", "namespace",
				"##targetNamespace" });
		addAnnotation(getBuild_FinalName(), source, new String[] { "kind", "element", "name", "finalName", "namespace",
				"##targetNamespace" });
		addAnnotation(getBuild_Filters(), source, new String[] { "kind", "element", "name", "filters", "namespace",
				"##targetNamespace" });
		addAnnotation(getBuild_PluginManagement(), source, new String[] { "kind", "element", "name",
				"pluginManagement", "namespace", "##targetNamespace" });
		addAnnotation(getBuild_Plugins(), source, new String[] { "kind", "element", "name", "plugins", "namespace",
				"##targetNamespace" });
		addAnnotation(buildBaseEClass, source, new String[] { "name", "BuildBase", "kind", "elementOnly" });
		addAnnotation(getBuildBase_DefaultGoal(), source, new String[] { "kind", "element", "name", "defaultGoal",
				"namespace", "##targetNamespace" });
		addAnnotation(getBuildBase_Resources(), source, new String[] { "kind", "element", "name", "resources",
				"namespace", "##targetNamespace" });
		addAnnotation(getBuildBase_TestResources(), source, new String[] { "kind", "element", "name", "testResources",
				"namespace", "##targetNamespace" });
		addAnnotation(getBuildBase_Directory(), source, new String[] { "kind", "element", "name", "directory",
				"namespace", "##targetNamespace" });
		addAnnotation(getBuildBase_FinalName(), source, new String[] { "kind", "element", "name", "finalName",
				"namespace", "##targetNamespace" });
		addAnnotation(getBuildBase_Filters(), source, new String[] { "kind", "element", "name", "filters", "namespace",
				"##targetNamespace" });
		addAnnotation(getBuildBase_PluginManagement(), source, new String[] { "kind", "element", "name",
				"pluginManagement", "namespace", "##targetNamespace" });
		addAnnotation(getBuildBase_Plugins(), source, new String[] { "kind", "element", "name", "plugins", "namespace",
				"##targetNamespace" });
		addAnnotation(ciManagementEClass, source, new String[] { "name", "CiManagement", "kind", "elementOnly" });
		addAnnotation(getCiManagement_System(), source, new String[] { "kind", "element", "name", "system",
				"namespace", "##targetNamespace" });
		addAnnotation(getCiManagement_Url(), source, new String[] { "kind", "element", "name", "url", "namespace",
				"##targetNamespace" });
		addAnnotation(getCiManagement_Notifiers(), source, new String[] { "kind", "element", "name", "notifiers",
				"namespace", "##targetNamespace" });
		addAnnotation(configurationTypeEClass, source, new String[] { "name", "configuration_._type", "kind",
				"elementOnly" });
		addAnnotation(getConfigurationType_Any(), source, new String[] { "kind", "elementWildcard", "wildcards",
				"##any", "name", ":0", "processing", "skip" });
		addAnnotation(contributorEClass, source, new String[] { "name", "Contributor", "kind", "elementOnly" });
		addAnnotation(getContributor_Name(), source, new String[] { "kind", "element", "name", "name", "namespace",
				"##targetNamespace" });
		addAnnotation(getContributor_Email(), source, new String[] { "kind", "element", "name", "email", "namespace",
				"##targetNamespace" });
		addAnnotation(getContributor_Url(), source, new String[] { "kind", "element", "name", "url", "namespace",
				"##targetNamespace" });
		addAnnotation(getContributor_Organization(), source, new String[] { "kind", "element", "name", "organization",
				"namespace", "##targetNamespace" });
		addAnnotation(getContributor_OrganizationUrl(), source, new String[] { "kind", "element", "name",
				"organizationUrl", "namespace", "##targetNamespace" });
		addAnnotation(getContributor_Roles(), source, new String[] { "kind", "element", "name", "roles", "namespace",
				"##targetNamespace" });
		addAnnotation(getContributor_Timezone(), source, new String[] { "kind", "element", "name", "timezone",
				"namespace", "##targetNamespace" });
		addAnnotation(getContributor_Properties(), source, new String[] { "kind", "element", "name", "properties",
				"namespace", "##targetNamespace" });
		addAnnotation(contributorsTypeEClass, source, new String[] { "name", "contributors_._type", "kind",
				"elementOnly" });
		addAnnotation(getContributorsType_Contributor(), source, new String[] { "kind", "element", "name",
				"contributor", "namespace", "##targetNamespace" });
		addAnnotation(dependenciesTypeEClass, source, new String[] { "name", "dependencies_._type", "kind",
				"elementOnly" });
		addAnnotation(getDependenciesType_Dependency(), source, new String[] { "kind", "element", "name", "dependency",
				"namespace", "##targetNamespace" });
		addAnnotation(dependencyEClass, source, new String[] { "name", "Dependency", "kind", "elementOnly" });
		addAnnotation(getDependency_GroupId(), source, new String[] { "kind", "element", "name", "groupId",
				"namespace", "##targetNamespace" });
		addAnnotation(getDependency_ArtifactId(), source, new String[] { "kind", "element", "name", "artifactId",
				"namespace", "##targetNamespace" });
		addAnnotation(getDependency_Version(), source, new String[] { "kind", "element", "name", "version",
				"namespace", "##targetNamespace" });
		addAnnotation(getDependency_Type(), source, new String[] { "kind", "element", "name", "type", "namespace",
				"##targetNamespace" });
		addAnnotation(getDependency_Classifier(), source, new String[] { "kind", "element", "name", "classifier",
				"namespace", "##targetNamespace" });
		addAnnotation(getDependency_Scope(), source, new String[] { "kind", "element", "name", "scope", "namespace",
				"##targetNamespace" });
		addAnnotation(getDependency_SystemPath(), source, new String[] { "kind", "element", "name", "systemPath",
				"namespace", "##targetNamespace" });
		addAnnotation(getDependency_Exclusions(), source, new String[] { "kind", "element", "name", "exclusions",
				"namespace", "##targetNamespace" });
		addAnnotation(getDependency_Optional(), source, new String[] { "kind", "element", "name", "optional",
				"namespace", "##targetNamespace" });
		addAnnotation(dependencyManagementEClass, source, new String[] { "name", "DependencyManagement", "kind",
				"elementOnly" });
		addAnnotation(getDependencyManagement_Dependencies(), source, new String[] { "kind", "element", "name",
				"dependencies", "namespace", "##targetNamespace" });
		addAnnotation(deploymentRepositoryEClass, source, new String[] { "name", "DeploymentRepository", "kind",
				"elementOnly" });
		addAnnotation(getDeploymentRepository_UniqueVersion(), source, new String[] { "kind", "element", "name",
				"uniqueVersion", "namespace", "##targetNamespace" });
		addAnnotation(getDeploymentRepository_Id(), source, new String[] { "kind", "element", "name", "id",
				"namespace", "##targetNamespace" });
		addAnnotation(getDeploymentRepository_Name(), source, new String[] { "kind", "element", "name", "name",
				"namespace", "##targetNamespace" });
		addAnnotation(getDeploymentRepository_Url(), source, new String[] { "kind", "element", "name", "url",
				"namespace", "##targetNamespace" });
		addAnnotation(getDeploymentRepository_Layout(), source, new String[] { "kind", "element", "name", "layout",
				"namespace", "##targetNamespace" });
		addAnnotation(developerEClass, source, new String[] { "name", "Developer", "kind", "elementOnly" });
		addAnnotation(getDeveloper_Id(), source, new String[] { "kind", "element", "name", "id", "namespace",
				"##targetNamespace" });
		addAnnotation(getDeveloper_Name(), source, new String[] { "kind", "element", "name", "name", "namespace",
				"##targetNamespace" });
		addAnnotation(getDeveloper_Email(), source, new String[] { "kind", "element", "name", "email", "namespace",
				"##targetNamespace" });
		addAnnotation(getDeveloper_Url(), source, new String[] { "kind", "element", "name", "url", "namespace",
				"##targetNamespace" });
		addAnnotation(getDeveloper_Organization(), source, new String[] { "kind", "element", "name", "organization",
				"namespace", "##targetNamespace" });
		addAnnotation(getDeveloper_OrganizationUrl(), source, new String[] { "kind", "element", "name",
				"organizationUrl", "namespace", "##targetNamespace" });
		addAnnotation(getDeveloper_Roles(), source, new String[] { "kind", "element", "name", "roles", "namespace",
				"##targetNamespace" });
		addAnnotation(getDeveloper_Timezone(), source, new String[] { "kind", "element", "name", "timezone",
				"namespace", "##targetNamespace" });
		addAnnotation(getDeveloper_Properties(), source, new String[] { "kind", "element", "name", "properties",
				"namespace", "##targetNamespace" });
		addAnnotation(developersTypeEClass, source, new String[] { "name", "developers_._type", "kind", "elementOnly" });
		addAnnotation(getDevelopersType_Developer(), source, new String[] { "kind", "element", "name", "developer",
				"namespace", "##targetNamespace" });
		addAnnotation(distributionManagementEClass, source, new String[] { "name", "DistributionManagement", "kind",
				"elementOnly" });
		addAnnotation(getDistributionManagement_Repository(), source, new String[] { "kind", "element", "name",
				"repository", "namespace", "##targetNamespace" });
		addAnnotation(getDistributionManagement_SnapshotRepository(), source, new String[] { "kind", "element", "name",
				"snapshotRepository", "namespace", "##targetNamespace" });
		addAnnotation(getDistributionManagement_Site(), source, new String[] { "kind", "element", "name", "site",
				"namespace", "##targetNamespace" });
		addAnnotation(getDistributionManagement_DownloadUrl(), source, new String[] { "kind", "element", "name",
				"downloadUrl", "namespace", "##targetNamespace" });
		addAnnotation(getDistributionManagement_Relocation(), source, new String[] { "kind", "element", "name",
				"relocation", "namespace", "##targetNamespace" });
		addAnnotation(getDistributionManagement_Status(), source, new String[] { "kind", "element", "name", "status",
				"namespace", "##targetNamespace" });
		addAnnotation(documentRootEClass, source, new String[] { "name", "", "kind", "mixed" });
		addAnnotation(getDocumentRoot_Mixed(), source, new String[] { "kind", "elementWildcard", "name", ":mixed" });
		addAnnotation(getDocumentRoot_XMLNSPrefixMap(), source, new String[] { "kind", "attribute", "name",
				"xmlns:prefix" });
		addAnnotation(getDocumentRoot_XSISchemaLocation(), source, new String[] { "kind", "attribute", "name",
				"xsi:schemaLocation" });
		addAnnotation(getDocumentRoot_Project(), source, new String[] { "kind", "element", "name", "project",
				"namespace", "##targetNamespace" });
		addAnnotation(excludesTypeEClass, source, new String[] { "name", "excludes_._type", "kind", "elementOnly" });
		addAnnotation(getExcludesType_Exclude(), source, new String[] { "kind", "element", "name", "exclude",
				"namespace", "##targetNamespace" });
		addAnnotation(exclusionEClass, source, new String[] { "name", "Exclusion", "kind", "elementOnly" });
		addAnnotation(getExclusion_ArtifactId(), source, new String[] { "kind", "element", "name", "artifactId",
				"namespace", "##targetNamespace" });
		addAnnotation(getExclusion_GroupId(), source, new String[] { "kind", "element", "name", "groupId", "namespace",
				"##targetNamespace" });
		addAnnotation(exclusionsTypeEClass, source, new String[] { "name", "exclusions_._type", "kind", "elementOnly" });
		addAnnotation(getExclusionsType_Exclusion(), source, new String[] { "kind", "element", "name", "exclusion",
				"namespace", "##targetNamespace" });
		addAnnotation(executionGoalsTypeEClass, source, new String[] { "name", "execution_._goals_._type", "kind",
				"elementOnly" });
		addAnnotation(getExecutionGoalsType_Goal(), source, new String[] { "kind", "element", "name", "goal",
				"namespace", "##targetNamespace" });
		addAnnotation(executionsTypeEClass, source, new String[] { "name", "executions_._type", "kind", "elementOnly" });
		addAnnotation(getExecutionsType_Execution(), source, new String[] { "kind", "element", "name", "execution",
				"namespace", "##targetNamespace" });
		addAnnotation(extensionEClass, source, new String[] { "name", "Extension", "kind", "elementOnly" });
		addAnnotation(getExtension_GroupId(), source, new String[] { "kind", "element", "name", "groupId", "namespace",
				"##targetNamespace" });
		addAnnotation(getExtension_ArtifactId(), source, new String[] { "kind", "element", "name", "artifactId",
				"namespace", "##targetNamespace" });
		addAnnotation(getExtension_Version(), source, new String[] { "kind", "element", "name", "version", "namespace",
				"##targetNamespace" });
		addAnnotation(extensionsTypeEClass, source, new String[] { "name", "extensions_._type", "kind", "elementOnly" });
		addAnnotation(getExtensionsType_Extension(), source, new String[] { "kind", "element", "name", "extension",
				"namespace", "##targetNamespace" });
		addAnnotation(filtersTypeEClass, source, new String[] { "name", "filters_._type", "kind", "elementOnly" });
		addAnnotation(getFiltersType_Filter(), source, new String[] { "kind", "element", "name", "filter", "namespace",
				"##targetNamespace" });
		addAnnotation(goalsTypeEClass, source, new String[] { "name", "goals_._type", "kind", "elementOnly" });
		addAnnotation(getGoalsType_Any(), source, new String[] { "kind", "elementWildcard", "wildcards", "##any",
				"name", ":0", "processing", "skip" });
		addAnnotation(includesTypeEClass, source, new String[] { "name", "includes_._type", "kind", "elementOnly" });
		addAnnotation(getIncludesType_Include(), source, new String[] { "kind", "element", "name", "include",
				"namespace", "##targetNamespace" });
		addAnnotation(issueManagementEClass, source, new String[] { "name", "IssueManagement", "kind", "elementOnly" });
		addAnnotation(getIssueManagement_System(), source, new String[] { "kind", "element", "name", "system",
				"namespace", "##targetNamespace" });
		addAnnotation(getIssueManagement_Url(), source, new String[] { "kind", "element", "name", "url", "namespace",
				"##targetNamespace" });
		addAnnotation(licenseEClass, source, new String[] { "name", "License", "kind", "elementOnly" });
		addAnnotation(getLicense_Name(), source, new String[] { "kind", "element", "name", "name", "namespace",
				"##targetNamespace" });
		addAnnotation(getLicense_Url(), source, new String[] { "kind", "element", "name", "url", "namespace",
				"##targetNamespace" });
		addAnnotation(getLicense_Distribution(), source, new String[] { "kind", "element", "name", "distribution",
				"namespace", "##targetNamespace" });
		addAnnotation(getLicense_Comments(), source, new String[] { "kind", "element", "name", "comments", "namespace",
				"##targetNamespace" });
		addAnnotation(licensesTypeEClass, source, new String[] { "name", "licenses_._type", "kind", "elementOnly" });
		addAnnotation(getLicensesType_License(), source, new String[] { "kind", "element", "name", "license",
				"namespace", "##targetNamespace" });
		addAnnotation(mailingListEClass, source, new String[] { "name", "MailingList", "kind", "elementOnly" });
		addAnnotation(getMailingList_Name(), source, new String[] { "kind", "element", "name", "name", "namespace",
				"##targetNamespace" });
		addAnnotation(getMailingList_Subscribe(), source, new String[] { "kind", "element", "name", "subscribe",
				"namespace", "##targetNamespace" });
		addAnnotation(getMailingList_Unsubscribe(), source, new String[] { "kind", "element", "name", "unsubscribe",
				"namespace", "##targetNamespace" });
		addAnnotation(getMailingList_Post(), source, new String[] { "kind", "element", "name", "post", "namespace",
				"##targetNamespace" });
		addAnnotation(getMailingList_Archive(), source, new String[] { "kind", "element", "name", "archive",
				"namespace", "##targetNamespace" });
		addAnnotation(getMailingList_OtherArchives(), source, new String[] { "kind", "element", "name",
				"otherArchives", "namespace", "##targetNamespace" });
		addAnnotation(mailingListsTypeEClass, source, new String[] { "name", "mailingLists_._type", "kind",
				"elementOnly" });
		addAnnotation(getMailingListsType_MailingList(), source, new String[] { "kind", "element", "name",
				"mailingList", "namespace", "##targetNamespace" });
		addAnnotation(modelEClass, source, new String[] { "name", "Model", "kind", "elementOnly" });
		addAnnotation(getModel_Parent(), source, new String[] { "kind", "element", "name", "parent", "namespace",
				"##targetNamespace" });
		addAnnotation(getModel_ModelVersion(), source, new String[] { "kind", "element", "name", "modelVersion",
				"namespace", "##targetNamespace" });
		addAnnotation(getModel_GroupId(), source, new String[] { "kind", "element", "name", "groupId", "namespace",
				"##targetNamespace" });
		addAnnotation(getModel_ArtifactId(), source, new String[] { "kind", "element", "name", "artifactId",
				"namespace", "##targetNamespace" });
		addAnnotation(getModel_Packaging(), source, new String[] { "kind", "element", "name", "packaging", "namespace",
				"##targetNamespace" });
		addAnnotation(getModel_Name(), source, new String[] { "kind", "element", "name", "name", "namespace",
				"##targetNamespace" });
		addAnnotation(getModel_Version(), source, new String[] { "kind", "element", "name", "version", "namespace",
				"##targetNamespace" });
		addAnnotation(getModel_Description(), source, new String[] { "kind", "element", "name", "description",
				"namespace", "##targetNamespace" });
		addAnnotation(getModel_Url(), source, new String[] { "kind", "element", "name", "url", "namespace",
				"##targetNamespace" });
		addAnnotation(getModel_Prerequisites(), source, new String[] { "kind", "element", "name", "prerequisites",
				"namespace", "##targetNamespace" });
		addAnnotation(getModel_IssueManagement(), source, new String[] { "kind", "element", "name", "issueManagement",
				"namespace", "##targetNamespace" });
		addAnnotation(getModel_CiManagement(), source, new String[] { "kind", "element", "name", "ciManagement",
				"namespace", "##targetNamespace" });
		addAnnotation(getModel_InceptionYear(), source, new String[] { "kind", "element", "name", "inceptionYear",
				"namespace", "##targetNamespace" });
		addAnnotation(getModel_MailingLists(), source, new String[] { "kind", "element", "name", "mailingLists",
				"namespace", "##targetNamespace" });
		addAnnotation(getModel_Developers(), source, new String[] { "kind", "element", "name", "developers",
				"namespace", "##targetNamespace" });
		addAnnotation(getModel_Contributors(), source, new String[] { "kind", "element", "name", "contributors",
				"namespace", "##targetNamespace" });
		addAnnotation(getModel_Licenses(), source, new String[] { "kind", "element", "name", "licenses", "namespace",
				"##targetNamespace" });
		addAnnotation(getModel_Scm(), source, new String[] { "kind", "element", "name", "scm", "namespace",
				"##targetNamespace" });
		addAnnotation(getModel_Organization(), source, new String[] { "kind", "element", "name", "organization",
				"namespace", "##targetNamespace" });
		addAnnotation(getModel_Build(), source, new String[] { "kind", "element", "name", "build", "namespace",
				"##targetNamespace" });
		addAnnotation(getModel_Profiles(), source, new String[] { "kind", "element", "name", "profiles", "namespace",
				"##targetNamespace" });
		addAnnotation(getModel_Modules(), source, new String[] { "kind", "element", "name", "modules", "namespace",
				"##targetNamespace" });
		addAnnotation(getModel_Repositories(), source, new String[] { "kind", "element", "name", "repositories",
				"namespace", "##targetNamespace" });
		addAnnotation(getModel_PluginRepositories(), source, new String[] { "kind", "element", "name",
				"pluginRepositories", "namespace", "##targetNamespace" });
		addAnnotation(getModel_Dependencies(), source, new String[] { "kind", "element", "name", "dependencies",
				"namespace", "##targetNamespace" });
		addAnnotation(getModel_Reports(), source, new String[] { "kind", "element", "name", "reports", "namespace",
				"##targetNamespace" });
		addAnnotation(getModel_Reporting(), source, new String[] { "kind", "element", "name", "reporting", "namespace",
				"##targetNamespace" });
		addAnnotation(getModel_DependencyManagement(), source, new String[] { "kind", "element", "name",
				"dependencyManagement", "namespace", "##targetNamespace" });
		addAnnotation(getModel_DistributionManagement(), source, new String[] { "kind", "element", "name",
				"distributionManagement", "namespace", "##targetNamespace" });
		addAnnotation(getModel_Properties(), source, new String[] { "kind", "element", "name", "properties",
				"namespace", "##targetNamespace" });
		addAnnotation(modulesTypeEClass, source, new String[] { "name", "modules_._type", "kind", "elementOnly" });
		addAnnotation(getModulesType_Module(), source, new String[] { "kind", "element", "name", "module", "namespace",
				"##targetNamespace" });
		addAnnotation(notifierEClass, source, new String[] { "name", "Notifier", "kind", "elementOnly" });
		addAnnotation(getNotifier_Type(), source, new String[] { "kind", "element", "name", "type", "namespace",
				"##targetNamespace" });
		addAnnotation(getNotifier_SendOnError(), source, new String[] { "kind", "element", "name", "sendOnError",
				"namespace", "##targetNamespace" });
		addAnnotation(getNotifier_SendOnFailure(), source, new String[] { "kind", "element", "name", "sendOnFailure",
				"namespace", "##targetNamespace" });
		addAnnotation(getNotifier_SendOnSuccess(), source, new String[] { "kind", "element", "name", "sendOnSuccess",
				"namespace", "##targetNamespace" });
		addAnnotation(getNotifier_SendOnWarning(), source, new String[] { "kind", "element", "name", "sendOnWarning",
				"namespace", "##targetNamespace" });
		addAnnotation(getNotifier_Address(), source, new String[] { "kind", "element", "name", "address", "namespace",
				"##targetNamespace" });
		addAnnotation(getNotifier_Configuration(), source, new String[] { "kind", "element", "name", "configuration",
				"namespace", "##targetNamespace" });
		addAnnotation(notifiersTypeEClass, source, new String[] { "name", "notifiers_._type", "kind", "elementOnly" });
		addAnnotation(getNotifiersType_Notifier(), source, new String[] { "kind", "element", "name", "notifier",
				"namespace", "##targetNamespace" });
		addAnnotation(organizationEClass, source, new String[] { "name", "Organization", "kind", "elementOnly" });
		addAnnotation(getOrganization_Name(), source, new String[] { "kind", "element", "name", "name", "namespace",
				"##targetNamespace" });
		addAnnotation(getOrganization_Url(), source, new String[] { "kind", "element", "name", "url", "namespace",
				"##targetNamespace" });
		addAnnotation(otherArchivesTypeEClass, source, new String[] { "name", "otherArchives_._type", "kind",
				"elementOnly" });
		addAnnotation(getOtherArchivesType_OtherArchive(), source, new String[] { "kind", "element", "name",
				"otherArchive", "namespace", "##targetNamespace" });
		addAnnotation(parentEClass, source, new String[] { "name", "Parent", "kind", "elementOnly" });
		addAnnotation(getParent_ArtifactId(), source, new String[] { "kind", "element", "name", "artifactId",
				"namespace", "##targetNamespace" });
		addAnnotation(getParent_GroupId(), source, new String[] { "kind", "element", "name", "groupId", "namespace",
				"##targetNamespace" });
		addAnnotation(getParent_Version(), source, new String[] { "kind", "element", "name", "version", "namespace",
				"##targetNamespace" });
		addAnnotation(getParent_RelativePath(), source, new String[] { "kind", "element", "name", "relativePath",
				"namespace", "##targetNamespace" });
		addAnnotation(pluginEClass, source, new String[] { "name", "Plugin", "kind", "elementOnly" });
		addAnnotation(getPlugin_GroupId(), source, new String[] { "kind", "element", "name", "groupId", "namespace",
				"##targetNamespace" });
		addAnnotation(getPlugin_ArtifactId(), source, new String[] { "kind", "element", "name", "artifactId",
				"namespace", "##targetNamespace" });
		addAnnotation(getPlugin_Version(), source, new String[] { "kind", "element", "name", "version", "namespace",
				"##targetNamespace" });
		addAnnotation(getPlugin_Extensions(), source, new String[] { "kind", "element", "name", "extensions",
				"namespace", "##targetNamespace" });
		addAnnotation(getPlugin_Executions(), source, new String[] { "kind", "element", "name", "executions",
				"namespace", "##targetNamespace" });
		addAnnotation(getPlugin_Dependencies(), source, new String[] { "kind", "element", "name", "dependencies",
				"namespace", "##targetNamespace" });
		addAnnotation(getPlugin_Goals(), source, new String[] { "kind", "element", "name", "goals", "namespace",
				"##targetNamespace" });
		addAnnotation(getPlugin_Inherited(), source, new String[] { "kind", "element", "name", "inherited",
				"namespace", "##targetNamespace" });
		addAnnotation(getPlugin_Configuration(), source, new String[] { "kind", "element", "name", "configuration",
				"namespace", "##targetNamespace" });
		addAnnotation(pluginExecutionEClass, source, new String[] { "name", "PluginExecution", "kind", "elementOnly" });
		addAnnotation(getPluginExecution_Id(), source, new String[] { "kind", "element", "name", "id", "namespace",
				"##targetNamespace" });
		addAnnotation(getPluginExecution_Phase(), source, new String[] { "kind", "element", "name", "phase",
				"namespace", "##targetNamespace" });
		addAnnotation(getPluginExecution_Goals(), source, new String[] { "kind", "element", "name", "goals",
				"namespace", "##targetNamespace" });
		addAnnotation(getPluginExecution_Inherited(), source, new String[] { "kind", "element", "name", "inherited",
				"namespace", "##targetNamespace" });
		addAnnotation(getPluginExecution_Configuration(), source, new String[] { "kind", "element", "name",
				"configuration", "namespace", "##targetNamespace" });
		addAnnotation(pluginManagementEClass, source,
				new String[] { "name", "PluginManagement", "kind", "elementOnly" });
		addAnnotation(getPluginManagement_Plugins(), source, new String[] { "kind", "element", "name", "plugins",
				"namespace", "##targetNamespace" });
		addAnnotation(pluginRepositoriesTypeEClass, source, new String[] { "name", "pluginRepositories_._type", "kind",
				"elementOnly" });
		addAnnotation(getPluginRepositoriesType_PluginRepository(), source, new String[] { "kind", "element", "name",
				"pluginRepository", "namespace", "##targetNamespace" });
		addAnnotation(pluginsTypeEClass, source, new String[] { "name", "plugins_._type", "kind", "elementOnly" });
		addAnnotation(getPluginsType_Plugin(), source, new String[] { "kind", "element", "name", "plugin", "namespace",
				"##targetNamespace" });
		addAnnotation(prerequisitesEClass, source, new String[] { "name", "Prerequisites", "kind", "elementOnly" });
		addAnnotation(getPrerequisites_Maven(), source, new String[] { "kind", "element", "name", "maven", "namespace",
				"##targetNamespace" });
		addAnnotation(profileEClass, source, new String[] { "name", "Profile", "kind", "elementOnly" });
		addAnnotation(getProfile_Id(), source, new String[] { "kind", "element", "name", "id", "namespace",
				"##targetNamespace" });
		addAnnotation(getProfile_Activation(), source, new String[] { "kind", "element", "name", "activation",
				"namespace", "##targetNamespace" });
		addAnnotation(getProfile_Build(), source, new String[] { "kind", "element", "name", "build", "namespace",
				"##targetNamespace" });
		addAnnotation(getProfile_Modules(), source, new String[] { "kind", "element", "name", "modules", "namespace",
				"##targetNamespace" });
		addAnnotation(getProfile_Repositories(), source, new String[] { "kind", "element", "name", "repositories",
				"namespace", "##targetNamespace" });
		addAnnotation(getProfile_PluginRepositories(), source, new String[] { "kind", "element", "name",
				"pluginRepositories", "namespace", "##targetNamespace" });
		addAnnotation(getProfile_Dependencies(), source, new String[] { "kind", "element", "name", "dependencies",
				"namespace", "##targetNamespace" });
		addAnnotation(getProfile_Reports(), source, new String[] { "kind", "element", "name", "reports", "namespace",
				"##targetNamespace" });
		addAnnotation(getProfile_Reporting(), source, new String[] { "kind", "element", "name", "reporting",
				"namespace", "##targetNamespace" });
		addAnnotation(getProfile_DependencyManagement(), source, new String[] { "kind", "element", "name",
				"dependencyManagement", "namespace", "##targetNamespace" });
		addAnnotation(getProfile_DistributionManagement(), source, new String[] { "kind", "element", "name",
				"distributionManagement", "namespace", "##targetNamespace" });
		addAnnotation(getProfile_Properties(), source, new String[] { "kind", "element", "name", "properties",
				"namespace", "##targetNamespace" });
		addAnnotation(profilesTypeEClass, source, new String[] { "name", "profiles_._type", "kind", "elementOnly" });
		addAnnotation(getProfilesType_Profile(), source, new String[] { "kind", "element", "name", "profile",
				"namespace", "##targetNamespace" });
		addAnnotation(propertiesTypeEClass, source, new String[] { "name", "properties_._type", "kind", "elementOnly" });
		addAnnotation(getPropertiesType_Any(), source, new String[] { "kind", "elementWildcard", "wildcards", "##any",
				"name", ":0", "processing", "skip" });
		addAnnotation(relocationEClass, source, new String[] { "name", "Relocation", "kind", "elementOnly" });
		addAnnotation(getRelocation_GroupId(), source, new String[] { "kind", "element", "name", "groupId",
				"namespace", "##targetNamespace" });
		addAnnotation(getRelocation_ArtifactId(), source, new String[] { "kind", "element", "name", "artifactId",
				"namespace", "##targetNamespace" });
		addAnnotation(getRelocation_Version(), source, new String[] { "kind", "element", "name", "version",
				"namespace", "##targetNamespace" });
		addAnnotation(getRelocation_Message(), source, new String[] { "kind", "element", "name", "message",
				"namespace", "##targetNamespace" });
		addAnnotation(reportingEClass, source, new String[] { "name", "Reporting", "kind", "elementOnly" });
		addAnnotation(getReporting_ExcludeDefaults(), source, new String[] { "kind", "element", "name",
				"excludeDefaults", "namespace", "##targetNamespace" });
		addAnnotation(getReporting_OutputDirectory(), source, new String[] { "kind", "element", "name",
				"outputDirectory", "namespace", "##targetNamespace" });
		addAnnotation(getReporting_Plugins(), source, new String[] { "kind", "element", "name", "plugins", "namespace",
				"##targetNamespace" });
		addAnnotation(reportPluginEClass, source, new String[] { "name", "ReportPlugin", "kind", "elementOnly" });
		addAnnotation(getReportPlugin_GroupId(), source, new String[] { "kind", "element", "name", "groupId",
				"namespace", "##targetNamespace" });
		addAnnotation(getReportPlugin_ArtifactId(), source, new String[] { "kind", "element", "name", "artifactId",
				"namespace", "##targetNamespace" });
		addAnnotation(getReportPlugin_Version(), source, new String[] { "kind", "element", "name", "version",
				"namespace", "##targetNamespace" });
		addAnnotation(getReportPlugin_Inherited(), source, new String[] { "kind", "element", "name", "inherited",
				"namespace", "##targetNamespace" });
		addAnnotation(getReportPlugin_Configuration(), source, new String[] { "kind", "element", "name",
				"configuration", "namespace", "##targetNamespace" });
		addAnnotation(getReportPlugin_ReportSets(), source, new String[] { "kind", "element", "name", "reportSets",
				"namespace", "##targetNamespace" });
		addAnnotation(reportingPluginsTypeEClass, source, new String[] { "name", "reporting_._plugins_._type", "kind",
				"elementOnly" });
		addAnnotation(getReportingPluginsType_Plugin(), source, new String[] { "kind", "element", "name", "plugin",
				"namespace", "##targetNamespace" });
		addAnnotation(reportSetEClass, source, new String[] { "name", "ReportSet", "kind", "elementOnly" });
		addAnnotation(getReportSet_Id(), source, new String[] { "kind", "element", "name", "id", "namespace",
				"##targetNamespace" });
		addAnnotation(getReportSet_Configuration(), source, new String[] { "kind", "element", "name", "configuration",
				"namespace", "##targetNamespace" });
		addAnnotation(getReportSet_Inherited(), source, new String[] { "kind", "element", "name", "inherited",
				"namespace", "##targetNamespace" });
		addAnnotation(getReportSet_Reports(), source, new String[] { "kind", "element", "name", "reports", "namespace",
				"##targetNamespace" });
		addAnnotation(reportSetReportsTypeEClass, source, new String[] { "name", "report_._set_._reports_._type",
				"kind", "elementOnly" });
		addAnnotation(getReportSetReportsType_Report(), source, new String[] { "kind", "element", "name", "report",
				"namespace", "##targetNamespace" });
		addAnnotation(reportSetsTypeEClass, source, new String[] { "name", "reportSets_._type", "kind", "elementOnly" });
		addAnnotation(getReportSetsType_ReportSet(), source, new String[] { "kind", "element", "name", "reportSet",
				"namespace", "##targetNamespace" });
		addAnnotation(reportsTypeEClass, source, new String[] { "name", "reports_._type", "kind", "elementOnly" });
		addAnnotation(getReportsType_Any(), source, new String[] { "kind", "elementWildcard", "wildcards", "##any",
				"name", ":0", "processing", "skip" });
		addAnnotation(repositoriesTypeEClass, source, new String[] { "name", "repositories_._type", "kind",
				"elementOnly" });
		addAnnotation(getRepositoriesType_Repository(), source, new String[] { "kind", "element", "name", "repository",
				"namespace", "##targetNamespace" });
		addAnnotation(repositoryEClass, source, new String[] { "name", "Repository", "kind", "elementOnly" });
		addAnnotation(getRepository_Releases(), source, new String[] { "kind", "element", "name", "releases",
				"namespace", "##targetNamespace" });
		addAnnotation(getRepository_Snapshots(), source, new String[] { "kind", "element", "name", "snapshots",
				"namespace", "##targetNamespace" });
		addAnnotation(getRepository_Id(), source, new String[] { "kind", "element", "name", "id", "namespace",
				"##targetNamespace" });
		addAnnotation(getRepository_Name(), source, new String[] { "kind", "element", "name", "name", "namespace",
				"##targetNamespace" });
		addAnnotation(getRepository_Url(), source, new String[] { "kind", "element", "name", "url", "namespace",
				"##targetNamespace" });
		addAnnotation(getRepository_Layout(), source, new String[] { "kind", "element", "name", "layout", "namespace",
				"##targetNamespace" });
		addAnnotation(repositoryPolicyEClass, source,
				new String[] { "name", "RepositoryPolicy", "kind", "elementOnly" });
		addAnnotation(getRepositoryPolicy_Enabled(), source, new String[] { "kind", "element", "name", "enabled",
				"namespace", "##targetNamespace" });
		addAnnotation(getRepositoryPolicy_UpdatePolicy(), source, new String[] { "kind", "element", "name",
				"updatePolicy", "namespace", "##targetNamespace" });
		addAnnotation(getRepositoryPolicy_ChecksumPolicy(), source, new String[] { "kind", "element", "name",
				"checksumPolicy", "namespace", "##targetNamespace" });
		addAnnotation(resourceEClass, source, new String[] { "name", "Resource", "kind", "elementOnly" });
		addAnnotation(getResource_TargetPath(), source, new String[] { "kind", "element", "name", "targetPath",
				"namespace", "##targetNamespace" });
		addAnnotation(getResource_Filtering(), source, new String[] { "kind", "element", "name", "filtering",
				"namespace", "##targetNamespace" });
		addAnnotation(getResource_Directory(), source, new String[] { "kind", "element", "name", "directory",
				"namespace", "##targetNamespace" });
		addAnnotation(getResource_Includes(), source, new String[] { "kind", "element", "name", "includes",
				"namespace", "##targetNamespace" });
		addAnnotation(getResource_Excludes(), source, new String[] { "kind", "element", "name", "excludes",
				"namespace", "##targetNamespace" });
		addAnnotation(resourcesTypeEClass, source, new String[] { "name", "resources_._type", "kind", "elementOnly" });
		addAnnotation(getResourcesType_Resource(), source, new String[] { "kind", "element", "name", "resource",
				"namespace", "##targetNamespace" });
		addAnnotation(rolesTypeEClass, source, new String[] { "name", "roles_._type", "kind", "elementOnly" });
		addAnnotation(getRolesType_Role(), source, new String[] { "kind", "element", "name", "role", "namespace",
				"##targetNamespace" });
		addAnnotation(scmEClass, source, new String[] { "name", "Scm", "kind", "elementOnly" });
		addAnnotation(getScm_Connection(), source, new String[] { "kind", "element", "name", "connection", "namespace",
				"##targetNamespace" });
		addAnnotation(getScm_DeveloperConnection(), source, new String[] { "kind", "element", "name",
				"developerConnection", "namespace", "##targetNamespace" });
		addAnnotation(getScm_Tag(), source, new String[] { "kind", "element", "name", "tag", "namespace",
				"##targetNamespace" });
		addAnnotation(getScm_Url(), source, new String[] { "kind", "element", "name", "url", "namespace",
				"##targetNamespace" });
		addAnnotation(siteEClass, source, new String[] { "name", "Site", "kind", "elementOnly" });
		addAnnotation(getSite_Id(), source, new String[] { "kind", "element", "name", "id", "namespace",
				"##targetNamespace" });
		addAnnotation(getSite_Name(), source, new String[] { "kind", "element", "name", "name", "namespace",
				"##targetNamespace" });
		addAnnotation(getSite_Url(), source, new String[] { "kind", "element", "name", "url", "namespace",
				"##targetNamespace" });
		addAnnotation(testResourcesTypeEClass, source, new String[] { "name", "testResources_._type", "kind",
				"elementOnly" });
		addAnnotation(getTestResourcesType_TestResource(), source, new String[] { "kind", "element", "name",
				"testResource", "namespace", "##targetNamespace" });
	}

} // PomPackageImpl
