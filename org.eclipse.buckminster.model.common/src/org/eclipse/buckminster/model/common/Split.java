/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.model.common;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Split</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.buckminster.model.common.Split#getLimit <em>Limit</em>}</li>
 *   <li>{@link org.eclipse.buckminster.model.common.Split#getPattern <em>Pattern</em>}</li>
 *   <li>{@link org.eclipse.buckminster.model.common.Split#getStyle <em>Style</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.buckminster.model.common.CommonPackage#getSplit()
 * @model
 * @generated
 */
public interface Split extends ValueFilter
{
	/**
	 * Returns the value of the '<em><b>Limit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Limit</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Limit</em>' attribute.
	 * @see #setLimit(int)
	 * @see org.eclipse.buckminster.model.common.CommonPackage#getSplit_Limit()
	 * @model extendedMetaData="kind='attribute'"
	 * @generated
	 */
	int getLimit();

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
	 * @see org.eclipse.buckminster.model.common.CommonPackage#getSplit_Pattern()
	 * @model required="true"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getPattern();

	/**
	 * Returns the value of the '<em><b>Style</b></em>' attribute.
	 * The default value is <code>"quoted"</code>.
	 * The literals are from the enumeration {@link org.eclipse.buckminster.model.common.SplitType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Style</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Style</em>' attribute.
	 * @see org.eclipse.buckminster.model.common.SplitType
	 * @see #isSetStyle()
	 * @see #unsetStyle()
	 * @see #setStyle(SplitType)
	 * @see org.eclipse.buckminster.model.common.CommonPackage#getSplit_Style()
	 * @model default="quoted" unsettable="true"
	 *        extendedMetaData="kind='attribute'"
	 * @generated
	 */
	SplitType getStyle();

	/**
	 * Returns whether the value of the '{@link org.eclipse.buckminster.model.common.Split#getStyle <em>Style</em>}' attribute is set.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return whether the value of the '<em>Style</em>' attribute is set.
	 * @see #unsetStyle()
	 * @see #getStyle()
	 * @see #setStyle(SplitType)
	 * @generated
	 */
	boolean isSetStyle();

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.model.common.Split#getLimit <em>Limit</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Limit</em>' attribute.
	 * @see #getLimit()
	 * @generated
	 */
	void setLimit(int value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.model.common.Split#getPattern <em>Pattern</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pattern</em>' attribute.
	 * @see #getPattern()
	 * @generated
	 */
	void setPattern(String value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.model.common.Split#getStyle <em>Style</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Style</em>' attribute.
	 * @see org.eclipse.buckminster.model.common.SplitType
	 * @see #isSetStyle()
	 * @see #unsetStyle()
	 * @see #getStyle()
	 * @generated
	 */
	void setStyle(SplitType value);

	/**
	 * Unsets the value of the '{@link org.eclipse.buckminster.model.common.Split#getStyle <em>Style</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isSetStyle()
	 * @see #getStyle()
	 * @see #setStyle(SplitType)
	 * @generated
	 */
	void unsetStyle();

} // Split
