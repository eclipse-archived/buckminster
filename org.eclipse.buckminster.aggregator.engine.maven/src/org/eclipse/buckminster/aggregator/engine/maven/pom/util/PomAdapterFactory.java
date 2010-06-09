/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.engine.maven.pom.util;

import org.eclipse.buckminster.aggregator.engine.maven.pom.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides an adapter <code>createXXX</code>
 * method for each class of the model. <!-- end-user-doc -->
 * 
 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage
 * @generated
 */
public class PomAdapterFactory extends AdapterFactoryImpl
{
	/**
	 * The cached model package. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected static PomPackage modelPackage;

	/**
	 * The switch that delegates to the <code>createXXX</code> methods. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected PomSwitch<Adapter> modelSwitch = new PomSwitch<Adapter>()
	{
		@Override
		public Adapter caseActivation(Activation object)
		{
			return createActivationAdapter();
		}

		@Override
		public Adapter caseActivationFile(ActivationFile object)
		{
			return createActivationFileAdapter();
		}

		@Override
		public Adapter caseActivationOS(ActivationOS object)
		{
			return createActivationOSAdapter();
		}

		@Override
		public Adapter caseActivationProperty(ActivationProperty object)
		{
			return createActivationPropertyAdapter();
		}

		@Override
		public Adapter caseBuild(Build object)
		{
			return createBuildAdapter();
		}

		@Override
		public Adapter caseBuildBase(BuildBase object)
		{
			return createBuildBaseAdapter();
		}

		@Override
		public Adapter caseCiManagement(CiManagement object)
		{
			return createCiManagementAdapter();
		}

		@Override
		public Adapter caseConfigurationType(ConfigurationType object)
		{
			return createConfigurationTypeAdapter();
		}

		@Override
		public Adapter caseContributor(Contributor object)
		{
			return createContributorAdapter();
		}

		@Override
		public Adapter caseContributorsType(ContributorsType object)
		{
			return createContributorsTypeAdapter();
		}

		@Override
		public Adapter caseDependenciesType(DependenciesType object)
		{
			return createDependenciesTypeAdapter();
		}

		@Override
		public Adapter caseDependency(Dependency object)
		{
			return createDependencyAdapter();
		}

		@Override
		public Adapter caseDependencyManagement(DependencyManagement object)
		{
			return createDependencyManagementAdapter();
		}

		@Override
		public Adapter caseDeploymentRepository(DeploymentRepository object)
		{
			return createDeploymentRepositoryAdapter();
		}

		@Override
		public Adapter caseDeveloper(Developer object)
		{
			return createDeveloperAdapter();
		}

		@Override
		public Adapter caseDevelopersType(DevelopersType object)
		{
			return createDevelopersTypeAdapter();
		}

		@Override
		public Adapter caseDistributionManagement(DistributionManagement object)
		{
			return createDistributionManagementAdapter();
		}

		@Override
		public Adapter caseDocumentRoot(DocumentRoot object)
		{
			return createDocumentRootAdapter();
		}

		@Override
		public Adapter caseExcludesType(ExcludesType object)
		{
			return createExcludesTypeAdapter();
		}

		@Override
		public Adapter caseExclusion(Exclusion object)
		{
			return createExclusionAdapter();
		}

		@Override
		public Adapter caseExclusionsType(ExclusionsType object)
		{
			return createExclusionsTypeAdapter();
		}

		@Override
		public Adapter caseExecutionGoalsType(ExecutionGoalsType object)
		{
			return createExecutionGoalsTypeAdapter();
		}

		@Override
		public Adapter caseExecutionsType(ExecutionsType object)
		{
			return createExecutionsTypeAdapter();
		}

		@Override
		public Adapter caseExtension(Extension object)
		{
			return createExtensionAdapter();
		}

		@Override
		public Adapter caseExtensionsType(ExtensionsType object)
		{
			return createExtensionsTypeAdapter();
		}

		@Override
		public Adapter caseFiltersType(FiltersType object)
		{
			return createFiltersTypeAdapter();
		}

		@Override
		public Adapter caseGoalsType(GoalsType object)
		{
			return createGoalsTypeAdapter();
		}

		@Override
		public Adapter caseIncludesType(IncludesType object)
		{
			return createIncludesTypeAdapter();
		}

		@Override
		public Adapter caseIssueManagement(IssueManagement object)
		{
			return createIssueManagementAdapter();
		}

		@Override
		public Adapter caseLicense(License object)
		{
			return createLicenseAdapter();
		}

		@Override
		public Adapter caseLicensesType(LicensesType object)
		{
			return createLicensesTypeAdapter();
		}

		@Override
		public Adapter caseMailingList(MailingList object)
		{
			return createMailingListAdapter();
		}

		@Override
		public Adapter caseMailingListsType(MailingListsType object)
		{
			return createMailingListsTypeAdapter();
		}

		@Override
		public Adapter caseModel(Model object)
		{
			return createModelAdapter();
		}

		@Override
		public Adapter caseModulesType(ModulesType object)
		{
			return createModulesTypeAdapter();
		}

		@Override
		public Adapter caseNotifier(org.eclipse.buckminster.aggregator.engine.maven.pom.Notifier object)
		{
			return createNotifierAdapter();
		}

		@Override
		public Adapter caseNotifiersType(NotifiersType object)
		{
			return createNotifiersTypeAdapter();
		}

		@Override
		public Adapter caseOrganization(Organization object)
		{
			return createOrganizationAdapter();
		}

		@Override
		public Adapter caseOtherArchivesType(OtherArchivesType object)
		{
			return createOtherArchivesTypeAdapter();
		}

		@Override
		public Adapter caseParent(Parent object)
		{
			return createParentAdapter();
		}

		@Override
		public Adapter casePlugin(Plugin object)
		{
			return createPluginAdapter();
		}

		@Override
		public Adapter casePluginExecution(PluginExecution object)
		{
			return createPluginExecutionAdapter();
		}

		@Override
		public Adapter casePluginManagement(PluginManagement object)
		{
			return createPluginManagementAdapter();
		}

		@Override
		public Adapter casePluginRepositoriesType(PluginRepositoriesType object)
		{
			return createPluginRepositoriesTypeAdapter();
		}

		@Override
		public Adapter casePluginsType(PluginsType object)
		{
			return createPluginsTypeAdapter();
		}

		@Override
		public Adapter casePrerequisites(Prerequisites object)
		{
			return createPrerequisitesAdapter();
		}

		@Override
		public Adapter caseProfile(Profile object)
		{
			return createProfileAdapter();
		}

		@Override
		public Adapter caseProfilesType(ProfilesType object)
		{
			return createProfilesTypeAdapter();
		}

		@Override
		public Adapter casePropertiesType(PropertiesType object)
		{
			return createPropertiesTypeAdapter();
		}

		@Override
		public Adapter caseRelocation(Relocation object)
		{
			return createRelocationAdapter();
		}

		@Override
		public Adapter caseReporting(Reporting object)
		{
			return createReportingAdapter();
		}

		@Override
		public Adapter caseReportingPluginsType(ReportingPluginsType object)
		{
			return createReportingPluginsTypeAdapter();
		}

		@Override
		public Adapter caseReportPlugin(ReportPlugin object)
		{
			return createReportPluginAdapter();
		}

		@Override
		public Adapter caseReportSet(ReportSet object)
		{
			return createReportSetAdapter();
		}

		@Override
		public Adapter caseReportSetReportsType(ReportSetReportsType object)
		{
			return createReportSetReportsTypeAdapter();
		}

		@Override
		public Adapter caseReportSetsType(ReportSetsType object)
		{
			return createReportSetsTypeAdapter();
		}

		@Override
		public Adapter caseReportsType(ReportsType object)
		{
			return createReportsTypeAdapter();
		}

		@Override
		public Adapter caseRepositoriesType(RepositoriesType object)
		{
			return createRepositoriesTypeAdapter();
		}

		@Override
		public Adapter caseRepository(Repository object)
		{
			return createRepositoryAdapter();
		}

		@Override
		public Adapter caseRepositoryPolicy(RepositoryPolicy object)
		{
			return createRepositoryPolicyAdapter();
		}

		@Override
		public Adapter caseResource(Resource object)
		{
			return createResourceAdapter();
		}

		@Override
		public Adapter caseResourcesType(ResourcesType object)
		{
			return createResourcesTypeAdapter();
		}

		@Override
		public Adapter caseRolesType(RolesType object)
		{
			return createRolesTypeAdapter();
		}

		@Override
		public Adapter caseScm(Scm object)
		{
			return createScmAdapter();
		}

		@Override
		public Adapter caseSite(Site object)
		{
			return createSiteAdapter();
		}

		@Override
		public Adapter caseTestResourcesType(TestResourcesType object)
		{
			return createTestResourcesTypeAdapter();
		}

		@Override
		public Adapter defaultCase(EObject object)
		{
			return createEObjectAdapter();
		}
	};

	/**
	 * Creates an instance of the adapter factory. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public PomAdapterFactory()
	{
		if(modelPackage == null)
		{
			modelPackage = PomPackage.eINSTANCE;
		}
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.Activation <em>Activation</em>}'. <!-- begin-user-doc
	 * --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case
	 * when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.Activation
	 * @generated
	 */
	public Adapter createActivationAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.ActivationFile <em>Activation File</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
	 * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.ActivationFile
	 * @generated
	 */
	public Adapter createActivationFileAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.ActivationOS <em>Activation OS</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
	 * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.ActivationOS
	 * @generated
	 */
	public Adapter createActivationOSAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.ActivationProperty <em>Activation Property</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful
	 * to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.ActivationProperty
	 * @generated
	 */
	public Adapter createActivationPropertyAdapter()
	{
		return null;
	}

