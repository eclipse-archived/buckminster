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
 * <!-- begin-user-doc --> A representation of the model object '<em><b>IU Presentation With Details</b></em>'. <!--
 * end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.IUPresentationWithDetails#isDetailsResolved <em>Details Resolved
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.aggregator.p2view.P2viewPackage#getIUPresentationWithDetails()
 * @model abstract="true"
 * @generated
 */
public interface IUPresentationWithDetails extends IUPresentation, IUDetails
{

	/**
	 * Returns the value of the '<em><b>Details Resolved</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Details Resolved</em>' attribute isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Details Resolved</em>' attribute.
	 * @see #setDetailsResolved(boolean)
	 * @see org.eclipse.buckminster.aggregator.p2view.P2viewPackage#getIUPresentationWithDetails_DetailsResolved()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 * @generated
	 */
	boolean isDetailsResolved();

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.aggregator.p2view.IUPresentationWithDetails#isDetailsResolved
	 * <em>Details Resolved</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Details Resolved</em>' attribute.
	 * @see #isDetailsResolved()
	 * @generated
	 */
	void setDetailsResolved(boolean value);
} // IUPresentationWithDetails
