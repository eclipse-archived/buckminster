/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspec;

import java.util.regex.Pattern;
import org.eclipse.buckminster.model.common.PropertyConstant;

import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Action</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.cspec.Action#getProperties <em>Properties
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.Action#getActorProperties <em>Actor
 * Properties</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.Action#getActor <em>Actor</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.Action#getProduct <em>Product</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.Action#getProducts <em>Products
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.Action#getPrerequisitesAlias <em>
 * Prerequisites Alias</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.Action#getPrerequisitesRebase <em>
 * Prerequisites Rebase</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.Action#getProductAlias <em>Product
 * Alias</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.Action#getUpToDatePolicy <em>Up To
 * Date Policy</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.Action#getProductFileCount <em>
 * Product File Count</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.Action#getPattern <em>Pattern</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.Action#getReplacement <em>
 * Replacement</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.cspec.CspecPackage#getAction()
 * @model
 * @generated
 */
public interface Action extends Group {
	/**
	 * Returns the value of the '<em><b>Actor</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Actor</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Actor</em>' attribute.
	 * @see #setActor(String)
	 * @see org.eclipse.buckminster.cspec.CspecPackage#getAction_Actor()
	 * @model
	 * @generated
	 */
	String getActor();

	/**
	 * Returns the value of the '<em><b>Actor Properties</b></em>' containment
	 * reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.model.common.PropertyConstant}. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Actor Properties</em>' containment reference
	 * list isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Actor Properties</em>' containment
	 *         reference list.
	 * @see org.eclipse.buckminster.cspec.CspecPackage#getAction_ActorProperties()
	 * @model containment="true"
	 * @generated
	 */
	EList<PropertyConstant> getActorProperties();

	/**
	 * Returns the value of the '<em><b>Pattern</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pattern</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Pattern</em>' attribute.
	 * @see #setPattern(Pattern)
	 * @see org.eclipse.buckminster.cspec.CspecPackage#getAction_Pattern()
	 * @model dataType="org.eclipse.buckminster.model.common.Pattern"
	 * @generated
	 */
	Pattern getPattern();

	/**
	 * Returns the value of the '<em><b>Prerequisites Alias</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Prerequisites Alias</em>' attribute isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Prerequisites Alias</em>' attribute.
	 * @see #setPrerequisitesAlias(String)
	 * @see org.eclipse.buckminster.cspec.CspecPackage#getAction_PrerequisitesAlias()
	 * @model
	 * @generated
	 */
	String getPrerequisitesAlias();

	/**
	 * Returns the value of the '<em><b>Prerequisites Rebase</b></em>'
	 * attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Prerequisites Rebase</em>' containment
	 * reference isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Prerequisites Rebase</em>' attribute.
	 * @see #setPrerequisitesRebase(IPath)
	 * @see org.eclipse.buckminster.cspec.CspecPackage#getAction_PrerequisitesRebase()
	 * @model dataType="org.eclipse.buckminster.cspec.IPath"
	 * @generated
	 */
	IPath getPrerequisitesRebase();

	/**
	 * Returns the value of the '<em><b>Product</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Product</em>' containment reference isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Product</em>' containment reference.
	 * @see #setProduct(PathGroup)
	 * @see org.eclipse.buckminster.cspec.CspecPackage#getAction_Product()
	 * @model containment="true"
	 * @generated
	 */
	PathGroup getProduct();

	/**
	 * Returns the value of the '<em><b>Product Alias</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Product Alias</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Product Alias</em>' attribute.
	 * @see #setProductAlias(String)
	 * @see org.eclipse.buckminster.cspec.CspecPackage#getAction_ProductAlias()
	 * @model
	 * @generated
	 */
	String getProductAlias();

	/**
	 * Returns the value of the '<em><b>Product File Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Product File Count</em>' attribute isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Product File Count</em>' attribute.
	 * @see #setProductFileCount(int)
	 * @see org.eclipse.buckminster.cspec.CspecPackage#getAction_ProductFileCount()
	 * @model
	 * @generated
	 */
	int getProductFileCount();

