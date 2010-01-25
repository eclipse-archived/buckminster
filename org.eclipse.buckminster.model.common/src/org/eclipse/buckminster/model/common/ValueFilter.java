/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.model.common;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Value Filter</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.buckminster.model.common.ValueFilter#getConstants <em>Constants</em>}</li>
 *   <li>{@link org.eclipse.buckminster.model.common.ValueFilter#getFormats <em>Formats</em>}</li>
 *   <li>{@link org.eclipse.buckminster.model.common.ValueFilter#getPropertyRefs <em>Property Refs</em>}</li>
 *   <li>{@link org.eclipse.buckminster.model.common.ValueFilter#getReplacements <em>Replacements</em>}</li>
 *   <li>{@link org.eclipse.buckminster.model.common.ValueFilter#getSplits <em>Splits</em>}</li>
 *   <li>{@link org.eclipse.buckminster.model.common.ValueFilter#getToLowers <em>To Lowers</em>}</li>
 *   <li>{@link org.eclipse.buckminster.model.common.ValueFilter#getToUppers <em>To Uppers</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.buckminster.model.common.CommonPackage#getValueFilter()
 * @model abstract="true"
 * @extends BObject
 * @generated
 */
public interface ValueFilter extends BObject
{
	/**
	 * Returns the value of the '<em><b>Constants</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.buckminster.model.common.Constant}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Constants</em>' containment reference list isn't clear, there really should be more of
	 * a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Constants</em>' containment reference list.
	 * @see org.eclipse.buckminster.model.common.CommonPackage#getValueFilter_Constants()
	 * @model containment="true"
	 *        extendedMetaData="namespace='##targetNamespace' kind='element' name='constant'"
	 * @generated
	 */
	EList<Constant> getConstants();

	/**
	 * Returns the value of the '<em><b>Formats</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.buckminster.model.common.Format}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Formats</em>' containment reference list isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Formats</em>' containment reference list.
	 * @see org.eclipse.buckminster.model.common.CommonPackage#getValueFilter_Formats()
	 * @model containment="true"
	 *        extendedMetaData="namespace='##targetNamespace' kind='element' name='format'"
	 * @generated
	 */
	EList<Format> getFormats();

	/**
	 * Returns the value of the '<em><b>Property Refs</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.buckminster.model.common.PropertyRef}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Property Refs</em>' containment reference list isn't clear, there really should be
	 * more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Property Refs</em>' containment reference list.
	 * @see org.eclipse.buckminster.model.common.CommonPackage#getValueFilter_PropertyRefs()
	 * @model containment="true"
	 *        extendedMetaData="namespace='##targetNamespace' kind='element' name='propertyRef'"
	 * @generated
	 */
	EList<PropertyRef> getPropertyRefs();

	/**
	 * Returns the value of the '<em><b>Replacements</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.buckminster.model.common.Replace}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Replacements</em>' containment reference list isn't clear, there really should be more
	 * of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Replacements</em>' containment reference list.
	 * @see org.eclipse.buckminster.model.common.CommonPackage#getValueFilter_Replacements()
	 * @model containment="true"
	 *        extendedMetaData="namespace='##targetNamespace' kind='element' name='replace'"
	 * @generated
	 */
	EList<Replace> getReplacements();

	/**
	 * Returns the value of the '<em><b>Splits</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.buckminster.model.common.Split}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Splits</em>' containment reference list isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Splits</em>' containment reference list.
	 * @see org.eclipse.buckminster.model.common.CommonPackage#getValueFilter_Splits()
	 * @model containment="true"
	 *        extendedMetaData="namespace='##targetNamespace' kind='element' name='split'"
	 * @generated
	 */
	EList<Split> getSplits();

	/**
	 * Returns the value of the '<em><b>To Lowers</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.buckminster.model.common.ToLower}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>To Lowers</em>' containment reference list isn't clear, there really should be more of
	 * a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>To Lowers</em>' containment reference list.
	 * @see org.eclipse.buckminster.model.common.CommonPackage#getValueFilter_ToLowers()
	 * @model containment="true"
	 *        extendedMetaData="namespace='##targetNamespace' kind='element' name='toLower'"
	 * @generated
	 */
	EList<ToLower> getToLowers();

	/**
	 * Returns the value of the '<em><b>To Uppers</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.buckminster.model.common.ToUpper}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>To Uppers</em>' containment reference list isn't clear, there really should be more of
	 * a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>To Uppers</em>' containment reference list.
	 * @see org.eclipse.buckminster.model.common.CommonPackage#getValueFilter_ToUppers()
	 * @model containment="true"
	 *        extendedMetaData="namespace='##targetNamespace' kind='element' name='toUpper'"
	 * @generated
	 */
	EList<ToUpper> getToUppers();

} // ValueFilter
