/**
 * Copyright (c) 2006-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.impl;

import java.util.Collection;

import org.eclipse.buckminster.aggregator.AggregatorPackage;
import org.eclipse.buckminster.aggregator.InfosProvider;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Infos Provider</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.InfosProviderImpl#getErrors <em>Errors</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.InfosProviderImpl#getWarnings <em>Warnings</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.InfosProviderImpl#getInfos <em>Infos</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class InfosProviderImpl extends MinimalEObjectImpl.Container implements InfosProvider
{
	/**
	 * A set of bit flags representing the values of boolean attributes and whether unsettable features have been set.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	protected int eFlags = 0;

	/**
	 * The cached value of the '{@link #getErrors() <em>Errors</em>}' attribute list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getErrors()
	 * @generated
	 * @ordered
	 */
	protected EList<String> errors;

	/**
	 * The cached value of the '{@link #getWarnings() <em>Warnings</em>}' attribute list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getWarnings()
	 * @generated
	 * @ordered
	 */
	protected EList<String> warnings;

	/**
	 * The cached value of the '{@link #getInfos() <em>Infos</em>}' attribute list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getInfos()
	 * @generated
	 * @ordered
	 */
	protected EList<String> infos;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected InfosProviderImpl()
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
		case AggregatorPackage.INFOS_PROVIDER__ERRORS:
			return getErrors();
		case AggregatorPackage.INFOS_PROVIDER__WARNINGS:
			return getWarnings();
		case AggregatorPackage.INFOS_PROVIDER__INFOS:
			return getInfos();
		}
		return super.eGet(featureID, resolve, coreType);
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
		case AggregatorPackage.INFOS_PROVIDER__ERRORS:
			return errors != null && !errors.isEmpty();
		case AggregatorPackage.INFOS_PROVIDER__WARNINGS:
			return warnings != null && !warnings.isEmpty();
		case AggregatorPackage.INFOS_PROVIDER__INFOS:
			return infos != null && !infos.isEmpty();
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
		case AggregatorPackage.INFOS_PROVIDER__ERRORS:
			getErrors().clear();
			getErrors().addAll((Collection<? extends String>)newValue);
			return;
		case AggregatorPackage.INFOS_PROVIDER__WARNINGS:
			getWarnings().clear();
			getWarnings().addAll((Collection<? extends String>)newValue);
			return;
		case AggregatorPackage.INFOS_PROVIDER__INFOS:
			getInfos().clear();
			getInfos().addAll((Collection<? extends String>)newValue);
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
		case AggregatorPackage.INFOS_PROVIDER__ERRORS:
			getErrors().clear();
			return;
		case AggregatorPackage.INFOS_PROVIDER__WARNINGS:
			getWarnings().clear();
			return;
		case AggregatorPackage.INFOS_PROVIDER__INFOS:
			getInfos().clear();
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<String> getErrors()
	{
		if(errors == null)
		{
			errors = new EDataTypeUniqueEList<String>(String.class, this, AggregatorPackage.INFOS_PROVIDER__ERRORS);
		}
		return errors;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EList<String> getInfos()
	{
		if(infos == null)
		{
			infos = new EDataTypeUniqueEList<String>(String.class, this, AggregatorPackage.INFOS_PROVIDER__INFOS);
		}
		return infos;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<String> getWarnings()
	{
		if(warnings == null)
		{
			warnings = new EDataTypeUniqueEList<String>(String.class, this, AggregatorPackage.INFOS_PROVIDER__WARNINGS);
		}
		return warnings;
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
		result.append(" (errors: ");
		result.append(errors);
		result.append(", warnings: ");
		result.append(warnings);
		result.append(", infos: ");
		result.append(infos);
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
		return AggregatorPackage.Literals.INFOS_PROVIDER;
	}

} // InfosProviderImpl