	/**
	 * Creates an adapter for the <code>target</code>. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param target
	 *            the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target)
	{
		return modelSwitch.doSwitch((EObject)target);
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.buckminster.aggregator.engine.maven.pom.Build
	 * <em>Build</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
	 * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.Build
	 * @generated
	 */
	public Adapter createBuildAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.BuildBase <em>Build Base</em>}'. <!-- begin-user-doc
	 * --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case
	 * when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.BuildBase
	 * @generated
	 */
	public Adapter createBuildBaseAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.CiManagement <em>Ci Management</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
	 * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.CiManagement
	 * @generated
	 */
	public Adapter createCiManagementAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.ConfigurationType <em>Configuration Type</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
	 * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.ConfigurationType
	 * @generated
	 */
	public Adapter createConfigurationTypeAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.Contributor <em>Contributor</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
	 * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.Contributor
	 * @generated
	 */
	public Adapter createContributorAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.ContributorsType <em>Contributors Type</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
	 * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.ContributorsType
	 * @generated
	 */
	public Adapter createContributorsTypeAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.DependenciesType <em>Dependencies Type</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
	 * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.DependenciesType
	 * @generated
	 */
	public Adapter createDependenciesTypeAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.Dependency <em>Dependency</em>}'. <!-- begin-user-doc
	 * --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case
	 * when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.Dependency
	 * @generated
	 */
	public Adapter createDependencyAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.DependencyManagement <em>Dependency Management</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful
	 * to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.DependencyManagement
	 * @generated
	 */
	public Adapter createDependencyManagementAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.DeploymentRepository <em>Deployment Repository</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful
	 * to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.DeploymentRepository
	 * @generated
	 */
	public Adapter createDeploymentRepositoryAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.Developer <em>Developer</em>}'. <!-- begin-user-doc
	 * --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case
	 * when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.Developer
	 * @generated
	 */
	public Adapter createDeveloperAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.DevelopersType <em>Developers Type</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
	 * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.DevelopersType
	 * @generated
	 */
	public Adapter createDevelopersTypeAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.DistributionManagement
	 * <em>Distribution Management</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we
	 * can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.DistributionManagement
	 * @generated
	 */
	public Adapter createDistributionManagementAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.DocumentRoot <em>Document Root</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
	 * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.DocumentRoot
	 * @generated
	 */
	public Adapter createDocumentRootAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for the default case. <!-- begin-user-doc --> This default implementation returns null.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.ExcludesType <em>Excludes Type</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
	 * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.ExcludesType
	 * @generated
	 */
	public Adapter createExcludesTypeAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.Exclusion <em>Exclusion</em>}'. <!-- begin-user-doc
	 * --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case
	 * when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.Exclusion
	 * @generated
	 */
	public Adapter createExclusionAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.ExclusionsType <em>Exclusions Type</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
	 * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.ExclusionsType
	 * @generated
	 */
	public Adapter createExclusionsTypeAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.ExecutionGoalsType <em>Execution Goals Type</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful
	 * to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.ExecutionGoalsType
	 * @generated
	 */
	public Adapter createExecutionGoalsTypeAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.ExecutionsType <em>Executions Type</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
	 * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.ExecutionsType
	 * @generated
	 */
	public Adapter createExecutionsTypeAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.Extension <em>Extension</em>}'. <!-- begin-user-doc
	 * --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case
	 * when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.Extension
	 * @generated
	 */
	public Adapter createExtensionAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.ExtensionsType <em>Extensions Type</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
	 * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.ExtensionsType
	 * @generated
	 */
	public Adapter createExtensionsTypeAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.FiltersType <em>Filters Type</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
	 * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.FiltersType
	 * @generated
	 */
	public Adapter createFiltersTypeAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.GoalsType <em>Goals Type</em>}'. <!-- begin-user-doc
	 * --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case
	 * when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.GoalsType
	 * @generated
	 */
	public Adapter createGoalsTypeAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.IncludesType <em>Includes Type</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
	 * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.IncludesType
	 * @generated
	 */
	public Adapter createIncludesTypeAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.IssueManagement <em>Issue Management</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
	 * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.IssueManagement
	 * @generated
	 */
	public Adapter createIssueManagementAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.buckminster.aggregator.engine.maven.pom.License
	 * <em>License</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
	 * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.License
	 * @generated
	 */
	public Adapter createLicenseAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.LicensesType <em>Licenses Type</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
	 * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.LicensesType
	 * @generated
	 */
	public Adapter createLicensesTypeAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.MailingList <em>Mailing List</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
	 * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.MailingList
	 * @generated
	 */
	public Adapter createMailingListAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.MailingListsType <em>Mailing Lists Type</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
	 * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.MailingListsType
	 * @generated
	 */
	public Adapter createMailingListsTypeAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.buckminster.aggregator.engine.maven.pom.Model
	 * <em>Model</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
	 * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.Model
	 * @generated
	 */
	public Adapter createModelAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.ModulesType <em>Modules Type</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
	 * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.ModulesType
	 * @generated
	 */
	public Adapter createModulesTypeAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.Notifier <em>Notifier</em>}'. <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case when
	 * inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.Notifier
	 * @generated
	 */
	public Adapter createNotifierAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.NotifiersType <em>Notifiers Type</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
	 * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.NotifiersType
	 * @generated
	 */
	public Adapter createNotifiersTypeAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.Organization <em>Organization</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
	 * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.Organization
	 * @generated
	 */
	public Adapter createOrganizationAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.OtherArchivesType <em>Other Archives Type</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
	 * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.OtherArchivesType
	 * @generated
	 */
	public Adapter createOtherArchivesTypeAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.buckminster.aggregator.engine.maven.pom.Parent
	 * <em>Parent</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
	 * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.Parent
	 * @generated
	 */
	public Adapter createParentAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.buckminster.aggregator.engine.maven.pom.Plugin
	 * <em>Plugin</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
	 * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.Plugin
	 * @generated
	 */
	public Adapter createPluginAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.PluginExecution <em>Plugin Execution</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
	 * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PluginExecution
	 * @generated
	 */
	public Adapter createPluginExecutionAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.PluginManagement <em>Plugin Management</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
	 * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PluginManagement
	 * @generated
	 */
	public Adapter createPluginManagementAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.PluginRepositoriesType
	 * <em>Plugin Repositories Type</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we
	 * can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PluginRepositoriesType
	 * @generated
	 */
	public Adapter createPluginRepositoriesTypeAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.PluginsType <em>Plugins Type</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
	 * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PluginsType
	 * @generated
	 */
	public Adapter createPluginsTypeAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.Prerequisites <em>Prerequisites</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
	 * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.Prerequisites
	 * @generated
	 */
	public Adapter createPrerequisitesAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.buckminster.aggregator.engine.maven.pom.Profile
	 * <em>Profile</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
	 * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.Profile
	 * @generated
	 */
	public Adapter createProfileAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.ProfilesType <em>Profiles Type</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
	 * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.ProfilesType
	 * @generated
	 */
	public Adapter createProfilesTypeAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.PropertiesType <em>Properties Type</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
	 * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PropertiesType
	 * @generated
	 */
	public Adapter createPropertiesTypeAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.Relocation <em>Relocation</em>}'. <!-- begin-user-doc
	 * --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case
	 * when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.Relocation
	 * @generated
	 */
	public Adapter createRelocationAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.Reporting <em>Reporting</em>}'. <!-- begin-user-doc
	 * --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case
	 * when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.Reporting
	 * @generated
	 */
	public Adapter createReportingAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.ReportingPluginsType <em>Reporting Plugins Type</em>}
	 * '. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.ReportingPluginsType
	 * @generated
	 */
	public Adapter createReportingPluginsTypeAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.ReportPlugin <em>Report Plugin</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
	 * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.ReportPlugin
	 * @generated
	 */
	public Adapter createReportPluginAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.ReportSet <em>Report Set</em>}'. <!-- begin-user-doc
	 * --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case
	 * when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.ReportSet
	 * @generated
	 */
	public Adapter createReportSetAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.ReportSetReportsType <em>Report Set Reports Type</em>}
	 * '. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.ReportSetReportsType
	 * @generated
	 */
	public Adapter createReportSetReportsTypeAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.ReportSetsType <em>Report Sets Type</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
	 * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.ReportSetsType
	 * @generated
	 */
	public Adapter createReportSetsTypeAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.ReportsType <em>Reports Type</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
	 * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.ReportsType
	 * @generated
	 */
	public Adapter createReportsTypeAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.RepositoriesType <em>Repositories Type</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
	 * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.RepositoriesType
	 * @generated
	 */
	public Adapter createRepositoriesTypeAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.Repository <em>Repository</em>}'. <!-- begin-user-doc
	 * --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case
	 * when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.Repository
	 * @generated
	 */
	public Adapter createRepositoryAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.RepositoryPolicy <em>Repository Policy</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
	 * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.RepositoryPolicy
	 * @generated
	 */
	public Adapter createRepositoryPolicyAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.Resource <em>Resource</em>}'. <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case when
	 * inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.Resource
	 * @generated
	 */
	public Adapter createResourceAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.ResourcesType <em>Resources Type</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
	 * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.ResourcesType
	 * @generated
	 */
	public Adapter createResourcesTypeAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.RolesType <em>Roles Type</em>}'. <!-- begin-user-doc
	 * --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case
	 * when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.RolesType
	 * @generated
	 */
	public Adapter createRolesTypeAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.buckminster.aggregator.engine.maven.pom.Scm
	 * <em>Scm</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
	 * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.Scm
	 * @generated
	 */
	public Adapter createScmAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.buckminster.aggregator.engine.maven.pom.Site
	 * <em>Site</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
	 * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.Site
	 * @generated
	 */
	public Adapter createSiteAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.TestResourcesType <em>Test Resources Type</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
	 * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.TestResourcesType
	 * @generated
	 */
	public Adapter createTestResourcesTypeAdapter()
	{
		return null;
	}

	/**
	 * Returns whether this factory is applicable for the type of the object. <!-- begin-user-doc --> This
	 * implementation returns <code>true</code> if the object is either the model's package or is an instance object of
	 * the model. <!-- end-user-doc -->
	 * 
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object)
	{
		if(object == modelPackage)
		{
			return true;
		}
		if(object instanceof EObject)
		{
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

} // PomAdapterFactory
