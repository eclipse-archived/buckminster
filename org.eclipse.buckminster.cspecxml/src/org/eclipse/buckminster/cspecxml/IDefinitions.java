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
 * <em><b>Definitions</b></em>'. <!-- end-user-doc -->
 * 
 * <!-- begin-model-doc -->
 * 
 * A definition defines an attribute as something that is part of another
 * attribute. A good example is a Java package that is part of a jar file.<br/>
 * The Define is very similar to an OSGi manifest Export-Package
 * 
 * <!-- end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.cspecxml.IDefinitions#getDefine <em>Define
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getDefinitions()
 * @model extendedMetaData="name='Definitions' kind='elementOnly'"
 * @generated
 */
public interface IDefinitions extends EObject {
	/**
	 * Returns the value of the '<em><b>Define</b></em>' containment reference
	 * list. The list contents are of type
	 * {@link org.eclipse.buckminster.cspecxml.IAttribute}. <!-- begin-user-doc
	 * -->
	 * <p>
	 * If the meaning of the '<em>Define</em>' containment reference list isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Define</em>' containment reference list.
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getDefinitions_Define()
	 * @model containment="true" required="true" extendedMetaData=
	 *        "kind='element' name='define' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<IAttribute> getDefine();

} // IDefinitions
