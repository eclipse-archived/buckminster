/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.team.psf;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>PSF</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.team.psf.PSF#getVersion <em>Version</em>}</li>
 * <li>{@link org.eclipse.buckminster.team.psf.PSF#getProviders <em>Providers
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.team.psf.PSF#getWorkingSets <em>Working
 * Sets</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.team.psf.PsfPackage#getPSF()
 * @model
 * @generated
 */
public interface PSF extends EObject {
	/**
	 * Returns the value of the '<em><b>Providers</b></em>' containment
	 * reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.team.psf.RepositoryProvider}. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Providers</em>' containment reference list
	 * isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Providers</em>' containment reference list.
	 * @see org.eclipse.buckminster.team.psf.PsfPackage#getPSF_Providers()
	 * @model containment="true"
	 *        extendedMetaData="name='provider' kind='element'"
	 * @generated
	 */
	EList<RepositoryProvider> getProviders();

	/**
	 * Returns the value of the '<em><b>Version</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Version</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Version</em>' attribute.
	 * @see #setVersion(String)
	 * @see org.eclipse.buckminster.team.psf.PsfPackage#getPSF_Version()
	 * @model required="true"
	 * @generated
	 */
	String getVersion();

	/**
	 * Returns the value of the '<em><b>Working Sets</b></em>' containment
	 * reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.team.psf.WorkingSet}. <!-- begin-user-doc
	 * -->
	 * <p>
	 * If the meaning of the '<em>Working Sets</em>' containment reference list
	 * isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Working Sets</em>' containment reference
	 *         list.
	 * @see org.eclipse.buckminster.team.psf.PsfPackage#getPSF_WorkingSets()
	 * @model containment="true" extendedMetaData="kind='element'"
	 * @generated
	 */
	EList<WorkingSet> getWorkingSets();

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.team.psf.PSF#getVersion <em>Version</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Version</em>' attribute.
	 * @see #getVersion()
	 * @generated
	 */
	void setVersion(String value);

} // PSF
