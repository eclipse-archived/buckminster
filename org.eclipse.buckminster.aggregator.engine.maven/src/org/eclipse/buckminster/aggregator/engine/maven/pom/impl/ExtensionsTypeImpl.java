/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.engine.maven.pom.impl;

import java.util.Collection;

import org.eclipse.buckminster.aggregator.engine.maven.pom.Extension;
import org.eclipse.buckminster.aggregator.engine.maven.pom.ExtensionsType;
import org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Extensions Type</b></em>'. <!-- end-user-doc
 * -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.impl.ExtensionsTypeImpl#getExtension <em>Extension
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class ExtensionsTypeImpl extends EObjectImpl implements ExtensionsType
{
	/**
	 * The cached value of the '{@link #getExtension() <em>Extension</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getExtension()
	 * @generated
	 * @ordered
	 */
	protected EList<Extension> extension;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ExtensionsTypeImpl()
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
		case PomPackage.EXTENSIONS_TYPE__EXTENSION:
			return getExtension();
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
		case PomPackage.EXTENSIONS_TYPE__EXTENSION:
			return ((InternalEList<?>)getExtension()).basicRemove(otherEnd, msgs);
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
		case PomPackage.EXTENSIONS_TYPE__EXTENSION:
			return extension != null && !extension.isEmpty();
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
		case PomPackage.EXTENSIONS_TYPE__EXTENSION:
			getExtension().clear();
			getExtension().addAll((Collection<? extends Extension>)newValue);
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
		case PomPackage.EXTENSIONS_TYPE__EXTENSION:
			getExtension().clear();
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<Extension> getExtension()
	{
		if(extension == null)
		{
			extension = new EObjectContainmentEList<Extension>(Extension.class, this,
					PomPackage.EXTENSIONS_TYPE__EXTENSION);
		}
		return extension;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass()
	{
		return PomPackage.Literals.EXTENSIONS_TYPE;
	}

} // ExtensionsTypeImpl
