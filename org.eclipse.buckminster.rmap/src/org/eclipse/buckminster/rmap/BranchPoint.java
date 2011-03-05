/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap;

import java.util.Date;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Branch Point</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.buckminster.rmap.BranchPoint#getName <em>Name</em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.BranchPoint#getTimestamp <em>
 * Timestamp</em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.BranchPoint#getRevision <em>Revision
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.BranchPoint#getTag <em>Tag</em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.BranchPoint#getOnMergeConflict <em>On
 * Merge Conflict</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.buckminster.rmap.RmapPackage#getBranchPoint()
 * @model
 * @generated
 */
public interface BranchPoint extends EObject {
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
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getBranchPoint_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Returns the value of the '<em><b>On Merge Conflict</b></em>' attribute.
	 * The default value is <code>"FAIL"</code>. The literals are from the
	 * enumeration {@link org.eclipse.buckminster.rmap.ConflictPolicy}. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>On Merge Conflict</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>On Merge Conflict</em>' attribute.
	 * @see org.eclipse.buckminster.rmap.ConflictPolicy
	 * @see #setOnMergeConflict(ConflictPolicy)
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getBranchPoint_OnMergeConflict()
	 * @model default="FAIL"
	 * @generated
	 */
	ConflictPolicy getOnMergeConflict();

	/**
	 * Returns the value of the '<em><b>Revision</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Revision</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Revision</em>' attribute.
	 * @see #setRevision(String)
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getBranchPoint_Revision()
	 * @model
	 * @generated
	 */
	String getRevision();

	/**
	 * Returns the value of the '<em><b>Tag</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tag</em>' attribute isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Tag</em>' attribute.
	 * @see #setTag(String)
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getBranchPoint_Tag()
	 * @model
	 * @generated
	 */
	String getTag();

	/**
	 * Returns the value of the '<em><b>Timestamp</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Timestamp</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Timestamp</em>' attribute.
	 * @see #setTimestamp(Date)
	 * @see org.eclipse.buckminster.rmap.RmapPackage#getBranchPoint_Timestamp()
	 * @model
	 * @generated
	 */
	Date getTimestamp();

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.rmap.BranchPoint#getName <em>Name</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.rmap.BranchPoint#getOnMergeConflict
	 * <em>On Merge Conflict</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>On Merge Conflict</em>' attribute.
	 * @see org.eclipse.buckminster.rmap.ConflictPolicy
	 * @see #getOnMergeConflict()
	 * @generated
	 */
	void setOnMergeConflict(ConflictPolicy value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.rmap.BranchPoint#getRevision
	 * <em>Revision</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @param value
	 *            the new value of the '<em>Revision</em>' attribute.
	 * @see #getRevision()
	 * @generated
	 */
	void setRevision(String value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.rmap.BranchPoint#getTag <em>Tag</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Tag</em>' attribute.
	 * @see #getTag()
	 * @generated
	 */
	void setTag(String value);

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.buckminster.rmap.BranchPoint#getTimestamp
	 * <em>Timestamp</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @param value
	 *            the new value of the '<em>Timestamp</em>' attribute.
	 * @see #getTimestamp()
	 * @generated
	 */
	void setTimestamp(Date value);

} // BranchPoint
