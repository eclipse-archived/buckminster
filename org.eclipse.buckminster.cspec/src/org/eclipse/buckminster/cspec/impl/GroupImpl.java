/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspec.impl;

import java.util.Collection;

import org.eclipse.buckminster.cspec.CspecPackage;
import org.eclipse.buckminster.cspec.Group;
import org.eclipse.buckminster.cspec.Prerequisite;

import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Group</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.cspec.impl.GroupImpl#getPrerequisites <em>Prerequisites</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.impl.GroupImpl#getRebase <em>Rebase</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class GroupImpl extends AttributeImpl implements Group
{
	/**
	 * The cached value of the '{@link #getPrerequisites() <em>Prerequisites</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getPrerequisites()
	 * @generated
	 * @ordered
	 */
	protected EList<Prerequisite> prerequisites;

	/**
	 * The default value of the '{@link #getRebase() <em>Rebase</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getRebase()
	 * @generated
	 * @ordered
	 */
	protected static final IPath REBASE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRebase() <em>Rebase</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getRebase()
	 * @generated
	 * @ordered
	 */
	protected IPath rebase = REBASE_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected GroupImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType)
	{
		switch(featureID)
		{
		case CspecPackage.GROUP__PREREQUISITES:
			return getPrerequisites();
		case CspecPackage.GROUP__REBASE:
			return getRebase();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
	{
		switch(featureID)
		{
		case CspecPackage.GROUP__PREREQUISITES:
			return ((InternalEList<?>)getPrerequisites()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID)
	{
		switch(featureID)
		{
		case CspecPackage.GROUP__PREREQUISITES:
			return prerequisites != null && !prerequisites.isEmpty();
		case CspecPackage.GROUP__REBASE:
			return REBASE_EDEFAULT == null
					? rebase != null
					: !REBASE_EDEFAULT.equals(rebase);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue)
	{
		switch(featureID)
		{
		case CspecPackage.GROUP__PREREQUISITES:
			getPrerequisites().clear();
			getPrerequisites().addAll((Collection<? extends Prerequisite>)newValue);
			return;
		case CspecPackage.GROUP__REBASE:
			setRebase((IPath)newValue);
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
	public void eUnset(int featureID)
	{
		switch(featureID)
		{
		case CspecPackage.GROUP__PREREQUISITES:
			getPrerequisites().clear();
			return;
		case CspecPackage.GROUP__REBASE:
			setRebase(REBASE_EDEFAULT);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<Prerequisite> getPrerequisites()
	{
		if(prerequisites == null)
		{
			prerequisites = new EObjectContainmentEList<Prerequisite>(Prerequisite.class, this,
					CspecPackage.GROUP__PREREQUISITES);
		}
		return prerequisites;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public IPath getRebase()
	{
		return rebase;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setRebase(IPath newRebase)
	{
		IPath oldRebase = rebase;
		rebase = newRebase;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CspecPackage.GROUP__REBASE, oldRebase, rebase));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String toString()
	{
		if(eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (rebase: ");
		result.append(rebase);
		result.append(')');
		return result.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass()
	{
		return CspecPackage.Literals.GROUP;
	}

} // GroupImpl
