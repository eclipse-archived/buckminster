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

import org.eclipse.buckminster.aggregator.p2.InstallableUnit;
import org.eclipse.buckminster.aggregator.p2.InstallableUnitType;
import org.eclipse.buckminster.aggregator.p2view.Details;
import org.eclipse.buckminster.aggregator.p2view.OtherIU;
import org.eclipse.buckminster.aggregator.p2view.P2viewFactory;
import org.eclipse.buckminster.aggregator.p2view.P2viewPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Other IU</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.impl.OtherIUImpl#getDetails <em>Details</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class OtherIUImpl extends IUPresentationImpl implements OtherIU
{
	/**
	 * The cached value of the '{@link #getDetails() <em>Details</em>}' reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getDetails()
	 * @generated
	 * @ordered
	 */
	protected Details details;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected OtherIUImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc --> <<<<<<< .mine
	 * 
	 * @generated NOT
	 */
	protected OtherIUImpl(InstallableUnit iu)
	{
		super(iu);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Details basicGetDetails()
	{
		return details;
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
		case P2viewPackage.OTHER_IU__DETAILS:
			if(resolve)
				return getDetails();
			return basicGetDetails();
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
		case P2viewPackage.OTHER_IU__DETAILS:
			return details != null;
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
		case P2viewPackage.OTHER_IU__DETAILS:
			setDetails((Details)newValue);
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
		case P2viewPackage.OTHER_IU__DETAILS:
			setDetails((Details)null);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Details getDetails()
	{
		if(details != null && details.eIsProxy())
		{
			InternalEObject oldDetails = (InternalEObject)details;
			details = (Details)eResolveProxy(oldDetails);
			if(details != oldDetails)
			{
				if(eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, P2viewPackage.OTHER_IU__DETAILS,
							oldDetails, details));
			}
		}
		return details;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Details getNotNullDetails()
	{
		if(details == null)
			setDetails(P2viewFactory.eINSTANCE.createDetails());

		return getDetails();
	}

	public InstallableUnitType getType()
	{
		return InstallableUnitType.OTHER;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setDetails(Details newDetails)
	{
		Details oldDetails = details;
		details = newDetails;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, P2viewPackage.OTHER_IU__DETAILS, oldDetails, details));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass()
	{
		return P2viewPackage.Literals.OTHER_IU;
	}

} // OtherIUImpl
