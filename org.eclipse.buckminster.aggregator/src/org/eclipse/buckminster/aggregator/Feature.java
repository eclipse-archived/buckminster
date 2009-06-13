/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Feature</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.buckminster.aggregator.Feature#getCategory <em>Category</em>}</li>
 *   <li>{@link org.eclipse.buckminster.aggregator.Feature#isInProduct <em>In Product</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getFeature()
 * @model
 * @generated
 */
public interface Feature extends InstallationUnit
{
	/**
	 * Returns the value of the '<em><b>Category</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.buckminster.aggregator.Category}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.buckminster.aggregator.Category#getFeatures <em>Features</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Category</em>' reference list isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Category</em>' reference list.
	 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getFeature_Category()
	 * @see org.eclipse.buckminster.aggregator.Category#getFeatures
	 * @model opposite="features"
	 * @generated
	 */
	EList<Category> getCategory();

	/**
	 * Returns the value of the '<em><b>In Product</b></em>' attribute. The default value is <code>"true"</code>. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>In Product</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>In Product</em>' attribute.
	 * @see #setInProduct(boolean)
	 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getFeature_InProduct()
	 * @model default="true"
	 * @generated
	 */
	boolean isInProduct();

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.Feature#isInProduct <em>In Product</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>In Product</em>' attribute.
	 * @see #isInProduct()
	 * @generated
	 */
	void setInProduct(boolean value);

} // Feature
