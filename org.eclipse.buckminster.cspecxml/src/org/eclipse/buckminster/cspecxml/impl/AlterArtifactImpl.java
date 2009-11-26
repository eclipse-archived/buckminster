/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspecxml.impl;

import java.util.Collection;

import org.eclipse.buckminster.cspecxml.IAlterArtifact;
import org.eclipse.buckminster.cspecxml.ICSpecXMLPackage;
import org.eclipse.buckminster.cspecxml.IRemovePath;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Alter Artifact</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.AlterArtifactImpl#getGroup1 <em>Group1</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.AlterArtifactImpl#getRemovePath <em>Remove Path</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class AlterArtifactImpl extends ArtifactImpl implements IAlterArtifact
{
	/**
	 * The cached value of the '{@link #getGroup1() <em>Group1</em>}' attribute list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getGroup1()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap group1;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected AlterArtifactImpl()
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
		case ICSpecXMLPackage.ALTER_ARTIFACT__GROUP1:
			if(coreType)
				return getGroup1();
			return ((FeatureMap.Internal)getGroup1()).getWrapper();
		case ICSpecXMLPackage.ALTER_ARTIFACT__REMOVE_PATH:
			return getRemovePath();
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
		case ICSpecXMLPackage.ALTER_ARTIFACT__GROUP1:
			return ((InternalEList<?>)getGroup1()).basicRemove(otherEnd, msgs);
		case ICSpecXMLPackage.ALTER_ARTIFACT__REMOVE_PATH:
			return ((InternalEList<?>)getRemovePath()).basicRemove(otherEnd, msgs);
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
		case ICSpecXMLPackage.ALTER_ARTIFACT__GROUP1:
			return group1 != null && !group1.isEmpty();
		case ICSpecXMLPackage.ALTER_ARTIFACT__REMOVE_PATH:
			return !getRemovePath().isEmpty();
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
		case ICSpecXMLPackage.ALTER_ARTIFACT__GROUP1:
			((FeatureMap.Internal)getGroup1()).set(newValue);
			return;
		case ICSpecXMLPackage.ALTER_ARTIFACT__REMOVE_PATH:
			getRemovePath().clear();
			getRemovePath().addAll((Collection<? extends IRemovePath>)newValue);
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
		case ICSpecXMLPackage.ALTER_ARTIFACT__GROUP1:
			getGroup1().clear();
			return;
		case ICSpecXMLPackage.ALTER_ARTIFACT__REMOVE_PATH:
			getRemovePath().clear();
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public FeatureMap getGroup1()
	{
		if(group1 == null)
		{
			group1 = new BasicFeatureMap(this, ICSpecXMLPackage.ALTER_ARTIFACT__GROUP1);
		}
		return group1;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<IRemovePath> getRemovePath()
	{
		return getGroup1().list(ICSpecXMLPackage.Literals.ALTER_ARTIFACT__REMOVE_PATH);
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
		result.append(" (group1: ");
		result.append(group1);
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
		return ICSpecXMLPackage.Literals.ALTER_ARTIFACT;
	}

} // AlterArtifactImpl
