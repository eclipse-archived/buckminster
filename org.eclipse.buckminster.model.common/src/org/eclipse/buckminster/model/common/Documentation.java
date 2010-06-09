/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.model.common;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Documentation</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.model.common.Documentation#getMixed <em>
 * Mixed</em>}</li>
 * <li>{@link org.eclipse.buckminster.model.common.Documentation#getAny <em>Any
 * </em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.model.common.Documentation#getAnyAttribute
 * <em>Any Attribute</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.model.common.CommonPackage#getDocumentation()
 * @model extendedMetaData="name='Documentation' kind='mixed'"
 * @extends BObject
 * @generated
 */
public interface Documentation extends BObject {

	/**
	 * Returns the value of the '<em><b>Any</b></em>' attribute list. The list
	 * contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Any</em>' attribute list isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Any</em>' attribute list.
	 * @see org.eclipse.buckminster.model.common.CommonPackage#getDocumentation_Any()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry"
	 *        many="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData=
	 *        "kind='elementWildcard' wildcards='##any' name=':1' processing='lax'"
	 * @generated
	 */
	FeatureMap getAny();

	/**
	 * Returns the value of the '<em><b>Any Attribute</b></em>' attribute list.
	 * The list contents are of type
	 * {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}. <!-- begin-user-doc
	 * -->
	 * <p>
	 * If the meaning of the '<em>Any Attribute</em>' attribute list isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Any Attribute</em>' attribute list.
	 * @see org.eclipse.buckminster.model.common.CommonPackage#getDocumentation_AnyAttribute()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry"
	 *        many="true" extendedMetaData=
	 *        "kind='attributeWildcard' wildcards='##any' name=':2' processing='lax'"
	 * @generated
	 */
	FeatureMap getAnyAttribute();

	/**
	 * Returns the value of the '<em><b>Mixed</b></em>' attribute list. The list
	 * contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mixed</em>' attribute list isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Mixed</em>' attribute list.
	 * @see org.eclipse.buckminster.model.common.CommonPackage#getDocumentation_Mixed()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry"
	 *        many="true"
	 *        extendedMetaData="kind='elementWildcard' name=':mixed'"
	 * @generated
	 */
	FeatureMap getMixed();
} // Documentation
