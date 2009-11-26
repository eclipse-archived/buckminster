/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspec;

import java.net.URL;

import org.eclipse.buckminster.model.common.ComponentIdentifier;
import org.eclipse.buckminster.model.common.ComponentRequest;
import org.eclipse.buckminster.model.common.Documentation;

import org.eclipse.buckminster.osgi.filter.Filter;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>CSpec</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.cspec.CSpec#getDependencies <em>Dependencies</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.CSpec#getGenerators <em>Generators</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.CSpec#getAttributes <em>Attributes</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.CSpec#getDocumentation <em>Documentation</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.CSpec#getShortDesc <em>Short Desc</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.CSpec#getFilter <em>Filter</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.CSpec#getProjectInfo <em>Project Info</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.cspec.CspecPackage#getCSpec()
 * @model
 * @generated
 */
public interface CSpec extends ComponentIdentifier
{
	/**
	 * Returns the value of the '<em><b>Attributes</b></em>' containment reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.cspec.Attribute}. It is bidirectional and its opposite is '
	 * {@link org.eclipse.buckminster.cspec.Attribute#getCspec <em>Cspec</em>}'. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attributes</em>' containment reference list isn't clear, there really should be more
	 * of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Attributes</em>' containment reference list.
	 * @see org.eclipse.buckminster.cspec.CspecPackage#getCSpec_Attributes()
	 * @see org.eclipse.buckminster.cspec.Attribute#getCspec
	 * @model opposite="cspec" containment="true"
	 * @generated
	 */
	EList<Attribute> getAttributes();

	/**
	 * Returns the value of the '<em><b>Dependencies</b></em>' containment reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.model.common.ComponentRequest}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dependencies</em>' containment reference list isn't clear, there really should be more
	 * of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Dependencies</em>' containment reference list.
	 * @see org.eclipse.buckminster.cspec.CspecPackage#getCSpec_Dependencies()
	 * @model containment="true"
	 * @generated
	 */
	EList<ComponentRequest> getDependencies();

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
	 * @see org.eclipse.buckminster.cspec.CspecPackage#getCSpec_Documentation()
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
	 * @see org.eclipse.buckminster.cspec.CspecPackage#getCSpec_Filter()
	 * @model dataType="org.eclipse.buckminster.model.common.Filter"
	 * @generated
	 */
	Filter getFilter();

	/**
	 * Returns the value of the '<em><b>Generators</b></em>' containment reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.cspec.Generator}. It is bidirectional and its opposite is '
	 * {@link org.eclipse.buckminster.cspec.Generator#getCspec <em>Cspec</em>}'. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Generators</em>' containment reference list isn't clear, there really should be more
	 * of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Generators</em>' containment reference list.
	 * @see org.eclipse.buckminster.cspec.CspecPackage#getCSpec_Generators()
	 * @see org.eclipse.buckminster.cspec.Generator#getCspec
	 * @model opposite="cspec" containment="true"
	 * @generated
	 */
	EList<Generator> getGenerators();

	/**
	 * Returns the value of the '<em><b>Project Info</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Project Info</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Project Info</em>' attribute.
	 * @see #setProjectInfo(URL)
	 * @see org.eclipse.buckminster.cspec.CspecPackage#getCSpec_ProjectInfo()
	 * @model dataType="org.eclipse.buckminster.model.common.URL"
	 * @generated
	 */
	URL getProjectInfo();

	/**
	 * Returns the value of the '<em><b>Short Desc</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Short Desc</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Short Desc</em>' attribute.
	 * @see #setShortDesc(String)
	 * @see org.eclipse.buckminster.cspec.CspecPackage#getCSpec_ShortDesc()
	 * @model
	 * @generated
	 */
	String getShortDesc();

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.cspec.CSpec#getDocumentation <em>Documentation</em>}'
	 * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Documentation</em>' containment reference.
	 * @see #getDocumentation()
	 * @generated
	 */
	void setDocumentation(Documentation value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.cspec.CSpec#getFilter <em>Filter</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Filter</em>' attribute.
	 * @see #getFilter()
	 * @generated
	 */
	void setFilter(Filter value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.cspec.CSpec#getProjectInfo <em>Project Info</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Project Info</em>' attribute.
	 * @see #getProjectInfo()
	 * @generated
	 */
	void setProjectInfo(URL value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.cspec.CSpec#getShortDesc <em>Short Desc</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Short Desc</em>' attribute.
	 * @see #getShortDesc()
	 * @generated
	 */
	void setShortDesc(String value);

} // CSpec
