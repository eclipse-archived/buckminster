/**
 * Copyright (c) 2006-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Status Provider</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.StatusProvider#getStatus <em>Status</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getStatusProvider()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface StatusProvider
{
	/**
	 * Returns the value of the '<em><b>Status</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Status</em>' reference.
	 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getStatusProvider_Status()
	 * @model resolveProxies="false" required="true" transient="true" changeable="false" volatile="true"
	 * @generated
	 */
	Status getStatus();

} // IStatusProvider
