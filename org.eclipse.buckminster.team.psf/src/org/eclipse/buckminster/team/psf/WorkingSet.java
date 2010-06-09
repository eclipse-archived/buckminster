/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.team.psf;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Working Set</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.team.psf.WorkingSet#getId <em>Id</em>}</li>
 * <li>{@link org.eclipse.buckminster.team.psf.WorkingSet#getLabel <em>Label
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.team.psf.WorkingSet#getName <em>Name</em>}
 * </li>
 * <li>{@link org.eclipse.buckminster.team.psf.WorkingSet#getEditPageId <em>Edit
 * Page Id</em>}</li>
 * <li>{@link org.eclipse.buckminster.team.psf.WorkingSet#getItems <em>Items
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.team.psf.PsfPackage#getWorkingSet()
 * @model
 * @generated
 */
public interface WorkingSet extends EObject {
	/**
	 * Returns the value of the '<em><b>Edit Page Id</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Edit Page Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Edit Page Id</em>' attribute.
	 * @see #setEditPageId(String)
	 * @see org.eclipse.buckminster.team.psf.PsfPackage#getWorkingSet_EditPageId()
	 * @model
	 * @generated
	 */
	String getEditPageId();

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.eclipse.buckminster.team.psf.PsfPackage#getWorkingSet_Id()
	 * @model required="true"
	 * @generated
	 */
	String getId();

	/**
	 * Returns the value of the '<em><b>Items</b></em>' containment reference
	 * list. The list contents are of type
	 * {@link org.eclipse.buckminster.team.psf.Item}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Items</em>' containment reference list isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Items</em>' containment reference list.
	 * @see org.eclipse.buckminster.team.psf.PsfPackage#getWorkingSet_Items()
	 * @model containment="true" extendedMetaData="name='item' kind='element'"
	 * @generated
	 */
	EList<Item> getItems();

	/**
	 * Returns the value of the '<em><b>Label</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Label</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Label</em>' attribute.
	 * @see #setLabel(String)
	 * @see org.eclipse.buckminster.team.psf.PsfPackage#getWorkingSet_Label()
	 * @model
	 * @generated
	 */
	String getLabel();

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.buckminster.team.psf.PsfPackage#getWorkingSet_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.team.psf.WorkingSet#getEditPageId
	 * <em>Edit Page Id</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Edit Page Id</em>' attribute.
	 * @see #getEditPageId()
	 * @generated
	 */
	void setEditPageId(String value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.team.psf.WorkingSet#getId <em>Id</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.team.psf.WorkingSet#getLabel
	 * <em>Label</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Label</em>' attribute.
	 * @see #getLabel()
	 * @generated
	 */
	void setLabel(String value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.team.psf.WorkingSet#getName <em>Name</em>}
	 * ' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // WorkingSet
