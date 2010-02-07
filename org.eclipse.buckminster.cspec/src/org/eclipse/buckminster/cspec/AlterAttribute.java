/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspec;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Alter Attribute</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.cspec.AlterAttribute#getCspecext <em>
 * Cspecext</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.cspec.CspecPackage#getAlterAttribute()
 * @model abstract="true"
 * @generated
 */
public interface AlterAttribute extends EObject {
	/**
	 * Returns the value of the '<em><b>Cspecext</b></em>' container reference.
	 * It is bidirectional and its opposite is '
	 * {@link org.eclipse.buckminster.cspec.CSpecExtension#getAttributeAlterations
	 * <em>Attribute Alterations</em>}'. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cspecext</em>' container reference isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Cspecext</em>' container reference.
	 * @see #setCspecext(CSpecExtension)
	 * @see org.eclipse.buckminster.cspec.CspecPackage#getAlterAttribute_Cspecext()
	 * @see org.eclipse.buckminster.cspec.CSpecExtension#getAttributeAlterations
	 * @model opposite="attributeAlterations" required="true" transient="false"
	 * @generated
	 */
	CSpecExtension getCspecext();

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.cspec.AlterAttribute#getCspecext
	 * <em>Cspecext</em>}' container reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Cspecext</em>' container reference.
	 * @see #getCspecext()
	 * @generated
	 */
	void setCspecext(CSpecExtension value);

} // AlterAttribute
