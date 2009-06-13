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
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Aggregator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.buckminster.aggregator.Aggregator#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.buckminster.aggregator.Aggregator#getBuildRoot <em>Build Root</em>}</li>
 *   <li>{@link org.eclipse.buckminster.aggregator.Aggregator#getConfigurations <em>Configurations</em>}</li>
 *   <li>{@link org.eclipse.buckminster.aggregator.Aggregator#getContributions <em>Contributions</em>}</li>
 *   <li>{@link org.eclipse.buckminster.aggregator.Aggregator#getLabel <em>Label</em>}</li>
 *   <li>{@link org.eclipse.buckminster.aggregator.Aggregator#getBuildmaster <em>Buildmaster</em>}</li>
 *   <li>{@link org.eclipse.buckminster.aggregator.Aggregator#isSendmail <em>Sendmail</em>}</li>
 *   <li>{@link org.eclipse.buckminster.aggregator.Aggregator#getContacts <em>Contacts</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getAggregator()
 * @model
 * @generated
 */
public interface Aggregator extends EObject
{
  /**
   * Returns the value of the '<em><b>Type</b></em>' attribute.
   * The literals are from the enumeration {@link org.eclipse.buckminster.aggregator.AggregateType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Type</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type</em>' attribute.
   * @see org.eclipse.buckminster.aggregator.AggregateType
   * @see #setType(AggregateType)
   * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getAggregator_Type()
   * @model required="true"
   * @generated
   */
  AggregateType getType();

  /**
   * Sets the value of the '{@link org.eclipse.buckminster.aggregator.Aggregator#getType <em>Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type</em>' attribute.
   * @see org.eclipse.buckminster.aggregator.AggregateType
   * @see #getType()
   * @generated
   */
  void setType(AggregateType value);

  /**
   * Returns the value of the '<em><b>Build Root</b></em>' attribute.
   * The default value is <code>"${user.home}/build"</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Build Root</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Build Root</em>' attribute.
   * @see #setBuildRoot(String)
   * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getAggregator_BuildRoot()
   * @model default="${user.home}/build"
   * @generated
   */
  String getBuildRoot();

  /**
   * Sets the value of the '{@link org.eclipse.buckminster.aggregator.Aggregator#getBuildRoot <em>Build Root</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Build Root</em>' attribute.
   * @see #getBuildRoot()
   * @generated
   */
  void setBuildRoot(String value);

  /**
   * Returns the value of the '<em><b>Configurations</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.buckminster.aggregator.Configuration}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Configurations</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Configurations</em>' containment reference list.
   * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getAggregator_Configurations()
   * @model containment="true" required="true"
   * @generated
   */
  EList<Configuration> getConfigurations();

  /**
   * Returns the value of the '<em><b>Contributions</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.buckminster.aggregator.Contribution}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Contributions</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Contributions</em>' containment reference list.
   * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getAggregator_Contributions()
   * @model containment="true" keys="label"
   * @generated
   */
  EList<Contribution> getContributions();

  /**
   * Returns the value of the '<em><b>Label</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Label</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Label</em>' attribute.
   * @see #setLabel(String)
   * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getAggregator_Label()
   * @model
   * @generated
   */
  String getLabel();

  /**
   * Sets the value of the '{@link org.eclipse.buckminster.aggregator.Aggregator#getLabel <em>Label</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Label</em>' attribute.
   * @see #getLabel()
   * @generated
   */
  void setLabel(String value);

  /**
   * Returns the value of the '<em><b>Buildmaster</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Buildmaster</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Buildmaster</em>' containment reference.
   * @see #setBuildmaster(Contact)
   * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getAggregator_Buildmaster()
   * @model containment="true" keys="email"
   * @generated
   */
  Contact getBuildmaster();

  /**
   * Sets the value of the '{@link org.eclipse.buckminster.aggregator.Aggregator#getBuildmaster <em>Buildmaster</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Buildmaster</em>' containment reference.
   * @see #getBuildmaster()
   * @generated
   */
  void setBuildmaster(Contact value);

  /**
   * Returns the value of the '<em><b>Sendmail</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Sendmail</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Sendmail</em>' attribute.
   * @see #setSendmail(boolean)
   * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getAggregator_Sendmail()
   * @model
   * @generated
   */
  boolean isSendmail();

  /**
   * Sets the value of the '{@link org.eclipse.buckminster.aggregator.Aggregator#isSendmail <em>Sendmail</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Sendmail</em>' attribute.
   * @see #isSendmail()
   * @generated
   */
  void setSendmail(boolean value);

  /**
   * Returns the value of the '<em><b>Contacts</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.buckminster.aggregator.Contact}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Contacts</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Contacts</em>' containment reference list.
   * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getAggregator_Contacts()
   * @model containment="true" keys="email"
   * @generated
   */
  EList<Contact> getContacts();

} // Aggregator
