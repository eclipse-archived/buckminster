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
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Product</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.Product#getFeatureContainer <em>Feature Container</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.Product#getBundleContainer <em>Bundle Container</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.Product#getDetails <em>Details</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.aggregator.p2view.P2viewPackage#getProduct()
 * @model
 * @generated
 */
public interface Product extends IUPresentation
{
	/**
	 * Returns the value of the '<em><b>Bundle Container</b></em>' reference. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Bundle Container</em>' reference isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Bundle Container</em>' reference.
	 * @see #setBundleContainer(Bundles)
	 * @see org.eclipse.buckminster.aggregator.p2view.P2viewPackage#getProduct_BundleContainer()
	 * @model
	 * @generated
	 */
	Bundles getBundleContainer();

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
	 * @see org.eclipse.buckminster.aggregator.p2view.P2viewPackage#getProduct_Details()
	 * @model
	 * @generated
	 */
	Details getDetails();

	/**
	 * Returns the value of the '<em><b>Feature Container</b></em>' reference. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Feature Container</em>' reference isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Feature Container</em>' reference.
	 * @see #setFeatureContainer(Features)
	 * @see org.eclipse.buckminster.aggregator.p2view.P2viewPackage#getProduct_FeatureContainer()
	 * @model
	 * @generated
	 */
	Features getFeatureContainer();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model kind="operation"
	 * @generated
	 */
	Bundles getNotNullBundleContainer();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model kind="operation"
	 * @generated
	 */
	Details getNotNullDetails();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model kind="operation"
	 * @generated
	 */
	Features getNotNullFeatureContainer();

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.p2view.Product#getBundleContainer
	 * <em>Bundle Container</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Bundle Container</em>' reference.
	 * @see #getBundleContainer()
	 * @generated
	 */
	void setBundleContainer(Bundles value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.p2view.Product#getDetails <em>Details</em>}'
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Details</em>' reference.
	 * @see #getDetails()
	 * @generated
	 */
	void setDetails(Details value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.p2view.Product#getFeatureContainer
	 * <em>Feature Container</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Feature Container</em>' reference.
	 * @see #getFeatureContainer()
	 * @generated
	 */
	void setFeatureContainer(Features value);

} // Product
