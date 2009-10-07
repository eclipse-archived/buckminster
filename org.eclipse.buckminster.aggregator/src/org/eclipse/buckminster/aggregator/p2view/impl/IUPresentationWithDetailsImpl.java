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
import org.eclipse.buckminster.aggregator.p2view.IUDetails;
import org.eclipse.buckminster.aggregator.p2view.IUPresentationWithDetails;
import org.eclipse.buckminster.aggregator.p2view.P2viewFactory;
import org.eclipse.buckminster.aggregator.p2view.P2viewPackage;
import org.eclipse.buckminster.aggregator.p2view.Properties;
import org.eclipse.buckminster.aggregator.p2view.ProvidedCapabilities;
import org.eclipse.buckminster.aggregator.p2view.RequiredCapabilities;
import org.eclipse.buckminster.aggregator.p2view.Touchpoints;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.equinox.internal.provisional.p2.metadata.ICopyright;
import org.eclipse.equinox.internal.provisional.p2.metadata.ILicense;
import org.eclipse.equinox.internal.provisional.p2.metadata.IUpdateDescriptor;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>IU Presentation With Details</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.buckminster.aggregator.p2view.impl.IUPresentationWithDetailsImpl#getRequiredCapabilitiesContainer
 * <em>Required Capabilities Container</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.aggregator.p2view.impl.IUPresentationWithDetailsImpl#getProvidedCapabilitiesContainer
 * <em>Provided Capabilities Container</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.impl.IUPresentationWithDetailsImpl#getPropertiesContainer <em>
 * Properties Container</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.impl.IUPresentationWithDetailsImpl#getTouchpointsContainer <em>
 * Touchpoints Container</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.impl.IUPresentationWithDetailsImpl#getUpdateDescriptor <em>
 * Update Descriptor</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.impl.IUPresentationWithDetailsImpl#getCopyright <em>Copyright
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.impl.IUPresentationWithDetailsImpl#getLicense <em>License</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.impl.IUPresentationWithDetailsImpl#isDetailsResolved <em>Details
 * Resolved</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public abstract class IUPresentationWithDetailsImpl extends IUPresentationImpl implements IUPresentationWithDetails
{
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
	 * The default value of the '{@link #isDetailsResolved() <em>Details Resolved</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #isDetailsResolved()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DETAILS_RESOLVED_EDEFAULT = false;

	/**
	 * The flag representing the value of the '{@link #isDetailsResolved() <em>Details Resolved</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isDetailsResolved()
	 * @generated
	 * @ordered
	 */
	protected static final int DETAILS_RESOLVED_EFLAG = 1 << 0;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected IUPresentationWithDetailsImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	protected IUPresentationWithDetailsImpl(InstallableUnit iu)
	{
		super(iu);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass)
	{
		if(baseClass == IUDetails.class)
		{
			switch(derivedFeatureID)
			{
			case P2viewPackage.IU_PRESENTATION_WITH_DETAILS__REQUIRED_CAPABILITIES_CONTAINER:
				return P2viewPackage.IU_DETAILS__REQUIRED_CAPABILITIES_CONTAINER;
			case P2viewPackage.IU_PRESENTATION_WITH_DETAILS__PROVIDED_CAPABILITIES_CONTAINER:
				return P2viewPackage.IU_DETAILS__PROVIDED_CAPABILITIES_CONTAINER;
			case P2viewPackage.IU_PRESENTATION_WITH_DETAILS__PROPERTIES_CONTAINER:
				return P2viewPackage.IU_DETAILS__PROPERTIES_CONTAINER;
			case P2viewPackage.IU_PRESENTATION_WITH_DETAILS__TOUCHPOINTS_CONTAINER:
				return P2viewPackage.IU_DETAILS__TOUCHPOINTS_CONTAINER;
			case P2viewPackage.IU_PRESENTATION_WITH_DETAILS__UPDATE_DESCRIPTOR:
				return P2viewPackage.IU_DETAILS__UPDATE_DESCRIPTOR;
			case P2viewPackage.IU_PRESENTATION_WITH_DETAILS__COPYRIGHT:
				return P2viewPackage.IU_DETAILS__COPYRIGHT;
			case P2viewPackage.IU_PRESENTATION_WITH_DETAILS__LICENSE:
				return P2viewPackage.IU_DETAILS__LICENSE;
			default:
				return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass)
	{
		if(baseClass == IUDetails.class)
		{
			switch(baseFeatureID)
			{
			case P2viewPackage.IU_DETAILS__REQUIRED_CAPABILITIES_CONTAINER:
				return P2viewPackage.IU_PRESENTATION_WITH_DETAILS__REQUIRED_CAPABILITIES_CONTAINER;
			case P2viewPackage.IU_DETAILS__PROVIDED_CAPABILITIES_CONTAINER:
				return P2viewPackage.IU_PRESENTATION_WITH_DETAILS__PROVIDED_CAPABILITIES_CONTAINER;
			case P2viewPackage.IU_DETAILS__PROPERTIES_CONTAINER:
				return P2viewPackage.IU_PRESENTATION_WITH_DETAILS__PROPERTIES_CONTAINER;
			case P2viewPackage.IU_DETAILS__TOUCHPOINTS_CONTAINER:
				return P2viewPackage.IU_PRESENTATION_WITH_DETAILS__TOUCHPOINTS_CONTAINER;
			case P2viewPackage.IU_DETAILS__UPDATE_DESCRIPTOR:
				return P2viewPackage.IU_PRESENTATION_WITH_DETAILS__UPDATE_DESCRIPTOR;
			case P2viewPackage.IU_DETAILS__COPYRIGHT:
				return P2viewPackage.IU_PRESENTATION_WITH_DETAILS__COPYRIGHT;
			case P2viewPackage.IU_DETAILS__LICENSE:
				return P2viewPackage.IU_PRESENTATION_WITH_DETAILS__LICENSE;
			default:
				return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
		case P2viewPackage.IU_PRESENTATION_WITH_DETAILS__REQUIRED_CAPABILITIES_CONTAINER:
			return getRequiredCapabilitiesContainer();
		case P2viewPackage.IU_PRESENTATION_WITH_DETAILS__PROVIDED_CAPABILITIES_CONTAINER:
			return getProvidedCapabilitiesContainer();
		case P2viewPackage.IU_PRESENTATION_WITH_DETAILS__PROPERTIES_CONTAINER:
			return getPropertiesContainer();
		case P2viewPackage.IU_PRESENTATION_WITH_DETAILS__TOUCHPOINTS_CONTAINER:
			return getTouchpointsContainer();
		case P2viewPackage.IU_PRESENTATION_WITH_DETAILS__UPDATE_DESCRIPTOR:
			return getUpdateDescriptor();
		case P2viewPackage.IU_PRESENTATION_WITH_DETAILS__COPYRIGHT:
			return getCopyright();
		case P2viewPackage.IU_PRESENTATION_WITH_DETAILS__LICENSE:
			return getLicense();
		case P2viewPackage.IU_PRESENTATION_WITH_DETAILS__DETAILS_RESOLVED:
			return isDetailsResolved();
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
		case P2viewPackage.IU_PRESENTATION_WITH_DETAILS__REQUIRED_CAPABILITIES_CONTAINER:
			return requiredCapabilitiesContainer != null;
		case P2viewPackage.IU_PRESENTATION_WITH_DETAILS__PROVIDED_CAPABILITIES_CONTAINER:
			return providedCapabilitiesContainer != null;
		case P2viewPackage.IU_PRESENTATION_WITH_DETAILS__PROPERTIES_CONTAINER:
			return propertiesContainer != null;
		case P2viewPackage.IU_PRESENTATION_WITH_DETAILS__TOUCHPOINTS_CONTAINER:
			return touchpointsContainer != null;
		case P2viewPackage.IU_PRESENTATION_WITH_DETAILS__UPDATE_DESCRIPTOR:
			return updateDescriptor != null;
		case P2viewPackage.IU_PRESENTATION_WITH_DETAILS__COPYRIGHT:
			return copyright != null;
		case P2viewPackage.IU_PRESENTATION_WITH_DETAILS__LICENSE:
			return license != null;
		case P2viewPackage.IU_PRESENTATION_WITH_DETAILS__DETAILS_RESOLVED:
			return ((eFlags & DETAILS_RESOLVED_EFLAG) != 0) != DETAILS_RESOLVED_EDEFAULT;
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
		case P2viewPackage.IU_PRESENTATION_WITH_DETAILS__REQUIRED_CAPABILITIES_CONTAINER:
			setRequiredCapabilitiesContainer((RequiredCapabilities)newValue);
			return;
		case P2viewPackage.IU_PRESENTATION_WITH_DETAILS__PROVIDED_CAPABILITIES_CONTAINER:
			setProvidedCapabilitiesContainer((ProvidedCapabilities)newValue);
			return;
		case P2viewPackage.IU_PRESENTATION_WITH_DETAILS__PROPERTIES_CONTAINER:
			setPropertiesContainer((Properties)newValue);
			return;
		case P2viewPackage.IU_PRESENTATION_WITH_DETAILS__TOUCHPOINTS_CONTAINER:
			setTouchpointsContainer((Touchpoints)newValue);
			return;
		case P2viewPackage.IU_PRESENTATION_WITH_DETAILS__UPDATE_DESCRIPTOR:
			setUpdateDescriptor((IUpdateDescriptor)newValue);
			return;
		case P2viewPackage.IU_PRESENTATION_WITH_DETAILS__COPYRIGHT:
			setCopyright((ICopyright)newValue);
			return;
		case P2viewPackage.IU_PRESENTATION_WITH_DETAILS__LICENSE:
			setLicense((ILicense)newValue);
			return;
		case P2viewPackage.IU_PRESENTATION_WITH_DETAILS__DETAILS_RESOLVED:
			setDetailsResolved((Boolean)newValue);
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
		case P2viewPackage.IU_PRESENTATION_WITH_DETAILS__REQUIRED_CAPABILITIES_CONTAINER:
			setRequiredCapabilitiesContainer((RequiredCapabilities)null);
			return;
		case P2viewPackage.IU_PRESENTATION_WITH_DETAILS__PROVIDED_CAPABILITIES_CONTAINER:
			setProvidedCapabilitiesContainer((ProvidedCapabilities)null);
			return;
		case P2viewPackage.IU_PRESENTATION_WITH_DETAILS__PROPERTIES_CONTAINER:
			setPropertiesContainer((Properties)null);
			return;
		case P2viewPackage.IU_PRESENTATION_WITH_DETAILS__TOUCHPOINTS_CONTAINER:
			setTouchpointsContainer((Touchpoints)null);
			return;
		case P2viewPackage.IU_PRESENTATION_WITH_DETAILS__UPDATE_DESCRIPTOR:
			setUpdateDescriptor((IUpdateDescriptor)null);
			return;
		case P2viewPackage.IU_PRESENTATION_WITH_DETAILS__COPYRIGHT:
			setCopyright((ICopyright)null);
			return;
		case P2viewPackage.IU_PRESENTATION_WITH_DETAILS__LICENSE:
			setLicense((ILicense)null);
			return;
		case P2viewPackage.IU_PRESENTATION_WITH_DETAILS__DETAILS_RESOLVED:
			setDetailsResolved(DETAILS_RESOLVED_EDEFAULT);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public ICopyright getCopyright()
	{
		if(!isDetailsResolved())
			resolveDetails();

		return copyright;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public ILicense getLicense()
	{
		if(!isDetailsResolved())
			resolveDetails();

		return license;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Properties getPropertiesContainer()
	{
		if(!isDetailsResolved())
			resolveDetails();

		return propertiesContainer;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public ProvidedCapabilities getProvidedCapabilitiesContainer()
	{
		if(!isDetailsResolved())
			resolveDetails();

		return providedCapabilitiesContainer;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public RequiredCapabilities getRequiredCapabilitiesContainer()
	{
		if(!isDetailsResolved())
			resolveDetails();

		return requiredCapabilitiesContainer;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Touchpoints getTouchpointsContainer()
	{
		if(!isDetailsResolved())
			resolveDetails();

		return touchpointsContainer;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public IUpdateDescriptor getUpdateDescriptor()
	{
		if(!isDetailsResolved())
			resolveDetails();

		return updateDescriptor;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isDetailsResolved()
	{
		return (eFlags & DETAILS_RESOLVED_EFLAG) != 0;
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
			eNotify(new ENotificationImpl(this, Notification.SET,
					P2viewPackage.IU_PRESENTATION_WITH_DETAILS__COPYRIGHT, oldCopyright, copyright));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setDetailsResolved(boolean newDetailsResolved)
	{
		boolean oldDetailsResolved = (eFlags & DETAILS_RESOLVED_EFLAG) != 0;
		if(newDetailsResolved)
			eFlags |= DETAILS_RESOLVED_EFLAG;
		else
			eFlags &= ~DETAILS_RESOLVED_EFLAG;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					P2viewPackage.IU_PRESENTATION_WITH_DETAILS__DETAILS_RESOLVED, oldDetailsResolved,
					newDetailsResolved));
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
			eNotify(new ENotificationImpl(this, Notification.SET, P2viewPackage.IU_PRESENTATION_WITH_DETAILS__LICENSE,
					oldLicense, license));
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
			eNotify(new ENotificationImpl(this, Notification.SET,
					P2viewPackage.IU_PRESENTATION_WITH_DETAILS__PROPERTIES_CONTAINER, oldPropertiesContainer,
					propertiesContainer));
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
					P2viewPackage.IU_PRESENTATION_WITH_DETAILS__PROVIDED_CAPABILITIES_CONTAINER,
					oldProvidedCapabilitiesContainer, providedCapabilitiesContainer));
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
					P2viewPackage.IU_PRESENTATION_WITH_DETAILS__REQUIRED_CAPABILITIES_CONTAINER,
					oldRequiredCapabilitiesContainer, requiredCapabilitiesContainer));
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
			eNotify(new ENotificationImpl(this, Notification.SET,
					P2viewPackage.IU_PRESENTATION_WITH_DETAILS__TOUCHPOINTS_CONTAINER, oldTouchpointsContainer,
					touchpointsContainer));
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
			eNotify(new ENotificationImpl(this, Notification.SET,
					P2viewPackage.IU_PRESENTATION_WITH_DETAILS__UPDATE_DESCRIPTOR, oldUpdateDescriptor,
					updateDescriptor));
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
		result.append(" (detailsResolved: ");
		result.append((eFlags & DETAILS_RESOLVED_EFLAG) != 0);
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
		return P2viewPackage.Literals.IU_PRESENTATION_WITH_DETAILS;
	}

	private void resolveDetails()
	{
		IUDetails iuDetails = P2viewFactory.eINSTANCE.createIUDetails(getInstallableUnit());

		setRequiredCapabilitiesContainer(iuDetails.getRequiredCapabilitiesContainer());
		setProvidedCapabilitiesContainer(iuDetails.getProvidedCapabilitiesContainer());
		setPropertiesContainer(iuDetails.getPropertiesContainer());
		setTouchpointsContainer(iuDetails.getTouchpointsContainer());
		setUpdateDescriptor(iuDetails.getUpdateDescriptor());
		setCopyright(iuDetails.getCopyright());
		setLicense(iuDetails.getLicense());

		setDetailsResolved(true);
	}

} // IUPresentationWithDetailsImpl
