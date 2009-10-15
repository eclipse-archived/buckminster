/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Aggregator</b></em>'.
 * 
 * @extends StatusProvider <!-- end-user-doc -->
 * 
 *          <p>
 *          The following features are supported:
 *          <ul>
 *          <li>{@link org.eclipse.buckminster.aggregator.Aggregator#getConfigurations <em>Configurations</em>}</li>
 *          <li>{@link org.eclipse.buckminster.aggregator.Aggregator#getContributions <em>Contributions</em>}</li>
 *          <li>{@link org.eclipse.buckminster.aggregator.Aggregator#getBuildmaster <em>Buildmaster</em>}</li>
 *          <li>{@link org.eclipse.buckminster.aggregator.Aggregator#getContacts <em>Contacts</em>}</li>
 *          <li>{@link org.eclipse.buckminster.aggregator.Aggregator#getCustomCategories <em>Custom Categories</em>}</li>
 *          <li>{@link org.eclipse.buckminster.aggregator.Aggregator#getLabel <em>Label</em>}</li>
 *          <li>{@link org.eclipse.buckminster.aggregator.Aggregator#getBuildRoot <em>Build Root</em>}</li>
 *          <li>{@link org.eclipse.buckminster.aggregator.Aggregator#getPackedStrategy <em>Packed Strategy</em>}</li>
 *          <li>{@link org.eclipse.buckminster.aggregator.Aggregator#isSendmail <em>Sendmail</em>}</li>
 *          <li>{@link org.eclipse.buckminster.aggregator.Aggregator#getType <em>Type</em>}</li>
 *          <li>{@link org.eclipse.buckminster.aggregator.Aggregator#getValidationRepositories <em>Validation
 *          Repositories</em>}</li>
 *          </ul>
 *          </p>
 * 
 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getAggregator()
 * @model
 * @generated
 */
public interface Aggregator extends EObject, StatusProvider
{
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model
	 * @generated
	 */
	EList<MetadataRepositoryReference> getAllMetadataRepositoryReferences(boolean enabledOnly);

	/**
	 * Returns the value of the '<em><b>Buildmaster</b></em>' reference. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Buildmaster</em>' containment reference isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Buildmaster</em>' reference.
	 * @see #setBuildmaster(Contact)
	 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getAggregator_Buildmaster()
	 * @model keys="email"
	 * @generated
	 */
	Contact getBuildmaster();

	/**
	 * Returns the value of the '<em><b>Build Root</b></em>' attribute. The default value is
	 * <code>"${user.home}/build"</code>. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Build Root</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Build Root</em>' attribute.
	 * @see #setBuildRoot(String)
	 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getAggregator_BuildRoot()
	 * @model default="${user.home}/build"
	 * @generated
	 */
	String getBuildRoot();

	/**
	 * Returns the value of the '<em><b>Configurations</b></em>' containment reference list. The list contents are of
	 * type {@link org.eclipse.buckminster.aggregator.Configuration}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Configurations</em>' containment reference list isn't clear, there really should be
	 * more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Configurations</em>' containment reference list.
	 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getAggregator_Configurations()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<Configuration> getConfigurations();

	/**
	 * Returns the value of the '<em><b>Contacts</b></em>' containment reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.aggregator.Contact}. It is bidirectional and its opposite is '
	 * {@link org.eclipse.buckminster.aggregator.Contact#getAggregator <em>Aggregator</em>}'. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contacts</em>' containment reference list isn't clear, there really should be more of
	 * a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Contacts</em>' containment reference list.
	 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getAggregator_Contacts()
	 * @see org.eclipse.buckminster.aggregator.Contact#getAggregator
	 * @model opposite="aggregator" containment="true" keys="email"
	 * @generated
	 */
	EList<Contact> getContacts();

	/**
	 * Returns the value of the '<em><b>Contributions</b></em>' containment reference list. The list contents are of
	 * type {@link org.eclipse.buckminster.aggregator.Contribution}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contributions</em>' containment reference list isn't clear, there really should be
	 * more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Contributions</em>' containment reference list.
	 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getAggregator_Contributions()
	 * @model containment="true" keys="label"
	 * @generated
	 */
	EList<Contribution> getContributions();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model
	 * @generated
	 */
	EList<Contribution> getContributions(boolean enabledOnly);

	/**
	 * Returns the value of the '<em><b>Custom Categories</b></em>' containment reference list. The list contents are of
	 * type {@link org.eclipse.buckminster.aggregator.CustomCategory}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Custom Categories</em>' containment reference list isn't clear, there really should be
	 * more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Custom Categories</em>' containment reference list.
	 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getAggregator_CustomCategories()
	 * @model containment="true" keys="identifier"
	 * @generated
	 */
	EList<CustomCategory> getCustomCategories();

	/**
	 * Returns the value of the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Label</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Label</em>' attribute.
	 * @see #setLabel(String)
	 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getAggregator_Label()
	 * @model required="true"
	 * @generated
	 */
	String getLabel();

	/**
	 * Returns the value of the '<em><b>Packed Strategy</b></em>' attribute. The literals are from the enumeration
	 * {@link org.eclipse.buckminster.aggregator.PackedStrategy}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Packed Strategy</em>' attribute isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Packed Strategy</em>' attribute.
	 * @see org.eclipse.buckminster.aggregator.PackedStrategy
	 * @see #setPackedStrategy(PackedStrategy)
	 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getAggregator_PackedStrategy()
	 * @model
	 * @generated
	 */
	PackedStrategy getPackedStrategy();

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute. The literals are from the enumeration
	 * {@link org.eclipse.buckminster.aggregator.AggregateType}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see org.eclipse.buckminster.aggregator.AggregateType
	 * @see #setType(AggregateType)
	 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getAggregator_Type()
	 * @model required="true"
	 * @generated
	 */
	AggregateType getType();

	/**
	 * Returns the value of the '<em><b>Validation Repositories</b></em>' containment reference list. The list contents
	 * are of type {@link org.eclipse.buckminster.aggregator.MetadataRepositoryReference}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Validation Repositories</em>' containment reference list isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Validation Repositories</em>' containment reference list.
	 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getAggregator_ValidationRepositories()
	 * @model containment="true"
	 * @generated
	 */
	EList<MetadataRepositoryReference> getValidationRepositories();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model
	 * @generated
	 */
	EList<MetadataRepositoryReference> getValidationRepositories(boolean enabledOnly);

	/**
	 * Returns the value of the '<em><b>Sendmail</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sendmail</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Sendmail</em>' attribute.
	 * @see #setSendmail(boolean)
	 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getAggregator_Sendmail()
	 * @model
	 * @generated
	 */
	boolean isSendmail();

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.Aggregator#getBuildmaster <em>Buildmaster</em>}'
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Buildmaster</em>' reference.
	 * @see #getBuildmaster()
	 * @generated
	 */
	void setBuildmaster(Contact value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.Aggregator#getBuildRoot <em>Build Root</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Build Root</em>' attribute.
	 * @see #getBuildRoot()
	 * @generated
	 */
	void setBuildRoot(String value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.Aggregator#getLabel <em>Label</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Label</em>' attribute.
	 * @see #getLabel()
	 * @generated
	 */
	void setLabel(String value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.Aggregator#getPackedStrategy
	 * <em>Packed Strategy</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Packed Strategy</em>' attribute.
	 * @see org.eclipse.buckminster.aggregator.PackedStrategy
	 * @see #getPackedStrategy()
	 * @generated
	 */
	void setPackedStrategy(PackedStrategy value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.Aggregator#isSendmail <em>Sendmail</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Sendmail</em>' attribute.
	 * @see #isSendmail()
	 * @generated
	 */
	void setSendmail(boolean value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.Aggregator#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Type</em>' attribute.
	 * @see org.eclipse.buckminster.aggregator.AggregateType
	 * @see #getType()
	 * @generated
	 */
	void setType(AggregateType value);

} // Aggregator
