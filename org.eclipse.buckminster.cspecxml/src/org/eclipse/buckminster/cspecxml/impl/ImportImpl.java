/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspecxml.impl;

import org.eclipse.buckminster.cspecxml.ICSpecXMLPackage;
import org.eclipse.buckminster.cspecxml.IImport;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Import</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.ImportImpl#getAttribute <em>Attribute</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.ImportImpl#getFilter <em>Filter</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.ImportImpl#getVersionDesignator <em>Version Designator</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class ImportImpl extends EObjectImpl implements IImport
{
	/**
	 * The default value of the '{@link #getAttribute() <em>Attribute</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getAttribute()
	 * @generated
	 * @ordered
	 */
	protected static final String ATTRIBUTE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAttribute() <em>Attribute</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getAttribute()
	 * @generated
	 * @ordered
	 */
	protected String attribute = ATTRIBUTE_EDEFAULT;

	/**
	 * The default value of the '{@link #getFilter() <em>Filter</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getFilter()
	 * @generated
	 * @ordered
	 */
	protected static final String FILTER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFilter() <em>Filter</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getFilter()
	 * @generated
	 * @ordered
	 */
	protected String filter = FILTER_EDEFAULT;

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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ImportImpl()
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
		case ICSpecXMLPackage.IMPORT__ATTRIBUTE:
			return getAttribute();
		case ICSpecXMLPackage.IMPORT__FILTER:
			return getFilter();
		case ICSpecXMLPackage.IMPORT__VERSION_DESIGNATOR:
			return getVersionDesignator();
		}
		return super.eGet(featureID, resolve, coreType);
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
		case ICSpecXMLPackage.IMPORT__ATTRIBUTE:
			return ATTRIBUTE_EDEFAULT == null
					? attribute != null
					: !ATTRIBUTE_EDEFAULT.equals(attribute);
		case ICSpecXMLPackage.IMPORT__FILTER:
			return FILTER_EDEFAULT == null
					? filter != null
					: !FILTER_EDEFAULT.equals(filter);
		case ICSpecXMLPackage.IMPORT__VERSION_DESIGNATOR:
			return VERSION_DESIGNATOR_EDEFAULT == null
					? versionDesignator != null
					: !VERSION_DESIGNATOR_EDEFAULT.equals(versionDesignator);
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
		case ICSpecXMLPackage.IMPORT__ATTRIBUTE:
			setAttribute((String)newValue);
			return;
		case ICSpecXMLPackage.IMPORT__FILTER:
			setFilter((String)newValue);
			return;
		case ICSpecXMLPackage.IMPORT__VERSION_DESIGNATOR:
			setVersionDesignator((String)newValue);
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
		case ICSpecXMLPackage.IMPORT__ATTRIBUTE:
			setAttribute(ATTRIBUTE_EDEFAULT);
			return;
		case ICSpecXMLPackage.IMPORT__FILTER:
			setFilter(FILTER_EDEFAULT);
			return;
		case ICSpecXMLPackage.IMPORT__VERSION_DESIGNATOR:
			setVersionDesignator(VERSION_DESIGNATOR_EDEFAULT);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getAttribute()
	{
		return attribute;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getFilter()
	{
		return filter;
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
	public void setAttribute(String newAttribute)
	{
		String oldAttribute = attribute;
		attribute = newAttribute;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ICSpecXMLPackage.IMPORT__ATTRIBUTE, oldAttribute,
					attribute));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setFilter(String newFilter)
	{
		String oldFilter = filter;
		filter = newFilter;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ICSpecXMLPackage.IMPORT__FILTER, oldFilter, filter));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ICSpecXMLPackage.IMPORT__VERSION_DESIGNATOR,
					oldVersionDesignator, versionDesignator));
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
		result.append(" (attribute: ");
		result.append(attribute);
		result.append(", filter: ");
		result.append(filter);
		result.append(", versionDesignator: ");
		result.append(versionDesignator);
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
		return ICSpecXMLPackage.Literals.IMPORT;
	}

} // ImportImpl
