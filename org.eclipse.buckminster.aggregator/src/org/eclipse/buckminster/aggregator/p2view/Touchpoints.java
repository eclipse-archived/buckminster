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

import org.eclipse.buckminster.aggregator.p2.TouchpointData;
import org.eclipse.emf.common.util.EList;

import org.eclipse.equinox.internal.provisional.p2.metadata.ITouchpointType;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Touchpoints</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.Touchpoints#getTouchpointType <em>Touchpoint Type</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.Touchpoints#getTouchpointDataList <em>Touchpoint Data List</em>}
 * </li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.aggregator.p2view.P2viewPackage#getTouchpoints()
 * @model
 * @generated
 */
public interface Touchpoints
{
	/**
	 * Returns the value of the '<em><b>Touchpoint Data List</b></em>' reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.aggregator.p2.TouchpointData}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Touchpoint Data List</em>' reference list isn't clear, there really should be more of
	 * a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Touchpoint Data List</em>' reference list.
	 * @see org.eclipse.buckminster.aggregator.p2view.P2viewPackage#getTouchpoints_TouchpointDataList()
	 * @model
	 * @generated
	 */
	EList<TouchpointData> getTouchpointDataList();

	/**
	 * Returns the value of the '<em><b>Touchpoint Type</b></em>' reference. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Touchpoint Type</em>' reference isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Touchpoint Type</em>' reference.
	 * @see #setTouchpointType(ITouchpointType)
	 * @see org.eclipse.buckminster.aggregator.p2view.P2viewPackage#getTouchpoints_TouchpointType()
	 * @model type="org.eclipse.buckminster.aggregator.p2.ITouchpointType"
	 * @generated
	 */
	ITouchpointType getTouchpointType();

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.p2view.Touchpoints#getTouchpointType
	 * <em>Touchpoint Type</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Touchpoint Type</em>' reference.
	 * @see #getTouchpointType()
	 * @generated
	 */
	void setTouchpointType(ITouchpointType value);

} // Touchpoints
