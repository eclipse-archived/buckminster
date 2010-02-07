/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspec.impl;

import java.util.Collection;

import org.eclipse.buckminster.cspec.CspecPackage;
import org.eclipse.buckminster.cspec.PathGroup;

import org.eclipse.core.runtime.IPath;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Path Group</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.cspec.impl.PathGroupImpl#getBase <em>Base
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.impl.PathGroupImpl#getPaths <em>
 * Paths</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class PathGroupImpl extends EObjectImpl implements PathGroup {
	/**
	 * The default value of the '{@link #getBase() <em>Base</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getBase()
	 * @generated
	 * @ordered
	 */
	protected static final IPath BASE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBase() <em>Base</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getBase()
	 * @generated
	 * @ordered
	 */
	protected IPath base = BASE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getPaths() <em>Paths</em>}' attribute
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
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
	protected PathGroupImpl() {
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
			case CspecPackage.PATH_GROUP__BASE:
				return getBase();
			case CspecPackage.PATH_GROUP__PATHS:
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
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case CspecPackage.PATH_GROUP__BASE:
				return BASE_EDEFAULT == null ? base != null : !BASE_EDEFAULT.equals(base);
			case CspecPackage.PATH_GROUP__PATHS:
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
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case CspecPackage.PATH_GROUP__BASE:
				setBase((IPath) newValue);
				return;
			case CspecPackage.PATH_GROUP__PATHS:
				getPaths().clear();
				getPaths().addAll((Collection<? extends IPath>) newValue);
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
			case CspecPackage.PATH_GROUP__BASE:
				setBase(BASE_EDEFAULT);
				return;
			case CspecPackage.PATH_GROUP__PATHS:
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
	public IPath getBase() {
		return base;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<IPath> getPaths() {
		if (paths == null) {
			paths = new EDataTypeUniqueEList<IPath>(IPath.class, this, CspecPackage.PATH_GROUP__PATHS);
		}
		return paths;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public PathGroup resolve(IPath path) {
		if (base.isAbsolute())
			return this;

		PathGroup resolved = new PathGroupImpl();
		resolved.setBase(path.append(base));
		resolved.getPaths().addAll(getPaths());
		return resolved;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setBase(IPath newBase) {
		IPath oldBase = base;
		base = newBase;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CspecPackage.PATH_GROUP__BASE, oldBase, base));
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
	protected EClass eStaticClass() {
		return CspecPackage.Literals.PATH_GROUP;
	}

} // PathGroupImpl
