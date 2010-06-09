/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspecxml;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Action Artifact</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.cspecxml.IActionArtifact#getAlias <em>
 * Alias</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getActionArtifact()
 * @model extendedMetaData="name='ActionArtifact' kind='elementOnly'"
 * @generated
 */
public interface IActionArtifact extends IArtifact {
	/**
	 * Returns the value of the '<em><b>Alias</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Alias</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Alias</em>' attribute.
	 * @see #setAlias(String)
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getActionArtifact_Alias()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='alias'"
	 * @generated
	 */
	String getAlias();

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.cspecxml.IActionArtifact#getAlias
	 * <em>Alias</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Alias</em>' attribute.
	 * @see #getAlias()
	 * @generated
	 */
	void setAlias(String value);

} // IActionArtifact
