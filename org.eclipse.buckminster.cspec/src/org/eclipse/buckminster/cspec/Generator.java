/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspec;

import org.eclipse.buckminster.model.common.ComponentIdentifier;
import org.eclipse.buckminster.model.common.ComponentRequest;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Generator</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.cspec.Generator#getAttribute <em>Attribute
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.Generator#getComponent <em>Component
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.Generator#getGenerates <em>Generates
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.Generator#getCspec <em>Cspec</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.cspec.CspecPackage#getGenerator()
 * @model
 * @generated
 */
public interface Generator extends EObject {
	/**
	 * Returns the value of the '<em><b>Attribute</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attribute</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Attribute</em>' attribute.
	 * @see #setAttribute(String)
	 * @see org.eclipse.buckminster.cspec.CspecPackage#getGenerator_Attribute()
	 * @model required="true"
	 * @generated
	 */
	String getAttribute();

	/**
	 * Returns the value of the '<em><b>Component</b></em>' reference. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Component</em>' reference isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Component</em>' reference.
	 * @see #setComponent(ComponentRequest)
	 * @see org.eclipse.buckminster.cspec.CspecPackage#getGenerator_Component()
	 * @model
	 * @generated
	 */
	ComponentRequest getComponent();

	/**
	 * Returns the value of the '<em><b>Cspec</b></em>' container reference. It
	 * is bidirectional and its opposite is '
	 * {@link org.eclipse.buckminster.cspec.CSpec#getGenerators
	 * <em>Generators</em>}'. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cspec</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Cspec</em>' container reference.
	 * @see #setCspec(CSpec)
	 * @see org.eclipse.buckminster.cspec.CspecPackage#getGenerator_Cspec()
	 * @see org.eclipse.buckminster.cspec.CSpec#getGenerators
	 * @model opposite="generators" required="true" transient="false"
	 * @generated
	 */
	CSpec getCspec();

	/**
	 * Returns the value of the '<em><b>Generates</b></em>' containment
	 * reference. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Generates</em>' containment reference isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Generates</em>' containment reference.
	 * @see #setGenerates(ComponentIdentifier)
	 * @see org.eclipse.buckminster.cspec.CspecPackage#getGenerator_Generates()
	 * @model containment="true" required="true"
	 * @generated
	 */
	ComponentIdentifier getGenerates();

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.cspec.Generator#getAttribute
	 * <em>Attribute</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @param value
	 *            the new value of the '<em>Attribute</em>' attribute.
	 * @see #getAttribute()
	 * @generated
	 */
	void setAttribute(String value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.cspec.Generator#getComponent
	 * <em>Component</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @param value
	 *            the new value of the '<em>Component</em>' reference.
	 * @see #getComponent()
	 * @generated
	 */
	void setComponent(ComponentRequest value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.cspec.Generator#getCspec <em>Cspec</em>}'
	 * container reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Cspec</em>' container reference.
	 * @see #getCspec()
	 * @generated
	 */
	void setCspec(CSpec value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.cspec.Generator#getGenerates
	 * <em>Generates</em>}' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Generates</em>' containment
	 *            reference.
	 * @see #getGenerates()
	 * @generated
	 */
	void setGenerates(ComponentIdentifier value);

} // Generator
