/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.engine.maven.pom;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Profile</b></em>'. <!-- end-user-doc -->
 * 
 * <!-- begin-model-doc --> 4.0.0
 * 
 * Modifications to the build process which is activated based on environmental parameters or command line arguments.
 * 
 * <!-- end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.Profile#getId <em>Id</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.Profile#getActivation <em>Activation</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.Profile#getBuild <em>Build</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.Profile#getModules <em>Modules</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.Profile#getRepositories <em>Repositories</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.Profile#getPluginRepositories <em>Plugin Repositories
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.Profile#getDependencies <em>Dependencies</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.Profile#getReports <em>Reports</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.Profile#getReporting <em>Reporting</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.Profile#getDependencyManagement <em>Dependency
 * Management</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.Profile#getDistributionManagement <em>Distribution
 * Management</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.Profile#getProperties <em>Properties</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage#getProfile()
 * @model extendedMetaData="name='Profile' kind='elementOnly'"
 * @generated
 */
public interface Profile extends EObject
{
	/**
	 * Returns the value of the '<em><b>Activation</b></em>' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc --> <!-- begin-model-doc --> 4.0.0 The conditional logic which will automatically trigger the
	 * inclusion of this profile. <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Activation</em>' containment reference.
	 * @see #setActivation(Activation)
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage#getProfile_Activation()
	 * @model containment="true" extendedMetaData="kind='element' name='activation' namespace='##targetNamespace'"
	 * @generated
	 */
	Activation getActivation();

	/**
	 * Returns the value of the '<em><b>Build</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * --> <!-- begin-model-doc --> 4.0.0 Information required to build the project. <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Build</em>' containment reference.
	 * @see #setBuild(BuildBase)
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage#getProfile_Build()
	 * @model containment="true" extendedMetaData="kind='element' name='build' namespace='##targetNamespace'"
	 * @generated
	 */
	BuildBase getBuild();

	/**
	 * Returns the value of the '<em><b>Dependencies</b></em>' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc --> <!-- begin-model-doc --> 3.0.0+
	 * 
	 * This element describes all of the dependencies associated with a project. These dependencies are used to
	 * construct a classpath for your project during the build process. They are automatically downloaded from the
	 * repositories defined in this project. See &lt;a
	 * href="http://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism.html"&gt;the dependency
	 * mechanism&lt;/a&gt; for more information.
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Dependencies</em>' containment reference.
	 * @see #setDependencies(DependenciesType)
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage#getProfile_Dependencies()
	 * @model containment="true" extendedMetaData="kind='element' name='dependencies' namespace='##targetNamespace'"
	 * @generated
	 */
	DependenciesType getDependencies();

	/**
	 * Returns the value of the '<em><b>Dependency Management</b></em>' containment reference. <!-- begin-user-doc -->
	 * <!-- end-user-doc --> <!-- begin-model-doc --> 4.0.0
	 * 
	 * Default dependency information for projects that inherit from this one. The dependencies in this section are not
	 * immediately resolved. Instead, when a POM derived from this one declares a dependency described by a matching
	 * groupId and artifactId, the version and other values from this section are used for that dependency if they were
	 * not already specified.
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Dependency Management</em>' containment reference.
	 * @see #setDependencyManagement(DependencyManagement)
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage#getProfile_DependencyManagement()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='dependencyManagement' namespace='##targetNamespace'"
	 * @generated
	 */
	DependencyManagement getDependencyManagement();

	/**
	 * Returns the value of the '<em><b>Distribution Management</b></em>' containment reference. <!-- begin-user-doc -->
	 * <!-- end-user-doc --> <!-- begin-model-doc --> 4.0.0 Distribution information for a project that enables
	 * deployment of the site and artifacts to remote web servers and repositories respectively. <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Distribution Management</em>' containment reference.
	 * @see #setDistributionManagement(DistributionManagement)
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage#getProfile_DistributionManagement()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='distributionManagement' namespace='##targetNamespace'"
	 * @generated
	 */
	DistributionManagement getDistributionManagement();

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
	 * begin-model-doc --> 4.0.0 The identifier of this build profile. This used both for command line activation, and
	 * identifies identical profiles to merge with during inheritance. <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage#getProfile_Id()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='id' namespace='##targetNamespace'"
	 * @generated
	 */
	String getId();

	/**
	 * Returns the value of the '<em><b>Modules</b></em>' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc --> <!-- begin-model-doc --> 4.0.0
	 * 
	 * The modules (sometimes called subprojects) to build as a part of this project. Each module listed is a relative
	 * path to the directory containing the module.
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Modules</em>' containment reference.
	 * @see #setModules(ModulesType)
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage#getProfile_Modules()
	 * @model containment="true" extendedMetaData="kind='element' name='modules' namespace='##targetNamespace'"
	 * @generated
	 */
	ModulesType getModules();