	/**
	 * Returns the value of the '<em><b>Products</b></em>' reference list. The
	 * list contents are of type
	 * {@link org.eclipse.buckminster.cspec.ActionAttribute}. It is
	 * bidirectional and its opposite is '
	 * {@link org.eclipse.buckminster.cspec.ActionAttribute#getAction
	 * <em>Action</em>}'. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Products</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Products</em>' reference list.
	 * @see org.eclipse.buckminster.cspec.CspecPackage#getAction_Products()
	 * @see org.eclipse.buckminster.cspec.ActionAttribute#getAction
	 * @model opposite="action"
	 * @generated
	 */
	EList<ActionAttribute> getProducts();

	/**
	 * Returns the value of the '<em><b>Properties</b></em>' containment
	 * reference list. The list contents are of type
	 * {@link org.eclipse.buckminster.model.common.PropertyConstant}. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Properties</em>' containment reference list
	 * isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Properties</em>' containment reference
	 *         list.
	 * @see org.eclipse.buckminster.cspec.CspecPackage#getAction_Properties()
	 * @model containment="true"
	 * @generated
	 */
	EList<PropertyConstant> getProperties();

	/**
	 * Returns the value of the '<em><b>Replacement</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Replacement</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Replacement</em>' attribute.
	 * @see #setReplacement(String)
	 * @see org.eclipse.buckminster.cspec.CspecPackage#getAction_Replacement()
	 * @model
	 * @generated
	 */
	String getReplacement();

	/**
	 * Returns the value of the '<em><b>Up To Date Policy</b></em>' attribute.
	 * The default value is <code>"DEFAULT"</code>. The literals are from the
	 * enumeration {@link org.eclipse.buckminster.cspec.UpToDatePolicy}. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Up To Date Policy</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Up To Date Policy</em>' attribute.
	 * @see org.eclipse.buckminster.cspec.UpToDatePolicy
	 * @see #setUpToDatePolicy(UpToDatePolicy)
	 * @see org.eclipse.buckminster.cspec.CspecPackage#getAction_UpToDatePolicy()
	 * @model default="DEFAULT"
	 * @generated
	 */
	UpToDatePolicy getUpToDatePolicy();

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.cspec.Action#getActor <em>Actor</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Actor</em>' attribute.
	 * @see #getActor()
	 * @generated
	 */
	void setActor(String value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.cspec.Action#getPattern <em>Pattern</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Pattern</em>' attribute.
	 * @see #getPattern()
	 * @generated
	 */
	void setPattern(Pattern value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.cspec.Action#getPrerequisitesAlias
	 * <em>Prerequisites Alias</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Prerequisites Alias</em>' attribute.
	 * @see #getPrerequisitesAlias()
	 * @generated
	 */
	void setPrerequisitesAlias(String value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.cspec.Action#getPrerequisitesRebase
	 * <em>Prerequisites Rebase</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Prerequisites Rebase</em>'
	 *            attribute.
	 * @see #getPrerequisitesRebase()
	 * @generated
	 */
	void setPrerequisitesRebase(IPath value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.cspec.Action#getProduct <em>Product</em>}'
	 * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Product</em>' containment reference.
	 * @see #getProduct()
	 * @generated
	 */
	void setProduct(PathGroup value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.cspec.Action#getProductAlias
	 * <em>Product Alias</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Product Alias</em>' attribute.
	 * @see #getProductAlias()
	 * @generated
	 */
	void setProductAlias(String value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.cspec.Action#getProductFileCount
	 * <em>Product File Count</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Product File Count</em>' attribute.
	 * @see #getProductFileCount()
	 * @generated
	 */
	void setProductFileCount(int value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.cspec.Action#getReplacement
	 * <em>Replacement</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Replacement</em>' attribute.
	 * @see #getReplacement()
	 * @generated
	 */
	void setReplacement(String value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.cspec.Action#getUpToDatePolicy
	 * <em>Up To Date Policy</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Up To Date Policy</em>' attribute.
	 * @see org.eclipse.buckminster.cspec.UpToDatePolicy
	 * @see #getUpToDatePolicy()
	 * @generated
	 */
	void setUpToDatePolicy(UpToDatePolicy value);

} // Action
