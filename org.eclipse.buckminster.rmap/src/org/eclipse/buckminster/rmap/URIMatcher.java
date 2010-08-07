/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap;

import org.eclipse.buckminster.model.common.RxAssembly;
import org.eclipse.equinox.p2.metadata.IVersionFormat;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>URI Matcher</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.buckminster.rmap.URIMatcher#getBase <em>Base</em>}</li>
 *   <li>{@link org.eclipse.buckminster.rmap.URIMatcher#getVersionFormat <em>Version Format</em>}</li>
 *   <li>{@link org.eclipse.buckminster.rmap.URIMatcher#getVersionType <em>Version Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.buckminster.rmap.RmapPackage#getURIMatcher()
 * @model
 * @generated
 */
public interface URIMatcher extends RxAssembly {
	/**
	 * Returns the value of the '<em><b>Base</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Base</em>' attribute isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Base</em>' attribute.
	 * @see #setBase(String)
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getURIMatcher_Base()
	 * @model extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getBase();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	String getComponentType();

	/**
	 * Returns the value of the '<em><b>Version Format</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Version Format</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Version Format</em>' attribute.
	 * @see #setVersionFormat(String)
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getURIMatcher_VersionFormat()
	 * @model
	 * @generated
	 */
	IVersionFormat getVersionFormat();

	/**
	 * Returns the value of the '<em><b>Version Type</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Version Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Version Type</em>' attribute.
	 * @see #setVersionType(String)
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getURIMatcher_VersionType()
	 * @model
	 * @generated
	 */
	String getVersionType();

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.rmap.URIMatcher#getBase <em>Base</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Base</em>' attribute.
	 * @see #getBase()
	 * @generated
	 */
	void setBase(String value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.rmap.URIMatcher#getVersionFormat <em>Version Format</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @param value the new value of the '<em>Version Format</em>' attribute.
	 * @see #getVersionFormat()
	 * @generated
	 */
	void setVersionFormat(IVersionFormat value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.rmap.URIMatcher#getVersionType <em>Version Type</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @param value the new value of the '<em>Version Type</em>' attribute.
	 * @see #getVersionType()
	 * @generated
	 */
	void setVersionType(String value);

} // URIMatcher
