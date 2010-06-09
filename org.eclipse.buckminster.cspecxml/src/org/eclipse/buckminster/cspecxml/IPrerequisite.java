/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspecxml;

import java.util.regex.Pattern;
import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Prerequisite</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.cspecxml.IPrerequisite#getAlias <em>Alias
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IPrerequisite#getComponent <em>
 * Component</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IPrerequisite#getComponentType
 * <em>Component Type</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IPrerequisite#isContributor <em>
 * Contributor</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IPrerequisite#getExcludePattern
 * <em>Exclude Pattern</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IPrerequisite#getFilter <em>
 * Filter</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IPrerequisite#getIncludePattern
 * <em>Include Pattern</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IPrerequisite#getName <em>Name
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IPrerequisite#isOptional <em>
 * Optional</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getPrerequisite()
 * @model extendedMetaData="name='Prerequisite' kind='empty'"
 * @generated
 */
public interface IPrerequisite extends EObject {
	/**
	 * Returns the value of the '<em><b>Alias</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
	 * 
	 * The name to use for properties and filesets that reflect this requirement
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Alias</em>' attribute.
	 * @see #setAlias(String)
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getPrerequisite_Alias()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='alias'"
	 * @generated
	 */
	String getAlias();

	/**
	 * Returns the value of the '<em><b>Component</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
	 * 
	 * The name of a dependency when this prerequisite extends beyond the cspec
	 * where it is declared.
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Component</em>' attribute.
	 * @see #setComponent(String)
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getPrerequisite_Component()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='component'"
	 * @generated
	 */
	String getComponent();

	/**
	 * Returns the value of the '<em><b>Component Type</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
	 * 
	 * The type of a dependency when this prerequisite extends beyond the cspec
	 * where it is declared.
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Component Type</em>' attribute.
	 * @see #setComponentType(String)
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getPrerequisite_ComponentType()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='componentType'"
	 * @generated
	 */
	String getComponentType();

	/**
	 * Returns the value of the '<em><b>Exclude Pattern</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
	 * 
	 * A regular expression controlling exclusion of component attributes
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Exclude Pattern</em>' attribute.
	 * @see #setExcludePattern(Pattern)
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getPrerequisite_ExcludePattern()
	 * @model dataType="org.eclipse.buckminster.model.common.Pattern"
	 *        extendedMetaData="kind='attribute' name='excludePattern'"
	 * @generated
	 */
	Pattern getExcludePattern();

	/**
	 * Returns the value of the '<em><b>Filter</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
	 * 
	 * An OSGi filter controlling inclusion of the prerequisite
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Filter</em>' attribute.
	 * @see #setFilter(Filter)
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getPrerequisite_Filter()
	 * @model dataType="org.eclipse.buckminster.model.common.Filter"
	 *        extendedMetaData="kind='attribute' name='filter'"
	 * @generated
	 */
	Filter getFilter();

	/**
	 * Returns the value of the '<em><b>Include Pattern</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
	 * 
	 * A regular expression controlling inclusion of component attributes
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Include Pattern</em>' attribute.
	 * @see #setIncludePattern(Pattern)
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getPrerequisite_IncludePattern()
	 * @model dataType="org.eclipse.buckminster.model.common.Pattern"
	 *        extendedMetaData="kind='attribute' name='includePattern'"
	 * @generated
	 */
	Pattern getIncludePattern();

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
	 * 
	 * The attribute name in the appointed component
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getPrerequisite_Name()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='name'"
	 * @generated
	 */
	String getName();

	/**
	 * Returns the value of the '<em><b>Contributor</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
	 * 
	 * Set to false if we only want to trigger the referenced attribute but have
	 * no interest in what it produces.
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Contributor</em>' attribute.
	 * @see #isSetContributor()
	 * @see #unsetContributor()
	 * @see #setContributor(boolean)
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getPrerequisite_Contributor()
	 * @model unsettable="true"
	 *        dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='contributor'"
	 * @generated
	 */
	boolean isContributor();

	/**
	 * Returns the value of the '<em><b>Optional</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
	 * 
	 * Deprecated and ignored.
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Optional</em>' attribute.
	 * @see #isSetOptional()
	 * @see #unsetOptional()
	 * @see #setOptional(boolean)
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getPrerequisite_Optional()
	 * @model unsettable="true"
	 *        dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='optional'"
	 * @generated
	 */
	boolean isOptional();

	/**
	 * Returns whether the value of the '
	 * {@link org.eclipse.buckminster.cspecxml.IPrerequisite#isContributor
	 * <em>Contributor</em>}' attribute is set. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return whether the value of the '<em>Contributor</em>' attribute is set.
	 * @see #unsetContributor()
	 * @see #isContributor()
	 * @see #setContributor(boolean)
	 * @generated
	 */
	boolean isSetContributor();

	/**
	 * Returns whether the value of the '
	 * {@link org.eclipse.buckminster.cspecxml.IPrerequisite#isOptional
	 * <em>Optional</em>}' attribute is set. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return whether the value of the '<em>Optional</em>' attribute is set.
	 * @see #unsetOptional()
	 * @see #isOptional()
	 * @see #setOptional(boolean)
	 * @generated
	 */
	boolean isSetOptional();

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.cspecxml.IPrerequisite#getAlias
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
	 * {@link org.eclipse.buckminster.cspecxml.IPrerequisite#getComponent
	 * <em>Component</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @param value
	 *            the new value of the '<em>Component</em>' attribute.
	 * @see #getComponent()
	 * @generated
	 */
	void setComponent(String value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.cspecxml.IPrerequisite#getComponentType
	 * <em>Component Type</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Component Type</em>' attribute.
	 * @see #getComponentType()
	 * @generated
	 */
	void setComponentType(String value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.cspecxml.IPrerequisite#isContributor
	 * <em>Contributor</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Contributor</em>' attribute.
	 * @see #isSetContributor()
	 * @see #unsetContributor()
	 * @see #isContributor()
	 * @generated
	 */
	void setContributor(boolean value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.cspecxml.IPrerequisite#getExcludePattern
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
	 * {@link org.eclipse.buckminster.cspecxml.IPrerequisite#getFilter
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
	 * {@link org.eclipse.buckminster.cspecxml.IPrerequisite#getIncludePattern
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
	 * {@link org.eclipse.buckminster.cspecxml.IPrerequisite#getName
	 * <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.cspecxml.IPrerequisite#isOptional
	 * <em>Optional</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @param value
	 *            the new value of the '<em>Optional</em>' attribute.
	 * @see #isSetOptional()
	 * @see #unsetOptional()
	 * @see #isOptional()
	 * @generated
	 */
	void setOptional(boolean value);

	/**
	 * Unsets the value of the '
	 * {@link org.eclipse.buckminster.cspecxml.IPrerequisite#isContributor
	 * <em>Contributor</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #isSetContributor()
	 * @see #isContributor()
	 * @see #setContributor(boolean)
	 * @generated
	 */
	void unsetContributor();

	/**
	 * Unsets the value of the '
	 * {@link org.eclipse.buckminster.cspecxml.IPrerequisite#isOptional
	 * <em>Optional</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #isSetOptional()
	 * @see #isOptional()
	 * @see #setOptional(boolean)
	 * @generated
	 */
	void unsetOptional();

} // IPrerequisite
