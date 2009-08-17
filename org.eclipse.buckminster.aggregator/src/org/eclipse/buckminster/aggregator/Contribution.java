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
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Contribution</b></em>'.
 * 
 * @extends StatusProvider <!-- end-user-doc -->
 * 
 *          <p>
 *          The following features are supported:
 *          <ul>
 *          <li>{@link org.eclipse.buckminster.aggregator.Contribution#getLabel <em>Label</em>}</li>
 *          <li>{@link org.eclipse.buckminster.aggregator.Contribution#getRepositories <em>Repositories</em>}</li>
 *          <li>{@link org.eclipse.buckminster.aggregator.Contribution#getContacts <em>Contacts</em>}</li>
 *          <li>{@link org.eclipse.buckminster.aggregator.Contribution#isEnabled <em>Enabled</em>}</li>
 *          </ul>
 *          </p>
 * 
 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getContribution()
 * @model
 * @generated
 */
public interface Contribution extends EObject, StatusProvider
{
	/**
	 * Returns the value of the '<em><b>Contacts</b></em>' reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.aggregator.Contact}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contacts</em>' reference list isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Contacts</em>' reference list.
	 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getContribution_Contacts()
	 * @model keys="email"
	 * @generated
	 */
	EList<Contact> getContacts();

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
	 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getContribution_Label()
	 * @model required="true"
	 * @generated
	 */
	String getLabel();

	/**
	 * Returns the value of the '<em><b>Repositories</b></em>' containment reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.aggregator.MappedRepository}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Repositories</em>' containment reference list isn't clear, there really should be more
	 * of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Repositories</em>' containment reference list.
	 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getContribution_Repositories()
	 * @model containment="true"
	 * @generated
	 */
	EList<MappedRepository> getRepositories();

	/**
	 * Returns the value of the '<em><b>Enabled</b></em>' attribute. The default value is <code>"true"</code>. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Enabled</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Enabled</em>' attribute.
	 * @see #setEnabled(boolean)
	 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getContribution_Enabled()
	 * @model default="true"
	 * @generated
	 */
	boolean isEnabled();

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.Contribution#isEnabled <em>Enabled</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Enabled</em>' attribute.
	 * @see #isEnabled()
	 * @generated
	 */
	void setEnabled(boolean value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.Contribution#getLabel <em>Label</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Label</em>' attribute.
	 * @see #getLabel()
	 * @generated
	 */
	void setLabel(String value);

} // Contribution
