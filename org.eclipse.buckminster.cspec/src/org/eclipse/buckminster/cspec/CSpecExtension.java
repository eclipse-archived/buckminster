/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspec;

import org.eclipse.buckminster.model.common.ComponentRequest;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>CSpec Extension</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.cspec.CSpecExtension#getAttributeAlterations <em>Attribute Alterations</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.CSpecExtension#getRenameAttributes <em>Rename Attributes</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.CSpecExtension#getRemoveDependencies <em>Remove Dependencies</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.CSpecExtension#getRemoveGenerators <em>Remove Generators</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.CSpecExtension#getReplaceGenerators <em>Replace Generators</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.CSpecExtension#getReplaceDependencies <em>Replace Dependencies</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.cspec.CspecPackage#getCSpecExtension()
 * @model
 * @generated
 */
public interface CSpecExtension extends CSpec
{
	/**
	 * Returns the value of the '<em><b>Attribute Alterations</b></em>' containment reference list. The list contents
	 * are of type {@link org.eclipse.buckminster.cspec.AlterAttribute}. It is bidirectional and its opposite is '
	 * {@link org.eclipse.buckminster.cspec.AlterAttribute#getCspecext <em>Cspecext</em>}'. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attribute Alterations</em>' containment reference list isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Attribute Alterations</em>' containment reference list.
	 * @see org.eclipse.buckminster.cspec.CspecPackage#getCSpecExtension_AttributeAlterations()
	 * @see org.eclipse.buckminster.cspec.AlterAttribute#getCspecext
	 * @model opposite="cspecext" containment="true"
	 * @generated
	 */
	EList<AlterAttribute> getAttributeAlterations();

	/**
	 * Returns the value of the '<em><b>Remove Dependencies</b></em>' containment reference list. The list contents are
	 * of type {@link org.eclipse.buckminster.cspec.Remove}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Remove Dependencies</em>' containment reference list isn't clear, there really should
	 * be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Remove Dependencies</em>' containment reference list.
	 * @see org.eclipse.buckminster.cspec.CspecPackage#getCSpecExtension_RemoveDependencies()
	 * @model containment="true"
	 * @generated
	 */
	EList<Remove> getRemoveDependencies();

	/**
	 * Returns the value of the '<em><b>Remove Generators</b></em>' containment reference list. The list contents are of
	 * type {@link org.eclipse.buckminster.cspec.Remove}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Remove Generators</em>' containment reference list isn't clear, there really should be
	 * more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Remove Generators</em>' containment reference list.
	 * @see org.eclipse.buckminster.cspec.CspecPackage#getCSpecExtension_RemoveGenerators()
	 * @model containment="true"
	 * @generated
	 */
	EList<Remove> getRemoveGenerators();

	/**
	 * Returns the value of the '<em><b>Rename Attributes</b></em>' containment reference list. The list contents are of
	 * type {@link org.eclipse.buckminster.cspec.Rename}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rename Attributes</em>' containment reference list isn't clear, there really should be
	 * more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Rename Attributes</em>' containment reference list.
	 * @see org.eclipse.buckminster.cspec.CspecPackage#getCSpecExtension_RenameAttributes()
	 * @model containment="true"
	 * @generated
	 */
	EList<Rename> getRenameAttributes();

	/**
	 * Returns the value of the '<em><b>Replace Dependencies</b></em>' containment reference list. The list contents are
	 * of type {@link org.eclipse.buckminster.model.common.ComponentRequest}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Replace Dependencies</em>' containment reference list isn't clear, there really should
	 * be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Replace Dependencies</em>' containment reference list.
	 * @see org.eclipse.buckminster.cspec.CspecPackage#getCSpecExtension_ReplaceDependencies()
	 * @model containment="true"
	 * @generated
	 */
	EList<ComponentRequest> getReplaceDependencies();

	/**
	 * Returns the value of the '<em><b>Replace Generators</b></em>' containment reference list. The list contents are
	 * of type {@link org.eclipse.buckminster.cspec.Generator}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Replace Generators</em>' containment reference list isn't clear, there really should
	 * be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Replace Generators</em>' containment reference list.
	 * @see org.eclipse.buckminster.cspec.CspecPackage#getCSpecExtension_ReplaceGenerators()
	 * @model containment="true"
	 * @generated
	 */
	EList<Generator> getReplaceGenerators();

} // CSpecExtension
