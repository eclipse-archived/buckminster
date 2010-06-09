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

import java.util.Collection;

import org.eclipse.buckminster.aggregator.p2.TouchpointData;
import org.eclipse.buckminster.aggregator.p2view.P2viewPackage;
import org.eclipse.buckminster.aggregator.p2view.Touchpoints;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.equinox.internal.provisional.p2.metadata.ITouchpointType;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Touchpoints</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.impl.TouchpointsImpl#getTouchpointType <em>Touchpoint Type</em>}
 * </li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.impl.TouchpointsImpl#getTouchpointDataList <em>Touchpoint Data
 * List</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class TouchpointsImpl extends MinimalEObjectImpl.Container implements Touchpoints
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
	 * The cached value of the '{@link #getTouchpointType() <em>Touchpoint Type</em>}' reference. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getTouchpointType()
	 * @generated
	 * @ordered
	 */
	protected ITouchpointType touchpointType;

	/**
	 * The cached value of the '{@link #getTouchpointDataList() <em>Touchpoint Data List</em>}' reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getTouchpointDataList()
	 * @generated
	 * @ordered
	 */
	protected EList<TouchpointData> touchpointDataList;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected TouchpointsImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ITouchpointType basicGetTouchpointType()
	{
		return touchpointType;
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
		case P2viewPackage.TOUCHPOINTS__TOUCHPOINT_TYPE:
			if(resolve)
				return getTouchpointType();
			return basicGetTouchpointType();
		case P2viewPackage.TOUCHPOINTS__TOUCHPOINT_DATA_LIST:
			return getTouchpointDataList();
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
		case P2viewPackage.TOUCHPOINTS__TOUCHPOINT_TYPE:
			return touchpointType != null;
		case P2viewPackage.TOUCHPOINTS__TOUCHPOINT_DATA_LIST:
			return touchpointDataList != null && !touchpointDataList.isEmpty();
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
		case P2viewPackage.TOUCHPOINTS__TOUCHPOINT_TYPE:
			setTouchpointType((ITouchpointType)newValue);
			return;
		case P2viewPackage.TOUCHPOINTS__TOUCHPOINT_DATA_LIST:
			getTouchpointDataList().clear();
			getTouchpointDataList().addAll((Collection<? extends TouchpointData>)newValue);
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
		case P2viewPackage.TOUCHPOINTS__TOUCHPOINT_TYPE:
			setTouchpointType((ITouchpointType)null);
			return;
		case P2viewPackage.TOUCHPOINTS__TOUCHPOINT_DATA_LIST:
			getTouchpointDataList().clear();
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<TouchpointData> getTouchpointDataList()
	{
		if(touchpointDataList == null)
		{
			touchpointDataList = new EObjectResolvingEList<TouchpointData>(TouchpointData.class, this,
					P2viewPackage.TOUCHPOINTS__TOUCHPOINT_DATA_LIST);
		}
		return touchpointDataList;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ITouchpointType getTouchpointType()
	{
		if(touchpointType != null && ((EObject)touchpointType).eIsProxy())
		{
			InternalEObject oldTouchpointType = (InternalEObject)touchpointType;
			touchpointType = (ITouchpointType)eResolveProxy(oldTouchpointType);
			if(touchpointType != oldTouchpointType)
			{
				if(eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							P2viewPackage.TOUCHPOINTS__TOUCHPOINT_TYPE, oldTouchpointType, touchpointType));
			}
		}
		return touchpointType;
	}

	public void setTouchpointType(ITouchpointType newTouchpointType)
	{
		if(newTouchpointType == ITouchpointType.NONE)
			setTouchpointTypeGen(null);
		else
			setTouchpointTypeGen(newTouchpointType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setTouchpointTypeGen(ITouchpointType newTouchpointType)
	{
		ITouchpointType oldTouchpointType = touchpointType;
		touchpointType = newTouchpointType;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, P2viewPackage.TOUCHPOINTS__TOUCHPOINT_TYPE,
					oldTouchpointType, touchpointType));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass()
	{
		return P2viewPackage.Literals.TOUCHPOINTS;
	}

} // TouchpointsImpl
