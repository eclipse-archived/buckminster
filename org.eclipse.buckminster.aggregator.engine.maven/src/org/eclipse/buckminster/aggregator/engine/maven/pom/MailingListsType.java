/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.engine.maven.pom;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Mailing Lists Type</b></em>'. <!-- end-user-doc
 * -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.MailingListsType#getMailingList <em>Mailing List</em>}
 * </li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage#getMailingListsType()
 * @model extendedMetaData="name='mailingLists_._type' kind='elementOnly'"
 * @generated
 */
public interface MailingListsType extends EObject
{
	/**
	 * Returns the value of the '<em><b>Mailing List</b></em>' containment reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.MailingList}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mailing List</em>' containment reference list isn't clear, there really should be more
	 * of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Mailing List</em>' containment reference list.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage#getMailingListsType_MailingList()
	 * @model containment="true" extendedMetaData="kind='element' name='mailingList' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<MailingList> getMailingList();

} // MailingListsType
