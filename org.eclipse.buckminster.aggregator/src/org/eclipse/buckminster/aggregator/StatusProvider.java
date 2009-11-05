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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Status Provider</b></em>'. <!-- end-user-doc -->
 * 
 * 
 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getStatusProvider()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface StatusProvider extends EObject
{
	public static final int OK = 0;

	public static final int BROKEN = 1;

	public static final int BROKEN_CHILD = 2;

	public static final int WAITING = 3;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model kind="operation" dataType="org.eclipse.emf.ecore.xml.type.Int"
	 * @generated
	 */
	int getStatus();

} // IStatusProvider
