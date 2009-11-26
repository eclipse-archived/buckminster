/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspecxml;

import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Group</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.cspecxml.IGroup#getGroup <em>Group</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IGroup#getDefinitions <em>Definitions</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IGroup#getAttribute <em>Attribute</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IGroup#getFilter <em>Filter</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IGroup#getRebase <em>Rebase</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getGroup()
 * @model extendedMetaData="name='Group' kind='elementOnly'"
 * @generated
 */
public interface IGroup extends IAttribute
{
	/**
	 * Returns the value of the '<em><b>Attribute</b></em>' containment reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.cspecxml.IPrerequisite}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attribute</em>' containment reference list isn't clear, there really should be more of
	 * a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Attribute</em>' containment reference list.
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getGroup_Attribute()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='attribute' namespace='##targetNamespace' group='#group:4'"
	 * @generated
	 */
	EList<IPrerequisite> getAttribute();

	/**
	 * Returns the value of the '<em><b>Definitions</b></em>' containment reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.cspecxml.IDefinitions}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Definitions</em>' containment reference list isn't clear, there really should be more
	 * of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Definitions</em>' containment reference list.
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getGroup_Definitions()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='definitions' namespace='##targetNamespace' group='#group:4'"
	 * @generated
	 */
	EList<IDefinitions> getDefinitions();

	/**
	 * Returns the value of the '<em><b>Filter</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
	 * begin-model-doc -->
	 * 
	 * LDAP filter controlling if this group is enabled or disabled
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Filter</em>' attribute.
	 * @see #setFilter(Filter)
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getGroup_Filter()
	 * @model dataType="org.eclipse.buckminster.model.common.Filter" extendedMetaData="kind='attribute' name='filter'"
	 * @generated
	 */
	Filter getFilter();

	/**
	 * Returns the value of the '<em><b>Group</b></em>' attribute list. The list contents are of type
	 * {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Group</em>' attribute list isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Group</em>' attribute list.
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getGroup_Group()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='group:4'"
	 * @generated
	 */
	FeatureMap getGroup();

	/**
	 * Returns the value of the '<em><b>Rebase</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
	 * begin-model-doc -->
	 * 
	 * New base to use on all local paths where possible. Paths not parented by this base are left "as is", i.e. the
	 * path group they belong to will not change.
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Rebase</em>' attribute.
	 * @see #setRebase(String)
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getGroup_Rebase()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" extendedMetaData="kind='attribute' name='rebase'"
	 * @generated
	 */
	String getRebase();

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.cspecxml.IGroup#getFilter <em>Filter</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Filter</em>' attribute.
	 * @see #getFilter()
	 * @generated
	 */
	void setFilter(Filter value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.cspecxml.IGroup#getRebase <em>Rebase</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Rebase</em>' attribute.
	 * @see #getRebase()
	 * @generated
	 */
	void setRebase(String value);

} // IGroup
