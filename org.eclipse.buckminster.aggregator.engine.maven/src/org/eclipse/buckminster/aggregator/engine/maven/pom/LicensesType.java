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
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Licenses Type</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.LicensesType#getLicense <em>License</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage#getLicensesType()
 * @model extendedMetaData="name='licenses_._type' kind='elementOnly'"
 * @generated
 */
public interface LicensesType extends EObject
{
	/**
	 * Returns the value of the '<em><b>License</b></em>' containment reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.aggregator.engine.maven.pom.License}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>License</em>' containment reference list isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>License</em>' containment reference list.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage#getLicensesType_License()
	 * @model containment="true" extendedMetaData="kind='element' name='license' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<License> getLicense();

} // LicensesType
