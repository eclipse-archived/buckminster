/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.model.common;

import java.util.regex.Pattern;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Match</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.model.common.Match#getPattern <em>Pattern
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.model.common.Match#isQuotePattern <em>
 * Quote Pattern</em>}</li>
 * <li>{@link org.eclipse.buckminster.model.common.Match#getReplacement <em>
 * Replacement</em>}</li>
 * <li>{@link org.eclipse.buckminster.model.common.Match#getCompiledPattern <em>
 * Compiled Pattern</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.model.common.CommonPackage#getMatch()
 * @model
 * @generated
 */
public interface Match extends EObject {
	/**
	 * Returns the value of the '<em><b>Compiled Pattern</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Compiled Pattern</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Compiled Pattern</em>' attribute.
	 * @see org.eclipse.buckminster.model.common.CommonPackage#getMatch_CompiledPattern()
	 * @model dataType="org.eclipse.buckminster.model.common.Pattern"
	 *        transient="true" changeable="false" derived="true"
	 * @generated
	 */
	Pattern getCompiledPattern();

	/**
	 * Returns the value of the '<em><b>Pattern</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pattern</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Pattern</em>' attribute.
	 * @see #setPattern(String)
	 * @see org.eclipse.buckminster.model.common.CommonPackage#getMatch_Pattern()
	 * @model required="true" extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getPattern();

	/**
	 * Returns the value of the '<em><b>Replacement</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Replacement</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Replacement</em>' attribute.
	 * @see #setReplacement(String)
	 * @see org.eclipse.buckminster.model.common.CommonPackage#getMatch_Replacement()
	 * @model required="true" extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getReplacement();

	/**
	 * Returns the value of the '<em><b>Quote Pattern</b></em>' attribute. The
	 * default value is <code>"false"</code>. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Quote Pattern</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Quote Pattern</em>' attribute.
	 * @see #isSetQuotePattern()
	 * @see #unsetQuotePattern()
	 * @see #setQuotePattern(boolean)
	 * @see org.eclipse.buckminster.model.common.CommonPackage#getMatch_QuotePattern()
	 * @model default="false" unsettable="true"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	boolean isQuotePattern();

	/**
	 * Returns whether the value of the '
	 * {@link org.eclipse.buckminster.model.common.Match#isQuotePattern
	 * <em>Quote Pattern</em>}' attribute is set. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return whether the value of the '<em>Quote Pattern</em>' attribute is
	 *         set.
	 * @see #unsetQuotePattern()
	 * @see #isQuotePattern()
	 * @see #setQuotePattern(boolean)
	 * @generated
	 */
	boolean isSetQuotePattern();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model
	 * @generated
	 */
	String match(String resolved);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.model.common.Match#getPattern
	 * <em>Pattern</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @param value
	 *            the new value of the '<em>Pattern</em>' attribute.
	 * @see #getPattern()
	 * @generated
	 */
	void setPattern(String value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.model.common.Match#isQuotePattern
	 * <em>Quote Pattern</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Quote Pattern</em>' attribute.
	 * @see #isSetQuotePattern()
	 * @see #unsetQuotePattern()
	 * @see #isQuotePattern()
	 * @generated
	 */
	void setQuotePattern(boolean value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.model.common.Match#getReplacement
	 * <em>Replacement</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Replacement</em>' attribute.
	 * @see #getReplacement()
	 * @generated
	 */
	void setReplacement(String value);

	/**
	 * Unsets the value of the '
	 * {@link org.eclipse.buckminster.model.common.Match#isQuotePattern
	 * <em>Quote Pattern</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #isSetQuotePattern()
	 * @see #isQuotePattern()
	 * @see #setQuotePattern(boolean)
	 * @generated
	 */
	void unsetQuotePattern();

} // Match
