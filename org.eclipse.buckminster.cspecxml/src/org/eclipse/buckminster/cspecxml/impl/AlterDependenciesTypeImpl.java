/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspecxml.impl;

import java.util.Collection;

import org.eclipse.buckminster.cspecxml.IAlterDependenciesType;
import org.eclipse.buckminster.cspecxml.ICSpecXMLPackage;
import org.eclipse.buckminster.cspecxml.IComponentRequest;
import org.eclipse.buckminster.cspecxml.IRemove;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Alter Dependencies Type</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.AlterDependenciesTypeImpl#getGroup <em>Group</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.AlterDependenciesTypeImpl#getDependency <em>Dependency</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.AlterDependenciesTypeImpl#getRemove <em>Remove</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class AlterDependenciesTypeImpl extends EObjectImpl implements IAlterDependenciesType
{
	/**
	 * The cached value of the '{@link #getGroup() <em>Group</em>}' attribute list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getGroup()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap group;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected AlterDependenciesTypeImpl()
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
		case ICSpecXMLPackage.ALTER_DEPENDENCIES_TYPE__GROUP:
			if(coreType)
				return getGroup();
			return ((FeatureMap.Internal)getGroup()).getWrapper();
		case ICSpecXMLPackage.ALTER_DEPENDENCIES_TYPE__DEPENDENCY:
			return getDependency();
		case ICSpecXMLPackage.ALTER_DEPENDENCIES_TYPE__REMOVE:
			return getRemove();
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
		case ICSpecXMLPackage.ALTER_DEPENDENCIES_TYPE__GROUP:
			return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
		case ICSpecXMLPackage.ALTER_DEPENDENCIES_TYPE__DEPENDENCY:
			return ((InternalEList<?>)getDependency()).basicRemove(otherEnd, msgs);
		case ICSpecXMLPackage.ALTER_DEPENDENCIES_TYPE__REMOVE:
			return ((InternalEList<?>)getRemove()).basicRemove(otherEnd, msgs);
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
		case ICSpecXMLPackage.ALTER_DEPENDENCIES_TYPE__GROUP:
			return group != null && !group.isEmpty();
		case ICSpecXMLPackage.ALTER_DEPENDENCIES_TYPE__DEPENDENCY:
			return !getDependency().isEmpty();
		case ICSpecXMLPackage.ALTER_DEPENDENCIES_TYPE__REMOVE:
			return !getRemove().isEmpty();
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
		case ICSpecXMLPackage.ALTER_DEPENDENCIES_TYPE__GROUP:
			((FeatureMap.Internal)getGroup()).set(newValue);
			return;
		case ICSpecXMLPackage.ALTER_DEPENDENCIES_TYPE__DEPENDENCY:
			getDependency().clear();
			getDependency().addAll((Collection<? extends IComponentRequest>)newValue);
			return;
		case ICSpecXMLPackage.ALTER_DEPENDENCIES_TYPE__REMOVE:
			getRemove().clear();
			getRemove().addAll((Collection<? extends IRemove>)newValue);
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
		case ICSpecXMLPackage.ALTER_DEPENDENCIES_TYPE__GROUP:
			getGroup().clear();
			return;
		case ICSpecXMLPackage.ALTER_DEPENDENCIES_TYPE__DEPENDENCY:
			getDependency().clear();
			return;
		case ICSpecXMLPackage.ALTER_DEPENDENCIES_TYPE__REMOVE:
			getRemove().clear();
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<IComponentRequest> getDependency()
	{
		return getGroup().list(ICSpecXMLPackage.Literals.ALTER_DEPENDENCIES_TYPE__DEPENDENCY);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public FeatureMap getGroup()
	{
		if(group == null)
		{
			group = new BasicFeatureMap(this, ICSpecXMLPackage.ALTER_DEPENDENCIES_TYPE__GROUP);
		}
		return group;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<IRemove> getRemove()
	{
		return getGroup().list(ICSpecXMLPackage.Literals.ALTER_DEPENDENCIES_TYPE__REMOVE);
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
		result.append(" (group: ");
		result.append(group);
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
		return ICSpecXMLPackage.Literals.ALTER_DEPENDENCIES_TYPE;
	}

} // AlterDependenciesTypeImpl
