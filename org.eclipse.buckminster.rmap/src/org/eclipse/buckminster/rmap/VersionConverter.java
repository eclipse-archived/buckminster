/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Version Converter</b></em>'. <!-- end-user-doc
 * -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.rmap.VersionConverter#getType <em>Type</em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.VersionConverter#getVersionType <em>Version Type</em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.VersionConverter#getTransformers <em>Transformers</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.rmap.RmapPackage#getVersionConverter()
 * @model
 * @generated
 */
public interface VersionConverter extends EObject
{
	/**
	 * Returns the value of the '<em><b>Transformers</b></em>' containment reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.rmap.Transform}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transformers</em>' containment reference list isn't clear, there really should be more
	 * of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Transformers</em>' containment reference list.
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getVersionConverter_Transformers()
	 * @model containment="true" extendedMetaData="name='transform' namespace='##targetNamespace' kind='element'"
	 * @generated
	 */
	EList<Transform> getTransformers();

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(String)
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getVersionConverter_Type()
	 * @model required="true"
	 * @generated
	 */
	String getType();

	/**
	 * Returns the value of the '<em><b>Version Type</b></em>' attribute. The default value is <code>"OSGi"</code>. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Version Type</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Version Type</em>' attribute.
	 * @see #setVersionType(String)
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getVersionConverter_VersionType()
	 * @model default="OSGi"
	 * @generated
	 */
	String getVersionType();

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.rmap.VersionConverter#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.rmap.VersionConverter#getVersionType <em>Version Type</em>}
	 * ' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Version Type</em>' attribute.
	 * @see #getVersionType()
	 * @generated
	 */
	void setVersionType(String value);

} // VersionConverter