	/**
	 * Returns the value of the '<em><b>Plugin Repositories</b></em>' containment reference. <!-- begin-user-doc -->
	 * <!-- end-user-doc --> <!-- begin-model-doc --> 4.0.0
	 * 
	 * The lists of the remote repositories for discovering plugins for builds and reports. <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Plugin Repositories</em>' containment reference.
	 * @see #setPluginRepositories(PluginRepositoriesType)
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage#getProfile_PluginRepositories()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='pluginRepositories' namespace='##targetNamespace'"
	 * @generated
	 */
	PluginRepositoriesType getPluginRepositories();

	/**
	 * Returns the value of the '<em><b>Properties</b></em>' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc --> <!-- begin-model-doc --> 4.0.0
	 * 
	 * Properties that can be used throughout the POM as a substitution, and are used as filters in resources if
	 * enabled. The format is &lt;code&gt;&amp;lt;name&amp;gt;value&amp;lt;/name&amp;gt;&lt;/code&gt;.
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Properties</em>' containment reference.
	 * @see #setProperties(PropertiesType)
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage#getProfile_Properties()
	 * @model containment="true" extendedMetaData="kind='element' name='properties' namespace='##targetNamespace'"
	 * @generated
	 */
	PropertiesType getProperties();

	/**
	 * Returns the value of the '<em><b>Reporting</b></em>' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc --> <!-- begin-model-doc --> 4.0.0
	 * 
	 * This element includes the specification of report plugins to use to generate the reports on the Maven-generated
	 * site. These reports will be run when a user executes &lt;code&gt;mvn site&lt;/code&gt;. All of the reports will
	 * be included in the navigation bar for browsing.
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Reporting</em>' containment reference.
	 * @see #setReporting(Reporting)
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage#getProfile_Reporting()
	 * @model containment="true" extendedMetaData="kind='element' name='reporting' namespace='##targetNamespace'"
	 * @generated
	 */
	Reporting getReporting();

	/**
	 * Returns the value of the '<em><b>Reports</b></em>' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc --> <!-- begin-model-doc --> 4.0.0
	 * 
	 * &lt;b&gt;Deprecated&lt;/b&gt;. Now ignored by Maven.
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Reports</em>' containment reference.
	 * @see #setReports(ReportsType)
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage#getProfile_Reports()
	 * @model containment="true" extendedMetaData="kind='element' name='reports' namespace='##targetNamespace'"
	 * @generated
	 */
	ReportsType getReports();

	/**
	 * Returns the value of the '<em><b>Repositories</b></em>' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc --> <!-- begin-model-doc --> 4.0.0 The lists of the remote repositories for discovering dependencies
	 * and extensions. <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Repositories</em>' containment reference.
	 * @see #setRepositories(RepositoriesType)
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage#getProfile_Repositories()
	 * @model containment="true" extendedMetaData="kind='element' name='repositories' namespace='##targetNamespace'"
	 * @generated
	 */
	RepositoriesType getRepositories();

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.engine.maven.pom.Profile#getActivation
	 * <em>Activation</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Activation</em>' containment reference.
	 * @see #getActivation()
	 * @generated
	 */
	void setActivation(Activation value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.engine.maven.pom.Profile#getBuild
	 * <em>Build</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Build</em>' containment reference.
	 * @see #getBuild()
	 * @generated
	 */
	void setBuild(BuildBase value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.engine.maven.pom.Profile#getDependencies
	 * <em>Dependencies</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Dependencies</em>' containment reference.
	 * @see #getDependencies()
	 * @generated
	 */
	void setDependencies(DependenciesType value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.Profile#getDependencyManagement
	 * <em>Dependency Management</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Dependency Management</em>' containment reference.
	 * @see #getDependencyManagement()
	 * @generated
	 */
	void setDependencyManagement(DependencyManagement value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.Profile#getDistributionManagement
	 * <em>Distribution Management</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Distribution Management</em>' containment reference.
	 * @see #getDistributionManagement()
	 * @generated
	 */
	void setDistributionManagement(DistributionManagement value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.engine.maven.pom.Profile#getId <em>Id</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.engine.maven.pom.Profile#getModules
	 * <em>Modules</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Modules</em>' containment reference.
	 * @see #getModules()
	 * @generated
	 */
	void setModules(ModulesType value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.engine.maven.pom.Profile#getPluginRepositories
	 * <em>Plugin Repositories</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Plugin Repositories</em>' containment reference.
	 * @see #getPluginRepositories()
	 * @generated
	 */
	void setPluginRepositories(PluginRepositoriesType value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.engine.maven.pom.Profile#getProperties
	 * <em>Properties</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Properties</em>' containment reference.
	 * @see #getProperties()
	 * @generated
	 */
	void setProperties(PropertiesType value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.engine.maven.pom.Profile#getReporting
	 * <em>Reporting</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Reporting</em>' containment reference.
	 * @see #getReporting()
	 * @generated
	 */
	void setReporting(Reporting value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.engine.maven.pom.Profile#getReports
	 * <em>Reports</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Reports</em>' containment reference.
	 * @see #getReports()
	 * @generated
	 */
	void setReports(ReportsType value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.engine.maven.pom.Profile#getRepositories
	 * <em>Repositories</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Repositories</em>' containment reference.
	 * @see #getRepositories()
	 * @generated
	 */
	void setRepositories(RepositoriesType value);

} // Profile
