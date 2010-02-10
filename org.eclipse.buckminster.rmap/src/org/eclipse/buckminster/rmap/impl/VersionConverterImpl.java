/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap.impl;

import java.util.Collection;

import org.eclipse.buckminster.rmap.RmapPackage;
import org.eclipse.buckminster.rmap.Transform;
import org.eclipse.buckminster.rmap.VersionConverter;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Version Converter</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.rmap.impl.VersionConverterImpl#getType
 * <em>Type</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.rmap.impl.VersionConverterImpl#getVersionType
 * <em>Version Type</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.rmap.impl.VersionConverterImpl#getTransformers
 * <em>Transformers</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class VersionConverterImpl extends EObjectImpl implements VersionConverter {
	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final String TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected String type = TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getVersionType() <em>Version Type</em>}
	 * ' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getVersionType()
	 * @generated
	 * @ordered
	 */
	protected static final String VERSION_TYPE_EDEFAULT = "OSGi";

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
	 * The cached value of the '{@link #getTransformers() <em>Transformers</em>}
	 * ' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getTransformers()
	 * @generated
	 * @ordered
	 */
	protected EList<Transform> transformers;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected VersionConverterImpl() {
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
			case RmapPackage.VERSION_CONVERTER__TYPE:
				return getType();
			case RmapPackage.VERSION_CONVERTER__VERSION_TYPE:
				return getVersionType();
			case RmapPackage.VERSION_CONVERTER__TRANSFORMERS:
				return getTransformers();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RmapPackage.VERSION_CONVERTER__TRANSFORMERS:
				return ((InternalEList<?>) getTransformers()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case RmapPackage.VERSION_CONVERTER__TYPE:
				return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
			case RmapPackage.VERSION_CONVERTER__VERSION_TYPE:
				return VERSION_TYPE_EDEFAULT == null ? versionType != null : !VERSION_TYPE_EDEFAULT.equals(versionType);
			case RmapPackage.VERSION_CONVERTER__TRANSFORMERS:
				return transformers != null && !transformers.isEmpty();
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
			case RmapPackage.VERSION_CONVERTER__TYPE:
				setType((String) newValue);
				return;
			case RmapPackage.VERSION_CONVERTER__VERSION_TYPE:
				setVersionType((String) newValue);
				return;
			case RmapPackage.VERSION_CONVERTER__TRANSFORMERS:
				getTransformers().clear();
				getTransformers().addAll((Collection<? extends Transform>) newValue);
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
			case RmapPackage.VERSION_CONVERTER__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case RmapPackage.VERSION_CONVERTER__VERSION_TYPE:
				setVersionType(VERSION_TYPE_EDEFAULT);
				return;
			case RmapPackage.VERSION_CONVERTER__TRANSFORMERS:
				getTransformers().clear();
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
	public EList<Transform> getTransformers() {
		if (transformers == null) {
			transformers = new EObjectContainmentEList<Transform>(Transform.class, this, RmapPackage.VERSION_CONVERTER__TRANSFORMERS);
		}
		return transformers;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getType() {
		return type;
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
	public void setType(String newType) {
		String oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RmapPackage.VERSION_CONVERTER__TYPE, oldType, type));
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
			eNotify(new ENotificationImpl(this, Notification.SET, RmapPackage.VERSION_CONVERTER__VERSION_TYPE, oldVersionType, versionType));
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
		result.append(" (type: ");
		result.append(type);
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
		return RmapPackage.Literals.VERSION_CONVERTER;
	}

} // VersionConverterImpl
