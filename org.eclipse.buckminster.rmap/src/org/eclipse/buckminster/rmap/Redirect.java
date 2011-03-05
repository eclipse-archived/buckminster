/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Redirect</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.rmap.Redirect#getRedirectTo <em>Redirect
 * To</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.rmap.RmapPackage#getRedirect()
 * @model
 * @generated
 */
public interface Redirect extends Matcher {
	/**
	 * Returns the value of the '<em><b>Href</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Href</em>' attribute isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Href</em>' attribute.
	 * @see #setRedirectTo(String)
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getRedirect_Href()
	 * @model required="true"
	 * @generated
	 */
	String getRedirectTo();

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.rmap.Redirect#getRedirectTo
	 * <em>Redirect To</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Redirect To</em>' attribute.
	 * @see #getRedirectTo()
	 * @generated
	 */
	void setRedirectTo(String value);

} // Redirect
