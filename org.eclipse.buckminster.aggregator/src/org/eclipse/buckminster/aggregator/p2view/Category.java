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
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Category</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.Category#getCategoryContainer <em>Category Container</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.Category#getFeatureContainer <em>Feature Container</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.Category#getProductContainer <em>Product Container</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.Category#getBundleContainer <em>Bundle Container</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.Category#getFragmentContainer <em>Fragment Container</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.Category#getIuDetails <em>Iu Details</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.aggregator.p2view.P2viewPackage#getCategory()
 * @model
 * @generated
 */
public interface Category extends IUPresentation
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
	 * @see org.eclipse.buckminster.aggregator.p2view.P2viewPackage#getCategory_BundleContainer()
	 * @model
	 * @generated
	 */
	Bundles getBundleContainer();

	/**
	 * Returns the value of the '<em><b>Category Container</b></em>' reference. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Category Container</em>' reference isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Category Container</em>' reference.
	 * @see #setCategoryContainer(Categories)
	 * @see org.eclipse.buckminster.aggregator.p2view.P2viewPackage#getCategory_CategoryContainer()
	 * @model
	 * @generated
	 */
	Categories getCategoryContainer();

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
	 * @see org.eclipse.buckminster.aggregator.p2view.P2viewPackage#getCategory_FeatureContainer()
	 * @model
	 * @generated
	 */
	Features getFeatureContainer();

	/**
	 * Returns the value of the '<em><b>Fragment Container</b></em>' reference. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fragment Container</em>' reference isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Fragment Container</em>' reference.
	 * @see #setFragmentContainer(Fragments)
	 * @see org.eclipse.buckminster.aggregator.p2view.P2viewPackage#getCategory_FragmentContainer()
	 * @model
	 * @generated
	 */
	Fragments getFragmentContainer();

	/**
	 * Returns the value of the '<em><b>Iu Details</b></em>' reference. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Iu Details</em>' reference isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Iu Details</em>' reference.
	 * @see org.eclipse.buckminster.aggregator.p2view.P2viewPackage#getCategory_IuDetails()
	 * @model resolveProxies="false" changeable="false"
	 * @generated
	 */
	IUDetails getIuDetails();

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
	Categories getNotNullCategoryContainer();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model kind="operation"
	 * @generated
	 */
	Features getNotNullFeatureContainer();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model kind="operation"
	 * @generated
	 */
	Fragments getNotNullFragmentContainer();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model kind="operation"
	 * @generated
	 */
	Products getNotNullProductContainer();

	/**
	 * Returns the value of the '<em><b>Product Container</b></em>' reference. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Product Container</em>' reference isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Product Container</em>' reference.
	 * @see #setProductContainer(Products)
	 * @see org.eclipse.buckminster.aggregator.p2view.P2viewPackage#getCategory_ProductContainer()
	 * @model
	 * @generated
	 */
	Products getProductContainer();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model kind="operation"
	 * @generated
	 */
	boolean isNested();

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.p2view.Category#getBundleContainer
	 * <em>Bundle Container</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Bundle Container</em>' reference.
	 * @see #getBundleContainer()
	 * @generated
	 */
	void setBundleContainer(Bundles value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.p2view.Category#getCategoryContainer
	 * <em>Category Container</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Category Container</em>' reference.
	 * @see #getCategoryContainer()
	 * @generated
	 */
	void setCategoryContainer(Categories value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.p2view.Category#getFeatureContainer
	 * <em>Feature Container</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Feature Container</em>' reference.
	 * @see #getFeatureContainer()
	 * @generated
	 */
	void setFeatureContainer(Features value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.p2view.Category#getFragmentContainer
	 * <em>Fragment Container</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Fragment Container</em>' reference.
	 * @see #getFragmentContainer()
	 * @generated
	 */
	void setFragmentContainer(Fragments value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.p2view.Category#getProductContainer
	 * <em>Product Container</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Product Container</em>' reference.
	 * @see #getProductContainer()
	 * @generated
	 */
	void setProductContainer(Products value);

} // Category
