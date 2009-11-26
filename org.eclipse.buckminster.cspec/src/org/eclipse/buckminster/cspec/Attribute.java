/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspec;

import org.eclipse.buckminster.model.common.Documentation;

import org.eclipse.buckminster.osgi.filter.Filter;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Attribute</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.cspec.Attribute#getName <em>Name</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.Attribute#isPublic <em>Public</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.Attribute#getFilter <em>Filter</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.Attribute#getCspec <em>Cspec</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.Attribute#getDocumentation <em>Documentation</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.cspec.CspecPackage#getAttribute()
 * @model abstract="true"
 * @generated
 */
public interface Attribute extends EObject
{
	/**
	 * Returns the value of the '<em><b>Cspec</b></em>' container reference. It is bidirectional and its opposite is '
	 * {@link org.eclipse.buckminster.cspec.CSpec#getAttributes <em>Attributes</em>}'. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cspec</em>' container reference isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Cspec</em>' container reference.
	 * @see #setCspec(CSpec)
	 * @see org.eclipse.buckminster.cspec.CspecPackage#getAttribute_Cspec()
	 * @see org.eclipse.buckminster.cspec.CSpec#getAttributes
	 * @model opposite="attributes" required="true" transient="false"
	 * @generated
	 */
	CSpec getCspec();

	/**
	 * Returns the value of the '<em><b>Documentation</b></em>' containment reference. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Documentation</em>' containment reference isn't clear, there really should be more of
	 * a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Documentation</em>' containment reference.
	 * @see #setDocumentation(Documentation)
	 * @see org.eclipse.buckminster.cspec.CspecPackage#getAttribute_Documentation()
	 * @model containment="true"
	 * @generated
	 */
	Documentation getDocumentation();

	/**
	 * Returns the value of the '<em><b>Filter</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Filter</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Filter</em>' attribute.
	 * @see #setFilter(Filter)
	 * @see org.eclipse.buckminster.cspec.CspecPackage#getAttribute_Filter()
	 * @model dataType="org.eclipse.buckminster.model.common.Filter"
	 * @generated
	 */
	Filter getFilter();

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.buckminster.cspec.CspecPackage#getAttribute_Name()
	 * @model id="true" required="true"
	 * @generated
	 */
	String getName();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model
	 * @generated
	 */
	boolean isDerived(IContext context);

	/**
	 * Returns the value of the '<em><b>Public</b></em>' attribute. The default value is <code>"true"</code>. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Public</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Public</em>' attribute.
	 * @see #setPublic(boolean)
	 * @see org.eclipse.buckminster.cspec.CspecPackage#getAttribute_Public()
	 * @model default="true"
	 * @generated
	 */
	boolean isPublic();

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.cspec.Attribute#getCspec <em>Cspec</em>}' container
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Cspec</em>' container reference.
	 * @see #getCspec()
	 * @generated
	 */
	void setCspec(CSpec value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.cspec.Attribute#getDocumentation <em>Documentation</em>}'
	 * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Documentation</em>' containment reference.
	 * @see #getDocumentation()
	 * @generated
	 */
	void setDocumentation(Documentation value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.cspec.Attribute#getFilter <em>Filter</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Filter</em>' attribute.
	 * @see #getFilter()
	 * @generated
	 */
	void setFilter(Filter value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.cspec.Attribute#getName <em>Name</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.cspec.Attribute#isPublic <em>Public</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Public</em>' attribute.
	 * @see #isPublic()
	 * @generated
	 */
	void setPublic(boolean value);

} // Attribute
