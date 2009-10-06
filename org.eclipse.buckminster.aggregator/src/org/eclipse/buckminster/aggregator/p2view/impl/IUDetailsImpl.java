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

import org.eclipse.buckminster.aggregator.p2view.IUDetails;
import org.eclipse.buckminster.aggregator.p2view.P2viewPackage;
import org.eclipse.buckminster.aggregator.p2view.Properties;
import org.eclipse.buckminster.aggregator.p2view.ProvidedCapabilities;
import org.eclipse.buckminster.aggregator.p2view.RequiredCapabilities;
import org.eclipse.buckminster.aggregator.p2view.Touchpoints;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.equinox.internal.provisional.p2.metadata.ICopyright;
import org.eclipse.equinox.internal.provisional.p2.metadata.ILicense;
import org.eclipse.equinox.internal.provisional.p2.metadata.IUpdateDescriptor;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>IU Details</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.impl.IUDetailsImpl#getRequiredCapabilitiesContainer <em>Required
 * Capabilities Container</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.impl.IUDetailsImpl#getProvidedCapabilitiesContainer <em>Provided
 * Capabilities Container</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.impl.IUDetailsImpl#getPropertiesContainer <em>Properties
 * Container</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.impl.IUDetailsImpl#getTouchpointsContainer <em>Touchpoints
 * Container</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.impl.IUDetailsImpl#getUpdateDescriptor <em>Update Descriptor
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.impl.IUDetailsImpl#getCopyright <em>Copyright</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.impl.IUDetailsImpl#getLicense <em>License</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class IUDetailsImpl extends MinimalEObjectImpl.Container implements IUDetails
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
	 * The cached value of the '{@link #getRequiredCapabilitiesContainer() <em>Required Capabilities Container</em>}'
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getRequiredCapabilitiesContainer()
	 * @generated
	 * @ordered
	 */
	protected RequiredCapabilities requiredCapabilitiesContainer;

	/**
	 * The cached value of the '{@link #getProvidedCapabilitiesContainer() <em>Provided Capabilities Container</em>}'
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getProvidedCapabilitiesContainer()
	 * @generated
	 * @ordered
	 */
	protected ProvidedCapabilities providedCapabilitiesContainer;

	/**
	 * The cached value of the '{@link #getPropertiesContainer() <em>Properties Container</em>}' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getPropertiesContainer()
	 * @generated
	 * @ordered
	 */
	protected Properties propertiesContainer;

	/**
	 * The cached value of the '{@link #getTouchpointsContainer() <em>Touchpoints Container</em>}' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getTouchpointsContainer()
	 * @generated
	 * @ordered
	 */
	protected Touchpoints touchpointsContainer;

	/**
	 * The cached value of the '{@link #getUpdateDescriptor() <em>Update Descriptor</em>}' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getUpdateDescriptor()
	 * @generated
	 * @ordered
	 */
	protected IUpdateDescriptor updateDescriptor;

	/**
	 * The cached value of the '{@link #getCopyright() <em>Copyright</em>}' reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getCopyright()
	 * @generated
	 * @ordered
	 */
	protected ICopyright copyright;

	/**
	 * The cached value of the '{@link #getLicense() <em>License</em>}' reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getLicense()
	 * @generated
	 * @ordered
	 */
	protected ILicense license;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected IUDetailsImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ICopyright basicGetCopyright()
	{
		return copyright;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ILicense basicGetLicense()
	{
		return license;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Properties basicGetPropertiesContainer()
	{
		return propertiesContainer;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ProvidedCapabilities basicGetProvidedCapabilitiesContainer()
	{
		return providedCapabilitiesContainer;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public RequiredCapabilities basicGetRequiredCapabilitiesContainer()
	{
		return requiredCapabilitiesContainer;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Touchpoints basicGetTouchpointsContainer()
	{
		return touchpointsContainer;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public IUpdateDescriptor basicGetUpdateDescriptor()
	{
		return updateDescriptor;
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
		case P2viewPackage.IU_DETAILS__REQUIRED_CAPABILITIES_CONTAINER:
			if(resolve)
				return getRequiredCapabilitiesContainer();
			return basicGetRequiredCapabilitiesContainer();
		case P2viewPackage.IU_DETAILS__PROVIDED_CAPABILITIES_CONTAINER:
			if(resolve)
				return getProvidedCapabilitiesContainer();
			return basicGetProvidedCapabilitiesContainer();
		case P2viewPackage.IU_DETAILS__PROPERTIES_CONTAINER:
			if(resolve)
				return getPropertiesContainer();
			return basicGetPropertiesContainer();
		case P2viewPackage.IU_DETAILS__TOUCHPOINTS_CONTAINER:
			if(resolve)
				return getTouchpointsContainer();
			return basicGetTouchpointsContainer();
		case P2viewPackage.IU_DETAILS__UPDATE_DESCRIPTOR:
			if(resolve)
				return getUpdateDescriptor();
			return basicGetUpdateDescriptor();
		case P2viewPackage.IU_DETAILS__COPYRIGHT:
			if(resolve)
				return getCopyright();
			return basicGetCopyright();
		case P2viewPackage.IU_DETAILS__LICENSE:
			if(resolve)
				return getLicense();
			return basicGetLicense();
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
		case P2viewPackage.IU_DETAILS__REQUIRED_CAPABILITIES_CONTAINER:
			return requiredCapabilitiesContainer != null;
		case P2viewPackage.IU_DETAILS__PROVIDED_CAPABILITIES_CONTAINER:
			return providedCapabilitiesContainer != null;
		case P2viewPackage.IU_DETAILS__PROPERTIES_CONTAINER:
			return propertiesContainer != null;
		case P2viewPackage.IU_DETAILS__TOUCHPOINTS_CONTAINER:
			return touchpointsContainer != null;
		case P2viewPackage.IU_DETAILS__UPDATE_DESCRIPTOR:
			return updateDescriptor != null;
		case P2viewPackage.IU_DETAILS__COPYRIGHT:
			return copyright != null;
		case P2viewPackage.IU_DETAILS__LICENSE:
			return license != null;
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
		case P2viewPackage.IU_DETAILS__REQUIRED_CAPABILITIES_CONTAINER:
			setRequiredCapabilitiesContainer((RequiredCapabilities)newValue);
			return;
		case P2viewPackage.IU_DETAILS__PROVIDED_CAPABILITIES_CONTAINER:
			setProvidedCapabilitiesContainer((ProvidedCapabilities)newValue);
			return;
		case P2viewPackage.IU_DETAILS__PROPERTIES_CONTAINER:
			setPropertiesContainer((Properties)newValue);
			return;
		case P2viewPackage.IU_DETAILS__TOUCHPOINTS_CONTAINER:
			setTouchpointsContainer((Touchpoints)newValue);
			return;
		case P2viewPackage.IU_DETAILS__UPDATE_DESCRIPTOR:
			setUpdateDescriptor((IUpdateDescriptor)newValue);
			return;
		case P2viewPackage.IU_DETAILS__COPYRIGHT:
			setCopyright((ICopyright)newValue);
			return;
		case P2viewPackage.IU_DETAILS__LICENSE:
			setLicense((ILicense)newValue);
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
		case P2viewPackage.IU_DETAILS__REQUIRED_CAPABILITIES_CONTAINER:
			setRequiredCapabilitiesContainer((RequiredCapabilities)null);
			return;
		case P2viewPackage.IU_DETAILS__PROVIDED_CAPABILITIES_CONTAINER:
			setProvidedCapabilitiesContainer((ProvidedCapabilities)null);
			return;
		case P2viewPackage.IU_DETAILS__PROPERTIES_CONTAINER:
			setPropertiesContainer((Properties)null);
			return;
		case P2viewPackage.IU_DETAILS__TOUCHPOINTS_CONTAINER:
			setTouchpointsContainer((Touchpoints)null);
			return;
		case P2viewPackage.IU_DETAILS__UPDATE_DESCRIPTOR:
			setUpdateDescriptor((IUpdateDescriptor)null);
			return;
		case P2viewPackage.IU_DETAILS__COPYRIGHT:
			setCopyright((ICopyright)null);
			return;
		case P2viewPackage.IU_DETAILS__LICENSE:
			setLicense((ILicense)null);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ICopyright getCopyright()
	{
		if(copyright != null && ((EObject)copyright).eIsProxy())
		{
			InternalEObject oldCopyright = (InternalEObject)copyright;
			copyright = (ICopyright)eResolveProxy(oldCopyright);
			if(copyright != oldCopyright)
			{
				if(eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, P2viewPackage.IU_DETAILS__COPYRIGHT,
							oldCopyright, copyright));
			}
		}
		return copyright;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ILicense getLicense()
	{
		if(license != null && ((EObject)license).eIsProxy())
		{
			InternalEObject oldLicense = (InternalEObject)license;
			license = (ILicense)eResolveProxy(oldLicense);
			if(license != oldLicense)
			{
				if(eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, P2viewPackage.IU_DETAILS__LICENSE,
							oldLicense, license));
			}
		}
		return license;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Properties getPropertiesContainer()
	{
		if(propertiesContainer != null && propertiesContainer.eIsProxy())
		{
			InternalEObject oldPropertiesContainer = (InternalEObject)propertiesContainer;
			propertiesContainer = (Properties)eResolveProxy(oldPropertiesContainer);
			if(propertiesContainer != oldPropertiesContainer)
			{
				if(eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							P2viewPackage.IU_DETAILS__PROPERTIES_CONTAINER, oldPropertiesContainer, propertiesContainer));
			}
		}
		return propertiesContainer;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ProvidedCapabilities getProvidedCapabilitiesContainer()
	{
		if(providedCapabilitiesContainer != null && providedCapabilitiesContainer.eIsProxy())
		{
			InternalEObject oldProvidedCapabilitiesContainer = (InternalEObject)providedCapabilitiesContainer;
			providedCapabilitiesContainer = (ProvidedCapabilities)eResolveProxy(oldProvidedCapabilitiesContainer);
			if(providedCapabilitiesContainer != oldProvidedCapabilitiesContainer)
			{
				if(eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							P2viewPackage.IU_DETAILS__PROVIDED_CAPABILITIES_CONTAINER,
							oldProvidedCapabilitiesContainer, providedCapabilitiesContainer));
			}
		}
		return providedCapabilitiesContainer;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public RequiredCapabilities getRequiredCapabilitiesContainer()
	{
		if(requiredCapabilitiesContainer != null && requiredCapabilitiesContainer.eIsProxy())
		{
			InternalEObject oldRequiredCapabilitiesContainer = (InternalEObject)requiredCapabilitiesContainer;
			requiredCapabilitiesContainer = (RequiredCapabilities)eResolveProxy(oldRequiredCapabilitiesContainer);
			if(requiredCapabilitiesContainer != oldRequiredCapabilitiesContainer)
			{
				if(eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							P2viewPackage.IU_DETAILS__REQUIRED_CAPABILITIES_CONTAINER,
							oldRequiredCapabilitiesContainer, requiredCapabilitiesContainer));
			}
		}
		return requiredCapabilitiesContainer;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Touchpoints getTouchpointsContainer()
	{
		if(touchpointsContainer != null && touchpointsContainer.eIsProxy())
		{
			InternalEObject oldTouchpointsContainer = (InternalEObject)touchpointsContainer;
			touchpointsContainer = (Touchpoints)eResolveProxy(oldTouchpointsContainer);
			if(touchpointsContainer != oldTouchpointsContainer)
			{
				if(eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							P2viewPackage.IU_DETAILS__TOUCHPOINTS_CONTAINER, oldTouchpointsContainer,
							touchpointsContainer));
			}
		}
		return touchpointsContainer;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public IUpdateDescriptor getUpdateDescriptor()
	{
		if(updateDescriptor != null && ((EObject)updateDescriptor).eIsProxy())
		{
			InternalEObject oldUpdateDescriptor = (InternalEObject)updateDescriptor;
			updateDescriptor = (IUpdateDescriptor)eResolveProxy(oldUpdateDescriptor);
			if(updateDescriptor != oldUpdateDescriptor)
			{
				if(eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							P2viewPackage.IU_DETAILS__UPDATE_DESCRIPTOR, oldUpdateDescriptor, updateDescriptor));
			}
		}
		return updateDescriptor;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setCopyright(ICopyright newCopyright)
	{
		ICopyright oldCopyright = copyright;
		copyright = newCopyright;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, P2viewPackage.IU_DETAILS__COPYRIGHT, oldCopyright,
					copyright));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setLicense(ILicense newLicense)
	{
		ILicense oldLicense = license;
		license = newLicense;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, P2viewPackage.IU_DETAILS__LICENSE, oldLicense,
					license));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setPropertiesContainer(Properties newPropertiesContainer)
	{
		Properties oldPropertiesContainer = propertiesContainer;
		propertiesContainer = newPropertiesContainer;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, P2viewPackage.IU_DETAILS__PROPERTIES_CONTAINER,
					oldPropertiesContainer, propertiesContainer));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setProvidedCapabilitiesContainer(ProvidedCapabilities newProvidedCapabilitiesContainer)
	{
		ProvidedCapabilities oldProvidedCapabilitiesContainer = providedCapabilitiesContainer;
		providedCapabilitiesContainer = newProvidedCapabilitiesContainer;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					P2viewPackage.IU_DETAILS__PROVIDED_CAPABILITIES_CONTAINER, oldProvidedCapabilitiesContainer,
					providedCapabilitiesContainer));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setRequiredCapabilitiesContainer(RequiredCapabilities newRequiredCapabilitiesContainer)
	{
		RequiredCapabilities oldRequiredCapabilitiesContainer = requiredCapabilitiesContainer;
		requiredCapabilitiesContainer = newRequiredCapabilitiesContainer;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					P2viewPackage.IU_DETAILS__REQUIRED_CAPABILITIES_CONTAINER, oldRequiredCapabilitiesContainer,
					requiredCapabilitiesContainer));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setTouchpointsContainer(Touchpoints newTouchpointsContainer)
	{
		Touchpoints oldTouchpointsContainer = touchpointsContainer;
		touchpointsContainer = newTouchpointsContainer;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, P2viewPackage.IU_DETAILS__TOUCHPOINTS_CONTAINER,
					oldTouchpointsContainer, touchpointsContainer));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setUpdateDescriptor(IUpdateDescriptor newUpdateDescriptor)
	{
		IUpdateDescriptor oldUpdateDescriptor = updateDescriptor;
		updateDescriptor = newUpdateDescriptor;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, P2viewPackage.IU_DETAILS__UPDATE_DESCRIPTOR,
					oldUpdateDescriptor, updateDescriptor));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass()
	{
		return P2viewPackage.Literals.IU_DETAILS;
	}

} // IUDetailsImpl
