/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspecxml;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Dependencies Type</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.cspecxml.IDependenciesType#getDependency
 * <em>Dependency</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getDependenciesType()
 * @model extendedMetaData="name='dependencies_._type' kind='elementOnly'"
 * @generated
 */
public interface IDependenciesType extends EObject {
	/**
	 * Returns the value of the '<em><b>Dependency</b></em>' containment
	 * reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.cspecxml.IComponentRequest}. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dependency</em>' containment reference list
	 * isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Dependency</em>' containment reference
	 *         list.
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getDependenciesType_Dependency()
	 * @model containment="true" extendedMetaData=
	 *        "kind='element' name='dependency' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<IComponentRequest> getDependency();

} // IDependenciesType
