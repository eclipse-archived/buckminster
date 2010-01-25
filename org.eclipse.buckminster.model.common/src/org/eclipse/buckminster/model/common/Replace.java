/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.model.common;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Replace</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.buckminster.model.common.Replace#getMatches <em>Matches</em>}</li>
 *   <li>{@link org.eclipse.buckminster.model.common.Replace#getPattern <em>Pattern</em>}</li>
 *   <li>{@link org.eclipse.buckminster.model.common.Replace#isQuotePattern <em>Quote Pattern</em>}</li>
 *   <li>{@link org.eclipse.buckminster.model.common.Replace#getReplacement <em>Replacement</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.buckminster.model.common.CommonPackage#getReplace()
 * @model
 * @generated
 */
public interface Replace extends ValueFilter
{
	/**
	 * Returns the value of the '<em><b>Matches</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.buckminster.model.common.Match}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Matches</em>' containment reference list isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Matches</em>' containment reference list.
	 * @see org.eclipse.buckminster.model.common.CommonPackage#getReplace_Matches()
	 * @model containment="true"
	 *        extendedMetaData="namespace='##targetNamespace' kind='element'"
	 * @generated
	 */
	EList<Match> getMatches();

	/**
	 * Returns the value of the '<em><b>Pattern</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pattern</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pattern</em>' attribute.
	 * @see #setPattern(String)
	 * @see org.eclipse.buckminster.model.common.CommonPackage#getReplace_Pattern()
	 * @model extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getPattern();

	/**
	 * Returns the value of the '<em><b>Replacement</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Replacement</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Replacement</em>' attribute.
	 * @see #setReplacement(String)
	 * @see org.eclipse.buckminster.model.common.CommonPackage#getReplace_Replacement()
	 * @model extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getReplacement();

	/**
	 * Returns the value of the '<em><b>Quote Pattern</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Quote Pattern</em>' attribute isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Quote Pattern</em>' attribute.
	 * @see #isSetQuotePattern()
	 * @see #unsetQuotePattern()
	 * @see #setQuotePattern(boolean)
	 * @see org.eclipse.buckminster.model.common.CommonPackage#getReplace_QuotePattern()
	 * @model default="false" unsettable="true"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	boolean isQuotePattern();

	/**
	 * Returns whether the value of the '{@link org.eclipse.buckminster.model.common.Replace#isQuotePattern <em>Quote Pattern</em>}' attribute is set.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return whether the value of the '<em>Quote Pattern</em>' attribute is set.
	 * @see #unsetQuotePattern()
	 * @see #isQuotePattern()
	 * @see #setQuotePattern(boolean)
	 * @generated
	 */
	boolean isSetQuotePattern();

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.model.common.Replace#getPattern <em>Pattern</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pattern</em>' attribute.
	 * @see #getPattern()
	 * @generated
	 */
	void setPattern(String value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.model.common.Replace#isQuotePattern <em>Quote Pattern</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Quote Pattern</em>' attribute.
	 * @see #isSetQuotePattern()
	 * @see #unsetQuotePattern()
	 * @see #isQuotePattern()
	 * @generated
	 */
	void setQuotePattern(boolean value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.model.common.Replace#getReplacement <em>Replacement</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Replacement</em>' attribute.
	 * @see #getReplacement()
	 * @generated
	 */
	void setReplacement(String value);

	/**
	 * Unsets the value of the '{@link org.eclipse.buckminster.model.common.Replace#isQuotePattern <em>Quote Pattern</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isSetQuotePattern()
	 * @see #isQuotePattern()
	 * @see #setQuotePattern(boolean)
	 * @generated
	 */
	void unsetQuotePattern();

} // Replace
