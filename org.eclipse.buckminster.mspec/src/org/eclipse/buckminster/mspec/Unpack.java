/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.mspec;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Unpack</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.mspec.Unpack#isExpand <em>Expand</em>}</li>
 * <li>{@link org.eclipse.buckminster.mspec.Unpack#getSuffix <em>Suffix</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.mspec.MspecPackage#getUnpack()
 * @model
 * @generated
 */
public interface Unpack extends EObject
{
	/**
	 * Returns the value of the '<em><b>Suffix</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
	 * begin-model-doc --> The suffix to be assumed when a suffix cannot be derived from the resource locator appoints
	 * the remote resource. This suffix controls the choice of unpacker. <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Suffix</em>' attribute.
	 * @see #setSuffix(String)
	 * @see org.eclipse.buckminster.mspec.MspecPackage#getUnpack_Suffix()
	 * @model
	 * @generated
	 */
	String getSuffix();

	/**
	 * Returns the value of the '<em><b>Expand</b></em>' attribute. The default value is <code>"true"</code>. <!--
	 * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> Indicates that the materializer should expand
	 * the artifact as part of the unpack. <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Expand</em>' attribute.
	 * @see #setExpand(boolean)
	 * @see org.eclipse.buckminster.mspec.MspecPackage#getUnpack_Expand()
	 * @model default="true"
	 * @generated
	 */
	boolean isExpand();

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.mspec.Unpack#isExpand <em>Expand</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Expand</em>' attribute.
	 * @see #isExpand()
	 * @generated
	 */
	void setExpand(boolean value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.mspec.Unpack#getSuffix <em>Suffix</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Suffix</em>' attribute.
	 * @see #getSuffix()
	 * @generated
	 */
	void setSuffix(String value);

} // Unpack
