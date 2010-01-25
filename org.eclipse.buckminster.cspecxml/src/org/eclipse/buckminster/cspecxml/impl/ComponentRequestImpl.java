/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspecxml.impl;

import java.util.Collection;

import org.eclipse.buckminster.cspecxml.ICSpecXMLPackage;
import org.eclipse.buckminster.cspecxml.IComponentRequest;
import org.eclipse.buckminster.cspecxml.IImport;

import org.eclipse.buckminster.model.common.util.VersionHelper;
import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.equinox.p2.metadata.VersionRange;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Component Request</b></em>'. <!-- end-user-doc
 * -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.ComponentRequestImpl#getImport <em>Import</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.ComponentRequestImpl#getComponentType <em>Component Type</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.ComponentRequestImpl#getFilter <em>Filter</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.ComponentRequestImpl#getName <em>Name</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.ComponentRequestImpl#getVersionDesignator <em>Version Designator
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.ComponentRequestImpl#getVersionType <em>Version Type</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class ComponentRequestImpl extends EObjectImpl implements IComponentRequest
{
	/**
	 * The cached value of the '{@link #getImport() <em>Import</em>}' containment reference list. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getImport()
	 * @generated
	 * @ordered
	 */
	protected EList<IImport> import_;

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
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getVersionDesignator() <em>Version Designator</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getVersionDesignator()
	 * @generated
	 * @ordered
	 */
	protected static final String VERSION_DESIGNATOR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVersionDesignator() <em>Version Designator</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getVersionDesignator()
	 * @generated
	 * @ordered
	 */
	protected String versionDesignator = VERSION_DESIGNATOR_EDEFAULT;

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
	protected ComponentRequestImpl()
	{
		super();
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
		case ICSpecXMLPackage.COMPONENT_REQUEST__IMPORT:
			return getImport();
		case ICSpecXMLPackage.COMPONENT_REQUEST__COMPONENT_TYPE:
			return getComponentType();
		case ICSpecXMLPackage.COMPONENT_REQUEST__FILTER:
			return getFilter();
		case ICSpecXMLPackage.COMPONENT_REQUEST__NAME:
			return getName();
		case ICSpecXMLPackage.COMPONENT_REQUEST__VERSION_DESIGNATOR:
			return getVersionDesignator();
		case ICSpecXMLPackage.COMPONENT_REQUEST__VERSION_TYPE:
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
		case ICSpecXMLPackage.COMPONENT_REQUEST__IMPORT:
			return ((InternalEList<?>)getImport()).basicRemove(otherEnd, msgs);
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
		case ICSpecXMLPackage.COMPONENT_REQUEST__IMPORT:
			return import_ != null && !import_.isEmpty();
		case ICSpecXMLPackage.COMPONENT_REQUEST__COMPONENT_TYPE:
			return COMPONENT_TYPE_EDEFAULT == null
					? componentType != null
					: !COMPONENT_TYPE_EDEFAULT.equals(componentType);
		case ICSpecXMLPackage.COMPONENT_REQUEST__FILTER:
			return FILTER_EDEFAULT == null
					? filter != null
					: !FILTER_EDEFAULT.equals(filter);
		case ICSpecXMLPackage.COMPONENT_REQUEST__NAME:
			return NAME_EDEFAULT == null
					? name != null
					: !NAME_EDEFAULT.equals(name);
		case ICSpecXMLPackage.COMPONENT_REQUEST__VERSION_DESIGNATOR:
			return VERSION_DESIGNATOR_EDEFAULT == null
					? versionDesignator != null
					: !VERSION_DESIGNATOR_EDEFAULT.equals(versionDesignator);
		case ICSpecXMLPackage.COMPONENT_REQUEST__VERSION_TYPE:
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
		case ICSpecXMLPackage.COMPONENT_REQUEST__IMPORT:
			getImport().clear();
			getImport().addAll((Collection<? extends IImport>)newValue);
			return;
		case ICSpecXMLPackage.COMPONENT_REQUEST__COMPONENT_TYPE:
			setComponentType((String)newValue);
			return;
		case ICSpecXMLPackage.COMPONENT_REQUEST__FILTER:
			setFilter((Filter)newValue);
			return;
		case ICSpecXMLPackage.COMPONENT_REQUEST__NAME:
			setName((String)newValue);
			return;
		case ICSpecXMLPackage.COMPONENT_REQUEST__VERSION_DESIGNATOR:
			setVersionDesignator((String)newValue);
			return;
		case ICSpecXMLPackage.COMPONENT_REQUEST__VERSION_TYPE:
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
		case ICSpecXMLPackage.COMPONENT_REQUEST__IMPORT:
			getImport().clear();
			return;
		case ICSpecXMLPackage.COMPONENT_REQUEST__COMPONENT_TYPE:
			setComponentType(COMPONENT_TYPE_EDEFAULT);
			return;
		case ICSpecXMLPackage.COMPONENT_REQUEST__FILTER:
			setFilter(FILTER_EDEFAULT);
			return;
		case ICSpecXMLPackage.COMPONENT_REQUEST__NAME:
			setName(NAME_EDEFAULT);
			return;
		case ICSpecXMLPackage.COMPONENT_REQUEST__VERSION_DESIGNATOR:
			setVersionDesignator(VERSION_DESIGNATOR_EDEFAULT);
			return;
		case ICSpecXMLPackage.COMPONENT_REQUEST__VERSION_TYPE:
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
	public String getComponentType()
	{
		return componentType;
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
	public EList<IImport> getImport()
	{
		if(import_ == null)
		{
			import_ = new EObjectContainmentEList<IImport>(IImport.class, this,
					ICSpecXMLPackage.COMPONENT_REQUEST__IMPORT);
		}
		return import_;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public VersionRange getRange()
	{
		return VersionHelper.createRange(getVersionType(), getVersionDesignator());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getVersionDesignator()
	{
		return versionDesignator;
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
	public void setComponentType(String newComponentType)
	{
		String oldComponentType = componentType;
		componentType = newComponentType;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ICSpecXMLPackage.COMPONENT_REQUEST__COMPONENT_TYPE,
					oldComponentType, componentType));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ICSpecXMLPackage.COMPONENT_REQUEST__FILTER,
					oldFilter, filter));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setName(String newName)
	{
		String oldName = name;
		name = newName;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ICSpecXMLPackage.COMPONENT_REQUEST__NAME, oldName,
					name));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setRange(VersionRange range)
	{
		setVersionType(null);
		setVersionDesignator(range == null
				? null
				: range.toString());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setVersionDesignator(String newVersionDesignator)
	{
		String oldVersionDesignator = versionDesignator;
		versionDesignator = newVersionDesignator;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ICSpecXMLPackage.COMPONENT_REQUEST__VERSION_DESIGNATOR, oldVersionDesignator, versionDesignator));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ICSpecXMLPackage.COMPONENT_REQUEST__VERSION_TYPE,
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
		result.append(" (componentType: ");
		result.append(componentType);
		result.append(", filter: ");
		result.append(filter);
		result.append(", name: ");
		result.append(name);
		result.append(", versionDesignator: ");
		result.append(versionDesignator);
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
		return ICSpecXMLPackage.Literals.COMPONENT_REQUEST;
	}

} // ComponentRequestImpl
