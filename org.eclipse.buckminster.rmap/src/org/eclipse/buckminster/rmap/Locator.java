/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Locator</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.rmap.Locator#getSearchPath <em>Search Path
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.Locator#isFailOnError <em>Fail On
 * Error</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.rmap.RmapPackage#getLocator()
 * @model
 * @generated
 */
public interface Locator extends Matcher {
	/**
	 * Returns the value of the '<em><b>Search Path</b></em>' reference. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Search Path</em>' reference isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Search Path</em>' reference.
	 * @see #setSearchPath(SearchPath)
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getLocator_SearchPath()
	 * @model resolveProxies="false" required="true"
	 *        extendedMetaData="name='searchPathRef' kind='attribute'"
	 * @generated
	 */
	SearchPath getSearchPath();

	/**
	 * Returns the value of the '<em><b>Fail On Error</b></em>' attribute. The
	 * default value is <code>"true"</code>. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fail On Error</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Fail On Error</em>' attribute.
	 * @see #setFailOnError(boolean)
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getLocator_FailOnError()
	 * @model default="true"
	 * @generated
	 */
	boolean isFailOnError();

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.rmap.Locator#isFailOnError
	 * <em>Fail On Error</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Fail On Error</em>' attribute.
	 * @see #isFailOnError()
	 * @generated
	 */
	void setFailOnError(boolean value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.rmap.Locator#getSearchPath
	 * <em>Search Path</em>}' reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Search Path</em>' reference.
	 * @see #getSearchPath()
	 * @generated
	 */
	void setSearchPath(SearchPath value);

} // Locator
