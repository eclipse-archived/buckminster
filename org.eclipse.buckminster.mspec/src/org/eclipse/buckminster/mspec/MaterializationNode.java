/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.mspec;

import java.util.regex.Pattern;
import org.eclipse.buckminster.osgi.filter.Filter;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Materialization Node</b></em>'. <!--
 * end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.mspec.MaterializationNode#getNamePattern <em>Name Pattern</em>}</li>
 * <li>{@link org.eclipse.buckminster.mspec.MaterializationNode#getLeafArtifact <em>Leaf Artifact</em>}</li>
 * <li>{@link org.eclipse.buckminster.mspec.MaterializationNode#getComponentType <em>Component Type</em>}</li>
 * <li>{@link org.eclipse.buckminster.mspec.MaterializationNode#getResourcePath <em>Resource Path</em>}</li>
 * <li>{@link org.eclipse.buckminster.mspec.MaterializationNode#isExclude <em>Exclude</em>}</li>
 * <li>{@link org.eclipse.buckminster.mspec.MaterializationNode#getBindingNamePattern <em>Binding Name Pattern</em>}</li>
 * <li>{@link org.eclipse.buckminster.mspec.MaterializationNode#getBindingNameReplacement <em>Binding Name Replacement
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.mspec.MaterializationNode#getUnpack <em>Unpack</em>}</li>
 * <li>{@link org.eclipse.buckminster.mspec.MaterializationNode#getFilter <em>Filter</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.mspec.MspecPackage#getMaterializationNode()
 * @model
 * @generated
 */
public interface MaterializationNode extends MaterializationDirective
{
	/**
	 * Returns the value of the '<em><b>Binding Name Pattern</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Binding Name Pattern</em>' attribute isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Binding Name Pattern</em>' attribute.
	 * @see #setBindingNamePattern(Pattern)
	 * @see org.eclipse.buckminster.mspec.MspecPackage#getMaterializationNode_BindingNamePattern()
	 * @model dataType="org.eclipse.buckminster.model.common.Pattern"
	 * @generated
	 */
	Pattern getBindingNamePattern();

	/**
	 * Returns the value of the '<em><b>Binding Name Replacement</b></em>' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc --> <!-- begin-model-doc --> A replacement string that produces the resulting project name from a
	 * name matched by pattern bindingNamePattern. <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Binding Name Replacement</em>' attribute.
	 * @see #setBindingNameReplacement(String)
	 * @see org.eclipse.buckminster.mspec.MspecPackage#getMaterializationNode_BindingNameReplacement()
	 * @model
	 * @generated
	 */
	String getBindingNameReplacement();

	/**
	 * Returns the value of the '<em><b>Component Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * --> <!-- begin-model-doc --> If set, this node will only match components of this type. <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Component Type</em>' attribute.
	 * @see #setComponentType(String)
	 * @see org.eclipse.buckminster.mspec.MspecPackage#getMaterializationNode_ComponentType()
	 * @model
	 * @generated
	 */
	String getComponentType();

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
	 * @see org.eclipse.buckminster.mspec.MspecPackage#getMaterializationNode_Filter()
	 * @model dataType="org.eclipse.buckminster.model.common.Filter"
	 * @generated
	 */
	Filter getFilter();

	/**
	 * Returns the value of the '<em><b>Leaf Artifact</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * <!-- begin-model-doc --> The name of the file system artifact (file or folder) to materialize. This name is
	 * optional and normally derived from the remote location. <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Leaf Artifact</em>' attribute.
	 * @see #setLeafArtifact(String)
	 * @see org.eclipse.buckminster.mspec.MspecPackage#getMaterializationNode_LeafArtifact()
	 * @model
	 * @generated
	 */
	String getLeafArtifact();

	/**
	 * Returns the value of the '<em><b>Name Pattern</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * <!-- begin-model-doc --> A regular expression pattern that will be matched against component names. The matching
	 * occurs in the order that the nodes are declared. The first node that matches a given component is used. No
	 * further matching takes place once a node is found. <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Name Pattern</em>' attribute.
	 * @see #setNamePattern(Pattern)
	 * @see org.eclipse.buckminster.mspec.MspecPackage#getMaterializationNode_NamePattern()
	 * @model dataType="org.eclipse.buckminster.model.common.Pattern" required="true"
	 * @generated
	 */
	Pattern getNamePattern();

	/**
	 * Returns the value of the '<em><b>Resource Path</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * <!-- begin-model-doc --> Path to where a match component will end up in the workspace. Only used by the workspace
	 * materializer. Typically used when resolving conflicts between equally named features and bundles. <!--
	 * end-model-doc -->
	 * 
	 * @return the value of the '<em>Resource Path</em>' attribute.
	 * @see #setResourcePath(String)
	 * @see org.eclipse.buckminster.mspec.MspecPackage#getMaterializationNode_ResourcePath()
	 * @model
	 * @generated
	 */
	String getResourcePath();

	/**
	 * Returns the value of the '<em><b>Unpack</b></em>' containment reference. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Unpack</em>' reference isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Unpack</em>' containment reference.
	 * @see #setUnpack(Unpack)
	 * @see org.eclipse.buckminster.mspec.MspecPackage#getMaterializationNode_Unpack()
	 * @model containment="true" extendedMetaData="kind='element' name='unpack' namespace='##targetNamespace'"
	 * @generated
	 */
	Unpack getUnpack();

	/**
	 * Returns the value of the '<em><b>Exclude</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
	 * begin-model-doc --> If set, matched components will be excluded from materialization. <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Exclude</em>' attribute.
	 * @see #setExclude(boolean)
	 * @see org.eclipse.buckminster.mspec.MspecPackage#getMaterializationNode_Exclude()
	 * @model
	 * @generated
	 */
	boolean isExclude();

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.mspec.MaterializationNode#getBindingNamePattern
	 * <em>Binding Name Pattern</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Binding Name Pattern</em>' attribute.
	 * @see #getBindingNamePattern()
	 * @generated
	 */
	void setBindingNamePattern(Pattern value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.mspec.MaterializationNode#getBindingNameReplacement
	 * <em>Binding Name Replacement</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Binding Name Replacement</em>' attribute.
	 * @see #getBindingNameReplacement()
	 * @generated
	 */
	void setBindingNameReplacement(String value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.mspec.MaterializationNode#getComponentType
	 * <em>Component Type</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Component Type</em>' attribute.
	 * @see #getComponentType()
	 * @generated
	 */
	void setComponentType(String value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.mspec.MaterializationNode#isExclude <em>Exclude</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Exclude</em>' attribute.
	 * @see #isExclude()
	 * @generated
	 */
	void setExclude(boolean value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.mspec.MaterializationNode#getFilter <em>Filter</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Filter</em>' attribute.
	 * @see #getFilter()
	 * @generated
	 */
	void setFilter(Filter value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.mspec.MaterializationNode#getLeafArtifact
	 * <em>Leaf Artifact</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Leaf Artifact</em>' attribute.
	 * @see #getLeafArtifact()
	 * @generated
	 */
	void setLeafArtifact(String value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.mspec.MaterializationNode#getNamePattern
	 * <em>Name Pattern</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Name Pattern</em>' attribute.
	 * @see #getNamePattern()
	 * @generated
	 */
	void setNamePattern(Pattern value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.mspec.MaterializationNode#getResourcePath
	 * <em>Resource Path</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Resource Path</em>' attribute.
	 * @see #getResourcePath()
	 * @generated
	 */
	void setResourcePath(String value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.mspec.MaterializationNode#getUnpack <em>Unpack</em>}'
	 * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Unpack</em>' containment reference.
	 * @see #getUnpack()
	 * @generated
	 */
	void setUnpack(Unpack value);

} // MaterializationNode
