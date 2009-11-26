/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspec.impl;

import java.util.Collection;

import org.eclipse.buckminster.cspec.Artifact;
import org.eclipse.buckminster.cspec.CspecPackage;
import org.eclipse.buckminster.cspec.PathGroup;

import org.eclipse.core.runtime.IPath;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Artifact</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.cspec.impl.ArtifactImpl#getBase <em>Base</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.impl.ArtifactImpl#getPaths <em>Paths</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class ArtifactImpl extends AttributeImpl implements Artifact
{
	/**
	 * The default value of the '{@link #getBase() <em>Base</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getBase()
	 * @generated
	 * @ordered
	 */
	protected static final IPath BASE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBase() <em>Base</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getBase()
	 * @generated
	 * @ordered
	 */
	protected IPath base = BASE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getPaths() <em>Paths</em>}' attribute list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getPaths()
	 * @generated
	 * @ordered
	 */
	protected EList<IPath> paths;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ArtifactImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass)
	{
		if(baseClass == PathGroup.class)
		{
			switch(derivedFeatureID)
			{
			case CspecPackage.ARTIFACT__BASE:
				return CspecPackage.PATH_GROUP__BASE;
			case CspecPackage.ARTIFACT__PATHS:
				return CspecPackage.PATH_GROUP__PATHS;
			default:
				return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass)
	{
		if(baseClass == PathGroup.class)
		{
			switch(baseFeatureID)
			{
			case CspecPackage.PATH_GROUP__BASE:
				return CspecPackage.ARTIFACT__BASE;
			case CspecPackage.PATH_GROUP__PATHS:
				return CspecPackage.ARTIFACT__PATHS;
			default:
				return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
		case CspecPackage.ARTIFACT__BASE:
			return getBase();
		case CspecPackage.ARTIFACT__PATHS:
			return getPaths();
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
		case CspecPackage.ARTIFACT__BASE:
			return BASE_EDEFAULT == null
					? base != null
					: !BASE_EDEFAULT.equals(base);
		case CspecPackage.ARTIFACT__PATHS:
			return paths != null && !paths.isEmpty();
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
		case CspecPackage.ARTIFACT__BASE:
			setBase((IPath)newValue);
			return;
		case CspecPackage.ARTIFACT__PATHS:
			getPaths().clear();
			getPaths().addAll((Collection<? extends IPath>)newValue);
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
		case CspecPackage.ARTIFACT__BASE:
			setBase(BASE_EDEFAULT);
			return;
		case CspecPackage.ARTIFACT__PATHS:
			getPaths().clear();
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public IPath getBase()
	{
		return base;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<IPath> getPaths()
	{
		if(paths == null)
		{
			paths = new EDataTypeUniqueEList<IPath>(IPath.class, this, CspecPackage.ARTIFACT__PATHS);
		}
		return paths;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public PathGroup resolve(IPath path)
	{
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setBase(IPath newBase)
	{
		IPath oldBase = base;
		base = newBase;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CspecPackage.ARTIFACT__BASE, oldBase, base));
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
		result.append(" (base: ");
		result.append(base);
		result.append(", paths: ");
		result.append(paths);
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
		return CspecPackage.Literals.ARTIFACT;
	}

} // ArtifactImpl
