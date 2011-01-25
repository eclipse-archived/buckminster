/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap.impl;

import java.util.Collection;
import java.util.List;

import org.eclipse.buckminster.model.common.util.VersionHelper;
import org.eclipse.buckminster.rmap.RmapPackage;
import org.eclipse.buckminster.rmap.Transform;
import org.eclipse.buckminster.rmap.VersionConverter;
import org.eclipse.buckminster.rmap.VersionSelectorType;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.equinox.p2.metadata.IVersionFormat;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.equinox.p2.metadata.VersionFormatException;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Version Converter</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.rmap.impl.VersionConverterImpl#getType
 * <em>Type</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.rmap.impl.VersionConverterImpl#getTransformers
 * <em>Transformers</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.rmap.impl.VersionConverterImpl#getVersionFormat
 * <em>Version Format</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.rmap.impl.VersionConverterImpl#getVersionType
 * <em>Version Type</em>}</li>
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
	protected static final VersionSelectorType TYPE_EDEFAULT = VersionSelectorType.BRANCH;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected VersionSelectorType type = TYPE_EDEFAULT;

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
	 * @generated NOT
	 */
	public String createIdentifier(Version version) {
		if (version == null)
			return null;

		String result = VersionHelper.getOriginal(version);
		List<Transform> trs = getTransformers();
		if (trs.size() > 0) {
			boolean matchFound = false;
			for (Transform transformer : trs) {
				String transformed = transformer.transformFrom(result);
				if (transformed != null) {
					matchFound = true;
					result = transformed;
				}
			}
			if (!matchFound)
				return null;
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Version createVersion(String identifier) {
		if (identifier == null)
			return null;

		List<Transform> trs = getTransformers();
		if (trs.size() > 0) {
			boolean matchFound = false;
			for (Transform transformer : trs) {
				String transformed = transformer.transformTo(identifier);
				if (transformed != null) {
					matchFound = true;
					identifier = transformed;
				}
			}
			if (!matchFound)
				return null;
		}
		if (versionFormat == null) {
			String vtype = getVersionType();
			if (vtype == null) {
				if (getType() == VersionSelectorType.BRANCH)
					try {
						setVersionFormat(Version.compile("S"));
					} catch (VersionFormatException e) {
						// Internal bogus. That shouldn't have failed
						throw new RuntimeException(e);
					}
				else
					setVersionFormat(VersionHelper.getOSGiFormat());
			} else {
				setVersionFormat(VersionHelper.getVersionType(vtype).getFormat());
			}
		}
		return versionFormat.parse(identifier);
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
			case RmapPackage.VERSION_CONVERTER__TRANSFORMERS:
				return getTransformers();
			case RmapPackage.VERSION_CONVERTER__VERSION_FORMAT:
				return getVersionFormat();
			case RmapPackage.VERSION_CONVERTER__VERSION_TYPE:
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
				return type != TYPE_EDEFAULT;
			case RmapPackage.VERSION_CONVERTER__TRANSFORMERS:
				return transformers != null && !transformers.isEmpty();
			case RmapPackage.VERSION_CONVERTER__VERSION_FORMAT:
				return VERSION_FORMAT_EDEFAULT == null ? versionFormat != null : !VERSION_FORMAT_EDEFAULT.equals(versionFormat);
			case RmapPackage.VERSION_CONVERTER__VERSION_TYPE:
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
	@SuppressWarnings("unchecked")
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case RmapPackage.VERSION_CONVERTER__TYPE:
				setType((VersionSelectorType) newValue);
				return;
			case RmapPackage.VERSION_CONVERTER__TRANSFORMERS:
				getTransformers().clear();
				getTransformers().addAll((Collection<? extends Transform>) newValue);
				return;
			case RmapPackage.VERSION_CONVERTER__VERSION_FORMAT:
				setVersionFormat((IVersionFormat) newValue);
				return;
			case RmapPackage.VERSION_CONVERTER__VERSION_TYPE:
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
			case RmapPackage.VERSION_CONVERTER__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case RmapPackage.VERSION_CONVERTER__TRANSFORMERS:
				getTransformers().clear();
				return;
			case RmapPackage.VERSION_CONVERTER__VERSION_FORMAT:
				setVersionFormat(VERSION_FORMAT_EDEFAULT);
				return;
			case RmapPackage.VERSION_CONVERTER__VERSION_TYPE:
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

	public VersionSelectorType getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public IVersionFormat getVersionFormat() {
		return versionFormat;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public String getVersionType() {
		return versionType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setType(VersionSelectorType newType) {
		VersionSelectorType oldType = type;
		type = newType == null ? TYPE_EDEFAULT : newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RmapPackage.VERSION_CONVERTER__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public void setVersionFormat(IVersionFormat newVersionFormat) {
		IVersionFormat oldVersionFormat = versionFormat;
		versionFormat = newVersionFormat;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RmapPackage.VERSION_CONVERTER__VERSION_FORMAT, oldVersionFormat, versionFormat));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

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
		return RmapPackage.Literals.VERSION_CONVERTER;
	}
} // VersionConverterImpl
