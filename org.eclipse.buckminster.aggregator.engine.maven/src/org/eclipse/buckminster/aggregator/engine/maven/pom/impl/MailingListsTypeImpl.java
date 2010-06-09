/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.engine.maven.pom.impl;

import java.util.Collection;

import org.eclipse.buckminster.aggregator.engine.maven.pom.MailingList;
import org.eclipse.buckminster.aggregator.engine.maven.pom.MailingListsType;
import org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Mailing Lists Type</b></em>'. <!-- end-user-doc
 * -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.impl.MailingListsTypeImpl#getMailingList <em>Mailing
 * List</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class MailingListsTypeImpl extends EObjectImpl implements MailingListsType
{
	/**
	 * The cached value of the '{@link #getMailingList() <em>Mailing List</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getMailingList()
	 * @generated
	 * @ordered
	 */
	protected EList<MailingList> mailingList;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected MailingListsTypeImpl()
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
		case PomPackage.MAILING_LISTS_TYPE__MAILING_LIST:
			return getMailingList();
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
		case PomPackage.MAILING_LISTS_TYPE__MAILING_LIST:
			return ((InternalEList<?>)getMailingList()).basicRemove(otherEnd, msgs);
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
		case PomPackage.MAILING_LISTS_TYPE__MAILING_LIST:
			return mailingList != null && !mailingList.isEmpty();
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
		case PomPackage.MAILING_LISTS_TYPE__MAILING_LIST:
			getMailingList().clear();
			getMailingList().addAll((Collection<? extends MailingList>)newValue);
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
		case PomPackage.MAILING_LISTS_TYPE__MAILING_LIST:
			getMailingList().clear();
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<MailingList> getMailingList()
	{
		if(mailingList == null)
		{
			mailingList = new EObjectContainmentEList<MailingList>(MailingList.class, this,
					PomPackage.MAILING_LISTS_TYPE__MAILING_LIST);
		}
		return mailingList;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass()
	{
		return PomPackage.Literals.MAILING_LISTS_TYPE;
	}

} // MailingListsTypeImpl
