/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap.impl;

import java.util.List;

import org.eclipse.buckminster.model.common.impl.RxAssemblyImpl;
import org.eclipse.buckminster.rmap.Provider;
import org.eclipse.buckminster.rmap.RmapPackage;
import org.eclipse.buckminster.rmap.URIMatcher;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.equinox.p2.metadata.IVersionFormat;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>URI Matcher</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.rmap.impl.URIMatcherImpl#getBase <em>Base
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.impl.URIMatcherImpl#getVersionFormat
 * <em>Version Format</em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.impl.URIMatcherImpl#getVersionType
 * <em>Version Type</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class URIMatcherImpl extends RxAssemblyImpl implements URIMatcher {
	/**
	 * The default value of the '{@link #getBase() <em>Base</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getBase()
	 * @generated
	 * @ordered
	 */
	protected static final String BASE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBase() <em>Base</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getBase()
	 * @generated
	 * @ordered
	 */
	protected String base = BASE_EDEFAULT;

	/**
	 * The default value of the '{@link #getVersionFormat()
	 * <em>Version Format</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getVersionFormat()
	 * @generated
	 * @ordered
	 */
	protected static final IVersionFormat VERSION_FORMAT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVersionFormat()
	 * <em>Version Format</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getVersionFormat()
	 * @generated
	 * @ordered
	 */
	protected IVersionFormat versionFormat = VERSION_FORMAT_EDEFAULT;

	/**
	 * The default value of the '{@link #getVersionType() <em>Version Type</em>}
	 * ' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getVersionType()
	 * @generated
	 * @ordered
	 */
	protected static final String VERSION_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVersionType() <em>Version Type</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getVersionType()
	 * @generated
	 * @ordered
	 */
	protected String versionType = VERSION_TYPE_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected URIMatcherImpl() {
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
			case RmapPackage.URI_MATCHER__BASE:
				return getBase();
			case RmapPackage.URI_MATCHER__VERSION_FORMAT:
				return getVersionFormat();
			case RmapPackage.URI_MATCHER__VERSION_TYPE:
				return getVersionType();
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
			case RmapPackage.URI_MATCHER__BASE:
				return BASE_EDEFAULT == null ? base != null : !BASE_EDEFAULT.equals(base);
			case RmapPackage.URI_MATCHER__VERSION_FORMAT:
				return VERSION_FORMAT_EDEFAULT == null ? versionFormat != null : !VERSION_FORMAT_EDEFAULT.equals(versionFormat);
			case RmapPackage.URI_MATCHER__VERSION_TYPE:
				return VERSION_TYPE_EDEFAULT == null ? versionType != null : !VERSION_TYPE_EDEFAULT.equals(versionType);
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
			case RmapPackage.URI_MATCHER__BASE:
				setBase((String) newValue);
				return;
			case RmapPackage.URI_MATCHER__VERSION_FORMAT:
				setVersionFormat((IVersionFormat) newValue);
				return;
			case RmapPackage.URI_MATCHER__VERSION_TYPE:
				setVersionType((String) newValue);
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
			case RmapPackage.URI_MATCHER__BASE:
				setBase(BASE_EDEFAULT);
				return;
			case RmapPackage.URI_MATCHER__VERSION_FORMAT:
				setVersionFormat(VERSION_FORMAT_EDEFAULT);
				return;
			case RmapPackage.URI_MATCHER__VERSION_TYPE:
				setVersionType(VERSION_TYPE_EDEFAULT);
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
	public String getBase() {
		return base;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */

	@Override
	public String getComponentType() {
		Provider provider = (Provider) eContainer();
		if (provider == null)
			return null;
		List<String> ctypes = provider.getComponentTypes();
		return ctypes.size() == 1 ? ctypes.get(0) : null;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */

	@Override
	public IVersionFormat getVersionFormat() {
		return versionFormat;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public String getVersionType() {
		return versionType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public void setBase(String newBase) {
		String oldBase = base;
		base = newBase;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RmapPackage.URI_MATCHER__BASE, oldBase, base));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public void setVersionFormat(IVersionFormat newVersionFormat) {
		IVersionFormat oldVersionFormat = versionFormat;
		versionFormat = newVersionFormat;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RmapPackage.URI_MATCHER__VERSION_FORMAT, oldVersionFormat, versionFormat));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public void setVersionType(String newVersionType) {
		String oldVersionType = versionType;
		versionType = newVersionType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RmapPackage.URI_MATCHER__VERSION_TYPE, oldVersionType, versionType));
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
		result.append(", versionFormat: ");
		result.append(versionFormat);
		result.append(", versionType: ");
		result.append(versionType);
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
		return RmapPackage.Literals.URI_MATCHER;
	}

} // URIMatcherImpl
