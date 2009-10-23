/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.engine.maven.pom.impl;

import org.eclipse.buckminster.aggregator.engine.maven.pom.BuildBase;
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
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Build Base</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.impl.BuildBaseImpl#getDefaultGoal <em>Default Goal
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.impl.BuildBaseImpl#getResources <em>Resources</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.impl.BuildBaseImpl#getTestResources <em>Test Resources
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.impl.BuildBaseImpl#getDirectory <em>Directory</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.impl.BuildBaseImpl#getFinalName <em>Final Name</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.impl.BuildBaseImpl#getFilters <em>Filters</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.impl.BuildBaseImpl#getPluginManagement <em>Plugin
 * Management</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.impl.BuildBaseImpl#getPlugins <em>Plugins</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class BuildBaseImpl extends EObjectImpl implements BuildBase
{
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
	protected BuildBaseImpl()
	{
		super();
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					PomPackage.BUILD_BASE__FILTERS, oldFilters, newFilters);
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
					PomPackage.BUILD_BASE__PLUGIN_MANAGEMENT, oldPluginManagement, newPluginManagement);
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					PomPackage.BUILD_BASE__PLUGINS, oldPlugins, newPlugins);
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					PomPackage.BUILD_BASE__RESOURCES, oldResources, newResources);
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
					PomPackage.BUILD_BASE__TEST_RESOURCES, oldTestResources, newTestResources);
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
		case PomPackage.BUILD_BASE__DEFAULT_GOAL:
			return getDefaultGoal();
		case PomPackage.BUILD_BASE__RESOURCES:
			return getResources();
		case PomPackage.BUILD_BASE__TEST_RESOURCES:
			return getTestResources();
		case PomPackage.BUILD_BASE__DIRECTORY:
			return getDirectory();
		case PomPackage.BUILD_BASE__FINAL_NAME:
			return getFinalName();
		case PomPackage.BUILD_BASE__FILTERS:
			return getFilters();
		case PomPackage.BUILD_BASE__PLUGIN_MANAGEMENT:
			return getPluginManagement();
		case PomPackage.BUILD_BASE__PLUGINS:
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
		case PomPackage.BUILD_BASE__RESOURCES:
			return basicSetResources(null, msgs);
		case PomPackage.BUILD_BASE__TEST_RESOURCES:
			return basicSetTestResources(null, msgs);
		case PomPackage.BUILD_BASE__FILTERS:
			return basicSetFilters(null, msgs);
		case PomPackage.BUILD_BASE__PLUGIN_MANAGEMENT:
			return basicSetPluginManagement(null, msgs);
		case PomPackage.BUILD_BASE__PLUGINS:
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
		case PomPackage.BUILD_BASE__DEFAULT_GOAL:
			return DEFAULT_GOAL_EDEFAULT == null
					? defaultGoal != null
					: !DEFAULT_GOAL_EDEFAULT.equals(defaultGoal);
		case PomPackage.BUILD_BASE__RESOURCES:
			return resources != null;
		case PomPackage.BUILD_BASE__TEST_RESOURCES:
			return testResources != null;
		case PomPackage.BUILD_BASE__DIRECTORY:
			return DIRECTORY_EDEFAULT == null
					? directory != null
					: !DIRECTORY_EDEFAULT.equals(directory);
		case PomPackage.BUILD_BASE__FINAL_NAME:
			return FINAL_NAME_EDEFAULT == null
					? finalName != null
					: !FINAL_NAME_EDEFAULT.equals(finalName);
		case PomPackage.BUILD_BASE__FILTERS:
			return filters != null;
		case PomPackage.BUILD_BASE__PLUGIN_MANAGEMENT:
			return pluginManagement != null;
		case PomPackage.BUILD_BASE__PLUGINS:
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
		case PomPackage.BUILD_BASE__DEFAULT_GOAL:
			setDefaultGoal((String)newValue);
			return;
		case PomPackage.BUILD_BASE__RESOURCES:
			setResources((ResourcesType)newValue);
			return;
		case PomPackage.BUILD_BASE__TEST_RESOURCES:
			setTestResources((TestResourcesType)newValue);
			return;
		case PomPackage.BUILD_BASE__DIRECTORY:
			setDirectory((String)newValue);
			return;
		case PomPackage.BUILD_BASE__FINAL_NAME:
			setFinalName((String)newValue);
			return;
		case PomPackage.BUILD_BASE__FILTERS:
			setFilters((FiltersType)newValue);
			return;
		case PomPackage.BUILD_BASE__PLUGIN_MANAGEMENT:
			setPluginManagement((PluginManagement)newValue);
			return;
		case PomPackage.BUILD_BASE__PLUGINS:
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
		case PomPackage.BUILD_BASE__DEFAULT_GOAL:
			setDefaultGoal(DEFAULT_GOAL_EDEFAULT);
			return;
		case PomPackage.BUILD_BASE__RESOURCES:
			setResources((ResourcesType)null);
			return;
		case PomPackage.BUILD_BASE__TEST_RESOURCES:
			setTestResources((TestResourcesType)null);
			return;
		case PomPackage.BUILD_BASE__DIRECTORY:
			setDirectory(DIRECTORY_EDEFAULT);
			return;
		case PomPackage.BUILD_BASE__FINAL_NAME:
			setFinalName(FINAL_NAME_EDEFAULT);
			return;
		case PomPackage.BUILD_BASE__FILTERS:
			setFilters((FiltersType)null);
			return;
		case PomPackage.BUILD_BASE__PLUGIN_MANAGEMENT:
			setPluginManagement((PluginManagement)null);
			return;
		case PomPackage.BUILD_BASE__PLUGINS:
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
	public TestResourcesType getTestResources()
	{
		return testResources;
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
			eNotify(new ENotificationImpl(this, Notification.SET, PomPackage.BUILD_BASE__DEFAULT_GOAL, oldDefaultGoal,
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
			eNotify(new ENotificationImpl(this, Notification.SET, PomPackage.BUILD_BASE__DIRECTORY, oldDirectory,
					directory));
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
						- PomPackage.BUILD_BASE__FILTERS, null, msgs);
			if(newFilters != null)
				msgs = ((InternalEObject)newFilters).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
						- PomPackage.BUILD_BASE__FILTERS, null, msgs);
			msgs = basicSetFilters(newFilters, msgs);
			if(msgs != null)
				msgs.dispatch();
		}
		else if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PomPackage.BUILD_BASE__FILTERS, newFilters,
					newFilters));
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
			eNotify(new ENotificationImpl(this, Notification.SET, PomPackage.BUILD_BASE__FINAL_NAME, oldFinalName,
					finalName));
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
						- PomPackage.BUILD_BASE__PLUGIN_MANAGEMENT, null, msgs);
			if(newPluginManagement != null)
				msgs = ((InternalEObject)newPluginManagement).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
						- PomPackage.BUILD_BASE__PLUGIN_MANAGEMENT, null, msgs);
			msgs = basicSetPluginManagement(newPluginManagement, msgs);
			if(msgs != null)
				msgs.dispatch();
		}
		else if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PomPackage.BUILD_BASE__PLUGIN_MANAGEMENT,
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
						- PomPackage.BUILD_BASE__PLUGINS, null, msgs);
			if(newPlugins != null)
				msgs = ((InternalEObject)newPlugins).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
						- PomPackage.BUILD_BASE__PLUGINS, null, msgs);
			msgs = basicSetPlugins(newPlugins, msgs);
			if(msgs != null)
				msgs.dispatch();
		}
		else if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PomPackage.BUILD_BASE__PLUGINS, newPlugins,
					newPlugins));
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
						- PomPackage.BUILD_BASE__RESOURCES, null, msgs);
			if(newResources != null)
				msgs = ((InternalEObject)newResources).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
						- PomPackage.BUILD_BASE__RESOURCES, null, msgs);
			msgs = basicSetResources(newResources, msgs);
			if(msgs != null)
				msgs.dispatch();
		}
		else if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PomPackage.BUILD_BASE__RESOURCES, newResources,
					newResources));
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
						- PomPackage.BUILD_BASE__TEST_RESOURCES, null, msgs);
			if(newTestResources != null)
				msgs = ((InternalEObject)newTestResources).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
						- PomPackage.BUILD_BASE__TEST_RESOURCES, null, msgs);
			msgs = basicSetTestResources(newTestResources, msgs);
			if(msgs != null)
				msgs.dispatch();
		}
		else if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PomPackage.BUILD_BASE__TEST_RESOURCES,
					newTestResources, newTestResources));
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
		result.append(" (defaultGoal: ");
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
		return PomPackage.Literals.BUILD_BASE;
	}

} // BuildBaseImpl
