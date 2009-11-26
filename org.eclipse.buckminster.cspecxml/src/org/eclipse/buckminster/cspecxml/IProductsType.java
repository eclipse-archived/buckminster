/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspecxml;

import java.util.regex.Pattern;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Products Type</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.cspecxml.IProductsType#getGroup <em>Group</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IProductsType#getPath <em>Path</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IProductsType#getPublic <em>Public</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IProductsType#getPrivate <em>Private</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IProductsType#getAlias <em>Alias</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IProductsType#getBase <em>Base</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IProductsType#getFileCount <em>File Count</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IProductsType#getPattern <em>Pattern</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IProductsType#getReplacement <em>Replacement</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.IProductsType#getUpToDatePolicy <em>Up To Date Policy</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getProductsType()
 * @model extendedMetaData="name='products_._type' kind='elementOnly'"
 * @generated
 */
public interface IProductsType extends EObject
{
	/**
	 * Returns the value of the '<em><b>Alias</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
	 * begin-model-doc -->
	 * 
	 * The name to use for properties and filesets that reflect the product
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Alias</em>' attribute.
	 * @see #setAlias(String)
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getProductsType_Alias()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" extendedMetaData="kind='attribute' name='alias'"
	 * @generated
	 */
	String getAlias();

	/**
	 * Returns the value of the '<em><b>Base</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
	 * begin-model-doc -->
	 * 
	 * Base of all paths, relative to component root
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Base</em>' attribute.
	 * @see #setBase(String)
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getProductsType_Base()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" extendedMetaData="kind='attribute' name='base'"
	 * @generated
	 */
	String getBase();

	/**
	 * Returns the value of the '<em><b>File Count</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>File Count</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>File Count</em>' attribute.
	 * @see #setFileCount(int)
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getProductsType_FileCount()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Int" extendedMetaData="kind='attribute' name='fileCount'"
	 * @generated
	 */
	int getFileCount();

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
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getProductsType_Group()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='group:0'"
	 * @generated
	 */
	FeatureMap getGroup();

	/**
	 * Returns the value of the '<em><b>Path</b></em>' containment reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.cspecxml.IPath}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Path</em>' containment reference list isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Path</em>' containment reference list.
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getProductsType_Path()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='path' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<IPath> getPath();

	/**
	 * Returns the value of the '<em><b>Pattern</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pattern</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Pattern</em>' attribute.
	 * @see #setPattern(Pattern)
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getProductsType_Pattern()
	 * @model dataType="org.eclipse.buckminster.model.common.Pattern" extendedMetaData="kind='attribute' name='pattern'"
	 * @generated
	 */
	Pattern getPattern();

	/**
	 * Returns the value of the '<em><b>Private</b></em>' containment reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.cspecxml.IActionArtifact}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Private</em>' containment reference list isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Private</em>' containment reference list.
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getProductsType_Private()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='private' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<IActionArtifact> getPrivate();

	/**
	 * Returns the value of the '<em><b>Public</b></em>' containment reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.cspecxml.IActionArtifact}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Public</em>' containment reference list isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Public</em>' containment reference list.
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getProductsType_Public()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='public' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<IActionArtifact> getPublic();

	/**
	 * Returns the value of the '<em><b>Replacement</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Replacement</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Replacement</em>' attribute.
	 * @see #setReplacement(String)
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getProductsType_Replacement()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" extendedMetaData="kind='attribute' name='replacement'"
	 * @generated
	 */
	String getReplacement();

	/**
	 * Returns the value of the '<em><b>Up To Date Policy</b></em>' attribute. The literals are from the enumeration
	 * {@link org.eclipse.buckminster.cspecxml.UpToDatePolicy}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Up To Date Policy</em>' attribute isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Up To Date Policy</em>' attribute.
	 * @see org.eclipse.buckminster.cspecxml.UpToDatePolicy
	 * @see #isSetUpToDatePolicy()
	 * @see #unsetUpToDatePolicy()
	 * @see #setUpToDatePolicy(UpToDatePolicy)
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#getProductsType_UpToDatePolicy()
	 * @model unsettable="true" extendedMetaData="kind='attribute' name='upToDatePolicy'"
	 * @generated
	 */
	UpToDatePolicy getUpToDatePolicy();

	/**
	 * Returns whether the value of the '{@link org.eclipse.buckminster.cspecxml.IProductsType#getUpToDatePolicy
	 * <em>Up To Date Policy</em>}' attribute is set. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return whether the value of the '<em>Up To Date Policy</em>' attribute is set.
	 * @see #unsetUpToDatePolicy()
	 * @see #getUpToDatePolicy()
	 * @see #setUpToDatePolicy(UpToDatePolicy)
	 * @generated
	 */
	boolean isSetUpToDatePolicy();

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.cspecxml.IProductsType#getAlias <em>Alias</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Alias</em>' attribute.
	 * @see #getAlias()
	 * @generated
	 */
	void setAlias(String value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.cspecxml.IProductsType#getBase <em>Base</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Base</em>' attribute.
	 * @see #getBase()
	 * @generated
	 */
	void setBase(String value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.cspecxml.IProductsType#getFileCount <em>File Count</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>File Count</em>' attribute.
	 * @see #getFileCount()
	 * @generated
	 */
	void setFileCount(int value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.cspecxml.IProductsType#getPattern <em>Pattern</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Pattern</em>' attribute.
	 * @see #getPattern()
	 * @generated
	 */
	void setPattern(Pattern value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.cspecxml.IProductsType#getReplacement <em>Replacement</em>}
	 * ' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Replacement</em>' attribute.
	 * @see #getReplacement()
	 * @generated
	 */
	void setReplacement(String value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.cspecxml.IProductsType#getUpToDatePolicy
	 * <em>Up To Date Policy</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Up To Date Policy</em>' attribute.
	 * @see org.eclipse.buckminster.cspecxml.UpToDatePolicy
	 * @see #isSetUpToDatePolicy()
	 * @see #unsetUpToDatePolicy()
	 * @see #getUpToDatePolicy()
	 * @generated
	 */
	void setUpToDatePolicy(UpToDatePolicy value);

	/**
	 * Unsets the value of the '{@link org.eclipse.buckminster.cspecxml.IProductsType#getUpToDatePolicy
	 * <em>Up To Date Policy</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isSetUpToDatePolicy()
	 * @see #getUpToDatePolicy()
	 * @see #setUpToDatePolicy(UpToDatePolicy)
	 * @generated
	 */
	void unsetUpToDatePolicy();

} // IProductsType
