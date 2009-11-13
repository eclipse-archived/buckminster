/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Contact</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.Contact#getName <em>Name</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.Contact#getEmail <em>Email</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.Contact#getAggregator <em>Aggregator</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getContact()
 * @model
 * @generated
 */
public interface Contact
{
	/**
	 * Returns the value of the '<em><b>Aggregator</b></em>' container reference. It is bidirectional and its opposite
	 * is '{@link org.eclipse.buckminster.aggregator.Aggregator#getContacts <em>Contacts</em>}'. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Aggregator</em>' container reference isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Aggregator</em>' container reference.
	 * @see #setAggregator(Aggregator)
	 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getContact_Aggregator()
	 * @see org.eclipse.buckminster.aggregator.Aggregator#getContacts
	 * @model opposite="contacts" required="true" transient="false"
	 * @generated
	 */
	Aggregator getAggregator();

	/**
	 * Returns the value of the '<em><b>Email</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Email</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Email</em>' attribute.
	 * @see #setEmail(String)
	 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getContact_Email()
	 * @model required="true"
	 * @generated
	 */
	String getEmail();

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getContact_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.Contact#getAggregator <em>Aggregator</em>}'
	 * container reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Aggregator</em>' container reference.
	 * @see #getAggregator()
	 * @generated
	 */
	void setAggregator(Aggregator value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.Contact#getEmail <em>Email</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Email</em>' attribute.
	 * @see #getEmail()
	 * @generated
	 */
	void setEmail(String value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.aggregator.Contact#getName <em>Name</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // Contact
