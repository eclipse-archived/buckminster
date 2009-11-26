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
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Generators Type</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.cspecxml.IGeneratorsType#getGenerator <em>Generator</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getGeneratorsType()
 * @model extendedMetaData="name='generators_._type' kind='elementOnly'"
 * @generated
 */
public interface IGeneratorsType extends EObject
{
	/**
	 * Returns the value of the '<em><b>Generator</b></em>' containment reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.cspecxml.IGenerator}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Generator</em>' containment reference list isn't clear, there really should be more of
	 * a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Generator</em>' containment reference list.
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getGeneratorsType_Generator()
	 * @model containment="true" extendedMetaData="kind='element' name='generator' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<IGenerator> getGenerator();

} // IGeneratorsType
