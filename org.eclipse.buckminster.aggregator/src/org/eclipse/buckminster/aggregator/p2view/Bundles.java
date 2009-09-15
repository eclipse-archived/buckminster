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

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Bundles</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.Bundles#getBundles <em>Bundles</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.aggregator.p2view.P2viewPackage#getBundles()
 * @model
 * @generated
 */
public interface Bundles extends EObject
{
	/**
	 * Returns the value of the '<em><b>Bundles</b></em>' reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.aggregator.p2view.Bundle}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Bundles</em>' reference list isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Bundles</em>' reference list.
	 * @see org.eclipse.buckminster.aggregator.p2view.P2viewPackage#getBundles_Bundles()
	 * @model
	 * @generated
	 */
	EList<Bundle> getBundles();

} // Bundles
