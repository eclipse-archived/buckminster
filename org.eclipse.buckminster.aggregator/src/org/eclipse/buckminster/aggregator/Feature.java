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
 * <li>{@link org.eclipse.buckminster.aggregator.Feature#getCategories <em>Categories</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getFeature()
 * @model
 * @generated
 */
public interface Feature extends MappedUnit
{
	/**
	 * Returns the value of the '<em><b>Categories</b></em>' reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.aggregator.CustomCategory}. It is bidirectional and its opposite is '
	 * {@link org.eclipse.buckminster.aggregator.CustomCategory#getFeatures <em>Features</em>}'. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Categories</em>' reference list isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Categories</em>' reference list.
	 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getFeature_Categories()
	 * @see org.eclipse.buckminster.aggregator.CustomCategory#getFeatures
	 * @model opposite="features"
	 * @generated
	 */
	EList<CustomCategory> getCategories();

} // Feature
