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
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Matcher</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.rmap.Matcher#getPattern <em>Pattern</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.rmap.RmapPackage#getMatcher()
 * @model abstract="true"
 * @generated
 */
public interface Matcher extends EObject
{
	/**
	 * Returns the value of the '<em><b>Pattern</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pattern</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Pattern</em>' attribute.
	 * @see #setPattern(Pattern)
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getMatcher_Pattern()
	 * @model dataType="org.eclipse.buckminster.model.common.Pattern"
	 * @generated
	 */
	Pattern getPattern();

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.rmap.Matcher#getPattern <em>Pattern</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Pattern</em>' attribute.
	 * @see #getPattern()
	 * @generated
	 */
	void setPattern(Pattern value);

} // Matcher
