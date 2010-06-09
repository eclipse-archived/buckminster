/**
 * Copyright (c) 2006-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.p2view;

import org.eclipse.equinox.internal.provisional.p2.metadata.ICopyright;
import org.eclipse.equinox.internal.provisional.p2.metadata.ILicense;
import org.eclipse.equinox.internal.provisional.p2.metadata.IUpdateDescriptor;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>IU Details</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.IUDetails#getRequiredCapabilitiesContainer <em>Required
 * Capabilities Container</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.IUDetails#getProvidedCapabilitiesContainer <em>Provided
 * Capabilities Container</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.IUDetails#getPropertiesContainer <em>Properties Container</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.IUDetails#getTouchpointsContainer <em>Touchpoints Container
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.IUDetails#getUpdateDescriptor <em>Update Descriptor</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.IUDetails#getCopyright <em>Copyright</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.IUDetails#getLicense <em>License</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.aggregator.p2view.P2viewPackage#getIUDetails()
 * @model
 * @generated
 */
public interface IUDetails
{
	/**
	 * Returns the value of the '<em><b>Copyright</b></em>' reference. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Copyright</em>' reference isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Copyright</em>' reference.
	 * @see #setCopyright(ICopyright)
	 * @see org.eclipse.buckminster.aggregator.p2view.P2viewPackage#getIUDetails_Copyright()
	 * @model type="org.eclipse.buckminster.aggregator.p2.ICopyright" resolveProxies="false"
	 * @generated
	 */
	ICopyright getCopyright();

	/**
	 * Returns the value of the '<em><b>License</b></em>' reference. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>License</em>' reference isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>License</em>' reference.
	 * @see #setLicense(ILicense)
	 * @see org.eclipse.buckminster.aggregator.p2view.P2viewPackage#getIUDetails_License()
	 * @model type="org.eclipse.buckminster.aggregator.p2.ILicense" resolveProxies="false"
	 * @generated
	 */
	ILicense getLicense();

	/**
	 * Returns the value of the '<em><b>Properties Container</b></em>' reference. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Properties Container</em>' reference isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Properties Container</em>' reference.
	 * @see #setPropertiesContainer(Properties)
	 * @see org.eclipse.buckminster.aggregator.p2view.P2viewPackage#getIUDetails_PropertiesContainer()
	 * @model resolveProxies="false"
	 * @generated
	 */
	Properties getPropertiesContainer();

	/**
	 * Returns the value of the '<em><b>Provided Capabilities Container</b></em>' reference. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Provided Capabilities Container</em>' reference isn't clear, there really should be
	 * more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Provided Capabilities Container</em>' reference.
	 * @see #setProvidedCapabilitiesContainer(ProvidedCapabilities)
	 * @see org.eclipse.buckminster.aggregator.p2view.P2viewPackage#getIUDetails_ProvidedCapabilitiesContainer()
	 * @model resolveProxies="false"
	 * @generated
	 */
	ProvidedCapabilities getProvidedCapabilitiesContainer();

	/**
	 * Returns the value of the '<em><b>Required Capabilities Container</b></em>' reference. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Required Capabilities Container</em>' reference isn't clear, there really should be
	 * more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Required Capabilities Container</em>' reference.
	 * @see #setRequiredCapabilitiesContainer(RequiredCapabilities)
	 * @see org.eclipse.buckminster.aggregator.p2view.P2viewPackage#getIUDetails_RequiredCapabilitiesContainer()
	 * @model resolveProxies="false"
	 * @generated
	 */
	RequiredCapabilities getRequiredCapabilitiesContainer();

	/**
	 * Returns the value of the '<em><b>Touchpoints Container</b></em>' reference. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Touchpoints Container</em>' reference isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Touchpoints Container</em>' reference.
	 * @see #setTouchpointsContainer(Touchpoints)
	 * @see org.eclipse.buckminster.aggregator.p2view.P2viewPackage#getIUDetails_TouchpointsContainer()
	 * @model resolveProxies="false"
	 * @generated
	 */
	Touchpoints getTouchpointsContainer();

	/**
	 * Returns the value of the '<em><b>Update Descriptor</b></em>' reference. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Update Descriptor</em>' reference isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Update Descriptor</em>' reference.
	 * @see #setUpdateDescriptor(IUpdateDescriptor)
	 * @see org.eclipse.buckminster.aggregator.p2view.P2viewPackage#getIUDetails_UpdateDescriptor()
	 * @model type="org.eclipse.buckminster.aggregator.p2.IUpdateDescriptor" resolveProxies="false"
	 * @generated
	 */
	IUpdateDescriptor getUpdateDescriptor();

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.p2view.IUDetails#getCopyright
	 * <em>Copyright</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Copyright</em>' reference.
	 * @see #getCopyright()
	 * @generated
	 */
	void setCopyright(ICopyright value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.p2view.IUDetails#getLicense <em>License</em>}'
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>License</em>' reference.
	 * @see #getLicense()
	 * @generated
	 */
	void setLicense(ILicense value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.p2view.IUDetails#getPropertiesContainer
	 * <em>Properties Container</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Properties Container</em>' reference.
	 * @see #getPropertiesContainer()
	 * @generated
	 */
	void setPropertiesContainer(Properties value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.aggregator.p2view.IUDetails#getProvidedCapabilitiesContainer
	 * <em>Provided Capabilities Container</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Provided Capabilities Container</em>' reference.
	 * @see #getProvidedCapabilitiesContainer()
	 * @generated
	 */
	void setProvidedCapabilitiesContainer(ProvidedCapabilities value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.aggregator.p2view.IUDetails#getRequiredCapabilitiesContainer
	 * <em>Required Capabilities Container</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Required Capabilities Container</em>' reference.
	 * @see #getRequiredCapabilitiesContainer()
	 * @generated
	 */
	void setRequiredCapabilitiesContainer(RequiredCapabilities value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.p2view.IUDetails#getTouchpointsContainer
	 * <em>Touchpoints Container</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Touchpoints Container</em>' reference.
	 * @see #getTouchpointsContainer()
	 * @generated
	 */
	void setTouchpointsContainer(Touchpoints value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.p2view.IUDetails#getUpdateDescriptor
	 * <em>Update Descriptor</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Update Descriptor</em>' reference.
	 * @see #getUpdateDescriptor()
	 * @generated
	 */
	void setUpdateDescriptor(IUpdateDescriptor value);

} // IUDetails
