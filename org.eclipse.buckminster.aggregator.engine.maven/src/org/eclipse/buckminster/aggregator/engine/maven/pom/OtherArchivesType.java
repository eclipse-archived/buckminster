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
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Other Archives Type</b></em>'. <!-- end-user-doc
 * -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.OtherArchivesType#getOtherArchive <em>Other Archive
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage#getOtherArchivesType()
 * @model extendedMetaData="name='otherArchives_._type' kind='elementOnly'"
 * @generated
 */
public interface OtherArchivesType extends EObject
{
	/**
	 * Returns the value of the '<em><b>Other Archive</b></em>' attribute list. The list contents are of type
	 * {@link java.lang.String}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Other Archive</em>' attribute list isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Other Archive</em>' attribute list.
	 * @see org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage#getOtherArchivesType_OtherArchive()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='otherArchive' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<String> getOtherArchive();

} // OtherArchivesType
