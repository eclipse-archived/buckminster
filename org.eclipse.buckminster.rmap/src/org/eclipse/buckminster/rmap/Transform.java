/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap;

import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Transform</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.rmap.Transform#getFromPattern <em>From Pattern</em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.Transform#getFromReplacement <em>From Replacement</em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.Transform#getToPattern <em>To Pattern</em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.Transform#getToReplacement <em>To Replacement</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.rmap.RmapPackage#getTransform()
 * @model
 * @generated
 */
public interface Transform extends EObject
{
	/**
	 * Returns the value of the '<em><b>From Pattern</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>From Pattern</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>From Pattern</em>' attribute.
	 * @see #setFromPattern(Pattern)
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getTransform_FromPattern()
	 * @model dataType="org.eclipse.buckminster.model.common.Pattern" required="true"
	 * @generated
	 */
	Pattern getFromPattern();

	/**
	 * Returns the value of the '<em><b>From Replacement</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>From Replacement</em>' attribute isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>From Replacement</em>' attribute.
	 * @see #setFromReplacement(String)
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getTransform_FromReplacement()
	 * @model required="true"
	 * @generated
	 */
	String getFromReplacement();

	/**
	 * Returns the value of the '<em><b>To Pattern</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>To Pattern</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>To Pattern</em>' attribute.
	 * @see #setToPattern(Pattern)
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getTransform_ToPattern()
	 * @model dataType="org.eclipse.buckminster.model.common.Pattern" required="true"
	 * @generated
	 */
	Pattern getToPattern();

	/**
	 * Returns the value of the '<em><b>To Replacement</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>To Replacement</em>' attribute isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>To Replacement</em>' attribute.
	 * @see #setToReplacement(String)
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getTransform_ToReplacement()
	 * @model required="true"
	 * @generated
	 */
	String getToReplacement();

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.rmap.Transform#getFromPattern <em>From Pattern</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>From Pattern</em>' attribute.
	 * @see #getFromPattern()
	 * @generated
	 */
	void setFromPattern(Pattern value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.rmap.Transform#getFromReplacement
	 * <em>From Replacement</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>From Replacement</em>' attribute.
	 * @see #getFromReplacement()
	 * @generated
	 */
	void setFromReplacement(String value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.rmap.Transform#getToPattern <em>To Pattern</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>To Pattern</em>' attribute.
	 * @see #getToPattern()
	 * @generated
	 */
	void setToPattern(Pattern value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.rmap.Transform#getToReplacement <em>To Replacement</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>To Replacement</em>' attribute.
	 * @see #getToReplacement()
	 * @generated
	 */
	void setToReplacement(String value);

} // Transform
