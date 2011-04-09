/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap.pde;

import org.eclipse.buckminster.model.common.Replace;
import org.eclipse.buckminster.rmap.Provider;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>PDE Map Provider</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.rmap.pde.PDEMapProvider#getReplace <em>
 * Replace</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.rmap.pde.PdePackage#getPDEMapProvider()
 * @model
 * @generated
 */
public interface PDEMapProvider extends Provider {

	/**
	 * Returns the value of the '<em><b>Replace</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Replace</em>' containment reference list isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Replace</em>' containment reference.
	 * @see #setReplace(Replace)
	 * @see org.eclipse.buckminster.rmap.pde.PdePackage#getPDEMapProvider_Replace()
	 * @model containment="true" extendedMetaData=
	 *        "name='replace' kind='element' namespace='##targetNamespace'"
	 * @generated
	 */
	Replace getReplace();

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.rmap.pde.PDEMapProvider#getReplace
	 * <em>Replace</em>}' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Replace</em>' containment reference.
	 * @see #getReplace()
	 * @generated
	 */
	void setReplace(Replace value);
} // PDEMapProvider
