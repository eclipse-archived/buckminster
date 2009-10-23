/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.engine.maven.pom.impl;

import org.eclipse.buckminster.aggregator.engine.maven.pom.Build;
import org.eclipse.buckminster.aggregator.engine.maven.pom.ExtensionsType;
import org.eclipse.buckminster.aggregator.engine.maven.pom.FiltersType;
import org.eclipse.buckminster.aggregator.engine.maven.pom.PluginManagement;
import org.eclipse.buckminster.aggregator.engine.maven.pom.PluginsType;
import org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage;
import org.eclipse.buckminster.aggregator.engine.maven.pom.ResourcesType;
import org.eclipse.buckminster.aggregator.engine.maven.pom.TestResourcesType;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Build</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.impl.BuildImpl#getSourceDirectory <em>Source Directory
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.impl.BuildImpl#getScriptSourceDirectory <em>Script
 * Source Directory</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.impl.BuildImpl#getTestSourceDirectory <em>Test Source
 * Directory</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.impl.BuildImpl#getOutputDirectory <em>Output Directory
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.impl.BuildImpl#getTestOutputDirectory <em>Test Output
 * Directory</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.impl.BuildImpl#getExtensions <em>Extensions</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.impl.BuildImpl#getDefaultGoal <em>Default Goal</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.impl.BuildImpl#getResources <em>Resources</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.impl.BuildImpl#getTestResources <em>Test Resources
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.impl.BuildImpl#getDirectory <em>Directory</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.impl.BuildImpl#getFinalName <em>Final Name</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.impl.BuildImpl#getFilters <em>Filters</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.impl.BuildImpl#getPluginManagement <em>Plugin
 * Management</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.impl.BuildImpl#getPlugins <em>Plugins</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class BuildImpl extends EObjectImpl implements Build
{
	/**
	 * The default value of the '{@link #getSourceDirectory() <em>Source Directory</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getSourceDirectory()
	 * @generated
	 * @ordered
	 */
	protected static final String SOURCE_DIRECTORY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSourceDirectory() <em>Source Directory</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getSourceDirectory()
	 * @generated
	 * @ordered
	 */
	protected String sourceDirectory = SOURCE_DIRECTORY_EDEFAULT;

	/**
	 * The default value of the '{@link #getScriptSourceDirectory() <em>Script Source Directory</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getScriptSourceDirectory()
	 * @generated
	 * @ordered
	 */
	protected static final String SCRIPT_SOURCE_DIRECTORY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getScriptSourceDirectory() <em>Script Source Directory</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getScriptSourceDirectory()
	 * @generated
	 * @ordered
	 */
	protected String scriptSourceDirectory = SCRIPT_SOURCE_DIRECTORY_EDEFAULT;

	/**
	 * The default value of the '{@link #getTestSourceDirectory() <em>Test Source Directory</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getTestSourceDirectory()
	 * @generated
	 * @ordered
	 */
	protected static final String TEST_SOURCE_DIRECTORY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTestSourceDirectory() <em>Test Source Directory</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getTestSourceDirectory()
	 * @generated
	 * @ordered
	 */
	protected String testSourceDirectory = TEST_SOURCE_DIRECTORY_EDEFAULT;

	/**
	 * The default value of the '{@link #getOutputDirectory() <em>Output Directory</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getOutputDirectory()
	 * @generated
	 * @ordered
	 */
	protected static final String OUTPUT_DIRECTORY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOutputDirectory() <em>Output Directory</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getOutputDirectory()
	 * @generated
	 * @ordered
	 */
	protected String outputDirectory = OUTPUT_DIRECTORY_EDEFAULT;

	/**
	 * The default value of the '{@link #getTestOutputDirectory() <em>Test Output Directory</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getTestOutputDirectory()
	 * @generated
	 * @ordered
	 */
	protected static final String TEST_OUTPUT_DIRECTORY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTestOutputDirectory() <em>Test Output Directory</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getTestOutputDirectory()
	 * @generated
	 * @ordered
	 */
	protected String testOutputDirectory = TEST_OUTPUT_DIRECTORY_EDEFAULT;

	/**
	 * The cached value of the '{@link #getExtensions() <em>Extensions</em>}' containment reference. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getExtensions()
	 * @generated
	 * @ordered
	 */
	protected ExtensionsType extensions;

	/**
	 * The default value of the '{@link #getDefaultGoal() <em>Default Goal</em>}' attribute. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getDefaultGoal()
	 * @generated
	 * @ordered
	 */
	protected static final String DEFAULT_GOAL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDefaultGoal() <em>Default Goal</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getDefaultGoal()
	 * @generated
	 * @ordered
	 */
	protected String defaultGoal = DEFAULT_GOAL_EDEFAULT;

	/**
	 * The cached value of the '{@link #getResources() <em>Resources</em>}' containment reference. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getResources()
	 * @generated
	 * @ordered
	 */
	protected ResourcesType resources;

	/**
	 * The cached value of the '{@link #getTestResources() <em>Test Resources</em>}' containment reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getTestResources()
	 * @generated
	 * @ordered
	 */
	protected TestResourcesType testResources;

	/**
	 * The default value of the '{@link #getDirectory() <em>Directory</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getDirectory()
	 * @generated
	 * @ordered
	 */
	protected static final String DIRECTORY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDirectory() <em>Directory</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getDirectory()
	 * @generated
	 * @ordered
	 */
	protected String directory = DIRECTORY_EDEFAULT;

	/**
	 * The default value of the '{@link #getFinalName() <em>Final Name</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getFinalName()
	 * @generated
	 * @ordered
	 */
	protected static final String FINAL_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFinalName() <em>Final Name</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getFinalName()
	 * @generated
	 * @ordered
	 */
	protected String finalName = FINAL_NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getFilters() <em>Filters</em>}' containment reference. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getFilters()
	 * @generated
	 * @ordered
	 */
	protected FiltersType filters;

	/**
	 * The cached value of the '{@link #getPluginManagement() <em>Plugin Management</em>}' containment reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getPluginManagement()
	 * @generated
	 * @ordered
	 */
	protected PluginManagement pluginManagement;

	/**
	 * The cached value of the '{@link #getPlugins() <em>Plugins</em>}' containment reference. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getPlugins()
	 * @generated
	 * @ordered
	 */
	protected PluginsType plugins;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected BuildImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetExtensions(ExtensionsType newExtensions, NotificationChain msgs)
	{
		ExtensionsType oldExtensions = extensions;
		extensions = newExtensions;
		if(eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					PomPackage.BUILD__EXTENSIONS, oldExtensions, newExtensions);
			if(msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetFilters(FiltersType newFilters, NotificationChain msgs)
	{
		FiltersType oldFilters = filters;
		filters = newFilters;
		if(eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PomPackage.BUILD__FILTERS,
					oldFilters, newFilters);
			if(msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetPluginManagement(PluginManagement newPluginManagement, NotificationChain msgs)
	{
		PluginManagement oldPluginManagement = pluginManagement;
		pluginManagement = newPluginManagement;
		if(eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					PomPackage.BUILD__PLUGIN_MANAGEMENT, oldPluginManagement, newPluginManagement);
			if(msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetPlugins(PluginsType newPlugins, NotificationChain msgs)
	{
		PluginsType oldPlugins = plugins;
		plugins = newPlugins;
		if(eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PomPackage.BUILD__PLUGINS,
					oldPlugins, newPlugins);
			if(msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetResources(ResourcesType newResources, NotificationChain msgs)
	{
		ResourcesType oldResources = resources;
		resources = newResources;
		if(eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PomPackage.BUILD__RESOURCES,
					oldResources, newResources);
			if(msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetTestResources(TestResourcesType newTestResources, NotificationChain msgs)
	{
		TestResourcesType oldTestResources = testResources;
		testResources = newTestResources;
		if(eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					PomPackage.BUILD__TEST_RESOURCES, oldTestResources, newTestResources);
			if(msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType)
	{
		switch(featureID)
		{
		case PomPackage.BUILD__SOURCE_DIRECTORY:
			return getSourceDirectory();
		case PomPackage.BUILD__SCRIPT_SOURCE_DIRECTORY:
			return getScriptSourceDirectory();
		case PomPackage.BUILD__TEST_SOURCE_DIRECTORY:
			return getTestSourceDirectory();
		case PomPackage.BUILD__OUTPUT_DIRECTORY:
			return getOutputDirectory();
		case PomPackage.BUILD__TEST_OUTPUT_DIRECTORY:
			return getTestOutputDirectory();
		case PomPackage.BUILD__EXTENSIONS:
			return getExtensions();
		case PomPackage.BUILD__DEFAULT_GOAL:
			return getDefaultGoal();
		case PomPackage.BUILD__RESOURCES:
			return getResources();
		case PomPackage.BUILD__TEST_RESOURCES:
			return getTestResources();
		case PomPackage.BUILD__DIRECTORY:
			return getDirectory();
		case PomPackage.BUILD__FINAL_NAME:
			return getFinalName();
		case PomPackage.BUILD__FILTERS:
			return getFilters();
		case PomPackage.BUILD__PLUGIN_MANAGEMENT:
			return getPluginManagement();
		case PomPackage.BUILD__PLUGINS:
			return getPlugins();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
	{
		switch(featureID)
		{
		case PomPackage.BUILD__EXTENSIONS:
			return basicSetExtensions(null, msgs);
		case PomPackage.BUILD__RESOURCES:
			return basicSetResources(null, msgs);
		case PomPackage.BUILD__TEST_RESOURCES:
			return basicSetTestResources(null, msgs);
		case PomPackage.BUILD__FILTERS:
			return basicSetFilters(null, msgs);
		case PomPackage.BUILD__PLUGIN_MANAGEMENT:
			return basicSetPluginManagement(null, msgs);
		case PomPackage.BUILD__PLUGINS:
			return basicSetPlugins(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID)
	{
		switch(featureID)
		{
		case PomPackage.BUILD__SOURCE_DIRECTORY:
			return SOURCE_DIRECTORY_EDEFAULT == null
					? sourceDirectory != null
					: !SOURCE_DIRECTORY_EDEFAULT.equals(sourceDirectory);
		case PomPackage.BUILD__SCRIPT_SOURCE_DIRECTORY:
			return SCRIPT_SOURCE_DIRECTORY_EDEFAULT == null
					? scriptSourceDirectory != null
					: !SCRIPT_SOURCE_DIRECTORY_EDEFAULT.equals(scriptSourceDirectory);
		case PomPackage.BUILD__TEST_SOURCE_DIRECTORY:
			return TEST_SOURCE_DIRECTORY_EDEFAULT == null
					? testSourceDirectory != null
					: !TEST_SOURCE_DIRECTORY_EDEFAULT.equals(testSourceDirectory);
		case PomPackage.BUILD__OUTPUT_DIRECTORY:
			return OUTPUT_DIRECTORY_EDEFAULT == null
					? outputDirectory != null
					: !OUTPUT_DIRECTORY_EDEFAULT.equals(outputDirectory);
		case PomPackage.BUILD__TEST_OUTPUT_DIRECTORY:
			return TEST_OUTPUT_DIRECTORY_EDEFAULT == null
					? testOutputDirectory != null
					: !TEST_OUTPUT_DIRECTORY_EDEFAULT.equals(testOutputDirectory);
		case PomPackage.BUILD__EXTENSIONS:
			return extensions != null;
		case PomPackage.BUILD__DEFAULT_GOAL:
			return DEFAULT_GOAL_EDEFAULT == null
					? defaultGoal != null
					: !DEFAULT_GOAL_EDEFAULT.equals(defaultGoal);
		case PomPackage.BUILD__RESOURCES:
			return resources != null;
		case PomPackage.BUILD__TEST_RESOURCES:
			return testResources != null;
		case PomPackage.BUILD__DIRECTORY:
			return DIRECTORY_EDEFAULT == null
					? directory != null
					: !DIRECTORY_EDEFAULT.equals(directory);
		case PomPackage.BUILD__FINAL_NAME:
			return FINAL_NAME_EDEFAULT == null
					? finalName != null
					: !FINAL_NAME_EDEFAULT.equals(finalName);
		case PomPackage.BUILD__FILTERS:
			return filters != null;
		case PomPackage.BUILD__PLUGIN_MANAGEMENT:
			return pluginManagement != null;
		case PomPackage.BUILD__PLUGINS:
			return plugins != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue)
	{
		switch(featureID)
		{
		case PomPackage.BUILD__SOURCE_DIRECTORY:
			setSourceDirectory((String)newValue);
			return;
		case PomPackage.BUILD__SCRIPT_SOURCE_DIRECTORY:
			setScriptSourceDirectory((String)newValue);
			return;
		case PomPackage.BUILD__TEST_SOURCE_DIRECTORY:
			setTestSourceDirectory((String)newValue);
			return;
		case PomPackage.BUILD__OUTPUT_DIRECTORY:
			setOutputDirectory((String)newValue);
			return;
		case PomPackage.BUILD__TEST_OUTPUT_DIRECTORY:
			setTestOutputDirectory((String)newValue);
			return;
		case PomPackage.BUILD__EXTENSIONS:
			setExtensions((ExtensionsType)newValue);
			return;
		case PomPackage.BUILD__DEFAULT_GOAL:
			setDefaultGoal((String)newValue);
			return;
		case PomPackage.BUILD__RESOURCES:
			setResources((ResourcesType)newValue);
			return;
		case PomPackage.BUILD__TEST_RESOURCES:
			setTestResources((TestResourcesType)newValue);
			return;
		case PomPackage.BUILD__DIRECTORY:
			setDirectory((String)newValue);
			return;
		case PomPackage.BUILD__FINAL_NAME:
			setFinalName((String)newValue);
			return;
		case PomPackage.BUILD__FILTERS:
			setFilters((FiltersType)newValue);
			return;
		case PomPackage.BUILD__PLUGIN_MANAGEMENT:
			setPluginManagement((PluginManagement)newValue);
			return;
		case PomPackage.BUILD__PLUGINS:
			setPlugins((PluginsType)newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eUnset(int featureID)
	{
		switch(featureID)
		{
		case PomPackage.BUILD__SOURCE_DIRECTORY:
			setSourceDirectory(SOURCE_DIRECTORY_EDEFAULT);
			return;
		case PomPackage.BUILD__SCRIPT_SOURCE_DIRECTORY:
			setScriptSourceDirectory(SCRIPT_SOURCE_DIRECTORY_EDEFAULT);
			return;
		case PomPackage.BUILD__TEST_SOURCE_DIRECTORY:
			setTestSourceDirectory(TEST_SOURCE_DIRECTORY_EDEFAULT);
			return;
		case PomPackage.BUILD__OUTPUT_DIRECTORY:
			setOutputDirectory(OUTPUT_DIRECTORY_EDEFAULT);
			return;
		case PomPackage.BUILD__TEST_OUTPUT_DIRECTORY:
			setTestOutputDirectory(TEST_OUTPUT_DIRECTORY_EDEFAULT);
			return;
		case PomPackage.BUILD__EXTENSIONS:
			setExtensions((ExtensionsType)null);
			return;
		case PomPackage.BUILD__DEFAULT_GOAL:
			setDefaultGoal(DEFAULT_GOAL_EDEFAULT);
			return;
		case PomPackage.BUILD__RESOURCES:
			setResources((ResourcesType)null);
			return;
		case PomPackage.BUILD__TEST_RESOURCES:
			setTestResources((TestResourcesType)null);
			return;
		case PomPackage.BUILD__DIRECTORY:
			setDirectory(DIRECTORY_EDEFAULT);
			return;
		case PomPackage.BUILD__FINAL_NAME:
			setFinalName(FINAL_NAME_EDEFAULT);
			return;
		case PomPackage.BUILD__FILTERS:
			setFilters((FiltersType)null);
			return;
		case PomPackage.BUILD__PLUGIN_MANAGEMENT:
			setPluginManagement((PluginManagement)null);
			return;
		case PomPackage.BUILD__PLUGINS:
			setPlugins((PluginsType)null);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getDefaultGoal()
	{
		return defaultGoal;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getDirectory()
	{
		return directory;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ExtensionsType getExtensions()
	{
		return extensions;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public FiltersType getFilters()
	{
		return filters;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getFinalName()
	{
		return finalName;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getOutputDirectory()
	{
		return outputDirectory;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public PluginManagement getPluginManagement()
	{
		return pluginManagement;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public PluginsType getPlugins()
	{
		return plugins;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ResourcesType getResources()
	{
		return resources;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getScriptSourceDirectory()
	{
		return scriptSourceDirectory;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getSourceDirectory()
	{
		return sourceDirectory;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getTestOutputDirectory()
	{
		return testOutputDirectory;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public TestResourcesType getTestResources()
	{
		return testResources;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getTestSourceDirectory()
	{
		return testSourceDirectory;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setDefaultGoal(String newDefaultGoal)
	{
		String oldDefaultGoal = defaultGoal;
		defaultGoal = newDefaultGoal;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PomPackage.BUILD__DEFAULT_GOAL, oldDefaultGoal,
					defaultGoal));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setDirectory(String newDirectory)
	{
		String oldDirectory = directory;
		directory = newDirectory;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PomPackage.BUILD__DIRECTORY, oldDirectory, directory));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setExtensions(ExtensionsType newExtensions)
	{
		if(newExtensions != extensions)
		{
			NotificationChain msgs = null;
			if(extensions != null)
				msgs = ((InternalEObject)extensions).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
						- PomPackage.BUILD__EXTENSIONS, null, msgs);
			if(newExtensions != null)
				msgs = ((InternalEObject)newExtensions).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
						- PomPackage.BUILD__EXTENSIONS, null, msgs);
			msgs = basicSetExtensions(newExtensions, msgs);
			if(msgs != null)
				msgs.dispatch();
		}
		else if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PomPackage.BUILD__EXTENSIONS, newExtensions,
					newExtensions));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setFilters(FiltersType newFilters)
	{
		if(newFilters != filters)
		{
			NotificationChain msgs = null;
			if(filters != null)
				msgs = ((InternalEObject)filters).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
						- PomPackage.BUILD__FILTERS, null, msgs);
			if(newFilters != null)
				msgs = ((InternalEObject)newFilters).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
						- PomPackage.BUILD__FILTERS, null, msgs);
			msgs = basicSetFilters(newFilters, msgs);
			if(msgs != null)
				msgs.dispatch();
		}
		else if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PomPackage.BUILD__FILTERS, newFilters, newFilters));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setFinalName(String newFinalName)
	{
		String oldFinalName = finalName;
		finalName = newFinalName;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PomPackage.BUILD__FINAL_NAME, oldFinalName, finalName));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setOutputDirectory(String newOutputDirectory)
	{
		String oldOutputDirectory = outputDirectory;
		outputDirectory = newOutputDirectory;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PomPackage.BUILD__OUTPUT_DIRECTORY,
					oldOutputDirectory, outputDirectory));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setPluginManagement(PluginManagement newPluginManagement)
	{
		if(newPluginManagement != pluginManagement)
		{
			NotificationChain msgs = null;
			if(pluginManagement != null)
				msgs = ((InternalEObject)pluginManagement).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
						- PomPackage.BUILD__PLUGIN_MANAGEMENT, null, msgs);
			if(newPluginManagement != null)
				msgs = ((InternalEObject)newPluginManagement).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
						- PomPackage.BUILD__PLUGIN_MANAGEMENT, null, msgs);
			msgs = basicSetPluginManagement(newPluginManagement, msgs);
			if(msgs != null)
				msgs.dispatch();
		}
		else if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PomPackage.BUILD__PLUGIN_MANAGEMENT,
					newPluginManagement, newPluginManagement));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setPlugins(PluginsType newPlugins)
	{
		if(newPlugins != plugins)
		{
			NotificationChain msgs = null;
			if(plugins != null)
				msgs = ((InternalEObject)plugins).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
						- PomPackage.BUILD__PLUGINS, null, msgs);
			if(newPlugins != null)
				msgs = ((InternalEObject)newPlugins).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
						- PomPackage.BUILD__PLUGINS, null, msgs);
			msgs = basicSetPlugins(newPlugins, msgs);
			if(msgs != null)
				msgs.dispatch();
		}
		else if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PomPackage.BUILD__PLUGINS, newPlugins, newPlugins));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setResources(ResourcesType newResources)
	{
		if(newResources != resources)
		{
			NotificationChain msgs = null;
			if(resources != null)
				msgs = ((InternalEObject)resources).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
						- PomPackage.BUILD__RESOURCES, null, msgs);
			if(newResources != null)
				msgs = ((InternalEObject)newResources).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
						- PomPackage.BUILD__RESOURCES, null, msgs);
			msgs = basicSetResources(newResources, msgs);
			if(msgs != null)
				msgs.dispatch();
		}
		else if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PomPackage.BUILD__RESOURCES, newResources,
					newResources));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setScriptSourceDirectory(String newScriptSourceDirectory)
	{
		String oldScriptSourceDirectory = scriptSourceDirectory;
		scriptSourceDirectory = newScriptSourceDirectory;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PomPackage.BUILD__SCRIPT_SOURCE_DIRECTORY,
					oldScriptSourceDirectory, scriptSourceDirectory));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setSourceDirectory(String newSourceDirectory)
	{
		String oldSourceDirectory = sourceDirectory;
		sourceDirectory = newSourceDirectory;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PomPackage.BUILD__SOURCE_DIRECTORY,
					oldSourceDirectory, sourceDirectory));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setTestOutputDirectory(String newTestOutputDirectory)
	{
		String oldTestOutputDirectory = testOutputDirectory;
		testOutputDirectory = newTestOutputDirectory;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PomPackage.BUILD__TEST_OUTPUT_DIRECTORY,
					oldTestOutputDirectory, testOutputDirectory));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setTestResources(TestResourcesType newTestResources)
	{
		if(newTestResources != testResources)
		{
			NotificationChain msgs = null;
			if(testResources != null)
				msgs = ((InternalEObject)testResources).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
						- PomPackage.BUILD__TEST_RESOURCES, null, msgs);
			if(newTestResources != null)
				msgs = ((InternalEObject)newTestResources).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
						- PomPackage.BUILD__TEST_RESOURCES, null, msgs);
			msgs = basicSetTestResources(newTestResources, msgs);
			if(msgs != null)
				msgs.dispatch();
		}
		else if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PomPackage.BUILD__TEST_RESOURCES, newTestResources,
					newTestResources));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setTestSourceDirectory(String newTestSourceDirectory)
	{
		String oldTestSourceDirectory = testSourceDirectory;
		testSourceDirectory = newTestSourceDirectory;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PomPackage.BUILD__TEST_SOURCE_DIRECTORY,
					oldTestSourceDirectory, testSourceDirectory));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String toString()
	{
		if(eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (sourceDirectory: ");
		result.append(sourceDirectory);
		result.append(", scriptSourceDirectory: ");
		result.append(scriptSourceDirectory);
		result.append(", testSourceDirectory: ");
		result.append(testSourceDirectory);
		result.append(", outputDirectory: ");
		result.append(outputDirectory);
		result.append(", testOutputDirectory: ");
		result.append(testOutputDirectory);
		result.append(", defaultGoal: ");
		result.append(defaultGoal);
		result.append(", directory: ");
		result.append(directory);
		result.append(", finalName: ");
		result.append(finalName);
		result.append(')');
		return result.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass()
	{
		return PomPackage.Literals.BUILD;
	}

} // BuildImpl
