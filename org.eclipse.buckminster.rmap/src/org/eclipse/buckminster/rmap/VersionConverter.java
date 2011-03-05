/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.equinox.p2.metadata.IVersionFormat;
import org.eclipse.equinox.p2.metadata.Version;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Version Converter</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.rmap.VersionConverter#getType <em>Type
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.VersionConverter#getTransformers <em>
 * Transformers</em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.VersionConverter#getVersionFormat
 * <em>Version Format</em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.VersionConverter#getVersionType <em>
 * Version Type</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.rmap.RmapPackage#getVersionConverter()
 * @model
 * @generated
 */
public interface VersionConverter extends EObject {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model versionDataType="org.eclipse.buckminster.model.common.Version"
	 * @generated
	 */
	String createIdentifier(Version version);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model dataType="org.eclipse.buckminster.model.common.Version"
	 * @generated
	 */
	Version createVersion(String identifier);

	/**
	 * Returns the value of the '<em><b>Transformers</b></em>' containment
	 * reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.rmap.Transform}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transformers</em>' containment reference list
	 * isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Transformers</em>' containment reference
	 *         list.
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getVersionConverter_Transformers()
	 * @model containment="true" extendedMetaData=
	 *        "name='transform' namespace='##targetNamespace' kind='element'"
	 * @generated
	 */
	EList<Transform> getTransformers();

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(String)
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getVersionConverter_Type()
	 * @model required="true"
	 * @generated
	 */
	VersionSelectorType getType();

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
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getVersionConverter_VersionFormat()
	 * @model
	 * @generated
	 */
	IVersionFormat getVersionFormat();

	/**
	 * Returns the value of the '<em><b>Version Type</b></em>' attribute. The
	 * default value is <code>"OSGi"</code>. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Version Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
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
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.rmap.VersionConverter#getType
	 * <em>Type</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Type</em>' attribute.
	 * @see org.eclipse.buckminster.rmap.VersionSelectorType
	 * @see #getType()
	 * @generated
	 */
	void setType(VersionSelectorType value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.rmap.VersionConverter#getVersionFormat
	 * <em>Version Format</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Version Format</em>' attribute.
	 * @see #getVersionFormat()
	 * @generated
	 */
	void setVersionFormat(IVersionFormat value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.rmap.VersionConverter#getVersionType
	 * <em>Version Type</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Version Type</em>' attribute.
	 * @see #getVersionType()
	 * @generated
	 */
	void setVersionType(String value);

} // VersionConverter
