/**
 * Copyright (c) 2006-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Status</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.Status#getCode <em>Code</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.Status#getMessage <em>Message</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getStatus()
 * @model
 * @generated
 */
public interface Status
{
	/**
	 * Returns the value of the '<em><b>Code</b></em>' attribute. The default value is <code>""</code>. The literals are
	 * from the enumeration {@link org.eclipse.buckminster.aggregator.StatusCode}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Code</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Code</em>' attribute.
	 * @see org.eclipse.buckminster.aggregator.StatusCode
	 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getStatus_Code()
	 * @model default="" required="true" changeable="false"
	 * @generated
	 */
	StatusCode getCode();

	/**
	 * Returns the value of the '<em><b>Message</b></em>' attribute. The default value is <code>""</code>. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Message</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Message</em>' attribute.
	 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getStatus_Message()
	 * @model default="" dataType="org.eclipse.emf.ecore.xml.type.String" changeable="false"
	 * @generated
	 */
	String getMessage();

} // Status
