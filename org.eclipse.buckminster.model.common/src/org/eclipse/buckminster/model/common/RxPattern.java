/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.model.common;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Rx Pattern</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Defines a partial regexp as a pattern
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.buckminster.model.common.RxPattern#getPattern <em>Pattern</em>}</li>
 *   <li>{@link org.eclipse.buckminster.model.common.RxPattern#getPrefix <em>Prefix</em>}</li>
 *   <li>{@link org.eclipse.buckminster.model.common.RxPattern#getSuffix <em>Suffix</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.buckminster.model.common.CommonPackage#getRxPattern()
 * @model
 * @generated
 */
public interface RxPattern extends RxPart
{
	/**
	 * Returns the value of the '<em><b>Pattern</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
	 * begin-model-doc --> The regular expression for this group (excluding parenthesis for the group itself). <!--
	 * end-model-doc -->
	 * 
	 * @return the value of the '<em>Pattern</em>' attribute.
	 * @see #setPattern(String)
	 * @see org.eclipse.buckminster.model.common.CommonPackage#getRxPattern_Pattern()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true" extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getPattern();

	/**
	 * Returns the value of the '<em><b>Prefix</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
	 * begin-model-doc --> The quoted prefix of the pattern. Not included in the capture <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Prefix</em>' attribute.
	 * @see #setPrefix(String)
	 * @see org.eclipse.buckminster.model.common.CommonPackage#getRxPattern_Prefix()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getPrefix();

	/**
	 * Returns the value of the '<em><b>Suffix</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
	 * begin-model-doc --> The quoted suffix of the pattern. Not included in the capture <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Suffix</em>' attribute.
	 * @see #setSuffix(String)
	 * @see org.eclipse.buckminster.model.common.CommonPackage#getRxPattern_Suffix()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getSuffix();

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.model.common.RxPattern#getPattern <em>Pattern</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pattern</em>' attribute.
	 * @see #getPattern()
	 * @generated
	 */
	void setPattern(String value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.model.common.RxPattern#getPrefix <em>Prefix</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Prefix</em>' attribute.
	 * @see #getPrefix()
	 * @generated
	 */
	void setPrefix(String value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.model.common.RxPattern#getSuffix <em>Suffix</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Suffix</em>' attribute.
	 * @see #getSuffix()
	 * @generated
	 */
	void setSuffix(String value);

} // RxPattern
