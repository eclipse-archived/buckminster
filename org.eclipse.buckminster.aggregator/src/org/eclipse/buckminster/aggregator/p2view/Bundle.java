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

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Bundle</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.Bundle#getDetails <em>Details</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.aggregator.p2view.P2viewPackage#getBundle()
 * @model
 * @generated
 */
public interface Bundle extends IUPresentation
{
	/**
	 * Returns the value of the '<em><b>Details</b></em>' reference. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Details</em>' reference isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Details</em>' reference.
	 * @see #setDetails(Details)
	 * @see org.eclipse.buckminster.aggregator.p2view.P2viewPackage#getBundle_Details()
	 * @model
	 * @generated
	 */
	Details getDetails();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model kind="operation"
	 * @generated
	 */
	Details getNotNullDetails();

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.p2view.Bundle#getDetails <em>Details</em>}'
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Details</em>' reference.
	 * @see #getDetails()
	 * @generated
	 */
	void setDetails(Details value);

} // Bundle
