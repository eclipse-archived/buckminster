/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Mapped Category</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.buckminster.aggregator.Category#getLabelOverride <em>Label Override</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getCategory()
 * @model
 * @generated
 */
public interface Category extends MappedUnit
{
	/**
	 * Returns the value of the '<em><b>Label Override</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Label Override</em>' attribute isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Label Override</em>' attribute.
	 * @see #setLabelOverride(String)
	 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getCategory_LabelOverride()
	 * @model
	 * @generated
	 */
	String getLabelOverride();

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.Category#getLabelOverride <em>Label Override</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Label Override</em>' attribute.
	 * @see #getLabelOverride()
	 * @generated
	 */
	void setLabelOverride(String value);

} // Category
