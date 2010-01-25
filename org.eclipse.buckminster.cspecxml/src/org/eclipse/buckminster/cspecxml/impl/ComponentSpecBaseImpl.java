/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspecxml.impl;

import java.net.URL;
import java.util.Collection;

import org.eclipse.buckminster.cspecxml.IActionsType;
import org.eclipse.buckminster.cspecxml.IArtifactsType;
import org.eclipse.buckminster.cspecxml.ICSpecXMLPackage;
import org.eclipse.buckminster.cspecxml.IComponentSpecBase;
import org.eclipse.buckminster.cspecxml.IDependenciesType;
import org.eclipse.buckminster.cspecxml.IGeneratorsType;
import org.eclipse.buckminster.cspecxml.IGroupsType;
import org.eclipse.buckminster.model.common.Documentation;
import org.eclipse.buckminster.model.common.util.VersionHelper;
import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.equinox.p2.metadata.Version;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Component Spec Base</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.ComponentSpecBaseImpl#getDocumentation <em>Documentation</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.ComponentSpecBaseImpl#getGroup <em>Group</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.ComponentSpecBaseImpl#getDependencies <em>Dependencies</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.ComponentSpecBaseImpl#getGenerators <em>Generators</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.ComponentSpecBaseImpl#getArtifacts <em>Artifacts</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.ComponentSpecBaseImpl#getGroups <em>Groups</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.ComponentSpecBaseImpl#getActions <em>Actions</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.ComponentSpecBaseImpl#getCategory <em>Category</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.ComponentSpecBaseImpl#getComponentType <em>Component Type</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.ComponentSpecBaseImpl#getFilter <em>Filter</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.ComponentSpecBaseImpl#getProjectInfo <em>Project Info</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.ComponentSpecBaseImpl#getShortDesc <em>Short Desc</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.ComponentSpecBaseImpl#getVersionString <em>Version String</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.ComponentSpecBaseImpl#getVersionType <em>Version Type</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class ComponentSpecBaseImpl extends EObjectImpl implements IComponentSpecBase
{
	/**
	 * The cached value of the '{@link #getDocumentation() <em>Documentation</em>}' containment reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getDocumentation()
	 * @generated
	 * @ordered
	 */
	protected Documentation documentation;

	/**
	 * The cached value of the '{@link #getGroup() <em>Group</em>}' attribute list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getGroup()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap group;

	/**
	 * The default value of the '{@link #getCategory() <em>Category</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getCategory()
	 * @generated
	 * @ordered
	 */
	protected static final String CATEGORY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCategory() <em>Category</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getCategory()
	 * @generated
	 * @ordered
	 */
	protected String category = CATEGORY_EDEFAULT;

	/**
	 * The default value of the '{@link #getComponentType() <em>Component Type</em>}' attribute. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getComponentType()
	 * @generated
	 * @ordered
	 */
	protected static final String COMPONENT_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getComponentType() <em>Component Type</em>}' attribute. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getComponentType()
	 * @generated
	 * @ordered
	 */
	protected String componentType = COMPONENT_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getFilter() <em>Filter</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getFilter()
	 * @generated
	 * @ordered
	 */
	protected static final Filter FILTER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFilter() <em>Filter</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getFilter()
	 * @generated
	 * @ordered
	 */
	protected Filter filter = FILTER_EDEFAULT;

	/**
	 * The default value of the '{@link #getProjectInfo() <em>Project Info</em>}' attribute. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getProjectInfo()
	 * @generated
	 * @ordered
	 */
	protected static final URL PROJECT_INFO_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getProjectInfo() <em>Project Info</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getProjectInfo()
	 * @generated
	 * @ordered
	 */
	protected URL projectInfo = PROJECT_INFO_EDEFAULT;

	/**
	 * The default value of the '{@link #getShortDesc() <em>Short Desc</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getShortDesc()
	 * @generated
	 * @ordered
	 */
	protected static final String SHORT_DESC_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getShortDesc() <em>Short Desc</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getShortDesc()
	 * @generated
	 * @ordered
	 */
	protected String shortDesc = SHORT_DESC_EDEFAULT;

	/**
	 * The default value of the '{@link #getVersionString() <em>Version String</em>}' attribute. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getVersionString()
	 * @generated
	 * @ordered
	 */
	protected static final String VERSION_STRING_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVersionString() <em>Version String</em>}' attribute. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getVersionString()
	 * @generated
	 * @ordered
	 */
	protected String versionString = VERSION_STRING_EDEFAULT;

	/**
	 * The default value of the '{@link #getVersionType() <em>Version Type</em>}' attribute. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getVersionType()
	 * @generated
	 * @ordered
	 */
	protected static final String VERSION_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVersionType() <em>Version Type</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getVersionType()
	 * @generated
	 * @ordered
	 */
	protected String versionType = VERSION_TYPE_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ComponentSpecBaseImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetDocumentation(Documentation newDocumentation, NotificationChain msgs)
	{
		Documentation oldDocumentation = documentation;
		documentation = newDocumentation;
		if(eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					ICSpecXMLPackage.COMPONENT_SPEC_BASE__DOCUMENTATION, oldDocumentation, newDocumentation);
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
		case ICSpecXMLPackage.COMPONENT_SPEC_BASE__DOCUMENTATION:
			return getDocumentation();
		case ICSpecXMLPackage.COMPONENT_SPEC_BASE__GROUP:
			if(coreType)
				return getGroup();
			return ((FeatureMap.Internal)getGroup()).getWrapper();
		case ICSpecXMLPackage.COMPONENT_SPEC_BASE__DEPENDENCIES:
			return getDependencies();
		case ICSpecXMLPackage.COMPONENT_SPEC_BASE__GENERATORS:
			return getGenerators();
		case ICSpecXMLPackage.COMPONENT_SPEC_BASE__ARTIFACTS:
			return getArtifacts();
		case ICSpecXMLPackage.COMPONENT_SPEC_BASE__GROUPS:
			return getGroups();
		case ICSpecXMLPackage.COMPONENT_SPEC_BASE__ACTIONS:
			return getActions();
		case ICSpecXMLPackage.COMPONENT_SPEC_BASE__CATEGORY:
			return getCategory();
		case ICSpecXMLPackage.COMPONENT_SPEC_BASE__COMPONENT_TYPE:
			return getComponentType();
		case ICSpecXMLPackage.COMPONENT_SPEC_BASE__FILTER:
			return getFilter();
		case ICSpecXMLPackage.COMPONENT_SPEC_BASE__PROJECT_INFO:
			return getProjectInfo();
		case ICSpecXMLPackage.COMPONENT_SPEC_BASE__SHORT_DESC:
			return getShortDesc();
		case ICSpecXMLPackage.COMPONENT_SPEC_BASE__VERSION_STRING:
			return getVersionString();
		case ICSpecXMLPackage.COMPONENT_SPEC_BASE__VERSION_TYPE:
			return getVersionType();
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
		case ICSpecXMLPackage.COMPONENT_SPEC_BASE__DOCUMENTATION:
			return basicSetDocumentation(null, msgs);
		case ICSpecXMLPackage.COMPONENT_SPEC_BASE__GROUP:
			return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
		case ICSpecXMLPackage.COMPONENT_SPEC_BASE__DEPENDENCIES:
			return ((InternalEList<?>)getDependencies()).basicRemove(otherEnd, msgs);
		case ICSpecXMLPackage.COMPONENT_SPEC_BASE__GENERATORS:
			return ((InternalEList<?>)getGenerators()).basicRemove(otherEnd, msgs);
		case ICSpecXMLPackage.COMPONENT_SPEC_BASE__ARTIFACTS:
			return ((InternalEList<?>)getArtifacts()).basicRemove(otherEnd, msgs);
		case ICSpecXMLPackage.COMPONENT_SPEC_BASE__GROUPS:
			return ((InternalEList<?>)getGroups()).basicRemove(otherEnd, msgs);
		case ICSpecXMLPackage.COMPONENT_SPEC_BASE__ACTIONS:
			return ((InternalEList<?>)getActions()).basicRemove(otherEnd, msgs);
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
		case ICSpecXMLPackage.COMPONENT_SPEC_BASE__DOCUMENTATION:
			return documentation != null;
		case ICSpecXMLPackage.COMPONENT_SPEC_BASE__GROUP:
			return group != null && !group.isEmpty();
		case ICSpecXMLPackage.COMPONENT_SPEC_BASE__DEPENDENCIES:
			return !getDependencies().isEmpty();
		case ICSpecXMLPackage.COMPONENT_SPEC_BASE__GENERATORS:
			return !getGenerators().isEmpty();
		case ICSpecXMLPackage.COMPONENT_SPEC_BASE__ARTIFACTS:
			return !getArtifacts().isEmpty();
		case ICSpecXMLPackage.COMPONENT_SPEC_BASE__GROUPS:
			return !getGroups().isEmpty();
		case ICSpecXMLPackage.COMPONENT_SPEC_BASE__ACTIONS:
			return !getActions().isEmpty();
		case ICSpecXMLPackage.COMPONENT_SPEC_BASE__CATEGORY:
			return CATEGORY_EDEFAULT == null
					? category != null
					: !CATEGORY_EDEFAULT.equals(category);
		case ICSpecXMLPackage.COMPONENT_SPEC_BASE__COMPONENT_TYPE:
			return COMPONENT_TYPE_EDEFAULT == null
					? componentType != null
					: !COMPONENT_TYPE_EDEFAULT.equals(componentType);
		case ICSpecXMLPackage.COMPONENT_SPEC_BASE__FILTER:
			return FILTER_EDEFAULT == null
					? filter != null
					: !FILTER_EDEFAULT.equals(filter);
		case ICSpecXMLPackage.COMPONENT_SPEC_BASE__PROJECT_INFO:
			return PROJECT_INFO_EDEFAULT == null
					? projectInfo != null
					: !PROJECT_INFO_EDEFAULT.equals(projectInfo);
		case ICSpecXMLPackage.COMPONENT_SPEC_BASE__SHORT_DESC:
			return SHORT_DESC_EDEFAULT == null
					? shortDesc != null
					: !SHORT_DESC_EDEFAULT.equals(shortDesc);
		case ICSpecXMLPackage.COMPONENT_SPEC_BASE__VERSION_STRING:
			return VERSION_STRING_EDEFAULT == null
					? versionString != null
					: !VERSION_STRING_EDEFAULT.equals(versionString);
		case ICSpecXMLPackage.COMPONENT_SPEC_BASE__VERSION_TYPE:
			return VERSION_TYPE_EDEFAULT == null
					? versionType != null
					: !VERSION_TYPE_EDEFAULT.equals(versionType);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue)
	{
		switch(featureID)
		{
		case ICSpecXMLPackage.COMPONENT_SPEC_BASE__DOCUMENTATION:
			setDocumentation((Documentation)newValue);
			return;
		case ICSpecXMLPackage.COMPONENT_SPEC_BASE__GROUP:
			((FeatureMap.Internal)getGroup()).set(newValue);
			return;
		case ICSpecXMLPackage.COMPONENT_SPEC_BASE__DEPENDENCIES:
			getDependencies().clear();
			getDependencies().addAll((Collection<? extends IDependenciesType>)newValue);
			return;
		case ICSpecXMLPackage.COMPONENT_SPEC_BASE__GENERATORS:
			getGenerators().clear();
			getGenerators().addAll((Collection<? extends IGeneratorsType>)newValue);
			return;
		case ICSpecXMLPackage.COMPONENT_SPEC_BASE__ARTIFACTS:
			getArtifacts().clear();
			getArtifacts().addAll((Collection<? extends IArtifactsType>)newValue);
			return;
		case ICSpecXMLPackage.COMPONENT_SPEC_BASE__GROUPS:
			getGroups().clear();
			getGroups().addAll((Collection<? extends IGroupsType>)newValue);
			return;
		case ICSpecXMLPackage.COMPONENT_SPEC_BASE__ACTIONS:
			getActions().clear();
			getActions().addAll((Collection<? extends IActionsType>)newValue);
			return;
		case ICSpecXMLPackage.COMPONENT_SPEC_BASE__CATEGORY:
			setCategory((String)newValue);
			return;
		case ICSpecXMLPackage.COMPONENT_SPEC_BASE__COMPONENT_TYPE:
			setComponentType((String)newValue);
			return;
		case ICSpecXMLPackage.COMPONENT_SPEC_BASE__FILTER:
			setFilter((Filter)newValue);
			return;
		case ICSpecXMLPackage.COMPONENT_SPEC_BASE__PROJECT_INFO:
			setProjectInfo((URL)newValue);
			return;
		case ICSpecXMLPackage.COMPONENT_SPEC_BASE__SHORT_DESC:
			setShortDesc((String)newValue);
			return;
		case ICSpecXMLPackage.COMPONENT_SPEC_BASE__VERSION_STRING:
			setVersionString((String)newValue);
			return;
		case ICSpecXMLPackage.COMPONENT_SPEC_BASE__VERSION_TYPE:
			setVersionType((String)newValue);
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
		case ICSpecXMLPackage.COMPONENT_SPEC_BASE__DOCUMENTATION:
			setDocumentation((Documentation)null);
			return;
		case ICSpecXMLPackage.COMPONENT_SPEC_BASE__GROUP:
			getGroup().clear();
			return;
		case ICSpecXMLPackage.COMPONENT_SPEC_BASE__DEPENDENCIES:
			getDependencies().clear();
			return;
		case ICSpecXMLPackage.COMPONENT_SPEC_BASE__GENERATORS:
			getGenerators().clear();
			return;
		case ICSpecXMLPackage.COMPONENT_SPEC_BASE__ARTIFACTS:
			getArtifacts().clear();
			return;
		case ICSpecXMLPackage.COMPONENT_SPEC_BASE__GROUPS:
			getGroups().clear();
			return;
		case ICSpecXMLPackage.COMPONENT_SPEC_BASE__ACTIONS:
			getActions().clear();
			return;
		case ICSpecXMLPackage.COMPONENT_SPEC_BASE__CATEGORY:
			setCategory(CATEGORY_EDEFAULT);
			return;
		case ICSpecXMLPackage.COMPONENT_SPEC_BASE__COMPONENT_TYPE:
			setComponentType(COMPONENT_TYPE_EDEFAULT);
			return;
		case ICSpecXMLPackage.COMPONENT_SPEC_BASE__FILTER:
			setFilter(FILTER_EDEFAULT);
			return;
		case ICSpecXMLPackage.COMPONENT_SPEC_BASE__PROJECT_INFO:
			setProjectInfo(PROJECT_INFO_EDEFAULT);
			return;
		case ICSpecXMLPackage.COMPONENT_SPEC_BASE__SHORT_DESC:
			setShortDesc(SHORT_DESC_EDEFAULT);
			return;
		case ICSpecXMLPackage.COMPONENT_SPEC_BASE__VERSION_STRING:
			setVersionString(VERSION_STRING_EDEFAULT);
			return;
		case ICSpecXMLPackage.COMPONENT_SPEC_BASE__VERSION_TYPE:
			setVersionType(VERSION_TYPE_EDEFAULT);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<IActionsType> getActions()
	{
		return getGroup().list(ICSpecXMLPackage.Literals.COMPONENT_SPEC_BASE__ACTIONS);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<IArtifactsType> getArtifacts()
	{
		return getGroup().list(ICSpecXMLPackage.Literals.COMPONENT_SPEC_BASE__ARTIFACTS);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getCategory()
	{
		return category;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getComponentType()
	{
		return componentType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<IDependenciesType> getDependencies()
	{
		return getGroup().list(ICSpecXMLPackage.Literals.COMPONENT_SPEC_BASE__DEPENDENCIES);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Documentation getDocumentation()
	{
		return documentation;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Filter getFilter()
	{
		return filter;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<IGeneratorsType> getGenerators()
	{
		return getGroup().list(ICSpecXMLPackage.Literals.COMPONENT_SPEC_BASE__GENERATORS);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public FeatureMap getGroup()
	{
		if(group == null)
		{
			group = new BasicFeatureMap(this, ICSpecXMLPackage.COMPONENT_SPEC_BASE__GROUP);
		}
		return group;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<IGroupsType> getGroups()
	{
		return getGroup().list(ICSpecXMLPackage.Literals.COMPONENT_SPEC_BASE__GROUPS);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public URL getProjectInfo()
	{
		return projectInfo;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getShortDesc()
	{
		return shortDesc;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Version getVersion()
	{
		return VersionHelper.createVersion(getVersionType(), getVersionString());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getVersionString()
	{
		return versionString;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getVersionType()
	{
		return versionType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setCategory(String newCategory)
	{
		String oldCategory = category;
		category = newCategory;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ICSpecXMLPackage.COMPONENT_SPEC_BASE__CATEGORY,
					oldCategory, category));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setComponentType(String newComponentType)
	{
		String oldComponentType = componentType;
		componentType = newComponentType;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ICSpecXMLPackage.COMPONENT_SPEC_BASE__COMPONENT_TYPE,
					oldComponentType, componentType));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setDocumentation(Documentation newDocumentation)
	{
		if(newDocumentation != documentation)
		{
			NotificationChain msgs = null;
			if(documentation != null)
				msgs = ((InternalEObject)documentation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
						- ICSpecXMLPackage.COMPONENT_SPEC_BASE__DOCUMENTATION, null, msgs);
			if(newDocumentation != null)
				msgs = ((InternalEObject)newDocumentation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
						- ICSpecXMLPackage.COMPONENT_SPEC_BASE__DOCUMENTATION, null, msgs);
			msgs = basicSetDocumentation(newDocumentation, msgs);
			if(msgs != null)
				msgs.dispatch();
		}
		else if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ICSpecXMLPackage.COMPONENT_SPEC_BASE__DOCUMENTATION,
					newDocumentation, newDocumentation));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setFilter(Filter newFilter)
	{
		Filter oldFilter = filter;
		filter = newFilter;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ICSpecXMLPackage.COMPONENT_SPEC_BASE__FILTER,
					oldFilter, filter));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setProjectInfo(URL newProjectInfo)
	{
		URL oldProjectInfo = projectInfo;
		projectInfo = newProjectInfo;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ICSpecXMLPackage.COMPONENT_SPEC_BASE__PROJECT_INFO,
					oldProjectInfo, projectInfo));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setShortDesc(String newShortDesc)
	{
		String oldShortDesc = shortDesc;
		shortDesc = newShortDesc;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ICSpecXMLPackage.COMPONENT_SPEC_BASE__SHORT_DESC,
					oldShortDesc, shortDesc));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setVersion(Version version)
	{
		setVersionType(null);
		setVersionString(version == null
				? null
				: version.toString());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setVersionString(String newVersionString)
	{
		String oldVersionString = versionString;
		versionString = newVersionString;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ICSpecXMLPackage.COMPONENT_SPEC_BASE__VERSION_STRING,
					oldVersionString, versionString));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setVersionType(String newVersionType)
	{
		String oldVersionType = versionType;
		versionType = newVersionType;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ICSpecXMLPackage.COMPONENT_SPEC_BASE__VERSION_TYPE,
					oldVersionType, versionType));
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
		result.append(" (group: ");
		result.append(group);
		result.append(", category: ");
		result.append(category);
		result.append(", componentType: ");
		result.append(componentType);
		result.append(", filter: ");
		result.append(filter);
		result.append(", projectInfo: ");
		result.append(projectInfo);
		result.append(", shortDesc: ");
		result.append(shortDesc);
		result.append(", versionString: ");
		result.append(versionString);
		result.append(", versionType: ");
		result.append(versionType);
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
		return ICSpecXMLPackage.Literals.COMPONENT_SPEC_BASE;
	}

} // ComponentSpecBaseImpl
