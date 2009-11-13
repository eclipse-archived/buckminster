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

import org.eclipse.buckminster.aggregator.LabelProvider;

import org.eclipse.buckminster.aggregator.p2.RequiredCapability;

import org.eclipse.equinox.internal.provisional.p2.metadata.IRequiredCapability;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Required Capability Wrapper</b></em>'. <!--
 * end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.RequiredCapabilityWrapper#getGenuine <em>Genuine</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.aggregator.p2view.P2viewPackage#getRequiredCapabilityWrapper()
 * @model superTypes=
 *        "org.eclipse.buckminster.aggregator.p2.IRequiredCapability org.eclipse.buckminster.aggregator.LabelProvider"
 * @generated
 */
public interface RequiredCapabilityWrapper extends IRequiredCapability, LabelProvider
{
	/**
	 * Returns the value of the '<em><b>Genuine</b></em>' reference. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Genuine</em>' reference isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Genuine</em>' reference.
	 * @see org.eclipse.buckminster.aggregator.p2view.P2viewPackage#getRequiredCapabilityWrapper_Genuine()
	 * @model resolveProxies="false" required="true" changeable="false"
	 * @generated
	 */
	RequiredCapability getGenuine();

} // RequiredCapabilityWrapper
