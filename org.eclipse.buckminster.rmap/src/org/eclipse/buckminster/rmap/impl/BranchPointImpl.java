/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap.impl;

import java.util.Date;

import org.eclipse.buckminster.rmap.BranchPoint;
import org.eclipse.buckminster.rmap.ConflictPolicy;
import org.eclipse.buckminster.rmap.RmapPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Branch Point</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.rmap.impl.BranchPointImpl#getName <em>Name
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.impl.BranchPointImpl#getTimestamp
 * <em>Timestamp</em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.impl.BranchPointImpl#getRevision <em>
 * Revision</em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.impl.BranchPointImpl#getTag <em>Tag
 * </em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.rmap.impl.BranchPointImpl#getOnMergeConflict
 * <em>On Merge Conflict</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class BranchPointImpl extends EObjectImpl implements BranchPoint {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getTimestamp() <em>Timestamp</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getTimestamp()
	 * @generated
	 * @ordered
	 */
	protected static final Date TIMESTAMP_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTimestamp() <em>Timestamp</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getTimestamp()
	 * @generated
	 * @ordered
	 */
	protected Date timestamp = TIMESTAMP_EDEFAULT;

	/**
	 * The default value of the '{@link #getRevision() <em>Revision</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getRevision()
	 * @generated
	 * @ordered
	 */
	protected static final String REVISION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRevision() <em>Revision</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getRevision()
	 * @generated
	 * @ordered
	 */
	protected String revision = REVISION_EDEFAULT;

	/**
	 * The default value of the '{@link #getTag() <em>Tag</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getTag()
	 * @generated
	 * @ordered
	 */
	protected static final String TAG_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTag() <em>Tag</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getTag()
	 * @generated
	 * @ordered
	 */
	protected String tag = TAG_EDEFAULT;

	/**
	 * The default value of the '{@link #getOnMergeConflict()
	 * <em>On Merge Conflict</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getOnMergeConflict()
	 * @generated
	 * @ordered
	 */
	protected static final ConflictPolicy ON_MERGE_CONFLICT_EDEFAULT = ConflictPolicy.FAIL;

	/**
	 * The cached value of the '{@link #getOnMergeConflict()
	 * <em>On Merge Conflict</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getOnMergeConflict()
	 * @generated
	 * @ordered
	 */
	protected ConflictPolicy onMergeConflict = ON_MERGE_CONFLICT_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected BranchPointImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RmapPackage.BRANCH_POINT__NAME:
				return getName();
			case RmapPackage.BRANCH_POINT__TIMESTAMP:
				return getTimestamp();
			case RmapPackage.BRANCH_POINT__REVISION:
				return getRevision();
			case RmapPackage.BRANCH_POINT__TAG:
				return getTag();
			case RmapPackage.BRANCH_POINT__ON_MERGE_CONFLICT:
				return getOnMergeConflict();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case RmapPackage.BRANCH_POINT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case RmapPackage.BRANCH_POINT__TIMESTAMP:
				return TIMESTAMP_EDEFAULT == null ? timestamp != null : !TIMESTAMP_EDEFAULT.equals(timestamp);
			case RmapPackage.BRANCH_POINT__REVISION:
				return REVISION_EDEFAULT == null ? revision != null : !REVISION_EDEFAULT.equals(revision);
			case RmapPackage.BRANCH_POINT__TAG:
				return TAG_EDEFAULT == null ? tag != null : !TAG_EDEFAULT.equals(tag);
			case RmapPackage.BRANCH_POINT__ON_MERGE_CONFLICT:
				return onMergeConflict != ON_MERGE_CONFLICT_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case RmapPackage.BRANCH_POINT__NAME:
				setName((String) newValue);
				return;
			case RmapPackage.BRANCH_POINT__TIMESTAMP:
				setTimestamp((Date) newValue);
				return;
			case RmapPackage.BRANCH_POINT__REVISION:
				setRevision((String) newValue);
				return;
			case RmapPackage.BRANCH_POINT__TAG:
				setTag((String) newValue);
				return;
			case RmapPackage.BRANCH_POINT__ON_MERGE_CONFLICT:
				setOnMergeConflict((ConflictPolicy) newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case RmapPackage.BRANCH_POINT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case RmapPackage.BRANCH_POINT__TIMESTAMP:
				setTimestamp(TIMESTAMP_EDEFAULT);
				return;
			case RmapPackage.BRANCH_POINT__REVISION:
				setRevision(REVISION_EDEFAULT);
				return;
			case RmapPackage.BRANCH_POINT__TAG:
				setTag(TAG_EDEFAULT);
				return;
			case RmapPackage.BRANCH_POINT__ON_MERGE_CONFLICT:
				setOnMergeConflict(ON_MERGE_CONFLICT_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public ConflictPolicy getOnMergeConflict() {
		return onMergeConflict;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public String getRevision() {
		return revision;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public String getTag() {
		return tag;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public Date getTimestamp() {
		return timestamp;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RmapPackage.BRANCH_POINT__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public void setOnMergeConflict(ConflictPolicy newOnMergeConflict) {
		ConflictPolicy oldOnMergeConflict = onMergeConflict;
		onMergeConflict = newOnMergeConflict == null ? ON_MERGE_CONFLICT_EDEFAULT : newOnMergeConflict;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RmapPackage.BRANCH_POINT__ON_MERGE_CONFLICT, oldOnMergeConflict, onMergeConflict));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public void setRevision(String newRevision) {
		String oldRevision = revision;
		revision = newRevision;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RmapPackage.BRANCH_POINT__REVISION, oldRevision, revision));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public void setTag(String newTag) {
		String oldTag = tag;
		tag = newTag;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RmapPackage.BRANCH_POINT__TAG, oldTag, tag));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public void setTimestamp(Date newTimestamp) {
		Date oldTimestamp = timestamp;
		timestamp = newTimestamp;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RmapPackage.BRANCH_POINT__TIMESTAMP, oldTimestamp, timestamp));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(", timestamp: ");
		result.append(timestamp);
		result.append(", revision: ");
		result.append(revision);
		result.append(", tag: ");
		result.append(tag);
		result.append(", onMergeConflict: ");
		result.append(onMergeConflict);
		result.append(')');
		return result.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	protected EClass eStaticClass() {
		return RmapPackage.Literals.BRANCH_POINT;
	}

} // BranchPointImpl
