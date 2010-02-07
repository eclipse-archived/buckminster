/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspec;

import java.util.regex.Pattern;
import org.eclipse.buckminster.model.common.ComponentRequest;

import org.eclipse.buckminster.osgi.filter.Filter;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Prerequisite</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.cspec.Prerequisite#getComponent <em>
 * Component</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.Prerequisite#getAttribute <em>
 * Attribute</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.Prerequisite#getAlias <em>Alias
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.Prerequisite#isContributor <em>
 * Contributor</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.Prerequisite#isOptional <em>Optional
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.Prerequisite#getFilter <em>Filter
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.Prerequisite#getIncludePattern <em>
 * Include Pattern</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.Prerequisite#getExcludePattern <em>
 * Exclude Pattern</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.cspec.CspecPackage#getPrerequisite()
 * @model
 * @generated
 */
public interface Prerequisite extends EObject {
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
	 * @see org.eclipse.buckminster.cspec.CspecPackage#getPrerequisite_Alias()
	 * @model
	 * @generated
	 */
	String getAlias();

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
	 * @see org.eclipse.buckminster.cspec.CspecPackage#getPrerequisite_Attribute()
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
	 * @see org.eclipse.buckminster.cspec.CspecPackage#getPrerequisite_Component()
	 * @model
	 * @generated
	 */
	ComponentRequest getComponent();

	/**
	 * Returns the value of the '<em><b>Exclude Pattern</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Exclude Pattern</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Exclude Pattern</em>' attribute.
	 * @see #setExcludePattern(Pattern)
	 * @see org.eclipse.buckminster.cspec.CspecPackage#getPrerequisite_ExcludePattern()
	 * @model dataType="org.eclipse.buckminster.model.common.Pattern"
	 * @generated
	 */
	Pattern getExcludePattern();

	/**
	 * Returns the value of the '<em><b>Filter</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Filter</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Filter</em>' attribute.
	 * @see #setFilter(Filter)
	 * @see org.eclipse.buckminster.cspec.CspecPackage#getPrerequisite_Filter()
	 * @model dataType="org.eclipse.buckminster.model.common.Filter"
	 * @generated
	 */
	Filter getFilter();

	/**
	 * Returns the value of the '<em><b>Include Pattern</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Include Pattern</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Include Pattern</em>' attribute.
	 * @see #setIncludePattern(Pattern)
	 * @see org.eclipse.buckminster.cspec.CspecPackage#getPrerequisite_IncludePattern()
	 * @model dataType="org.eclipse.buckminster.model.common.Pattern"
	 * @generated
	 */
	Pattern getIncludePattern();

	/**
	 * Returns the value of the '<em><b>Contributor</b></em>' attribute. The
	 * default value is <code>"true"</code>. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contributor</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Contributor</em>' attribute.
	 * @see #setContributor(boolean)
	 * @see org.eclipse.buckminster.cspec.CspecPackage#getPrerequisite_Contributor()
	 * @model default="true"
	 * @generated
	 */
	boolean isContributor();

	/**
	 * Returns the value of the '<em><b>Optional</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Optional</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Optional</em>' attribute.
	 * @see #setOptional(boolean)
	 * @see org.eclipse.buckminster.cspec.CspecPackage#getPrerequisite_Optional()
	 * @model
	 * @generated
	 */
	boolean isOptional();

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.cspec.Prerequisite#getAlias
	 * <em>Alias</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Alias</em>' attribute.
	 * @see #getAlias()
	 * @generated
	 */
	void setAlias(String value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.cspec.Prerequisite#getAttribute
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
	 * {@link org.eclipse.buckminster.cspec.Prerequisite#getComponent
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
	 * {@link org.eclipse.buckminster.cspec.Prerequisite#isContributor
	 * <em>Contributor</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Contributor</em>' attribute.
	 * @see #isContributor()
	 * @generated
	 */
	void setContributor(boolean value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.cspec.Prerequisite#getExcludePattern
	 * <em>Exclude Pattern</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Exclude Pattern</em>' attribute.
	 * @see #getExcludePattern()
	 * @generated
	 */
	void setExcludePattern(Pattern value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.cspec.Prerequisite#getFilter
	 * <em>Filter</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @param value
	 *            the new value of the '<em>Filter</em>' attribute.
	 * @see #getFilter()
	 * @generated
	 */
	void setFilter(Filter value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.cspec.Prerequisite#getIncludePattern
	 * <em>Include Pattern</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Include Pattern</em>' attribute.
	 * @see #getIncludePattern()
	 * @generated
	 */
	void setIncludePattern(Pattern value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.cspec.Prerequisite#isOptional
	 * <em>Optional</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @param value
	 *            the new value of the '<em>Optional</em>' attribute.
	 * @see #isOptional()
	 * @generated
	 */
	void setOptional(boolean value);

} // Prerequisite
