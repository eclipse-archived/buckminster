/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap.psf;

import org.eclipse.buckminster.rmap.Provider;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>PSF Provider</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.rmap.psf.PSFProvider#getPsfFile <em>Psf File</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.rmap.psf.PsfPackage#getPSFProvider()
 * @model
 * @generated
 */
public interface PSFProvider extends Provider
{
	/**
	 * Returns the value of the '<em><b>Psf File</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Psf File</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Psf File</em>' attribute.
	 * @see #setPsfFile(String)
	 * @see org.eclipse.buckminster.rmap.psf.PsfPackage#getPSFProvider_PsfFile()
	 * @model extendedMetaData="kind='attribute'"
	 * @generated
	 */
	String getPsfFile();

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.rmap.psf.PSFProvider#getPsfFile <em>Psf File</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Psf File</em>' attribute.
	 * @see #getPsfFile()
	 * @generated
	 */
	void setPsfFile(String value);

} // PSFProvider
