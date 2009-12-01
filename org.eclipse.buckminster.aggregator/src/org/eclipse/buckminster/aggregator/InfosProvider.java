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

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Infos Provider</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.InfosProvider#getErrors <em>Errors</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.InfosProvider#getWarnings <em>Warnings</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.InfosProvider#getInfos <em>Infos</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getInfosProvider()
 * @model
 * @generated
 */
public interface InfosProvider
{
	/**
	 * Returns the value of the '<em><b>Errors</b></em>' attribute list. The list contents are of type
	 * {@link java.lang.String}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Errors</em>' attribute list isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Errors</em>' attribute list.
	 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getInfosProvider_Errors()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" transient="true"
	 * @generated
	 */
	EList<String> getErrors();

	/**
	 * Returns the value of the '<em><b>Infos</b></em>' attribute list. The list contents are of type
	 * {@link java.lang.String}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Infos</em>' attribute list isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Infos</em>' attribute list.
	 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getInfosProvider_Infos()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" transient="true"
	 * @generated
	 */
	EList<String> getInfos();

	/**
	 * Returns the value of the '<em><b>Warnings</b></em>' attribute list. The list contents are of type
	 * {@link java.lang.String}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Warnings</em>' attribute list isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Warnings</em>' attribute list.
	 * @see org.eclipse.buckminster.aggregator.AggregatorPackage#getInfosProvider_Warnings()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" transient="true"
	 * @generated
	 */
	EList<String> getWarnings();

} // InfosProvider
