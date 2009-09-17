/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Mapped Repository</b></em>'.
 * 
 * @extends StatusProvider <!-- end-user-doc -->
 * 
 *          <p>
 *          The following features are supported:
 *          <ul>
 *          <li>{@link org.eclipse.buckminster.aggregator.MappedRepository#getProducts <em>Products</em>}</li>
 *          <li>{@link org.eclipse.buckminster.aggregator.MappedRepository#getBundles <em>Bundles</em>}</li>
 *          <li>{@link org.eclipse.buckminster.aggregator.MappedRepository#getFeatures <em>Features</em>}</li>
 *          <li>{@link org.eclipse.buckminster.aggregator.MappedRepository#getCategories <em>Categories</em>}</li>
 *          <li>{@link org.eclipse.buckminster.aggregator.MappedRepository#isMirrorArtifacts <em>Mirror Artifacts</em>}</li>
 *          <li>{@link org.eclipse.buckminster.aggregator.MappedRepository#getCategoryPrefix <em>Category Prefix</em>}</li>
 *          <li>{@link org.eclipse.buckminster.aggregator.MappedRepository#getMapRules <em>Map Rules</em>}</li>
 *          </ul>
 *          </p>
 * 
 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getMappedRepository()
 * @model
 * @generated
 */
public interface MappedRepository extends MetadataRepositoryReference, StatusProvider
{
	/**
	 * Adds MappedUnit to the collection of either features, categories, bundles or products
	 * 
	 * @param mu
	 *            MappedUnit
	 */
	void addUnit(MappedUnit unit);

	/**
	 * Returns the value of the '<em><b>Bundles</b></em>' containment reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.aggregator.Bundle}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Bundles</em>' containment reference list isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Bundles</em>' containment reference list.
	 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getMappedRepository_Bundles()
	 * @model containment="true"
	 * @generated
	 */
	EList<Bundle> getBundles();

	/**
	 * Returns the value of the '<em><b>Categories</b></em>' containment reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.aggregator.Category}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Categories</em>' containment reference list isn't clear, there really should be more
	 * of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Categories</em>' containment reference list.
	 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getMappedRepository_Categories()
	 * @model containment="true"
	 * @generated
	 */
	EList<Category> getCategories();

	/**
	 * Returns the value of the '<em><b>Category Prefix</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Category Prefix</em>' attribute isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Category Prefix</em>' attribute.
	 * @see #setCategoryPrefix(String)
	 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getMappedRepository_CategoryPrefix()
	 * @model
	 * @generated
	 */
	String getCategoryPrefix();

	/**
	 * Returns the value of the '<em><b>Features</b></em>' containment reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.aggregator.Feature}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Features</em>' containment reference list isn't clear, there really should be more of
	 * a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Features</em>' containment reference list.
	 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getMappedRepository_Features()
	 * @model containment="true"
	 * @generated
	 */
	EList<Feature> getFeatures();

	/**
	 * Returns the value of the '<em><b>Map Rules</b></em>' containment reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.aggregator.MapRule}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Map Rules</em>' containment reference list isn't clear, there really should be more of
	 * a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Map Rules</em>' containment reference list.
	 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getMappedRepository_MapRules()
	 * @model containment="true"
	 * @generated
	 */
	EList<MapRule> getMapRules();

	/**
	 * Returns the value of the '<em><b>Products</b></em>' containment reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.aggregator.Product}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Products</em>' containment reference list isn't clear, there really should be more of
	 * a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Products</em>' containment reference list.
	 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getMappedRepository_Products()
	 * @model containment="true"
	 * @generated
	 */
	EList<Product> getProducts();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> Returns the MappedUnits referenced by this
	 * instance. Units that have their <code>enabled</code> flag set to false will be excluded if the parameter
	 * <code>enabledOnly</code> is set to <code>true</code>. <!-- end-model-doc -->
	 * 
	 * @model
	 * @generated
	 */
	EList<MappedUnit> getUnits(boolean enabledOnly);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> Returns <code>true</code> if the container
	 * has MappedUnit children. <!-- end-model-doc -->
	 * 
	 * @model kind="operation"
	 * @generated
	 */
	boolean isMapExclusive();

	/**
	 * Returns the value of the '<em><b>Mirror Artifacts</b></em>' attribute. The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mirror Artifacts</em>' attribute isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Mirror Artifacts</em>' attribute.
	 * @see #setMirrorArtifacts(boolean)
	 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getMappedRepository_MirrorArtifacts()
	 * @model default="true"
	 * @generated
	 */
	boolean isMirrorArtifacts();

	/**
	 * Removes MappedUnit from the collection of either features, categories, bundles or products
	 * 
	 * @param mu
	 *            MappedUnit
	 */
	void removeUnit(MappedUnit unit);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.MappedRepository#getCategoryPrefix
	 * <em>Category Prefix</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Category Prefix</em>' attribute.
	 * @see #getCategoryPrefix()
	 * @generated
	 */
	void setCategoryPrefix(String value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.MappedRepository#isMirrorArtifacts
	 * <em>Mirror Artifacts</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Mirror Artifacts</em>' attribute.
	 * @see #isMirrorArtifacts()
	 * @generated
	 */
	void setMirrorArtifacts(boolean value);

} // MappedRepository
