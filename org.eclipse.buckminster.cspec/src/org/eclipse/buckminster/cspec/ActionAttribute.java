/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspec;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Action Attribute</b></em>'. <!-- end-user-doc
 * -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.cspec.ActionAttribute#getAlias <em>Alias</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.ActionAttribute#getAction <em>Action</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.cspec.CspecPackage#getActionAttribute()
 * @model
 * @generated
 */
public interface ActionAttribute extends Artifact
{
	/**
	 * Returns the value of the '<em><b>Action</b></em>' reference. It is bidirectional and its opposite is '
	 * {@link org.eclipse.buckminster.cspec.Action#getProducts <em>Products</em>}'. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Action</em>' reference isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Action</em>' reference.
	 * @see #setAction(Action)
	 * @see org.eclipse.buckminster.cspec.CspecPackage#getActionAttribute_Action()
	 * @see org.eclipse.buckminster.cspec.Action#getProducts
	 * @model opposite="products" required="true"
	 * @generated
	 */
	Action getAction();

	/**
	 * Returns the value of the '<em><b>Alias</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Alias</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Alias</em>' attribute.
	 * @see #setAlias(String)
	 * @see org.eclipse.buckminster.cspec.CspecPackage#getActionAttribute_Alias()
	 * @model
	 * @generated
	 */
	String getAlias();

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.cspec.ActionAttribute#getAction <em>Action</em>}'
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Action</em>' reference.
	 * @see #getAction()
	 * @generated
	 */
	void setAction(Action value);

	/**
	 * Sets the value of the '{@link org.eclipse.buckminster.cspec.ActionAttribute#getAlias <em>Alias</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Alias</em>' attribute.
	 * @see #getAlias()
	 * @generated
	 */
	void setAlias(String value);

} // ActionAttribute
