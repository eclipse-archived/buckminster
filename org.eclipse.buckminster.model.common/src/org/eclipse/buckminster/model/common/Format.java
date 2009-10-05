/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.model.common;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Format</b></em>'. <!-- end-user-doc -->
 * 
 * <!-- begin-model-doc --> A Format is the base for a java.text.Format that in turn is used when creating a string from
 * a set of parameter values. A parameter is denoted by {&lt;n&gt;} where &lt;n&gt; denotes n'th parameter element using
 * zero for the first parameter. <!-- end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.model.common.Format#getFormat <em>Format</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.model.common.CommonPackage#getFormat()
 * @model
 * @generated
 */
public interface Format extends ValueFilter
{

	/**
	 * Returns the value of the '<em><b>Format</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Format</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Format</em>' attribute.
	 * @see #setFormat(String)
	 * @see org.eclipse.buckminster.model.common.CommonPackage#getFormat_Format()
	 * @model required="true" extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getFormat();

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.model.common.Format#getFormat <em>Format</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Format</em>' attribute.
	 * @see #getFormat()
	 * @generated
	 */
	void setFormat(String value);
} // Format
