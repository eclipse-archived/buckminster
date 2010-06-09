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
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Notifiers Type</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.NotifiersType#getNotifier <em>Notifier</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage#getNotifiersType()
 * @model extendedMetaData="name='notifiers_._type' kind='elementOnly'"
 * @generated
 */
public interface NotifiersType extends EObject
{
	/**
	 * Returns the value of the '<em><b>Notifier</b></em>' containment reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.Notifier}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Notifier</em>' containment reference list isn't clear, there really should be more of
	 * a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Notifier</em>' containment reference list.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage#getNotifiersType_Notifier()
	 * @model containment="true" extendedMetaData="kind='element' name='notifier' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<Notifier> getNotifier();

} // NotifiersType
