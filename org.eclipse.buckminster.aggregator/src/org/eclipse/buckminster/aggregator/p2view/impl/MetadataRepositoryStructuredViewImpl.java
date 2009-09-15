/**
 * Copyright (c) 2006-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.p2view.impl;

import org.eclipse.buckminster.aggregator.p2.MetadataRepository;
import org.eclipse.buckminster.aggregator.p2view.InstallableUnits;
import org.eclipse.buckminster.aggregator.p2view.MetadataRepositoryStructuredView;
import org.eclipse.buckminster.aggregator.p2view.P2viewPackage;

import org.eclipse.buckminster.aggregator.p2view.Properties;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Metadata Repository Structured View</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.impl.MetadataRepositoryStructuredViewImpl#getName <em>Name</em>}
 * </li>
 * <li>
 * {@link org.eclipse.buckminster.aggregator.p2view.impl.MetadataRepositoryStructuredViewImpl#getInstallableUnitList
 * <em>Installable Unit List</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.impl.MetadataRepositoryStructuredViewImpl#getProperties <em>
 * Properties</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.impl.MetadataRepositoryStructuredViewImpl#getMdr <em>Mdr</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class MetadataRepositoryStructuredViewImpl extends MinimalEObjectImpl.Container implements
		MetadataRepositoryStructuredView
{
	/**
	 * A set of bit flags representing the values of boolean attributes and whether unsettable features have been set.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	protected int eFlags = 0;

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
	 * The cached value of the '{@link #getInstallableUnitList() <em>Installable Unit List</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getInstallableUnitList()
	 * @generated
	 * @ordered
	 */
	protected InstallableUnits installableUnitList;

	/**
	 * The cached value of the '{@link #getProperties() <em>Properties</em>}' containment reference. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getProperties()
	 * @generated
	 * @ordered
	 */
	protected Properties properties;

	/**
	 * The cached value of the '{@link #getMdr() <em>Mdr</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getMdr()
	 * @generated
	 * @ordered
	 */
	protected MetadataRepository mdr;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected MetadataRepositoryStructuredViewImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc --> <<<<<<< .mine
	 * 
	 * @generated NOT
	 */
	protected MetadataRepositoryStructuredViewImpl(MetadataRepository mdr)
	{
		super();
		this.mdr = mdr;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetInstallableUnitList(InstallableUnits newInstallableUnitList, NotificationChain msgs)
	{
		InstallableUnits oldInstallableUnitList = installableUnitList;
		installableUnitList = newInstallableUnitList;
		if(eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					P2viewPackage.METADATA_REPOSITORY_STRUCTURED_VIEW__INSTALLABLE_UNIT_LIST, oldInstallableUnitList,
					newInstallableUnitList);
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
	public NotificationChain basicSetProperties(Properties newProperties, NotificationChain msgs)
	{
		Properties oldProperties = properties;
		properties = newProperties;
		if(eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					P2viewPackage.METADATA_REPOSITORY_STRUCTURED_VIEW__PROPERTIES, oldProperties, newProperties);
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
		case P2viewPackage.METADATA_REPOSITORY_STRUCTURED_VIEW__NAME:
			return getName();
		case P2viewPackage.METADATA_REPOSITORY_STRUCTURED_VIEW__INSTALLABLE_UNIT_LIST:
			return getInstallableUnitList();
		case P2viewPackage.METADATA_REPOSITORY_STRUCTURED_VIEW__PROPERTIES:
			return getProperties();
		case P2viewPackage.METADATA_REPOSITORY_STRUCTURED_VIEW__MDR:
			return getMdr();
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
		case P2viewPackage.METADATA_REPOSITORY_STRUCTURED_VIEW__INSTALLABLE_UNIT_LIST:
			return basicSetInstallableUnitList(null, msgs);
		case P2viewPackage.METADATA_REPOSITORY_STRUCTURED_VIEW__PROPERTIES:
			return basicSetProperties(null, msgs);
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
		case P2viewPackage.METADATA_REPOSITORY_STRUCTURED_VIEW__NAME:
			return NAME_EDEFAULT == null
					? name != null
					: !NAME_EDEFAULT.equals(name);
		case P2viewPackage.METADATA_REPOSITORY_STRUCTURED_VIEW__INSTALLABLE_UNIT_LIST:
			return installableUnitList != null;
		case P2viewPackage.METADATA_REPOSITORY_STRUCTURED_VIEW__PROPERTIES:
			return properties != null;
		case P2viewPackage.METADATA_REPOSITORY_STRUCTURED_VIEW__MDR:
			return mdr != null;
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
		case P2viewPackage.METADATA_REPOSITORY_STRUCTURED_VIEW__NAME:
			setName((String)newValue);
			return;
		case P2viewPackage.METADATA_REPOSITORY_STRUCTURED_VIEW__INSTALLABLE_UNIT_LIST:
			setInstallableUnitList((InstallableUnits)newValue);
			return;
		case P2viewPackage.METADATA_REPOSITORY_STRUCTURED_VIEW__PROPERTIES:
			setProperties((Properties)newValue);
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
		case P2viewPackage.METADATA_REPOSITORY_STRUCTURED_VIEW__NAME:
			setName(NAME_EDEFAULT);
			return;
		case P2viewPackage.METADATA_REPOSITORY_STRUCTURED_VIEW__INSTALLABLE_UNIT_LIST:
			setInstallableUnitList((InstallableUnits)null);
			return;
		case P2viewPackage.METADATA_REPOSITORY_STRUCTURED_VIEW__PROPERTIES:
			setProperties((Properties)null);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public InstallableUnits getInstallableUnitList()
	{
		return installableUnitList;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc --> <<<<<<< .mine
	 * 
	 * @generated
	 */
	public MetadataRepository getMdr()
	{
		return mdr;
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
	public Properties getProperties()
	{
		return properties;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setInstallableUnitList(InstallableUnits newInstallableUnitList)
	{
		if(newInstallableUnitList != installableUnitList)
		{
			NotificationChain msgs = null;
			if(installableUnitList != null)
				msgs = ((InternalEObject)installableUnitList).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
						- P2viewPackage.METADATA_REPOSITORY_STRUCTURED_VIEW__INSTALLABLE_UNIT_LIST, null, msgs);
			if(newInstallableUnitList != null)
				msgs = ((InternalEObject)newInstallableUnitList).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
						- P2viewPackage.METADATA_REPOSITORY_STRUCTURED_VIEW__INSTALLABLE_UNIT_LIST, null, msgs);
			msgs = basicSetInstallableUnitList(newInstallableUnitList, msgs);
			if(msgs != null)
				msgs.dispatch();
		}
		else if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					P2viewPackage.METADATA_REPOSITORY_STRUCTURED_VIEW__INSTALLABLE_UNIT_LIST, newInstallableUnitList,
					newInstallableUnitList));
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
			eNotify(new ENotificationImpl(this, Notification.SET,
					P2viewPackage.METADATA_REPOSITORY_STRUCTURED_VIEW__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setProperties(Properties newProperties)
	{
		if(newProperties != properties)
		{
			NotificationChain msgs = null;
			if(properties != null)
				msgs = ((InternalEObject)properties).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
						- P2viewPackage.METADATA_REPOSITORY_STRUCTURED_VIEW__PROPERTIES, null, msgs);
			if(newProperties != null)
				msgs = ((InternalEObject)newProperties).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
						- P2viewPackage.METADATA_REPOSITORY_STRUCTURED_VIEW__PROPERTIES, null, msgs);
			msgs = basicSetProperties(newProperties, msgs);
			if(msgs != null)
				msgs.dispatch();
		}
		else if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					P2viewPackage.METADATA_REPOSITORY_STRUCTURED_VIEW__PROPERTIES, newProperties, newProperties));
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
		result.append(" (name: ");
		result.append(name);
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
		return P2viewPackage.Literals.METADATA_REPOSITORY_STRUCTURED_VIEW;
	}

} // MetadataRepositoryStructuredViewImpl
